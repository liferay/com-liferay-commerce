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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for CommerceDiscountUsageEntry. This utility wraps
 * <code>com.liferay.commerce.discount.service.impl.CommerceDiscountUsageEntryLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Marco Leo
 * @see CommerceDiscountUsageEntryLocalService
 * @generated
 */
public class CommerceDiscountUsageEntryLocalServiceUtil {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.discount.service.impl.CommerceDiscountUsageEntryLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the commerce discount usage entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceDiscountUsageEntry the commerce discount usage entry
	 * @return the commerce discount usage entry that was added
	 */
	public static com.liferay.commerce.discount.model.CommerceDiscountUsageEntry
		addCommerceDiscountUsageEntry(
			com.liferay.commerce.discount.model.CommerceDiscountUsageEntry
				commerceDiscountUsageEntry) {

		return getService().addCommerceDiscountUsageEntry(
			commerceDiscountUsageEntry);
	}

	public static com.liferay.commerce.discount.model.CommerceDiscountUsageEntry
			addCommerceDiscountUsageEntry(
				long commerceAccountId, long commerceOrderId,
				long commerceDiscountId,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCommerceDiscountUsageEntry(
			commerceAccountId, commerceOrderId, commerceDiscountId,
			serviceContext);
	}

	/**
	 * Creates a new commerce discount usage entry with the primary key. Does not add the commerce discount usage entry to the database.
	 *
	 * @param commerceDiscountUsageEntryId the primary key for the new commerce discount usage entry
	 * @return the new commerce discount usage entry
	 */
	public static com.liferay.commerce.discount.model.CommerceDiscountUsageEntry
		createCommerceDiscountUsageEntry(long commerceDiscountUsageEntryId) {

		return getService().createCommerceDiscountUsageEntry(
			commerceDiscountUsageEntryId);
	}

	/**
	 * Deletes the commerce discount usage entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceDiscountUsageEntry the commerce discount usage entry
	 * @return the commerce discount usage entry that was removed
	 */
	public static com.liferay.commerce.discount.model.CommerceDiscountUsageEntry
		deleteCommerceDiscountUsageEntry(
			com.liferay.commerce.discount.model.CommerceDiscountUsageEntry
				commerceDiscountUsageEntry) {

		return getService().deleteCommerceDiscountUsageEntry(
			commerceDiscountUsageEntry);
	}

	/**
	 * Deletes the commerce discount usage entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceDiscountUsageEntryId the primary key of the commerce discount usage entry
	 * @return the commerce discount usage entry that was removed
	 * @throws PortalException if a commerce discount usage entry with the primary key could not be found
	 */
	public static com.liferay.commerce.discount.model.CommerceDiscountUsageEntry
			deleteCommerceDiscountUsageEntry(long commerceDiscountUsageEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCommerceDiscountUsageEntry(
			commerceDiscountUsageEntryId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.discount.model.impl.CommerceDiscountUsageEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.discount.model.impl.CommerceDiscountUsageEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.commerce.discount.model.CommerceDiscountUsageEntry
		fetchCommerceDiscountUsageEntry(long commerceDiscountUsageEntryId) {

		return getService().fetchCommerceDiscountUsageEntry(
			commerceDiscountUsageEntryId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns a range of all the commerce discount usage entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.discount.model.impl.CommerceDiscountUsageEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce discount usage entries
	 * @param end the upper bound of the range of commerce discount usage entries (not inclusive)
	 * @return the range of commerce discount usage entries
	 */
	public static java.util.List
		<com.liferay.commerce.discount.model.CommerceDiscountUsageEntry>
			getCommerceDiscountUsageEntries(int start, int end) {

		return getService().getCommerceDiscountUsageEntries(start, end);
	}

	/**
	 * Returns the number of commerce discount usage entries.
	 *
	 * @return the number of commerce discount usage entries
	 */
	public static int getCommerceDiscountUsageEntriesCount() {
		return getService().getCommerceDiscountUsageEntriesCount();
	}

	/**
	 * Returns the commerce discount usage entry with the primary key.
	 *
	 * @param commerceDiscountUsageEntryId the primary key of the commerce discount usage entry
	 * @return the commerce discount usage entry
	 * @throws PortalException if a commerce discount usage entry with the primary key could not be found
	 */
	public static com.liferay.commerce.discount.model.CommerceDiscountUsageEntry
			getCommerceDiscountUsageEntry(long commerceDiscountUsageEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommerceDiscountUsageEntry(
			commerceDiscountUsageEntryId);
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
	 * Updates the commerce discount usage entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param commerceDiscountUsageEntry the commerce discount usage entry
	 * @return the commerce discount usage entry that was updated
	 */
	public static com.liferay.commerce.discount.model.CommerceDiscountUsageEntry
		updateCommerceDiscountUsageEntry(
			com.liferay.commerce.discount.model.CommerceDiscountUsageEntry
				commerceDiscountUsageEntry) {

		return getService().updateCommerceDiscountUsageEntry(
			commerceDiscountUsageEntry);
	}

	public static CommerceDiscountUsageEntryLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CommerceDiscountUsageEntryLocalService,
		 CommerceDiscountUsageEntryLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CommerceDiscountUsageEntryLocalService.class);

		ServiceTracker
			<CommerceDiscountUsageEntryLocalService,
			 CommerceDiscountUsageEntryLocalService> serviceTracker =
				new ServiceTracker
					<CommerceDiscountUsageEntryLocalService,
					 CommerceDiscountUsageEntryLocalService>(
						 bundle.getBundleContext(),
						 CommerceDiscountUsageEntryLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}