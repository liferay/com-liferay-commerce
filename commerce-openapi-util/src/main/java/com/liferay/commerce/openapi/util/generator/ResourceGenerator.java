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

package com.liferay.commerce.openapi.util.generator;

import com.liferay.commerce.openapi.util.ComponentDefinition;
import com.liferay.commerce.openapi.util.Content;
import com.liferay.commerce.openapi.util.Method;
import com.liferay.commerce.openapi.util.Parameter;
import com.liferay.commerce.openapi.util.Path;
import com.liferay.commerce.openapi.util.Response;
import com.liferay.commerce.openapi.util.Schema;
import com.liferay.commerce.openapi.util.util.StringUtils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author Igor Beslic
 * @author Ivica Cardic
 */
public class ResourceGenerator {

	public ResourceGenerator(String applicationName) {
		_applicationName = applicationName;
	}

	public String generateResourceGetters(List<Path> paths) {
		Iterator<Path> iterator = paths.iterator();

		StringBuilder sb = new StringBuilder();

		while (iterator.hasNext()) {
			Path path = iterator.next();

			sb.append("\t@Path(\"");
			sb.append(path.getPath());
			sb.append("\")\n");
			sb.append("\tpublic Object get");
			sb.append(StringUtils.upperCaseFirstChar(path.getName()));
			sb.append("() {\n");
			sb.append("\t\treturn _");
			sb.append(path.getName());
			sb.append("Resource;\n");
			sb.append("\t}\n");

			if (iterator.hasNext()) {
				sb.append("\n");
			}
		}

		return sb.toString();
	}

	public String generateResourceImports(
		String resourcePackage, List<Path> paths) {

		Iterator<Path> iterator = paths.iterator();

		StringBuilder sb = new StringBuilder();

		while (iterator.hasNext()) {
			Path path = iterator.next();

			sb.append("import ");
			sb.append(resourcePackage);
			sb.append(".");
			sb.append(StringUtils.upperCaseFirstChar(path.getName()));
			sb.append("Resource;\n");

			if (iterator.hasNext()) {
				sb.append("\n");
			}
		}

		return sb.toString();
	}

	public String generateResourceReferences(List<Path> paths) {
		Iterator<Path> iterator = paths.iterator();

		StringBuilder sb = new StringBuilder();

		while (iterator.hasNext()) {
			Path path = iterator.next();

			sb.append("\t@Reference\n");
			sb.append("\tprivate ");
			sb.append(StringUtils.upperCaseFirstChar(path.getName()));
			sb.append("Resource _");
			sb.append(path.getName());
			sb.append("Resource;");

			if (iterator.hasNext()) {
				sb.append("\n");
			}
		}

		return sb.toString();
	}

	public String toJavaxImports(List<Method> methods) {
		StringBuilder sb = new StringBuilder();

		Set<String> importedClasses = new HashSet<>();

		for (Method method : methods) {
			if (!importedClasses.contains(method.getHttpMethod())) {
				sb.append("import javax.ws.rs.");
				sb.append(method.getHttpMethod());
				sb.append(";\n");

				importedClasses.add(method.getHttpMethod());
			}

			List<Content> requestBody = method.getRequestBody();

			if (!requestBody.isEmpty() &&
				!importedClasses.contains("Consumes")) {

				sb.append("import javax.ws.rs.Consumes;\n");

				importedClasses.add("Consumes");
			}

			if (!importedClasses.contains("Produces")) {
				for (Response response : method.getResponses()) {
					if (response.hasContent()) {
						sb.append("import javax.ws.rs.Produces;\n");

						importedClasses.add("Produces");

						break;
					}
				}
			}

			for (Parameter parameter : method.getParameters()) {
				String location = parameter.getLocation();

				if (location.equals("body")) {
					continue;
				}

				if (importedClasses.contains(location)) {
					continue;
				}

				sb.append("import javax.ws.rs.");
				sb.append(StringUtils.upperCaseFirstChar(location));
				sb.append("Param;\n");

				importedClasses.add(location);
			}
		}

		return sb.toString();
	}

	public String toModelImportStatements(
		String modelPackage, Set<String> referencedModels) {

		StringBuilder sb = new StringBuilder();

		for (String referencedModel : referencedModels) {
			sb.append("import ");
			sb.append(modelPackage);
			sb.append(".");
			sb.append(StringUtils.upperCaseFirstChar(referencedModel));
			sb.append("DTO;\n");
		}

		return sb.toString();
	}

	public String toResourceImplementationMethods(
		List<Method> methods, Set<ComponentDefinition> componentDefinitions) {

		StringBuilder sb = new StringBuilder();

		Iterator<Method> iterator = methods.iterator();

		while (iterator.hasNext()) {
			Method method = iterator.next();

			sb.append("\t@Override\n");

			sb.append(
				_getMethodDeclaration(method, false, componentDefinitions));

			sb.append(" {\n");

			if (method.hasResponseContent()) {
				ComponentDefinition schemaComponentDefinition =
					_getSchemaComponentDefinition(method, componentDefinitions);

				if (schemaComponentDefinition.isArray()) {
					sb.append("\t\treturn Collections.emptyList();\n");
				}
				else {
					sb.append("\t\treturn null;\n");
				}
			}
			else {
				sb.append("\t\treturn Response.ok().build();\n");
			}

			sb.append("\t}\n");

			if (iterator.hasNext()) {
				sb.append("\n");
			}
		}

		return sb.toString();
	}

