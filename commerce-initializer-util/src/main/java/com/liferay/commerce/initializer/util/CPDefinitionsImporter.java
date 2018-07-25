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

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetCategoryConstants;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.commerce.constants.CPDefinitionInventoryConstants;
import com.liferay.commerce.product.model.CPAttachmentFileEntry;
import com.liferay.commerce.product.model.CPAttachmentFileEntryConstants;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPFriendlyURLEntry;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPAttachmentFileEntryLocalService;
import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.commerce.product.service.CPFriendlyURLEntryLocalService;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.commerce.service.CPDefinitionInventoryLocalService;
import com.liferay.commerce.service.CommerceWarehouseItemLocalService;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.MimeTypes;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.io.InputStream;

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
 */
@Component(service = CPDefinitionsImporter.class)
public class CPDefinitionsImporter {

	public List<CPDefinition> importCPDefinitions(
			JSONArray jsonArray, String assetVocabularyName,
			long[] commerceWarehouseIds, ClassLoader classLoader,
			String imagesDependencyPath, ServiceContext serviceContext)
		throws Exception {

		List<CPDefinition> cpDefinitions = new ArrayList<>(jsonArray.length());

		AssetVocabulary assetVocabulary = _addAssetVocabulary(
			assetVocabularyName, serviceContext);

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			CPDefinition cpDefinition = _importCPDefinition(
				jsonObject, assetVocabulary.getVocabularyId(),
				commerceWarehouseIds, classLoader, imagesDependencyPath,
				serviceContext);

			cpDefinitions.add(cpDefinition);
		}

