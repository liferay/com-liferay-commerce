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

import com.liferay.commerce.account.model.CommerceAccountUserRel;
import com.liferay.commerce.account.service.CommerceAccountUserRelService;
import com.liferay.commerce.account.service.persistence.CommerceAccountUserRelPK;
import com.liferay.headless.commerce.admin.account.dto.v1_0.AccountMember;
import com.liferay.headless.commerce.admin.account.dto.v1_0.AccountRole;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterContext;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterRegistry;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroupRole;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = "model.class.name=com.liferay.commerce.account.model.CommerceAccountUserRel",
	service = {AccountMemberDTOConverter.class, DTOConverter.class}
)
public class AccountMemberDTOConverter implements DTOConverter {

	@Override
	public String getContentType() {
		return AccountMember.class.getSimpleName();
	}

	public AccountMember toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CommerceAccountUserRel commerceAccountUserRel =
			_commerceAccountUserRelService.getCommerceAccountUserRel(
				(CommerceAccountUserRelPK)
					dtoConverterContext.getCompositeResourcePrimKey());

		User user = commerceAccountUserRel.getUser();

		return new AccountMember() {
			{
				accountId = commerceAccountUserRel.getCommerceAccountId();
				name = user.getFullName();
				userId = user.getUserId();
				roles = _getRoles(commerceAccountUserRel, dtoConverterContext);
			}
		};
	}

	private AccountRole[] _getRoles(
			CommerceAccountUserRel commerceAccountUserRel,
			DTOConverterContext dtoConverterContext)
		throws Exception {

		List<AccountRole> accountRoles = new ArrayList<>();

		DTOConverter accountRoleDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				UserGroupRole.class.getName());

		for (UserGroupRole userGroupRole :
				commerceAccountUserRel.getUserGroupRoles()) {

			accountRoles.add(
				(AccountRole)accountRoleDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						dtoConverterContext.getLocale(),
						userGroupRole.getPrimaryKey())));
		}

		Stream<AccountRole> stream = accountRoles.stream();

		return stream.toArray(AccountRole[]::new);
	}

	@Reference
	private CommerceAccountUserRelService _commerceAccountUserRelService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

}