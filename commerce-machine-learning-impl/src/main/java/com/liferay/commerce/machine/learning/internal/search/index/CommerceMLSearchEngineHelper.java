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

package com.liferay.commerce.machine.learning.internal.search.index;

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
 * @author Riccardo Ferrari
 */
@Component(immediate = true, service = CommerceMLSearchEngineHelper.class)
public class CommerceMLSearchEngineHelper {

	public void createIndex(String indexName, String indexMappingFileName) {
		if (indicesExists(indexName)) {
			if (_log.isDebugEnabled()) {
				_log.debug(String.format("Index %s already exist", indexName));
			}

			return;
		}

		CreateIndexRequest createIndexRequest = new CreateIndexRequest(
			indexName);

		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
				StringUtil.read(getClass(), indexMappingFileName));

			createIndexRequest.setSource(
				JSONUtil.put(
					"mappings", jsonObject
				).toString());
		}
		catch (JSONException jsone) {
			_log.error(jsone, jsone);
		}

		searchEngineAdapter.execute(createIndexRequest);

		if (_log.isDebugEnabled()) {
			_log.debug(
				String.format("Index %s created successfully", indexName));
		}
	}

	public void dropIndex(String indexName) {
		if (!indicesExists(indexName)) {
			if (_log.isDebugEnabled()) {
				_log.debug(String.format("Index %s does not exist", indexName));
			}

			return;
		}

		DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(
			indexName);

		searchEngineAdapter.execute(deleteIndexRequest);

		if (_log.isDebugEnabled()) {
			_log.debug(
				String.format("Index %s dropped successfully", indexName));
		}
	}

	protected boolean indicesExists(String indexName) {
		IndicesExistsIndexRequest indicesExistsIndexRequest =
			new IndicesExistsIndexRequest(indexName);

		IndicesExistsIndexResponse indicesExistsIndexResponse =
			searchEngineAdapter.execute(indicesExistsIndexRequest);

		return indicesExistsIndexResponse.isExists();
	}

	@Reference
	protected SearchEngineAdapter searchEngineAdapter;

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceMLSearchEngineHelper.class);

}