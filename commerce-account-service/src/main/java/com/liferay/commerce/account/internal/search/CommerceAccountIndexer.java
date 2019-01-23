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

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountLocalService;
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
public class CommerceAccountIndexer extends BaseIndexer<CommerceAccount> {

	public static final String CLASS_NAME = CommerceAccount.class.getName();

	public static final String FIELD_ACTIVE = "active";

	public static final String FIELD_PARENT_COMMERCE_ACCOUNT_ID =
		"parentCommerceAccountId";

	public static final String FIELD_TAX_ID = "taxId";

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

		long parentCommerceAccountId = GetterUtil.getLong(
			searchContext.getAttribute(FIELD_PARENT_COMMERCE_ACCOUNT_ID));

		contextBooleanFilter.addRequiredTerm(
			FIELD_PARENT_COMMERCE_ACCOUNT_ID, parentCommerceAccountId);

		int type = GetterUtil.getInteger(
			searchContext.getAttribute(Field.TYPE), -1);

		if (type > -1) {
			contextBooleanFilter.addRequiredTerm(Field.TYPE, type);
		}
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
	protected void doDelete(CommerceAccount commerceAccount) throws Exception {
		deleteDocument(
			commerceAccount.getCompanyId(),
			commerceAccount.getCommerceAccountId());
	}

	@Override
	protected Document doGetDocument(CommerceAccount commerceAccount)
		throws Exception {

		if (_log.isDebugEnabled()) {
			_log.debug("Indexing commerce account " + commerceAccount);
		}

		Document document = getBaseModelDocument(CLASS_NAME, commerceAccount);

		document.addText(Field.NAME, commerceAccount.getName());
		document.addKeyword(FIELD_ACTIVE, commerceAccount.isActive());
		document.addNumber(
			FIELD_PARENT_COMMERCE_ACCOUNT_ID,
			commerceAccount.getParentCommerceAccountId());
		document.addText(FIELD_TAX_ID, commerceAccount.getTaxId());
		document.addNumber(Field.TYPE, commerceAccount.getType());

		if (_log.isDebugEnabled()) {
			_log.debug("Document " + commerceAccount + " indexed successfully");
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
	protected void doReindex(CommerceAccount commerceAccount) throws Exception {
		Document document = getDocument(commerceAccount);

		_indexWriterHelper.updateDocument(
			getSearchEngineId(), commerceAccount.getCompanyId(), document,
			isCommitImmediately());
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		CommerceAccount commerceAccount =
			_commerceAccountLocalService.getCommerceAccount(classPK);

		doReindex(commerceAccount);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		reindexCommerceAccounts(companyId);
	}

	protected void reindexCommerceAccounts(long companyId)
		throws PortalException {

		final IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			_commerceAccountLocalService.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery.setPerformActionMethod(
			(CommerceAccount commerceAccount) -> {
				try {
					Document document = getDocument(commerceAccount);

					indexableActionableDynamicQuery.addDocuments(document);
				}
				catch (PortalException pe) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							"Unable to index commerce account " +
								commerceAccount.getCommerceAccountId(),
							pe);
					}
				}
			});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceAccountIndexer.class);

	@Reference
	private CommerceAccountLocalService _commerceAccountLocalService;

	@Reference
	private IndexWriterHelper _indexWriterHelper;

}