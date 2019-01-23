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

package com.liferay.commerce.account.web.internal.display.context;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountService;
import com.liferay.commerce.account.util.CommerceAccountHelper;
import com.liferay.commerce.account.web.internal.display.context.util.CommerceAccountRequestHelper;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.service.CommerceCountryService;
import com.liferay.commerce.service.CommerceRegionService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletQName;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public abstract class BaseCommerceAccountDisplayContext {

	public BaseCommerceAccountDisplayContext(
		CommerceAccountHelper commerceAccountHelper,
		CommerceAccountService commerceAccountService,
		CommerceCountryService commerceCountryService,
		CommerceRegionService commerceRegionService,
		HttpServletRequest httpServletRequest,
		ModelResourcePermission<CommerceAccount> modelResourcePermission,
		Portal portal) {

		_commerceAccountHelper = commerceAccountHelper;

		this.commerceAccountService = commerceAccountService;
		this.commerceCountryService = commerceCountryService;
		this.commerceRegionService = commerceRegionService;
		this.modelResourcePermission = modelResourcePermission;
		this.portal = portal;

		commerceAccountRequestHelper = new CommerceAccountRequestHelper(
			httpServletRequest);

		_defaultOrderByCol = "create-date";
		_defaultOrderByType = "desc";
	}

	public List<CommerceCountry> getCommerceCountries() {
		return commerceCountryService.getCommerceCountries(
			commerceAccountRequestHelper.getScopeGroupId(), true);
	}

	public List<CommerceRegion> getCommerceRegions(long commerceCountryId)
		throws PortalException {

		return commerceRegionService.getCommerceRegions(
			commerceCountryId, true);
	}

	public CommerceAccount getCurrentCommerceAccount() throws PortalException {
		long commerceAccountId = ParamUtil.getLong(
			commerceAccountRequestHelper.getRequest(), "commerceAccountId");

		if (commerceAccountId > 0) {
			return commerceAccountService.getCommerceAccount(commerceAccountId);
		}

		return _getCurrentAccount();
	}

	public long getCurrentCommerceAccountId() throws PortalException {
		CommerceAccount commerceAccount = getCurrentCommerceAccount();

		if (commerceAccount != null) {
			return commerceAccount.getCommerceAccountId();
		}

		return 0;
	}

	public String getDisplayStyle() {
		if (_displayStyle == null) {
			_displayStyle = ParamUtil.getString(
				commerceAccountRequestHelper.getRequest(), "displayStyle");
		}

		return _displayStyle;
	}

	public String getKeywords() {
		if (Validator.isNotNull(_keywords)) {
			return _keywords;
		}

		HttpServletRequest httpServletRequest =
			PortalUtil.getOriginalServletRequest(
				commerceAccountRequestHelper.getRequest());

		_keywords = ParamUtil.getString(httpServletRequest, "q", null);

		if (_keywords == null) {
			return StringPool.BLANK;
		}

		return _keywords;
	}

	public String getOrderByCol() {
		return ParamUtil.getString(
			commerceAccountRequestHelper.getRequest(),
			SearchContainer.DEFAULT_ORDER_BY_COL_PARAM, _defaultOrderByCol);
	}

	public String getOrderByType() {
		return ParamUtil.getString(
			commerceAccountRequestHelper.getRequest(),
			SearchContainer.DEFAULT_ORDER_BY_TYPE_PARAM, _defaultOrderByType);
	}

	public String getPath(CommerceAccount commerceAccount)
		throws PortalException {

		CommerceAccount topCommerceAccount = _getCurrentAccount();

		List<CommerceAccount> commerceAccounts = new ArrayList<>();

		while (commerceAccount != null) {
			if (commerceAccount.getCommerceAccountId() ==
					topCommerceAccount.getCommerceAccountId()) {

				break;
			}

			commerceAccount = commerceAccount.getParentCommerceAccount();

			if (commerceAccount == null) {
				break;
			}

			commerceAccounts.add(commerceAccount);
		}

		int size = commerceAccounts.size();

		StringBundler sb = new StringBundler(((size - 1) * 4) + 1);

		commerceAccount = commerceAccounts.get(size - 1);

		sb.append(commerceAccount.getName());

		for (int i = size - 2; i >= 0; i--) {
			commerceAccount = commerceAccounts.get(i);

			sb.append(StringPool.SPACE);
			sb.append(StringPool.GREATER_THAN);
			sb.append(StringPool.SPACE);
			sb.append(commerceAccount.getName());
		}

		return sb.toString();
	}

	public PortletURL getPortletURL() throws PortalException {
		LiferayPortletResponse liferayPortletResponse =
			commerceAccountRequestHelper.getLiferayPortletResponse();

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		HttpServletRequest httpServletRequest =
			PortalUtil.getOriginalServletRequest(
				commerceAccountRequestHelper.getRequest());

		String backURL = ParamUtil.getString(
			httpServletRequest,
			PortletQName.PUBLIC_RENDER_PARAMETER_NAMESPACE + "backURL");

		if (Validator.isNotNull(backURL)) {
			portletURL.setParameter(
				PortletQName.PUBLIC_RENDER_PARAMETER_NAMESPACE + "backURL",
				backURL);
		}

		String redirect = ParamUtil.getString(
			commerceAccountRequestHelper.getRequest(), "redirect");

		if (Validator.isNotNull(redirect)) {
			portletURL.setParameter("redirect", redirect);
		}

		String delta = ParamUtil.getString(
			commerceAccountRequestHelper.getRequest(), "delta");

		if (Validator.isNotNull(delta)) {
			portletURL.setParameter("delta", delta);
		}

		String deltaEntry = ParamUtil.getString(
			commerceAccountRequestHelper.getRequest(), "deltaEntry");

		if (Validator.isNotNull(deltaEntry)) {
			portletURL.setParameter("deltaEntry", deltaEntry);
		}

		String displayStyle = ParamUtil.getString(
			commerceAccountRequestHelper.getRequest(), "displayStyle");

		if (Validator.isNotNull(displayStyle)) {
			portletURL.setParameter("displayStyle", getDisplayStyle());
		}

		String keywords = getKeywords();

		if (Validator.isNotNull(keywords)) {
			portletURL.setParameter("keywords", keywords);
		}

		CommerceAccount commerceAccount = getCurrentCommerceAccount();

		if (commerceAccount != null) {
			portletURL.setParameter(
				"commerceAccountId",
				String.valueOf(commerceAccount.getCommerceAccountId()));
		}

		return portletURL;
	}

	public boolean hasEditCommerceAccountPermissions(long commerceAccountId)
		throws PortalException {

		return modelResourcePermission.contains(
			commerceAccountRequestHelper.getPermissionChecker(),
			commerceAccountId, ActionKeys.UPDATE);
	}

	protected void setDefaultOrderByCol(String defaultOrderByCol) {
		_defaultOrderByCol = defaultOrderByCol;
	}

	protected void setDefaultOrderByType(String defaultOrderByType) {
		_defaultOrderByType = defaultOrderByType;
	}

	protected final CommerceAccountRequestHelper commerceAccountRequestHelper;
	protected final CommerceAccountService commerceAccountService;
	protected final CommerceCountryService commerceCountryService;
	protected final CommerceRegionService commerceRegionService;
	protected final ModelResourcePermission<CommerceAccount>
		modelResourcePermission;
	protected final Portal portal;

	private CommerceAccount _getCurrentAccount() throws PortalException {
		return _commerceAccountHelper.getCurrentCommerceAccount(
			commerceAccountRequestHelper.getRequest());
	}

	private final CommerceAccountHelper _commerceAccountHelper;
	private String _defaultOrderByCol;
	private String _defaultOrderByType;
	private String _displayStyle;
	private String _keywords;

}