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
 * Provides the remote service utility for Process. This utility wraps
 * {@link com.liferay.commerce.data.integration.manager.service.impl.ProcessServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ProcessService
 * @see com.liferay.commerce.data.integration.manager.service.base.ProcessServiceBaseImpl
 * @see com.liferay.commerce.data.integration.manager.service.impl.ProcessServiceImpl
 * @generated
 */
@ProviderType
public class ProcessServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.commerce.data.integration.manager.service.impl.ProcessServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* NOTE FOR DEVELOPERS:
	*
	* Never reference this class directly. Always use {@link ProcessServiceUtil} to access the process remote service.
	*
	* @throws PortalException
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

	public static com.liferay.commerce.data.integration.manager.model.Process deleteProcess(
		long userId, long processId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteProcess(userId, processId, serviceContext);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.commerce.data.integration.manager.model.Process getProcess(
		long userId, long processId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getProcess(userId, processId);
	}

	public static java.util.List<com.liferay.commerce.data.integration.manager.model.Process> getProcessesByGroupId(
		long userId, long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getProcessesByGroupId(userId, groupId, start, end);
	}

	public static int getProcessesByGroupIdCount(long userId, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getProcessesByGroupIdCount(userId, groupId);
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

	public static ProcessService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ProcessService, ProcessService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ProcessService.class);

		ServiceTracker<ProcessService, ProcessService> serviceTracker = new ServiceTracker<ProcessService, ProcessService>(bundle.getBundleContext(),
				ProcessService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}