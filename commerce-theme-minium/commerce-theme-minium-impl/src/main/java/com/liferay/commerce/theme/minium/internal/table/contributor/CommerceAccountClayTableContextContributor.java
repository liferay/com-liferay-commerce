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

package com.liferay.commerce.theme.minium.internal.table.contributor;

import com.liferay.commerce.frontend.ClayTable;
import com.liferay.commerce.frontend.ClayTableHttpContextContributor;
import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(
	immediate = true,
	property = {
		"commerce.table.name=commerceAccountAddresses",
		"commerce.table.name=commerceAccountOrganizations",
		"commerce.table.name=commerceAccounts",
		"commerce.table.name=commerceAccountUsers"
	},
	service = ClayTableHttpContextContributor.class
)
public class CommerceAccountClayTableContextContributor
	implements ClayTableHttpContextContributor {

	@Override
	public void contribute(
		ClayTable clayTable, Map<String, Object> context,
		HttpServletRequest httpServletRequest) {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		if (!_MINIUM_THEME_ID.equals(themeDisplay.getThemeId())) {
			return;
		}

		context.put("actionsMenuVariant", "miniumActionsMenuVariant");
	}

	@Override
	public Set<String> getDependencies(
		ClayTable clayTable, HttpServletRequest httpServletRequest) {

		Set<String> dependencies = new HashSet<>();

		dependencies.add(
			_npmResolver.resolveModuleName(
				"commerce-theme-minium-impl/action_menus/MiniumExtensions.es"));

		return dependencies;
	}

	private static final String _MINIUM_THEME_ID = "minium_WAR_miniumtheme";

	@Reference
	private NPMResolver _npmResolver;

}