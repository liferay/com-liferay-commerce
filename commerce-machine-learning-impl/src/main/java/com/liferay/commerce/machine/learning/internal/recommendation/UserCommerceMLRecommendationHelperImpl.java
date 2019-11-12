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

import com.liferay.commerce.machine.learning.internal.recommendation.api.UserCommerceMLRecommendationHelper;
import com.liferay.commerce.machine.learning.internal.recommendation.constants.CommerceMLRecommendationField;
import com.liferay.commerce.machine.learning.internal.search.api.CommerceMLIndexer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.search.TermQuery;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.search.generic.TermQueryImpl;
import com.liferay.portal.search.engine.adapter.SearchEngineAdapter;
import com.liferay.portal.search.engine.adapter.search.SearchSearchRequest;
import com.liferay.portal.search.engine.adapter.search.SearchSearchResponse;

import java.util.Collections;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Ferrari
 */
@Component(immediate = true, service = UserCommerceMLRecommendationHelper.class)
public class UserCommerceMLRecommendationHelperImpl
	implements UserCommerceMLRecommendationHelper {

	@Override
	public Hits getRecommendations(
			long companyId, long commerceAccountId, long[] assetCategoryIds)
		throws Exception {

		SearchSearchRequest searchSearchRequest = new SearchSearchRequest();

		searchSearchRequest.setIndexNames(
			new String[] {_commerceMLIndexer.getIndexName(companyId)});

		searchSearchRequest.setSize(_DEFAULT_FETCH_SIZE);

		TermQuery companyTermQuery = new TermQueryImpl(
			Field.COMPANY_ID, String.valueOf(companyId));

		TermQuery entryClassPKTermQuery = new TermQueryImpl(
			Field.ENTRY_CLASS_PK, String.valueOf(commerceAccountId));

		BooleanQuery booleanQuery = new BooleanQueryImpl();

		booleanQuery.add(companyTermQuery, BooleanClauseOccur.MUST);

		booleanQuery.add(entryClassPKTermQuery, BooleanClauseOccur.MUST);

		if (assetCategoryIds != null) {
			for (long categoryId : assetCategoryIds) {
				TermQuery categoryIdTermQuery = new TermQueryImpl(
					Field.ASSET_CATEGORY_IDS, String.valueOf(categoryId));

				booleanQuery.add(categoryIdTermQuery, BooleanClauseOccur.MUST);
			}
		}

		searchSearchRequest.setQuery(booleanQuery);

		Sort scoreSort = SortFactoryUtil.create(
			CommerceMLRecommendationField.SCORE, Sort.FLOAT_TYPE, false);

		searchSearchRequest.setSorts(new Sort[] {scoreSort});

		searchSearchRequest.setStats(Collections.emptyMap());

		SearchSearchResponse searchSearchResponse =
			_searchEngineAdapter.execute(searchSearchRequest);

		if (_log.isTraceEnabled()) {
			_log.trace(searchSearchResponse.getSearchRequestString());
		}

		return searchSearchResponse.getHits();
	}

	private static final int _DEFAULT_FETCH_SIZE = 10;

	private static final Log _log = LogFactoryUtil.getLog(
		UserCommerceMLRecommendationHelperImpl.class);

	@Reference(
		target = "(component.name=com.liferay.commerce.machine.learning.internal.recommendation.search.index.UserCommerceMLRecommendationIndexer)"
	)
	private CommerceMLIndexer _commerceMLIndexer;

	@Reference
	private SearchEngineAdapter _searchEngineAdapter;

}