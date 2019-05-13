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
 * Provides the local service utility for CommerceInventoryWarehouseGroupRel. This utility wraps
 * {@link com.liferay.commerce.inventory.service.impl.CommerceInventoryWarehouseGroupRelLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryWarehouseGroupRelLocalService
 * @see com.liferay.commerce.inventory.service.base.CommerceInventoryWarehouseGroupRelLocalServiceBaseImpl
 * @see com.liferay.commerce.inventory.service.impl.CommerceInventoryWarehouseGroupRelLocalServiceImpl
 * @generated
 */
@ProviderType
public class CommerceInventoryWarehouseGroupRelLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.commerce.inventory.service.impl.CommerceInventoryWarehouseGroupRelLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the commerce inventory warehouse group rel to the database. Also notifies the appropriate model listeners.
	*
	* @param commerceInventoryWarehouseGroupRel the commerce inventory warehouse group rel
	* @return the commerce inventory warehouse group rel that was added
	*/
	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouseGroupRel addCommerceInventoryWarehouseGroupRel(
		com.liferay.commerce.inventory.model.CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel) {
		return getService()
				   .addCommerceInventoryWarehouseGroupRel(commerceInventoryWarehouseGroupRel);
	}

	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouseGroupRel addCommerceWarehouseGroupRel(
		long commerceWarehouseId, boolean primary,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCommerceWarehouseGroupRel(commerceWarehouseId, primary,
			serviceContext);
	}

	/**
	* Creates a new commerce inventory warehouse group rel with the primary key. Does not add the commerce inventory warehouse group rel to the database.
	*
	* @param commerceInventoryWarehouseGroupRelId the primary key for the new commerce inventory warehouse group rel
	* @return the new commerce inventory warehouse group rel
	*/
	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouseGroupRel createCommerceInventoryWarehouseGroupRel(
		long commerceInventoryWarehouseGroupRelId) {
		return getService()
				   .createCommerceInventoryWarehouseGroupRel(commerceInventoryWarehouseGroupRelId);
	}

	/**
	* Deletes the commerce inventory warehouse group rel from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceInventoryWarehouseGroupRel the commerce inventory warehouse group rel
	* @return the commerce inventory warehouse group rel that was removed
	*/
	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouseGroupRel deleteCommerceInventoryWarehouseGroupRel(
		com.liferay.commerce.inventory.model.CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel) {
		return getService()
				   .deleteCommerceInventoryWarehouseGroupRel(commerceInventoryWarehouseGroupRel);
	}

	/**
	* Deletes the commerce inventory warehouse group rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceInventoryWarehouseGroupRelId the primary key of the commerce inventory warehouse group rel
	* @return the commerce inventory warehouse group rel that was removed
	* @throws PortalException if a commerce inventory warehouse group rel with the primary key could not be found
	*/
	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouseGroupRel deleteCommerceInventoryWarehouseGroupRel(
		long commerceInventoryWarehouseGroupRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .deleteCommerceInventoryWarehouseGroupRel(commerceInventoryWarehouseGroupRelId);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.inventory.model.impl.CommerceInventoryWarehouseGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.inventory.model.impl.CommerceInventoryWarehouseGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouseGroupRel fetchCommerceInventoryWarehouseGroupRel(
		long commerceInventoryWarehouseGroupRelId) {
		return getService()
				   .fetchCommerceInventoryWarehouseGroupRel(commerceInventoryWarehouseGroupRelId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the commerce inventory warehouse group rel with the primary key.
	*
	* @param commerceInventoryWarehouseGroupRelId the primary key of the commerce inventory warehouse group rel
	* @return the commerce inventory warehouse group rel
	* @throws PortalException if a commerce inventory warehouse group rel with the primary key could not be found
	*/
	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouseGroupRel getCommerceInventoryWarehouseGroupRel(
		long commerceInventoryWarehouseGroupRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getCommerceInventoryWarehouseGroupRel(commerceInventoryWarehouseGroupRelId);
	}

	/**
	* Returns a range of all the commerce inventory warehouse group rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.inventory.model.impl.CommerceInventoryWarehouseGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce inventory warehouse group rels
	* @param end the upper bound of the range of commerce inventory warehouse group rels (not inclusive)
	* @return the range of commerce inventory warehouse group rels
	*/
	public static java.util.List<com.liferay.commerce.inventory.model.CommerceInventoryWarehouseGroupRel> getCommerceInventoryWarehouseGroupRels(
		int start, int end) {
		return getService().getCommerceInventoryWarehouseGroupRels(start, end);
	}

	/**
	* Returns the number of commerce inventory warehouse group rels.
	*
	* @return the number of commerce inventory warehouse group rels
	*/
	public static int getCommerceInventoryWarehouseGroupRelsCount() {
		return getService().getCommerceInventoryWarehouseGroupRelsCount();
	}

	public static java.util.List<com.liferay.commerce.inventory.model.CommerceInventoryWarehouseGroupRel> getCommerceWarehouseGroupRels(
		long groupId) {
		return getService().getCommerceWarehouseGroupRels(groupId);
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

	public static long getPrimaryCommerceWarehouseId(long groupId) {
		return getService().getPrimaryCommerceWarehouseId(groupId);
	}

	/**
	* Updates the commerce inventory warehouse group rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param commerceInventoryWarehouseGroupRel the commerce inventory warehouse group rel
	* @return the commerce inventory warehouse group rel that was updated
	*/
	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouseGroupRel updateCommerceInventoryWarehouseGroupRel(
		com.liferay.commerce.inventory.model.CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel) {
		return getService()
				   .updateCommerceInventoryWarehouseGroupRel(commerceInventoryWarehouseGroupRel);
	}

	public static CommerceInventoryWarehouseGroupRelLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceInventoryWarehouseGroupRelLocalService, CommerceInventoryWarehouseGroupRelLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceInventoryWarehouseGroupRelLocalService.class);

		ServiceTracker<CommerceInventoryWarehouseGroupRelLocalService, CommerceInventoryWarehouseGroupRelLocalService> serviceTracker =
			new ServiceTracker<CommerceInventoryWarehouseGroupRelLocalService, CommerceInventoryWarehouseGroupRelLocalService>(bundle.getBundleContext(),
				CommerceInventoryWarehouseGroupRelLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}