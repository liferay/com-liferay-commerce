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

package com.liferay.commerce.openapi.util;

import com.liferay.commerce.openapi.util.importer.exception.ImporterException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Igor Beslic
 */
public class Method {

	public Method(
		String name, List<Content> requestBody, String httpMethod,
		String absolutePath, List<Parameter> parameters,
		List<Response> responses, List<Extension> extensions) {

		_name = name;

		if (requestBody != null) {
			_requestBody.addAll(requestBody);
		}

		_httpMethod = httpMethod.toUpperCase();
		_absolutePath = absolutePath;

		_parameters.addAll(parameters);

		_responses.addAll(responses);

		boolean hasResponseContent = false;

		for (Response response : responses) {
			if (response.hasContent()) {
				hasResponseContent = true;

				break;
			}
		}

		_extensions.addAll(extensions);

		_hasResponseContent = hasResponseContent;
	}

	public String getAbsolutePath() {
		return _absolutePath;
	}

	public List<Extension> getExtensions() {
		return new ArrayList<>(_extensions);
	}

	public String getHttpMethod() {
		return _httpMethod;
	}

	public String getModelPath() {
		if (_modelPath != null) {
			return _modelPath;
		}

		Matcher matcher = _pathPattern.matcher(_absolutePath);

		if (!matcher.find()) {
			throw new ImporterException(
				"Unable to process path " + _absolutePath);
		}

		_modelPath = matcher.group(1);

		return _modelPath;
	}

	public String getName() {
		return _name;
	}

	public List<Parameter> getParameters() {
		return new ArrayList<>(_parameters);
	}

	public String getPath() {
		if (_path != null) {
			return _path;
		}

		String modelPath = getModelPath();

		_path = _absolutePath.substring(
			_absolutePath.indexOf(modelPath) + modelPath.length());

		return _path;
	}

	public List<Content> getRequestBody() {
		return _requestBody;
	}

	public List<Response> getResponses() {
		return _responses;
	}

	public String getReturnType(Set<ComponentDefinition> componentDefinitions) {
		ComponentDefinition schemaComponentDefinition =
			_getSchemaComponentDefinition(componentDefinitions);

		if (schemaComponentDefinition == null) {
			return null;
		}

		if (hasCollectionReturnType(componentDefinitions)) {
			String itemsReferenceModel =
				schemaComponentDefinition.getItemsReferencedModel();

			schemaComponentDefinition = _getSchemaComponentDefinition(
				itemsReferenceModel, componentDefinitions);
		}

		return schemaComponentDefinition.getName();
	}

	public boolean hasCollectionReturnType(
		Set<ComponentDefinition> componentDefinitions) {

		ComponentDefinition schemaComponentDefinition =
			_getSchemaComponentDefinition(componentDefinitions);

		if (schemaComponentDefinition == null) {
			return false;
		}

		if (schemaComponentDefinition.isArray()) {
			return true;
		}

		return false;
	}

	public boolean hasExtensions() {
		if (_extensions.isEmpty()) {
			return false;
		}

		return true;
	}

	public boolean hasImplicitPaginationContext(
		Set<ComponentDefinition> componentDefinitions) {

		if (!hasPaginationContextExtension()) {
			ComponentDefinition schemaComponentDefinition =
				_getSchemaComponentDefinition(componentDefinitions);

			if ((schemaComponentDefinition != null) &&
				schemaComponentDefinition.isArray()) {

				return true;
			}
		}

		return false;
	}

	public boolean hasPaginationContextExtension() {
		for (Extension extension : _extensions) {
			if (extension.isPaginationContext()) {
				return true;
			}
		}

		return false;
	}

	public boolean hasResponseContent() {
		return _hasResponseContent;
	}

	@Override
	public String toString() {
		if (_toString != null) {
			return _toString;
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{path: ");
		sb.append(getPath());
		sb.append(", httpMethod: ");
		sb.append(_httpMethod);
		sb.append(", ");

		for (Response response : _responses) {
			if (response.hasContent()) {
				sb.append("produces: ");
				sb.append(response);
				sb.append(", ");
			}
		}

		for (Content content : _requestBody) {
			sb.append("consumes: ");
			sb.append(content);
			sb.append(", ");
		}

		sb.append("name: ");
		sb.append(_name);
		sb.append(", parameters: {");

		Iterator<Parameter> iterator = _parameters.iterator();

		while (iterator.hasNext()) {
			sb.append(iterator.next());

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		_toString = sb.toString();

		return _toString;
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

	private ComponentDefinition _getSchemaComponentDefinition(
		Set<ComponentDefinition> componentDefinitions) {

		Content content = _getResponseContent(getResponses());

		if (content == null) {
			return null;
		}

		Schema schema = content.getSchema();

		ComponentDefinition schemaComponentDefinition =
			_getSchemaComponentDefinition(
				schema.getReferencedModel(), componentDefinitions);

		if ("array".equals(schema.getType())) {
			return ComponentDefinition.asComponentTypeArray(
				schemaComponentDefinition, schema.getReference());
		}

		return schemaComponentDefinition;
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

	private final String _absolutePath;
	private final List<Extension> _extensions = new ArrayList<>();
	private final boolean _hasResponseContent;
	private final String _httpMethod;
	private String _modelPath;
	private final String _name;
	private final List<Parameter> _parameters = new ArrayList<>();
	private String _path;
	private Pattern _pathPattern = Pattern.compile(
		"^/(\\w+)(/\\{?\\w+\\}?)*/?$");
	private final List<Content> _requestBody = new ArrayList<>();
	private final List<Response> _responses = new ArrayList<>();
	private String _toString;

}