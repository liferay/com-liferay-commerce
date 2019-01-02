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

package com.liferay.commerce.product.content.web.internal.product.publisher.servlet.taglib.ui;

import com.liferay.commerce.product.content.web.internal.configuration.CPPublisherPortletInstanceConfiguration;
import com.liferay.commerce.product.content.web.internal.constants.CPPublisherConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.servlet.taglib.ui.BaseJSPFormNavigatorEntry;
import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorEntry;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.portlet.PortletPreferences;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = "form.navigator.entry.order:Integer=600",
	service = FormNavigatorEntry.class
)
public class CPTypeRendererFormNavigatorEntry
	extends BaseJSPFormNavigatorEntry<Void> {

	@Override
	public String getCategoryKey() {
		return CPPublisherConstants.CATEGORY_KEY_RENDER_SELECTION;
	}

	@Override
	public String getFormNavigatorId() {
		return CPPublisherConstants.FORM_NAVIGATOR_ID_CONFIGURATION;
	}

	@Override
	public String getKey() {
		return "product-type-renderer";
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(resourceBundle, getKey());
	}

	@Override
	public boolean isVisible(User user, Void object) {
		return _isVisible();
	}

	@Override
	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.commerce.product.content.web)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}

	@Override
	protected String getJspPath() {
		return "/product_publisher/configuration/product_type_renderer.jsp";
	}

	private boolean _isVisible() {
		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		ThemeDisplay themeDisplay = serviceContext.getThemeDisplay();

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		try {
			CPPublisherPortletInstanceConfiguration
				cpPublisherPortletInstanceConfiguration =
					portletDisplay.getPortletInstanceConfiguration(
						CPPublisherPortletInstanceConfiguration.class);

			boolean enableViewMode =
				cpPublisherPortletInstanceConfiguration.enableViewMode();

			if (enableViewMode) {
				return false;
			}

			PortletPreferences portletPreferences =
				themeDisplay.getStrictLayoutPortletSetup(
					themeDisplay.getLayout(),
					portletDisplay.getPortletResource());

			String renderSelection = GetterUtil.getString(
				portletPreferences.getValue("renderSelection", null), "custom");

			return renderSelection.equals("custom");
		}
		catch (PortalException pe) {
			if (_log.isDebugEnabled()) {
				_log.debug(pe, pe);
			}

			return false;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CPTypeRendererFormNavigatorEntry.class);

}