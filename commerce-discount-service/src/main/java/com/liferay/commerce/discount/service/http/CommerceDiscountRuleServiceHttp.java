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

package com.liferay.commerce.discount.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.discount.service.CommerceDiscountRuleServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>CommerceDiscountRuleServiceUtil</code> service
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
 * @see CommerceDiscountRuleServiceSoap
 * @generated
 */
@ProviderType
public class CommerceDiscountRuleServiceHttp {

	public static com.liferay.commerce.discount.model.CommerceDiscountRule
			addCommerceDiscountRule(
				HttpPrincipal httpPrincipal, long commerceDiscountId,
				String type, String typeSettings,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceDiscountRuleServiceUtil.class,
				"addCommerceDiscountRule",
				_addCommerceDiscountRuleParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceDiscountId, type, typeSettings,
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

			return (com.liferay.commerce.discount.model.CommerceDiscountRule)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteCommerceDiscountRule(
			HttpPrincipal httpPrincipal, long commerceDiscountRuleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceDiscountRuleServiceUtil.class,
				"deleteCommerceDiscountRule",
				_deleteCommerceDiscountRuleParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceDiscountRuleId);

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

	public static com.liferay.commerce.discount.model.CommerceDiscountRule
			fetchCommerceDiscountRule(
				HttpPrincipal httpPrincipal, long commerceDiscountRuleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceDiscountRuleServiceUtil.class,
				"fetchCommerceDiscountRule",
				_fetchCommerceDiscountRuleParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceDiscountRuleId);

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

			return (com.liferay.commerce.discount.model.CommerceDiscountRule)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.discount.model.CommerceDiscountRule
			getCommerceDiscountRule(
				HttpPrincipal httpPrincipal, long commerceDiscountRuleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceDiscountRuleServiceUtil.class,
				"getCommerceDiscountRule",
				_getCommerceDiscountRuleParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceDiscountRuleId);

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

			return (com.liferay.commerce.discount.model.CommerceDiscountRule)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List
		<com.liferay.commerce.discount.model.CommerceDiscountRule>
				getCommerceDiscountRules(
					HttpPrincipal httpPrincipal, long commerceDiscountId,
					int start, int end,
					com.liferay.portal.kernel.util.OrderByComparator
						<com.liferay.commerce.discount.model.
							CommerceDiscountRule> orderByComparator)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceDiscountRuleServiceUtil.class,
				"getCommerceDiscountRules",
				_getCommerceDiscountRulesParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceDiscountId, start, end, orderByComparator);

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
				<com.liferay.commerce.discount.model.CommerceDiscountRule>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getCommerceDiscountRulesCount(
			HttpPrincipal httpPrincipal, long commerceDiscountId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceDiscountRuleServiceUtil.class,
				"getCommerceDiscountRulesCount",
				_getCommerceDiscountRulesCountParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceDiscountId);

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

	public static com.liferay.commerce.discount.model.CommerceDiscountRule
			updateCommerceDiscountRule(
				HttpPrincipal httpPrincipal, long commerceDiscountRuleId,
				String type, String typeSettings)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceDiscountRuleServiceUtil.class,
				"updateCommerceDiscountRule",
				_updateCommerceDiscountRuleParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceDiscountRuleId, type, typeSettings);

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

			return (com.liferay.commerce.discount.model.CommerceDiscountRule)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		CommerceDiscountRuleServiceHttp.class);

	private static final Class<?>[] _addCommerceDiscountRuleParameterTypes0 =
		new Class[] {
			long.class, String.class, String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _deleteCommerceDiscountRuleParameterTypes1 =
		new Class[] {long.class};
	private static final Class<?>[] _fetchCommerceDiscountRuleParameterTypes2 =
		new Class[] {long.class};
	private static final Class<?>[] _getCommerceDiscountRuleParameterTypes3 =
		new Class[] {long.class};
	private static final Class<?>[] _getCommerceDiscountRulesParameterTypes4 =
		new Class[] {
			long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[]
		_getCommerceDiscountRulesCountParameterTypes5 = new Class[] {
			long.class
		};
	private static final Class<?>[] _updateCommerceDiscountRuleParameterTypes6 =
		new Class[] {long.class, String.class, String.class};

}