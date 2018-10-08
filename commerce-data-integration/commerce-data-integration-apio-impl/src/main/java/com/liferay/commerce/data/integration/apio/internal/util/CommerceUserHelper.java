/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.commerce.data.integration.apio.internal.util;

import com.liferay.commerce.data.integration.apio.identifiers.ClassPKExternalReferenceCode;
import com.liferay.commerce.data.integration.apio.internal.form.CommerceUserUpserterForm;
import com.liferay.external.reference.service.ERUserLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserWrapper;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;

import javax.ws.rs.BadRequestException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eduardo V. Bruno
 */
@Component(immediate = true, service = CommerceUserHelper.class)
public class CommerceUserHelper {

	public void deleteUser(
			ClassPKExternalReferenceCode classPKExternalReferenceCode,
			Company company)
		throws PortalException {

		User user = getUser(classPKExternalReferenceCode, company);

		if (user == null) {
			if (_log.isInfoEnabled()) {
				_log.info(
					"User does not exist, entry " +
						classPKExternalReferenceCode.getClassPK() + ":" +
							classPKExternalReferenceCode.
								getExternalReferenceCode());
			}
		}
		else {
			_userLocalService.deleteUser(user);
		}
	}

	public User getUser(
			ClassPKExternalReferenceCode classPKExternalReferenceCode,
			Company company)
		throws PortalException {

		long classPK = classPKExternalReferenceCode.getClassPK();

		if (classPK > 0) {
			return _userLocalService.getUserById(classPK);
		}

		return _userLocalService.fetchUserByReferenceCode(
			company.getCompanyId(),
			classPKExternalReferenceCode.getExternalReferenceCode());
	}

	public UserWrapper upsert(
			long companyId, long userId,
			CommerceUserUpserterForm commerceUserUpserterForm)
		throws PortalException {

		long[] commerceAccountIds = _extractCommerceAccountIds(
			commerceUserUpserterForm, companyId);

		long[] roleIds = _getRoleNames(commerceUserUpserterForm, companyId);

		String screenName = _getScreenName(companyId, commerceUserUpserterForm);

		User user = _erUserLocalService.addOrUpdateUser(
			commerceUserUpserterForm.getExternalReferenceCode(), userId,
			companyId, true, null, null, screenName == null, screenName,
			commerceUserUpserterForm.getEmail(), LocaleUtil.getDefault(),
			commerceUserUpserterForm.getGivenName(), null,
			commerceUserUpserterForm.getFamilyName(), 0, 0, true, 1, 1, 1970,
			commerceUserUpserterForm.getJobTitle(), null, commerceAccountIds,
			roleIds, null, null, true, new ServiceContext());

		return new UserWrapper(user);
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

	private long[] _getRoleNames(
		CommerceUserUpserterForm form, long companyId) {

		String[] roleNames = form.getRoleNames();

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

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceUserHelper.class);

	@Reference
	private ERUserLocalService _erUserLocalService;

	@Reference
	private OrganizationLocalService _organizationLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference
	private UserLocalService _userLocalService;

}