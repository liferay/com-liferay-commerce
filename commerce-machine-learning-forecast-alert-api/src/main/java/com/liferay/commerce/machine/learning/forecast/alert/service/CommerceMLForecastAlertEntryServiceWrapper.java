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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceMLForecastAlertEntryService}.
 *
 * @author Riccardo Ferrari
 * @see CommerceMLForecastAlertEntryService
 * @generated
 */
public class CommerceMLForecastAlertEntryServiceWrapper
	implements CommerceMLForecastAlertEntryService,
			   ServiceWrapper<CommerceMLForecastAlertEntryService> {

	public CommerceMLForecastAlertEntryServiceWrapper(
		CommerceMLForecastAlertEntryService
			commerceMLForecastAlertEntryService) {

		_commerceMLForecastAlertEntryService =
			commerceMLForecastAlertEntryService;
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceMLForecastAlertEntryServiceUtil} to access the commerce ml forecast alert entry remote service. Add custom service methods to <code>com.liferay.commerce.machine.learning.forecast.alert.service.impl.CommerceMLForecastAlertEntryServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Override
	public java.util.List
		<com.liferay.commerce.machine.learning.forecast.alert.model.
			CommerceMLForecastAlertEntry>
					getAboveThresholdCommerceMLForecastAlertEntries(
						long companyId, long userId, int status,
						double relativeChangeThreshold, int start, int end)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceMLForecastAlertEntryService.
			getAboveThresholdCommerceMLForecastAlertEntries(
				companyId, userId, status, relativeChangeThreshold, start, end);
	}

	@Override
	public int getAboveThresholdCommerceMLForecastAlertEntriesCount(
			long companyId, long userId, int status,
			double relativeChangeThreshold)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceMLForecastAlertEntryService.
			getAboveThresholdCommerceMLForecastAlertEntriesCount(
				companyId, userId, status, relativeChangeThreshold);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.machine.learning.forecast.alert.model.
			CommerceMLForecastAlertEntry>
					getBelowThresholdCommerceMLForecastAlertEntries(
						long companyId, long userId, int status,
						double relativeChangeThreshold, int start, int end)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceMLForecastAlertEntryService.
			getBelowThresholdCommerceMLForecastAlertEntries(
				companyId, userId, status, relativeChangeThreshold, start, end);
	}

	@Override
	public int getBelowThresholdCommerceMLForecastAlertEntriesCount(
			long companyId, long userId, int status,
			double relativeChangeThreshold)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceMLForecastAlertEntryService.
			getBelowThresholdCommerceMLForecastAlertEntriesCount(
				companyId, userId, status, relativeChangeThreshold);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.machine.learning.forecast.alert.model.
			CommerceMLForecastAlertEntry> getCommerceMLForecastAlertEntries(
					long companyId, long userId, int status, int start, int end)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceMLForecastAlertEntryService.
			getCommerceMLForecastAlertEntries(
				companyId, userId, status, start, end);
	}

	@Override
	public int getCommerceMLForecastAlertEntriesCount(
			long companyId, long userId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceMLForecastAlertEntryService.
			getCommerceMLForecastAlertEntriesCount(companyId, userId, status);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceMLForecastAlertEntryService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.commerce.machine.learning.forecast.alert.model.
		CommerceMLForecastAlertEntry updateStatus(
				long userId, long commerceMLForecastAlertEntryId, int status)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceMLForecastAlertEntryService.updateStatus(
			userId, commerceMLForecastAlertEntryId, status);
	}

	@Override
	public CommerceMLForecastAlertEntryService getWrappedService() {
		return _commerceMLForecastAlertEntryService;
	}

	@Override
	public void setWrappedService(
		CommerceMLForecastAlertEntryService
			commerceMLForecastAlertEntryService) {

		_commerceMLForecastAlertEntryService =
			commerceMLForecastAlertEntryService;
	}

	private CommerceMLForecastAlertEntryService
		_commerceMLForecastAlertEntryService;

}