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

import com.liferay.commerce.product.service.CommerceChannelRelServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>CommerceChannelRelServiceUtil</code> service
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
 * @see CommerceChannelRelServiceSoap
 * @generated
 */
public class CommerceChannelRelServiceHttp {

	public static com.liferay.commerce.product.model.CommerceChannelRel
			addCommerceChannelRel(
				HttpPrincipal httpPrincipal, String className, long classPK,
				long commerceChannelId,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceChannelRelServiceUtil.class, "addCommerceChannelRel",
				_addCommerceChannelRelParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, className, classPK, commerceChannelId,
				serviceContext);

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

			return (com.liferay.commerce.product.model.CommerceChannelRel)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteCommerceChannelRels(
		HttpPrincipal httpPrincipal, String className, long classPK) {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceChannelRelServiceUtil.class,
				"deleteCommerceChannelRels",
				_deleteCommerceChannelRelsParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, className, classPK);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List
		<com.liferay.commerce.product.model.CommerceChannelRel>
			getCommerceChannelRels(
				HttpPrincipal httpPrincipal, long commerceChannelId, int start,
				int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.product.model.CommerceChannelRel>
						orderByComparator) {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceChannelRelServiceUtil.class, "getCommerceChannelRels",
				_getCommerceChannelRelsParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceChannelId, start, end, orderByComparator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (java.util.List
				<com.liferay.commerce.product.model.CommerceChannelRel>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List
		<com.liferay.commerce.product.model.CommerceChannelRel>
			getCommerceChannelRels(
				HttpPrincipal httpPrincipal, String className, long classPK,
				int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.product.model.CommerceChannelRel>
						orderByComparator) {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceChannelRelServiceUtil.class, "getCommerceChannelRels",
				_getCommerceChannelRelsParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, className, classPK, start, end, orderByComparator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (java.util.List
				<com.liferay.commerce.product.model.CommerceChannelRel>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getCommerceChannelRelsCount(
		HttpPrincipal httpPrincipal, long commerceChannelId) {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceChannelRelServiceUtil.class,
				"getCommerceChannelRelsCount",
				_getCommerceChannelRelsCountParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceChannelId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getCommerceChannelRelsCount(
		HttpPrincipal httpPrincipal, String className, long classPK) {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceChannelRelServiceUtil.class,
				"getCommerceChannelRelsCount",
				_getCommerceChannelRelsCountParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, className, classPK);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		CommerceChannelRelServiceHttp.class);

	private static final Class<?>[] _addCommerceChannelRelParameterTypes0 =
		new Class[] {
			String.class, long.class, long.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _deleteCommerceChannelRelsParameterTypes1 =
		new Class[] {String.class, long.class};
	private static final Class<?>[] _getCommerceChannelRelsParameterTypes2 =
		new Class[] {
			long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getCommerceChannelRelsParameterTypes3 =
		new Class[] {
			String.class, long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[]
		_getCommerceChannelRelsCountParameterTypes4 = new Class[] {long.class};
	private static final Class<?>[]
		_getCommerceChannelRelsCountParameterTypes5 = new Class[] {
			String.class, long.class
		};

}