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

package com.liferay.commerce.inventory.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceInventoryWarehouseItemService}.
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryWarehouseItemService
 * @generated
 */
public class CommerceInventoryWarehouseItemServiceWrapper
	implements CommerceInventoryWarehouseItemService,
			   ServiceWrapper<CommerceInventoryWarehouseItemService> {

	public CommerceInventoryWarehouseItemServiceWrapper(
		CommerceInventoryWarehouseItemService
			commerceInventoryWarehouseItemService) {

		_commerceInventoryWarehouseItemService =
			commerceInventoryWarehouseItemService;
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceInventoryWarehouseItemServiceUtil} to access the commerce inventory warehouse item remote service. Add custom service methods to <code>com.liferay.commerce.inventory.service.impl.CommerceInventoryWarehouseItemServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem
			addCommerceInventoryWarehouseItem(
				long userId, long commerceInventoryWarehouseId, String sku,
				int quantity)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryWarehouseItemService.
			addCommerceInventoryWarehouseItem(
				userId, commerceInventoryWarehouseId, sku, quantity);
	}

	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem
			addCommerceInventoryWarehouseItem(
				long userId, long commerceInventoryWarehouseId,
				String externalReferenceCode, String sku, int quantity)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryWarehouseItemService.
			addCommerceInventoryWarehouseItem(
				userId, commerceInventoryWarehouseId, externalReferenceCode,
				sku, quantity);
	}

	@Override
	public void deleteCommerceInventoryWarehouseItem(
			long commerceInventoryWarehouseItemId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commerceInventoryWarehouseItemService.
			deleteCommerceInventoryWarehouseItem(
				commerceInventoryWarehouseItemId);
	}

	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem
			fetchCommerceInventoryWarehouseItem(
				long commerceInventoryWarehouseId, String sku)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryWarehouseItemService.
			fetchCommerceInventoryWarehouseItem(
				commerceInventoryWarehouseId, sku);
	}

	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem
			fetchCommerceInventoryWarehouseItemByReferenceCode(
				long companyId, String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryWarehouseItemService.
			fetchCommerceInventoryWarehouseItemByReferenceCode(
				companyId, externalReferenceCode);
	}

	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem
			getCommerceInventoryWarehouseItem(
				long commerceInventoryWarehouseItemId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryWarehouseItemService.
			getCommerceInventoryWarehouseItem(commerceInventoryWarehouseItemId);
	}

	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem
			getCommerceInventoryWarehouseItemByReferenceCode(
				long companyId, String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryWarehouseItemService.
			getCommerceInventoryWarehouseItemByReferenceCode(
				companyId, externalReferenceCode);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem>
				getCommerceInventoryWarehouseItems(
					long commerceInventoryWarehouseId, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryWarehouseItemService.
			getCommerceInventoryWarehouseItems(
				commerceInventoryWarehouseId, start, end);
	}

	@Override
	public int getCommerceInventoryWarehouseItemsCount(
			long commerceInventoryWarehouseId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryWarehouseItemService.
			getCommerceInventoryWarehouseItemsCount(
				commerceInventoryWarehouseId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceInventoryWarehouseItemService.
			getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem
			updateCommerceInventoryWarehouseItem(
				long commerceInventoryWarehouseItemId, int quantity)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryWarehouseItemService.
			updateCommerceInventoryWarehouseItem(
				commerceInventoryWarehouseItemId, quantity);
	}

	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem
			upsertCommerceInventoryWarehouseItem(
				long companyId, long userId, long commerceInventoryWarehouseId,
				String externalReferenceCode, String sku, int quantity)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryWarehouseItemService.
			upsertCommerceInventoryWarehouseItem(
				companyId, userId, commerceInventoryWarehouseId,
				externalReferenceCode, sku, quantity);
	}

	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem
			upsertCommerceInventoryWarehouseItem(
				long userId, long commerceInventoryWarehouseId, String sku,
				int quantity)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryWarehouseItemService.
			upsertCommerceInventoryWarehouseItem(
				userId, commerceInventoryWarehouseId, sku, quantity);
	}

	@Override
	public CommerceInventoryWarehouseItemService getWrappedService() {
		return _commerceInventoryWarehouseItemService;
	}

	@Override
	public void setWrappedService(
		CommerceInventoryWarehouseItemService
			commerceInventoryWarehouseItemService) {

		_commerceInventoryWarehouseItemService =
			commerceInventoryWarehouseItemService;
	}

	private CommerceInventoryWarehouseItemService
		_commerceInventoryWarehouseItemService;

}