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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for CommerceDiscountRel. This utility wraps
 * <code>com.liferay.commerce.discount.service.impl.CommerceDiscountRelLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Marco Leo
 * @see CommerceDiscountRelLocalService
 * @generated
 */
@ProviderType
public class CommerceDiscountRelLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.discount.service.impl.CommerceDiscountRelLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the commerce discount rel to the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceDiscountRel the commerce discount rel
	 * @return the commerce discount rel that was added
	 */
	public static com.liferay.commerce.discount.model.CommerceDiscountRel
		addCommerceDiscountRel(
			com.liferay.commerce.discount.model.CommerceDiscountRel
				commerceDiscountRel) {

		return getService().addCommerceDiscountRel(commerceDiscountRel);
	}

	public static com.liferay.commerce.discount.model.CommerceDiscountRel
			addCommerceDiscountRel(
				long commerceDiscountId, String className, long classPK,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCommerceDiscountRel(
			commerceDiscountId, className, classPK, serviceContext);
	}

	/**
	 * Creates a new commerce discount rel with the primary key. Does not add the commerce discount rel to the database.
	 *
	 * @param commerceDiscountRelId the primary key for the new commerce discount rel
	 * @return the new commerce discount rel
	 */
	public static com.liferay.commerce.discount.model.CommerceDiscountRel
		createCommerceDiscountRel(long commerceDiscountRelId) {

		return getService().createCommerceDiscountRel(commerceDiscountRelId);
	}

	/**
	 * Deletes the commerce discount rel from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceDiscountRel the commerce discount rel
	 * @return the commerce discount rel that was removed
	 * @throws PortalException
	 */
	public static com.liferay.commerce.discount.model.CommerceDiscountRel
			deleteCommerceDiscountRel(
				com.liferay.commerce.discount.model.CommerceDiscountRel
					commerceDiscountRel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCommerceDiscountRel(commerceDiscountRel);
	}

	/**
	 * Deletes the commerce discount rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceDiscountRelId the primary key of the commerce discount rel
	 * @return the commerce discount rel that was removed
	 * @throws PortalException if a commerce discount rel with the primary key could not be found
	 */
	public static com.liferay.commerce.discount.model.CommerceDiscountRel
			deleteCommerceDiscountRel(long commerceDiscountRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCommerceDiscountRel(commerceDiscountRelId);
	}

	public static void deleteCommerceDiscountRels(long commerceDiscountId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteCommerceDiscountRels(commerceDiscountId);
	}

	public static void deleteCommerceDiscountRels(
			String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteCommerceDiscountRels(className, classPK);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.discount.model.impl.CommerceDiscountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.discount.model.impl.CommerceDiscountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
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

	public static com.liferay.commerce.discount.model.CommerceDiscountRel
		fetchCommerceDiscountRel(long commerceDiscountRelId) {

		return getService().fetchCommerceDiscountRel(commerceDiscountRelId);
	}

	public static com.liferay.commerce.discount.model.CommerceDiscountRel
		fetchCommerceDiscountRel(String className, long classPK) {

		return getService().fetchCommerceDiscountRel(className, classPK);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static long[] getClassPKs(
		long commerceDiscountId, String className) {

		return getService().getClassPKs(commerceDiscountId, className);
	}

	/**
	 * Returns the commerce discount rel with the primary key.
	 *
	 * @param commerceDiscountRelId the primary key of the commerce discount rel
	 * @return the commerce discount rel
	 * @throws PortalException if a commerce discount rel with the primary key could not be found
	 */
	public static com.liferay.commerce.discount.model.CommerceDiscountRel
			getCommerceDiscountRel(long commerceDiscountRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommerceDiscountRel(commerceDiscountRelId);
	}

	/**
	 * Returns a range of all the commerce discount rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.discount.model.impl.CommerceDiscountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce discount rels
	 * @param end the upper bound of the range of commerce discount rels (not inclusive)
	 * @return the range of commerce discount rels
	 */
	public static java.util.List
		<com.liferay.commerce.discount.model.CommerceDiscountRel>
			getCommerceDiscountRels(int start, int end) {

		return getService().getCommerceDiscountRels(start, end);
	}

	public static java.util.List
		<com.liferay.commerce.discount.model.CommerceDiscountRel>
			getCommerceDiscountRels(long commerceDiscountId, String className) {

		return getService().getCommerceDiscountRels(
			commerceDiscountId, className);
	}

	public static java.util.List
		<com.liferay.commerce.discount.model.CommerceDiscountRel>
			getCommerceDiscountRels(
				long commerceDiscountId, String className, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.discount.model.CommerceDiscountRel>
						orderByComparator) {

		return getService().getCommerceDiscountRels(
			commerceDiscountId, className, start, end, orderByComparator);
	}

	/**
	 * Returns the number of commerce discount rels.
	 *
	 * @return the number of commerce discount rels
	 */
	public static int getCommerceDiscountRelsCount() {
		return getService().getCommerceDiscountRelsCount();
	}

	public static int getCommerceDiscountRelsCount(
		long commerceDiscountId, String className) {

		return getService().getCommerceDiscountRelsCount(
			commerceDiscountId, className);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

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

	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the commerce discount rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param commerceDiscountRel the commerce discount rel
	 * @return the commerce discount rel that was updated
	 */
	public static com.liferay.commerce.discount.model.CommerceDiscountRel
		updateCommerceDiscountRel(
			com.liferay.commerce.discount.model.CommerceDiscountRel
				commerceDiscountRel) {

		return getService().updateCommerceDiscountRel(commerceDiscountRel);
	}

	public static CommerceDiscountRelLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CommerceDiscountRelLocalService, CommerceDiscountRelLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CommerceDiscountRelLocalService.class);

		ServiceTracker
			<CommerceDiscountRelLocalService, CommerceDiscountRelLocalService>
				serviceTracker =
					new ServiceTracker
						<CommerceDiscountRelLocalService,
						 CommerceDiscountRelLocalService>(
							 bundle.getBundleContext(),
							 CommerceDiscountRelLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}