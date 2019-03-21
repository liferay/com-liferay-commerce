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

package com.liferay.commerce.admin.web.internal.util;

import com.liferay.commerce.admin.CommerceAdminModule;
import com.liferay.commerce.product.configuration.CPRuleGroupServiceConfiguration;
import com.liferay.commerce.product.constants.CPRuleConstants;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.io.IOException;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ethan Bustad
 */
@Component(
	immediate = true,
	property = "commerce.admin.module.key=" + CPRuleSettingsCommerceAdminModule.KEY,
	service = CommerceAdminModule.class
)
public class CPRuleSettingsCommerceAdminModule implements CommerceAdminModule {

	public static final String KEY = "catalog-rules";

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(resourceBundle, KEY);
	}

	@Override
	public PortletURL getSearchURL(
		RenderRequest renderRequest, RenderResponse renderResponse) {

		return null;
	}

	@Override
	public boolean isVisible(long groupId) throws PortalException {
		return true;
	}

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException {

		HttpServletRequest httpServletRequest = _portal.getHttpServletRequest(
			renderRequest);
		HttpServletResponse httpServletResponse =
			_portal.getHttpServletResponse(renderResponse);

		int catalogRuleApplicationType = CPRuleConstants.APPLICATION_TYPE_ALL;

		try {
			CPRuleGroupServiceConfiguration cpRuleGroupServiceConfiguration =
				_configurationProvider.getConfiguration(
					CPRuleGroupServiceConfiguration.class,
					new GroupServiceSettingsLocator(
						_portal.getScopeGroupId(httpServletRequest),
						CPRuleConstants.SERVICE_NAME));

			if (cpRuleGroupServiceConfiguration != null) {
				catalogRuleApplicationType =
					cpRuleGroupServiceConfiguration.
						catalogRuleApplicationType();
			}
		}
		catch (PortalException pe) {
			_log.error(pe, pe);
		}

		httpServletRequest.setAttribute(
			"catalogRuleApplicationType", catalogRuleApplicationType);

		_jspRenderer.renderJSP(
			httpServletRequest, httpServletResponse,
			"/configuration/edit_rule_settings.jsp");
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CPRuleSettingsCommerceAdminModule.class);

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference
	private JSPRenderer _jspRenderer;

	@Reference
	private Portal _portal;

}