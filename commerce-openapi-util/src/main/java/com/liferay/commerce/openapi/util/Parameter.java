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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Igor Beslic
 */
public class Parameter {

	public static String getParameterReference(String reference) {
		Matcher matcher = _parameterReferencePattern.matcher(reference);

		if (matcher.find()) {
			return matcher.group(2);
		}

		return null;
	}

	public String getContentMimeType() {
		if (_content == null) {
			return null;
		}

		return _content.getMimeType();
	}

	public String getJavaType() {
		return _httpParameterFormat.getJavaType();
	}

	public String getLocation() {
		return _location;
	}

	public String getName() {
		return _name;
	}

	public Schema getSchema() {
		return _schema;
	}

	@Override
	public String toString() {
		if (_toString != null) {
			return _toString;
		}

		_toString = String.format(
			"{content=%s, location=%s, name=%s, javaType=%s, required=%s, " +
				"schema=%s}",
			_content, _location, _name, _httpParameterFormat, _required,
			_schema);

		return _toString;
	}

	public static class ParameterBuilder {

		public Parameter build() {
			return new Parameter(this);
		}

		public ParameterBuilder content(Content content) {
			_content = content;

			return this;
		}

		public ParameterBuilder httpParameterFormat(
			HttpParameterFormat httpParameterFormat) {

			_httpParameterFormat = httpParameterFormat;

			return this;
		}

		public ParameterBuilder location(String location) {
			_location = location;

			return this;
		}

		public ParameterBuilder name(String name) {
			_name = name;

			return this;
		}

		public ParameterBuilder required(boolean required) {
			_required = required;

			return this;
		}

		public ParameterBuilder schema(Schema schema) {
			_schema = schema;

			return this;
		}

		private Content _content;
		private HttpParameterFormat _httpParameterFormat;
		private String _location;
		private String _name;
		private boolean _required;
		private Schema _schema;

	}

	private Parameter(ParameterBuilder parameterBuilder) {
		_name = parameterBuilder._name;
		_location = parameterBuilder._location;
		_content = parameterBuilder._content;
		_schema = parameterBuilder._schema;
		_required = parameterBuilder._required;

		HttpParameterType httpParameterType = HttpParameterType.OBJECT;

		if (_schema.getType() != null) {
			httpParameterType = HttpParameterType.fromDefinition(
				_schema.getType());
		}

		HttpParameterFormat httpParameterFormat =
			HttpParameterFormat.fromHttpParameterTypeAndDefinition(
				httpParameterType, _schema.getFormat());

		_httpParameterFormat = httpParameterFormat;
	}

	private static final Pattern _parameterReferencePattern = Pattern.compile(
		"^#/?(\\w+/)+(\\w+)$");

	private Content _content;
	private final HttpParameterFormat _httpParameterFormat;
	private final String _location;
	private final String _name;
	private final boolean _required;
	private final Schema _schema;
	private String _toString;

}