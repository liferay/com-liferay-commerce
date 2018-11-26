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

package com.liferay.commerce.internal.subscription;

import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.model.CommerceSubscriptionEntry;
import com.liferay.commerce.service.CommerceOrderItemLocalService;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.commerce.service.CommerceSubscriptionCycleEntryLocalService;
import com.liferay.commerce.service.CommerceSubscriptionEntryLocalService;
import com.liferay.commerce.subscription.CommerceSubscriptionEntryHelper;
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
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = CommerceSubscriptionEntryHelper.class)
public class CommerceSubscriptionEntryHelperImpl
	implements CommerceSubscriptionEntryHelper {

	public void renewSubscriptionEntries(
			List<CommerceSubscriptionEntry> cpSubscriptionEntries)
		throws PortalException {

		for (CommerceSubscriptionEntry commerceSubscriptionEntry :
				cpSubscriptionEntries) {

			renewSubscriptionEntry(commerceSubscriptionEntry);
		}
	}

	public void renewSubscriptionEntry(
			CommerceSubscriptionEntry commerceSubscriptionEntry)
		throws PortalException {

		Date now = new Date();

		Date nextIterationDate =
			commerceSubscriptionEntry.getNextIterationDate();

		if (commerceSubscriptionEntry.isActive() &&
			nextIterationDate.before(now)) {

			ServiceContext serviceContext = new ServiceContext();

			CommerceOrder commerceOrder = _addCommerceOrder(
				commerceSubscriptionEntry, serviceContext);

			_commerceOrderLocalService.checkoutCommerceOrder(
				commerceOrder.getCommerceOrderId(), null, serviceContext);
		}

		if (commerceSubscriptionEntry.getMaxSubscriptionCyclesNumber() > 0) {
			int cpSubscriptionCycleEntriesCount =
				_commerceSubscriptionCycleEntryLocalService.
					getCPSubscriptionCycleEntriesCount(
						commerceSubscriptionEntry.
							getCommerceSubscriptionEntryId());

			if (cpSubscriptionCycleEntriesCount >=
					commerceSubscriptionEntry.
						getMaxSubscriptionCyclesNumber()) {

				_commerceSubscriptionEntryLocalService.setActive(
					commerceSubscriptionEntry.getCommerceSubscriptionEntryId(),
					false);
			}
		}
	}

	private CommerceOrder _addCommerceOrder(
			CommerceSubscriptionEntry commerceSubscriptionEntry,
			ServiceContext serviceContext)
		throws PortalException {

		CommerceOrderItem oldCommerceOrderItem =
			_commerceOrderItemLocalService.getCommerceOrderItem(
				commerceSubscriptionEntry.getCommerceOrderItemId());

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

		// Add CommerceSubscriptionCycleEntry

		_commerceSubscriptionCycleEntryLocalService.addCommerceSubscriptionCycleEntry(
			commerceSubscriptionEntry.getCommerceSubscriptionEntryId(),
			newCommerceOrderItemId, true);

		return newOrder;
	}

	@Reference
	private CommerceOrderItemLocalService _commerceOrderItemLocalService;

	@Reference
	private CommerceOrderLocalService _commerceOrderLocalService;

	@Reference
	private CommerceSubscriptionCycleEntryLocalService
		_commerceSubscriptionCycleEntryLocalService;

	@Reference
	private CommerceSubscriptionEntryLocalService
		_commerceSubscriptionEntryLocalService;

	@Reference
	private CounterLocalService _counterLocalService;

}