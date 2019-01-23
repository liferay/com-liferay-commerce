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

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountService;
import com.liferay.commerce.account.web.internal.model.AccountRole;
import com.liferay.commerce.frontend.ClayTable;
import com.liferay.commerce.frontend.ClayTableAction;
import com.liferay.commerce.frontend.ClayTableActionProvider;
import com.liferay.commerce.frontend.ClayTableSchema;
import com.liferay.commerce.frontend.ClayTableSchemaBuilder;
import com.liferay.commerce.frontend.ClayTableSchemaBuilderFactory;
import com.liferay.commerce.frontend.CommerceDataSetDataProvider;
import com.liferay.commerce.frontend.Filter;
import com.liferay.commerce.frontend.Pagination;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"commerce.data.provider.key=" + CommerceAccountUserRolesClayTable.NAME,
		"commerce.table.name=" + CommerceAccountUserRolesClayTable.NAME
	},
	service = {
		ClayTable.class, ClayTableActionProvider.class,
		CommerceDataSetDataProvider.class
	}
)
public class CommerceAccountUserRolesClayTable
	implements CommerceDataSetDataProvider<AccountRole>, ClayTable,
			   ClayTableActionProvider {

	public static final String NAME = "commerceAccountUserRoles";

	@Override
	public List<ClayTableAction> clayTableActions(
			HttpServletRequest httpServletRequest, long groupId, Object model)
		throws PortalException {

		List<ClayTableAction> clayTableActions = new ArrayList<>();

		AccountRole accountRole = (AccountRole)model;

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		long commerceAccountId = ParamUtil.getLong(
			httpServletRequest, "commerceAccountId");

		if (_modelResourcePermission.contains(
				themeDisplay.getPermissionChecker(), commerceAccountId,
				ActionKeys.UPDATE)) {

			StringBundler sb = new StringBundler(7);

			sb.append("javascript:deleteAccountRole");
			sb.append(StringPool.OPEN_PARENTHESIS);
			sb.append(StringPool.APOSTROPHE);
			sb.append(accountRole.getRoleId());
			sb.append(StringPool.APOSTROPHE);
			sb.append(StringPool.CLOSE_PARENTHESIS);
			sb.append(StringPool.SEMICOLON);

			ClayTableAction deleteClayTableAction = new ClayTableAction(
				sb.toString(), StringPool.BLANK,
				LanguageUtil.get(httpServletRequest, "delete"), false, false);

			clayTableActions.add(deleteClayTableAction);
		}

		return clayTableActions;
	}

	@Override
	public int countItems(HttpServletRequest httpServletRequest, Filter filter)
		throws PortalException {

		AccountFilterImpl accountFilter = (AccountFilterImpl)filter;

		CommerceAccount commerceAccount =
			_commerceAccountService.getCommerceAccount(
				accountFilter.getAccountId());

		return _userGroupRoleLocalService.getUserGroupRolesCount(
				_portal.getUserId(httpServletRequest),
				commerceAccount.getCommerceAccountGroupId());
	}

	@Override
	public ClayTableSchema getClayTableSchema() {
		ClayTableSchemaBuilder clayTableSchemaBuilder =
			_clayTableSchemaBuilderFactory.clayTableSchemaBuilder();

		clayTableSchemaBuilder.addField("name", "name");

		return clayTableSchemaBuilder.build();
	}

	@Override
	public String getId() {
		return NAME;
	}

	@Override
	public List<AccountRole> getItems(
			HttpServletRequest httpServletRequest, Filter filter,
			Pagination pagination, Sort sort)
		throws PortalException {

		AccountFilterImpl accountFilter = (AccountFilterImpl)filter;

		CommerceAccount commerceAccount =
			_commerceAccountService.getCommerceAccount(
				accountFilter.getAccountId());

		List<UserGroupRole> userGroupRoles =
			_userGroupRoleLocalService.getUserGroupRoles(
				_portal.getUserId(httpServletRequest),
				commerceAccount.getCommerceAccountGroupId());

		List<AccountRole> accountRoles = new ArrayList<>();

		for (UserGroupRole userGroupRole : userGroupRoles) {
			Role role = userGroupRole.getRole();

			accountRoles.add(new AccountRole(role.getRoleId(), role.getName()));
		}

		return accountRoles;
	}

	@Override
	public boolean isShowActionsMenu() {
		return true;
	}

	@Reference
	private ClayTableSchemaBuilderFactory _clayTableSchemaBuilderFactory;

	@Reference
	private CommerceAccountService _commerceAccountService;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.account.model.CommerceAccount)"
	)
	private ModelResourcePermission<CommerceAccount> _modelResourcePermission;

	@Reference
	private Portal _portal;

	@Reference
	private UserGroupRoleLocalService _userGroupRoleLocalService;

}