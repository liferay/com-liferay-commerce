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

package com.liferay.commerce.account.item.selector.web.internal.display.context;

import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.account.item.selector.web.internal.display.context.util.CommerceAccountItemSelectorRequestHelper;
import com.liferay.commerce.account.item.selector.web.internal.search.CommerceAccountItemSelectorChecker;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountService;
import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alessio Antonio Rendina
 * @author Ethan Bustad
 */
public class CommerceAccountItemSelectorViewDisplayContext {

	public CommerceAccountItemSelectorViewDisplayContext(
		CommerceAccountService commerceAccountService,
		HttpServletRequest httpServletRequest, PortletURL portletURL,
		String itemSelectedEventName) {

		_commerceAccountService = commerceAccountService;
		_portletURL = portletURL;
		_itemSelectedEventName = itemSelectedEventName;

		_commerceAccountItemSelectorRequestHelper =
			new CommerceAccountItemSelectorRequestHelper(httpServletRequest);
	}

	public String getItemSelectedEventName() {
		return _itemSelectedEventName;
	}

	public String getOrderByCol() {
		return ParamUtil.getString(
			_commerceAccountItemSelectorRequestHelper.getRenderRequest(),
			SearchContainer.DEFAULT_ORDER_BY_COL_PARAM, "createDate_sortable");
	}

	public String getOrderByType() {
		return ParamUtil.getString(
			_commerceAccountItemSelectorRequestHelper.getRenderRequest(),
			SearchContainer.DEFAULT_ORDER_BY_TYPE_PARAM, "desc");
	}

	public PortletURL getPortletURL() {
		return _portletURL;
	}

	public SearchContainer<CommerceAccount> getSearchContainer()
		throws PortalException {

		if (_searchContainer != null) {
			return _searchContainer;
		}

		_searchContainer = new SearchContainer<>(
			_commerceAccountItemSelectorRequestHelper.
				getLiferayPortletRequest(),
			getPortletURL(), null, null);

		_searchContainer.setEmptyResultsMessage("there-are-no-accounts");

		_searchContainer.setOrderByCol(getOrderByCol());
		_searchContainer.setOrderByType(getOrderByType());

		RowChecker rowChecker = new CommerceAccountItemSelectorChecker(
			_commerceAccountItemSelectorRequestHelper.getRenderResponse(),
			getCheckedCommerceAccountIds());

		_searchContainer.setRowChecker(rowChecker);

		List<CommerceAccount> results =
			_commerceAccountService.getUserCommerceAccounts(
				_commerceAccountItemSelectorRequestHelper.getUserId(),
				CommerceAccountConstants.DEFAULT_PARENT_ACCOUNT_ID,
				CommerceAccountConstants.SITE_TYPE_B2C_B2B, getKeywords(),
				_searchContainer.getStart(), _searchContainer.getEnd());

		_searchContainer.setResults(results);

		int total = _commerceAccountService.getUserCommerceAccountsCount(
			_commerceAccountItemSelectorRequestHelper.getUserId(),
			CommerceAccountConstants.DEFAULT_PARENT_ACCOUNT_ID,
			CommerceAccountConstants.SITE_TYPE_B2C_B2B, getKeywords());

		_searchContainer.setTotal(total);

		return _searchContainer;
	}

	protected long[] getCheckedCommerceAccountIds() {
		return ParamUtil.getLongValues(
			_commerceAccountItemSelectorRequestHelper.getRenderRequest(),
			"checkedCommerceAccountIds");
	}

	protected String getKeywords() {
		if (_keywords != null) {
			return _keywords;
		}

		_keywords = ParamUtil.getString(
			_commerceAccountItemSelectorRequestHelper.getRenderRequest(),
			"keywords");

		return _keywords;
	}

	private final CommerceAccountItemSelectorRequestHelper
		_commerceAccountItemSelectorRequestHelper;
	private final CommerceAccountService _commerceAccountService;
	private final String _itemSelectedEventName;
	private String _keywords;
	private final PortletURL _portletURL;
	private SearchContainer<CommerceAccount> _searchContainer;

}