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

import com.liferay.commerce.openapi.util.exception.OpenApiException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Igor Beslic
 */
public class Schema {

	public static String getReferencedModel(String reference) {
		if (reference == null) {
			return null;
		}

		Matcher matcher = _schemaReferencedModelPattern.matcher(reference);

		if (matcher.find()) {
			return matcher.group(2);
		}

		throw new OpenApiException(
			"Unable to locate model pattern in " + reference);
	}

	public Schema(String type, String format, String reference) {
		_type = type;
		_format = format;
		_reference = reference;

		_referencedModel = getReferencedModel(reference);
	}

	public String getFormat() {
		return _format;
	}

	public String getReference() {
		return _reference;
	}

	public String getReferencedModel() {
		return _referencedModel;
	}

	public String getType() {
		return _type;
	}

	@Override
	public String toString() {
		return String.format(
			"{format=%s, reference=%s, referencedModel=%s, type=%s}", _format,
			_reference, _referencedModel, _type);
	}

	private static final Pattern _schemaReferencedModelPattern =
		Pattern.compile("^#/?(\\w+/)+(\\w+)$");

	private final String _format;
	private final String _reference;
	private final String _referencedModel;
	private final String _type;

}