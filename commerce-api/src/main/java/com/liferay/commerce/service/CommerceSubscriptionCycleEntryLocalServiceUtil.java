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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for CommerceSubscriptionCycleEntry. This utility wraps
 * {@link com.liferay.commerce.service.impl.CommerceSubscriptionCycleEntryLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceSubscriptionCycleEntryLocalService
 * @see com.liferay.commerce.service.base.CommerceSubscriptionCycleEntryLocalServiceBaseImpl
 * @see com.liferay.commerce.service.impl.CommerceSubscriptionCycleEntryLocalServiceImpl
 * @generated
 */
@ProviderType
public class CommerceSubscriptionCycleEntryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.commerce.service.impl.CommerceSubscriptionCycleEntryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the commerce subscription cycle entry to the database. Also notifies the appropriate model listeners.
	*
	* @param commerceSubscriptionCycleEntry the commerce subscription cycle entry
	* @return the commerce subscription cycle entry that was added
	*/
	public static com.liferay.commerce.model.CommerceSubscriptionCycleEntry addCommerceSubscriptionCycleEntry(
		com.liferay.commerce.model.CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry) {
		return getService()
				   .addCommerceSubscriptionCycleEntry(commerceSubscriptionCycleEntry);
	}

	public static com.liferay.commerce.model.CommerceSubscriptionCycleEntry addCommerceSubscriptionCycleEntry(
		long commerceSubscriptionEntryId, long commerceOrderItemId,
		boolean renew)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCommerceSubscriptionCycleEntry(commerceSubscriptionEntryId,
			commerceOrderItemId, renew);
	}

	/**
	* Creates a new commerce subscription cycle entry with the primary key. Does not add the commerce subscription cycle entry to the database.
	*
	* @param commerceSubscriptionCycleEntryId the primary key for the new commerce subscription cycle entry
	* @return the new commerce subscription cycle entry
	*/
	public static com.liferay.commerce.model.CommerceSubscriptionCycleEntry createCommerceSubscriptionCycleEntry(
		long commerceSubscriptionCycleEntryId) {
		return getService()
				   .createCommerceSubscriptionCycleEntry(commerceSubscriptionCycleEntryId);
	}

	/**
	* Deletes the commerce subscription cycle entry from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceSubscriptionCycleEntry the commerce subscription cycle entry
	* @return the commerce subscription cycle entry that was removed
	*/
	public static com.liferay.commerce.model.CommerceSubscriptionCycleEntry deleteCommerceSubscriptionCycleEntry(
		com.liferay.commerce.model.CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry) {
		return getService()
				   .deleteCommerceSubscriptionCycleEntry(commerceSubscriptionCycleEntry);
	}

	/**
	* Deletes the commerce subscription cycle entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceSubscriptionCycleEntryId the primary key of the commerce subscription cycle entry
	* @return the commerce subscription cycle entry that was removed
	* @throws PortalException if a commerce subscription cycle entry with the primary key could not be found
	*/
	public static com.liferay.commerce.model.CommerceSubscriptionCycleEntry deleteCommerceSubscriptionCycleEntry(
		long commerceSubscriptionCycleEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .deleteCommerceSubscriptionCycleEntry(commerceSubscriptionCycleEntryId);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.model.impl.CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.model.impl.CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.commerce.model.CommerceSubscriptionCycleEntry fetchCommerceSubscriptionCycleEntry(
		long commerceSubscriptionCycleEntryId) {
		return getService()
				   .fetchCommerceSubscriptionCycleEntry(commerceSubscriptionCycleEntryId);
	}

	public static com.liferay.commerce.model.CommerceSubscriptionCycleEntry fetchCommerceSubscriptionCycleEntryByCommerceOrderItemId(
		long commerceOrderItemId) {
		return getService()
				   .fetchCommerceSubscriptionCycleEntryByCommerceOrderItemId(commerceOrderItemId);
	}

	/**
	* Returns the commerce subscription cycle entry matching the UUID and group.
	*
	* @param uuid the commerce subscription cycle entry's UUID
	* @param groupId the primary key of the group
	* @return the matching commerce subscription cycle entry, or <code>null</code> if a matching commerce subscription cycle entry could not be found
	*/
	public static com.liferay.commerce.model.CommerceSubscriptionCycleEntry fetchCommerceSubscriptionCycleEntryByUuidAndGroupId(
		String uuid, long groupId) {
		return getService()
				   .fetchCommerceSubscriptionCycleEntryByUuidAndGroupId(uuid,
			groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns a range of all the commerce subscription cycle entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.model.impl.CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce subscription cycle entries
	* @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	* @return the range of commerce subscription cycle entries
	*/
	public static java.util.List<com.liferay.commerce.model.CommerceSubscriptionCycleEntry> getCommerceSubscriptionCycleEntries(
		int start, int end) {
		return getService().getCommerceSubscriptionCycleEntries(start, end);
	}

	public static java.util.List<com.liferay.commerce.model.CommerceSubscriptionCycleEntry> getCommerceSubscriptionCycleEntries(
		long commerceSubscriptionEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.model.CommerceSubscriptionCycleEntry> orderByComparator) {
		return getService()
				   .getCommerceSubscriptionCycleEntries(commerceSubscriptionEntryId,
			start, end, orderByComparator);
	}

	/**
	* Returns all the commerce subscription cycle entries matching the UUID and company.
	*
	* @param uuid the UUID of the commerce subscription cycle entries
	* @param companyId the primary key of the company
	* @return the matching commerce subscription cycle entries, or an empty list if no matches were found
	*/
	public static java.util.List<com.liferay.commerce.model.CommerceSubscriptionCycleEntry> getCommerceSubscriptionCycleEntriesByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService()
				   .getCommerceSubscriptionCycleEntriesByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of commerce subscription cycle entries matching the UUID and company.
	*
	* @param uuid the UUID of the commerce subscription cycle entries
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of commerce subscription cycle entries
	* @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching commerce subscription cycle entries, or an empty list if no matches were found
	*/
	public static java.util.List<com.liferay.commerce.model.CommerceSubscriptionCycleEntry> getCommerceSubscriptionCycleEntriesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.model.CommerceSubscriptionCycleEntry> orderByComparator) {
		return getService()
				   .getCommerceSubscriptionCycleEntriesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of commerce subscription cycle entries.
	*
	* @return the number of commerce subscription cycle entries
	*/
	public static int getCommerceSubscriptionCycleEntriesCount() {
		return getService().getCommerceSubscriptionCycleEntriesCount();
	}

	public static int getCommerceSubscriptionCycleEntriesCount(
		long commerceSubscriptionEntryId) {
		return getService()
				   .getCommerceSubscriptionCycleEntriesCount(commerceSubscriptionEntryId);
	}

	/**
	* Returns the commerce subscription cycle entry with the primary key.
	*
	* @param commerceSubscriptionCycleEntryId the primary key of the commerce subscription cycle entry
	* @return the commerce subscription cycle entry
	* @throws PortalException if a commerce subscription cycle entry with the primary key could not be found
	*/
	public static com.liferay.commerce.model.CommerceSubscriptionCycleEntry getCommerceSubscriptionCycleEntry(
		long commerceSubscriptionCycleEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getCommerceSubscriptionCycleEntry(commerceSubscriptionCycleEntryId);
	}

	/**
	* Returns the commerce subscription cycle entry matching the UUID and group.
	*
	* @param uuid the commerce subscription cycle entry's UUID
	* @param groupId the primary key of the group
	* @return the matching commerce subscription cycle entry
	* @throws PortalException if a matching commerce subscription cycle entry could not be found
	*/
	public static com.liferay.commerce.model.CommerceSubscriptionCycleEntry getCommerceSubscriptionCycleEntryByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getCommerceSubscriptionCycleEntryByUuidAndGroupId(uuid,
			groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
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
	* Updates the commerce subscription cycle entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param commerceSubscriptionCycleEntry the commerce subscription cycle entry
	* @return the commerce subscription cycle entry that was updated
	*/
	public static com.liferay.commerce.model.CommerceSubscriptionCycleEntry updateCommerceSubscriptionCycleEntry(
		com.liferay.commerce.model.CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry) {
		return getService()
				   .updateCommerceSubscriptionCycleEntry(commerceSubscriptionCycleEntry);
	}

	public static CommerceSubscriptionCycleEntryLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceSubscriptionCycleEntryLocalService, CommerceSubscriptionCycleEntryLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceSubscriptionCycleEntryLocalService.class);

		ServiceTracker<CommerceSubscriptionCycleEntryLocalService, CommerceSubscriptionCycleEntryLocalService> serviceTracker =
			new ServiceTracker<CommerceSubscriptionCycleEntryLocalService, CommerceSubscriptionCycleEntryLocalService>(bundle.getBundleContext(),
				CommerceSubscriptionCycleEntryLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}