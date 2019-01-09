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

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountLocalService;
import com.liferay.commerce.account.service.CommerceAccountService;
import com.liferay.commerce.account.service.CommerceAccountUserRelService;
import com.liferay.commerce.data.integration.apio.identifier.ClassPKExternalReferenceCode;
import com.liferay.external.reference.service.EROrganizationLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rodrigo Guedes de Souza
 * @author Eduardo V. Bruno
 * @author Zoltán Takács
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = CommerceAccountHelper.class)
public class CommerceAccountHelper {

	public ClassPKExternalReferenceCode
		commerceAccountIdToClassPKExternalReferenceCode(
			long commerceAccountId) {

		CommerceAccount commerceAccount =
			_commerceAccountLocalService.fetchCommerceAccount(
				commerceAccountId);

		return commerceAccountToClassPKExternalReferenceCode(commerceAccount);
	}

	public ClassPKExternalReferenceCode
		commerceAccountToClassPKExternalReferenceCode(
			CommerceAccount commerceAccount) {

		if (commerceAccount == null) {
			return null;
		}

		return ClassPKExternalReferenceCode.create(
			commerceAccount.getCommerceAccountId(),
			commerceAccount.getExternalReferenceCode());
	}

	public void deleteOrganization(
			ClassPKExternalReferenceCode commerceAccountCPKERC, Company company)
		throws PortalException {

		CommerceAccount commerceAccount = getCommerceAccount(
			commerceAccountCPKERC, company);

		if (commerceAccount == null) {
			if (_log.isInfoEnabled()) {
				_log.info("Account does not exist: " + commerceAccountCPKERC);
			}
		}
		else {
			long commerceAccountId = commerceAccount.getCommerceAccountId();

			_removeAllMembers(commerceAccountId);
			_commerceAccountService.deleteCommerceAccount(commerceAccountId);
		}
	}

	public CommerceAccount getCommerceAccount(
			ClassPKExternalReferenceCode commerceAccountCPKERC, Company company)
		throws PortalException {

		return getCommerceAccount(
			commerceAccountCPKERC, company.getCompanyId());
	}

	public CommerceAccount getCommerceAccount(
			ClassPKExternalReferenceCode commerceAccountCPKERC, long companyId)
		throws PortalException {

		long commerceAccountId = commerceAccountCPKERC.getClassPK();

		if (commerceAccountId > 0) {
			return _commerceAccountService.getCommerceAccount(
				commerceAccountId);
		}

		String externalReferenceCode =
			commerceAccountCPKERC.getExternalReferenceCode();

		return _commerceAccountLocalService.fetchCommerceAccountByReferenceCode(
			companyId, externalReferenceCode);
	}

	public long getParentCommerceAccountId(Long commerceAccountId) {
		CommerceAccount commerceAccount =
			_commerceAccountLocalService.fetchCommerceAccount(
				commerceAccountId);

		long parentCommerceAccountId = 0;

		if (commerceAccount == null) {
			return parentCommerceAccountId;
		}

		parentCommerceAccountId = commerceAccount.getParentCommerceAccountId();

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Parent Commerce Account ID: " + parentCommerceAccountId);
		}

		return parentCommerceAccountId;
	}

	public CommerceAccount updateCommerceAccount(
			ClassPKExternalReferenceCode commerceAccountCPKERC, String name,
			String email, String taxId, boolean active, List<Long> userIds,
			User currentUser)
		throws PortalException {

		CommerceAccount commerceAccount = getCommerceAccount(
			commerceAccountCPKERC, currentUser.getCompanyId());

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			currentUser);

		commerceAccount = _commerceAccountLocalService.updateCommerceAccount(
			commerceAccount.getCommerceAccountId(), name, email, taxId, active,
			serviceContext);

		_setMembers(commerceAccount, userIds, serviceContext);

		return commerceAccount;
	}

	public CommerceAccount upsertCommerceAccount(
			String name, long parentCommerceAccountId, boolean logo,
			byte[] logoBytes, String email, String taxId, int type,
			boolean active, String externalReferenceCode, List<Long> userIds,
			User currentUser)
		throws PortalException {

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			currentUser);

		CommerceAccount commerceAccount =
			_commerceAccountLocalService.upsertCommerceAccount(
				name, parentCommerceAccountId, logo, logoBytes, email, taxId,
				type, active, externalReferenceCode, serviceContext);

		_setMembers(commerceAccount, userIds, serviceContext);

		return commerceAccount;
	}

	private void _removeAllMembers(long commerceAccountId)
		throws PortalException {

		_commerceAccountUserRelService.deleteCommerceAccountUserRels(
			commerceAccountId);
	}

	private void _setMembers(
			CommerceAccount commerceAccount, List<Long> userIds,
			ServiceContext serviceContext)
		throws PortalException {

		if (userIds != null) {
			_removeAllMembers(commerceAccount.getCommerceAccountId());

			for (Long userId : userIds) {
				try {
					User userMember = _userService.getUser(userId);

					if (userMember != null) {
						_commerceAccountUserRelService.
							addCommerceAccountUserRel(
								commerceAccount.getCommerceAccountId(),
								userMember.getUserId(), serviceContext);
					}
				}
				catch (PortalException pe) {
					_log.error(
						"Unable to add member to account with ID: " +
							commerceAccount.getCommerceAccountId());

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
	private CommerceAccountLocalService _commerceAccountLocalService;

	@Reference
	private CommerceAccountService _commerceAccountService;

	@Reference
	private CommerceAccountUserRelService _commerceAccountUserRelService;

	@Reference
	private EROrganizationLocalService _erOrganizationLocalService;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

	@Reference
	private UserLocalService _userService;

}