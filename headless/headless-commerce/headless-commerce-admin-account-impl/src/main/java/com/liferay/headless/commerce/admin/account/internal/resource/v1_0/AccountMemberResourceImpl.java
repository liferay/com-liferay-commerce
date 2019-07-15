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

package com.liferay.headless.commerce.admin.account.internal.resource.v1_0;

import com.liferay.commerce.account.exception.NoSuchAccountException;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.model.CommerceAccountUserRel;
import com.liferay.commerce.account.service.CommerceAccountService;
import com.liferay.commerce.account.service.CommerceAccountUserRelService;
import com.liferay.headless.commerce.admin.account.dto.v1_0.AccountMember;
import com.liferay.headless.commerce.admin.account.dto.v1_0.AccountRole;
import com.liferay.headless.commerce.admin.account.resource.v1_0.AccountMemberResource;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterRegistry;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;
import com.liferay.headless.commerce.core.util.LanguageUtils;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.RoleService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/account-member.properties",
	scope = ServiceScope.PROTOTYPE, service = AccountMemberResource.class
)
public class AccountMemberResourceImpl extends BaseAccountMemberResourceImpl {

	@Override
	public Page<AccountMember>
			getAccountByExternalReferenceCodeAccountMembersPage(
				String externalReferenceCode, Pagination pagination)
		throws Exception {

		CommerceAccount commerceAccount =
			_commerceAccountService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceAccount == null) {
			throw new NoSuchAccountException(
				"Unable to find Account with externalReferenceCode: " +
					externalReferenceCode);
		}

		return _getAccountMembersPage(commerceAccount, pagination);
	}

	@Override
	public Page<AccountMember> getAccountIdAccountMembersPage(
			Long id, Pagination pagination)
		throws Exception {

		return _getAccountMembersPage(
			_commerceAccountService.getCommerceAccount(id), pagination);
	}

	@Override
	public AccountMember postAccountByExternalReferenceCodeAccountMember(
			String externalReferenceCode, AccountMember accountMember)
		throws Exception {

		CommerceAccount commerceAccount =
			_commerceAccountService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceAccount == null) {
			throw new NoSuchAccountException(
				"Unable to find Account with externalReferenceCode: " +
					externalReferenceCode);
		}

		return _addAccountMember(commerceAccount, accountMember);
	}

	@Override
	public AccountMember postAccountIdAccountMember(
			Long id, AccountMember accountMember)
		throws Exception {

		return _addAccountMember(
			_commerceAccountService.getCommerceAccount(id), accountMember);
	}

	private AccountMember _addAccountMember(
			CommerceAccount commerceAccount, AccountMember accountMember)
		throws Exception {

		CommerceAccountUserRel commerceAccountUserRel;

		User user = _userLocalService.fetchUserByEmailAddress(
			contextCompany.getCompanyId(), accountMember.getEmail());

		if (user == null) {
			commerceAccountUserRel = _commerceAccountUserRelService.inviteUser(
				commerceAccount.getCommerceAccountId(),
				accountMember.getEmail(),
				_getAccountRoleIds(
					commerceAccount.getCommerceAccountId(), accountMember),
				accountMember.getExternalReferenceCode(),
				_serviceContextHelper.getServiceContext());
		}
		else {
			commerceAccountUserRel =
				_commerceAccountUserRelService.addCommerceAccountUserRel(
					commerceAccount.getCommerceAccountId(), user.getUserId(),
					_getAccountRoleIds(
						commerceAccount.getCommerceAccountId(), accountMember),
					_serviceContextHelper.getServiceContext());
		}

		DTOConverter accountMemberDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceAccountUserRel.class.getName());

		return (AccountMember)accountMemberDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				commerceAccountUserRel.getPrimaryKey()));
	}

	private Page<AccountMember> _getAccountMembersPage(
			CommerceAccount commerceAccount, Pagination pagination)
		throws Exception {

		List<CommerceAccountUserRel> commerceAccountUserRels =
			_commerceAccountUserRelService.getCommerceAccountUserRels(
				commerceAccount.getCommerceAccountId(),
				pagination.getStartPosition(), pagination.getEndPosition());

		int totalItems =
			_commerceAccountUserRelService.getCommerceAccountUserRelsCount(
				commerceAccount.getCommerceAccountId());

		return Page.of(
			_toAccountMembers(commerceAccountUserRels), pagination, totalItems);
	}

	private long[] _getAccountRoleIds(
			long commerceAccountId, AccountMember accountMember)
		throws PortalException {

		List<Role> roles = new ArrayList<>();

		AccountRole[] accountRoles = accountMember.getRoles();

		if ((accountRoles != null) && (accountRoles.length > 0)) {
			for (AccountRole accountRole : accountRoles) {
				roles.add(_upsertRole(commerceAccountId, accountRole));
			}

			Stream<Role> roleStream = roles.stream();

			return roleStream.mapToLong(
				Role::getRoleId
			).toArray();
		}

		return null;
	}

	private List<AccountMember> _toAccountMembers(
			List<CommerceAccountUserRel> commerceAccountUserRels)
		throws Exception {

		List<AccountMember> accountMembers = new ArrayList<>();

		DTOConverter accountMemberDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceAccountUserRel.class.getName());

		for (CommerceAccountUserRel commerceAccountUserRel :
				commerceAccountUserRels) {

			accountMembers.add(
				(AccountMember)accountMemberDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						contextAcceptLanguage.getPreferredLocale(),
						commerceAccountUserRel.getPrimaryKey())));
		}

		return accountMembers;
	}

	private Role _upsertRole(long commerceAccountId, AccountRole accountRole)
		throws PortalException {

		Role role = _roleLocalService.fetchRole(
			contextCompany.getCompanyId(), accountRole.getName());

		if (role != null) {
			return role;
		}

		return _roleService.addRole(
			CommerceAccount.class.getName(), commerceAccountId,
			accountRole.getName(),
			LanguageUtils.getLocalizedMap(accountRole.getTitle()),
			LanguageUtils.getLocalizedMap(accountRole.getDescription()),
			RoleConstants.TYPE_SITE, null,
			_serviceContextHelper.getServiceContext());
	}

	@Reference
	private CommerceAccountService _commerceAccountService;

	@Reference
	private CommerceAccountUserRelService _commerceAccountUserRelService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference
	private RoleService _roleService;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

	@Reference
	private UserLocalService _userLocalService;

}