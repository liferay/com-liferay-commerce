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

package com.liferay.commerce.bom.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.bom.service.CommerceBOMFolderServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link CommerceBOMFolderServiceUtil} service utility. The
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
 * @author Luca Pellizzon
 * @see CommerceBOMFolderServiceSoap
 * @see HttpPrincipal
 * @see CommerceBOMFolderServiceUtil
 * @generated
 */
@ProviderType
public class CommerceBOMFolderServiceHttp {
	public static com.liferay.commerce.bom.model.CommerceBOMFolder addCommerceBOMFolder(
		HttpPrincipal httpPrincipal, long userId,
		long parentCommerceBOMFolderId, String name, boolean logo,
		byte[] logoBytes)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceBOMFolderServiceUtil.class,
					"addCommerceBOMFolder", _addCommerceBOMFolderParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, userId,
					parentCommerceBOMFolderId, name, logo, logoBytes);

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

			return (com.liferay.commerce.bom.model.CommerceBOMFolder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteCommerceBOMFolder(HttpPrincipal httpPrincipal,
		long commerceBOMFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceBOMFolderServiceUtil.class,
					"deleteCommerceBOMFolder",
					_deleteCommerceBOMFolderParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceBOMFolderId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.bom.model.CommerceBOMFolder getCommerceBOMFolder(
		HttpPrincipal httpPrincipal, long commerceBOMFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceBOMFolderServiceUtil.class,
					"getCommerceBOMFolder", _getCommerceBOMFolderParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceBOMFolderId);

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

			return (com.liferay.commerce.bom.model.CommerceBOMFolder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.commerce.bom.model.CommerceBOMFolder> getCommerceBOMFolders(
		HttpPrincipal httpPrincipal, long companyId, int start, int end) {
		try {
			MethodKey methodKey = new MethodKey(CommerceBOMFolderServiceUtil.class,
					"getCommerceBOMFolders",
					_getCommerceBOMFoldersParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.commerce.bom.model.CommerceBOMFolder>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.commerce.bom.model.CommerceBOMFolder> getCommerceBOMFolders(
		HttpPrincipal httpPrincipal, long companyId,
		long parentCommerceBOMFolderId, int start, int end) {
		try {
			MethodKey methodKey = new MethodKey(CommerceBOMFolderServiceUtil.class,
					"getCommerceBOMFolders",
					_getCommerceBOMFoldersParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, parentCommerceBOMFolderId, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.commerce.bom.model.CommerceBOMFolder>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getCommerceBOMFoldersCount(HttpPrincipal httpPrincipal,
		long companyId) {
		try {
			MethodKey methodKey = new MethodKey(CommerceBOMFolderServiceUtil.class,
					"getCommerceBOMFoldersCount",
					_getCommerceBOMFoldersCountParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey, companyId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getCommerceBOMFoldersCount(HttpPrincipal httpPrincipal,
		long companyId, long parentCommerceBOMFolderId) {
		try {
			MethodKey methodKey = new MethodKey(CommerceBOMFolderServiceUtil.class,
					"getCommerceBOMFoldersCount",
					_getCommerceBOMFoldersCountParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, parentCommerceBOMFolderId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.bom.model.CommerceBOMFolder updateCommerceBOMFolder(
		HttpPrincipal httpPrincipal, long commerceBOMFolderId, String name,
		boolean logo, byte[] logoBytes)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceBOMFolderServiceUtil.class,
					"updateCommerceBOMFolder",
					_updateCommerceBOMFolderParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceBOMFolderId, name, logo, logoBytes);

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

			return (com.liferay.commerce.bom.model.CommerceBOMFolder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CommerceBOMFolderServiceHttp.class);
	private static final Class<?>[] _addCommerceBOMFolderParameterTypes0 = new Class[] {
			long.class, long.class, String.class, boolean.class, byte[].class
		};
	private static final Class<?>[] _deleteCommerceBOMFolderParameterTypes1 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getCommerceBOMFolderParameterTypes2 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getCommerceBOMFoldersParameterTypes3 = new Class[] {
			long.class, int.class, int.class
		};
	private static final Class<?>[] _getCommerceBOMFoldersParameterTypes4 = new Class[] {
			long.class, long.class, int.class, int.class
		};
	private static final Class<?>[] _getCommerceBOMFoldersCountParameterTypes5 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getCommerceBOMFoldersCountParameterTypes6 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _updateCommerceBOMFolderParameterTypes7 = new Class[] {
			long.class, String.class, boolean.class, byte[].class
		};
}