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

import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseLocalService;
import com.liferay.commerce.item.selector.web.internal.search.CommerceWarehouseChecker;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.service.CommerceCountryService;
import com.liferay.commerce.util.CommerceUtil;
import com.liferay.frontend.taglib.servlet.taglib.ManagementBarFilterItem;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Andrea Di Giorgi
 * @author Alessio Antonio Rendina
 */
public class CommerceWarehouseItemSelectorViewDisplayContext
	extends BaseCommerceItemSelectorViewDisplayContext
		<CommerceInventoryWarehouse> {

	public CommerceWarehouseItemSelectorViewDisplayContext(
		CommerceCountryService commerceCountryService,
		CommerceInventoryWarehouseLocalService commerceWarehouseLocalService,
		HttpServletRequest httpServletRequest, PortletURL portletURL,
		String itemSelectedEventName, boolean search) {

		super(httpServletRequest, portletURL, itemSelectedEventName);

		_commerceCountryService = commerceCountryService;
		_commerceWarehouseLocalService = commerceWarehouseLocalService;
		_search = search;
	}

	public long getCommerceCountryId() {
		return ParamUtil.getLong(
			cpRequestHelper.getRenderRequest(), "commerceCountryId", -1);
	}

	public List<ManagementBarFilterItem> getManagementBarFilterItems()
		throws PortalException, PortletException {

		List<CommerceCountry> commerceCountries =
			_commerceCountryService.getWarehouseCommerceCountries(
				cpRequestHelper.getCompanyId(), false);

		List<ManagementBarFilterItem> managementBarFilterItems =
			new ArrayList<>(commerceCountries.size() + 2);

		managementBarFilterItems.add(getManagementBarFilterItem(-1, "all"));
		managementBarFilterItems.add(getManagementBarFilterItem(0, "none"));

		for (CommerceCountry commerceCountry : commerceCountries) {
			managementBarFilterItems.add(
				getManagementBarFilterItem(
					commerceCountry.getCommerceCountryId(),
					commerceCountry.getName(cpRequestHelper.getLocale())));
		}

		return managementBarFilterItems;
	}

	public PortletURL getPortletURL() {
		PortletURL portletURL = super.getPortletURL();

		portletURL.setParameter(
			"commerceCountryId", String.valueOf(getCommerceCountryId()));

		return portletURL;
	}

	public SearchContainer<CommerceInventoryWarehouse> getSearchContainer()
		throws PortalException {

		if (searchContainer != null) {
			return searchContainer;
		}

		long commerceCountryId = getCommerceCountryId();

		String emptyResultsMessage = "there-are-no-warehouses";

		if (_search) {
			emptyResultsMessage = "no-warehouses-were-found";
		}

		CommerceCountry commerceCountry = null;

		if (commerceCountryId > 0) {
			emptyResultsMessage += "-in-x";

			commerceCountry = _commerceCountryService.getCommerceCountry(
				commerceCountryId);

			Locale locale = cpRequestHelper.getLocale();

			ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
				"content.Language", locale, getClass());

			emptyResultsMessage = LanguageUtil.format(
				resourceBundle, emptyResultsMessage,
				commerceCountry.getName(locale), false);
		}

		searchContainer = new SearchContainer<>(
			cpRequestHelper.getRenderRequest(), getPortletURL(), null,
			emptyResultsMessage);

		String orderByCol = getOrderByCol();
		String orderByType = getOrderByType();

		OrderByComparator<CommerceInventoryWarehouse> orderByComparator =
			CommerceUtil.getCommerceWarehouseOrderByComparator(
				orderByCol, orderByType);

		searchContainer.setOrderByCol(orderByCol);
		searchContainer.setOrderByComparator(orderByComparator);
		searchContainer.setOrderByType(orderByType);
		searchContainer.setRowChecker(
			new CommerceWarehouseChecker(
				cpRequestHelper.getRenderResponse(),
				getCheckedCommerceWarehouseIds(),
				getDisabledCommerceWarehouseIds()));
		searchContainer.setSearch(_search);

		int total = 0;
		List<CommerceInventoryWarehouse> results = Collections.emptyList();

		if (searchContainer.isSearch() && (commerceCountry != null)) {
			total = _commerceWarehouseLocalService.searchCount(
				cpRequestHelper.getCompanyId(),
				cpRequestHelper.getScopeGroupId(), getKeywords(), false,
				commerceCountry.getTwoLettersISOCode());
			results = _commerceWarehouseLocalService.search(
				cpRequestHelper.getCompanyId(),
				cpRequestHelper.getScopeGroupId(), getKeywords(), false,
				commerceCountry.getTwoLettersISOCode(),
				searchContainer.getStart(), searchContainer.getEnd(),
				searchContainer.getOrderByComparator());
		}
		else if (commerceCountry != null) {
			total = _commerceWarehouseLocalService.getCommerceWarehousesCount(
				cpRequestHelper.getCompanyId(),
				cpRequestHelper.getScopeGroupId(), true,
				commerceCountry.getTwoLettersISOCode());
			results = _commerceWarehouseLocalService.getCommerceWarehouses(
				cpRequestHelper.getCompanyId(),
				cpRequestHelper.getScopeGroupId(), true,
				commerceCountry.getTwoLettersISOCode());
		}

		searchContainer.setTotal(total);
		searchContainer.setResults(results);

		return searchContainer;
	}

	protected long[] getCheckedCommerceWarehouseIds() {
		return ParamUtil.getLongValues(
			cpRequestHelper.getRenderRequest(), "checkedCommerceWarehouseIds");
	}

	protected long[] getDisabledCommerceWarehouseIds() {
		return ParamUtil.getLongValues(
			cpRequestHelper.getRenderRequest(), "disabledCommerceWarehouseIds");
	}

	protected ManagementBarFilterItem getManagementBarFilterItem(
			long commerceCountryId, String label)
		throws PortletException {

		boolean active = false;

		if (getCommerceCountryId() == commerceCountryId) {
			active = true;
		}

		PortletURL portletURL = PortletURLUtil.clone(
			getPortletURL(), cpRequestHelper.getRenderResponse());

		portletURL.setParameter(
			"commerceCountryId", String.valueOf(commerceCountryId));

		return new ManagementBarFilterItem(
			active, String.valueOf(commerceCountryId), label,
			portletURL.toString());
	}

	private final CommerceCountryService _commerceCountryService;
	private final CommerceInventoryWarehouseLocalService
		_commerceWarehouseLocalService;
	private final boolean _search;

}