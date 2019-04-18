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

package com.liferay.commerce.product.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for CommerceChannelFilter. This utility wraps
 * {@link com.liferay.commerce.product.service.impl.CommerceChannelFilterLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Marco Leo
 * @see CommerceChannelFilterLocalService
 * @see com.liferay.commerce.product.service.base.CommerceChannelFilterLocalServiceBaseImpl
 * @see com.liferay.commerce.product.service.impl.CommerceChannelFilterLocalServiceImpl
 * @generated
 */
@ProviderType
public class CommerceChannelFilterLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.commerce.product.service.impl.CommerceChannelFilterLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the commerce channel filter to the database. Also notifies the appropriate model listeners.
	*
	* @param commerceChannelFilter the commerce channel filter
	* @return the commerce channel filter that was added
	*/
	public static com.liferay.commerce.product.model.CommerceChannelFilter addCommerceChannelFilter(
		com.liferay.commerce.product.model.CommerceChannelFilter commerceChannelFilter) {
		return getService().addCommerceChannelFilter(commerceChannelFilter);
	}

	/**
	* Creates a new commerce channel filter with the primary key. Does not add the commerce channel filter to the database.
	*
	* @param commerceChannelFilterId the primary key for the new commerce channel filter
	* @return the new commerce channel filter
	*/
	public static com.liferay.commerce.product.model.CommerceChannelFilter createCommerceChannelFilter(
		long commerceChannelFilterId) {
		return getService().createCommerceChannelFilter(commerceChannelFilterId);
	}

	/**
	* Deletes the commerce channel filter from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceChannelFilter the commerce channel filter
	* @return the commerce channel filter that was removed
	*/
	public static com.liferay.commerce.product.model.CommerceChannelFilter deleteCommerceChannelFilter(
		com.liferay.commerce.product.model.CommerceChannelFilter commerceChannelFilter) {
		return getService().deleteCommerceChannelFilter(commerceChannelFilter);
	}

	/**
	* Deletes the commerce channel filter with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceChannelFilterId the primary key of the commerce channel filter
	* @return the commerce channel filter that was removed
	* @throws PortalException if a commerce channel filter with the primary key could not be found
	*/
	public static com.liferay.commerce.product.model.CommerceChannelFilter deleteCommerceChannelFilter(
		long commerceChannelFilterId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteCommerceChannelFilter(commerceChannelFilterId);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.product.model.impl.CommerceChannelFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.product.model.impl.CommerceChannelFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.commerce.product.model.CommerceChannelFilter fetchCommerceChannelFilter(
		long commerceChannelFilterId) {
		return getService().fetchCommerceChannelFilter(commerceChannelFilterId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the commerce channel filter with the primary key.
	*
	* @param commerceChannelFilterId the primary key of the commerce channel filter
	* @return the commerce channel filter
	* @throws PortalException if a commerce channel filter with the primary key could not be found
	*/
	public static com.liferay.commerce.product.model.CommerceChannelFilter getCommerceChannelFilter(
		long commerceChannelFilterId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCommerceChannelFilter(commerceChannelFilterId);
	}

	/**
	* Returns a range of all the commerce channel filters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.product.model.impl.CommerceChannelFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce channel filters
	* @param end the upper bound of the range of commerce channel filters (not inclusive)
	* @return the range of commerce channel filters
	*/
	public static java.util.List<com.liferay.commerce.product.model.CommerceChannelFilter> getCommerceChannelFilters(
		int start, int end) {
		return getService().getCommerceChannelFilters(start, end);
	}

	/**
	* Returns the number of commerce channel filters.
	*
	* @return the number of commerce channel filters
	*/
	public static int getCommerceChannelFiltersCount() {
		return getService().getCommerceChannelFiltersCount();
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

	/**
	* Updates the commerce channel filter in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param commerceChannelFilter the commerce channel filter
	* @return the commerce channel filter that was updated
	*/
	public static com.liferay.commerce.product.model.CommerceChannelFilter updateCommerceChannelFilter(
		com.liferay.commerce.product.model.CommerceChannelFilter commerceChannelFilter) {
		return getService().updateCommerceChannelFilter(commerceChannelFilter);
	}

	public static CommerceChannelFilterLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceChannelFilterLocalService, CommerceChannelFilterLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceChannelFilterLocalService.class);

		ServiceTracker<CommerceChannelFilterLocalService, CommerceChannelFilterLocalService> serviceTracker =
			new ServiceTracker<CommerceChannelFilterLocalService, CommerceChannelFilterLocalService>(bundle.getBundleContext(),
				CommerceChannelFilterLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}