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

import com.liferay.commerce.account.service.CommerceAccountServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link CommerceAccountServiceUtil} service utility. The
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
 * @see CommerceAccountServiceSoap
 * @see HttpPrincipal
 * @see CommerceAccountServiceUtil
 * @generated
 */
@ProviderType
public class CommerceAccountServiceHttp {
	public static com.liferay.portal.kernel.search.BaseModelSearchResult<com.liferay.commerce.account.model.CommerceAccount> searchCommerceAccounts(
		HttpPrincipal httpPrincipal, long companyId,
		long parentCommerceAccountId, String keywords, Boolean active,
		int start, int end, com.liferay.portal.kernel.search.Sort sort)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceAccountServiceUtil.class,
					"searchCommerceAccounts",
					_searchCommerceAccountsParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, parentCommerceAccountId, keywords, active,
					start, end, sort);

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

			return (com.liferay.portal.kernel.search.BaseModelSearchResult<com.liferay.commerce.account.model.CommerceAccount>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.account.model.CommerceAccount upsertCommerceAccount(
		HttpPrincipal httpPrincipal, long userId, long parentCommerceAccountId,
		String name, String taxId, boolean active,
		String externalReferenceCode,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceAccountServiceUtil.class,
					"upsertCommerceAccount",
					_upsertCommerceAccountParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey, userId,
					parentCommerceAccountId, name, taxId, active,
					externalReferenceCode, serviceContext);

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

			return (com.liferay.commerce.account.model.CommerceAccount)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.account.model.CommerceAccount updateCommerceAccount(
		HttpPrincipal httpPrincipal, long commerceAccountId, String name,
		String taxId, boolean active,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceAccountServiceUtil.class,
					"updateCommerceAccount",
					_updateCommerceAccountParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceAccountId, name, taxId, active, serviceContext);

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

			return (com.liferay.commerce.account.model.CommerceAccount)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.account.model.CommerceAccount addCommerceAccount(
		HttpPrincipal httpPrincipal, long userId, long parentCommerceAccountId,
		String name, String taxId, boolean active,
		String externalReferenceCode,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceAccountServiceUtil.class,
					"addCommerceAccount", _addCommerceAccountParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey, userId,
					parentCommerceAccountId, name, taxId, active,
					externalReferenceCode, serviceContext);

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

			return (com.liferay.commerce.account.model.CommerceAccount)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CommerceAccountServiceHttp.class);
	private static final Class<?>[] _searchCommerceAccountsParameterTypes0 = new Class[] {
			long.class, long.class, String.class, Boolean.class, int.class,
			int.class, com.liferay.portal.kernel.search.Sort.class
		};
	private static final Class<?>[] _upsertCommerceAccountParameterTypes1 = new Class[] {
			long.class, long.class, String.class, String.class, boolean.class,
			String.class, com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateCommerceAccountParameterTypes2 = new Class[] {
			long.class, String.class, String.class, boolean.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _addCommerceAccountParameterTypes3 = new Class[] {
			long.class, long.class, String.class, String.class, boolean.class,
			String.class, com.liferay.portal.kernel.service.ServiceContext.class
		};
}