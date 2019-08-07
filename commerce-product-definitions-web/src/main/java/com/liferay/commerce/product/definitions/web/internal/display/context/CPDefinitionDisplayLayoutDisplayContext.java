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

package com.liferay.commerce.product.definitions.web.internal.display.context;

import com.liferay.commerce.product.definitions.web.display.context.BaseCPDefinitionsSearchContainerDisplayContext;
import com.liferay.commerce.product.definitions.web.internal.admin.ProductDisplayLayoutsCommerceAdminModule;
import com.liferay.commerce.product.definitions.web.portlet.action.ActionHelper;
import com.liferay.commerce.product.item.selector.criterion.CPDefinitionItemSelectorCriterion;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPDisplayLayout;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.CPDefinitionService;
import com.liferay.commerce.product.service.CPDisplayLayoutService;
import com.liferay.commerce.product.service.CommerceCatalogService;
import com.liferay.item.selector.ItemSelector;
import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.criteria.UUIDItemSelectorReturnType;
import com.liferay.layout.item.selector.criterion.LayoutItemSelectorCriterion;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Stream;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class CPDefinitionDisplayLayoutDisplayContext
	extends BaseCPDefinitionsSearchContainerDisplayContext<CPDisplayLayout> {

	public CPDefinitionDisplayLayoutDisplayContext(
		ActionHelper actionHelper, HttpServletRequest httpServletRequest,
		CommerceCatalogService commerceCatalogService,
		CPDefinitionService cpDefinitionService,
		CPDisplayLayoutService cpDisplayLayoutService,
		ItemSelector itemSelector) {

		super(
			actionHelper, httpServletRequest,
			CPDisplayLayout.class.getSimpleName());

		_commerceCatalogService = commerceCatalogService;
		_cpDefinitionService = cpDefinitionService;
		_cpDisplayLayoutService = cpDisplayLayoutService;
		_itemSelector = itemSelector;
	}

	public CPDisplayLayout getCPDisplayLayout() throws PortalException {
		if (_cpDisplayLayout != null) {
			return _cpDisplayLayout;
		}

		long cpDisplayLayoutId = ParamUtil.getLong(
			cpRequestHelper.getRequest(), "cpDisplayLayoutId");

		_cpDisplayLayout = _cpDisplayLayoutService.fetchCPDisplayLayout(
			cpDisplayLayoutId);

		return _cpDisplayLayout;
	}

	public String getDisplayPageItemSelectorUrl() {
		RequestBackedPortletURLFactory requestBackedPortletURLFactory =
			RequestBackedPortletURLFactoryUtil.create(
				cpRequestHelper.getRenderRequest());

		LayoutItemSelectorCriterion layoutItemSelectorCriterion =
			new LayoutItemSelectorCriterion();

		layoutItemSelectorCriterion.setShowHiddenPages(true);

		layoutItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
			Collections.<ItemSelectorReturnType>singletonList(
				new UUIDItemSelectorReturnType()));

		PortletURL itemSelectorURL = _itemSelector.getItemSelectorURL(
			requestBackedPortletURLFactory, "selectDisplayPage",
			layoutItemSelectorCriterion);

		return itemSelectorURL.toString();
	}

	public String getLayoutBreadcrumb(Layout layout) throws Exception {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		Locale locale = themeDisplay.getLocale();

		List<Layout> ancestors = layout.getAncestors();

		StringBundler sb = new StringBundler(4 * ancestors.size() + 5);

		if (layout.isPrivateLayout()) {
			sb.append(LanguageUtil.get(httpServletRequest, "private-pages"));
		}
		else {
			sb.append(LanguageUtil.get(httpServletRequest, "public-pages"));
		}

		sb.append(StringPool.SPACE);
		sb.append(StringPool.GREATER_THAN);
		sb.append(StringPool.SPACE);

		Collections.reverse(ancestors);

		for (Layout ancestor : ancestors) {
			sb.append(HtmlUtil.escape(ancestor.getName(locale)));
			sb.append(StringPool.SPACE);
			sb.append(StringPool.GREATER_THAN);
			sb.append(StringPool.SPACE);
		}

		sb.append(HtmlUtil.escape(layout.getName(locale)));

		return sb.toString();
	}

	public String getLayoutUuid() throws PortalException {
		long cpDefinitionId = getCPDefinitionId();

		if (cpDefinitionId <= 0) {
			return null;
		}

		return _cpDefinitionService.getLayoutUuid(cpDefinitionId);
	}

	@Override
	public PortletURL getPortletURL() throws PortalException {
		PortletURL portletURL = super.getPortletURL();

		String commerceAdminModuleKey = ParamUtil.getString(
			cpRequestHelper.getRequest(), "commerceAdminModuleKey",
			ProductDisplayLayoutsCommerceAdminModule.KEY);

		portletURL.setParameter(
			"commerceAdminModuleKey", commerceAdminModuleKey);

		return portletURL;
	}

	public String getProductItemSelectorUrl() {
		RequestBackedPortletURLFactory requestBackedPortletURLFactory =
			RequestBackedPortletURLFactoryUtil.create(
				cpRequestHelper.getRenderRequest());

		CPDefinitionItemSelectorCriterion cpDefinitionItemSelectorCriterion =
			new CPDefinitionItemSelectorCriterion();

		cpDefinitionItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
			Collections.<ItemSelectorReturnType>singletonList(
				new UUIDItemSelectorReturnType()));

		PortletURL itemSelectorURL = _itemSelector.getItemSelectorURL(
			requestBackedPortletURLFactory, "productDefinitionsSelectItem",
			cpDefinitionItemSelectorCriterion);

		itemSelectorURL.setParameter("singleSelection", Boolean.toString(true));

		return itemSelectorURL.toString();
	}

	@Override
	public SearchContainer<CPDisplayLayout> getSearchContainer()
		throws PortalException {

		if (searchContainer != null) {
			return searchContainer;
		}

		searchContainer = new SearchContainer<>(
			liferayPortletRequest, getPortletURL(), null, null);

		searchContainer.setOrderByCol(getOrderByCol());
		searchContainer.setOrderByType(getOrderByType());
		searchContainer.setRowChecker(getRowChecker());

		Indexer<CPDisplayLayout> indexer =
			IndexerRegistryUtil.nullSafeGetIndexer(CPDisplayLayout.class);

		searchContainer.setTotal(
			(int)indexer.searchCount(
				buildSearchContext(QueryUtil.ALL_POS, QueryUtil.ALL_POS)));

		Hits hits = indexer.search(
			buildSearchContext(
				searchContainer.getStart(), searchContainer.getEnd()));

		searchContainer.setResults(getCPDisplayLayouts(indexer, hits));

		return searchContainer;
	}

	protected SearchContext buildSearchContext(int start, int end) {
		SearchContext searchContext = new SearchContext();

		Map<String, Serializable> attributes = new HashMap<>();

		attributes.put("commerceCatalogGroupId", _getCatalogGroupIds());
		attributes.put("entryModelClassName", CPDefinition.class.getName());
		attributes.put("searchFilterEnabled", true);

		searchContext.setAttributes(attributes);

		searchContext.setCompanyId(cpRequestHelper.getCompanyId());
		searchContext.setGroupIds(
			new long[] {cpRequestHelper.getScopeGroupId()});
		searchContext.setStart(start);
		searchContext.setEnd(end);

		QueryConfig queryConfig = searchContext.getQueryConfig();

		queryConfig.setHighlightEnabled(false);
		queryConfig.setScoreEnabled(false);

		return searchContext;
	}

	protected List<CPDisplayLayout> getCPDisplayLayouts(
			Indexer<CPDisplayLayout> indexer, Hits hits)
		throws PortalException {

		List<Document> documents = hits.toList();

		List<CPDisplayLayout> cpDisplayLayouts = new ArrayList<>(
			documents.size());

		for (Document document : documents) {
			long cpDisplayLayoutId = GetterUtil.getLong(
				document.get(Field.ENTRY_CLASS_PK));

			CPDisplayLayout cpDisplayLayout =
				_cpDisplayLayoutService.fetchCPDisplayLayout(cpDisplayLayoutId);

			if (cpDisplayLayout == null) {
				cpDisplayLayouts = null;

				long companyId = GetterUtil.getLong(
					document.get(Field.COMPANY_ID));

				indexer.delete(companyId, document.getUID());
			}
			else if (cpDisplayLayouts != null) {
				cpDisplayLayouts.add(cpDisplayLayout);
			}
		}

		return cpDisplayLayouts;
	}

	private long[] _getCatalogGroupIds() {
		List<CommerceCatalog> commerceCatalogs =
			_commerceCatalogService.getCommerceCatalogs(
				cpRequestHelper.getCompanyId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		Stream<CommerceCatalog> stream = commerceCatalogs.stream();

		return stream.mapToLong(
			CommerceCatalog::getGroupId
		).toArray();
	}

	private final CommerceCatalogService _commerceCatalogService;
	private final CPDefinitionService _cpDefinitionService;
	private CPDisplayLayout _cpDisplayLayout;
	private final CPDisplayLayoutService _cpDisplayLayoutService;
	private final ItemSelector _itemSelector;

}