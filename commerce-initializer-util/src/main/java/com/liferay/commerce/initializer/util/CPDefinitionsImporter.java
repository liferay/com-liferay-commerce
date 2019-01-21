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

package com.liferay.commerce.initializer.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.MappingJsonFactory;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.commerce.constants.CPDefinitionInventoryConstants;
import com.liferay.commerce.model.CPDAvailabilityEstimate;
import com.liferay.commerce.model.CommerceAvailabilityEstimate;
import com.liferay.commerce.product.exception.NoSuchSkuContributorCPDefinitionOptionRelException;
import com.liferay.commerce.product.model.CPAttachmentFileEntryConstants;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPDefinitionOptionRel;
import com.liferay.commerce.product.model.CPDefinitionOptionValueRel;
import com.liferay.commerce.product.model.CPDefinitionSpecificationOptionValue;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CPOption;
import com.liferay.commerce.product.model.CPOptionCategory;
import com.liferay.commerce.product.model.CPOptionValue;
import com.liferay.commerce.product.model.CPSpecificationOption;
import com.liferay.commerce.product.model.CPTaxCategory;
import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.commerce.product.service.CPDefinitionOptionRelLocalService;
import com.liferay.commerce.product.service.CPDefinitionOptionValueRelLocalService;
import com.liferay.commerce.product.service.CPDefinitionSpecificationOptionValueLocalService;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.commerce.product.service.CPOptionCategoryLocalService;
import com.liferay.commerce.product.service.CPOptionLocalService;
import com.liferay.commerce.product.service.CPOptionValueLocalService;
import com.liferay.commerce.product.service.CPSpecificationOptionLocalService;
import com.liferay.commerce.product.service.CPTaxCategoryLocalService;
import com.liferay.commerce.service.CPDAvailabilityEstimateLocalService;
import com.liferay.commerce.service.CPDefinitionInventoryLocalService;
import com.liferay.commerce.service.CommerceAvailabilityEstimateLocalService;
import com.liferay.commerce.service.CommerceWarehouseItemLocalService;
import com.liferay.commerce.util.comparator.CommerceAvailabilityEstimatePriorityComparator;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.File;
import java.io.Serializable;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Andrea Di Giorgi
 * @author Alessio Antonio Rendina
 */
@Component(service = CPDefinitionsImporter.class)
public class CPDefinitionsImporter {

	public void importCPDefinitions(
			File cpDefinitionsFile, String assetVocabularyName,
			long[] commerceWarehouseIds, ClassLoader classLoader,
			String imageDependenciesPath, long scopeGroupId, long userId)
		throws Exception {

		ServiceContext serviceContext = getServiceContext(scopeGroupId, userId);

		MappingJsonFactory mappingJsonFactory = new MappingJsonFactory();

		JsonParser jsonFactoryParser = mappingJsonFactory.createParser(
			cpDefinitionsFile);

		JsonToken jsonToken = jsonFactoryParser.nextToken();

		if (jsonToken != JsonToken.START_ARRAY) {
			throw new Exception("JSON Array Expected");
		}

		int importCount = 0;

		while (jsonFactoryParser.nextToken() != JsonToken.END_ARRAY) {
			TreeNode treeNode = jsonFactoryParser.readValueAsTree();

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
				treeNode.toString());

			if (_log.isDebugEnabled()) {
				_log.debug(jsonObject);
			}

			_importCPDefinition(
				jsonObject, assetVocabularyName, commerceWarehouseIds,
				classLoader, imageDependenciesPath, serviceContext);

			importCount += 1;
		}

		if (_log.isInfoEnabled()) {
			_log.info("Products import count: " + importCount);
		}

