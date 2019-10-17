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

import com.liferay.commerce.product.constants.CPField;
import com.liferay.commerce.product.model.CPDefinitionOptionRel;
import com.liferay.commerce.product.model.CPDefinitionOptionValueRel;
import com.liferay.commerce.product.service.CPDefinitionOptionRelLocalService;
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
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(immediate = true, service = Indexer.class)
public class CPDefinitionOptionRelIndexer
	extends BaseIndexer<CPDefinitionOptionRel> {

	public static final String CLASS_NAME =
		CPDefinitionOptionRel.class.getName();

	public CPDefinitionOptionRelIndexer() {
		setDefaultSelectedFieldNames(
			Field.COMPANY_ID, Field.ENTRY_CLASS_NAME, Field.ENTRY_CLASS_PK,
			Field.GROUP_ID, Field.MODIFIED_DATE, Field.NAME,
			Field.SCOPE_GROUP_ID, Field.UID);
	}

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public void postProcessContextBooleanFilter(
			BooleanFilter contextBooleanFilter, SearchContext searchContext)
		throws Exception {

		long cpDefinitionId = GetterUtil.getLong(
			searchContext.getAttribute(CPField.CP_DEFINITION_ID));

		if (cpDefinitionId > 0) {
			contextBooleanFilter.addRequiredTerm(
				CPField.CP_DEFINITION_ID, cpDefinitionId);
		}
	}

	@Override
	public void postProcessSearchQuery(
			BooleanQuery searchQuery, BooleanFilter fullQueryBooleanFilter,
			SearchContext searchContext)
		throws Exception {

		addSearchLocalizedTerm(
			searchQuery, searchContext, Field.CONTENT, false);
		addSearchTerm(
			searchQuery, searchContext,
			CPField.DEFINITION_OPTION_VALUE_REL_NAME, false);
		addSearchLocalizedTerm(
			searchQuery, searchContext,
			CPField.DEFINITION_OPTION_VALUE_REL_NAME, false);
		addSearchLocalizedTerm(
			searchQuery, searchContext, Field.DESCRIPTION, false);
		addSearchTerm(searchQuery, searchContext, Field.ENTRY_CLASS_PK, false);
		addSearchLocalizedTerm(searchQuery, searchContext, Field.NAME, false);
		addSearchTerm(searchQuery, searchContext, Field.USER_NAME, false);

		LinkedHashMap<String, Object> params =
			(LinkedHashMap<String, Object>)searchContext.getAttribute("params");

		if (params != null) {
			String expandoAttributes = (String)params.get("expandoAttributes");

			if (Validator.isNotNull(expandoAttributes)) {
				addSearchExpando(searchQuery, searchContext, expandoAttributes);
			}
		}
	}

	@Override
	protected void doDelete(CPDefinitionOptionRel cpDefinitionOptionRel)
		throws Exception {

		deleteDocument(
			cpDefinitionOptionRel.getCompanyId(),
			cpDefinitionOptionRel.getCPDefinitionOptionRelId());
	}

	@Override
	protected Document doGetDocument(
			CPDefinitionOptionRel cpDefinitionOptionRel)
		throws Exception {

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Indexing definition option rel " + cpDefinitionOptionRel);
		}

		Document document = getBaseModelDocument(
			CLASS_NAME, cpDefinitionOptionRel);

		List<CPDefinitionOptionValueRel> cpDefinitionOptionValueRels =
			cpDefinitionOptionRel.getCPDefinitionOptionValueRels();

		String cpDefinitionOptionRelDefaultLanguageId =
			LocalizationUtil.getDefaultLanguageId(
				cpDefinitionOptionRel.getName());

		String[] languageIds = LocalizationUtil.getAvailableLanguageIds(
			cpDefinitionOptionRel.getName());

		for (String languageId : languageIds) {
			String description = cpDefinitionOptionRel.getDescription(
				languageId);
			String name = cpDefinitionOptionRel.getName(languageId);

			List<String> cpDefinitionOptionValueRelNamesList =
				new ArrayList<>();

			for (CPDefinitionOptionValueRel cpDefinitionOptionValueRel :
					cpDefinitionOptionValueRels) {

				cpDefinitionOptionValueRelNamesList.add(
					cpDefinitionOptionValueRel.getName(languageId));
			}

			String[] cpDefinitionOptionValueRelNames =
				cpDefinitionOptionValueRelNamesList.toArray(new String[0]);

			if (languageId.equals(cpDefinitionOptionRelDefaultLanguageId)) {
				document.addText(
					CPField.DEFINITION_OPTION_VALUE_REL_NAME,
					cpDefinitionOptionValueRelNames);
				document.addText(Field.DESCRIPTION, description);
				document.addText(Field.NAME, name);
				document.addText("defaultLanguageId", languageId);
			}

			document.addText(
				LocalizationUtil.getLocalizedName(Field.NAME, languageId),
				name);
			document.addText(
				LocalizationUtil.getLocalizedName(
					Field.DESCRIPTION, languageId),
				description);

			document.addText(Field.CONTENT, name);

			document.addKeyword(
				CPField.CP_DEFINITION_ID,
				cpDefinitionOptionRel.getCPDefinitionId());

			document.addText(
				LocalizationUtil.getLocalizedName(
					CPField.DEFINITION_OPTION_VALUE_REL_NAME, languageId),
				cpDefinitionOptionValueRelNames);
		}

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Document " + cpDefinitionOptionRel + " indexed successfully");
		}

		return document;
	}

	@Override
	protected Summary doGetSummary(
		Document document, Locale locale, String snippet,
		PortletRequest portletRequest, PortletResponse portletResponse) {

		Summary summary = createSummary(
			document, Field.NAME, Field.DESCRIPTION);

		summary.setMaxContentLength(200);

		return summary;
	}

	@Override
	protected void doReindex(CPDefinitionOptionRel cpDefinitionOptionRel)
		throws Exception {

		_indexWriterHelper.updateDocument(
			getSearchEngineId(), cpDefinitionOptionRel.getCompanyId(),
			getDocument(cpDefinitionOptionRel), isCommitImmediately());
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		doReindex(
			_cpDefinitionOptionRelLocalService.getCPDefinitionOptionRel(
				classPK));
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		reindexCPDefinitionOptionRels(companyId);
	}

	protected void reindexCPDefinitionOptionRels(long companyId)
		throws PortalException {

		final IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			_cpDefinitionOptionRelLocalService.
				getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery.setPerformActionMethod(
			(CPDefinitionOptionRel cpDefinitionOptionRel) -> {
				try {
					indexableActionableDynamicQuery.addDocuments(
						getDocument(cpDefinitionOptionRel));
				}
				catch (PortalException pe) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							"Unable to index definition option rel " +
								cpDefinitionOptionRel.
									getCPDefinitionOptionRelId(),
							pe);
					}
				}
			});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CPDefinitionOptionRelIndexer.class);

	@Reference
	private CPDefinitionOptionRelLocalService
		_cpDefinitionOptionRelLocalService;

	@Reference
	private IndexWriterHelper _indexWriterHelper;

}