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

package com.liferay.commerce.openapi.admin.context;

import com.liferay.portal.kernel.search.Sort;

/**
 * @author Zoltán Takács
 */
public class ClientHelper {

	public ClientHelper(Pagination pagination, Sort sort) {
		_pagination = pagination;
		_sort = sort;
	}

	public Pagination getPagination() {
		return _pagination;
	}

	public Sort getSort() {
		return _sort;
	}

	private final Pagination _pagination;
	private final Sort _sort;

}