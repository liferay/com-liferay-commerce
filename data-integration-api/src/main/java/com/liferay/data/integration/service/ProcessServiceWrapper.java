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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProcessService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProcessService
 * @generated
 */
@ProviderType
public class ProcessServiceWrapper implements ProcessService,
	ServiceWrapper<ProcessService> {
	public ProcessServiceWrapper(ProcessService processService) {
		_processService = processService;
	}

	/**
	* NOTE FOR DEVELOPERS:
	*
	* Never reference this class directly. Always use {@link ProcessServiceUtil} to access the process remote service.
	*
	* @throws PortalException
	*/
	@Override
	public com.liferay.data.integration.model.Process addProcess(String name,
		String className, String processType, String version,
		long contextPropertiesFileEntryId, long srcArchiveFileEntryId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processService.addProcess(name, className, processType,
			version, contextPropertiesFileEntryId, srcArchiveFileEntryId,
			serviceContext);
	}

	@Override
	public com.liferay.data.integration.model.Process deleteProcess(
		long userId, long processId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processService.deleteProcess(userId, processId, serviceContext);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _processService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.data.integration.model.Process getProcess(long userId,
		long processId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processService.getProcess(userId, processId);
	}

	@Override
	public java.util.List<com.liferay.data.integration.model.Process> getProcessesByGroupId(
		long userId, long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processService.getProcessesByGroupId(userId, groupId, start, end);
	}

	@Override
	public int getProcessesByGroupIdCount(long userId, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processService.getProcessesByGroupIdCount(userId, groupId);
	}

	@Override
	public com.liferay.data.integration.model.Process updateProcess(
		long processId, String name, String className, String processType,
		String version, long contextPropertiesFileEntryId,
		long srcArchiveFileEntryId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processService.updateProcess(processId, name, className,
			processType, version, contextPropertiesFileEntryId,
			srcArchiveFileEntryId, serviceContext);
	}

	@Override
	public ProcessService getWrappedService() {
		return _processService;
	}

	@Override
	public void setWrappedService(ProcessService processService) {
		_processService = processService;
	}

	private ProcessService _processService;
}