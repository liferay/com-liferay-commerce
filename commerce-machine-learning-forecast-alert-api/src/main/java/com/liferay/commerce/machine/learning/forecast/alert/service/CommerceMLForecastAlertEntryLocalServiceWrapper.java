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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceMLForecastAlertEntryLocalService}.
 *
 * @author Riccardo Ferrari
 * @see CommerceMLForecastAlertEntryLocalService
 * @generated
 */
public class CommerceMLForecastAlertEntryLocalServiceWrapper
	implements CommerceMLForecastAlertEntryLocalService,
			   ServiceWrapper<CommerceMLForecastAlertEntryLocalService> {

	public CommerceMLForecastAlertEntryLocalServiceWrapper(
		CommerceMLForecastAlertEntryLocalService
			commerceMLForecastAlertEntryLocalService) {

		_commerceMLForecastAlertEntryLocalService =
			commerceMLForecastAlertEntryLocalService;
	}

	/**
	 * Adds the commerce ml forecast alert entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceMLForecastAlertEntry the commerce ml forecast alert entry
	 * @return the commerce ml forecast alert entry that was added
	 */
	@Override
	public com.liferay.commerce.machine.learning.forecast.alert.model.
		CommerceMLForecastAlertEntry addCommerceMLForecastAlertEntry(
			com.liferay.commerce.machine.learning.forecast.alert.model.
				CommerceMLForecastAlertEntry commerceMLForecastAlertEntry) {

		return _commerceMLForecastAlertEntryLocalService.
			addCommerceMLForecastAlertEntry(commerceMLForecastAlertEntry);
	}

	/**
	 * Creates a new commerce ml forecast alert entry with the primary key. Does not add the commerce ml forecast alert entry to the database.
	 *
	 * @param commerceMLForecastAlertEntryId the primary key for the new commerce ml forecast alert entry
	 * @return the new commerce ml forecast alert entry
	 */
	@Override
	public com.liferay.commerce.machine.learning.forecast.alert.model.
		CommerceMLForecastAlertEntry createCommerceMLForecastAlertEntry(
			long commerceMLForecastAlertEntryId) {

		return _commerceMLForecastAlertEntryLocalService.
			createCommerceMLForecastAlertEntry(commerceMLForecastAlertEntryId);
	}

	/**
	 * Deletes the commerce ml forecast alert entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceMLForecastAlertEntry the commerce ml forecast alert entry
	 * @return the commerce ml forecast alert entry that was removed
	 */
	@Override
	public com.liferay.commerce.machine.learning.forecast.alert.model.
		CommerceMLForecastAlertEntry deleteCommerceMLForecastAlertEntry(
			com.liferay.commerce.machine.learning.forecast.alert.model.
				CommerceMLForecastAlertEntry commerceMLForecastAlertEntry) {

		return _commerceMLForecastAlertEntryLocalService.
			deleteCommerceMLForecastAlertEntry(commerceMLForecastAlertEntry);
	}

	/**
	 * Deletes the commerce ml forecast alert entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceMLForecastAlertEntryId the primary key of the commerce ml forecast alert entry
	 * @return the commerce ml forecast alert entry that was removed
	 * @throws PortalException if a commerce ml forecast alert entry with the primary key could not be found
	 */
	@Override
	public com.liferay.commerce.machine.learning.forecast.alert.model.
		CommerceMLForecastAlertEntry deleteCommerceMLForecastAlertEntry(
				long commerceMLForecastAlertEntryId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceMLForecastAlertEntryLocalService.
			deleteCommerceMLForecastAlertEntry(commerceMLForecastAlertEntryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceMLForecastAlertEntryLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _commerceMLForecastAlertEntryLocalService.dynamicQuery();
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

		return _commerceMLForecastAlertEntryLocalService.dynamicQuery(
			dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _commerceMLForecastAlertEntryLocalService.dynamicQuery(
			dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _commerceMLForecastAlertEntryLocalService.dynamicQuery(
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

		return _commerceMLForecastAlertEntryLocalService.dynamicQueryCount(
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

		return _commerceMLForecastAlertEntryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.commerce.machine.learning.forecast.alert.model.
		CommerceMLForecastAlertEntry fetchCommerceMLForecastAlertEntry(
			long commerceMLForecastAlertEntryId) {

		return _commerceMLForecastAlertEntryLocalService.
			fetchCommerceMLForecastAlertEntry(commerceMLForecastAlertEntryId);
	}

	/**
	 * Returns the commerce ml forecast alert entry with the matching UUID and company.
	 *
	 * @param uuid the commerce ml forecast alert entry's UUID
	 * @param companyId the primary key of the company
	 * @return the matching commerce ml forecast alert entry, or <code>null</code> if a matching commerce ml forecast alert entry could not be found
	 */
	@Override
	public com.liferay.commerce.machine.learning.forecast.alert.model.
		CommerceMLForecastAlertEntry
			fetchCommerceMLForecastAlertEntryByUuidAndCompanyId(
				String uuid, long companyId) {

		return _commerceMLForecastAlertEntryLocalService.
			fetchCommerceMLForecastAlertEntryByUuidAndCompanyId(
				uuid, companyId);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.machine.learning.forecast.alert.model.
			CommerceMLForecastAlertEntry>
				getAboveThresholdCommerceMLForecastAlertEntries(
					long companyId, long[] commerceAccountIds, double threshold,
					int status, int start, int end) {

		return _commerceMLForecastAlertEntryLocalService.
			getAboveThresholdCommerceMLForecastAlertEntries(
				companyId, commerceAccountIds, threshold, status, start, end);
	}

	@Override
	public int getAboveThresholdCommerceMLForecastAlertEntriesCount(
		long companyId, long[] commerceAccountIds, double threshold,
		int status) {

		return _commerceMLForecastAlertEntryLocalService.
			getAboveThresholdCommerceMLForecastAlertEntriesCount(
				companyId, commerceAccountIds, threshold, status);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _commerceMLForecastAlertEntryLocalService.
			getActionableDynamicQuery();
	}

	@Override
	public java.util.List
		<com.liferay.commerce.machine.learning.forecast.alert.model.
			CommerceMLForecastAlertEntry>
				getBelowThresholdCommerceMLForecastAlertEntries(
					long companyId, long[] commerceAccountIds, double threshold,
					int status, int start, int end) {

		return _commerceMLForecastAlertEntryLocalService.
			getBelowThresholdCommerceMLForecastAlertEntries(
				companyId, commerceAccountIds, threshold, status, start, end);
	}

	@Override
	public int getBelowThresholdCommerceMLForecastAlertEntriesCount(
		long companyId, long[] commerceAccountIds, double threshold,
		int status) {

		return _commerceMLForecastAlertEntryLocalService.
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
	@Override
	public java.util.List
		<com.liferay.commerce.machine.learning.forecast.alert.model.
			CommerceMLForecastAlertEntry> getCommerceMLForecastAlertEntries(
				int start, int end) {

		return _commerceMLForecastAlertEntryLocalService.
			getCommerceMLForecastAlertEntries(start, end);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.machine.learning.forecast.alert.model.
			CommerceMLForecastAlertEntry> getCommerceMLForecastAlertEntries(
				long companyId, long[] commerceAccountIds, int status,
				int start, int end) {

		return _commerceMLForecastAlertEntryLocalService.
			getCommerceMLForecastAlertEntries(
				companyId, commerceAccountIds, status, start, end);
	}

	/**
	 * Returns the number of commerce ml forecast alert entries.
	 *
	 * @return the number of commerce ml forecast alert entries
	 */
	@Override
	public int getCommerceMLForecastAlertEntriesCount() {
		return _commerceMLForecastAlertEntryLocalService.
			getCommerceMLForecastAlertEntriesCount();
	}

	@Override
	public int getCommerceMLForecastAlertEntriesCount(
		long companyId, long[] commerceAccountIds, int status) {

		return _commerceMLForecastAlertEntryLocalService.
			getCommerceMLForecastAlertEntriesCount(
				companyId, commerceAccountIds, status);
	}

	/**
	 * Returns the commerce ml forecast alert entry with the primary key.
	 *
	 * @param commerceMLForecastAlertEntryId the primary key of the commerce ml forecast alert entry
	 * @return the commerce ml forecast alert entry
	 * @throws PortalException if a commerce ml forecast alert entry with the primary key could not be found
	 */
	@Override
	public com.liferay.commerce.machine.learning.forecast.alert.model.
		CommerceMLForecastAlertEntry getCommerceMLForecastAlertEntry(
				long commerceMLForecastAlertEntryId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceMLForecastAlertEntryLocalService.
			getCommerceMLForecastAlertEntry(commerceMLForecastAlertEntryId);
	}

	/**
	 * Returns the commerce ml forecast alert entry with the matching UUID and company.
	 *
	 * @param uuid the commerce ml forecast alert entry's UUID
	 * @param companyId the primary key of the company
	 * @return the matching commerce ml forecast alert entry
	 * @throws PortalException if a matching commerce ml forecast alert entry could not be found
	 */
	@Override
	public com.liferay.commerce.machine.learning.forecast.alert.model.
		CommerceMLForecastAlertEntry
				getCommerceMLForecastAlertEntryByUuidAndCompanyId(
					String uuid, long companyId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceMLForecastAlertEntryLocalService.
			getCommerceMLForecastAlertEntryByUuidAndCompanyId(uuid, companyId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _commerceMLForecastAlertEntryLocalService.
			getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _commerceMLForecastAlertEntryLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceMLForecastAlertEntryLocalService.
			getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceMLForecastAlertEntryLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Updates the commerce ml forecast alert entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param commerceMLForecastAlertEntry the commerce ml forecast alert entry
	 * @return the commerce ml forecast alert entry that was updated
	 */
	@Override
	public com.liferay.commerce.machine.learning.forecast.alert.model.
		CommerceMLForecastAlertEntry updateCommerceMLForecastAlertEntry(
			com.liferay.commerce.machine.learning.forecast.alert.model.
				CommerceMLForecastAlertEntry commerceMLForecastAlertEntry) {

		return _commerceMLForecastAlertEntryLocalService.
			updateCommerceMLForecastAlertEntry(commerceMLForecastAlertEntry);
	}

	@Override
	public com.liferay.commerce.machine.learning.forecast.alert.model.
		CommerceMLForecastAlertEntry updateStatus(
				long userId, long commerceMLForecastAlertEntryId, int status)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceMLForecastAlertEntryLocalService.updateStatus(
			userId, commerceMLForecastAlertEntryId, status);
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>CommerceMLForecastAlertEntryLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>CommerceMLForecastAlertEntryLocalServiceUtil</code>.
	 */
	@Override
	public com.liferay.commerce.machine.learning.forecast.alert.model.
		CommerceMLForecastAlertEntry upsertCommerceMLForecastAlertEntry(
				long companyId, long userId, long commerceAccountId,
				java.util.Date timestamp, float actual, float forecast,
				float relativeChange)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceMLForecastAlertEntryLocalService.
			upsertCommerceMLForecastAlertEntry(
				companyId, userId, commerceAccountId, timestamp, actual,
				forecast, relativeChange);
	}

	@Override
	public CommerceMLForecastAlertEntryLocalService getWrappedService() {
		return _commerceMLForecastAlertEntryLocalService;
	}

	@Override
	public void setWrappedService(
		CommerceMLForecastAlertEntryLocalService
			commerceMLForecastAlertEntryLocalService) {

		_commerceMLForecastAlertEntryLocalService =
			commerceMLForecastAlertEntryLocalService;
	}

	private CommerceMLForecastAlertEntryLocalService
		_commerceMLForecastAlertEntryLocalService;

}