		return cpDefinitions;
	}

	private AssetCategory _addAssetCategory(
			long assetVocabularyId, String title, ServiceContext serviceContext)
		throws PortalException {

		AssetCategory assetCategory = _assetCategoryLocalService.fetchCategory(
			serviceContext.getScopeGroupId(),
			AssetCategoryConstants.DEFAULT_PARENT_CATEGORY_ID, title,
			assetVocabularyId);

		if (assetCategory == null) {
			Map<Locale, String> titleMap = Collections.singletonMap(
				serviceContext.getLocale(), title);

			assetCategory = _assetCategoryLocalService.addCategory(
				serviceContext.getUserId(), serviceContext.getScopeGroupId(),
				AssetCategoryConstants.DEFAULT_PARENT_CATEGORY_ID, titleMap,
				null, assetVocabularyId, new String[0], serviceContext);
		}

		return assetCategory;
	}

	private AssetVocabulary _addAssetVocabulary(
			String name, ServiceContext serviceContext)
		throws PortalException {

		AssetVocabulary assetVocabulary =
			_assetVocabularyLocalService.fetchGroupVocabulary(
				serviceContext.getScopeGroupId(), name);

		if (assetVocabulary == null) {
			assetVocabulary = _assetVocabularyLocalService.addVocabulary(
				serviceContext.getUserId(), serviceContext.getScopeGroupId(),
				name, serviceContext);
		}

		return assetVocabulary;
	}

	private CPDefinition _addCPDefinition(
			String name, String description, String sku,
			long[] assetCategoryIds, ServiceContext serviceContext)
		throws PortalException {

		serviceContext.setAssetCategoryIds(assetCategoryIds);

		Calendar displayCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

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
			serviceContext.getTimeZone());

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

		Locale locale = serviceContext.getLocale();

		Map<Locale, String> nameMap = Collections.singletonMap(locale, name);
		Map<Locale, String> descriptionMap = Collections.singletonMap(
			locale, description);

		return _cpDefinitionLocalService.addCPDefinition(
			nameMap, null, descriptionMap, nameMap, null, null, null, "simple",
			true, true, false, false, 0, 10, 10, 10, 10, 0, false, false, null,
			true, displayDateMonth, displayDateDay, displayDateYear,
			displayDateHour, displayDateMinute, expirationDateMonth,
			expirationDateDay, expirationDateYear, expirationDateHour,
			expirationDateMinute, true, sku, StringPool.BLANK, serviceContext);
	}

	private CPAttachmentFileEntry _addCPDefinitionAttachmentFileEntry(
			long cpDefinitionId, ClassLoader classLoader, String dependencyPath,
			String fileName, double priority, ServiceContext serviceContext)
		throws Exception {

		long classNameId = _portal.getClassNameId(CPDefinition.class);

		Map<Locale, String> titleMap = Collections.singletonMap(
			serviceContext.getLocale(), fileName);

		InputStream inputStream = classLoader.getResourceAsStream(
			dependencyPath + fileName);

		if (inputStream == null) {
			return null;
		}

		File file = null;
		FileEntry fileEntry = null;

		try {
			file = FileUtil.createTempFile(inputStream);

			fileEntry = _dlAppService.addFileEntry(
				serviceContext.getScopeGroupId(),
				DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, fileName,
				_mimeTypes.getContentType(file), fileName, StringPool.BLANK,
				StringPool.BLANK, file, serviceContext);
		}
		finally {
			FileUtil.delete(file);
		}

		Calendar displayCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

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
			serviceContext.getTimeZone());

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

		return _cpAttachmentFileEntryLocalService.addCPAttachmentFileEntry(
			classNameId, cpDefinitionId, fileEntry.getFileEntryId(),
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute, true,
			titleMap, null, priority, CPAttachmentFileEntryConstants.TYPE_IMAGE,
			serviceContext);
	}

	private Map<Locale, String> _getUniqueUrlTitles(
		AssetCategory assetCategory) {

		Map<Locale, String> urlTitleMap = new HashMap<>();

		Map<Locale, String> titleMap = assetCategory.getTitleMap();

		long classNameId = _portal.getClassNameId(AssetCategory.class);

		for (Map.Entry<Locale, String> titleEntry : titleMap.entrySet()) {
			String languageId = LocaleUtil.toLanguageId(titleEntry.getKey());

			String urlTitle = _cpFriendlyURLEntryLocalService.buildUrlTitle(
				assetCategory.getGroupId(), classNameId,
				assetCategory.getCategoryId(), languageId,
				titleEntry.getValue());

			urlTitleMap.put(titleEntry.getKey(), urlTitle);
		}

		return urlTitleMap;
	}

	private long[] _importAssetCategories(
			JSONArray jsonArray, long assetVocabularyId,
			ServiceContext serviceContext)
		throws PortalException {

		long[] assetCategoryIds = new long[jsonArray.length()];

		long classNameId = _portal.getClassNameId(AssetCategory.class);

		for (int i = 0; i < jsonArray.length(); i++) {
			String title = jsonArray.getString(i);

			AssetCategory assetCategory = _addAssetCategory(
				assetVocabularyId, title, serviceContext);

			assetCategoryIds[i] = assetCategory.getCategoryId();

			List<CPFriendlyURLEntry> cpFriendlyURLEntries =
				_cpFriendlyURLEntryLocalService.getCPFriendlyURLEntries(
					serviceContext.getScopeGroupId(), classNameId,
					assetCategory.getCategoryId());

			if (cpFriendlyURLEntries.isEmpty()) {
				Map<Locale, String> urlTitleMap = _getUniqueUrlTitles(
					assetCategory);

				_cpFriendlyURLEntryLocalService.addCPFriendlyURLEntries(
					serviceContext.getScopeGroupId(),
					serviceContext.getCompanyId(), AssetCategory.class,
					assetCategory.getCategoryId(), urlTitleMap);
			}
		}

		return assetCategoryIds;
	}

	private CPDefinition _importCPDefinition(
			JSONObject jsonObject, long assetVocabularyId,
			long[] commerceWarehouseIds, ClassLoader classLoader,
			String imagesDependencyPath, ServiceContext serviceContext)
		throws Exception {

		// Categories

		JSONArray categoriesJSONArray = jsonObject.getJSONArray("Categories");

		long[] assetCategoryIds = _importAssetCategories(
			categoriesJSONArray, assetVocabularyId, serviceContext);

		// Commerce product definition

		String name = jsonObject.getString("Name");
		String description = jsonObject.getString("Description");
		String sku = jsonObject.getString("Sku");

		CPDefinition cpDefinition = _addCPDefinition(
			name, description, sku, assetCategoryIds, serviceContext);

		// Commerce product instance

		BigDecimal cost = BigDecimal.valueOf(jsonObject.getDouble("Cost"));
		BigDecimal price = BigDecimal.valueOf(jsonObject.getDouble("Price"));

		CPInstance cpInstance = _cpInstanceLocalService.getCPInstance(
			cpDefinition.getCPDefinitionId(), sku);

		cpInstance.setPrice(price);
		cpInstance.setCost(cost);

		_cpInstanceLocalService.updateCPInstance(cpInstance);

		// Commerce warehouse items

		for (int i = 0; i < commerceWarehouseIds.length; i++) {
			long commerceWarehouseId = commerceWarehouseIds[i];

			int quantity = jsonObject.getInt(
				"Warehouse" + String.valueOf(i + 1));

			if (quantity > 0) {
				_commerceWarehouseItemLocalService.addCommerceWarehouseItem(
					commerceWarehouseId, cpInstance.getCPInstanceId(), quantity,
					serviceContext);
			}
		}

		// Commerce product definition inventory

		_cpDefinitionInventoryLocalService.addCPDefinitionInventory(
			cpDefinition.getCPDefinitionId(), null, null, false, false, 0, true,
			CPDefinitionInventoryConstants.DEFAULT_MIN_ORDER_QUANTITY,
			CPDefinitionInventoryConstants.DEFAULT_MAX_ORDER_QUANTITY, null,
			CPDefinitionInventoryConstants.DEFAULT_MULTIPLE_ORDER_QUANTITY,
			serviceContext);

		// Commerce product attachment file entries

		String image = jsonObject.getString("Image");

		if (Validator.isNotNull(image)) {
			_addCPDefinitionAttachmentFileEntry(
				cpDefinition.getCPDefinitionId(), classLoader,
				imagesDependencyPath, image, 0, serviceContext);
		}

		JSONArray imagesJSONArray = jsonObject.getJSONArray("Images");

		if (imagesJSONArray != null) {
			for (int i = 0; i < imagesJSONArray.length(); i++) {
				_addCPDefinitionAttachmentFileEntry(
					cpDefinition.getCPDefinitionId(), classLoader,
					imagesDependencyPath, imagesJSONArray.getString(i), i,
					serviceContext);
			}
		}

		return cpDefinition;
	}

	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;

	@Reference
	private AssetVocabularyLocalService _assetVocabularyLocalService;

	@Reference
	private CommerceWarehouseItemLocalService
		_commerceWarehouseItemLocalService;

	@Reference
	private CPAttachmentFileEntryLocalService
		_cpAttachmentFileEntryLocalService;

	@Reference
	private CPDefinitionInventoryLocalService
		_cpDefinitionInventoryLocalService;

	@Reference
	private CPDefinitionLocalService _cpDefinitionLocalService;

	@Reference
	private CPFriendlyURLEntryLocalService _cpFriendlyURLEntryLocalService;

	@Reference
	private CPInstanceLocalService _cpInstanceLocalService;

	@Reference
	private DLAppService _dlAppService;

	@Reference
	private MimeTypes _mimeTypes;

	@Reference
	private Portal _portal;

}