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

package com.liferay.commerce.application.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.application.service.CommerceApplicationModelCProductRelServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link CommerceApplicationModelCProductRelServiceUtil} service utility. The
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
 * @see CommerceApplicationModelCProductRelServiceSoap
 * @see HttpPrincipal
 * @see CommerceApplicationModelCProductRelServiceUtil
 * @generated
 */
@ProviderType
public class CommerceApplicationModelCProductRelServiceHttp {
	public static com.liferay.commerce.application.model.CommerceApplicationModelCProductRel addCommerceApplicationModelCProductRel(
		HttpPrincipal httpPrincipal, long userId,
		long commerceApplicationModelId, long cProductId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceApplicationModelCProductRelServiceUtil.class,
					"addCommerceApplicationModelCProductRel",
					_addCommerceApplicationModelCProductRelParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, userId,
					commerceApplicationModelId, cProductId);

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

			return (com.liferay.commerce.application.model.CommerceApplicationModelCProductRel)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteCommerceApplicationModelCProductRel(
		HttpPrincipal httpPrincipal, long commerceApplicationModelCProductRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceApplicationModelCProductRelServiceUtil.class,
					"deleteCommerceApplicationModelCProductRel",
					_deleteCommerceApplicationModelCProductRelParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceApplicationModelCProductRelId);

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

	public static java.util.List<com.liferay.commerce.application.model.CommerceApplicationModelCProductRel> getCommerceApplicationModelCProductRels(
		HttpPrincipal httpPrincipal, long commerceApplicationModelId,
		int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceApplicationModelCProductRelServiceUtil.class,
					"getCommerceApplicationModelCProductRels",
					_getCommerceApplicationModelCProductRelsParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceApplicationModelId, start, end);

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

			return (java.util.List<com.liferay.commerce.application.model.CommerceApplicationModelCProductRel>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getCommerceApplicationModelCProductRelsCount(
		HttpPrincipal httpPrincipal, long commerceApplicationModelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceApplicationModelCProductRelServiceUtil.class,
					"getCommerceApplicationModelCProductRelsCount",
					_getCommerceApplicationModelCProductRelsCountParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceApplicationModelId);

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

	private static Log _log = LogFactoryUtil.getLog(CommerceApplicationModelCProductRelServiceHttp.class);
	private static final Class<?>[] _addCommerceApplicationModelCProductRelParameterTypes0 =
		new Class[] { long.class, long.class, long.class };
	private static final Class<?>[] _deleteCommerceApplicationModelCProductRelParameterTypes1 =
		new Class[] { long.class };
	private static final Class<?>[] _getCommerceApplicationModelCProductRelsParameterTypes2 =
		new Class[] { long.class, int.class, int.class };
	private static final Class<?>[] _getCommerceApplicationModelCProductRelsCountParameterTypes3 =
		new Class[] { long.class };
}