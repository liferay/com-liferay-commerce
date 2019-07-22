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

package com.liferay.commerce.discount.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceDiscountCommerceAccountGroupRelLocalService}.
 *
 * @author Marco Leo
 * @see CommerceDiscountCommerceAccountGroupRelLocalService
 * @generated
 */
@ProviderType
public class CommerceDiscountCommerceAccountGroupRelLocalServiceWrapper
	implements CommerceDiscountCommerceAccountGroupRelLocalService,
			   ServiceWrapper
				   <CommerceDiscountCommerceAccountGroupRelLocalService> {

	public CommerceDiscountCommerceAccountGroupRelLocalServiceWrapper(
		CommerceDiscountCommerceAccountGroupRelLocalService
			commerceDiscountCommerceAccountGroupRelLocalService) {

		_commerceDiscountCommerceAccountGroupRelLocalService =
			commerceDiscountCommerceAccountGroupRelLocalService;
	}

	/**
	 * Adds the commerce discount commerce account group rel to the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceDiscountCommerceAccountGroupRel the commerce discount commerce account group rel
	 * @return the commerce discount commerce account group rel that was added
	 */
	@Override
	public
		com.liferay.commerce.discount.model.
			CommerceDiscountCommerceAccountGroupRel
				addCommerceDiscountCommerceAccountGroupRel(
					com.liferay.commerce.discount.model.
						CommerceDiscountCommerceAccountGroupRel
							commerceDiscountCommerceAccountGroupRel) {

		return _commerceDiscountCommerceAccountGroupRelLocalService.
			addCommerceDiscountCommerceAccountGroupRel(
				commerceDiscountCommerceAccountGroupRel);
	}

	@Override
	public
		com.liferay.commerce.discount.model.
			CommerceDiscountCommerceAccountGroupRel
					addCommerceDiscountCommerceAccountGroupRel(
						long commerceDiscountId, long commerceAccountGroupId,
						com.liferay.portal.kernel.service.ServiceContext
							serviceContext)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDiscountCommerceAccountGroupRelLocalService.
			addCommerceDiscountCommerceAccountGroupRel(
				commerceDiscountId, commerceAccountGroupId, serviceContext);
	}

	/**
	 * Creates a new commerce discount commerce account group rel with the primary key. Does not add the commerce discount commerce account group rel to the database.
	 *
	 * @param commerceDiscountCommerceAccountGroupRelId the primary key for the new commerce discount commerce account group rel
	 * @return the new commerce discount commerce account group rel
	 */
	@Override
	public
		com.liferay.commerce.discount.model.
			CommerceDiscountCommerceAccountGroupRel
				createCommerceDiscountCommerceAccountGroupRel(
					long commerceDiscountCommerceAccountGroupRelId) {

		return _commerceDiscountCommerceAccountGroupRelLocalService.
			createCommerceDiscountCommerceAccountGroupRel(
				commerceDiscountCommerceAccountGroupRelId);
	}

	/**
	 * Deletes the commerce discount commerce account group rel from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceDiscountCommerceAccountGroupRel the commerce discount commerce account group rel
	 * @return the commerce discount commerce account group rel that was removed
	 */
	@Override
	public
		com.liferay.commerce.discount.model.
			CommerceDiscountCommerceAccountGroupRel
				deleteCommerceDiscountCommerceAccountGroupRel(
					com.liferay.commerce.discount.model.
						CommerceDiscountCommerceAccountGroupRel
							commerceDiscountCommerceAccountGroupRel) {

		return _commerceDiscountCommerceAccountGroupRelLocalService.
			deleteCommerceDiscountCommerceAccountGroupRel(
				commerceDiscountCommerceAccountGroupRel);
	}

	/**
	 * Deletes the commerce discount commerce account group rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceDiscountCommerceAccountGroupRelId the primary key of the commerce discount commerce account group rel
	 * @return the commerce discount commerce account group rel that was removed
	 * @throws PortalException if a commerce discount commerce account group rel with the primary key could not be found
	 */
	@Override
	public
		com.liferay.commerce.discount.model.
			CommerceDiscountCommerceAccountGroupRel
					deleteCommerceDiscountCommerceAccountGroupRel(
						long commerceDiscountCommerceAccountGroupRelId)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDiscountCommerceAccountGroupRelLocalService.
			deleteCommerceDiscountCommerceAccountGroupRel(
				commerceDiscountCommerceAccountGroupRelId);
	}

	@Override
	public void
		deleteCommerceDiscountCommerceAccountGroupRelsBycommerceAccountGroupId(
			long commerceAccountGroupId) {

		_commerceDiscountCommerceAccountGroupRelLocalService.
			deleteCommerceDiscountCommerceAccountGroupRelsBycommerceAccountGroupId(
				commerceAccountGroupId);
	}

	@Override
	public void
		deleteCommerceDiscountCommerceAccountGroupRelsByCommerceDiscountId(
			long commerceDiscountId) {

		_commerceDiscountCommerceAccountGroupRelLocalService.
			deleteCommerceDiscountCommerceAccountGroupRelsByCommerceDiscountId(
				commerceDiscountId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDiscountCommerceAccountGroupRelLocalService.
			deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _commerceDiscountCommerceAccountGroupRelLocalService.
			dynamicQuery();
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

		return _commerceDiscountCommerceAccountGroupRelLocalService.
			dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.discount.model.impl.CommerceDiscountCommerceAccountGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _commerceDiscountCommerceAccountGroupRelLocalService.
			dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.discount.model.impl.CommerceDiscountCommerceAccountGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _commerceDiscountCommerceAccountGroupRelLocalService.
			dynamicQuery(dynamicQuery, start, end, orderByComparator);
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

		return _commerceDiscountCommerceAccountGroupRelLocalService.
			dynamicQueryCount(dynamicQuery);
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

		return _commerceDiscountCommerceAccountGroupRelLocalService.
			dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public
		com.liferay.commerce.discount.model.
			CommerceDiscountCommerceAccountGroupRel
				fetchCommerceDiscountCommerceAccountGroupRel(
					long commerceDiscountCommerceAccountGroupRelId) {

		return _commerceDiscountCommerceAccountGroupRelLocalService.
			fetchCommerceDiscountCommerceAccountGroupRel(
				commerceDiscountCommerceAccountGroupRelId);
	}

	@Override
	public
		com.liferay.commerce.discount.model.
			CommerceDiscountCommerceAccountGroupRel
				fetchCommerceDiscountCommerceAccountGroupRel(
					long commerceDiscountId, long commerceAccountGroupId) {

		return _commerceDiscountCommerceAccountGroupRelLocalService.
			fetchCommerceDiscountCommerceAccountGroupRel(
				commerceDiscountId, commerceAccountGroupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _commerceDiscountCommerceAccountGroupRelLocalService.
			getActionableDynamicQuery();
	}

	/**
	 * Returns the commerce discount commerce account group rel with the primary key.
	 *
	 * @param commerceDiscountCommerceAccountGroupRelId the primary key of the commerce discount commerce account group rel
	 * @return the commerce discount commerce account group rel
	 * @throws PortalException if a commerce discount commerce account group rel with the primary key could not be found
	 */
	@Override
	public
		com.liferay.commerce.discount.model.
			CommerceDiscountCommerceAccountGroupRel
					getCommerceDiscountCommerceAccountGroupRel(
						long commerceDiscountCommerceAccountGroupRelId)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDiscountCommerceAccountGroupRelLocalService.
			getCommerceDiscountCommerceAccountGroupRel(
				commerceDiscountCommerceAccountGroupRelId);
	}

	/**
	 * Returns a range of all the commerce discount commerce account group rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.discount.model.impl.CommerceDiscountCommerceAccountGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce discount commerce account group rels
	 * @param end the upper bound of the range of commerce discount commerce account group rels (not inclusive)
	 * @return the range of commerce discount commerce account group rels
	 */
	@Override
	public java.util.List
		<com.liferay.commerce.discount.model.
			CommerceDiscountCommerceAccountGroupRel>
				getCommerceDiscountCommerceAccountGroupRels(
					int start, int end) {

		return _commerceDiscountCommerceAccountGroupRelLocalService.
			getCommerceDiscountCommerceAccountGroupRels(start, end);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.discount.model.
			CommerceDiscountCommerceAccountGroupRel>
				getCommerceDiscountCommerceAccountGroupRels(
					long commerceDiscountId, int start, int end,
					com.liferay.portal.kernel.util.OrderByComparator
						<com.liferay.commerce.discount.model.
							CommerceDiscountCommerceAccountGroupRel>
								orderByComparator) {

		return _commerceDiscountCommerceAccountGroupRelLocalService.
			getCommerceDiscountCommerceAccountGroupRels(
				commerceDiscountId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of commerce discount commerce account group rels.
	 *
	 * @return the number of commerce discount commerce account group rels
	 */
	@Override
	public int getCommerceDiscountCommerceAccountGroupRelsCount() {
		return _commerceDiscountCommerceAccountGroupRelLocalService.
			getCommerceDiscountCommerceAccountGroupRelsCount();
	}

	@Override
	public int getCommerceDiscountCommerceAccountGroupRelsCount(
		long commerceDiscountId) {

		return _commerceDiscountCommerceAccountGroupRelLocalService.
			getCommerceDiscountCommerceAccountGroupRelsCount(
				commerceDiscountId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _commerceDiscountCommerceAccountGroupRelLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceDiscountCommerceAccountGroupRelLocalService.
			getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDiscountCommerceAccountGroupRelLocalService.
			getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the commerce discount commerce account group rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param commerceDiscountCommerceAccountGroupRel the commerce discount commerce account group rel
	 * @return the commerce discount commerce account group rel that was updated
	 */
	@Override
	public
		com.liferay.commerce.discount.model.
			CommerceDiscountCommerceAccountGroupRel
				updateCommerceDiscountCommerceAccountGroupRel(
					com.liferay.commerce.discount.model.
						CommerceDiscountCommerceAccountGroupRel
							commerceDiscountCommerceAccountGroupRel) {

		return _commerceDiscountCommerceAccountGroupRelLocalService.
			updateCommerceDiscountCommerceAccountGroupRel(
				commerceDiscountCommerceAccountGroupRel);
	}

	@Override
	public CommerceDiscountCommerceAccountGroupRelLocalService
		getWrappedService() {

		return _commerceDiscountCommerceAccountGroupRelLocalService;
	}

	@Override
	public void setWrappedService(
		CommerceDiscountCommerceAccountGroupRelLocalService
			commerceDiscountCommerceAccountGroupRelLocalService) {

		_commerceDiscountCommerceAccountGroupRelLocalService =
			commerceDiscountCommerceAccountGroupRelLocalService;
	}

	private CommerceDiscountCommerceAccountGroupRelLocalService
		_commerceDiscountCommerceAccountGroupRelLocalService;

}