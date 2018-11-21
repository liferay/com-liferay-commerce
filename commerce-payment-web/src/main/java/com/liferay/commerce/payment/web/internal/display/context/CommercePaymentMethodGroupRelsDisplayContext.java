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
import com.liferay.commerce.payment.method.CommercePaymentEngineMethod;
import com.liferay.commerce.payment.method.CommercePaymentEngineMethodRegistry;
import com.liferay.commerce.payment.method.CommercePaymentScreenNavigationConstants;
import com.liferay.commerce.payment.method.web.internal.admin.PaymentMethodsCommerceAdminModule;
import com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel;
import com.liferay.commerce.payment.service.CommercePaymentMethodGroupRelService;
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
public class CommercePaymentMethodGroupRelsDisplayContext {

	public CommercePaymentMethodGroupRelsDisplayContext(
		CommercePaymentEngineMethodRegistry commercePaymentEngineMethodRegistry,
		CommercePaymentMethodGroupRelService CommercePaymentMethodGroupRelService,
		PortletResourcePermission portletResourcePermission,
		RenderRequest renderRequest, RenderResponse renderResponse) {

		_commercePaymentEngineMethodRegistry =
			commercePaymentEngineMethodRegistry;
		_CommercePaymentMethodGroupRelService = CommercePaymentMethodGroupRelService;
		_portletResourcePermission = portletResourcePermission;
		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
	}

	public CommercePaymentEngineMethod getCommercePaymentEngineMethod()
		throws PortalException {

		String key = null;

		CommercePaymentMethodGroupRel CommercePaymentMethodGroupRel =
			getCommercePaymentMethodGroupRel();

		if (CommercePaymentMethodGroupRel != null) {
			key = CommercePaymentMethodGroupRel.getEngineKey();
		}
		else {
			key = ParamUtil.getString(_renderRequest, "engineKey");

			if (Validator.isNull(key)) {
				return null;
			}
		}

		return _commercePaymentEngineMethodRegistry.getCommercePaymentMethodGroupRel(
			key);
	}

	public CommercePaymentMethodGroupRel getCommercePaymentMethodGroupRel()
		throws PortalException {

		if (_CommercePaymentMethodGroupRel != null) {
			return _CommercePaymentMethodGroupRel;
		}

		long CommercePaymentMethodGroupRelId = ParamUtil.getLong(
			_renderRequest, "CommercePaymentMethodGroupRelId");
		String engineKey = ParamUtil.getString(_renderRequest, "engineKey");

		if (CommercePaymentMethodGroupRelId > 0) {
			_CommercePaymentMethodGroupRel =
				_CommercePaymentMethodGroupRelService.getCommercePaymentMethodGroupRel(
					CommercePaymentMethodGroupRelId);
		}
		else if (Validator.isNotNull(engineKey)) {
			_CommercePaymentMethodGroupRel = getDefaultCommercePaymentMethodGroupRel(engineKey);
		}

		return _CommercePaymentMethodGroupRel;
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

		List<CommercePaymentMethodGroupRel> results;

		if (active != null) {
			results = _CommercePaymentMethodGroupRelService.getCommercePaymentMethodGroupRels(
				themeDisplay.getScopeGroupId(), active);
		}
		else {
			results = _CommercePaymentMethodGroupRelService.getCommercePaymentMethodGroupRels(
				themeDisplay.getScopeGroupId());
		}

		if ((active == null) || !active) {
			results = addDefaultCommercePaymentMethodGroupRels(results);
		}

		results.sort(
			new CommercePaymentMethodGroupRelNameComparator(themeDisplay.getLocale()));

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

	public boolean hasManageCommercePaymentMethodGroupRelsPermission() {
		ThemeDisplay themeDisplay = (ThemeDisplay)_renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		return _portletResourcePermission.contains(
			themeDisplay.getPermissionChecker(), themeDisplay.getScopeGroupId(),
			CommerceActionKeys.MANAGE_COMMERCE_PAYMENT_METHODS);
	}

	protected List<CommercePaymentMethodGroupRel> addDefaultCommercePaymentMethodGroupRels(
			List<CommercePaymentMethodGroupRel> CommercePaymentMethodGroupRels)
		throws PortalException {

		CommercePaymentMethodGroupRels = ListUtil.copy(CommercePaymentMethodGroupRels);

		Map<String, CommercePaymentEngineMethod> commercePaymentEngines =
			_commercePaymentEngineMethodRegistry.
				getCommercePaymentEngineMethods();

		Set<String> commerceEngineKeys = new TreeSet<>(
			commercePaymentEngines.keySet());

		for (CommercePaymentMethodGroupRel CommercePaymentMethodGroupRel :
				CommercePaymentMethodGroupRels) {

			commerceEngineKeys.remove(CommercePaymentMethodGroupRel.getEngineKey());
		}

		for (String name : commerceEngineKeys) {
			CommercePaymentMethodGroupRel CommercePaymentMethodGroupRel =
				getDefaultCommercePaymentMethodGroupRel(name);

			CommercePaymentMethodGroupRels.add(CommercePaymentMethodGroupRel);
		}

		return CommercePaymentMethodGroupRels;
	}

	protected CommercePaymentMethodGroupRel getDefaultCommercePaymentMethodGroupRel(
			String engineKey)
		throws PortalException {

		CommercePaymentEngineMethod commercePaymentEngineMethod =
			_commercePaymentEngineMethodRegistry.getCommercePaymentMethodGroupRel(
				engineKey);

		CommercePaymentMethodGroupRel CommercePaymentMethodGroupRel =
			_CommercePaymentMethodGroupRelService.createCommercePaymentMethodGroupRel(0L);

		Locale locale = LocaleUtil.getSiteDefault();

		CommercePaymentMethodGroupRel.setName(
			commercePaymentEngineMethod.getName(locale), locale);
		CommercePaymentMethodGroupRel.setDescription(
			commercePaymentEngineMethod.getDescription(locale), locale);

		CommercePaymentMethodGroupRel.setEngineKey(engineKey);

		return CommercePaymentMethodGroupRel;
	}

	protected String getNavigation() {
		return ParamUtil.getString(_renderRequest, "navigation");
	}

	private final CommercePaymentEngineMethodRegistry
		_commercePaymentEngineMethodRegistry;
	private CommercePaymentMethodGroupRel _CommercePaymentMethodGroupRel;
	private final CommercePaymentMethodGroupRelService _CommercePaymentMethodGroupRelService;
	private final PortletResourcePermission _portletResourcePermission;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;
	private SearchContainer<CommercePaymentMethodGroupRel> _searchContainer;

}