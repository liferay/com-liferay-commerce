/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.commerce.headless.graphql.internal.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import com.liferay.commerce.headless.graphql.internal.schema.ProductDataFetcher;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.servlet.PortalSessionThreadLocal;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.Portal;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLError;
import graphql.GraphqlErrorHelper;

import graphql.introspection.IntrospectionQuery;

import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

import java.io.IOException;
import java.io.InputStreamReader;

import java.nio.charset.Charset;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(
	immediate = true,
	property = {
		"osgi.http.whiteboard.context.path=/test",
		"osgi.http.whiteboard.servlet.name=com.liferay.commerce.headless.graphql.internal.servlet.CommerceGraphQLServlet",
		"osgi.http.whiteboard.servlet.pattern=/test/*"
	},
	service = Servlet.class
)
public class CommerceGraphQLServlet extends HttpServlet {

	@Activate
	protected void activate(BundleContext bundleContext) {
		_graphQL = _buildGraphQL();

		_objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		_objectMapper.registerModule(new Jdk8Module());
	}

	@Override
	protected void doGet(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException, ServletException {

		ExecutionResult executionResult = _graphQL.execute(
			IntrospectionQuery.INTROSPECTION_QUERY);

		String json = _toString(executionResult);

		httpServletResponse.setContentType(ContentTypes.APPLICATION_JSON);

		ServletResponseUtil.write(httpServletResponse, json);
	}

	@Override
	protected void doPost(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException, ServletException {

		try {
			HttpSession httpSession = httpServletRequest.getSession();

			if (PortalSessionThreadLocal.getHttpSession() == null) {
				PortalSessionThreadLocal.setHttpSession(httpSession);
			}

			long companyId = _portal.getCompanyId(httpServletRequest);

			User user = _portal.getUser(httpServletRequest);

			if (user == null) {
				user = _userLocalService.getDefaultUser(companyId);
			}

			PermissionChecker permissionChecker =
				PermissionCheckerFactoryUtil.create(user);

			PermissionThreadLocal.setPermissionChecker(permissionChecker);

			String body = IOUtils.toString(
				httpServletRequest.getInputStream(), Charset.defaultCharset());

			if (_log.isDebugEnabled()) {
				_log.debug("Post body: " + body);
			}

			GraphQLRequest graphQLRequest = _objectMapper.readValue(
				body, GraphQLRequest.class);

			ServiceContext serviceContext = null;

			serviceContext = ServiceContextFactory.getInstance(
				httpServletRequest);

			ExecutionInput.Builder executionInputBuilder =
				ExecutionInput.newExecutionInput();

			ExecutionInput executionInput = executionInputBuilder.context(
				serviceContext
			).operationName(
				graphQLRequest.getOperationName()
			).query(
				graphQLRequest.getQuery()
			).variables(
				graphQLRequest.getVariables()
			).build();

			ExecutionResult executionResult = _graphQL.execute(executionInput);

			String json = _toString(executionResult);

			httpServletResponse.setContentType(ContentTypes.APPLICATION_JSON);

			ServletResponseUtil.write(httpServletResponse, json);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private GraphQL _buildGraphQL() {
		SchemaGenerator schemaGenerator = new SchemaGenerator();

		GraphQL.Builder graphQLBuilder = GraphQL.newGraphQL(
			schemaGenerator.makeExecutableSchema(
				_buildTypeDefinitionRegistry(), _buildRuntimeWiring()));

		return graphQLBuilder.build();
	}

	private ObjectMapper _buildObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();

		objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

		objectMapper.registerModule(new Jdk8Module());

		return objectMapper;
	}

	private RuntimeWiring _buildRuntimeWiring() {
		RuntimeWiring.Builder runtimeWiringBuilder =
			RuntimeWiring.newRuntimeWiring();

		runtimeWiringBuilder.type(
			"Query",
			builder -> builder.dataFetcher("products", _productDataFetcher));

		return runtimeWiringBuilder.build();
	}

	private TypeDefinitionRegistry _buildTypeDefinitionRegistry() {
		SchemaParser schemaParser = new SchemaParser();

		Class<?> clazz = getClass();

		return schemaParser.parse(
			new InputStreamReader(
				clazz.getResourceAsStream("commerce.graphqls"),
				Charset.defaultCharset()));
	}

	private String _toString(ExecutionResult executionResult)
		throws IOException {

		Map<String, Object> map = new HashMap<>();

		map.put("data", executionResult.getData());

		List<GraphQLError> graphQLErrors = executionResult.getErrors();

		if ((graphQLErrors != null) && !graphQLErrors.isEmpty()) {
			Stream<GraphQLError> stream = graphQLErrors.stream();

			map.put(
				"errors",
				stream.map(
					GraphqlErrorHelper::toSpecification
				).collect(
					Collectors.toList()
				));
		}

		return _objectMapper.writeValueAsString(map);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceGraphQLServlet.class);

	private GraphQL _graphQL;
	private final ObjectMapper _objectMapper = _buildObjectMapper();

	@Reference
	private Portal _portal;

	@Reference
	private ProductDataFetcher _productDataFetcher;

	@Reference
	private UserLocalService _userLocalService;

	private static class GraphQLRequest {

		public String getOperationName() {
			return _operationName;
		}

		public String getQuery() {
			return _query;
		}

		public Map<String, Object> getVariables() {
			return _variables;
		}

		@SuppressWarnings("unused")
		public void setOperationName(String operationName) {
			_operationName = operationName;
		}

		@SuppressWarnings("unused")
		public void setQuery(String query) {
			_query = query;
		}

		@SuppressWarnings("unused")
		public void setVariables(Map<String, Object> variables) {
			_variables = variables;
		}

		private String _operationName;
		private String _query;
		private Map<String, Object> _variables = new HashMap<>();

	}

}