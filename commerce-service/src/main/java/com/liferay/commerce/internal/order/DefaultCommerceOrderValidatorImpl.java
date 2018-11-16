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

import com.liferay.commerce.inventory.CPDefinitionInventoryEngine;
import com.liferay.commerce.inventory.CPDefinitionInventoryEngineRegistry;
import com.liferay.commerce.model.CPDefinitionInventory;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.order.CommerceOrderValidator;
import com.liferay.commerce.order.CommerceOrderValidatorResult;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.service.CPDefinitionInventoryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

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
		"commerce.order.validator.key=" + DefaultCommerceOrderValidatorImpl.KEY,
		"commerce.order.validator.priority:Integer=10"
	},
	service = CommerceOrderValidator.class
)
public class DefaultCommerceOrderValidatorImpl
	implements CommerceOrderValidator {

	public static final String KEY = "default";

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

		CPDefinitionInventory cpDefinitionInventory =
			_cpDefinitionInventoryLocalService.
				fetchCPDefinitionInventoryByCPDefinitionId(
					cpInstance.getCPDefinitionId());

		CPDefinitionInventoryEngine cpDefinitionInventoryEngine =
			_cpDefinitionInventoryEngineRegistry.getCPDefinitionInventoryEngine(
				cpDefinitionInventory);

		int minOrderQuantity = cpDefinitionInventoryEngine.getMinOrderQuantity(
			cpInstance);
		int maxOrderQuantity = cpDefinitionInventoryEngine.getMaxOrderQuantity(
			cpInstance);
		String[] allowedOrderQuantities =
			cpDefinitionInventoryEngine.getAllowedOrderQuantities(cpInstance);

		if (cpDefinitionInventoryEngine.isBackOrderAllowed(cpInstance) &&
			(quantity >= minOrderQuantity) && (quantity <= maxOrderQuantity)) {

			return new CommerceOrderValidatorResult(true);
		}

		if ((minOrderQuantity > 0) && (quantity < minOrderQuantity)) {
			return new CommerceOrderValidatorResult(
				false, "the-minimum-quantity-is-x",
				String.valueOf(minOrderQuantity),
				_getResourceBundle(commerceOrder));
		}

		if ((maxOrderQuantity > 0) && (quantity > maxOrderQuantity)) {
			return new CommerceOrderValidatorResult(
				false, "the-maximum-quantity-is-x",
				String.valueOf(maxOrderQuantity),
				_getResourceBundle(commerceOrder));
		}

		if ((allowedOrderQuantities.length > 0) &&
			!ArrayUtil.contains(
				allowedOrderQuantities, String.valueOf(quantity))) {

			return new CommerceOrderValidatorResult(
				false, "that-quantity-is-not-allowed",
				_getResourceBundle(commerceOrder));
		}

		return new CommerceOrderValidatorResult(true);
	}

	@Override
	public CommerceOrderValidatorResult validate(
			CommerceOrderItem commerceOrderItem)
		throws PortalException {

		CPInstance cpInstance = commerceOrderItem.getCPInstance();

		CPDefinitionInventory cpDefinitionInventory =
			_cpDefinitionInventoryLocalService.
				fetchCPDefinitionInventoryByCPDefinitionId(
					cpInstance.getCPDefinitionId());

		CPDefinitionInventoryEngine cpDefinitionInventoryEngine =
			_cpDefinitionInventoryEngineRegistry.getCPDefinitionInventoryEngine(
				cpDefinitionInventory);

		if (cpDefinitionInventoryEngine.isBackOrderAllowed(cpInstance)) {
			return new CommerceOrderValidatorResult(true);
		}

		int minOrderQuantity = cpDefinitionInventoryEngine.getMinOrderQuantity(
			cpInstance);
		int maxOrderQuantity = cpDefinitionInventoryEngine.getMaxOrderQuantity(
			cpInstance);
		String[] allowedOrderQuantities =
			cpDefinitionInventoryEngine.getAllowedOrderQuantities(cpInstance);

		if ((minOrderQuantity > 0) &&
			(commerceOrderItem.getQuantity() < minOrderQuantity)) {
			
			return new CommerceOrderValidatorResult(
				commerceOrderItem.getCommerceOrderItemId(), false,
				"the-minimum-quantity-is-x", String.valueOf(minOrderQuantity),
				_getResourceBundle(commerceOrderItem.getCommerceOrder()));
		}

		if ((maxOrderQuantity > 0) &&
			(commerceOrderItem.getQuantity() > maxOrderQuantity)) {

			return new CommerceOrderValidatorResult(
				commerceOrderItem.getCommerceOrderItemId(), false,
				"the-maximum-quantity-is-x", String.valueOf(maxOrderQuantity),
				_getResourceBundle(commerceOrderItem.getCommerceOrder()));
		}

		if ((allowedOrderQuantities.length > 0) &&
			!ArrayUtil.contains(
				allowedOrderQuantities,
				String.valueOf(commerceOrderItem.getQuantity()))) {

			return new CommerceOrderValidatorResult(
				commerceOrderItem.getCommerceOrderItemId(), false,
				"that-quantity-is-not-allowed",
				_getResourceBundle(commerceOrderItem.getCommerceOrder()));
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
	private CPDefinitionInventoryEngineRegistry
		_cpDefinitionInventoryEngineRegistry;

	@Reference
	private CPDefinitionInventoryLocalService
		_cpDefinitionInventoryLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private UserLocalService _userLocalService;

}