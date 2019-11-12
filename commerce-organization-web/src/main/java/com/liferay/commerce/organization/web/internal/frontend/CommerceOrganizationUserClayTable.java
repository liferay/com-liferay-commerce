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

package com.liferay.commerce.organization.web.internal.frontend;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.frontend.ClayTable;
import com.liferay.commerce.frontend.ClayTableAction;
import com.liferay.commerce.frontend.ClayTableActionProvider;
import com.liferay.commerce.frontend.ClayTableSchema;
import com.liferay.commerce.frontend.ClayTableSchemaBuilder;
import com.liferay.commerce.frontend.ClayTableSchemaBuilderFactory;
import com.liferay.commerce.frontend.CommerceDataSetDataProvider;
import com.liferay.commerce.frontend.Filter;
import com.liferay.commerce.frontend.Pagination;
import com.liferay.commerce.organization.web.internal.model.User;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.portlet.PortletQName;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.UserService;
import com.liferay.portal.kernel.service.permission.OrganizationPermissionUtil;
import com.liferay.portal.kernel.service.permission.RolePermissionUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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
		"commerce.data.provider.key=" + CommerceOrganizationUserClayTable.NAME,
		"commerce.table.name=" + CommerceOrganizationUserClayTable.NAME
	},
	service = {
		ClayTable.class, ClayTableActionProvider.class,
		CommerceDataSetDataProvider.class
	}
)
public class CommerceOrganizationUserClayTable
	implements ClayTable, ClayTableActionProvider,
			   CommerceDataSetDataProvider<User> {

	public static final String NAME = "commerceOrganizationUsers";

	@Override
	public List<ClayTableAction> clayTableActions(
			HttpServletRequest httpServletRequest, long groupId, Object model)
		throws PortalException {

		List<ClayTableAction> clayTableActions = new ArrayList<>();

		User user = (User)model;

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		if (OrganizationPermissionUtil.contains(
				permissionChecker, user.getOrganizationId(), ActionKeys.VIEW)) {

			String viewURL = _getOrganizationUserViewDetailURL(
				user.getUserId(), httpServletRequest);

			ClayTableAction viewClayTableAction = new ClayTableAction(
				StringPool.BLANK, viewURL, StringPool.BLANK,
				LanguageUtil.get(httpServletRequest, "view"), false, false);

			clayTableActions.add(viewClayTableAction);
		}

		if (permissionChecker.isCompanyAdmin() ||
			(user.getUserId() != themeDisplay.getUserId())) {

			StringBundler sb = new StringBundler(7);

			sb.append("javascript:deleteCommerceOrganizationUser");
			sb.append(StringPool.OPEN_PARENTHESIS);
			sb.append(StringPool.APOSTROPHE);
			sb.append(user.getUserId());
			sb.append(StringPool.APOSTROPHE);
			sb.append(StringPool.CLOSE_PARENTHESIS);
			sb.append(StringPool.SEMICOLON);

			ClayTableAction clayTableAction = new ClayTableAction(
				StringPool.BLANK, sb.toString(), StringPool.BLANK,
				LanguageUtil.get(httpServletRequest, "delete"), false, false);

			clayTableActions.add(clayTableAction);
		}

		return clayTableActions;
	}

	@Override
	public int countItems(HttpServletRequest httpServletRequest, Filter filter)
		throws PortalException {

		OrganizationFilterImpl organizationFilterImpl =
			(OrganizationFilterImpl)filter;

		return _userService.getOrganizationUsersCount(
			organizationFilterImpl.getOrganizationId(),
			WorkflowConstants.STATUS_ANY);
	}

	@Override
	public ClayTableSchema getClayTableSchema() {
		ClayTableSchemaBuilder clayTableSchemaBuilder =
			_clayTableSchemaBuilderFactory.clayTableSchemaBuilder();

		clayTableSchemaBuilder.addField("name", "name");

		clayTableSchemaBuilder.addField("roles", "roles");

		clayTableSchemaBuilder.addField("email", "email");

		return clayTableSchemaBuilder.build();
	}

	@Override
	public String getId() {
		return NAME;
	}

	@Override
	public List<User> getItems(
			HttpServletRequest httpServletRequest, Filter filter,
			Pagination pagination, Sort sort)
		throws PortalException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		OrganizationFilterImpl organizationFilterImpl =
			(OrganizationFilterImpl)filter;

		List<User> users = new ArrayList<>();

		List<com.liferay.portal.kernel.model.User> userList =
			_userService.getOrganizationUsers(
				organizationFilterImpl.getOrganizationId(),
				WorkflowConstants.STATUS_ANY, pagination.getStartPosition(),
				pagination.getEndPosition(), null);

		for (com.liferay.portal.kernel.model.User user : userList) {
			users.add(
				new User(
					user.getUserId(),
					organizationFilterImpl.getOrganizationId(),
					HtmlUtil.escape(user.getFullName()), user.getEmailAddress(),
					getUserRoles(user, themeDisplay.getPermissionChecker()),
					_getOrganizationUserViewDetailURL(
						user.getUserId(), httpServletRequest)));
		}

		return users;
	}

	@Override
	public boolean isShowActionsMenu() {
		return true;
	}

	protected String[] getUserRoles(
		com.liferay.portal.kernel.model.User user,
		PermissionChecker permissionChecker) {

		List<Role> roles = new ArrayList<>();

		List<Role> roleList = user.getRoles();

		for (Role role : roleList) {
			if (RolePermissionUtil.contains(
					permissionChecker, role.getRoleId(), ActionKeys.VIEW) &&
				(role.getType() == RoleConstants.TYPE_ORGANIZATION)) {

				roles.add(role);
			}
		}

		Stream<Role> stream = roles.stream();

		return stream.map(
			Role::getName
		).toArray(
			String[]::new
		);
	}

	private String _getOrganizationUserViewDetailURL(
			long userId, HttpServletRequest httpServletRequest)
		throws PortalException {

		PortletURL viewURL = PortletProviderUtil.getPortletURL(
			httpServletRequest, CommerceAccount.class.getName(),
			PortletProvider.Action.VIEW);

		viewURL.setParameter(
			"mvcRenderCommandName", "viewCommerceOrganizationUser");

		long organizationId = ParamUtil.getLong(
			httpServletRequest, "organizationId");

		if (organizationId > 0) {
			viewURL.setParameter(
				"organizationId", String.valueOf(organizationId));
		}

		viewURL.setParameter("userId", String.valueOf(userId));

		viewURL.setParameter(
			PortletQName.PUBLIC_RENDER_PARAMETER_NAMESPACE + "backURL",
			_portal.getCurrentURL(httpServletRequest));

		return viewURL.toString();
	}

	@Reference
	private ClayTableSchemaBuilderFactory _clayTableSchemaBuilderFactory;

	@Reference
	private Portal _portal;

	@Reference
	private UserService _userService;

}