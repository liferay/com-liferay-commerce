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

package com.liferay.data.integration.service.impl;

import com.liferay.data.integration.constants.ScheduledTaskActionKeys;
import com.liferay.data.integration.model.ProcessConstants;
import com.liferay.data.integration.model.ScheduledTask;
import com.liferay.data.integration.service.base.ScheduledTaskServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Date;
import java.util.List;

/**
 * The implementation of the scheduled task remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.data.integration.service.ScheduledTaskService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ScheduledTaskServiceBaseImpl
 * @see com.liferay.data.integration.service.ScheduledTaskServiceUtil
 */
public class ScheduledTaskServiceImpl extends ScheduledTaskServiceBaseImpl {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.liferay.data.integration.service.ScheduledTaskServiceUtil} to access the scheduled task remote service.
	 */
	public ScheduledTask addScheduledTask(
			long processId, String frequency, Date startDate, String startHour,
			String name, ServiceContext serviceContext)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			ScheduledTaskActionKeys.MANAGE_SCHEDULED_TASK);

		return scheduledTaskLocalService.addScheduledTask(
			processId, frequency, startDate, startHour, name, serviceContext);
	}

	public List<ScheduledTask> getScheduledTaskByGroupId(
			long groupId, int start, int end)
		throws PrincipalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			ScheduledTaskActionKeys.MANAGE_SCHEDULED_TASK);

		return scheduledTaskLocalService.getScheduledTaskByGroupId(
			groupId, start, end);
	}

	public int getScheduledTaskByGroupIdCount(long groupId)
		throws PrincipalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			ScheduledTaskActionKeys.MANAGE_SCHEDULED_TASK);

		return scheduledTaskLocalService.getScheduledTaskByGroupIdCount(
			groupId);
	}

	public ScheduledTask updateScheduledTask(
			long scheduledTaskId, long processId, String frequency,
			Date startDate, String startHour, String name,
			ServiceContext serviceContext)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			ScheduledTaskActionKeys.MANAGE_SCHEDULED_TASK);

		return scheduledTaskLocalService.updateScheduledTask(
			scheduledTaskId, processId, frequency, startDate, startHour, name,
			serviceContext);
	}

	private static volatile PortletResourcePermission
		_portletResourcePermission =
			PortletResourcePermissionFactory.getInstance(
				ProcessServiceImpl.class, "_portletResourcePermission",
				ProcessConstants.RESOURCE_NAME);

}