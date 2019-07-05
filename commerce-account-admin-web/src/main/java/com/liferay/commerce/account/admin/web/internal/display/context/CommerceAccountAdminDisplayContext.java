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

import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountService;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.taglib.util.CustomAttributesUtil;
import com.liferay.users.admin.configuration.UserFileUploadsConfiguration;

import java.util.List;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceAccountAdminDisplayContext
	extends BaseCommerceAccountAdminDisplayContext<CommerceAccount> {

	public CommerceAccountAdminDisplayContext(
		ModelResourcePermission<CommerceAccount>
			commerceAccountModelResourcePermission,
		CommerceAccountService commerceAccountService,
		RenderRequest renderRequest,
		UserFileUploadsConfiguration userFileUploadsConfiguration) {

		super(
			commerceAccountModelResourcePermission, commerceAccountService,
			renderRequest);

		_userFileUploadsConfiguration = userFileUploadsConfiguration;
	}

	public PortletURL getPortletURL() {
		PortletURL portletURL = super.getPortletURL();

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
			commerceAccountAdminRequestHelper.getLiferayPortletRequest(),
			getPortletURL(), null, "there-are-no-accounts");

		setOrderByColAndType(
			CommerceAccount.class, _searchContainer, "name", "asc");

		_searchContainer.setRowChecker(
			new EmptyOnClickRowChecker(
				commerceAccountAdminRequestHelper.getLiferayPortletResponse()));

		int total = commerceAccountService.getUserCommerceAccountsCount(
			commerceAccountAdminRequestHelper.getUserId(),
			getParentCommerceAccountId(), getCommerceSiteType(), getKeywords(),
			getActive());

		List<CommerceAccount> results =
			commerceAccountService.getUserCommerceAccounts(
				commerceAccountAdminRequestHelper.getUserId(),
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
			commerceAccountAdminRequestHelper.getCompanyId(),
			CommerceAccount.class.getName(), getCommerceAccountId(), null);
	}

	protected boolean getActive() {
		String navigation = getNavigation("active");

		if (navigation.equals("active")) {
			return true;
		}

		return false;
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

	protected String getNavigation(String suffix) {
		return ParamUtil.getString(
			commerceAccountAdminRequestHelper.getRequest(),
			suffix + "Navigation", "all");
	}

	private SearchContainer<CommerceAccount> _searchContainer;
	private final UserFileUploadsConfiguration _userFileUploadsConfiguration;

}