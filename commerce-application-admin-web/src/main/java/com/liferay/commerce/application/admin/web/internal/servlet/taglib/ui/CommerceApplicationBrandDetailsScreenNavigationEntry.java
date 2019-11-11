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

package com.liferay.commerce.application.admin.web.internal.servlet.taglib.ui;

import com.liferay.commerce.application.constants.CommerceApplicationActionKeys;
import com.liferay.commerce.application.model.CommerceApplicationBrand;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationCategory;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationEntry;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.service.permission.PortalPermissionUtil;

import java.io.IOException;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = {
		"screen.navigation.category.order:Integer=10",
		"screen.navigation.entry.order:Integer=10"
	},
	service = {ScreenNavigationCategory.class, ScreenNavigationEntry.class}
)
public class CommerceApplicationBrandDetailsScreenNavigationEntry
	implements ScreenNavigationCategory,
			   ScreenNavigationEntry<CommerceApplicationBrand> {

	@Override
	public String getCategoryKey() {
		return CommerceApplicationBrandScreenNavigationConstants.
			CATEGORY_KEY_DETAILS;
	}

	@Override
	public String getEntryKey() {
		return CommerceApplicationBrandScreenNavigationConstants.
			ENTRY_KEY_DETAILS;
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, "details");
	}

	@Override
	public String getScreenNavigationKey() {
		return CommerceApplicationBrandScreenNavigationConstants.
			SCREEN_NAVIGATION_KEY;
	}

	@Override
	public boolean isVisible(
		User user, CommerceApplicationBrand commerceApplicationBrand) {

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		try {
			if (commerceApplicationBrand == null) {
				return PortalPermissionUtil.contains(
					permissionChecker,
					CommerceApplicationActionKeys.ADD_COMMERCE_BRAND);
			}

			return _commerceApplicationBrandModelResourcePermission.contains(
				permissionChecker, commerceApplicationBrand, ActionKeys.UPDATE);
		}
		catch (PortalException pe) {
			_log.error(pe, pe);
		}

		return false;
	}

	@Override
	public void render(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		_jspRenderer.renderJSP(
			httpServletRequest, httpServletResponse, "/brand/details.jsp");
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceApplicationBrandDetailsScreenNavigationEntry.class);

	@Reference(
		target = "(model.class.name=com.liferay.commerce.application.model.CommerceApplicationBrand)"
	)
	private ModelResourcePermission<CommerceApplicationBrand>
		_commerceApplicationBrandModelResourcePermission;

	@Reference
	private JSPRenderer _jspRenderer;

}