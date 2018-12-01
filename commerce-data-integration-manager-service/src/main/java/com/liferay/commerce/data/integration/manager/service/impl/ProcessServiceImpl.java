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

package com.liferay.commerce.data.integration.manager.service.impl;

import com.liferay.commerce.data.integration.manager.constants.ProcessActionKeys;
import com.liferay.commerce.data.integration.manager.model.Process;
import com.liferay.commerce.data.integration.manager.model.ProcessConstants;
import com.liferay.commerce.data.integration.manager.service.ProcessService;
import com.liferay.commerce.data.integration.manager.service.ProcessServiceUtil;
import com.liferay.commerce.data.integration.manager.service.base.ProcessServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.List;

/**
 * The implementation of the process remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link ProcessService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProcessServiceBaseImpl
 * @see ProcessServiceUtil
 */
public class ProcessServiceImpl extends ProcessServiceBaseImpl {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link ProcessServiceUtil} to access the process remote service.
	 * @throws PortalException
	 */
	public Process addProcess(
			String name, String className, String processType, String version,
			long contextPropertiesFileEntryId, long srcArchiveFileEntryId,
			ServiceContext serviceContext)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			ProcessActionKeys.MANAGE_PROCESS);

		return processLocalService.addProcess(
			name, className, processType, version, contextPropertiesFileEntryId,
			srcArchiveFileEntryId, serviceContext);
	}

	public Process deleteProcess(
			long userId, long processId, ServiceContext serviceContext)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			ProcessActionKeys.MANAGE_PROCESS);

		return processLocalService.deleteProcess(processId);
	}

	public Process getProcess(long userId, long processId)
		throws PortalException {

		return processLocalService.getProcess(processId);
	}

	public List<Process> getProcessesByGroupId(
			long userId, long groupId, int start, int end)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId, ProcessActionKeys.MANAGE_PROCESS);

		return processLocalService.getProcessesByGroupId(groupId, start, end);
	}

	public int getProcessesByGroupIdCount(long userId, long groupId)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId, ProcessActionKeys.MANAGE_PROCESS);

		return processLocalService.getProcessesByGroupIdCount(groupId);
	}

	public Process updateProcess(
			long processId, String name, String className, String processType,
			String version, long contextPropertiesFileEntryId,
			long srcArchiveFileEntryId, ServiceContext serviceContext)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			ProcessActionKeys.MANAGE_PROCESS);

		return processLocalService.updateProcess(
			processId, name, className, processType, version,
			contextPropertiesFileEntryId, srcArchiveFileEntryId,
			serviceContext);
	}

	private static volatile PortletResourcePermission
		_portletResourcePermission =
			PortletResourcePermissionFactory.getInstance(
				ProcessServiceImpl.class, "_portletResourcePermission",
				ProcessConstants.RESOURCE_NAME);

}