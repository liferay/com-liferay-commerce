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

import com.liferay.commerce.frontend.Filter;
import com.liferay.commerce.frontend.FilterFactory;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = "commerce.data.provider.key=" + CommerceOrderItemClayTable.NAME,
	service = FilterFactory.class
)
public class OrderItemFilterFactoryImpl implements FilterFactory {

	@Override
	public Filter create(HttpServletRequest httpServletRequest) {
		OrderItemFilterImpl orderItemFilter = new OrderItemFilterImpl();

		boolean advancedSearch = ParamUtil.getBoolean(
			httpServletRequest, "advancedSearch");
		boolean andOperator = ParamUtil.getBoolean(
			httpServletRequest, "andOperator", true);
		String keywords = ParamUtil.getString(httpServletRequest, "keywords");
		String name = ParamUtil.getString(httpServletRequest, "name");
		String sku = ParamUtil.getString(httpServletRequest, "sku");

		orderItemFilter.setAdvancedSearch(advancedSearch);
		orderItemFilter.setAndOperator(andOperator);
		orderItemFilter.setKeywords(keywords);
		orderItemFilter.setName(name);
		orderItemFilter.setSku(sku);

		return orderItemFilter;
	}

}