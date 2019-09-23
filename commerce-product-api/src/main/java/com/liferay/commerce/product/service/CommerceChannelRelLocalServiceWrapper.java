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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceChannelRelLocalService}.
 *
 * @author Marco Leo
 * @see CommerceChannelRelLocalService
 * @generated
 */
public class CommerceChannelRelLocalServiceWrapper
	implements CommerceChannelRelLocalService,
			   ServiceWrapper<CommerceChannelRelLocalService> {

	public CommerceChannelRelLocalServiceWrapper(
		CommerceChannelRelLocalService commerceChannelRelLocalService) {

		_commerceChannelRelLocalService = commerceChannelRelLocalService;
	}

	/**
	 * Adds the commerce channel rel to the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceChannelRel the commerce channel rel
	 * @return the commerce channel rel that was added
	 */
	@Override
	public com.liferay.commerce.product.model.CommerceChannelRel
		addCommerceChannelRel(
			com.liferay.commerce.product.model.CommerceChannelRel
				commerceChannelRel) {

		return _commerceChannelRelLocalService.addCommerceChannelRel(
			commerceChannelRel);
	}

	@Override
	public com.liferay.commerce.product.model.CommerceChannelRel
			addCommerceChannelRel(
				String className, long classPK, long commerceChannelId,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceChannelRelLocalService.addCommerceChannelRel(
			className, classPK, commerceChannelId, serviceContext);
	}

	/**
	 * Creates a new commerce channel rel with the primary key. Does not add the commerce channel rel to the database.
	 *
	 * @param commerceChannelRelId the primary key for the new commerce channel rel
	 * @return the new commerce channel rel
	 */
	@Override
	public com.liferay.commerce.product.model.CommerceChannelRel
		createCommerceChannelRel(long commerceChannelRelId) {

		return _commerceChannelRelLocalService.createCommerceChannelRel(
			commerceChannelRelId);
	}

	/**
	 * Deletes the commerce channel rel from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceChannelRel the commerce channel rel
	 * @return the commerce channel rel that was removed
	 */
	@Override
	public com.liferay.commerce.product.model.CommerceChannelRel
		deleteCommerceChannelRel(
			com.liferay.commerce.product.model.CommerceChannelRel
				commerceChannelRel) {

		return _commerceChannelRelLocalService.deleteCommerceChannelRel(
			commerceChannelRel);
	}

	/**
	 * Deletes the commerce channel rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceChannelRelId the primary key of the commerce channel rel
	 * @return the commerce channel rel that was removed
	 * @throws PortalException if a commerce channel rel with the primary key could not be found
	 */
	@Override
	public com.liferay.commerce.product.model.CommerceChannelRel
			deleteCommerceChannelRel(long commerceChannelRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceChannelRelLocalService.deleteCommerceChannelRel(
			commerceChannelRelId);
	}

	@Override
	public void deleteCommerceChannelRels(long commerceChannelId) {
		_commerceChannelRelLocalService.deleteCommerceChannelRels(
			commerceChannelId);
	}

	@Override
	public void deleteCommerceChannelRels(String className, long classPK) {
		_commerceChannelRelLocalService.deleteCommerceChannelRels(
			className, classPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceChannelRelLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _commerceChannelRelLocalService.dynamicQuery();
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

		return _commerceChannelRelLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.model.impl.CommerceChannelRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _commerceChannelRelLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.model.impl.CommerceChannelRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _commerceChannelRelLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
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

		return _commerceChannelRelLocalService.dynamicQueryCount(dynamicQuery);
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

		return _commerceChannelRelLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.commerce.product.model.CommerceChannelRel
		fetchCommerceChannelRel(long commerceChannelRelId) {

		return _commerceChannelRelLocalService.fetchCommerceChannelRel(
			commerceChannelRelId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _commerceChannelRelLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the commerce channel rel with the primary key.
	 *
	 * @param commerceChannelRelId the primary key of the commerce channel rel
	 * @return the commerce channel rel
	 * @throws PortalException if a commerce channel rel with the primary key could not be found
	 */
	@Override
	public com.liferay.commerce.product.model.CommerceChannelRel
			getCommerceChannelRel(long commerceChannelRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceChannelRelLocalService.getCommerceChannelRel(
			commerceChannelRelId);
	}

	/**
	 * Returns a range of all the commerce channel rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.model.impl.CommerceChannelRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce channel rels
	 * @param end the upper bound of the range of commerce channel rels (not inclusive)
	 * @return the range of commerce channel rels
	 */
	@Override
	public java.util.List<com.liferay.commerce.product.model.CommerceChannelRel>
		getCommerceChannelRels(int start, int end) {

		return _commerceChannelRelLocalService.getCommerceChannelRels(
			start, end);
	}

	@Override
	public java.util.List<com.liferay.commerce.product.model.CommerceChannelRel>
		getCommerceChannelRels(
			long commerceChannelId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.commerce.product.model.CommerceChannelRel>
					orderByComparator) {

		return _commerceChannelRelLocalService.getCommerceChannelRels(
			commerceChannelId, start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.commerce.product.model.CommerceChannelRel>
		getCommerceChannelRels(
			String className, long classPK, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.commerce.product.model.CommerceChannelRel>
					orderByComparator) {

		return _commerceChannelRelLocalService.getCommerceChannelRels(
			className, classPK, start, end, orderByComparator);
	}

	/**
	 * Returns the number of commerce channel rels.
	 *
	 * @return the number of commerce channel rels
	 */
	@Override
	public int getCommerceChannelRelsCount() {
		return _commerceChannelRelLocalService.getCommerceChannelRelsCount();
	}

	@Override
	public int getCommerceChannelRelsCount(long commerceChannelId) {
		return _commerceChannelRelLocalService.getCommerceChannelRelsCount(
			commerceChannelId);
	}

	@Override
	public int getCommerceChannelRelsCount(String className, long classPK) {
		return _commerceChannelRelLocalService.getCommerceChannelRelsCount(
			className, classPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _commerceChannelRelLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceChannelRelLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceChannelRelLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the commerce channel rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param commerceChannelRel the commerce channel rel
	 * @return the commerce channel rel that was updated
	 */
	@Override
	public com.liferay.commerce.product.model.CommerceChannelRel
		updateCommerceChannelRel(
			com.liferay.commerce.product.model.CommerceChannelRel
				commerceChannelRel) {

		return _commerceChannelRelLocalService.updateCommerceChannelRel(
			commerceChannelRel);
	}

	@Override
	public CommerceChannelRelLocalService getWrappedService() {
		return _commerceChannelRelLocalService;
	}

	@Override
	public void setWrappedService(
		CommerceChannelRelLocalService commerceChannelRelLocalService) {

		_commerceChannelRelLocalService = commerceChannelRelLocalService;
	}

	private CommerceChannelRelLocalService _commerceChannelRelLocalService;

}