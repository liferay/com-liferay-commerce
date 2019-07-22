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
import com.liferay.commerce.account.service.persistence.CommerceAccountUserRelPK;
import com.liferay.headless.commerce.admin.account.dto.v1_0.AccountMember;
import com.liferay.headless.commerce.admin.account.dto.v1_0.AccountRole;
import com.liferay.headless.commerce.admin.account.internal.util.v1_0.AccountMemberUtil;
import com.liferay.headless.commerce.admin.account.resource.v1_0.AccountMemberResource;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterRegistry;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import javax.ws.rs.core.Response;

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
	public Response deleteAccountByExternalReferenceCodeAccountMember(
			String externalReferenceCode, Long userId)
		throws Exception {

		CommerceAccount commerceAccount =
			_commerceAccountService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceAccount == null) {
			throw new NoSuchAccountException(
				"Unable to find Account with externalReferenceCode: " +
					externalReferenceCode);
		}

		_commerceAccountUserRelService.deleteCommerceAccountUserRel(
			commerceAccount.getCommerceAccountId(), userId);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Response deleteAccountIdAccountMember(Long id, Long userId)
		throws Exception {

		_commerceAccountUserRelService.deleteCommerceAccountUserRel(id, userId);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public AccountMember getAccountByExternalReferenceCodeAccountMember(
			String externalReferenceCode, Long userId)
		throws Exception {

		CommerceAccount commerceAccount =
			_commerceAccountService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceAccount == null) {
			throw new NoSuchAccountException(
				"Unable to find Account with externalReferenceCode: " +
					externalReferenceCode);
		}

		CommerceAccountUserRel commerceAccountUserRel =
			_commerceAccountUserRelService.getCommerceAccountUserRel(
				new CommerceAccountUserRelPK(
					commerceAccount.getCommerceAccountId(), userId));

		DTOConverter accountMemberDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceAccountUserRel.class.getName());

		return (AccountMember)accountMemberDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				commerceAccountUserRel.getPrimaryKey()));
	}

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

	@Override
	public AccountMember getAccountIdAccountMember(Long id, Long userId)
		throws Exception {

		DTOConverter accountMemberDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceAccountUserRel.class.getName());

		return (AccountMember)accountMemberDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				new CommerceAccountUserRelPK(id, userId)));
	}

	@Override
	public Page<AccountMember> getAccountIdAccountMembersPage(
			Long id, Pagination pagination)
		throws Exception {

		List<CommerceAccountUserRel> commerceAccountUserRels =
			_commerceAccountUserRelService.getCommerceAccountUserRels(
				id, pagination.getStartPosition(), pagination.getEndPosition());

		int totalItems =
			_commerceAccountUserRelService.getCommerceAccountUserRelsCount(id);

		return Page.of(
			_toAccountMembers(commerceAccountUserRels), pagination, totalItems);
	}

	@Override
	public Response patchAccountByExternalReferenceCodeAccountMember(
			String externalReferenceCode, Long userId,
			AccountMember accountMember)
		throws Exception {

		CommerceAccount commerceAccount =
			_commerceAccountService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceAccount == null) {
			throw new NoSuchAccountException(
				"Unable to find Account with externalReferenceCode: " +
					externalReferenceCode);
		}

		_updateCommerceAccountUserRel(
			commerceAccount,
			AccountMemberUtil.getUser(
				_userLocalService, accountMember,
				contextCompany.getCompanyId()),
			accountMember);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Response patchAccountIdAccountMember(
			Long id, Long userId, AccountMember accountMember)
		throws Exception {

		_updateCommerceAccountUserRel(
			_commerceAccountService.getCommerceAccount(id),
			AccountMemberUtil.getUser(
				_userLocalService, accountMember,
				contextCompany.getCompanyId()),
			accountMember);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
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

		CommerceAccountUserRel commerceAccountUserRel =
			AccountMemberUtil.addCommerceAccountUserRel(
				_commerceAccountUserRelService, accountMember, commerceAccount,
				AccountMemberUtil.getUser(
					_userLocalService, accountMember,
					contextCompany.getCompanyId()),
				_serviceContextHelper.getServiceContext());

		DTOConverter accountMemberDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceAccountUserRel.class.getName());

		return (AccountMember)accountMemberDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				commerceAccountUserRel.getPrimaryKey()));
	}

	@Override
	public AccountMember postAccountIdAccountMember(
			Long id, AccountMember accountMember)
		throws Exception {

		CommerceAccountUserRel commerceAccountUserRel =
			AccountMemberUtil.addCommerceAccountUserRel(
				_commerceAccountUserRelService, accountMember,
				_commerceAccountService.getCommerceAccount(id),
				AccountMemberUtil.getUser(
					_userLocalService, accountMember,
					contextCompany.getCompanyId()),
				_serviceContextHelper.getServiceContext());

		DTOConverter accountMemberDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceAccountUserRel.class.getName());

		return (AccountMember)accountMemberDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				commerceAccountUserRel.getPrimaryKey()));
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

	private void _updateCommerceAccountUserRel(
			CommerceAccount commerceAccount, User user,
			AccountMember accountMember)
		throws PortalException {

		long[] roleIds = null;

		AccountRole[] roles = accountMember.getRoles();

		if (roles != null) {
			Stream<AccountRole> accountRoleStream = Arrays.stream(roles);

			roleIds = accountRoleStream.mapToLong(
				AccountRole::getRoleId
			).toArray();
		}

		_userGroupRoleLocalService.deleteUserGroupRoles(
			user.getUserId(),
			new long[] {commerceAccount.getCommerceAccountGroupId()});

		_userGroupRoleLocalService.addUserGroupRoles(
			user.getUserId(), commerceAccount.getCommerceAccountGroupId(),
			roleIds);
	}

	@Reference
	private CommerceAccountService _commerceAccountService;

	@Reference
	private CommerceAccountUserRelService _commerceAccountUserRelService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

	@Reference
	private UserGroupRoleLocalService _userGroupRoleLocalService;

	@Reference
	private UserLocalService _userLocalService;

}