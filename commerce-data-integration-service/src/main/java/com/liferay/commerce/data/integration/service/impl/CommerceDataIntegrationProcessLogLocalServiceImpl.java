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

package com.liferay.commerce.data.integration.service.impl;

import com.liferay.commerce.data.integration.model.CommerceDataIntegrationProcessLog;
import com.liferay.commerce.data.integration.service.base.CommerceDataIntegrationProcessLogLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;

import java.util.Date;
import java.util.List;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceDataIntegrationProcessLogLocalServiceImpl
	extends CommerceDataIntegrationProcessLogLocalServiceBaseImpl {

	@Override
	public CommerceDataIntegrationProcessLog
			addCommerceDataIntegrationProcessLog(
				long userId, long commerceDataIntegrationProcessId,
				String error, String output, int status, Date startDate,
				Date endDate)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		CommerceDataIntegrationProcessLog commerceDataIntegrationProcessLog =
			commerceDataIntegrationProcessLogPersistence.create(
				counterLocalService.increment());

		commerceDataIntegrationProcessLog.setCompanyId(user.getCompanyId());
		commerceDataIntegrationProcessLog.setUserId(user.getUserId());
		commerceDataIntegrationProcessLog.setUserName(user.getFullName());
		commerceDataIntegrationProcessLog.setCDataIntegrationProcessId(
			commerceDataIntegrationProcessId);
		commerceDataIntegrationProcessLog.setError(error);
		commerceDataIntegrationProcessLog.setOutput(output);
		commerceDataIntegrationProcessLog.setStatus(status);
		commerceDataIntegrationProcessLog.setStartDate(startDate);
		commerceDataIntegrationProcessLog.setEndDate(endDate);

		return commerceDataIntegrationProcessLogPersistence.update(
			commerceDataIntegrationProcessLog);
	}

	@Override
	public void deleteCommerceDataIntegrationProcessLogs(
		long commerceDataIntegrationProcessId) {

		commerceDataIntegrationProcessLogPersistence.
			removeByCDataIntegrationProcessId(commerceDataIntegrationProcessId);
	}

	@Override
	public List<CommerceDataIntegrationProcessLog>
		getCommerceDataIntegrationProcessLogs(
			long commerceDataIntegrationProcessId, int start, int end) {

		return commerceDataIntegrationProcessLogPersistence.
			findByCDataIntegrationProcessId(
				commerceDataIntegrationProcessId, start, end);
	}

	@Override
	public int getCommerceDataIntegrationProcessLogsCount(
		long commerceDataIntegrationProcessId) {

		return commerceDataIntegrationProcessLogPersistence.
			countByCDataIntegrationProcessId(commerceDataIntegrationProcessId);
	}

	@Override
	public CommerceDataIntegrationProcessLog
			updateCommerceDataIntegrationProcessLog(
				long cDataIntegrationProcessLogId, String error, String output,
				int status, Date endDate)
		throws PortalException {

		CommerceDataIntegrationProcessLog commerceDataIntegrationProcessLog =
			commerceDataIntegrationProcessLogPersistence.findByPrimaryKey(
				cDataIntegrationProcessLogId);

		commerceDataIntegrationProcessLog.setError(error);
		commerceDataIntegrationProcessLog.setOutput(output);
		commerceDataIntegrationProcessLog.setStatus(status);
		commerceDataIntegrationProcessLog.setEndDate(endDate);

		return commerceDataIntegrationProcessLogPersistence.update(
			commerceDataIntegrationProcessLog);
	}

}