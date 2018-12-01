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

package com.liferay.commerce.data.integration.manager.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.data.integration.manager.model.ScheduledTask;
import com.liferay.commerce.data.integration.manager.model.ScheduledTaskSoap;
import com.liferay.commerce.data.integration.manager.service.ScheduledTaskServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link ScheduledTaskServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link ScheduledTaskSoap}.
 * If the method in the service utility returns a
 * {@link ScheduledTask}, that is translated to a
 * {@link ScheduledTaskSoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ScheduledTaskServiceHttp
 * @see ScheduledTaskSoap
 * @see ScheduledTaskServiceUtil
 * @generated
 */
@ProviderType
public class ScheduledTaskServiceSoap {
	/**
	* NOTE FOR DEVELOPERS:
	*
	* Never reference this class directly. Always use {@link ScheduledTaskServiceUtil} to access the scheduled task remote service.
	*/
	public static ScheduledTaskSoap addScheduledTask(
		long processId, String frequency, java.util.Date startDate,
		String startHour, String name,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			ScheduledTask returnValue = ScheduledTaskServiceUtil.addScheduledTask(processId,
					frequency, startDate, startHour, name, serviceContext);

			return ScheduledTaskSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static ScheduledTaskSoap[] getScheduledTaskByGroupId(
		long groupId, int start, int end) throws RemoteException {
		try {
			java.util.List<ScheduledTask> returnValue =
				ScheduledTaskServiceUtil.getScheduledTaskByGroupId(groupId,
					start, end);

			return ScheduledTaskSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getScheduledTaskByGroupIdCount(long groupId)
		throws RemoteException {
		try {
			int returnValue = ScheduledTaskServiceUtil.getScheduledTaskByGroupIdCount(groupId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static ScheduledTaskSoap updateScheduledTask(
		long scheduledTaskId, long processId, String frequency,
		java.util.Date startDate, String startHour, String name,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			ScheduledTask returnValue = ScheduledTaskServiceUtil.updateScheduledTask(scheduledTaskId,
					processId, frequency, startDate, startHour, name,
					serviceContext);

			return ScheduledTaskSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ScheduledTaskServiceSoap.class);
}