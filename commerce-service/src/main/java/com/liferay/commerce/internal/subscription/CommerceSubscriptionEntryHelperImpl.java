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
import com.liferay.commerce.constants.CommerceSubscriptionEntryConstants;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.model.CommerceSubscriptionEntry;
import com.liferay.commerce.payment.engine.CommerceSubscriptionEngine;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CProduct;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.commerce.service.CommerceOrderItemLocalService;
import com.liferay.commerce.service.CommerceSubscriptionEntryLocalService;
import com.liferay.commerce.subscription.CommerceSubscriptionEntryHelper;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.Date;
import java.util.List;
import java.util.Objects;

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

		CommerceAccount commerceAccount = commerceOrder.getCommerceAccount();

		List<CommerceOrderItem> commerceOrderItems =
			_commerceOrderItemLocalService.getSubscriptionCommerceOrderItems(
				commerceOrder.getCommerceOrderId());

		for (CommerceOrderItem commerceOrderItem : commerceOrderItems) {
			CPInstance cpInstance = commerceOrderItem.getCPInstance();

			if (_isNewSubscription(commerceOrderItem)) {
				_commerceSubscriptionEntryLocalService.
					addCommerceSubscriptionEntry(
						commerceAccount.getUserId(), commerceOrder.getGroupId(),
						cpInstance.getCPInstanceUuid(),
						commerceOrderItem.getCProductId(),
						commerceOrderItem.getCommerceOrderItemId());
			}
		}
	}

	public void checkSubscriptionEntriesStatus(
			List<CommerceSubscriptionEntry> commerceSubscriptionEntries)
		throws Exception {

		for (CommerceSubscriptionEntry commerceSubscriptionEntry :
				commerceSubscriptionEntries) {

			checkSubscriptionStatus(commerceSubscriptionEntry);
		}
	}

	public void checkSubscriptionStatus(
			CommerceSubscriptionEntry commerceSubscriptionEntry)
		throws Exception {

		Date now = new Date();

		Date nextIterationDate =
			commerceSubscriptionEntry.getNextIterationDate();

		CommerceOrderItem commerceOrderItem =
			commerceSubscriptionEntry.fetchCommerceOrderItem();

		if ((commerceOrderItem != null) && now.after(nextIterationDate)) {
			CommerceOrder commerceOrder = commerceOrderItem.getCommerceOrder();

			boolean subscriptionValid =
				_commerceSubscriptionEngine.getSubscriptionValidity(
					commerceOrder.getCommerceOrderId());

			if (subscriptionValid &&
				Objects.equals(
					CommerceSubscriptionEntryConstants.
						SUBSCRIPTION_STATUS_ACTIVE,
					commerceSubscriptionEntry.getSubscriptionStatus())) {

				_commerceSubscriptionEntryLocalService.
					incrementCommerceSubscriptionEntryCycle(
						commerceSubscriptionEntry.
							getCommerceSubscriptionEntryId());
			}
			else {
				_commerceSubscriptionEntryLocalService.updateSubscriptionStatus(
					commerceSubscriptionEntry.getCommerceSubscriptionEntryId(),
					CommerceSubscriptionEntryConstants.
						SUBSCRIPTION_STATUS_SUSPENDED);
			}
		}
	}

	private boolean _isNewSubscription(CommerceOrderItem commerceOrderItem)
		throws PortalException {

		CPInstance cpInstance = commerceOrderItem.getCPInstance();

		CProduct cProduct = commerceOrderItem.getCProduct();

		CommerceSubscriptionEntry commerceSubscriptionEntry =
			_commerceSubscriptionEntryLocalService.
				fetchCommerceSubscriptionEntries(
					cpInstance.getCPInstanceUuid(), cProduct.getCProductId(),
					commerceOrderItem.getCommerceOrderItemId());

		if (commerceSubscriptionEntry != null) {
			return false;
		}

		return true;
	}

	@Reference
	private CommerceChannelLocalService _commerceChannelLocalService;

	@Reference
	private CommerceOrderItemLocalService _commerceOrderItemLocalService;

	@Reference
	private CommerceSubscriptionEngine _commerceSubscriptionEngine;

	@Reference
	private CommerceSubscriptionEntryLocalService
		_commerceSubscriptionEntryLocalService;

}