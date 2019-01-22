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

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.constants.CommerceSubscriptionEntryConstants;
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
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.math.BigDecimal;

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

		serviceContext.setScopeGroupId(commerceOrder.getGroupId());

		CommerceAccount commerceAccount = commerceOrder.getCommerceAccount();

		serviceContext.setUserId(commerceAccount.getUserId());

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

		if (nextIterationDate.before(now) &&
			(commerceSubscriptionEntry.getSubscriptionStatus() ==
				CommerceSubscriptionEntryConstants.
					SUBSCRIPTION_STATUS_ACTIVE)) {

			CommerceOrder commerceOrder = _addCommerceOrder(
				commerceSubscriptionEntry);

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setScopeGroupId(commerceOrder.getGroupId());
			serviceContext.setUserId(commerceOrder.getUserId());

			_commerceOrderLocalService.checkoutCommerceOrder(
				commerceOrder.getCommerceOrderId(), null, serviceContext);
		}

		if (commerceSubscriptionEntry.getMaxSubscriptionCycles() > 0) {
			int commerceSubscriptionCycleEntriesCount =
				_commerceSubscriptionCycleEntryLocalService.
					getCommerceSubscriptionCycleEntriesCount(
						commerceSubscriptionEntry.
							getCommerceSubscriptionEntryId());

			if (commerceSubscriptionCycleEntriesCount >=
					commerceSubscriptionEntry.getMaxSubscriptionCycles()) {

				_commerceSubscriptionEntryLocalService.updateSubscriptionStatus(
					commerceSubscriptionEntry.getCommerceSubscriptionEntryId(),
					CommerceSubscriptionEntryConstants.
						SUBSCRIPTION_STATUS_COMPLETED);
			}
		}
	}

	private CommerceOrder _addCommerceOrder(
			CommerceSubscriptionEntry commerceSubscriptionEntry)
		throws PortalException {

		CommerceOrderItem oldCommerceOrderItem =
			_commerceOrderItemLocalService.getCommerceOrderItem(
				commerceSubscriptionEntry.getCommerceOrderItemId());

		CommerceOrder oldCommerceOrder =
			oldCommerceOrderItem.getCommerceOrder();

		ServiceContext serviceContext = new ServiceContext();

		ExpandoBridge expandoBridge = oldCommerceOrder.getExpandoBridge();

		serviceContext.setExpandoBridgeAttributes(
			expandoBridge.getAttributes());

		serviceContext.setCompanyId(oldCommerceOrder.getCompanyId());
		serviceContext.setScopeGroupId(oldCommerceOrder.getGroupId());
		serviceContext.setUserId(oldCommerceOrder.getUserId());
		serviceContext.setUuid(PortalUUIDUtil.generate());

		CommerceOrder newCommerceOrder =
			_commerceOrderLocalService.addCommerceOrder(
				oldCommerceOrder.getCommerceAccountId(),
				oldCommerceOrder.getCommerceCurrencyId(), 0,
				oldCommerceOrder.getShippingAddressId(), null, 0, null,
				oldCommerceOrder.getPurchaseOrderNumber(), BigDecimal.ZERO,
				BigDecimal.ZERO, BigDecimal.ZERO,
				CommerceOrderConstants.PAYMENT_STATUS_PENDING,
				CommerceOrderConstants.ORDER_STATUS_OPEN, serviceContext);

		newCommerceOrder.setBillingAddressId(
			oldCommerceOrder.getBillingAddressId());
		newCommerceOrder.setCommercePaymentMethodKey(
			oldCommerceOrder.getCommercePaymentMethodKey());
		newCommerceOrder.setCommerceShippingMethodId(
			oldCommerceOrder.getCommerceShippingMethodId());

		long newCommerceOrderItemId = _counterLocalService.increment();

		CommerceOrderItem newCommerceOrderItem =
			(CommerceOrderItem)oldCommerceOrderItem.clone();

		newCommerceOrderItem.setCommerceOrderItemId(newCommerceOrderItemId);
		newCommerceOrderItem.setCommerceOrderId(
			newCommerceOrder.getCommerceOrderId());

		newCommerceOrderItem =
			_commerceOrderItemLocalService.addCommerceOrderItem(
				newCommerceOrderItem);

		newCommerceOrder.setSubtotal(newCommerceOrderItem.getFinalPrice());
		newCommerceOrder.setTotal(newCommerceOrderItem.getFinalPrice());

		newCommerceOrder.setPaymentStatus(
			CommerceOrderConstants.PAYMENT_STATUS_PENDING);
		newCommerceOrder.setOrderStatus(
			CommerceOrderConstants.ORDER_STATUS_SUBSCRIPTION);

		newCommerceOrder = _commerceOrderLocalService.updateCommerceOrder(
			newCommerceOrder);

		// Add CommerceSubscriptionCycleEntry

		_commerceSubscriptionCycleEntryLocalService.
			addCommerceSubscriptionCycleEntry(
				commerceSubscriptionEntry.getCommerceSubscriptionEntryId(),
				newCommerceOrderItemId, true);

		return newCommerceOrder;
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