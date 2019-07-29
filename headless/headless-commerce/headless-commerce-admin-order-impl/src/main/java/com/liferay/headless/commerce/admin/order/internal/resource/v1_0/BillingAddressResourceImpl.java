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
import com.liferay.headless.commerce.admin.order.dto.v1_0.BillingAddress;
import com.liferay.headless.commerce.admin.order.resource.v1_0.BillingAddressResource;
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
	properties = "OSGI-INF/liferay/rest/v1_0/billing-address.properties",
	scope = ServiceScope.PROTOTYPE, service = BillingAddressResource.class
)
public class BillingAddressResourceImpl extends BaseBillingAddressResourceImpl {

	@Override
	public BillingAddress getOrderByExternalReferenceCodeBillingAddress(
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
				commerceOrder.getBillingAddressId());

		DTOConverter billingAddressDTOConverter =
			_dtoConverterRegistry.getDTOConverter("BillingAddress");

		return (BillingAddress)billingAddressDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				commerceAddress.getCommerceAddressId()));
	}

	@Override
	public BillingAddress getOrderIdBillingAddress(Long id) throws Exception {
		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			id);

		CommerceAddress commerceAddress =
			_commerceAddressService.getCommerceAddress(
				commerceOrder.getBillingAddressId());

		DTOConverter billingAddressDTOConverter =
			_dtoConverterRegistry.getDTOConverter("BillingAddress");

		return (BillingAddress)billingAddressDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				commerceAddress.getCommerceAddressId()));
	}

	@Override
	public Response patchOrderByExternalReferenceCodeBillingAddress(
			String externalReferenceCode, BillingAddress billingAddress)
		throws Exception {

		CommerceOrder commerceOrder =
			_commerceOrderService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceOrder == null) {
			throw new NoSuchOrderException(
				"Unable to find Order with externalReferenceCode: " +
					externalReferenceCode);
		}

		_updateBillingAddress(commerceOrder, billingAddress);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Response patchOrderIdBillingAddress(
			Long id, BillingAddress billingAddress)
		throws Exception {

		_updateBillingAddress(
			_commerceOrderService.getCommerceOrder(id), billingAddress);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	private long _getCommerceRegionId(
			CommerceAddress commerceAddress, CommerceCountry commerceCountry,
			BillingAddress billingAddress)
		throws PortalException {

		if (Validator.isNull(billingAddress.getRegionISOCode())) {
			return commerceAddress.getCommerceRegionId();
		}

		CommerceRegion commerceRegion =
			_commerceRegionLocalService.getCommerceRegion(
				commerceCountry.getCommerceCountryId(),
				billingAddress.getRegionISOCode());

		return commerceRegion.getCommerceCountryId();
	}

	private CommerceOrder _updateBillingAddress(
			CommerceOrder commerceOrder, BillingAddress billingAddress)
		throws Exception {

		CommerceAddress commerceAddress =
			_commerceAddressService.getCommerceAddress(
				commerceOrder.getBillingAddressId());

		CommerceCountry commerceCountry = commerceAddress.getCommerceCountry();

		return _commerceOrderService.updateBillingAddress(
			commerceOrder.getCommerceOrderId(), billingAddress.getName(),
			GetterUtil.get(
				billingAddress.getDescription(),
				commerceAddress.getDescription()),
			billingAddress.getStreet1(),
			GetterUtil.get(
				billingAddress.getStreet2(), commerceAddress.getStreet2()),
			GetterUtil.get(
				billingAddress.getStreet3(), commerceAddress.getStreet3()),
			billingAddress.getCity(),
			GetterUtil.get(billingAddress.getZip(), commerceAddress.getZip()),
			_getCommerceRegionId(
				commerceAddress, commerceCountry, billingAddress),
			commerceCountry.getCommerceCountryId(),
			GetterUtil.get(
				billingAddress.getPhoneNumber(),
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