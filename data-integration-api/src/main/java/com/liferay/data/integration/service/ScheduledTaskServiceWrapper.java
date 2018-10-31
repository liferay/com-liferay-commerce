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
 * Provides a wrapper for {@link ScheduledTaskService}.
 *
 * @author Brian Wing Shun Chan
 * @see ScheduledTaskService
 * @generated
 */
@ProviderType
public class ScheduledTaskServiceWrapper implements ScheduledTaskService,
	ServiceWrapper<ScheduledTaskService> {
	public ScheduledTaskServiceWrapper(
		ScheduledTaskService scheduledTaskService) {
		_scheduledTaskService = scheduledTaskService;
	}

	/**
	* NOTE FOR DEVELOPERS:
	*
	* Never reference this class directly. Always use {@link ScheduledTaskServiceUtil} to access the scheduled task remote service.
	*/
	@Override
	public com.liferay.data.integration.model.ScheduledTask addScheduledTask(
		long processId, String frequency, java.util.Date startDate,
		String startHour, String name,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _scheduledTaskService.addScheduledTask(processId, frequency,
			startDate, startHour, name, serviceContext);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _scheduledTaskService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.data.integration.model.ScheduledTask> getScheduledTaskByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.security.auth.PrincipalException {
		return _scheduledTaskService.getScheduledTaskByGroupId(groupId, start,
			end);
	}

	@Override
	public int getScheduledTaskByGroupIdCount(long groupId)
		throws com.liferay.portal.kernel.security.auth.PrincipalException {
		return _scheduledTaskService.getScheduledTaskByGroupIdCount(groupId);
	}

	@Override
	public com.liferay.data.integration.model.ScheduledTask updateScheduledTask(
		long scheduledTaskId, long processId, String frequency,
		java.util.Date startDate, String startHour, String name,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _scheduledTaskService.updateScheduledTask(scheduledTaskId,
			processId, frequency, startDate, startHour, name, serviceContext);
	}

	@Override
	public ScheduledTaskService getWrappedService() {
		return _scheduledTaskService;
	}

	@Override
	public void setWrappedService(ScheduledTaskService scheduledTaskService) {
		_scheduledTaskService = scheduledTaskService;
	}

	private ScheduledTaskService _scheduledTaskService;
}