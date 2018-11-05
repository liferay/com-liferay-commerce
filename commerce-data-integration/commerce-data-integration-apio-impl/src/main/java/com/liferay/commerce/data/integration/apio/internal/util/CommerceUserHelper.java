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

package com.liferay.commerce.data.integration.apio.internal.util;

import com.liferay.apio.architect.functional.Try;
import com.liferay.commerce.data.integration.apio.identifier.ClassPKExternalReferenceCode;
import com.liferay.commerce.data.integration.apio.internal.form.CommerceUserUpserterForm;
import com.liferay.commerce.data.integration.apio.internal.model.UserWrapper;
import com.liferay.external.reference.service.ERUserLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.ListTypeConstants;
import com.liferay.portal.kernel.model.ListTypeModel;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ListTypeService;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.Optional;

import javax.ws.rs.BadRequestException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eduardo V. Bruno
 * @author Zoltán Takács
 */
@Component(immediate = true, service = CommerceUserHelper.class)
public class CommerceUserHelper {

	public void deleteUser(
			ClassPKExternalReferenceCode commerceUserCPKERC, Company company)
		throws PortalException {

		User user = getUser(commerceUserCPKERC, company.getCompanyId());

		if (user == null) {
			if (_log.isInfoEnabled()) {
				_log.info(
					"User does not exist with identifier: " +
						commerceUserCPKERC);
			}
		}
		else {
			_userLocalService.deleteUser(user);
		}
	}

	public User getUser(ClassPKExternalReferenceCode commerceUserCPKERC)
		throws PortalException {

		return getUser(commerceUserCPKERC, CompanyThreadLocal.getCompanyId());
	}

	public User getUser(
			ClassPKExternalReferenceCode commerceUserCPKERC, long companyId)
		throws PortalException {

		long classPK = commerceUserCPKERC.getClassPK();

		if (classPK > 0) {
			return _userLocalService.getUserById(classPK);
		}

		return _userLocalService.fetchUserByReferenceCode(
			companyId, commerceUserCPKERC.getExternalReferenceCode());
	}

	public UserWrapper updateUser(
			ClassPKExternalReferenceCode commerceUserCPKERC,
			CommerceUserUpserterForm commerceUserUpserterForm,
			ThemeDisplay themeDisplay)
		throws PortalException {

		User user = getUser(commerceUserCPKERC, themeDisplay.getCompanyId());

		Contact contact = user.getContact();

		long prefixId = _getPrefixId(
			commerceUserUpserterForm.getHonorificPrefix(),
			ListTypeConstants.CONTACT_PREFIX, contact.getPrefixId());
		long suffixId = _getPrefixId(
			commerceUserUpserterForm.getHonorificSuffix(),
			ListTypeConstants.CONTACT_SUFFIX, contact.getSuffixId());

		String alternateName = _getAlternateName(
			commerceUserUpserterForm, user);

		Date birthdayDate = user.getBirthday();

		user = _userLocalService.updateUser(
			user.getUserId(), user.getPassword(),
			commerceUserUpserterForm.getPassword(),
			commerceUserUpserterForm.getPassword(), false,
			user.getReminderQueryQuestion(), user.getReminderQueryAnswer(),
			alternateName, commerceUserUpserterForm.getEmail(),
			user.getFacebookId(), user.getOpenId(), false, null,
			user.getLanguageId(), user.getTimeZoneId(), user.getGreeting(),
			user.getComments(), commerceUserUpserterForm.getGivenName(),
			user.getMiddleName(), commerceUserUpserterForm.getFamilyName(),
			prefixId, suffixId, _isMale(commerceUserUpserterForm, user),
			_getDefaultValue(
				commerceUserUpserterForm.getBirthdayMonthOptional(),
				birthdayDate.getMonth()),
			_getDefaultValue(
				commerceUserUpserterForm.getBirthdayDayOptional(),
				birthdayDate.getDate()),
			_getDefaultValue(
				commerceUserUpserterForm.getBirthdayYearOptional(),
				birthdayDate.getYear()),
			contact.getSmsSn(), contact.getFacebookSn(), contact.getJabberSn(),
			contact.getSkypeSn(), contact.getTwitterSn(),
			commerceUserUpserterForm.getJobTitle(), user.getGroupIds(),
			user.getOrganizationIds(), user.getRoleIds(), null,
			user.getUserGroupIds(), new ServiceContext());

		return new UserWrapper(user, themeDisplay);
	}

