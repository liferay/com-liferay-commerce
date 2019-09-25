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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for CommerceInventoryWarehouseItem. This utility wraps
 * <code>com.liferay.commerce.inventory.service.impl.CommerceInventoryWarehouseItemServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryWarehouseItemService
 * @generated
 */
public class CommerceInventoryWarehouseItemServiceUtil {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.inventory.service.impl.CommerceInventoryWarehouseItemServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceInventoryWarehouseItemServiceUtil} to access the commerce inventory warehouse item remote service. Add custom service methods to <code>com.liferay.commerce.inventory.service.impl.CommerceInventoryWarehouseItemServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static
		com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem
				addCommerceInventoryWarehouseItem(
					long userId, long commerceInventoryWarehouseId, String sku,
					int quantity)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCommerceInventoryWarehouseItem(
			userId, commerceInventoryWarehouseId, sku, quantity);
	}

<<<<<<< HEAD
	public static
		com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem
				addCommerceInventoryWarehouseItem(
					long userId, long commerceInventoryWarehouseId,
					String externalReferenceCode, String sku, int quantity)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCommerceInventoryWarehouseItem(
			userId, commerceInventoryWarehouseId, externalReferenceCode, sku,
			quantity);
=======
	public static int countUpdatedItemsByM(
			long companyId, java.util.Date startDate, java.util.Date endDate)
		throws com.liferay.portal.kernel.security.auth.PrincipalException {

		return getService().countUpdatedItemsByM(companyId, startDate, endDate);
>>>>>>> COMMERCE-1835 auto generated code (blade gw buildService)
	}

	public static void deleteCommerceInventoryWarehouseItem(
			long commerceInventoryWarehouseItemId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteCommerceInventoryWarehouseItem(
			commerceInventoryWarehouseItemId);
	}

	public static
		com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem
				fetchCommerceInventoryWarehouseItem(
					long commerceInventoryWarehouseId, String sku)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().fetchCommerceInventoryWarehouseItem(
			commerceInventoryWarehouseId, sku);
	}

	public static java.util.List
		<com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem>
				findUpdatedItemsByM(
					long companyId, java.util.Date startDate,
					java.util.Date endDate, int start, int end)
			throws com.liferay.portal.kernel.security.auth.PrincipalException {

		return getService().findUpdatedItemsByM(
			companyId, startDate, endDate, start, end);
	}

	public static
		com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem
				fetchCommerceInventoryWarehouseItemByReferenceCode(
					long companyId, String externalReferenceCode)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().fetchCommerceInventoryWarehouseItemByReferenceCode(
			companyId, externalReferenceCode);
	}

	public static
		com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem
				getCommerceInventoryWarehouseItem(
					long commerceInventoryWarehouseItemId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommerceInventoryWarehouseItem(
			commerceInventoryWarehouseItemId);
	}

	public static
		com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem
				getCommerceInventoryWarehouseItemByReferenceCode(
					long companyId, String externalReferenceCode)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommerceInventoryWarehouseItemByReferenceCode(
			companyId, externalReferenceCode);
	}

	public static java.util.List
		<com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem>
				getCommerceInventoryWarehouseItems(
					long commerceInventoryWarehouseId, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommerceInventoryWarehouseItems(
			commerceInventoryWarehouseId, start, end);
	}

	public static int getCommerceInventoryWarehouseItemsCount(
			long commerceInventoryWarehouseId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommerceInventoryWarehouseItemsCount(
			commerceInventoryWarehouseId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static
		com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem
				updateCommerceInventoryWarehouseItem(
					long commerceInventoryWarehouseItemId, int quantity)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateCommerceInventoryWarehouseItem(
			commerceInventoryWarehouseItemId, quantity);
	}

	public static
		com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem
				upsertCommerceInventoryWarehouseItem(
					long companyId, long userId,
					long commerceInventoryWarehouseId,
					String externalReferenceCode, String sku, int quantity)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().upsertCommerceInventoryWarehouseItem(
			companyId, userId, commerceInventoryWarehouseId,
			externalReferenceCode, sku, quantity);
	}

	public static
		com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem
				upsertCommerceInventoryWarehouseItem(
					long userId, long commerceInventoryWarehouseId, String sku,
					int quantity)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().upsertCommerceInventoryWarehouseItem(
			userId, commerceInventoryWarehouseId, sku, quantity);
	}

	public static CommerceInventoryWarehouseItemService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CommerceInventoryWarehouseItemService,
		 CommerceInventoryWarehouseItemService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CommerceInventoryWarehouseItemService.class);

		ServiceTracker
			<CommerceInventoryWarehouseItemService,
			 CommerceInventoryWarehouseItemService> serviceTracker =
				new ServiceTracker
					<CommerceInventoryWarehouseItemService,
					 CommerceInventoryWarehouseItemService>(
						 bundle.getBundleContext(),
						 CommerceInventoryWarehouseItemService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}