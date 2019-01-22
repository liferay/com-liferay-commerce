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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Igor Beslic
 * @author Zoltán Takács
 */
public class Extension {

	public Extension(String name, List<Parameter> parameters) {
		_name = name;

		_parameters.addAll(parameters);

		_extensionType = ExtensionType.fromOpenApiName(name);
	}

	public ExtensionType getExtensionType() {
		return _extensionType;
	}

	public String getName() {
		return _name;
	}

	public List<Parameter> getParameters() {
		return _parameters;
	}

	public boolean isPaginationContext() {
		if (_extensionType == ExtensionType.PAGINATION_CONTEXT) {
			return true;
		}

		return false;
	}

	public static class Provider {

		public Provider(String modelName, String fqcn) {
			_modelName = modelName;
			_fqcn = fqcn;
		}

		public String getModelFQCN() {
			return _fqcn;
		}

		public String getModelName() {
			return _modelName;
		}

		private final String _fqcn;
		private final String _modelName;

	}

	public enum ExtensionType {

		COMPANY_CONTEXT("company"), FILTER_CONTEXT("filter"),
		LOCALE_CONTEXT("locale"), PAGINATION_CONTEXT("pagination"),
		PERMISSION_CHEKCER_CONTEXT("permissionchecker"), SORT_CONTEXT("sort"),
		THEMEDISPLAY_CONTEXT("themedisplay"), USER_CONTEXT("user");

		public static ExtensionType fromOpenApiName(String openApiName) {
			for (ExtensionType extensionType : values()) {
				if (openApiName.equals(extensionType._openApiName)) {
					return extensionType;
				}
			}

			throw new IllegalArgumentException(
				"Unsupported Open API name: " + openApiName);
		}

		public Provider getProvider() {
			return _extensionToProvider.get(this);
		}

		private ExtensionType(String openApiName) {
			_openApiName = openApiName;
		}

		private static final Map<ExtensionType, Provider> _extensionToProvider =
			new HashMap<ExtensionType, Provider>() {
				{
					put(
						USER_CONTEXT,
						new Provider(
							"User", "com.liferay.portal.kernel.model.User"));
					put(
						LOCALE_CONTEXT,
						new Provider("Locale", "java.util.Locale"));
					put(
						THEMEDISPLAY_CONTEXT,
						new Provider(
							"ThemeDisplay",
							"com.liferay.portal.kernel.theme.ThemeDisplay"));
					put(
						COMPANY_CONTEXT,
						new Provider(
							"Company",
							"com.liferay.portal.kernel.model.Company"));
					put(
						PERMISSION_CHEKCER_CONTEXT,
						new Provider(
							"PermissionChecker",
							"com.liferay.portal.kernel.security.permission." +
								"PermissionChecker"));
					put(
						PAGINATION_CONTEXT,
						new Provider(
							"Pagination",
							"com.liferay.commerce.openapi.core.context." +
								"Pagination"));
					put(
						SORT_CONTEXT,
						new Provider(
							"Sort", "com.liferay.portal.kernel.search.Sort"));
					put(
						FILTER_CONTEXT,
						new Provider(
							"Filter",
							"com.liferay.commerce.openapi.core.context." +
								"Filter"));
				}
			};

		private final String _openApiName;

	}

	private final ExtensionType _extensionType;
	private final String _name;
	private final List<Parameter> _parameters = new ArrayList<>();

}