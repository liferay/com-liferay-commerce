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

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;

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
 * Provides the remote service interface for CommerceInventoryWarehouse. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryWarehouseServiceUtil
 * @see com.liferay.commerce.inventory.service.base.CommerceInventoryWarehouseServiceBaseImpl
 * @see com.liferay.commerce.inventory.service.impl.CommerceInventoryWarehouseServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(property =  {
	"json.web.service.context.name=commerce", "json.web.service.context.path=CommerceInventoryWarehouse"}, service = CommerceInventoryWarehouseService.class)
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface CommerceInventoryWarehouseService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceInventoryWarehouseServiceUtil} to access the commerce inventory warehouse remote service. Add custom service methods to {@link com.liferay.commerce.inventory.service.impl.CommerceInventoryWarehouseServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public CommerceInventoryWarehouse addCommerceWarehouse(String name,
		String description, boolean active, String street1, String street2,
		String street3, String city, String zip, String commerceRegionCode,
		String commerceCountryCode, double latitude, double longitude,
		ServiceContext serviceContext) throws PortalException;

	public CommerceInventoryWarehouse addCommerceWarehouseAndGroupRel(
		String name, String description, boolean active, String street1,
		String street2, String street3, String city, String zip,
		String commerceRegionCode, String commerceCountryCode, double latitude,
		double longitude, ServiceContext serviceContext)
		throws PortalException;

	public CommerceInventoryWarehouse deleteCommerceWarehouse(
		long commerceWarehouseId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CommerceInventoryWarehouse fetchDefaultCommerceWarehouse(
		long groupId) throws PortalException;

	public CommerceInventoryWarehouse geolocateCommerceWarehouse(
		long commerceWarehouseId, double latitude, double longitude)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CommerceInventoryWarehouse getCommerceWarehouse(
		long commerceWarehouseId) throws PortalException;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	public CommerceInventoryWarehouse setActive(long commerceWarehouseId,
		boolean active) throws PortalException;

	public CommerceInventoryWarehouse updateCommerceWarehouse(
		long commerceWarehouseId, String name, String description,
		boolean active, String street1, String street2, String street3,
		String city, String zip, String commerceRegionCode,
		String commerceCountryCode, double latitude, double longitude,
		ServiceContext serviceContext) throws PortalException;

	public CommerceInventoryWarehouse updateDefaultCommerceWarehouse(
		String name, String street1, String street2, String street3,
		String city, String zip, String commerceRegionCode,
		String commerceCountryCode, double latitude, double longitude,
		ServiceContext serviceContext) throws PortalException;
}