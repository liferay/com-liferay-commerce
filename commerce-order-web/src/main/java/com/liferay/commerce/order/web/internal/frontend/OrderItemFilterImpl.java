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

package com.liferay.commerce.order.web.internal.frontend;

import com.liferay.commerce.frontend.DefaultFilterImpl;

/**
 * @author Alessio Antonio Rendina
 */
public class OrderItemFilterImpl extends DefaultFilterImpl {

	public String getName() {
		return _name;
	}

	public String getSku() {
		return _sku;
	}

	public boolean isAdvancedSearch() {
		return _advancedSearch;
	}

	public boolean isAndOperator() {
		return _andOperator;
	}

	public void setAdvancedSearch(boolean advancedSearch) {
		_advancedSearch = advancedSearch;
	}

	public void setAndOperator(boolean andOperator) {
		_andOperator = andOperator;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setSku(String sku) {
		_sku = sku;
	}

	private boolean _advancedSearch;
	private boolean _andOperator;
	private String _name;
	private String _sku;

}