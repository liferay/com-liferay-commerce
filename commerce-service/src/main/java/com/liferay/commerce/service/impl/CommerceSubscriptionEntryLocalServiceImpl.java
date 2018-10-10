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
import com.liferay.commerce.internal.search.CommerceSubscriptionEntryIndexer;
import com.liferay.commerce.internal.util.CommerceSubscriptionUtil;
import com.liferay.commerce.model.CommerceSubscriptionEntry;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CPSubscriptionInfo;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.commerce.service.base.CommerceSubscriptionEntryLocalServiceBaseImpl;
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
public class CommerceSubscriptionEntryLocalServiceImpl
	extends CommerceSubscriptionEntryLocalServiceBaseImpl {

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceSubscriptionEntry addCommerceSubscriptionEntry(
			long cpInstanceId, long commerceOrderItemId,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(serviceContext.getUserId());
		long groupId = serviceContext.getScopeGroupId();

		CPInstance cpInstance = _cpInstanceLocalService.getCPInstance(
			cpInstanceId);

		CPSubscriptionInfo cpSubscriptionInfo =
			cpInstance.getCPSubscriptionInfo();

		if (cpSubscriptionInfo == null) {
			throw new CPSubscriptionCPInstanceIdException();
		}

		Date subscriptionNextIterationDate =
			CommerceSubscriptionUtil.getSubscriptionNextIterationDate(
				user.getTimeZone(),
				cpSubscriptionInfo.getSubscriptionCycleLength(),
				cpSubscriptionInfo.getSubscriptionCyclePeriod());

		long commerceSubscriptionEntryId = counterLocalService.increment();

		CommerceSubscriptionEntry commerceSubscriptionEntry =
			commerceSubscriptionEntryPersistence.create(
				commerceSubscriptionEntryId);

		commerceSubscriptionEntry.setUuid(serviceContext.getUuid());
		commerceSubscriptionEntry.setGroupId(groupId);
		commerceSubscriptionEntry.setCompanyId(user.getCompanyId());
		commerceSubscriptionEntry.setUserId(user.getUserId());
		commerceSubscriptionEntry.setUserName(user.getFullName());
		commerceSubscriptionEntry.setCPInstanceId(cpInstanceId);
		commerceSubscriptionEntry.setCommerceOrderItemId(commerceOrderItemId);
		commerceSubscriptionEntry.setSubscriptionCycleLength(
			cpSubscriptionInfo.getSubscriptionCycleLength());
		commerceSubscriptionEntry.setSubscriptionCyclePeriod(
			cpSubscriptionInfo.getSubscriptionCyclePeriod());
		commerceSubscriptionEntry.setMaxSubscriptionCyclesNumber(
			cpSubscriptionInfo.getMaxSubscriptionCyclesNumber());
		commerceSubscriptionEntry.setActive(true);
		commerceSubscriptionEntry.setNextIterationDate(
			subscriptionNextIterationDate);

		commerceSubscriptionEntryPersistence.update(commerceSubscriptionEntry);

		// Add CommerceSubscriptionCycleEntry

		commerceSubscriptionCycleEntryLocalService.addCommerceSubscriptionCycleEntry(
			commerceSubscriptionEntryId, commerceOrderItemId, false);

		return commerceSubscriptionEntry;
	}

	@Override
	public List<CommerceSubscriptionEntry> getActiveCPSubscriptionEntries() {
		return commerceSubscriptionEntryPersistence.findByactive(true);
	}

	@Override
	public List<CommerceSubscriptionEntry> getCPSubscriptionEntries(
		long groupId, long userId, int start, int end,
		OrderByComparator<CommerceSubscriptionEntry> orderByComparator) {

		return commerceSubscriptionEntryPersistence.findByG_U(
			groupId, userId, start, end, orderByComparator);
	}

	@Override
	public int getCPSubscriptionEntriesCount(long groupId, long userId) {
		return commerceSubscriptionEntryPersistence.countByG_U(groupId, userId);
	}

	@Override
	public List<CommerceSubscriptionEntry> getCPSubscriptionEntriesToRenew() {
		return commerceSubscriptionEntryFinder.findByNextIterationDate(
			new Date());
	}

	@Override
	public BaseModelSearchResult<CommerceSubscriptionEntry>
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
	public CommerceSubscriptionEntry setActive(
			long commerceSubscriptionEntryId, boolean active)
		throws PortalException {

		CommerceSubscriptionEntry commerceSubscriptionEntry =
			commerceSubscriptionEntryPersistence.findByPrimaryKey(
				commerceSubscriptionEntryId);

		commerceSubscriptionEntry.setActive(active);

		commerceSubscriptionEntryPersistence.update(commerceSubscriptionEntry);

		return commerceSubscriptionEntry;
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceSubscriptionEntry updateCommerceSubscriptionEntry(
			long commerceSubscriptionEntryId, long subscriptionCycleLength,
			String subscriptionCyclePeriod, long maxSubscriptionCyclesNumber,
			boolean active)
		throws PortalException {

		CommerceSubscriptionEntry commerceSubscriptionEntry =
			commerceSubscriptionEntryPersistence.findByPrimaryKey(
				commerceSubscriptionEntryId);

		commerceSubscriptionEntry.setSubscriptionCycleLength(
			subscriptionCycleLength);
		commerceSubscriptionEntry.setSubscriptionCyclePeriod(
			subscriptionCyclePeriod);
		commerceSubscriptionEntry.setMaxSubscriptionCyclesNumber(
			maxSubscriptionCyclesNumber);
		commerceSubscriptionEntry.setActive(active);

		commerceSubscriptionEntryPersistence.update(commerceSubscriptionEntry);

		return commerceSubscriptionEntry;
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
			CommerceSubscriptionEntryIndexer.FIELD_CP_INSTANCE_ID, keywords);
		attributes.put(CommerceSubscriptionEntryIndexer.FIELD_SKU, keywords);

		if (active != null) {
			attributes.put(
				CommerceSubscriptionEntryIndexer.FIELD_ACTIVE, active);
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

	protected List<CommerceSubscriptionEntry> getCPSubscriptionEntries(
			Hits hits)
		throws PortalException {

		List<Document> documents = hits.toList();

		List<CommerceSubscriptionEntry> cpSubscriptionEntries = new ArrayList<>(
			documents.size());

		for (Document document : documents) {
			long commerceSubscriptionEntryId = GetterUtil.getLong(
				document.get(Field.ENTRY_CLASS_PK));

			CommerceSubscriptionEntry commerceSubscriptionEntry =
				fetchCommerceSubscriptionEntry(commerceSubscriptionEntryId);

			if (commerceSubscriptionEntry == null) {
				cpSubscriptionEntries = null;

				Indexer<CommerceSubscriptionEntry> indexer =
					IndexerRegistryUtil.getIndexer(
						CommerceSubscriptionEntry.class);

				long companyId = GetterUtil.getLong(
					document.get(Field.COMPANY_ID));

				indexer.delete(companyId, document.getUID());
			}
			else if (cpSubscriptionEntries != null) {
				cpSubscriptionEntries.add(commerceSubscriptionEntry);
			}
		}

		return cpSubscriptionEntries;
	}

	protected BaseModelSearchResult<CommerceSubscriptionEntry>
			searchCPSubscriptionEntries(SearchContext searchContext)
		throws PortalException {

		Indexer<CommerceSubscriptionEntry> indexer =
			IndexerRegistryUtil.nullSafeGetIndexer(
				CommerceSubscriptionEntry.class);

		for (int i = 0; i < 10; i++) {
			Hits hits = indexer.search(searchContext, _SELECTED_FIELD_NAMES);

			List<CommerceSubscriptionEntry> cpSubscriptionEntries =
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