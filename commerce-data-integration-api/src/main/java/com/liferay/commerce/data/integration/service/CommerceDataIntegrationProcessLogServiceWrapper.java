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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceDataIntegrationProcessLogService}.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceDataIntegrationProcessLogService
 * @generated
 */
public class CommerceDataIntegrationProcessLogServiceWrapper
	implements CommerceDataIntegrationProcessLogService,
			   ServiceWrapper<CommerceDataIntegrationProcessLogService> {

	public CommerceDataIntegrationProcessLogServiceWrapper(
		CommerceDataIntegrationProcessLogService
			commerceDataIntegrationProcessLogService) {

		_commerceDataIntegrationProcessLogService =
			commerceDataIntegrationProcessLogService;
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceDataIntegrationProcessLogServiceUtil} to access the commerce data integration process log remote service. Add custom service methods to <code>com.liferay.commerce.data.integration.service.impl.CommerceDataIntegrationProcessLogServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Override
	public com.liferay.commerce.data.integration.model.
		CommerceDataIntegrationProcessLog addCommerceDataIntegrationProcessLog(
				long userId, long commerceDataIntegrationProcessId,
				String error, String output, int status,
				java.util.Date startDate, java.util.Date endDate)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDataIntegrationProcessLogService.
			addCommerceDataIntegrationProcessLog(
				userId, commerceDataIntegrationProcessId, error, output, status,
				startDate, endDate);
	}

	@Override
	public void deleteCommerceDataIntegrationProcessLog(
			long cDataIntegrationProcessLogId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commerceDataIntegrationProcessLogService.
			deleteCommerceDataIntegrationProcessLog(
				cDataIntegrationProcessLogId);
	}

	@Override
	public com.liferay.commerce.data.integration.model.
		CommerceDataIntegrationProcessLog getCommerceDataIntegrationProcessLog(
				long cDataIntegrationProcessLogId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDataIntegrationProcessLogService.
			getCommerceDataIntegrationProcessLog(cDataIntegrationProcessLogId);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.data.integration.model.
			CommerceDataIntegrationProcessLog>
					getCommerceDataIntegrationProcessLogs(
						long commerceDataIntegrationProcessId, int start,
						int end)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDataIntegrationProcessLogService.
			getCommerceDataIntegrationProcessLogs(
				commerceDataIntegrationProcessId, start, end);
	}

	@Override
	public int getCommerceDataIntegrationProcessLogsCount(
			long commerceDataIntegrationProcessId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDataIntegrationProcessLogService.
			getCommerceDataIntegrationProcessLogsCount(
				commerceDataIntegrationProcessId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceDataIntegrationProcessLogService.
			getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.commerce.data.integration.model.
		CommerceDataIntegrationProcessLog
				updateCommerceDataIntegrationProcessLog(
					long cDataIntegrationProcessLogId, String error,
					String output, int status, java.util.Date endDate)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDataIntegrationProcessLogService.
			updateCommerceDataIntegrationProcessLog(
				cDataIntegrationProcessLogId, error, output, status, endDate);
	}

	@Override
	public CommerceDataIntegrationProcessLogService getWrappedService() {
		return _commerceDataIntegrationProcessLogService;
	}

	@Override
	public void setWrappedService(
		CommerceDataIntegrationProcessLogService
			commerceDataIntegrationProcessLogService) {

		_commerceDataIntegrationProcessLogService =
			commerceDataIntegrationProcessLogService;
	}

	private CommerceDataIntegrationProcessLogService
		_commerceDataIntegrationProcessLogService;

}