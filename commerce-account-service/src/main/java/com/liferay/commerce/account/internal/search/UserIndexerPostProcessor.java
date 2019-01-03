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

package com.liferay.commerce.account.internal.search;

import com.liferay.commerce.account.model.CommerceAccountUserRel;
import com.liferay.commerce.account.service.CommerceAccountUserRelLocalService;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BaseIndexerPostProcessor;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.IndexerPostProcessor;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.TermFilter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.portal.kernel.model.User",
	service = IndexerPostProcessor.class
)
public class UserIndexerPostProcessor extends BaseIndexerPostProcessor {

	@Override
	public void postProcessContextBooleanFilter(
			BooleanFilter booleanFilter, SearchContext searchContext)
		throws Exception {

		LinkedHashMap<String, Object> params =
			(LinkedHashMap<String, Object>)searchContext.getAttribute("params");

		if (params == null) {
			return;
		}

		long commerceAccountId = (long)params.getOrDefault(
			"commerceAccountId", 0L);

		if (commerceAccountId > 0) {
			TermFilter termFilter = new TermFilter(
				"commerceAccountIds", String.valueOf(commerceAccountId));

			booleanFilter.add(termFilter, BooleanClauseOccur.MUST);
		}
	}

	@Override
	public void postProcessDocument(Document document, Object obj)
		throws Exception {

		User user = (User)obj;

		List<CommerceAccountUserRel> commerceAccountUserRels =
			_commerceAccountUserRelLocalService.
				getCommerceAccountUserRelsByCommerceAccountUserId(
					user.getUserId());

		Stream<CommerceAccountUserRel> stream =
			commerceAccountUserRels.stream();

		long[] commerceAccountIds = stream.mapToLong(
			CommerceAccountUserRel::getCommerceAccountId
		).toArray();

		document.addNumber("commerceAccountIds", commerceAccountIds);
	}

	@Reference
	private CommerceAccountUserRelLocalService
		_commerceAccountUserRelLocalService;

}