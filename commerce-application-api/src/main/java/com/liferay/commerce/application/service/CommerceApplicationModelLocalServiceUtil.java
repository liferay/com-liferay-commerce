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

package com.liferay.commerce.application.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for CommerceApplicationModel. This utility wraps
 * {@link com.liferay.commerce.application.service.impl.CommerceApplicationModelLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Luca Pellizzon
 * @see CommerceApplicationModelLocalService
 * @see com.liferay.commerce.application.service.base.CommerceApplicationModelLocalServiceBaseImpl
 * @see com.liferay.commerce.application.service.impl.CommerceApplicationModelLocalServiceImpl
 * @generated
 */
@ProviderType
public class CommerceApplicationModelLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.commerce.application.service.impl.CommerceApplicationModelLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the commerce application model to the database. Also notifies the appropriate model listeners.
	*
	* @param commerceApplicationModel the commerce application model
	* @return the commerce application model that was added
	*/
	public static com.liferay.commerce.application.model.CommerceApplicationModel addCommerceApplicationModel(
		com.liferay.commerce.application.model.CommerceApplicationModel commerceApplicationModel) {
		return getService().addCommerceApplicationModel(commerceApplicationModel);
	}

	public static com.liferay.commerce.application.model.CommerceApplicationModel addCommerceApplicationModel(
		long userId, long commerceApplicationBrandId, String name, String year)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCommerceApplicationModel(userId,
			commerceApplicationBrandId, name, year);
	}

	/**
	* Creates a new commerce application model with the primary key. Does not add the commerce application model to the database.
	*
	* @param commerceApplicationModelId the primary key for the new commerce application model
	* @return the new commerce application model
	*/
	public static com.liferay.commerce.application.model.CommerceApplicationModel createCommerceApplicationModel(
		long commerceApplicationModelId) {
		return getService()
				   .createCommerceApplicationModel(commerceApplicationModelId);
	}

	/**
	* Deletes the commerce application model from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceApplicationModel the commerce application model
	* @return the commerce application model that was removed
	* @throws PortalException
	*/
	public static com.liferay.commerce.application.model.CommerceApplicationModel deleteCommerceApplicationModel(
		com.liferay.commerce.application.model.CommerceApplicationModel commerceApplicationModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .deleteCommerceApplicationModel(commerceApplicationModel);
	}

	/**
	* Deletes the commerce application model with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceApplicationModelId the primary key of the commerce application model
	* @return the commerce application model that was removed
	* @throws PortalException if a commerce application model with the primary key could not be found
	*/
	public static com.liferay.commerce.application.model.CommerceApplicationModel deleteCommerceApplicationModel(
		long commerceApplicationModelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .deleteCommerceApplicationModel(commerceApplicationModelId);
	}

	public static void deleteCommerceApplicationModels(
		long commerceApplicationBrandId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteCommerceApplicationModels(commerceApplicationBrandId);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.application.model.impl.CommerceApplicationModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.application.model.impl.CommerceApplicationModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.commerce.application.model.CommerceApplicationModel fetchCommerceApplicationModel(
		long commerceApplicationModelId) {
		return getService()
				   .fetchCommerceApplicationModel(commerceApplicationModelId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the commerce application model with the primary key.
	*
	* @param commerceApplicationModelId the primary key of the commerce application model
	* @return the commerce application model
	* @throws PortalException if a commerce application model with the primary key could not be found
	*/
	public static com.liferay.commerce.application.model.CommerceApplicationModel getCommerceApplicationModel(
		long commerceApplicationModelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getCommerceApplicationModel(commerceApplicationModelId);
	}

	/**
	* Returns a range of all the commerce application models.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.application.model.impl.CommerceApplicationModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce application models
	* @param end the upper bound of the range of commerce application models (not inclusive)
	* @return the range of commerce application models
	*/
	public static java.util.List<com.liferay.commerce.application.model.CommerceApplicationModel> getCommerceApplicationModels(
		int start, int end) {
		return getService().getCommerceApplicationModels(start, end);
	}

	/**
	* Returns the number of commerce application models.
	*
	* @return the number of commerce application models
	*/
	public static int getCommerceApplicationModelsCount() {
		return getService().getCommerceApplicationModelsCount();
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
	* Updates the commerce application model in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param commerceApplicationModel the commerce application model
	* @return the commerce application model that was updated
	*/
	public static com.liferay.commerce.application.model.CommerceApplicationModel updateCommerceApplicationModel(
		com.liferay.commerce.application.model.CommerceApplicationModel commerceApplicationModel) {
		return getService()
				   .updateCommerceApplicationModel(commerceApplicationModel);
	}

	public static com.liferay.commerce.application.model.CommerceApplicationModel updateCommerceApplicationModel(
		long commerceApplicationModelId, String name, String year)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCommerceApplicationModel(commerceApplicationModelId,
			name, year);
	}

	public static CommerceApplicationModelLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceApplicationModelLocalService, CommerceApplicationModelLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceApplicationModelLocalService.class);

		ServiceTracker<CommerceApplicationModelLocalService, CommerceApplicationModelLocalService> serviceTracker =
			new ServiceTracker<CommerceApplicationModelLocalService, CommerceApplicationModelLocalService>(bundle.getBundleContext(),
				CommerceApplicationModelLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}