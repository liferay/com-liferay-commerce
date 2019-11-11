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

package com.liferay.commerce.machine.learning.forecast.alert.service.impl;

import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountLocalService;
import com.liferay.commerce.machine.learning.forecast.alert.constants.CommerceMLForecastAlertActionKeys;
import com.liferay.commerce.machine.learning.forecast.alert.constants.CommerceMLForecastAlertConstants;
import com.liferay.commerce.machine.learning.forecast.alert.model.CommerceMLForecastAlertEntry;
import com.liferay.commerce.machine.learning.forecast.alert.service.base.CommerceMLForecastAlertEntryServiceBaseImpl;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermissionFactory;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.List;

/**
 * @author Riccardo Ferrari
 */
public class CommerceMLForecastAlertEntryServiceImpl
	extends CommerceMLForecastAlertEntryServiceBaseImpl {

	@Override
	public List<CommerceMLForecastAlertEntry>
			getAboveThresholdCommerceMLForecastAlertEntries(
				long companyId, long userId, int status, double relativeChange,
				int start, int end)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), GroupConstants.DEFAULT_LIVE_GROUP_ID,
			CommerceMLForecastAlertActionKeys.VIEW_ALERTS);

		long[] commerceAccountIds = _getUserCommerceAccountIds(userId);

		return commerceMLForecastAlertEntryLocalService.
			getAboveThresholdCommerceMLForecastAlertEntries(
				companyId, commerceAccountIds, relativeChange, status, start,
				end);
	}

	@Override
	public int getAboveThresholdCommerceMLForecastAlertEntriesCount(
			long companyId, long userId, int status, double relativeChange)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), GroupConstants.DEFAULT_LIVE_GROUP_ID,
			CommerceMLForecastAlertActionKeys.VIEW_ALERTS);

		long[] commerceAccountIds = _getUserCommerceAccountIds(userId);

		return commerceMLForecastAlertEntryLocalService.
			getAboveThresholdCommerceMLForecastAlertEntriesCount(
				companyId, commerceAccountIds, relativeChange, status);
	}

	@Override
	public List<CommerceMLForecastAlertEntry>
			getBelowThresholdCommerceMLForecastAlertEntries(
				long companyId, long userId, int status, double relativeChange,
				int start, int end)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), GroupConstants.DEFAULT_LIVE_GROUP_ID,
			CommerceMLForecastAlertActionKeys.VIEW_ALERTS);

		long[] commerceAccountIds = _getUserCommerceAccountIds(userId);

		return commerceMLForecastAlertEntryLocalService.
			getBelowThresholdCommerceMLForecastAlertEntries(
				companyId, commerceAccountIds, relativeChange, status, start,
				end);
	}

	@Override
	public int getBelowThresholdCommerceMLForecastAlertEntriesCount(
			long companyId, long userId, int status, double relativeChange)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), GroupConstants.DEFAULT_LIVE_GROUP_ID,
			CommerceMLForecastAlertActionKeys.VIEW_ALERTS);

		long[] commerceAccountIds = _getUserCommerceAccountIds(userId);

		return commerceMLForecastAlertEntryLocalService.
			getBelowThresholdCommerceMLForecastAlertEntriesCount(
				companyId, commerceAccountIds, relativeChange, status);
	}

	@Override
	public List<CommerceMLForecastAlertEntry> getCommerceMLForecastAlertEntries(
			long companyId, long userId, int status, int start, int end)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), GroupConstants.DEFAULT_LIVE_GROUP_ID,
			CommerceMLForecastAlertActionKeys.VIEW_ALERTS);

		long[] commerceAccountIds = _getUserCommerceAccountIds(userId);

		return commerceMLForecastAlertEntryLocalService.
			getCommerceMLForecastAlertEntries(
				companyId, commerceAccountIds, status, start, end);
	}

	@Override
	public int getCommerceMLForecastAlertEntriesCount(
			long companyId, long userId, int status)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), GroupConstants.DEFAULT_LIVE_GROUP_ID,
			CommerceMLForecastAlertActionKeys.VIEW_ALERTS);

		long[] commerceAccountIds = _getUserCommerceAccountIds(userId);

		return commerceMLForecastAlertEntryLocalService.
			getCommerceMLForecastAlertEntriesCount(
				companyId, commerceAccountIds, status);
	}

	@Override
	public CommerceMLForecastAlertEntry updateStatus(
			long userId, long commerceMLForecastAlertEntryId, int status)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), GroupConstants.DEFAULT_LIVE_GROUP_ID,
			CommerceMLForecastAlertActionKeys.MANAGE_ALERT_STATUS);

		return commerceMLForecastAlertEntryLocalService.updateStatus(
			userId, commerceMLForecastAlertEntryId, status);
	}

	private long[] _getUserCommerceAccountIds(long userId) {
		List<CommerceAccount> commerceAccounts =
			_commerceAccountLocalService.getUserCommerceAccounts(
				userId, null, CommerceAccountConstants.SITE_TYPE_B2C_B2B, null,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		return ListUtil.toLongArray(
			commerceAccounts, CommerceAccount::getCommerceAccountId);
	}

	private static volatile PortletResourcePermission
		_portletResourcePermission =
			PortletResourcePermissionFactory.getInstance(
				CommerceMLForecastAlertEntryServiceImpl.class,
				"_portletResourcePermission",
				CommerceMLForecastAlertConstants.RESOURCE_NAME);

	@ServiceReference(type = CommerceAccountLocalService.class)
	private CommerceAccountLocalService _commerceAccountLocalService;

}