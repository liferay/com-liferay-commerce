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

package com.liferay.commerce.product.internal.search;

import com.liferay.commerce.product.indexer.CommerceCatalogScopeIndexer;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.engine.adapter.SearchEngineAdapter;
import com.liferay.portal.search.engine.adapter.index.CreateIndexRequest;
import com.liferay.portal.search.engine.adapter.index.DeleteIndexRequest;
import com.liferay.portal.search.engine.adapter.index.IndicesExistsIndexRequest;
import com.liferay.portal.search.engine.adapter.index.IndicesExistsIndexResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ethan Bustad
 * @author Riccardo Ferrari
 */
@Component(immediate = true, service = CommerceCatalogScopeIndexer.class)
public class CommerceCatalogScopeIndexerImpl
	implements CommerceCatalogScopeIndexer {

	public void createIndex(long companyId) {
		String indexName = getIndexName(companyId);

		if (_indexExists(indexName)) {
			if (_log.isDebugEnabled()) {
				_log.debug(String.format("Index %s already exists", indexName));
			}

			return;
		}

		CreateIndexRequest createIndexRequest = new CreateIndexRequest(
			indexName);

		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
				StringUtil.read(
					getClass(),
					"/META-INF/search" +
						"/commerce-catalog-scope-document-type.json"));

			createIndexRequest.setSource(
				JSONUtil.put(
					"mappings", jsonObject
				).toString());
		}
		catch (JSONException jsone) {
			_log.error(jsone, jsone);
		}

		_searchEngineAdapter.execute(createIndexRequest);

		if (_log.isDebugEnabled()) {
			_log.debug(
				String.format("Index %s created successfully", indexName));
		}
	}

	public void dropIndex(long companyId) {
		String indexName = getIndexName(companyId);

		if (!_indexExists(indexName)) {
			if (_log.isDebugEnabled()) {
				_log.debug(String.format("Index %s does not exist", indexName));
			}

			return;
		}

		DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(
			indexName);

		_searchEngineAdapter.execute(deleteIndexRequest);

		if (_log.isDebugEnabled()) {
			_log.debug(
				String.format("Index %s dropped successfully", indexName));
		}
	}

	@Override
	public String getIndexName(long companyId) {
		return String.format(_INDEX_NAME_PATTERN, companyId);
	}

	private boolean _indexExists(String indexName) {
		IndicesExistsIndexRequest indicesExistsIndexRequest =
			new IndicesExistsIndexRequest(indexName);

		IndicesExistsIndexResponse indicesExistsIndexResponse =
			_searchEngineAdapter.execute(indicesExistsIndexRequest);

		return indicesExistsIndexResponse.isExists();
	}

	private static final String _INDEX_NAME_PATTERN = "commerce-catalogs-%s";

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceCatalogScopeIndexerImpl.class);

	@Reference
	private SearchEngineAdapter _searchEngineAdapter;

}