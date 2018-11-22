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

package com.liferay.commerce.payment.method.web.internal.display.context;

import com.liferay.commerce.constants.CommerceActionKeys;
import com.liferay.commerce.model.CommercePaymentMethod;
import com.liferay.commerce.payment.method.CommercePaymentEngineMethod;
import com.liferay.commerce.payment.method.CommercePaymentEngineMethodRegistry;
import com.liferay.commerce.payment.method.CommercePaymentScreenNavigationConstants;
import com.liferay.commerce.payment.method.web.internal.admin.PaymentMethodsCommerceAdminModule;
import com.liferay.commerce.service.CommercePaymentMethodService;
import com.liferay.commerce.util.comparator.CommercePaymentMethodNameComparator;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Andrea Di Giorgi
 */
public class CommercePaymentMethodsDisplayContext {

	public CommercePaymentMethodsDisplayContext(
		CommercePaymentEngineMethodRegistry commercePaymentEngineMethodRegistry,
		CommercePaymentMethodService commercePaymentMethodService,
		PortletResourcePermission portletResourcePermission,
		RenderRequest renderRequest, RenderResponse renderResponse) {

		_commercePaymentEngineMethodRegistry =
			commercePaymentEngineMethodRegistry;
		_commercePaymentMethodService = commercePaymentMethodService;
		_portletResourcePermission = portletResourcePermission;
		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
	}

	public CommercePaymentEngineMethod getCommercePaymentEngineMethod()
		throws PortalException {

		String key = null;

		CommercePaymentMethod commercePaymentMethod =
			getCommercePaymentMethod();

		if (commercePaymentMethod != null) {
			key = commercePaymentMethod.getEngineKey();
		}
		else {
			key = ParamUtil.getString(_renderRequest, "engineKey");

			if (Validator.isNull(key)) {
				return null;
			}
		}

		return _commercePaymentEngineMethodRegistry.getCommercePaymentMethod(
			key);
	}

	public CommercePaymentMethod getCommercePaymentMethod()
		throws PortalException {

		if (_commercePaymentMethod != null) {
			return _commercePaymentMethod;
		}

		long commercePaymentMethodId = ParamUtil.getLong(
			_renderRequest, "commercePaymentMethodId");
		String engineKey = ParamUtil.getString(_renderRequest, "engineKey");

		if (commercePaymentMethodId > 0) {
			_commercePaymentMethod =
				_commercePaymentMethodService.getCommercePaymentMethod(
					commercePaymentMethodId);
		}
		else if (Validator.isNotNull(engineKey)) {
			_commercePaymentMethod = getDefaultCommercePaymentMethod(engineKey);
		}

		return _commercePaymentMethod;
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

	public SearchContainer<CommercePaymentMethod> getSearchContainer()
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

		List<CommercePaymentMethod> results;

		if (active != null) {
			results = _commercePaymentMethodService.getCommercePaymentMethods(
				themeDisplay.getScopeGroupId(), active);
		}
		else {
			results = _commercePaymentMethodService.getCommercePaymentMethods(
				themeDisplay.getScopeGroupId());
		}

		if ((active == null) || !active) {
			results = addDefaultCommercePaymentMethods(results);
		}

		results.sort(
			new CommercePaymentMethodNameComparator(themeDisplay.getLocale()));

		_searchContainer.setTotal(results.size());
		_searchContainer.setResults(results);

		return _searchContainer;
	}

	public String getSelectedScreenNavigationCategoryKey() {
		return ParamUtil.getString(
			_renderRequest, "screenNavigationCategoryKey",
			CommercePaymentScreenNavigationConstants.
				CATEGORY_KEY_COMMERCE_PAYMENT_METHOD_DETAILS);
	}

	public boolean hasManageCommercePaymentMethodsPermission() {
		ThemeDisplay themeDisplay = (ThemeDisplay)_renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		return _portletResourcePermission.contains(
			themeDisplay.getPermissionChecker(), themeDisplay.getScopeGroupId(),
			CommerceActionKeys.MANAGE_COMMERCE_PAYMENT_METHODS);
	}

	protected List<CommercePaymentMethod> addDefaultCommercePaymentMethods(
			List<CommercePaymentMethod> commercePaymentMethods)
		throws PortalException {

		commercePaymentMethods = ListUtil.copy(commercePaymentMethods);

		Map<String, CommercePaymentEngineMethod> commercePaymentEngines =
			_commercePaymentEngineMethodRegistry.
				getCommercePaymentEngineMethods();

		Set<String> commerceEngineKeys = new TreeSet<>(
			commercePaymentEngines.keySet());

		for (CommercePaymentMethod commercePaymentMethod :
				commercePaymentMethods) {

			commerceEngineKeys.remove(commercePaymentMethod.getEngineKey());
		}

		for (String name : commerceEngineKeys) {
			CommercePaymentMethod commercePaymentMethod =
				getDefaultCommercePaymentMethod(name);

			commercePaymentMethods.add(commercePaymentMethod);
		}

		return commercePaymentMethods;
	}

	protected CommercePaymentMethod getDefaultCommercePaymentMethod(
			String engineKey)
		throws PortalException {

		CommercePaymentEngineMethod commercePaymentEngineMethod =
			_commercePaymentEngineMethodRegistry.getCommercePaymentMethod(
				engineKey);

		CommercePaymentMethod commercePaymentMethod =
			_commercePaymentMethodService.createCommercePaymentMethod(0L);

		Locale locale = LocaleUtil.getSiteDefault();

		commercePaymentMethod.setName(
			commercePaymentEngineMethod.getName(locale), locale);
		commercePaymentMethod.setDescription(
			commercePaymentEngineMethod.getDescription(locale), locale);

		commercePaymentMethod.setEngineKey(engineKey);

		return commercePaymentMethod;
	}

	protected String getNavigation() {
		return ParamUtil.getString(_renderRequest, "navigation");
	}

	private final CommercePaymentEngineMethodRegistry
		_commercePaymentEngineMethodRegistry;
	private CommercePaymentMethod _commercePaymentMethod;
	private final CommercePaymentMethodService _commercePaymentMethodService;
	private final PortletResourcePermission _portletResourcePermission;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;
	private SearchContainer<CommercePaymentMethod> _searchContainer;

}