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

package com.liferay.commerce.account.web.internal.frontend;

import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountService;
import com.liferay.commerce.account.web.internal.model.Account;
import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.context.CommerceContextFactory;
import com.liferay.commerce.frontend.ClayTable;
import com.liferay.commerce.frontend.ClayTableAction;
import com.liferay.commerce.frontend.ClayTableActionProvider;
import com.liferay.commerce.frontend.ClayTableSchema;
import com.liferay.commerce.frontend.ClayTableSchemaBuilder;
import com.liferay.commerce.frontend.ClayTableSchemaBuilderFactory;
import com.liferay.commerce.frontend.ClayTableSchemaField;
import com.liferay.commerce.frontend.CommerceDataSetDataProvider;
import com.liferay.commerce.frontend.Filter;
import com.liferay.commerce.frontend.Pagination;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.service.CommerceAddressService;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.portlet.PortletQName;
import com.liferay.portal.kernel.portlet.PortletURLFactory;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.webserver.WebServerServletTokenUtil;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"commerce.data.provider.key=" + CommerceAccountClayTable.NAME,
		"commerce.table.name=" + CommerceAccountClayTable.NAME
	},
	service = {
		ClayTable.class, ClayTableActionProvider.class,
		CommerceDataSetDataProvider.class
	}
)
public class CommerceAccountClayTable
	implements ClayTable, ClayTableActionProvider,
			   CommerceDataSetDataProvider<Account> {

	public static final String NAME = "commerceAccounts";

	@Override
	public List<ClayTableAction> clayTableActions(
			HttpServletRequest httpServletRequest, long groupId, Object model)
		throws PortalException {

		List<ClayTableAction> clayTableActions = new ArrayList<>();

		Account account = (Account)model;

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		if (_modelResourcePermission.contains(
				themeDisplay.getPermissionChecker(), account.getAccountId(),
				ActionKeys.VIEW)) {

			String viewURL = _getAccountViewDetailURL(
				account.getAccountId(), httpServletRequest);

			ClayTableAction clayTableViewAction = new ClayTableAction(
				StringPool.BLANK, viewURL, StringPool.BLANK,
				LanguageUtil.get(httpServletRequest, "view"), null, false,
				false);

			clayTableActions.add(clayTableViewAction);

			CommerceContext commerceContext =
				(CommerceContext)httpServletRequest.getAttribute(
					CommerceWebKeys.COMMERCE_CONTEXT);

			CommerceAccount currentCommerceAccount =
				commerceContext.getCommerceAccount();

			if (((currentCommerceAccount == null) ||
				 (account.getAccountId() !=
					 currentCommerceAccount.getCommerceAccountId())) &&
				account.getActive()) {

				ClayTableAction clayTableSetActiveAction = new ClayTableAction(
					StringPool.BLANK, StringPool.POUND, StringPool.BLANK,
					LanguageUtil.get(httpServletRequest, "select"),
					"setCurrentAccount('" + account.getAccountId() + "')",
					false, false);

				clayTableActions.add(clayTableSetActiveAction);
			}
		}

		if (_modelResourcePermission.contains(
				themeDisplay.getPermissionChecker(), account.getAccountId(),
				ActionKeys.UPDATE)) {

			String toggleActiveJavascript =
				"toggleActiveCommerceAccount('" + account.getAccountId() + "')";

			ClayTableAction clayTableSetActiveAction = new ClayTableAction(
				"commerce-button--good", StringPool.POUND, StringPool.BLANK,
				LanguageUtil.get(httpServletRequest, "activate"),
				toggleActiveJavascript, false, false);

			if (account.getActive()) {
				clayTableSetActiveAction = new ClayTableAction(
					"commerce-button--bad", StringPool.POUND, StringPool.BLANK,
					LanguageUtil.get(httpServletRequest, "deactivate"),
					toggleActiveJavascript, false, false);
			}

			clayTableActions.add(clayTableSetActiveAction);
		}

		return clayTableActions;
	}

	@Override
	public int countItems(HttpServletRequest httpServletRequest, Filter filter)
		throws PortalException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		AccountFilterImpl accountFilterImpl = (AccountFilterImpl)filter;

		CommerceContext commerceContext = _commerceContextFactory.create(
			themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(),
			_portal.getUserId(httpServletRequest), 0, 0);

		return _commerceAccountService.getUserCommerceAccountsCount(
			_portal.getUserId(httpServletRequest),
			CommerceAccountConstants.DEFAULT_PARENT_ACCOUNT_ID,
			commerceContext.getCommerceSiteType(),
			accountFilterImpl.getKeywords());
	}

	@Override
	public ClayTableSchema getClayTableSchema() {
		ClayTableSchemaBuilder clayTableSchemaBuilder =
			_clayTableSchemaBuilderFactory.clayTableSchemaBuilder();

		ClayTableSchemaField nameField = clayTableSchemaBuilder.addField(
			"name", "name");

		nameField.setContentRenderer("commerceTableCellImageName");

		clayTableSchemaBuilder.addField("accountId", "id");

		clayTableSchemaBuilder.addField("email", "email");

		clayTableSchemaBuilder.addField("address", "address");

		ClayTableSchemaField statusField = clayTableSchemaBuilder.addField(
			"active", "status");

		statusField.setContentRenderer("commerceTableCellActive");

		return clayTableSchemaBuilder.build();
	}

	@Override
	public String getId() {
		return NAME;
	}

	@Override
	public List<Account> getItems(
			HttpServletRequest httpServletRequest, Filter filter,
			Pagination pagination, Sort sort)
		throws PortalException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		List<Account> accounts = new ArrayList<>();

		AccountFilterImpl accountFilterImpl = (AccountFilterImpl)filter;

		CommerceContext commerceContext = _commerceContextFactory.create(
			themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(),
			_portal.getUserId(httpServletRequest), 0, 0);

		List<CommerceAccount> commerceAccounts =
			_commerceAccountService.getUserCommerceAccounts(
				_portal.getUserId(httpServletRequest),
				CommerceAccountConstants.DEFAULT_PARENT_ACCOUNT_ID,
				commerceContext.getCommerceSiteType(),
				accountFilterImpl.getKeywords(), pagination.getStartPosition(),
				pagination.getEndPosition());

		for (CommerceAccount commerceAccount : commerceAccounts) {
			StringBundler thumbnailSB = new StringBundler(5);

			thumbnailSB.append(themeDisplay.getPathImage());

			if (commerceAccount.getLogoId() == 0) {
				thumbnailSB.append("/organization_logo?img_id=0");
			}
			else {
				thumbnailSB.append("/organization_logo?img_id=");
				thumbnailSB.append(commerceAccount.getLogoId());
				thumbnailSB.append("&t=");
				thumbnailSB.append(
					WebServerServletTokenUtil.getToken(
						commerceAccount.getLogoId()));
			}

			String statusLabel = "inactive";

			if (commerceAccount.isActive()) {
				statusLabel = "active";
			}

			accounts.add(
				new Account(
					commerceAccount.getCommerceAccountId(),
					commerceAccount.isActive(), commerceAccount.getName(),
					commerceAccount.getEmail(),
					_getDefaultBillingCommerceAddress(commerceAccount),
					LanguageUtil.get(httpServletRequest, statusLabel),
					thumbnailSB.toString(),
					_getAccountViewDetailURL(
						commerceAccount.getCommerceAccountId(),
						httpServletRequest)));
		}

		return accounts;
	}

	@Override
	public boolean isShowActionsMenu() {
		return true;
	}

	private String _getAccountViewDetailURL(
			long commerceAccountId, HttpServletRequest httpServletRequest)
		throws PortalException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		if (!_modelResourcePermission.contains(
				themeDisplay.getPermissionChecker(), commerceAccountId,
				ActionKeys.VIEW)) {

			return StringPool.BLANK;
		}

		PortletURL viewURL = PortletProviderUtil.getPortletURL(
			httpServletRequest, CommerceAccount.class.getName(),
			PortletProvider.Action.VIEW);

		viewURL.setParameter(
			"commerceAccountId", String.valueOf(commerceAccountId));

		PortletURL backURL = PortletProviderUtil.getPortletURL(
			httpServletRequest, CommerceAccount.class.getName(),
			PortletProvider.Action.MANAGE);

		viewURL.setParameter(
			PortletQName.PUBLIC_RENDER_PARAMETER_NAMESPACE + "backURL",
			backURL.toString());

		return viewURL.toString();
	}

	private String _getDefaultBillingCommerceAddress(
			CommerceAccount commerceAccount)
		throws PortalException {

		CommerceAddress commerceAddress =
			_commerceAddressService.fetchCommerceAddress(
				commerceAccount.getDefaultBillingAddressId());

		if (commerceAddress == null) {
			return null;
		}

		StringBundler sb = new StringBundler(5);

		sb.append(commerceAddress.getStreet1());
		sb.append(StringPool.SPACE);
		sb.append(commerceAddress.getCity());
		sb.append(StringPool.SPACE);
		sb.append(commerceAddress.getZip());

		return sb.toString();
	}

	@Reference
	private ClayTableSchemaBuilderFactory _clayTableSchemaBuilderFactory;

	@Reference
	private CommerceAccountService _commerceAccountService;

	@Reference
	private CommerceAddressService _commerceAddressService;

	@Reference
	private CommerceContextFactory _commerceContextFactory;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.account.model.CommerceAccount)"
	)
	private ModelResourcePermission<CommerceAccount> _modelResourcePermission;

	@Reference
	private Portal _portal;

	@Reference
	private PortletURLFactory _portletURLFactory;

}