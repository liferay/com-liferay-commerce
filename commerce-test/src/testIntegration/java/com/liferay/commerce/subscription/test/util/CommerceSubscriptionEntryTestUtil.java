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

package com.liferay.commerce.subscription.test.util;

import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPInstanceLocalServiceUtil;
import com.liferay.commerce.product.test.util.CPTestUtil;
import com.liferay.commerce.service.CommerceOrderLocalServiceUtil;
import com.liferay.commerce.subscription.CommerceSubscriptionEntryHelper;
import com.liferay.commerce.test.util.CommerceTestUtil;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceSubscriptionEntryTestUtil {

	public static void setUpCommerceSubscriptionEntry(
			long groupId, long userId, long maxSubscriptionCycles,
			CommerceSubscriptionEntryHelper commerceSubscriptionEntryHelper)
		throws Exception {

		CPInstance cpInstance = CPTestUtil.addCPInstance(groupId);

		cpInstance.setOverrideSubscriptionInfo(true);
		cpInstance.setSubscriptionEnabled(true);
		cpInstance.setSubscriptionLength(1);
		cpInstance.setSubscriptionType("daily");
		cpInstance.setMaxSubscriptionCycles(maxSubscriptionCycles);

		cpInstance = CPInstanceLocalServiceUtil.updateCPInstance(cpInstance);

		CommerceTestUtil.addBackOrderCPDefinitionInventory(
			cpInstance.getCPDefinition());

		CommerceOrder commerceOrder = CommerceTestUtil.addB2CCommerceOrder(
			groupId, userId, 0);

		CommerceAddress commerceAddress =
			CommerceTestUtil.addUserCommerceAddress(groupId, userId);

		commerceOrder.setBillingAddressId(
			commerceAddress.getCommerceAddressId());
		commerceOrder.setShippingAddressId(
			commerceAddress.getCommerceAddressId());

		CommerceOrderLocalServiceUtil.updateCommerceOrder(commerceOrder);

		CommerceTestUtil.addCommerceOrderItem(
			commerceOrder.getCommerceOrderId(), cpInstance.getCPInstanceId(),
			1);

		commerceSubscriptionEntryHelper.checkCommerceSubscriptions(
			commerceOrder);
	}

}