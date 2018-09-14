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

package com.liferay.commerce.product.subscription.web.internal.display.context;

import com.liferay.commerce.product.constants.CPActionKeys;
import com.liferay.commerce.product.display.context.util.CPRequestHelper;
import com.liferay.commerce.product.model.CPSubscriptionEntry;
import com.liferay.commerce.product.service.CPSubscriptionEntryService;
import com.liferay.commerce.product.subscription.web.subscription.util.CPSubscriptionEntryPortletUtil;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Luca Pellizzon
 */
public class CPSubscriptionEntryDisplayContext {

	public CPSubscriptionEntryDisplayContext(
		CPSubscriptionEntryService cpSubscriptionEntryService,
		HttpServletRequest httpServletRequest,
		PortletResourcePermission portletResourcePermission) {

		_httpServletRequest = httpServletRequest;

		_cpRequestHelper = new CPRequestHelper(_httpServletRequest);

		_cpSubscriptionEntryService = cpSubscriptionEntryService;

		_portletResourcePermission = portletResourcePermission;

		_portalPreferences = PortletPreferencesFactoryUtil.getPortalPreferences(
			_httpServletRequest);

		_portalPreferenceNamespace = CPSubscriptionEntry.class.getName();

		_rowChecker = getRowChecker();
	}

	public CPSubscriptionEntry getCpSubscriptionEntry() throws PortalException {
		if (_cpSubscriptionEntry != null) {
			return _cpSubscriptionEntry;
		}

		long cpSubscriptionEntryId = ParamUtil.getLong(
			_httpServletRequest, "cpSubscriptionEntryId");

		if (cpSubscriptionEntryId > 0) {
			_cpSubscriptionEntry =
				_cpSubscriptionEntryService.fetchCPSubscriptionEntry(
					cpSubscriptionEntryId);
		}

		if (_cpSubscriptionEntry != null) {
			return _cpSubscriptionEntry;
		}

		return null;
	}

	public long getCPSubscriptionEntryId() throws PortalException {
		CPSubscriptionEntry cpSubscriptionEntry = getCpSubscriptionEntry();

		if (cpSubscriptionEntry != null) {
			return cpSubscriptionEntry.getCPSubscriptionEntryId();
		}

		return 0;
	}

	public String getKeywords() {
		if (_keywords != null) {
			return _keywords;
		}

		_keywords = ParamUtil.getString(_httpServletRequest, "keywords");

		return _keywords;
	}

	public String getOrderByCol() {
		if (_orderByCol != null) {
			return _orderByCol;
		}

		_orderByCol = ParamUtil.getString(_httpServletRequest, "orderByCol");

		if (Validator.isNull(_orderByCol)) {
			_orderByCol = _portalPreferences.getValue(
				_portalPreferenceNamespace, "order-by-col", "create-date");
		}
		else {
			boolean saveOrderBy = ParamUtil.getBoolean(
				_httpServletRequest, "saveOrderBy");

			if (saveOrderBy) {
				_portalPreferences.setValue(
					_portalPreferenceNamespace, "order-by-col", _orderByCol);
			}
		}

		return _orderByCol;
	}

	public String getOrderByType() {
		if (_orderByType != null) {
			return _orderByType;
		}

		_orderByType = ParamUtil.getString(_httpServletRequest, "orderByType");

		if (Validator.isNull(_orderByType)) {
			_orderByType = _portalPreferences.getValue(
				_portalPreferenceNamespace, "order-by-type", "desc");
		}
		else {
			boolean saveOrderBy = ParamUtil.getBoolean(
				_httpServletRequest, "saveOrderBy");

			if (saveOrderBy) {
				_portalPreferences.setValue(
					_portalPreferenceNamespace, "order-by-type", _orderByType);
			}
		}

		return _orderByType;
	}

