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

import com.liferay.commerce.openapi.util.util.Provider;

/**
 * @author Igor Beslic
 */
public class OpenApiProperty {

	public String getGetterSyntax() {
		if (_openApiFormat == null) {
			return "get";
		}

		return _openApiFormat.getGetterSyntax();
	}

	public Provider getJavaTypeProvider() {
		return _openApiFormat.getProvider();
	}

	public String getName() {
		return _name;
	}

	public String getReference() {
		return _reference;
	}

	public String getSetterSyntax() {
		if (_openApiFormat == null) {
			return "set";
		}

		return _openApiFormat.getSetterSyntax();
	}

	public boolean isArray() {
		if (_openApiType == OpenApiType.ARRAY) {
			return true;
		}

		return false;
	}

	public boolean isDictionary() {
		if (_openApiType == OpenApiType.DICTIONARY) {
			return true;
		}

		return false;
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

	public void setName(String name) {
		_name = name;
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
		sb.append(_itemsFormat);
		sb.append(", itemType=");
		sb.append(_itemsType);
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

		public OpenApiProperty build() {
			if (_openApiTypeDefinition.equals("array")) {
				if (_componentReference != null) {
					return new ReferenceArrayOpenApiProperty(this);
				}

				return new ArrayOpenApiProperty(this);
			}
			else if (_openApiTypeDefinition.equals("dictionary")) {
				if ((_itemsOpenApiTypeDefinition == null) &&
					(_itemsReference == null)) {

					return new FreeFormDictionaryOpenApiProperty(this);
				}

				return new DictionaryOpenApiProperty(this);
			}
			else if (_openApiTypeDefinition.equals("object")) {
				return new ObjectOpenApiProperty(this);
			}

			return new OpenApiProperty(this);
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

		public OpenApiPropertyBuilder itemsOpenApiFormatDefinition(
			String openApiFormatDefinition) {

			_itemsOpenApiFormatDefinition = openApiFormatDefinition;

			return this;
		}

		public OpenApiPropertyBuilder itemsOpenApiTypeDefinition(
			String openApiTypeDefinition) {

			_itemsOpenApiTypeDefinition = openApiTypeDefinition;

			return this;
		}

		public OpenApiPropertyBuilder itemsReference(String itemsReference) {
			_itemsReference = itemsReference;

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
		private String _itemsOpenApiFormatDefinition;
		private String _itemsOpenApiTypeDefinition;
		private String _itemsReference;
		private String _name;
		private String _openApiFormatDefinition;
		private String _openApiTypeDefinition;
		private boolean _required;

	}

	protected OpenApiProperty(OpenApiPropertyBuilder openApiPropertyBuilder) {
		_example = openApiPropertyBuilder._example;

		if (openApiPropertyBuilder._itemsOpenApiTypeDefinition != null) {
			_itemsType = _fromOpenApiDefinition(
				openApiPropertyBuilder._itemsOpenApiTypeDefinition);

			_itemsFormat = OpenApiFormat.fromOpenApiTypeAndFormat(
				_itemsType,
				openApiPropertyBuilder._itemsOpenApiFormatDefinition);
		}
		else {
			_itemsType = null;
			_itemsFormat = null;
		}

		_itemsReference = openApiPropertyBuilder._itemsReference;
		_name = openApiPropertyBuilder._name;
		_required = openApiPropertyBuilder._required;
		_openApiType = _fromOpenApiDefinition(
			openApiPropertyBuilder._openApiTypeDefinition);

		_openApiFormat = OpenApiFormat.fromOpenApiTypeAndFormat(
			_openApiType, openApiPropertyBuilder._openApiFormatDefinition);

		_reference = openApiPropertyBuilder._componentReference;
	}

	protected OpenApiFormat getItemsFormat() {
		return _itemsFormat;
	}

	private OpenApiType _fromOpenApiDefinition(String openApiTypeDefinition) {
		if (openApiTypeDefinition == null) {
			return OpenApiType.OBJECT;
		}

		return OpenApiType.fromDefinition(openApiTypeDefinition);
	}

	private String _example;
	private final OpenApiFormat _itemsFormat;
	private final String _itemsReference;
	private final OpenApiType _itemsType;
	private String _name;
	private final OpenApiFormat _openApiFormat;
	private OpenApiType _openApiType;
	private final String _reference;
	private boolean _required;
	private String _toString;

}