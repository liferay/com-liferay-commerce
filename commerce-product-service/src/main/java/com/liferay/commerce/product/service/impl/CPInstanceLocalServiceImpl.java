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

package com.liferay.commerce.product.service.impl;

import com.liferay.commerce.product.exception.CPInstanceDisplayDateException;
import com.liferay.commerce.product.exception.CPInstanceExpirationDateException;
import com.liferay.commerce.product.exception.CPInstanceJsonException;
import com.liferay.commerce.product.exception.CPInstanceSkuException;
import com.liferay.commerce.product.exception.NoSuchCPInstanceException;
import com.liferay.commerce.product.exception.NoSuchSkuContributorCPDefinitionOptionRelException;
import com.liferay.commerce.product.internal.util.SKUCombinationsIterator;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPDefinitionOptionRel;
import com.liferay.commerce.product.model.CPDefinitionOptionValueRel;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CProduct;
import com.liferay.commerce.product.search.CPInstanceIndexer;
import com.liferay.commerce.product.service.base.CPInstanceLocalServiceBaseImpl;
import com.liferay.commerce.product.util.DDMFormValuesUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryDefinition;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
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
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class CPInstanceLocalServiceImpl extends CPInstanceLocalServiceBaseImpl {

	@Override
	public CPInstance addCPInstance(
			long cpDefinitionId, long groupId, String sku, String gtin,
			String manufacturerPartNumber, boolean purchasable, String json,
			boolean published, int displayDateMonth, int displayDateDay,
			int displayDateYear, int displayDateHour, int displayDateMinute,
			int expirationDateMonth, int expirationDateDay,
			int expirationDateYear, int expirationDateHour,
			int expirationDateMinute, boolean neverExpire,
			ServiceContext serviceContext)
		throws PortalException {

		CPDefinition cpDefinition = cpDefinitionLocalService.getCPDefinition(
			cpDefinitionId);

		return cpInstanceLocalService.addCPInstance(
			cpDefinitionId, groupId, sku, gtin, manufacturerPartNumber,
			purchasable, json, cpDefinition.getWidth(),
			cpDefinition.getHeight(), cpDefinition.getDepth(),
			cpDefinition.getWeight(), BigDecimal.ZERO, BigDecimal.ZERO,
			BigDecimal.ZERO, published, StringPool.BLANK, displayDateMonth,
			displayDateDay, displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, neverExpire,
			serviceContext);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPInstance addCPInstance(
			long cpDefinitionId, long groupId, String sku, String gtin,
			String manufacturerPartNumber, boolean purchasable, String json,
			double width, double height, double depth, double weight,
			BigDecimal price, BigDecimal promoPrice, BigDecimal cost,
			boolean published, String externalReferenceCode,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, boolean overrideSubscriptionInfo,
			boolean subscriptionEnabled, int subscriptionLength,
			String subscriptionType,
			UnicodeProperties subscriptionTypeSettingsProperties,
			long maxSubscriptionCycles, ServiceContext serviceContext)
		throws PortalException {

		// Commerce product instance

		User user = userLocalService.getUser(serviceContext.getUserId());

		if (Validator.isBlank(externalReferenceCode)) {
			externalReferenceCode = null;
		}

		Date displayDate = null;
		Date expirationDate = null;
		Date now = new Date();

		displayDate = PortalUtil.getDate(
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, user.getTimeZone(),
			CPInstanceDisplayDateException.class);

		if (!neverExpire) {
			expirationDate = PortalUtil.getDate(
				expirationDateMonth, expirationDateDay, expirationDateYear,
				expirationDateHour, expirationDateMinute, user.getTimeZone(),
				CPInstanceExpirationDateException.class);
		}

		int status = WorkflowConstants.STATUS_APPROVED;

		if ((displayDate != null) && now.before(displayDate)) {
			status = WorkflowConstants.STATUS_SCHEDULED;
		}

		validate(0, cpDefinitionId, sku, json, status, serviceContext);

		long cpInstanceId = counterLocalService.increment();

		CPInstance cpInstance = cpInstancePersistence.create(cpInstanceId);

		if (cpDefinitionLocalService.isVersionable(cpDefinitionId)) {
			CPDefinition newCPDefinition =
				cpDefinitionLocalService.copyCPDefinition(cpDefinitionId);

			cpDefinitionId = newCPDefinition.getCPDefinitionId();
		}

		cpInstance.setUuid(serviceContext.getUuid());
		cpInstance.setGroupId(groupId);
		cpInstance.setCompanyId(user.getCompanyId());
		cpInstance.setUserId(user.getUserId());
		cpInstance.setUserName(user.getFullName());
		cpInstance.setCPDefinitionId(cpDefinitionId);
		cpInstance.setCPInstanceUuid(PortalUUIDUtil.generate());
		cpInstance.setSku(sku);
		cpInstance.setGtin(gtin);
		cpInstance.setManufacturerPartNumber(manufacturerPartNumber);
		cpInstance.setPurchasable(purchasable);
		cpInstance.setJson(json);
		cpInstance.setWidth(width);
		cpInstance.setHeight(height);
		cpInstance.setDepth(depth);
		cpInstance.setWeight(weight);
		cpInstance.setPrice(price);
		cpInstance.setPromoPrice(promoPrice);
		cpInstance.setCost(cost);
		cpInstance.setPublished(published);
		cpInstance.setExternalReferenceCode(externalReferenceCode);
		cpInstance.setDisplayDate(displayDate);
		cpInstance.setExpirationDate(expirationDate);
		cpInstance.setOverrideSubscriptionInfo(overrideSubscriptionInfo);
		cpInstance.setSubscriptionEnabled(subscriptionEnabled);
		cpInstance.setSubscriptionLength(subscriptionLength);
		cpInstance.setSubscriptionType(subscriptionType);
		cpInstance.setSubscriptionTypeSettingsProperties(
			subscriptionTypeSettingsProperties);
		cpInstance.setMaxSubscriptionCycles(maxSubscriptionCycles);

		if ((expirationDate == null) || expirationDate.after(now)) {
			cpInstance.setStatus(WorkflowConstants.STATUS_DRAFT);
		}
		else {
			cpInstance.setStatus(WorkflowConstants.STATUS_EXPIRED);
		}

		cpInstance.setStatusByUserId(user.getUserId());
		cpInstance.setStatusDate(serviceContext.getModifiedDate(now));
		cpInstance.setExpandoBridgeAttributes(serviceContext);

		cpInstancePersistence.update(cpInstance);

		reindexCPDefinition(cpDefinitionId);

		// Workflow

		return startWorkflowInstance(
			user.getUserId(), cpInstance, serviceContext);
	}

	@Override
	public CPInstance addCPInstance(
			long cpDefinitionId, long groupId, String sku, String gtin,
			String manufacturerPartNumber, boolean purchasable, String json,
			double width, double height, double depth, double weight,
			BigDecimal price, BigDecimal promoPrice, BigDecimal cost,
			boolean published, String externalReferenceCode,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, ServiceContext serviceContext)
		throws PortalException {

		return cpInstanceLocalService.addCPInstance(
			cpDefinitionId, groupId, sku, gtin, manufacturerPartNumber,
			purchasable, json, width, height, depth, weight, price, promoPrice,
			cost, published, externalReferenceCode, displayDateMonth,
			displayDateDay, displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, neverExpire, false, false,
			1, StringPool.BLANK, null, 0, serviceContext);
	}

	@Override
	public void buildCPInstances(
			long cpDefinitionId, ServiceContext serviceContext)
		throws PortalException {

		CPDefinition cpDefinition = cpDefinitionLocalService.getCPDefinition(
			cpDefinitionId);

		boolean neverExpire = false;

		if (cpDefinition.getExpirationDate() == null) {
			neverExpire = true;
		}

		Map<CPDefinitionOptionRel, CPDefinitionOptionValueRel[]>
			combinationGeneratorMap = new HashMap<>();

		List<CPDefinitionOptionRel> cpDefinitionOptionRels =
			cpDefinitionOptionRelLocalService.getCPDefinitionOptionRels(
				cpDefinitionId, true);

		if (cpDefinitionOptionRels.isEmpty()) {
			throw new NoSuchSkuContributorCPDefinitionOptionRelException();
		}

		for (CPDefinitionOptionRel cpDefinitionOptionRel :
				cpDefinitionOptionRels) {

			List<CPDefinitionOptionValueRel> cpDefinitionOptionValueRels =
				cpDefinitionOptionRel.getCPDefinitionOptionValueRels();

			CPDefinitionOptionValueRel[] cpDefinitionOptionValueRelArray =
				new CPDefinitionOptionValueRel
					[cpDefinitionOptionValueRels.size()];

			cpDefinitionOptionValueRelArray =
				cpDefinitionOptionValueRels.toArray(
					cpDefinitionOptionValueRelArray);

			combinationGeneratorMap.put(
				cpDefinitionOptionRel, cpDefinitionOptionValueRelArray);
		}

		SKUCombinationsIterator iterator = new SKUCombinationsIterator(
			combinationGeneratorMap);

		while (iterator.hasNext()) {
			CPDefinitionOptionValueRel[] cpDefinitionOptionValueRels =
				iterator.next();

			JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

			StringBundler skuSB = new StringBundler(
				cpDefinitionOptionValueRels.length + 1);

			for (CPDefinitionOptionValueRel cpDefinitionOptionValueRel :
					cpDefinitionOptionValueRels) {

				skuSB.append(
					StringUtil.toUpperCase(
						cpDefinitionOptionValueRel.getName(
							serviceContext.getLanguageId())));

				JSONArray valueJSONArray = JSONUtil.put(
					String.valueOf(
						cpDefinitionOptionValueRel.
							getCPDefinitionOptionValueRelId()));

				JSONObject jsonObject = JSONUtil.put(
					"key",
					cpDefinitionOptionValueRel.getCPDefinitionOptionRelId()
				).put(
					"value", valueJSONArray
				);

				jsonArray.put(jsonObject);
			}

			CPInstance cpInstance = cpInstancePersistence.fetchByC_S(
				cpDefinitionId, skuSB.toString());

			if (cpInstance != null) {
				continue;
			}

			try {
				addCPInstance(
					cpDefinitionId, cpDefinition.getGroupId(), skuSB.toString(),
					StringPool.BLANK, StringPool.BLANK, true,
					jsonArray.toString(), cpDefinition.getWidth(),
					cpDefinition.getHeight(), cpDefinition.getDepth(),
					cpDefinition.getWeight(), BigDecimal.ZERO, BigDecimal.ZERO,
					BigDecimal.ZERO, true, cpDefinition.getDisplayDate(),
					cpDefinition.getExpirationDate(), neverExpire,
					serviceContext);
			}
			catch (CPInstanceJsonException cpije) {
				if (_log.isDebugEnabled()) {
					_log.debug(cpije, cpije);
				}
			}
		}
	}

	@Override
	public void checkCPInstances() throws PortalException {
		checkCPInstancesByDisplayDate(0);
		checkCPInstancesByExpirationDate();
	}

	@Override
	public void checkCPInstancesByDisplayDate(long cpDefinitionId)
		throws PortalException {

		List<CPInstance> cpInstances = null;

		if (cpDefinitionId > 0) {
			cpInstances = cpInstancePersistence.findByC_LtD_S(
				cpDefinitionId, new Date(), WorkflowConstants.STATUS_SCHEDULED);
		}
		else {
			cpInstances = cpInstancePersistence.findByLtD_S(
				new Date(), WorkflowConstants.STATUS_SCHEDULED);
		}

		for (CPInstance cpInstance : cpInstances) {
			long userId = PortalUtil.getValidUserId(
				cpInstance.getCompanyId(), cpInstance.getUserId());

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCommand(Constants.UPDATE);
			serviceContext.setScopeGroupId(cpInstance.getGroupId());
			serviceContext.setUserId(userId);
			serviceContext.setWorkflowAction(WorkflowConstants.ACTION_PUBLISH);

			cpInstanceLocalService.updateStatus(
				userId, cpInstance.getCPInstanceId(),
				WorkflowConstants.STATUS_APPROVED, serviceContext,
				new HashMap<String, Serializable>());

			validate(
				cpInstance.getCPInstanceId(), cpInstance.getCPDefinitionId(),
				cpInstance.getSku(), cpInstance.getJson(),
				cpInstance.getStatus(), serviceContext);
		}
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public CPInstance deleteCPInstance(CPInstance cpInstance)
		throws PortalException {

		if (cpDefinitionLocalService.isVersionable(
				cpInstance.getCPDefinitionId())) {

			CPDefinition newCPDefinition =
				cpDefinitionLocalService.copyCPDefinition(
					cpInstance.getCPDefinitionId());

			cpInstance = cpInstancePersistence.findByC_C(
				newCPDefinition.getCPDefinitionId(),
				cpInstance.getCPInstanceUuid());
		}

		// Commerce product instance

		cpInstancePersistence.remove(cpInstance);

		// Expando

		expandoRowLocalService.deleteRows(cpInstance.getCPInstanceId());

		// Workflow

		workflowInstanceLinkLocalService.deleteWorkflowInstanceLinks(
			cpInstance.getCompanyId(), cpInstance.getGroupId(),
			CPInstance.class.getName(), cpInstance.getCPInstanceId());

		reindexCPDefinition(cpInstance.getCPDefinitionId());

		return cpInstance;
	}

	@Override
	public CPInstance deleteCPInstance(long cpInstanceId)
		throws PortalException {

		CPInstance cpInstance = cpInstancePersistence.findByPrimaryKey(
			cpInstanceId);

		return cpInstanceLocalService.deleteCPInstance(cpInstance);
	}

	@Override
	public void deleteCPInstances(long cpDefinitionId) throws PortalException {
		List<CPInstance> cpInstances =
			cpInstanceLocalService.getCPDefinitionInstances(
				cpDefinitionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (CPInstance cpInstance : cpInstances) {
			cpInstanceLocalService.deleteCPInstance(cpInstance);
		}
	}

	@Override
	public CPInstance fetchByExternalReferenceCode(
		long companyId, String externalReferenceCode) {

		if (Validator.isBlank(externalReferenceCode)) {
			return null;
		}

		return cpInstancePersistence.fetchByC_ERC(
			companyId, externalReferenceCode);
	}

	@Override
	public CPInstance fetchCProductInstance(
		long cProductId, String cpInstanceUuid) {

		CProduct cProduct = cProductLocalService.fetchCProduct(cProductId);

		if (cProduct == null) {
			return null;
		}

		return cpInstancePersistence.fetchByC_C(
			cProduct.getPublishedCPDefinitionId(), cpInstanceUuid);
	}

	@Override
	public List<CPInstance> getCPDefinitionInstances(long cpDefinitionId) {
		return cpInstancePersistence.findByCPDefinitionId(cpDefinitionId);
	}

	@Override
	public List<CPInstance> getCPDefinitionInstances(
		long cpDefinitionId, int start, int end) {

		return cpInstancePersistence.findByCPDefinitionId(
			cpDefinitionId, start, end);
	}

	@Override
	public List<CPInstance> getCPDefinitionInstances(
		long cpDefinitionId, int status, int start, int end,
		OrderByComparator<CPInstance> orderByComparator) {

		if (status == WorkflowConstants.STATUS_ANY) {
			return cpInstancePersistence.findByCPDefinitionId(
				cpDefinitionId, start, end, orderByComparator);
		}

		return cpInstancePersistence.findByC_ST(
			cpDefinitionId, status, start, end, orderByComparator);
	}

	@Override
	public int getCPDefinitionInstancesCount(long cpDefinitionId, int status) {
		if (status == WorkflowConstants.STATUS_ANY) {
			return cpInstancePersistence.countByCPDefinitionId(cpDefinitionId);
		}

		return cpInstancePersistence.countByC_ST(cpDefinitionId, status);
	}

	@Override
	public CPInstance getCPInstance(long cpDefinitionId, String sku)
		throws PortalException {

		return cpInstancePersistence.findByC_S(cpDefinitionId, sku);
	}

	@Override
	public CPInstance getCPInstanceByExternalReferenceCode(
			long companyId, String externalReferenceCode)
		throws PortalException {

		if (Validator.isBlank(externalReferenceCode)) {
			throw new NoSuchCPInstanceException();
		}

		return cpInstancePersistence.findByC_ERC(
			companyId, externalReferenceCode);
	}

	@Override
	public List<CPInstance> getCPInstances(
			long groupId, int status, int start, int end,
			OrderByComparator<CPInstance> orderByComparator)
		throws PortalException {

		if (status == WorkflowConstants.STATUS_ANY) {
			return cpInstancePersistence.findByGroupId(
				groupId, start, end, orderByComparator);
		}

		return cpInstancePersistence.findByG_ST(
			groupId, status, start, end, orderByComparator);
	}

	@Override
	public int getCPInstancesCount(long groupId, int status)
		throws PortalException {

		if (status == WorkflowConstants.STATUS_ANY) {
			return cpInstancePersistence.countByGroupId(groupId);
		}

		return cpInstancePersistence.countByG_ST(groupId, status);
	}

	@Override
	public CPInstance getCProductInstance(
			long cProductId, String cpInstanceUuid)
		throws PortalException {

		CProduct cProduct = cProductLocalService.getCProduct(cProductId);

		return cpInstancePersistence.findByC_C(
			cProduct.getPublishedCPDefinitionId(), cpInstanceUuid);
	}

	@Override
	public String[] getSKUs(long cpDefinitionId) {
		List<CPInstance> cpInstances = getCPDefinitionInstances(
			cpDefinitionId, WorkflowConstants.STATUS_APPROVED,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		String[] skusArray = new String[cpInstances.size()];

		List<String> skus = new ArrayList<>();

		for (CPInstance cpInstance : cpInstances) {
			skus.add(cpInstance.getSku());
		}

		skusArray = skus.toArray(skusArray);

		return skusArray;
	}

	@Override
	public Hits search(SearchContext searchContext) {
		try {
			Indexer<CPInstance> indexer =
				IndexerRegistryUtil.nullSafeGetIndexer(CPInstance.class);

			return indexer.search(searchContext);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@Override
	public BaseModelSearchResult<CPInstance> searchCPDefinitionInstances(
			long companyId, long cpDefinitionId, String keywords, int status,
			int start, int end, Sort sort)
		throws PortalException {

		SearchContext searchContext = buildSearchContext(
			companyId, cpDefinitionId, keywords, status, start, end, sort);

		return searchCPInstances(searchContext);
	}

	@Override
	public BaseModelSearchResult<CPInstance> searchCPInstances(
			long companyId, long[] groupIds, String keywords, int status,
			int start, int end, Sort sort)
		throws PortalException {

		SearchContext searchContext = buildSearchContext(
			companyId, groupIds, keywords, status, start, end, sort);

		return searchCPInstances(searchContext);
	}

	@Override
	public BaseModelSearchResult<CPInstance> searchCPInstances(
			long companyId, String keywords, int status, int start, int end,
			Sort sort)
		throws PortalException {

		SearchContext searchContext = buildSearchContext(
			companyId, keywords, status, start, end, sort);

		return searchCPInstances(searchContext);
	}

	@Override
	public BaseModelSearchResult<CPInstance> searchCPInstances(
			SearchContext searchContext)
		throws PortalException {

		Indexer<CPInstance> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			CPInstance.class);

		for (int i = 0; i < 10; i++) {
			Hits hits = indexer.search(searchContext, _SELECTED_FIELD_NAMES);

			List<CPInstance> cpInstances = getCPInstances(hits);

			if (cpInstances != null) {
				return new BaseModelSearchResult<>(
					cpInstances, hits.getLength());
			}
		}

		throw new SearchException(
			"Unable to fix the search index after 10 attempts");
	}

	@Override
	public CPInstance updateCPInstance(
			long cpInstanceId, String sku, String gtin,
			String manufacturerPartNumber, boolean purchasable,
			boolean published, int displayDateMonth, int displayDateDay,
			int displayDateYear, int displayDateHour, int displayDateMinute,
			int expirationDateMonth, int expirationDateDay,
			int expirationDateYear, int expirationDateHour,
			int expirationDateMinute, boolean neverExpire,
			ServiceContext serviceContext)
		throws PortalException {

		CPInstance cpInstance = cpInstancePersistence.findByPrimaryKey(
			cpInstanceId);

		return cpInstanceLocalService.updateCPInstance(
			cpInstanceId, sku, gtin, manufacturerPartNumber, purchasable,
			cpInstance.getWidth(), cpInstance.getHeight(),
			cpInstance.getDepth(), cpInstance.getWeight(),
			cpInstance.getPrice(), cpInstance.getPromoPrice(),
			cpInstance.getCost(), published, displayDateMonth, displayDateDay,
			displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, neverExpire,
			serviceContext);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPInstance updateCPInstance(
			long cpInstanceId, String sku, String gtin,
			String manufacturerPartNumber, boolean purchasable, double width,
			double height, double depth, double weight, BigDecimal price,
			BigDecimal promoPrice, BigDecimal cost, boolean published,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, ServiceContext serviceContext)
		throws PortalException {

		// Commerce product instance

		User user = userLocalService.getUser(serviceContext.getUserId());

		CPInstance cpInstance = cpInstancePersistence.findByPrimaryKey(
			cpInstanceId);

		validate(
			cpInstanceId, cpInstance.getCPDefinitionId(), cpInstance.getSku(),
			cpInstance.getJson(), cpInstance.getStatus(), serviceContext);

		if (cpDefinitionLocalService.isVersionable(
				cpInstance.getCPDefinitionId())) {

			CPDefinition newCPDefinition =
				cpDefinitionLocalService.copyCPDefinition(
					cpInstance.getCPDefinitionId());

			cpInstance = cpInstancePersistence.findByC_C(
				newCPDefinition.getCPDefinitionId(),
				cpInstance.getCPInstanceUuid());
		}

		Date displayDate = null;
		Date expirationDate = null;
		Date now = new Date();

		displayDate = PortalUtil.getDate(
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, user.getTimeZone(),
			CPInstanceDisplayDateException.class);

		if (!neverExpire) {
			expirationDate = PortalUtil.getDate(
				expirationDateMonth, expirationDateDay, expirationDateYear,
				expirationDateHour, expirationDateMinute, user.getTimeZone(),
				CPInstanceExpirationDateException.class);
		}

		cpInstance.setSku(sku);
		cpInstance.setGtin(gtin);
		cpInstance.setManufacturerPartNumber(manufacturerPartNumber);
		cpInstance.setPurchasable(purchasable);
		cpInstance.setWidth(width);
		cpInstance.setHeight(height);
		cpInstance.setDepth(depth);
		cpInstance.setWeight(weight);
		cpInstance.setPrice(price);
		cpInstance.setPromoPrice(promoPrice);
		cpInstance.setCost(cost);
		cpInstance.setPublished(published);
		cpInstance.setDisplayDate(displayDate);
		cpInstance.setExpirationDate(expirationDate);

		if ((expirationDate == null) || expirationDate.after(now)) {
			cpInstance.setStatus(WorkflowConstants.STATUS_DRAFT);
		}
		else {
			cpInstance.setStatus(WorkflowConstants.STATUS_EXPIRED);
		}

		cpInstance.setStatusByUserId(user.getUserId());
		cpInstance.setStatusDate(serviceContext.getModifiedDate(now));
		cpInstance.setExpandoBridgeAttributes(serviceContext);

		cpInstancePersistence.update(cpInstance);

		reindexCPDefinition(cpInstance.getCPDefinitionId());

		// Workflow

		return startWorkflowInstance(
			user.getUserId(), cpInstance, serviceContext);
	}

	@Override
	public CPInstance updatePricingInfo(
			long cpInstanceId, BigDecimal price, BigDecimal promoPrice,
			BigDecimal cost, ServiceContext serviceContext)
		throws PortalException {

		CPInstance cpInstance = cpInstancePersistence.findByPrimaryKey(
			cpInstanceId);

		if (cpDefinitionLocalService.isVersionable(
				cpInstance.getCPDefinitionId())) {

			CPDefinition newCPDefinition =
				cpDefinitionLocalService.copyCPDefinition(
					cpInstance.getCPDefinitionId());

			cpInstance = cpInstancePersistence.findByC_C(
				newCPDefinition.getCPDefinitionId(),
				cpInstance.getCPInstanceUuid());
		}

		Date now = new Date();

		cpInstance.setModifiedDate(serviceContext.getModifiedDate(now));

		cpInstance.setPrice(price);
		cpInstance.setPromoPrice(promoPrice);
		cpInstance.setCost(cost);

		return cpInstancePersistence.update(cpInstance);
	}

	@Override
	public CPInstance updateShippingInfo(
			long cpInstanceId, double width, double height, double depth,
			double weight, ServiceContext serviceContext)
		throws PortalException {

		CPInstance cpInstance = cpInstancePersistence.findByPrimaryKey(
			cpInstanceId);

		if (cpDefinitionLocalService.isVersionable(
				cpInstance.getCPDefinitionId())) {

			CPDefinition newCPDefinition =
				cpDefinitionLocalService.copyCPDefinition(
					cpInstance.getCPDefinitionId());

			cpInstance = cpInstancePersistence.findByC_C(
				newCPDefinition.getCPDefinitionId(),
				cpInstance.getCPInstanceUuid());
		}

		Date now = new Date();

		cpInstance.setModifiedDate(serviceContext.getModifiedDate(now));

		cpInstance.setWidth(width);
		cpInstance.setHeight(height);
		cpInstance.setDepth(depth);
		cpInstance.setWeight(weight);

		return cpInstancePersistence.update(cpInstance);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPInstance updateStatus(
			long userId, long cpInstanceId, int status,
			ServiceContext serviceContext,
			Map<String, Serializable> workflowContext)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		CPInstance cpInstance = cpInstancePersistence.findByPrimaryKey(
			cpInstanceId);

		CPDefinition cpDefinition = cpInstance.getCPDefinition();

		if (!cpDefinition.isIgnoreSKUCombinations() &&
			Validator.isNull(cpInstance.getJson())) {

			status = WorkflowConstants.STATUS_INACTIVE;
		}

		if ((status == WorkflowConstants.STATUS_APPROVED) &&
			(cpInstance.getDisplayDate() != null) &&
			now.before(cpInstance.getDisplayDate())) {

			status = WorkflowConstants.STATUS_SCHEDULED;
		}

		Date modifiedDate = serviceContext.getModifiedDate(now);

		cpInstance.setModifiedDate(modifiedDate);

		if (status == WorkflowConstants.STATUS_APPROVED) {
			Date expirationDate = cpInstance.getExpirationDate();

			if ((expirationDate != null) && expirationDate.before(now)) {
				cpInstance.setExpirationDate(null);
			}
		}

		if (status == WorkflowConstants.STATUS_EXPIRED) {
			cpInstance.setExpirationDate(now);
		}

		cpInstance.setStatus(status);
		cpInstance.setStatusByUserId(user.getUserId());
		cpInstance.setStatusByUserName(user.getFullName());
		cpInstance.setStatusDate(modifiedDate);

		cpInstancePersistence.update(cpInstance);

		return cpInstance;
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPInstance updateSubscriptionInfo(
			long cpInstanceId, boolean overrideSubscriptionInfo,
			boolean subscriptionEnabled, int subscriptionLength,
			String subscriptionType,
			UnicodeProperties subscriptionTypeSettingsProperties,
			long maxSubscriptionCycles, ServiceContext serviceContext)
		throws PortalException {

		CPInstance cpInstance = cpInstancePersistence.findByPrimaryKey(
			cpInstanceId);

		if (cpDefinitionLocalService.isVersionable(
				cpInstance.getCPDefinitionId())) {

			CPDefinition newCPDefinition =
				cpDefinitionLocalService.copyCPDefinition(
					cpInstance.getCPDefinitionId());

			cpInstance = cpInstancePersistence.findByC_C(
				newCPDefinition.getCPDefinitionId(),
				cpInstance.getCPInstanceUuid());
		}

		Date now = new Date();

		cpInstance.setModifiedDate(serviceContext.getModifiedDate(now));

		cpInstance.setOverrideSubscriptionInfo(overrideSubscriptionInfo);
		cpInstance.setSubscriptionEnabled(subscriptionEnabled);
		cpInstance.setSubscriptionLength(subscriptionLength);
		cpInstance.setSubscriptionType(subscriptionType);
		cpInstance.setSubscriptionTypeSettingsProperties(
			subscriptionTypeSettingsProperties);
		cpInstance.setMaxSubscriptionCycles(maxSubscriptionCycles);

		return cpInstancePersistence.update(cpInstance);
	}

	@Override
	public CPInstance upsertCPInstance(
			long cpDefinitionId, long groupId, String sku, String gtin,
			String manufacturerPartNumber, boolean purchasable, String json,
			double width, double height, double depth, double weight,
			BigDecimal price, BigDecimal promoPrice, BigDecimal cost,
			boolean published, String externalReferenceCode,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, ServiceContext serviceContext)
		throws PortalException {

		if (Validator.isBlank(externalReferenceCode)) {
			externalReferenceCode = null;
		}
		else {
			CPInstance cpInstance = cpInstancePersistence.fetchByC_ERC(
				serviceContext.getCompanyId(), externalReferenceCode);

			if (cpInstance != null) {
				return updateCPInstance(
					cpInstance.getCPInstanceId(), sku, gtin,
					manufacturerPartNumber, purchasable, width, height, depth,
					weight, price, promoPrice, cost, published,
					displayDateMonth, displayDateDay, displayDateYear,
					displayDateHour, displayDateMinute, expirationDateMonth,
					expirationDateDay, expirationDateYear, expirationDateHour,
					expirationDateMinute, neverExpire, serviceContext);
			}
		}

		return addCPInstance(
			cpDefinitionId, groupId, sku, gtin, manufacturerPartNumber,
			purchasable, json, width, height, depth, weight, price, promoPrice,
			cost, published, externalReferenceCode, displayDateMonth,
			displayDateDay, displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, neverExpire,
			serviceContext);
	}

	protected CPInstance addCPInstance(
			long cpDefinitionId, long groupId, String sku, String gtin,
			String manufacturerPartNumber, boolean purchasable, String json,
			double width, double height, double depth, double weight,
			BigDecimal price, BigDecimal promoPrice, BigDecimal cost,
			boolean published, Date displayDate, Date expirationDate,
			boolean neverExpire, ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(serviceContext.getUserId());

		Calendar displayDateCalendar = CalendarFactoryUtil.getCalendar(
			displayDate.getTime(), user.getTimeZone());

		int displayDateMonth = displayDateCalendar.get(Calendar.MONTH);
		int displayDateDay = displayDateCalendar.get(Calendar.DAY_OF_MONTH);
		int displayDateYear = displayDateCalendar.get(Calendar.YEAR);
		int displayDateHour = displayDateCalendar.get(Calendar.HOUR);
		int displayDateMinute = displayDateCalendar.get(Calendar.MINUTE);

		int expirationDateMonth = 0;
		int expirationDateDay = 0;
		int expirationDateYear = 0;
		int expirationDateHour = 0;
		int expirationDateMinute = 0;

		if (!neverExpire) {
			Calendar expirationDateCalendar = CalendarFactoryUtil.getCalendar(
				expirationDate.getTime(), user.getTimeZone());

			expirationDateMonth = expirationDateCalendar.get(Calendar.MONTH);
			expirationDateDay = expirationDateCalendar.get(
				Calendar.DAY_OF_MONTH);
			expirationDateYear = expirationDateCalendar.get(Calendar.YEAR);
			expirationDateHour = expirationDateCalendar.get(Calendar.HOUR);
			expirationDateMinute = expirationDateCalendar.get(Calendar.MINUTE);
		}

		return cpInstanceLocalService.addCPInstance(
			cpDefinitionId, groupId, sku, gtin, manufacturerPartNumber,
			purchasable, json, width, height, depth, weight, price, promoPrice,
			cost, published, StringPool.BLANK, displayDateMonth, displayDateDay,
			displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, neverExpire,
			serviceContext);
	}

	protected SearchContext buildSearchContext(
		long companyId, long cpDefinitionId, String keywords, int status,
		int start, int end, Sort sort) {

		SearchContext searchContext = new SearchContext();

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("keywords", keywords);

		Map<String, Serializable> attributes = new HashMap<>();

		attributes.put(
			CPInstanceIndexer.FIELD_CP_DEFINITION_ID, cpDefinitionId);
		attributes.put(
			CPInstanceIndexer.FIELD_CP_DEFINITION_STATUS,
			WorkflowConstants.STATUS_ANY);
		attributes.put(Field.CONTENT, keywords);
		attributes.put(Field.STATUS, status);
		attributes.put("params", params);

		searchContext.setAttributes(attributes);

		searchContext.setCompanyId(companyId);
		searchContext.setStart(start);
		searchContext.setEnd(end);

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

	protected SearchContext buildSearchContext(
		long companyId, long[] groupIds, String keywords, int status, int start,
		int end, Sort sort) {

		SearchContext searchContext = new SearchContext();

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("keywords", keywords);

		Map<String, Serializable> attributes = new HashMap<>();

		attributes.put(Field.CONTENT, keywords);
		attributes.put(Field.STATUS, status);
		attributes.put("params", params);

		searchContext.setAttributes(attributes);

		searchContext.setCompanyId(companyId);
		searchContext.setGroupIds(groupIds);
		searchContext.setStart(start);
		searchContext.setEnd(end);

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

	protected SearchContext buildSearchContext(
		long companyId, String keywords, int status, int start, int end,
		Sort sort) {

		SearchContext searchContext = new SearchContext();

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("keywords", keywords);

		Map<String, Serializable> attributes = new HashMap<>();

		attributes.put(Field.CONTENT, keywords);
		attributes.put(Field.STATUS, status);
		attributes.put("params", params);

		searchContext.setAttributes(attributes);

		searchContext.setCompanyId(companyId);
		searchContext.setStart(start);
		searchContext.setEnd(end);

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

	protected void checkCPInstancesByExpirationDate() throws PortalException {
		List<CPInstance> cpInstances = cpInstanceFinder.findByExpirationDate(
			new Date(),
			new QueryDefinition<>(WorkflowConstants.STATUS_APPROVED));

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Expiring " + cpInstances.size() +
					" commerce product instances");
		}

		if ((cpInstances != null) && !cpInstances.isEmpty()) {
			for (CPInstance cpInstance : cpInstances) {
				long userId = PortalUtil.getValidUserId(
					cpInstance.getCompanyId(), cpInstance.getUserId());

				ServiceContext serviceContext = new ServiceContext();

				serviceContext.setCommand(Constants.UPDATE);
				serviceContext.setScopeGroupId(cpInstance.getGroupId());

				cpInstanceLocalService.updateStatus(
					userId, cpInstance.getCPInstanceId(),
					WorkflowConstants.STATUS_EXPIRED, serviceContext,
					new HashMap<String, Serializable>());
			}
		}
	}

	protected List<CPInstance> getCPInstances(Hits hits)
		throws PortalException {

		List<Document> documents = hits.toList();

		List<CPInstance> cpInstances = new ArrayList<>(documents.size());

		for (Document document : documents) {
			long cpInstanceId = GetterUtil.getLong(
				document.get(Field.ENTRY_CLASS_PK));

			CPInstance cpInstance = fetchCPInstance(cpInstanceId);

			if (cpInstance == null) {
				cpInstances = null;

				Indexer<CPInstance> indexer = IndexerRegistryUtil.getIndexer(
					CPInstance.class);

				long companyId = GetterUtil.getLong(
					document.get(Field.COMPANY_ID));

				indexer.delete(companyId, document.getUID());
			}
			else if (cpInstances != null) {
				cpInstances.add(cpInstance);
			}
		}

		return cpInstances;
	}

	protected void reindexCPDefinition(long cpDefinitionId)
		throws PortalException {

		Indexer<CPDefinition> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			CPDefinition.class);

		indexer.reindex(CPDefinition.class.getName(), cpDefinitionId);
	}

	protected CPInstance startWorkflowInstance(
			long userId, CPInstance cpInstance, ServiceContext serviceContext)
		throws PortalException {

		Map<String, Serializable> workflowContext = new HashMap<>();

		return WorkflowHandlerRegistryUtil.startWorkflowInstance(
			cpInstance.getCompanyId(), cpInstance.getGroupId(), userId,
			CPInstance.class.getName(), cpInstance.getCPInstanceId(),
			cpInstance, serviceContext, workflowContext);
	}

	protected void validate(
			long cpInstanceId, long cpDefinitionId, String sku, String json,
			int status, ServiceContext serviceContext)
		throws PortalException {

		CPInstance cpInstance = cpInstancePersistence.fetchByC_S(
			cpDefinitionId, sku);

		if (cpInstanceId > 0) {
			CPInstance oldCPInstance = cpInstanceLocalService.getCPInstance(
				cpInstanceId);

			if (!sku.equals(oldCPInstance.getSku()) && (cpInstance != null)) {
				throw new CPInstanceSkuException();
			}
		}
		else {
			if (cpInstance != null) {
				throw new CPInstanceSkuException();
			}
		}

		int workflowAction = serviceContext.getWorkflowAction();

		if (workflowAction == WorkflowConstants.ACTION_PUBLISH) {
			CPDefinition cpDefinition =
				cpDefinitionLocalService.getCPDefinition(cpDefinitionId);

			if (cpDefinition.isIgnoreSKUCombinations()) {
				List<CPInstance> cpInstances = cpInstancePersistence.findByC_ST(
					cpDefinitionId, WorkflowConstants.STATUS_APPROVED, 0, 2);

				for (CPInstance curCPInstance : cpInstances) {
					if (curCPInstance.getCPInstanceId() == cpInstanceId) {
						continue;
					}

					if (status == WorkflowConstants.STATUS_APPROVED) {
						updateStatus(
							serviceContext.getUserId(),
							curCPInstance.getCPInstanceId(),
							WorkflowConstants.STATUS_EXPIRED, serviceContext,
							new HashMap<String, Serializable>());

						serviceContext.setWorkflowAction(0);
					}
				}
			}
			else {
				List<CPInstance> cpInstances = cpInstancePersistence.findByC_ST(
					cpDefinitionId, WorkflowConstants.STATUS_APPROVED);

				for (CPInstance curCPInstance : cpInstances) {
					if (Validator.isNull(curCPInstance.getJson())) {
						updateStatus(
							serviceContext.getUserId(),
							curCPInstance.getCPInstanceId(),
							WorkflowConstants.STATUS_INACTIVE, serviceContext,
							new HashMap<String, Serializable>());

						serviceContext.setWorkflowAction(0);
					}

					if ((cpInstanceId <= 0) &&
						(status == WorkflowConstants.STATUS_APPROVED) &&
						DDMFormValuesUtil.equals(
							json, curCPInstance.getJson())) {

						updateStatus(
							serviceContext.getUserId(),
							curCPInstance.getCPInstanceId(),
							WorkflowConstants.STATUS_EXPIRED, serviceContext,
							new HashMap<String, Serializable>());

						serviceContext.setWorkflowAction(0);
					}
				}

				if ((cpInstanceId > 0) && Validator.isNull(json)) {
					updateStatus(
						serviceContext.getUserId(), cpInstanceId,
						WorkflowConstants.STATUS_INACTIVE, serviceContext,
						new HashMap<String, Serializable>());

					serviceContext.setWorkflowAction(0);
				}
			}
		}
	}

	private static final String[] _SELECTED_FIELD_NAMES = {
		Field.ENTRY_CLASS_PK, Field.COMPANY_ID, Field.GROUP_ID, Field.UID
	};

	private static final Log _log = LogFactoryUtil.getLog(
		CPInstanceLocalServiceImpl.class);

}