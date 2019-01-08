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

/**
 * @author Igor Beslic
 */
public class BaseGenerator {

	public String lowerCaseFirstChar(String string) {
		if (Character.isLowerCase(string.charAt(0))) {
			return string;
		}

		return Character.toLowerCase(string.charAt(0)) + string.substring(1);
	}

	public String toCamelCase(String string) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < string.length(); i++) {
			if (string.charAt(i) == '_') {
				i++;

				sb.append(Character.toUpperCase(string.charAt(i)));
			}
			else {
				sb.append(string.charAt(i));
			}
		}

		return sb.toString();
	}

	public String upperCaseFirstChar(String string) {
		if (Character.isUpperCase(string.charAt(0))) {
			return string;
		}

		return Character.toUpperCase(string.charAt(0)) + string.substring(1);
	}

}