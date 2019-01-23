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
import com.liferay.commerce.account.model.CommerceAccountUserRel;
import com.liferay.commerce.account.service.CommerceAccountUserRelService;
import com.liferay.commerce.account.web.internal.model.Member;
import com.liferay.commerce.frontend.ClayTable;
import com.liferay.commerce.frontend.ClayTableAction;
import com.liferay.commerce.frontend.ClayTableActionProvider;
import com.liferay.commerce.frontend.ClayTableSchema;
import com.liferay.commerce.frontend.ClayTableSchemaBuilder;
import com.liferay.commerce.frontend.ClayTableSchemaBuilderFactory;
import com.liferay.commerce.frontend.CommerceDataSetDataProvider;
import com.liferay.commerce.frontend.Filter;
import com.liferay.commerce.frontend.Pagination;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.portlet.PortletQName;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.portlet.ActionRequest;
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
		"commerce.data.provider.key=" + CommerceAccountUserClayTable.NAME,
		"commerce.table.name=" + CommerceAccountUserClayTable.NAME
	},
	service = {
		ClayTable.class, ClayTableActionProvider.class,
		CommerceDataSetDataProvider.class
	}
)
public class CommerceAccountUserClayTable
	implements CommerceDataSetDataProvider<Member>, ClayTable,
			   ClayTableActionProvider {

	public static final String NAME = "commerceAccountUsers";

	@Override
	public List<ClayTableAction> clayTableActions(
			HttpServletRequest httpServletRequest, long groupId, Object model)
		throws PortalException {

		List<ClayTableAction> clayTableActions = new ArrayList<>();

		Member member = (Member)model;

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		long commerceAccountId = ParamUtil.getLong(
			httpServletRequest, "commerceAccountId");

		if (_modelResourcePermission.contains(
				themeDisplay.getPermissionChecker(), commerceAccountId,
				ActionKeys.VIEW)) {

			String viewURL = _getAccountUserViewDetailURL(
				member.getMemberId(), httpServletRequest);

			ClayTableAction clayTableAction = new ClayTableAction(
				viewURL, StringPool.BLANK,
				LanguageUtil.get(httpServletRequest, "view"), false, false);

			clayTableActions.add(clayTableAction);
		}

		if (_modelResourcePermission.contains(
				themeDisplay.getPermissionChecker(), commerceAccountId,
				ActionKeys.UPDATE)) {

			String deleteURL = _getAccountUserDeleteURL(
				member.getMemberId(), httpServletRequest);

			ClayTableAction clayTableAction = new ClayTableAction(
				deleteURL, StringPool.BLANK,
				LanguageUtil.get(httpServletRequest, "delete"), false, false);

			clayTableActions.add(clayTableAction);
		}

		return clayTableActions;
	}

	@Override
	public int countItems(HttpServletRequest httpServletRequest, Filter filter)
		throws PortalException {

		AccountFilterImpl accountFilter = (AccountFilterImpl)filter;

		return _commerceAccountUserRelService.getCommerceAccountUserRelsCount(
			accountFilter.getAccountId());
	}

	@Override
	public ClayTableSchema getClayTableSchema() {
		ClayTableSchemaBuilder clayTableSchemaBuilder =
			_clayTableSchemaBuilderFactory.clayTableSchemaBuilder();

		clayTableSchemaBuilder.addField("name");
		clayTableSchemaBuilder.addField("roles");
		clayTableSchemaBuilder.addField("email");

		return clayTableSchemaBuilder.build();
	}

	@Override
	public String getId() {
		return NAME;
	}

	@Override
	public List<Member> getItems(
			HttpServletRequest httpServletRequest, Filter filter,
			Pagination pagination, Sort sort)
		throws PortalException {

		AccountFilterImpl accountFilter = (AccountFilterImpl)filter;

		List<Member> members = new ArrayList<>();

		List<CommerceAccountUserRel> commerceAccountUserRels =
			_commerceAccountUserRelService.getCommerceAccountUserRels(
				accountFilter.getAccountId(), pagination.getStartPosition(),
				pagination.getEndPosition());

		for (CommerceAccountUserRel commerceAccountUserRel :
				commerceAccountUserRels) {

			User user = commerceAccountUserRel.getUser();

			members.add(
				new Member(
					user.getUserId(), user.getFullName(),
					user.getEmailAddress(), getUserRoles(user)));
		}

		return members;
	}

	@Override
	public boolean isShowActionsMenu() {
		return true;
	}

	protected String[] getUserRoles(User user) {
		List<Role> roles = user.getRoles();

		Stream<Role> stream = roles.stream();

		return stream.map(
			Role::getName
		).toArray(
			String[]::new
		);
	}

	private String _getAccountUserDeleteURL(
			long userId, HttpServletRequest httpServletRequest)
		throws PortalException {

		PortletURL deleteURL = PortletProviderUtil.getPortletURL(
			httpServletRequest, CommerceAccount.class.getName(),
			PortletProvider.Action.EDIT);

		deleteURL.setParameter(ActionRequest.ACTION_NAME, "inviteUser");
		deleteURL.setParameter(Constants.CMD, Constants.REMOVE);
		deleteURL.setParameter(
			"redirect", _portal.getCurrentURL(httpServletRequest));

		long commerceAccountId = ParamUtil.getLong(
			httpServletRequest, "commerceAccountId");

		if (commerceAccountId > 0) {
			deleteURL.setParameter(
				"commerceAccountId", String.valueOf(commerceAccountId));
		}

		deleteURL.setParameter("userId", String.valueOf(userId));

		return deleteURL.toString();
	}

	private String _getAccountUserViewDetailURL(
			long userId, HttpServletRequest httpServletRequest)
		throws PortalException {

		PortletURL viewURL = PortletProviderUtil.getPortletURL(
			httpServletRequest, CommerceAccount.class.getName(),
			PortletProvider.Action.VIEW);

		viewURL.setParameter("mvcRenderCommandName", "viewCommerceAccountUser");

		long commerceAccountId = ParamUtil.getLong(
			httpServletRequest, "commerceAccountId");

		if (commerceAccountId > 0) {
			viewURL.setParameter(
				"commerceAccountId", String.valueOf(commerceAccountId));
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
	private CommerceAccountUserRelService _commerceAccountUserRelService;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.account.model.CommerceAccount)"
	)
	private ModelResourcePermission<CommerceAccount> _modelResourcePermission;

	@Reference
	private Portal _portal;

}