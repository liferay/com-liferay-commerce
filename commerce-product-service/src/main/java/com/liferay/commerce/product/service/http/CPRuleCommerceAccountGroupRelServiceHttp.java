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

import com.liferay.commerce.product.service.CPRuleCommerceAccountGroupRelServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link CPRuleCommerceAccountGroupRelServiceUtil} service utility. The
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
 * @see CPRuleCommerceAccountGroupRelServiceSoap
 * @see HttpPrincipal
 * @see CPRuleCommerceAccountGroupRelServiceUtil
 * @generated
 */
@ProviderType
public class CPRuleCommerceAccountGroupRelServiceHttp {
	public static com.liferay.commerce.product.model.CPRuleCommerceAccountGroupRel addCPRuleCommerceAccountGroupRel(
		HttpPrincipal httpPrincipal, long cpRuleId,
		long commerceAccountGroupId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CPRuleCommerceAccountGroupRelServiceUtil.class,
					"addCPRuleCommerceAccountGroupRel",
					_addCPRuleCommerceAccountGroupRelParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					cpRuleId, commerceAccountGroupId, serviceContext);

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

			return (com.liferay.commerce.product.model.CPRuleCommerceAccountGroupRel)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteCPRuleCommerceAccountGroupRel(
		HttpPrincipal httpPrincipal, long cpRuleCommerceAccountGroupRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CPRuleCommerceAccountGroupRelServiceUtil.class,
					"deleteCPRuleCommerceAccountGroupRel",
					_deleteCPRuleCommerceAccountGroupRelParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					cpRuleCommerceAccountGroupRelId);

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

	public static java.util.List<com.liferay.commerce.product.model.CPRuleCommerceAccountGroupRel> getCPRuleCommerceAccountGroupRels(
		HttpPrincipal httpPrincipal, long cpRuleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.product.model.CPRuleCommerceAccountGroupRel> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CPRuleCommerceAccountGroupRelServiceUtil.class,
					"getCPRuleCommerceAccountGroupRels",
					_getCPRuleCommerceAccountGroupRelsParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					cpRuleId, start, end, orderByComparator);

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

			return (java.util.List<com.liferay.commerce.product.model.CPRuleCommerceAccountGroupRel>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getCPRuleCommerceAccountGroupRelsCount(
		HttpPrincipal httpPrincipal, long cpRuleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CPRuleCommerceAccountGroupRelServiceUtil.class,
					"getCPRuleCommerceAccountGroupRelsCount",
					_getCPRuleCommerceAccountGroupRelsCountParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey, cpRuleId);

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

	private static Log _log = LogFactoryUtil.getLog(CPRuleCommerceAccountGroupRelServiceHttp.class);
	private static final Class<?>[] _addCPRuleCommerceAccountGroupRelParameterTypes0 =
		new Class[] {
			long.class, long.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _deleteCPRuleCommerceAccountGroupRelParameterTypes1 =
		new Class[] { long.class };
	private static final Class<?>[] _getCPRuleCommerceAccountGroupRelsParameterTypes2 =
		new Class[] {
			long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getCPRuleCommerceAccountGroupRelsCountParameterTypes3 =
		new Class[] { long.class };
}