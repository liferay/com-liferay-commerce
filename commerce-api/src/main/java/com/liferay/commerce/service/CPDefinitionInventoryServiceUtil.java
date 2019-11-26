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

package com.liferay.commerce.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for CPDefinitionInventory. This utility wraps
 * <code>com.liferay.commerce.service.impl.CPDefinitionInventoryServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Alessio Antonio Rendina
 * @see CPDefinitionInventoryService
 * @generated
 */
public class CPDefinitionInventoryServiceUtil {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.service.impl.CPDefinitionInventoryServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CPDefinitionInventoryServiceUtil} to access the cp definition inventory remote service. Add custom service methods to <code>com.liferay.commerce.service.impl.CPDefinitionInventoryServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static com.liferay.commerce.model.CPDefinitionInventory
			addCPDefinitionInventory(
				long cpDefinitionId, String cpDefinitionInventoryEngine,
				String lowStockActivity, boolean displayAvailability,
				boolean displayStockQuantity, int minStockQuantity,
				boolean backOrders, int minOrderQuantity, int maxOrderQuantity,
				String allowedOrderQuantities, int multipleOrderQuantity)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCPDefinitionInventory(
			cpDefinitionId, cpDefinitionInventoryEngine, lowStockActivity,
			displayAvailability, displayStockQuantity, minStockQuantity,
			backOrders, minOrderQuantity, maxOrderQuantity,
			allowedOrderQuantities, multipleOrderQuantity);
	}

	/**
	 * Adds new CP definition inventory.
	 *
	 * @param cpDefinitionId
	 * @param cpDefinitionInventoryEngine
	 * @param lowStockActivity
	 * @param displayAvailability
	 * @param displayStockQuantity
	 * @param minStockQuantity
	 * @param backOrders
	 * @param minOrderQuantity
	 * @param maxOrderQuantity
	 * @param allowedOrderQuantities
	 * @param multipleOrderQuantity
	 * @param serviceContext
	 * @throws PortalException
	 * @deprecated As of Mueller (7.2.x), see {@link
	 #addCPDefinitionInventory(long, String, String, boolean,
	 boolean, int, boolean, int, int, String, int)}
	 */
	@Deprecated
	public static com.liferay.commerce.model.CPDefinitionInventory
			addCPDefinitionInventory(
				long cpDefinitionId, String cpDefinitionInventoryEngine,
				String lowStockActivity, boolean displayAvailability,
				boolean displayStockQuantity, int minStockQuantity,
				boolean backOrders, int minOrderQuantity, int maxOrderQuantity,
				String allowedOrderQuantities, int multipleOrderQuantity,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCPDefinitionInventory(
			cpDefinitionId, cpDefinitionInventoryEngine, lowStockActivity,
			displayAvailability, displayStockQuantity, minStockQuantity,
			backOrders, minOrderQuantity, maxOrderQuantity,
			allowedOrderQuantities, multipleOrderQuantity, serviceContext);
	}

	public static void deleteCPDefinitionInventory(long cpDefinitionInventoryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteCPDefinitionInventory(cpDefinitionInventoryId);
	}

	public static com.liferay.commerce.model.CPDefinitionInventory
			fetchCPDefinitionInventoryByCPDefinitionId(long cpDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().fetchCPDefinitionInventoryByCPDefinitionId(
			cpDefinitionId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.commerce.model.CPDefinitionInventory
			updateCPDefinitionInventory(
				long groupId, long cpDefinitionInventoryId,
				String cpDefinitionInventoryEngine, String lowStockActivity,
				boolean displayAvailability, boolean displayStockQuantity,
				int minStockQuantity, boolean backOrders, int minOrderQuantity,
				int maxOrderQuantity, String allowedOrderQuantities,
				int multipleOrderQuantity)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateCPDefinitionInventory(
			groupId, cpDefinitionInventoryId, cpDefinitionInventoryEngine,
			lowStockActivity, displayAvailability, displayStockQuantity,
			minStockQuantity, backOrders, minOrderQuantity, maxOrderQuantity,
			allowedOrderQuantities, multipleOrderQuantity);
	}

	/**
	 * Updates CP definition inventory
	 *
	 * @param cpDefinitionInventoryId
	 * @param cpDefinitionInventoryEngine
	 * @param lowStockActivity
	 * @param displayAvailability
	 * @param displayStockQuantity
	 * @param minStockQuantity
	 * @param backOrders
	 * @param minOrderQuantity
	 * @param maxOrderQuantity
	 * @param allowedOrderQuantities
	 * @param multipleOrderQuantity
	 * @param serviceContext
	 * @throws PortalException
	 * @deprecated As of Mueller (7.2.x), see {@link
	 #updateCPDefinitionInventory(long, long, String, String,
	 boolean, boolean, int, boolean, int, int, String, int)}
	 */
	@Deprecated
	public static com.liferay.commerce.model.CPDefinitionInventory
			updateCPDefinitionInventory(
				long cpDefinitionInventoryId,
				String cpDefinitionInventoryEngine, String lowStockActivity,
				boolean displayAvailability, boolean displayStockQuantity,
				int minStockQuantity, boolean backOrders, int minOrderQuantity,
				int maxOrderQuantity, String allowedOrderQuantities,
				int multipleOrderQuantity,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateCPDefinitionInventory(
			cpDefinitionInventoryId, cpDefinitionInventoryEngine,
			lowStockActivity, displayAvailability, displayStockQuantity,
			minStockQuantity, backOrders, minOrderQuantity, maxOrderQuantity,
			allowedOrderQuantities, multipleOrderQuantity, serviceContext);
	}

	public static CPDefinitionInventoryService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CPDefinitionInventoryService, CPDefinitionInventoryService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CPDefinitionInventoryService.class);

		ServiceTracker
			<CPDefinitionInventoryService, CPDefinitionInventoryService>
				serviceTracker =
					new ServiceTracker
						<CPDefinitionInventoryService,
						 CPDefinitionInventoryService>(
							 bundle.getBundleContext(),
							 CPDefinitionInventoryService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}