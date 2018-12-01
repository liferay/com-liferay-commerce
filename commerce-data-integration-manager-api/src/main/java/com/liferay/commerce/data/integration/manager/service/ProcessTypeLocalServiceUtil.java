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

import com.liferay.commerce.data.integration.manager.model.ProcessType;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for ProcessType. This utility wraps
 * {@link com.liferay.data.integration.service.impl.ProcessTypeLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ProcessTypeLocalService
 * @see com.liferay.data.integration.service.base.ProcessTypeLocalServiceBaseImpl
 * @see com.liferay.data.integration.service.impl.ProcessTypeLocalServiceImpl
 * @generated
 */
@ProviderType
public class ProcessTypeLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.data.integration.service.impl.ProcessTypeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the process type to the database. Also notifies the appropriate model listeners.
	*
	* @param processType the process type
	* @return the process type that was added
	*/
	public static ProcessType addProcessType(
		ProcessType processType) {
		return getService().addProcessType(processType);
	}

	/**
	* Creates a new process type with the primary key. Does not add the process type to the database.
	*
	* @param processTypeId the primary key for the new process type
	* @return the new process type
	*/
	public static ProcessType createProcessType(
		long processTypeId) {
		return getService().createProcessType(processTypeId);
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
	* Deletes the process type with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param processTypeId the primary key of the process type
	* @return the process type that was removed
	* @throws PortalException if a process type with the primary key could not be found
	*/
	public static ProcessType deleteProcessType(
		long processTypeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteProcessType(processTypeId);
	}

	/**
	* Deletes the process type from the database. Also notifies the appropriate model listeners.
	*
	* @param processType the process type
	* @return the process type that was removed
	*/
	public static ProcessType deleteProcessType(
		ProcessType processType) {
		return getService().deleteProcessType(processType);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.data.integration.model.impl.ProcessTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.data.integration.model.impl.ProcessTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static ProcessType fetchProcessType(
		long processTypeId) {
		return getService().fetchProcessType(processTypeId);
	}

	/**
	* Returns the process type matching the UUID and group.
	*
	* @param uuid the process type's UUID
	* @param groupId the primary key of the group
	* @return the matching process type, or <code>null</code> if a matching process type could not be found
	*/
	public static ProcessType fetchProcessTypeByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchProcessTypeByUuidAndGroupId(uuid, groupId);
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
	* Returns the process type with the primary key.
	*
	* @param processTypeId the primary key of the process type
	* @return the process type
	* @throws PortalException if a process type with the primary key could not be found
	*/
	public static ProcessType getProcessType(
		long processTypeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getProcessType(processTypeId);
	}

	/**
	* Returns the process type matching the UUID and group.
	*
	* @param uuid the process type's UUID
	* @param groupId the primary key of the group
	* @return the matching process type
	* @throws PortalException if a matching process type could not be found
	*/
	public static ProcessType getProcessTypeByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getProcessTypeByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the process types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.data.integration.model.impl.ProcessTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of process types
	* @param end the upper bound of the range of process types (not inclusive)
	* @return the range of process types
	*/
	public static java.util.List<ProcessType> getProcessTypes(
		int start, int end) {
		return getService().getProcessTypes(start, end);
	}

	/**
	* Returns all the process types matching the UUID and company.
	*
	* @param uuid the UUID of the process types
	* @param companyId the primary key of the company
	* @return the matching process types, or an empty list if no matches were found
	*/
	public static java.util.List<ProcessType> getProcessTypesByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getProcessTypesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of process types matching the UUID and company.
	*
	* @param uuid the UUID of the process types
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of process types
	* @param end the upper bound of the range of process types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching process types, or an empty list if no matches were found
	*/
	public static java.util.List<ProcessType> getProcessTypesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessType> orderByComparator) {
		return getService()
				   .getProcessTypesByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of process types.
	*
	* @return the number of process types
	*/
	public static int getProcessTypesCount() {
		return getService().getProcessTypesCount();
	}

	/**
	* Updates the process type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param processType the process type
	* @return the process type that was updated
	*/
	public static ProcessType updateProcessType(
		ProcessType processType) {
		return getService().updateProcessType(processType);
	}

	public static ProcessTypeLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ProcessTypeLocalService, ProcessTypeLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ProcessTypeLocalService.class);

		ServiceTracker<ProcessTypeLocalService, ProcessTypeLocalService> serviceTracker =
			new ServiceTracker<ProcessTypeLocalService, ProcessTypeLocalService>(bundle.getBundleContext(),
				ProcessTypeLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}