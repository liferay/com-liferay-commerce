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

import com.liferay.commerce.discount.service.CommerceDiscountServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>CommerceDiscountServiceUtil</code> service
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
 * @see CommerceDiscountServiceSoap
 * @generated
 */
public class CommerceDiscountServiceHttp {

	public static com.liferay.commerce.discount.model.CommerceDiscount
			addCommerceDiscount(
				HttpPrincipal httpPrincipal, long userId, String title,
				String target, boolean useCouponCode, String couponCode,
				boolean usePercentage,
				java.math.BigDecimal maximumDiscountAmount,
				java.math.BigDecimal level1, java.math.BigDecimal level2,
				java.math.BigDecimal level3, java.math.BigDecimal level4,
				String limitationType, int limitationTimes, boolean active,
				int displayDateMonth, int displayDateDay, int displayDateYear,
				int displayDateHour, int displayDateMinute,
				int expirationDateMonth, int expirationDateDay,
				int expirationDateYear, int expirationDateHour,
				int expirationDateMinute, boolean neverExpire,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceDiscountServiceUtil.class, "addCommerceDiscount",
				_addCommerceDiscountParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, title, target, useCouponCode, couponCode,
				usePercentage, maximumDiscountAmount, level1, level2, level3,
				level4, limitationType, limitationTimes, active,
				displayDateMonth, displayDateDay, displayDateYear,
				displayDateHour, displayDateMinute, expirationDateMonth,
				expirationDateDay, expirationDateYear, expirationDateHour,
				expirationDateMinute, neverExpire, serviceContext);

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

			return (com.liferay.commerce.discount.model.CommerceDiscount)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteCommerceDiscount(
			HttpPrincipal httpPrincipal, long commerceDiscountId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceDiscountServiceUtil.class, "deleteCommerceDiscount",
				_deleteCommerceDiscountParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceDiscountId);

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

	public static com.liferay.commerce.discount.model.CommerceDiscount
			fetchByExternalReferenceCode(
				HttpPrincipal httpPrincipal, long companyId,
				String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceDiscountServiceUtil.class,
				"fetchByExternalReferenceCode",
				_fetchByExternalReferenceCodeParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, externalReferenceCode);

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

			return (com.liferay.commerce.discount.model.CommerceDiscount)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.discount.model.CommerceDiscount
			fetchCommerceDiscount(
				HttpPrincipal httpPrincipal, long commerceDiscountId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceDiscountServiceUtil.class, "fetchCommerceDiscount",
				_fetchCommerceDiscountParameterTypes3);

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

			return (com.liferay.commerce.discount.model.CommerceDiscount)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.discount.model.CommerceDiscount
			getCommerceDiscount(
				HttpPrincipal httpPrincipal, long commerceDiscountId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceDiscountServiceUtil.class, "getCommerceDiscount",
				_getCommerceDiscountParameterTypes4);

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

