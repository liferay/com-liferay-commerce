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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ScheduledTaskLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ScheduledTaskLocalService
 * @generated
 */
@ProviderType
public class ScheduledTaskLocalServiceWrapper
	implements ScheduledTaskLocalService,
		ServiceWrapper<ScheduledTaskLocalService> {
	public ScheduledTaskLocalServiceWrapper(
		ScheduledTaskLocalService scheduledTaskLocalService) {
		_scheduledTaskLocalService = scheduledTaskLocalService;
	}

	/**
	* NOTE FOR DEVELOPERS:
	*
	* Never reference this class directly. Always use {@link ScheduledTaskLocalServiceUtil} to access the scheduled task local service.
	*
	* @throws PortalException
	*/
	@Override
	public com.liferay.commerce.data.integration.manager.model.ScheduledTask addScheduledTask(
		long processId, String frequency, java.util.Date startDate,
		String startHour, String name,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _scheduledTaskLocalService.addScheduledTask(processId,
			frequency, startDate, startHour, name, serviceContext);
	}

	/**
	* Adds the scheduled task to the database. Also notifies the appropriate model listeners.
	*
	* @param scheduledTask the scheduled task
	* @return the scheduled task that was added
	*/
	@Override
	public com.liferay.commerce.data.integration.manager.model.ScheduledTask addScheduledTask(
		com.liferay.commerce.data.integration.manager.model.ScheduledTask scheduledTask) {
		return _scheduledTaskLocalService.addScheduledTask(scheduledTask);
	}

	@Override
	public int countScheduledTasksByActive(boolean active) {
		return _scheduledTaskLocalService.countScheduledTasksByActive(active);
	}

	@Override
	public int countScheduledTasksByActive(long groupId, boolean active) {
		return _scheduledTaskLocalService.countScheduledTasksByActive(groupId,
			active);
	}

	@Override
	public int countScheduledTasksByEnabled(boolean enabled) {
		return _scheduledTaskLocalService.countScheduledTasksByEnabled(enabled);
	}

	@Override
	public int countScheduledTasksByGroupId_Enabled(long groupId,
		boolean enabled) {
		return _scheduledTaskLocalService.countScheduledTasksByGroupId_Enabled(groupId,
			enabled);
	}

	/**
	* Creates a new scheduled task with the primary key. Does not add the scheduled task to the database.
	*
	* @param scheduledTaskId the primary key for the new scheduled task
	* @return the new scheduled task
	*/
	@Override
	public com.liferay.commerce.data.integration.manager.model.ScheduledTask createScheduledTask(
		long scheduledTaskId) {
		return _scheduledTaskLocalService.createScheduledTask(scheduledTaskId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _scheduledTaskLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the scheduled task with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param scheduledTaskId the primary key of the scheduled task
	* @return the scheduled task that was removed
	* @throws PortalException if a scheduled task with the primary key could not be found
	*/
	@Override
	public com.liferay.commerce.data.integration.manager.model.ScheduledTask deleteScheduledTask(
		long scheduledTaskId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _scheduledTaskLocalService.deleteScheduledTask(scheduledTaskId);
	}

	/**
	* Deletes the scheduled task from the database. Also notifies the appropriate model listeners.
	*
	* @param scheduledTask the scheduled task
	* @return the scheduled task that was removed
	*/
	@Override
	public com.liferay.commerce.data.integration.manager.model.ScheduledTask deleteScheduledTask(
		com.liferay.commerce.data.integration.manager.model.ScheduledTask scheduledTask) {
		return _scheduledTaskLocalService.deleteScheduledTask(scheduledTask);
	}

	@Override
	public com.liferay.commerce.data.integration.manager.model.ScheduledTask disableScheduledTask(
		long scheduledTaskId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _scheduledTaskLocalService.disableScheduledTask(scheduledTaskId,
			userId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _scheduledTaskLocalService.dynamicQuery();
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
		return _scheduledTaskLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.data.integration.manager.model.impl.ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _scheduledTaskLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.data.integration.manager.model.impl.ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _scheduledTaskLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _scheduledTaskLocalService.dynamicQueryCount(dynamicQuery);
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
		return _scheduledTaskLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.commerce.data.integration.manager.model.ScheduledTask enableScheduledTask(
		long scheduledTaskId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _scheduledTaskLocalService.enableScheduledTask(scheduledTaskId,
			userId);
	}

	@Override
	public com.liferay.commerce.data.integration.manager.model.ScheduledTask fetchScheduledTask(
		long scheduledTaskId) {
		return _scheduledTaskLocalService.fetchScheduledTask(scheduledTaskId);
	}

	/**
	* Returns the scheduled task matching the UUID and group.
	*
	* @param uuid the scheduled task's UUID
	* @param groupId the primary key of the group
	* @return the matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	*/
	@Override
	public com.liferay.commerce.data.integration.manager.model.ScheduledTask fetchScheduledTaskByUuidAndGroupId(
		String uuid, long groupId) {
		return _scheduledTaskLocalService.fetchScheduledTaskByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _scheduledTaskLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _scheduledTaskLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _scheduledTaskLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _scheduledTaskLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _scheduledTaskLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the scheduled task with the primary key.
	*
	* @param scheduledTaskId the primary key of the scheduled task
	* @return the scheduled task
	* @throws PortalException if a scheduled task with the primary key could not be found
	*/
	@Override
	public com.liferay.commerce.data.integration.manager.model.ScheduledTask getScheduledTask(
		long scheduledTaskId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _scheduledTaskLocalService.getScheduledTask(scheduledTaskId);
	}

	@Override
	public java.util.List<com.liferay.commerce.data.integration.manager.model.ScheduledTask> getScheduledTaskByGroupId(
		long groupId, int start, int end) {
		return _scheduledTaskLocalService.getScheduledTaskByGroupId(groupId,
			start, end);
	}

	@Override
	public int getScheduledTaskByGroupIdCount(long groupId) {
		return _scheduledTaskLocalService.getScheduledTaskByGroupIdCount(groupId);
	}

	/**
	* Returns the scheduled task matching the UUID and group.
	*
	* @param uuid the scheduled task's UUID
	* @param groupId the primary key of the group
	* @return the matching scheduled task
	* @throws PortalException if a matching scheduled task could not be found
	*/
	@Override
	public com.liferay.commerce.data.integration.manager.model.ScheduledTask getScheduledTaskByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _scheduledTaskLocalService.getScheduledTaskByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the scheduled tasks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.data.integration.manager.model.impl.ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of scheduled tasks
	* @param end the upper bound of the range of scheduled tasks (not inclusive)
	* @return the range of scheduled tasks
	*/
	@Override
	public java.util.List<com.liferay.commerce.data.integration.manager.model.ScheduledTask> getScheduledTasks(
		int start, int end) {
		return _scheduledTaskLocalService.getScheduledTasks(start, end);
	}

	@Override
	public java.util.List<com.liferay.commerce.data.integration.manager.model.ScheduledTask> getScheduledTasksByActive(
		boolean active, int start, int end) {
		return _scheduledTaskLocalService.getScheduledTasksByActive(active,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.commerce.data.integration.manager.model.ScheduledTask> getScheduledTasksByEnabled(
		boolean enabled, int start, int end) {
		return _scheduledTaskLocalService.getScheduledTasksByEnabled(enabled,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.commerce.data.integration.manager.model.ScheduledTask> getScheduledTasksByGroupId_Active(
		long groupId, boolean active, int start, int end) {
		return _scheduledTaskLocalService.getScheduledTasksByGroupId_Active(groupId,
			active, start, end);
	}

	@Override
	public java.util.List<com.liferay.commerce.data.integration.manager.model.ScheduledTask> getScheduledTasksByGroupId_Enabled(
		long groupId, boolean enabled, int start, int end) {
		return _scheduledTaskLocalService.getScheduledTasksByGroupId_Enabled(groupId,
			enabled, start, end);
	}

	/**
	* Returns all the scheduled tasks matching the UUID and company.
	*
	* @param uuid the UUID of the scheduled tasks
	* @param companyId the primary key of the company
	* @return the matching scheduled tasks, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.commerce.data.integration.manager.model.ScheduledTask> getScheduledTasksByUuidAndCompanyId(
		String uuid, long companyId) {
		return _scheduledTaskLocalService.getScheduledTasksByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of scheduled tasks matching the UUID and company.
	*
	* @param uuid the UUID of the scheduled tasks
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of scheduled tasks
	* @param end the upper bound of the range of scheduled tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching scheduled tasks, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.commerce.data.integration.manager.model.ScheduledTask> getScheduledTasksByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.data.integration.manager.model.ScheduledTask> orderByComparator) {
		return _scheduledTaskLocalService.getScheduledTasksByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of scheduled tasks.
	*
	* @return the number of scheduled tasks
	*/
	@Override
	public int getScheduledTasksCount() {
		return _scheduledTaskLocalService.getScheduledTasksCount();
	}

	@Override
	public com.liferay.commerce.data.integration.manager.model.ScheduledTask startScheduledTask(
		long userId, long scheduledTaskId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _scheduledTaskLocalService.startScheduledTask(userId,
			scheduledTaskId);
	}

	@Override
	public com.liferay.commerce.data.integration.manager.model.ScheduledTask stopScheduledTask(
		long userId, long scheduledTaskId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _scheduledTaskLocalService.stopScheduledTask(userId,
			scheduledTaskId);
	}

	@Override
	public com.liferay.commerce.data.integration.manager.model.ScheduledTask updateScheduledTask(
		long scheduledTaskId, long processId, String frequency,
		java.util.Date startDate, String startHour, String name,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _scheduledTaskLocalService.updateScheduledTask(scheduledTaskId,
			processId, frequency, startDate, startHour, name, serviceContext);
	}

	/**
	* Updates the scheduled task in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param scheduledTask the scheduled task
	* @return the scheduled task that was updated
	*/
	@Override
	public com.liferay.commerce.data.integration.manager.model.ScheduledTask updateScheduledTask(
		com.liferay.commerce.data.integration.manager.model.ScheduledTask scheduledTask) {
		return _scheduledTaskLocalService.updateScheduledTask(scheduledTask);
	}

	@Override
	public ScheduledTaskLocalService getWrappedService() {
		return _scheduledTaskLocalService;
	}

	@Override
	public void setWrappedService(
		ScheduledTaskLocalService scheduledTaskLocalService) {
		_scheduledTaskLocalService = scheduledTaskLocalService;
	}

	private ScheduledTaskLocalService _scheduledTaskLocalService;
}