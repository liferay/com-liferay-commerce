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

import com.liferay.commerce.product.service.CPSubscriptionEntryServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link CPSubscriptionEntryServiceUtil} service utility. The
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
 * @see CPSubscriptionEntryServiceSoap
 * @see HttpPrincipal
 * @see CPSubscriptionEntryServiceUtil
 * @generated
 */
@ProviderType
public class CPSubscriptionEntryServiceHttp {
	public static java.util.List<com.liferay.commerce.product.model.CPSubscriptionEntry> getCPSubscriptionEntries(
		HttpPrincipal httpPrincipal, long groupId, long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.product.model.CPSubscriptionEntry> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CPSubscriptionEntryServiceUtil.class,
					"getCPSubscriptionEntries",
					_getCPSubscriptionEntriesParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					userId, start, end, orderByComparator);

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

			return (java.util.List<com.liferay.commerce.product.model.CPSubscriptionEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteCPSubscriptionEntry(HttpPrincipal httpPrincipal,
		long cpSubscriptionEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CPSubscriptionEntryServiceUtil.class,
					"deleteCPSubscriptionEntry",
					_deleteCPSubscriptionEntryParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					cpSubscriptionEntryId);

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

	public static com.liferay.commerce.product.model.CPSubscriptionEntry fetchCPSubscriptionEntry(
		HttpPrincipal httpPrincipal, long cpSubscriptionEntryId)
		throws com.liferay.portal.kernel.security.auth.PrincipalException {
		try {
			MethodKey methodKey = new MethodKey(CPSubscriptionEntryServiceUtil.class,
					"fetchCPSubscriptionEntry",
					_fetchCPSubscriptionEntryParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					cpSubscriptionEntryId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.security.auth.PrincipalException) {
					throw (com.liferay.portal.kernel.security.auth.PrincipalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.commerce.product.model.CPSubscriptionEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.search.BaseModelSearchResult<com.liferay.commerce.product.model.CPSubscriptionEntry> searchCPSubscriptionEntries(
		HttpPrincipal httpPrincipal, long companyId, long groupId,
		Boolean active, String keywords, int start, int end,
		com.liferay.portal.kernel.search.Sort sort)
		throws com.liferay.portal.kernel.security.auth.PrincipalException {
		try {
			MethodKey methodKey = new MethodKey(CPSubscriptionEntryServiceUtil.class,
					"searchCPSubscriptionEntries",
					_searchCPSubscriptionEntriesParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, groupId, active, keywords, start, end, sort);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.security.auth.PrincipalException) {
					throw (com.liferay.portal.kernel.security.auth.PrincipalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.search.BaseModelSearchResult<com.liferay.commerce.product.model.CPSubscriptionEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getCPSubscriptionEntriesCount(
		HttpPrincipal httpPrincipal, long groupId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CPSubscriptionEntryServiceUtil.class,
					"getCPSubscriptionEntriesCount",
					_getCPSubscriptionEntriesCountParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					userId);

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

	public static com.liferay.commerce.product.model.CPSubscriptionEntry updateCommercePriceEntry(
		HttpPrincipal httpPrincipal, long cpSubscriptionEntryId,
		long subscriptionCycleLength, String subscriptionCyclePeriod,
		long maxSubscriptionCyclesNumber, boolean active,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.security.auth.PrincipalException {
		try {
			MethodKey methodKey = new MethodKey(CPSubscriptionEntryServiceUtil.class,
					"updateCommercePriceEntry",
					_updateCommercePriceEntryParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					cpSubscriptionEntryId, subscriptionCycleLength,
					subscriptionCyclePeriod, maxSubscriptionCyclesNumber,
					active, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.security.auth.PrincipalException) {
					throw (com.liferay.portal.kernel.security.auth.PrincipalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.commerce.product.model.CPSubscriptionEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CPSubscriptionEntryServiceHttp.class);
	private static final Class<?>[] _getCPSubscriptionEntriesParameterTypes0 = new Class[] {
			long.class, long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _deleteCPSubscriptionEntryParameterTypes1 = new Class[] {
			long.class
		};
	private static final Class<?>[] _fetchCPSubscriptionEntryParameterTypes2 = new Class[] {
			long.class
		};
	private static final Class<?>[] _searchCPSubscriptionEntriesParameterTypes3 = new Class[] {
			long.class, long.class, Boolean.class, String.class, int.class,
			int.class, com.liferay.portal.kernel.search.Sort.class
		};
	private static final Class<?>[] _getCPSubscriptionEntriesCountParameterTypes4 =
		new Class[] { long.class, long.class };
	private static final Class<?>[] _updateCommercePriceEntryParameterTypes5 = new Class[] {
			long.class, long.class, String.class, long.class, boolean.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
}