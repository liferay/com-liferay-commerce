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

import com.liferay.commerce.openapi.util.util.MapStringStringProvider;
import com.liferay.commerce.openapi.util.util.Provider;

/**
 * @author Igor Beslic
 */
public class DictionaryOpenApiProperty extends OpenApiProperty {

	public Provider getJavaTypeProvider() {
		return _provider;
	}

	protected DictionaryOpenApiProperty(
		OpenApiPropertyBuilder openApiPropertyBuilder) {

		super(openApiPropertyBuilder);

		OpenApiFormat dictionaryOpenApiFormat = OpenApiFormat.DICTIONARY;

		_provider = new MapStringStringProvider(
			dictionaryOpenApiFormat.getProvider());
	}

	private final Provider _provider;

}