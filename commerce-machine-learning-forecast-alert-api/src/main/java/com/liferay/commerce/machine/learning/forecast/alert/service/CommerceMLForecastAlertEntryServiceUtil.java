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

package com.liferay.commerce.machine.learning.forecast.alert.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for CommerceMLForecastAlertEntry. This utility wraps
 * <code>com.liferay.commerce.machine.learning.forecast.alert.service.impl.CommerceMLForecastAlertEntryServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Riccardo Ferrari
 * @see CommerceMLForecastAlertEntryService
 * @generated
 */
public class CommerceMLForecastAlertEntryServiceUtil {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.machine.learning.forecast.alert.service.impl.CommerceMLForecastAlertEntryServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceMLForecastAlertEntryServiceUtil} to access the commerce ml forecast alert entry remote service. Add custom service methods to <code>com.liferay.commerce.machine.learning.forecast.alert.service.impl.CommerceMLForecastAlertEntryServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static java.util.List
		<com.liferay.commerce.machine.learning.forecast.alert.model.
			CommerceMLForecastAlertEntry>
					getAboveThresholdCommerceMLForecastAlertEntries(
						long companyId, long userId, int status,
						double relativeChangeThreshold, int start, int end)
				throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getAboveThresholdCommerceMLForecastAlertEntries(
			companyId, userId, status, relativeChangeThreshold, start, end);
	}

	public static int getAboveThresholdCommerceMLForecastAlertEntriesCount(
			long companyId, long userId, int status,
			double relativeChangeThreshold)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().
			getAboveThresholdCommerceMLForecastAlertEntriesCount(
				companyId, userId, status, relativeChangeThreshold);
	}

	public static java.util.List
		<com.liferay.commerce.machine.learning.forecast.alert.model.
			CommerceMLForecastAlertEntry>
					getBelowThresholdCommerceMLForecastAlertEntries(
						long companyId, long userId, int status,
						double relativeChangeThreshold, int start, int end)
				throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getBelowThresholdCommerceMLForecastAlertEntries(
			companyId, userId, status, relativeChangeThreshold, start, end);
	}

	public static int getBelowThresholdCommerceMLForecastAlertEntriesCount(
			long companyId, long userId, int status,
			double relativeChangeThreshold)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().
			getBelowThresholdCommerceMLForecastAlertEntriesCount(
				companyId, userId, status, relativeChangeThreshold);
	}

	public static java.util.List
		<com.liferay.commerce.machine.learning.forecast.alert.model.
			CommerceMLForecastAlertEntry> getCommerceMLForecastAlertEntries(
					long companyId, long userId, int status, int start, int end)
				throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommerceMLForecastAlertEntries(
			companyId, userId, status, start, end);
	}

	public static int getCommerceMLForecastAlertEntriesCount(
			long companyId, long userId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommerceMLForecastAlertEntriesCount(
			companyId, userId, status);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.commerce.machine.learning.forecast.alert.model.
		CommerceMLForecastAlertEntry updateStatus(
				long userId, long commerceMLForecastAlertEntryId, int status)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateStatus(
			userId, commerceMLForecastAlertEntryId, status);
	}

	public static CommerceMLForecastAlertEntryService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CommerceMLForecastAlertEntryService,
		 CommerceMLForecastAlertEntryService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CommerceMLForecastAlertEntryService.class);

		ServiceTracker
			<CommerceMLForecastAlertEntryService,
			 CommerceMLForecastAlertEntryService> serviceTracker =
				new ServiceTracker
					<CommerceMLForecastAlertEntryService,
					 CommerceMLForecastAlertEntryService>(
						 bundle.getBundleContext(),
						 CommerceMLForecastAlertEntryService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}