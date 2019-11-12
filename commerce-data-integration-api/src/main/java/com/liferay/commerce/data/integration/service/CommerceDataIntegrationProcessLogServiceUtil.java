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

package com.liferay.commerce.data.integration.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for CommerceDataIntegrationProcessLog. This utility wraps
 * <code>com.liferay.commerce.data.integration.service.impl.CommerceDataIntegrationProcessLogServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceDataIntegrationProcessLogService
 * @generated
 */
public class CommerceDataIntegrationProcessLogServiceUtil {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.data.integration.service.impl.CommerceDataIntegrationProcessLogServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceDataIntegrationProcessLogServiceUtil} to access the commerce data integration process log remote service. Add custom service methods to <code>com.liferay.commerce.data.integration.service.impl.CommerceDataIntegrationProcessLogServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static com.liferay.commerce.data.integration.model.
		CommerceDataIntegrationProcessLog addCommerceDataIntegrationProcessLog(
				long userId, long commerceDataIntegrationProcessId,
				String error, String output, int status,
				java.util.Date startDate, java.util.Date endDate)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCommerceDataIntegrationProcessLog(
			userId, commerceDataIntegrationProcessId, error, output, status,
			startDate, endDate);
	}

	public static void deleteCommerceDataIntegrationProcessLog(
			long cDataIntegrationProcessLogId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteCommerceDataIntegrationProcessLog(
			cDataIntegrationProcessLogId);
	}

	public static com.liferay.commerce.data.integration.model.
		CommerceDataIntegrationProcessLog getCommerceDataIntegrationProcessLog(
				long cDataIntegrationProcessLogId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommerceDataIntegrationProcessLog(
			cDataIntegrationProcessLogId);
	}

	public static java.util.List
		<com.liferay.commerce.data.integration.model.
			CommerceDataIntegrationProcessLog>
					getCommerceDataIntegrationProcessLogs(
						long commerceDataIntegrationProcessId, int start,
						int end)
				throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommerceDataIntegrationProcessLogs(
			commerceDataIntegrationProcessId, start, end);
	}

	public static int getCommerceDataIntegrationProcessLogsCount(
			long commerceDataIntegrationProcessId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommerceDataIntegrationProcessLogsCount(
			commerceDataIntegrationProcessId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.commerce.data.integration.model.
		CommerceDataIntegrationProcessLog
				updateCommerceDataIntegrationProcessLog(
					long cDataIntegrationProcessLogId, String error,
					String output, int status, java.util.Date endDate)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateCommerceDataIntegrationProcessLog(
			cDataIntegrationProcessLogId, error, output, status, endDate);
	}

	public static CommerceDataIntegrationProcessLogService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CommerceDataIntegrationProcessLogService,
		 CommerceDataIntegrationProcessLogService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CommerceDataIntegrationProcessLogService.class);

		ServiceTracker
			<CommerceDataIntegrationProcessLogService,
			 CommerceDataIntegrationProcessLogService> serviceTracker =
				new ServiceTracker
					<CommerceDataIntegrationProcessLogService,
					 CommerceDataIntegrationProcessLogService>(
						 bundle.getBundleContext(),
						 CommerceDataIntegrationProcessLogService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}