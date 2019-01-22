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
import com.liferay.commerce.constants.CommerceSubscriptionEntryConstants;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceSubscriptionCycleEntry;
import com.liferay.commerce.model.CommerceSubscriptionEntry;
import com.liferay.commerce.product.display.context.util.CPRequestHelper;
import com.liferay.commerce.product.util.CPSubscriptionType;
import com.liferay.commerce.product.util.CPSubscriptionTypeJSPContributor;
import com.liferay.commerce.product.util.CPSubscriptionTypeJSPContributorRegistry;
import com.liferay.commerce.product.util.CPSubscriptionTypeRegistry;
import com.liferay.commerce.service.CommerceSubscriptionEntryService;
import com.liferay.commerce.subscription.web.internal.display.context.util.CommerceSubscriptionDisplayContextHelper;
import com.liferay.commerce.subscription.web.internal.subscription.util.CommerceSubscriptionEntryPortletUtil;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.DateFormat;
import java.text.Format;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Luca Pellizzon
 * @author Alessio Antonio Rendina
 */
public class CommerceSubscriptionEntryDisplayContext {

	public CommerceSubscriptionEntryDisplayContext(
		CommerceSubscriptionEntryService commerceSubscriptionEntryService,
		ConfigurationProvider configurationProvider,
		CPSubscriptionTypeJSPContributorRegistry
			cpSubscriptionTypeJSPContributorRegistry,
		CPSubscriptionTypeRegistry cpSubscriptionTypeRegistry,
		HttpServletRequest httpServletRequest,
		PortletResourcePermission portletResourcePermission) {

		_commerceSubscriptionEntryService = commerceSubscriptionEntryService;
		_configurationProvider = configurationProvider;
		_cpSubscriptionTypeJSPContributorRegistry =
			cpSubscriptionTypeJSPContributorRegistry;
		_cpSubscriptionTypeRegistry = cpSubscriptionTypeRegistry;
		_httpServletRequest = httpServletRequest;
		_portletResourcePermission = portletResourcePermission;

		_cpRequestHelper = new CPRequestHelper(_httpServletRequest);

		_themeDisplay = _cpRequestHelper.getThemeDisplay();

		_commerceOrderDateFormatDateTime =
			FastDateFormatFactoryUtil.getDateTime(
				DateFormat.MEDIUM, DateFormat.MEDIUM, _themeDisplay.getLocale(),
				_themeDisplay.getTimeZone());

		_portalPreferences = PortletPreferencesFactoryUtil.getPortalPreferences(
			_httpServletRequest);

		_portalPreferenceNamespace = CommerceSubscriptionEntry.class.getName();

		_rowChecker = getRowChecker();
	}

