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

package com.liferay.data.integration.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for History. This utility wraps
 * {@link com.liferay.data.integration.service.impl.HistoryLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see HistoryLocalService
 * @see com.liferay.data.integration.service.base.HistoryLocalServiceBaseImpl
 * @see com.liferay.data.integration.service.impl.HistoryLocalServiceImpl
 * @generated
 */
@ProviderType
public class HistoryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.data.integration.service.impl.HistoryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the history to the database. Also notifies the appropriate model listeners.
	*
	* @param history the history
	* @return the history that was added
	*/
	public static com.liferay.data.integration.model.History addHistory(
		com.liferay.data.integration.model.History history) {
		return getService().addHistory(history);
	}

	/**
	* NOTE FOR DEVELOPERS:
	*
	* Never reference this class directly. Always use {@link HistoryLocalServiceUtil} to access the history local service.
	*
	* @throws PortalException
	*/
	public static com.liferay.data.integration.model.History addHistory(
		long userId, long scheduledTaskId, String executionType,
		java.util.Date startDate, java.util.Date endDate, int status,
		long errorLogFileEntryId, long runtimeLogFileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addHistory(userId, scheduledTaskId, executionType,
			startDate, endDate, status, errorLogFileEntryId,
			runtimeLogFileEntryId);
	}

	public static com.liferay.data.integration.model.History addHistory(
		long scheduledTaskId, String executionType, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addHistory(scheduledTaskId, executionType, status,
			serviceContext);
	}

	/**
	* Creates a new history with the primary key. Does not add the history to the database.
	*
	* @param historyId the primary key for the new history
	* @return the new history
	*/
	public static com.liferay.data.integration.model.History createHistory(
		long historyId) {
		return getService().createHistory(historyId);
	}

	public static com.liferay.data.integration.model.History delete(
		long historyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().delete(historyId);
	}

	/**
	* Deletes the history from the database. Also notifies the appropriate model listeners.
	*
	* @param history the history
	* @return the history that was removed
	*/
	public static com.liferay.data.integration.model.History deleteHistory(
		com.liferay.data.integration.model.History history) {
		return getService().deleteHistory(history);
	}

	/**
	* Deletes the history with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param historyId the primary key of the history
	* @return the history that was removed
	* @throws PortalException if a history with the primary key could not be found
	*/
	public static com.liferay.data.integration.model.History deleteHistory(
		long historyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteHistory(historyId);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.data.integration.model.impl.HistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.data.integration.model.impl.HistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.data.integration.model.History fetchHistory(
		long historyId) {
		return getService().fetchHistory(historyId);
	}

	/**
	* Returns the history matching the UUID and group.
	*
	* @param uuid the history's UUID
	* @param groupId the primary key of the group
	* @return the matching history, or <code>null</code> if a matching history could not be found
	*/
	public static com.liferay.data.integration.model.History fetchHistoryByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchHistoryByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	/**
	* Returns a range of all the histories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.data.integration.model.impl.HistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of histories
	* @param end the upper bound of the range of histories (not inclusive)
	* @return the range of histories
	*/
	public static java.util.List<com.liferay.data.integration.model.History> getHistories(
		int start, int end) {
		return getService().getHistories(start, end);
	}

	public static java.util.List<com.liferay.data.integration.model.History> getHistoriesByGoupId(
		long groupId, int start, int end) {
		return getService().getHistoriesByGoupId(groupId, start, end);
	}

	public static int getHistoriesByGoupIdCount(long groupId) {
		return getService().getHistoriesByGoupIdCount(groupId);
	}

	/**
	* Returns all the histories matching the UUID and company.
	*
	* @param uuid the UUID of the histories
	* @param companyId the primary key of the company
	* @return the matching histories, or an empty list if no matches were found
	*/
	public static java.util.List<com.liferay.data.integration.model.History> getHistoriesByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getHistoriesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of histories matching the UUID and company.
	*
	* @param uuid the UUID of the histories
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of histories
	* @param end the upper bound of the range of histories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching histories, or an empty list if no matches were found
	*/
	public static java.util.List<com.liferay.data.integration.model.History> getHistoriesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.data.integration.model.History> orderByComparator) {
		return getService()
				   .getHistoriesByUuidAndCompanyId(uuid, companyId, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of histories.
	*
	* @return the number of histories
	*/
	public static int getHistoriesCount() {
		return getService().getHistoriesCount();
	}

	/**
	* Returns the history with the primary key.
	*
	* @param historyId the primary key of the history
	* @return the history
	* @throws PortalException if a history with the primary key could not be found
	*/
	public static com.liferay.data.integration.model.History getHistory(
		long historyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getHistory(historyId);
	}

	/**
	* Returns the history matching the UUID and group.
	*
	* @param uuid the history's UUID
	* @param groupId the primary key of the group
	* @return the matching history
	* @throws PortalException if a matching history could not be found
	*/
	public static com.liferay.data.integration.model.History getHistoryByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getHistoryByUuidAndGroupId(uuid, groupId);
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
	* Updates the history in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param history the history
	* @return the history that was updated
	*/
	public static com.liferay.data.integration.model.History updateHistory(
		com.liferay.data.integration.model.History history) {
		return getService().updateHistory(history);
	}

	public static com.liferay.data.integration.model.History updateHistory(
		long historyId, long scheduledTaskId, String executionType, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateHistory(historyId, scheduledTaskId, executionType,
			status, serviceContext);
	}

	public static HistoryLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<HistoryLocalService, HistoryLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(HistoryLocalService.class);

		ServiceTracker<HistoryLocalService, HistoryLocalService> serviceTracker = new ServiceTracker<HistoryLocalService, HistoryLocalService>(bundle.getBundleContext(),
				HistoryLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}