	public UserWrapper upsert(
			CommerceUserUpserterForm commerceUserUpserterForm,
			ThemeDisplay themeDisplay)
		throws PortalException {

		long[] commerceAccountIds = _extractCommerceAccountIds(
			commerceUserUpserterForm, themeDisplay.getCompanyId());

		long[] roleIds = _getRoleNames(
			commerceUserUpserterForm, themeDisplay.getCompanyId());

		String password = commerceUserUpserterForm.getPassword();

		String screenName = _getScreenName(
			themeDisplay.getCompanyId(), commerceUserUpserterForm);

		User user = _erUserLocalService.addOrUpdateUser(
			commerceUserUpserterForm.getExternalReferenceCode(),
			themeDisplay.getUserId(), themeDisplay.getCompanyId(),
			password == null, password, password, screenName == null,
			screenName, commerceUserUpserterForm.getEmail(),
			LocaleUtil.getDefault(), commerceUserUpserterForm.getGivenName(),
			null, commerceUserUpserterForm.getFamilyName(), 0, 0, true, 1, 1,
			1970, commerceUserUpserterForm.getJobTitle(), null,
			commerceAccountIds, roleIds, null, null, true,
			new ServiceContext());

		return new UserWrapper(user, themeDisplay);
	}

	public ClassPKExternalReferenceCode userToClassPKExternalReferenceCode(
		User user) {

		if (user == null) {
			return null;
		}

		return ClassPKExternalReferenceCode.create(
			user.getUserId(), user.getExternalReferenceCode());
	}

	private long[] _extractCommerceAccountIds(
		CommerceUserUpserterForm commerceUserUpserterForm, long companyId) {

		String accountExternalReferenceCode =
			commerceUserUpserterForm.getAccountExternalReferenceCode();

		long[] commerceAccountIds =
			commerceUserUpserterForm.getCommerceAccountIds();

		if (ArrayUtil.isEmpty(commerceAccountIds) &&
			!Validator.isBlank(accountExternalReferenceCode)) {

			Organization organization =
				_organizationLocalService.fetchOrganizationByReferenceCode(
					companyId, accountExternalReferenceCode);

			if (organization == null) {
				throw new BadRequestException(
					"Account with external reference code " +
						accountExternalReferenceCode + " not found");
			}

			commerceAccountIds = new long[] {organization.getOrganizationId()};
		}

		return commerceAccountIds;
	}

	private String _getAlternateName(
		CommerceUserUpserterForm commerceUserUpserterForm, User user) {

		if (commerceUserUpserterForm.getAlternateName() == null) {
			return user.getScreenName();
		}

		return commerceUserUpserterForm.getAlternateName();
	}

	private Integer _getDefaultValue(
		Optional<Integer> optional, int defaultValue) {

		return optional.orElse(defaultValue);
	}

	private long _getPrefixId(
		String honorificTitle, String className, long defaultTitle) {

		return Try.fromFallible(
			() -> _listTypeService.getListType(honorificTitle, className)
		).map(
			ListTypeModel::getListTypeId
		).orElse(
			defaultTitle
		);
	}

	private long[] _getRoleNames(
		CommerceUserUpserterForm commerceUserUpserterForm, long companyId) {

		String[] roleNames = commerceUserUpserterForm.getRoleNames();

		if (ArrayUtil.isEmpty(roleNames)) {
			return new long[0];
		}

		long[] roleIds = new long[roleNames.length];

		for (int i = 0; i < roleNames.length; i++) {
			Role role = _roleLocalService.fetchRole(companyId, roleNames[i]);

			if (role == null) {
				throw new BadRequestException(
					"Invalid role name: " + roleNames[i]);
			}

			roleIds[i] = role.getRoleId();
		}

		return roleIds;
	}

	private String _getScreenName(
		long companyId, CommerceUserUpserterForm commerceUserUpserterForm) {

		User user = _userLocalService.fetchUserByReferenceCode(
			companyId, commerceUserUpserterForm.getExternalReferenceCode());

		if (user == null) {
			return null;
		}

		return user.getScreenName();
	}

	private Boolean _isMale(
			CommerceUserUpserterForm commerceUserUpserterForm, User user)
		throws PortalException {

		Optional<Boolean> optional = commerceUserUpserterForm.isMaleOptional();

		return optional.orElse(user.isMale());
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceUserHelper.class);

	@Reference
	private ERUserLocalService _erUserLocalService;

	@Reference
	private ListTypeService _listTypeService;

	@Reference
	private OrganizationLocalService _organizationLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference
	private UserLocalService _userLocalService;

}