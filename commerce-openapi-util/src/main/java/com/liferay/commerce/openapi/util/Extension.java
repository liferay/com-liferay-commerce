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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Igor Beslic
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

	public enum ExtensionType {

		CLIENT_HELPER_CONTEXT("client"), FILTER_CONTEXT("filter"),
		PORTAL_HELPER_CONTEXT("portal");

		public static ExtensionType fromOpenApiName(String openApiName) {
			for (ExtensionType extensionType : values()) {
				if (openApiName.equals(extensionType._openApiName)) {
					return extensionType;
				}
			}

			throw new IllegalArgumentException(
				"Unsupported Open API name: " + openApiName);
		}

		public List<String> getProviders() {
			return new ArrayList<>(_extensionToProvider.get(this));
		}

		private ExtensionType(String openApiName) {
			_openApiName = openApiName;
		}

		private static final Map<ExtensionType, List<String>>
			_extensionToProvider = new HashMap<ExtensionType, List<String>>() {
				{
					put(CLIENT_HELPER_CONTEXT, Arrays.asList("Pagination"));
					put(PORTAL_HELPER_CONTEXT, Arrays.asList("Locale"));
					put(FILTER_CONTEXT, Arrays.asList("Filter"));
				}
			};

		private final String _openApiName;

	}

	private final ExtensionType _extensionType;
	private final String _name;
	private final List<Parameter> _parameters = new ArrayList<>();

}