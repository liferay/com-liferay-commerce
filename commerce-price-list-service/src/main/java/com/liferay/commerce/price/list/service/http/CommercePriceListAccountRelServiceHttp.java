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

package com.liferay.commerce.price.list.service.http;

import com.liferay.commerce.price.list.service.CommercePriceListAccountRelServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>CommercePriceListAccountRelServiceUtil</code> service
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
 * @author Alessio Antonio Rendina
 * @see CommercePriceListAccountRelServiceSoap
 * @generated
 */
public class CommercePriceListAccountRelServiceHttp {

	public static
		com.liferay.commerce.price.list.model.CommercePriceListAccountRel
				addCommercePriceListAccountRel(
					HttpPrincipal httpPrincipal, long commercePriceListId,
					long commerceAccountId, int order,
					com.liferay.portal.kernel.service.ServiceContext
						serviceContext)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommercePriceListAccountRelServiceUtil.class,
				"addCommercePriceListAccountRel",
				_addCommercePriceListAccountRelParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commercePriceListId, commerceAccountId, order,
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

			return (com.liferay.commerce.price.list.model.
				CommercePriceListAccountRel)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteCommercePriceListAccountRel(
			HttpPrincipal httpPrincipal, long commercePriceListAccountRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommercePriceListAccountRelServiceUtil.class,
				"deleteCommercePriceListAccountRel",
				_deleteCommercePriceListAccountRelParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commercePriceListAccountRelId);

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

	public static
		com.liferay.commerce.price.list.model.CommercePriceListAccountRel
				fetchCommercePriceListAccountRel(
					HttpPrincipal httpPrincipal, long commercePriceListId,
					long commerceAccountId)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommercePriceListAccountRelServiceUtil.class,
				"fetchCommercePriceListAccountRel",
				_fetchCommercePriceListAccountRelParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commercePriceListId, commerceAccountId);

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

			return (com.liferay.commerce.price.list.model.
				CommercePriceListAccountRel)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List
		<com.liferay.commerce.price.list.model.CommercePriceListAccountRel>
				getCommercePriceListAccountRels(
					HttpPrincipal httpPrincipal, long commercePriceListId)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommercePriceListAccountRelServiceUtil.class,
				"getCommercePriceListAccountRels",
				_getCommercePriceListAccountRelsParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commercePriceListId);

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

			return (java.util.List
				<com.liferay.commerce.price.list.model.
					CommercePriceListAccountRel>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		CommercePriceListAccountRelServiceHttp.class);

	private static final Class<?>[]
		_addCommercePriceListAccountRelParameterTypes0 = new Class[] {
			long.class, long.class, int.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[]
		_deleteCommercePriceListAccountRelParameterTypes1 = new Class[] {
			long.class
		};
	private static final Class<?>[]
		_fetchCommercePriceListAccountRelParameterTypes2 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[]
		_getCommercePriceListAccountRelsParameterTypes3 = new Class[] {
			long.class
		};

}