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

package com.liferay.commerce.openapi.admin.context;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import java.util.Locale;

/**
 * @author Zoltán Takács
 */
public class PortalHelper {

	public PortalHelper(
		Group group, Locale locale, PermissionChecker permissionChecker,
		User user, ThemeDisplay themeDisplay, Company company) {

		_group = group;
		_locale = locale;
		_permissionChecker = permissionChecker;
		_user = user;
		_themeDisplay = themeDisplay;
		_company = company;
	}

	public Company getCompany() {
		return _company;
	}

	public Group getGroup() {
		return _group;
	}

	public Locale getLocale() {
		return _locale;
	}

	public PermissionChecker getPermissionChecker() {
		return _permissionChecker;
	}

	public ThemeDisplay getThemeDisplay() {
		return _themeDisplay;
	}

	public User getUser() {
		return _user;
	}

	private final Company _company;
	private final Group _group;
	private final Locale _locale;
	private final PermissionChecker _permissionChecker;
	private final ThemeDisplay _themeDisplay;
	private final User _user;

}