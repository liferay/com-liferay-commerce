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

import com.liferay.commerce.constants.CommerceSubscriptionEntryConstants;
import com.liferay.commerce.constants.CommerceSubscriptionNotificationConstants;
import com.liferay.commerce.exception.CommerceSubscriptionCPInstanceIdException;
import com.liferay.commerce.exception.CommerceSubscriptionEntryNextIterationDateException;
import com.liferay.commerce.exception.CommerceSubscriptionEntryStartDateException;
import com.liferay.commerce.exception.CommerceSubscriptionEntrySubscriptionStatusException;
import com.liferay.commerce.exception.CommerceSubscriptionTypeException;
import com.liferay.commerce.internal.search.CommerceSubscriptionEntryIndexer;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.model.CommerceSubscriptionEntry;
import com.liferay.commerce.notification.util.CommerceNotificationHelper;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CPSubscriptionInfo;
import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.commerce.product.util.CPSubscriptionType;
import com.liferay.commerce.product.util.CPSubscriptionTypeRegistry;
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
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
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
			long userId, long groupId, long commerceOrderItemId,
			int subscriptionLength, String subscriptionType,
			long maxSubscriptionCycles,
			UnicodeProperties subscriptionTypeSettingsProperties)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		CPSubscriptionType cpSubscriptionType =
			_cpSubscriptionTypeRegistry.getCPSubscriptionType(subscriptionType);

		validateCPSubscriptionType(cpSubscriptionType);

		long commerceSubscriptionEntryId = counterLocalService.increment();

		CommerceSubscriptionEntry commerceSubscriptionEntry =
			commerceSubscriptionEntryPersistence.create(
				commerceSubscriptionEntryId);

		commerceSubscriptionEntry.setUuid(PortalUUIDUtil.generate());
		commerceSubscriptionEntry.setGroupId(groupId);
		commerceSubscriptionEntry.setCompanyId(user.getCompanyId());
		commerceSubscriptionEntry.setUserId(user.getUserId());
		commerceSubscriptionEntry.setUserName(user.getFullName());

		commerceSubscriptionEntry.setCommerceOrderItemId(commerceOrderItemId);
		commerceSubscriptionEntry.setSubscriptionLength(subscriptionLength);
		commerceSubscriptionEntry.setSubscriptionType(subscriptionType);
		commerceSubscriptionEntry.setCurrentCycle(1);
		commerceSubscriptionEntry.setMaxSubscriptionCycles(
			maxSubscriptionCycles);
		commerceSubscriptionEntry.setSubscriptionTypeSettingsProperties(
			subscriptionTypeSettingsProperties);
		commerceSubscriptionEntry.setSubscriptionStatus(
			CommerceSubscriptionEntryConstants.SUBSCRIPTION_STATUS_ACTIVE);
		commerceSubscriptionEntry.setLastIterationDate(new Date());

		Date subscriptionNextIterationDate =
			cpSubscriptionType.getSubscriptionNextIterationDate(
				user.getTimeZone(), subscriptionLength,
				subscriptionTypeSettingsProperties, null);

		commerceSubscriptionEntry.setNextIterationDate(
			subscriptionNextIterationDate);

		Date subscriptionStartDate =
			cpSubscriptionType.getSubscriptionStartDate(
				user.getTimeZone(), subscriptionTypeSettingsProperties);

		commerceSubscriptionEntry.setStartDate(subscriptionStartDate);

		commerceSubscriptionEntryPersistence.update(commerceSubscriptionEntry);

		return commerceSubscriptionEntry;
	}

	/**
	 * @deprecated As of Mueller (7.2.x), pass userId and groupId
	 */
	@Deprecated
	@Override
	public CommerceSubscriptionEntry addCommerceSubscriptionEntry(
			long cpInstanceId, long commerceOrderItemId,
			ServiceContext serviceContext)
		throws PortalException {

		CPInstance cpInstance = _cpInstanceLocalService.getCPInstance(
			cpInstanceId);

		CPDefinition cpDefinition = _cpDefinitionLocalService.getCPDefinition(
			cpInstance.getCPDefinitionId());

		return addCommerceSubscriptionEntry(
			serviceContext.getUserId(), serviceContext.getScopeGroupId(),
			cpInstance.getCPInstanceUuid(), cpDefinition.getCProductId(),
			commerceOrderItemId);
	}

	/**
	 * @deprecated As of Mueller (7.2.x), pass subscription info instead of
	 * cpInstanceUuid and cProductId
	 */
	@Deprecated
	@Override
	public CommerceSubscriptionEntry addCommerceSubscriptionEntry(
			long userId, long groupId, String cpInstanceUuid, long cProductId,
			long commerceOrderItemId)
		throws PortalException {

		CPSubscriptionInfo cpSubscriptionInfo = null;

		User user = userLocalService.getUser(userId);

		CPInstance cpInstance = _cpInstanceLocalService.fetchCProductInstance(
			cProductId, cpInstanceUuid);

		if (cpInstance != null) {
			cpSubscriptionInfo = cpInstance.getCPSubscriptionInfo();
		}

		if (cpSubscriptionInfo == null) {
			throw new CommerceSubscriptionCPInstanceIdException();
		}

		CPSubscriptionType cpSubscriptionType =
			_cpSubscriptionTypeRegistry.getCPSubscriptionType(
				cpSubscriptionInfo.getSubscriptionType());

		validateCPSubscriptionType(cpSubscriptionType);

		long commerceSubscriptionEntryId = counterLocalService.increment();

		CommerceSubscriptionEntry commerceSubscriptionEntry =
			commerceSubscriptionEntryPersistence.create(
				commerceSubscriptionEntryId);

		commerceSubscriptionEntry.setUuid(PortalUUIDUtil.generate());
		commerceSubscriptionEntry.setGroupId(groupId);
		commerceSubscriptionEntry.setCompanyId(user.getCompanyId());
		commerceSubscriptionEntry.setUserId(user.getUserId());
		commerceSubscriptionEntry.setUserName(user.getFullName());

		commerceSubscriptionEntry.setCPInstanceUuid(cpInstanceUuid);
		commerceSubscriptionEntry.setCProductId(cProductId);
		commerceSubscriptionEntry.setCommerceOrderItemId(commerceOrderItemId);
		commerceSubscriptionEntry.setSubscriptionLength(
			cpSubscriptionInfo.getSubscriptionLength());
		commerceSubscriptionEntry.setSubscriptionType(
			cpSubscriptionInfo.getSubscriptionType());
		commerceSubscriptionEntry.setCurrentCycle(1);
		commerceSubscriptionEntry.setMaxSubscriptionCycles(
			cpSubscriptionInfo.getMaxSubscriptionCycles());
		commerceSubscriptionEntry.setSubscriptionTypeSettingsProperties(
			cpSubscriptionInfo.getSubscriptionTypeSettingsProperties());
		commerceSubscriptionEntry.setSubscriptionStatus(
			CommerceSubscriptionEntryConstants.SUBSCRIPTION_STATUS_ACTIVE);
		commerceSubscriptionEntry.setLastIterationDate(new Date());

		Date subscriptionNextIterationDate =
			cpSubscriptionType.getSubscriptionNextIterationDate(
				user.getTimeZone(), cpSubscriptionInfo.getSubscriptionLength(),
				cpSubscriptionInfo.getSubscriptionTypeSettingsProperties(),
				null);

		commerceSubscriptionEntry.setNextIterationDate(
			subscriptionNextIterationDate);

		Date subscriptionStartDate =
			cpSubscriptionType.getSubscriptionStartDate(
				user.getTimeZone(),
				cpSubscriptionInfo.getSubscriptionTypeSettingsProperties());

		commerceSubscriptionEntry.setStartDate(subscriptionStartDate);

		return commerceSubscriptionEntryPersistence.update(
			commerceSubscriptionEntry);
	}

	/**
	 * @deprecated As of Mueller (7.2.x), pass userId and groupId
	 */
	@Deprecated
	@Override
	public CommerceSubscriptionEntry addCommerceSubscriptionEntry(
			String cpInstanceUuid, long cProductId, long commerceOrderItemId,
			ServiceContext serviceContext)
		throws PortalException {

		return addCommerceSubscriptionEntry(
			serviceContext.getUserId(), serviceContext.getScopeGroupId(),
			cpInstanceUuid, cProductId, commerceOrderItemId);
	}

	/**
	 * @deprecated As of Mueller (7.2.x), fetch by commerceOrderItemId instead
	 */
	@Deprecated
	@Override
	public CommerceSubscriptionEntry fetchCommerceSubscriptionEntries(
		String cpInstanceUuid, long cProductId, long commerceOrderItemId) {

		return commerceSubscriptionEntryPersistence.fetchByC_C_C(
			cpInstanceUuid, cProductId, commerceOrderItemId);
	}

	@Override
	public CommerceSubscriptionEntry fetchCommerceSubscriptionEntry(
		long commerceOrderItemId) {

		return commerceSubscriptionEntryPersistence.fetchByCommerceOrderItemId(
			commerceOrderItemId);
	}

	@Override
	public List<CommerceSubscriptionEntry>
		getActiveCommerceSubscriptionEntries() {

		return commerceSubscriptionEntryPersistence.findBySubscriptionStatus(
			CommerceSubscriptionEntryConstants.SUBSCRIPTION_STATUS_ACTIVE);
	}

	@Override
	public List<CommerceSubscriptionEntry> getCommerceSubscriptionEntries(
		long companyId, long userId, int start, int end,
		OrderByComparator<CommerceSubscriptionEntry> orderByComparator) {

		return commerceSubscriptionEntryPersistence.findByC_U(
			companyId, userId, start, end, orderByComparator);
	}

	@Override
	public List<CommerceSubscriptionEntry> getCommerceSubscriptionEntries(
		long companyId, long groupId, long userId, int start, int end,
		OrderByComparator<CommerceSubscriptionEntry> orderByComparator) {

		return commerceSubscriptionEntryPersistence.findByG_C_U(
			groupId, companyId, userId, start, end, orderByComparator);
	}

	@Override
	public int getCommerceSubscriptionEntriesCount(
		long companyId, long userId) {

		return commerceSubscriptionEntryPersistence.countByC_U(
			companyId, userId);
	}

	@Override
	public int getCommerceSubscriptionEntriesCount(
		long companyId, long groupId, long userId) {

		return commerceSubscriptionEntryPersistence.countByG_C_U(
			groupId, companyId, userId);
	}

	@Override
	public List<CommerceSubscriptionEntry>
		getCommerceSubscriptionEntriesToRenew() {

		return commerceSubscriptionEntryFinder.findByNextIterationDate(
			new Date());
	}

	@Override
	public CommerceSubscriptionEntry incrementCommerceSubscriptionEntryCycle(
			long commerceSubscriptionEntryId)
		throws PortalException {

		CommerceSubscriptionEntry commerceSubscriptionEntry =
			commerceSubscriptionEntryPersistence.findByPrimaryKey(
				commerceSubscriptionEntryId);

		long currentSubscriptionCycle =
			commerceSubscriptionEntry.getCurrentCycle();

		commerceSubscriptionEntry.setCurrentCycle(currentSubscriptionCycle + 1);

		User user = userLocalService.getUser(
			commerceSubscriptionEntry.getUserId());

		CPSubscriptionType cpSubscriptionType =
			_cpSubscriptionTypeRegistry.getCPSubscriptionType(
				commerceSubscriptionEntry.getSubscriptionType());

		validateCPSubscriptionType(cpSubscriptionType);

		commerceSubscriptionEntry.setLastIterationDate(
			commerceSubscriptionEntry.getNextIterationDate());

		Date subscriptionNextIterationDate =
			cpSubscriptionType.getSubscriptionNextIterationDate(
				user.getTimeZone(),
				commerceSubscriptionEntry.getSubscriptionLength(),
				commerceSubscriptionEntry.
					getSubscriptionTypeSettingsProperties(),
				commerceSubscriptionEntry.getNextIterationDate());

		commerceSubscriptionEntry.setNextIterationDate(
			subscriptionNextIterationDate);

		CommerceSubscriptionEntry updatedSubscriptionEntry =
			commerceSubscriptionEntryPersistence.update(
				commerceSubscriptionEntry);

		// Send user notification

		CommerceOrderItem commerceOrderItem =
			commerceSubscriptionEntry.fetchCommerceOrderItem();

		if (commerceOrderItem != null) {
			CommerceOrder commerceOrder = commerceOrderItem.getCommerceOrder();

			_commerceNotificationHelper.sendNotifications(
				commerceOrder.getGroupId(),
				CommerceSubscriptionNotificationConstants.SUBSCRIPTION_RENEWED,
				updatedSubscriptionEntry);
		}

		return updatedSubscriptionEntry;
	}

	@Override
	public BaseModelSearchResult<CommerceSubscriptionEntry>
			searchCommerceSubscriptionEntries(
				long companyId, long groupId, Long maxSubscriptionCycles,
				Integer subscriptionStatus, String keywords, int start, int end,
				Sort sort)
		throws PortalException {

		SearchContext searchContext = buildSearchContext(
			companyId, groupId, maxSubscriptionCycles, subscriptionStatus,
			keywords, start, end, sort);

		return searchCommerceSubscriptionEntries(searchContext);
	}

	@Override
	public BaseModelSearchResult<CommerceSubscriptionEntry>
			searchCommerceSubscriptionEntries(
				long companyId, Long maxSubscriptionCycles,
				Integer subscriptionStatus, String keywords, int start, int end,
				Sort sort)
		throws PortalException {

		SearchContext searchContext = buildSearchContext(
			companyId, null, maxSubscriptionCycles, subscriptionStatus,
			keywords, start, end, sort);

		return searchCommerceSubscriptionEntries(searchContext);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceSubscriptionEntry updateCommerceSubscriptionEntry(
			long commerceSubscriptionEntryId, int subscriptionLength,
			String subscriptionType,
			UnicodeProperties subscriptionTypeSettingsProperties,
			long maxSubscriptionCycles, int subscriptionStatus,
			int startDateMonth, int startDateDay, int startDateYear,
			int startDateHour, int startDateMinute, int nextIterationDateMonth,
			int nextIterationDateDay, int nextIterationDateYear,
			int nextIterationDateHour, int nextIterationDateMinute)
		throws PortalException {

		validateCPSubscriptionType(
			_cpSubscriptionTypeRegistry.getCPSubscriptionType(
				subscriptionType));

		CommerceSubscriptionEntry commerceSubscriptionEntry =
			commerceSubscriptionEntryPersistence.findByPrimaryKey(
				commerceSubscriptionEntryId);

		User user = userLocalService.getUser(
			commerceSubscriptionEntry.getUserId());

		validateSubscriptionStatus(
			subscriptionStatus,
			commerceSubscriptionEntry.getSubscriptionStatus());

		commerceSubscriptionEntry.setSubscriptionLength(subscriptionLength);
		commerceSubscriptionEntry.setSubscriptionType(subscriptionType);
		commerceSubscriptionEntry.setSubscriptionTypeSettingsProperties(
			subscriptionTypeSettingsProperties);
		commerceSubscriptionEntry.setMaxSubscriptionCycles(
			maxSubscriptionCycles);
		commerceSubscriptionEntry.setSubscriptionStatus(subscriptionStatus);

		Date nextIterationDate = PortalUtil.getDate(
			nextIterationDateMonth, nextIterationDateDay, nextIterationDateYear,
			nextIterationDateHour, nextIterationDateMinute, user.getTimeZone(),
			CommerceSubscriptionEntryNextIterationDateException.class);

		commerceSubscriptionEntry.setNextIterationDate(nextIterationDate);

		Date startDate = PortalUtil.getDate(
			startDateMonth, startDateDay, startDateYear, startDateHour,
			startDateMinute, user.getTimeZone(),
			CommerceSubscriptionEntryStartDateException.class);

		commerceSubscriptionEntry.setStartDate(startDate);

		return commerceSubscriptionEntryPersistence.update(
			commerceSubscriptionEntry);
	}

	@Override
	public CommerceSubscriptionEntry
			updateCommerceSubscriptionEntryIterationDates(
				long commerceSubscriptionEntryId, Date lastIterationDate)
		throws PortalException {

		CommerceSubscriptionEntry commerceSubscriptionEntry =
			commerceSubscriptionEntryPersistence.findByPrimaryKey(
				commerceSubscriptionEntryId);

		User user = userLocalService.getUser(
			commerceSubscriptionEntry.getUserId());

		CPSubscriptionType cpSubscriptionType =
			_cpSubscriptionTypeRegistry.getCPSubscriptionType(
				commerceSubscriptionEntry.getSubscriptionType());

		validateCPSubscriptionType(cpSubscriptionType);

		commerceSubscriptionEntry.setLastIterationDate(lastIterationDate);

		Date subscriptionNextIterationDate =
			cpSubscriptionType.getSubscriptionNextIterationDate(
				user.getTimeZone(),
				commerceSubscriptionEntry.getSubscriptionLength(),
				commerceSubscriptionEntry.
					getSubscriptionTypeSettingsProperties(),
				lastIterationDate);

		commerceSubscriptionEntry.setNextIterationDate(
			subscriptionNextIterationDate);

		return commerceSubscriptionEntryPersistence.update(
			commerceSubscriptionEntry);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceSubscriptionEntry updateSubscriptionStatus(
			long commerceSubscriptionEntryId, int subscriptionStatus)
		throws PortalException {

		CommerceSubscriptionEntry commerceSubscriptionEntry =
			commerceSubscriptionEntryPersistence.findByPrimaryKey(
				commerceSubscriptionEntryId);

		validateSubscriptionStatus(
			subscriptionStatus,
			commerceSubscriptionEntry.getSubscriptionStatus());

		commerceSubscriptionEntry.setSubscriptionStatus(subscriptionStatus);

		return commerceSubscriptionEntryPersistence.update(
			commerceSubscriptionEntry);
	}

	protected SearchContext buildSearchContext(
		long companyId, Long groupId, Long maxSubscriptionCycles,
		Integer subscriptionStatus, String keywords, int start, int end,
		Sort sort) {

		SearchContext searchContext = new SearchContext();

		Map<String, Serializable> attributes = new HashMap<>();

		attributes.put(Field.ENTRY_CLASS_PK, keywords);
		attributes.put(
			CommerceSubscriptionEntryIndexer.FIELD_CP_INSTANCE_ID, keywords);
		attributes.put(CommerceSubscriptionEntryIndexer.FIELD_SKU, keywords);

		if (maxSubscriptionCycles != null) {
			attributes.put(
				CommerceSubscriptionEntryIndexer.FIELD_MAX_SUBSCRIPTION_CYCLES,
				maxSubscriptionCycles);
		}

		if (subscriptionStatus != null) {
			attributes.put(
				CommerceSubscriptionEntryIndexer.FIELD_SUBSCRIPTION_STATUS,
				subscriptionStatus);
		}

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("keywords", keywords);

		attributes.put("params", params);

		searchContext.setAttributes(attributes);

		searchContext.setCompanyId(companyId);
		searchContext.setStart(start);
		searchContext.setEnd(end);

		if (groupId != null) {
			searchContext.setGroupIds(new long[] {groupId});
		}

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

	protected List<CommerceSubscriptionEntry> getCommerceSubscriptionEntries(
			Hits hits)
		throws PortalException {

		List<Document> documents = hits.toList();

		List<CommerceSubscriptionEntry> commerceSubscriptionEntries =
			new ArrayList<>(documents.size());

		for (Document document : documents) {
			long commerceSubscriptionEntryId = GetterUtil.getLong(
				document.get(Field.ENTRY_CLASS_PK));

			CommerceSubscriptionEntry commerceSubscriptionEntry =
				fetchCommerceSubscriptionEntry(commerceSubscriptionEntryId);

			if (commerceSubscriptionEntry == null) {
				commerceSubscriptionEntries = null;

				Indexer<CommerceSubscriptionEntry> indexer =
					IndexerRegistryUtil.getIndexer(
						CommerceSubscriptionEntry.class);

				long companyId = GetterUtil.getLong(
					document.get(Field.COMPANY_ID));

				indexer.delete(companyId, document.getUID());
			}
			else if (commerceSubscriptionEntries != null) {
				commerceSubscriptionEntries.add(commerceSubscriptionEntry);
			}
		}

		return commerceSubscriptionEntries;
	}

	protected BaseModelSearchResult<CommerceSubscriptionEntry>
			searchCommerceSubscriptionEntries(SearchContext searchContext)
		throws PortalException {

		Indexer<CommerceSubscriptionEntry> indexer =
			IndexerRegistryUtil.nullSafeGetIndexer(
				CommerceSubscriptionEntry.class);

		for (int i = 0; i < 10; i++) {
			Hits hits = indexer.search(searchContext, _SELECTED_FIELD_NAMES);

			List<CommerceSubscriptionEntry> commerceSubscriptionEntries =
				getCommerceSubscriptionEntries(hits);

			if (commerceSubscriptionEntries != null) {
				return new BaseModelSearchResult<>(
					commerceSubscriptionEntries, hits.getLength());
			}
		}

		throw new SearchException(
			"Unable to fix the search index after 10 attempts");
	}

	protected void validateCPSubscriptionType(
			CPSubscriptionType cpSubscriptionType)
		throws PortalException {

		if (cpSubscriptionType == null) {
			throw new CommerceSubscriptionTypeException();
		}
	}

	protected void validateSubscriptionStatus(
			int subscriptionStatus, int oldSubscriptionStatus)
		throws PortalException {

		if (oldSubscriptionStatus ==
				CommerceSubscriptionEntryConstants.
					SUBSCRIPTION_STATUS_SUSPENDED) {

			return;
		}

		if (subscriptionStatus < oldSubscriptionStatus) {
			throw new CommerceSubscriptionEntrySubscriptionStatusException();
		}
	}

	private static final String[] _SELECTED_FIELD_NAMES = {
		Field.ENTRY_CLASS_PK, Field.COMPANY_ID, Field.GROUP_ID, Field.UID
	};

	@ServiceReference(type = CommerceChannelLocalService.class)
	private CommerceChannelLocalService _commerceChannelLocalService;

	@ServiceReference(type = CommerceNotificationHelper.class)
	private CommerceNotificationHelper _commerceNotificationHelper;

	@ServiceReference(type = CPDefinitionLocalService.class)
	private CPDefinitionLocalService _cpDefinitionLocalService;

	@ServiceReference(type = CPInstanceLocalService.class)
	private CPInstanceLocalService _cpInstanceLocalService;

	@ServiceReference(type = CPSubscriptionTypeRegistry.class)
	private CPSubscriptionTypeRegistry _cpSubscriptionTypeRegistry;

}