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

package com.liferay.commerce.product.type.virtual.order.internal.messaging;

import com.liferay.commerce.constants.CommerceDestinationNames;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.model.CommerceSubscriptionCycleEntry;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.type.virtual.constants.VirtualCPTypeConstants;
import com.liferay.commerce.product.type.virtual.order.model.CommerceVirtualOrderItem;
import com.liferay.commerce.product.type.virtual.order.service.CommerceVirtualOrderItemLocalService;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.commerce.service.CommerceSubscriptionCycleEntryLocalService;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.security.SecureRandomUtil;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.List;
import java.util.UUID;

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

		long commerceOrderId = message.getLong("commerceOrderId");

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.fetchCommerceOrder(commerceOrderId);

		if (commerceOrder == null) {
			return;
		}

		List<CommerceOrderItem> commerceOrderItems =
			commerceOrder.getCommerceOrderItems();

		for (CommerceOrderItem commerceOrderItem : commerceOrderItems) {
			CommerceVirtualOrderItem commerceVirtualOrderItem =
				_commerceVirtualOrderItemLocalService.
					fetchCommerceVirtualOrderItemByCommerceOrderItemId(
						commerceOrderItem.getCommerceOrderItemId());

			if ((commerceVirtualOrderItem == null) &&
				_isNewSubscription(commerceOrderItem)) {

				CPDefinition cpDefinition = commerceOrderItem.getCPDefinition();

				if (!VirtualCPTypeConstants.NAME.equals(
						cpDefinition.getProductTypeName())) {

					continue;
				}

				ServiceContext serviceContext = new ServiceContext();

				serviceContext.setScopeGroupId(commerceOrder.getSiteGroupId());
				serviceContext.setUserId(commerceOrder.getUserId());

				UUID uuid = new UUID(
					SecureRandomUtil.nextLong(), SecureRandomUtil.nextLong());

				serviceContext.setUuid(uuid.toString());

				commerceVirtualOrderItem =
					_commerceVirtualOrderItemLocalService.
						addCommerceVirtualOrderItem(
							commerceOrderItem.getCommerceOrderItemId(),
							serviceContext);
			}

			if ((commerceVirtualOrderItem != null) &&
				(orderStatus ==
					commerceVirtualOrderItem.getActivationStatus())) {

				_commerceVirtualOrderItemLocalService.setActive(
					commerceVirtualOrderItem.getCommerceVirtualOrderItemId(),
					true);
			}
		}
	}

	private boolean _isNewSubscription(CommerceOrderItem commerceOrderItem) {
		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry =
			_commerceSubscriptionCycleEntryLocalService.
				fetchCommerceSubscriptionCycleEntryByCommerceOrderItemId(
					commerceOrderItem.getCommerceOrderItemId());

		if ((commerceSubscriptionCycleEntry != null) &&
			commerceSubscriptionCycleEntry.isRenew()) {

			return false;
		}

		return true;
	}

	@Reference
	private CommerceOrderLocalService _commerceOrderLocalService;

	@Reference
	private CommerceSubscriptionCycleEntryLocalService
		_commerceSubscriptionCycleEntryLocalService;

	@Reference
	private CommerceVirtualOrderItemLocalService
		_commerceVirtualOrderItemLocalService;

}