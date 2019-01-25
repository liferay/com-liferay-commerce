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

package com.liferay.commerce.openapi.admin.internal.resource.util;

import com.liferay.commerce.openapi.admin.internal.util.DTOUtils;
import com.liferay.commerce.openapi.admin.internal.util.IdUtils;
import com.liferay.commerce.openapi.admin.model.CollectionDTO;
import com.liferay.commerce.openapi.admin.model.UserDTO;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.external.reference.service.ERUserLocalService;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.RoleService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.UserService;
import com.liferay.portal.kernel.service.permission.UserPermission;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Matija Petanjek
 */
@Component(immediate = true, service = UserHelper.class)
public class UserHelper {

	public void deleteUser(long companyId, String id) throws PortalException {
		if (IdUtils.isLocalPK(id)) {
			_userService.deleteUser(GetterUtil.getLong(id));

			return;
		}

		User user = _userLocalService.fetchUserByReferenceCode(
			companyId, IdUtils.getExternalReferenceCodeFromId(id));

		_userService.deleteUser(user.getUserId());
	}

	public User getUser(
			long companyId, String id, PermissionChecker permissionChecker)
		throws PortalException {

		if (IdUtils.isLocalPK(id)) {
			return _userService.getUserById(GetterUtil.getLong(id));
		}

		User user = _userLocalService.fetchUserByReferenceCode(
			companyId, IdUtils.getExternalReferenceCodeFromId(id));

		if (user == null) {
			throw new NoSuchUserException("Unable to find user with ID " + id);
		}

		_userPermission.check(
			permissionChecker, user.getUserId(), ActionKeys.VIEW);

		return user;
	}

	public UserDTO getUserDTO(
			long companyId, String id, PermissionChecker permissionChecker,
			ThemeDisplay themeDisplay)
		throws PortalException {

		User user = getUser(companyId, id, permissionChecker);

		String dashboardURL = _getDashboardURL(user, themeDisplay);
		String profileURL = _getProfileURL(user, themeDisplay);
		String[] roleNames = _getRoleNames(user);

		return DTOUtils.modelToDTO(
			user, user.getOrganizationIds(), dashboardURL, profileURL,
			roleNames, themeDisplay);
	}

	public CollectionDTO<UserDTO> getUserDTOs(
			long companyId, Pagination pagination,
			PermissionChecker permissionChecker, ThemeDisplay themeDisplay)
		throws PortalException {

		List<User> users = _userService.getCompanyUsers(
			companyId, pagination.getStartPosition(),
			pagination.getEndPosition());

		int count = _userService.getCompanyUsersCount(companyId);

		List<UserDTO> userDTOs = new ArrayList<>();

		for (User user : users) {
			userDTOs.add(
				getUserDTO(
					companyId, String.valueOf(user.getUserId()),
					permissionChecker, themeDisplay));
		}

		return new CollectionDTO<>(userDTOs, count);
	}

	public User updateUser(
			long companyId, String id, PermissionChecker permissionChecker,
			UserDTO userDTO)
		throws PortalException {

		User user = getUser(companyId, id, permissionChecker);

		String alternateName = _getAlternateName(user, userDTO);

		Date birthday = _getBirthday(user, userDTO);

		Contact contact = user.getContact();

		return _userService.updateUser(
			user.getUserId(), user.getPassword(), null, null, false,
			user.getReminderQueryQuestion(), user.getReminderQueryAnswer(),
			alternateName, userDTO.getEmail(), user.getFacebookId(),
			user.getOpenId(), user.getLanguageId(), user.getTimeZoneId(),
			user.getGreeting(), user.getComments(), userDTO.getGivenName(),
			user.getMiddleName(), userDTO.getFamilyName(), 0L, 0L,
			user.isMale(), birthday.getMonth(), birthday.getDate(),
			birthday.getYear(), contact.getSmsSn(), contact.getFacebookSn(),
			contact.getJabberSn(), contact.getSkypeSn(), contact.getTwitterSn(),
			userDTO.getJobTitle(), user.getGroupIds(),
			user.getOrganizationIds(), user.getRoleIds(), null,
			user.getUserGroupIds(), new ServiceContext());
	}

	public UserDTO upsertUser(
			long companyId, PermissionChecker permissionChecker,
			UserDTO userDTO, ThemeDisplay themeDisplay)
		throws PortalException {

		Date birthDate = userDTO.getBirthDate();

		User user = _erUserLocalService.addOrUpdateUser(
			userDTO.getExternalReferenceCode(), themeDisplay.getUserId(),
			companyId, true, null, null, userDTO.getAlternateName() == null,
			userDTO.getAlternateName(), userDTO.getEmail(),
			LocaleUtil.getDefault(), userDTO.getGivenName(),
			userDTO.getAdditionalName(), userDTO.getFamilyName(), 0, 0,
			"male".equals(userDTO.getGender()), birthDate.getMonth(),
			birthDate.getDay(), birthDate.getYear(), userDTO.getJobTitle(),
			null, userDTO.getCommerceAccountIds(),
			_getRoleIds(companyId, userDTO.getRoleNames()), null, null, true,
			new ServiceContext());

		return getUserDTO(
			companyId, String.valueOf(user.getUserId()), permissionChecker,
			themeDisplay);
	}

	private String _getAlternateName(User user, UserDTO userDTO) {
		if (userDTO.getAlternateName() == null) {
			return user.getScreenName();
		}

		return userDTO.getAlternateName();
	}

	private Date _getBirthday(User user, UserDTO userDTO)
		throws PortalException {

		if (userDTO.getBirthDate() == null) {
			return user.getBirthday();
		}

		return userDTO.getBirthDate();
	}

	private String _getDashboardURL(User user, ThemeDisplay themeDisplay)
		throws PortalException {

		if (user.getPrivateLayoutsPageCount() > 0) {
			Group userGroup = user.getGroup();

			return userGroup.getDisplayURL(themeDisplay, true);
		}

		return null;
	}

	private String _getProfileURL(User user, ThemeDisplay themeDisplay)
		throws PortalException {

		if (user.getPublicLayoutsPageCount() > 0) {
			Group userGroup = user.getGroup();

			return userGroup.getDisplayURL(themeDisplay, false);
		}

		return null;
	}

	private long[] _getRoleIds(long companyId, String[] roleNames)
		throws PortalException {

		long[] roleIds = new long[roleNames.length];

		for (int i = 0; i < roleNames.length; i++) {
			String roleName = roleNames[i];

			Role role = _roleService.getRole(companyId, roleName);

			roleIds[i] = role.getRoleId();
		}

		return roleIds;
	}

	private String[] _getRoleNames(User user) throws PortalException {
		long[] roleIds = user.getRoleIds();

		String[] roleNames = new String[roleIds.length];

		for (int i = 0; i < roleIds.length; i++) {
			long roleId = roleIds[i];

			Role role = _roleService.getRole(roleId);

			roleNames[i] = role.getName();
		}

		return roleNames;
	}

	@Reference
	private ERUserLocalService _erUserLocalService;

	@Reference
	private RoleService _roleService;

	@Reference
	private UserLocalService _userLocalService;

	@Reference
	private UserPermission _userPermission;

	@Reference
	private UserService _userService;

}