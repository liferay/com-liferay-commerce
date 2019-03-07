/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.commerce.theme.minium.api.internal.table.contributor;

import com.liferay.commerce.frontend.ClayTable;
import com.liferay.commerce.frontend.ClayTableContextContributor;
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
	service = ClayTableContextContributor.class
)
public class CommerceAccountClayTableContextContributor
	implements ClayTableContextContributor {

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
			"commerce-theme-minium-impl@1.0.8/action_menus" +
				"/MiniumExtensions.es");

		return dependencies;
	}

	private static final String _MINIUM_THEME_ID = "minium_WAR_miniumtheme";

	@Reference
	private NPMResolver _npmResolver;

}