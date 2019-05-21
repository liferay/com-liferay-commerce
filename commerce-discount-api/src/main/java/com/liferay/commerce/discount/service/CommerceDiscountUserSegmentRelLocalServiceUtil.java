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
 * Provides the local service utility for CommerceDiscountUserSegmentRel. This utility wraps
 * <code>com.liferay.commerce.discount.service.impl.CommerceDiscountUserSegmentRelLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Marco Leo
 * @see CommerceDiscountUserSegmentRelLocalService
 * @generated
 */
@ProviderType
public class CommerceDiscountUserSegmentRelLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.discount.service.impl.CommerceDiscountUserSegmentRelLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the commerce discount user segment rel to the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceDiscountUserSegmentRel the commerce discount user segment rel
	 * @return the commerce discount user segment rel that was added
	 */
	public static
		com.liferay.commerce.discount.model.CommerceDiscountUserSegmentRel
			addCommerceDiscountUserSegmentRel(
				com.liferay.commerce.discount.model.
					CommerceDiscountUserSegmentRel
						commerceDiscountUserSegmentRel) {

		return getService().addCommerceDiscountUserSegmentRel(
			commerceDiscountUserSegmentRel);
	}

	public static
		com.liferay.commerce.discount.model.CommerceDiscountUserSegmentRel
				addCommerceDiscountUserSegmentRel(
					long commerceDiscountId, long commerceUserSegmentEntryId,
					com.liferay.portal.kernel.service.ServiceContext
						serviceContext)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCommerceDiscountUserSegmentRel(
			commerceDiscountId, commerceUserSegmentEntryId, serviceContext);
	}

	/**
	 * Creates a new commerce discount user segment rel with the primary key. Does not add the commerce discount user segment rel to the database.
	 *
	 * @param commerceDiscountUserSegmentRelId the primary key for the new commerce discount user segment rel
	 * @return the new commerce discount user segment rel
	 */
	public static
		com.liferay.commerce.discount.model.CommerceDiscountUserSegmentRel
			createCommerceDiscountUserSegmentRel(
				long commerceDiscountUserSegmentRelId) {

		return getService().createCommerceDiscountUserSegmentRel(
			commerceDiscountUserSegmentRelId);
	}

	/**
	 * Deletes the commerce discount user segment rel from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceDiscountUserSegmentRel the commerce discount user segment rel
	 * @return the commerce discount user segment rel that was removed
	 */
	public static
		com.liferay.commerce.discount.model.CommerceDiscountUserSegmentRel
			deleteCommerceDiscountUserSegmentRel(
				com.liferay.commerce.discount.model.
					CommerceDiscountUserSegmentRel
						commerceDiscountUserSegmentRel) {

		return getService().deleteCommerceDiscountUserSegmentRel(
			commerceDiscountUserSegmentRel);
	}

	/**
	 * Deletes the commerce discount user segment rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceDiscountUserSegmentRelId the primary key of the commerce discount user segment rel
	 * @return the commerce discount user segment rel that was removed
	 * @throws PortalException if a commerce discount user segment rel with the primary key could not be found
	 */
	public static
		com.liferay.commerce.discount.model.CommerceDiscountUserSegmentRel
				deleteCommerceDiscountUserSegmentRel(
					long commerceDiscountUserSegmentRelId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCommerceDiscountUserSegmentRel(
			commerceDiscountUserSegmentRelId);
	}

	public static void
		deleteCommerceDiscountUserSegmentRelsByCommerceDiscountId(
			long commerceDiscountId) {

		getService().deleteCommerceDiscountUserSegmentRelsByCommerceDiscountId(
			commerceDiscountId);
	}

	public static void
		deleteCommerceDiscountUserSegmentRelsByCommerceUserSegmentEntryId(
			long commerceUserSegmentEntryId) {

		getService().
			deleteCommerceDiscountUserSegmentRelsByCommerceUserSegmentEntryId(
				commerceUserSegmentEntryId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.discount.model.impl.CommerceDiscountUserSegmentRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.discount.model.impl.CommerceDiscountUserSegmentRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static
		com.liferay.commerce.discount.model.CommerceDiscountUserSegmentRel
			fetchCommerceDiscountUserSegmentRel(
				long commerceDiscountUserSegmentRelId) {

		return getService().fetchCommerceDiscountUserSegmentRel(
			commerceDiscountUserSegmentRelId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the commerce discount user segment rel with the primary key.
	 *
	 * @param commerceDiscountUserSegmentRelId the primary key of the commerce discount user segment rel
	 * @return the commerce discount user segment rel
	 * @throws PortalException if a commerce discount user segment rel with the primary key could not be found
	 */
	public static
		com.liferay.commerce.discount.model.CommerceDiscountUserSegmentRel
				getCommerceDiscountUserSegmentRel(
					long commerceDiscountUserSegmentRelId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommerceDiscountUserSegmentRel(
			commerceDiscountUserSegmentRelId);
	}

	/**
	 * Returns a range of all the commerce discount user segment rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.discount.model.impl.CommerceDiscountUserSegmentRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce discount user segment rels
	 * @param end the upper bound of the range of commerce discount user segment rels (not inclusive)
	 * @return the range of commerce discount user segment rels
	 */
	public static java.util.List
		<com.liferay.commerce.discount.model.CommerceDiscountUserSegmentRel>
			getCommerceDiscountUserSegmentRels(int start, int end) {

		return getService().getCommerceDiscountUserSegmentRels(start, end);
	}

	public static java.util.List
		<com.liferay.commerce.discount.model.CommerceDiscountUserSegmentRel>
			getCommerceDiscountUserSegmentRels(
				long commerceDiscountId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.discount.model.
						CommerceDiscountUserSegmentRel> orderByComparator) {

		return getService().getCommerceDiscountUserSegmentRels(
			commerceDiscountId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of commerce discount user segment rels.
	 *
	 * @return the number of commerce discount user segment rels
	 */
	public static int getCommerceDiscountUserSegmentRelsCount() {
		return getService().getCommerceDiscountUserSegmentRelsCount();
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
	 * Updates the commerce discount user segment rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param commerceDiscountUserSegmentRel the commerce discount user segment rel
	 * @return the commerce discount user segment rel that was updated
	 */
	public static
		com.liferay.commerce.discount.model.CommerceDiscountUserSegmentRel
			updateCommerceDiscountUserSegmentRel(
				com.liferay.commerce.discount.model.
					CommerceDiscountUserSegmentRel
						commerceDiscountUserSegmentRel) {

		return getService().updateCommerceDiscountUserSegmentRel(
			commerceDiscountUserSegmentRel);
	}

	public static CommerceDiscountUserSegmentRelLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CommerceDiscountUserSegmentRelLocalService,
		 CommerceDiscountUserSegmentRelLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CommerceDiscountUserSegmentRelLocalService.class);

		ServiceTracker
			<CommerceDiscountUserSegmentRelLocalService,
			 CommerceDiscountUserSegmentRelLocalService> serviceTracker =
				new ServiceTracker
					<CommerceDiscountUserSegmentRelLocalService,
					 CommerceDiscountUserSegmentRelLocalService>(
						 bundle.getBundleContext(),
						 CommerceDiscountUserSegmentRelLocalService.class,
						 null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}