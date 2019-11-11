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

package com.liferay.commerce.machine.learning.internal.forecast.data.integration;

import com.liferay.commerce.data.integration.model.CommerceDataIntegrationProcess;
import com.liferay.commerce.data.integration.service.CommerceDataIntegrationProcessLocalService;
import com.liferay.commerce.data.integration.service.ScheduledTaskExecutorService;
import com.liferay.commerce.machine.learning.internal.data.integration.CommerceMLScheduledTaskExecutorService;
import com.liferay.commerce.machine.learning.internal.search.api.CommerceMLIndexer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Ferrari
 */
public abstract class BaseForecastScheduledTaskExecutorService
	implements ScheduledTaskExecutorService {

	@Override
	public void runProcess(long commerceDataIntegrationProcessId)
		throws IOException, PortalException {

		CommerceDataIntegrationProcess commerceDataIntegrationProcess =
			commerceDataIntegrationProcessLocalService.
				getCommerceDataIntegrationProcess(
					commerceDataIntegrationProcessId);

		commerceMLScheduledTaskExecutorService.executeScheduledTask(
			commerceDataIntegrationProcess.getUserId(),
			commerceDataIntegrationProcess.
				getCommerceDataIntegrationProcessId(),
			getContextProperties(commerceDataIntegrationProcess));
	}

	protected Map<String, String> getContextProperties(
		CommerceDataIntegrationProcess commerceDataIntegrationProcess) {

		Map<String, String> contextProperties = new HashMap<>();

		String commerceMLForecastIndexName = commerceMLIndexer.getIndexName(
			commerceDataIntegrationProcess.getCompanyId());

		contextProperties.put(
			"COMMERCE_ML_FORECAST_INDEX_NAME", commerceMLForecastIndexName);

		UnicodeProperties typeSettingsProperties =
			commerceDataIntegrationProcess.getTypeSettingsProperties();

		contextProperties.put(
			"COMMERCE_ML_FORECAST_PERIOD",
			typeSettingsProperties.getProperty(
				COMMERCE_ML_FORECAST_PERIOD, getPeriod()));

		contextProperties.put("COMMERCE_ML_FORECAST_SCOPE", getScope());

		contextProperties.put(
			"COMMERCE_ML_FORECAST_TARGET",
			typeSettingsProperties.getProperty(
				COMMERCE_ML_FORECAST_TARGET, getTarget()));

		contextProperties.put("COMMERCE_ML_PROCESS_TYPE", getName());

		String liferayIndexName =
			getLiferayIndexNamePrefix() +
				commerceDataIntegrationProcess.getCompanyId();

		contextProperties.put("LIFERAY_INDEX_NAME", liferayIndexName);

		return contextProperties;
	}

	protected abstract String getLiferayIndexNamePrefix();

	protected abstract String getPeriod();

	protected abstract String getScope();

	protected abstract String getTarget();

	protected static final String COMMERCE_ML_FORECAST_PERIOD =
		"commerce.ml.forecast.period";

	protected static final String COMMERCE_ML_FORECAST_TARGET =
		"commerce.ml.forecast.target";

	@Reference
	protected CommerceDataIntegrationProcessLocalService
		commerceDataIntegrationProcessLocalService;

	@Reference(
		target = "(component.name=com.liferay.commerce.machine.learning.internal.forecast.search.index.CommerceMLForecastIndexer)"
	)
	protected CommerceMLIndexer commerceMLIndexer;

	@Reference
	protected CommerceMLScheduledTaskExecutorService
		commerceMLScheduledTaskExecutorService;

}