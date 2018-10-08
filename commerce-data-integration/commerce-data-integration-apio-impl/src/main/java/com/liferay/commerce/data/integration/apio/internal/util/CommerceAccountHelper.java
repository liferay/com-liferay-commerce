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

import com.liferay.commerce.data.integration.apio.identifiers.ClassPKExternalReferenceCode;
import com.liferay.commerce.organization.constants.CommerceOrganizationConstants;
import com.liferay.commerce.organization.service.CommerceOrganizationService;
import com.liferay.external.reference.service.EROrganizationLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.ListTypeConstants;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rodrigo Guedes de Souza
 * @author Eduardo V. Bruno
 */
@Component(immediate = true, service = CommerceAccountHelper.class)
public class CommerceAccountHelper {

	public void deleteOrganization(
			ClassPKExternalReferenceCode classPKExternalReferenceCode,
			Company company)
		throws PortalException {

		Organization organization = getOrganization(
			classPKExternalReferenceCode, company);

		if (organization == null) {
			if (_log.isInfoEnabled()) {
				_log.info(
					String.format(
						"Account does not exist with identifier %d : %s",
						classPKExternalReferenceCode.getClassPK(),
						classPKExternalReferenceCode.
							getExternalReferenceCode()));
			}
		}
		else {
			long organizationId = organization.getOrganizationId();

			_removeAllMembers(organizationId);
			_commerceOrganizationService.deleteOrganization(organizationId);
		}
	}

	public Organization getOrganization(
			ClassPKExternalReferenceCode classPKExternalReferenceCode,
			Company company)
		throws PortalException {

		return getOrganization(
			classPKExternalReferenceCode, company.getCompanyId());
	}

	public Organization getOrganization(
			ClassPKExternalReferenceCode classPKExternalReferenceCode,
			long companyId)
		throws PortalException {

		long organizationId = classPKExternalReferenceCode.getClassPK();

		if (organizationId > 0) {
			return _commerceOrganizationService.getOrganization(organizationId);
		}

		String externalReferenceCode =
			classPKExternalReferenceCode.getExternalReferenceCode();

		return _organizationLocalService.fetchOrganizationByReferenceCode(
			companyId, externalReferenceCode);
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

	public Organization upsert(
			String externalReferenceCode, long parentOrganizationId,
			String name, long regionId, long countryId, List<Long> userIds,
			User currentUser)
		throws PortalException {

		ServiceContext serviceContext = _getServiceContext(currentUser);

		Organization organization =
			_erOrganizationLocalService.addOrUpdateOrganization(
				externalReferenceCode, serviceContext.getUserId(),
				parentOrganizationId, name,
				CommerceOrganizationConstants.TYPE_ACCOUNT, regionId, countryId,
				ListTypeConstants.ORGANIZATION_STATUS_DEFAULT, StringPool.BLANK,
				false, false, null, serviceContext);

		_addMembers(organization, userIds);

		return organization;
	}

	private void _addMembers(Organization organization, List<Long> userIds) {
		if (userIds != null) {
			_removeAllMembers(organization.getOrganizationId());

			for (Long userId : userIds) {
				try {
					User userMember = _userLocalService.getUser(userId);

					if (userMember != null) {
						_userLocalService.addOrganizationUser(
							organization.getOrganizationId(), userId);
					}
				}
				catch (PortalException pe) {
					_log.error("Error on add member", pe);
				}
			}
		}
	}

	private ServiceContext _getServiceContext(User curreUser)
		throws PortalException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);
		serviceContext.setCompanyId(curreUser.getCompanyId());
		serviceContext.setTimeZone(curreUser.getTimeZone());
		serviceContext.setUserId(curreUser.getUserId());

		return serviceContext;
	}

	private void _removeAllMembers(long organizationId) {
		_userLocalService.clearOrganizationUsers(organizationId);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceAccountHelper.class);

	@Reference
	private CommerceOrganizationService _commerceOrganizationService;

	@Reference
	private EROrganizationLocalService _erOrganizationLocalService;

	@Reference
	private OrganizationLocalService _organizationLocalService;

	@Reference
	private UserLocalService _userLocalService;

}