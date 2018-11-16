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

package com.liferay.commerce.internal.order;

import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.order.CommerceOrderValidator;
import com.liferay.commerce.order.CommerceOrderValidatorResult;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CPSubscriptionInfo;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"commerce.order.validator.key=" + SubscriptionCommerceOrderValidatorImpl.KEY,
		"commerce.order.validator.priority:Integer=30"
	},
	service = CommerceOrderValidator.class
)
public class SubscriptionCommerceOrderValidatorImpl
	implements CommerceOrderValidator {

	public static final String KEY = "subscription";

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public CommerceOrderValidatorResult validate(
			CommerceOrder commerceOrder, CPInstance cpInstance, int quantity)
		throws PortalException {

		if (cpInstance == null) {
			return new CommerceOrderValidatorResult(false);
		}

		List<CommerceOrderItem> commerceOrderItems =
			commerceOrder.getCommerceOrderItems();

		if (commerceOrderItems.size() <= 0) {
			return new CommerceOrderValidatorResult(true);
		}

		CPSubscriptionInfo cpSubscriptionInfo =
			cpInstance.getCPSubscriptionInfo();

		CommerceOrderItem commerceOrderItem = commerceOrderItems.get(0);

		boolean subscription = commerceOrderItem.getSubscription();

		if (subscription && (cpSubscriptionInfo == null)) {
			return new CommerceOrderValidatorResult(
				commerceOrderItem.getCommerceOrderItemId(), false,
				"your-cart-contains-recurring-items-only-one-product-type-is-" +
					"allowed-per-order",
				_getResourceBundle(commerceOrder));
		}
		else if (!subscription && (cpSubscriptionInfo != null)) {
			return new CommerceOrderValidatorResult(
				commerceOrderItem.getCommerceOrderItemId(), false,
				"your-cart-contains-recurring-items-only-one-product-type-is-" +
					"allowed-per-order",
				_getResourceBundle(commerceOrder));
		}

		return new CommerceOrderValidatorResult(true);
	}

	@Override
	public CommerceOrderValidatorResult validate(
			CommerceOrderItem commerceOrderItem)
		throws PortalException {

		CommerceOrder commerceOrder = commerceOrderItem.getCommerceOrder();

		List<CommerceOrderItem> commerceOrderItems =
			commerceOrder.getCommerceOrderItems();

		if (commerceOrderItems.size() <= 1) {
			return new CommerceOrderValidatorResult(true);
		}

		for (CommerceOrderItem curCommerceOrderItem : commerceOrderItems) {
			if (curCommerceOrderItem.equals(commerceOrderItem)) {
				continue;
			}

			if (curCommerceOrderItem.getSubscription() !=
					commerceOrderItem.getSubscription()) {

				return new CommerceOrderValidatorResult(
					commerceOrderItem.getCommerceOrderItemId(), false,
					"your-cart-contains-recurring-items-only-one-product-" +
						"type-is-allowed-per-order",
					_getResourceBundle(commerceOrder));
			}
		}

		return new CommerceOrderValidatorResult(true);
	}

	private ResourceBundle _getResourceBundle(CommerceOrder commerceOrder)
		throws PortalException {

		Locale locale = _portal.getSiteDefaultLocale(
			commerceOrder.getSiteGroupId());

		User user = _userLocalService.fetchUser(commerceOrder.getUserId());

		if (user != null) {
			locale = user.getLocale();
		}

		return ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());
	}

	@Reference
	private Portal _portal;

	@Reference
	private UserLocalService _userLocalService;

}