			return (com.liferay.commerce.discount.model.CommerceDiscount)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List
		<com.liferay.commerce.discount.model.CommerceDiscount>
				getCommerceDiscounts(
					HttpPrincipal httpPrincipal, long companyId,
					String couponCode)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceDiscountServiceUtil.class, "getCommerceDiscounts",
				_getCommerceDiscountsParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, couponCode);

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
				<com.liferay.commerce.discount.model.CommerceDiscount>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getCommerceDiscountsCount(
			HttpPrincipal httpPrincipal, long companyId, String couponCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceDiscountServiceUtil.class, "getCommerceDiscountsCount",
				_getCommerceDiscountsCountParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, couponCode);

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

	public static com.liferay.portal.kernel.search.BaseModelSearchResult
		<com.liferay.commerce.discount.model.CommerceDiscount>
				searchCommerceDiscounts(
					HttpPrincipal httpPrincipal, long companyId,
					String keywords, int status, int start, int end,
					com.liferay.portal.kernel.search.Sort sort)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceDiscountServiceUtil.class, "searchCommerceDiscounts",
				_searchCommerceDiscountsParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, keywords, status, start, end, sort);

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

			return (com.liferay.portal.kernel.search.BaseModelSearchResult
				<com.liferay.commerce.discount.model.CommerceDiscount>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.discount.model.CommerceDiscount
			updateCommerceDiscount(
				HttpPrincipal httpPrincipal, long commerceDiscountId,
				String title, String target, boolean useCouponCode,
				String couponCode, boolean usePercentage,
				java.math.BigDecimal maximumDiscountAmount,
				java.math.BigDecimal level1, java.math.BigDecimal level2,
				java.math.BigDecimal level3, java.math.BigDecimal level4,
				String limitationType, int limitationTimes, boolean active,
				int displayDateMonth, int displayDateDay, int displayDateYear,
				int displayDateHour, int displayDateMinute,
				int expirationDateMonth, int expirationDateDay,
				int expirationDateYear, int expirationDateHour,
				int expirationDateMinute, boolean neverExpire,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceDiscountServiceUtil.class, "updateCommerceDiscount",
				_updateCommerceDiscountParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceDiscountId, title, target, useCouponCode,
				couponCode, usePercentage, maximumDiscountAmount, level1,
				level2, level3, level4, limitationType, limitationTimes, active,
				displayDateMonth, displayDateDay, displayDateYear,
				displayDateHour, displayDateMinute, expirationDateMonth,
				expirationDateDay, expirationDateYear, expirationDateHour,
				expirationDateMinute, neverExpire, serviceContext);

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

			return (com.liferay.commerce.discount.model.CommerceDiscount)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.discount.model.CommerceDiscount
			upsertCommerceDiscount(
				HttpPrincipal httpPrincipal, long userId,
				long commerceDiscountId, String title, String target,
				boolean useCouponCode, String couponCode, boolean usePercentage,
				java.math.BigDecimal maximumDiscountAmount,
				java.math.BigDecimal level1, java.math.BigDecimal level2,
				java.math.BigDecimal level3, java.math.BigDecimal level4,
				String limitationType, int limitationTimes, boolean active,
				int displayDateMonth, int displayDateDay, int displayDateYear,
				int displayDateHour, int displayDateMinute,
				int expirationDateMonth, int expirationDateDay,
				int expirationDateYear, int expirationDateHour,
				int expirationDateMinute, String externalReferenceCode,
				boolean neverExpire,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceDiscountServiceUtil.class, "upsertCommerceDiscount",
				_upsertCommerceDiscountParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, commerceDiscountId, title, target,
				useCouponCode, couponCode, usePercentage, maximumDiscountAmount,
				level1, level2, level3, level4, limitationType, limitationTimes,
				active, displayDateMonth, displayDateDay, displayDateYear,
				displayDateHour, displayDateMinute, expirationDateMonth,
				expirationDateDay, expirationDateYear, expirationDateHour,
				expirationDateMinute, externalReferenceCode, neverExpire,
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

			return (com.liferay.commerce.discount.model.CommerceDiscount)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		CommerceDiscountServiceHttp.class);

	private static final Class<?>[] _addCommerceDiscountParameterTypes0 =
		new Class[] {
			long.class, String.class, String.class, boolean.class, String.class,
			boolean.class, java.math.BigDecimal.class,
			java.math.BigDecimal.class, java.math.BigDecimal.class,
			java.math.BigDecimal.class, java.math.BigDecimal.class,
			String.class, int.class, boolean.class, int.class, int.class,
			int.class, int.class, int.class, int.class, int.class, int.class,
			int.class, int.class, boolean.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _deleteCommerceDiscountParameterTypes1 =
		new Class[] {long.class};
	private static final Class<?>[]
		_fetchByExternalReferenceCodeParameterTypes2 = new Class[] {
			long.class, String.class
		};
	private static final Class<?>[] _fetchCommerceDiscountParameterTypes3 =
		new Class[] {long.class};
	private static final Class<?>[] _getCommerceDiscountParameterTypes4 =
		new Class[] {long.class};
	private static final Class<?>[] _getCommerceDiscountsParameterTypes5 =
		new Class[] {long.class, String.class};
	private static final Class<?>[] _getCommerceDiscountsCountParameterTypes6 =
		new Class[] {long.class, String.class};
	private static final Class<?>[] _searchCommerceDiscountsParameterTypes7 =
		new Class[] {
			long.class, String.class, int.class, int.class, int.class,
			com.liferay.portal.kernel.search.Sort.class
		};
	private static final Class<?>[] _updateCommerceDiscountParameterTypes8 =
		new Class[] {
			long.class, String.class, String.class, boolean.class, String.class,
			boolean.class, java.math.BigDecimal.class,
			java.math.BigDecimal.class, java.math.BigDecimal.class,
			java.math.BigDecimal.class, java.math.BigDecimal.class,
			String.class, int.class, boolean.class, int.class, int.class,
			int.class, int.class, int.class, int.class, int.class, int.class,
			int.class, int.class, boolean.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _upsertCommerceDiscountParameterTypes9 =
		new Class[] {
			long.class, long.class, String.class, String.class, boolean.class,
			String.class, boolean.class, java.math.BigDecimal.class,
			java.math.BigDecimal.class, java.math.BigDecimal.class,
			java.math.BigDecimal.class, java.math.BigDecimal.class,
			String.class, int.class, boolean.class, int.class, int.class,
			int.class, int.class, int.class, int.class, int.class, int.class,
			int.class, int.class, String.class, boolean.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};

}