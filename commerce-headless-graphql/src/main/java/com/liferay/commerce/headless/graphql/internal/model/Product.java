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

package com.liferay.commerce.headless.graphql.internal.model;

/**
 * @author Marco Leo
 */
public class Product {

	public String getCpDefinitionId() {
		return _cpDefinitionId;
	}

	public String getTitle() {
		return _title;
	}

	public void setCpDefinitionId(String cpDefinitionId) {
		_cpDefinitionId = cpDefinitionId;
	}

	public void setTitle(String title) {
		_title = title;
	}

	private String _cpDefinitionId;
	private String _title;

}