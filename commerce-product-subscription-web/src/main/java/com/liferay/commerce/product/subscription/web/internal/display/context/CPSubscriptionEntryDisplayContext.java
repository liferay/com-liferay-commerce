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

import com.liferay.commerce.product.constants.CPWebKeys;
import com.liferay.commerce.product.display.context.util.CPRequestHelper;
import com.liferay.commerce.product.model.CPSubscriptionEntry;
import com.liferay.commerce.product.service.CPSubscriptionEntryService;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Luca Pellizzon
 */
public class CPSubscriptionEntryDisplayContext {

	public CPSubscriptionEntryDisplayContext(
		HttpServletRequest httpServletRequest,
		CPSubscriptionEntryService cpSubscriptionEntryService) {

		_httpServletRequest = httpServletRequest;

		CPRequestHelper cpRequestHelper = new CPRequestHelper(
			_httpServletRequest);

		_liferayPortletRequest = cpRequestHelper.getLiferayPortletRequest();
		_liferayPortletResponse = cpRequestHelper.getLiferayPortletResponse();

		_cpSubscriptionEntryService = cpSubscriptionEntryService;
	}

	public long getCPSubscriptionEntryId() throws PortalException {
		CPSubscriptionEntry cpSubscriptionEntry =
			(CPSubscriptionEntry)_liferayPortletRequest.getAttribute(
				CPWebKeys.CP_SUBSCRIPTION_ENTRY);

		if (cpSubscriptionEntry != null) {
			return cpSubscriptionEntry.getCPSubscriptionEntryId();
		}

		long cpSubscriptionEntryId = ParamUtil.getLong(
			_liferayPortletRequest, "cpSubscriptionEntryId");

		if (cpSubscriptionEntryId > 0) {
			cpSubscriptionEntry =
				_cpSubscriptionEntryService.fetchCPSubscriptionEntry(
					cpSubscriptionEntryId);
		}

		if (cpSubscriptionEntry != null) {
			_liferayPortletRequest.setAttribute(
				CPWebKeys.CP_SUBSCRIPTION_ENTRY, cpSubscriptionEntry);

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

	public PortletURL getPortletURL() throws PortalException {
		PortletURL portletURL = _liferayPortletResponse.createRenderURL();

		return portletURL;
	}

	public SearchContainer<CPSubscriptionEntry> getSearchContainer()
		throws PortalException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)_httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		_searchContainer = new SearchContainer<>(
			_liferayPortletRequest, getPortletURL(), null,
			"there-are-no-subscriptions");

		BaseModelSearchResult<CPSubscriptionEntry>
			cpSubscriptionBaseModelSearchResult =
				_cpSubscriptionEntryService.searchCPSubscriptionEntries(
					themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(),
					getCPSubscriptionEntryId(), getKeywords(),
					_searchContainer.getStart(), _searchContainer.getEnd(),
					null);

		_searchContainer.setTotal(
			cpSubscriptionBaseModelSearchResult.getLength());
		_searchContainer.setResults(
			cpSubscriptionBaseModelSearchResult.getBaseModels());

		return _searchContainer;
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

	private final CPSubscriptionEntryService _cpSubscriptionEntryService;
	private final HttpServletRequest _httpServletRequest;
	private String _keywords;
	private final LiferayPortletRequest _liferayPortletRequest;
	private final LiferayPortletResponse _liferayPortletResponse;
	private SearchContainer<CPSubscriptionEntry> _searchContainer;

}