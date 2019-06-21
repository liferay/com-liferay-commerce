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

package com.liferay.commerce.product.content.web.internal.display.context;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetTagLocalService;
import com.liferay.asset.kernel.service.AssetVocabularyServiceUtil;
import com.liferay.commerce.product.catalog.CPCatalogEntry;
import com.liferay.commerce.product.catalog.CPSku;
import com.liferay.commerce.product.content.render.list.CPContentListRendererRegistry;
import com.liferay.commerce.product.content.render.list.entry.CPContentListEntryRendererRegistry;
import com.liferay.commerce.product.content.web.internal.util.CPPublisherWebHelper;
import com.liferay.commerce.product.data.source.CPDataSource;
import com.liferay.commerce.product.data.source.CPDataSourceRegistry;
import com.liferay.commerce.product.item.selector.criterion.CPDefinitionItemSelectorCriterion;
import com.liferay.commerce.product.type.CPTypeServicesTracker;
import com.liferay.commerce.product.util.CPInstanceHelper;
import com.liferay.item.selector.ItemSelector;
import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.criteria.UUIDItemSelectorReturnType;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PrefsParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Marco Leo
 */
public class CPPublisherConfigurationDisplayContext
	extends BaseCPPublisherDisplayContext {

	public CPPublisherConfigurationDisplayContext(
			AssetCategoryLocalService assetCategoryLocalService,
			AssetTagLocalService assetTagLocalService,
			CPContentListEntryRendererRegistry contentListEntryRendererRegistry,
			CPContentListRendererRegistry cpContentListRendererRegistry,
			CPDataSourceRegistry cpDataSourceRegistry,
			CPInstanceHelper cpInstanceHelper,
			CPPublisherWebHelper cpPublisherWebHelper,
			CPTypeServicesTracker cpTypeServicesTracker,
			HttpServletRequest httpServletRequest, ItemSelector itemSelector)
		throws PortalException {

		super(
			contentListEntryRendererRegistry, cpContentListRendererRegistry,
			cpPublisherWebHelper, cpTypeServicesTracker, httpServletRequest);

		_assetCategoryLocalService = assetCategoryLocalService;
		_assetTagLocalService = assetTagLocalService;
		_cpDataSourceRegistry = cpDataSourceRegistry;
		_cpInstanceHelper = cpInstanceHelper;
		_itemSelector = itemSelector;
	}

	public String filterAssetTagNames(long groupId, String assetTagNames) {
		List<String> filteredAssetTagNames = new ArrayList<>();

		String[] assetTagNamesArray = StringUtil.split(assetTagNames);

		long[] assetTagIds = _assetTagLocalService.getTagIds(
			groupId, assetTagNamesArray);

		for (long assetTagId : assetTagIds) {
			AssetTag assetTag = _assetTagLocalService.fetchAssetTag(assetTagId);

			if (assetTag != null) {
				filteredAssetTagNames.add(assetTag.getName());
			}
		}

		return StringUtil.merge(filteredAssetTagNames);
	}

	public JSONArray getAutoFieldRulesJSONArray() {
		ThemeDisplay themeDisplay = cpContentRequestHelper.getThemeDisplay();

		PortletPreferences portletPreferences =
			cpContentRequestHelper.getPortletPreferences();

		String queryLogicIndexesParam = ParamUtil.getString(
			cpContentRequestHelper.getRequest(), "queryLogicIndexes");

		int[] queryLogicIndexes = null;

		if (Validator.isNotNull(queryLogicIndexesParam)) {
			queryLogicIndexes = StringUtil.split(queryLogicIndexesParam, 0);
		}
		else {
			queryLogicIndexes = new int[0];

			for (int i = 0; true; i++) {
				String queryValues = PrefsParamUtil.getString(
					portletPreferences, cpContentRequestHelper.getRequest(),
					"queryValues" + i);

				if (Validator.isNull(queryValues)) {
					break;
				}

				queryLogicIndexes = ArrayUtil.append(queryLogicIndexes, i);
			}

			if (queryLogicIndexes.length == 0) {
				queryLogicIndexes = ArrayUtil.append(queryLogicIndexes, -1);
			}
		}

		JSONArray rulesJSONArray = JSONFactoryUtil.createJSONArray();

		for (int queryLogicIndex : queryLogicIndexes) {
			boolean queryAndOperator = PrefsParamUtil.getBoolean(
				portletPreferences, cpContentRequestHelper.getRequest(),
				"queryAndOperator" + queryLogicIndex);
			boolean queryContains = PrefsParamUtil.getBoolean(
				portletPreferences, cpContentRequestHelper.getRequest(),
				"queryContains" + queryLogicIndex, true);

			JSONObject ruleJSONObject = JSONUtil.put(
				"queryAndOperator", queryAndOperator
			).put(
				"queryContains", queryContains
			);

			String queryValues = StringUtil.merge(
				portletPreferences.getValues(
					"queryValues" + queryLogicIndex, new String[0]));

			String queryName = PrefsParamUtil.getString(
				portletPreferences, cpContentRequestHelper.getRequest(),
				"queryName" + queryLogicIndex, "assetTags");

			if (Objects.equals(queryName, "assetTags")) {
				queryValues = ParamUtil.getString(
					cpContentRequestHelper.getRequest(),
					"queryTagNames" + queryLogicIndex, queryValues);

				queryValues = filterAssetTagNames(
					themeDisplay.getScopeGroupId(), queryValues);
			}
			else {
				queryValues = ParamUtil.getString(
					cpContentRequestHelper.getRequest(),
					"queryCategoryIds" + queryLogicIndex, queryValues);

				JSONArray categoryIdsTitles = JSONFactoryUtil.createJSONArray();

				List<AssetCategory> categories = _filterAssetCategories(
					GetterUtil.getLongValues(StringUtil.split(queryValues)));

				for (AssetCategory category : categories) {
					categoryIdsTitles.put(
						category.getTitle(themeDisplay.getLocale()));
				}

				List<Long> categoryIds = ListUtil.toList(
					categories, AssetCategory.CATEGORY_ID_ACCESSOR);

				queryValues = StringUtil.merge(categoryIds);

				ruleJSONObject.put("categoryIdsTitles", categoryIdsTitles);
			}

			if (Validator.isNull(queryValues)) {
				continue;
			}

			ruleJSONObject.put("queryValues", queryValues);
			ruleJSONObject.put("type", queryName);

			rulesJSONArray.put(ruleJSONObject);
		}

		return rulesJSONArray;
	}

	public String getCategorySelectorURL() {
		try {
			PortletURL portletURL = PortletProviderUtil.getPortletURL(
				cpContentRequestHelper.getRequest(),
				AssetCategory.class.getName(), PortletProvider.Action.BROWSE);

			if (portletURL == null) {
				return null;
			}

			portletURL.setParameter(
				"eventName", _getPortletNamespace() + "selectCategory");
			portletURL.setParameter(
				"selectedCategories", "{selectedCategories}");
			portletURL.setParameter("singleSelect", "{singleSelect}");
			portletURL.setParameter("vocabularyIds", "{vocabularyIds}");

			portletURL.setWindowState(LiferayWindowState.POP_UP);

			return portletURL.toString();
		}
		catch (Exception e) {
		}

		return null;
	}

	public List<CPDataSource> getCPDataSources() {
		return _cpDataSourceRegistry.getCPDataSources();
	}

	public CPSku getDefaultCPSku(CPCatalogEntry cpCatalogEntry)
		throws Exception {

		return _cpInstanceHelper.getDefaultCPSku(cpCatalogEntry);
	}

	public String getItemSelectorUrl() throws Exception {
		RequestBackedPortletURLFactory requestBackedPortletURLFactory =
			RequestBackedPortletURLFactoryUtil.create(
				cpContentRequestHelper.getRenderRequest());

		CPDefinitionItemSelectorCriterion cpDefinitionItemSelectorCriterion =
			new CPDefinitionItemSelectorCriterion();

		cpDefinitionItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
			Collections.<ItemSelectorReturnType>singletonList(
				new UUIDItemSelectorReturnType()));

		PortletURL itemSelectorURL = _itemSelector.getItemSelectorURL(
			requestBackedPortletURLFactory, "productDefinitionsSelectItem",
			cpDefinitionItemSelectorCriterion);

		return itemSelectorURL.toString();
	}

	public String getOrderByColumn1() {
		if (_orderByColumn1 != null) {
			return _orderByColumn1;
		}

		PortletPreferences portletPreferences =
			cpContentRequestHelper.getPortletPreferences();

		_orderByColumn1 = GetterUtil.getString(
			portletPreferences.getValue("orderByColumn1", "modifiedDate"));

		return _orderByColumn1;
	}

	public String getOrderByColumn2() {
		if (_orderByColumn2 != null) {
			return _orderByColumn2;
		}

		PortletPreferences portletPreferences =
			cpContentRequestHelper.getPortletPreferences();

		_orderByColumn2 = GetterUtil.getString(
			portletPreferences.getValue("orderByColumn2", "title"));

		return _orderByColumn2;
	}

	public String getOrderByType1() {
		if (_orderByType1 != null) {
			return _orderByType1;
		}

		PortletPreferences portletPreferences =
			cpContentRequestHelper.getPortletPreferences();

		_orderByType1 = GetterUtil.getString(
			portletPreferences.getValue("orderByType1", "DESC"));

		return _orderByType1;
	}

	public String getOrderByType2() {
		if (_orderByType2 != null) {
			return _orderByType2;
		}

		PortletPreferences portletPreferences =
			cpContentRequestHelper.getPortletPreferences();

		_orderByType2 = GetterUtil.getString(
			portletPreferences.getValue("orderByType2", "ASC"));

		return _orderByType2;
	}

	public String getTagSelectorURL() {
		try {
			PortletURL portletURL = PortletProviderUtil.getPortletURL(
				cpContentRequestHelper.getRequest(), AssetTag.class.getName(),
				PortletProvider.Action.BROWSE);

			if (portletURL == null) {
				return null;
			}

			portletURL.setParameter(
				"eventName", _getPortletNamespace() + "selectTag");
			portletURL.setParameter(
				"groupIds",
				String.valueOf(cpContentRequestHelper.getScopeGroupId()));
			portletURL.setParameter("selectedTagNames", "{selectedTagNames}");
			portletURL.setWindowState(LiferayWindowState.POP_UP);

			return portletURL.toString();
		}
		catch (Exception e) {
		}

		return null;
	}

	public String getVocabularyIds() throws Exception {
		ThemeDisplay themeDisplay = cpContentRequestHelper.getThemeDisplay();

		List<AssetVocabulary> vocabularies =
			AssetVocabularyServiceUtil.getGroupVocabularies(
				themeDisplay.getCompanyGroupId());

		return ListUtil.toString(
			vocabularies, AssetVocabulary.VOCABULARY_ID_ACCESSOR);
	}

	private List<AssetCategory> _filterAssetCategories(long[] categoryIds) {
		List<AssetCategory> filteredCategories = new ArrayList<>();

		for (long categoryId : categoryIds) {
			AssetCategory category =
				_assetCategoryLocalService.fetchAssetCategory(categoryId);

			if (category == null) {
				continue;
			}

			filteredCategories.add(category);
		}

		return filteredCategories;
	}

	private String _getPortletNamespace() {
		LiferayPortletResponse liferayPortletResponse =
			cpContentRequestHelper.getLiferayPortletResponse();

		return liferayPortletResponse.getNamespace();
	}

	private final AssetCategoryLocalService _assetCategoryLocalService;
	private final AssetTagLocalService _assetTagLocalService;
	private final CPDataSourceRegistry _cpDataSourceRegistry;
	private final CPInstanceHelper _cpInstanceHelper;
	private final ItemSelector _itemSelector;
	private String _orderByColumn1;
	private String _orderByColumn2;
	private String _orderByType1;
	private String _orderByType2;

}