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

package com.liferay.commerce.tax.engine.fixed.web.internal.servlet.taglib.ui;

import com.liferay.commerce.constants.CommerceTaxScreenNavigationConstants;
import com.liferay.commerce.tax.engine.fixed.web.internal.display.context.CommerceTaxedAddressDisplayContext;
import com.liferay.commerce.tax.model.CommerceTaxMethod;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationCategory;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationEntry;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alec Sloan
 */
@Component(
	property = {
		"screen.navigation.category.order:Integer=40",
		"screen.navigation.entry.order:Integer=10"
	},
	service = {ScreenNavigationCategory.class, ScreenNavigationEntry.class}
)
public class CommerceTaxMethodAddressTypeScreenNavigationEntry
	implements ScreenNavigationCategory,
			   ScreenNavigationEntry<CommerceTaxMethod> {

	public static final String CATEGORY_KEY = "taxed-address";

	public static final String ENTRY_KEY = "taxed-address";

	@Override
	public String getCategoryKey() {
		return CATEGORY_KEY;
	}

	@Override
	public String getEntryKey() {
		return ENTRY_KEY;
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(resourceBundle, ENTRY_KEY);
	}

	@Override
	public String getScreenNavigationKey() {
		return CommerceTaxScreenNavigationConstants.
			SCREEN_NAVIGATION_KEY_COMMERCE_TAX_METHOD;
	}

	@Override
	public boolean isVisible(User user, CommerceTaxMethod commerceTaxMethod) {
		String engineKey = commerceTaxMethod.getEngineKey();

		if (engineKey.equals("by-address")) {
			return true;
		}

		return false;
	}

	@Override
	public void render(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		CommerceTaxedAddressDisplayContext commerceTaxedAddressDisplayContext =
			new CommerceTaxedAddressDisplayContext(httpServletRequest);

		httpServletRequest.setAttribute(
			WebKeys.PORTLET_DISPLAY_CONTEXT,
			commerceTaxedAddressDisplayContext);

		_jspRenderer.renderJSP(
			_servletContext, httpServletRequest, httpServletResponse,
			"/taxed_address.jsp");
	}

	@Reference
	private JSPRenderer _jspRenderer;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.commerce.tax.engine.fixed.web)"
	)
	private ServletContext _servletContext;

}