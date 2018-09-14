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

package com.liferay.commerce.product.search;

import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CPSubscriptionEntry;
import com.liferay.commerce.product.service.CPSubscriptionEntryLocalService;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
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
public class CPSubscriptionEntryIndexer
	extends BaseIndexer<CPSubscriptionEntry> {

	public static final String CLASS_NAME = CPSubscriptionEntry.class.getName();

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
	protected void doDelete(CPSubscriptionEntry cpSubscriptionEntry)
		throws Exception {

		deleteDocument(
			cpSubscriptionEntry.getCompanyId(),
			cpSubscriptionEntry.getCPSubscriptionEntryId());
	}

	@Override
	protected Document doGetDocument(CPSubscriptionEntry cpSubscriptionEntry)
		throws Exception {

		if (_log.isDebugEnabled()) {
			_log.debug("Indexing subscription entry " + cpSubscriptionEntry);
		}

		Document document = getBaseModelDocument(
			CLASS_NAME, cpSubscriptionEntry);

		CPInstance cpInstance = cpSubscriptionEntry.getCPInstance();

		document.addKeyword(FIELD_ACTIVE, cpSubscriptionEntry.isActive());
		document.addNumber(FIELD_CP_INSTANCE_ID, cpInstance.getCPInstanceId());
		document.addText(FIELD_SKU, cpInstance.getSku());

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Document " + cpSubscriptionEntry + " indexed successfully");
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
	protected void doReindex(CPSubscriptionEntry cpSubscriptionEntry)
		throws Exception {

		Document document = getDocument(cpSubscriptionEntry);

		_indexWriterHelper.updateDocument(
			getSearchEngineId(), cpSubscriptionEntry.getCompanyId(), document,
			isCommitImmediately());
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		CPSubscriptionEntry commerceCountry =
			_cpSubscriptionEntryLocalService.getCPSubscriptionEntry(classPK);

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
			_cpSubscriptionEntryLocalService.
				getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.
				PerformActionMethod<CPSubscriptionEntry>() {

				@Override
				public void performAction(
					CPSubscriptionEntry cpSubscriptionEntry) {

					try {
						Document document = getDocument(cpSubscriptionEntry);

						indexableActionableDynamicQuery.addDocuments(document);
					}
					catch (PortalException pe) {
						if (_log.isWarnEnabled()) {
							_log.warn(
								"Unable to index subscription entry " +
									cpSubscriptionEntry.
										getCPSubscriptionEntryId(),
								pe);
						}
					}
				}

			});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CPSubscriptionEntryIndexer.class);

	@Reference
	private CPSubscriptionEntryLocalService _cpSubscriptionEntryLocalService;

	@Reference
	private IndexWriterHelper _indexWriterHelper;

}