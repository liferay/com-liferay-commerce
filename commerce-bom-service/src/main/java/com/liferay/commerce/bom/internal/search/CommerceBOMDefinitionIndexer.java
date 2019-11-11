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

package com.liferay.commerce.bom.internal.search;

import com.liferay.commerce.bom.model.CommerceBOMDefinition;
import com.liferay.commerce.bom.service.CommerceBOMDefinitionLocalService;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexer;
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
public class CommerceBOMDefinitionIndexer
	extends BaseIndexer<CommerceBOMDefinition> {

	public static final String CLASS_NAME =
		CommerceBOMDefinition.class.getName();

	public static final String FIELD_COMMERCE_BOM_FOLDER_ID =
		"commerceBOMFolderId";

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public void postProcessContextBooleanFilter(
			BooleanFilter contextBooleanFilter, SearchContext searchContext)
		throws Exception {

		long commerceBOMFolderId = GetterUtil.getLong(
			searchContext.getAttribute(FIELD_COMMERCE_BOM_FOLDER_ID));

		contextBooleanFilter.addRequiredTerm(
			FIELD_COMMERCE_BOM_FOLDER_ID, commerceBOMFolderId);
	}

	@Override
	public void postProcessSearchQuery(
			BooleanQuery searchQuery, BooleanFilter fullQueryBooleanFilter,
			SearchContext searchContext)
		throws Exception {

		addSearchTerm(searchQuery, searchContext, Field.ENTRY_CLASS_PK, false);
		addSearchTerm(searchQuery, searchContext, Field.NAME, false);
	}

	@Override
	protected void doDelete(CommerceBOMDefinition commerceBOMDefinition)
		throws Exception {

		deleteDocument(
			commerceBOMDefinition.getCompanyId(),
			commerceBOMDefinition.getCommerceBOMDefinitionId());
	}

	@Override
	protected Document doGetDocument(
			CommerceBOMDefinition commerceBOMDefinition)
		throws Exception {

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Indexing commerce BOM definition " + commerceBOMDefinition);
		}

		Document document = getBaseModelDocument(
			CLASS_NAME, commerceBOMDefinition);

		document.addText(Field.NAME, commerceBOMDefinition.getName());
		document.addNumber(
			FIELD_COMMERCE_BOM_FOLDER_ID,
			commerceBOMDefinition.getCommerceBOMFolderId());

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Document " + commerceBOMDefinition + " indexed successfully");
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
	protected void doReindex(CommerceBOMDefinition commerceBOMDefinition)
		throws Exception {

		_indexWriterHelper.updateDocument(
			getSearchEngineId(), commerceBOMDefinition.getCompanyId(),
			getDocument(commerceBOMDefinition), isCommitImmediately());
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		doReindex(
			_commerceBOMDefinitionLocalService.getCommerceBOMDefinition(
				classPK));
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		reindexCommerceBOMDefinitions(companyId);
	}

	protected void reindexCommerceBOMDefinitions(long companyId)
		throws PortalException {

		final IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			_commerceBOMDefinitionLocalService.
				getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery.setPerformActionMethod(
			(CommerceBOMDefinition commerceBOMDefinition) -> {
				try {
					indexableActionableDynamicQuery.addDocuments(
						getDocument(commerceBOMDefinition));
				}
				catch (PortalException pe) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							"Unable to index commerce BOM definition " +
								commerceBOMDefinition.
									getCommerceBOMDefinitionId(),
							pe);
					}
				}
			});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceBOMDefinitionIndexer.class);

	@Reference
	private CommerceBOMDefinitionLocalService
		_commerceBOMDefinitionLocalService;

	@Reference
	private IndexWriterHelper _indexWriterHelper;

}