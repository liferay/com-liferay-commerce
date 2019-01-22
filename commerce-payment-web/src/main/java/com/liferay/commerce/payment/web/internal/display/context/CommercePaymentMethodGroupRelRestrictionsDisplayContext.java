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

package com.liferay.commerce.payment.web.internal.display.context;

import com.liferay.commerce.constants.CommerceActionKeys;
import com.liferay.commerce.item.selector.criterion.CommerceCountryItemSelectorCriterion;
import com.liferay.commerce.model.CommerceAddressRestriction;
import com.liferay.commerce.payment.constants.CommercePaymentScreenNavigationConstants;
import com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel;
import com.liferay.commerce.payment.service.CommercePaymentMethodGroupRelService;
import com.liferay.commerce.payment.web.internal.admin.PaymentMethodsCommerceAdminModule;
import com.liferay.commerce.util.CommerceUtil;
import com.liferay.item.selector.ItemSelector;
import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.criteria.UUIDItemSelectorReturnType;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Alessio Antonio Rendina
 */
public class CommercePaymentMethodGroupRelRestrictionsDisplayContext {

	public CommercePaymentMethodGroupRelRestrictionsDisplayContext(
		CommercePaymentMethodGroupRelService
			commercePaymentMethodGroupRelService,
		ItemSelector itemSelector,
		PortletResourcePermission portletResourcePermission,
		RenderRequest renderRequest, RenderResponse renderResponse) {

		_commercePaymentMethodGroupRelService =
			commercePaymentMethodGroupRelService;
		_itemSelector = itemSelector;
		_portletResourcePermission = portletResourcePermission;
		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
	}

	public CommercePaymentMethodGroupRel getCommercePaymentMethodGroupRel()
		throws PortalException {

		if (_commercePaymentMethodGroupRel != null) {
			return _commercePaymentMethodGroupRel;
		}

		long commercePaymentMethodGroupRelId = ParamUtil.getLong(
			_renderRequest, "commercePaymentMethodGroupRelId");

		if (commercePaymentMethodGroupRelId > 0) {
			_commercePaymentMethodGroupRel =
				_commercePaymentMethodGroupRelService.
					getCommercePaymentMethodGroupRel(
						commercePaymentMethodGroupRelId);
		}

		return _commercePaymentMethodGroupRel;
	}

	public long getCommercePaymentMethodGroupRelId() throws PortalException {
		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel =
			getCommercePaymentMethodGroupRel();

		if (commercePaymentMethodGroupRel == null) {
			return 0;
		}

		return commercePaymentMethodGroupRel.
			getCommercePaymentMethodGroupRelId();
	}

	public String getItemSelectorUrl() throws PortalException {
		RequestBackedPortletURLFactory requestBackedPortletURLFactory =
			RequestBackedPortletURLFactoryUtil.create(_renderRequest);

		CommerceCountryItemSelectorCriterion
			commerceCountryItemSelectorCriterion =
				new CommerceCountryItemSelectorCriterion();

		commerceCountryItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
			Collections.<ItemSelectorReturnType>singletonList(
				new UUIDItemSelectorReturnType()));

		PortletURL itemSelectorURL = _itemSelector.getItemSelectorURL(
			requestBackedPortletURLFactory, "countriesSelectItem",
			commerceCountryItemSelectorCriterion);

		String checkedCommerceCountryIds = StringUtil.merge(
			getCheckedCommerceCountryIds());

		itemSelectorURL.setParameter(
			"checkedCommerceCountryIds", checkedCommerceCountryIds);

