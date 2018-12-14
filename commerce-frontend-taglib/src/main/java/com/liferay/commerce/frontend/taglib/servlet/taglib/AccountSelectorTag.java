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

import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.frontend.taglib.internal.js.loader.modules.extender.npm.NPMResolverProvider;
import com.liferay.commerce.frontend.taglib.internal.model.CurrentAccountModel;
import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;
import com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.webserver.WebServerServletTokenUtil;

import java.util.Map;

/**
 * @author Marco Leo
 */
public class AccountSelectorTag extends ComponentRendererTag {

	@Override
	public int doStartTag() {
		Map<String, Object> context = getContext();

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		try {
			CommerceContext commerceContext =
				(CommerceContext)request.getAttribute(
					CommerceWebKeys.COMMERCE_CONTEXT);

			Organization organization = commerceContext.getOrganization();

			if (organization != null) {
				String thumbnail;

				StringBundler stringBundler = new StringBundler(5);

				stringBundler.append(themeDisplay.getPathImage());

				if (organization.getLogoId() == 0) {
					stringBundler.append("/organization_logo?img_id=0");
				}
				else {
					stringBundler.append("/organization_logo?img_id=");
					stringBundler.append(organization.getLogoId());
					stringBundler.append("&t=");
					stringBundler.append(
						WebServerServletTokenUtil.getToken(
							organization.getLogoId()));
				}

				CurrentAccountModel currentAccountModel =
					new CurrentAccountModel(
						organization.getOrganizationId(),
						organization.getName(), stringBundler.toString());

				putValue("currentAccount", currentAccountModel);
			}
		}
		catch (PortalException pe) {
			_log.error(pe, pe);
		}

		putValue(
			"dataSetAPI",
			PortalUtil.getPortalURL(request) + "/o/commerce-data-set");

		putValue(
			"spritemap",
			themeDisplay.getPathThemeImages() + "/commerce-icons.svg");

		setTemplateNamespace("AccountSelector.render");

		return super.doStartTag();
	}

	@Override
	public String getModule() {
		NPMResolver npmResolver = NPMResolverProvider.getNPMResolver();

		if (npmResolver == null) {
			return StringPool.BLANK;
		}

		return npmResolver.resolveModuleName(
			"commerce-frontend-taglib/account_selector/AccountSelector.es");
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AccountSelectorTag.class);

}