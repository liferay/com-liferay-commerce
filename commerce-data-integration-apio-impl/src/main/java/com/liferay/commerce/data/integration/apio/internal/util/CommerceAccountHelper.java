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

import com.liferay.commerce.data.integration.apio.identifier.ClassPKExternalReferenceCode;
import com.liferay.commerce.organization.constants.CommerceOrganizationConstants;
import com.liferay.commerce.organization.service.CommerceOrganizationService;
import com.liferay.external.reference.service.EROrganizationLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ListTypeConstants;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.UserLocalService;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rodrigo Guedes de Souza
 * @author Eduardo V. Bruno
 * @author Zoltán Takács
 */
@Component(immediate = true, service = CommerceAccountHelper.class)
public class CommerceAccountHelper {

	public void deleteOrganization(
			ClassPKExternalReferenceCode commerceAccountCPKERC, Company company)
		throws PortalException {

		Organization organization = getOrganization(
			commerceAccountCPKERC, company);

		if (organization == null) {
			if (_log.isInfoEnabled()) {
				_log.info("Account does not exist: " + commerceAccountCPKERC);
			}
		}
		else {
			long organizationId = organization.getOrganizationId();

			_removeAllMembers(organizationId);
			_commerceOrganizationService.deleteOrganization(organizationId);
		}
	}

	public Organization getOrganization(
			ClassPKExternalReferenceCode commerceAccountCPKERC, Company company)
		throws PortalException {

		return getOrganization(commerceAccountCPKERC, company.getCompanyId());
	}

	public Organization getOrganization(
			ClassPKExternalReferenceCode commerceAccountCPKERC, long companyId)
		throws PortalException {

		long organizationId = commerceAccountCPKERC.getClassPK();

		if (organizationId > 0) {
			return _commerceOrganizationService.getOrganization(organizationId);
		}

		String externalReferenceCode =
			commerceAccountCPKERC.getExternalReferenceCode();

		return _organizationLocalService.fetchOrganizationByReferenceCode(
			companyId, externalReferenceCode);
	}

	public long getParentOrganizationId(Long groupId) {
		Group group = _groupLocalService.fetchGroup(groupId);

		long parentOrganizationId = 0;

		if (group == null) {
			return parentOrganizationId;
		}

		parentOrganizationId = group.getOrganizationId();

		if (_log.isDebugEnabled()) {
			_log.debug("Parent Organization ID: " + parentOrganizationId);
		}

		return parentOrganizationId;
	}

	public ClassPKExternalReferenceCode
		organizationIdToClassPKExternalReferenceCode(long organizationId) {

		Organization organization = _organizationLocalService.fetchOrganization(
			organizationId);

		return organizationToClassPKExternalReferenceCode(organization);
	}

	public ClassPKExternalReferenceCode
		organizationToClassPKExternalReferenceCode(Organization organization) {

		if (organization == null) {
			return null;
		}

		return ClassPKExternalReferenceCode.create(
			organization.getOrganizationId(),
			organization.getExternalReferenceCode());
	}

	public Organization updateOrganization(
			ClassPKExternalReferenceCode commerceAccountCPKERC, String name,
			long regionId, long countryId, List<Long> userIds, User currentUser)
		throws PortalException {

		Organization organization = getOrganization(
			commerceAccountCPKERC, currentUser.getCompanyId());

		organization = _organizationLocalService.updateOrganization(
			currentUser.getCompanyId(), organization.getOrganizationId(),
			organization.getParentOrganizationId(), name,
			CommerceOrganizationConstants.TYPE_ACCOUNT, regionId, countryId,
			ListTypeConstants.ORGANIZATION_STATUS_DEFAULT, StringPool.BLANK,
			false, null, false,
			_serviceContextHelper.getServiceContext(currentUser));

		_setMembers(organization, userIds);

		return organization;
	}

	public Organization upsertOrganization(
			String externalReferenceCode, long parentOrganizationId,
			String name, long regionId, long countryId, List<Long> userIds,
			User currentUser)
		throws PortalException {

		Organization organization =
			_erOrganizationLocalService.addOrUpdateOrganization(
				externalReferenceCode, currentUser.getUserId(),
				parentOrganizationId, name,
				CommerceOrganizationConstants.TYPE_ACCOUNT, regionId, countryId,
				ListTypeConstants.ORGANIZATION_STATUS_DEFAULT, StringPool.BLANK,
				false, false, null,
				_serviceContextHelper.getServiceContext(currentUser));

		_setMembers(organization, userIds);

		return organization;
	}

	private void _removeAllMembers(long organizationId) {
		_userService.clearOrganizationUsers(organizationId);
	}

	private void _setMembers(Organization organization, List<Long> userIds) {
		if (userIds != null) {
			_removeAllMembers(organization.getOrganizationId());

			for (Long userId : userIds) {
				try {
					User userMember = _userService.getUser(userId);

					if (userMember != null) {
						_userService.addOrganizationUser(
							organization.getOrganizationId(), userId);
					}
				}
				catch (PortalException pe) {
					_log.error(
						"Unable to add member to organization with ID: " +
							organization.getOrganizationId());

					if (_log.isDebugEnabled()) {
						_log.debug(pe, pe);
					}
				}
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceAccountHelper.class);

	@Reference
	private CommerceOrganizationService _commerceOrganizationService;

	@Reference
	private EROrganizationLocalService _erOrganizationLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private OrganizationLocalService _organizationLocalService;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

	@Reference
	private UserLocalService _userService;

}