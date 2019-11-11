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

import com.liferay.commerce.data.integration.service.ScheduledTaskExecutorService;
import com.liferay.commerce.machine.learning.internal.forecast.constants.CommerceMLForecastPeriod;
import com.liferay.commerce.machine.learning.internal.forecast.constants.CommerceMLForecastScope;
import com.liferay.commerce.machine.learning.internal.forecast.constants.CommerceMLForecastTarget;
import com.liferay.commerce.machine.learning.internal.forecast.data.integration.process.type.AssetCategoryCommerceMLForecastProcessType;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.search.elasticsearch6.configuration.ElasticsearchConfiguration;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

/**
 * @author Riccardo Ferrari
 */
@Component(
	configurationPid = "com.liferay.portal.search.elasticsearch6.configuration.ElasticsearchConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL, immediate = true,
	property = "data.integration.service.executor.key=" + AssetCategoryCommerceMLForecastProcessType.KEY,
	service = ScheduledTaskExecutorService.class
)
public class AssetCategoryCommerceMLForecastScheduledTaskExecutorService
	extends BaseForecastScheduledTaskExecutorService {

	@Override
	public String getName() {
		return AssetCategoryCommerceMLForecastProcessType.KEY;
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_elasticsearchConfiguration = ConfigurableUtil.createConfigurable(
			ElasticsearchConfiguration.class, properties);
	}

	@Override
	protected String getLiferayIndexNamePrefix() {
		return _elasticsearchConfiguration.indexNamePrefix();
	}

	@Override
	protected String getPeriod() {
		return _COMMERCE_ML_FORECAST_PERIOD.getLabel();
	}

	@Override
	protected String getScope() {
		return _COMMERCE_ML_FORECAST_SCOPE.getLabel();
	}

	@Override
	protected String getTarget() {
		return _COMMERCE_ML_FORECAST_TARGET.getLabel();
	}

	private static final CommerceMLForecastPeriod _COMMERCE_ML_FORECAST_PERIOD =
		CommerceMLForecastPeriod.MONTH;

	private static final CommerceMLForecastScope _COMMERCE_ML_FORECAST_SCOPE =
		CommerceMLForecastScope.ASSET_CATEGORY;

	private static final CommerceMLForecastTarget _COMMERCE_ML_FORECAST_TARGET =
		CommerceMLForecastTarget.REVENUE;

	private ElasticsearchConfiguration _elasticsearchConfiguration;

}