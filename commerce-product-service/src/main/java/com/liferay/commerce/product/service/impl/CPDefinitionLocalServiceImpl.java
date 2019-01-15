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

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLinkConstants;
import com.liferay.commerce.model.CPDefinitionInventory;
import com.liferay.commerce.product.configuration.CProductVersionConfiguration;
import com.liferay.commerce.product.exception.CPDefinitionDisplayDateException;
import com.liferay.commerce.product.exception.CPDefinitionExpirationDateException;
import com.liferay.commerce.product.exception.CPDefinitionIgnoreSKUCombinationsException;
import com.liferay.commerce.product.exception.CPDefinitionMetaDescriptionException;
import com.liferay.commerce.product.exception.CPDefinitionMetaKeywordsException;
import com.liferay.commerce.product.exception.CPDefinitionMetaTitleException;
import com.liferay.commerce.product.exception.CPDefinitionProductTypeNameException;
import com.liferay.commerce.product.model.CPAttachmentFileEntry;
import com.liferay.commerce.product.model.CPAttachmentFileEntryConstants;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPDefinitionLink;
import com.liferay.commerce.product.model.CPDefinitionLocalization;
import com.liferay.commerce.product.model.CPDefinitionOptionRel;
import com.liferay.commerce.product.model.CPDefinitionOptionValueRel;
import com.liferay.commerce.product.model.CPDefinitionSpecificationOptionValue;
import com.liferay.commerce.product.model.CPDisplayLayout;
import com.liferay.commerce.product.model.CPFriendlyURLEntry;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CPInstanceConstants;
import com.liferay.commerce.product.model.CProduct;
import com.liferay.commerce.product.model.impl.CPDefinitionImpl;
import com.liferay.commerce.product.service.base.CPDefinitionLocalServiceBaseImpl;
import com.liferay.commerce.product.type.CPType;
import com.liferay.commerce.product.type.CPTypeServicesTracker;
import com.liferay.commerce.product.type.grouped.model.CPDefinitionGroupedEntry;
import com.liferay.commerce.product.type.grouped.service.CPDefinitionGroupedEntryLocalServiceUtil;
import com.liferay.commerce.product.type.virtual.model.CPDefinitionVirtualSetting;
import com.liferay.commerce.product.type.virtual.service.CPDefinitionVirtualSettingLocalServiceUtil;
import com.liferay.commerce.product.util.CPVersionContributor;
import com.liferay.commerce.product.util.CPVersionContributorRegistryUtil;
import com.liferay.commerce.service.CPDefinitionInventoryLocalServiceUtil;
import com.liferay.dynamic.data.mapping.exception.NoSuchStructureException;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryDefinition;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil;
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
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.MultiValueFacet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.settings.SystemSettingsLocator;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.trash.kernel.exception.RestoreEntryException;
import com.liferay.trash.kernel.exception.TrashEntryException;
import com.liferay.trash.kernel.model.TrashEntry;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class CPDefinitionLocalServiceImpl
	extends CPDefinitionLocalServiceBaseImpl {

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPDefinition addCPDefinition(
			Map<Locale, String> nameMap,
			Map<Locale, String> shortDescriptionMap,
			Map<Locale, String> descriptionMap, Map<Locale, String> urlTitleMap,
			Map<Locale, String> metaTitleMap,
			Map<Locale, String> metaDescriptionMap,
			Map<Locale, String> metaKeywordsMap, String productTypeName,
			boolean ignoreSKUCombinations, boolean shippable,
			boolean freeShipping, boolean shipSeparately,
			double shippingExtraPrice, double width, double height,
			double depth, double weight, long cpTaxCategoryId,
			boolean taxExempt, boolean telcoOrElectronics,
			String ddmStructureKey, boolean published, int displayDateMonth,
			int displayDateDay, int displayDateYear, int displayDateHour,
			int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, boolean hasDefaultInstance,
			ServiceContext serviceContext)
		throws PortalException {

		String defaultSku = null;

		if (hasDefaultInstance) {
			defaultSku = CPInstanceConstants.DEFAULT_SKU;
		}

		return cpDefinitionLocalService.addCPDefinition(
			nameMap, shortDescriptionMap, descriptionMap, urlTitleMap,
			metaTitleMap, metaDescriptionMap, metaKeywordsMap, productTypeName,
			ignoreSKUCombinations, shippable, freeShipping, shipSeparately,
			shippingExtraPrice, width, height, depth, weight, cpTaxCategoryId,
			taxExempt, telcoOrElectronics, ddmStructureKey, published,
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute,
			neverExpire, defaultSku, StringPool.BLANK, serviceContext);
	}

	@Override
	public CPDefinition addCPDefinition(
			Map<Locale, String> nameMap,
			Map<Locale, String> shortDescriptionMap,
			Map<Locale, String> descriptionMap, Map<Locale, String> urlTitleMap,
			Map<Locale, String> metaTitleMap,
			Map<Locale, String> metaDescriptionMap,
			Map<Locale, String> metaKeywordsMap, String productTypeName,
			boolean ignoreSKUCombinations, boolean shippable,
			boolean freeShipping, boolean shipSeparately,
			double shippingExtraPrice, double width, double height,
			double depth, double weight, long cpTaxCategoryId,
			boolean taxExempt, boolean telcoOrElectronics,
			String ddmStructureKey, boolean published, int displayDateMonth,
			int displayDateDay, int displayDateYear, int displayDateHour,
			int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, ServiceContext serviceContext)
		throws PortalException {

		return cpDefinitionLocalService.addCPDefinition(
			nameMap, shortDescriptionMap, descriptionMap, urlTitleMap,
			metaTitleMap, metaDescriptionMap, metaKeywordsMap, productTypeName,
			ignoreSKUCombinations, shippable, freeShipping, shipSeparately,
			shippingExtraPrice, width, height, depth, weight, cpTaxCategoryId,
			taxExempt, telcoOrElectronics, ddmStructureKey, published,
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute,
			neverExpire, CPInstanceConstants.DEFAULT_SKU, serviceContext);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPDefinition addCPDefinition(
			Map<Locale, String> nameMap,
			Map<Locale, String> shortDescriptionMap,
			Map<Locale, String> descriptionMap, Map<Locale, String> urlTitleMap,
			Map<Locale, String> metaTitleMap,
			Map<Locale, String> metaDescriptionMap,
			Map<Locale, String> metaKeywordsMap, String productTypeName,
			boolean ignoreSKUCombinations, boolean shippable,
			boolean freeShipping, boolean shipSeparately,
			double shippingExtraPrice, double width, double height,
			double depth, double weight, long cpTaxCategoryId,
			boolean taxExempt, boolean telcoOrElectronics,
			String ddmStructureKey, boolean published, int displayDateMonth,
			int displayDateDay, int displayDateYear, int displayDateHour,
			int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, String defaultSku, boolean subscriptionEnabled,
			int subscriptionLength, String subscriptionType,
			UnicodeProperties subscriptionTypeSettingsProperties,
			long maxSubscriptionCycles, String externalReferenceCode,
			ServiceContext serviceContext)
		throws PortalException {

		// Commerce product definition

		User user = userLocalService.getUser(serviceContext.getUserId());
		long groupId = serviceContext.getScopeGroupId();

		Date displayDate = null;
		Date expirationDate = null;
		Date now = new Date();

		displayDate = PortalUtil.getDate(
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, user.getTimeZone(),
			CPDefinitionDisplayDateException.class);

		if (!neverExpire) {
			expirationDate = PortalUtil.getDate(
				expirationDateMonth, expirationDateDay, expirationDateYear,
				expirationDateHour, expirationDateMinute, user.getTimeZone(),
				CPDefinitionExpirationDateException.class);
		}

		validate(
			groupId, ddmStructureKey, metaTitleMap, metaDescriptionMap,
			metaKeywordsMap, productTypeName);

		long cpDefinitionId = counterLocalService.increment();

		CPDefinition cpDefinition = cpDefinitionPersistence.create(
			cpDefinitionId);

		ServiceContext cProductServiceContext = new ServiceContext();

		cProductServiceContext.setScopeGroupId(
			serviceContext.getScopeGroupId());
		cProductServiceContext.setUserId(serviceContext.getUserId());

		CProduct cProduct = cProductLocalService.addCProduct(
			cProductServiceContext);

		cpDefinition.setUuid(serviceContext.getUuid());
		cpDefinition.setGroupId(groupId);
		cpDefinition.setCompanyId(user.getCompanyId());
		cpDefinition.setUserId(user.getUserId());
		cpDefinition.setUserName(user.getFullName());
		cpDefinition.setCProductId(cProduct.getCProductId());
		cpDefinition.setProductTypeName(productTypeName);
		cpDefinition.setIgnoreSKUCombinations(ignoreSKUCombinations);
		cpDefinition.setShippable(shippable);
		cpDefinition.setFreeShipping(freeShipping);
		cpDefinition.setShipSeparately(shipSeparately);
		cpDefinition.setShippingExtraPrice(shippingExtraPrice);
		cpDefinition.setWidth(width);
		cpDefinition.setHeight(height);
		cpDefinition.setDepth(depth);
		cpDefinition.setWeight(weight);
		cpDefinition.setCPTaxCategoryId(cpTaxCategoryId);
		cpDefinition.setTaxExempt(taxExempt);
		cpDefinition.setTelcoOrElectronics(telcoOrElectronics);
		cpDefinition.setDDMStructureKey(ddmStructureKey);
		cpDefinition.setPublished(published);
		cpDefinition.setExternalReferenceCode(externalReferenceCode);

		Locale locale = LocaleUtil.getSiteDefault();

		cpDefinition.setDefaultLanguageId(LocaleUtil.toLanguageId(locale));

		cpDefinition.setDisplayDate(displayDate);
		cpDefinition.setExpirationDate(expirationDate);
		cpDefinition.setSubscriptionEnabled(subscriptionEnabled);
		cpDefinition.setSubscriptionLength(subscriptionLength);
		cpDefinition.setSubscriptionType(subscriptionType);
		cpDefinition.setSubscriptionTypeSettingsProperties(
			subscriptionTypeSettingsProperties);
		cpDefinition.setMaxSubscriptionCycles(maxSubscriptionCycles);
		cpDefinition.setVersion(1);

		if ((expirationDate == null) || expirationDate.after(now)) {
			cpDefinition.setStatus(WorkflowConstants.STATUS_DRAFT);
		}
		else {
			cpDefinition.setStatus(WorkflowConstants.STATUS_EXPIRED);
		}

		cpDefinition.setStatusByUserId(user.getUserId());
		cpDefinition.setStatusDate(serviceContext.getModifiedDate(now));
		cpDefinition.setExpandoBridgeAttributes(serviceContext);

		cpDefinitionPersistence.update(cpDefinition);

		// Commerce product definition localization

		_addCPDefinitionLocalizedFields(
			user.getCompanyId(), cpDefinitionId, nameMap, shortDescriptionMap,
			descriptionMap, metaTitleMap, metaDescriptionMap, metaKeywordsMap);

		// Commerce product instance

		if (Validator.isNotNull(defaultSku)) {
			ServiceContext cpInstanceServiceContext = new ServiceContext();

			cpInstanceServiceContext.setScopeGroupId(
				serviceContext.getScopeGroupId());
			cpInstanceServiceContext.setUserId(serviceContext.getUserId());

			cpInstanceLocalService.addCPInstance(
				cpDefinitionId, defaultSku, null, null, true, null, true,
				displayDateMonth, displayDateDay, displayDateYear,
				displayDateHour, displayDateMinute, expirationDateMonth,
				expirationDateDay, expirationDateYear, expirationDateHour,
				expirationDateMinute, neverExpire, cpInstanceServiceContext);
		}

		// Commerce product friendly URL

		if (MapUtil.isEmpty(urlTitleMap)) {
			urlTitleMap = _getUniqueUrlTitles(cpDefinition, nameMap);
		}
		else {
			urlTitleMap = _getUniqueUrlTitles(cpDefinition, urlTitleMap);
		}

		cpFriendlyURLEntryLocalService.addCPFriendlyURLEntries(
			groupId, serviceContext.getCompanyId(), CPDefinition.class,
			cpDefinitionId, urlTitleMap);

		// Asset

		updateAsset(
			user.getUserId(), cpDefinition,
			serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames(),
			serviceContext.getAssetLinkEntryIds(),
			serviceContext.getAssetPriority());

		// Workflow

		return startWorkflowInstance(
			user.getUserId(), cpDefinition, serviceContext);
	}

	@Override
	public CPDefinition addCPDefinition(
			Map<Locale, String> nameMap,
			Map<Locale, String> shortDescriptionMap,
			Map<Locale, String> descriptionMap, Map<Locale, String> urlTitleMap,
			Map<Locale, String> metaTitleMap,
			Map<Locale, String> metaDescriptionMap,
			Map<Locale, String> metaKeywordsMap, String productTypeName,
			boolean ignoreSKUCombinations, boolean shippable,
			boolean freeShipping, boolean shipSeparately,
			double shippingExtraPrice, double width, double height,
			double depth, double weight, long cpTaxCategoryId,
			boolean taxExempt, boolean telcoOrElectronics,
			String ddmStructureKey, boolean published, int displayDateMonth,
			int displayDateDay, int displayDateYear, int displayDateHour,
			int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, String defaultSku,
			ServiceContext serviceContext)
		throws PortalException {

		return cpDefinitionLocalService.addCPDefinition(
			nameMap, shortDescriptionMap, descriptionMap, urlTitleMap,
			metaTitleMap, metaDescriptionMap, metaKeywordsMap, productTypeName,
			ignoreSKUCombinations, shippable, freeShipping, shipSeparately,
			shippingExtraPrice, width, height, depth, weight, cpTaxCategoryId,
			taxExempt, telcoOrElectronics, ddmStructureKey, published,
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute,
			neverExpire, defaultSku, StringPool.BLANK, serviceContext);
	}

	@Override
	public CPDefinition addCPDefinition(
			Map<Locale, String> nameMap,
			Map<Locale, String> shortDescriptionMap,
			Map<Locale, String> descriptionMap, Map<Locale, String> urlTitleMap,
			Map<Locale, String> metaTitleMap,
			Map<Locale, String> metaDescriptionMap,
			Map<Locale, String> metaKeywordsMap, String productTypeName,
			boolean ignoreSKUCombinations, boolean shippable,
			boolean freeShipping, boolean shipSeparately,
			double shippingExtraPrice, double width, double height,
			double depth, double weight, long cpTaxCategoryId,
			boolean taxExempt, boolean telcoOrElectronics,
			String ddmStructureKey, boolean published, int displayDateMonth,
			int displayDateDay, int displayDateYear, int displayDateHour,
			int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, String defaultSku,
			String externalReferenceCode, ServiceContext serviceContext)
		throws PortalException {

		return cpDefinitionLocalService.addCPDefinition(
			nameMap, shortDescriptionMap, descriptionMap, urlTitleMap,
			metaTitleMap, metaDescriptionMap, metaKeywordsMap, productTypeName,
			ignoreSKUCombinations, shippable, freeShipping, shipSeparately,
			shippingExtraPrice, width, height, depth, weight, cpTaxCategoryId,
			taxExempt, telcoOrElectronics, ddmStructureKey, published,
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute,
			neverExpire, defaultSku, false, 1, StringPool.BLANK, null, 0,
			externalReferenceCode, serviceContext);
	}

	@Override
	public CPDefinition addCPDefinition(
			Map<Locale, String> nameMap,
			Map<Locale, String> shortDescriptionMap,
			Map<Locale, String> descriptionMap, Map<Locale, String> urlTitleMap,
			Map<Locale, String> metaTitleMap,
			Map<Locale, String> metaDescriptionMap,
			Map<Locale, String> metaKeywordsMap, String productTypeName,
			boolean ignoreSKUCombinations, String ddmStructureKey,
			boolean published, int displayDateMonth, int displayDateDay,
			int displayDateYear, int displayDateHour, int displayDateMinute,
			int expirationDateMonth, int expirationDateDay,
			int expirationDateYear, int expirationDateHour,
			int expirationDateMinute, boolean neverExpire,
			ServiceContext serviceContext)
		throws PortalException {

		return cpDefinitionLocalService.addCPDefinition(
			nameMap, shortDescriptionMap, descriptionMap, urlTitleMap,
			metaTitleMap, metaDescriptionMap, metaKeywordsMap, productTypeName,
			ignoreSKUCombinations, true, false, false, 0, 0, 0, 0, 0, 0, false,
			false, ddmStructureKey, published, displayDateMonth, displayDateDay,
			displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, neverExpire,
			serviceContext);
	}

	@Override
	public void checkCPDefinitions() throws PortalException {
		checkCPDefinitionsByDisplayDate();
		checkCPDefinitionsByExpirationDate();
	}

	@Override
	public CPDefinition copyCPDefinition(long cpDefinitionId)
		throws PortalException {

		// CPDefinition

		CPDefinition originalCPDefinition =
			cpDefinitionLocalService.getCPDefinition(cpDefinitionId);

		CPDefinition newCPDefinition =
			(CPDefinition)originalCPDefinition.clone();

		if (originalCPDefinition.isPublished()) {
			originalCPDefinition.setPublished(false);

			cpDefinitionPersistence.update(originalCPDefinition);
		}

		newCPDefinition.setUuid(PortalUUIDUtil.generate());

		long newCPDefinitionId = counterLocalService.increment();

		newCPDefinition.setCPDefinitionId(newCPDefinitionId);

		newCPDefinition.setModifiedDate(new Date());

		newCPDefinition.setVersion(
			cProductLocalService.increment(
				originalCPDefinition.getCProductId()));

		cpDefinitionPersistence.update(newCPDefinition);

		// AssetEntry

		long cpDefinitionClassNameId = classNameLocalService.getClassNameId(
			CPDefinition.class);

		AssetEntry assetEntry = assetEntryLocalService.fetchEntry(
			cpDefinitionClassNameId, cpDefinitionId);

		if (assetEntry != null) {
			AssetEntry newAssetEntry = (AssetEntry)assetEntry.clone();

			newAssetEntry.setEntryId(counterLocalService.increment());
			newAssetEntry.setModifiedDate(new Date());
			newAssetEntry.setClassPK(newCPDefinitionId);

			assetEntryLocalService.updateAssetEntry(newAssetEntry);
		}

		// CPDefinitionLocalization

		List<CPDefinitionLocalization> cpDefinitionLocalizations =
			cpDefinitionLocalizationPersistence.findByCPDefinitionId(
				cpDefinitionId);

		for (CPDefinitionLocalization cpDefinitionLocalization :
				cpDefinitionLocalizations) {

			CPDefinitionLocalization newCPDefinitionLocalization =
				(CPDefinitionLocalization)cpDefinitionLocalization.clone();

			newCPDefinitionLocalization.setCpDefinitionLocalizationId(
				counterLocalService.increment());
			newCPDefinitionLocalization.setCPDefinitionId(newCPDefinitionId);

			cpDefinitionLocalizationPersistence.update(
				newCPDefinitionLocalization);
		}

		// CPAttachmentFileEntry

		List<CPAttachmentFileEntry> cpAttachmentFileEntries =
			cpAttachmentFileEntryPersistence.findByC_C(
				cpDefinitionClassNameId, cpDefinitionId);

		for (CPAttachmentFileEntry cpAttachmentFileEntry :
				cpAttachmentFileEntries) {

			CPAttachmentFileEntry newCPAttachmentFileEntry =
				(CPAttachmentFileEntry)cpAttachmentFileEntry.clone();

			newCPAttachmentFileEntry.setUuid(PortalUUIDUtil.generate());
			newCPAttachmentFileEntry.setCPAttachmentFileEntryId(
				counterLocalService.increment());
			newCPAttachmentFileEntry.setModifiedDate(new Date());
			newCPAttachmentFileEntry.setClassPK(newCPDefinitionId);

			cpAttachmentFileEntryPersistence.update(newCPAttachmentFileEntry);
		}

		// CPDefinitionLink

		List<CPDefinitionLink> cpDefinitionLinks =
			cpDefinitionLinkPersistence.findByCPDefinitionId(cpDefinitionId);

		for (CPDefinitionLink cpDefinitionLink : cpDefinitionLinks) {
			CPDefinitionLink newCPDefinitionLink =
				(CPDefinitionLink)cpDefinitionLink.clone();

			newCPDefinitionLink.setUuid(PortalUUIDUtil.generate());
			newCPDefinitionLink.setCPDefinitionLinkId(
				counterLocalService.increment());
			newCPDefinitionLink.setModifiedDate(new Date());
			newCPDefinitionLink.setCPDefinitionId(newCPDefinitionId);

			cpDefinitionLinkPersistence.update(newCPDefinitionLink);
		}

		// CPDefinitionOptionRel

		List<CPDefinitionOptionRel> cpDefinitionOptionRels =
			cpDefinitionOptionRelPersistence.findByCPDefinitionId(
				cpDefinitionId);

		for (CPDefinitionOptionRel cpDefinitionOptionRel :
				cpDefinitionOptionRels) {

			CPDefinitionOptionRel newCPDefinitionOptionRel =
				(CPDefinitionOptionRel)cpDefinitionOptionRel.clone();

			newCPDefinitionOptionRel.setUuid(PortalUUIDUtil.generate());

			long newCPDefinitionOptionRelId = counterLocalService.increment();

			newCPDefinitionOptionRel.setCPDefinitionOptionRelId(
				newCPDefinitionOptionRelId);

			newCPDefinitionOptionRel.setModifiedDate(new Date());
			newCPDefinitionOptionRel.setCPDefinitionId(newCPDefinitionId);

			cpDefinitionOptionRelPersistence.update(newCPDefinitionOptionRel);

			// CPDefinitionOptionValueRel

			List<CPDefinitionOptionValueRel> cpDefinitionOptionValueRels =
				cpDefinitionOptionValueRelPersistence.
					findByCPDefinitionOptionRelId(
						cpDefinitionOptionRel.getCPDefinitionOptionRelId());

			for (CPDefinitionOptionValueRel cpDefinitionOptionValueRel :
					cpDefinitionOptionValueRels) {

				CPDefinitionOptionValueRel newCPDefinitionOptionValueRel =
					(CPDefinitionOptionValueRel)cpDefinitionOptionValueRel.
						clone();

				newCPDefinitionOptionValueRel.setUuid(
					PortalUUIDUtil.generate());
				newCPDefinitionOptionValueRel.setCPDefinitionOptionValueRelId(
					counterLocalService.increment());
				newCPDefinitionOptionValueRel.setModifiedDate(new Date());
				newCPDefinitionOptionValueRel.setCPDefinitionOptionRelId(
					newCPDefinitionOptionRelId);

				cpDefinitionOptionValueRelPersistence.update(
					newCPDefinitionOptionValueRel);
			}
		}

		// CPDefinitionSpecificationOptionValue

		List<CPDefinitionSpecificationOptionValue>
			cpDefinitionSpecificationOptionValues =
				cpDefinitionSpecificationOptionValuePersistence.
					findByCPDefinitionId(cpDefinitionId);

		for (CPDefinitionSpecificationOptionValue
				cpDefinitionSpecificationOptionValue :
					cpDefinitionSpecificationOptionValues) {

			CPDefinitionSpecificationOptionValue
				newCPDefinitionSpecificationOptionValue =
					(CPDefinitionSpecificationOptionValue)
						cpDefinitionSpecificationOptionValue.clone();

			newCPDefinitionSpecificationOptionValue.setUuid(
				PortalUUIDUtil.generate());
			newCPDefinitionSpecificationOptionValue.
				setCPDefinitionSpecificationOptionValueId(
					counterLocalService.increment());
			newCPDefinitionSpecificationOptionValue.setModifiedDate(new Date());
			newCPDefinitionSpecificationOptionValue.setCPDefinitionId(
				newCPDefinitionId);

			cpDefinitionSpecificationOptionValuePersistence.update(
				newCPDefinitionSpecificationOptionValue);
		}

		// CPDisplayLayout

		CPDisplayLayout cpDisplayLayout = cpDisplayLayoutPersistence.fetchByC_C(
			cpDefinitionClassNameId, cpDefinitionId);

		if (cpDisplayLayout != null) {
			CPDisplayLayout newCPDisplayLayout =
				(CPDisplayLayout)cpDisplayLayout.clone();

			newCPDisplayLayout.setUuid(PortalUUIDUtil.generate());
			newCPDisplayLayout.setCPDisplayLayoutId(
				counterLocalService.increment());
			newCPDisplayLayout.setModifiedDate(new Date());
			newCPDisplayLayout.setClassPK(newCPDefinitionId);

			cpDisplayLayoutPersistence.update(newCPDisplayLayout);
		}

		// CPFriendlyURLEntry

		List<CPFriendlyURLEntry> cpFriendlyURLEntries =
			cpFriendlyURLEntryPersistence.findByC_C(
				cpDefinitionClassNameId, cpDefinitionId);

		for (CPFriendlyURLEntry cpFriendlyURLEntry : cpFriendlyURLEntries) {
			CPFriendlyURLEntry newCPFriendlyURLEntry =
				(CPFriendlyURLEntry)cpFriendlyURLEntry.clone();

			newCPFriendlyURLEntry.setUuid(PortalUUIDUtil.generate());
			newCPFriendlyURLEntry.setCPFriendlyURLEntryId(
				counterLocalService.increment());
			newCPFriendlyURLEntry.setModifiedDate(new Date());
			newCPFriendlyURLEntry.setClassPK(newCPDefinitionId);

			cpFriendlyURLEntryPersistence.update(newCPFriendlyURLEntry);
		}

		// CPInstance

		List<CPInstance> cpInstances =
			cpInstancePersistence.findByCPDefinitionId(cpDefinitionId);

		for (CPInstance cpInstance : cpInstances) {
			CPInstance newCPInstance = (CPInstance)cpInstance.clone();

			newCPInstance.setUuid(PortalUUIDUtil.generate());
			newCPInstance.setCPInstanceId(counterLocalService.increment());
			newCPInstance.setModifiedDate(new Date());
			newCPInstance.setCPDefinitionId(newCPDefinitionId);

			cpInstancePersistence.update(newCPInstance);
		}

		// CPVersionContributors

		List<CPVersionContributor> cpVersionContributors =
			CPVersionContributorRegistryUtil.getCPVersionContributors();

		for (CPVersionContributor cpVersionContributor :
				cpVersionContributors) {

			cpVersionContributor.onUpdate(cpDefinitionId, newCPDefinitionId);
		}

		// CProduct

		if (cpDefinitionLocalService.isVersionable(originalCPDefinition)) {
			cProductLocalService.updatePublishedCPDefinitionId(
				newCPDefinition.getCProductId(),
				newCPDefinition.getCPDefinitionId());
		}

		return newCPDefinition;
	}

	@Override
	public void deleteAssetCategoryCPDefinition(
			long cpDefinitionId, long categoryId, ServiceContext serviceContext)
		throws PortalException {

		AssetEntry assetEntry = assetEntryLocalService.getEntry(
			CPDefinition.class.getName(), cpDefinitionId);

		long[] categoryIds = ArrayUtil.remove(
			assetEntry.getCategoryIds(), categoryId);

		serviceContext.setAssetCategoryIds(categoryIds);

		serviceContext.setAssetTagNames(assetEntry.getTagNames());

		cpDefinitionLocalService.updateCPDefinitionCategorization(
			cpDefinitionId, serviceContext);
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public CPDefinition deleteCPDefinition(CPDefinition cpDefinition)
		throws PortalException {

		// Commerce product definition localization

		cpDefinitionLocalizationPersistence.removeByCPDefinitionId(
			cpDefinition.getCPDefinitionId());

		// Commerce product definition specification option values

		cpDefinitionSpecificationOptionValueLocalService.
			deleteCPDefinitionSpecificationOptionValues(
				cpDefinition.getCPDefinitionId());

		// Commerce product instances

		cpInstanceLocalService.deleteCPInstances(
			cpDefinition.getCPDefinitionId());

		// Commerce product definition option rels

		cpDefinitionOptionRelLocalService.deleteCPDefinitionOptionRels(
			cpDefinition.getCPDefinitionId());

		// Commerce product definition attachment file entries

		cpAttachmentFileEntryLocalService.deleteCPAttachmentFileEntries(
			CPDefinition.class.getName(), cpDefinition.getCPDefinitionId());

		// Commerce product definition links

		cpDefinitionLinkLocalService.deleteCPDefinitionLinks(
			cpDefinition.getCPDefinitionId());

		// Commerce product type

		CPType cpType = _cpTypeServicesTracker.getCPType(
			cpDefinition.getProductTypeName());

		if (cpType != null) {
			cpType.deleteCPDefinition(cpDefinition.getCPDefinitionId());
		}

		// Commerce product friendly URL entries

		cpFriendlyURLEntryLocalService.deleteCPFriendlyURLEntries(
			cpDefinition.getGroupId(), CPDefinition.class,
			cpDefinition.getCPDefinitionId());

		// Commerce product display layout

		cpDisplayLayoutLocalService.deleteCPDisplayLayout(
			CPDefinition.class, cpDefinition.getCPDefinitionId());

		// CPVersionContributors

		List<CPVersionContributor> cpVersionContributors =
			CPVersionContributorRegistryUtil.getCPVersionContributors();

		for (CPVersionContributor cpVersionContributor :
				cpVersionContributors) {

			cpVersionContributor.onDelete(cpDefinition.getCPDefinitionId());
		}

		// Commerce product definition

		cpDefinitionPersistence.remove(cpDefinition);

		// Asset

		assetEntryLocalService.deleteEntry(
			CPDefinition.class.getName(), cpDefinition.getCPDefinitionId());

		// Expando

		expandoRowLocalService.deleteRows(cpDefinition.getCPDefinitionId());

		// Trash

		trashEntryLocalService.deleteEntry(
			CPDefinition.class.getName(), cpDefinition.getCPDefinitionId());

		// Workflow

		workflowInstanceLinkLocalService.deleteWorkflowInstanceLinks(
			cpDefinition.getCompanyId(), cpDefinition.getGroupId(),
			CPDefinition.class.getName(), cpDefinition.getCPDefinitionId());

		return cpDefinition;
	}

	@Override
	public CPDefinition deleteCPDefinition(long cpDefinitionId)
		throws PortalException {

		CPDefinition cpDefinition = cpDefinitionPersistence.findByPrimaryKey(
			cpDefinitionId);

		return cpDefinitionLocalService.deleteCPDefinition(cpDefinition);
	}

	@Override
	public void deleteCPDefinitions(long groupId) throws PortalException {
		List<CPDefinition> cpDefinitions =
			cpDefinitionPersistence.findByGroupId(groupId);

		for (CPDefinition cpDefinition : cpDefinitions) {
			cpDefinitionLocalService.deleteCPDefinition(cpDefinition);
		}
	}

	@Override
	public CPDefinition fetchByExternalReferenceCode(
		long companyId, String externalReferenceCode) {

		return cpDefinitionPersistence.fetchByC_ERC(
			companyId, externalReferenceCode);
	}

	@Override
	public Map<Locale, String> getCPDefinitionDescriptionMap(
		long cpDefinitionId) {

		Map<Locale, String> cpDefinitionLocalizationDescriptionMap =
			new HashMap<>();

		List<CPDefinitionLocalization> cpDefinitionLocalizationList =
			cpDefinitionLocalizationPersistence.findByCPDefinitionId(
				cpDefinitionId);

		for (CPDefinitionLocalization cpDefinitionLocalization :
				cpDefinitionLocalizationList) {

			cpDefinitionLocalizationDescriptionMap.put(
				LocaleUtil.fromLanguageId(
					cpDefinitionLocalization.getLanguageId()),
				cpDefinitionLocalization.getDescription());
		}

		return cpDefinitionLocalizationDescriptionMap;
	}

	@Override
	public List<String> getCPDefinitionLocalizationLanguageIds(
		long cpDefinitionId) {

		List<CPDefinitionLocalization> cpDefinitionLocalizationList =
			cpDefinitionLocalizationPersistence.findByCPDefinitionId(
				cpDefinitionId);

		List<String> availableLanguageIds = new ArrayList<>();

		for (CPDefinitionLocalization cpDefinitionLocalization :
				cpDefinitionLocalizationList) {

			availableLanguageIds.add(cpDefinitionLocalization.getLanguageId());
		}

		return availableLanguageIds;
	}

	@Override
	public Map<Locale, String> getCPDefinitionMetaDescriptionMap(
		long cpDefinitionId) {

		Map<Locale, String> cpDefinitionLocalizationMetaDescriptionMap =
			new HashMap<>();

		List<CPDefinitionLocalization> cpDefinitionLocalizationList =
			cpDefinitionLocalizationPersistence.findByCPDefinitionId(
				cpDefinitionId);

		for (CPDefinitionLocalization cpDefinitionLocalization :
				cpDefinitionLocalizationList) {

			cpDefinitionLocalizationMetaDescriptionMap.put(
				LocaleUtil.fromLanguageId(
					cpDefinitionLocalization.getLanguageId()),
				cpDefinitionLocalization.getMetaDescription());
		}

		return cpDefinitionLocalizationMetaDescriptionMap;
	}

	@Override
	public Map<Locale, String> getCPDefinitionMetaKeywordsMap(
		long cpDefinitionId) {

		Map<Locale, String> cpDefinitionLocalizationMetaKeywordsMap =
			new HashMap<>();

		List<CPDefinitionLocalization> cpDefinitionLocalizationList =
			cpDefinitionLocalizationPersistence.findByCPDefinitionId(
				cpDefinitionId);

		for (CPDefinitionLocalization cpDefinitionLocalization :
				cpDefinitionLocalizationList) {

			cpDefinitionLocalizationMetaKeywordsMap.put(
				LocaleUtil.fromLanguageId(
					cpDefinitionLocalization.getLanguageId()),
				cpDefinitionLocalization.getMetaKeywords());
		}

		return cpDefinitionLocalizationMetaKeywordsMap;
	}

	@Override
	public Map<Locale, String> getCPDefinitionMetaTitleMap(
		long cpDefinitionId) {

		Map<Locale, String> cpDefinitionLocalizationMetaTitleMap =
			new HashMap<>();

		List<CPDefinitionLocalization> cpDefinitionLocalizationList =
			cpDefinitionLocalizationPersistence.findByCPDefinitionId(
				cpDefinitionId);

		for (CPDefinitionLocalization cpDefinitionLocalization :
				cpDefinitionLocalizationList) {

			cpDefinitionLocalizationMetaTitleMap.put(
				LocaleUtil.fromLanguageId(
					cpDefinitionLocalization.getLanguageId()),
				cpDefinitionLocalization.getMetaTitle());
		}

		return cpDefinitionLocalizationMetaTitleMap;
	}

	@Override
	public Map<Locale, String> getCPDefinitionNameMap(long cpDefinitionId) {
		Map<Locale, String> cpDefinitionLocalizationNameMap = new HashMap<>();

		List<CPDefinitionLocalization> cpDefinitionLocalizationList =
			cpDefinitionLocalizationPersistence.findByCPDefinitionId(
				cpDefinitionId);

		for (CPDefinitionLocalization cpDefinitionLocalization :
				cpDefinitionLocalizationList) {

			cpDefinitionLocalizationNameMap.put(
				LocaleUtil.fromLanguageId(
					cpDefinitionLocalization.getLanguageId()),
				cpDefinitionLocalization.getName());
		}

		return cpDefinitionLocalizationNameMap;
	}

	@Override
	public List<CPDefinition> getCPDefinitions(
		long groupId, int status, int start, int end) {

		if (status == WorkflowConstants.STATUS_ANY) {
			return cpDefinitionPersistence.findByG_NotS(
				groupId, WorkflowConstants.STATUS_IN_TRASH, start, end);
		}

		return cpDefinitionPersistence.findByG_S(groupId, status, start, end);
	}

	@Override
	public List<CPDefinition> getCPDefinitions(
		long groupId, int status, int start, int end,
		OrderByComparator<CPDefinition> orderByComparator) {

		if (status == WorkflowConstants.STATUS_ANY) {
			return cpDefinitionPersistence.findByG_NotS(
				groupId, WorkflowConstants.STATUS_IN_TRASH, start, end,
				orderByComparator);
		}

		return cpDefinitionPersistence.findByG_S(
			groupId, status, start, end, orderByComparator);
	}

	@Override
	public List<CPDefinition> getCPDefinitions(
		long groupId, String productTypeName, String languageId, int status,
		int start, int end, OrderByComparator<CPDefinition> orderByComparator) {

		QueryDefinition<CPDefinition> queryDefinition = new QueryDefinition<>(
			status, start, end, orderByComparator);

		if (status == WorkflowConstants.STATUS_ANY) {
			queryDefinition.setStatus(WorkflowConstants.STATUS_IN_TRASH, true);
		}

		return cpDefinitionFinder.findByG_P_S(
			groupId, productTypeName, languageId, queryDefinition);
	}

	@Override
	public List<CPDefinition> getCPDefinitionsByCategoryId(
			long categoryId, int start, int end)
		throws PortalException {

		AssetCategory assetCategory =
			assetCategoryLocalService.getAssetCategory(categoryId);

		SearchContext searchContext = buildSearchContext(
			assetCategory.getCompanyId(), assetCategory.getGroupId(), null,
			WorkflowConstants.STATUS_ANY, start, end, null);

		searchContext.setAssetCategoryIds(new long[] {categoryId});

		BaseModelSearchResult<CPDefinition> cpDefinitionBaseModelSearchResult =
			searchCPDefinitions(searchContext);

		return cpDefinitionBaseModelSearchResult.getBaseModels();
	}

	@Override
	public int getCPDefinitionsCount(long groupId, int status) {
		if (status == WorkflowConstants.STATUS_ANY) {
			return cpDefinitionPersistence.countByG_NotS(
				groupId, WorkflowConstants.STATUS_IN_TRASH);
		}

		return cpDefinitionPersistence.countByG_S(groupId, status);
	}

	@Override
	public int getCPDefinitionsCount(
		long groupId, String productTypeName, String languageId, int status) {

		QueryDefinition<CPDefinition> queryDefinition = new QueryDefinition<>(
			status);

		if (status == WorkflowConstants.STATUS_ANY) {
			queryDefinition.setStatus(WorkflowConstants.STATUS_IN_TRASH, true);
		}

		return cpDefinitionFinder.countByG_P_S(
			groupId, productTypeName, languageId, queryDefinition);
	}

	@Override
	public int getCPDefinitionsCountByCategoryId(long categoryId)
		throws PortalException {

		AssetCategory assetCategory =
			assetCategoryLocalService.getAssetCategory(categoryId);

		SearchContext searchContext = buildSearchContext(
			assetCategory.getCompanyId(), assetCategory.getGroupId(), null,
			WorkflowConstants.STATUS_ANY, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);

		searchContext.setAssetCategoryIds(new long[] {categoryId});

		BaseModelSearchResult<CPDefinition> cpDefinitionBaseModelSearchResult =
			searchCPDefinitions(searchContext);

		return cpDefinitionBaseModelSearchResult.getLength();
	}

	@Override
	public Map<Locale, String> getCPDefinitionShortDescriptionMap(
		long cpDefinitionId) {

		Map<Locale, String> cpDefinitionLocalizationShortDescriptionMap =
			new HashMap<>();

		List<CPDefinitionLocalization> cpDefinitionLocalizationList =
			cpDefinitionLocalizationPersistence.findByCPDefinitionId(
				cpDefinitionId);

		for (CPDefinitionLocalization cpDefinitionLocalization :
				cpDefinitionLocalizationList) {

			cpDefinitionLocalizationShortDescriptionMap.put(
				LocaleUtil.fromLanguageId(
					cpDefinitionLocalization.getLanguageId()),
				cpDefinitionLocalization.getShortDescription());
		}

		return cpDefinitionLocalizationShortDescriptionMap;
	}

	@Override
	public CPAttachmentFileEntry getDefaultImage(long cpDefinitionId)
		throws PortalException {

		long classNameId = classNameLocalService.getClassNameId(
			CPDefinition.class);

		List<CPAttachmentFileEntry> cpAttachmentFileEntries =
			cpAttachmentFileEntryLocalService.getCPAttachmentFileEntries(
				classNameId, cpDefinitionId,
				CPAttachmentFileEntryConstants.TYPE_IMAGE,
				WorkflowConstants.STATUS_APPROVED, 0, 1);

		if (cpAttachmentFileEntries.isEmpty()) {
			return null;
		}

		return cpAttachmentFileEntries.get(0);
	}

	@Override
	public List<Facet> getFacets(
		String filterFields, String filterValues, SearchContext searchContext) {

		List<Facet> facets = new ArrayList<>();

		if (Validator.isNotNull(filterFields) &&
			Validator.isNotNull(filterValues)) {

			Map<String, List<String>> facetMap = new HashMap<>();

			String[] filterFieldsArray = StringUtil.split(filterFields);
			String[] filterValuesArray = StringUtil.split(filterValues);

			List<String> options = new ArrayList<>();

			for (int i = 0; i < filterFieldsArray.length; i++) {
				String key = filterFieldsArray[i];
				String value = filterValuesArray[i];

				if (key.startsWith("OPTION_")) {
					key = key.replace("OPTION_", StringPool.BLANK);

					key = _getIndexFieldName(key);

					options.add(key);
				}

				List<String> facetValues = null;

				if (facetMap.containsKey(key)) {
					facetValues = facetMap.get(key);
				}

				if (facetValues == null) {
					facetValues = new ArrayList<>();
				}

				facetValues.add(value);

				facetMap.put(key, facetValues);
			}

			for (Map.Entry<String, List<String>> entry : facetMap.entrySet()) {
				String fieldName = entry.getKey();

				List<String> facetValues = entry.getValue();

				String[] facetValuesArray = ArrayUtil.toStringArray(
					facetValues);

				MultiValueFacet multiValueFacet = new MultiValueFacet(
					searchContext);

				multiValueFacet.setFieldName(fieldName);
				multiValueFacet.setStatic(true);
				multiValueFacet.setValues(facetValuesArray);

				searchContext.setAttribute(fieldName, facetValuesArray);

				if (fieldName.equals("assetCategoryIds")) {
					Stream<String> stream = Arrays.stream(facetValuesArray);

					LongStream longStream = stream.mapToLong(
						GetterUtil::getLong);

					long[] assetCategoryIds = longStream.toArray();

					searchContext.setAssetCategoryIds(assetCategoryIds);
				}

				facets.add(multiValueFacet);
			}
		}

		return facets;
	}

	@Override
	public String getLayoutUuid(long cpDefinitionId) {
		CPDisplayLayout cpDisplayLayout =
			cpDisplayLayoutLocalService.fetchCPDisplayLayout(
				CPDefinition.class, cpDefinitionId);

		if (cpDisplayLayout == null) {
			return null;
		}

		return cpDisplayLayout.getLayoutUuid();
	}

	@Override
	public Map<Locale, String> getUrlTitleMap(long cpDefinitionId) {
		CPDefinition cpDefinition = cpDefinitionPersistence.fetchByPrimaryKey(
			cpDefinitionId);

		if (cpDefinition == null) {
			return Collections.emptyMap();
		}

		long classNameId = classNameLocalService.getClassNameId(
			CPDefinition.class);

		return cpFriendlyURLEntryLocalService.getUrlTitleMap(
			cpDefinition.getGroupId(), classNameId,
			cpDefinition.getCPDefinitionId());
	}

	@Override
	public String getUrlTitleMapAsXML(long cpDefinitionId)
		throws PortalException {

		CPDefinition cpDefinition = cpDefinitionPersistence.findByPrimaryKey(
			cpDefinitionId);

		long classNameId = classNameLocalService.getClassNameId(
			CPDefinition.class);

		Locale defaultLocale = LocaleUtil.getSiteDefault();

		String defaultLanguageId = LanguageUtil.getLanguageId(defaultLocale);

		return cpFriendlyURLEntryLocalService.getUrlTitleMapAsXML(
			cpDefinition.getGroupId(), classNameId,
			cpDefinition.getCPDefinitionId(), defaultLanguageId);
	}

	@Override
	public boolean isPublishedCPDefinition(CPDefinition cpDefinition) {
		CProduct cProduct = cProductLocalService.fetchCProduct(
			cpDefinition.getCProductId());

		if ((cProduct != null) &&
			(cProduct.getPublishedCPDefinitionId() ==
				cpDefinition.getCPDefinitionId())) {

			return true;
		}

		return false;
	}

	@Override
	public boolean isPublishedCPDefinition(long cpDefinitionId) {
		CPDefinition cpDefinition = cpDefinitionLocalService.fetchCPDefinition(
			cpDefinitionId);

		if (cpDefinition == null) {
			return false;
		}

		return isPublishedCPDefinition(cpDefinition);
	}

	@Override
	public boolean isVersionable(CPDefinition cpDefinition) {
		if (!_isVersioningEnabled()) {
			return false;
		}

		return isPublishedCPDefinition(cpDefinition);
	}

	@Override
	public boolean isVersionable(long cpDefinitionId) {
		if (!_isVersioningEnabled()) {
			return false;
		}

		return isPublishedCPDefinition(cpDefinitionId);
	}

	@Override
	public boolean isVersionable(
		long cpDefinitionId, HttpServletRequest httpServletRequest) {

		if (httpServletRequest == null) {
			return isVersionable(cpDefinitionId);
		}

		boolean versionable = GetterUtil.getBoolean(
			httpServletRequest.getAttribute("versionable#" + cpDefinitionId),
			true);

		if (versionable) {
			httpServletRequest.setAttribute("versionable#" + cpDefinitionId,
				Boolean.FALSE);

			return isVersionable(cpDefinitionId);
		}

		return false;
	}

	@Override
	public void moveCPDefinitionsToTrash(long groupId, long userId)
		throws PortalException {

		List<CPDefinition> cpDefinitions =
			cpDefinitionPersistence.findByGroupId(groupId);

		for (CPDefinition cpDefinition : cpDefinitions) {
			cpDefinitionLocalService.moveCPDefinitionToTrash(
				userId, cpDefinition);
		}
	}

	/**
	 * Moves the commerce product definition to the recycle bin.
	 *
	 * @param  userId the primary key of the user moving the commerce product
	 *         definition
	 * @param  cpDefinition the commerce product definition to be moved
	 * @return the moved commerce product definition
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPDefinition moveCPDefinitionToTrash(
			long userId, CPDefinition cpDefinition)
		throws PortalException {

		// Commerce product definition

		if (cpDefinition.isInTrash()) {
			throw new TrashEntryException();
		}

		int oldStatus = cpDefinition.getStatus();

		if (oldStatus == WorkflowConstants.STATUS_PENDING) {
			cpDefinition.setStatus(WorkflowConstants.STATUS_DRAFT);

			cpDefinitionPersistence.update(cpDefinition);
		}

		cpDefinition = updateStatus(
			userId, cpDefinition.getCPDefinitionId(),
			WorkflowConstants.STATUS_IN_TRASH, new ServiceContext(),
			new HashMap<>());

		// Workflow

		if (oldStatus == WorkflowConstants.STATUS_PENDING) {
			workflowInstanceLinkLocalService.deleteWorkflowInstanceLink(
				cpDefinition.getCompanyId(), cpDefinition.getGroupId(),
				CPDefinition.class.getName(), cpDefinition.getCPDefinitionId());
		}

		return cpDefinition;
	}

	/**
	 * Moves the commerce product definition with the ID to the recycle bin.
	 *
	 * @param  userId the primary key of the user moving the commerce product
	 *         definition
	 * @param  cpDefinitionId the primary key of the commerce product definition
	 *         to be moved
	 * @return the moved commerce product definition
	 */
	@Override
	public CPDefinition moveCPDefinitionToTrash(
			long userId, long cpDefinitionId)
		throws PortalException {

		CPDefinition cpDefinition = cpDefinitionPersistence.findByPrimaryKey(
			cpDefinitionId);

		return cpDefinitionLocalService.moveCPDefinitionToTrash(
			userId, cpDefinition);
	}

	/**
	 * Restores the commerce product definition with the ID from the recycle
	 * bin.
	 *
	 * @param  userId the primary key of the user restoring the commerce product
	 *         definition
	 * @param  cpDefinitionId the primary key of the commerce product definition
	 *         to be restored
	 * @return the restored commerce product definition from the recycle bin
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPDefinition restoreCPDefinitionFromTrash(
			long userId, long cpDefinitionId)
		throws PortalException {

		CPDefinition cpDefinition = cpDefinitionPersistence.findByPrimaryKey(
			cpDefinitionId);

		if (!cpDefinition.isInTrash()) {
			throw new RestoreEntryException(
				RestoreEntryException.INVALID_STATUS);
		}

		TrashEntry trashEntry = trashEntryLocalService.getEntry(
			CPDefinition.class.getName(), cpDefinitionId);

		cpDefinition = updateStatus(
			userId, cpDefinitionId, trashEntry.getStatus(),
			new ServiceContext(), new HashMap<String, Serializable>());

		return cpDefinition;
	}

	@Override
	public BaseModelSearchResult<CPDefinition> searchCPDefinitions(
			long companyId, long groupId, String keywords, int status,
			int start, int end, Sort sort)
		throws PortalException {

		SearchContext searchContext = buildSearchContext(
			companyId, groupId, keywords, status, start, end, sort);

		return searchCPDefinitions(searchContext);
	}

	@Override
	public BaseModelSearchResult<CPDefinition> searchCPDefinitions(
			long companyId, long groupId, String keywords, String filterFields,
			String filterValues, int start, int end, Sort sort)
		throws PortalException {

		SearchContext searchContext = buildSearchContext(
			companyId, groupId, keywords, WorkflowConstants.STATUS_ANY, start,
			end, sort);

		List<Facet> facets = getFacets(
			filterFields, filterValues, searchContext);

		searchContext.setFacets(facets);

		return searchCPDefinitions(searchContext);
	}

	@Override
	public void updateAsset(
			long userId, CPDefinition cpDefinition, long[] assetCategoryIds,
			String[] assetTagNames, long[] assetLinkEntryIds, Double priority)
		throws PortalException {

		AssetEntry assetEntry = assetEntryLocalService.updateEntry(
			userId, cpDefinition.getGroupId(), cpDefinition.getCreateDate(),
			cpDefinition.getModifiedDate(), CPDefinition.class.getName(),
			cpDefinition.getCPDefinitionId(), cpDefinition.getUuid(), 0,
			assetCategoryIds, assetTagNames, true, true, null, null,
			cpDefinition.getCreateDate(), null, ContentTypes.TEXT_PLAIN,
			cpDefinition.getNameMapAsXML(),
			cpDefinition.getDescriptionMapAsXML(), null, null, null, 0, 0,
			priority);

		assetLinkLocalService.updateLinks(
			userId, assetEntry.getEntryId(), assetLinkEntryIds,
			AssetLinkConstants.TYPE_RELATED);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPDefinition updateCPDefinition(
			long cpDefinitionId, Map<Locale, String> nameMap,
			Map<Locale, String> shortDescriptionMap,
			Map<Locale, String> descriptionMap, Map<Locale, String> urlTitleMap,
			Map<Locale, String> metaTitleMap,
			Map<Locale, String> metaDescriptionMap,
			Map<Locale, String> metaKeywordsMap, boolean ignoreSKUCombinations,
			boolean shippable, boolean freeShipping, boolean shipSeparately,
			double shippingExtraPrice, double width, double height,
			double depth, double weight, long cpTaxCategoryId,
			boolean taxExempt, boolean telcoOrElectronics,
			String ddmStructureKey, boolean published, int displayDateMonth,
			int displayDateDay, int displayDateYear, int displayDateHour,
			int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, ServiceContext serviceContext)
		throws PortalException {

		// Commerce product definition

		User user = userLocalService.getUser(serviceContext.getUserId());
		long groupId = serviceContext.getScopeGroupId();
		CPDefinition cpDefinition = cpDefinitionPersistence.findByPrimaryKey(
			cpDefinitionId);

		Date displayDate = null;
		Date expirationDate = null;
		Date now = new Date();

		displayDate = PortalUtil.getDate(
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, user.getTimeZone(),
			CPDefinitionDisplayDateException.class);

		if (!neverExpire) {
			expirationDate = PortalUtil.getDate(
				expirationDateMonth, expirationDateDay, expirationDateYear,
				expirationDateHour, expirationDateMinute, user.getTimeZone(),
				CPDefinitionExpirationDateException.class);
		}

		validate(
			groupId, ddmStructureKey, metaTitleMap, metaDescriptionMap,
			metaKeywordsMap, cpDefinition.getProductTypeName());

		if (cpDefinitionLocalService.isVersionable(cpDefinition)) {
			cpDefinition = cpDefinitionLocalService.copyCPDefinition(
				cpDefinitionId);

			cpDefinitionId = cpDefinition.getCPDefinitionId();
		}

		cpDefinition.setIgnoreSKUCombinations(ignoreSKUCombinations);
		cpDefinition.setShippable(shippable);
		cpDefinition.setFreeShipping(freeShipping);
		cpDefinition.setShipSeparately(shipSeparately);
		cpDefinition.setShippingExtraPrice(shippingExtraPrice);
		cpDefinition.setWidth(width);
		cpDefinition.setHeight(height);
		cpDefinition.setDepth(depth);
		cpDefinition.setWeight(weight);
		cpDefinition.setCPTaxCategoryId(cpTaxCategoryId);
		cpDefinition.setTaxExempt(taxExempt);
		cpDefinition.setTelcoOrElectronics(telcoOrElectronics);
		cpDefinition.setDDMStructureKey(ddmStructureKey);
		cpDefinition.setPublished(published);
		cpDefinition.setDisplayDate(displayDate);
		cpDefinition.setExpirationDate(expirationDate);

		if ((expirationDate == null) || expirationDate.after(now)) {
			cpDefinition.setStatus(WorkflowConstants.STATUS_DRAFT);
		}
		else {
			cpDefinition.setStatus(WorkflowConstants.STATUS_EXPIRED);
		}

		cpDefinition.setStatusByUserId(user.getUserId());
		cpDefinition.setStatusDate(serviceContext.getModifiedDate(now));
		cpDefinition.setExpandoBridgeAttributes(serviceContext);

		cpDefinitionPersistence.update(cpDefinition);

		if (MapUtil.isEmpty(urlTitleMap)) {
			urlTitleMap = _getUniqueUrlTitles(cpDefinition, nameMap);
		}
		else {
			urlTitleMap = _getUniqueUrlTitles(cpDefinition, urlTitleMap);
		}

		// Commerce product definition localization

		_updateCPDefinitionLocalizedFields(
			cpDefinition.getCompanyId(), cpDefinition.getCPDefinitionId(),
			nameMap, shortDescriptionMap, descriptionMap, metaTitleMap,
			metaDescriptionMap, metaKeywordsMap);

		// Commerce product friendly URL entries

		cpFriendlyURLEntryLocalService.addCPFriendlyURLEntries(
			groupId, serviceContext.getCompanyId(), CPDefinition.class,
			cpDefinitionId, urlTitleMap);

		// Asset

		updateAsset(
			user.getUserId(), cpDefinition,
			serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames(),
			serviceContext.getAssetLinkEntryIds(),
			serviceContext.getAssetPriority());

		// Workflow

		return startWorkflowInstance(
			user.getUserId(), cpDefinition, serviceContext);
	}

	@Override
	public CPDefinition updateCPDefinition(
			long cpDefinitionId, Map<Locale, String> nameMap,
			Map<Locale, String> shortDescriptionMap,
			Map<Locale, String> descriptionMap, Map<Locale, String> urlTitleMap,
			Map<Locale, String> metaTitleMap,
			Map<Locale, String> metaDescriptionMap,
			Map<Locale, String> metaKeywordsMap, boolean ignoreSKUCombinations,
			String ddmStructureKey, boolean published, int displayDateMonth,
			int displayDateDay, int displayDateYear, int displayDateHour,
			int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, ServiceContext serviceContext)
		throws PortalException {

		CPDefinition cpDefinition = cpDefinitionPersistence.findByPrimaryKey(
			cpDefinitionId);

		return cpDefinitionLocalService.updateCPDefinition(
			cpDefinitionId, nameMap, shortDescriptionMap, descriptionMap,
			urlTitleMap, metaTitleMap, metaDescriptionMap, metaKeywordsMap,
			ignoreSKUCombinations, cpDefinition.isShippable(),
			cpDefinition.isFreeShipping(), cpDefinition.isShipSeparately(),
			cpDefinition.getShippingExtraPrice(), cpDefinition.getWidth(),
			cpDefinition.getHeight(), cpDefinition.getDepth(),
			cpDefinition.getWeight(), cpDefinition.getCPTaxCategoryId(),
			cpDefinition.isTaxExempt(), cpDefinition.isTelcoOrElectronics(),
			ddmStructureKey, published, displayDateMonth, displayDateDay,
			displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, neverExpire,
			serviceContext);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPDefinition updateCPDefinitionCategorization(
			long cpDefinitionId, ServiceContext serviceContext)
		throws PortalException {

		CPDefinition cpDefinition = cpDefinitionPersistence.findByPrimaryKey(
			cpDefinitionId);

		if (cpDefinitionLocalService.isVersionable(cpDefinition)) {
			cpDefinition = cpDefinitionLocalService.copyCPDefinition(
				cpDefinitionId);

			cpDefinitionId = cpDefinition.getCPDefinitionId();
		}

		updateStatus(
			serviceContext.getUserId(), cpDefinitionId,
			cpDefinition.getStatus(), serviceContext,
			new HashMap<String, Serializable>());

		// Asset

		assetEntryLocalService.updateEntry(
			serviceContext.getUserId(), cpDefinition.getGroupId(),
			CPDefinition.class.getName(), cpDefinition.getCPDefinitionId(),
			serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames());

		return cpDefinitionPersistence.update(cpDefinition);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPDefinition updateCPDefinitionIgnoreSKUCombinations(
			long cpDefinitionId, boolean ignoreSKUCombinations,
			ServiceContext serviceContext)
		throws PortalException {

		checkCPInstances(
			serviceContext.getUserId(), cpDefinitionId, ignoreSKUCombinations,
			serviceContext);

		CPDefinition cpDefinition = cpDefinitionPersistence.findByPrimaryKey(
			cpDefinitionId);

		cpDefinition.setIgnoreSKUCombinations(ignoreSKUCombinations);

		return cpDefinitionPersistence.update(cpDefinition);
	}

	@Override
	public void updateCPDefinitionsByCPTaxCategoryId(long cpTaxCategoryId)
		throws PortalException {

		List<CPDefinition> cpDefinitions =
			cpDefinitionPersistence.findByCPTaxCategoryId(cpTaxCategoryId);

		for (CPDefinition cpDefinition : cpDefinitions) {
			updateTaxCategoryInfo(
				cpDefinition.getCPDefinitionId(), 0, cpDefinition.isTaxExempt(),
				cpDefinition.isTelcoOrElectronics());
		}
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPDefinition updateShippingInfo(
			long cpDefinitionId, boolean shippable, boolean freeShipping,
			boolean shipSeparately, double shippingExtraPrice, double width,
			double height, double depth, double weight,
			ServiceContext serviceContext)
		throws PortalException {

		CPDefinition cpDefinition = cpDefinitionPersistence.findByPrimaryKey(
			cpDefinitionId);

		if (cpDefinitionLocalService.isVersionable(cpDefinition)) {
			cpDefinition = cpDefinitionLocalService.copyCPDefinition(
				cpDefinitionId);
		}

		Date now = new Date();

		cpDefinition.setModifiedDate(serviceContext.getModifiedDate(now));

		cpDefinition.setShippable(shippable);
		cpDefinition.setFreeShipping(freeShipping);
		cpDefinition.setShipSeparately(shipSeparately);
		cpDefinition.setShippingExtraPrice(shippingExtraPrice);
		cpDefinition.setWidth(width);
		cpDefinition.setHeight(height);
		cpDefinition.setDepth(depth);
		cpDefinition.setWeight(weight);

		return cpDefinitionPersistence.update(cpDefinition);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPDefinition updateStatus(
			long userId, long cpDefinitionId, int status,
			ServiceContext serviceContext,
			Map<String, Serializable> workflowContext)
		throws PortalException {

		// Commerce product definition

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		CPDefinition cpDefinition = cpDefinitionPersistence.findByPrimaryKey(
			cpDefinitionId);

		int oldStatus = cpDefinition.getStatus();

		if ((status == WorkflowConstants.STATUS_APPROVED) &&
			(cpDefinition.getDisplayDate() != null) &&
			now.before(cpDefinition.getDisplayDate())) {

			status = WorkflowConstants.STATUS_SCHEDULED;
		}

		Date modifiedDate = serviceContext.getModifiedDate(now);

		cpDefinition.setModifiedDate(modifiedDate);

		if (status == WorkflowConstants.STATUS_APPROVED) {
			Date expirationDate = cpDefinition.getExpirationDate();

			if ((expirationDate != null) && expirationDate.before(now)) {
				cpDefinition.setExpirationDate(null);
			}
		}

		if (status == WorkflowConstants.STATUS_EXPIRED) {
			cpDefinition.setExpirationDate(now);
		}

		cpDefinition.setStatus(status);
		cpDefinition.setStatusByUserId(user.getUserId());
		cpDefinition.setStatusByUserName(user.getFullName());
		cpDefinition.setStatusDate(modifiedDate);

		cpDefinitionPersistence.update(cpDefinition);

		if (status == WorkflowConstants.STATUS_APPROVED) {

			// Asset

			assetEntryLocalService.updateEntry(
				CPDefinition.class.getName(), cpDefinition.getCPDefinitionId(),
				cpDefinition.getDisplayDate(), cpDefinition.getExpirationDate(),
				true, true);

			//CProduct

			cProductLocalService.updatePublishedCPDefinitionId(
				cpDefinition.getCProductId(), cpDefinition.getCPDefinitionId());

			// Trash

			if (oldStatus == WorkflowConstants.STATUS_IN_TRASH) {
				trashEntryLocalService.deleteEntry(
					CPDefinition.class.getName(), cpDefinitionId);
			}
		}
		else {

			// Asset

			assetEntryLocalService.updateVisible(
				CPDefinition.class.getName(), cpDefinitionId, false);

			// Trash

			if (status == WorkflowConstants.STATUS_IN_TRASH) {
				trashEntryLocalService.addTrashEntry(
					userId, cpDefinition.getGroupId(),
					CPDefinition.class.getName(),
					cpDefinition.getCPDefinitionId(), cpDefinition.getUuid(),
					null, oldStatus, null, null);
			}
			else if (oldStatus == WorkflowConstants.STATUS_IN_TRASH) {
				trashEntryLocalService.deleteEntry(
					CPDefinition.class.getName(), cpDefinitionId);
			}
		}

		// Commerce product instances

		reindexCPInstances(cpDefinition);

		return cpDefinition;
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPDefinition updateSubscriptionInfo(
			long cpDefinitionId, boolean subscriptionEnabled,
			int subscriptionLength, String subscriptionType,
			UnicodeProperties subscriptionTypeSettingsProperties,
			long maxSubscriptionCycles, ServiceContext serviceContext)
		throws PortalException {

		CPDefinition cpDefinition = cpDefinitionPersistence.findByPrimaryKey(
			cpDefinitionId);

		if (cpDefinitionLocalService.isVersionable(cpDefinition)) {
			cpDefinition = cpDefinitionLocalService.copyCPDefinition(
				cpDefinitionId);
		}

		Date now = new Date();

		cpDefinition.setModifiedDate(serviceContext.getModifiedDate(now));

		cpDefinition.setSubscriptionEnabled(subscriptionEnabled);
		cpDefinition.setSubscriptionLength(subscriptionLength);
		cpDefinition.setSubscriptionType(subscriptionType);
		cpDefinition.setSubscriptionTypeSettingsProperties(
			subscriptionTypeSettingsProperties);
		cpDefinition.setMaxSubscriptionCycles(maxSubscriptionCycles);

		return cpDefinitionPersistence.update(cpDefinition);
	}

	@Override
	public CPDefinition updateTaxCategoryInfo(
			long cpDefinitionId, long cpTaxCategoryId, boolean taxExempt,
			boolean telcoOrElectronics)
		throws PortalException {

		CPDefinition cpDefinition = cpDefinitionPersistence.findByPrimaryKey(
			cpDefinitionId);

		if (cpDefinitionLocalService.isVersionable(cpDefinition)) {
			cpDefinition = cpDefinitionLocalService.copyCPDefinition(
				cpDefinitionId);
		}

		cpDefinition.setCPTaxCategoryId(cpTaxCategoryId);
		cpDefinition.setTaxExempt(taxExempt);
		cpDefinition.setTelcoOrElectronics(telcoOrElectronics);

		return cpDefinitionPersistence.update(cpDefinition);
	}

	@Override
	public CPDefinition upsertCPDefinition(
			Map<Locale, String> nameMap,
			Map<Locale, String> shortDescriptionMap,
			Map<Locale, String> descriptionMap, Map<Locale, String> urlTitleMap,
			Map<Locale, String> metaTitleMap,
			Map<Locale, String> metaDescriptionMap,
			Map<Locale, String> metaKeywordsMap, String productTypeName,
			boolean ignoreSKUCombinations, boolean shippable,
			boolean freeShipping, boolean shipSeparately,
			double shippingExtraPrice, double width, double height,
			double depth, double weight, long cpTaxCategoryId,
			boolean taxExempt, boolean telcoOrElectronics,
			String ddmStructureKey, boolean published, int displayDateMonth,
			int displayDateDay, int displayDateYear, int displayDateHour,
			int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, String defaultSKU,
			String externalReferenceCode, ServiceContext serviceContext)
		throws PortalException {

		CPDefinition cpDefinition = cpDefinitionPersistence.fetchByC_ERC(
			serviceContext.getCompanyId(), externalReferenceCode);

		if (cpDefinition == null) {
			cpDefinition = addCPDefinition(
				nameMap, shortDescriptionMap, descriptionMap, urlTitleMap,
				metaTitleMap, metaDescriptionMap, metaKeywordsMap,
				productTypeName, ignoreSKUCombinations, shippable, freeShipping,
				shipSeparately, shippingExtraPrice, width, height, depth,
				weight, cpTaxCategoryId, taxExempt, telcoOrElectronics,
				ddmStructureKey, published, displayDateMonth, displayDateDay,
				displayDateYear, displayDateHour, displayDateMinute,
				expirationDateMonth, expirationDateDay, expirationDateYear,
				expirationDateHour, expirationDateMinute, neverExpire,
				defaultSKU, externalReferenceCode, serviceContext);
		}
		else {
			cpDefinition = updateCPDefinition(
				cpDefinition.getCPDefinitionId(), nameMap, shortDescriptionMap,
				descriptionMap, urlTitleMap, metaTitleMap, metaDescriptionMap,
				metaKeywordsMap, ignoreSKUCombinations, shippable, freeShipping,
				shipSeparately, shippingExtraPrice, width, height, depth,
				weight, cpTaxCategoryId, taxExempt, telcoOrElectronics,
				ddmStructureKey, published, displayDateMonth, displayDateDay,
				displayDateYear, displayDateHour, displayDateMinute,
				expirationDateMonth, expirationDateDay, expirationDateYear,
				expirationDateHour, expirationDateMinute, neverExpire,
				serviceContext);
		}

		return cpDefinition;
	}

	protected SearchContext buildSearchContext(
		long companyId, long groupId, String keywords, int status, int start,
		int end, Sort sort) {

		SearchContext searchContext = new SearchContext();

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("keywords", keywords);

		Map<String, Serializable> attributes = new HashMap<>();

		attributes.put(Field.ENTRY_CLASS_PK, keywords);
		attributes.put(Field.NAME, keywords);
		attributes.put(Field.DESCRIPTION, keywords);
		attributes.put(Field.CONTENT, keywords);
		attributes.put(Field.STATUS, status);
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

	protected void checkCPDefinitionsByDisplayDate() throws PortalException {
		List<CPDefinition> cpDefinitions = cpDefinitionPersistence.findByLtD_S(
			new Date(), WorkflowConstants.STATUS_SCHEDULED);

		for (CPDefinition cpDefinition : cpDefinitions) {
			long userId = PortalUtil.getValidUserId(
				cpDefinition.getCompanyId(), cpDefinition.getUserId());

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCommand(Constants.UPDATE);
			serviceContext.setScopeGroupId(cpDefinition.getGroupId());

			cpDefinitionLocalService.updateStatus(
				userId, cpDefinition.getCPDefinitionId(),
				WorkflowConstants.STATUS_APPROVED, serviceContext,
				new HashMap<String, Serializable>());

			if (cpDefinition.isApproved()) {
				long classNameId = classNameLocalService.getClassNameId(
					cpDefinition.getModelClassName());

				cpAttachmentFileEntryLocalService.
					checkCPAttachmentFileEntriesByDisplayDate(
						classNameId, cpDefinition.getCPDefinitionId());

				cpInstanceLocalService.checkCPInstancesByDisplayDate(
					cpDefinition.getCPDefinitionId());
			}
		}
	}

	protected void checkCPDefinitionsByExpirationDate() throws PortalException {
		List<CPDefinition> cpDefinitions =
			cpDefinitionFinder.findByExpirationDate(
				new Date(),
				new QueryDefinition<>(WorkflowConstants.STATUS_APPROVED));

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Expiring " + cpDefinitions.size() +
					" commerce product definitions");
		}

		if ((cpDefinitions != null) && !cpDefinitions.isEmpty()) {
			for (CPDefinition cpDefinition : cpDefinitions) {
				long userId = PortalUtil.getValidUserId(
					cpDefinition.getCompanyId(), cpDefinition.getUserId());

				ServiceContext serviceContext = new ServiceContext();

				serviceContext.setCommand(Constants.UPDATE);
				serviceContext.setScopeGroupId(cpDefinition.getGroupId());

				cpDefinitionLocalService.updateStatus(
					userId, cpDefinition.getCPDefinitionId(),
					WorkflowConstants.STATUS_EXPIRED, serviceContext,
					new HashMap<String, Serializable>());
			}
		}
	}

	protected void checkCPInstances(
			long userId, long cpDefinitionId, boolean ignoreSKUCombinations,
			ServiceContext serviceContext)
		throws PortalException {

		if (ignoreSKUCombinations) {
			int cpInstancesCount =
				cpInstanceLocalService.getCPDefinitionInstancesCount(
					cpDefinitionId, WorkflowConstants.STATUS_APPROVED);

			if (cpInstancesCount > 1) {
				throw new CPDefinitionIgnoreSKUCombinationsException();
			}
		}
		else {
			int cpDefinitionOptionRelsCount =
				cpDefinitionOptionRelLocalService.
					getCPDefinitionOptionRelsCount(cpDefinitionId, true);

			if (cpDefinitionOptionRelsCount == 0) {
				return;
			}

			List<CPInstance> cpInstances =
				cpInstanceLocalService.getCPDefinitionInstances(
					cpDefinitionId, WorkflowConstants.STATUS_APPROVED,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

			for (CPInstance cpInstance : cpInstances) {
				if (Validator.isNull(cpInstance.getJson())) {
					cpInstanceLocalService.updateStatus(
						userId, cpInstance.getCPInstanceId(),
						WorkflowConstants.STATUS_INACTIVE, serviceContext,
						new HashMap<String, Serializable>());
				}
			}
		}
	}

	protected List<CPDefinition> getCPDefinitions(Hits hits)
		throws PortalException {

		List<Document> documents = hits.toList();

		List<CPDefinition> cpDefinitions = new ArrayList<>(documents.size());

		for (Document document : documents) {
			long cpDefinitionId = GetterUtil.getLong(
				document.get(Field.ENTRY_CLASS_PK));

			CPDefinition cpDefinition = fetchCPDefinition(cpDefinitionId);

			if (cpDefinition == null) {
				cpDefinitions = null;

				Indexer<CPDefinition> indexer = IndexerRegistryUtil.getIndexer(
					CPDefinition.class);

				long companyId = GetterUtil.getLong(
					document.get(Field.COMPANY_ID));

				indexer.delete(companyId, document.getUID());
			}
			else if (cpDefinitions != null) {
				cpDefinitions.add(cpDefinition);
			}
		}

		return cpDefinitions;
	}

	protected void reindexCPInstances(CPDefinition cpDefinition)
		throws PortalException {

		Indexer<CPInstance> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			CPInstance.class);

		indexer.reindex(cpDefinition.getCPInstances());
	}

	protected BaseModelSearchResult<CPDefinition> searchCPDefinitions(
			SearchContext searchContext)
		throws PortalException {

		Indexer<CPDefinition> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			CPDefinition.class);

		for (int i = 0; i < 10; i++) {
			Hits hits = indexer.search(searchContext, _SELECTED_FIELD_NAMES);

			List<CPDefinition> cpDefinitions = getCPDefinitions(hits);

			if (cpDefinitions != null) {
				return new BaseModelSearchResult<>(
					cpDefinitions, hits.getLength());
			}
		}

		throw new SearchException(
			"Unable to fix the search index after 10 attempts");
	}

	protected CPDefinition startWorkflowInstance(
			long userId, CPDefinition cpDefinition,
			ServiceContext serviceContext)
		throws PortalException {

		Map<String, Serializable> workflowContext = new HashMap<>();

		return WorkflowHandlerRegistryUtil.startWorkflowInstance(
			cpDefinition.getCompanyId(), cpDefinition.getGroupId(), userId,
			CPDefinition.class.getName(), cpDefinition.getCPDefinitionId(),
			cpDefinition, serviceContext, workflowContext);
	}

	protected void validate(
			long groupId, String ddmStructureKey,
			Map<Locale, String> metaTitleMap,
			Map<Locale, String> metaDescriptionMap,
			Map<Locale, String> metaKeywordsMap, String productTypeName)
		throws PortalException {

		if (Validator.isNotNull(ddmStructureKey)) {
			long classNameId = classNameLocalService.getClassNameId(
				CPDefinition.class.getName());

			DDMStructure ddmStructure =
				_ddmStructureLocalService.fetchStructure(
					groupId, classNameId, ddmStructureKey, true);

			if (ddmStructure == null) {
				throw new NoSuchStructureException();
			}
		}

		if (metaTitleMap != null) {
			for (Map.Entry<Locale, String> entry : metaTitleMap.entrySet()) {
				CPDefinitionMetaTitleException cpDefinitionMetaTitleException =
					CPDefinitionImpl.validateMetaTitle(entry.getValue());

				if (cpDefinitionMetaTitleException != null) {
					throw cpDefinitionMetaTitleException;
				}
			}
		}

		if (metaDescriptionMap != null) {
			for (Map.Entry<Locale, String> entry :
					metaDescriptionMap.entrySet()) {

				CPDefinitionMetaDescriptionException
					cpDefinitionMetaDescriptionException =
						CPDefinitionImpl.validateMetaDescription(
							entry.getValue());

				if (cpDefinitionMetaDescriptionException != null) {
					throw cpDefinitionMetaDescriptionException;
				}
			}
		}

		if (metaKeywordsMap != null) {
			for (Map.Entry<Locale, String> entry : metaKeywordsMap.entrySet()) {
				CPDefinitionMetaKeywordsException
					cpDefinitionMetaKeywordsException =
						CPDefinitionImpl.validateMetaKeyword(entry.getValue());

				if (cpDefinitionMetaKeywordsException != null) {
					throw cpDefinitionMetaKeywordsException;
				}
			}
		}

		CPType cpType = _cpTypeServicesTracker.getCPType(productTypeName);

		if (cpType == null) {
			throw new CPDefinitionProductTypeNameException();
		}
	}

	private List<CPDefinitionLocalization> _addCPDefinitionLocalizedFields(
			long companyId, long cpDefinitionId, Map<Locale, String> nameMap,
			Map<Locale, String> shortDescriptionMap,
			Map<Locale, String> descriptionMap,
			Map<Locale, String> metaTitleMap,
			Map<Locale, String> metaDescriptionMap,
			Map<Locale, String> metaKeywordsMap)
		throws PortalException {

		Set<Locale> localeSet = new HashSet<>();

		localeSet.addAll(nameMap.keySet());

		if (shortDescriptionMap != null) {
			localeSet.addAll(shortDescriptionMap.keySet());
		}

		if (descriptionMap != null) {
			localeSet.addAll(descriptionMap.keySet());
		}

		if (metaTitleMap != null) {
			localeSet.addAll(metaTitleMap.keySet());
		}

		if (metaDescriptionMap != null) {
			localeSet.addAll(metaDescriptionMap.keySet());
		}

		if (metaKeywordsMap != null) {
			localeSet.addAll(metaKeywordsMap.keySet());
		}

		List<CPDefinitionLocalization> cpDefinitionLocalizations =
			new ArrayList<>();

		for (Locale locale : localeSet) {
			String name = nameMap.get(locale);
			String shortDescription = null;
			String description = null;
			String metaTitle = null;
			String metaDescription = null;
			String metaKeywords = null;

			if (shortDescriptionMap != null) {
				shortDescription = shortDescriptionMap.get(locale);
			}

			if (descriptionMap != null) {
				description = descriptionMap.get(locale);
			}

			if (metaTitleMap != null) {
				metaTitle = metaTitleMap.get(locale);
			}

			if (metaDescriptionMap != null) {
				metaDescription = metaDescriptionMap.get(locale);
			}

			if (metaKeywordsMap != null) {
				metaKeywords = metaKeywordsMap.get(locale);
			}

			if (Validator.isNull(name) && Validator.isNull(shortDescription) &&
				Validator.isNull(description) && Validator.isNull(metaTitle) &&
				Validator.isNull(metaDescription) &&
				Validator.isNull(metaKeywords)) {

				continue;
			}

			CPDefinitionLocalization cpDefinitionLocalization =
				_addCPDefinitionLocalizedFields(
					companyId, cpDefinitionId, name, shortDescription,
					description, metaTitle, metaDescription, metaKeywords,
					LocaleUtil.toLanguageId(locale));

			cpDefinitionLocalizations.add(cpDefinitionLocalization);
		}

		return cpDefinitionLocalizations;
	}

	private CPDefinitionLocalization _addCPDefinitionLocalizedFields(
			long companyId, long cpDefinitionId, String name,
			String shortDescription, String description, String metaTitle,
			String metaDescription, String metaKeywords, String languageId)
		throws PortalException {

		CPDefinitionLocalization cpDefinitionLocalization =
			cpDefinitionLocalizationPersistence.
				fetchByCPDefinitionId_LanguageId(cpDefinitionId, languageId);

		if (cpDefinitionLocalization == null) {
			long cpDefinitionLocalizationId = counterLocalService.increment();

			cpDefinitionLocalization =
				cpDefinitionLocalizationPersistence.create(
					cpDefinitionLocalizationId);

			cpDefinitionLocalization.setCompanyId(companyId);
			cpDefinitionLocalization.setCPDefinitionId(cpDefinitionId);
			cpDefinitionLocalization.setName(name);
			cpDefinitionLocalization.setShortDescription(shortDescription);
			cpDefinitionLocalization.setDescription(description);
			cpDefinitionLocalization.setMetaTitle(metaTitle);
			cpDefinitionLocalization.setMetaDescription(metaDescription);
			cpDefinitionLocalization.setMetaKeywords(metaKeywords);
			cpDefinitionLocalization.setLanguageId(languageId);
		}
		else {
			cpDefinitionLocalization.setName(name);
			cpDefinitionLocalization.setShortDescription(shortDescription);
			cpDefinitionLocalization.setDescription(description);
			cpDefinitionLocalization.setMetaTitle(metaTitle);
			cpDefinitionLocalization.setMetaDescription(metaDescription);
			cpDefinitionLocalization.setMetaKeywords(metaKeywords);
		}

		return cpDefinitionLocalizationPersistence.update(
			cpDefinitionLocalization);
	}

	private String _getIndexFieldName(String optionKey) {
		return "ATTRIBUTE_" + optionKey + "_VALUES_NAMES";
	}

	private Map<Locale, String> _getUniqueUrlTitles(
			CPDefinition cpDefinition, Map<Locale, String> urlTitleMap)
		throws PortalException {

		Map<Locale, String> newUrlTitleMap = new HashMap<>();

		Map<Locale, String> titleMap = cpDefinition.getNameMap();

		long classNameId = classNameLocalService.getClassNameId(
			CPDefinition.class);

		for (Map.Entry<Locale, String> titleEntry : titleMap.entrySet()) {
			String urlTitle = urlTitleMap.get(titleEntry.getKey());

			if (Validator.isNull(urlTitle)) {
				urlTitle = titleEntry.getValue();
			}

			String languageId = LanguageUtil.getLanguageId(titleEntry.getKey());

			urlTitle = cpFriendlyURLEntryLocalService.buildUrlTitle(
				cpDefinition.getGroupId(), classNameId,
				cpDefinition.getCPDefinitionId(), languageId, urlTitle);

			newUrlTitleMap.put(titleEntry.getKey(), urlTitle);
		}

		return newUrlTitleMap;
	}

	private boolean _isVersioningEnabled() {
		try {
			CProductVersionConfiguration cProductVersionConfiguration =
				ConfigurationProviderUtil.getConfiguration(
					CProductVersionConfiguration.class,
					new SystemSettingsLocator(
						CProductVersionConfiguration.class.getName()));

			if (cProductVersionConfiguration.enabled()) {
				return true;
			}
		}
		catch (PortalException pe) {
			pe.printStackTrace();
		}

		return false;
	}

	private List<CPDefinitionLocalization> _updateCPDefinitionLocalizedFields(
			long companyId, long cpDefinitionId, Map<Locale, String> nameMap,
			Map<Locale, String> shortDescriptionMap,
			Map<Locale, String> descriptionMap,
			Map<Locale, String> metaTitleMap,
			Map<Locale, String> metaDescriptionMap,
			Map<Locale, String> metaKeywordsMap)
		throws PortalException {

		List<CPDefinitionLocalization> oldCPDefinitionLocalizations =
			new ArrayList<>(
				cpDefinitionLocalizationPersistence.findByCPDefinitionId(
					cpDefinitionId));

		List<CPDefinitionLocalization> newCPDefinitionLocalizations =
			_addCPDefinitionLocalizedFields(
				companyId, cpDefinitionId, nameMap, shortDescriptionMap,
				descriptionMap, metaTitleMap, metaDescriptionMap,
				metaKeywordsMap);

		oldCPDefinitionLocalizations.removeAll(newCPDefinitionLocalizations);

		for (CPDefinitionLocalization oldCPDefinitionLocalization :
				oldCPDefinitionLocalizations) {

			cpDefinitionLocalizationPersistence.remove(
				oldCPDefinitionLocalization);
		}

		return newCPDefinitionLocalizations;
	}

	private static final String[] _SELECTED_FIELD_NAMES =
		{Field.ENTRY_CLASS_PK, Field.COMPANY_ID, Field.GROUP_ID, Field.UID};

	private static final Log _log = LogFactoryUtil.getLog(
		CPDefinitionLocalServiceImpl.class);

	@ServiceReference(type = CPTypeServicesTracker.class)
	private CPTypeServicesTracker _cpTypeServicesTracker;

	@ServiceReference(type = DDMStructureLocalService.class)
	private DDMStructureLocalService _ddmStructureLocalService;

}