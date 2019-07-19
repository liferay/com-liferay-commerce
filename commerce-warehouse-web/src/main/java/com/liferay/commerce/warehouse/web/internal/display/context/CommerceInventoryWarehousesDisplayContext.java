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
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseService;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.model.CommerceChannelRel;
import com.liferay.commerce.product.service.CommerceChannelRelService;
import com.liferay.commerce.product.service.CommerceChannelService;
import com.liferay.commerce.service.CommerceCountryService;
import com.liferay.commerce.util.CommerceUtil;
import com.liferay.commerce.warehouse.web.internal.admin.WarehousesCommerceAdminModule;
import com.liferay.commerce.warehouse.web.internal.display.context.util.CommerceInventoryRequestHelper;
import com.liferay.frontend.taglib.servlet.taglib.ManagementBarFilterItem;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Andrea Di Giorgi
 * @author Alessio Antonio Rendina
 */
public class CommerceInventoryWarehousesDisplayContext {

	public CommerceInventoryWarehousesDisplayContext(
		CommerceChannelRelService commerceChannelRelService,
		CommerceChannelService commerceChannelService,
		CommerceCountryService commerceCountryService,
		CommerceInventoryWarehouseService commerceInventoryWarehouseService,
		HttpServletRequest httpServletRequest) {

		_commerceChannelRelService = commerceChannelRelService;
		_commerceChannelService = commerceChannelService;
		_commerceCountryService = commerceCountryService;
		_commerceInventoryWarehouseService = commerceInventoryWarehouseService;

		_commerceInventoryRequestHelper = new CommerceInventoryRequestHelper(
			httpServletRequest);
	}

