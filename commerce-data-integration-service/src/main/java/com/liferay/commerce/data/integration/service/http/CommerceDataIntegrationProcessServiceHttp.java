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

package com.liferay.commerce.data.integration.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.data.integration.service.CommerceDataIntegrationProcessServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>CommerceDataIntegrationProcessServiceUtil</code> service
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
 * @see CommerceDataIntegrationProcessServiceSoap
 * @generated
 */
@ProviderType
public class CommerceDataIntegrationProcessServiceHttp {

	public static
		com.liferay.commerce.data.integration.model.
			CommerceDataIntegrationProcess addCommerceDataIntegrationProcess(
					HttpPrincipal httpPrincipal, long userId, String name,
					String type,
					com.liferay.portal.kernel.util.UnicodeProperties
						typeSettingsProperties)
				throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceDataIntegrationProcessServiceUtil.class,
				"addCommerceDataIntegrationProcess",
				_addCommerceDataIntegrationProcessParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, name, type, typeSettingsProperties);

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

			return (com.liferay.commerce.data.integration.model.
				CommerceDataIntegrationProcess)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteCommerceDataIntegrationProcess(
			HttpPrincipal httpPrincipal, long commerceDataIntegrationProcessId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceDataIntegrationProcessServiceUtil.class,
				"deleteCommerceDataIntegrationProcess",
				_deleteCommerceDataIntegrationProcessParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceDataIntegrationProcessId);

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

	public static
		com.liferay.commerce.data.integration.model.
			CommerceDataIntegrationProcess fetchCommerceDataIntegrationProcess(
					HttpPrincipal httpPrincipal,
					long commerceDataIntegrationProcessId)
				throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceDataIntegrationProcessServiceUtil.class,
				"fetchCommerceDataIntegrationProcess",
				_fetchCommerceDataIntegrationProcessParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceDataIntegrationProcessId);

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

			return (com.liferay.commerce.data.integration.model.
				CommerceDataIntegrationProcess)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static
		com.liferay.commerce.data.integration.model.
			CommerceDataIntegrationProcess getCommerceDataIntegrationProcess(
					HttpPrincipal httpPrincipal,
					long commerceDataIntegrationProcessId)
				throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceDataIntegrationProcessServiceUtil.class,
				"getCommerceDataIntegrationProcess",
				_getCommerceDataIntegrationProcessParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceDataIntegrationProcessId);

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

			return (com.liferay.commerce.data.integration.model.
				CommerceDataIntegrationProcess)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List
		<com.liferay.commerce.data.integration.model.
			CommerceDataIntegrationProcess> getCommerceDataIntegrationProcesses(
					HttpPrincipal httpPrincipal, long companyId, int start,
					int end)
				throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceDataIntegrationProcessServiceUtil.class,
				"getCommerceDataIntegrationProcesses",
				_getCommerceDataIntegrationProcessesParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, start, end);

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
				<com.liferay.commerce.data.integration.model.
					CommerceDataIntegrationProcess>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getCommerceDataIntegrationProcessesCount(
			HttpPrincipal httpPrincipal, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceDataIntegrationProcessServiceUtil.class,
				"getCommerceDataIntegrationProcessesCount",
				_getCommerceDataIntegrationProcessesCountParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId);

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

	public static
		com.liferay.commerce.data.integration.model.
			CommerceDataIntegrationProcess updateCommerceDataIntegrationProcess(
					HttpPrincipal httpPrincipal,
					long commerceDataIntegrationProcessId, String name,
					com.liferay.portal.kernel.util.UnicodeProperties
						typeSettingsProperties)
				throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceDataIntegrationProcessServiceUtil.class,
				"updateCommerceDataIntegrationProcess",
				_updateCommerceDataIntegrationProcessParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceDataIntegrationProcessId, name,
				typeSettingsProperties);

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

			return (com.liferay.commerce.data.integration.model.
				CommerceDataIntegrationProcess)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static
		com.liferay.commerce.data.integration.model.
			CommerceDataIntegrationProcess
					updateCommerceDataIntegrationProcessTrigger(
						HttpPrincipal httpPrincipal,
						long commerceDataIntegrationProcessId, boolean active,
						String cronExpression, int startDateMonth,
						int startDateDay, int startDateYear, int startDateHour,
						int startDateMinute, int endDateMonth, int endDateDay,
						int endDateYear, int endDateHour, int endDateMinute,
						boolean neverEnd)
				throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceDataIntegrationProcessServiceUtil.class,
				"updateCommerceDataIntegrationProcessTrigger",
				_updateCommerceDataIntegrationProcessTriggerParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceDataIntegrationProcessId, active,
				cronExpression, startDateMonth, startDateDay, startDateYear,
				startDateHour, startDateMinute, endDateMonth, endDateDay,
				endDateYear, endDateHour, endDateMinute, neverEnd);

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

			return (com.liferay.commerce.data.integration.model.
				CommerceDataIntegrationProcess)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		CommerceDataIntegrationProcessServiceHttp.class);

	private static final Class<?>[]
		_addCommerceDataIntegrationProcessParameterTypes0 = new Class[] {
			long.class, String.class, String.class,
			com.liferay.portal.kernel.util.UnicodeProperties.class
		};
	private static final Class<?>[]
		_deleteCommerceDataIntegrationProcessParameterTypes1 = new Class[] {
			long.class
		};
	private static final Class<?>[]
		_fetchCommerceDataIntegrationProcessParameterTypes2 = new Class[] {
			long.class
		};
	private static final Class<?>[]
		_getCommerceDataIntegrationProcessParameterTypes3 = new Class[] {
			long.class
		};
	private static final Class<?>[]
		_getCommerceDataIntegrationProcessesParameterTypes4 = new Class[] {
			long.class, int.class, int.class
		};
	private static final Class<?>[]
		_getCommerceDataIntegrationProcessesCountParameterTypes5 = new Class[] {
			long.class
		};
	private static final Class<?>[]
		_updateCommerceDataIntegrationProcessParameterTypes6 = new Class[] {
			long.class, String.class,
			com.liferay.portal.kernel.util.UnicodeProperties.class
		};
	private static final Class<?>[]
		_updateCommerceDataIntegrationProcessTriggerParameterTypes7 =
			new Class[] {
				long.class, boolean.class, String.class, int.class, int.class,
				int.class, int.class, int.class, int.class, int.class,
				int.class, int.class, int.class, boolean.class
			};

}