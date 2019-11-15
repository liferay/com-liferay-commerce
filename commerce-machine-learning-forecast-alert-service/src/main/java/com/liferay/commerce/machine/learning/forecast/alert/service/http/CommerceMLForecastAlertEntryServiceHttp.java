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

package com.liferay.commerce.machine.learning.forecast.alert.service.http;

import com.liferay.commerce.machine.learning.forecast.alert.service.CommerceMLForecastAlertEntryServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>CommerceMLForecastAlertEntryServiceUtil</code> service
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
 * @author Riccardo Ferrari
 * @see CommerceMLForecastAlertEntryServiceSoap
 * @generated
 */
public class CommerceMLForecastAlertEntryServiceHttp {

	public static java.util.List
		<com.liferay.commerce.machine.learning.forecast.alert.model.
			CommerceMLForecastAlertEntry>
					getAboveThresholdCommerceMLForecastAlertEntries(
						HttpPrincipal httpPrincipal, long companyId,
						long userId, int status, double relativeChangeThreshold,
						int start, int end)
				throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceMLForecastAlertEntryServiceUtil.class,
				"getAboveThresholdCommerceMLForecastAlertEntries",
				_getAboveThresholdCommerceMLForecastAlertEntriesParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, userId, status, relativeChangeThreshold,
				start, end);

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
				<com.liferay.commerce.machine.learning.forecast.alert.model.
					CommerceMLForecastAlertEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getAboveThresholdCommerceMLForecastAlertEntriesCount(
			HttpPrincipal httpPrincipal, long companyId, long userId,
			int status, double relativeChangeThreshold)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceMLForecastAlertEntryServiceUtil.class,
				"getAboveThresholdCommerceMLForecastAlertEntriesCount",
				_getAboveThresholdCommerceMLForecastAlertEntriesCountParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, userId, status, relativeChangeThreshold);

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

	public static java.util.List
		<com.liferay.commerce.machine.learning.forecast.alert.model.
			CommerceMLForecastAlertEntry>
					getBelowThresholdCommerceMLForecastAlertEntries(
						HttpPrincipal httpPrincipal, long companyId,
						long userId, int status, double relativeChangeThreshold,
						int start, int end)
				throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceMLForecastAlertEntryServiceUtil.class,
				"getBelowThresholdCommerceMLForecastAlertEntries",
				_getBelowThresholdCommerceMLForecastAlertEntriesParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, userId, status, relativeChangeThreshold,
				start, end);

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
				<com.liferay.commerce.machine.learning.forecast.alert.model.
					CommerceMLForecastAlertEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getBelowThresholdCommerceMLForecastAlertEntriesCount(
			HttpPrincipal httpPrincipal, long companyId, long userId,
			int status, double relativeChangeThreshold)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceMLForecastAlertEntryServiceUtil.class,
				"getBelowThresholdCommerceMLForecastAlertEntriesCount",
				_getBelowThresholdCommerceMLForecastAlertEntriesCountParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, userId, status, relativeChangeThreshold);

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

	public static java.util.List
		<com.liferay.commerce.machine.learning.forecast.alert.model.
			CommerceMLForecastAlertEntry> getCommerceMLForecastAlertEntries(
					HttpPrincipal httpPrincipal, long companyId, long userId,
					int status, int start, int end)
				throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceMLForecastAlertEntryServiceUtil.class,
				"getCommerceMLForecastAlertEntries",
				_getCommerceMLForecastAlertEntriesParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, userId, status, start, end);

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
				<com.liferay.commerce.machine.learning.forecast.alert.model.
					CommerceMLForecastAlertEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getCommerceMLForecastAlertEntriesCount(
			HttpPrincipal httpPrincipal, long companyId, long userId,
			int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceMLForecastAlertEntryServiceUtil.class,
				"getCommerceMLForecastAlertEntriesCount",
				_getCommerceMLForecastAlertEntriesCountParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, userId, status);

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

	public static com.liferay.commerce.machine.learning.forecast.alert.model.
		CommerceMLForecastAlertEntry updateStatus(
				HttpPrincipal httpPrincipal, long userId,
				long commerceMLForecastAlertEntryId, int status)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceMLForecastAlertEntryServiceUtil.class, "updateStatus",
				_updateStatusParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, commerceMLForecastAlertEntryId, status);

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

			return (com.liferay.commerce.machine.learning.forecast.alert.model.
				CommerceMLForecastAlertEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		CommerceMLForecastAlertEntryServiceHttp.class);

	private static final Class<?>[]
		_getAboveThresholdCommerceMLForecastAlertEntriesParameterTypes0 =
			new Class[] {
				long.class, long.class, int.class, double.class, int.class,
				int.class
			};
	private static final Class<?>[]
		_getAboveThresholdCommerceMLForecastAlertEntriesCountParameterTypes1 =
			new Class[] {long.class, long.class, int.class, double.class};
	private static final Class<?>[]
		_getBelowThresholdCommerceMLForecastAlertEntriesParameterTypes2 =
			new Class[] {
				long.class, long.class, int.class, double.class, int.class,
				int.class
			};
	private static final Class<?>[]
		_getBelowThresholdCommerceMLForecastAlertEntriesCountParameterTypes3 =
			new Class[] {long.class, long.class, int.class, double.class};
	private static final Class<?>[]
		_getCommerceMLForecastAlertEntriesParameterTypes4 = new Class[] {
			long.class, long.class, int.class, int.class, int.class
		};
	private static final Class<?>[]
		_getCommerceMLForecastAlertEntriesCountParameterTypes5 = new Class[] {
			long.class, long.class, int.class
		};
	private static final Class<?>[] _updateStatusParameterTypes6 = new Class[] {
		long.class, long.class, int.class
	};

}