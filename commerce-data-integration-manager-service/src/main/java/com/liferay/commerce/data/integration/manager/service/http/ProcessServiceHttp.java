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

import com.liferay.commerce.data.integration.manager.model.Process;
import com.liferay.commerce.data.integration.manager.service.ProcessServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link ProcessServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * {@link HttpPrincipal} parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProcessServiceSoap
 * @see HttpPrincipal
 * @see ProcessServiceUtil
 * @generated
 */
@ProviderType
public class ProcessServiceHttp {
	public static Process addProcess(
		HttpPrincipal httpPrincipal, String name, String className,
		String processType, String version, long contextPropertiesFileEntryId,
		long srcArchiveFileEntryId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(ProcessServiceUtil.class,
					"addProcess", _addProcessParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, name,
					className, processType, version,
					contextPropertiesFileEntryId, srcArchiveFileEntryId,
					serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (Process)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static Process deleteProcess(
		HttpPrincipal httpPrincipal, long userId, long processId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(ProcessServiceUtil.class,
					"deleteProcess", _deleteProcessParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey, userId,
					processId, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (Process)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static Process getProcess(
		HttpPrincipal httpPrincipal, long userId, long processId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(ProcessServiceUtil.class,
					"getProcess", _getProcessParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey, userId,
					processId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (Process)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<Process> getProcessesByGroupId(
		HttpPrincipal httpPrincipal, long userId, long groupId, int start,
		int end) throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(ProcessServiceUtil.class,
					"getProcessesByGroupId",
					_getProcessesByGroupIdParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey, userId,
					groupId, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<Process>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getProcessesByGroupIdCount(HttpPrincipal httpPrincipal,
		long userId, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(ProcessServiceUtil.class,
					"getProcessesByGroupIdCount",
					_getProcessesByGroupIdCountParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey, userId,
					groupId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static Process updateProcess(
		HttpPrincipal httpPrincipal, long processId, String name,
		String className, String processType, String version,
		long contextPropertiesFileEntryId, long srcArchiveFileEntryId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(ProcessServiceUtil.class,
					"updateProcess", _updateProcessParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					processId, name, className, processType, version,
					contextPropertiesFileEntryId, srcArchiveFileEntryId,
					serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (Process)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ProcessServiceHttp.class);
	private static final Class<?>[] _addProcessParameterTypes0 = new Class[] {
			String.class, String.class, String.class, String.class, long.class,
			long.class, com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _deleteProcessParameterTypes1 = new Class[] {
			long.class, long.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _getProcessParameterTypes2 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _getProcessesByGroupIdParameterTypes3 = new Class[] {
			long.class, long.class, int.class, int.class
		};
	private static final Class<?>[] _getProcessesByGroupIdCountParameterTypes4 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _updateProcessParameterTypes5 = new Class[] {
			long.class, String.class, String.class, String.class, String.class,
			long.class, long.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
}