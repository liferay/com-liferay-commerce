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
 * Provides the local service utility for Process. This utility wraps
 * {@link com.liferay.commerce.data.integration.manager.service.impl.ProcessLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ProcessLocalService
 * @see com.liferay.commerce.data.integration.manager.service.base.ProcessLocalServiceBaseImpl
 * @see com.liferay.commerce.data.integration.manager.service.impl.ProcessLocalServiceImpl
 * @generated
 */
@ProviderType
public class ProcessLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.commerce.data.integration.manager.service.impl.ProcessLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the process to the database. Also notifies the appropriate model listeners.
	*
	* @param process the process
	* @return the process that was added
	*/
	public static com.liferay.commerce.data.integration.manager.model.Process addProcess(
		com.liferay.commerce.data.integration.manager.model.Process process) {
		return getService().addProcess(process);
	}

	/**
	* NOTE FOR DEVELOPERS:
	*
	* Never reference this class directly. Always use {@link ProcessLocalServiceUtil} to access the process local service.
	*/
	public static com.liferay.commerce.data.integration.manager.model.Process addProcess(
		String name, String className, String processType, String version,
		long contextPropertiesFileEntryId, long srcArchiveFileEntryId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addProcess(name, className, processType, version,
			contextPropertiesFileEntryId, srcArchiveFileEntryId, serviceContext);
	}

	/**
	* Creates a new process with the primary key. Does not add the process to the database.
	*
	* @param processId the primary key for the new process
	* @return the new process
	*/
	public static com.liferay.commerce.data.integration.manager.model.Process createProcess(
		long processId) {
		return getService().createProcess(processId);
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
	* Deletes the process with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param processId the primary key of the process
	* @return the process that was removed
	* @throws PortalException if a process with the primary key could not be found
	*/
	public static com.liferay.commerce.data.integration.manager.model.Process deleteProcess(
		long processId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteProcess(processId);
	}

	/**
	* Deletes the process from the database. Also notifies the appropriate model listeners.
	*
	* @param process the process
	* @return the process that was removed
	*/
	public static com.liferay.commerce.data.integration.manager.model.Process deleteProcess(
		com.liferay.commerce.data.integration.manager.model.Process process) {
		return getService().deleteProcess(process);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.data.integration.manager.model.impl.ProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.data.integration.manager.model.impl.ProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.commerce.data.integration.manager.model.Process fetchProcess(
		long processId) {
		return getService().fetchProcess(processId);
	}

	/**
	* Returns the process matching the UUID and group.
	*
	* @param uuid the process's UUID
	* @param groupId the primary key of the group
	* @return the matching process, or <code>null</code> if a matching process could not be found
	*/
	public static com.liferay.commerce.data.integration.manager.model.Process fetchProcessByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchProcessByUuidAndGroupId(uuid, groupId);
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
	* Returns the process with the primary key.
	*
	* @param processId the primary key of the process
	* @return the process
	* @throws PortalException if a process with the primary key could not be found
	*/
	public static com.liferay.commerce.data.integration.manager.model.Process getProcess(
		long processId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getProcess(processId);
	}

	/**
	* Returns the process matching the UUID and group.
	*
	* @param uuid the process's UUID
	* @param groupId the primary key of the group
	* @return the matching process
	* @throws PortalException if a matching process could not be found
	*/
	public static com.liferay.commerce.data.integration.manager.model.Process getProcessByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getProcessByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the processes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.data.integration.manager.model.impl.ProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of processes
	* @param end the upper bound of the range of processes (not inclusive)
	* @return the range of processes
	*/
	public static java.util.List<com.liferay.commerce.data.integration.manager.model.Process> getProcesses(
		int start, int end) {
		return getService().getProcesses(start, end);
	}

	public static java.util.List<com.liferay.commerce.data.integration.manager.model.Process> getProcessesByGroupId(
		long groupId, int start, int end) {
		return getService().getProcessesByGroupId(groupId, start, end);
	}

	public static int getProcessesByGroupIdCount(long groupId) {
		return getService().getProcessesByGroupIdCount(groupId);
	}

	/**
	* Returns all the processes matching the UUID and company.
	*
	* @param uuid the UUID of the processes
	* @param companyId the primary key of the company
	* @return the matching processes, or an empty list if no matches were found
	*/
	public static java.util.List<com.liferay.commerce.data.integration.manager.model.Process> getProcessesByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getProcessesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of processes matching the UUID and company.
	*
	* @param uuid the UUID of the processes
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of processes
	* @param end the upper bound of the range of processes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching processes, or an empty list if no matches were found
	*/
	public static java.util.List<com.liferay.commerce.data.integration.manager.model.Process> getProcessesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.data.integration.manager.model.Process> orderByComparator) {
		return getService()
				   .getProcessesByUuidAndCompanyId(uuid, companyId, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of processes.
	*
	* @return the number of processes
	*/
	public static int getProcessesCount() {
		return getService().getProcessesCount();
	}

	public static com.liferay.commerce.data.integration.manager.model.Process updateProcess(
		long processId, String name, String className, String processType,
		String version, long contextPropertiesFileEntryId,
		long srcArchiveFileEntryId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateProcess(processId, name, className, processType,
			version, contextPropertiesFileEntryId, srcArchiveFileEntryId,
			serviceContext);
	}

	/**
	* Updates the process in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param process the process
	* @return the process that was updated
	*/
	public static com.liferay.commerce.data.integration.manager.model.Process updateProcess(
		com.liferay.commerce.data.integration.manager.model.Process process) {
		return getService().updateProcess(process);
	}

	public static ProcessLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ProcessLocalService, ProcessLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ProcessLocalService.class);

		ServiceTracker<ProcessLocalService, ProcessLocalService> serviceTracker = new ServiceTracker<ProcessLocalService, ProcessLocalService>(bundle.getBundleContext(),
				ProcessLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}