	public String toResourceInterfaceMethods(
		List<Method> methods, Set<ComponentDefinition> componentDefinitions) {

		StringBuilder sb = new StringBuilder();

		Iterator<Method> iterator = methods.iterator();

		while (iterator.hasNext()) {
			Method method = iterator.next();

			sb.append("\t@Path(\"");
			sb.append(method.getPath());
			sb.append("\")\n");
			sb.append("\t@");
			sb.append(method.getHttpMethod());
			sb.append("\n");

			sb.append(_getProducesAnnotation(method.getResponses()));

			sb.append(_getConsumesAnnotation(method.getRequestBody()));

			sb.append(_getRequiresScopeAnnotation(method.getHttpMethod()));

			sb.append(
				_getMethodDeclaration(method, true, componentDefinitions));

			sb.append(";\n");

			if (iterator.hasNext()) {
				sb.append("\n");
			}
		}

		return sb.toString();
	}

	private String _getConsumesAnnotation(List<Content> requestBody) {
		if (requestBody.isEmpty()) {
			return "";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("\t@Consumes(");

		sb.append(_getMimeTypes(requestBody));

		sb.append(")\n");

		return sb.toString();
	}

	private String _getMethodDeclaration(
		Method method, boolean annotateParameter,
		Set<ComponentDefinition> componentDefinitions) {

		StringBuilder sb = new StringBuilder();

		sb.append("\tpublic ");

		sb.append(_getReturnValue(method, componentDefinitions));

		sb.append(method.getName());

		sb.append("(");

		List<Parameter> parameters = method.getParameters();

		Iterator<Parameter> parameterIterator = parameters.iterator();

		while (parameterIterator.hasNext()) {
			if (annotateParameter) {
				sb.append(
					_parameterGenerator.toAnnotatedMethodParameter(
						parameterIterator.next()));
			}
			else {
				sb.append(
					_parameterGenerator.toMethodParameter(
						parameterIterator.next()));
			}

			if (parameterIterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append(")");

		return sb.toString();
	}

	private String _getMimeTypes(List<Content> contents) {
		StringBuilder sb = new StringBuilder();

		sb.append("\"");

		if (contents.size() > 1) {
			sb.append("{");
		}

		for (int i = 0; i < contents.size(); i++) {
			Content content = contents.get(i);

			sb.append(content.getMimeType());

			if (i < (contents.size() - 1)) {
				sb.append(",");
			}
		}

		if (contents.size() > 1) {
			sb.append("}");
		}

		sb.append("\"");

		return sb.toString();
	}

	private String _getProducesAnnotation(List<Response> responses) {
		Stream<Response> stream = responses.stream();

		Response response = stream.filter(
			Response::hasContent
		).findFirst(
		).orElse(
			null
		);

		if (response == null) {
			return "";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("\t@Produces(");

		List<Content> contents = response.getContents();

		sb.append(_getMimeTypes(contents));

		sb.append(")\n");

		return sb.toString();
	}

	private String _getRequiresScopeAnnotation(String httpMethod) {
		StringBuilder sb = new StringBuilder();

		sb.append("\t@RequiresScope(\"");
		sb.append(_applicationName);
		sb.append(".");

		if (Objects.equals(httpMethod, "GET")) {
			sb.append("read");
		}
		else {
			sb.append("write");
		}

		sb.append("\")\n");

		return sb.toString();
	}

	private Content _getResponseContent(List<Response> responses) {
		for (Response response : responses) {
			List<Content> contents = response.getContents();

			if (!contents.isEmpty()) {
				return contents.get(0);
			}
		}

		return null;
	}

	private String _getReturnValue(
		Method method, Set<ComponentDefinition> componentDefinitions) {

		StringBuilder sb = new StringBuilder();

		if (method.hasResponseContent()) {
			ComponentDefinition schemaComponentDefinition =
				_getSchemaComponentDefinition(method, componentDefinitions);

			String itemsReferenceModel =
				schemaComponentDefinition.getItemsReferencedModel();

			if (schemaComponentDefinition.isArray() &&
				(itemsReferenceModel != null)) {

				schemaComponentDefinition = _getSchemaComponentDefinition(
					itemsReferenceModel, componentDefinitions);

				sb.append("List<");
				sb.append(schemaComponentDefinition.getName());
				sb.append("DTO> ");
			}
			else if (schemaComponentDefinition.isObject()) {
				sb.append(schemaComponentDefinition.getName());
				sb.append("DTO ");
			}
			else {
				sb.append("Response ");
			}
		}
		else {
			sb.append("Response ");
		}

		return sb.toString();
	}

	private ComponentDefinition _getSchemaComponentDefinition(
		Method method, Set<ComponentDefinition> componentDefinitions) {

		Content content = _getResponseContent(method.getResponses());

		Schema schema = content.getSchema();

		return _getSchemaComponentDefinition(
			schema.getReferencedModel(), componentDefinitions);
	}

	private ComponentDefinition _getSchemaComponentDefinition(
		String name, Set<ComponentDefinition> componentDefinitions) {

		for (ComponentDefinition componentDefinition : componentDefinitions) {
			if (componentDefinition.isParameter()) {
				continue;
			}

			if (Objects.equals(name, componentDefinition.getName())) {
				return componentDefinition;
			}
		}

		return null;
	}

	private final String _applicationName;
	private final ParameterGenerator _parameterGenerator =
		new ParameterGenerator();

}