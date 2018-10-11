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

package com.liferay.commerce.subscription.web.internal.display.context;

import com.liferay.commerce.constants.CommerceActionKeys;
import com.liferay.commerce.model.CommerceSubscriptionEntry;
import com.liferay.commerce.product.display.context.util.CPRequestHelper;
import com.liferay.commerce.service.CommerceSubscriptionEntryService;
import com.liferay.commerce.subscription.web.internal.subscription.util.CommerceSubscriptionEntryPortletUtil;
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
public class CommerceSubscriptionEntryDisplayContext {

	public CommerceSubscriptionEntryDisplayContext(
		CommerceSubscriptionEntryService commerceSubscriptionEntryService,
		HttpServletRequest httpServletRequest,
		PortletResourcePermission portletResourcePermission) {

		_commerceSubscriptionEntryService = commerceSubscriptionEntryService;
		_httpServletRequest = httpServletRequest;
		_portletResourcePermission = portletResourcePermission;

		_cpRequestHelper = new CPRequestHelper(_httpServletRequest);

		_portalPreferences = PortletPreferencesFactoryUtil.getPortalPreferences(
			_httpServletRequest);

		_portalPreferenceNamespace = CommerceSubscriptionEntry.class.getName();

		_rowChecker = getRowChecker();
	}

	public CommerceSubscriptionEntry getCommerceSubscriptionEntry()
		throws PortalException {

		if (_commerceSubscriptionEntry != null) {
			return _commerceSubscriptionEntry;
		}

		long commerceSubscriptionEntryId = ParamUtil.getLong(
			_httpServletRequest, "commerceSubscriptionEntryId");

		if (commerceSubscriptionEntryId > 0) {
			_commerceSubscriptionEntry =
				_commerceSubscriptionEntryService.
					fetchCommerceSubscriptionEntry(commerceSubscriptionEntryId);
		}

		return _commerceSubscriptionEntry;
	}

	public long getCommerceSubscriptionEntryId() throws PortalException {
		CommerceSubscriptionEntry commerceSubscriptionEntry =
			getCommerceSubscriptionEntry();

		if (commerceSubscriptionEntry != null) {
			return commerceSubscriptionEntry.getCommerceSubscriptionEntryId();
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

		portletURL.setParameter("navigation", getNavigation());

		String orderByCol = getOrderByCol();

		if (Validator.isNotNull(orderByCol)) {
			portletURL.setParameter("orderByCol", orderByCol);
		}

		String orderByType = getOrderByType();

		if (Validator.isNotNull(orderByType)) {
			portletURL.setParameter("orderByType", orderByType);
		}

		long commerceSubscriptionEntryId = ParamUtil.getLong(
			_httpServletRequest, "commerceSubscriptionEntryId");

		if (commerceSubscriptionEntryId > 0) {
			portletURL.setParameter(
				"commerceSubscriptionEntryId",
				String.valueOf(commerceSubscriptionEntryId));
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

	public SearchContainer<CommerceSubscriptionEntry> getSearchContainer()
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

		BaseModelSearchResult<CommerceSubscriptionEntry>
			cpSubscriptionBaseModelSearchResult =
				_commerceSubscriptionEntryService.searchCPSubscriptionEntries(
					_cpRequestHelper.getCompanyId(),
					_cpRequestHelper.getScopeGroupId(), active, getKeywords(),
					_searchContainer.getStart(), _searchContainer.getEnd(),
					CommerceSubscriptionEntryPortletUtil.
						getCommerceSubscriptionEntrySort(
							orderByCol, orderByType));

		_searchContainer.setTotal(
			cpSubscriptionBaseModelSearchResult.getLength());
		_searchContainer.setResults(
			cpSubscriptionBaseModelSearchResult.getBaseModels());

		_searchContainer.setRowChecker(_rowChecker);

		return _searchContainer;
	}

	public boolean hasManageCommerceSubscriptionEntryPermission() {
		return _portletResourcePermission.contains(
			_cpRequestHelper.getPermissionChecker(),
			_cpRequestHelper.getScopeGroupId(),
			CommerceActionKeys.MANAGE_COMMERCE_SUBSCRIPTIONS);
	}

	protected String getNavigation() {
		return ParamUtil.getString(_httpServletRequest, "navigation", "all");
	}

	private CommerceSubscriptionEntry _commerceSubscriptionEntry;
	private final CommerceSubscriptionEntryService
		_commerceSubscriptionEntryService;
	private final CPRequestHelper _cpRequestHelper;
	private final HttpServletRequest _httpServletRequest;
	private String _keywords;
	private String _orderByCol;
	private String _orderByType;
	private final String _portalPreferenceNamespace;
	private final PortalPreferences _portalPreferences;
	private final PortletResourcePermission _portletResourcePermission;
	private RowChecker _rowChecker;
	private SearchContainer<CommerceSubscriptionEntry> _searchContainer;

}