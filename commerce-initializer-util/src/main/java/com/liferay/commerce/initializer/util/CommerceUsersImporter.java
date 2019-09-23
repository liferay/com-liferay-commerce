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

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.MappingJsonFactory;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.model.CommerceAccountUserRel;
import com.liferay.commerce.account.service.CommerceAccountLocalService;
import com.liferay.commerce.account.service.CommerceAccountUserRelLocalService;
import com.liferay.commerce.account.service.persistence.CommerceAccountUserRelPK;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;
import com.liferay.portal.kernel.service.UserIdMapperLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.URI;
import java.net.URLEncoder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alec Sloan
 */
@Component(service = CommerceUsersImporter.class)
public class CommerceUsersImporter {

	public void importCommerceUsers(
			File commerceUsersFile, ClassLoader classLoader,
			String imageDependenciesPath, long scopeGroupId, long userId)
		throws Exception {

		ServiceContext serviceContext = getServiceContext(scopeGroupId, userId);

		MappingJsonFactory mappingJsonFactory = new MappingJsonFactory();

		JsonParser jsonFactoryParser = mappingJsonFactory.createParser(
			commerceUsersFile);

		JsonToken jsonToken = jsonFactoryParser.nextToken();

		if (jsonToken != JsonToken.START_ARRAY) {
			throw new Exception("JSON Array Expected");
		}

		int importCount = 0;

		while (jsonFactoryParser.nextToken() != JsonToken.END_ARRAY) {
			TreeNode treeNode = jsonFactoryParser.readValueAsTree();

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
				treeNode.toString());

			if (_log.isDebugEnabled()) {
				_log.debug(jsonObject);
			}

			_importCommerceUser(
				jsonObject, classLoader, imageDependenciesPath, serviceContext);

			importCount += 1;
		}

		if (_log.isInfoEnabled()) {
			_log.info("Imported users count: " + importCount);
		}

