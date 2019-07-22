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

package com.liferay.headless.commerce.admin.order.internal.dto.v1_0.converter;

import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.service.CommerceAddressService;
import com.liferay.headless.commerce.admin.order.dto.v1_0.ShippingAddress;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterContext;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = "model.class.name=ShippingAddress",
	service = {DTOConverter.class, ShippingAddressDTOConverter.class}
)
public class ShippingAddressDTOConverter implements DTOConverter {

	@Override
	public String getContentType() {
		return ShippingAddress.class.getSimpleName();
	}

	public ShippingAddress toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CommerceAddress commerceAddress =
			_commerceAddressService.getCommerceAddress(
				dtoConverterContext.getResourcePrimKey());

		CommerceCountry commerceCountry = commerceAddress.getCommerceCountry();

		return new ShippingAddress() {
			{
				city = commerceAddress.getCity();
				countryISOCode = commerceCountry.getTwoLettersISOCode();
				defaultBilling = commerceAddress.isDefaultBilling();
				defaultShipping = commerceAddress.isDefaultShipping();
				description = commerceAddress.getDescription();
				id = commerceAddress.getCommerceAddressId();
				latitude = commerceAddress.getLatitude();
				longitude = commerceAddress.getLongitude();
				name = commerceAddress.getName();
				phoneNumber = commerceAddress.getPhoneNumber();
				regionISOCode = _getRegionISOCode(commerceAddress);
				street1 = commerceAddress.getStreet1();
				street2 = commerceAddress.getStreet2();
				street3 = commerceAddress.getStreet3();
				zip = commerceAddress.getZip();
			}
		};
	}

	private String _getRegionISOCode(CommerceAddress commerceAddress)
		throws PortalException {

		CommerceRegion commerceRegion = commerceAddress.getCommerceRegion();

		if (commerceRegion == null) {
			return StringPool.BLANK;
		}

		return commerceRegion.getCode();
	}

	@Reference
	private CommerceAddressService _commerceAddressService;

}