	public long[] getCommerceChannelRelCommerceChannelIds()
		throws PortalException {

		CommerceInventoryWarehouse commerceInventoryWarehouse =
			getCommerceInventoryWarehouse();

		if (commerceInventoryWarehouse == null) {
			return new long[0];
		}

		List<CommerceChannelRel> commerceChannelRels =
			_commerceChannelRelService.getCommerceChannelRels(
				CommerceInventoryWarehouse.class.getName(),
				commerceInventoryWarehouse.getCommerceInventoryWarehouseId(),
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		Stream<CommerceChannelRel> stream = commerceChannelRels.stream();

		return stream.mapToLong(
			CommerceChannelRel::getCommerceChannelId
		).toArray();
	}

	public List<CommerceChannel> getCommerceChannels() throws PortalException {
		return _commerceChannelService.getCommerceChannels(
			_commerceInventoryRequestHelper.getCompanyId());
	}

	public CommerceCountry getCommerceCountry(
			String commerceCountryTwoLettersIsoCode)
		throws PortalException {

		return _commerceCountryService.getCommerceCountry(
			_commerceInventoryRequestHelper.getCompanyId(),
			commerceCountryTwoLettersIsoCode);
	}

	public String getCommerceCountryTwoLettersIsoCode() {
		return ParamUtil.getString(
			_commerceInventoryRequestHelper.getRenderRequest(),
			"countryTwoLettersISOCode");
	}

	public CommerceInventoryWarehouse getCommerceInventoryWarehouse()
		throws PortalException {

		if (_commerceInventoryWarehouse != null) {
			return _commerceInventoryWarehouse;
		}

		long commerceInventoryWarehouseId = ParamUtil.getLong(
			_commerceInventoryRequestHelper.getRenderRequest(),
			"commerceInventoryWarehouseId");

		if (commerceInventoryWarehouseId > 0) {
			_commerceInventoryWarehouse =
				_commerceInventoryWarehouseService.
					getCommerceInventoryWarehouse(commerceInventoryWarehouseId);
		}

		return _commerceInventoryWarehouse;
	}

	public List<ManagementBarFilterItem> getManagementBarFilterItems()
		throws PortalException, PortletException {

		List<CommerceCountry> commerceCountries =
			_commerceCountryService.getWarehouseCommerceCountries(
				_commerceInventoryRequestHelper.getCompanyId(), true);

		List<ManagementBarFilterItem> managementBarFilterItems =
			new ArrayList<>(commerceCountries.size() + 2);

		managementBarFilterItems.add(getManagementBarFilterItem(-1, "all"));
		managementBarFilterItems.add(getManagementBarFilterItem(0, "none"));

		for (CommerceCountry commerceCountry : commerceCountries) {
			managementBarFilterItems.add(
				getManagementBarFilterItem(
					commerceCountry.getCommerceCountryId(),
					commerceCountry.getName(
						_commerceInventoryRequestHelper.getLocale())));
		}

		return managementBarFilterItems;
	}

	public String getOrderByCol() {
		return ParamUtil.getString(
			_commerceInventoryRequestHelper.getRenderRequest(),
			SearchContainer.DEFAULT_ORDER_BY_COL_PARAM, "name");
	}

	public String getOrderByType() {
		return ParamUtil.getString(
			_commerceInventoryRequestHelper.getRenderRequest(),
			SearchContainer.DEFAULT_ORDER_BY_TYPE_PARAM, "asc");
	}

	public PortletURL getPortletURL() {
		RenderResponse renderResponse =
			_commerceInventoryRequestHelper.getRenderResponse();

		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter(
			"commerceAdminModuleKey", WarehousesCommerceAdminModule.KEY);
		portletURL.setParameter(
			"countryTwoLettersISOCode", getCommerceCountryTwoLettersIsoCode());

		String delta = ParamUtil.getString(
			_commerceInventoryRequestHelper.getRenderRequest(), "delta");

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
		String commerceCountryTwoLettersIsoCode =
			getCommerceCountryTwoLettersIsoCode();
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

		if (Validator.isNotNull(commerceCountryTwoLettersIsoCode)) {
			emptyResultsMessage += "-in-x";

			commerceCountry = getCommerceCountry(
				commerceCountryTwoLettersIsoCode);

			emptyResultsMessage = LanguageUtil.format(
				_commerceInventoryRequestHelper.getRequest(),
				emptyResultsMessage,
				commerceCountry.getName(
					_commerceInventoryRequestHelper.getLocale()),
				false);
		}

		_searchContainer = new SearchContainer<>(
			_commerceInventoryRequestHelper.getRenderRequest(), getPortletURL(),
			null, emptyResultsMessage);

		if (!search && hasManageCommerceInventoryWarehousePermission()) {
			_searchContainer.setEmptyResultsMessageCssClass(
				"taglib-empty-result-message-header-has-plus-btn");
		}

		String orderByCol = getOrderByCol();
		String orderByType = getOrderByType();

		OrderByComparator<CommerceInventoryWarehouse> orderByComparator =
			CommerceUtil.getCommerceInventoryWarehouseOrderByComparator(
				orderByCol, orderByType);

		_searchContainer.setOrderByCol(orderByCol);
		_searchContainer.setOrderByComparator(orderByComparator);
		_searchContainer.setOrderByType(orderByType);
		_searchContainer.setSearch(search);

		List<CommerceInventoryWarehouse> results;
		int total;

		if (_searchContainer.isSearch() && (commerceCountry != null)) {
			results =
				_commerceInventoryWarehouseService.
					searchCommerceInventoryWarehouses(
						_commerceInventoryRequestHelper.getCompanyId(), active,
						commerceCountry.getTwoLettersISOCode(), getKeywords(),
						_searchContainer.getStart(), _searchContainer.getEnd(),
						CommerceUtil.getCommerceInventoryWarehouseSort(
							orderByCol, orderByType));

			total =
				_commerceInventoryWarehouseService.
					searchCommerceInventoryWarehousesCount(
						_commerceInventoryRequestHelper.getCompanyId(), active,
						commerceCountryTwoLettersIsoCode, getKeywords());
		}
		else {
			if (active == null) {
				results =
					_commerceInventoryWarehouseService.
						getCommerceInventoryWarehouses(
							_commerceInventoryRequestHelper.getCompanyId(),
							_searchContainer.getStart(),
							_searchContainer.getEnd(), orderByComparator);

				total =
					_commerceInventoryWarehouseService.
						getCommerceInventoryWarehousesCount(
							_commerceInventoryRequestHelper.getCompanyId());
			}
			else {
				results =
					_commerceInventoryWarehouseService.
						getCommerceInventoryWarehouses(
							_commerceInventoryRequestHelper.getCompanyId(),
							active, commerceCountry.getTwoLettersISOCode(),
							_searchContainer.getStart(),
							_searchContainer.getEnd(), orderByComparator);

				total =
					_commerceInventoryWarehouseService.
						getCommerceInventoryWarehousesCount(
							_commerceInventoryRequestHelper.getCompanyId(),
							active, commerceCountry.getTwoLettersISOCode());
			}
		}

		_searchContainer.setResults(results);
		_searchContainer.setTotal(total);

		return _searchContainer;
	}

	public boolean hasManageCommerceInventoryWarehousePermission() {
		return true;
	}

	protected String getKeywords() {
		if (_keywords != null) {
			return _keywords;
		}

		_keywords = ParamUtil.getString(
			_commerceInventoryRequestHelper.getRenderRequest(), "keywords");

		return _keywords;
	}

	protected ManagementBarFilterItem getManagementBarFilterItem(
			long commerceCountryId, String label)
		throws PortalException, PortletException {

		boolean active = false;

		PortletURL portletURL = PortletURLUtil.clone(
			getPortletURL(),
			_commerceInventoryRequestHelper.getRenderResponse());

		String commerceCountryTwoLettersIsoCode =
			getCommerceCountryTwoLettersIsoCode();

		if ((commerceCountryId > 0) &&
			Validator.isNotNull(commerceCountryTwoLettersIsoCode)) {

			CommerceCountry commerceCountry = getCommerceCountry(
				commerceCountryTwoLettersIsoCode);

			if (commerceCountry.getCommerceCountryId() == commerceCountryId) {
				active = true;
			}

			portletURL.setParameter(
				"countryTwoLettersISOCode",
				commerceCountry.getTwoLettersISOCode());
		}

		return new ManagementBarFilterItem(
			active, String.valueOf(commerceCountryId), label,
			portletURL.toString());
	}

	protected String getNavigation() {
		return ParamUtil.getString(
			_commerceInventoryRequestHelper.getRenderRequest(), "navigation");
	}

	protected boolean isSearch() {
		if (Validator.isNotNull(getKeywords())) {
			return true;
		}

		return false;
	}

	private final CommerceChannelRelService _commerceChannelRelService;
	private final CommerceChannelService _commerceChannelService;
	private final CommerceCountryService _commerceCountryService;
	private final CommerceInventoryRequestHelper
		_commerceInventoryRequestHelper;
	private CommerceInventoryWarehouse _commerceInventoryWarehouse;
	private final CommerceInventoryWarehouseService
		_commerceInventoryWarehouseService;
	private String _keywords;
	private SearchContainer<CommerceInventoryWarehouse> _searchContainer;

}