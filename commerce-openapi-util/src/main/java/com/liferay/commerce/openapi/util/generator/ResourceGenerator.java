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
import com.liferay.commerce.openapi.util.Extension;
import com.liferay.commerce.openapi.util.Method;
import com.liferay.commerce.openapi.util.Parameter;
import com.liferay.commerce.openapi.util.Path;
import com.liferay.commerce.openapi.util.Response;
import com.liferay.commerce.openapi.util.util.StringUtils;

import java.io.IOException;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Igor Beslic
 * @author Ivica Cardic
 */
public class ResourceGenerator extends BaseSourceGenerator {

	public ResourceGenerator(
		String applicationName, String author, String contextOutputPath,
		String moduleOutputPath, String modelPackagePath,
		boolean overwriteImplementation, String resourceInterfacePackagePath,
		String resourcePackagePath) {

		_applicationName = applicationName;
		_author = author;
		_contextOutputPath = contextOutputPath;
		_moduleOutputPath = moduleOutputPath;
		_modelPackagePath = modelPackagePath;
		_overwriteImplementation = overwriteImplementation;
		_resourceInterfacePackagePath = resourceInterfacePackagePath;
		_resourcePackagePath = resourcePackagePath;
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

	public void writeResourceSources(
			String version, Path path,
			Set<ComponentDefinition> componentDefinitions)
		throws IOException {

		_writeResourceInterfaceSource(version, path, componentDefinitions);

		_writeResourceImplementationSource(version, path, componentDefinitions);
	}

	protected String getReturnType(
		Method method, Set<ComponentDefinition> componentDefinitions) {

		StringBuilder sb = new StringBuilder();

		if (method.hasResponseContent()) {
			String returnType = method.getReturnType(componentDefinitions);

			if (method.hasCollectionReturnType(componentDefinitions)) {
				sb.append("CollectionDTO<");
				sb.append(returnType);
				sb.append("DTO> ");
			}
			else {
				sb.append(returnType);
				sb.append("DTO ");
			}
		}
		else {
			sb.append("Response ");
		}

		return sb.toString();
	}

	protected String toJavaxImports(
		List<Method> methods, Set<ComponentDefinition> componentDefinitions) {

		Set<String> importStatements = new HashSet<>();

		for (Method method : methods) {
			importStatements.add(
				String.format(
					"import javax.ws.rs.%s;%n", method.getHttpMethod()));

			List<Content> requestBody = method.getRequestBody();

			if (!requestBody.isEmpty()) {
				importStatements.add("import javax.ws.rs.Consumes;\n");
			}

			for (Response response : method.getResponses()) {
				if (response.hasContent()) {
					importStatements.add("import javax.ws.rs.Produces;\n");

					break;
				}
			}

			for (Parameter parameter : method.getParameters()) {
				String location = parameter.getLocation();

				if (location.equals("body")) {
					continue;
				}

				importStatements.add(
					String.format(
						"import javax.ws.rs.%sParam;%n",
						StringUtils.upperCaseFirstChar(location)));
			}

			if (method.hasExtensions()) {
				importStatements.add("import javax.ws.rs.core.Context;\n");

				for (Extension extension : method.getExtensions()) {
					Extension.ExtensionType extensionType =
						extension.getExtensionType();

					Extension.Provider provider = extensionType.getProvider();

					List<Parameter> parameters = extension.getParameters();

					if (parameters.isEmpty()) {
						importStatements.add(
							String.format(
								"import %s;%n", provider.getModelFQCN()));
					}
					else {
						importStatements.add(
							String.format(
								"import %s.%s;%n", _contextOutputPath,
								StringUtils.upperCaseFirstChar(
									provider.getModelName())));
					}
				}
			}

			if (method.hasImplicitPaginationContext(componentDefinitions)) {
				importStatements.add("import javax.ws.rs.core.Context;\n");

				Extension.ExtensionType paginationExtensionType =
					Extension.ExtensionType.PAGINATION_CONTEXT;

				Extension.Provider provider =
					paginationExtensionType.getProvider();

				importStatements.add(
					String.format("import %s;%n", provider.getModelFQCN()));
			}
		}

		StringBuilder sb = new StringBuilder();

		for (String importStatement : importStatements) {
			sb.append(importStatement);
		}

		return sb.toString();
	}

	protected String toResourceImplementationMethods(
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
				if (method.hasCollectionReturnType(componentDefinitions)) {
					sb.append("\t\treturn new CollectionDTO(");
					sb.append("Collections.emptyList(), 0);\n");
				}
				else {
					sb.append("\t\treturn new ");

					sb.append(method.getReturnType(componentDefinitions));

					sb.append("DTO();\n");
				}
			}
			else {
				sb.append("\t\tResponse.ResponseBuilder responseBuilder = ");
				sb.append(_getResponse(method.getResponses()));
				sb.append("\t\treturn responseBuilder.build();\n");
			}

			sb.append("\t}\n");

			if (iterator.hasNext()) {
				sb.append("\n");
			}
		}

