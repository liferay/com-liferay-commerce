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

package com.liferay.commerce.product.subscription.test.util;

import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.test.util.CommerceCurrencyTestUtil;
import com.liferay.commerce.internal.test.util.CommerceTestUtil;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.product.constants.CPConstants;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPInstanceLocalServiceUtil;
import com.liferay.commerce.product.test.util.CPTestUtil;
import com.liferay.commerce.service.CommerceOrderLocalServiceUtil;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceSubscriptionEntryTestUtil {

	public static void setUpCommerceSubscriptionEntry(
			long groupId, long userId, long maxSubscriptionCyclesNumber)
		throws Exception {

		CPInstance cpInstance = CPTestUtil.addCPInstance(groupId);

		cpInstance.setSubscriptionEnabled(true);
		cpInstance.setSubscriptionCycleLength(1);
		cpInstance.setSubscriptionCyclePeriod(
			CPConstants.SUBSCRIPTION_CYCLE_DAY);
		cpInstance.setMaxSubscriptionCyclesNumber(maxSubscriptionCyclesNumber);

		cpInstance = CPInstanceLocalServiceUtil.updateCPInstance(cpInstance);

		CPDefinition cpDefinition = cpInstance.getCPDefinition();

		CommerceTestUtil.addBackOrderCPDefinitionInventory(cpDefinition);

		CommerceCurrency commerceCurrency =
			CommerceCurrencyTestUtil.addCommerceCurrency(groupId);

		CommerceOrder commerceOrder = CommerceTestUtil.addUserCommerceOrder(
			groupId, userId, commerceCurrency.getCommerceCurrencyId());

		CommerceTestUtil.addCommerceOrderItem(
			commerceOrder.getCommerceOrderId(), cpInstance.getCPInstanceId(),
			1);

		commerceOrder.setOrderStatus(
			CommerceOrderConstants.ORDER_STATUS_TO_TRANSMIT);

		CommerceOrderLocalServiceUtil.updateCommerceOrder(commerceOrder);
	}

}