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

package com.liferay.commerce.machine.learning.internal.recommendation.data.integration;

import com.liferay.commerce.data.integration.model.CommerceDataIntegrationProcess;
import com.liferay.commerce.data.integration.service.CommerceDataIntegrationProcessLocalService;
import com.liferay.commerce.data.integration.service.ScheduledTaskExecutorService;
import com.liferay.commerce.machine.learning.internal.data.integration.CommerceMLScheduledTaskExecutorService;
import com.liferay.commerce.machine.learning.internal.recommendation.data.integration.process.type.ProductContentCommerceMLRecommendationProcessType;
import com.liferay.commerce.machine.learning.internal.search.api.CommerceMLIndexer;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.search.elasticsearch6.configuration.ElasticsearchConfiguration;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Ferrari
 */
@Component(
	configurationPid = "com.liferay.portal.search.elasticsearch6.configuration.ElasticsearchConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL, immediate = true,
	property = "data.integration.service.executor.key=" + ProductContentCommerceMLRecommendationProcessType.KEY,
	service = ScheduledTaskExecutorService.class
)
public class ProductContentCommerceMLRecommendationScheduledTaskExecutorService
	implements ScheduledTaskExecutorService {

	@Override
	public String getName() {
		return ProductContentCommerceMLRecommendationProcessType.KEY;
	}

	@Override
	public void runProcess(long commerceDataIntegrationProcessId)
		throws IOException, PortalException {

		CommerceDataIntegrationProcess commerceDataIntegrationProcess =
			_commerceDataIntegrationProcessLocalService.
				getCommerceDataIntegrationProcess(
					commerceDataIntegrationProcessId);

		_commerceMLScheduledTaskExecutorService.executeScheduledTask(
			commerceDataIntegrationProcess.getUserId(),
			commerceDataIntegrationProcessId,
			getContextProperties(
				commerceDataIntegrationProcess.getCompanyId()));
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_elasticsearchConfiguration = ConfigurableUtil.createConfigurable(
			ElasticsearchConfiguration.class, properties);
	}

	protected Map<String, String> getContextProperties(long companyId) {
		Map<String, String> contextProperties = new HashMap<>();

		contextProperties.put("COMMERCE_ML_PROCESS_TYPE", getName());

		contextProperties.put("LIFERAY_COMPANY_ID", String.valueOf(companyId));

		String sourceIndexName =
			_elasticsearchConfiguration.indexNamePrefix() + companyId;

		contextProperties.put("LIFERAY_INDEX_NAME", sourceIndexName);

		String productContentCommerceMLRecommendationIndexName =
			_productContentCommerceMLRecommendationIndexer.getIndexName(
				companyId);

		contextProperties.put(
			"PRODUCT_CONTENT_COMMERCE_ML_RECOMMENDATION_INDEX_NAME",
			productContentCommerceMLRecommendationIndexName);

		return contextProperties;
	}

	@Reference
	private CommerceDataIntegrationProcessLocalService
		_commerceDataIntegrationProcessLocalService;

	@Reference
	private CommerceMLScheduledTaskExecutorService
		_commerceMLScheduledTaskExecutorService;

	private ElasticsearchConfiguration _elasticsearchConfiguration;

	@Reference(
		target = "(component.name=com.liferay.commerce.machine.learning.internal.recommendation.search.index.ProductContentCommerceMLRecommendationIndexer)"
	)
	private CommerceMLIndexer _productContentCommerceMLRecommendationIndexer;

}