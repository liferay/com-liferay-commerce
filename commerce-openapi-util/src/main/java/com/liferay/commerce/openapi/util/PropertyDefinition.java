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

/**
 * @author Igor Beslic
 */
public class PropertyDefinition {

	public String getComponentReference() {
		return _componentReference;
	}

	public String getGetterSyntax() {
		if (_openApiFormat == null) {
			return "get";
		}

		return _openApiFormat.getGetterSyntax();
	}

	public String getJavaType() {
		if (_openApiType == OpenApiType.ARRAY) {
			return _itemFormat.getJavaType() + "[]";
		}
		else if (_openApiType == OpenApiType.DICTIONARY) {
			return "Map<String, String>";
		}

		return _openApiFormat.getJavaType();
	}

	public String getName() {
		return _name;
	}

	public String getSetterSyntax() {
		if (_openApiFormat == null) {
			return "set";
		}

		return _openApiFormat.getSetterSyntax();
	}

	public boolean isObject() {
		if (_openApiType == OpenApiType.OBJECT) {
			return true;
		}

		return false;
	}

	public boolean isRequired() {
		return _required;
	}

	public void setExample(String example) {
		_example = example;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setRequired(boolean required) {
		_required = required;
	}

	public void setType(String openApiDefinition) {
		_openApiType = OpenApiType.fromDefinition(openApiDefinition);
	}

	@Override
	public String toString() {
		if (_toString != null) {
			return _toString;
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{example=");
		sb.append(_example);
		sb.append(", format=");
		sb.append(_openApiFormat);
		sb.append(", itemFormat=");
		sb.append(_itemFormat);
		sb.append(", itemType=");
		sb.append(_itemType);
		sb.append(", name=");
		sb.append(_name);
		sb.append(", required=");
		sb.append(_required);
		sb.append(", type=");
		sb.append(_openApiType);
		sb.append("}");

		_toString = sb.toString();

		return _toString;
	}

	public static class OpenApiPropertyBuilder {

		public PropertyDefinition build() {
			return new PropertyDefinition(this);
		}

		public OpenApiPropertyBuilder componentReference(
			String componentReference) {

			_componentReference = componentReference;

			return this;
		}

		public OpenApiPropertyBuilder example(String example) {
			_example = example;

			return this;
		}

		public OpenApiPropertyBuilder itemOpenApiFormatDefinition(
			String itemOpenApiFormatDefinition) {

			_itemOpenApiFormatDefinition = itemOpenApiFormatDefinition;

			return this;
		}

		public OpenApiPropertyBuilder itemOpenApiTypeDefinition(
			String itemOpenApiTypeDefinition) {

			_itemOpenApiTypeDefinition = itemOpenApiTypeDefinition;

			return this;
		}

		public OpenApiPropertyBuilder name(String name) {
			_name = name;

			return this;
		}

		public OpenApiPropertyBuilder openApiFormatDefinition(
			String openApiFormatDefinition) {

			_openApiFormatDefinition = openApiFormatDefinition;

			return this;
		}

		public OpenApiPropertyBuilder openApiTypeDefinition(
			String openApiTypeDefinition) {

			_openApiTypeDefinition = openApiTypeDefinition;

			return this;
		}

		public OpenApiPropertyBuilder required(boolean required) {
			_required = required;

			return this;
		}

		private String _componentReference;
		private String _example;
		private String _itemOpenApiFormatDefinition;
		private String _itemOpenApiTypeDefinition;
		private String _name;
		private String _openApiFormatDefinition;
		private String _openApiTypeDefinition;
		private boolean _required;

	}

	private PropertyDefinition(OpenApiPropertyBuilder openApiPropertyBuilder) {
		_example = openApiPropertyBuilder._example;
		_itemType = _fromOpenApiDefinition(
			openApiPropertyBuilder._itemOpenApiTypeDefinition);

		_itemFormat = OpenApiFormat.fromOpenApiTypeAndFormat(
			_itemType, openApiPropertyBuilder._itemOpenApiFormatDefinition);

		_name = openApiPropertyBuilder._name;
		_required = openApiPropertyBuilder._required;
		_openApiType = _fromOpenApiDefinition(
			openApiPropertyBuilder._openApiTypeDefinition);

		_openApiFormat = OpenApiFormat.fromOpenApiTypeAndFormat(
			_openApiType, openApiPropertyBuilder._openApiFormatDefinition);

		_componentReference = openApiPropertyBuilder._componentReference;
	}

	private OpenApiType _fromOpenApiDefinition(String openApiTypeDefinition) {
		if (openApiTypeDefinition == null) {
			return OpenApiType.OBJECT;
		}

		return OpenApiType.fromDefinition(openApiTypeDefinition);
	}

	private String _componentReference;
	private String _example;
	private final OpenApiFormat _itemFormat;
	private final OpenApiType _itemType;
	private String _name;
	private final OpenApiFormat _openApiFormat;
	private OpenApiType _openApiType;
	private boolean _required;
	private String _toString;

}