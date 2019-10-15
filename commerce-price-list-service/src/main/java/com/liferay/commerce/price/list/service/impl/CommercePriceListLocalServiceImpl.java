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

package com.liferay.commerce.price.list.service.impl;

import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.service.CommerceCurrencyLocalService;
import com.liferay.commerce.price.list.exception.CommercePriceListCurrencyException;
import com.liferay.commerce.price.list.exception.CommercePriceListDisplayDateException;
import com.liferay.commerce.price.list.exception.CommercePriceListExpirationDateException;
import com.liferay.commerce.price.list.exception.CommercePriceListParentPriceListGroupIdException;
import com.liferay.commerce.price.list.exception.DuplicateCommercePriceListException;
import com.liferay.commerce.price.list.exception.NoSuchPriceListException;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.base.CommercePriceListLocalServiceBaseImpl;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.cache.MultiVMPoolUtil;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.dao.orm.QueryDefinition;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.SystemEventConstants;
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
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 * @author Zoltán Takács
 */
public class CommercePriceListLocalServiceImpl
	extends CommercePriceListLocalServiceBaseImpl {

	@Override
	public CommercePriceList addCommercePriceList(
			long groupId, long userId, long commerceCurrencyId,
			long parentCommercePriceListId, String name, double priority,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, ServiceContext serviceContext)
		throws PortalException {

		return commercePriceListLocalService.addCommercePriceList(
			groupId, userId, commerceCurrencyId, parentCommercePriceListId,
			name, priority, displayDateMonth, displayDateDay, displayDateYear,
			displayDateHour, displayDateMinute, expirationDateMonth,
			expirationDateDay, expirationDateYear, expirationDateHour,
			expirationDateMinute, null, neverExpire, serviceContext);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommercePriceList addCommercePriceList(
			long groupId, long userId, long commerceCurrencyId,
			long parentCommercePriceListId, String name, double priority,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			String externalReferenceCode, boolean neverExpire,
			ServiceContext serviceContext)
		throws PortalException {

		// Commerce price list

		User user = userLocalService.getUser(userId);

		if (Validator.isBlank(externalReferenceCode)) {
			externalReferenceCode = null;
		}

		validate(groupId, commerceCurrencyId, parentCommercePriceListId);

		validateExternalReferenceCode(
			serviceContext.getCompanyId(), externalReferenceCode);

		Date displayDate = null;
		Date expirationDate = null;
		Date now = new Date();

		displayDate = PortalUtil.getDate(
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, user.getTimeZone(),
			CommercePriceListDisplayDateException.class);

		if (!neverExpire) {
			expirationDate = PortalUtil.getDate(
				expirationDateMonth, expirationDateDay, expirationDateYear,
				expirationDateHour, expirationDateMinute, user.getTimeZone(),
				CommercePriceListExpirationDateException.class);
		}

		long commercePriceListId = counterLocalService.increment();

		CommercePriceList commercePriceList =
			commercePriceListPersistence.create(commercePriceListId);

		commercePriceList.setUuid(serviceContext.getUuid());
		commercePriceList.setGroupId(groupId);
		commercePriceList.setCompanyId(user.getCompanyId());
		commercePriceList.setUserId(user.getUserId());
		commercePriceList.setUserName(user.getFullName());
		commercePriceList.setCommerceCurrencyId(commerceCurrencyId);
		commercePriceList.setParentCommercePriceListId(
			parentCommercePriceListId);
		commercePriceList.setName(name);
		commercePriceList.setPriority(priority);
		commercePriceList.setDisplayDate(displayDate);
		commercePriceList.setExpirationDate(expirationDate);
		commercePriceList.setExternalReferenceCode(externalReferenceCode);

		if ((expirationDate == null) || expirationDate.after(now)) {
			commercePriceList.setStatus(WorkflowConstants.STATUS_DRAFT);
		}
		else {
			commercePriceList.setStatus(WorkflowConstants.STATUS_EXPIRED);
		}

		commercePriceList.setStatusByUserId(user.getUserId());
		commercePriceList.setStatusDate(serviceContext.getModifiedDate(now));
		commercePriceList.setExpandoBridgeAttributes(serviceContext);

		commercePriceListPersistence.update(commercePriceList);

		// Workflow

		commercePriceList = startWorkflowInstance(
			user.getUserId(), commercePriceList, serviceContext);

		// Cache

		cleanPriceListCache(user.getCompanyId());

		return commercePriceList;
	}

	@Override
	public CommercePriceList addCommercePriceList(
			long groupId, long userId, long commerceCurrencyId, String name,
			double priority, int displayDateMonth, int displayDateDay,
			int displayDateYear, int displayDateHour, int displayDateMinute,
			int expirationDateMonth, int expirationDateDay,
			int expirationDateYear, int expirationDateHour,
			int expirationDateMinute, boolean neverExpire,
			ServiceContext serviceContext)
		throws PortalException {

		return commercePriceListLocalService.addCommercePriceList(
			groupId, userId, commerceCurrencyId, 0, name, priority,
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute, null,
			neverExpire, serviceContext);
	}

	@Override
	public CommercePriceList addCommercePriceList(
			long groupId, long userId, long commerceCurrencyId, String name,
			double priority, int displayDateMonth, int displayDateDay,
			int displayDateYear, int displayDateHour, int displayDateMinute,
			int expirationDateMonth, int expirationDateDay,
			int expirationDateYear, int expirationDateHour,
			int expirationDateMinute, String externalReferenceCode,
			boolean neverExpire, ServiceContext serviceContext)
		throws PortalException {

		return commercePriceListLocalService.addCommercePriceList(
			groupId, userId, commerceCurrencyId, 0, name, priority,
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute,
			externalReferenceCode, neverExpire, serviceContext);
	}

	@Override
	public void checkCommercePriceLists() throws PortalException {
		checkCommercePriceListsByDisplayDate();
		checkCommercePriceListsByExpirationDate();
	}

	@Override
	public void cleanPriceListCache(long companyId) {
		MultiVMPoolUtil.removePortalCache("PRICE_LISTS_" + companyId);
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public CommercePriceList deleteCommercePriceList(
			CommercePriceList commercePriceList)
		throws PortalException {

		// Commerce price entries

		commercePriceEntryLocalService.deleteCommercePriceEntries(
			commercePriceList.getCommercePriceListId());

		// Commerce price list account rels

		commercePriceListAccountRelLocalService.
			deleteCommercePriceListAccountRels(
				commercePriceList.getCommercePriceListId());

		// Commerce price list commerce account group rels

		commercePriceListCommerceAccountGroupRelLocalService.
			deleteCommercePriceListCommerceAccountGroupRels(
				commercePriceList.getCommercePriceListId());

		// Commerce price list

		commercePriceListPersistence.remove(commercePriceList);

		// Expando

		expandoRowLocalService.deleteRows(
			commercePriceList.getCommercePriceListId());

		// Workflow

		workflowInstanceLinkLocalService.deleteWorkflowInstanceLinks(
			commercePriceList.getCompanyId(), commercePriceList.getGroupId(),
			CommercePriceList.class.getName(),
			commercePriceList.getCommercePriceListId());

		// Cache

		cleanPriceListCache(commercePriceList.getCompanyId());

		return commercePriceList;
	}

	@Override
	public CommercePriceList deleteCommercePriceList(long commercePriceListId)
		throws PortalException {

		CommercePriceList commercePriceList =
			commercePriceListPersistence.findByPrimaryKey(commercePriceListId);

		return commercePriceListLocalService.deleteCommercePriceList(
			commercePriceList);
	}

	@Override
	public void deleteCommercePriceLists(long companyId)
		throws PortalException {

		List<CommercePriceList> commercePriceLists =
			commercePriceListLocalService.getCommercePriceLists(
				companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (CommercePriceList commercePriceList : commercePriceLists) {
			commercePriceListLocalService.deleteCommercePriceList(
				commercePriceList);
		}
	}

	@Override
	public CommercePriceList fetchByExternalReferenceCode(
		long companyId, String externalReferenceCode) {

		if (Validator.isBlank(externalReferenceCode)) {
			return null;
		}

		return commercePriceListPersistence.fetchByC_ERC(
			companyId, externalReferenceCode);
	}

	@Override
	public Optional<CommercePriceList> getCommercePriceList(
			long companyId, long groupId, long commerceAccountId,
			long[] commerceAccountGroupIds)
		throws PortalException {

		Company company = companyLocalService.getCompany(companyId);

		if (commerceAccountGroupIds == null) {
			commerceAccountGroupIds = new long[0];
		}
		else if (commerceAccountGroupIds.length > 1) {
			commerceAccountGroupIds = ArrayUtil.unique(commerceAccountGroupIds);

			Arrays.sort(commerceAccountGroupIds);
		}

		String cacheKey = StringBundler.concat(
			groupId, StringPool.POUND, commerceAccountId, StringPool.POUND,
			StringUtil.merge(commerceAccountGroupIds));

		PortalCache<String, Serializable> portalCache =
			MultiVMPoolUtil.getPortalCache(
				"PRICE_LISTS_" + company.getCompanyId());

		boolean priceListCalculated = GetterUtil.getBoolean(
			portalCache.get(cacheKey + "_calculated"));

		CommercePriceList commercePriceList =
			(CommercePriceList)portalCache.get(cacheKey);

		if (priceListCalculated) {
			return Optional.ofNullable(commercePriceList);
		}

		SearchContext searchContext = buildSearchContext(
			company.getCompanyId(), groupId, commerceAccountId,
			commerceAccountGroupIds);

		Indexer<CommercePriceList> indexer =
			IndexerRegistryUtil.nullSafeGetIndexer(CommercePriceList.class);

		Hits hits = indexer.search(searchContext, Field.ENTRY_CLASS_PK);

		List<Document> documents = hits.toList();

		if (documents.isEmpty()) {
			portalCache.put(cacheKey + "_calculated", true);

			return Optional.empty();
		}

		Document document = documents.get(0);

		long commercePriceListId = GetterUtil.getLong(
			document.get(Field.ENTRY_CLASS_PK));

		commercePriceList = fetchCommercePriceList(commercePriceListId);

		portalCache.put(cacheKey, commercePriceList);

		portalCache.put(cacheKey + "_calculated", true);

		return Optional.ofNullable(commercePriceList);
	}

	@Override
	public List<CommercePriceList> getCommercePriceLists(
		long companyId, int start, int end) {

		return commercePriceListPersistence.findByCompanyId(
			companyId, start, end);
	}

	@Override
	public List<CommercePriceList> getCommercePriceLists(
		long[] groupIds, long companyId, int start, int end) {

		return commercePriceListPersistence.findByG_C(
			groupIds, companyId, start, end);
	}

	@Override
	public List<CommercePriceList> getCommercePriceLists(
		long[] groupIds, long companyId, int status, int start, int end,
		OrderByComparator<CommercePriceList> orderByComparator) {

		if (status == WorkflowConstants.STATUS_ANY) {
			return commercePriceListPersistence.findByG_C_NotS(
				groupIds, companyId, WorkflowConstants.STATUS_IN_TRASH, start,
				end, orderByComparator);
		}

		return commercePriceListPersistence.findByG_C_S(
			groupIds, companyId, status, start, end, orderByComparator);
	}

	@Override
	public int getCommercePriceListsCount(
		long[] groupIds, long companyId, int status) {

		if (status == WorkflowConstants.STATUS_ANY) {
			return commercePriceListPersistence.countByG_C_NotS(
				groupIds, companyId, WorkflowConstants.STATUS_IN_TRASH);
		}

		return commercePriceListPersistence.countByG_C_S(
			groupIds, companyId, status);
	}

	@Override
	public Hits search(SearchContext searchContext) {
		try {
			Indexer<CommercePriceList> indexer =
				IndexerRegistryUtil.nullSafeGetIndexer(CommercePriceList.class);

			return indexer.search(searchContext);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@Override
	public BaseModelSearchResult<CommercePriceList> searchCommercePriceLists(
			long companyId, long[] groupIds, String keywords, int status,
			int start, int end, Sort sort)
		throws PortalException {

		SearchContext searchContext = buildSearchContext(
			companyId, groupIds, keywords, status, start, end, sort);

		return searchCommercePriceLists(searchContext);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommercePriceList updateCommercePriceList(
			long commercePriceListId, long commerceCurrencyId,
			long parentCommercePriceListId, String name, double priority,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, ServiceContext serviceContext)
		throws PortalException {

		// Commerce price list

		User user = userLocalService.getUser(serviceContext.getUserId());

		CommercePriceList commercePriceList =
			commercePriceListPersistence.findByPrimaryKey(commercePriceListId);

		validate(
			commercePriceList.getGroupId(), commerceCurrencyId,
			parentCommercePriceListId);

		Date displayDate = null;
		Date expirationDate = null;
		Date now = new Date();

		displayDate = PortalUtil.getDate(
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, user.getTimeZone(),
			CommercePriceListDisplayDateException.class);

		if (!neverExpire) {
			expirationDate = PortalUtil.getDate(
				expirationDateMonth, expirationDateDay, expirationDateYear,
				expirationDateHour, expirationDateMinute, user.getTimeZone(),
				CommercePriceListExpirationDateException.class);
		}

		commercePriceList.setCommerceCurrencyId(commerceCurrencyId);
		commercePriceList.setParentCommercePriceListId(
			parentCommercePriceListId);
		commercePriceList.setName(name);
		commercePriceList.setPriority(priority);
		commercePriceList.setDisplayDate(displayDate);
		commercePriceList.setExpirationDate(expirationDate);

		if ((expirationDate == null) || expirationDate.after(now)) {
			commercePriceList.setStatus(WorkflowConstants.STATUS_DRAFT);
		}
		else {
			commercePriceList.setStatus(WorkflowConstants.STATUS_EXPIRED);
		}

		commercePriceList.setStatusByUserId(user.getUserId());
		commercePriceList.setStatusDate(serviceContext.getModifiedDate(now));
		commercePriceList.setExpandoBridgeAttributes(serviceContext);

		commercePriceListPersistence.update(commercePriceList);

		// Workflow

		commercePriceList = startWorkflowInstance(
			user.getUserId(), commercePriceList, serviceContext);

		cleanPriceListCache(commercePriceList.getCompanyId());

		return commercePriceList;
	}

	@Override
	public CommercePriceList updateCommercePriceList(
			long commercePriceListId, long commerceCurrencyId, String name,
			double priority, int displayDateMonth, int displayDateDay,
			int displayDateYear, int displayDateHour, int displayDateMinute,
			int expirationDateMonth, int expirationDateDay,
			int expirationDateYear, int expirationDateHour,
			int expirationDateMinute, boolean neverExpire,
			ServiceContext serviceContext)
		throws PortalException {

		return updateCommercePriceList(
			commercePriceListId, commerceCurrencyId, 0, name, priority,
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute,
			neverExpire, serviceContext);
	}

	@Override
	public void updateCommercePriceListCurrencies(long commerceCurrencyId)
		throws PortalException {

		List<CommercePriceList> commercePriceLists =
			commercePriceListPersistence.findByCommerceCurrencyId(
				commerceCurrencyId);

		for (CommercePriceList commercePriceList : commercePriceLists) {
			commercePriceList.setCommerceCurrencyId(0);

			commercePriceListPersistence.update(commercePriceList);

			cleanPriceListCache(commercePriceList.getCompanyId());

			doReindex(commercePriceList.getCommercePriceListId());
		}
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommercePriceList updateExternalReferenceCode(
			CommercePriceList commercePriceList, String externalReferenceCode)
		throws PortalException {

		if (Validator.isBlank(externalReferenceCode)) {
			externalReferenceCode = null;
		}

		commercePriceList.setExternalReferenceCode(externalReferenceCode);

		commercePriceListPersistence.update(commercePriceList);

		cleanPriceListCache(commercePriceList.getCompanyId());

		return commercePriceList;
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommercePriceList updateStatus(
			long userId, long commercePriceListId, int status,
			ServiceContext serviceContext,
			Map<String, Serializable> workflowContext)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		CommercePriceList commercePriceList =
			commercePriceListPersistence.findByPrimaryKey(commercePriceListId);

		if ((status == WorkflowConstants.STATUS_APPROVED) &&
			(commercePriceList.getDisplayDate() != null) &&
			now.before(commercePriceList.getDisplayDate())) {

			status = WorkflowConstants.STATUS_SCHEDULED;
		}

		Date modifiedDate = serviceContext.getModifiedDate(now);

		commercePriceList.setModifiedDate(modifiedDate);

		if (status == WorkflowConstants.STATUS_APPROVED) {
			Date expirationDate = commercePriceList.getExpirationDate();

			if ((expirationDate != null) && expirationDate.before(now)) {
				commercePriceList.setExpirationDate(null);
			}
		}

		if (status == WorkflowConstants.STATUS_EXPIRED) {
			commercePriceList.setExpirationDate(now);
		}

		commercePriceList.setStatus(status);
		commercePriceList.setStatusByUserId(user.getUserId());
		commercePriceList.setStatusByUserName(user.getFullName());
		commercePriceList.setStatusDate(modifiedDate);

		commercePriceListPersistence.update(commercePriceList);

		cleanPriceListCache(commercePriceList.getCompanyId());

		return commercePriceList;
	}

	/**
	 * This method is used to insert a new CommercePriceList or update an
	 * existing one
	 *
	 * @param  commercePriceListId - <b>Only</b> used when updating an entity;
	 *         the matching one will be updated
	 * @param  commerceCurrencyId
	 * @param  parentCommercePriceListId
	 * @param  name
	 * @param  priority
	 * @param  displayDateMonth
	 * @param  displayDateDay
	 * @param  displayDateYear
	 * @param  displayDateHour
	 * @param  displayDateMinute
	 * @param  expirationDateMonth
	 * @param  expirationDateDay
	 * @param  expirationDateYear
	 * @param  expirationDateHour
	 * @param  expirationDateMinute
	 * @param  externalReferenceCode - The external identifier code from a 3rd
	 *         party system to be able to locate the same entity in the portal
	 *         <b>Only</b> used when updating an entity; the first entity with a
	 *         matching reference code one will be updated
	 * @param  neverExpire
	 * @param  serviceContext
	 * @return
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommercePriceList upsertCommercePriceList(
			long groupId, long userId, long commercePriceListId,
			long commerceCurrencyId, long parentCommercePriceListId,
			String name, double priority, int displayDateMonth,
			int displayDateDay, int displayDateYear, int displayDateHour,
			int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			String externalReferenceCode, boolean neverExpire,
			ServiceContext serviceContext)
		throws PortalException {

		// Update

		if (commercePriceListId > 0) {
			try {
				return updateCommercePriceList(
					commercePriceListId, commerceCurrencyId,
					parentCommercePriceListId, name, priority, displayDateMonth,
					displayDateDay, displayDateYear, displayDateHour,
					displayDateMinute, expirationDateMonth, expirationDateDay,
					expirationDateYear, expirationDateHour,
					expirationDateMinute, neverExpire, serviceContext);
			}
			catch (NoSuchPriceListException nsple) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"Unable to find price list with ID: " +
							commercePriceListId);
				}
			}
		}

		if (Validator.isBlank(externalReferenceCode)) {
			externalReferenceCode = null;
		}

		if (Validator.isNotNull(externalReferenceCode)) {
			CommercePriceList commercePriceList =
				commercePriceListPersistence.fetchByC_ERC(
					serviceContext.getCompanyId(), externalReferenceCode);

			if (commercePriceList != null) {
				return commercePriceListLocalService.updateCommercePriceList(
					commercePriceList.getCommercePriceListId(),
					commerceCurrencyId, parentCommercePriceListId, name,
					priority, displayDateMonth, displayDateDay, displayDateYear,
					displayDateHour, displayDateMinute, expirationDateMonth,
					expirationDateDay, expirationDateYear, expirationDateHour,
					expirationDateMinute, neverExpire, serviceContext);
			}
		}

		// Add

		return commercePriceListLocalService.addCommercePriceList(
			groupId, userId, commerceCurrencyId, parentCommercePriceListId,
			name, priority, displayDateMonth, displayDateDay, displayDateYear,
			displayDateHour, displayDateMinute, expirationDateMonth,
			expirationDateDay, expirationDateYear, expirationDateHour,
			expirationDateMinute, externalReferenceCode, neverExpire,
			serviceContext);
	}

	@Override
	public CommercePriceList upsertCommercePriceList(
			long groupId, long userId, long commercePriceListId,
			long commerceCurrencyId, String name, double priority,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			String externalReferenceCode, boolean neverExpire,
			ServiceContext serviceContext)
		throws PortalException {

		return commercePriceListLocalService.upsertCommercePriceList(
			groupId, userId, commercePriceListId, commerceCurrencyId, 0, name,
			priority, displayDateMonth, displayDateDay, displayDateYear,
			displayDateHour, displayDateMinute, expirationDateMonth,
			expirationDateDay, expirationDateYear, expirationDateHour,
			expirationDateMinute, externalReferenceCode, neverExpire,
			serviceContext);
	}

	protected SearchContext buildSearchContext(
		long companyId, long groupId, long commerceAccountId,
		long[] commerceAccountGroupIds) {

		SearchContext searchContext = new SearchContext();

		Map<String, Serializable> attributes = new HashMap<>();

		attributes.put(Field.STATUS, WorkflowConstants.STATUS_APPROVED);
		attributes.put("commerceAccountGroupIds", commerceAccountGroupIds);
		attributes.put("commerceAccountId", commerceAccountId);

		searchContext.setAttributes(attributes);

		searchContext.setCompanyId(companyId);
		searchContext.setStart(0);
		searchContext.setEnd(1);
		searchContext.setGroupIds(new long[] {groupId});

		QueryConfig queryConfig = searchContext.getQueryConfig();

		queryConfig.setHighlightEnabled(false);
		queryConfig.setScoreEnabled(false);

		Sort sort = SortFactoryUtil.create(
			Field.PRIORITY + "_Number_sortable", true);

		searchContext.setSorts(sort);

		return searchContext;
	}

	protected SearchContext buildSearchContext(
		long companyId, long[] groupIds, String keywords, int status, int start,
		int end, Sort sort) {

		SearchContext searchContext = new SearchContext();

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("keywords", keywords);

		Map<String, Serializable> attributes = new HashMap<>();

		attributes.put(Field.ENTRY_CLASS_PK, keywords);
		attributes.put(Field.NAME, keywords);
		attributes.put(Field.STATUS, status);
		attributes.put(Field.USER_NAME, keywords);
		attributes.put("params", params);

		searchContext.setAttributes(attributes);

		searchContext.setCompanyId(companyId);
		searchContext.setStart(start);
		searchContext.setEnd(end);
		searchContext.setGroupIds(groupIds);

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

	protected void checkCommercePriceListsByDisplayDate()
		throws PortalException {

		List<CommercePriceList> commercePriceLists =
			commercePriceListPersistence.findByLtD_S(
				new Date(), WorkflowConstants.STATUS_SCHEDULED);

		for (CommercePriceList commercePriceList : commercePriceLists) {
			long userId = PortalUtil.getValidUserId(
				commercePriceList.getCompanyId(),
				commercePriceList.getUserId());

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCommand(Constants.UPDATE);
			serviceContext.setScopeGroupId(commercePriceList.getGroupId());

			commercePriceListLocalService.updateStatus(
				userId, commercePriceList.getCommercePriceListId(),
				WorkflowConstants.STATUS_APPROVED, serviceContext,
				new HashMap<String, Serializable>());

			cleanPriceListCache(commercePriceList.getCompanyId());
		}
	}

	protected void checkCommercePriceListsByExpirationDate()
		throws PortalException {

		List<CommercePriceList> commercePriceLists =
			commercePriceListFinder.findByExpirationDate(
				new Date(),
				new QueryDefinition<CommercePriceList>(
					WorkflowConstants.STATUS_APPROVED));

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Expiring " + commercePriceLists.size() +
					" commerce price lists");
		}

		if ((commercePriceLists != null) && !commercePriceLists.isEmpty()) {
			for (CommercePriceList commercePriceList : commercePriceLists) {
				long userId = PortalUtil.getValidUserId(
					commercePriceList.getCompanyId(),
					commercePriceList.getUserId());

				ServiceContext serviceContext = new ServiceContext();

				serviceContext.setCommand(Constants.UPDATE);
				serviceContext.setScopeGroupId(commercePriceList.getGroupId());

				commercePriceListLocalService.updateStatus(
					userId, commercePriceList.getCommercePriceListId(),
					WorkflowConstants.STATUS_EXPIRED, serviceContext,
					new HashMap<String, Serializable>());

				cleanPriceListCache(commercePriceList.getCompanyId());
			}
		}
	}

	protected void doReindex(long commercePriceListId) throws PortalException {
		Indexer<CommercePriceList> indexer =
			IndexerRegistryUtil.nullSafeGetIndexer(CommercePriceList.class);

		indexer.reindex(CommercePriceList.class.getName(), commercePriceListId);
	}

	protected List<CommercePriceList> getCommercePriceLists(Hits hits)
		throws PortalException {

		List<Document> documents = hits.toList();

		List<CommercePriceList> commercePriceLists = new ArrayList<>(
			documents.size());

		for (Document document : documents) {
			long commercePriceListId = GetterUtil.getLong(
				document.get(Field.ENTRY_CLASS_PK));

			CommercePriceList commercePriceList = fetchCommercePriceList(
				commercePriceListId);

			if (commercePriceList == null) {
				commercePriceLists = null;

				Indexer<CommercePriceList> indexer =
					IndexerRegistryUtil.getIndexer(CommercePriceList.class);

				long companyId = GetterUtil.getLong(
					document.get(Field.COMPANY_ID));

				indexer.delete(companyId, document.getUID());
			}
			else if (commercePriceLists != null) {
				commercePriceLists.add(commercePriceList);
			}
		}

		return commercePriceLists;
	}

	protected BaseModelSearchResult<CommercePriceList> searchCommercePriceLists(
			SearchContext searchContext)
		throws PortalException {

		Indexer<CommercePriceList> indexer =
			IndexerRegistryUtil.nullSafeGetIndexer(CommercePriceList.class);

		for (int i = 0; i < 10; i++) {
			Hits hits = indexer.search(searchContext, _SELECTED_FIELD_NAMES);

			List<CommercePriceList> commercePriceLists = getCommercePriceLists(
				hits);

			if (commercePriceLists != null) {
				return new BaseModelSearchResult<>(
					commercePriceLists, hits.getLength());
			}
		}

		throw new SearchException(
			"Unable to fix the search index after 10 attempts");
	}

	protected CommercePriceList startWorkflowInstance(
			long userId, CommercePriceList commercePriceList,
			ServiceContext serviceContext)
		throws PortalException {

		Map<String, Serializable> workflowContext = new HashMap<>();

		return WorkflowHandlerRegistryUtil.startWorkflowInstance(
			commercePriceList.getCompanyId(), commercePriceList.getGroupId(),
			userId, CommercePriceList.class.getName(),
			commercePriceList.getCommercePriceListId(), commercePriceList,
			serviceContext, workflowContext);
	}

	protected void validate(
			long groupId, long commerceCurrencyId,
			long parentCommercePriceListId)
		throws PortalException {

		if (parentCommercePriceListId > 0) {
			CommercePriceList commercePriceList =
				commercePriceListLocalService.fetchCommercePriceList(
					parentCommercePriceListId);

			if ((commercePriceList != null) &&
				(commercePriceList.getGroupId() != groupId)) {

				throw new CommercePriceListParentPriceListGroupIdException();
			}
		}

		CommerceCurrency commerceCurrency =
			_commerceCurrencyLocalService.fetchCommerceCurrency(
				commerceCurrencyId);

		if (commerceCurrency == null) {
			throw new CommercePriceListCurrencyException();
		}
	}

	protected void validateExternalReferenceCode(
			long companyId, String externalReferenceCode)
		throws PortalException {

		if (Validator.isNull(externalReferenceCode)) {
			return;
		}

		CommercePriceList commercePriceList =
			commercePriceListPersistence.fetchByC_ERC(
				companyId, externalReferenceCode);

		if (commercePriceList != null) {
			throw new DuplicateCommercePriceListException(
				"There is another commerce price list with external " +
					"reference code " + externalReferenceCode);
		}
	}

	private static final String[] _SELECTED_FIELD_NAMES = {
		Field.ENTRY_CLASS_PK, Field.COMPANY_ID, Field.GROUP_ID, Field.UID
	};

	private static final Log _log = LogFactoryUtil.getLog(
		CommercePriceListLocalServiceImpl.class);

	@ServiceReference(type = CommerceCurrencyLocalService.class)
	private CommerceCurrencyLocalService _commerceCurrencyLocalService;

}