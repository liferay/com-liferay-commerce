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

import com.liferay.commerce.product.service.CommerceCatalogServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link CommerceCatalogServiceUtil} service utility. The
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
 * @see CommerceCatalogServiceSoap
 * @see HttpPrincipal
 * @see CommerceCatalogServiceUtil
 * @generated
 */
@ProviderType
public class CommerceCatalogServiceHttp {
	public static com.liferay.commerce.product.model.CommerceCatalog addCommerceCatalog(
		HttpPrincipal httpPrincipal,
		java.util.Map<java.util.Locale, String> nameMap,
		String catalogDefaultLanguageId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceCatalogServiceUtil.class,
					"addCommerceCatalog", _addCommerceCatalogParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, nameMap,
					catalogDefaultLanguageId, serviceContext);

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

			return (com.liferay.commerce.product.model.CommerceCatalog)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.product.model.CommerceCatalog deleteCommerceCatalog(
		HttpPrincipal httpPrincipal, long commerceCatalogId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceCatalogServiceUtil.class,
					"deleteCommerceCatalog",
					_deleteCommerceCatalogParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceCatalogId);

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

			return (com.liferay.commerce.product.model.CommerceCatalog)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.product.model.CommerceCatalog fetchCommerceCatalog(
		HttpPrincipal httpPrincipal, long commerceCatalogId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceCatalogServiceUtil.class,
					"fetchCommerceCatalog", _fetchCommerceCatalogParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceCatalogId);

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

			return (com.liferay.commerce.product.model.CommerceCatalog)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Group getCommerceCatalogGroup(
		HttpPrincipal httpPrincipal, long commerceCatalogId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceCatalogServiceUtil.class,
					"getCommerceCatalogGroup",
					_getCommerceCatalogGroupParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceCatalogId);

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

			return (com.liferay.portal.kernel.model.Group)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.commerce.product.model.CommerceCatalog> searchCommerceCatalogs(
		HttpPrincipal httpPrincipal, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceCatalogServiceUtil.class,
					"searchCommerceCatalogs",
					_searchCommerceCatalogsParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey, companyId);

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

			return (java.util.List<com.liferay.commerce.product.model.CommerceCatalog>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.commerce.product.model.CommerceCatalog> searchCommerceCatalogs(
		HttpPrincipal httpPrincipal, long companyId, String keywords,
		int start, int end, com.liferay.portal.kernel.search.Sort sort)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceCatalogServiceUtil.class,
					"searchCommerceCatalogs",
					_searchCommerceCatalogsParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, keywords, start, end, sort);

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

			return (java.util.List<com.liferay.commerce.product.model.CommerceCatalog>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int searchCommerceCatalogsCount(HttpPrincipal httpPrincipal,
		long companyId, String keywords)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceCatalogServiceUtil.class,
					"searchCommerceCatalogsCount",
					_searchCommerceCatalogsCountParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, keywords);

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

	public static com.liferay.commerce.product.model.CommerceCatalog updateCommerceCatalog(
		HttpPrincipal httpPrincipal, long commerceCatalogId,
		String catalogDefaultLanguageId,
		java.util.Map<java.util.Locale, String> nameMap,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceCatalogServiceUtil.class,
					"updateCommerceCatalog",
					_updateCommerceCatalogParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceCatalogId, catalogDefaultLanguageId, nameMap,
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

			return (com.liferay.commerce.product.model.CommerceCatalog)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CommerceCatalogServiceHttp.class);
	private static final Class<?>[] _addCommerceCatalogParameterTypes0 = new Class[] {
			java.util.Map.class, String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _deleteCommerceCatalogParameterTypes1 = new Class[] {
			long.class
		};
	private static final Class<?>[] _fetchCommerceCatalogParameterTypes2 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getCommerceCatalogGroupParameterTypes3 = new Class[] {
			long.class
		};
	private static final Class<?>[] _searchCommerceCatalogsParameterTypes4 = new Class[] {
			long.class
		};
	private static final Class<?>[] _searchCommerceCatalogsParameterTypes5 = new Class[] {
			long.class, String.class, int.class, int.class,
			com.liferay.portal.kernel.search.Sort.class
		};
	private static final Class<?>[] _searchCommerceCatalogsCountParameterTypes6 = new Class[] {
			long.class, String.class
		};
	private static final Class<?>[] _updateCommerceCatalogParameterTypes7 = new Class[] {
			long.class, String.class, java.util.Map.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
}