		return sb.toString();
	}

	protected String toResourceInterfaceMethods(
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

	private String _getContextParametersDeclaration(
		Method method, boolean annotateParameter) {

		List<Extension> extensions = method.getExtensions();

		Iterator<Extension> iterator = extensions.iterator();

		StringBuilder sb = new StringBuilder();

		while (iterator.hasNext()) {
			Extension extension = iterator.next();

			Extension.ExtensionType extensionType =
				extension.getExtensionType();

			Extension.Provider provider = extensionType.getProvider();

			if (annotateParameter) {
				sb.append(
					_parameterGenerator.toAnnotatedMethodContextParameter(
						provider.getModelName()));
			}
			else {
				sb.append(
					_parameterGenerator.toMethodParameter(
						provider.getModelName()));
			}

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		return sb.toString();
	}

	private String _getMethodDeclaration(
		Method method, boolean annotateParameter,
		Set<ComponentDefinition> componentDefinitions) {

		StringBuilder sb = new StringBuilder();

		sb.append("\tpublic ");

		sb.append(getReturnType(method, componentDefinitions));

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

		if (method.hasExtensions()) {
			if (!parameters.isEmpty()) {
				sb.append(", ");
			}

			sb.append(
				_getContextParametersDeclaration(method, annotateParameter));
		}

		if (method.hasImplicitPaginationContext(componentDefinitions)) {
			if (!parameters.isEmpty() || method.hasExtensions()) {
				sb.append(", ");
			}

			sb.append(
				_getPagingContextParametersDeclaration(annotateParameter));
		}

		sb.append(") throws Exception");

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

	private String _getPagingContextParametersDeclaration(
		boolean annotateParameter) {

		Extension.ExtensionType paginationContextExtension =
			Extension.ExtensionType.PAGINATION_CONTEXT;

		Extension.Provider provider = paginationContextExtension.getProvider();

		if (annotateParameter) {
			return _parameterGenerator.toAnnotatedMethodContextParameter(
				provider.getModelName());
		}

		return _parameterGenerator.toMethodParameter(provider.getModelName());
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

	private String _getResponse(List<Response> responses) {
		for (Response response : responses) {
			if (response.getStatus() == 202) {
				return "Response.accepted();\n";
			}
			else if (response.getStatus() == 204) {
				return "Response.noContent();\n";
			}
		}

		return "Response.ok();\n";
	}

	private String _toModelImportStatements(
		String modelPackage, Set<String> referencedModels) {

		StringBuilder sb = new StringBuilder();

		for (String referencedModel : referencedModels) {
			sb.append("import ");
			sb.append(modelPackage);
			sb.append(".");
			sb.append(StringUtils.upperCaseFirstChar(referencedModel));
			sb.append("DTO;\n");
		}

		sb.append("import ");
		sb.append(modelPackage);
		sb.append(".CollectionDTO;\n");

		return sb.toString();
	}

	private void _writeResourceImplementationSource(
			String version, Path path,
			Set<ComponentDefinition> componentDefinitions)
		throws IOException {

		String resourceImplementationClassName = StringUtils.upperCaseFirstChar(
			path.getName() + "ResourceImpl");

		String componentSourcePath = getClassSourcePath(
			_moduleOutputPath, resourceImplementationClassName + ".java",
			_resourcePackagePath);

		if (!_overwriteImplementation && exists(componentSourcePath)) {
			_logger.warn(
				"Resource implementation source file {} is not generated. " +
					"Configure overwrite mode in config file.",
				componentSourcePath);

			return;
		}

		String osgiResourceComponent = getTemplate(
			_TEMPLATE_FILE_RESOURCE_IMPLEMENTATION);

		osgiResourceComponent = osgiResourceComponent.replace(
			"${PACKAGE}", _resourcePackagePath);

		StringBuilder sb = new StringBuilder();

		sb.append(
			_toModelImportStatements(
				_modelPackagePath, path.getReferencedModels()));

		sb.append("import ");
		sb.append(_resourceInterfacePackagePath);
		sb.append(".");
		sb.append(StringUtils.upperCaseFirstChar(path.getName()));
		sb.append("Resource;");

		osgiResourceComponent = osgiResourceComponent.replace(
			"${IMPORT_STATEMENTS}", sb.toString());

		osgiResourceComponent = osgiResourceComponent.replace(
			"${API_VERSION}", version);

		osgiResourceComponent = osgiResourceComponent.replace(
			"${AUTHOR}", _author);

		osgiResourceComponent = osgiResourceComponent.replace(
			"${APPLICATION_NAME}", _applicationName);

		osgiResourceComponent = osgiResourceComponent.replace(
			"${MODEL_IMPORT_STATEMENTS_JAVAX}",
			toJavaxImports(path.getMethods(), componentDefinitions));

		osgiResourceComponent = osgiResourceComponent.replace(
			"${MODEL_RESOURCE_IMPLEMENTATION_CLASS}",
			resourceImplementationClassName);

		String resourceInterfaceClassName = StringUtils.upperCaseFirstChar(
			path.getName() + "Resource");

		osgiResourceComponent = osgiResourceComponent.replace(
			"${MODEL_RESOURCE_INTERFACE_CLASS}", resourceInterfaceClassName);

		osgiResourceComponent = osgiResourceComponent.replace(
			"${PATH}", path.getName());

		osgiResourceComponent = osgiResourceComponent.replace(
			"${METHODS}",
			toResourceImplementationMethods(
				path.getMethods(), componentDefinitions));

		writeSource(osgiResourceComponent, componentSourcePath);
	}

	private void _writeResourceInterfaceSource(
			String version, Path path,
			Set<ComponentDefinition> componentDefinitions)
		throws IOException {

		String osgiResourceComponent = getTemplate(
			_TEMPLATE_FILE_RESOURCE_INTERFACE);

		osgiResourceComponent = osgiResourceComponent.replace(
			"${PACKAGE}", _resourceInterfacePackagePath);

		osgiResourceComponent = osgiResourceComponent.replace(
			"${MODEL_IMPORT_STATEMENTS}",
			_toModelImportStatements(
				_modelPackagePath, path.getReferencedModels()));

		osgiResourceComponent = osgiResourceComponent.replace(
			"${API_VERSION}", version);

		osgiResourceComponent = osgiResourceComponent.replace(
			"${AUTHOR}", _author);

		osgiResourceComponent = osgiResourceComponent.replace(
			"${MODEL_IMPORT_STATEMENTS_JAVAX}",
			toJavaxImports(path.getMethods(), componentDefinitions));

		osgiResourceComponent = osgiResourceComponent.replace(
			"${PATH}", path.getName());

		String resourceInterfaceClassName = StringUtils.upperCaseFirstChar(
			path.getName() + "Resource");

		osgiResourceComponent = osgiResourceComponent.replace(
			"${MODEL_RESOURCE_INTERFACE_CLASS}", resourceInterfaceClassName);

		osgiResourceComponent = osgiResourceComponent.replace(
			"${METHODS}",
			toResourceInterfaceMethods(
				path.getMethods(), componentDefinitions));

		String componentSourcePath = getClassSourcePath(
			_moduleOutputPath, resourceInterfaceClassName + ".java",
			_resourceInterfacePackagePath);

		writeSource(osgiResourceComponent, componentSourcePath);
	}

	private static final String _TEMPLATE_FILE_RESOURCE_IMPLEMENTATION =
		"ResourceImpl.java.tpl";

	private static final String _TEMPLATE_FILE_RESOURCE_INTERFACE =
		"Resource.java.tpl";

	private static final Logger _logger = LoggerFactory.getLogger(
		ResourceGenerator.class);

	private final String _applicationName;
	private final String _author;
	private final String _contextOutputPath;
	private final String _modelPackagePath;
	private final String _moduleOutputPath;
	private final boolean _overwriteImplementation;
	private final ParameterGenerator _parameterGenerator =
		new ParameterGenerator();
	private final String _resourceInterfacePackagePath;
	private final String _resourcePackagePath;

}