	public String getCommerceOrderDateTime(CommerceOrder commerceOrder) {
		return _commerceOrderDateFormatDateTime.format(
			commerceOrder.getCreateDate());
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

	public DropdownItemList getCommerceSubscriptionEntryActionItemList(
			CommerceSubscriptionEntry commerceSubscriptionEntry,
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws PortalException {

		CommerceSubscriptionDisplayContextHelper
			commerceSubscriptionDisplayContextHelper =
				new CommerceSubscriptionDisplayContextHelper(
					commerceSubscriptionEntry, _configurationProvider,
					portletRequest, portletResponse);

		return commerceSubscriptionDisplayContextHelper.
			getCommerceSubscriptionEntryActionItemList();
	}

	public long getCommerceSubscriptionEntryId() throws PortalException {
		CommerceSubscriptionEntry commerceSubscriptionEntry =
			getCommerceSubscriptionEntry();

		if (commerceSubscriptionEntry != null) {
			return commerceSubscriptionEntry.getCommerceSubscriptionEntryId();
		}

		return 0;
	}

	public SearchContainer<CommerceOrder>
			getCommerceSubscriptionEntryOrderSearchContainer()
		throws PortalException {

		if (_commerceSubscriptionEntryOrderSearchContainer != null) {
			return _commerceSubscriptionEntryOrderSearchContainer;
		}

		SearchContainer<CommerceOrder> searchContainer = new SearchContainer<>(
			_cpRequestHelper.getLiferayPortletRequest(), getPortletURL(), null,
			"there-are-no-orders");

		List<CommerceOrder> commerceOrders = new ArrayList<>();

		CommerceSubscriptionEntry commerceSubscriptionEntry =
			getCommerceSubscriptionEntry();

		List<CommerceSubscriptionCycleEntry> commerceSubscriptionCycleEntries =
			commerceSubscriptionEntry.getCommerceSubscriptionCycleEntries();

		for (CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry :
				commerceSubscriptionCycleEntries) {

			CommerceOrder commerceOrder =
				commerceSubscriptionCycleEntry.fetchCommerceOrder();

			if (commerceOrder != null) {
				commerceOrders.add(commerceOrder);
			}
		}

		int end = searchContainer.getEnd();

		if (total < end) {
			end = total;
		}

		searchContainer.setResults(
			commerceOrders.subList(searchContainer.getStart(), end));

		int total = commerceOrders.size();

		searchContainer.setTotal(total);

		_commerceSubscriptionEntryOrderSearchContainer = searchContainer;

		return _commerceSubscriptionEntryOrderSearchContainer;
	}

	public String getCommerceSubscriptionEntryRemainingCycles(
		CommerceSubscriptionEntry commerceSubscriptionEntry) {

		long maxSubscriptionCycles =
			commerceSubscriptionEntry.getMaxSubscriptionCycles();

		if (maxSubscriptionCycles == 0) {
			return LanguageUtil.get(_httpServletRequest, "unlimited");
		}

		int commerceSubscriptionCycleEntriesCount =
			commerceSubscriptionEntry.
				getCommerceSubscriptionCycleEntriesCount();

		long remainingCycles =
			maxSubscriptionCycles - commerceSubscriptionCycleEntriesCount;

		return String.valueOf(remainingCycles);
	}

	public CPSubscriptionType getCPSubscriptionType(String subscriptionType) {
		return _cpSubscriptionTypeRegistry.getCPSubscriptionType(
			subscriptionType);
	}

	public CPSubscriptionTypeJSPContributor getCPSubscriptionTypeJSPContributor(
		String subscriptionType) {

		return _cpSubscriptionTypeJSPContributorRegistry.
			getCPSubscriptionTypeJSPContributor(subscriptionType);
	}

	public List<CPSubscriptionType> getCPSubscriptionTypes() {
		return _cpSubscriptionTypeRegistry.getCPSubscriptionTypes();
	}

	public String getEditCommerceOrderURL(long commerceOrderId)
		throws PortalException {

		PortletURL portletURL = PortletProviderUtil.getPortletURL(
			_httpServletRequest, _themeDisplay.getScopeGroup(),
			CommerceOrder.class.getName(), PortletProvider.Action.VIEW);

		portletURL.setParameter("mvcRenderCommandName", "editCommerceOrder");
		portletURL.setParameter("redirect", _themeDisplay.getURLCurrent());
		portletURL.setParameter(
			"commerceOrderId", String.valueOf(commerceOrderId));

		return portletURL.toString();
	}

	public PortletURL getEditCommerceSubscriptionEntryURL() {
		PortletURL portletURL = getPortletURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "editCommerceSubscriptionEntry");

		return portletURL;
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

		long commerceSubscriptionEntryId = ParamUtil.getLong(
			_httpServletRequest, "commerceSubscriptionEntryId");

		if (commerceSubscriptionEntryId > 0) {
			portletURL.setParameter(
				"commerceSubscriptionEntryId",
				String.valueOf(commerceSubscriptionEntryId));
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

		if (_searchContainer != null) {
			return _searchContainer;
		}

		String emptyResultsMessage = "there-are-no-subscriptions";
		Long maxSubscriptionCycles = null;
		Integer subscriptionStatus = null;

		String navigation = getNavigation();

		if (navigation.equals("active")) {
			emptyResultsMessage = "there-are-no-active-subscriptions";
			subscriptionStatus =
				CommerceSubscriptionEntryConstants.SUBSCRIPTION_STATUS_ACTIVE;
		}
		else if (navigation.equals("suspended")) {
			emptyResultsMessage = "there-are-no-suspended-subscriptions";
			subscriptionStatus =
				CommerceSubscriptionEntryConstants.
					SUBSCRIPTION_STATUS_SUSPENDED;
		}
		else if (navigation.equals("cancelled")) {
			emptyResultsMessage = "there-are-no-cancelled-subscriptions";
			subscriptionStatus =
				CommerceSubscriptionEntryConstants.
					SUBSCRIPTION_STATUS_CANCELLED;
		}
		else if (navigation.equals("completed")) {
			emptyResultsMessage = "there-are-no-completed-subscriptions";
			subscriptionStatus =
				CommerceSubscriptionEntryConstants.
					SUBSCRIPTION_STATUS_COMPLETED;
		}
		else if (navigation.equals("never-ends")) {
			emptyResultsMessage = "there-are-no-unlimited-subscriptions";
			maxSubscriptionCycles = 0;
		}

		SearchContainer<CommerceSubscriptionEntry> searchContainer =
			new SearchContainer<>(
				_cpRequestHelper.getLiferayPortletRequest(), getPortletURL(),
				null, emptyResultsMessage);

		String orderByCol = getOrderByCol();
		String orderByType = getOrderByType();

		searchContainer.setOrderByCol(orderByCol);
		searchContainer.setOrderByType(orderByType);

		searchContainer.setRowChecker(_rowChecker);

		BaseModelSearchResult<CommerceSubscriptionEntry>
			commerceSubscriptionBaseModelSearchResult =
				_commerceSubscriptionEntryService.
					searchCommerceSubscriptionEntries(
						_cpRequestHelper.getCompanyId(),
						_cpRequestHelper.getScopeGroupId(),
						maxSubscriptionCycles, subscriptionStatus,
						getKeywords(), searchContainer.getStart(),
						searchContainer.getEnd(),
						CommerceSubscriptionEntryPortletUtil.
							getCommerceSubscriptionEntrySort(
								orderByCol, orderByType));

		searchContainer.setResults(
			commerceSubscriptionBaseModelSearchResult.getBaseModels());
		searchContainer.setTotal(
			commerceSubscriptionBaseModelSearchResult.getLength());

		_searchContainer = searchContainer;

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

	private final Format _commerceOrderDateFormatDateTime;
	private CommerceSubscriptionEntry _commerceSubscriptionEntry;
	private SearchContainer<CommerceOrder>
		_commerceSubscriptionEntryOrderSearchContainer;
	private final CommerceSubscriptionEntryService
		_commerceSubscriptionEntryService;
	private final ConfigurationProvider _configurationProvider;
	private final CPRequestHelper _cpRequestHelper;
	private final CPSubscriptionTypeJSPContributorRegistry
		_cpSubscriptionTypeJSPContributorRegistry;
	private final CPSubscriptionTypeRegistry _cpSubscriptionTypeRegistry;
	private final HttpServletRequest _httpServletRequest;
	private String _keywords;
	private String _orderByCol;
	private String _orderByType;
	private final String _portalPreferenceNamespace;
	private final PortalPreferences _portalPreferences;
	private final PortletResourcePermission _portletResourcePermission;
	private RowChecker _rowChecker;
	private SearchContainer<CommerceSubscriptionEntry> _searchContainer;
	private final ThemeDisplay _themeDisplay;

}