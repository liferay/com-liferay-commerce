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

package com.liferay.commerce.account.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.account.service.CommerceAccountUserRelServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link CommerceAccountUserRelServiceUtil} service utility. The
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
 * @author Marco Leo
 * @see CommerceAccountUserRelServiceSoap
 * @see HttpPrincipal
 * @see CommerceAccountUserRelServiceUtil
 * @generated
 */
@ProviderType
public class CommerceAccountUserRelServiceHttp {
	public static void addCommerceAccountUserRels(HttpPrincipal httpPrincipal,
		long commerceAccountId, long[] userIds, String[] emailAddresses,
		long[] roleIds,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceAccountUserRelServiceUtil.class,
					"addCommerceAccountUserRels",
					_addCommerceAccountUserRelsParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceAccountId, userIds, emailAddresses, roleIds,
					serviceContext);

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

	public static void deleteCommerceAccountUserRel(
		HttpPrincipal httpPrincipal, long commerceAccountId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceAccountUserRelServiceUtil.class,
					"deleteCommerceAccountUserRel",
					_deleteCommerceAccountUserRelParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceAccountId, userId);

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

	public static void deleteCommerceAccountUserRels(
		HttpPrincipal httpPrincipal, long commerceAccountId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceAccountUserRelServiceUtil.class,
					"deleteCommerceAccountUserRels",
					_deleteCommerceAccountUserRelsParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceAccountId);

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

	public static void deleteCommerceAccountUserRels(
		HttpPrincipal httpPrincipal, long commerceAccountId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceAccountUserRelServiceUtil.class,
					"deleteCommerceAccountUserRels",
					_deleteCommerceAccountUserRelsParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceAccountId, userIds);

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

	public static java.util.List<com.liferay.commerce.account.model.CommerceAccountUserRel> getCommerceAccountUserRels(
		HttpPrincipal httpPrincipal, long commerceAccountId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceAccountUserRelServiceUtil.class,
					"getCommerceAccountUserRels",
					_getCommerceAccountUserRelsParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceAccountId, start, end);

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

			return (java.util.List<com.liferay.commerce.account.model.CommerceAccountUserRel>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getCommerceAccountUserRelsCount(
		HttpPrincipal httpPrincipal, long commerceAccountId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceAccountUserRelServiceUtil.class,
					"getCommerceAccountUserRelsCount",
					_getCommerceAccountUserRelsCountParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceAccountId);

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

	private static Log _log = LogFactoryUtil.getLog(CommerceAccountUserRelServiceHttp.class);
	private static final Class<?>[] _addCommerceAccountUserRelsParameterTypes0 = new Class[] {
			long.class, long[].class, String[].class, long[].class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _deleteCommerceAccountUserRelParameterTypes1 =
		new Class[] { long.class, long.class };
	private static final Class<?>[] _deleteCommerceAccountUserRelsParameterTypes2 =
		new Class[] { long.class };
	private static final Class<?>[] _deleteCommerceAccountUserRelsParameterTypes3 =
		new Class[] { long.class, long[].class };
	private static final Class<?>[] _getCommerceAccountUserRelsParameterTypes4 = new Class[] {
			long.class, int.class, int.class
		};
	private static final Class<?>[] _getCommerceAccountUserRelsCountParameterTypes5 =
		new Class[] { long.class };
}