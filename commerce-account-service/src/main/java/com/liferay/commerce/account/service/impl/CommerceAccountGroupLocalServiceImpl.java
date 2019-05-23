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

import com.liferay.commerce.account.model.CommerceAccountGroup;
import com.liferay.commerce.account.service.base.CommerceAccountGroupLocalServiceBaseImpl;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
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
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.ArrayList;
import java.util.List;

/**

 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class CommerceAccountGroupLocalServiceImpl
	extends CommerceAccountGroupLocalServiceBaseImpl {

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceAccountGroup addCommerceAccountGroup(
			String name, int type, String externalReferenceCode,
			ServiceContext serviceContext)
		throws PortalException {

		// Commerce Account Group

		User user = userLocalService.getUser(serviceContext.getUserId());

		long commerceAccountGroupId = counterLocalService.increment();

		CommerceAccountGroup commerceAccountGroup =
			commerceAccountGroupPersistence.create(commerceAccountGroupId);

		commerceAccountGroup.setCompanyId(user.getCompanyId());
		commerceAccountGroup.setUserId(user.getUserId());
		commerceAccountGroup.setUserName(user.getFullName());
		commerceAccountGroup.setName(name);
		commerceAccountGroup.setType(type);
		commerceAccountGroup.setExternalReferenceCode(externalReferenceCode);
		commerceAccountGroup.setExpandoBridgeAttributes(serviceContext);

		commerceAccountGroupPersistence.update(commerceAccountGroup);

		// Resources

		resourceLocalService.addResources(
			user.getCompanyId(), GroupConstants.DEFAULT_LIVE_GROUP_ID,
			user.getUserId(), CommerceAccountGroup.class.getName(),
			commerceAccountGroup.getCommerceAccountGroupId(), false, false,
			false);

		return commerceAccountGroup;
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public CommerceAccountGroup deleteCommerceAccountGroup(
			CommerceAccountGroup commerceAccountGroup)
		throws PortalException {

		// Commerce account rels

		commerceAccountGroupCommerceAccountRelLocalService.
			deleteCommerceAccountGroupCommerceAccountRelByCAccountGroupId(
				commerceAccountGroup.getCommerceAccountGroupId());

		// Commerce account group

		commerceAccountGroupPersistence.remove(commerceAccountGroup);

		// Resources

		resourceLocalService.deleteResource(
			commerceAccountGroup, ResourceConstants.SCOPE_INDIVIDUAL);

		// Expando

		expandoRowLocalService.deleteRows(
			commerceAccountGroup.getCommerceAccountGroupId());

		return commerceAccountGroup;
	}

	@Override
	public CommerceAccountGroup deleteCommerceAccountGroup(
			long commerceAccountGroupId)
		throws PortalException {

		CommerceAccountGroup commerceAccountGroup =
			commerceAccountGroupPersistence.findByPrimaryKey(
				commerceAccountGroupId);

		return commerceAccountGroupLocalService.deleteCommerceAccountGroup(
			commerceAccountGroup);
	}

	@Override
	public List<CommerceAccountGroup> getCommerceAccountGroups(
		long companyId, int start, int end,
		OrderByComparator<CommerceAccountGroup> orderByComparator) {

		return commerceAccountGroupPersistence.findByCompanyId(
			companyId, start, end, orderByComparator);
	}

	@Override
	public int getCommerceAccountGroupsCount(long companyId) {
		return commerceAccountGroupPersistence.countByCompanyId(companyId);
	}

	@Override
	public List<CommerceAccountGroup> searchCommerceAccountGroups(
			long companyId, String keywords, int start, int end, Sort sort)
		throws PortalException {

		SearchContext searchContext = buildSearchContext(
			companyId, start, end, sort);

		searchContext.setKeywords(keywords);

		return searchCommerceAccountGroups(searchContext);
	}

	@Override
	public int searchCommerceAccountsGroupCount(long companyId, String keywords)
		throws PortalException {

		SearchContext searchContext = buildSearchContext(
			companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		searchContext.setKeywords(keywords);

		return searchCommerceAccountGroupsCount(searchContext);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceAccountGroup updateCommerceAccountGroup(
			long commerceAccountGroupId, String name,
			ServiceContext serviceContext)
		throws PortalException {

		CommerceAccountGroup commerceAccountGroup =
			commerceAccountGroupPersistence.findByPrimaryKey(
				commerceAccountGroupId);

		commerceAccountGroup.setName(name);
		commerceAccountGroup.setExpandoBridgeAttributes(serviceContext);

		return commerceAccountGroupPersistence.update(commerceAccountGroup);
	}

	protected SearchContext buildSearchContext(
		long companyId, int start, int end, Sort sort) {

		SearchContext searchContext = new SearchContext();

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

	protected List<CommerceAccountGroup> getCommerceAccountGroups(Hits hits)
		throws PortalException {

		List<Document> documents = hits.toList();

		List<CommerceAccountGroup> commerceAccountGroups = new ArrayList<>(
			documents.size());

		for (Document document : documents) {
			long commerceAccountGroupId = GetterUtil.getLong(
				document.get(Field.ENTRY_CLASS_PK));

			CommerceAccountGroup commerceAccountGroup =
				commerceAccountGroupPersistence.fetchByPrimaryKey(
					commerceAccountGroupId);

			if (commerceAccountGroup == null) {
				commerceAccountGroups = null;

				Indexer<CommerceAccountGroup> indexer =
					IndexerRegistryUtil.getIndexer(CommerceAccountGroup.class);

				long companyId = GetterUtil.getLong(
					document.get(Field.COMPANY_ID));

				indexer.delete(companyId, document.getUID());
			}
			else if (commerceAccountGroup != null) {
				commerceAccountGroups.add(commerceAccountGroup);
			}
		}

		return commerceAccountGroups;
	}

	protected List<CommerceAccountGroup> searchCommerceAccountGroups(
			SearchContext searchContext)
		throws PortalException {

		Indexer<CommerceAccountGroup> indexer =
			IndexerRegistryUtil.nullSafeGetIndexer(CommerceAccountGroup.class);

		for (int i = 0; i < 10; i++) {
			Hits hits = indexer.search(searchContext, _SELECTED_FIELD_NAMES);

			List<CommerceAccountGroup> commerceAccountGroups =
				getCommerceAccountGroups(hits);

			if (commerceAccountGroups != null) {
				return commerceAccountGroups;
			}
		}

		throw new SearchException(
			"Unable to fix the search index after 10 attempts");
	}

	protected int searchCommerceAccountGroupsCount(SearchContext searchContext)
		throws PortalException {

		Indexer<CommerceAccountGroup> indexer =
			IndexerRegistryUtil.nullSafeGetIndexer(CommerceAccountGroup.class);

		return GetterUtil.getInteger(indexer.searchCount(searchContext));
	}

	private static final String[] _SELECTED_FIELD_NAMES = {
		Field.ENTRY_CLASS_PK, Field.COMPANY_ID
	};

}