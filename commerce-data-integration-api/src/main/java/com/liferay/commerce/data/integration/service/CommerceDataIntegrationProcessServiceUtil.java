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
 * Provides the remote service utility for CommerceDataIntegrationProcess. This utility wraps
 * <code>com.liferay.commerce.data.integration.service.impl.CommerceDataIntegrationProcessServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceDataIntegrationProcessService
 * @generated
 */
public class CommerceDataIntegrationProcessServiceUtil {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.data.integration.service.impl.CommerceDataIntegrationProcessServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceDataIntegrationProcessServiceUtil} to access the commerce data integration process remote service. Add custom service methods to <code>com.liferay.commerce.data.integration.service.impl.CommerceDataIntegrationProcessServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static
		com.liferay.commerce.data.integration.model.
			CommerceDataIntegrationProcess addCommerceDataIntegrationProcess(
					long userId, String name, String type,
					com.liferay.portal.kernel.util.UnicodeProperties
						typeSettingsProperties)
				throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCommerceDataIntegrationProcess(
			userId, name, type, typeSettingsProperties);
	}

	public static void deleteCommerceDataIntegrationProcess(
			long commerceDataIntegrationProcessId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteCommerceDataIntegrationProcess(
			commerceDataIntegrationProcessId);
	}

	public static
		com.liferay.commerce.data.integration.model.
			CommerceDataIntegrationProcess fetchCommerceDataIntegrationProcess(
					long commerceDataIntegrationProcessId)
				throws com.liferay.portal.kernel.exception.PortalException {

		return getService().fetchCommerceDataIntegrationProcess(
			commerceDataIntegrationProcessId);
	}

	public static
		com.liferay.commerce.data.integration.model.
			CommerceDataIntegrationProcess getCommerceDataIntegrationProcess(
					long commerceDataIntegrationProcessId)
				throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommerceDataIntegrationProcess(
			commerceDataIntegrationProcessId);
	}

	public static java.util.List
		<com.liferay.commerce.data.integration.model.
			CommerceDataIntegrationProcess> getCommerceDataIntegrationProcesses(
					long companyId, int start, int end)
				throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommerceDataIntegrationProcesses(
			companyId, start, end);
	}

	public static int getCommerceDataIntegrationProcessesCount(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommerceDataIntegrationProcessesCount(companyId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static
		com.liferay.commerce.data.integration.model.
			CommerceDataIntegrationProcess updateCommerceDataIntegrationProcess(
					long commerceDataIntegrationProcessId, String name,
					com.liferay.portal.kernel.util.UnicodeProperties
						typeSettingsProperties)
				throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateCommerceDataIntegrationProcess(
			commerceDataIntegrationProcessId, name, typeSettingsProperties);
	}

	public static
		com.liferay.commerce.data.integration.model.
			CommerceDataIntegrationProcess
					updateCommerceDataIntegrationProcessTrigger(
						long commerceDataIntegrationProcessId, boolean active,
						String cronExpression, int startDateMonth,
						int startDateDay, int startDateYear, int startDateHour,
						int startDateMinute, int endDateMonth, int endDateDay,
						int endDateYear, int endDateHour, int endDateMinute,
						boolean neverEnd)
				throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateCommerceDataIntegrationProcessTrigger(
			commerceDataIntegrationProcessId, active, cronExpression,
			startDateMonth, startDateDay, startDateYear, startDateHour,
			startDateMinute, endDateMonth, endDateDay, endDateYear, endDateHour,
			endDateMinute, neverEnd);
	}

	public static CommerceDataIntegrationProcessService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CommerceDataIntegrationProcessService,
		 CommerceDataIntegrationProcessService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CommerceDataIntegrationProcessService.class);

		ServiceTracker
			<CommerceDataIntegrationProcessService,
			 CommerceDataIntegrationProcessService> serviceTracker =
				new ServiceTracker
					<CommerceDataIntegrationProcessService,
					 CommerceDataIntegrationProcessService>(
						 bundle.getBundleContext(),
						 CommerceDataIntegrationProcessService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}