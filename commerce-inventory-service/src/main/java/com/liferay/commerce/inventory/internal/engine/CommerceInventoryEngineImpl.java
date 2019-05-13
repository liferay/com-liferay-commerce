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

package com.liferay.commerce.inventory.internal.engine;

import com.liferay.commerce.inventory.constants.CommerceInventoryConstants;
import com.liferay.commerce.inventory.engine.CommerceInventoryEngine;
import com.liferay.commerce.inventory.internal.configuration.CommerceInventoryConfigurationHelper;
import com.liferay.commerce.inventory.method.CommerceInventoryMethod;
import com.liferay.commerce.inventory.method.CommerceInventoryMethodRegistry;
import com.liferay.commerce.inventory.service.CommerceInventoryAuditLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Luca Pellizzon
 */
@Component(immediate = true, service = CommerceInventoryEngine.class)
public class CommerceInventoryEngineImpl implements CommerceInventoryEngine {

	@Override
	@Transactional(
		propagation = Propagation.REQUIRED, readOnly = false,
		rollbackFor = Exception.class
	)
	public void consumeQuantity(
			long userId, long groupId, String sku, int quantity,
			long warehouseId, long bookedQuantityId,
			Map<String, String> context)
		throws PortalException {

		String commerceInventoryEngineMethodKey =
			CommerceInventoryConfigurationHelper.
				getCommerceInventoryEngineMethodKey(
					groupId, _configurationProvider);

		CommerceInventoryMethod commerceInventoryMethod =
			_getCommerceInventoryMethod(commerceInventoryEngineMethodKey);

		commerceInventoryMethod.consumeQuantity(
			groupId, sku, quantity, warehouseId, bookedQuantityId);

		String description = "Consume Quantity: ";

		try {
			ByteArrayOutputStream byteArrayOutputStream =
				new ByteArrayOutputStream();

			ObjectOutputStream oos = new ObjectOutputStream(
				byteArrayOutputStream);

			oos.writeObject(context);
			oos.close();

			description += byteArrayOutputStream.toString();
		}
		catch (Exception e) {
			throw new PortalException(e.getMessage());
		}

		_commerceInventoryAuditLocalService.addCommerceInventoryItemEntry(
			description, sku, quantity, userId);
	}

	@Override
	public void decreaseStockQuantity(
			long groupId, String sku, int quantity, long warehouseId)
		throws PortalException {

		String commerceInventoryEngineMethodKey =
			CommerceInventoryConfigurationHelper.
				getCommerceInventoryEngineMethodKey(
					groupId, _configurationProvider);

		CommerceInventoryMethod commerceInventoryMethod =
			_getCommerceInventoryMethod(commerceInventoryEngineMethodKey);

		if (commerceInventoryMethod == null) {
			commerceInventoryMethod = _getCommerceInventoryMethod(
				CommerceInventoryConstants.DEFAULT_METHOD_NAME);
		}

		commerceInventoryMethod.decreaseStockQuantity(
			sku, quantity, warehouseId);
	}

	@Override
	public Map<String, Integer> getStockQuantities(
			long companyId, long groupId, List<String> skus)
		throws PortalException {

		String commerceInventoryEngineMethodKey =
			CommerceInventoryConfigurationHelper.
				getCommerceInventoryEngineMethodKey(
					groupId, _configurationProvider);

		CommerceInventoryMethod commerceInventoryMethod =
			_getCommerceInventoryMethod(commerceInventoryEngineMethodKey);

		if (commerceInventoryMethod == null) {
			commerceInventoryMethod = _getCommerceInventoryMethod(
				CommerceInventoryConstants.DEFAULT_METHOD_NAME);
		}

		return commerceInventoryMethod.getStockQuantities(
			companyId, groupId, skus);
	}

	@Override
	public int getStockQuantity(long companyId, long groupId, String sku)
		throws PortalException {

		String commerceInventoryEngineMethodKey =
			CommerceInventoryConfigurationHelper.
				getCommerceInventoryEngineMethodKey(
					groupId, _configurationProvider);

		CommerceInventoryMethod commerceInventoryMethod =
			_getCommerceInventoryMethod(commerceInventoryEngineMethodKey);

		if (commerceInventoryMethod == null) {
			commerceInventoryMethod = _getCommerceInventoryMethod(
				CommerceInventoryConstants.DEFAULT_METHOD_NAME);
		}

		return commerceInventoryMethod.getStockQuantity(
			companyId, groupId, sku);
	}

	@Override
	public void increaseStockQuantity(
			long groupId, String sku, int quantity, long warehouseId)
		throws PortalException {

		String commerceInventoryEngineMethodKey =
			CommerceInventoryConfigurationHelper.
				getCommerceInventoryEngineMethodKey(
					groupId, _configurationProvider);

		CommerceInventoryMethod commerceInventoryMethod =
			_getCommerceInventoryMethod(commerceInventoryEngineMethodKey);

		if (commerceInventoryMethod == null) {
			commerceInventoryMethod = _getCommerceInventoryMethod(
				CommerceInventoryConstants.DEFAULT_METHOD_NAME);
		}

		commerceInventoryMethod.increaseStockQuantity(
			sku, quantity, warehouseId);
	}

	private CommerceInventoryMethod _getCommerceInventoryMethod(String key) {
		return _commerceInventoryMethodRegistry.getCommerceInventoryMethod(key);
	}

	@Reference
	private CommerceInventoryAuditLocalService
		_commerceInventoryAuditLocalService;

	@Reference
	private CommerceInventoryMethodRegistry _commerceInventoryMethodRegistry;

	@Reference
	private ConfigurationProvider _configurationProvider;

}