	public PortletURL getPortletURL() {
		LiferayPortletResponse liferayPortletResponse =
			_cpRequestHelper.getLiferayPortletResponse();

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		String redirect = ParamUtil.getString(_httpServletRequest, "redirect");

		if (Validator.isNotNull(redirect)) {
			portletURL.setParameter("redirect", redirect);
		}

		String delta = ParamUtil.getString(_httpServletRequest, "delta");

		if (Validator.isNotNull(delta)) {
			portletURL.setParameter("delta", delta);
		}

		String deltaEntry = ParamUtil.getString(
			_httpServletRequest, "deltaEntry");

		if (Validator.isNotNull(deltaEntry)) {
			portletURL.setParameter("deltaEntry", deltaEntry);
		}

		String keywords = ParamUtil.getString(_httpServletRequest, "keywords");

		if (Validator.isNotNull(keywords)) {
			portletURL.setParameter("keywords", keywords);
		}

		String orderByCol = getOrderByCol();

		if (Validator.isNotNull(orderByCol)) {
			portletURL.setParameter("orderByCol", orderByCol);
		}

		String orderByType = getOrderByType();

		if (Validator.isNotNull(orderByType)) {
			portletURL.setParameter("orderByType", orderByType);
		}

		long cpSubscriptionEntryId = ParamUtil.getLong(
			_httpServletRequest, "cpSubscriptionEntryId");

		if (Validator.isNotNull(cpSubscriptionEntryId)) {
			portletURL.setParameter(
				"cpSubscriptionEntryId", String.valueOf(cpSubscriptionEntryId));
		}

		return portletURL;
	}

	public RowChecker getRowChecker() {
		if (_rowChecker == null) {
			_rowChecker = new EmptyOnClickRowChecker(
				_cpRequestHelper.getLiferayPortletResponse());
		}

		return _rowChecker;
	}

	public SearchContainer<CPSubscriptionEntry> getSearchContainer()
		throws PortalException {

		Boolean active = null;
		String emptyResultsMessage = "there-are-no-subscriptions";

		String navigation = getNavigation();

		if (navigation.equals("active")) {
			active = Boolean.TRUE;
			emptyResultsMessage = "there-are-no-active-subscriptions";
		}
		else if (navigation.equals("inactive")) {
			active = Boolean.FALSE;
			emptyResultsMessage = "there-are-no-inactive-subscriptions";
		}

		_searchContainer = new SearchContainer<>(
			_cpRequestHelper.getLiferayPortletRequest(), getPortletURL(), null,
			emptyResultsMessage);

		String orderByCol = getOrderByCol();
		String orderByType = getOrderByType();

		_searchContainer.setOrderByCol(orderByCol);
		_searchContainer.setOrderByType(orderByType);

		BaseModelSearchResult<CPSubscriptionEntry>
			cpSubscriptionBaseModelSearchResult =
				_cpSubscriptionEntryService.searchCPSubscriptionEntries(
					_cpRequestHelper.getCompanyId(),
					_cpRequestHelper.getScopeGroupId(), active, getKeywords(),
					_searchContainer.getStart(), _searchContainer.getEnd(),
					CPSubscriptionEntryPortletUtil.getCPSubscriptionEntrySort(
						orderByCol, orderByType));

		_searchContainer.setTotal(
			cpSubscriptionBaseModelSearchResult.getLength());
		_searchContainer.setResults(
			cpSubscriptionBaseModelSearchResult.getBaseModels());

		_searchContainer.setRowChecker(_rowChecker);

		return _searchContainer;
	}

	public boolean hasManageCPSubscriptionEntryPermission() {
		return _portletResourcePermission.contains(
			_cpRequestHelper.getPermissionChecker(),
			_cpRequestHelper.getScopeGroupId(),
			CPActionKeys.MANAGE_COMMERCE_PRODUCT_SUBSCRIPTIONS);
	}

	public boolean isSearch() {
		if (Validator.isNotNull(getKeywords())) {
			return true;
		}

		return false;
	}

	public boolean isShowInfoPanel() {
		if (isSearch()) {
			return false;
		}

		return true;
	}

	protected String getNavigation() {
		return ParamUtil.getString(_httpServletRequest, "navigation");
	}

	private final CPRequestHelper _cpRequestHelper;
	private CPSubscriptionEntry _cpSubscriptionEntry;
	private final CPSubscriptionEntryService _cpSubscriptionEntryService;
	private final HttpServletRequest _httpServletRequest;
	private String _keywords;
	private String _orderByCol;
	private String _orderByType;
	private final String _portalPreferenceNamespace;
	private final PortalPreferences _portalPreferences;
	private final PortletResourcePermission _portletResourcePermission;
	private RowChecker _rowChecker;
	private SearchContainer<CPSubscriptionEntry> _searchContainer;

}