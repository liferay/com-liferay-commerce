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

package com.liferay.commerce.internal.messaging;

import com.liferay.commerce.constants.CommerceDestinationNames;
import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.internal.notification.type.OrderPlacedCommerceNotificationTypeImpl;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.notification.util.CommerceNotificationHelper;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CPSubscriptionCycleEntry;
import com.liferay.commerce.product.service.CPSubscriptionCycleEntryLocalService;
import com.liferay.commerce.product.service.CPSubscriptionEntryLocalService;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = "destination.name=" + CommerceDestinationNames.ORDER_STATUS,
	service = MessageListener.class
)
public class CommerceOrderStatusMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		int orderStatus = message.getInteger("orderStatus");

		if (orderStatus == CommerceOrderConstants.ORDER_STATUS_TO_TRANSMIT) {
			long commerceOrderId = message.getLong("commerceOrderId");

			CommerceOrder commerceOrder =
				_commerceOrderLocalService.getCommerceOrder(commerceOrderId);

			// Commerce notifications

			_commerceNotificationHelper.sendNotifications(
				commerceOrder.getSiteGroupId(),
				OrderPlacedCommerceNotificationTypeImpl.KEY, commerceOrder);

			// Commerce product subscriptions

			_checkCPSubscriptions(commerceOrder);
		}
	}

	private void _checkCPSubscriptions(CommerceOrder commerceOrder)
		throws PortalException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setScopeGroupId(commerceOrder.getSiteGroupId());
		serviceContext.setUserId(commerceOrder.getOrderUserId());

		List<CommerceOrderItem> commerceOrderItems =
			commerceOrder.getCommerceOrderItems();

		for (CommerceOrderItem commerceOrderItem : commerceOrderItems) {
			CPDefinition cpDefinition = commerceOrderItem.getCPDefinition();
			CPInstance cpInstance = commerceOrderItem.getCPInstance();

			if (!_isSubscriptionCycleRenew(commerceOrderItem) &&
				(cpDefinition.isSubscriptionEnabled() ||
				 cpInstance.isSubscriptionEnabled())) {

				_cpSubscriptionEntryLocalService.addCPSubscriptionEntry(
					cpInstance.getCPInstanceId(),
					commerceOrderItem.getCommerceOrderItemId(), serviceContext);
			}
		}
	}

	private boolean _isSubscriptionCycleRenew(
		CommerceOrderItem commerceOrderItem) {

		CPSubscriptionCycleEntry cpSubscriptionCycleEntry =
			_cpSubscriptionCycleEntryLocalService.
				fetchCPCpSubscriptionCycleEntryByCommerceOrderItemId(
					commerceOrderItem.getCommerceOrderItemId());

		if ((cpSubscriptionCycleEntry != null) &&
			cpSubscriptionCycleEntry.isRenew()) {

			return true;
		}

		return false;
	}

	@Reference
	private CommerceNotificationHelper _commerceNotificationHelper;

	@Reference
	private CommerceOrderLocalService _commerceOrderLocalService;

	@Reference
	private CPSubscriptionCycleEntryLocalService
		_cpSubscriptionCycleEntryLocalService;

	@Reference
	private CPSubscriptionEntryLocalService _cpSubscriptionEntryLocalService;

}