		return itemSelectorURL.toString();
	}

	public PortletURL getPortletURL() throws PortalException {
		PortletURL portletURL = _renderResponse.createRenderURL();

		portletURL.setParameter(
			"commerceAdminModuleKey", PaymentMethodsCommerceAdminModule.KEY);
		portletURL.setParameter(
			"mvcRenderCommandName", "editCommercePaymentMethodGroupRel");
		portletURL.setParameter(
			"commercePaymentMethodGroupRelId",
			String.valueOf(getCommercePaymentMethodGroupRelId()));
		portletURL.setParameter(
			"screenNavigationEntryKey",
			CommercePaymentScreenNavigationConstants.
				ENTRY_KEY_COMMERCE_PAYMENT_METHOD_RESTRICTIONS);

		String delta = ParamUtil.getString(_renderRequest, "delta");

		if (Validator.isNotNull(delta)) {
			portletURL.setParameter("delta", delta);
		}

		return portletURL;
	}

	public RowChecker getRowChecker() {
		if (_rowChecker == null) {
			_rowChecker = new EmptyOnClickRowChecker(_renderResponse);
		}

		return _rowChecker;
	}

	public SearchContainer<CommerceAddressRestriction> getSearchContainer()
		throws PortalException {

		if (_searchContainer != null) {
			return _searchContainer;
		}

		_searchContainer = new SearchContainer<>(
			_renderRequest, getPortletURL(), null, null);

		_searchContainer.setEmptyResultsMessage("there-are-no-restrictions");

		String orderByCol = "create-date";
		String orderByType = "desc";

		OrderByComparator<CommerceAddressRestriction> orderByComparator =
			CommerceUtil.getCommerceAddressRestrictionOrderByComparator(
				orderByCol, orderByType);

		_searchContainer.setOrderByCol(orderByCol);
		_searchContainer.setOrderByComparator(orderByComparator);
		_searchContainer.setOrderByType(orderByType);
		_searchContainer.setRowChecker(getRowChecker());

		int total =
			_commercePaymentMethodGroupRelService.
				getCommerceAddressRestrictionsCount(
					getCommercePaymentMethodGroupRelId());

		_searchContainer.setTotal(total);

		List<CommerceAddressRestriction> results =
			getCommerceAddressRestrictions(
				_searchContainer.getStart(), _searchContainer.getEnd(),
				orderByComparator);

		_searchContainer.setResults(results);

		return _searchContainer;
	}

	public boolean hasManageCommercePaymentMethodGroupRelsPermission() {
		ThemeDisplay themeDisplay = (ThemeDisplay)_renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		return _portletResourcePermission.contains(
			themeDisplay.getPermissionChecker(), themeDisplay.getScopeGroupId(),
			CommerceActionKeys.MANAGE_COMMERCE_PAYMENT_METHODS);
	}

	protected long[] getCheckedCommerceCountryIds() throws PortalException {
		List<CommerceAddressRestriction> commerceAddressRestrictions =
			getCommerceAddressRestrictions();

		if (commerceAddressRestrictions.isEmpty()) {
			return new long[0];
		}

		List<Long> commerceCountryIdsList = new ArrayList<>(
			commerceAddressRestrictions.size());

		for (CommerceAddressRestriction commerceAddressRestriction :
				commerceAddressRestrictions) {

			commerceCountryIdsList.add(
				commerceAddressRestriction.getCommerceCountryId());
		}

		return ArrayUtil.toLongArray(commerceCountryIdsList);
	}

	protected List<CommerceAddressRestriction> getCommerceAddressRestrictions()
		throws PortalException {

		return getCommerceAddressRestrictions(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	protected List<CommerceAddressRestriction> getCommerceAddressRestrictions(
			int start, int end,
			OrderByComparator<CommerceAddressRestriction> orderByComparator)
		throws PortalException {

		return _commercePaymentMethodGroupRelService.
			getCommerceAddressRestrictions(
				getCommercePaymentMethodGroupRelId(), start, end,
				orderByComparator);
	}

	private CommercePaymentMethodGroupRel _commercePaymentMethodGroupRel;
	private final CommercePaymentMethodGroupRelService
		_commercePaymentMethodGroupRelService;
	private final ItemSelector _itemSelector;
	private final PortletResourcePermission _portletResourcePermission;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;
	private RowChecker _rowChecker;
	private SearchContainer<CommerceAddressRestriction> _searchContainer;

}