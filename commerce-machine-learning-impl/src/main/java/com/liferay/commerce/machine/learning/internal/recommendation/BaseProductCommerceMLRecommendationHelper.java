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
import com.liferay.commerce.machine.learning.internal.recommendation.constants.CommerceMLRecommendationField;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.search.TermQuery;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.search.generic.TermQueryImpl;
import com.liferay.portal.search.engine.adapter.search.SearchSearchRequest;

import java.util.Collections;

/**
 * @author Riccardo Ferrari
 */
public abstract class BaseProductCommerceMLRecommendationHelper
	implements ProductCommerceMLRecommendationHelper {

	public SearchSearchRequest getSearchRequest(
			String indexName, long companyId, long entryClassPK)
		throws Exception {

		SearchSearchRequest searchRequest = new SearchSearchRequest();

		searchRequest.setIndexNames(new String[] {indexName});

		searchRequest.setSize(_DEFAULT_FETCH_SIZE);

		TermQuery companyTermQuery = new TermQueryImpl(
			Field.COMPANY_ID, String.valueOf(companyId));

		TermQuery entryClassPKTermQuery = new TermQueryImpl(
			Field.ENTRY_CLASS_PK, String.valueOf(entryClassPK));

		BooleanQuery booleanQuery = new BooleanQueryImpl();

		booleanQuery.add(companyTermQuery, BooleanClauseOccur.MUST);

		booleanQuery.add(entryClassPKTermQuery, BooleanClauseOccur.MUST);

		searchRequest.setQuery(booleanQuery);

		Sort rankSort = SortFactoryUtil.create(
			CommerceMLRecommendationField.RANK, Sort.INT_TYPE, false);

		searchRequest.setSorts(new Sort[] {rankSort});

		searchRequest.setStats(Collections.emptyMap());

		return searchRequest;
	}

	private static final int _DEFAULT_FETCH_SIZE = 10;

}