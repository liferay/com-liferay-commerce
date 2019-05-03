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

import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.service.CommerceAddressService;
import com.liferay.headless.commerce.admin.account.dto.v1_0.Address;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = "model.class.name=com.liferay.commerce.model.CommerceAddress",
	service = {AddressDTOConverter.class, DTOConverter.class}
)
public class AddressDTOConverter implements DTOConverter {

	@Override
	public String getContentType() {
		return Address.class.getSimpleName();
	}

	public Address toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CommerceAddress commerceAddress =
			_commerceAddressService.getCommerceAddress(
				dtoConverterContext.getResourcePrimKey());

		return new Address() {
			{
				city = commerceAddress.getCity();
				commerceCountryId = commerceAddress.getCommerceCountryId();
				commerceRegionId = commerceAddress.getCommerceRegionId();
				defaultBilling = commerceAddress.isDefaultBilling();
				defaultShipping = commerceAddress.isDefaultShipping();
				description = commerceAddress.getDescription();
				id = commerceAddress.getCommerceAddressId();
				latitude = commerceAddress.getLatitude();
				longitude = commerceAddress.getLongitude();
				name = commerceAddress.getName();
				phoneNumber = commerceAddress.getPhoneNumber();
				street1 = commerceAddress.getStreet1();
				street2 = commerceAddress.getStreet2();
				street3 = commerceAddress.getStreet3();
				zip = commerceAddress.getZip();
			}
		};
	}

	@Reference
	private CommerceAddressService _commerceAddressService;

}