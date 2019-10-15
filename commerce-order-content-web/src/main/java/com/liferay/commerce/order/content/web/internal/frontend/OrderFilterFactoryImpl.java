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

package com.liferay.commerce.order.content.web.internal.frontend;

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
	property = {
		"commerce.data.provider.key=" + CommercePendingOrderClayTable.NAME,
		"commerce.data.provider.key=" + CommercePendingOrderItemClayTable.NAME,
		"commerce.data.provider.key=" + CommercePlacedOrderClayTable.NAME,
		"commerce.data.provider.key=" + CommercePlacedOrderItemClayTable.NAME
	},
	service = FilterFactory.class
)
public class OrderFilterFactoryImpl implements FilterFactory {

	@Override
	public Filter create(HttpServletRequest httpServletRequest) {
		OrderFilterImpl orderFilterImpl = new OrderFilterImpl();

		long commerceOrderId = ParamUtil.getLong(
			httpServletRequest, "commerceOrderId");

		long commerceAccountId = ParamUtil.getLong(
			httpServletRequest, "accountId");

		orderFilterImpl.setAccountId(commerceAccountId);

		orderFilterImpl.setOrderId(commerceOrderId);

		String keywords = ParamUtil.getString(httpServletRequest, "q");

		orderFilterImpl.setKeywords(keywords);

		return orderFilterImpl;
	}

}