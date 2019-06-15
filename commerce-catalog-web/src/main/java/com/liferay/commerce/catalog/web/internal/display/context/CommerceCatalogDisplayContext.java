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

import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.service.CommerceCurrencyService;
import com.liferay.commerce.product.constants.CPActionKeys;
import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.CommerceCatalogService;
import com.liferay.commerce.product.util.CPUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.service.permission.PortalPermissionUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alec Sloan
 * @author Alessio Antonio Rendina
 */
public class CommerceCatalogDisplayContext
	extends BaseCommerceCatalogSearchContainerDisplayContext<CommerceCatalog> {

	public CommerceCatalogDisplayContext(
			HttpServletRequest httpServletRequest,
			CommerceCatalogService commerceCatalogService,
			ModelResourcePermission<CommerceCatalog>
				commerceCatalogModelResourcePermission,
			CommerceCurrencyService commerceCurrencyService, Portal portal)
		throws PortalException {

		super(httpServletRequest, CommerceCatalog.class.getSimpleName());

		setDefaultOrderByType("desc");

		_commerceCatalogService = commerceCatalogService;
		_commerceCatalogModelResourcePermission =
			commerceCatalogModelResourcePermission;
		_commerceCurrencyService = commerceCurrencyService;
		_portal = portal;
	}

	public String getCatalogURL(CommerceCatalog commerceCatalog)
		throws PortalException {

		if (commerceCatalog == null) {
			return StringPool.BLANK;
		}

		PortletURL portletURL = _portal.getControlPanelPortletURL(
			httpServletRequest, CPPortletKeys.COMMERCE_CATALOGS,
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter("mvcRenderCommandName", "editCommerceCatalog");
		portletURL.setParameter(
			"commerceCatalogId",
			String.valueOf(commerceCatalog.getCommerceCatalogId()));

		String backURL = _portal.getCurrentURL(httpServletRequest);

		portletURL.setParameter("backURL", backURL);

		return portletURL.toString();
	}

	public CommerceCatalog getCommerceCatalog() throws PortalException {
		long commerceCatalogId = ParamUtil.getLong(
			httpServletRequest, "commerceCatalogId");

		if (commerceCatalogId == 0) {
			return null;
		}

		return _commerceCatalogService.fetchCommerceCatalog(commerceCatalogId);
	}

	public List<CommerceCurrency> getCommerceCurrencies()
		throws PortalException {

		return _commerceCurrencyService.getCommerceCurrencies(
			cpRequestHelper.getCompanyId(), true, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
			(ThemeDisplay)httpServletRequest.getAttribute(
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

	public boolean hasAddCatalogPermission() {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		return PortalPermissionUtil.contains(
			themeDisplay.getPermissionChecker(),
			CPActionKeys.ADD_COMMERCE_CATALOG);
	}

	public boolean hasPermission(long commerceCatalogId, String actionId)
		throws PortalException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		return _commerceCatalogModelResourcePermission.contains(
			themeDisplay.getPermissionChecker(), commerceCatalogId, actionId);
	}

	private final ModelResourcePermission<CommerceCatalog>
		_commerceCatalogModelResourcePermission;
	private final CommerceCatalogService _commerceCatalogService;
	private final CommerceCurrencyService _commerceCurrencyService;
	private final Portal _portal;

}