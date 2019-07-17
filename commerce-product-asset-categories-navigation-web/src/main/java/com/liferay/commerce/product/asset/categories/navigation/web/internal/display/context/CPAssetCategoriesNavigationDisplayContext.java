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

package com.liferay.commerce.product.asset.categories.navigation.web.internal.display.context;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryService;
import com.liferay.asset.kernel.service.AssetVocabularyService;
import com.liferay.commerce.media.CommerceMediaResolver;
import com.liferay.commerce.product.asset.categories.navigation.web.internal.configuration.CPAssetCategoriesNavigationPortletInstanceConfiguration;
import com.liferay.commerce.product.constants.CPConstants;
import com.liferay.commerce.product.model.CPAttachmentFileEntry;
import com.liferay.commerce.product.model.CPAttachmentFileEntryConstants;
import com.liferay.commerce.product.model.CPFriendlyURLEntry;
import com.liferay.commerce.product.service.CPAttachmentFileEntryService;
import com.liferay.commerce.product.service.CPFriendlyURLEntryLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Marco Leo
 */
public class CPAssetCategoriesNavigationDisplayContext {

	public CPAssetCategoriesNavigationDisplayContext(
			HttpServletRequest httpServletRequest,
			AssetCategoryService assetCategoryService,
			AssetVocabularyService assetVocabularyService,
			CommerceMediaResolver commerceMediaResolver,
			CPAttachmentFileEntryService cpAttachmentFileEntryService,
			CPFriendlyURLEntryLocalService cpFriendlyURLEntryLocalService,
			Portal portal)
		throws ConfigurationException {

		_httpServletRequest = httpServletRequest;
		_assetCategoryService = assetCategoryService;
		_assetVocabularyService = assetVocabularyService;
		_commerceMediaResolver = commerceMediaResolver;
		_cpAttachmentFileEntryService = cpAttachmentFileEntryService;
		_cpFriendlyURLEntryLocalService = cpFriendlyURLEntryLocalService;
		_portal = portal;

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		_cpAssetCategoriesNavigationPortletInstanceConfiguration =
			portletDisplay.getPortletInstanceConfiguration(
				CPAssetCategoriesNavigationPortletInstanceConfiguration.class);
	}

	public List<AssetCategory> getAssetCategories() throws PortalException {
		if (_assetCategories != null) {
			return _assetCategories;
		}

		AssetCategory assetCategory = getParentCategory();

		if (assetCategory != null) {
			_assetCategories = _assetCategoryService.getVocabularyCategories(
				assetCategory.getCategoryId(), assetCategory.getVocabularyId(),
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
		}
		else {
			if (useRootCategory()) {
				return Collections.emptyList();
			}

			AssetVocabulary assetVocabulary = getAssetVocabulary();

			if (assetVocabulary == null) {
				return Collections.emptyList();
			}

			_assetCategories =
				_assetCategoryService.getVocabularyRootCategories(
					assetVocabulary.getGroupId(),
					assetVocabulary.getVocabularyId(), QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null);
		}

		return _assetCategories;
	}

	public List<AssetVocabulary> getAssetVocabularies() throws PortalException {
		if (_assetVocabularies != null) {
			return _assetVocabularies;
		}

		ThemeDisplay themeDisplay =
			(ThemeDisplay)_httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		_assetVocabularies = _assetVocabularyService.getGroupVocabularies(
			themeDisplay.getCompanyGroupId());

		return _assetVocabularies;
	}

	public AssetVocabulary getAssetVocabulary() throws PortalException {
		if (_assetVocabulary != null) {
			return _assetVocabulary;
		}

		long assetVocabularyId = GetterUtil.getLong(
			_cpAssetCategoriesNavigationPortletInstanceConfiguration.
				assetVocabularyId());

		_assetVocabulary = _assetVocabularyService.fetchVocabulary(
			assetVocabularyId);

		return _assetVocabulary;
	}

	public List<AssetCategory> getChildAssetCategories(long categoryId)
		throws PortalException {

		return _assetCategoryService.getChildCategories(categoryId);
	}

	public CPAssetCategoriesNavigationPortletInstanceConfiguration
		getCPAssetCategoriesNavigationPortletInstanceConfiguration() {

		return _cpAssetCategoriesNavigationPortletInstanceConfiguration;
	}

	public String getDefaultImageSrc(long categoryId, ThemeDisplay themeDisplay)
		throws Exception {

		CPAttachmentFileEntry cpAttachmentFileEntry = null;

		long classNameId = _portal.getClassNameId(AssetCategory.class);

		List<CPAttachmentFileEntry> cpAttachmentFileEntries =
			_cpAttachmentFileEntryService.getCPAttachmentFileEntries(
				classNameId, categoryId,
				CPAttachmentFileEntryConstants.TYPE_IMAGE,
				WorkflowConstants.STATUS_APPROVED, 0, 1);

		if (cpAttachmentFileEntries.isEmpty()) {
			return null;
		}

		cpAttachmentFileEntry = cpAttachmentFileEntries.get(0);

		if (cpAttachmentFileEntry == null) {
			return null;
		}

		return _commerceMediaResolver.getUrl(
			cpAttachmentFileEntry.getCPAttachmentFileEntryId());
	}

	public String getDisplayStyle() {
		return _cpAssetCategoriesNavigationPortletInstanceConfiguration.
			displayStyle();
	}

	public long getDisplayStyleGroupId() {
		if (_displayStyleGroupId > 0) {
			return _displayStyleGroupId;
		}

		_displayStyleGroupId =
			_cpAssetCategoriesNavigationPortletInstanceConfiguration.
				displayStyleGroupId();

		if (_displayStyleGroupId <= 0) {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)_httpServletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			_displayStyleGroupId = themeDisplay.getScopeGroupId();
		}

		return _displayStyleGroupId;
	}

