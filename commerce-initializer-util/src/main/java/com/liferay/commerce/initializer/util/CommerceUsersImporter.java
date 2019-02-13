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

package com.liferay.commerce.initializer.util;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountLocalService;
import com.liferay.commerce.account.service.CommerceAccountUserRelLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alec Sloan
 */
@Component(service = CommerceUsersImporter.class)
public class CommerceUsersImporter {

	public List<User> importCommerceUsers(
			JSONArray jsonArray, ClassLoader classLoader,
			Map<String, CommerceAccount> commerceAccounts,
			String dependenciesPath, ServiceContext serviceContext)
		throws IOException, PortalException {

		List<User> users = new ArrayList<>(jsonArray.length());

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			User user = _importCommerceUser(
				jsonObject, classLoader, commerceAccounts, dependenciesPath,
				serviceContext);

			users.add(user);
		}

		return users;
	}

	protected CommerceAccount getCommerceAccountByName(
		String commerceAccountName,
		Map<String, CommerceAccount> commerceAccounts) {

		return commerceAccounts.get(commerceAccountName);
	}

	protected Role getRoleByName(String roleName, ServiceContext serviceContext)
		throws PortalException {

		if (Validator.isBlank(roleName)) {
			return null;
		}

		return _roleLocalService.getRole(
			serviceContext.getCompanyId(), roleName);
	}

	private User _importCommerceUser(
			JSONObject jsonObject, ClassLoader classLoader,
			Map<String, CommerceAccount> commerceAccounts,
			String dependenciesPath, ServiceContext serviceContext)
		throws IOException, PortalException {

		String screenName = jsonObject.getString("ScreenName");
		String emailAddress = jsonObject.getString("EmailAddress");
		String firstName = jsonObject.getString("FirstName");
		String lastName = jsonObject.getString("LastName");
		String jobTitle = jsonObject.getString("JobTitle");
		String gender = jsonObject.getString("Gender");

		boolean male = true;

		if (gender.equals("Female")) {
			male = false;
		}

		Calendar calendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		User user = null;

		user = _userLocalService.fetchUserByScreenName(
			serviceContext.getCompanyId(), screenName);

		if (user == null) {
			user = _userLocalService.addUser(
				0, serviceContext.getCompanyId(), false, "test", "test", false,
				screenName, emailAddress, 0, null, serviceContext.getLocale(),
				firstName, null, lastName, 0, 0, male,
				calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH),
				calendar.get(Calendar.YEAR), jobTitle, null, null, null, null,
				false, serviceContext);
		}

		String comments = jsonObject.getString("Comments");

		user.setComments(comments);

		_userLocalService.updateUser(user);

		String profilePicture = jsonObject.getString("ProfilePicture");

		if (!Validator.isBlank(profilePicture)) {
			String filePath = dependenciesPath + "images/" + profilePicture;

			InputStream inputStream = classLoader.getResourceAsStream(filePath);

			if (inputStream != null) {
				File file = FileUtil.createTempFile(inputStream);

				_userLocalService.updatePortrait(
					user.getUserId(), FileUtil.getBytes(file));
			}
		}

		JSONArray accountJSONArray = jsonObject.getJSONArray("Accounts");

		if (accountJSONArray != null) {
			for (int i = 0; i < accountJSONArray.length(); i++) {
				JSONObject accountJSONObject = accountJSONArray.getJSONObject(
					i);

				CommerceAccount commerceAccount = getCommerceAccountByName(
					accountJSONObject.getString("Name"), commerceAccounts);

				_commerceAccountUserRelLocalService.addCommerceAccountUserRel(
					commerceAccount.getCommerceAccountId(), user.getUserId(),
					serviceContext);

				Role role = getRoleByName(
					accountJSONObject.getString("Role"), serviceContext);

				if (role != null) {
					_userGroupRoleLocalService.addUserGroupRoles(
						user.getUserId(),
						commerceAccount.getCommerceAccountGroupId(),
						new long[] {role.getRoleId()});
				}
			}
		}

		// Add Regular Roles

		JSONArray regularRolesJSONArray = jsonObject.getJSONArray(
			"RegularRoles");

		if (regularRolesJSONArray != null) {
			for (int i = 0; i < regularRolesJSONArray.length(); i++) {
				Role role = _roleLocalService.fetchRole(
					serviceContext.getCompanyId(),
					regularRolesJSONArray.getString(i));

				if (role != null) {
					_roleLocalService.addUserRole(user.getUserId(), role);
				}
			}
		}

		// Add Site Roles

		JSONArray siteRolesJSONArray = jsonObject.getJSONArray("SiteRoles");

		if (siteRolesJSONArray != null) {
			for (int i = 0; i < siteRolesJSONArray.length(); i++) {
				Role role = _roleLocalService.fetchRole(
					serviceContext.getCompanyId(),
					siteRolesJSONArray.getString(i));

				if (role != null) {
					_userGroupRoleLocalService.addUserGroupRoles(
						user.getUserId(), serviceContext.getScopeGroupId(),
						new long[] {role.getRoleId()});
				}
			}
		}
		else {
			Role role = _roleLocalService.fetchRole(
				serviceContext.getCompanyId(), RoleConstants.SITE_MEMBER);

			_userGroupRoleLocalService.addUserGroupRoles(
				user.getUserId(), serviceContext.getScopeGroupId(),
				new long[] {role.getRoleId()});
		}

		return user;
	}

	@Reference
	private CommerceAccountLocalService _commerceAccountLocalService;

	@Reference
	private CommerceAccountUserRelLocalService
		_commerceAccountUserRelLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference
	private UserGroupRoleLocalService _userGroupRoleLocalService;

	@Reference
	private UserLocalService _userLocalService;

}