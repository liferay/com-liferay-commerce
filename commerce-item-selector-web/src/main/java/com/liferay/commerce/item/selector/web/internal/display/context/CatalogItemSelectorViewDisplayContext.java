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

package com.liferay.commerce.item.selector.web.internal.display.context;

import com.liferay.commerce.item.selector.web.internal.search.CatalogChecker;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.CommerceCatalogService;
import com.liferay.commerce.product.util.CPUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alec Sloan
 */
public class CatalogItemSelectorViewDisplayContext
	extends BaseCommerceItemSelectorViewDisplayContext<CommerceCatalog> {

	public CatalogItemSelectorViewDisplayContext(
		CommerceCatalogService commerceCatalogService,
		HttpServletRequest httpServletRequest, PortletURL portletURL,
		String itemSelectedEventName, boolean search) {

		super(httpServletRequest, portletURL, itemSelectedEventName);

		_commerceCatalogService = commerceCatalogService;
		_search = search;
	}

	public long getCompanyId() {
		return ParamUtil.getLong(
			cpRequestHelper.getRenderRequest(), "companyId", -1);
	}

	public PortletURL getPortletURL() {
		PortletURL portletURL = super.getPortletURL();

		portletURL.setParameter("companyId", String.valueOf(getCompanyId()));

		return portletURL;
	}

	public SearchContainer<CommerceCatalog> getSearchContainer()
		throws PortalException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		if (searchContainer != null) {
			return searchContainer;
		}

		String emptyResultsMessage = "no-catalogs-were-found";

		searchContainer = new SearchContainer<>(
			cpRequestHelper.getRenderRequest(), getPortletURL(), null,
			emptyResultsMessage);

		String orderByCol = getOrderByCol();

		String orderByType = getOrderByType();

		searchContainer.setOrderByCol(orderByCol);
		searchContainer.setOrderByType(orderByType);
		searchContainer.setRowChecker(
			new CatalogChecker(
				cpRequestHelper.getRenderResponse(), getCheckedCatalogIds(),
				getDisabledCatalogIds()));
		searchContainer.setSearch(_search);

		long companyId = themeDisplay.getCompanyId();

		Sort sort = CPUtil.getCommerceCatalogSort(
			getOrderByCol(), getOrderByType());

		List<CommerceCatalog> commerceCatalogs =
			_commerceCatalogService.searchCommerceCatalogs(
				companyId, getKeywords(), searchContainer.getStart(),
				searchContainer.getEnd(), sort);

		searchContainer.setResults(commerceCatalogs);

		int commerceCatalogsCount =
			_commerceCatalogService.searchCommerceCatalogsCount(
				companyId, getKeywords());

		searchContainer.setTotal(commerceCatalogsCount);

		return searchContainer;
	}

	protected long[] getCheckedCatalogIds() {
		return ParamUtil.getLongValues(
			cpRequestHelper.getRenderRequest(), "checkedCatalogIds");
	}

	protected long[] getDisabledCatalogIds() {
		return ParamUtil.getLongValues(
			cpRequestHelper.getRenderRequest(), "disabledCatalogIds");
	}

	private final CommerceCatalogService _commerceCatalogService;
	private final boolean _search;

}