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

package com.liferay.commerce.machine.learning.internal.recommendation;

import com.liferay.commerce.machine.learning.internal.recommendation.api.ProductCommerceMLRecommendationHelper;
import com.liferay.commerce.machine.learning.internal.search.api.CommerceMLIndexer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.search.engine.adapter.SearchEngineAdapter;
import com.liferay.portal.search.engine.adapter.search.SearchSearchRequest;
import com.liferay.portal.search.engine.adapter.search.SearchSearchResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Ferrari
 */
@Component(
	immediate = true, service = ProductCommerceMLRecommendationHelper.class
)
public class ProductInteractionCommerceMLRecommendationHelperImpl
	extends BaseProductCommerceMLRecommendationHelper {

	@Override
	public Hits getRecommendations(long companyId, long entryClassPK)
		throws Exception {

		SearchSearchRequest searchRequest = getSearchRequest(
			_commerceMLIndexer.getIndexName(companyId), companyId,
			entryClassPK);

		SearchSearchResponse searchSearchResponse =
			_searchEngineAdapter.execute(searchRequest);

		if (_log.isTraceEnabled()) {
			_log.trace(searchSearchResponse.getSearchRequestString());
		}

		return searchSearchResponse.getHits();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ProductInteractionCommerceMLRecommendationHelperImpl.class);

	@Reference(
		target = "(component.name=com.liferay.commerce.machine.learning.internal.recommendation.search.index.ProductInteractionCommerceMLRecommendationIndexer)"
	)
	private CommerceMLIndexer _commerceMLIndexer;

	@Reference
	private SearchEngineAdapter _searchEngineAdapter;

}