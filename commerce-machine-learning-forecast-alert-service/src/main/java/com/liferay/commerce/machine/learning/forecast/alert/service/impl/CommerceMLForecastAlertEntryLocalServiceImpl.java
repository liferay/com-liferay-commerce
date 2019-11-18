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

import com.liferay.commerce.machine.learning.forecast.alert.constants.CommerceMLForecastAlertConstants;
import com.liferay.commerce.machine.learning.forecast.alert.model.CommerceMLForecastAlertEntry;
import com.liferay.commerce.machine.learning.forecast.alert.service.base.CommerceMLForecastAlertEntryLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;

import java.util.Date;
import java.util.List;

/**
 * @author Riccardo Ferrari
 */
public class CommerceMLForecastAlertEntryLocalServiceImpl
	extends CommerceMLForecastAlertEntryLocalServiceBaseImpl {

	@Override
	public List<CommerceMLForecastAlertEntry>
		getAboveThresholdCommerceMLForecastAlertEntries(
			long companyId, long[] commerceAccountIds, double relativeChange,
			int status, int start, int end) {

		return commerceMLForecastAlertEntryPersistence.findByC_C_GtRc_S(
			companyId, commerceAccountIds, relativeChange, status, start, end);
	}

	@Override
	public int getAboveThresholdCommerceMLForecastAlertEntriesCount(
		long companyId, long[] commerceAccountIds, double relativeChange,
		int status) {

		return commerceMLForecastAlertEntryPersistence.countByC_C_GtRc_S(
			companyId, commerceAccountIds, relativeChange, status);
	}

	@Override
	public List<CommerceMLForecastAlertEntry>
		getBelowThresholdCommerceMLForecastAlertEntries(
			long companyId, long[] commerceAccountIds, double relativeChange,
			int status, int start, int end) {

		return commerceMLForecastAlertEntryPersistence.findByC_C_LtRc_S(
			companyId, commerceAccountIds, relativeChange, status, start, end);
	}

	@Override
	public int getBelowThresholdCommerceMLForecastAlertEntriesCount(
		long companyId, long[] commerceAccountIds, double relativeChange,
		int status) {

		return commerceMLForecastAlertEntryPersistence.countByC_C_LtRc_S(
			companyId, commerceAccountIds, relativeChange, status);
	}

	@Override
	public List<CommerceMLForecastAlertEntry> getCommerceMLForecastAlertEntries(
		long companyId, long[] commerceAccountIds, int status, int start,
		int end) {

		return commerceMLForecastAlertEntryPersistence.findByC_C_S(
			companyId, commerceAccountIds, status, start, end);
	}

	@Override
	public int getCommerceMLForecastAlertEntriesCount(
		long companyId, long[] commerceAccountIds, int status) {

		return commerceMLForecastAlertEntryPersistence.countByC_C_S(
			companyId, commerceAccountIds, status);
	}

	@Override
	public CommerceMLForecastAlertEntry updateStatus(
			long userId, long commerceMLForecastAlertEntryId, int status)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		CommerceMLForecastAlertEntry commerceMLForecastAlertEntry =
			commerceMLForecastAlertEntryPersistence.findByPrimaryKey(
				commerceMLForecastAlertEntryId);

		commerceMLForecastAlertEntry.setUserId(userId);
		commerceMLForecastAlertEntry.setUserName(user.getFullName());
		commerceMLForecastAlertEntry.setModifiedDate(new Date());
		commerceMLForecastAlertEntry.setStatus(status);

		commerceMLForecastAlertEntryPersistence.update(
			commerceMLForecastAlertEntry);

		return commerceMLForecastAlertEntry;
	}

	@Indexable(type = IndexableType.REINDEX)
	public CommerceMLForecastAlertEntry upsertCommerceMLForecastAlertEntry(
			long companyId, long userId, long commerceAccountId, Date timestamp,
			float actual, float forecast, float relativeChange)
		throws PortalException {

		CommerceMLForecastAlertEntry commerceMLForecastAlertEntry =
			commerceMLForecastAlertEntryPersistence.findByC_C_T(
				companyId, commerceAccountId, timestamp);

		User user = userLocalService.getUser(userId);

		if (commerceMLForecastAlertEntry == null) {
			long commerceMLForecastAlertEntryId =
				counterLocalService.increment();

			commerceMLForecastAlertEntry =
				commerceMLForecastAlertEntryPersistence.create(
					commerceMLForecastAlertEntryId);

			commerceMLForecastAlertEntry.setCompanyId(companyId);
			commerceMLForecastAlertEntry.setUserId(userId);
			commerceMLForecastAlertEntry.setUserName(user.getFullName());
			commerceMLForecastAlertEntry.setCreateDate(new Date());
			commerceMLForecastAlertEntry.setCommerceAccountId(
				commerceAccountId);
			commerceMLForecastAlertEntry.setTimestamp(timestamp);
		}

		commerceMLForecastAlertEntry.setModifiedDate(new Date());
		commerceMLForecastAlertEntry.setActual(actual);
		commerceMLForecastAlertEntry.setForecast(forecast);
		commerceMLForecastAlertEntry.setRelativeChange(relativeChange);
		commerceMLForecastAlertEntry.setStatus(
			CommerceMLForecastAlertConstants.STATUS_NEW);

		commerceMLForecastAlertEntryPersistence.update(
			commerceMLForecastAlertEntry);

		// Resources

		resourceLocalService.addResources(
			user.getCompanyId(), GroupConstants.DEFAULT_LIVE_GROUP_ID,
			user.getUserId(), CommerceMLForecastAlertEntry.class.getName(),
			commerceMLForecastAlertEntry.getCommerceAccountId(), false, false,
			false);

		return commerceMLForecastAlertEntry;
	}

}