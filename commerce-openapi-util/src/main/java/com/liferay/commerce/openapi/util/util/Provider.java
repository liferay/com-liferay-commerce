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

/**
 * @author Zoltán Takács
 * @author Igor Beslic
 */
public class Provider {

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