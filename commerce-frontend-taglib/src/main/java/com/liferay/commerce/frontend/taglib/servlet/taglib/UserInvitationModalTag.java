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

import com.liferay.commerce.frontend.taglib.internal.servlet.ServletContextUtil;
import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;
import com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

/**
 * @author Fabio Diego Mastrorilli
 */
public class UserInvitationModalTag extends ComponentRendererTag {

	@Override
	public int doStartTag() {
		putValue("query", StringPool.BLANK);

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		putValue(
			"spritemap",
			themeDisplay.getPathThemeImages() + "/commerce-icons.svg");

		putValue(
			"usersAPI",
			PortalUtil.getPortalURL(request) + "/o/commerce-ui/search-users");

		setTemplateNamespace("UserInvitationModal.render");

		return super.doStartTag();
	}

	@Override
	public String getModule() {
		NPMResolver npmResolver = ServletContextUtil.getNPMResolver();

		if (npmResolver == null) {
			return StringPool.BLANK;
		}

		return npmResolver.resolveModuleName(
			"commerce-frontend-taglib/user_invitation_modal" +
				"/UserInvitationModal.es");
	}

}