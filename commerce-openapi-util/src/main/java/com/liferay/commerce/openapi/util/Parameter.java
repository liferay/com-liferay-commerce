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

	public Parameter(
		String name, String location, Content content, boolean required) {

		this(name, location, content.getSchema(), required);

		_content = content;
	}

	public Parameter(
		String name, String location, Schema schema, boolean required) {

		_name = name;
		_location = location;
		_schema = schema;
		_required = required;

		HttpParameterType httpParameterType = HttpParameterType.OBJECT;

		if (_schema.getType() != null) {
			httpParameterType = HttpParameterType.fromDefinition(
				schema.getType());
		}

		HttpParameterFormat httpParameterFormat =
			HttpParameterFormat.fromHttpParameterTypeAndDefinition(
				httpParameterType, schema.getFormat());

		_httpParameterFormat = httpParameterFormat;
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