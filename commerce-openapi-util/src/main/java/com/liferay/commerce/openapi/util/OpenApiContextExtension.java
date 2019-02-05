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

import java.util.HashMap;
import java.util.Map;

/**
 * @author Igor Beslic
 * @author Zoltán Takács
 */
public enum OpenApiContextExtension {

	COMPANY_CONTEXT("company"), FILTER_CONTEXT("filter"),
	LANGUAGE_CONTEXT("language"), PAGINATION_CONTEXT("pagination"),
	PERMISSION_CHEKCER_CONTEXT("permissionchecker"), SORT_CONTEXT("sort"),
	THEMEDISPLAY_CONTEXT("themedisplay"), USER_CONTEXT("user");

	public static OpenApiContextExtension fromOpenApiName(String openApiName) {
		for (OpenApiContextExtension extensionType : values()) {
			if (openApiName.equals(extensionType._openApiName)) {
				return extensionType;
			}
		}

		throw new IllegalArgumentException(
			"Unsupported Open API name: " + openApiName);
	}

	public String getOpenApiName() {
		return _openApiName;
	}

	public Provider getProvider() {
		return _extensionToProvider.get(this);
	}

	private OpenApiContextExtension(String openApiName) {
		_openApiName = openApiName;
	}

	private static final Map<OpenApiContextExtension, Provider>
		_extensionToProvider =
			new HashMap<OpenApiContextExtension, Provider>() {
				{
					put(
						COMPANY_CONTEXT,
						new Provider(
							"Company",
							"com.liferay.portal.kernel.model.Company"));
					put(
						FILTER_CONTEXT,
						new Provider(
							"Filter",
							"com.liferay.commerce.openapi.core.context." +
								"Filter"));
					put(
						LANGUAGE_CONTEXT,
						new Provider(
							"Language",
							"com.liferay.commerce.openapi.core.context." +
								"Language"));
					put(
						PAGINATION_CONTEXT,
						new Provider(
							"Pagination",
							"com.liferay.commerce.openapi.core.context." +
								"Pagination"));
					put(
						PERMISSION_CHEKCER_CONTEXT,
						new Provider(
							"PermissionChecker",
							"com.liferay.portal.kernel.security.permission." +
								"PermissionChecker"));
					put(
						SORT_CONTEXT,
						new Provider(
							"Sort", "com.liferay.portal.kernel.search.Sort"));
					put(
						THEMEDISPLAY_CONTEXT,
						new Provider(
							"ThemeDisplay",
							"com.liferay.portal.kernel.theme.ThemeDisplay"));
					put(
						USER_CONTEXT,
						new Provider(
							"User", "com.liferay.portal.kernel.model.User"));
				}
			};

	private final String _openApiName;

}