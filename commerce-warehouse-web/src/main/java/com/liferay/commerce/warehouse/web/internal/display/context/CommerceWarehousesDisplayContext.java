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

package com.liferay.commerce.warehouse.web.internal.display.context;

import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseLocalService;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.product.display.context.util.CPRequestHelper;
import com.liferay.commerce.service.CommerceCountryService;
import com.liferay.commerce.util.CommerceUtil;
import com.liferay.commerce.warehouse.web.internal.admin.WarehousesCommerceAdminModule;
import com.liferay.frontend.taglib.servlet.taglib.ManagementBarFilterItem;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Andrea Di Giorgi
 */
public class CommerceWarehousesDisplayContext {

	public CommerceWarehousesDisplayContext(
		CommerceCountryService commerceCountryService,
		CommerceInventoryWarehouseLocalService commerceWarehouseLocalService,
		HttpServletRequest httpServletRequest) {

		_commerceCountryService = commerceCountryService;
		_commerceWarehouseLocalService = commerceWarehouseLocalService;

		_cpRequestHelper = new CPRequestHelper(httpServletRequest);
	}

	public long getCommerceCountryId() {
		return ParamUtil.getLong(
			_cpRequestHelper.getRenderRequest(), "commerceCountryId", -1);
	}

	public CommerceInventoryWarehouse getCommerceWarehouse()
		throws PortalException {

		if (_commerceWarehouse != null) {
			return _commerceWarehouse;
		}

		long commerceWarehouseId = ParamUtil.getLong(
			_cpRequestHelper.getRenderRequest(), "commerceWarehouseId");

		if (commerceWarehouseId > 0) {
			_commerceWarehouse =
				_commerceWarehouseLocalService.getCommerceInventoryWarehouse(
					commerceWarehouseId);
		}

		return _commerceWarehouse;
	}

	public List<ManagementBarFilterItem> getManagementBarFilterItems()
		throws PortalException, PortletException {

		List<CommerceCountry> commerceCountries =
			_commerceCountryService.getWarehouseCommerceCountries(
				_cpRequestHelper.getCompanyId(), true);

		List<ManagementBarFilterItem> managementBarFilterItems =
			new ArrayList<>(commerceCountries.size() + 2);

		managementBarFilterItems.add(getManagementBarFilterItem(-1, "all"));
		managementBarFilterItems.add(getManagementBarFilterItem(0, "none"));

		for (CommerceCountry commerceCountry : commerceCountries) {
			managementBarFilterItems.add(
				getManagementBarFilterItem(
					commerceCountry.getCommerceCountryId(),
					commerceCountry.getName(_cpRequestHelper.getLocale())));
		}

		return managementBarFilterItems;
	}

	public String getOrderByCol() {
		return ParamUtil.getString(
			_cpRequestHelper.getRenderRequest(),
			SearchContainer.DEFAULT_ORDER_BY_COL_PARAM, "name");
	}

	public String getOrderByType() {
		return ParamUtil.getString(
			_cpRequestHelper.getRenderRequest(),
			SearchContainer.DEFAULT_ORDER_BY_TYPE_PARAM, "asc");
	}

	public PortletURL getPortletURL() {
		RenderResponse renderResponse = _cpRequestHelper.getRenderResponse();

		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter(
			"commerceAdminModuleKey", WarehousesCommerceAdminModule.KEY);
		portletURL.setParameter(
			"commerceCountryId", String.valueOf(getCommerceCountryId()));

		String delta = ParamUtil.getString(
			_cpRequestHelper.getRenderRequest(), "delta");

		if (Validator.isNotNull(delta)) {
			portletURL.setParameter("delta", delta);
		}

		portletURL.setParameter("keywords", getKeywords());
		portletURL.setParameter("navigation", getNavigation());
		portletURL.setParameter("orderByCol", getOrderByCol());
		portletURL.setParameter("orderByType", getOrderByType());

		return portletURL;
	}

