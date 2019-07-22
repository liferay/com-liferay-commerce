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

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.price.list.service.CommercePriceListCommerceAccountGroupRelServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>CommercePriceListCommerceAccountGroupRelServiceUtil</code> service
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
 * @see CommercePriceListCommerceAccountGroupRelServiceSoap
 * @generated
 */
@ProviderType
public class CommercePriceListCommerceAccountGroupRelServiceHttp {

	public static com.liferay.commerce.price.list.model.
		CommercePriceListCommerceAccountGroupRel
				addCommercePriceListCommerceAccountGroupRel(
					HttpPrincipal httpPrincipal, long commercePriceListId,
					long commerceAccountGroupId, int order,
					com.liferay.portal.kernel.service.ServiceContext
						serviceContext)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommercePriceListCommerceAccountGroupRelServiceUtil.class,
				"addCommercePriceListCommerceAccountGroupRel",
				_addCommercePriceListCommerceAccountGroupRelParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commercePriceListId, commerceAccountGroupId, order,
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
				CommercePriceListCommerceAccountGroupRel)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteCommercePriceListCommerceAccountGroupRel(
			HttpPrincipal httpPrincipal,
			long commercePriceListCommerceAccountGroupRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommercePriceListCommerceAccountGroupRelServiceUtil.class,
				"deleteCommercePriceListCommerceAccountGroupRel",
				_deleteCommercePriceListCommerceAccountGroupRelParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commercePriceListCommerceAccountGroupRelId);

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

	public static com.liferay.commerce.price.list.model.
		CommercePriceListCommerceAccountGroupRel
				fetchCommercePriceListCommerceAccountGroupRel(
					HttpPrincipal httpPrincipal, long commercePriceListId,
					long commerceAccountGroupId)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommercePriceListCommerceAccountGroupRelServiceUtil.class,
				"fetchCommercePriceListCommerceAccountGroupRel",
				_fetchCommercePriceListCommerceAccountGroupRelParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commercePriceListId, commerceAccountGroupId);

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
				CommercePriceListCommerceAccountGroupRel)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.price.list.model.
		CommercePriceListCommerceAccountGroupRel
				getCommercePriceListCommerceAccountGroupRel(
					HttpPrincipal httpPrincipal,
					long commercePriceListCommerceAccoungGroupRelId)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommercePriceListCommerceAccountGroupRelServiceUtil.class,
				"getCommercePriceListCommerceAccountGroupRel",
				_getCommercePriceListCommerceAccountGroupRelParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commercePriceListCommerceAccoungGroupRelId);

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
				CommercePriceListCommerceAccountGroupRel)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List
		<com.liferay.commerce.price.list.model.
			CommercePriceListCommerceAccountGroupRel>
					getCommercePriceListCommerceAccountGroupRels(
						HttpPrincipal httpPrincipal, long commercePriceListId)
				throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommercePriceListCommerceAccountGroupRelServiceUtil.class,
				"getCommercePriceListCommerceAccountGroupRels",
				_getCommercePriceListCommerceAccountGroupRelsParameterTypes4);

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
					CommercePriceListCommerceAccountGroupRel>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List
		<com.liferay.commerce.price.list.model.
			CommercePriceListCommerceAccountGroupRel>
					getCommercePriceListCommerceAccountGroupRels(
						HttpPrincipal httpPrincipal, long commercePriceListId,
						int start, int end,
						com.liferay.portal.kernel.util.OrderByComparator
							<com.liferay.commerce.price.list.model.
								CommercePriceListCommerceAccountGroupRel>
									orderByComparator)
				throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommercePriceListCommerceAccountGroupRelServiceUtil.class,
				"getCommercePriceListCommerceAccountGroupRels",
				_getCommercePriceListCommerceAccountGroupRelsParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commercePriceListId, start, end, orderByComparator);

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
					CommercePriceListCommerceAccountGroupRel>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getCommercePriceListCommerceAccountGroupRelsCount(
			HttpPrincipal httpPrincipal, long commercePriceListId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommercePriceListCommerceAccountGroupRelServiceUtil.class,
				"getCommercePriceListCommerceAccountGroupRelsCount",
				_getCommercePriceListCommerceAccountGroupRelsCountParameterTypes6);

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

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.price.list.model.
		CommercePriceListCommerceAccountGroupRel
				updateCommercePriceListCommerceAccountGroupRel(
					HttpPrincipal httpPrincipal,
					long commercePriceListCommerceAccountGroupRelId, int order,
					com.liferay.portal.kernel.service.ServiceContext
						serviceContext)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommercePriceListCommerceAccountGroupRelServiceUtil.class,
				"updateCommercePriceListCommerceAccountGroupRel",
				_updateCommercePriceListCommerceAccountGroupRelParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commercePriceListCommerceAccountGroupRelId, order,
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
				CommercePriceListCommerceAccountGroupRel)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		CommercePriceListCommerceAccountGroupRelServiceHttp.class);

	private static final Class<?>[]
		_addCommercePriceListCommerceAccountGroupRelParameterTypes0 =
			new Class[] {
				long.class, long.class, int.class,
				com.liferay.portal.kernel.service.ServiceContext.class
			};
	private static final Class<?>[]
		_deleteCommercePriceListCommerceAccountGroupRelParameterTypes1 =
			new Class[] {long.class};
	private static final Class<?>[]
		_fetchCommercePriceListCommerceAccountGroupRelParameterTypes2 =
			new Class[] {long.class, long.class};
	private static final Class<?>[]
		_getCommercePriceListCommerceAccountGroupRelParameterTypes3 =
			new Class[] {long.class};
	private static final Class<?>[]
		_getCommercePriceListCommerceAccountGroupRelsParameterTypes4 =
			new Class[] {long.class};
	private static final Class<?>[]
		_getCommercePriceListCommerceAccountGroupRelsParameterTypes5 =
			new Class[] {
				long.class, int.class, int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class
			};
	private static final Class<?>[]
		_getCommercePriceListCommerceAccountGroupRelsCountParameterTypes6 =
			new Class[] {long.class};
	private static final Class<?>[]
		_updateCommercePriceListCommerceAccountGroupRelParameterTypes7 =
			new Class[] {
				long.class, int.class,
				com.liferay.portal.kernel.service.ServiceContext.class
			};

}