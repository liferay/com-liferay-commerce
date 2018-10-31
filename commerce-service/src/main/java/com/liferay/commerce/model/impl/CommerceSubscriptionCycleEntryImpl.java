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

package com.liferay.commerce.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.model.CommerceSubscriptionEntry;
import com.liferay.commerce.service.CommerceOrderItemLocalServiceUtil;
import com.liferay.commerce.service.CommerceSubscriptionEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Alessio Antonio Rendina
 */
@ProviderType
public class CommerceSubscriptionCycleEntryImpl
	extends CommerceSubscriptionCycleEntryBaseImpl {

	public CommerceSubscriptionCycleEntryImpl() {
	}

	@Override
	public CommerceOrder fetchCommerceOrder() throws PortalException {
		CommerceOrderItem commerceOrderItem = fetchCommerceOrderItem();

		if (commerceOrderItem == null) {
			return null;
		}

		return commerceOrderItem.getCommerceOrder();
	}

	@Override
	public CommerceOrderItem fetchCommerceOrderItem() {
		return CommerceOrderItemLocalServiceUtil.fetchCommerceOrderItem(
			getCommerceOrderItemId());
	}

	@Override
	public CommerceSubscriptionEntry getCommerceSubscriptionEntry()
		throws PortalException {

		return CommerceSubscriptionEntryLocalServiceUtil.
			getCommerceSubscriptionEntry(getCommerceSubscriptionEntryId());
	}

}