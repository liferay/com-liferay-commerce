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

package com.liferay.commerce.account.service.impl;

import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.account.exception.CommerceAccountNameException;
import com.liferay.commerce.account.exception.DuplicateCommerceAccountException;
import com.liferay.commerce.account.internal.search.CommerceAccountIndexer;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.base.CommerceAccountLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class CommerceAccountLocalServiceImpl
	extends CommerceAccountLocalServiceBaseImpl {

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceAccount addCommerceAccount(
			long userId, long parentCommerceAccountId, String name,
			String taxId, boolean active, String externalReferenceCode,
			ServiceContext serviceContext)
		throws PortalException {

		// Commerce Account

		User user = userPersistence.findByPrimaryKey(userId);

		parentCommerceAccountId = getParentCommerceAccountId(
			user.getCompanyId(), parentCommerceAccountId);

		validate(user.getCompanyId(), 0, name, externalReferenceCode);

		long commerceAccountId = counterLocalService.increment();

		CommerceAccount commerceAccount = commerceAccountPersistence.create(
			commerceAccountId);

		commerceAccount.setCompanyId(user.getCompanyId());
		commerceAccount.setUserId(user.getUserId());
		commerceAccount.setUserName(user.getFullName());
		commerceAccount.setExternalReferenceCode(externalReferenceCode);
		commerceAccount.setParentCommerceAccountId(parentCommerceAccountId);
		commerceAccount.setName(name);
		commerceAccount.setTaxId(taxId);
		commerceAccount.setActive(active);
		commerceAccount.setExpandoBridgeAttributes(serviceContext);

		commerceAccountPersistence.update(commerceAccount);

		// Group

		groupLocalService.addGroup(
			userId, GroupConstants.DEFAULT_PARENT_GROUP_ID,
			CommerceAccount.class.getName(), commerceAccountId,
			GroupConstants.DEFAULT_LIVE_GROUP_ID, getLocalizationMap(name),
			null, GroupConstants.TYPE_SITE_PRIVATE, false,
			GroupConstants.DEFAULT_MEMBERSHIP_RESTRICTION, null, false, true,
			null);

		// Resources

		resourceLocalService.addResources(
			user.getCompanyId(), 0, userId, CommerceAccount.class.getName(),
			commerceAccount.getCommerceAccountId(), false, false, false);

		// Workflow

		return WorkflowHandlerRegistryUtil.startWorkflowInstance(
			commerceAccount.getCompanyId(), WorkflowConstants.DEFAULT_GROUP_ID,
			userId, CommerceAccount.class.getName(), commerceAccountId,
			commerceAccount, serviceContext, new HashMap<>());
	}

	@Override
	public BaseModelSearchResult<CommerceAccount> searchCommerceAccounts(
			long companyId, long parentCommerceAccountId, String keywords,
			Boolean active, int start, int end, Sort sort)
		throws PortalException {

		SearchContext searchContext = buildSearchContext(
			companyId, parentCommerceAccountId, active, start, end, sort);

		searchContext.setKeywords(keywords);

		return searchCommerceAccounts(searchContext);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceAccount updateCommerceAccount(
			long commerceAccountId, String name, String taxId, boolean active,
			ServiceContext serviceContext)
		throws PortalException {

		CommerceAccount commerceAccount =
			commerceAccountPersistence.findByPrimaryKey(commerceAccountId);

		validate(
			serviceContext.getCompanyId(),
			commerceAccount.getCommerceAccountId(), name,
			commerceAccount.getExternalReferenceCode());

		commerceAccount.setName(name);
		commerceAccount.setTaxId(taxId);
		commerceAccount.setActive(active);
		commerceAccount.setExpandoBridgeAttributes(serviceContext);

		commerceAccountPersistence.update(commerceAccount);

		// Workflow

		return WorkflowHandlerRegistryUtil.startWorkflowInstance(
			commerceAccount.getCompanyId(), WorkflowConstants.DEFAULT_GROUP_ID,
			commerceAccount.getUserId(), CommerceAccount.class.getName(),
			commerceAccountId, commerceAccount, serviceContext,
			new HashMap<>());
	}

	@Override
	public CommerceAccount upsertCommerceAccount(
			long userId, long parentCommerceAccountId, String name,
			String taxId, boolean active, String externalReferenceCode,
			ServiceContext serviceContext)
		throws PortalException {

		CommerceAccount commerceAccount =
			commerceAccountPersistence.fetchByC_ERC(
				serviceContext.getCompanyId(), externalReferenceCode);

		if (commerceAccount == null) {
			commerceAccount = commerceAccountPersistence.fetchByC_N(
				serviceContext.getCompanyId(), name);
		}

		if (commerceAccount != null) {
			return commerceAccountLocalService.updateCommerceAccount(
				commerceAccount.getCommerceAccountId(), name, taxId, active,
				serviceContext);
		}

		return commerceAccountLocalService.addCommerceAccount(
			userId, parentCommerceAccountId, name, taxId, active,
			externalReferenceCode, serviceContext);
	}

	protected SearchContext buildSearchContext(
		long companyId, long parentCommerceAccountId, Boolean active, int start,
		int end, Sort sort) {

		SearchContext searchContext = new SearchContext();

		searchContext.setAttribute(
			CommerceAccountIndexer.FIELD_PARENT_COMMERCE_ACCOUNT_ID,
			parentCommerceAccountId);

		if (active != null) {
			searchContext.setAttribute(
				CommerceAccountIndexer.FIELD_ACTIVE, active);
		}

		searchContext.setCompanyId(companyId);
		searchContext.setStart(start);
		searchContext.setEnd(end);

		QueryConfig queryConfig = searchContext.getQueryConfig();

		queryConfig.setHighlightEnabled(false);
		queryConfig.setScoreEnabled(false);

		if (sort != null) {
			searchContext.setSorts(sort);
		}

		return searchContext;
	}

	protected List<CommerceAccount> getCommerceAccounts(Hits hits)
		throws PortalException {

		List<Document> documents = hits.toList();

		List<CommerceAccount> commerceAccounts = new ArrayList<>(
			documents.size());

		for (Document document : documents) {
			long commerceAccountId = GetterUtil.getLong(
				document.get(Field.ENTRY_CLASS_PK));

			CommerceAccount commerceAccount =
				commerceAccountPersistence.fetchByPrimaryKey(commerceAccountId);

			if (commerceAccount == null) {
				commerceAccounts = null;

				Indexer<CommerceAccount> indexer =
					IndexerRegistryUtil.getIndexer(CommerceAccount.class);

				long companyId = GetterUtil.getLong(
					document.get(Field.COMPANY_ID));

				indexer.delete(companyId, document.getUID());
			}
			else if (commerceAccount != null) {
				commerceAccounts.add(commerceAccount);
			}
		}

		return commerceAccounts;
	}

	protected long getParentCommerceAccountId(
		long companyId, long parentCommerceAccountId) {

		if (parentCommerceAccountId !=
				CommerceAccountConstants.DEFAULT_PARENT_ACCOUNT_ID) {

			// Ensure parent account exists and belongs to the proper
			// company

			CommerceAccount parentCommerceAccount =
				commerceAccountPersistence.fetchByPrimaryKey(
					parentCommerceAccountId);

			if ((parentCommerceAccount == null) ||
				(companyId != parentCommerceAccount.getCompanyId())) {

				parentCommerceAccountId =
					CommerceAccountConstants.DEFAULT_PARENT_ACCOUNT_ID;
			}
		}

		return parentCommerceAccountId;
	}

	protected BaseModelSearchResult<CommerceAccount> searchCommerceAccounts(
			SearchContext searchContext)
		throws PortalException {

		Indexer<CommerceAccount> indexer =
			IndexerRegistryUtil.nullSafeGetIndexer(CommerceAccount.class);

		for (int i = 0; i < 10; i++) {
			Hits hits = indexer.search(searchContext, _SELECTED_FIELD_NAMES);

			List<CommerceAccount> commerceAccounts = getCommerceAccounts(hits);

			if (commerceAccounts != null) {
				return new BaseModelSearchResult<>(
					commerceAccounts, hits.getLength());
			}
		}

		throw new SearchException(
			"Unable to fix the search index after 10 attempts");
	}

	protected void validate(
			long companyId, long commerceAccountId,
			String externalReferenceCode, String name)
		throws PortalException {

		if (Validator.isNull(name)) {
			throw new CommerceAccountNameException();
		}

		CommerceAccount commerceAccount =
			commerceAccountPersistence.fetchByC_ERC(
				companyId, externalReferenceCode);

		String errorMessage =
			"There is another commerce account with external reference code " +
				externalReferenceCode;

		if (commerceAccount == null) {
			commerceAccount = commerceAccountPersistence.fetchByC_N(
				companyId, name);

			errorMessage = "There is another commerce account named " + name;
		}

		if ((commerceAccount != null) &&
			(commerceAccount.getCommerceAccountId() != commerceAccountId)) {

			throw new DuplicateCommerceAccountException(errorMessage);
		}
	}

	private static final String[] _SELECTED_FIELD_NAMES =
		{Field.ENTRY_CLASS_PK, Field.COMPANY_ID};

}