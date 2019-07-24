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

package com.liferay.commerce.product.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.product.service.CPDisplayLayoutServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>CPDisplayLayoutServiceUtil</code> service
 * utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * <code>HttpPrincipal</code> parameter.
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
 * @see CPDisplayLayoutServiceSoap
 * @generated
 */
@ProviderType
public class CPDisplayLayoutServiceHttp {

	public static com.liferay.commerce.product.model.CPDisplayLayout
			addCPDisplayLayout(
				HttpPrincipal httpPrincipal, Class<?> clazz, long classPK,
				String layoutUuid,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CPDisplayLayoutServiceUtil.class, "addCPDisplayLayout",
				_addCPDisplayLayoutParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, clazz, classPK, layoutUuid, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.commerce.product.model.CPDisplayLayout)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteCPDisplayLayout(
			HttpPrincipal httpPrincipal, Class<?> clazz, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CPDisplayLayoutServiceUtil.class, "deleteCPDisplayLayout",
				_deleteCPDisplayLayoutParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, clazz, classPK);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteCPDisplayLayout(
			HttpPrincipal httpPrincipal, long cpDisplayLayoutId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CPDisplayLayoutServiceUtil.class, "deleteCPDisplayLayout",
				_deleteCPDisplayLayoutParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, cpDisplayLayoutId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.product.model.CPDisplayLayout
			fetchCPDisplayLayout(
				HttpPrincipal httpPrincipal, long cpDisplayLayoutId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CPDisplayLayoutServiceUtil.class, "fetchCPDisplayLayout",
				_fetchCPDisplayLayoutParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, cpDisplayLayoutId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.commerce.product.model.CPDisplayLayout)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.product.model.CPDisplayLayout
			updateCPDisplayLayout(
				HttpPrincipal httpPrincipal, long cpDisplayLayoutId,
				String layoutUuid)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CPDisplayLayoutServiceUtil.class, "updateCPDisplayLayout",
				_updateCPDisplayLayoutParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, cpDisplayLayoutId, layoutUuid);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.commerce.product.model.CPDisplayLayout)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		CPDisplayLayoutServiceHttp.class);

	private static final Class<?>[] _addCPDisplayLayoutParameterTypes0 =
		new Class[] {
			Class.class, long.class, String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _deleteCPDisplayLayoutParameterTypes1 =
		new Class[] {Class.class, long.class};
	private static final Class<?>[] _deleteCPDisplayLayoutParameterTypes2 =
		new Class[] {long.class};
	private static final Class<?>[] _fetchCPDisplayLayoutParameterTypes3 =
		new Class[] {long.class};
	private static final Class<?>[] _updateCPDisplayLayoutParameterTypes4 =
		new Class[] {long.class, String.class};

}