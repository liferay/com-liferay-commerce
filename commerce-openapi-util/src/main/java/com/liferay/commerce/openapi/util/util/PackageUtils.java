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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Igor Beslic
 */
public class PackageUtils {

	public static String toPackageName(String versionExpression) {
		if (versionExpression == null) {
			throw new IllegalArgumentException(
				"Version expression must not be null");
		}

		Matcher matcher = _versionPattern.matcher(versionExpression);

		if (!matcher.matches()) {
			throw new IllegalArgumentException(
				"Version expression must match v?([0-9]+.)*[0-9]+$");
		}

		String packageName = versionExpression.replace(".", "_");

		if (packageName.startsWith("v")) {
			return packageName;
		}

		return "v" + packageName;
	}

	private static final Pattern _versionPattern = Pattern.compile(
		"v?([0-9]+.)*[0-9]+$");

}