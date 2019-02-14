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

package com.liferay.commerce.openapi.util.util;

import java.util.Collections;
import java.util.List;

/**
 * @author Ivica Cardic
 */
public class DateProvider extends Provider {

	public DateProvider() {
		super("Date", "java.util.Date");
	}

	public List<String> getAdditionalImportableJavaTypes() {
		return Collections.singletonList(
			"com.fasterxml.jackson.annotation.JsonFormat");
	}

	public String getGetterMethodAnnotation() {
		return _JSON_DATE_FORMAT;
	}

	public String getSetterMethodAnnotation() {
		return _JSON_DATE_FORMAT;
	}

	private static final String _JSON_DATE_FORMAT =
		"\t@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = " +
			"\"yyyy-MM-dd\")\n";

}