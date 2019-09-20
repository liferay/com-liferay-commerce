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

package com.liferay.commerce.wish.list.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceWishListItemLocalService}.
 *
 * @author Andrea Di Giorgi
 * @see CommerceWishListItemLocalService
 * @generated
 */
public class CommerceWishListItemLocalServiceWrapper
	implements CommerceWishListItemLocalService,
			   ServiceWrapper<CommerceWishListItemLocalService> {

	public CommerceWishListItemLocalServiceWrapper(
		CommerceWishListItemLocalService commerceWishListItemLocalService) {

		_commerceWishListItemLocalService = commerceWishListItemLocalService;
	}

	/**
	 * Adds the commerce wish list item to the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceWishListItem the commerce wish list item
	 * @return the commerce wish list item that was added
	 */
	@Override
	public com.liferay.commerce.wish.list.model.CommerceWishListItem
		addCommerceWishListItem(
			com.liferay.commerce.wish.list.model.CommerceWishListItem
				commerceWishListItem) {

		return _commerceWishListItemLocalService.addCommerceWishListItem(
			commerceWishListItem);
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	@Override
	public com.liferay.commerce.wish.list.model.CommerceWishListItem
			addCommerceWishListItem(
				long commerceWishListId, long cpDefinitionId, long cpInstanceId,
				String json,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceWishListItemLocalService.addCommerceWishListItem(
			commerceWishListId, cpDefinitionId, cpInstanceId, json,
			serviceContext);
	}

	@Override
	public com.liferay.commerce.wish.list.model.CommerceWishListItem
			addCommerceWishListItem(
				long commerceWishListId, long cProductId, String cpInstanceUuid,
				String json,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceWishListItemLocalService.addCommerceWishListItem(
			commerceWishListId, cProductId, cpInstanceUuid, json,
			serviceContext);
	}

	/**
	 * Creates a new commerce wish list item with the primary key. Does not add the commerce wish list item to the database.
	 *
	 * @param commerceWishListItemId the primary key for the new commerce wish list item
	 * @return the new commerce wish list item
	 */
	@Override
	public com.liferay.commerce.wish.list.model.CommerceWishListItem
		createCommerceWishListItem(long commerceWishListItemId) {

		return _commerceWishListItemLocalService.createCommerceWishListItem(
			commerceWishListItemId);
	}

	/**
	 * Deletes the commerce wish list item from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceWishListItem the commerce wish list item
	 * @return the commerce wish list item that was removed
	 */
	@Override
	public com.liferay.commerce.wish.list.model.CommerceWishListItem
		deleteCommerceWishListItem(
			com.liferay.commerce.wish.list.model.CommerceWishListItem
				commerceWishListItem) {

		return _commerceWishListItemLocalService.deleteCommerceWishListItem(
			commerceWishListItem);
	}

	/**
	 * Deletes the commerce wish list item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceWishListItemId the primary key of the commerce wish list item
	 * @return the commerce wish list item that was removed
	 * @throws PortalException if a commerce wish list item with the primary key could not be found
	 */
	@Override
	public com.liferay.commerce.wish.list.model.CommerceWishListItem
			deleteCommerceWishListItem(long commerceWishListItemId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceWishListItemLocalService.deleteCommerceWishListItem(
			commerceWishListItemId);
	}

	@Override
	public void deleteCommerceWishListItems(long commerceWishListId) {
		_commerceWishListItemLocalService.deleteCommerceWishListItems(
			commerceWishListId);
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	@Override
	public void deleteCommerceWishListItemsByCPDefinitionId(
		long cpDefinitionId) {

		_commerceWishListItemLocalService.
			deleteCommerceWishListItemsByCPDefinitionId(cpDefinitionId);
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	@Override
	public void deleteCommerceWishListItemsByCPInstanceId(long cpInstanceId) {
		_commerceWishListItemLocalService.
			deleteCommerceWishListItemsByCPInstanceId(cpInstanceId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceWishListItemLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _commerceWishListItemLocalService.dynamicQuery();
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

		return _commerceWishListItemLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.wish.list.model.impl.CommerceWishListItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _commerceWishListItemLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.wish.list.model.impl.CommerceWishListItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _commerceWishListItemLocalService.dynamicQuery(
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

		return _commerceWishListItemLocalService.dynamicQueryCount(
			dynamicQuery);
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

		return _commerceWishListItemLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.commerce.wish.list.model.CommerceWishListItem
		fetchCommerceWishListItem(long commerceWishListItemId) {

		return _commerceWishListItemLocalService.fetchCommerceWishListItem(
			commerceWishListItemId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _commerceWishListItemLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the commerce wish list item with the primary key.
	 *
	 * @param commerceWishListItemId the primary key of the commerce wish list item
	 * @return the commerce wish list item
	 * @throws PortalException if a commerce wish list item with the primary key could not be found
	 */
	@Override
	public com.liferay.commerce.wish.list.model.CommerceWishListItem
			getCommerceWishListItem(long commerceWishListItemId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceWishListItemLocalService.getCommerceWishListItem(
			commerceWishListItemId);
	}

	@Override
	public com.liferay.commerce.wish.list.model.CommerceWishListItem
			getCommerceWishListItem(
				long commerceWishListId, String cpInstanceUuid, long cProductId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceWishListItemLocalService.getCommerceWishListItem(
			commerceWishListId, cpInstanceUuid, cProductId);
	}

	@Override
	public int getCommerceWishListItemByContainsCPInstanceCount(
		long commerceWishListId, String cpInstanceUuid) {

		return _commerceWishListItemLocalService.
			getCommerceWishListItemByContainsCPInstanceCount(
				commerceWishListId, cpInstanceUuid);
	}

	@Override
	public int getCommerceWishListItemByContainsCProductCount(
		long commerceWishListId, long cProductId) {

		return _commerceWishListItemLocalService.
			getCommerceWishListItemByContainsCProductCount(
				commerceWishListId, cProductId);
	}

	/**
	 * Returns a range of all the commerce wish list items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.wish.list.model.impl.CommerceWishListItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce wish list items
	 * @param end the upper bound of the range of commerce wish list items (not inclusive)
	 * @return the range of commerce wish list items
	 */
	@Override
	public java.util.List
		<com.liferay.commerce.wish.list.model.CommerceWishListItem>
			getCommerceWishListItems(int start, int end) {

		return _commerceWishListItemLocalService.getCommerceWishListItems(
			start, end);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.wish.list.model.CommerceWishListItem>
			getCommerceWishListItems(
				long commerceWishListId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.wish.list.model.CommerceWishListItem>
						orderByComparator) {

		return _commerceWishListItemLocalService.getCommerceWishListItems(
			commerceWishListId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of commerce wish list items.
	 *
	 * @return the number of commerce wish list items
	 */
	@Override
	public int getCommerceWishListItemsCount() {
		return _commerceWishListItemLocalService.
			getCommerceWishListItemsCount();
	}

	@Override
	public int getCommerceWishListItemsCount(long commerceWishListId) {
		return _commerceWishListItemLocalService.getCommerceWishListItemsCount(
			commerceWishListId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _commerceWishListItemLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceWishListItemLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceWishListItemLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Updates the commerce wish list item in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param commerceWishListItem the commerce wish list item
	 * @return the commerce wish list item that was updated
	 */
	@Override
	public com.liferay.commerce.wish.list.model.CommerceWishListItem
		updateCommerceWishListItem(
			com.liferay.commerce.wish.list.model.CommerceWishListItem
				commerceWishListItem) {

		return _commerceWishListItemLocalService.updateCommerceWishListItem(
			commerceWishListItem);
	}

	@Override
	public CommerceWishListItemLocalService getWrappedService() {
		return _commerceWishListItemLocalService;
	}

	@Override
	public void setWrappedService(
		CommerceWishListItemLocalService commerceWishListItemLocalService) {

		_commerceWishListItemLocalService = commerceWishListItemLocalService;
	}

	private CommerceWishListItemLocalService _commerceWishListItemLocalService;

}