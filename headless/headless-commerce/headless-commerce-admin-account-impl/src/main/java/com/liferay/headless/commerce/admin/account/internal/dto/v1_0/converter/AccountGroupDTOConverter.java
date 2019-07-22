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

import com.liferay.commerce.account.model.CommerceAccountGroup;
import com.liferay.commerce.account.service.CommerceAccountGroupService;
import com.liferay.headless.commerce.admin.account.dto.v1_0.AccountGroup;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = "model.class.name=com.liferay.commerce.account.model.CommerceAccountGroup",
	service = {AccountGroupDTOConverter.class, DTOConverter.class}
)
public class AccountGroupDTOConverter implements DTOConverter {

	@Override
	public String getContentType() {
		return AccountGroup.class.getSimpleName();
	}

	public AccountGroup toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CommerceAccountGroup commerceAccountGroup =
			_commerceAccountGroupService.getCommerceAccountGroup(
				dtoConverterContext.getResourcePrimKey());

		return new AccountGroup() {
			{
				id = commerceAccountGroup.getCommerceAccountGroupId();
				externalReferenceCode =
					commerceAccountGroup.getExternalReferenceCode();
				name = commerceAccountGroup.getName();
			}
		};
	}

	@Reference
	private CommerceAccountGroupService _commerceAccountGroupService;

}