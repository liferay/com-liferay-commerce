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

import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.inventory.CPDefinitionInventoryEngine;
import com.liferay.commerce.inventory.CPDefinitionInventoryEngineRegistry;
import com.liferay.commerce.model.CPDefinitionInventory;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.order.CommerceOrderValidator;
import com.liferay.commerce.order.CommerceOrderValidatorResult;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.service.CPDefinitionInventoryLocalService;
import com.liferay.commerce.service.CommerceOrderItemLocalService;
import com.liferay.portal.kernel.exception.PortalException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"commerce.order.validator.key=" + AvailabilityCommerceOrderValidatorImpl.KEY,
		"commerce.order.validator.priority:Integer=20"
	},
	service = CommerceOrderValidator.class
)
public class AvailabilityCommerceOrderValidatorImpl
	implements CommerceOrderValidator {

	public static final String KEY = "availability";

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public CommerceOrderValidatorResult validate(
			CommerceOrderItem commerceOrderItem)
		throws PortalException {

		CPInstance cpInstance = commerceOrderItem.getCPInstance();

		CPDefinition cpDefinition = cpInstance.getCPDefinition();

		if (!cpDefinition.isApproved() || !cpInstance.isApproved() ||
			!cpInstance.isPublished() || !cpInstance.isPurchasable()) {

			return new CommerceOrderValidatorResult(
				commerceOrderItem.getCommerceOrderItemId(), false,
				"product-is-no-longer-available");
		}

		CPDefinitionInventory cpDefinitionInventory =
			_cpDefinitionInventoryLocalService.
				fetchCPDefinitionInventoryByCPDefinitionId(
					cpDefinition.getCPDefinitionId());

		CPDefinitionInventoryEngine cpDefinitionInventoryEngine =
			_cpDefinitionInventoryEngineRegistry.getCPDefinitionInventoryEngine(
				cpDefinitionInventory);

		if (cpDefinitionInventoryEngine.isBackOrderAllowed(cpInstance)) {
			return new CommerceOrderValidatorResult(true);
		}

		int availableQuantity = cpDefinitionInventoryEngine.getStockQuantity(
			cpInstance);

		int orderQuantity =
			_commerceOrderItemLocalService.getCPInstanceQuantity(
				commerceOrderItem.getCPInstanceId(),
				CommerceOrderConstants.ORDER_STATUS_OPEN);

		if (orderQuantity > availableQuantity) {
			return new CommerceOrderValidatorResult(
				commerceOrderItem.getCommerceOrderItemId(), false,
				"quantity-unavailable");
		}

		return new CommerceOrderValidatorResult(true);
	}

	@Override
	public CommerceOrderValidatorResult validate(
			CPInstance cpInstance, int quantity)
		throws PortalException {

		if (cpInstance == null) {
			return new CommerceOrderValidatorResult(
				false, "please-select-a-valid-product");
		}

		CPDefinition cpDefinition = cpInstance.getCPDefinition();

		if (!cpDefinition.isApproved() || !cpInstance.isApproved() ||
			!cpInstance.isPublished() || !cpInstance.isPurchasable()) {

			return new CommerceOrderValidatorResult(
				false, "product-is-no-longer-available");
		}

		CPDefinitionInventory cpDefinitionInventory =
			_cpDefinitionInventoryLocalService.
				fetchCPDefinitionInventoryByCPDefinitionId(
					cpDefinition.getCPDefinitionId());

		CPDefinitionInventoryEngine cpDefinitionInventoryEngine =
			_cpDefinitionInventoryEngineRegistry.getCPDefinitionInventoryEngine(
				cpDefinitionInventory);

		if (cpDefinitionInventoryEngine.isBackOrderAllowed(cpInstance)) {
			return new CommerceOrderValidatorResult(true);
		}

		int availableQuantity = cpDefinitionInventoryEngine.getStockQuantity(
			cpInstance);

		int orderQuantity =
			_commerceOrderItemLocalService.getCPInstanceQuantity(
				cpInstance.getCPInstanceId(),
				CommerceOrderConstants.ORDER_STATUS_OPEN);

		orderQuantity += quantity;

		if (orderQuantity > availableQuantity) {
			return new CommerceOrderValidatorResult(
				false, "quantity-unavailable");
		}

		return new CommerceOrderValidatorResult(true);
	}

	@Reference
	private CommerceOrderItemLocalService _commerceOrderItemLocalService;

	@Reference
	private CPDefinitionInventoryEngineRegistry
		_cpDefinitionInventoryEngineRegistry;

	@Reference
	private CPDefinitionInventoryLocalService
		_cpDefinitionInventoryLocalService;

}