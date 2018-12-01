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

package com.liferay.commerce.data.integration.manager.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.data.integration.manager.model.History;
import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link HistoryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see HistoryLocalService
 * @generated
 */
@ProviderType
public class HistoryLocalServiceWrapper implements HistoryLocalService,
	ServiceWrapper<HistoryLocalService> {
	public HistoryLocalServiceWrapper(HistoryLocalService historyLocalService) {
		_historyLocalService = historyLocalService;
	}

	/**
	* Adds the history to the database. Also notifies the appropriate model listeners.
	*
	* @param history the history
	* @return the history that was added
	*/
	@Override
	public History addHistory(
		History history) {
		return _historyLocalService.addHistory(history);
	}

	/**
	* NOTE FOR DEVELOPERS:
	*
	* Never reference this class directly. Always use {@link HistoryLocalServiceUtil} to access the history local service.
	*
	* @throws PortalException
	*/
	@Override
	public History addHistory(long userId,
							  long scheduledTaskId, String executionType, java.util.Date startDate,
							  java.util.Date endDate, int status, long errorLogFileEntryId,
							  long runtimeLogFileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _historyLocalService.addHistory(userId, scheduledTaskId,
			executionType, startDate, endDate, status, errorLogFileEntryId,
			runtimeLogFileEntryId);
	}

	@Override
	public History addHistory(
		long scheduledTaskId, String executionType, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _historyLocalService.addHistory(scheduledTaskId, executionType,
			status, serviceContext);
	}

	/**
	* Creates a new history with the primary key. Does not add the history to the database.
	*
	* @param historyId the primary key for the new history
	* @return the new history
	*/
	@Override
	public History createHistory(
		long historyId) {
		return _historyLocalService.createHistory(historyId);
	}

	@Override
	public History delete(long historyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _historyLocalService.delete(historyId);
	}

	/**
	* Deletes the history from the database. Also notifies the appropriate model listeners.
	*
	* @param history the history
	* @return the history that was removed
	*/
	@Override
	public History deleteHistory(
		History history) {
		return _historyLocalService.deleteHistory(history);
	}

	/**
	* Deletes the history with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param historyId the primary key of the history
	* @return the history that was removed
	* @throws PortalException if a history with the primary key could not be found
	*/
	@Override
	public History deleteHistory(
		long historyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _historyLocalService.deleteHistory(historyId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _historyLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _historyLocalService.dynamicQuery();
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
		return _historyLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _historyLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _historyLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _historyLocalService.dynamicQueryCount(dynamicQuery);
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
		return _historyLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public History fetchHistory(
		long historyId) {
		return _historyLocalService.fetchHistory(historyId);
	}

	/**
	* Returns the history matching the UUID and group.
	*
	* @param uuid the history's UUID
	* @param groupId the primary key of the group
	* @return the matching history, or <code>null</code> if a matching history could not be found
	*/
	@Override
	public History fetchHistoryByUuidAndGroupId(
		String uuid, long groupId) {
		return _historyLocalService.fetchHistoryByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _historyLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _historyLocalService.getExportActionableDynamicQuery(portletDataContext);
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
	@Override
	public java.util.List<History> getHistories(
		int start, int end) {
		return _historyLocalService.getHistories(start, end);
	}

	@Override
	public java.util.List<History> getHistoriesByGoupId(
		long groupId, int start, int end) {
		return _historyLocalService.getHistoriesByGoupId(groupId, start, end);
	}

	@Override
	public int getHistoriesByGoupIdCount(long groupId) {
		return _historyLocalService.getHistoriesByGoupIdCount(groupId);
	}

	/**
	* Returns all the histories matching the UUID and company.
	*
	* @param uuid the UUID of the histories
	* @param companyId the primary key of the company
	* @return the matching histories, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<History> getHistoriesByUuidAndCompanyId(
		String uuid, long companyId) {
		return _historyLocalService.getHistoriesByUuidAndCompanyId(uuid,
			companyId);
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
	@Override
	public java.util.List<History> getHistoriesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<History> orderByComparator) {
		return _historyLocalService.getHistoriesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of histories.
	*
	* @return the number of histories
	*/
	@Override
	public int getHistoriesCount() {
		return _historyLocalService.getHistoriesCount();
	}

	/**
	* Returns the history with the primary key.
	*
	* @param historyId the primary key of the history
	* @return the history
	* @throws PortalException if a history with the primary key could not be found
	*/
	@Override
	public History getHistory(long historyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _historyLocalService.getHistory(historyId);
	}

	/**
	* Returns the history matching the UUID and group.
	*
	* @param uuid the history's UUID
	* @param groupId the primary key of the group
	* @return the matching history
	* @throws PortalException if a matching history could not be found
	*/
	@Override
	public History getHistoryByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _historyLocalService.getHistoryByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _historyLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _historyLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _historyLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the history in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param history the history
	* @return the history that was updated
	*/
	@Override
	public History updateHistory(
		History history) {
		return _historyLocalService.updateHistory(history);
	}

	@Override
	public History updateHistory(
		long historyId, long scheduledTaskId, String executionType, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _historyLocalService.updateHistory(historyId, scheduledTaskId,
			executionType, status, serviceContext);
	}

	@Override
	public HistoryLocalService getWrappedService() {
		return _historyLocalService;
	}

	@Override
	public void setWrappedService(HistoryLocalService historyLocalService) {
		_historyLocalService = historyLocalService;
	}

	private HistoryLocalService _historyLocalService;
}