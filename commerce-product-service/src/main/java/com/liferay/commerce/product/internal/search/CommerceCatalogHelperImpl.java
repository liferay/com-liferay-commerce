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
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.CommerceCatalogLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.AuditedModel;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.search.TermQuery;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.search.generic.TermQueryImpl;
import com.liferay.portal.search.engine.adapter.SearchEngineAdapter;
import com.liferay.portal.search.engine.adapter.search.SearchSearchRequest;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ethan Bustad
 */
@Component(
	immediate = true,
	property = "commerce.index.class.name=com.liferay.commerce.product.model.CommerceCatalog",
	service = CommerceCatalogScopeHelper.class
)
public class CommerceCatalogHelperImpl
	extends BaseCommerceCatalogScopeHelperImpl {

	@Override
	public Sort[] getDefaultSorts() {
		Sort nameSort = SortFactoryUtil.create("name", Sort.STRING_TYPE, false);

		return new Sort[] {nameSort};
	}

	@Override
	protected SearchSearchRequest createSearchRequest(
			Map<String, String> parameters)
		throws SearchException {

		SearchSearchRequest searchRequest = new SearchSearchRequest();

		BooleanQuery booleanQuery = new BooleanQueryImpl();

		try {
			TermQueryImpl companyTermQuery = new TermQueryImpl(
				Field.COMPANY_ID,
				String.valueOf(parameters.get(Field.COMPANY_ID)));

			booleanQuery.add(companyTermQuery, BooleanClauseOccur.MUST);

			if (parameters.containsKey(Field.ENTRY_CLASS_PK)) {
				TermQuery entryClassPKTermQuery = new TermQueryImpl(
					Field.ENTRY_CLASS_PK,
					String.valueOf(parameters.get(Field.ENTRY_CLASS_PK)));

				booleanQuery.add(
					entryClassPKTermQuery, BooleanClauseOccur.MUST);
			}

			if (parameters.containsKey(Field.NAME)) {
				TermQuery nameTermQuery = new TermQueryImpl(
					Field.NAME, String.valueOf(parameters.get(Field.NAME)));

				booleanQuery.add(nameTermQuery, BooleanClauseOccur.MUST);
			}
		}
		catch (ParseException pe) {
			throw new SearchException(pe);
		}

		searchRequest.setQuery(booleanQuery);

		return searchRequest;
	}

	@Override
	protected Document populateDocument(
			Document document, AuditedModel auditedModel)
		throws PortalException {

		CommerceCatalog commerceCatalog = (CommerceCatalog)auditedModel;

		document.addKeyword(Field.NAME, commerceCatalog.getName());
		document.addKeyword(
			"catalogDefaultLanguageId",
			commerceCatalog.getCatalogDefaultLanguageId());

		Group group = _commerceCatalogLocalService.getCommerceCatalogGroup(
			commerceCatalog.getCommerceCatalogId());

		document.addKeyword("commerceCatalogGroupId", group.getGroupId());

		return document;
	}

	@Reference(unbind = "-")
	protected void setCommerceCatalogScopeIndexer(
		CommerceCatalogScopeIndexer commerceCatalogScopeIndexer) {

		this.commerceCatalogScopeIndexer = commerceCatalogScopeIndexer;
	}

	@Reference(unbind = "-")
	protected void setSearchEngineAdapter(
		SearchEngineAdapter searchEngineAdapter) {

		this.searchEngineAdapter = searchEngineAdapter;
	}

	@Reference
	private CommerceCatalogLocalService _commerceCatalogLocalService;

}