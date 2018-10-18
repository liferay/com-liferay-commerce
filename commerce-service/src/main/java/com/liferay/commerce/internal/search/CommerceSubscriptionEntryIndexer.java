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

package com.liferay.commerce.internal.search;

import com.liferay.commerce.model.CommerceSubscriptionEntry;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.service.CommerceSubscriptionEntryLocalService;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelper;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = Indexer.class)
public class CommerceSubscriptionEntryIndexer
	extends BaseIndexer<CommerceSubscriptionEntry> {

	public static final String CLASS_NAME =
		CommerceSubscriptionEntry.class.getName();

	public static final String FIELD_ACTIVE = "active";

	public static final String FIELD_CP_INSTANCE_ID = "CPInstanceId";

	public static final String FIELD_SKU = "sku";

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public void postProcessContextBooleanFilter(
			BooleanFilter contextBooleanFilter, SearchContext searchContext)
		throws Exception {

		Boolean active = (Boolean)searchContext.getAttribute(FIELD_ACTIVE);

		if (active != null) {
			contextBooleanFilter.addTerm(
				FIELD_ACTIVE, String.valueOf(active), BooleanClauseOccur.MUST);
		}
	}

	@Override
	public void postProcessSearchQuery(
			BooleanQuery searchQuery, BooleanFilter fullQueryBooleanFilter,
			SearchContext searchContext)
		throws Exception {

		addSearchTerm(searchQuery, searchContext, Field.ENTRY_CLASS_PK, false);
		addSearchLocalizedTerm(searchQuery, searchContext, Field.NAME, false);
		addSearchTerm(searchQuery, searchContext, FIELD_CP_INSTANCE_ID, false);
		addSearchTerm(searchQuery, searchContext, FIELD_SKU, false);
	}

	@Override
	protected void doDelete(CommerceSubscriptionEntry commerceSubscriptionEntry)
		throws Exception {

		deleteDocument(
			commerceSubscriptionEntry.getCompanyId(),
			commerceSubscriptionEntry.getCommerceSubscriptionEntryId());
	}

	@Override
	protected Document doGetDocument(
			CommerceSubscriptionEntry commerceSubscriptionEntry)
		throws Exception {

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Indexing subscription entry " + commerceSubscriptionEntry);
		}

		Document document = getBaseModelDocument(
			CLASS_NAME, commerceSubscriptionEntry);

		CPInstance cpInstance = commerceSubscriptionEntry.getCPInstance();

		document.addKeyword(FIELD_ACTIVE, commerceSubscriptionEntry.isActive());
		document.addNumber(FIELD_CP_INSTANCE_ID, cpInstance.getCPInstanceId());
		document.addText(FIELD_SKU, cpInstance.getSku());

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Document " + commerceSubscriptionEntry +
					" indexed successfully");
		}

		return document;
	}

	@Override
	protected Summary doGetSummary(
		Document document, Locale locale, String snippet,
		PortletRequest portletRequest, PortletResponse portletResponse) {

		Summary summary = createSummary(
			document, Field.ENTRY_CLASS_PK, Field.NAME);

		summary.setMaxContentLength(200);

		return summary;
	}

	@Override
	protected void doReindex(
			CommerceSubscriptionEntry commerceSubscriptionEntry)
		throws Exception {

		Document document = getDocument(commerceSubscriptionEntry);

		_indexWriterHelper.updateDocument(
			getSearchEngineId(), commerceSubscriptionEntry.getCompanyId(),
			document, isCommitImmediately());
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		CommerceSubscriptionEntry commerceCountry =
			_commerceSubscriptionEntryLocalService.getCommerceSubscriptionEntry(
				classPK);

		doReindex(commerceCountry);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		reindexCommerceCountries(companyId);
	}

	protected void reindexCommerceCountries(long companyId)
		throws PortalException {

		final IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			_commerceSubscriptionEntryLocalService.
				getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery.setPerformActionMethod(
			(CommerceSubscriptionEntry commerceSubscriptionEntry) -> {
				try {
					Document document = getDocument(commerceSubscriptionEntry);

					indexableActionableDynamicQuery.addDocuments(document);
				}
				catch (PortalException pe) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							"Unable to index subscription entry " +
								commerceSubscriptionEntry.
									getCommerceSubscriptionEntryId(),
							pe);
					}
				}
			});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceSubscriptionEntryIndexer.class);

	@Reference
	private CommerceSubscriptionEntryLocalService
		_commerceSubscriptionEntryLocalService;

	@Reference
	private IndexWriterHelper _indexWriterHelper;

}