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
import com.liferay.commerce.payment.constants.CommercePaymentScreenNavigationConstants;
import com.liferay.commerce.payment.method.CommercePaymentMethod;
import com.liferay.commerce.payment.method.CommercePaymentMethodRegistry;
import com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel;
import com.liferay.commerce.payment.service.CommercePaymentMethodGroupRelService;
import com.liferay.commerce.payment.util.comparator.CommercePaymentMethodGroupRelNameComparator;
import com.liferay.commerce.payment.util.comparator.CommercePaymentMethodGroupRelNameOrderByComparator;
import com.liferay.commerce.payment.web.internal.admin.PaymentMethodsCommerceAdminModule;
import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.commerce.service.CommerceSubscriptionEntryLocalService;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Andrea Di Giorgi
 */
public class CommercePaymentMethodGroupRelsDisplayContext {

	public CommercePaymentMethodGroupRelsDisplayContext(
		CommerceChannelLocalService commerceChannelLocalService,
		CommerceOrderLocalService commerceOrderLocalService,
		CommercePaymentMethodRegistry commercePaymentMethodRegistry,
		CommercePaymentMethodGroupRelService
			commercePaymentMethodGroupRelService,
		CommerceSubscriptionEntryLocalService
			commerceSubscriptionEntryLocalService,
		CPDefinitionLocalService cpDefinitionLocalService,
		PortletResourcePermission portletResourcePermission,
		RenderRequest renderRequest, RenderResponse renderResponse) {

		_commerceChannelLocalService = commerceChannelLocalService;
		_commerceOrderLocalService = commerceOrderLocalService;
		_commercePaymentMethodRegistry = commercePaymentMethodRegistry;
		_commercePaymentMethodGroupRelService =
			commercePaymentMethodGroupRelService;
		_commerceSubscriptionEntryLocalService =
			commerceSubscriptionEntryLocalService;
		_cpDefinitionLocalService = cpDefinitionLocalService;
		_portletResourcePermission = portletResourcePermission;
		_defaultCommercePaymentMethodGroupRels = new ArrayList<>();
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
		String engineKey = ParamUtil.getString(_renderRequest, "engineKey");

		if (commercePaymentMethodGroupRelId > 0) {
			_commercePaymentMethodGroupRel =
				_commercePaymentMethodGroupRelService.
					getCommercePaymentMethodGroupRel(
						commercePaymentMethodGroupRelId);
		}
		else if (Validator.isNotNull(engineKey)) {
			_commercePaymentMethodGroupRel =
				getDefaultCommercePaymentMethodGroupRel(engineKey);
		}

		return _commercePaymentMethodGroupRel;
	}

