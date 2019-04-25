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

package com.liferay.commerce.frontend.taglib.servlet.taglib;

import com.liferay.commerce.frontend.taglib.internal.js.loader.modules.extender.npm.NPMResolverProvider;
import com.liferay.commerce.frontend.taglib.internal.model.AccountRole;
import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;
import com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.taglib.util.HtmlTopTag;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Fabio Diego Mastrorilli
 * @author Alec Sloan
 */
public class UserRolesModalTag extends ComponentRendererTag {

	@Override
	public int doStartTag() {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		putValue(
			"spritemap", themeDisplay.getPathThemeImages() + "/clay/icons.svg");

		try {
			HtmlTopTag htmlTopTag = new HtmlTopTag() {

				@Override
				public StringBundler getBodyContentAsStringBundler() {
					return new StringBundler(
						"<link rel=\"stylesheet\" type=\"text/css\" " +
							"href=\"/o/commerce-frontend-taglib/css" +
								"/main.css\">");
				}

			};

			htmlTopTag.doTag(pageContext);

			Map<String, Object> context = getContext();

			List<AccountRole> selectedRoles = new ArrayList<>();

			long userId = GetterUtil.getLong(context.get("userId"));
			long groupId = GetterUtil.getLong(context.get("groupId"));

			List<Role> userRoles = RoleLocalServiceUtil.getUserGroupRoles(
				userId, groupId);

			for (Role role : userRoles) {
				selectedRoles.add(
					new AccountRole(role.getRoleId(), role.getName()));
			}

			putValue("selectedRoles", selectedRoles);

			List<AccountRole> availableRoles = new ArrayList<>();

			String subtype = GetterUtil.getString(context.get("subtype"));

			List<Role> roles = RoleServiceUtil.getRoles(
				PortalUtil.getCompanyId(request),
				new int[] {RoleConstants.TYPE_SITE});

			for (Role role : roles) {
				if (subtype.equals(role.getSubtype())) {
					availableRoles.add(
						new AccountRole(role.getRoleId(), role.getName()));
				}
			}

			putValue("roles", availableRoles);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		setTemplateNamespace("UserRolesModal.render");

		return super.doStartTag();
	}

	@Override
	public String getModule() {
		NPMResolver npmResolver = NPMResolverProvider.getNPMResolver();

		if (npmResolver == null) {
			return StringPool.BLANK;
		}

		return npmResolver.resolveModuleName(
			"commerce-frontend-taglib/user_roles_modal/UserRolesModal.es");
	}

	public void setCommerceAccountId(long commerceAccountId) {
		putValue("commerceAccountId", commerceAccountId);
	}

	public void setGroupId(long groupId) {
		putValue("groupId", groupId);
	}

	public void setSubtype(String subtype) {
		putValue("subtype", subtype);
	}

	public void setUserId(long userId) {
		putValue("userId", userId);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UserRolesModalTag.class);

}