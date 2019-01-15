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

import com.liferay.commerce.price.list.exception.DuplicateCommercePriceEntryException;
import com.liferay.commerce.price.list.exception.NoSuchPriceEntryException;
import com.liferay.commerce.price.list.model.CommercePriceEntry;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.base.CommercePriceEntryLocalServiceBaseImpl;
import com.liferay.commerce.product.exception.NoSuchCPInstanceException;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 * @author Zoltán Takács
 */
public class CommercePriceEntryLocalServiceImpl
	extends CommercePriceEntryLocalServiceBaseImpl {

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	@Override
	public CommercePriceEntry addCommercePriceEntry(
			long cpInstanceId, long commercePriceListId, BigDecimal price,
			BigDecimal promoPrice, ServiceContext serviceContext)
		throws PortalException {

		return addCommercePriceEntry(
			cpInstanceId, commercePriceListId, null, price, promoPrice,
			serviceContext);
	}

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	@Override
	public CommercePriceEntry addCommercePriceEntry(
			long cpInstanceId, long commercePriceListId,
			String externalReferenceCode, BigDecimal price,
			BigDecimal promoPrice, ServiceContext serviceContext)
		throws PortalException {

		CPInstance cpInstance = _cpInstanceLocalService.getCPInstance(
			cpInstanceId);

		CPDefinition cpDefinition = _cpDefinitionLocalService.getCPDefinition(
			cpInstance.getCPDefinitionId());

		return commercePriceEntryLocalService.addCommercePriceEntry(
			cpDefinition.getCProductId(), cpInstance.getUuid(),
			commercePriceListId, externalReferenceCode, price, promoPrice,
			serviceContext);
	}

	@Override
	public CommercePriceEntry addCommercePriceEntry(
			long cProductId, String cpInstanceUuid, long commercePriceListId,
			BigDecimal price, BigDecimal promoPrice,
			ServiceContext serviceContext)
		throws PortalException {

		return addCommercePriceEntry(
			cProductId, cpInstanceUuid, commercePriceListId, null, price,
			promoPrice, serviceContext);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommercePriceEntry addCommercePriceEntry(
			long cProductId, String cpInstanceUuid, long commercePriceListId,
			String externalReferenceCode, BigDecimal price,
			BigDecimal promoPrice, ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(serviceContext.getUserId());
		long groupId = serviceContext.getScopeGroupId();

		validate(commercePriceListId, cpInstanceUuid);

		long commercePriceEntryId = counterLocalService.increment();

		CommercePriceEntry commercePriceEntry =
			commercePriceEntryPersistence.create(commercePriceEntryId);

		commercePriceEntry.setUuid(serviceContext.getUuid());
		commercePriceEntry.setGroupId(groupId);
		commercePriceEntry.setCompanyId(user.getCompanyId());
		commercePriceEntry.setUserId(user.getUserId());
		commercePriceEntry.setUserName(user.getFullName());
		commercePriceEntry.setCommercePriceListId(commercePriceListId);
		commercePriceEntry.setPrice(price);
		commercePriceEntry.setPromoPrice(promoPrice);
		commercePriceEntry.setExpandoBridgeAttributes(serviceContext);
		commercePriceEntry.setExternalReferenceCode(externalReferenceCode);
		commercePriceEntry.setCProductId(cProductId);
		commercePriceEntry.setCPInstanceUuid(cpInstanceUuid);

		return commercePriceEntryPersistence.update(commercePriceEntry);
	}

	@Override
	public void deleteCommercePriceEntries(long commercePriceListId)
		throws PortalException {

		List<CommercePriceEntry> commercePriceEntries =
			commercePriceEntryLocalService.getCommercePriceEntries(
				commercePriceListId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (CommercePriceEntry commercePriceEntry : commercePriceEntries) {
			commercePriceEntryLocalService.deleteCommercePriceEntry(
				commercePriceEntry);
		}
	}

	@Override
	public void deleteCommercePriceEntries(String cpInstanceUuid)
		throws PortalException {

		List<CommercePriceEntry> commercePriceEntries =
			commercePriceEntryPersistence.findByCPInstanceUuid(cpInstanceUuid);

		for (CommercePriceEntry commercePriceEntry : commercePriceEntries) {
			commercePriceEntryLocalService.deleteCommercePriceEntry(
				commercePriceEntry);
		}
	}

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	@Override
	public void deleteCommercePriceEntriesByCPInstanceId(long cpInstanceId)
		throws PortalException {

		CPInstance cpInstance = _cpInstanceLocalService.getCPInstance(
			cpInstanceId);

		if (cpInstance != null) {
			commercePriceEntryLocalService.deleteCommercePriceEntries(
				cpInstance.getUuid());
		}
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public CommercePriceEntry deleteCommercePriceEntry(
			CommercePriceEntry commercePriceEntry)
		throws PortalException {

		// Commerce tier price entries

		commerceTierPriceEntryLocalService.deleteCommerceTierPriceEntries(
			commercePriceEntry.getCommercePriceEntryId());

		// Commerce price entry

		commercePriceEntryPersistence.remove(commercePriceEntry);

		// Expando

		expandoRowLocalService.deleteRows(
			commercePriceEntry.getCommercePriceEntryId());

		return commercePriceEntry;
	}

	@Override
	public CommercePriceEntry deleteCommercePriceEntry(
			long commercePriceEntryId)
		throws PortalException {

		CommercePriceEntry commercePriceEntry =
			commercePriceEntryPersistence.findByPrimaryKey(
				commercePriceEntryId);

		return commercePriceEntryLocalService.deleteCommercePriceEntry(
			commercePriceEntry);
	}

	@Override
	public CommercePriceEntry fetchByExternalReferenceCode(
		long companyId, String externalReferenceCode) {

		return commercePriceEntryPersistence.fetchByC_ERC(
			companyId, externalReferenceCode);
	}

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	@Override
	public CommercePriceEntry fetchCommercePriceEntry(
			long cpInstanceId, long commercePriceListId)
		throws PortalException {

		CPInstance cpInstance = _cpInstanceLocalService.fetchCPInstance(
			cpInstanceId);

		if (cpInstance == null) {
			return null;
		}

		return commercePriceEntryLocalService.fetchCommercePriceEntry(
			commercePriceListId, cpInstance.getUuid());
	}

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	@Override
	public CommercePriceEntry fetchCommercePriceEntry(
			long cpInstanceId, long commercePriceListId, boolean useAncestor)
		throws PortalException {

		CPInstance cpInstance = _cpInstanceLocalService.getCPInstance(
			cpInstanceId);

		return commercePriceEntryLocalService.fetchCommercePriceEntry(
			commercePriceListId, cpInstance.getUuid(), useAncestor);
	}

	@Override
	public CommercePriceEntry fetchCommercePriceEntry(
			long commercePriceListId, String cpInstanceUuid)
		throws PortalException {

		return commercePriceEntryPersistence.fetchByC_C(
			commercePriceListId, cpInstanceUuid);
	}

	@Override
	public CommercePriceEntry fetchCommercePriceEntry(
			long commercePriceListId, String cpInstanceUuid,
			boolean useAncestor)
		throws PortalException {

		CommercePriceEntry commercePriceEntry =
			commercePriceEntryLocalService.fetchCommercePriceEntry(
				commercePriceListId, cpInstanceUuid);

		if (!useAncestor || (commercePriceEntry != null)) {
			return commercePriceEntry;
		}

		CommercePriceList commercePriceList =
			commercePriceListLocalService.fetchCommercePriceList(
				commercePriceListId);

		if ((commercePriceList == null) ||
			(commercePriceList.getParentCommercePriceListId() == 0)) {

			return null;
		}

		return commercePriceEntryLocalService.fetchCommercePriceEntry(
			commercePriceList.getParentCommercePriceListId(), cpInstanceUuid,
			useAncestor);
	}

	@Override
	public List<CommercePriceEntry> getCommercePriceEntries(
		long commercePriceListId, int start, int end) {

		return commercePriceEntryPersistence.findByCommercePriceListId(
			commercePriceListId, start, end);
	}

	@Override
	public List<CommercePriceEntry> getCommercePriceEntries(
		long commercePriceListId, int start, int end,
		OrderByComparator<CommercePriceEntry> orderByComparator) {

		return commercePriceEntryPersistence.findByCommercePriceListId(
			commercePriceListId, start, end, orderByComparator);
	}

	@Override
	public List<CommercePriceEntry> getCommercePriceEntriesByGroupId(
		long groupId, int start, int end) {

		return commercePriceEntryPersistence.findByGroupId(groupId, start, end);
	}

	@Override
	public int getCommercePriceEntriesCount(long commercePriceListId) {
		return commercePriceEntryPersistence.countByCommercePriceListId(
			commercePriceListId);
	}

	@Override
	public int getCommercePriceEntriesCountByGroupId(long groupId) {
		return commercePriceEntryPersistence.countByGroupId(groupId);
	}

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	@Override
	public List<CommercePriceEntry> getInstanceCommercePriceEntries(
			long cpInstanceId, int start, int end)
		throws PortalException {

		CPInstance cpInstance = _cpInstanceLocalService.getCPInstance(
			cpInstanceId);

		return commercePriceEntryLocalService.getInstanceCommercePriceEntries(
			cpInstance.getUuid(), start, end);
	}

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	@Override
	public List<CommercePriceEntry> getInstanceCommercePriceEntries(
			long cpInstanceId, int start, int end,
			OrderByComparator<CommercePriceEntry> orderByComparator)
		throws PortalException {

		CPInstance cpInstance = _cpInstanceLocalService.getCPInstance(
			cpInstanceId);

		return commercePriceEntryLocalService.getInstanceCommercePriceEntries(
			cpInstance.getUuid(), start, end, orderByComparator);
	}

	@Override
	public List<CommercePriceEntry> getInstanceCommercePriceEntries(
			String cpInstanceUuid, int start, int end)
		throws PortalException {

		return commercePriceEntryPersistence.findByCPInstanceUuid(
			cpInstanceUuid, start, end);
	}

	@Override
	public List<CommercePriceEntry> getInstanceCommercePriceEntries(
			String cpInstanceUuid, int start, int end,
			OrderByComparator<CommercePriceEntry> orderByComparator)
		throws PortalException {

		return commercePriceEntryPersistence.findByCPInstanceUuid(
			cpInstanceUuid, start, end, orderByComparator);
	}

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	@Override
	public int getInstanceCommercePriceEntriesCount(long cpInstanceId)
		throws PortalException {

		CPInstance cpInstance = _cpInstanceLocalService.getCPInstance(
			cpInstanceId);

		return commercePriceEntryLocalService.
			getInstanceCommercePriceEntriesCount(cpInstance.getUuid());
	}

	@Override
	public int getInstanceCommercePriceEntriesCount(String cpInstanceUuid)
		throws PortalException {

		return commercePriceEntryPersistence.countByCPInstanceUuid(
			cpInstanceUuid);
	}

	@Override
	public Hits search(SearchContext searchContext) {
		try {
			Indexer<CommercePriceEntry> indexer =
				IndexerRegistryUtil.nullSafeGetIndexer(
					CommercePriceEntry.class);

			return indexer.search(searchContext);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@Override
	public BaseModelSearchResult<CommercePriceEntry> searchCommercePriceEntries(
			long companyId, long groupId, long commercePriceListId,
			String keywords, int start, int end, Sort sort)
		throws PortalException {

		SearchContext searchContext = buildSearchContext(
			companyId, groupId, commercePriceListId, keywords, start, end,
			sort);

		return searchCommercePriceEntries(searchContext);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommercePriceEntry setHasTierPrice(
			long commercePriceEntryId, boolean hasTierPrice)
		throws PortalException {

		CommercePriceEntry commercePriceEntry =
			commercePriceEntryPersistence.findByPrimaryKey(
				commercePriceEntryId);

		commercePriceEntry.setHasTierPrice(hasTierPrice);

		return commercePriceEntryPersistence.update(commercePriceEntry);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommercePriceEntry updateCommercePriceEntry(
			long commercePriceEntryId, BigDecimal price, BigDecimal promoPrice,
			ServiceContext serviceContext)
		throws PortalException {

		CommercePriceEntry commercePriceEntry =
			commercePriceEntryPersistence.findByPrimaryKey(
				commercePriceEntryId);

		commercePriceEntry.setExpandoBridgeAttributes(serviceContext);
		commercePriceEntry.setPrice(price);
		commercePriceEntry.setPromoPrice(promoPrice);

		return commercePriceEntryPersistence.update(commercePriceEntry);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommercePriceEntry updateExternalReferenceCode(
			CommercePriceEntry commercePriceEntry, String externalReferenceCode)
		throws PortalException {

		commercePriceEntry.setExternalReferenceCode(externalReferenceCode);

		return commercePriceEntryPersistence.update(commercePriceEntry);
	}

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	@Override
	public CommercePriceEntry upsertCommercePriceEntry(
			long commercePriceEntryId, long cpInstanceId,
			long commercePriceListId, String externalReferenceCode,
			BigDecimal price, BigDecimal promoPrice,
			String skuExternalReferenceCode, ServiceContext serviceContext)
		throws PortalException {

		CPInstance cpInstance = _cpInstanceLocalService.getCPInstance(
			cpInstanceId);

		CPDefinition cpDefinition = _cpDefinitionLocalService.getCPDefinition(
			cpInstance.getCPDefinitionId());

		return commercePriceEntryLocalService.upsertCommercePriceEntry(
			commercePriceEntryId, cpDefinition.getCProductId(),
			cpInstance.getUuid(), commercePriceListId, externalReferenceCode,
			price, promoPrice, skuExternalReferenceCode, serviceContext);
	}

	/**
	 * This method is used to insert a new CommercePriceEntry or update an
	 * existing one
	 *
	 * @param  commercePriceEntryId - <b>Only</b> used when updating an entity
	 *         the matching one will be updated
	 * @param  cpInstanceId - <b>Only</b> used when adding a new entity
	 * @param  commercePriceListId - <b>Only</b> used when adding a new entity
	 *         to a price list
	 * @param  externalReferenceCode - The external identifier code from a 3rd
	 *         party system to be able to locate the same entity in the portal
	 *         <b>Only</b> used when updating an entity; the first entity with a
	 *         matching reference code one will be updated
	 * @param  price
	 * @param  promoPrice
	 * @param  skuExternalReferenceCode - <b>Only</b> used when adding a new
	 *         entity, similar as <code>cpInstanceId</code> but the external
	 *         identifier code from a 3rd party system. If cpInstanceId is used,
	 *         it doesn't have any effect, otherwise it tries to fetch the
	 *         CPInstance against the external code reference
	 * @param  serviceContext
	 * @return CommercePriceEntry
	 * @throws PortalException
	 * @review
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommercePriceEntry upsertCommercePriceEntry(
			long commercePriceEntryId, long cProductId, String cpInstanceUuid,
			long commercePriceListId, String externalReferenceCode,
			BigDecimal price, BigDecimal promoPrice,
			String skuExternalReferenceCode, ServiceContext serviceContext)
		throws PortalException {

		// Update

		if (commercePriceEntryId > 0) {
			try {
				return updateCommercePriceEntry(
					commercePriceEntryId, price, promoPrice, serviceContext);
			}
			catch (NoSuchPriceEntryException nspee) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"Unable to find price entry with ID: " +
							commercePriceEntryId);
				}
			}
		}

		if (Validator.isNotNull(externalReferenceCode)) {
			CommercePriceEntry commercePriceEntry =
				commercePriceEntryPersistence.fetchByC_ERC(
					serviceContext.getCompanyId(), externalReferenceCode);

			if (commercePriceEntry != null) {
				return updateCommercePriceEntry(
					commercePriceEntry.getCommercePriceEntryId(), price,
					promoPrice, serviceContext);
			}
		}

		// Add

		if ((cProductId > 0) && (cpInstanceUuid != null)) {
			validate(commercePriceListId, cpInstanceUuid);

			return addCommercePriceEntry(
				cProductId, cpInstanceUuid, commercePriceListId,
				externalReferenceCode, price, promoPrice, serviceContext);
		}

		if (Validator.isNotNull(skuExternalReferenceCode)) {
			CPInstance cpInstance =
				_cpInstanceLocalService.getCPInstanceByExternalReferenceCode(
					serviceContext.getCompanyId(), skuExternalReferenceCode);

			CPDefinition cpDefinition =
				_cpDefinitionLocalService.getCPDefinition(
					cpInstance.getCPDefinitionId());

			validate(commercePriceListId, cpInstance.getUuid());

			return addCommercePriceEntry(
				cpDefinition.getCProductId(), cpInstance.getUuid(),
				commercePriceListId, externalReferenceCode, price, promoPrice,
				serviceContext);
		}

		StringBundler sb = new StringBundler(9);

		sb.append("{cProductId=");
		sb.append(cProductId);
		sb.append(StringPool.COMMA_AND_SPACE);
		sb.append("{cpInstanceUuid=");
		sb.append(cpInstanceUuid);
		sb.append(StringPool.COMMA_AND_SPACE);
		sb.append("skuExternalReferenceCode=");
		sb.append(skuExternalReferenceCode);
		sb.append(CharPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCPInstanceException(sb.toString());
	}

	protected SearchContext buildSearchContext(
		long companyId, long groupId, long commercePriceListId, String keywords,
		int start, int end, Sort sort) {

		SearchContext searchContext = new SearchContext();

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("keywords", keywords);

		Map<String, Serializable> attributes = new HashMap<>();

		attributes.put(Field.ENTRY_CLASS_PK, keywords);
		attributes.put("commercePriceListId", commercePriceListId);
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

	protected List<CommercePriceEntry> getCommercePriceEntries(Hits hits)
		throws PortalException {

		List<Document> documents = hits.toList();

		List<CommercePriceEntry> commercePriceEntries = new ArrayList<>(
			documents.size());

		for (Document document : documents) {
			long commercePriceEntryId = GetterUtil.getLong(
				document.get(Field.ENTRY_CLASS_PK));

			CommercePriceEntry commercePriceEntry = fetchCommercePriceEntry(
				commercePriceEntryId);

			if (commercePriceEntry == null) {
				commercePriceEntries = null;

				Indexer<CommercePriceEntry> indexer =
					IndexerRegistryUtil.getIndexer(CommercePriceEntry.class);

				long companyId = GetterUtil.getLong(
					document.get(Field.COMPANY_ID));

				indexer.delete(companyId, document.getUID());
			}
			else if (commercePriceEntries != null) {
				commercePriceEntries.add(commercePriceEntry);
			}
		}

		return commercePriceEntries;
	}

	protected BaseModelSearchResult<CommercePriceEntry>
			searchCommercePriceEntries(SearchContext searchContext)
		throws PortalException {

		Indexer<CommercePriceEntry> indexer =
			IndexerRegistryUtil.nullSafeGetIndexer(CommercePriceEntry.class);

		for (int i = 0; i < 10; i++) {
			Hits hits = indexer.search(searchContext, _SELECTED_FIELD_NAMES);

			List<CommercePriceEntry> commercePriceEntries =
				getCommercePriceEntries(hits);

			if (commercePriceEntries != null) {
				return new BaseModelSearchResult<>(
					commercePriceEntries, hits.getLength());
			}
		}

		throw new SearchException(
			"Unable to fix the search index after 10 attempts");
	}

	protected void validate(long commercePriceListId, String cpInstanceUuid)
		throws PortalException {

		CommercePriceEntry commercePriceEntry =
			commercePriceEntryPersistence.fetchByC_C(
				commercePriceListId, cpInstanceUuid);

		if (commercePriceEntry != null) {
			throw new DuplicateCommercePriceEntryException();
		}
	}

	private static final String[] _SELECTED_FIELD_NAMES =
		{Field.ENTRY_CLASS_PK, Field.COMPANY_ID, Field.GROUP_ID, Field.UID};

	private static final Log _log = LogFactoryUtil.getLog(
		CommercePriceEntryLocalServiceImpl.class);

	@ServiceReference(type = CPDefinitionLocalService.class)
	private CPDefinitionLocalService _cpDefinitionLocalService;

	@ServiceReference(type = CPInstanceLocalService.class)
	private CPInstanceLocalService _cpInstanceLocalService;

}