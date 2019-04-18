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

import com.liferay.commerce.product.indexer.CommerceCatalogScopeHelper;
import com.liferay.commerce.product.indexer.CommerceCatalogScopeIndexer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.AuditedModel;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.search.engine.adapter.SearchEngineAdapter;
import com.liferay.portal.search.engine.adapter.document.DeleteDocumentRequest;
import com.liferay.portal.search.engine.adapter.document.IndexDocumentRequest;
import com.liferay.portal.search.engine.adapter.search.SearchSearchRequest;
import com.liferay.portal.search.engine.adapter.search.SearchSearchResponse;

import java.util.Collections;
import java.util.Map;

/**
 * @author Ethan Bustad
 */
public abstract class BaseCommerceCatalogScopeHelperImpl
	implements CommerceCatalogScopeHelper {

	@Override
	public boolean deleteDocument(AuditedModel auditedModel) {
		String indexName = commerceCatalogScopeIndexer.getIndexName(
			auditedModel.getCompanyId());

		String uid = getUid(
			auditedModel.getModelClassName(),
			String.valueOf(auditedModel.getPrimaryKeyObj()));

		DeleteDocumentRequest deleteDocumentRequest = new DeleteDocumentRequest(
			indexName, uid);

		deleteDocumentRequest.setType(COMMERCE_CATALOG_SCOPE_DOCUMENT_TYPE);

		searchEngineAdapter.execute(deleteDocumentRequest);

		return true;
	}

	@Override
	public int getDefaultFetchSize() {
		return _DEFAULT_FETCH_SIZE;
	}

	@Override
	public Sort[] getDefaultSorts() {
		return null;
	}

	@Override
	public boolean reindex(AuditedModel auditedModel) {
		String indexName = commerceCatalogScopeIndexer.getIndexName(
			auditedModel.getCompanyId());

		Document document = new DocumentImpl();

		String classPK = String.valueOf(auditedModel.getPrimaryKeyObj());

		document.addKeyword(
			Field.UID, getUid(auditedModel.getModelClassName(), classPK));
		document.addKeyword(Field.ENTRY_CLASS_PK, classPK);

		document.addKeyword(
			Field.ENTRY_CLASS_NAME, auditedModel.getModelClassName());
		document.addKeyword(Field.COMPANY_ID, auditedModel.getCompanyId());
		document.addKeyword(Field.USER_ID, auditedModel.getUserId());
		document.addKeyword(Field.USER_NAME, auditedModel.getUserName());
		document.addDate(Field.CREATE_DATE, auditedModel.getCreateDate());
		document.addDate(Field.MODIFIED_DATE, auditedModel.getModifiedDate());

		try {
			document = populateDocument(document, auditedModel);
		}
		catch (PortalException pe) {
			_log.error(pe, pe);

			return false;
		}

		IndexDocumentRequest indexDocumentRequest = new IndexDocumentRequest(
			indexName, document);

		indexDocumentRequest.setType(COMMERCE_CATALOG_SCOPE_DOCUMENT_TYPE);

		searchEngineAdapter.execute(indexDocumentRequest);

		return true;
	}

	@Override
	public Hits search(long companyId, Map<String, String> parameters)
		throws SearchException {

		return search(companyId, parameters, 0, getDefaultFetchSize());
	}

	@Override
	public Hits search(
			long companyId, Map<String, String> parameters, int start, int end)
		throws SearchException {

		return search(companyId, parameters, start, end, getDefaultSorts());
	}

	@Override
	public Hits search(
			long companyId, Map<String, String> parameters, int start, int end,
			Sort[] sorts)
		throws SearchException {

		SearchSearchRequest searchSearchRequest = createSearchRequest(
			parameters);

		searchSearchRequest.setIndexNames(
			new String[] {commerceCatalogScopeIndexer.getIndexName(companyId)});

		searchSearchRequest.setSize(end - start);
		searchSearchRequest.setStart(start);
		searchSearchRequest.setStats(Collections.emptyMap());

		if (sorts != null) {
			searchSearchRequest.setSorts(sorts);
		}

		SearchSearchResponse searchSearchResponse = searchEngineAdapter.execute(
			searchSearchRequest);

		if (_log.isTraceEnabled()) {
			_log.trace(searchSearchResponse.getSearchRequestString());
		}

		return searchSearchResponse.getHits();
	}

	protected abstract SearchSearchRequest createSearchRequest(
			Map<String, String> parameters)
		throws SearchException;

	protected String getUid(String className, String classPK) {
		return Field.getUID(
			COMMERCE_CATALOG_SCOPE_DOCUMENT_TYPE, className, classPK);
	}

	protected abstract Document populateDocument(
			Document document, AuditedModel auditedModel)
		throws PortalException;

	protected CommerceCatalogScopeIndexer commerceCatalogScopeIndexer;
	protected SearchEngineAdapter searchEngineAdapter;

	private static final int _DEFAULT_FETCH_SIZE = 10;

	private static final Log _log = LogFactoryUtil.getLog(
		BaseCommerceCatalogScopeHelperImpl.class);

}