	public List<CommercePaymentMethodGroupRel>
			getDefaultCommercePaymentMethodGroupRels()
		throws PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay)_renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		List<CommercePaymentMethodGroupRel> commercePaymentMethodGroupRels =
			_commercePaymentMethodGroupRelService.
				getCommercePaymentMethodGroupRels(
					themeDisplay.getScopeGroupId());

		Stream<CommercePaymentMethodGroupRel> stream =
			commercePaymentMethodGroupRels.stream();

		String[] array = stream.map(
			CommercePaymentMethodGroupRel::getEngineKey
		).toArray(
			String[]::new
		);

		_defaultCommercePaymentMethodGroupRels =
			addDefaultCommercePaymentMethodGroupRels(
				_defaultCommercePaymentMethodGroupRels);

		stream = _defaultCommercePaymentMethodGroupRels.stream();

		_defaultCommercePaymentMethodGroupRels = stream.filter(
			commercePaymentMethodGroupRel -> !ArrayUtil.contains(
				array, commercePaymentMethodGroupRel.getEngineKey())
		).collect(
			Collectors.toList()
		);

		return _defaultCommercePaymentMethodGroupRels;
	}

	public PortletURL getPortletURL() {
		PortletURL portletURL = _renderResponse.createRenderURL();

		portletURL.setParameter(
			"commerceAdminModuleKey", PaymentMethodsCommerceAdminModule.KEY);

		String delta = ParamUtil.getString(_renderRequest, "delta");

		if (Validator.isNotNull(delta)) {
			portletURL.setParameter("delta", delta);
		}

		portletURL.setParameter("navigation", getNavigation());

		return portletURL;
	}

	public SearchContainer<CommercePaymentMethodGroupRel> getSearchContainer()
		throws PortalException {

		if (_searchContainer != null) {
			return _searchContainer;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)_renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Boolean active = null;
		String emptyResultsMessage = "there-are-no-payment-methods";

		String navigation = getNavigation();

		if (navigation.equals("active")) {
			active = Boolean.TRUE;
			emptyResultsMessage = "there-are-no-active-payment-methods";
		}
		else if (navigation.equals("inactive")) {
			active = Boolean.FALSE;
			emptyResultsMessage = "there-are-no-inactive-payment-methods";
		}

		_searchContainer = new SearchContainer<>(
			_renderRequest, getPortletURL(), null, emptyResultsMessage);

		List<CommercePaymentMethodGroupRel> commercePaymentMethodGroupRels =
			null;
		int commercePaymentMethodGroupRelsCount = 0;

		OrderByComparator<CommercePaymentMethodGroupRel>
			commercePaymentMethodGroupRelNameOrderByComparator =
				_getCommercePaymentMethodGroupRelNameOrderByComparator(
					"name", "asc");

		if (active != null) {
			commercePaymentMethodGroupRels =
				_commercePaymentMethodGroupRelService.
					getCommercePaymentMethodGroupRels(
						themeDisplay.getScopeGroupId(), active,
						_searchContainer.getStart(), _searchContainer.getEnd(),
						commercePaymentMethodGroupRelNameOrderByComparator);
			commercePaymentMethodGroupRelsCount =
				_commercePaymentMethodGroupRelService.
					getCommercePaymentMethodGroupRelsCount(
						themeDisplay.getScopeGroupId(), active);
		}
		else {
			commercePaymentMethodGroupRels =
				_commercePaymentMethodGroupRelService.
					getCommercePaymentMethodGroupRels(
						themeDisplay.getScopeGroupId());

			commercePaymentMethodGroupRelsCount =
				_commercePaymentMethodGroupRelService.
					getCommercePaymentMethodGroupRelsCount(
						themeDisplay.getScopeGroupId());
		}

		if (ListUtil.isNotEmpty(commercePaymentMethodGroupRels)) {
			commercePaymentMethodGroupRels = ListUtil.sort(
				commercePaymentMethodGroupRels,
				new CommercePaymentMethodGroupRelNameComparator(
					themeDisplay.getLocale()));
		}

		_searchContainer.setResults(commercePaymentMethodGroupRels);
		_searchContainer.setTotal(commercePaymentMethodGroupRelsCount);

		return _searchContainer;
	}

	public String getSelectedScreenNavigationCategoryKey() {
		return ParamUtil.getString(
			_renderRequest, "screenNavigationCategoryKey",
			CommercePaymentScreenNavigationConstants.
				CATEGORY_KEY_COMMERCE_PAYMENT_METHOD_DETAILS);
	}

	public boolean hasManageCommercePaymentMethodGroupRelsPermission() {
		ThemeDisplay themeDisplay = (ThemeDisplay)_renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		return _portletResourcePermission.contains(
			themeDisplay.getPermissionChecker(), themeDisplay.getScopeGroupId(),
			CommerceActionKeys.MANAGE_COMMERCE_PAYMENT_METHODS);
	}

	public boolean isLastRecurringPaymentMethod(String engineKey) {
		Map<String, CommercePaymentMethod> commercePaymentMethodsMap =
			_commercePaymentMethodRegistry.getCommercePaymentMethods();

		List<CommercePaymentMethod> recurringCommercePaymentMethods =
			new ArrayList<>();

		for (CommercePaymentMethod commercePaymentMethod :
				commercePaymentMethodsMap.values()) {

			if (commercePaymentMethod.isProcessRecurringEnabled()) {
				recurringCommercePaymentMethods.add(commercePaymentMethod);
			}
		}

		if (recurringCommercePaymentMethods.size() == 1) {
			CommercePaymentMethod lastRecurringCommercePaymentMethod =
				recurringCommercePaymentMethods.get(0);

			if (engineKey.equals(lastRecurringCommercePaymentMethod.getKey())) {
				return true;
			}
		}

		return false;
	}

	protected List<CommercePaymentMethodGroupRel>
			addDefaultCommercePaymentMethodGroupRels(
				List<CommercePaymentMethodGroupRel>
					commercePaymentMethodGroupRels)
		throws PortalException {

		Map<String, CommercePaymentMethod> commercePaymentMethods =
			_commercePaymentMethodRegistry.getCommercePaymentMethods();

		Set<String> commerceEngineKeys = new TreeSet<>(
			commercePaymentMethods.keySet());

		for (CommercePaymentMethodGroupRel commercePaymentMethodGroupRel :
				commercePaymentMethodGroupRels) {

			commerceEngineKeys.remove(
				commercePaymentMethodGroupRel.getEngineKey());
		}

		commercePaymentMethodGroupRels = ListUtil.copy(
			commercePaymentMethodGroupRels);

		for (String name : commerceEngineKeys) {
			CommercePaymentMethodGroupRel commercePaymentMethodGroupRel =
				getDefaultCommercePaymentMethodGroupRel(name);

			commercePaymentMethodGroupRels.add(commercePaymentMethodGroupRel);
		}

		return commercePaymentMethodGroupRels;
	}

	protected CommercePaymentMethodGroupRel
			getDefaultCommercePaymentMethodGroupRel(String engineKey)
		throws PortalException {

		long groupId = PortalUtil.getScopeGroupId(_renderRequest);

		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel =
			_commercePaymentMethodGroupRelService.
				createCommercePaymentMethodGroupRel(groupId);

		CommercePaymentMethod commercePaymentMethod =
			_commercePaymentMethodRegistry.getCommercePaymentMethod(engineKey);

		Locale locale = LocaleUtil.getSiteDefault();

		commercePaymentMethodGroupRel.setName(
			commercePaymentMethod.getName(locale), locale);
		commercePaymentMethodGroupRel.setDescription(
			commercePaymentMethod.getDescription(locale), locale);

		commercePaymentMethodGroupRel.setEngineKey(engineKey);

		return commercePaymentMethodGroupRel;
	}

	protected String getNavigation() {
		return ParamUtil.getString(_renderRequest, "navigation");
	}

	private OrderByComparator<CommercePaymentMethodGroupRel>
		_getCommercePaymentMethodGroupRelNameOrderByComparator(
			String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator<CommercePaymentMethodGroupRel> orderByComparator =
			null;

		if (orderByCol.equals("name")) {
			orderByComparator =
				new CommercePaymentMethodGroupRelNameOrderByComparator(
					orderByAsc);
		}

		return orderByComparator;
	}

	private final CommerceChannelLocalService _commerceChannelLocalService;
	private final CommerceOrderLocalService _commerceOrderLocalService;
	private CommercePaymentMethodGroupRel _commercePaymentMethodGroupRel;
	private final CommercePaymentMethodGroupRelService
		_commercePaymentMethodGroupRelService;
	private final CommercePaymentMethodRegistry _commercePaymentMethodRegistry;
	private final CommerceSubscriptionEntryLocalService
		_commerceSubscriptionEntryLocalService;
	private final CPDefinitionLocalService _cpDefinitionLocalService;
	private List<CommercePaymentMethodGroupRel>
		_defaultCommercePaymentMethodGroupRels;
	private final PortletResourcePermission _portletResourcePermission;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;
	private SearchContainer<CommercePaymentMethodGroupRel> _searchContainer;

}