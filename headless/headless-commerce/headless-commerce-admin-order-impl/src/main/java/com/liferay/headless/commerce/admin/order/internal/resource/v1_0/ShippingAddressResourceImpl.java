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

package com.liferay.headless.commerce.admin.order.internal.resource.v1_0;

import com.liferay.commerce.exception.NoSuchOrderException;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.service.CommerceAddressService;
import com.liferay.commerce.service.CommerceCountryService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.commerce.service.CommerceRegionLocalService;
import com.liferay.headless.commerce.admin.order.dto.v1_0.ShippingAddress;
import com.liferay.headless.commerce.admin.order.resource.v1_0.ShippingAddressResource;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterRegistry;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/shipping-address.properties",
	scope = ServiceScope.PROTOTYPE, service = ShippingAddressResource.class
)
public class ShippingAddressResourceImpl
	extends BaseShippingAddressResourceImpl {

	@Override
	public ShippingAddress getOrderByExternalReferenceCodeShippingAddress(
			String externalReferenceCode)
		throws Exception {

		CommerceOrder commerceOrder =
			_commerceOrderService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceOrder == null) {
			throw new NoSuchOrderException(
				"Unable to find Order with externalReferenceCode: " +
					externalReferenceCode);
		}

		CommerceAddress commerceAddress =
			_commerceAddressService.getCommerceAddress(
				commerceOrder.getShippingAddressId());

		DTOConverter shippingAddressDTOConverter =
			_dtoConverterRegistry.getDTOConverter("ShippingAddress");

		return (ShippingAddress)shippingAddressDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				commerceAddress.getCommerceAddressId()));
	}

	@Override
	public ShippingAddress getOrderIdShippingAddress(Long id) throws Exception {
		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			id);

		CommerceAddress commerceAddress =
			_commerceAddressService.getCommerceAddress(
				commerceOrder.getShippingAddressId());

		DTOConverter shippingAddressDTOConverter =
			_dtoConverterRegistry.getDTOConverter("ShippingAddress");

		return (ShippingAddress)shippingAddressDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				commerceAddress.getCommerceAddressId()));
	}

	@Override
	public Response patchOrderByExternalReferenceCodeShippingAddress(
			String externalReferenceCode, ShippingAddress shippingAddress)
		throws Exception {

		CommerceOrder commerceOrder =
			_commerceOrderService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceOrder == null) {
			throw new NoSuchOrderException(
				"Unable to find Order with externalReferenceCode: " +
					externalReferenceCode);
		}

		_updateShippingAddress(commerceOrder, shippingAddress);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Response patchOrderIdShippingAddress(
			Long id, ShippingAddress shippingAddress)
		throws Exception {

		_updateShippingAddress(
			_commerceOrderService.getCommerceOrder(id), shippingAddress);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	private long _getCommerceRegionId(
			CommerceAddress commerceAddress, CommerceCountry commerceCountry,
			ShippingAddress shippingAddress)
		throws PortalException {

		if (Validator.isNull(shippingAddress.getRegionISOCode())) {
			return commerceAddress.getCommerceRegionId();
		}

		CommerceRegion commerceRegion =
			_commerceRegionLocalService.getCommerceRegion(
				commerceCountry.getCommerceCountryId(),
				shippingAddress.getRegionISOCode());

		return commerceRegion.getCommerceCountryId();
	}

	private CommerceOrder _updateShippingAddress(
			CommerceOrder commerceOrder, ShippingAddress shippingAddress)
		throws Exception {

		CommerceAddress commerceAddress =
			_commerceAddressService.getCommerceAddress(
				commerceOrder.getShippingAddressId());

		CommerceCountry commerceCountry =
			_commerceCountryService.getCommerceCountry(
				contextCompany.getCompanyId(),
				shippingAddress.getCountryISOCode());

		return _commerceOrderService.updateBillingAddress(
			commerceOrder.getCommerceOrderId(), shippingAddress.getName(),
			GetterUtil.get(
				shippingAddress.getDescription(),
				commerceAddress.getDescription()),
			shippingAddress.getStreet1(),
			GetterUtil.get(
				shippingAddress.getStreet2(), commerceAddress.getStreet2()),
			GetterUtil.get(
				shippingAddress.getStreet3(), commerceAddress.getStreet3()),
			shippingAddress.getCity(),
			GetterUtil.get(shippingAddress.getZip(), commerceAddress.getZip()),
			_getCommerceRegionId(
				commerceAddress, commerceCountry, shippingAddress),
			commerceCountry.getCommerceCountryId(),
			GetterUtil.get(
				shippingAddress.getPhoneNumber(),
				commerceAddress.getPhoneNumber()),
			_serviceContextHelper.getServiceContext(
				commerceOrder.getGroupId()));
	}

	@Reference
	private CommerceAddressService _commerceAddressService;

	@Reference
	private CommerceCountryService _commerceCountryService;

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference
	private CommerceRegionLocalService _commerceRegionLocalService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}