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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceChannelFilterLocalService}.
 *
 * @author Marco Leo
 * @see CommerceChannelFilterLocalService
 * @generated
 */
@ProviderType
public class CommerceChannelFilterLocalServiceWrapper
	implements CommerceChannelFilterLocalService,
		ServiceWrapper<CommerceChannelFilterLocalService> {
	public CommerceChannelFilterLocalServiceWrapper(
		CommerceChannelFilterLocalService commerceChannelFilterLocalService) {
		_commerceChannelFilterLocalService = commerceChannelFilterLocalService;
	}

	/**
	* Adds the commerce channel filter to the database. Also notifies the appropriate model listeners.
	*
	* @param commerceChannelFilter the commerce channel filter
	* @return the commerce channel filter that was added
	*/
	@Override
	public com.liferay.commerce.product.model.CommerceChannelFilter addCommerceChannelFilter(
		com.liferay.commerce.product.model.CommerceChannelFilter commerceChannelFilter) {
		return _commerceChannelFilterLocalService.addCommerceChannelFilter(commerceChannelFilter);
	}

	/**
	* Creates a new commerce channel filter with the primary key. Does not add the commerce channel filter to the database.
	*
	* @param commerceChannelFilterId the primary key for the new commerce channel filter
	* @return the new commerce channel filter
	*/
	@Override
	public com.liferay.commerce.product.model.CommerceChannelFilter createCommerceChannelFilter(
		long commerceChannelFilterId) {
		return _commerceChannelFilterLocalService.createCommerceChannelFilter(commerceChannelFilterId);
	}

	/**
	* Deletes the commerce channel filter from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceChannelFilter the commerce channel filter
	* @return the commerce channel filter that was removed
	*/
	@Override
	public com.liferay.commerce.product.model.CommerceChannelFilter deleteCommerceChannelFilter(
		com.liferay.commerce.product.model.CommerceChannelFilter commerceChannelFilter) {
		return _commerceChannelFilterLocalService.deleteCommerceChannelFilter(commerceChannelFilter);
	}

	/**
	* Deletes the commerce channel filter with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceChannelFilterId the primary key of the commerce channel filter
	* @return the commerce channel filter that was removed
	* @throws PortalException if a commerce channel filter with the primary key could not be found
	*/
	@Override
	public com.liferay.commerce.product.model.CommerceChannelFilter deleteCommerceChannelFilter(
		long commerceChannelFilterId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceChannelFilterLocalService.deleteCommerceChannelFilter(commerceChannelFilterId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceChannelFilterLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _commerceChannelFilterLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _commerceChannelFilterLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _commerceChannelFilterLocalService.dynamicQuery(dynamicQuery,
			start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _commerceChannelFilterLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _commerceChannelFilterLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _commerceChannelFilterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.commerce.product.model.CommerceChannelFilter fetchCommerceChannelFilter(
		long commerceChannelFilterId) {
		return _commerceChannelFilterLocalService.fetchCommerceChannelFilter(commerceChannelFilterId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _commerceChannelFilterLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the commerce channel filter with the primary key.
	*
	* @param commerceChannelFilterId the primary key of the commerce channel filter
	* @return the commerce channel filter
	* @throws PortalException if a commerce channel filter with the primary key could not be found
	*/
	@Override
	public com.liferay.commerce.product.model.CommerceChannelFilter getCommerceChannelFilter(
		long commerceChannelFilterId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceChannelFilterLocalService.getCommerceChannelFilter(commerceChannelFilterId);
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
	@Override
	public java.util.List<com.liferay.commerce.product.model.CommerceChannelFilter> getCommerceChannelFilters(
		int start, int end) {
		return _commerceChannelFilterLocalService.getCommerceChannelFilters(start,
			end);
	}

	/**
	* Returns the number of commerce channel filters.
	*
	* @return the number of commerce channel filters
	*/
	@Override
	public int getCommerceChannelFiltersCount() {
		return _commerceChannelFilterLocalService.getCommerceChannelFiltersCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _commerceChannelFilterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceChannelFilterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceChannelFilterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the commerce channel filter in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param commerceChannelFilter the commerce channel filter
	* @return the commerce channel filter that was updated
	*/
	@Override
	public com.liferay.commerce.product.model.CommerceChannelFilter updateCommerceChannelFilter(
		com.liferay.commerce.product.model.CommerceChannelFilter commerceChannelFilter) {
		return _commerceChannelFilterLocalService.updateCommerceChannelFilter(commerceChannelFilter);
	}

	@Override
	public CommerceChannelFilterLocalService getWrappedService() {
		return _commerceChannelFilterLocalService;
	}

	@Override
	public void setWrappedService(
		CommerceChannelFilterLocalService commerceChannelFilterLocalService) {
		_commerceChannelFilterLocalService = commerceChannelFilterLocalService;
	}

	private CommerceChannelFilterLocalService _commerceChannelFilterLocalService;
}