	public String getFriendlyURL(long categoryId, ThemeDisplay themeDisplay)
		throws Exception {

		long classNameId = _portal.getClassNameId(AssetCategory.class);

		AssetCategory assetCategory = _assetCategoryService.fetchCategory(
			categoryId);

		if (assetCategory == null) {
			return StringPool.BLANK;
		}

		String languageId = LanguageUtil.getLanguageId(
			themeDisplay.getLocale());

		CPFriendlyURLEntry cpFriendlyURLEntry =
			_cpFriendlyURLEntryLocalService.fetchCPFriendlyURLEntry(
				GroupConstants.DEFAULT_LIVE_GROUP_ID, classNameId, categoryId,
				languageId, true);

		if (cpFriendlyURLEntry == null) {
			String defaultLanguageId = LanguageUtil.getLanguageId(
				themeDisplay.getSiteDefaultLocale());

			if (languageId.equals(defaultLanguageId)) {
				return StringPool.BLANK;
			}

			cpFriendlyURLEntry =
				_cpFriendlyURLEntryLocalService.fetchCPFriendlyURLEntry(
					GroupConstants.DEFAULT_LIVE_GROUP_ID, classNameId,
					categoryId, defaultLanguageId, true);

			if (cpFriendlyURLEntry == null) {
				return StringPool.BLANK;
			}
		}

		String groupFriendlyUrl = _portal.getGroupFriendlyURL(
			themeDisplay.getLayoutSet(), themeDisplay);

		return groupFriendlyUrl + CPConstants.SEPARATOR_ASSET_CATEGORY_URL +
			cpFriendlyURLEntry.getUrlTitle();
	}

	public String getRootAssetCategoryId() {
		return _cpAssetCategoriesNavigationPortletInstanceConfiguration.
			rootAssetCategoryId();
	}

	public String getVocabularyNavigation(ThemeDisplay themeDisplay)
		throws Exception {

		long categoryId = 0;

		AssetCategory assetCategory = getParentCategory();

		if (assetCategory != null) {
			categoryId = assetCategory.getCategoryId();
		}

		List<AssetCategory> categories = getAssetCategories();

		if (categories.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler();

		sb.append("<div class=\"lfr-asset-category-list-container\">");
		sb.append("<ul class=\"lfr-asset-category-list\">");

		buildCategoriesNavigation(categories, categoryId, themeDisplay, sb);

		sb.append("</ul></div>");

		return sb.toString();
	}

	public boolean useCategoryFromRequest() {
		return _cpAssetCategoriesNavigationPortletInstanceConfiguration.
			useCategoryFromRequest();
	}

	public boolean useRootCategory() {
		return _cpAssetCategoriesNavigationPortletInstanceConfiguration.
			useRootCategory();
	}

	protected void buildCategoriesNavigation(
			List<AssetCategory> categories, long categoryId,
			ThemeDisplay themeDisplay, StringBundler sb)
		throws Exception {

		for (AssetCategory assetCategory : categories) {
			List<AssetCategory> childAssetCategories = getChildAssetCategories(
				assetCategory.getCategoryId());

			String friendlyURL = getFriendlyURL(
				assetCategory.getCategoryId(), themeDisplay);

			sb.append("<li class=\"tree-node\"><span>");

			if (categoryId == assetCategory.getCategoryId()) {
				sb.append("<a class=\"tag-selected\" href=\"");
				sb.append(HtmlUtil.escape(friendlyURL));
			}
			else {
				sb.append("<a href=\"");
				sb.append(HtmlUtil.escape(friendlyURL));
			}

			sb.append("\">");

			String categoryTitle = assetCategory.getTitle(
				themeDisplay.getLocale());

			sb.append(HtmlUtil.escape(categoryTitle));

			sb.append("</a>");
			sb.append("</span>");

			if (!childAssetCategories.isEmpty()) {
				sb.append("<ul>");

				buildCategoriesNavigation(
					childAssetCategories, categoryId, themeDisplay, sb);

				sb.append("</ul>");
			}

			sb.append("</li>");
		}
	}

	protected AssetCategory getParentCategory() throws PortalException {
		AssetCategory assetCategory = null;

		if (useRootCategory()) {
			if (useCategoryFromRequest()) {
				assetCategory = (AssetCategory)_httpServletRequest.getAttribute(
					WebKeys.ASSET_CATEGORY);
			}
			else {
				long categoryId = GetterUtil.getLong(getRootAssetCategoryId());

				if (categoryId > 0) {
					assetCategory = _assetCategoryService.getCategory(
						categoryId);
				}
			}
		}

		return assetCategory;
	}

	private List<AssetCategory> _assetCategories;
	private final AssetCategoryService _assetCategoryService;
	private List<AssetVocabulary> _assetVocabularies;
	private AssetVocabulary _assetVocabulary;
	private final AssetVocabularyService _assetVocabularyService;
	private final CommerceMediaResolver _commerceMediaResolver;
	private final CPAssetCategoriesNavigationPortletInstanceConfiguration
		_cpAssetCategoriesNavigationPortletInstanceConfiguration;
	private final CPAttachmentFileEntryService _cpAttachmentFileEntryService;
	private final CPFriendlyURLEntryLocalService
		_cpFriendlyURLEntryLocalService;
	private long _displayStyleGroupId;
	private final HttpServletRequest _httpServletRequest;
	private final Portal _portal;

}