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

import com.liferay.commerce.user.segment.model.CommerceUserSegmentCriterionConstants;
import com.liferay.commerce.user.segment.model.CommerceUserSegmentEntry;
import com.liferay.commerce.user.segment.service.CommerceUserSegmentCriterionLocalService;
import com.liferay.commerce.user.segment.service.CommerceUserSegmentEntryLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.NoSuchOrganizationException;
import com.liferay.portal.kernel.exception.NoSuchRoleException;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.NoSuchUserGroupException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserGroupLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alec Sloan
 */
@Component(service = CommerceUserSegmentsImporter.class)
public class CommerceUserSegmentsImporter {

	public void importCommerceUserSegments(
			JSONArray jsonArray, long scopeGroupId, long userId)
		throws PortalException {

		User user = _userLocalService.getUser(userId);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setScopeGroupId(scopeGroupId);
		serviceContext.setUserId(userId);
		serviceContext.setCompanyId(user.getCompanyId());

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			_importCommerceUserSegment(jsonObject, serviceContext);
		}
	}

	private void _importCommerceUserSegment(
			JSONObject jsonObject, ServiceContext serviceContext)
		throws PortalException {

		Map<Locale, String> nameMap = new HashMap<>();

		String name = jsonObject.getString("Name");

		nameMap.put(LocaleUtil.getSiteDefault(), name);

		// Add User Segment

		CommerceUserSegmentEntry commerceUserSegmentEntry =
			_commerceUserSegmentEntryLocalService.addCommerceUserSegmentEntry(
				nameMap, FriendlyURLNormalizerUtil.normalize(name), true, false,
				0, serviceContext);

		// Add User Criterion

		JSONArray usersJSONArray = jsonObject.getJSONArray("Users");

		if (usersJSONArray != null) {
			List<Long> usersList = new ArrayList<>();

			for (int i = 0; i < usersJSONArray.length(); i++) {
				try {
					User user = _userLocalService.getUserByScreenName(
						serviceContext.getCompanyId(),
						usersJSONArray.getString(i));

					usersList.add(user.getUserId());
				}
				catch (NoSuchUserException nsue) {
					_log.error(nsue, nsue);
				}
			}

			if (!usersList.isEmpty()) {
				_commerceUserSegmentCriterionLocalService.
					addCommerceUserSegmentCriterion(
						commerceUserSegmentEntry.
							getCommerceUserSegmentEntryId(),
						CommerceUserSegmentCriterionConstants.TYPE_USER,
						ListUtil.toString(
							usersList, StringPool.BLANK,
							StringPool.COMMA_AND_SPACE),
						0, serviceContext);
			}
		}

		// Add Organization Criterion

		JSONArray organizationsJSONArray = jsonObject.getJSONArray(
			"Organizations");

		if (organizationsJSONArray != null) {
			List<Long> organizationsList = new ArrayList<>();

			for (int i = 0; i < organizationsJSONArray.length(); i++) {
				try {
					Organization organization =
						_organizationLocalService.getOrganization(
							serviceContext.getCompanyId(),
							organizationsJSONArray.getString(i));

					organizationsList.add(organization.getOrganizationId());
				}
				catch (NoSuchOrganizationException nsoe) {
					_log.error(nsoe, nsoe);
				}
			}

			if (!organizationsList.isEmpty()) {
				_commerceUserSegmentCriterionLocalService.
					addCommerceUserSegmentCriterion(
						commerceUserSegmentEntry.
							getCommerceUserSegmentEntryId(),
						CommerceUserSegmentCriterionConstants.TYPE_ORGANIZATION,
						ListUtil.toString(
							organizationsList, StringPool.BLANK,
							StringPool.COMMA_AND_SPACE),
						0, serviceContext);
			}
		}

		// Add User Groups Criterion

		JSONArray userGroupsJSONArray = jsonObject.getJSONArray("UserGroups");

		if (userGroupsJSONArray != null) {
			List<Long> userGroupsList = new ArrayList<>();

			for (int i = 0; i < userGroupsJSONArray.length(); i++) {
				try {
					UserGroup userGroup = _userGroupLocalService.getUserGroup(
						serviceContext.getCompanyId(),
						userGroupsJSONArray.getString(i));

					userGroupsList.add(userGroup.getUserGroupId());
				}
				catch (NoSuchUserGroupException nsuge) {
					_log.error(nsuge, nsuge);
				}
			}

			if (!userGroupsList.isEmpty()) {
				_commerceUserSegmentCriterionLocalService.
					addCommerceUserSegmentCriterion(
						commerceUserSegmentEntry.
							getCommerceUserSegmentEntryId(),
						CommerceUserSegmentCriterionConstants.TYPE_USER_GROUP,
						ListUtil.toString(
							userGroupsList, StringPool.BLANK,
							StringPool.COMMA_AND_SPACE),
						0, serviceContext);
			}
		}

		// Add Role Criterion

		JSONArray rolesJSONArray = jsonObject.getJSONArray("Roles");

		if (rolesJSONArray != null) {
			List<Long> rolesList = new ArrayList<>();

			for (int i = 0; i < rolesJSONArray.length(); i++) {
				try {
					Role role = _roleLocalService.getRole(
						serviceContext.getCompanyId(),
						rolesJSONArray.getString(i));

					rolesList.add(role.getRoleId());
				}
				catch (NoSuchRoleException nsre) {
					_log.error(nsre, nsre);
				}
			}

			if (!rolesList.isEmpty()) {
				_commerceUserSegmentCriterionLocalService.
					addCommerceUserSegmentCriterion(
						commerceUserSegmentEntry.
							getCommerceUserSegmentEntryId(),
						CommerceUserSegmentCriterionConstants.TYPE_ROLE,
						ListUtil.toString(
							rolesList, StringPool.BLANK,
							StringPool.COMMA_AND_SPACE),
						0, serviceContext);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceUserSegmentsImporter.class);

	@Reference
	private CommerceUserSegmentCriterionLocalService
		_commerceUserSegmentCriterionLocalService;

	@Reference
	private CommerceUserSegmentEntryLocalService
		_commerceUserSegmentEntryLocalService;

	@Reference
	private OrganizationLocalService _organizationLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference
	private UserGroupLocalService _userGroupLocalService;

	@Reference
	private UserLocalService _userLocalService;

}