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

package com.liferay.commerce.service.impl;

import com.liferay.commerce.exception.CPSubscriptionCPInstanceIdException;
import com.liferay.commerce.internal.search.CPSubscriptionEntryIndexer;
import com.liferay.commerce.internal.util.CPSubscriptionUtil;
import com.liferay.commerce.model.CPSubscriptionEntry;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.commerce.service.base.CPSubscriptionEntryLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
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
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Alessio Antonio Rendina
 */
public class CPSubscriptionEntryLocalServiceImpl
	extends CPSubscriptionEntryLocalServiceBaseImpl {

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPSubscriptionEntry addCPSubscriptionEntry(
			long cpInstanceId, long commerceOrderItemId,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(serviceContext.getUserId());
		long groupId = serviceContext.getScopeGroupId();

		long subscriptionCycleLength;
		String subscriptionCyclePeriod;
		long maxSubscriptionCyclesNumber;

		CPInstance cpInstance = _cpInstanceLocalService.getCPInstance(
			cpInstanceId);

		if (cpInstance.isSubscriptionEnabled()) {
			subscriptionCycleLength = cpInstance.getSubscriptionCycleLength();
			subscriptionCyclePeriod = cpInstance.getSubscriptionCyclePeriod();
			maxSubscriptionCyclesNumber =
				cpInstance.getMaxSubscriptionCyclesNumber();
		}
		else {
			CPDefinition cpDefinition = cpInstance.getCPDefinition();

			if (!cpDefinition.isSubscriptionEnabled()) {
				throw new CPSubscriptionCPInstanceIdException();
			}

			subscriptionCycleLength = cpDefinition.getSubscriptionCycleLength();
			subscriptionCyclePeriod = cpDefinition.getSubscriptionCyclePeriod();
			maxSubscriptionCyclesNumber =
				cpDefinition.getMaxSubscriptionCyclesNumber();
		}

		Date subscriptionNextIterationDate =
			CPSubscriptionUtil.getSubscriptionNextIterationDate(
				user.getTimeZone(), subscriptionCycleLength,
				subscriptionCyclePeriod);

		long cpSubscriptionEntryId = counterLocalService.increment();

		CPSubscriptionEntry cpSubscriptionEntry =
			cpSubscriptionEntryPersistence.create(cpSubscriptionEntryId);

		cpSubscriptionEntry.setUuid(serviceContext.getUuid());
		cpSubscriptionEntry.setGroupId(groupId);
		cpSubscriptionEntry.setCompanyId(user.getCompanyId());
		cpSubscriptionEntry.setUserId(user.getUserId());
		cpSubscriptionEntry.setUserName(user.getFullName());
		cpSubscriptionEntry.setCommerceOrderItemId(commerceOrderItemId);
		cpSubscriptionEntry.setSubscriptionCycleLength(subscriptionCycleLength);
		cpSubscriptionEntry.setSubscriptionCyclePeriod(subscriptionCyclePeriod);
		cpSubscriptionEntry.setMaxSubscriptionCyclesNumber(
			maxSubscriptionCyclesNumber);
		cpSubscriptionEntry.setActive(true);
		cpSubscriptionEntry.setNextIterationDate(subscriptionNextIterationDate);

		cpSubscriptionEntryPersistence.update(cpSubscriptionEntry);

		// Add CPSubscriptionCycleEntry

		cpSubscriptionCycleEntryLocalService.addCPSubscriptionCycleEntry(
			cpSubscriptionEntryId, commerceOrderItemId, false);

		return cpSubscriptionEntry;
	}

	@Override
	public List<CPSubscriptionEntry> getActiveCPSubscriptionEntries() {
		return cpSubscriptionEntryPersistence.findByactive(true);
	}

	@Override
	public List<CPSubscriptionEntry> getCPSubscriptionEntries(
		long groupId, long userId, int start, int end,
		OrderByComparator<CPSubscriptionEntry> orderByComparator) {

		return cpSubscriptionEntryPersistence.findByG_U(
			groupId, userId, start, end, orderByComparator);
	}

	@Override
	public int getCPSubscriptionEntriesCount(long groupId, long userId) {
		return cpSubscriptionEntryPersistence.countByG_U(groupId, userId);
	}

	@Override
	public List<CPSubscriptionEntry> getCPSubscriptionEntriesToRenew() {
		return cpSubscriptionEntryFinder.findByNextIterationDate(new Date());
	}

	@Override
	public BaseModelSearchResult<CPSubscriptionEntry>
			searchCPSubscriptionEntries(
				long companyId, long groupId, Boolean active, String keywords,
				int start, int end, Sort sort)
		throws PortalException {

		SearchContext searchContext = buildSearchContext(
			companyId, groupId, active, keywords, start, end, sort);

		return searchCPSubscriptionEntries(searchContext);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPSubscriptionEntry setActive(
			long cpSubscriptionEntryId, boolean active)
		throws PortalException {

		CPSubscriptionEntry cpSubscriptionEntry =
			cpSubscriptionEntryPersistence.findByPrimaryKey(
				cpSubscriptionEntryId);

		cpSubscriptionEntry.setActive(active);

		cpSubscriptionEntryPersistence.update(cpSubscriptionEntry);

		return cpSubscriptionEntry;
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPSubscriptionEntry updateCPSubscriptionEntry(
			long cpSubscriptionEntryId, long subscriptionCycleLength,
			String subscriptionCyclePeriod, long maxSubscriptionCyclesNumber,
			boolean active)
		throws PortalException {

		CPSubscriptionEntry cpSubscriptionEntry =
			cpSubscriptionEntryPersistence.findByPrimaryKey(
				cpSubscriptionEntryId);

		cpSubscriptionEntry.setSubscriptionCycleLength(subscriptionCycleLength);
		cpSubscriptionEntry.setSubscriptionCyclePeriod(subscriptionCyclePeriod);
		cpSubscriptionEntry.setMaxSubscriptionCyclesNumber(
			maxSubscriptionCyclesNumber);
		cpSubscriptionEntry.setActive(active);

		cpSubscriptionEntryPersistence.update(cpSubscriptionEntry);

		return cpSubscriptionEntry;
	}

	protected SearchContext buildSearchContext(
		long companyId, long groupId, Boolean active, String keywords,
		int start, int end, Sort sort) {

		SearchContext searchContext = new SearchContext();

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("keywords", keywords);

		Map<String, Serializable> attributes = new HashMap<>();

		attributes.put(Field.ENTRY_CLASS_PK, keywords);
		attributes.put(
			CPSubscriptionEntryIndexer.FIELD_CP_INSTANCE_ID, keywords);
		attributes.put(CPSubscriptionEntryIndexer.FIELD_SKU, keywords);

		if (active != null) {
			attributes.put(CPSubscriptionEntryIndexer.FIELD_ACTIVE, active);
		}

		attributes.put("params", params);

		searchContext.setAttributes(attributes);

		searchContext.setCompanyId(companyId);
		searchContext.setStart(start);
		searchContext.setEnd(end);
		searchContext.setGroupIds(new long[] {groupId});

		if (Validator.isNotNull(keywords)) {
			searchContext.setKeywords(keywords);
		}

		QueryConfig queryConfig = searchContext.getQueryConfig();

		queryConfig.setHighlightEnabled(false);
		queryConfig.setScoreEnabled(false);

		if (sort != null) {
			searchContext.setSorts(sort);
		}

		return searchContext;
	}

	protected List<CPSubscriptionEntry> getCPSubscriptionEntries(Hits hits)
		throws PortalException {

		List<Document> documents = hits.toList();

		List<CPSubscriptionEntry> cpSubscriptionEntries = new ArrayList<>(
			documents.size());

		for (Document document : documents) {
			long cpSubscriptionEntryId = GetterUtil.getLong(
				document.get(Field.ENTRY_CLASS_PK));

			CPSubscriptionEntry cpSubscriptionEntry = fetchCPSubscriptionEntry(
				cpSubscriptionEntryId);

			if (cpSubscriptionEntry == null) {
				cpSubscriptionEntries = null;

				Indexer<CPSubscriptionEntry> indexer =
					IndexerRegistryUtil.getIndexer(CPSubscriptionEntry.class);

				long companyId = GetterUtil.getLong(
					document.get(Field.COMPANY_ID));

				indexer.delete(companyId, document.getUID());
			}
			else if (cpSubscriptionEntries != null) {
				cpSubscriptionEntries.add(cpSubscriptionEntry);
			}
		}

		return cpSubscriptionEntries;
	}

	protected BaseModelSearchResult<CPSubscriptionEntry>
			searchCPSubscriptionEntries(SearchContext searchContext)
		throws PortalException {

		Indexer<CPSubscriptionEntry> indexer =
			IndexerRegistryUtil.nullSafeGetIndexer(CPSubscriptionEntry.class);

		for (int i = 0; i < 10; i++) {
			Hits hits = indexer.search(searchContext, _SELECTED_FIELD_NAMES);

			List<CPSubscriptionEntry> cpSubscriptionEntries =
				getCPSubscriptionEntries(hits);

			if (cpSubscriptionEntries != null) {
				return new BaseModelSearchResult<>(
					cpSubscriptionEntries, hits.getLength());
			}
		}

		throw new SearchException(
			"Unable to fix the search index after 10 attempts");
	}

	private static final String[] _SELECTED_FIELD_NAMES =
		{Field.ENTRY_CLASS_PK, Field.COMPANY_ID, Field.GROUP_ID, Field.UID};

	@ServiceReference(type = CPInstanceLocalService.class)
	private CPInstanceLocalService _cpInstanceLocalService;

}