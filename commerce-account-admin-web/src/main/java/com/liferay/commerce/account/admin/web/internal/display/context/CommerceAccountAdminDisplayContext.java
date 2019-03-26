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

package com.liferay.commerce.account.admin.web.internal.display.context;

import com.liferay.commerce.account.admin.web.internal.display.context.util.CommerceAccountAdminRequestHelper;
import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountService;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.service.CommerceAddressService;
import com.liferay.commerce.service.CommerceCountryService;
import com.liferay.commerce.service.CommerceRegionService;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.service.permission.PortalPermissionUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.taglib.util.CustomAttributesUtil;
import com.liferay.users.admin.configuration.UserFileUploadsConfiguration;

import java.util.List;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceAccountAdminDisplayContext {

	public CommerceAccountAdminDisplayContext(
		ModelResourcePermission<CommerceAccount>
			commerceAccountModelResourcePermission,
		CommerceAccountService commerceAccountService,
		CommerceAddressService commerceAddressService,
		CommerceCountryService commerceCountryService,
		CommerceRegionService commerceRegionService,
		RenderRequest renderRequest,
		UserFileUploadsConfiguration userFileUploadsConfiguration) {

		_commerceAccountModelResourcePermission =
			commerceAccountModelResourcePermission;
		_commerceAccountService = commerceAccountService;
		_commerceAddressService = commerceAddressService;
		_commerceCountryService = commerceCountryService;
		_commerceRegionService = commerceRegionService;
		_userFileUploadsConfiguration = userFileUploadsConfiguration;

		_commerceAccountAdminRequestHelper =
			new CommerceAccountAdminRequestHelper(renderRequest);

		_portalPreferences = PortletPreferencesFactoryUtil.getPortalPreferences(
			renderRequest);
	}

	public CommerceAccount getCommerceAccount() throws PortalException {
		if (_commerceAccount != null) {
			return _commerceAccount;
		}

		long commerceAccountId = ParamUtil.getLong(
			_commerceAccountAdminRequestHelper.getRequest(),
			"commerceAccountId");

		if (commerceAccountId > 0) {
			_commerceAccount = _commerceAccountService.getCommerceAccount(
				commerceAccountId);
		}

		return _commerceAccount;
	}

	public long getCommerceAccountId() throws PortalException {
		CommerceAccount commerceAccount = getCommerceAccount();

		if (commerceAccount == null) {
			return 0;
		}

		return commerceAccount.getCommerceAccountId();
	}

	public List<CommerceAddress> getCommerceAddresses() throws PortalException {
		CommerceAccount commerceAccount = getCommerceAccount();

		if (commerceAccount == null) {
			return null;
		}

		return _commerceAddressService.getCommerceAddresses(
			_commerceAccountAdminRequestHelper.getScopeGroupId(),
			CommerceAccount.class.getName(),
			commerceAccount.getCommerceAccountId());
	}

	public List<CommerceCountry> getCommerceCountries() {
		return _commerceCountryService.getCommerceCountries(
			_commerceAccountAdminRequestHelper.getScopeGroupId(), true);
	}

	public List<CommerceRegion> getCommerceRegions(long commerceCountryId) {
		return _commerceRegionService.getCommerceRegions(
			commerceCountryId, true);
	}

	public long getParentCommerceAccountId() {
		return ParamUtil.getLong(
			_commerceAccountAdminRequestHelper.getRequest(),
			"parentCommerceAccountId");
	}

	public PortletURL getPortletURL() {
		LiferayPortletResponse liferayPortletResponse =
			_commerceAccountAdminRequestHelper.getLiferayPortletResponse();

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		HttpServletRequest httpServletRequest =
			_commerceAccountAdminRequestHelper.getRequest();

		String redirect = ParamUtil.getString(httpServletRequest, "redirect");

		if (Validator.isNotNull(redirect)) {
			portletURL.setParameter("redirect", redirect);
		}

		long commerceAccountId = ParamUtil.getLong(
			httpServletRequest, "commerceAccountId");

		if (commerceAccountId > 0) {
			portletURL.setParameter(
				"commerceAccountId", String.valueOf(commerceAccountId));
		}

		String delta = ParamUtil.getString(httpServletRequest, "delta");

		if (Validator.isNotNull(delta)) {
			portletURL.setParameter("delta", delta);
		}

		String deltaEntry = ParamUtil.getString(
			httpServletRequest, "deltaEntry");

		if (Validator.isNotNull(deltaEntry)) {
			portletURL.setParameter("deltaEntry", deltaEntry);
		}

		String keywords = ParamUtil.getString(httpServletRequest, "keywords");

		if (Validator.isNotNull(keywords)) {
			portletURL.setParameter("keywords", keywords);
		}

		portletURL.setParameter("activeNavigation", getNavigation("active"));
		portletURL.setParameter("typeNavigation", getNavigation("type"));

		return portletURL;
	}

	public SearchContainer<CommerceAccount> getSearchContainer()
		throws PortalException {

		if (_searchContainer != null) {
			return _searchContainer;
		}

		_searchContainer = new SearchContainer<>(
			_commerceAccountAdminRequestHelper.getLiferayPortletRequest(),
			getPortletURL(), null, "there-are-no-accounts");

		setOrderByColAndType(
			CommerceAccount.class, _searchContainer, "name", "asc");

		_searchContainer.setRowChecker(
			new EmptyOnClickRowChecker(
				_commerceAccountAdminRequestHelper.
					getLiferayPortletResponse()));

		int total = _commerceAccountService.getUserCommerceAccountsCount(
			_commerceAccountAdminRequestHelper.getUserId(),
			getParentCommerceAccountId(), getCommerceSiteType(), getKeywords(),
			getActive());

		List<CommerceAccount> results =
			_commerceAccountService.getUserCommerceAccounts(
				_commerceAccountAdminRequestHelper.getUserId(),
				getParentCommerceAccountId(), getCommerceSiteType(),
				getKeywords(), getActive(), _searchContainer.getStart(),
				_searchContainer.getEnd());

		_searchContainer.setTotal(total);
		_searchContainer.setResults(results);

		return _searchContainer;
	}

	public UserFileUploadsConfiguration getUserFileUploadsConfiguration() {
		return _userFileUploadsConfiguration;
	}

	public boolean hasCustomAttributesAvailable() throws Exception {
		return CustomAttributesUtil.hasCustomAttributes(
			_commerceAccountAdminRequestHelper.getCompanyId(),
			CommerceAccount.class.getName(), getCommerceAccountId(), null);
	}

	public boolean hasPermission(long commerceAccountId, String actionId)
		throws PortalException {

		return _commerceAccountModelResourcePermission.contains(
			_commerceAccountAdminRequestHelper.getPermissionChecker(),
			commerceAccountId, actionId);
	}

	public boolean hasPermission(String actionId) {
		return PortalPermissionUtil.contains(
			_commerceAccountAdminRequestHelper.getPermissionChecker(),
			actionId);
	}

	protected Boolean getActive() {
		String navigation = getNavigation("active");

		if (navigation.equals("active")) {
			return true;
		}
		else if (navigation.equals("inactive")) {
			return false;
		}

		return null;
	}

	protected int getCommerceSiteType() {
		String navigation = getNavigation("type");

		if (navigation.equals(
				CommerceAccountConstants.getAccountTypeLabel(
					CommerceAccountConstants.ACCOUNT_TYPE_BUSINESS))) {

			return CommerceAccountConstants.SITE_TYPE_B2B;
		}
		else if (navigation.equals(
					CommerceAccountConstants.getAccountTypeLabel(
						CommerceAccountConstants.ACCOUNT_TYPE_PERSONAL))) {

			return CommerceAccountConstants.SITE_TYPE_B2C;
		}

		return CommerceAccountConstants.SITE_TYPE_B2C_B2B;
	}

	protected String getKeywords() {
		if (_keywords != null) {
			return _keywords;
		}

		_keywords = ParamUtil.getString(
			_commerceAccountAdminRequestHelper.getRequest(), "keywords");

		return _keywords;
	}

	protected String getNavigation(String suffix) {
		return ParamUtil.getString(
			_commerceAccountAdminRequestHelper.getRequest(),
			suffix + "Navigation", "all");
	}

	protected <T> void setOrderByColAndType(
		Class<T> clazz, SearchContainer<T> searchContainer,
		String defaultOrderByCol, String defaultOrderByType) {

		HttpServletRequest httpServletRequest =
			_commerceAccountAdminRequestHelper.getRequest();

		String orderByCol = ParamUtil.getString(
			httpServletRequest, searchContainer.getOrderByColParam());
		String orderByType = ParamUtil.getString(
			httpServletRequest, searchContainer.getOrderByTypeParam());

		String namespace = _commerceAccountAdminRequestHelper.getPortletId();
		String prefix = TextFormatter.format(
			clazz.getSimpleName(), TextFormatter.K);

		if (Validator.isNotNull(orderByCol) &&
			Validator.isNotNull(orderByType)) {

			_portalPreferences.setValue(
				namespace, prefix + "-order-by-col", orderByCol);
			_portalPreferences.setValue(
				namespace, prefix + "-order-by-type", orderByType);
		}
		else {
			orderByCol = _portalPreferences.getValue(
				namespace, prefix + "-order-by-col", defaultOrderByCol);
			orderByType = _portalPreferences.getValue(
				namespace, prefix + "-order-by-type", defaultOrderByType);
		}

		searchContainer.setOrderByCol(orderByCol);
		searchContainer.setOrderByType(orderByType);
	}

	private CommerceAccount _commerceAccount;
	private final CommerceAccountAdminRequestHelper
		_commerceAccountAdminRequestHelper;
	private final ModelResourcePermission<CommerceAccount>
		_commerceAccountModelResourcePermission;
	private final CommerceAccountService _commerceAccountService;
	private final CommerceAddressService _commerceAddressService;
	private final CommerceCountryService _commerceCountryService;
	private final CommerceRegionService _commerceRegionService;
	private String _keywords;
	private final PortalPreferences _portalPreferences;
	private SearchContainer<CommerceAccount> _searchContainer;
	private final UserFileUploadsConfiguration _userFileUploadsConfiguration;

}