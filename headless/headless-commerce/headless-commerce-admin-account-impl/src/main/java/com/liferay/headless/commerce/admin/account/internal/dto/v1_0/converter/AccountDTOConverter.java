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

package com.liferay.headless.commerce.admin.account.internal.dto.v1_0.converter;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.model.CommerceAccountOrganizationRel;
import com.liferay.commerce.account.model.CommerceAccountUserRel;
import com.liferay.commerce.account.service.CommerceAccountService;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.service.CommerceAddressService;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.headless.commerce.admin.account.dto.v1_0.Account;
import com.liferay.headless.commerce.admin.account.dto.v1_0.AccountAddress;
import com.liferay.headless.commerce.admin.account.dto.v1_0.AccountMember;
import com.liferay.headless.commerce.admin.account.dto.v1_0.AccountOrganization;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterContext;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterRegistry;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;
import com.liferay.portal.kernel.dao.orm.QueryUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = "model.class.name=com.liferay.commerce.account.model.CommerceAccount",
	service = {AccountDTOConverter.class, DTOConverter.class}
)
public class AccountDTOConverter implements DTOConverter {

	@Override
	public String getContentType() {
		return Account.class.getSimpleName();
	}

	public Account toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CommerceAccount commerceAccount =
			_commerceAccountService.getCommerceAccount(
				dtoConverterContext.getResourcePrimKey());

		ExpandoBridge expandoBridge = commerceAccount.getExpandoBridge();

		return new Account() {
			{
				addresses = _getAddresses(commerceAccount, dtoConverterContext);
				customFields = expandoBridge.getAttributes();
				emailAddresses = new String[] {commerceAccount.getEmail()};
				externalReferenceCode =
					commerceAccount.getExternalReferenceCode();
				id = commerceAccount.getCommerceAccountId();
				logoId = commerceAccount.getLogoId();
				name = commerceAccount.getName();
				organizations = _getOrganizations(
					commerceAccount, dtoConverterContext);
				root = commerceAccount.isRoot();
				taxId = commerceAccount.getTaxId();
				type = commerceAccount.getType();
				users = _getUsers(commerceAccount, dtoConverterContext);
			}
		};
	}

	private AccountAddress[] _getAddresses(
			CommerceAccount commerceAccount,
			DTOConverterContext dtoConverterContext)
		throws Exception {

		List<AccountAddress> accountAddresses = new ArrayList<>();

		DTOConverter accountAddressDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceAddress.class.getName());

		List<CommerceAddress> commerceAddresses =
			_commerceAddressService.getCommerceAddresses(
				commerceAccount.getModelClassName(),
				commerceAccount.getCommerceAccountId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		for (CommerceAddress commerceAddress : commerceAddresses) {
			accountAddresses.add(
				(AccountAddress)accountAddressDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						dtoConverterContext.getLocale(),
						commerceAddress.getCommerceAddressId())));
		}

		Stream<AccountAddress> stream = accountAddresses.stream();

		return stream.toArray(AccountAddress[]::new);
	}

	private AccountOrganization[] _getOrganizations(
			CommerceAccount commerceAccount,
			DTOConverterContext dtoConverterContext)
		throws Exception {

		List<AccountOrganization> accountOrganizations = new ArrayList<>();

		DTOConverter accountOrganizationDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceAccountOrganizationRel.class.getName());

		for (CommerceAccountOrganizationRel commerceAccountOrganizationRel :
				commerceAccount.getCommerceAccountOrganizationRels()) {

			accountOrganizations.add(
				(AccountOrganization)accountOrganizationDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						dtoConverterContext.getLocale(),
						commerceAccountOrganizationRel.getPrimaryKey())));
		}

		Stream<AccountOrganization> stream = accountOrganizations.stream();

		return stream.toArray(AccountOrganization[]::new);
	}

	private AccountMember[] _getUsers(
			CommerceAccount commerceAccount,
			DTOConverterContext dtoConverterContext)
		throws Exception {

		List<AccountMember> accountMembers = new ArrayList<>();

		DTOConverter accountMemberDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceAccountUserRel.class.getName());

		for (CommerceAccountUserRel commerceAccountUserRel :
				commerceAccount.getCommerceAccountUserRels()) {

			accountMembers.add(
				(AccountMember)accountMemberDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						dtoConverterContext.getLocale(),
						commerceAccountUserRel.getPrimaryKey())));
		}

		Stream<AccountMember> stream = accountMembers.stream();

		return stream.toArray(AccountMember[]::new);
	}

	@Reference
	private CommerceAccountService _commerceAccountService;

	@Reference
	private CommerceAddressService _commerceAddressService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

}