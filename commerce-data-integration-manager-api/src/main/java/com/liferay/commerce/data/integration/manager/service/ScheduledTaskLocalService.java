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

import com.liferay.commerce.data.integration.manager.model.ScheduledTask;

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

/**
 * Provides the local service interface for ScheduledTask. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see ScheduledTaskLocalServiceUtil
 * @see com.liferay.data.integration.service.base.ScheduledTaskLocalServiceBaseImpl
 * @see com.liferay.data.integration.service.impl.ScheduledTaskLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ScheduledTaskLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ScheduledTaskLocalServiceUtil} to access the scheduled task local service. Add custom service methods to {@link com.liferay.data.integration.service.impl.ScheduledTaskLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* NOTE FOR DEVELOPERS:
	*
	* Never reference this class directly. Always use {@link ScheduledTaskLocalServiceUtil} to access the scheduled task local service.
	*
	* @throws PortalException
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ScheduledTask addScheduledTask(long processId, String frequency,
										  Date startDate, String startHour, String name,
										  ServiceContext serviceContext) throws PortalException;

	/**
	* Adds the scheduled task to the database. Also notifies the appropriate model listeners.
	*
	* @param scheduledTask the scheduled task
	* @return the scheduled task that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ScheduledTask addScheduledTask(ScheduledTask scheduledTask);

	public int countScheduledTasksByActive(boolean active);

	public int countScheduledTasksByActive(long groupId, boolean active);

	public int countScheduledTasksByEnabled(boolean enabled);

	public int countScheduledTasksByGroupId_Enabled(long groupId,
		boolean enabled);

	/**
	* Creates a new scheduled task with the primary key. Does not add the scheduled task to the database.
	*
	* @param scheduledTaskId the primary key for the new scheduled task
	* @return the new scheduled task
	*/
	@Transactional(enabled = false)
	public ScheduledTask createScheduledTask(long scheduledTaskId);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	* Deletes the scheduled task with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param scheduledTaskId the primary key of the scheduled task
	* @return the scheduled task that was removed
	* @throws PortalException if a scheduled task with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public ScheduledTask deleteScheduledTask(long scheduledTaskId)
		throws PortalException;

	/**
	* Deletes the scheduled task from the database. Also notifies the appropriate model listeners.
	*
	* @param scheduledTask the scheduled task
	* @return the scheduled task that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public ScheduledTask deleteScheduledTask(ScheduledTask scheduledTask);

	@Indexable(type = IndexableType.REINDEX)
	public ScheduledTask disableScheduledTask(long scheduledTaskId, long userId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.data.integration.model.impl.ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.data.integration.model.impl.ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	@Indexable(type = IndexableType.REINDEX)
	public ScheduledTask enableScheduledTask(long scheduledTaskId, long userId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ScheduledTask fetchScheduledTask(long scheduledTaskId);

	/**
	* Returns the scheduled task matching the UUID and group.
	*
	* @param uuid the scheduled task's UUID
	* @param groupId the primary key of the group
	* @return the matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ScheduledTask fetchScheduledTaskByUuidAndGroupId(String uuid,
		long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Returns the scheduled task with the primary key.
	*
	* @param scheduledTaskId the primary key of the scheduled task
	* @return the scheduled task
	* @throws PortalException if a scheduled task with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ScheduledTask getScheduledTask(long scheduledTaskId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ScheduledTask> getScheduledTaskByGroupId(long groupId,
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getScheduledTaskByGroupIdCount(long groupId);

	/**
	* Returns the scheduled task matching the UUID and group.
	*
	* @param uuid the scheduled task's UUID
	* @param groupId the primary key of the group
	* @return the matching scheduled task
	* @throws PortalException if a matching scheduled task could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ScheduledTask getScheduledTaskByUuidAndGroupId(String uuid,
		long groupId) throws PortalException;

	/**
	* Returns a range of all the scheduled tasks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.data.integration.model.impl.ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of scheduled tasks
	* @param end the upper bound of the range of scheduled tasks (not inclusive)
	* @return the range of scheduled tasks
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ScheduledTask> getScheduledTasks(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ScheduledTask> getScheduledTasksByActive(boolean active,
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ScheduledTask> getScheduledTasksByEnabled(boolean enabled,
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ScheduledTask> getScheduledTasksByGroupId_Active(long groupId,
		boolean active, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ScheduledTask> getScheduledTasksByGroupId_Enabled(
		long groupId, boolean enabled, int start, int end);

	/**
	* Returns all the scheduled tasks matching the UUID and company.
	*
	* @param uuid the UUID of the scheduled tasks
	* @param companyId the primary key of the company
	* @return the matching scheduled tasks, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ScheduledTask> getScheduledTasksByUuidAndCompanyId(
		String uuid, long companyId);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ScheduledTask> getScheduledTasksByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ScheduledTask> orderByComparator);

	/**
	* Returns the number of scheduled tasks.
	*
	* @return the number of scheduled tasks
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getScheduledTasksCount();

	@Indexable(type = IndexableType.REINDEX)
	public ScheduledTask startScheduledTask(long userId, long scheduledTaskId)
		throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public ScheduledTask stopScheduledTask(long userId, long scheduledTaskId)
		throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public ScheduledTask updateScheduledTask(long scheduledTaskId,
		long processId, String frequency, Date startDate, String startHour,
		String name, ServiceContext serviceContext) throws PortalException;

	/**
	* Updates the scheduled task in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param scheduledTask the scheduled task
	* @return the scheduled task that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ScheduledTask updateScheduledTask(ScheduledTask scheduledTask);
}