		jsonFactoryParser.close();
	}

	public void importCommerceUsers(
			JSONArray jsonArray, ClassLoader classLoader,
			String dependenciesPath, long scopeGroupId, long userId)
		throws IOException, PortalException {

		User user = _userLocalService.getUser(userId);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setScopeGroupId(scopeGroupId);
		serviceContext.setUserId(userId);
		serviceContext.setCompanyId(user.getCompanyId());

		for (int i = 0; i < jsonArray.length(); i++) {
			_importCommerceUser(
				jsonArray.getJSONObject(i), classLoader, dependenciesPath,
				serviceContext);
		}
	}

	protected ServiceContext getServiceContext(long scopeGroupId, long userId)
		throws PortalException {

		User user = _userLocalService.getUser(userId);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setScopeGroupId(scopeGroupId);
		serviceContext.setUserId(userId);
		serviceContext.setCompanyId(user.getCompanyId());

		return serviceContext;
	}

	protected User upsertUser(
			String password, String userReminderQueryQuestion,
			String userReminderQueryAnswer, String screenName,
			String emailAddress, long facebookId, String openId,
			boolean portrait, byte[] portraitBytes, Locale locale,
			String timeZoneId, String greeting, String comments,
			String firstName, String middleName, String lastName, long prefixId,
			long suffixId, boolean male, int birthdayMonth, int birthdayDay,
			int birthdayYear, String smsSn, String facebookSn, String jabberSn,
			String skypeSn, String twitterSn, String jobTitle, long[] groupIds,
			long[] organizationIds, long[] roleIds, long[] userGroupIds,
			ServiceContext serviceContext)
		throws PortalException {

		User user = _userLocalService.fetchUserByScreenName(
			serviceContext.getCompanyId(), screenName);

		if (user == null) {
			user = _userLocalService.fetchUserByEmailAddress(
				serviceContext.getCompanyId(), emailAddress);
		}

		long companyId = serviceContext.getCompanyId();

		long creatorUserId = serviceContext.getUserId();

		boolean autoPassword = Validator.isNull(password);

		boolean autoScreenName = Validator.isNull(screenName);

		if (user == null) {
			user = _userLocalService.addUser(
				creatorUserId, companyId, autoPassword, password, password,
				autoScreenName, screenName, emailAddress, facebookId, openId,
				locale, firstName, middleName, lastName, prefixId, suffixId,
				male, birthdayMonth, birthdayDay, birthdayYear, jobTitle,
				groupIds, organizationIds, roleIds, userGroupIds, false,
				serviceContext);
		}
		else {
			groupIds = ArrayUtil.append(user.getGroupIds(), groupIds);
		}

		if (Validator.isNotNull(comments) ||
			!groupIds.equals(user.getGroupIds())) {

			user = _userLocalService.updateUser(
				user.getUserId(), StringPool.BLANK, StringPool.BLANK,
				StringPool.BLANK, false, userReminderQueryQuestion,
				userReminderQueryAnswer, screenName, emailAddress, facebookId,
				openId, portrait, portraitBytes,
				LocaleUtil.toLanguageId(locale), timeZoneId, greeting, comments,
				firstName, middleName, lastName, prefixId, suffixId, male,
				birthdayMonth, birthdayDay, birthdayYear, smsSn, facebookSn,
				jabberSn, skypeSn, twitterSn, jobTitle, groupIds,
				organizationIds, roleIds, null, userGroupIds, serviceContext);
		}
		else if (portrait) {
			_userLocalService.updatePortrait(user.getUserId(), portraitBytes);
		}

		return user;
	}

	@SuppressFBWarnings("PATH_TRAVERSAL_IN")
	private void _importCommerceUser(
			JSONObject jsonObject, ClassLoader classLoader,
			String dependenciesPath, ServiceContext serviceContext)
		throws IOException, PortalException {

		String password = jsonObject.getString("password");

		String userReminderQueryQuestion = jsonObject.getString(
			"userReminderQueryQuestion");
		String userReminderQueryAnswer = jsonObject.getString(
			"userReminderQueryAnswer");
		String screenName = jsonObject.getString("screenName");
		long facebookId = jsonObject.getLong("facebookId");
		String openId = jsonObject.getString("openId");

		String emailAddress = jsonObject.getString("emailAddress");

		boolean hasPortrait = false;
		byte[] portraitBytes = null;

		String portrait = jsonObject.getString("portrait");

		if (!Validator.isBlank(portrait)) {
			InputStream inputStream = null;

			try {
				String uriString =
					dependenciesPath + URLEncoder.encode(portrait, "UTF-8");

				URI uri = new URI(uriString);

				if (StringUtil.equalsIgnoreCase(uri.getScheme(), "file")) {
					inputStream = new FileInputStream(uri.getPath());
				}
				else {
					inputStream = classLoader.getResourceAsStream(
						dependenciesPath + portrait);
				}

				portraitBytes = FileUtil.getBytes(inputStream);

				hasPortrait = true;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
			finally {
				if (inputStream != null) {
					inputStream.close();
				}
			}
		}

		User user = _userLocalService.getUser(serviceContext.getUserId());

		Locale locale = user.getLocale();

		String importedLanguageCode = jsonObject.getString("languageCode");

		if (!Validator.isBlank(importedLanguageCode)) {
			locale = LanguageUtil.getLocale(importedLanguageCode);
		}

		TimeZone timeZone = user.getTimeZone();

		String timeZoneId = jsonObject.getString(
			"timeZoneId", timeZone.getID());

		String greeting = jsonObject.getString("greeting");
		String comments = jsonObject.getString("comments");
		String firstName = jsonObject.getString("firstName");
		String middleName = jsonObject.getString("middleName");
		String lastName = jsonObject.getString("lastName");
		long prefixId = jsonObject.getLong("prefixId");
		long suffixId = jsonObject.getLong("suffixId");
		boolean male = jsonObject.getBoolean("male");

		String gender = jsonObject.getString("gender");

		if (Validator.isNotNull(gender)) {
			male = gender.equals("male");
		}

		Calendar calendar = CalendarFactoryUtil.getCalendar(timeZone);

		int birthdayMonth = calendar.get(
			jsonObject.getInt("birthdayMonth", Calendar.MONTH));
		int birthdayDay = calendar.get(
			jsonObject.getInt("birthdayDay", Calendar.DAY_OF_MONTH));
		int birthdayYear = calendar.get(
			jsonObject.getInt("birthdayYear", Calendar.YEAR));

		String birthday = jsonObject.getString("birthday");

		if (Validator.isNotNull(birthday)) {
			String[] birthdayTokens = birthday.split("-");

			birthdayMonth = GetterUtil.getInteger(birthdayTokens[1]) - 1;

			birthdayDay = GetterUtil.getInteger(birthdayTokens[2]);

			birthdayYear = GetterUtil.getInteger(birthdayTokens[0]);
		}

		String smsSn = jsonObject.getString("smsSn");
		String facebookSn = jsonObject.getString("facebookSn");
		String jabberSn = jsonObject.getString("jabberSn");
		String skypeSn = jsonObject.getString("skypeSn");
		String twitterSn = jsonObject.getString("twitterSn");
		String jobTitle = jsonObject.getString("jobTitle");

		// Collect Organization Role Ids

		JSONArray organizationsJSONArray = jsonObject.getJSONArray(
			"organizations");

		List<Long> organizationIds = new ArrayList<>();

		if (organizationsJSONArray != null) {
			for (int i = 0; i < organizationsJSONArray.length(); i++) {
				Organization organization =
					_organizationLocalService.getOrganization(
						serviceContext.getCompanyId(),
						organizationsJSONArray.getString(i));

				if (organization != null) {
					organizationIds.add(organization.getOrganizationId());
				}
			}
		}

		// Collect Role Ids

		JSONArray rolesJSONArray = jsonObject.getJSONArray("roles");

		List<Long> roleIds = new ArrayList<>();

		if (rolesJSONArray != null) {
			for (int i = 0; i < rolesJSONArray.length(); i++) {
				Role role = _roleLocalService.getRole(
					serviceContext.getCompanyId(), rolesJSONArray.getString(i));

				if (role != null) {
					roleIds.add(role.getRoleId());
				}
			}
		}

		long[] userGroupIds = null;

		user = upsertUser(
			password, userReminderQueryQuestion, userReminderQueryAnswer,
			screenName, emailAddress, facebookId, openId, hasPortrait,
			portraitBytes, locale, timeZoneId, greeting, comments, firstName,
			middleName, lastName, prefixId, suffixId, male, birthdayMonth,
			birthdayDay, birthdayYear, smsSn, facebookSn, jabberSn, skypeSn,
			twitterSn, jobTitle, new long[] {serviceContext.getScopeGroupId()},
			ArrayUtil.toLongArray(organizationIds),
			ArrayUtil.toLongArray(roleIds), userGroupIds, serviceContext);

		// Add User Group Roles

		JSONArray groupRolesJSONArray = jsonObject.getJSONArray("groupRoles");

		if (groupRolesJSONArray != null) {
			for (int i = 0; i < groupRolesJSONArray.length(); i++) {
				Role role = _roleLocalService.getRole(
					serviceContext.getCompanyId(),
					groupRolesJSONArray.getString(i));

				_userGroupRoleLocalService.addUserGroupRoles(
					user.getUserId(), serviceContext.getScopeGroupId(),
					new long[] {role.getRoleId()});
			}
		}

		// Add External ID mapping

		String externalUserId = jsonObject.getString("externalUserId");

		String externalSystemType = jsonObject.getString("externalSystemType");

		String exteralSsystemDescription = jsonObject.getString(
			"externalSystemDescription", "");

		if (Validator.isNotNull(externalUserId) &&
			Validator.isNotNull(externalSystemType)) {

			_userIdMapperLocalService.updateUserIdMapper(
				user.getUserId(), externalSystemType, exteralSsystemDescription,
				externalUserId);
		}

		// Add User to Commerce Accounts and Account Roles

		JSONArray accountsJSONArray = jsonObject.getJSONArray("accounts");

		if (accountsJSONArray != null) {
			for (int i = 0; i < accountsJSONArray.length(); i++) {
				JSONObject accountJSONObject = accountsJSONArray.getJSONObject(
					i);

				JSONArray accountRolesJSONArray =
					accountJSONObject.getJSONArray("roles");

				if (accountRolesJSONArray != null) {
					CommerceAccount commerceAccount =
						_commerceAccountLocalService.
							fetchByExternalReferenceCode(
								serviceContext.getCompanyId(),
								accountJSONObject.getString("name"));

					CommerceAccountUserRelPK commerceAccountUserRelPK =
						new CommerceAccountUserRelPK(
							commerceAccount.getCommerceAccountId(),
							user.getUserId());

					CommerceAccountUserRel commerceAccountUserRel =
						_commerceAccountUserRelLocalService.
							fetchCommerceAccountUserRel(
								commerceAccountUserRelPK);

					if (commerceAccountUserRel == null) {
						List<Long> accountRoleIds = new ArrayList<>();

						for (int j = 0; j < accountRolesJSONArray.length();
							 j++) {

							Role role = _roleLocalService.fetchRole(
								serviceContext.getCompanyId(),
								accountRolesJSONArray.getString(j));

							if (role != null) {
								accountRoleIds.add(role.getRoleId());
							}
						}

						long[] userIds = {user.getUserId()};

						_commerceAccountUserRelLocalService.
							addCommerceAccountUserRels(
								commerceAccount.getCommerceAccountId(), userIds,
								null, ArrayUtil.toLongArray(accountRoleIds),
								serviceContext);
					}
				}
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceUsersImporter.class);

	@Reference
	private CommerceAccountLocalService _commerceAccountLocalService;

	@Reference
	private CommerceAccountUserRelLocalService
		_commerceAccountUserRelLocalService;

	@Reference
	private OrganizationLocalService _organizationLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference
	private UserGroupRoleLocalService _userGroupRoleLocalService;

	@Reference
	private UserIdMapperLocalService _userIdMapperLocalService;

	@Reference
	private UserLocalService _userLocalService;

}