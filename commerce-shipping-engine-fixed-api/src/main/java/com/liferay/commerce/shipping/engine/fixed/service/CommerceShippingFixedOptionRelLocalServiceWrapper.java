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

package com.liferay.commerce.shipping.engine.fixed.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceShippingFixedOptionRelLocalService}.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceShippingFixedOptionRelLocalService
 * @generated
 */
public class CommerceShippingFixedOptionRelLocalServiceWrapper
	implements CommerceShippingFixedOptionRelLocalService,
			   ServiceWrapper<CommerceShippingFixedOptionRelLocalService> {

	public CommerceShippingFixedOptionRelLocalServiceWrapper(
		CommerceShippingFixedOptionRelLocalService
			commerceShippingFixedOptionRelLocalService) {

		_commerceShippingFixedOptionRelLocalService =
			commerceShippingFixedOptionRelLocalService;
	}

	/**
	 * Adds the commerce shipping fixed option rel to the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceShippingFixedOptionRel the commerce shipping fixed option rel
	 * @return the commerce shipping fixed option rel that was added
	 */
	@Override
	public com.liferay.commerce.shipping.engine.fixed.model.
		CommerceShippingFixedOptionRel addCommerceShippingFixedOptionRel(
			com.liferay.commerce.shipping.engine.fixed.model.
				CommerceShippingFixedOptionRel commerceShippingFixedOptionRel) {

		return _commerceShippingFixedOptionRelLocalService.
			addCommerceShippingFixedOptionRel(commerceShippingFixedOptionRel);
	}

	@Override
	public com.liferay.commerce.shipping.engine.fixed.model.
		CommerceShippingFixedOptionRel addCommerceShippingFixedOptionRel(
				long commerceShippingMethodId,
				long commerceShippingFixedOptionId,
				long commerceInventoryWarehouseId, long commerceCountryId,
				long commerceRegionId, String zip, double weightFrom,
				double weightTo, java.math.BigDecimal fixedPrice,
				java.math.BigDecimal rateUnitWeightPrice, double ratePercentage,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceShippingFixedOptionRelLocalService.
			addCommerceShippingFixedOptionRel(
				commerceShippingMethodId, commerceShippingFixedOptionId,
				commerceInventoryWarehouseId, commerceCountryId,
				commerceRegionId, zip, weightFrom, weightTo, fixedPrice,
				rateUnitWeightPrice, ratePercentage, serviceContext);
	}

	/**
	 * Creates a new commerce shipping fixed option rel with the primary key. Does not add the commerce shipping fixed option rel to the database.
	 *
	 * @param commerceShippingFixedOptionRelId the primary key for the new commerce shipping fixed option rel
	 * @return the new commerce shipping fixed option rel
	 */
	@Override
	public com.liferay.commerce.shipping.engine.fixed.model.
		CommerceShippingFixedOptionRel createCommerceShippingFixedOptionRel(
			long commerceShippingFixedOptionRelId) {

		return _commerceShippingFixedOptionRelLocalService.
			createCommerceShippingFixedOptionRel(
				commerceShippingFixedOptionRelId);
	}

	/**
	 * Deletes the commerce shipping fixed option rel from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceShippingFixedOptionRel the commerce shipping fixed option rel
	 * @return the commerce shipping fixed option rel that was removed
	 */
	@Override
	public com.liferay.commerce.shipping.engine.fixed.model.
		CommerceShippingFixedOptionRel deleteCommerceShippingFixedOptionRel(
			com.liferay.commerce.shipping.engine.fixed.model.
				CommerceShippingFixedOptionRel commerceShippingFixedOptionRel) {

		return _commerceShippingFixedOptionRelLocalService.
			deleteCommerceShippingFixedOptionRel(
				commerceShippingFixedOptionRel);
	}

	/**
	 * Deletes the commerce shipping fixed option rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceShippingFixedOptionRelId the primary key of the commerce shipping fixed option rel
	 * @return the commerce shipping fixed option rel that was removed
	 * @throws PortalException if a commerce shipping fixed option rel with the primary key could not be found
	 */
	@Override
	public com.liferay.commerce.shipping.engine.fixed.model.
		CommerceShippingFixedOptionRel deleteCommerceShippingFixedOptionRel(
				long commerceShippingFixedOptionRelId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceShippingFixedOptionRelLocalService.
			deleteCommerceShippingFixedOptionRel(
				commerceShippingFixedOptionRelId);
	}

	@Override
	public void deleteCommerceShippingFixedOptionRels(
		long commerceShippingFixedOptionId) {

		_commerceShippingFixedOptionRelLocalService.
			deleteCommerceShippingFixedOptionRels(
				commerceShippingFixedOptionId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceShippingFixedOptionRelLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _commerceShippingFixedOptionRelLocalService.dynamicQuery();
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

		return _commerceShippingFixedOptionRelLocalService.dynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.shipping.engine.fixed.model.impl.CommerceShippingFixedOptionRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _commerceShippingFixedOptionRelLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.shipping.engine.fixed.model.impl.CommerceShippingFixedOptionRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _commerceShippingFixedOptionRelLocalService.dynamicQuery(
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

		return _commerceShippingFixedOptionRelLocalService.dynamicQueryCount(
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

		return _commerceShippingFixedOptionRelLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.commerce.shipping.engine.fixed.model.
		CommerceShippingFixedOptionRel fetchCommerceShippingFixedOptionRel(
			long commerceShippingFixedOptionRelId) {

		return _commerceShippingFixedOptionRelLocalService.
			fetchCommerceShippingFixedOptionRel(
				commerceShippingFixedOptionRelId);
	}

	@Override
	public com.liferay.commerce.shipping.engine.fixed.model.
		CommerceShippingFixedOptionRel fetchCommerceShippingFixedOptionRel(
			long commerceShippingFixedOptionId, long commerceCountryId,
			long commerceRegionId, String zip, double weight) {

		return _commerceShippingFixedOptionRelLocalService.
			fetchCommerceShippingFixedOptionRel(
				commerceShippingFixedOptionId, commerceCountryId,
				commerceRegionId, zip, weight);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _commerceShippingFixedOptionRelLocalService.
			getActionableDynamicQuery();
	}

	/**
	 * Returns the commerce shipping fixed option rel with the primary key.
	 *
	 * @param commerceShippingFixedOptionRelId the primary key of the commerce shipping fixed option rel
	 * @return the commerce shipping fixed option rel
	 * @throws PortalException if a commerce shipping fixed option rel with the primary key could not be found
	 */
	@Override
	public com.liferay.commerce.shipping.engine.fixed.model.
		CommerceShippingFixedOptionRel getCommerceShippingFixedOptionRel(
				long commerceShippingFixedOptionRelId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceShippingFixedOptionRelLocalService.
			getCommerceShippingFixedOptionRel(commerceShippingFixedOptionRelId);
	}

	/**
	 * Returns a range of all the commerce shipping fixed option rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.shipping.engine.fixed.model.impl.CommerceShippingFixedOptionRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce shipping fixed option rels
	 * @param end the upper bound of the range of commerce shipping fixed option rels (not inclusive)
	 * @return the range of commerce shipping fixed option rels
	 */
	@Override
	public java.util.List
		<com.liferay.commerce.shipping.engine.fixed.model.
			CommerceShippingFixedOptionRel> getCommerceShippingFixedOptionRels(
				int start, int end) {

		return _commerceShippingFixedOptionRelLocalService.
			getCommerceShippingFixedOptionRels(start, end);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.shipping.engine.fixed.model.
			CommerceShippingFixedOptionRel> getCommerceShippingFixedOptionRels(
				long commerceShippingFixedOptionId, int start, int end) {

		return _commerceShippingFixedOptionRelLocalService.
			getCommerceShippingFixedOptionRels(
				commerceShippingFixedOptionId, start, end);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.shipping.engine.fixed.model.
			CommerceShippingFixedOptionRel> getCommerceShippingFixedOptionRels(
				long commerceShippingFixedOptionId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.shipping.engine.fixed.model.
						CommerceShippingFixedOptionRel> orderByComparator) {

		return _commerceShippingFixedOptionRelLocalService.
			getCommerceShippingFixedOptionRels(
				commerceShippingFixedOptionId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of commerce shipping fixed option rels.
	 *
	 * @return the number of commerce shipping fixed option rels
	 */
	@Override
	public int getCommerceShippingFixedOptionRelsCount() {
		return _commerceShippingFixedOptionRelLocalService.
			getCommerceShippingFixedOptionRelsCount();
	}

	@Override
	public int getCommerceShippingFixedOptionRelsCount(
		long commerceShippingFixedOptionId) {

		return _commerceShippingFixedOptionRelLocalService.
			getCommerceShippingFixedOptionRelsCount(
				commerceShippingFixedOptionId);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.shipping.engine.fixed.model.
			CommerceShippingFixedOptionRel>
				getCommerceShippingMethodFixedOptionRels(
					long commerceShippingMethodId, int start, int end) {

		return _commerceShippingFixedOptionRelLocalService.
			getCommerceShippingMethodFixedOptionRels(
				commerceShippingMethodId, start, end);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.shipping.engine.fixed.model.
			CommerceShippingFixedOptionRel>
				getCommerceShippingMethodFixedOptionRels(
					long commerceShippingMethodId, int start, int end,
					com.liferay.portal.kernel.util.OrderByComparator
						<com.liferay.commerce.shipping.engine.fixed.model.
							CommerceShippingFixedOptionRel> orderByComparator) {

		return _commerceShippingFixedOptionRelLocalService.
			getCommerceShippingMethodFixedOptionRels(
				commerceShippingMethodId, start, end, orderByComparator);
	}

	@Override
	public int getCommerceShippingMethodFixedOptionRelsCount(
		long commerceShippingMethodId) {

		return _commerceShippingFixedOptionRelLocalService.
			getCommerceShippingMethodFixedOptionRelsCount(
				commerceShippingMethodId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _commerceShippingFixedOptionRelLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceShippingFixedOptionRelLocalService.
			getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceShippingFixedOptionRelLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Updates the commerce shipping fixed option rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param commerceShippingFixedOptionRel the commerce shipping fixed option rel
	 * @return the commerce shipping fixed option rel that was updated
	 */
	@Override
	public com.liferay.commerce.shipping.engine.fixed.model.
		CommerceShippingFixedOptionRel updateCommerceShippingFixedOptionRel(
			com.liferay.commerce.shipping.engine.fixed.model.
				CommerceShippingFixedOptionRel commerceShippingFixedOptionRel) {

		return _commerceShippingFixedOptionRelLocalService.
			updateCommerceShippingFixedOptionRel(
				commerceShippingFixedOptionRel);
	}

	@Override
	public com.liferay.commerce.shipping.engine.fixed.model.
		CommerceShippingFixedOptionRel updateCommerceShippingFixedOptionRel(
				long commerceShippingFixedOptionRelId,
				long commerceInventoryWarehouseId, long commerceCountryId,
				long commerceRegionId, String zip, double weightFrom,
				double weightTo, java.math.BigDecimal fixedPrice,
				java.math.BigDecimal rateUnitWeightPrice, double ratePercentage)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceShippingFixedOptionRelLocalService.
			updateCommerceShippingFixedOptionRel(
				commerceShippingFixedOptionRelId, commerceInventoryWarehouseId,
				commerceCountryId, commerceRegionId, zip, weightFrom, weightTo,
				fixedPrice, rateUnitWeightPrice, ratePercentage);
	}

	@Override
	public CommerceShippingFixedOptionRelLocalService getWrappedService() {
		return _commerceShippingFixedOptionRelLocalService;
	}

	@Override
	public void setWrappedService(
		CommerceShippingFixedOptionRelLocalService
			commerceShippingFixedOptionRelLocalService) {

		_commerceShippingFixedOptionRelLocalService =
			commerceShippingFixedOptionRelLocalService;
	}

	private CommerceShippingFixedOptionRelLocalService
		_commerceShippingFixedOptionRelLocalService;

}