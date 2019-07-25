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

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.model.CPDefinitionInventory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

/**
 * Provides the remote service interface for CPDefinitionInventory. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Alessio Antonio Rendina
 * @see CPDefinitionInventoryServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(
	property = {
		"json.web.service.context.name=commerce",
		"json.web.service.context.path=CPDefinitionInventory"
	},
	service = CPDefinitionInventoryService.class
)
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface CPDefinitionInventoryService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CPDefinitionInventoryServiceUtil} to access the cp definition inventory remote service. Add custom service methods to <code>com.liferay.commerce.service.impl.CPDefinitionInventoryServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public CPDefinitionInventory addCPDefinitionInventory(
			long cpDefinitionId, String cpDefinitionInventoryEngine,
			String lowStockActivity, boolean displayAvailability,
			boolean displayStockQuantity, int minStockQuantity,
			boolean backOrders, int minOrderQuantity, int maxOrderQuantity,
			String allowedOrderQuantities, int multipleOrderQuantity)
		throws PortalException;

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
	 * @return
	 * @throws PortalException
	 * @deprecated As of Mueller (7.2.x), see {@link
	 #addCPDefinitionInventory(long, String, String, boolean,
	 boolean, int, boolean, int, int, String, int)}
	 */
	@Deprecated
	public CPDefinitionInventory addCPDefinitionInventory(
			long cpDefinitionId, String cpDefinitionInventoryEngine,
			String lowStockActivity, boolean displayAvailability,
			boolean displayStockQuantity, int minStockQuantity,
			boolean backOrders, int minOrderQuantity, int maxOrderQuantity,
			String allowedOrderQuantities, int multipleOrderQuantity,
			ServiceContext serviceContext)
		throws PortalException;

	public void deleteCPDefinitionInventory(long cpDefinitionInventoryId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CPDefinitionInventory fetchCPDefinitionInventoryByCPDefinitionId(
			long cpDefinitionId)
		throws PortalException;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	public CPDefinitionInventory updateCPDefinitionInventory(
			long groupId, long cpDefinitionInventoryId,
			String cpDefinitionInventoryEngine, String lowStockActivity,
			boolean displayAvailability, boolean displayStockQuantity,
			int minStockQuantity, boolean backOrders, int minOrderQuantity,
			int maxOrderQuantity, String allowedOrderQuantities,
			int multipleOrderQuantity)
		throws PortalException;

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
	 * @return
	 * @throws PortalException
	 * @deprecated As of Mueller (7.2.x), see {@link
	 #updateCPDefinitionInventory(long, long, String, String,
	 boolean, boolean, int, boolean, int, int, String, int)}
	 */
	@Deprecated
	public CPDefinitionInventory updateCPDefinitionInventory(
			long cpDefinitionInventoryId, String cpDefinitionInventoryEngine,
			String lowStockActivity, boolean displayAvailability,
			boolean displayStockQuantity, int minStockQuantity,
			boolean backOrders, int minOrderQuantity, int maxOrderQuantity,
			String allowedOrderQuantities, int multipleOrderQuantity,
			ServiceContext serviceContext)
		throws PortalException;

}