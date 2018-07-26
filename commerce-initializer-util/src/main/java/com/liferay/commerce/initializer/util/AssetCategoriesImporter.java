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
import com.liferay.commerce.initializer.util.internal.CPAttachmentFileEntryCreator;
import com.liferay.commerce.product.model.CPFriendlyURLEntry;
import com.liferay.commerce.product.service.CPFriendlyURLEntryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
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
@Component(service = AssetCategoriesImporter.class)
public class AssetCategoriesImporter {

	public List<AssetCategory> importAssetCategories(
			JSONArray jsonArray, String assetVocabularyName,
			ClassLoader classLoader, String imageDependenciesPath,
			ServiceContext serviceContext)
		throws Exception {

		List<AssetCategory> assetCategories = new ArrayList<>(
			jsonArray.length());

		AssetVocabulary assetVocabulary = _addAssetVocabulary(
			assetVocabularyName, serviceContext);

		for (int i = 0; i < jsonArray.length(); i++) {
			String title = null;
			String imageFileName = null;

			JSONObject jsonObject = jsonArray.getJSONObject(i);

			if (jsonObject != null) {
				title = jsonObject.getString("Title");
				imageFileName = jsonObject.getString("Image");
			}
			else {
				title = jsonArray.getString(i);
			}

			AssetCategory assetCategory = _addAssetCategory(
				assetVocabulary.getVocabularyId(), title, classLoader,
				imageDependenciesPath, imageFileName, serviceContext);

			assetCategories.add(assetCategory);
		}

		return assetCategories;
	}

	private AssetCategory _addAssetCategory(
			long assetVocabularyId, String title, ClassLoader classLoader,
			String imageDependenciesPath, String imageFileName,
			ServiceContext serviceContext)
		throws Exception {

		// Asset category

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

		// Commerce product friendly URL entry

		long classNameId = _portal.getClassNameId(AssetCategory.class);

		List<CPFriendlyURLEntry> cpFriendlyURLEntries =
			_cpFriendlyURLEntryLocalService.getCPFriendlyURLEntries(
				serviceContext.getScopeGroupId(), classNameId,
				assetCategory.getCategoryId());

		if (cpFriendlyURLEntries.isEmpty()) {
			Map<Locale, String> urlTitleMap = _getUniqueUrlTitles(
				assetCategory);

			_cpFriendlyURLEntryLocalService.addCPFriendlyURLEntries(
				serviceContext.getScopeGroupId(), serviceContext.getCompanyId(),
				AssetCategory.class, assetCategory.getCategoryId(),
				urlTitleMap);
		}

		// Commerce product attachment file entry

		if (Validator.isNotNull(imageFileName)) {
			_cpAttachmentFileEntryCreator.addCPAttachmentFileEntry(
				assetCategory, classLoader, imageDependenciesPath,
				imageFileName, 0, serviceContext);
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

	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;

	@Reference
	private AssetVocabularyLocalService _assetVocabularyLocalService;

	@Reference
	private CPAttachmentFileEntryCreator _cpAttachmentFileEntryCreator;

	@Reference
	private CPFriendlyURLEntryLocalService _cpFriendlyURLEntryLocalService;

	@Reference
	private Portal _portal;

}