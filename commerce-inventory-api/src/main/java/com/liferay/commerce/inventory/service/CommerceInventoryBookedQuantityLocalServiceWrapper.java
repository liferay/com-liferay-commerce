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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceInventoryBookedQuantityLocalService}.
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryBookedQuantityLocalService
 * @generated
 */
public class CommerceInventoryBookedQuantityLocalServiceWrapper
	implements CommerceInventoryBookedQuantityLocalService,
			   ServiceWrapper<CommerceInventoryBookedQuantityLocalService> {

	public CommerceInventoryBookedQuantityLocalServiceWrapper(
		CommerceInventoryBookedQuantityLocalService
			commerceInventoryBookedQuantityLocalService) {

		_commerceInventoryBookedQuantityLocalService =
			commerceInventoryBookedQuantityLocalService;
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceInventoryBookedQuantityLocalServiceUtil} to access the commerce inventory booked quantity local service. Add custom service methods to <code>com.liferay.commerce.inventory.service.impl.CommerceInventoryBookedQuantityLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryBookedQuantity
			addCommerceBookedQuantity(
				long userId, String sku, int quantity,
				java.util.Date expirationDate,
				java.util.Map<String, String> context)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryBookedQuantityLocalService.
			addCommerceBookedQuantity(
				userId, sku, quantity, expirationDate, context);
	}

	/**
	 * Adds the commerce inventory booked quantity to the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceInventoryBookedQuantity the commerce inventory booked quantity
	 * @return the commerce inventory booked quantity that was added
	 */
	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryBookedQuantity
		addCommerceInventoryBookedQuantity(
			com.liferay.commerce.inventory.model.CommerceInventoryBookedQuantity
				commerceInventoryBookedQuantity) {

		return _commerceInventoryBookedQuantityLocalService.
			addCommerceInventoryBookedQuantity(commerceInventoryBookedQuantity);
	}

	@Override
	public void checkCommerceInventoryBookedQuantities() {
		_commerceInventoryBookedQuantityLocalService.
			checkCommerceInventoryBookedQuantities();
	}

	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryBookedQuantity
			consumeCommerceBookedQuantity(
				long commerceBookedQuantityId, int quantity)
		throws com.liferay.commerce.inventory.exception.
			NoSuchInventoryBookedQuantityException {

		return _commerceInventoryBookedQuantityLocalService.
			consumeCommerceBookedQuantity(commerceBookedQuantityId, quantity);
	}

	/**
	 * Creates a new commerce inventory booked quantity with the primary key. Does not add the commerce inventory booked quantity to the database.
	 *
	 * @param commerceInventoryBookedQuantityId the primary key for the new commerce inventory booked quantity
	 * @return the new commerce inventory booked quantity
	 */
	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryBookedQuantity
		createCommerceInventoryBookedQuantity(
			long commerceInventoryBookedQuantityId) {

		return _commerceInventoryBookedQuantityLocalService.
			createCommerceInventoryBookedQuantity(
				commerceInventoryBookedQuantityId);
	}

	/**
	 * Deletes the commerce inventory booked quantity from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceInventoryBookedQuantity the commerce inventory booked quantity
	 * @return the commerce inventory booked quantity that was removed
	 */
	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryBookedQuantity
		deleteCommerceInventoryBookedQuantity(
			com.liferay.commerce.inventory.model.CommerceInventoryBookedQuantity
				commerceInventoryBookedQuantity) {

		return _commerceInventoryBookedQuantityLocalService.
			deleteCommerceInventoryBookedQuantity(
				commerceInventoryBookedQuantity);
	}

	/**
	 * Deletes the commerce inventory booked quantity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceInventoryBookedQuantityId the primary key of the commerce inventory booked quantity
	 * @return the commerce inventory booked quantity that was removed
	 * @throws PortalException if a commerce inventory booked quantity with the primary key could not be found
	 */
	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryBookedQuantity
			deleteCommerceInventoryBookedQuantity(
				long commerceInventoryBookedQuantityId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryBookedQuantityLocalService.
			deleteCommerceInventoryBookedQuantity(
				commerceInventoryBookedQuantityId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryBookedQuantityLocalService.
			deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _commerceInventoryBookedQuantityLocalService.dynamicQuery();
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

		return _commerceInventoryBookedQuantityLocalService.dynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.inventory.model.impl.CommerceInventoryBookedQuantityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _commerceInventoryBookedQuantityLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.inventory.model.impl.CommerceInventoryBookedQuantityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _commerceInventoryBookedQuantityLocalService.dynamicQuery(
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

		return _commerceInventoryBookedQuantityLocalService.dynamicQueryCount(
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

		return _commerceInventoryBookedQuantityLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryBookedQuantity
		fetchCommerceInventoryBookedQuantity(
			long commerceInventoryBookedQuantityId) {

		return _commerceInventoryBookedQuantityLocalService.
			fetchCommerceInventoryBookedQuantity(
				commerceInventoryBookedQuantityId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _commerceInventoryBookedQuantityLocalService.
			getActionableDynamicQuery();
	}

	@Override
	public int getCommerceBookedQuantity(String sku) {
		return _commerceInventoryBookedQuantityLocalService.
			getCommerceBookedQuantity(sku);
	}

	/**
	 * Returns a range of all the commerce inventory booked quantities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.inventory.model.impl.CommerceInventoryBookedQuantityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce inventory booked quantities
	 * @param end the upper bound of the range of commerce inventory booked quantities (not inclusive)
	 * @return the range of commerce inventory booked quantities
	 */
	@Override
	public java.util.List
		<com.liferay.commerce.inventory.model.CommerceInventoryBookedQuantity>
			getCommerceInventoryBookedQuantities(int start, int end) {

		return _commerceInventoryBookedQuantityLocalService.
			getCommerceInventoryBookedQuantities(start, end);
	}

	/**
	 * Returns the number of commerce inventory booked quantities.
	 *
	 * @return the number of commerce inventory booked quantities
	 */
	@Override
	public int getCommerceInventoryBookedQuantitiesCount() {
		return _commerceInventoryBookedQuantityLocalService.
			getCommerceInventoryBookedQuantitiesCount();
	}

	/**
	 * Returns the commerce inventory booked quantity with the primary key.
	 *
	 * @param commerceInventoryBookedQuantityId the primary key of the commerce inventory booked quantity
	 * @return the commerce inventory booked quantity
	 * @throws PortalException if a commerce inventory booked quantity with the primary key could not be found
	 */
	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryBookedQuantity
			getCommerceInventoryBookedQuantity(
				long commerceInventoryBookedQuantityId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryBookedQuantityLocalService.
			getCommerceInventoryBookedQuantity(
				commerceInventoryBookedQuantityId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _commerceInventoryBookedQuantityLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceInventoryBookedQuantityLocalService.
			getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryBookedQuantityLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Updates the commerce inventory booked quantity in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param commerceInventoryBookedQuantity the commerce inventory booked quantity
	 * @return the commerce inventory booked quantity that was updated
	 */
	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryBookedQuantity
		updateCommerceInventoryBookedQuantity(
			com.liferay.commerce.inventory.model.CommerceInventoryBookedQuantity
				commerceInventoryBookedQuantity) {

		return _commerceInventoryBookedQuantityLocalService.
			updateCommerceInventoryBookedQuantity(
				commerceInventoryBookedQuantity);
	}

	@Override
	public CommerceInventoryBookedQuantityLocalService getWrappedService() {
		return _commerceInventoryBookedQuantityLocalService;
	}

	@Override
	public void setWrappedService(
		CommerceInventoryBookedQuantityLocalService
			commerceInventoryBookedQuantityLocalService) {

		_commerceInventoryBookedQuantityLocalService =
			commerceInventoryBookedQuantityLocalService;
	}

	private CommerceInventoryBookedQuantityLocalService
		_commerceInventoryBookedQuantityLocalService;

}