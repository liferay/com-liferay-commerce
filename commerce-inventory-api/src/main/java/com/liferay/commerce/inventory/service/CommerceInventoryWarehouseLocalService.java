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

import com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseException;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for CommerceInventoryWarehouse. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryWarehouseLocalServiceUtil
 * @see com.liferay.commerce.inventory.service.base.CommerceInventoryWarehouseLocalServiceBaseImpl
 * @see com.liferay.commerce.inventory.service.impl.CommerceInventoryWarehouseLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface CommerceInventoryWarehouseLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceInventoryWarehouseLocalServiceUtil} to access the commerce inventory warehouse local service. Add custom service methods to {@link com.liferay.commerce.inventory.service.impl.CommerceInventoryWarehouseLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the commerce inventory warehouse to the database. Also notifies the appropriate model listeners.
	*
	* @param commerceInventoryWarehouse the commerce inventory warehouse
	* @return the commerce inventory warehouse that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public CommerceInventoryWarehouse addCommerceInventoryWarehouse(
		CommerceInventoryWarehouse commerceInventoryWarehouse);

	@Indexable(type = IndexableType.REINDEX)
	public CommerceInventoryWarehouse addCommerceWarehouse(String name,
		String description, boolean active, String street1, String street2,
		String street3, String city, String zip, String commerceRegionCode,
		String commerceCountryCode, double latitude, double longitude,
		ServiceContext serviceContext) throws PortalException;

	/**
	* Creates a new commerce inventory warehouse with the primary key. Does not add the commerce inventory warehouse to the database.
	*
	* @param commerceInventoryWarehouseId the primary key for the new commerce inventory warehouse
	* @return the new commerce inventory warehouse
	*/
	@Transactional(enabled = false)
	public CommerceInventoryWarehouse createCommerceInventoryWarehouse(
		long commerceInventoryWarehouseId);

	/**
	* Deletes the commerce inventory warehouse from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceInventoryWarehouse the commerce inventory warehouse
	* @return the commerce inventory warehouse that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public CommerceInventoryWarehouse deleteCommerceInventoryWarehouse(
		CommerceInventoryWarehouse commerceInventoryWarehouse);

	/**
	* Deletes the commerce inventory warehouse with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceInventoryWarehouseId the primary key of the commerce inventory warehouse
	* @return the commerce inventory warehouse that was removed
	* @throws PortalException if a commerce inventory warehouse with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public CommerceInventoryWarehouse deleteCommerceInventoryWarehouse(
		long commerceInventoryWarehouseId) throws PortalException;

	@Indexable(type = IndexableType.DELETE)
	public CommerceInventoryWarehouse deleteCommerceWarehouse(
		CommerceInventoryWarehouse commerceWarehouse) throws PortalException;

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.inventory.model.impl.CommerceInventoryWarehouseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.inventory.model.impl.CommerceInventoryWarehouseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CommerceInventoryWarehouse fetchCommerceInventoryWarehouse(
		long commerceInventoryWarehouseId);

	/**
	* Returns the commerce inventory warehouse with the matching external reference code and company.
	*
	* @param companyId the primary key of the company
	* @param externalReferenceCode the commerce inventory warehouse's external reference code
	* @return the matching commerce inventory warehouse, or <code>null</code> if a matching commerce inventory warehouse could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CommerceInventoryWarehouse fetchCommerceInventoryWarehouseByReferenceCode(
		long companyId, String externalReferenceCode);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CommerceInventoryWarehouse fetchDefaultCommerceWarehouse(
		long groupId);

	public CommerceInventoryWarehouse geolocateCommerceWarehouse(
		long commerceWarehouseId, double latitude, double longitude)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	/**
	* Returns the commerce inventory warehouse with the primary key.
	*
	* @param commerceInventoryWarehouseId the primary key of the commerce inventory warehouse
	* @return the commerce inventory warehouse
	* @throws PortalException if a commerce inventory warehouse with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CommerceInventoryWarehouse getCommerceInventoryWarehouse(
		long commerceInventoryWarehouseId) throws PortalException;

	/**
	* Returns a range of all the commerce inventory warehouses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.inventory.model.impl.CommerceInventoryWarehouseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce inventory warehouses
	* @param end the upper bound of the range of commerce inventory warehouses (not inclusive)
	* @return the range of commerce inventory warehouses
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceInventoryWarehouse> getCommerceInventoryWarehouses(
		int start, int end);

	/**
	* Returns the number of commerce inventory warehouses.
	*
	* @return the number of commerce inventory warehouses
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCommerceInventoryWarehousesCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceInventoryWarehouse> getCommerceWarehouses(
		long companyId, long groupId, boolean active, String commerceCountryCode);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceInventoryWarehouse> getCommerceWarehousesByGroupId(
		long companyId, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceInventoryWarehouse> getCommerceWarehousesByGroupIdAndActive(
		long companyId, long groupId, boolean active);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceInventoryWarehouse> getCommerceWarehousesByGroupIdAndSku(
		long companyId, long groupId, String sku);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCommerceWarehousesCount(long companyId, long groupId,
		boolean active);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCommerceWarehousesCount(long companyId, long groupId,
		boolean active, String commerceCountryCode);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CommerceInventoryWarehouse getDefaultCommerceWarehouse(long groupId)
		throws NoSuchInventoryWarehouseException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	public CommerceInventoryWarehouse importDefaultCommerceWarehouse(
		ServiceContext serviceContext) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceInventoryWarehouse> search(long companyId,
		long groupId, String keywords, Boolean active,
		String commerceCountryCode, int start, int end,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(long companyId, long groupId, String keywords,
		Boolean active, String commerceCountryCode);

	public CommerceInventoryWarehouse setActive(long commerceWarehouseId,
		boolean active) throws PortalException;

	/**
	* Updates the commerce inventory warehouse in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param commerceInventoryWarehouse the commerce inventory warehouse
	* @return the commerce inventory warehouse that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public CommerceInventoryWarehouse updateCommerceInventoryWarehouse(
		CommerceInventoryWarehouse commerceInventoryWarehouse);

	@Indexable(type = IndexableType.REINDEX)
	public CommerceInventoryWarehouse updateCommerceWarehouse(
		long commerceWarehouseId, String name, String description,
		boolean active, String street1, String street2, String street3,
		String city, String zip, String commerceRegionCode,
		String commerceCountryCode, double latitude, double longitude,
		ServiceContext serviceContext) throws PortalException;
}