	public SearchContainer<CommerceInventoryWarehouse> getSearchContainer()
		throws PortalException {

		if (_searchContainer != null) {
			return _searchContainer;
		}

		Boolean active = null;
		long commerceCountryId = getCommerceCountryId();
		String emptyResultsMessage = "there-are-no-warehouses";
		boolean search = isSearch();

		String navigation = getNavigation();

		if (navigation.equals("active")) {
			active = Boolean.TRUE;
			emptyResultsMessage = "there-are-no-active-warehouses";
		}
		else if (navigation.equals("inactive")) {
			active = Boolean.FALSE;
			emptyResultsMessage = "there-are-no-inactive-warehouses";
		}

		if (search) {
			emptyResultsMessage = "no-warehouses-were-found";
		}

		CommerceCountry commerceCountry = null;

		if (commerceCountryId > 0) {
			emptyResultsMessage += "-in-x";

			commerceCountry = _commerceCountryService.getCommerceCountry(
				commerceCountryId);

			emptyResultsMessage = LanguageUtil.format(
				_cpRequestHelper.getRequest(), emptyResultsMessage,
				commerceCountry.getName(_cpRequestHelper.getLocale()), false);
		}

		_searchContainer = new SearchContainer<>(
			_cpRequestHelper.getRenderRequest(), getPortletURL(), null,
			emptyResultsMessage);

		if (!search && hasManageCommerceWarehousePermission()) {
			_searchContainer.setEmptyResultsMessageCssClass(
				"taglib-empty-result-message-header-has-plus-btn");
		}

		String orderByCol = getOrderByCol();
		String orderByType = getOrderByType();

		OrderByComparator<CommerceInventoryWarehouse> orderByComparator =
			CommerceUtil.getCommerceWarehouseOrderByComparator(
				orderByCol, orderByType);

		_searchContainer.setOrderByCol(orderByCol);
		_searchContainer.setOrderByComparator(orderByComparator);
		_searchContainer.setOrderByType(orderByType);
		_searchContainer.setSearch(search);

		List<CommerceInventoryWarehouse> results;

		if (_searchContainer.isSearch() && (commerceCountry != null)) {
			results = _commerceWarehouseLocalService.search(
				_cpRequestHelper.getCompanyId(),
				_cpRequestHelper.getScopeGroupId(), getKeywords(), active,
				commerceCountry.getTwoLettersISOCode(),
				_searchContainer.getStart(), _searchContainer.getEnd(),
				orderByComparator);
		}
		else {
			if (active == null) {
				results =
					_commerceWarehouseLocalService.
						getCommerceWarehousesByGroupId(
							_cpRequestHelper.getCompanyId(),
							_cpRequestHelper.getScopeGroupId());
			}
			else {
				results = _commerceWarehouseLocalService.getCommerceWarehouses(
					_cpRequestHelper.getCompanyId(),
					_cpRequestHelper.getScopeGroupId(), active,
					commerceCountry.getTwoLettersISOCode());
			}
		}

		_searchContainer.setTotal(results.size());
		_searchContainer.setResults(results);

		return _searchContainer;
	}

	public boolean hasManageCommerceWarehousePermission() {
		return true;
	}

	protected String getKeywords() {
		if (_keywords != null) {
			return _keywords;
		}

		_keywords = ParamUtil.getString(
			_cpRequestHelper.getRenderRequest(), "keywords");

		return _keywords;
	}

	protected ManagementBarFilterItem getManagementBarFilterItem(
			long commerceCountryId, String label)
		throws PortletException {

		boolean active = false;

		if (getCommerceCountryId() == commerceCountryId) {
			active = true;
		}

		PortletURL portletURL = PortletURLUtil.clone(
			getPortletURL(), _cpRequestHelper.getRenderResponse());

		portletURL.setParameter(
			"commerceCountryId", String.valueOf(commerceCountryId));

		return new ManagementBarFilterItem(
			active, String.valueOf(commerceCountryId), label,
			portletURL.toString());
	}

	protected String getNavigation() {
		return ParamUtil.getString(
			_cpRequestHelper.getRenderRequest(), "navigation");
	}

	protected boolean isSearch() {
		if (Validator.isNotNull(getKeywords())) {
			return true;
		}

		return false;
	}

	private final CommerceCountryService _commerceCountryService;
	private CommerceInventoryWarehouse _commerceWarehouse;
	private final CommerceInventoryWarehouseLocalService
		_commerceWarehouseLocalService;
	private final CPRequestHelper _cpRequestHelper;
	private String _keywords;
	private SearchContainer<CommerceInventoryWarehouse> _searchContainer;

}