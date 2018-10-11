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
import com.liferay.commerce.model.CommerceSubscriptionCycleEntry;
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

	@Override
	public void checkCommerceSubscriptions(CommerceOrder commerceOrder)
		throws PortalException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setScopeGroupId(commerceOrder.getSiteGroupId());
		serviceContext.setUserId(commerceOrder.getOrderUserId());

		List<CommerceOrderItem> commerceOrderItems =
			_commerceOrderItemLocalService.getSubscriptionCommerceOrderItems(
				commerceOrder.getCommerceOrderId());

		for (CommerceOrderItem commerceOrderItem : commerceOrderItems) {
			if (_isNewSubscription(commerceOrderItem)) {
				_commerceSubscriptionEntryLocalService.
					addCommerceSubscriptionEntry(
						commerceOrderItem.getCPInstanceId(),
						commerceOrderItem.getCommerceOrderItemId(),
						serviceContext);
			}
		}
	}

	public void renewSubscriptionEntries(
			List<CommerceSubscriptionEntry> commerceSubscriptionEntries)
		throws PortalException {

		for (CommerceSubscriptionEntry commerceSubscriptionEntry :
				commerceSubscriptionEntries) {

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
			int commerceSubscriptionCycleEntriesCount =
				_commerceSubscriptionCycleEntryLocalService.
					getCommerceSubscriptionCycleEntriesCount(
						commerceSubscriptionEntry.
							getCommerceSubscriptionEntryId());

			if (commerceSubscriptionCycleEntriesCount >=
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

		_commerceSubscriptionCycleEntryLocalService.
			addCommerceSubscriptionCycleEntry(
				commerceSubscriptionEntry.getCommerceSubscriptionEntryId(),
				newCommerceOrderItemId, true);

		return newOrder;
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