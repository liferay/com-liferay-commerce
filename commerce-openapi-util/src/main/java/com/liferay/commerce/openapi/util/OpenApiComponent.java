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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Igor Beslic
 * @author Ivica Cardic
 */
public class OpenApiComponent {

	public static OpenApiComponent asComponentTypeArray(
		OpenApiComponent openApiComponent, String itemsReference) {

		OpenApiComponentBuilder openApiComponentBuilder =
			new OpenApiComponentBuilder();

		openApiComponentBuilder.name(openApiComponent._name);
		openApiComponentBuilder.openApiProperties(
			openApiComponent._openApiProperties);
		openApiComponentBuilder.type("array");
		openApiComponentBuilder.itemsReference(itemsReference);

		return openApiComponentBuilder.build();
	}

	public OpenApiFormat getItemsFormat() {
		return _itemsFormat;
	}

	public String getItemsReference() {
		return _itemsReference;
	}

	public String getItemsReferencedModel() {
		return _itemsReferencedModel;
	}

	public OpenApiType getItemsType() {
		return _itemsType;
	}

	public String getName() {
		return _name;
	}

	public List<OpenApiProperty> getOpenApiProperties() {
		return new ArrayList<>(_openApiProperties);
	}

	public Parameter getParameter() {
		return _parameter;
	}

	public boolean isArray() {
		if (_componentType == ComponentType.ARRAY) {
			return true;
		}

		return false;
	}

	public boolean isDictionary() {
		if (_componentType == ComponentType.DICTIONARY) {
			return true;
		}

		return false;
	}

	public boolean isObject() {
		if (_componentType == ComponentType.OBJECT) {
			return true;
		}

		return false;
	}

	public boolean isParameter() {
		if (_componentType == ComponentType.PARAMETER) {
			return true;
		}

		return false;
	}

	@Override
	public String toString() {
		if (_toString != null) {
			return _toString;
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{name=");
		sb.append(_name);
		sb.append(", propertyDefinitions={");

		Iterator<OpenApiProperty> iterator = _openApiProperties.iterator();

		while (iterator.hasNext()) {
			sb.append(iterator.next());

			if (iterator.hasNext()) {
				sb.append(",");
			}
		}

		sb.append("}");
		sb.append(", componentType=");
		sb.append(_componentType);
		sb.append(", itemsReference=");
		sb.append(_itemsReference);
		sb.append(", itemsReferencedModel=");
		sb.append(_itemsReferencedModel);
		sb.append("}");

		_toString = sb.toString();

		return _toString;
	}

	public static class OpenApiComponentBuilder {

		public OpenApiComponent build() {
			ComponentType componentType = ComponentType.OBJECT;

			if (_parameter != null) {
				componentType = ComponentType.PARAMETER;
			}

			if (_type != null) {
				if (_type.equals("array")) {
					componentType = ComponentType.ARRAY;
				}
				else if (_type.equals("dictionary")) {
					componentType = ComponentType.DICTIONARY;
				}
			}

			String itemsReferencedModel = _itemsReference;

			if (_itemsReference != null) {
				Matcher matcher = _itemsReferenceModelPattern.matcher(
					_itemsReference);

				if (matcher.find()) {
					itemsReferencedModel = matcher.group(2);
				}
			}

			OpenApiType itemsOpenApiType = _fromOpenApiDefinition(_itemsType);

			OpenApiFormat itemsOpenApiFormat =
				OpenApiFormat.fromOpenApiTypeAndFormat(
					itemsOpenApiType, _itemsFormat);

			List<OpenApiProperty> openApiProperties = new ArrayList<>();

			if (_openApiProperties != null) {
				openApiProperties.addAll(_openApiProperties);
			}

			return new OpenApiComponent(
				_name, componentType, _itemsReference, itemsReferencedModel,
				itemsOpenApiType, itemsOpenApiFormat, openApiProperties,
				_parameter);
		}

		public OpenApiComponentBuilder itemsFormat(String openApiFormatName) {
			_itemsFormat = openApiFormatName;

			return this;
		}

		public OpenApiComponentBuilder itemsReference(String itemsReference) {
			_itemsReference = itemsReference;

			return this;
		}

		public OpenApiComponentBuilder itemsType(String openApiTypeName) {
			_itemsType = openApiTypeName;

			return this;
		}

		public OpenApiComponentBuilder name(String name) {
			_name = name;

			return this;
		}

		public OpenApiComponentBuilder openApiProperties(
			List<OpenApiProperty> openApiProperties) {

			_openApiProperties = new ArrayList<>(openApiProperties);

			return this;
		}

		public OpenApiComponentBuilder parameter(Parameter parameter) {
			_parameter = parameter;

			return this;
		}

		public OpenApiComponentBuilder type(String type) {
			_type = type;

			return this;
		}

		private OpenApiType _fromOpenApiDefinition(
			String openApiTypeDefinition) {

			if (openApiTypeDefinition == null) {
				return null;
			}

			return OpenApiType.fromDefinition(openApiTypeDefinition);
		}

		private String _itemsFormat;
		private String _itemsReference;
		private String _itemsType;
		private String _name;
		private List<OpenApiProperty> _openApiProperties;
		private Parameter _parameter;
		private String _type;

	}

	public enum ComponentType {

		ARRAY, DICTIONARY, OBJECT, PARAMETER

	}

	private OpenApiComponent(
		String name, ComponentType componentType, String itemsReference,
		String itemsReferencedModel, OpenApiType itemsOpenApiType,
		OpenApiFormat itemsOpenApiFormat,
		List<OpenApiProperty> openApiProperties, Parameter parameter) {

		_name = name;
		_componentType = componentType;
		_itemsReference = itemsReference;
		_itemsReferencedModel = itemsReferencedModel;
		_itemsType = itemsOpenApiType;
		_itemsFormat = itemsOpenApiFormat;
		_openApiProperties = new ArrayList<>(openApiProperties);
		_parameter = parameter;
	}

	private static final Pattern _itemsReferenceModelPattern = Pattern.compile(
		"^#/?(\\w+/)+(\\w+)$");

	private final ComponentType _componentType;
	private final OpenApiFormat _itemsFormat;
	private final String _itemsReference;
	private final String _itemsReferencedModel;
	private final OpenApiType _itemsType;
	private final String _name;
	private final List<OpenApiProperty> _openApiProperties;
	private final Parameter _parameter;
	private String _toString;

}