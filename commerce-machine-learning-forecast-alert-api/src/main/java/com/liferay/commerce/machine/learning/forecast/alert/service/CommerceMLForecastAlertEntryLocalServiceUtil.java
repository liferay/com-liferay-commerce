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

package com.liferay.commerce.machine.learning.forecast.alert.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for CommerceMLForecastAlertEntry. This utility wraps
 * <code>com.liferay.commerce.machine.learning.forecast.alert.service.impl.CommerceMLForecastAlertEntryLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Riccardo Ferrari
 * @see CommerceMLForecastAlertEntryLocalService
 * @generated
 */
public class CommerceMLForecastAlertEntryLocalServiceUtil {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.machine.learning.forecast.alert.service.impl.CommerceMLForecastAlertEntryLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the commerce ml forecast alert entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceMLForecastAlertEntry the commerce ml forecast alert entry
	 * @return the commerce ml forecast alert entry that was added
	 */
	public static com.liferay.commerce.machine.learning.forecast.alert.model.
		CommerceMLForecastAlertEntry addCommerceMLForecastAlertEntry(
			com.liferay.commerce.machine.learning.forecast.alert.model.
				CommerceMLForecastAlertEntry commerceMLForecastAlertEntry) {

		return getService().addCommerceMLForecastAlertEntry(
			commerceMLForecastAlertEntry);
	}

	/**
	 * Creates a new commerce ml forecast alert entry with the primary key. Does not add the commerce ml forecast alert entry to the database.
	 *
	 * @param commerceMLForecastAlertEntryId the primary key for the new commerce ml forecast alert entry
	 * @return the new commerce ml forecast alert entry
	 */
	public static com.liferay.commerce.machine.learning.forecast.alert.model.
		CommerceMLForecastAlertEntry createCommerceMLForecastAlertEntry(
			long commerceMLForecastAlertEntryId) {

		return getService().createCommerceMLForecastAlertEntry(
			commerceMLForecastAlertEntryId);
	}

	/**
	 * Deletes the commerce ml forecast alert entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceMLForecastAlertEntry the commerce ml forecast alert entry
	 * @return the commerce ml forecast alert entry that was removed
	 */
	public static com.liferay.commerce.machine.learning.forecast.alert.model.
		CommerceMLForecastAlertEntry deleteCommerceMLForecastAlertEntry(
			com.liferay.commerce.machine.learning.forecast.alert.model.
				CommerceMLForecastAlertEntry commerceMLForecastAlertEntry) {

		return getService().deleteCommerceMLForecastAlertEntry(
			commerceMLForecastAlertEntry);
	}

