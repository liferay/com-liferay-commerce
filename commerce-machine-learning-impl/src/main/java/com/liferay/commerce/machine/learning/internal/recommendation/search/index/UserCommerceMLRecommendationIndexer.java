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

package com.liferay.commerce.machine.learning.internal.recommendation.search.index;

import com.liferay.commerce.machine.learning.internal.search.api.CommerceMLIndexer;
import com.liferay.commerce.machine.learning.internal.search.index.CommerceMLSearchEngineHelper;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Ferrari
 */
@Component(service = CommerceMLIndexer.class)
public class UserCommerceMLRecommendationIndexer implements CommerceMLIndexer {

	@Override
	public void createIndex(long companyId) {
		_commerceMLSearchEngineHelper.createIndex(
			getIndexName(companyId), _INDEX_MAPPING_FILE_NAME);
	}

	@Override
	public void dropIndex(long companyId) {
		_commerceMLSearchEngineHelper.dropIndex(getIndexName(companyId));
	}

	@Override
	public String getIndexName(long companyId) {
		return String.format(_INDEX_NAME_PATTERN, companyId);
	}

	private static final String _INDEX_MAPPING_FILE_NAME =
		"/META-INF/search/user-commerce-ml-recommendation-document-type.json";

	private static final String _INDEX_NAME_PATTERN =
		"user-commerce-ml-recommendation-%s";

	@Reference
	private CommerceMLSearchEngineHelper _commerceMLSearchEngineHelper;

}