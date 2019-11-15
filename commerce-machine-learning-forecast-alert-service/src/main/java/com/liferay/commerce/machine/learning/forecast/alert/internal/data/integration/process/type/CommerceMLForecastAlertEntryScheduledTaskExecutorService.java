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

package com.liferay.commerce.machine.learning.forecast.alert.internal.data.integration.process.type;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountLocalService;
import com.liferay.commerce.data.integration.model.CommerceDataIntegrationProcess;
import com.liferay.commerce.data.integration.model.CommerceDataIntegrationProcessLog;
import com.liferay.commerce.data.integration.service.CommerceDataIntegrationProcessLocalService;
import com.liferay.commerce.data.integration.service.CommerceDataIntegrationProcessLogLocalService;
import com.liferay.commerce.data.integration.service.ScheduledTaskExecutorService;
import com.liferay.commerce.machine.learning.forecast.alert.constants.CommerceMLForecastAlertConstants;
import com.liferay.commerce.machine.learning.forecast.alert.service.CommerceMLForecastAlertEntryLocalService;
import com.liferay.commerce.machine.learning.forecast.model.CommerceAccountCommerceMLForecast;
import com.liferay.commerce.machine.learning.forecast.service.CommerceAccountCommerceMLForecastService;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskConstants;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.io.IOException;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Ferrari
 */
@Component(
	immediate = true,
	property = "data.integration.service.executor.key=" + CommerceMLForecastAlertEntryProcessType.KEY,
	service = ScheduledTaskExecutorService.class
)
public class CommerceMLForecastAlertEntryScheduledTaskExecutorService
	implements ScheduledTaskExecutorService {

	@Override
	public String getName() {
		return CommerceMLForecastAlertEntryProcessType.KEY;
	}

	@Override
	public void runProcess(long commerceDataIntegrationProcessId)
		throws IOException, PortalException {

		CommerceDataIntegrationProcess commerceDataIntegrationProcess =
			_commerceDataIntegrationProcessLocalService.
				getCommerceDataIntegrationProcess(
					commerceDataIntegrationProcessId);

		CommerceDataIntegrationProcessLog commerceDataIntegrationProcessLog =
			_commerceDataIntegrationProcessLogLocalService.
				addCommerceDataIntegrationProcessLog(
					commerceDataIntegrationProcess.getUserId(),
					commerceDataIntegrationProcess.
						getCommerceDataIntegrationProcessId(),
					null, null, BackgroundTaskConstants.STATUS_IN_PROGRESS,
					new Date(), null);

		try {
			executeProcess(commerceDataIntegrationProcess);

			commerceDataIntegrationProcessLog.setEndDate(new Date());

			commerceDataIntegrationProcessLog.setStatus(
				BackgroundTaskConstants.STATUS_SUCCESSFUL);

			_commerceDataIntegrationProcessLogLocalService.
				updateCommerceDataIntegrationProcessLog(
					commerceDataIntegrationProcessLog);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}

			_commerceDataIntegrationProcessLogLocalService.
				updateCommerceDataIntegrationProcessLog(
					commerceDataIntegrationProcessLog.
						getCommerceDataIntegrationProcessLogId(),
					e.getMessage(), null, BackgroundTaskConstants.STATUS_FAILED,
					new Date());
		}
	}

	protected void executeProcess(
			CommerceDataIntegrationProcess commerceDataIntegrationProcess)
		throws Exception {

		UnicodeProperties contextProperties =
			commerceDataIntegrationProcess.getTypeSettingsProperties();

		Date commerceMLForecastAlertEntryCheckDate = GetterUtil.getDate(
			contextProperties.getProperty(
				CommerceMLForecastAlertConstants.
					COMMERCE_ML_FORECAST_ALERT_ENTRY_CHECK_DATE),
			DateFormatFactoryUtil.getSimpleDateFormat(_DATE_FORMAT));

		if (commerceMLForecastAlertEntryCheckDate != null) {
			if (_log.isWarnEnabled()) {
				_log.warn("Using manually set Forecast Alert Check Date");
			}
		}
		else {
			commerceMLForecastAlertEntryCheckDate = new Date();
		}

		float commerceMLForecastAlertEntryThreshold = GetterUtil.getFloat(
			contextProperties.get(
				CommerceMLForecastAlertConstants.
					COMMERCE_ML_FORECAST_ALERT_ENTRY_THRESHOLD),
			_DEFAULT_COMMERCE_ML_FORECAST_ALERT_ENTRY_THRESHOLD);

		List<CommerceAccount> commerceAccounts =
			_commerceAccountLocalService.getCommerceAccounts(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		long[] commerceAccountIds = ListUtil.toLongArray(
			commerceAccounts, CommerceAccount::getCommerceAccountId);

		List<CommerceAccountCommerceMLForecast>
			commerceAccountCommerceMLForecasts =
				_commerceAccountCommerceMLForecastService.
					getMonthlyRevenueCommerceAccountCommerceMLForecasts(
						commerceDataIntegrationProcess.getCompanyId(),
						commerceAccountIds,
						commerceMLForecastAlertEntryCheckDate, 1, 0);

		for (CommerceAccountCommerceMLForecast
				commerceAccountCommerceMLForecast :
					commerceAccountCommerceMLForecasts) {

			float actual = commerceAccountCommerceMLForecast.getActual();

			float forecast = commerceAccountCommerceMLForecast.getForecast();

			if ((actual == Float.MIN_VALUE) || (forecast == Float.MIN_VALUE)) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						String.format(
							"Missing actual or forecast value for %s, skipping",
							commerceAccountCommerceMLForecast.getForecastId()));
				}

				continue;
			}

			float delta = actual - forecast;

			float percentChange = (delta / forecast) * 100;

			if ((percentChange > commerceMLForecastAlertEntryThreshold) ||
				(percentChange < -commerceMLForecastAlertEntryThreshold)) {

				if (_log.isWarnEnabled()) {
					_log.warn(
						String.format(
							"Actual value exceed threshold %s: change " +
								"percent: %s",
							commerceMLForecastAlertEntryThreshold,
							percentChange));
				}

				_commerceMLForecastAlertEntryLocalService.
					upsertCommerceMLForecastAlertEntry(
						commerceDataIntegrationProcess.getCompanyId(),
						commerceDataIntegrationProcess.getUserId(),
						commerceAccountCommerceMLForecast.
							getCommerceAccountId(),
						commerceAccountCommerceMLForecast.getTimestamp(),
						actual, forecast, percentChange);
			}
		}
	}

	private static final String _DATE_FORMAT = "yyyyMMdd";

	private static final float
		_DEFAULT_COMMERCE_ML_FORECAST_ALERT_ENTRY_THRESHOLD = 20.0F;

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceMLForecastAlertEntryScheduledTaskExecutorService.class);

	@Reference
	private CommerceAccountCommerceMLForecastService
		_commerceAccountCommerceMLForecastService;

	@Reference
	private CommerceAccountLocalService _commerceAccountLocalService;

	@Reference
	private CommerceDataIntegrationProcessLocalService
		_commerceDataIntegrationProcessLocalService;

	@Reference
	private CommerceDataIntegrationProcessLogLocalService
		_commerceDataIntegrationProcessLogLocalService;

	@Reference
	private CommerceMLForecastAlertEntryLocalService
		_commerceMLForecastAlertEntryLocalService;

}