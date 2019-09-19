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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceShippingMethodLocalService}.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceShippingMethodLocalService
 * @generated
 */
public class CommerceShippingMethodLocalServiceWrapper
	implements CommerceShippingMethodLocalService,
			   ServiceWrapper<CommerceShippingMethodLocalService> {

	public CommerceShippingMethodLocalServiceWrapper(
		CommerceShippingMethodLocalService commerceShippingMethodLocalService) {

		_commerceShippingMethodLocalService =
			commerceShippingMethodLocalService;
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceShippingMethodLocalServiceUtil} to access the commerce shipping method local service. Add custom service methods to <code>com.liferay.commerce.service.impl.CommerceShippingMethodLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Override
	public com.liferay.commerce.model.CommerceAddressRestriction
			addCommerceAddressRestriction(
				long commerceShippingMethodId, long commerceCountryId,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceShippingMethodLocalService.
			addCommerceAddressRestriction(
				commerceShippingMethodId, commerceCountryId, serviceContext);
	}

	/**
	 * Adds the commerce shipping method to the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceShippingMethod the commerce shipping method
	 * @return the commerce shipping method that was added
	 */
	@Override
	public com.liferay.commerce.model.CommerceShippingMethod
		addCommerceShippingMethod(
			com.liferay.commerce.model.CommerceShippingMethod
				commerceShippingMethod) {

		return _commerceShippingMethodLocalService.addCommerceShippingMethod(
			commerceShippingMethod);
	}

	@Override
	public com.liferay.commerce.model.CommerceShippingMethod
			addCommerceShippingMethod(
				java.util.Map<java.util.Locale, String> nameMap,
				java.util.Map<java.util.Locale, String> descriptionMap,
				java.io.File imageFile, String engineKey, double priority,
				boolean active,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceShippingMethodLocalService.addCommerceShippingMethod(
			nameMap, descriptionMap, imageFile, engineKey, priority, active,
			serviceContext);
	}

	/**
	 * Creates a new commerce shipping method with the primary key. Does not add the commerce shipping method to the database.
	 *
	 * @param commerceShippingMethodId the primary key for the new commerce shipping method
	 * @return the new commerce shipping method
	 */
	@Override
	public com.liferay.commerce.model.CommerceShippingMethod
		createCommerceShippingMethod(long commerceShippingMethodId) {

		return _commerceShippingMethodLocalService.createCommerceShippingMethod(
			commerceShippingMethodId);
	}

	@Override
	public void deleteCommerceAddressRestriction(
			long commerceAddressRestrictionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commerceShippingMethodLocalService.deleteCommerceAddressRestriction(
			commerceAddressRestrictionId);
	}

	/**
	 * Deletes the commerce shipping method from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceShippingMethod the commerce shipping method
	 * @return the commerce shipping method that was removed
	 * @throws PortalException
	 */
	@Override
	public com.liferay.commerce.model.CommerceShippingMethod
			deleteCommerceShippingMethod(
				com.liferay.commerce.model.CommerceShippingMethod
					commerceShippingMethod)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceShippingMethodLocalService.deleteCommerceShippingMethod(
			commerceShippingMethod);
	}

	/**
	 * Deletes the commerce shipping method with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceShippingMethodId the primary key of the commerce shipping method
	 * @return the commerce shipping method that was removed
	 * @throws PortalException if a commerce shipping method with the primary key could not be found
	 */
	@Override
	public com.liferay.commerce.model.CommerceShippingMethod
			deleteCommerceShippingMethod(long commerceShippingMethodId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceShippingMethodLocalService.deleteCommerceShippingMethod(
			commerceShippingMethodId);
	}

	@Override
	public void deleteCommerceShippingMethods(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commerceShippingMethodLocalService.deleteCommerceShippingMethods(
			groupId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceShippingMethodLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _commerceShippingMethodLocalService.dynamicQuery();
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

		return _commerceShippingMethodLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.model.impl.CommerceShippingMethodModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _commerceShippingMethodLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.model.impl.CommerceShippingMethodModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _commerceShippingMethodLocalService.dynamicQuery(
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

		return _commerceShippingMethodLocalService.dynamicQueryCount(
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

		return _commerceShippingMethodLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.commerce.model.CommerceShippingMethod
		fetchCommerceShippingMethod(long commerceShippingMethodId) {

		return _commerceShippingMethodLocalService.fetchCommerceShippingMethod(
			commerceShippingMethodId);
	}

	@Override
	public com.liferay.commerce.model.CommerceShippingMethod
		fetchCommerceShippingMethod(long groupId, String engineKey) {

		return _commerceShippingMethodLocalService.fetchCommerceShippingMethod(
			groupId, engineKey);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _commerceShippingMethodLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceAddressRestriction>
		getCommerceAddressRestrictions(
			long commerceShippingMethodId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.commerce.model.CommerceAddressRestriction>
					orderByComparator) {

		return _commerceShippingMethodLocalService.
			getCommerceAddressRestrictions(
				commerceShippingMethodId, start, end, orderByComparator);
	}

	@Override
	public int getCommerceAddressRestrictionsCount(
		long commerceShippingMethodId) {

		return _commerceShippingMethodLocalService.
			getCommerceAddressRestrictionsCount(commerceShippingMethodId);
	}

	/**
	 * Returns the commerce shipping method with the primary key.
	 *
	 * @param commerceShippingMethodId the primary key of the commerce shipping method
	 * @return the commerce shipping method
	 * @throws PortalException if a commerce shipping method with the primary key could not be found
	 */
	@Override
	public com.liferay.commerce.model.CommerceShippingMethod
			getCommerceShippingMethod(long commerceShippingMethodId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceShippingMethodLocalService.getCommerceShippingMethod(
			commerceShippingMethodId);
	}

	/**
	 * Returns a range of all the commerce shipping methods.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.model.impl.CommerceShippingMethodModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce shipping methods
	 * @param end the upper bound of the range of commerce shipping methods (not inclusive)
	 * @return the range of commerce shipping methods
	 */
	@Override
	public java.util.List<com.liferay.commerce.model.CommerceShippingMethod>
		getCommerceShippingMethods(int start, int end) {

		return _commerceShippingMethodLocalService.getCommerceShippingMethods(
			start, end);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceShippingMethod>
		getCommerceShippingMethods(long groupId) {

		return _commerceShippingMethodLocalService.getCommerceShippingMethods(
			groupId);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceShippingMethod>
		getCommerceShippingMethods(long groupId, boolean active) {

		return _commerceShippingMethodLocalService.getCommerceShippingMethods(
			groupId, active);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceShippingMethod>
		getCommerceShippingMethods(
			long groupId, long commerceCountryId, boolean active) {

		return _commerceShippingMethodLocalService.getCommerceShippingMethods(
			groupId, commerceCountryId, active);
	}

	/**
	 * Returns the number of commerce shipping methods.
	 *
	 * @return the number of commerce shipping methods
	 */
	@Override
	public int getCommerceShippingMethodsCount() {
		return _commerceShippingMethodLocalService.
			getCommerceShippingMethodsCount();
	}

	@Override
	public int getCommerceShippingMethodsCount(long groupId, boolean active) {
		return _commerceShippingMethodLocalService.
			getCommerceShippingMethodsCount(groupId, active);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _commerceShippingMethodLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceShippingMethodLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceShippingMethodLocalService.getPersistedModel(
			primaryKeyObj);
	}

	@Override
	public com.liferay.commerce.model.CommerceShippingMethod setActive(
			long commerceShippingMethodId, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceShippingMethodLocalService.setActive(
			commerceShippingMethodId, active);
	}

	/**
	 * Updates the commerce shipping method in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param commerceShippingMethod the commerce shipping method
	 * @return the commerce shipping method that was updated
	 */
	@Override
	public com.liferay.commerce.model.CommerceShippingMethod
		updateCommerceShippingMethod(
			com.liferay.commerce.model.CommerceShippingMethod
				commerceShippingMethod) {

		return _commerceShippingMethodLocalService.updateCommerceShippingMethod(
			commerceShippingMethod);
	}

	@Override
	public com.liferay.commerce.model.CommerceShippingMethod
			updateCommerceShippingMethod(
				long commerceShippingMethodId,
				java.util.Map<java.util.Locale, String> nameMap,
				java.util.Map<java.util.Locale, String> descriptionMap,
				java.io.File imageFile, double priority, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceShippingMethodLocalService.updateCommerceShippingMethod(
			commerceShippingMethodId, nameMap, descriptionMap, imageFile,
			priority, active);
	}

	@Override
	public CommerceShippingMethodLocalService getWrappedService() {
		return _commerceShippingMethodLocalService;
	}

	@Override
	public void setWrappedService(
		CommerceShippingMethodLocalService commerceShippingMethodLocalService) {

		_commerceShippingMethodLocalService =
			commerceShippingMethodLocalService;
	}

	private CommerceShippingMethodLocalService
		_commerceShippingMethodLocalService;

}