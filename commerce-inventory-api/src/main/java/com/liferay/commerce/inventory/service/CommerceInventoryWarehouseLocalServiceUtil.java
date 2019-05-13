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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for CommerceInventoryWarehouse. This utility wraps
 * {@link com.liferay.commerce.inventory.service.impl.CommerceInventoryWarehouseLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryWarehouseLocalService
 * @see com.liferay.commerce.inventory.service.base.CommerceInventoryWarehouseLocalServiceBaseImpl
 * @see com.liferay.commerce.inventory.service.impl.CommerceInventoryWarehouseLocalServiceImpl
 * @generated
 */
@ProviderType
public class CommerceInventoryWarehouseLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.commerce.inventory.service.impl.CommerceInventoryWarehouseLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the commerce inventory warehouse to the database. Also notifies the appropriate model listeners.
	*
	* @param commerceInventoryWarehouse the commerce inventory warehouse
	* @return the commerce inventory warehouse that was added
	*/
	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouse addCommerceInventoryWarehouse(
		com.liferay.commerce.inventory.model.CommerceInventoryWarehouse commerceInventoryWarehouse) {
		return getService()
				   .addCommerceInventoryWarehouse(commerceInventoryWarehouse);
	}

	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouse addCommerceWarehouse(
		String name, String description, boolean active, String street1,
		String street2, String street3, String city, String zip,
		String commerceRegionCode, String commerceCountryCode, double latitude,
		double longitude,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCommerceWarehouse(name, description, active, street1,
			street2, street3, city, zip, commerceRegionCode,
			commerceCountryCode, latitude, longitude, serviceContext);
	}

	/**
	* Creates a new commerce inventory warehouse with the primary key. Does not add the commerce inventory warehouse to the database.
	*
	* @param commerceInventoryWarehouseId the primary key for the new commerce inventory warehouse
	* @return the new commerce inventory warehouse
	*/
	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouse createCommerceInventoryWarehouse(
		long commerceInventoryWarehouseId) {
		return getService()
				   .createCommerceInventoryWarehouse(commerceInventoryWarehouseId);
	}

	/**
	* Deletes the commerce inventory warehouse from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceInventoryWarehouse the commerce inventory warehouse
	* @return the commerce inventory warehouse that was removed
	*/
	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouse deleteCommerceInventoryWarehouse(
		com.liferay.commerce.inventory.model.CommerceInventoryWarehouse commerceInventoryWarehouse) {
		return getService()
				   .deleteCommerceInventoryWarehouse(commerceInventoryWarehouse);
	}

	/**
	* Deletes the commerce inventory warehouse with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceInventoryWarehouseId the primary key of the commerce inventory warehouse
	* @return the commerce inventory warehouse that was removed
	* @throws PortalException if a commerce inventory warehouse with the primary key could not be found
	*/
	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouse deleteCommerceInventoryWarehouse(
		long commerceInventoryWarehouseId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .deleteCommerceInventoryWarehouse(commerceInventoryWarehouseId);
	}

	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouse deleteCommerceWarehouse(
		com.liferay.commerce.inventory.model.CommerceInventoryWarehouse commerceWarehouse)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteCommerceWarehouse(commerceWarehouse);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouse fetchCommerceInventoryWarehouse(
		long commerceInventoryWarehouseId) {
		return getService()
				   .fetchCommerceInventoryWarehouse(commerceInventoryWarehouseId);
	}

	/**
	* Returns the commerce inventory warehouse with the matching external reference code and company.
	*
	* @param companyId the primary key of the company
	* @param externalReferenceCode the commerce inventory warehouse's external reference code
	* @return the matching commerce inventory warehouse, or <code>null</code> if a matching commerce inventory warehouse could not be found
	*/
	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouse fetchCommerceInventoryWarehouseByReferenceCode(
		long companyId, String externalReferenceCode) {
		return getService()
				   .fetchCommerceInventoryWarehouseByReferenceCode(companyId,
			externalReferenceCode);
	}

	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouse fetchDefaultCommerceWarehouse(
		long groupId) {
		return getService().fetchDefaultCommerceWarehouse(groupId);
	}

	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouse geolocateCommerceWarehouse(
		long commerceWarehouseId, double latitude, double longitude)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .geolocateCommerceWarehouse(commerceWarehouseId, latitude,
			longitude);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the commerce inventory warehouse with the primary key.
	*
	* @param commerceInventoryWarehouseId the primary key of the commerce inventory warehouse
	* @return the commerce inventory warehouse
	* @throws PortalException if a commerce inventory warehouse with the primary key could not be found
	*/
	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouse getCommerceInventoryWarehouse(
		long commerceInventoryWarehouseId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getCommerceInventoryWarehouse(commerceInventoryWarehouseId);
	}

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
	public static java.util.List<com.liferay.commerce.inventory.model.CommerceInventoryWarehouse> getCommerceInventoryWarehouses(
		int start, int end) {
		return getService().getCommerceInventoryWarehouses(start, end);
	}

	/**
	* Returns the number of commerce inventory warehouses.
	*
	* @return the number of commerce inventory warehouses
	*/
	public static int getCommerceInventoryWarehousesCount() {
		return getService().getCommerceInventoryWarehousesCount();
	}

	public static java.util.List<com.liferay.commerce.inventory.model.CommerceInventoryWarehouse> getCommerceWarehouses(
		long companyId, long groupId, boolean active, String commerceCountryCode) {
		return getService()
				   .getCommerceWarehouses(companyId, groupId, active,
			commerceCountryCode);
	}

	public static java.util.List<com.liferay.commerce.inventory.model.CommerceInventoryWarehouse> getCommerceWarehousesByGroupId(
		long companyId, long groupId) {
		return getService().getCommerceWarehousesByGroupId(companyId, groupId);
	}

	public static java.util.List<com.liferay.commerce.inventory.model.CommerceInventoryWarehouse> getCommerceWarehousesByGroupIdAndActive(
		long companyId, long groupId, boolean active) {
		return getService()
				   .getCommerceWarehousesByGroupIdAndActive(companyId, groupId,
			active);
	}

	public static java.util.List<com.liferay.commerce.inventory.model.CommerceInventoryWarehouse> getCommerceWarehousesByGroupIdAndSku(
		long companyId, long groupId, String sku) {
		return getService()
				   .getCommerceWarehousesByGroupIdAndSku(companyId, groupId, sku);
	}

	public static int getCommerceWarehousesCount(long companyId, long groupId,
		boolean active) {
		return getService()
				   .getCommerceWarehousesCount(companyId, groupId, active);
	}

	public static int getCommerceWarehousesCount(long companyId, long groupId,
		boolean active, String commerceCountryCode) {
		return getService()
				   .getCommerceWarehousesCount(companyId, groupId, active,
			commerceCountryCode);
	}

	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouse getDefaultCommerceWarehouse(
		long groupId)
		throws com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseException {
		return getService().getDefaultCommerceWarehouse(groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouse importDefaultCommerceWarehouse(
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().importDefaultCommerceWarehouse(serviceContext);
	}

	public static java.util.List<com.liferay.commerce.inventory.model.CommerceInventoryWarehouse> search(
		long companyId, long groupId, String keywords, Boolean active,
		String commerceCountryCode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.inventory.model.CommerceInventoryWarehouse> orderByComparator) {
		return getService()
				   .search(companyId, groupId, keywords, active,
			commerceCountryCode, start, end, orderByComparator);
	}

	public static int searchCount(long companyId, long groupId,
		String keywords, Boolean active, String commerceCountryCode) {
		return getService()
				   .searchCount(companyId, groupId, keywords, active,
			commerceCountryCode);
	}

	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouse setActive(
		long commerceWarehouseId, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().setActive(commerceWarehouseId, active);
	}

	/**
	* Updates the commerce inventory warehouse in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param commerceInventoryWarehouse the commerce inventory warehouse
	* @return the commerce inventory warehouse that was updated
	*/
	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouse updateCommerceInventoryWarehouse(
		com.liferay.commerce.inventory.model.CommerceInventoryWarehouse commerceInventoryWarehouse) {
		return getService()
				   .updateCommerceInventoryWarehouse(commerceInventoryWarehouse);
	}

	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouse updateCommerceWarehouse(
		long commerceWarehouseId, String name, String description,
		boolean active, String street1, String street2, String street3,
		String city, String zip, String commerceRegionCode,
		String commerceCountryCode, double latitude, double longitude,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCommerceWarehouse(commerceWarehouseId, name,
			description, active, street1, street2, street3, city, zip,
			commerceRegionCode, commerceCountryCode, latitude, longitude,
			serviceContext);
	}

	public static CommerceInventoryWarehouseLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceInventoryWarehouseLocalService, CommerceInventoryWarehouseLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceInventoryWarehouseLocalService.class);

		ServiceTracker<CommerceInventoryWarehouseLocalService, CommerceInventoryWarehouseLocalService> serviceTracker =
			new ServiceTracker<CommerceInventoryWarehouseLocalService, CommerceInventoryWarehouseLocalService>(bundle.getBundleContext(),
				CommerceInventoryWarehouseLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}