		jsonFactoryParser.close();
	}

	public List<CPDefinition> importCPDefinitions(
			JSONArray jsonArray, String assetVocabularyName,
			long[] commerceWarehouseIds, ClassLoader classLoader,
			String imageDependenciesPath, long scopeGroupId, long userId)
		throws Exception {

		ServiceContext serviceContext = getServiceContext(scopeGroupId, userId);

		List<CPDefinition> cpDefinitions = new ArrayList<>(jsonArray.length());

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			CPDefinition cpDefinition = _importCPDefinition(
				jsonObject, assetVocabularyName, commerceWarehouseIds,
				classLoader, imageDependenciesPath, serviceContext);

			cpDefinitions.add(cpDefinition);
		}

		return cpDefinitions;
	}

	protected ServiceContext getServiceContext(long scopeGroupId, long userId)
		throws PortalException {

		User user = _userLocalService.getUser(userId);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setScopeGroupId(scopeGroupId);
		serviceContext.setUserId(userId);
		serviceContext.setCompanyId(user.getCompanyId());

		return serviceContext;
	}

	private CPDefinition _addCPDefinition(
			String name, String shortDescription, String description,
			String externalReferenceCode, String sku, String taxCategory,
			long width, long height, long depth, long weight,
			long[] assetCategoryIds, ServiceContext serviceContext)
		throws PortalException {

		serviceContext.setAssetCategoryIds(assetCategoryIds);

		User user = _userLocalService.getUser(serviceContext.getUserId());

		Calendar displayCalendar = CalendarFactoryUtil.getCalendar(
			user.getTimeZone());

		displayCalendar.add(Calendar.YEAR, -1);

		int displayDateMonth = displayCalendar.get(Calendar.MONTH);
		int displayDateDay = displayCalendar.get(Calendar.DAY_OF_MONTH);
		int displayDateYear = displayCalendar.get(Calendar.YEAR);
		int displayDateHour = displayCalendar.get(Calendar.HOUR);
		int displayDateMinute = displayCalendar.get(Calendar.MINUTE);
		int displayDateAmPm = displayCalendar.get(Calendar.AM_PM);

		if (displayDateAmPm == Calendar.PM) {
			displayDateHour += 12;
		}

		Calendar expirationCalendar = CalendarFactoryUtil.getCalendar(
			user.getTimeZone());

		expirationCalendar.add(Calendar.MONTH, 1);

		int expirationDateMonth = expirationCalendar.get(Calendar.MONTH);
		int expirationDateDay = expirationCalendar.get(Calendar.DAY_OF_MONTH);
		int expirationDateYear = expirationCalendar.get(Calendar.YEAR);
		int expirationDateHour = expirationCalendar.get(Calendar.HOUR);
		int expirationDateMinute = expirationCalendar.get(Calendar.MINUTE);
		int expirationDateAmPm = expirationCalendar.get(Calendar.AM_PM);

		if (expirationDateAmPm == Calendar.PM) {
			expirationDateHour += 12;
		}

		Locale locale = LocaleUtil.getSiteDefault();

		Map<Locale, String> nameMap = Collections.singletonMap(locale, name);
		Map<Locale, String> shortDescriptionMap = Collections.singletonMap(
			locale, shortDescription);
		Map<Locale, String> descriptionMap = Collections.singletonMap(
			locale, description);

		return _cpDefinitionLocalService.addCPDefinition(
			nameMap, shortDescriptionMap, descriptionMap, nameMap, null, null,
			null, "simple", true, true, false, false, 0, width, height, depth,
			weight, _getCPTaxCategoryId(taxCategory, serviceContext), false,
			false, null, true, displayDateMonth, displayDateDay,
			displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, true, sku,
			externalReferenceCode, serviceContext);
	}

	private void _addWarehouseQuantities(
			JSONObject skuJSONObject, long[] commerceWarehouseIds,
			ServiceContext serviceContext, CPInstance cpInstance)
		throws PortalException {

		for (int i = 0; i < commerceWarehouseIds.length; i++) {
			long commerceWarehouseId = commerceWarehouseIds[i];

			int quantity = skuJSONObject.getInt(
				"Warehouse" + String.valueOf(i + 1));

			if (quantity > 0) {
				_commerceWarehouseItemLocalService.addCommerceWarehouseItem(
					commerceWarehouseId, cpInstance.getCPInstanceId(), quantity,
					serviceContext);
			}
		}
	}

	private long _getCPTaxCategoryId(
			String taxCategory, ServiceContext serviceContext)
		throws PortalException {

		if (Validator.isNull(taxCategory)) {
			return 0;
		}

		List<CPTaxCategory> cpTaxCategories =
			_cpTaxCategoryLocalService.getCPTaxCategories(
				serviceContext.getScopeGroupId());

		for (CPTaxCategory cpTaxCategory : cpTaxCategories) {
			if (taxCategory.equals(
					cpTaxCategory.getName(LocaleUtil.getSiteDefault()))) {

				return cpTaxCategory.getCPTaxCategoryId();
			}
		}

		Map<Locale, String> nameMap = Collections.singletonMap(
			LocaleUtil.getSiteDefault(), taxCategory);

		CPTaxCategory cpTaxCategory =
			_cpTaxCategoryLocalService.addCPTaxCategory(
				nameMap, Collections.emptyMap(), serviceContext);

		return cpTaxCategory.getCPTaxCategoryId();
	}

	private CPDefinition _importCPDefinition(
			JSONObject jsonObject, String assetVocabularyName,
			long[] commerceWarehouseIds, ClassLoader classLoader,
			String imageDependenciesPath, ServiceContext serviceContext)
		throws Exception {

		// Categories

		List<AssetCategory> assetCategories = Collections.emptyList();

		JSONArray categoriesJSONArray = jsonObject.getJSONArray("Categories");

		if (categoriesJSONArray != null) {
			assetCategories = _assetCategoriesImporter.importAssetCategories(
				categoriesJSONArray, assetVocabularyName, classLoader,
				imageDependenciesPath, serviceContext.getScopeGroupId(),
				serviceContext.getUserId());
		}

		// Commerce product definition

		String name = jsonObject.getString("Name");
		String shortDescription = jsonObject.getString("ShortDescription");
		String description = jsonObject.getString("Description");
		String externalReferenceCode = jsonObject.getString(
			"ExternalReferenceCode");
		String sku = jsonObject.getString("Sku");
		String taxCategory = jsonObject.getString("TaxCategory");

		long width = jsonObject.getLong("Width");
		long height = jsonObject.getLong("Height");
		long length = jsonObject.getLong("Length");
		long weight = jsonObject.getLong("Weight");

		long[] assetCategoryIds = ListUtil.toLongArray(
			assetCategories, AssetCategory.CATEGORY_ID_ACCESSOR);

		int originalWorkflowAction = serviceContext.getWorkflowAction();

		serviceContext.setWorkflowAction(WorkflowConstants.STATUS_DRAFT);

		CPDefinition cpDefinition = _addCPDefinition(
			name, shortDescription, description, externalReferenceCode, sku,
			taxCategory, width, height, length, weight, assetCategoryIds,
			serviceContext);

		serviceContext.setWorkflowAction(originalWorkflowAction);

		// Commerce product definition specification option values

		JSONArray specificationOptionsJSONArray = jsonObject.getJSONArray(
			"SpecificationOptions");

		if (specificationOptionsJSONArray != null) {
			for (int i = 0; i < specificationOptionsJSONArray.length(); i++) {
				JSONObject specificationOptionJSONObject =
					specificationOptionsJSONArray.getJSONObject(i);

				_importCPDefinitionSpecificationOptionValue(
					specificationOptionJSONObject, cpDefinition, i,
					serviceContext);
			}
		}

		// Commerce product definition option rels

		JSONArray optionsJSONArray = jsonObject.getJSONArray("Options");

		if (optionsJSONArray != null) {
			for (int i = 0; i < optionsJSONArray.length(); i++) {
				JSONObject optionJSONObject = optionsJSONArray.getJSONObject(i);

				_importCPDefinitionOptionRel(
					optionJSONObject, cpDefinition, serviceContext);
			}
		}

		// Commerce product instances

		JSONArray skusJSONArray = jsonObject.getJSONArray("Skus");

		Calendar calendar = Calendar.getInstance();

		if (skusJSONArray != null) {
			for (int i = 0; i < skusJSONArray.length(); i++) {
				JSONObject skuJSONObject = skusJSONArray.getJSONObject(i);

				_importCPInstance(
					cpDefinition.getCPDefinitionId(), skuJSONObject,
					commerceWarehouseIds, calendar, serviceContext);
			}
		}
		else {
			try {
				_cpInstanceLocalService.buildCPInstances(
					cpDefinition.getCPDefinitionId(), serviceContext);
			}
			catch (NoSuchSkuContributorCPDefinitionOptionRelException
						nssccpdore) {

				if (_log.isInfoEnabled()) {
					_log.info(
						"No options defined as sku contributor for " +
							"CPDefinition " + cpDefinition.getCPDefinitionId());
				}
			}

			List<CPInstance> cpInstances = cpDefinition.getCPInstances();

			for (CPInstance cpInstance : cpInstances) {

				// Commerce product instance

				double priceDouble = jsonObject.getDouble("Price", 0);

				BigDecimal price = BigDecimal.valueOf(priceDouble);

				BigDecimal cost = BigDecimal.valueOf(
					jsonObject.getDouble("Cost", 0));

				BigDecimal promoPrice = BigDecimal.valueOf(
					jsonObject.getDouble("PromoPrice", 0));

				cpInstance.setPrice(price);
				cpInstance.setPromoPrice(promoPrice);
				cpInstance.setCost(cost);

				String manufacturerPartNumber = jsonObject.getString(
					"ManufacturerPartNumber");

				cpInstance.setManufacturerPartNumber(manufacturerPartNumber);

				String cpInstaceExternalReferenceCode = StringBundler.concat(
					String.valueOf(serviceContext.getScopeGroupId()), "_", sku);

				cpInstance.setExternalReferenceCode(
					cpInstaceExternalReferenceCode);

				_cpInstanceLocalService.updateCPInstance(cpInstance);

				// Commerce warehouse items

				_addWarehouseQuantities(
					jsonObject, commerceWarehouseIds, serviceContext,
					cpInstance);
			}
		}

		// Commerce product definition inventory

		String cpDefinitionInventoryEngine = jsonObject.getString(
			"CPDefinitionInventoryEngine");
		String lowStockActivity = jsonObject.getString("LowStockActivity");
		boolean displayAvailability = jsonObject.getBoolean(
			"DisplayAvailability");
		boolean displayStockQuantity = jsonObject.getBoolean(
			"DisplayStockQuantity");
		int minStockQuantity = jsonObject.getInt("MinStockQuantity");
		boolean backOrders = jsonObject.getBoolean("BackOrders");
		int minOrderQuantity = jsonObject.getInt(
			"MinOrderQuantity",
			CPDefinitionInventoryConstants.DEFAULT_MIN_ORDER_QUANTITY);
		int maxOrderQuantity = jsonObject.getInt(
			"MaxOrderQuantity",
			CPDefinitionInventoryConstants.DEFAULT_MAX_ORDER_QUANTITY);
		String allowedOrderQuantities = jsonObject.getString(
			"AllowedOrderQuantities");
		int multipleOrderQuantity = jsonObject.getInt(
			"MultipleOrderQuantity",
			CPDefinitionInventoryConstants.DEFAULT_MULTIPLE_ORDER_QUANTITY);

		_cpDefinitionInventoryLocalService.addCPDefinitionInventory(
			cpDefinition.getCPDefinitionId(), cpDefinitionInventoryEngine,
			lowStockActivity, displayAvailability, displayStockQuantity,
			minStockQuantity, backOrders, minOrderQuantity, maxOrderQuantity,
			allowedOrderQuantities, multipleOrderQuantity, serviceContext);

		// Commerce product definition availability estimate

		String availabilityEstimate = jsonObject.getString(
			"AvailabilityEstimate");

		if (Validator.isNotNull(availabilityEstimate)) {
			_updateCPDAvailabilityEstimate(
				cpDefinition, availabilityEstimate, serviceContext);
		}

		// Commerce product images

		String image = jsonObject.getString("Image");

		if (Validator.isNotNull(image)) {
			_cpAttachmentFileEntryCreator.addCPAttachmentFileEntry(
				cpDefinition, classLoader, imageDependenciesPath, image, 0,
				CPAttachmentFileEntryConstants.TYPE_IMAGE,
				serviceContext.getScopeGroupId(), serviceContext.getUserId());
		}

		JSONArray imagesJSONArray = jsonObject.getJSONArray("Images");

		if (imagesJSONArray != null) {
			for (int i = 0; i < imagesJSONArray.length(); i++) {
				_cpAttachmentFileEntryCreator.addCPAttachmentFileEntry(
					cpDefinition, classLoader, imageDependenciesPath,
					imagesJSONArray.getString(i), i,
					CPAttachmentFileEntryConstants.TYPE_IMAGE,
					serviceContext.getScopeGroupId(),
					serviceContext.getUserId());
			}
		}

		// Commerce product attachment file entries

		String attachment = jsonObject.getString("Attachment");

		if (Validator.isNotNull(attachment)) {
			_cpAttachmentFileEntryCreator.addCPAttachmentFileEntry(
				cpDefinition, classLoader, imageDependenciesPath, attachment, 0,
				CPAttachmentFileEntryConstants.TYPE_OTHER,
				serviceContext.getScopeGroupId(), serviceContext.getUserId());
		}

		JSONArray attachmentsJSONArray = jsonObject.getJSONArray("Attachments");

		if (attachmentsJSONArray != null) {
			for (int i = 0; i < attachmentsJSONArray.length(); i++) {
				_cpAttachmentFileEntryCreator.addCPAttachmentFileEntry(
					cpDefinition, classLoader, imageDependenciesPath,
					attachmentsJSONArray.getString(i), i,
					CPAttachmentFileEntryConstants.TYPE_OTHER,
					serviceContext.getScopeGroupId(),
					serviceContext.getUserId());
			}
		}

		_cpDefinitionLocalService.updateStatus(
			cpDefinition.getUserId(), cpDefinition.getCPDefinitionId(),
			WorkflowConstants.STATUS_APPROVED, serviceContext,
			new HashMap<String, Serializable>());

		return cpDefinition;
	}

	private CPDefinitionOptionRel _importCPDefinitionOptionRel(
			JSONObject jsonObject, CPDefinition cpDefinition,
			ServiceContext serviceContext)
		throws PortalException {

		// Commerce product definition option rel

		CPOption cpOption = _cpOptionLocalService.getCPOption(
			cpDefinition.getGroupId(), jsonObject.getString("Key"));

		boolean importOptionValue = true;

		JSONArray valuesJSONArray = jsonObject.getJSONArray("Values");

		if ((valuesJSONArray != null) && (valuesJSONArray.length() > 0)) {
			importOptionValue = false;
		}

		CPDefinitionOptionRel cpDefinitionOptionRel =
			_cpDefinitionOptionRelLocalService.addCPDefinitionOptionRel(
				cpDefinition.getCPDefinitionId(), cpOption.getCPOptionId(),
				importOptionValue, serviceContext);

		// Commerce product definition option value rels

		if (!importOptionValue) {
			for (int i = 0; i < valuesJSONArray.length(); i++) {
				String key;

				JSONObject valueJSONObject = valuesJSONArray.getJSONObject(i);

				if (valueJSONObject != null) {
					key = valueJSONObject.getString("Key");
				}
				else {
					key = valuesJSONArray.getString(i);
				}

				_importCPDefinitionOptionValueRel(
					key, cpDefinitionOptionRel, serviceContext);
			}
		}

		return cpDefinitionOptionRel;
	}

	private CPDefinitionOptionValueRel _importCPDefinitionOptionValueRel(
			String key, CPDefinitionOptionRel cpDefinitionOptionRel,
			ServiceContext serviceContext)
		throws PortalException {

		CPOptionValue cpOptionValue =
			_cpOptionValueLocalService.getCPOptionValue(
				cpDefinitionOptionRel.getCPOptionId(),
				FriendlyURLNormalizerUtil.normalize(key));

		return _cpDefinitionOptionValueRelLocalService.
			addCPDefinitionOptionValueRel(
				cpDefinitionOptionRel.getCPDefinitionOptionRelId(),
				cpOptionValue, serviceContext);
	}

	private CPDefinitionSpecificationOptionValue
			_importCPDefinitionSpecificationOptionValue(
				JSONObject jsonObject, CPDefinition cpDefinition,
				double defaultPriority, ServiceContext serviceContext)
		throws PortalException {

		CPSpecificationOption cpSpecificationOption =
			_cpSpecificationOptionLocalService.getCPSpecificationOption(
				cpDefinition.getGroupId(), jsonObject.getString("Key"));

		long cpOptionCategoryId = 0;

		String categoryKey = jsonObject.getString("CategoryKey");

		if (Validator.isNotNull(categoryKey)) {
			CPOptionCategory cpOptionCategory =
				_cpOptionCategoryLocalService.getCPOptionCategory(
					cpSpecificationOption.getGroupId(), categoryKey);

			cpOptionCategoryId = cpOptionCategory.getCPOptionCategoryId();
		}
		else {
			cpOptionCategoryId = cpSpecificationOption.getCPOptionCategoryId();
		}

		Map<Locale, String> valueMap = Collections.singletonMap(
			LocaleUtil.getSiteDefault(), jsonObject.getString("Value"));
		double priority = jsonObject.getDouble("Priority", defaultPriority);

		return _cpDefinitionSpecificationOptionValueLocalService.
			addCPDefinitionSpecificationOptionValue(
				cpDefinition.getCPDefinitionId(),
				cpSpecificationOption.getCPSpecificationOptionId(),
				cpOptionCategoryId, valueMap, priority, serviceContext);
	}

	private CPInstance _importCPInstance(
			long cpDefinitionId, JSONObject skuJSONObject,
			long[] commerceWarehouseIds, Calendar calendar,
			ServiceContext serviceContext)
		throws PortalException {

		String sku = skuJSONObject.getString("Sku");
		String manufacturerPartNumber = skuJSONObject.getString(
			"ManufacturerPartNumber");
		double price = skuJSONObject.getDouble("Price");
		double promoPrice = skuJSONObject.getDouble("PromoPrice");

		JSONArray options = skuJSONObject.getJSONArray("ContributorOptions");

		String optionsJSON = null;

		if (options != null) {
			JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

			for (int i = 0; i < options.length(); i++) {
				JSONObject optionsJSONObject = options.getJSONObject(i);

				String key = optionsJSONObject.getString("key");

				CPOption cpOption = _cpOptionLocalService.fetchCPOption(
					serviceContext.getScopeGroupId(), key);

				if (cpOption == null) {
					continue;
				}

				CPDefinitionOptionRel cpDefinitionOptionRel =
					_cpDefinitionOptionRelLocalService.
						fetchCPDefinitionOptionRel(
							cpDefinitionId, cpOption.getCPOptionId());

				if (cpDefinitionOptionRel == null) {
					continue;
				}

				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

				jsonObject.put(
					"key", cpDefinitionOptionRel.getCPDefinitionOptionRelId());

				List<CPDefinitionOptionValueRel> cpDefinitionOptionValueRels =
					_cpDefinitionOptionValueRelLocalService.
						getCPDefinitionOptionValueRels(
							cpDefinitionOptionRel.getCPDefinitionOptionRelId());

				for (CPDefinitionOptionValueRel cpDefinitionOptionValueRel :
						cpDefinitionOptionValueRels) {

					String name = cpDefinitionOptionValueRel.getName(
						LocaleUtil.getSiteDefault());

					if (name.equals(optionsJSONObject.getString("value"))) {
						JSONArray valueJSONArray =
							JSONFactoryUtil.createJSONArray();

						valueJSONArray.put(
							String.valueOf(
								cpDefinitionOptionValueRel.
									getCPDefinitionOptionValueRelId()));

						jsonObject.put("value", valueJSONArray);
					}
				}

				jsonArray.put(jsonObject);
			}

			optionsJSON = jsonArray.toString();
		}

		CPDefinition cpDefinition = _cpDefinitionLocalService.getCPDefinition(
			cpDefinitionId);

		String externalReferenceCode = StringBundler.concat(
			String.valueOf(serviceContext.getScopeGroupId()), "_", sku);

		CPInstance cpInstance = _cpInstanceLocalService.addCPInstance(
			cpDefinitionId, sku, null, manufacturerPartNumber, true,
			optionsJSON, cpDefinition.getWidth(), cpDefinition.getHeight(),
			cpDefinition.getDepth(), cpDefinition.getWeight(),
			BigDecimal.valueOf(price), BigDecimal.valueOf(promoPrice),
			BigDecimal.valueOf(0), true, externalReferenceCode,
			calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
			calendar.get(Calendar.YEAR), calendar.get(Calendar.HOUR_OF_DAY),
			calendar.get(Calendar.MINUTE), 0, 0, 0, 0, 0, true, serviceContext);

		_addWarehouseQuantities(
			skuJSONObject, commerceWarehouseIds, serviceContext, cpInstance);

		return cpInstance;
	}

	private CPDAvailabilityEstimate _updateCPDAvailabilityEstimate(
			CPDefinition cpDefinition, String availabilityEstimate,
			ServiceContext serviceContext)
		throws PortalException {

		List<CommerceAvailabilityEstimate> commerceAvailabilityEstimates =
			_commerceAvailabilityEstimateLocalService.
				getCommerceAvailabilityEstimates(
					serviceContext.getScopeGroupId(), QueryUtil.ALL_POS,
					QueryUtil.ALL_POS,
					new CommerceAvailabilityEstimatePriorityComparator(true));

		for (CommerceAvailabilityEstimate commerceAvailabilityEstimate :
				commerceAvailabilityEstimates) {

			if (availabilityEstimate.equals(
					commerceAvailabilityEstimate.getTitle(
						LocaleUtil.getSiteDefault()))) {

				return _cpdAvailabilityEstimateLocalService.
					updateCPDAvailabilityEstimate(
						0, cpDefinition.getCPDefinitionId(),
						commerceAvailabilityEstimate.
							getCommerceAvailabilityEstimateId(),
						serviceContext);
			}
		}

		Map<Locale, String> titleMap = Collections.singletonMap(
			LocaleUtil.getSiteDefault(), availabilityEstimate);

		CommerceAvailabilityEstimate commerceAvailabilityEstimate =
			_commerceAvailabilityEstimateLocalService.
				addCommerceAvailabilityEstimate(titleMap, 0, serviceContext);

		return _cpdAvailabilityEstimateLocalService.
			updateCPDAvailabilityEstimate(
				0, cpDefinition.getCPDefinitionId(),
				commerceAvailabilityEstimate.
					getCommerceAvailabilityEstimateId(),
				serviceContext);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CPDefinitionsImporter.class);

	@Reference
	private AssetCategoriesImporter _assetCategoriesImporter;

	@Reference
	private CommerceAvailabilityEstimateLocalService
		_commerceAvailabilityEstimateLocalService;

	@Reference
	private CommerceWarehouseItemLocalService
		_commerceWarehouseItemLocalService;

	@Reference
	private CPAttachmentFileEntryCreator _cpAttachmentFileEntryCreator;

	@Reference
	private CPDAvailabilityEstimateLocalService
		_cpdAvailabilityEstimateLocalService;

	@Reference
	private CPDefinitionInventoryLocalService
		_cpDefinitionInventoryLocalService;

	@Reference
	private CPDefinitionLocalService _cpDefinitionLocalService;

	@Reference
	private CPDefinitionOptionRelLocalService
		_cpDefinitionOptionRelLocalService;

	@Reference
	private CPDefinitionOptionValueRelLocalService
		_cpDefinitionOptionValueRelLocalService;

	@Reference
	private CPDefinitionSpecificationOptionValueLocalService
		_cpDefinitionSpecificationOptionValueLocalService;

	@Reference
	private CPInstanceLocalService _cpInstanceLocalService;

	@Reference
	private CPOptionCategoryLocalService _cpOptionCategoryLocalService;

	@Reference
	private CPOptionLocalService _cpOptionLocalService;

	@Reference
	private CPOptionValueLocalService _cpOptionValueLocalService;

	@Reference
	private CPSpecificationOptionLocalService
		_cpSpecificationOptionLocalService;

	@Reference
	private CPTaxCategoryLocalService _cpTaxCategoryLocalService;

	@Reference
	private UserLocalService _userLocalService;

}