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

package com.liferay.data.integration.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.data.integration.service.ProcessServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link ProcessServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.data.integration.model.ProcessSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.data.integration.model.Process}, that is translated to a
 * {@link com.liferay.data.integration.model.ProcessSoap}. Methods that SOAP cannot
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
 * @see ProcessServiceHttp
 * @see com.liferay.data.integration.model.ProcessSoap
 * @see ProcessServiceUtil
 * @generated
 */
@ProviderType
public class ProcessServiceSoap {
	/**
	* NOTE FOR DEVELOPERS:
	*
	* Never reference this class directly. Always use {@link ProcessServiceUtil} to access the process remote service.
	*
	* @throws PortalException
	*/
	public static com.liferay.data.integration.model.ProcessSoap addProcess(
		String name, String className, String processType, String version,
		long contextPropertiesFileEntryId, long srcArchiveFileEntryId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.data.integration.model.Process returnValue = ProcessServiceUtil.addProcess(name,
					className, processType, version,
					contextPropertiesFileEntryId, srcArchiveFileEntryId,
					serviceContext);

			return com.liferay.data.integration.model.ProcessSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.data.integration.model.ProcessSoap deleteProcess(
		long userId, long processId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.data.integration.model.Process returnValue = ProcessServiceUtil.deleteProcess(userId,
					processId, serviceContext);

			return com.liferay.data.integration.model.ProcessSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.data.integration.model.ProcessSoap getProcess(
		long userId, long processId) throws RemoteException {
		try {
			com.liferay.data.integration.model.Process returnValue = ProcessServiceUtil.getProcess(userId,
					processId);

			return com.liferay.data.integration.model.ProcessSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.data.integration.model.ProcessSoap[] getProcessesByGroupId(
		long userId, long groupId, int start, int end)
		throws RemoteException {
		try {
			java.util.List<com.liferay.data.integration.model.Process> returnValue =
				ProcessServiceUtil.getProcessesByGroupId(userId, groupId,
					start, end);

			return com.liferay.data.integration.model.ProcessSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getProcessesByGroupIdCount(long userId, long groupId)
		throws RemoteException {
		try {
			int returnValue = ProcessServiceUtil.getProcessesByGroupIdCount(userId,
					groupId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.data.integration.model.ProcessSoap updateProcess(
		long processId, String name, String className, String processType,
		String version, long contextPropertiesFileEntryId,
		long srcArchiveFileEntryId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.data.integration.model.Process returnValue = ProcessServiceUtil.updateProcess(processId,
					name, className, processType, version,
					contextPropertiesFileEntryId, srcArchiveFileEntryId,
					serviceContext);

			return com.liferay.data.integration.model.ProcessSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ProcessServiceSoap.class);
}