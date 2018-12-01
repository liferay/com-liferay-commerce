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
import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProcessTypeLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProcessTypeLocalService
 * @generated
 */
@ProviderType
public class ProcessTypeLocalServiceWrapper implements ProcessTypeLocalService,
	ServiceWrapper<ProcessTypeLocalService> {
	public ProcessTypeLocalServiceWrapper(
		ProcessTypeLocalService processTypeLocalService) {
		_processTypeLocalService = processTypeLocalService;
	}

	/**
	* Adds the process type to the database. Also notifies the appropriate model listeners.
	*
	* @param processType the process type
	* @return the process type that was added
	*/
	@Override
	public ProcessType addProcessType(
		ProcessType processType) {
		return _processTypeLocalService.addProcessType(processType);
	}

	/**
	* Creates a new process type with the primary key. Does not add the process type to the database.
	*
	* @param processTypeId the primary key for the new process type
	* @return the new process type
	*/
	@Override
	public ProcessType createProcessType(
		long processTypeId) {
		return _processTypeLocalService.createProcessType(processTypeId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processTypeLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the process type with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param processTypeId the primary key of the process type
	* @return the process type that was removed
	* @throws PortalException if a process type with the primary key could not be found
	*/
	@Override
	public ProcessType deleteProcessType(
		long processTypeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processTypeLocalService.deleteProcessType(processTypeId);
	}

	/**
	* Deletes the process type from the database. Also notifies the appropriate model listeners.
	*
	* @param processType the process type
	* @return the process type that was removed
	*/
	@Override
	public ProcessType deleteProcessType(
		ProcessType processType) {
		return _processTypeLocalService.deleteProcessType(processType);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _processTypeLocalService.dynamicQuery();
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
		return _processTypeLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _processTypeLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _processTypeLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _processTypeLocalService.dynamicQueryCount(dynamicQuery);
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
		return _processTypeLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public ProcessType fetchProcessType(
		long processTypeId) {
		return _processTypeLocalService.fetchProcessType(processTypeId);
	}

	/**
	* Returns the process type matching the UUID and group.
	*
	* @param uuid the process type's UUID
	* @param groupId the primary key of the group
	* @return the matching process type, or <code>null</code> if a matching process type could not be found
	*/
	@Override
	public ProcessType fetchProcessTypeByUuidAndGroupId(
		String uuid, long groupId) {
		return _processTypeLocalService.fetchProcessTypeByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _processTypeLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _processTypeLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _processTypeLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _processTypeLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processTypeLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the process type with the primary key.
	*
	* @param processTypeId the primary key of the process type
	* @return the process type
	* @throws PortalException if a process type with the primary key could not be found
	*/
	@Override
	public ProcessType getProcessType(
		long processTypeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processTypeLocalService.getProcessType(processTypeId);
	}

	/**
	* Returns the process type matching the UUID and group.
	*
	* @param uuid the process type's UUID
	* @param groupId the primary key of the group
	* @return the matching process type
	* @throws PortalException if a matching process type could not be found
	*/
	@Override
	public ProcessType getProcessTypeByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processTypeLocalService.getProcessTypeByUuidAndGroupId(uuid,
			groupId);
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
	@Override
	public java.util.List<ProcessType> getProcessTypes(
		int start, int end) {
		return _processTypeLocalService.getProcessTypes(start, end);
	}

	/**
	* Returns all the process types matching the UUID and company.
	*
	* @param uuid the UUID of the process types
	* @param companyId the primary key of the company
	* @return the matching process types, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<ProcessType> getProcessTypesByUuidAndCompanyId(
		String uuid, long companyId) {
		return _processTypeLocalService.getProcessTypesByUuidAndCompanyId(uuid,
			companyId);
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
	@Override
	public java.util.List<ProcessType> getProcessTypesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessType> orderByComparator) {
		return _processTypeLocalService.getProcessTypesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of process types.
	*
	* @return the number of process types
	*/
	@Override
	public int getProcessTypesCount() {
		return _processTypeLocalService.getProcessTypesCount();
	}

	/**
	* Updates the process type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param processType the process type
	* @return the process type that was updated
	*/
	@Override
	public ProcessType updateProcessType(
		ProcessType processType) {
		return _processTypeLocalService.updateProcessType(processType);
	}

	@Override
	public ProcessTypeLocalService getWrappedService() {
		return _processTypeLocalService;
	}

	@Override
	public void setWrappedService(
		ProcessTypeLocalService processTypeLocalService) {
		_processTypeLocalService = processTypeLocalService;
	}

	private ProcessTypeLocalService _processTypeLocalService;
}