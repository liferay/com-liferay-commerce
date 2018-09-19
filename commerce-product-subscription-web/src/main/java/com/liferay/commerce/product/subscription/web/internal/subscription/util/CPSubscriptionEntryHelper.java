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

package com.liferay.commerce.product.subscription.web.internal.subscription.util;

import com.liferay.commerce.model.CPSubscriptionEntry;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.service.CPSubscriptionCycleEntryLocalService;
import com.liferay.commerce.service.CPSubscriptionEntryLocalService;
import com.liferay.commerce.service.CommerceOrderItemLocalService;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Luca Pellizzon
 */
@Component(immediate = true)
public class CPSubscriptionEntryHelper {

	public void renewSubscriptionEntries(
			List<CPSubscriptionEntry> cpSubscriptionEntries)
		throws PortalException {

		for (CPSubscriptionEntry cpSubscriptionEntry : cpSubscriptionEntries) {
			renewSubscriptionEntry(cpSubscriptionEntry);
		}
	}

	public void renewSubscriptionEntry(CPSubscriptionEntry cpSubscriptionEntry)
		throws PortalException {

		Date now = new Date();

		Date nextIterationDate = cpSubscriptionEntry.getNextIterationDate();

		if (cpSubscriptionEntry.isActive() && nextIterationDate.before(now)) {
			ServiceContext serviceContext = new ServiceContext();

			CommerceOrder commerceOrder = _addCommerceOrder(
				cpSubscriptionEntry, serviceContext);

			_commerceOrderLocalService.checkoutCommerceOrder(
				commerceOrder.getCommerceOrderId(), null, serviceContext);
		}

		if (cpSubscriptionEntry.getMaxSubscriptionCyclesNumber() > 0) {
			int cpSubscriptionCycleEntriesCount =
				_cpSubscriptionCycleEntryLocalService.
					getCPSubscriptionCycleEntriesCount(
						cpSubscriptionEntry.getCPSubscriptionEntryId());

			if (cpSubscriptionCycleEntriesCount >=
					cpSubscriptionEntry.getMaxSubscriptionCyclesNumber()) {

				_cpSubscriptionEntryLocalService.setActive(
					cpSubscriptionEntry.getCPSubscriptionEntryId(), false);
			}
		}
	}

	private CommerceOrder _addCommerceOrder(
			CPSubscriptionEntry cpSubscriptionEntry,
			ServiceContext serviceContext)
		throws PortalException {

		CommerceOrderItem oldCommerceOrderItem =
			_commerceOrderItemLocalService.getCommerceOrderItem(
				cpSubscriptionEntry.getCommerceOrderItemId());

		CommerceOrder oldOrder = oldCommerceOrderItem.getCommerceOrder();

		CommerceOrder newOrder;

		if (oldOrder.getOrderOrganization() != null) {
			newOrder = _commerceOrderLocalService.addOrganizationCommerceOrder(
				oldOrder.getGroupId(), oldOrder.getUserId(),
				oldOrder.getSiteGroupId(), oldOrder.getOrderOrganizationId(),
				oldOrder.getCommerceCurrencyId(),
				oldOrder.getShippingAddressId(),
				oldOrder.getPurchaseOrderNumber());
		}
		else {
			newOrder = _commerceOrderLocalService.addUserCommerceOrder(
				oldOrder.getGroupId(), oldOrder.getUserId(),
				oldOrder.getOrderUserId(), oldOrder.getCommerceCurrencyId());
		}

		newOrder.setBillingAddressId(oldOrder.getBillingAddressId());
		newOrder.setCommercePaymentMethodId(
			oldOrder.getCommercePaymentMethodId());
		newOrder.setCommerceShippingMethodId(
			oldOrder.getCommerceShippingMethodId());

		long newCommerceOrderItemId = _counterLocalService.increment();

		CommerceOrderItem newCommerceOrderItem =
			(CommerceOrderItem)oldCommerceOrderItem.clone();

		newCommerceOrderItem.setCommerceOrderItemId(newCommerceOrderItemId);
		newCommerceOrderItem.setCommerceOrderId(newOrder.getCommerceOrderId());

		newCommerceOrderItem =
			_commerceOrderItemLocalService.addCommerceOrderItem(
				newCommerceOrderItem);

		newOrder.setSubtotal(newCommerceOrderItem.getFinalPrice());
		newOrder.setTotal(newCommerceOrderItem.getFinalPrice());

		newOrder = _commerceOrderLocalService.updateCommerceOrder(newOrder);

		// ServiceContext

		ExpandoBridge expandoBridge = oldOrder.getExpandoBridge();

		serviceContext.setExpandoBridgeAttributes(
			expandoBridge.getAttributes());

		serviceContext.setCompanyId(oldOrder.getCompanyId());
		serviceContext.setScopeGroupId(oldOrder.getGroupId());
		serviceContext.setUserId(oldOrder.getUserId());

		// Add CPSubscriptionCycleEntry

		_cpSubscriptionCycleEntryLocalService.addCPSubscriptionCycleEntry(
			cpSubscriptionEntry.getCPSubscriptionEntryId(),
			newCommerceOrderItemId, true);

		return newOrder;
	}

	@Reference
	private CommerceOrderItemLocalService _commerceOrderItemLocalService;

	@Reference
	private CommerceOrderLocalService _commerceOrderLocalService;

	@Reference
	private CounterLocalService _counterLocalService;

	@Reference
	private CPSubscriptionCycleEntryLocalService
		_cpSubscriptionCycleEntryLocalService;

	@Reference
	private CPSubscriptionEntryLocalService _cpSubscriptionEntryLocalService;

}