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

import com.liferay.commerce.data.integration.constants.CommerceDataIntegrationActionKeys;
import com.liferay.commerce.data.integration.model.CommerceDataIntegrationProcess;
import com.liferay.commerce.data.integration.service.base.CommerceDataIntegrationProcessServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.service.permission.PortalPermissionUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.util.List;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceDataIntegrationProcessServiceImpl
	extends CommerceDataIntegrationProcessServiceBaseImpl {

	@Override
	public CommerceDataIntegrationProcess addCommerceDataIntegrationProcess(
			long userId, String name, String type,
			UnicodeProperties typeSettingsProperties)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommerceDataIntegrationActionKeys.
				ADD_COMMERCE_DATA_INTEGRATION_PROCESS);

		return commerceDataIntegrationProcessLocalService.
			addCommerceDataIntegrationProcess(
				userId, name, type, typeSettingsProperties, false);
	}

	@Override
	public void deleteCommerceDataIntegrationProcess(
			long commerceDataIntegrationProcessId)
		throws PortalException {

		_commerceDataIntegrationProcessModelResourcePermission.check(
			getPermissionChecker(), commerceDataIntegrationProcessId,
			ActionKeys.DELETE);

		commerceDataIntegrationProcessLocalService.
			deleteCommerceDataIntegrationProcess(
				commerceDataIntegrationProcessId);
	}

	@Override
	public CommerceDataIntegrationProcess fetchCommerceDataIntegrationProcess(
			long commerceDataIntegrationProcessId)
		throws PortalException {

		CommerceDataIntegrationProcess commerceDataIntegrationProcess =
			commerceDataIntegrationProcessLocalService.
				fetchCommerceDataIntegrationProcess(
					commerceDataIntegrationProcessId);

		if (commerceDataIntegrationProcess != null) {
			_commerceDataIntegrationProcessModelResourcePermission.check(
				getPermissionChecker(), commerceDataIntegrationProcess,
				ActionKeys.VIEW);
		}

		return commerceDataIntegrationProcess;
	}

	@Override
	public CommerceDataIntegrationProcess getCommerceDataIntegrationProcess(
			long commerceDataIntegrationProcessId)
		throws PortalException {

		_commerceDataIntegrationProcessModelResourcePermission.check(
			getPermissionChecker(), commerceDataIntegrationProcessId,
			ActionKeys.VIEW);

		return commerceDataIntegrationProcessLocalService.
			getCommerceDataIntegrationProcess(commerceDataIntegrationProcessId);
	}

	@Override
	public List<CommerceDataIntegrationProcess>
			getCommerceDataIntegrationProcesses(
				long companyId, int start, int end)
		throws PortalException {

		return commerceDataIntegrationProcessPersistence.filterFindByCompanyId(
			companyId, start, end);
	}

	@Override
	public int getCommerceDataIntegrationProcessesCount(long companyId)
		throws PortalException {

		return commerceDataIntegrationProcessPersistence.filterCountByCompanyId(
			companyId);
	}

	@Override
	public CommerceDataIntegrationProcess updateCommerceDataIntegrationProcess(
			long commerceDataIntegrationProcessId, String name,
			UnicodeProperties typeSettingsProperties)
		throws PortalException {

		_commerceDataIntegrationProcessModelResourcePermission.check(
			getPermissionChecker(), commerceDataIntegrationProcessId,
			ActionKeys.UPDATE);

		return commerceDataIntegrationProcessLocalService.
			updateCommerceDataIntegrationProcess(
				commerceDataIntegrationProcessId, name, typeSettingsProperties);
	}

	@Override
	public CommerceDataIntegrationProcess
			updateCommerceDataIntegrationProcessTrigger(
				long commerceDataIntegrationProcessId, boolean active,
				String cronExpression, int startDateMonth, int startDateDay,
				int startDateYear, int startDateHour, int startDateMinute,
				int endDateMonth, int endDateDay, int endDateYear,
				int endDateHour, int endDateMinute, boolean neverEnd)
		throws PortalException {

		_commerceDataIntegrationProcessModelResourcePermission.check(
			getPermissionChecker(), commerceDataIntegrationProcessId,
			ActionKeys.UPDATE);

		return commerceDataIntegrationProcessLocalService.
			updateCommerceDataIntegrationProcessTrigger(
				commerceDataIntegrationProcessId, active, cronExpression,
				startDateMonth, startDateDay, startDateYear, startDateHour,
				startDateMinute, endDateMonth, endDateDay, endDateYear,
				endDateHour, endDateMinute, neverEnd);
	}

	private static volatile ModelResourcePermission
		<CommerceDataIntegrationProcess>
			_commerceDataIntegrationProcessModelResourcePermission =
				ModelResourcePermissionFactory.getInstance(
					CommerceDataIntegrationProcessServiceImpl.class,
					"_commerceDataIntegrationProcessModelResourcePermission",
					CommerceDataIntegrationProcess.class);

}