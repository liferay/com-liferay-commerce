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

package com.liferay.commerce.catalog.web.internal.display.context;

import com.liferay.commerce.catalog.web.display.context.BaseCommerceCatalogSearchContainerDisplayContext;
import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.CommerceCatalogService;
import com.liferay.commerce.product.util.CPUtil;
import com.liferay.item.selector.ItemSelector;
import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.criteria.UUIDItemSelectorReturnType;
import com.liferay.layout.item.selector.criterion.LayoutItemSelectorCriterion;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Collections;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alec Sloan
 */
public class CommerceCatalogDisplayContext
	extends BaseCommerceCatalogSearchContainerDisplayContext<CommerceCatalog> {

	public CommerceCatalogDisplayContext(
			HttpServletRequest httpServletRequest,
			CommerceCatalogService commerceCatalogService,
			ItemSelector itemSelector, Portal portal,
			PortletResourcePermission portletResourcePermission)
		throws PortalException {

		super(httpServletRequest, CommerceCatalog.class.getSimpleName());

		setDefaultOrderByType("desc");

		_httpServletRequest = httpServletRequest;
		_commerceCatalogService = commerceCatalogService;
		_itemSelector = itemSelector;
		_portal = portal;
		_portletResourcePermission = portletResourcePermission;
	}

	public String getCatalogURL(CommerceCatalog commerceCatalog)
		throws PortalException {

		if (commerceCatalog == null) {
			return StringPool.BLANK;
		}

		PortletURL portletURL = _portal.getControlPanelPortletURL(
			_httpServletRequest, CPPortletKeys.COMMERCE_CATALOGS,
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter("mvcRenderCommandName", "editCommerceCatalog");
		portletURL.setParameter(
			"commerceCatalogId",
			String.valueOf(commerceCatalog.getCommerceCatalogId()));

		return portletURL.toString();
	}

	public CommerceCatalog getCommerceCatalog() throws PortalException {
		long commerceCatalogId = ParamUtil.getLong(
			_httpServletRequest, "commerceCatalogId");

		return _commerceCatalogService.fetchCommerceCatalog(commerceCatalogId);
	}

	public String getItemSelectorUrl() {
		RequestBackedPortletURLFactory requestBackedPortletURLFactory =
			RequestBackedPortletURLFactoryUtil.create(
				cpRequestHelper.getRenderRequest());

		LayoutItemSelectorCriterion layoutItemSelectorCriterion =
			new LayoutItemSelectorCriterion();

		layoutItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
			Collections.<ItemSelectorReturnType>singletonList(
				new UUIDItemSelectorReturnType()));

		PortletURL itemSelectorURL = _itemSelector.getItemSelectorURL(
			requestBackedPortletURLFactory, "selectDisplayPage",
			layoutItemSelectorCriterion);

		return itemSelectorURL.toString();
	}

	@Override
	public PortletURL getPortletURL() throws PortalException {
		PortletURL portletURL = super.getPortletURL();

		String filterFields = ParamUtil.getString(
			httpServletRequest, "filterFields");

		if (Validator.isNotNull(filterFields)) {
			portletURL.setParameter("filterFields", filterFields);
		}

		String filtersLabels = ParamUtil.getString(
			httpServletRequest, "filtersLabels");

		if (Validator.isNotNull(filtersLabels)) {
			portletURL.setParameter("filtersLabels", filtersLabels);
		}

		String filtersValues = ParamUtil.getString(
			httpServletRequest, "filtersValues");

		if (Validator.isNotNull(filtersValues)) {
			portletURL.setParameter("filtersValues", filtersValues);
		}

		return portletURL;
	}

	@Override
	public SearchContainer<CommerceCatalog> getSearchContainer()
		throws PortalException {

		if (searchContainer != null) {
			return searchContainer;
		}

		searchContainer = new SearchContainer<>(
			liferayPortletRequest, getPortletURL(), null, null);

		searchContainer.setOrderByCol(getOrderByCol());
		searchContainer.setOrderByType(getOrderByType());
		searchContainer.setRowChecker(getRowChecker());

		ThemeDisplay themeDisplay =
			(ThemeDisplay)_httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		Sort sort = CPUtil.getCommerceCatalogSort(
			getOrderByCol(), getOrderByType());

		List<CommerceCatalog> catalogs =
			_commerceCatalogService.searchCommerceCatalogs(
				themeDisplay.getCompanyId(), getKeywords(),
				searchContainer.getStart(), searchContainer.getEnd(), sort);

		searchContainer.setTotal(catalogs.size());
		searchContainer.setResults(catalogs);

		return searchContainer;
	}

	private final CommerceCatalogService _commerceCatalogService;
	private final HttpServletRequest _httpServletRequest;
	private final ItemSelector _itemSelector;
	private final Portal _portal;
	private final PortletResourcePermission _portletResourcePermission;

}