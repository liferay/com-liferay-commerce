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
	public static com.liferay.commerce.account.model.CommerceAccount addCommerceAccount(
		HttpPrincipal httpPrincipal, String name, long parentCommerceAccountId,
		String email, String taxId, boolean active,
		String externalReferenceCode, long[] userIds, String[] emailAddresses,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceAccountServiceUtil.class,
					"addCommerceAccount", _addCommerceAccountParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, name,
					parentCommerceAccountId, email, taxId, active,
					externalReferenceCode, userIds, emailAddresses,
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

			return (com.liferay.commerce.account.model.CommerceAccount)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.account.model.CommerceAccount addCommerceAccount(
		HttpPrincipal httpPrincipal, String name, long parentCommerceAccountId,
		String email, String taxId, boolean active,
		String externalReferenceCode,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceAccountServiceUtil.class,
					"addCommerceAccount", _addCommerceAccountParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey, name,
					parentCommerceAccountId, email, taxId, active,
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

	public static void deleteCommerceAccount(HttpPrincipal httpPrincipal,
		long commerceAccountId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceAccountServiceUtil.class,
					"deleteCommerceAccount",
					_deleteCommerceAccountParameterTypes2);

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

	public static com.liferay.commerce.account.model.CommerceAccount fetchCommerceAccount(
		HttpPrincipal httpPrincipal, long commerceAccountId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceAccountServiceUtil.class,
					"fetchCommerceAccount", _fetchCommerceAccountParameterTypes3);

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

			return (com.liferay.commerce.account.model.CommerceAccount)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.account.model.CommerceAccount getCommerceAccount(
		HttpPrincipal httpPrincipal, long commerceAccountId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceAccountServiceUtil.class,
					"getCommerceAccount", _getCommerceAccountParameterTypes4);

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

			return (com.liferay.commerce.account.model.CommerceAccount)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.commerce.account.model.CommerceAccount> getUserCommerceAccounts(
		HttpPrincipal httpPrincipal, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceAccountServiceUtil.class,
					"getUserCommerceAccounts",
					_getUserCommerceAccountsParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey, start,
					end);

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

			return (java.util.List<com.liferay.commerce.account.model.CommerceAccount>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.commerce.account.model.CommerceAccount> getUserCommerceAccounts(
		HttpPrincipal httpPrincipal, Long parentCommerceAccountId,
		String keywords, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceAccountServiceUtil.class,
					"getUserCommerceAccounts",
					_getUserCommerceAccountsParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					parentCommerceAccountId, keywords, start, end);

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

			return (java.util.List<com.liferay.commerce.account.model.CommerceAccount>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getUserCommerceAccountsCount(
		HttpPrincipal httpPrincipal, Long parentCommerceAccountId,
		String keywords)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceAccountServiceUtil.class,
					"getUserCommerceAccountsCount",
					_getUserCommerceAccountsCountParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					parentCommerceAccountId, keywords);

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

	public static com.liferay.portal.kernel.search.BaseModelSearchResult<com.liferay.commerce.account.model.CommerceAccount> searchCommerceAccounts(
		HttpPrincipal httpPrincipal, long groupId,
		long parentCommerceAccountId, String keywords, Boolean active,
		int start, int end, com.liferay.portal.kernel.search.Sort sort)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceAccountServiceUtil.class,
					"searchCommerceAccounts",
					_searchCommerceAccountsParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					parentCommerceAccountId, keywords, active, start, end, sort);

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

	public static com.liferay.commerce.account.model.CommerceAccount updateCommerceAccount(
		HttpPrincipal httpPrincipal, long commerceAccountId, String name,
		boolean logo, byte[] logoBytes, String email, String taxId,
		boolean active,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceAccountServiceUtil.class,
					"updateCommerceAccount",
					_updateCommerceAccountParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceAccountId, name, logo, logoBytes, email, taxId,
					active, serviceContext);

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

	public static com.liferay.commerce.account.model.CommerceAccount upsertCommerceAccount(
		HttpPrincipal httpPrincipal, String name, long parentCommerceAccountId,
		boolean logo, byte[] logoBytes, String email, String taxId,
		boolean active, String externalReferenceCode,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceAccountServiceUtil.class,
					"upsertCommerceAccount",
					_upsertCommerceAccountParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(methodKey, name,
					parentCommerceAccountId, logo, logoBytes, email, taxId,
					active, externalReferenceCode, serviceContext);

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
	private static final Class<?>[] _addCommerceAccountParameterTypes0 = new Class[] {
			String.class, long.class, String.class, String.class, boolean.class,
			String.class, long[].class, String[].class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _addCommerceAccountParameterTypes1 = new Class[] {
			String.class, long.class, String.class, String.class, boolean.class,
			String.class, com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _deleteCommerceAccountParameterTypes2 = new Class[] {
			long.class
		};
	private static final Class<?>[] _fetchCommerceAccountParameterTypes3 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getCommerceAccountParameterTypes4 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getUserCommerceAccountsParameterTypes5 = new Class[] {
			int.class, int.class
		};
	private static final Class<?>[] _getUserCommerceAccountsParameterTypes6 = new Class[] {
			Long.class, String.class, int.class, int.class
		};
	private static final Class<?>[] _getUserCommerceAccountsCountParameterTypes7 =
		new Class[] { Long.class, String.class };
	private static final Class<?>[] _searchCommerceAccountsParameterTypes8 = new Class[] {
			long.class, long.class, String.class, Boolean.class, int.class,
			int.class, com.liferay.portal.kernel.search.Sort.class
		};
	private static final Class<?>[] _updateCommerceAccountParameterTypes9 = new Class[] {
			long.class, String.class, boolean.class, byte[].class, String.class,
			String.class, boolean.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _upsertCommerceAccountParameterTypes10 = new Class[] {
			String.class, long.class, boolean.class, byte[].class, String.class,
			String.class, boolean.class, String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
}