	/**
	 * Deletes the commerce ml forecast alert entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceMLForecastAlertEntryId the primary key of the commerce ml forecast alert entry
	 * @return the commerce ml forecast alert entry that was removed
	 * @throws PortalException if a commerce ml forecast alert entry with the primary key could not be found
	 */
	public static com.liferay.commerce.machine.learning.forecast.alert.model.
		CommerceMLForecastAlertEntry deleteCommerceMLForecastAlertEntry(
				long commerceMLForecastAlertEntryId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCommerceMLForecastAlertEntry(
			commerceMLForecastAlertEntryId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.machine.learning.forecast.alert.model.impl.CommerceMLForecastAlertEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.machine.learning.forecast.alert.model.impl.CommerceMLForecastAlertEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.commerce.machine.learning.forecast.alert.model.
		CommerceMLForecastAlertEntry fetchCommerceMLForecastAlertEntry(
			long commerceMLForecastAlertEntryId) {

		return getService().fetchCommerceMLForecastAlertEntry(
			commerceMLForecastAlertEntryId);
	}

	/**
	 * Returns the commerce ml forecast alert entry with the matching UUID and company.
	 *
	 * @param uuid the commerce ml forecast alert entry's UUID
	 * @param companyId the primary key of the company
	 * @return the matching commerce ml forecast alert entry, or <code>null</code> if a matching commerce ml forecast alert entry could not be found
	 */
	public static com.liferay.commerce.machine.learning.forecast.alert.model.
		CommerceMLForecastAlertEntry
			fetchCommerceMLForecastAlertEntryByUuidAndCompanyId(
				String uuid, long companyId) {

		return getService().fetchCommerceMLForecastAlertEntryByUuidAndCompanyId(
			uuid, companyId);
	}

	public static java.util.List
		<com.liferay.commerce.machine.learning.forecast.alert.model.
			CommerceMLForecastAlertEntry>
				getAboveThresholdCommerceMLForecastAlertEntries(
					long companyId, long[] commerceAccountIds, double threshold,
					int status, int start, int end) {

		return getService().getAboveThresholdCommerceMLForecastAlertEntries(
			companyId, commerceAccountIds, threshold, status, start, end);
	}

	public static int getAboveThresholdCommerceMLForecastAlertEntriesCount(
		long companyId, long[] commerceAccountIds, double threshold,
		int status) {

		return getService().
			getAboveThresholdCommerceMLForecastAlertEntriesCount(
				companyId, commerceAccountIds, threshold, status);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static java.util.List
		<com.liferay.commerce.machine.learning.forecast.alert.model.
			CommerceMLForecastAlertEntry>
				getBelowThresholdCommerceMLForecastAlertEntries(
					long companyId, long[] commerceAccountIds, double threshold,
					int status, int start, int end) {

		return getService().getBelowThresholdCommerceMLForecastAlertEntries(
			companyId, commerceAccountIds, threshold, status, start, end);
	}

	public static int getBelowThresholdCommerceMLForecastAlertEntriesCount(
		long companyId, long[] commerceAccountIds, double threshold,
		int status) {

		return getService().
			getBelowThresholdCommerceMLForecastAlertEntriesCount(
				companyId, commerceAccountIds, threshold, status);
	}

	/**
	 * Returns a range of all the commerce ml forecast alert entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.machine.learning.forecast.alert.model.impl.CommerceMLForecastAlertEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce ml forecast alert entries
	 * @param end the upper bound of the range of commerce ml forecast alert entries (not inclusive)
	 * @return the range of commerce ml forecast alert entries
	 */
	public static java.util.List
		<com.liferay.commerce.machine.learning.forecast.alert.model.
			CommerceMLForecastAlertEntry> getCommerceMLForecastAlertEntries(
				int start, int end) {

		return getService().getCommerceMLForecastAlertEntries(start, end);
	}

	public static java.util.List
		<com.liferay.commerce.machine.learning.forecast.alert.model.
			CommerceMLForecastAlertEntry> getCommerceMLForecastAlertEntries(
				long companyId, long[] commerceAccountIds, int status,
				int start, int end) {

		return getService().getCommerceMLForecastAlertEntries(
			companyId, commerceAccountIds, status, start, end);
	}

	/**
	 * Returns the number of commerce ml forecast alert entries.
	 *
	 * @return the number of commerce ml forecast alert entries
	 */
	public static int getCommerceMLForecastAlertEntriesCount() {
		return getService().getCommerceMLForecastAlertEntriesCount();
	}

	public static int getCommerceMLForecastAlertEntriesCount(
		long companyId, long[] commerceAccountIds, int status) {

		return getService().getCommerceMLForecastAlertEntriesCount(
			companyId, commerceAccountIds, status);
	}

	/**
	 * Returns the commerce ml forecast alert entry with the primary key.
	 *
	 * @param commerceMLForecastAlertEntryId the primary key of the commerce ml forecast alert entry
	 * @return the commerce ml forecast alert entry
	 * @throws PortalException if a commerce ml forecast alert entry with the primary key could not be found
	 */
	public static com.liferay.commerce.machine.learning.forecast.alert.model.
		CommerceMLForecastAlertEntry getCommerceMLForecastAlertEntry(
				long commerceMLForecastAlertEntryId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommerceMLForecastAlertEntry(
			commerceMLForecastAlertEntryId);
	}

	/**
	 * Returns the commerce ml forecast alert entry with the matching UUID and company.
	 *
	 * @param uuid the commerce ml forecast alert entry's UUID
	 * @param companyId the primary key of the company
	 * @return the matching commerce ml forecast alert entry
	 * @throws PortalException if a matching commerce ml forecast alert entry could not be found
	 */
	public static com.liferay.commerce.machine.learning.forecast.alert.model.
		CommerceMLForecastAlertEntry
				getCommerceMLForecastAlertEntryByUuidAndCompanyId(
					String uuid, long companyId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommerceMLForecastAlertEntryByUuidAndCompanyId(
			uuid, companyId);
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
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
	 * Updates the commerce ml forecast alert entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param commerceMLForecastAlertEntry the commerce ml forecast alert entry
	 * @return the commerce ml forecast alert entry that was updated
	 */
	public static com.liferay.commerce.machine.learning.forecast.alert.model.
		CommerceMLForecastAlertEntry updateCommerceMLForecastAlertEntry(
			com.liferay.commerce.machine.learning.forecast.alert.model.
				CommerceMLForecastAlertEntry commerceMLForecastAlertEntry) {

		return getService().updateCommerceMLForecastAlertEntry(
			commerceMLForecastAlertEntry);
	}

	public static com.liferay.commerce.machine.learning.forecast.alert.model.
		CommerceMLForecastAlertEntry updateStatus(
				long userId, long commerceMLForecastAlertEntryId, int status)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateStatus(
			userId, commerceMLForecastAlertEntryId, status);
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>CommerceMLForecastAlertEntryLocalService</code> via injection or a <code>ServiceTracker</code> or use <code>CommerceMLForecastAlertEntryLocalServiceUtil</code>.
	 */
	public static com.liferay.commerce.machine.learning.forecast.alert.model.
		CommerceMLForecastAlertEntry upsertCommerceMLForecastAlertEntry(
				long companyId, long userId, long commerceAccountId,
				java.util.Date timestamp, float actual, float forecast,
				float relativeChange)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().upsertCommerceMLForecastAlertEntry(
			companyId, userId, commerceAccountId, timestamp, actual, forecast,
			relativeChange);
	}

	public static CommerceMLForecastAlertEntryLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CommerceMLForecastAlertEntryLocalService,
		 CommerceMLForecastAlertEntryLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CommerceMLForecastAlertEntryLocalService.class);

		ServiceTracker
			<CommerceMLForecastAlertEntryLocalService,
			 CommerceMLForecastAlertEntryLocalService> serviceTracker =
				new ServiceTracker
					<CommerceMLForecastAlertEntryLocalService,
					 CommerceMLForecastAlertEntryLocalService>(
						 bundle.getBundleContext(),
						 CommerceMLForecastAlertEntryLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}