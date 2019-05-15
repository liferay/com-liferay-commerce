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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceWarehouseItemLocalService}.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceWarehouseItemLocalService
 * @generated
 */
@ProviderType
public class CommerceWarehouseItemLocalServiceWrapper
	implements CommerceWarehouseItemLocalService,
			   ServiceWrapper<CommerceWarehouseItemLocalService> {

	public CommerceWarehouseItemLocalServiceWrapper(
		CommerceWarehouseItemLocalService commerceWarehouseItemLocalService) {

		_commerceWarehouseItemLocalService = commerceWarehouseItemLocalService;
	}

	/**
	 * Adds the commerce warehouse item to the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceWarehouseItem the commerce warehouse item
	 * @return the commerce warehouse item that was added
	 */
	@Override
	public com.liferay.commerce.model.CommerceWarehouseItem
		addCommerceWarehouseItem(
			com.liferay.commerce.model.CommerceWarehouseItem
				commerceWarehouseItem) {

		return _commerceWarehouseItemLocalService.addCommerceWarehouseItem(
			commerceWarehouseItem);
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	@Override
	public com.liferay.commerce.model.CommerceWarehouseItem
			addCommerceWarehouseItem(
				long commerceWarehouseId, long cpInstanceId, int quantity,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceWarehouseItemLocalService.addCommerceWarehouseItem(
			commerceWarehouseId, cpInstanceId, quantity, serviceContext);
	}

	@Override
	public com.liferay.commerce.model.CommerceWarehouseItem
			addCommerceWarehouseItem(
				long commerceWarehouseId, long cProductId,
				String cpInstanceUuid, int quantity,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceWarehouseItemLocalService.addCommerceWarehouseItem(
			commerceWarehouseId, cProductId, cpInstanceUuid, quantity,
			serviceContext);
	}

	/**
	 * Creates a new commerce warehouse item with the primary key. Does not add the commerce warehouse item to the database.
	 *
	 * @param commerceWarehouseItemId the primary key for the new commerce warehouse item
	 * @return the new commerce warehouse item
	 */
	@Override
	public com.liferay.commerce.model.CommerceWarehouseItem
		createCommerceWarehouseItem(long commerceWarehouseItemId) {

		return _commerceWarehouseItemLocalService.createCommerceWarehouseItem(
			commerceWarehouseItemId);
	}

	/**
	 * Deletes the commerce warehouse item from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceWarehouseItem the commerce warehouse item
	 * @return the commerce warehouse item that was removed
	 */
	@Override
	public com.liferay.commerce.model.CommerceWarehouseItem
		deleteCommerceWarehouseItem(
			com.liferay.commerce.model.CommerceWarehouseItem
				commerceWarehouseItem) {

		return _commerceWarehouseItemLocalService.deleteCommerceWarehouseItem(
			commerceWarehouseItem);
	}

	/**
	 * Deletes the commerce warehouse item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceWarehouseItemId the primary key of the commerce warehouse item
	 * @return the commerce warehouse item that was removed
	 * @throws PortalException if a commerce warehouse item with the primary key could not be found
	 */
	@Override
	public com.liferay.commerce.model.CommerceWarehouseItem
			deleteCommerceWarehouseItem(long commerceWarehouseItemId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceWarehouseItemLocalService.deleteCommerceWarehouseItem(
			commerceWarehouseItemId);
	}

	@Override
	public void deleteCommerceWarehouseItems(long commerceWarehouseId) {
		_commerceWarehouseItemLocalService.deleteCommerceWarehouseItems(
			commerceWarehouseId);
	}

	@Override
	public void deleteCommerceWarehouseItemsByCPI_CPIU(
		long cProductId, String cpInstanceUuid) {

		_commerceWarehouseItemLocalService.
			deleteCommerceWarehouseItemsByCPI_CPIU(cProductId, cpInstanceUuid);
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	@Override
	public void deleteCommerceWarehouseItemsByCPInstanceId(long cpInstanceId) {
		_commerceWarehouseItemLocalService.
			deleteCommerceWarehouseItemsByCPInstanceId(cpInstanceId);
	}

	@Override
	public void deleteCommerceWarehouseItemsByCWI_CPIU(
		long commerceWarehouseId, String cpInstanceUuid) {

		_commerceWarehouseItemLocalService.
			deleteCommerceWarehouseItemsByCWI_CPIU(
				commerceWarehouseId, cpInstanceUuid);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceWarehouseItemLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _commerceWarehouseItemLocalService.dynamicQuery();
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

		return _commerceWarehouseItemLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.model.impl.CommerceWarehouseItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _commerceWarehouseItemLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.model.impl.CommerceWarehouseItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _commerceWarehouseItemLocalService.dynamicQuery(
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

		return _commerceWarehouseItemLocalService.dynamicQueryCount(
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

		return _commerceWarehouseItemLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.commerce.model.CommerceWarehouseItem
		fetchCommerceWarehouseItem(long commerceWarehouseItemId) {

		return _commerceWarehouseItemLocalService.fetchCommerceWarehouseItem(
			commerceWarehouseItemId);
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	@Override
	public com.liferay.commerce.model.CommerceWarehouseItem
		fetchCommerceWarehouseItem(
			long commerceWarehouseId, long cpInstanceId) {

		return _commerceWarehouseItemLocalService.fetchCommerceWarehouseItem(
			commerceWarehouseId, cpInstanceId);
	}

	@Override
	public com.liferay.commerce.model.CommerceWarehouseItem
		fetchCommerceWarehouseItemByCWI_CPIU(
			long commerceWarehouseId, String cpInstanceUuid) {

		return _commerceWarehouseItemLocalService.
			fetchCommerceWarehouseItemByCWI_CPIU(
				commerceWarehouseId, cpInstanceUuid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _commerceWarehouseItemLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the commerce warehouse item with the primary key.
	 *
	 * @param commerceWarehouseItemId the primary key of the commerce warehouse item
	 * @return the commerce warehouse item
	 * @throws PortalException if a commerce warehouse item with the primary key could not be found
	 */
	@Override
	public com.liferay.commerce.model.CommerceWarehouseItem
			getCommerceWarehouseItem(long commerceWarehouseItemId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceWarehouseItemLocalService.getCommerceWarehouseItem(
			commerceWarehouseItemId);
	}

	/**
	 * Returns a range of all the commerce warehouse items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.model.impl.CommerceWarehouseItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce warehouse items
	 * @param end the upper bound of the range of commerce warehouse items (not inclusive)
	 * @return the range of commerce warehouse items
	 */
	@Override
	public java.util.List<com.liferay.commerce.model.CommerceWarehouseItem>
		getCommerceWarehouseItems(int start, int end) {

		return _commerceWarehouseItemLocalService.getCommerceWarehouseItems(
			start, end);
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	@Override
	public java.util.List<com.liferay.commerce.model.CommerceWarehouseItem>
		getCommerceWarehouseItems(long cpInstanceId) {

		return _commerceWarehouseItemLocalService.getCommerceWarehouseItems(
			cpInstanceId);
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	@Override
	public java.util.List<com.liferay.commerce.model.CommerceWarehouseItem>
		getCommerceWarehouseItems(
			long cpInstanceId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.commerce.model.CommerceWarehouseItem>
					orderByComparator) {

		return _commerceWarehouseItemLocalService.getCommerceWarehouseItems(
			cpInstanceId, start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceWarehouseItem>
		getCommerceWarehouseItemsByCommerceWarehouseId(
			long commerceWarehouseId) {

		return _commerceWarehouseItemLocalService.
			getCommerceWarehouseItemsByCommerceWarehouseId(commerceWarehouseId);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceWarehouseItem>
		getCommerceWarehouseItemsByCPI_CPIU(
			long cProductId, String cpInstanceUuid) {

		return _commerceWarehouseItemLocalService.
			getCommerceWarehouseItemsByCPI_CPIU(cProductId, cpInstanceUuid);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceWarehouseItem>
		getCommerceWarehouseItemsByCPI_CPIU(
			long cProductId, String cpInstanceUuid, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.commerce.model.CommerceWarehouseItem>
					orderByComparator) {

		return _commerceWarehouseItemLocalService.
			getCommerceWarehouseItemsByCPI_CPIU(
				cProductId, cpInstanceUuid, start, end, orderByComparator);
	}

	/**
	 * Returns the number of commerce warehouse items.
	 *
	 * @return the number of commerce warehouse items
	 */
	@Override
	public int getCommerceWarehouseItemsCount() {
		return _commerceWarehouseItemLocalService.
			getCommerceWarehouseItemsCount();
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	@Override
	public int getCommerceWarehouseItemsCount(long cpInstanceId) {
		return _commerceWarehouseItemLocalService.
			getCommerceWarehouseItemsCount(cpInstanceId);
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	@Override
	public int getCommerceWarehouseItemsCount(
		long cProductId, String cpInstanceUuid) {

		return _commerceWarehouseItemLocalService.
			getCommerceWarehouseItemsCount(cProductId, cpInstanceUuid);
	}

	@Override
	public int getCPInstanceQuantity(long cpInstanceId) {
		return _commerceWarehouseItemLocalService.getCPInstanceQuantity(
			cpInstanceId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _commerceWarehouseItemLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceWarehouseItemLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceWarehouseItemLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Updates the commerce warehouse item in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param commerceWarehouseItem the commerce warehouse item
	 * @return the commerce warehouse item that was updated
	 */
	@Override
	public com.liferay.commerce.model.CommerceWarehouseItem
		updateCommerceWarehouseItem(
			com.liferay.commerce.model.CommerceWarehouseItem
				commerceWarehouseItem) {

		return _commerceWarehouseItemLocalService.updateCommerceWarehouseItem(
			commerceWarehouseItem);
	}

	@Override
	public com.liferay.commerce.model.CommerceWarehouseItem
			updateCommerceWarehouseItem(
				long commerceWarehouseItemId, int quantity,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceWarehouseItemLocalService.updateCommerceWarehouseItem(
			commerceWarehouseItemId, quantity, serviceContext);
	}

	@Override
	public CommerceWarehouseItemLocalService getWrappedService() {
		return _commerceWarehouseItemLocalService;
	}

	@Override
	public void setWrappedService(
		CommerceWarehouseItemLocalService commerceWarehouseItemLocalService) {

		_commerceWarehouseItemLocalService = commerceWarehouseItemLocalService;
	}

	private CommerceWarehouseItemLocalService
		_commerceWarehouseItemLocalService;

}