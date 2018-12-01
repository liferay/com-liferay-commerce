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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for ScheduledTask. This utility wraps
 * {@link com.liferay.commerce.data.integration.manager.service.impl.ScheduledTaskLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ScheduledTaskLocalService
 * @see com.liferay.commerce.data.integration.manager.service.base.ScheduledTaskLocalServiceBaseImpl
 * @see com.liferay.commerce.data.integration.manager.service.impl.ScheduledTaskLocalServiceImpl
 * @generated
 */
@ProviderType
public class ScheduledTaskLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.commerce.data.integration.manager.service.impl.ScheduledTaskLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* NOTE FOR DEVELOPERS:
	*
	* Never reference this class directly. Always use {@link ScheduledTaskLocalServiceUtil} to access the scheduled task local service.
	*
	* @throws PortalException
	*/
	public static com.liferay.commerce.data.integration.manager.model.ScheduledTask addScheduledTask(
		long processId, String frequency, java.util.Date startDate,
		String startHour, String name,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addScheduledTask(processId, frequency, startDate,
			startHour, name, serviceContext);
	}

	/**
	* Adds the scheduled task to the database. Also notifies the appropriate model listeners.
	*
	* @param scheduledTask the scheduled task
	* @return the scheduled task that was added
	*/
	public static com.liferay.commerce.data.integration.manager.model.ScheduledTask addScheduledTask(
		com.liferay.commerce.data.integration.manager.model.ScheduledTask scheduledTask) {
		return getService().addScheduledTask(scheduledTask);
	}

	public static int countScheduledTasksByActive(boolean active) {
		return getService().countScheduledTasksByActive(active);
	}

	public static int countScheduledTasksByActive(long groupId, boolean active) {
		return getService().countScheduledTasksByActive(groupId, active);
	}

	public static int countScheduledTasksByEnabled(boolean enabled) {
		return getService().countScheduledTasksByEnabled(enabled);
	}

	public static int countScheduledTasksByGroupId_Enabled(long groupId,
		boolean enabled) {
		return getService()
				   .countScheduledTasksByGroupId_Enabled(groupId, enabled);
	}

	/**
	* Creates a new scheduled task with the primary key. Does not add the scheduled task to the database.
	*
	* @param scheduledTaskId the primary key for the new scheduled task
	* @return the new scheduled task
	*/
	public static com.liferay.commerce.data.integration.manager.model.ScheduledTask createScheduledTask(
		long scheduledTaskId) {
		return getService().createScheduledTask(scheduledTaskId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the scheduled task with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param scheduledTaskId the primary key of the scheduled task
	* @return the scheduled task that was removed
	* @throws PortalException if a scheduled task with the primary key could not be found
	*/
	public static com.liferay.commerce.data.integration.manager.model.ScheduledTask deleteScheduledTask(
		long scheduledTaskId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteScheduledTask(scheduledTaskId);
	}

	/**
	* Deletes the scheduled task from the database. Also notifies the appropriate model listeners.
	*
	* @param scheduledTask the scheduled task
	* @return the scheduled task that was removed
	*/
	public static com.liferay.commerce.data.integration.manager.model.ScheduledTask deleteScheduledTask(
		com.liferay.commerce.data.integration.manager.model.ScheduledTask scheduledTask) {
		return getService().deleteScheduledTask(scheduledTask);
	}

	public static com.liferay.commerce.data.integration.manager.model.ScheduledTask disableScheduledTask(
		long scheduledTaskId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().disableScheduledTask(scheduledTaskId, userId);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.data.integration.manager.model.impl.ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.data.integration.manager.model.impl.ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.commerce.data.integration.manager.model.ScheduledTask enableScheduledTask(
		long scheduledTaskId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().enableScheduledTask(scheduledTaskId, userId);
	}

	public static com.liferay.commerce.data.integration.manager.model.ScheduledTask fetchScheduledTask(
		long scheduledTaskId) {
		return getService().fetchScheduledTask(scheduledTaskId);
	}

	/**
	* Returns the scheduled task matching the UUID and group.
	*
	* @param uuid the scheduled task's UUID
	* @param groupId the primary key of the group
	* @return the matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	*/
	public static com.liferay.commerce.data.integration.manager.model.ScheduledTask fetchScheduledTaskByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchScheduledTaskByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
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
	* Returns the scheduled task with the primary key.
	*
	* @param scheduledTaskId the primary key of the scheduled task
	* @return the scheduled task
	* @throws PortalException if a scheduled task with the primary key could not be found
	*/
	public static com.liferay.commerce.data.integration.manager.model.ScheduledTask getScheduledTask(
		long scheduledTaskId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getScheduledTask(scheduledTaskId);
	}

	public static java.util.List<com.liferay.commerce.data.integration.manager.model.ScheduledTask> getScheduledTaskByGroupId(
		long groupId, int start, int end) {
		return getService().getScheduledTaskByGroupId(groupId, start, end);
	}

	public static int getScheduledTaskByGroupIdCount(long groupId) {
		return getService().getScheduledTaskByGroupIdCount(groupId);
	}

	/**
	* Returns the scheduled task matching the UUID and group.
	*
	* @param uuid the scheduled task's UUID
	* @param groupId the primary key of the group
	* @return the matching scheduled task
	* @throws PortalException if a matching scheduled task could not be found
	*/
	public static com.liferay.commerce.data.integration.manager.model.ScheduledTask getScheduledTaskByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getScheduledTaskByUuidAndGroupId(uuid, groupId);
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
	public static java.util.List<com.liferay.commerce.data.integration.manager.model.ScheduledTask> getScheduledTasks(
		int start, int end) {
		return getService().getScheduledTasks(start, end);
	}

	public static java.util.List<com.liferay.commerce.data.integration.manager.model.ScheduledTask> getScheduledTasksByActive(
		boolean active, int start, int end) {
		return getService().getScheduledTasksByActive(active, start, end);
	}

	public static java.util.List<com.liferay.commerce.data.integration.manager.model.ScheduledTask> getScheduledTasksByEnabled(
		boolean enabled, int start, int end) {
		return getService().getScheduledTasksByEnabled(enabled, start, end);
	}

	public static java.util.List<com.liferay.commerce.data.integration.manager.model.ScheduledTask> getScheduledTasksByGroupId_Active(
		long groupId, boolean active, int start, int end) {
		return getService()
				   .getScheduledTasksByGroupId_Active(groupId, active, start,
			end);
	}

	public static java.util.List<com.liferay.commerce.data.integration.manager.model.ScheduledTask> getScheduledTasksByGroupId_Enabled(
		long groupId, boolean enabled, int start, int end) {
		return getService()
				   .getScheduledTasksByGroupId_Enabled(groupId, enabled, start,
			end);
	}

	/**
	* Returns all the scheduled tasks matching the UUID and company.
	*
	* @param uuid the UUID of the scheduled tasks
	* @param companyId the primary key of the company
	* @return the matching scheduled tasks, or an empty list if no matches were found
	*/
	public static java.util.List<com.liferay.commerce.data.integration.manager.model.ScheduledTask> getScheduledTasksByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getScheduledTasksByUuidAndCompanyId(uuid, companyId);
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
	public static java.util.List<com.liferay.commerce.data.integration.manager.model.ScheduledTask> getScheduledTasksByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.data.integration.manager.model.ScheduledTask> orderByComparator) {
		return getService()
				   .getScheduledTasksByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of scheduled tasks.
	*
	* @return the number of scheduled tasks
	*/
	public static int getScheduledTasksCount() {
		return getService().getScheduledTasksCount();
	}

	public static com.liferay.commerce.data.integration.manager.model.ScheduledTask startScheduledTask(
		long userId, long scheduledTaskId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().startScheduledTask(userId, scheduledTaskId);
	}

	public static com.liferay.commerce.data.integration.manager.model.ScheduledTask stopScheduledTask(
		long userId, long scheduledTaskId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().stopScheduledTask(userId, scheduledTaskId);
	}

	public static com.liferay.commerce.data.integration.manager.model.ScheduledTask updateScheduledTask(
		long scheduledTaskId, long processId, String frequency,
		java.util.Date startDate, String startHour, String name,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateScheduledTask(scheduledTaskId, processId, frequency,
			startDate, startHour, name, serviceContext);
	}

	/**
	* Updates the scheduled task in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param scheduledTask the scheduled task
	* @return the scheduled task that was updated
	*/
	public static com.liferay.commerce.data.integration.manager.model.ScheduledTask updateScheduledTask(
		com.liferay.commerce.data.integration.manager.model.ScheduledTask scheduledTask) {
		return getService().updateScheduledTask(scheduledTask);
	}

	public static ScheduledTaskLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ScheduledTaskLocalService, ScheduledTaskLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ScheduledTaskLocalService.class);

		ServiceTracker<ScheduledTaskLocalService, ScheduledTaskLocalService> serviceTracker =
			new ServiceTracker<ScheduledTaskLocalService, ScheduledTaskLocalService>(bundle.getBundleContext(),
				ScheduledTaskLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}