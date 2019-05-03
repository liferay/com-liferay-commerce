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
import com.liferay.commerce.account.service.CommerceAccountService;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.service.CommerceAddressService;
import com.liferay.headless.commerce.admin.account.dto.v1_0.Address;
import com.liferay.headless.commerce.admin.account.resource.v1_0.AddressResource;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterRegistry;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/address.properties",
	scope = ServiceScope.PROTOTYPE, service = AddressResource.class
)
public class AddressResourceImpl extends BaseAddressResourceImpl {

	@Override
	public Page<Address> getAccountByExternalReferenceCodeAddressesPage(
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

		return _getAddressesPage(commerceAccount, pagination);
	}

	@Override
	public Page<Address> getAccountIdAddressesPage(
			Long id, Pagination pagination)
		throws Exception {

		return _getAddressesPage(
			_commerceAccountService.getCommerceAccount(id), pagination);
	}

	@Override
	public Address postAccountByExternalReferenceCodeAddress(
			String externalReferenceCode, Address address)
		throws Exception {

		CommerceAccount commerceAccount =
			_commerceAccountService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceAccount == null) {
			throw new NoSuchAccountException(
				"Unable to find Account with externalReferenceCode: " +
					externalReferenceCode);
		}

		return _addAddress(commerceAccount, address);
	}

	@Override
	public Address postAccountIdAddress(Long id, Address address)
		throws Exception {

		return _addAddress(
			_commerceAccountService.getCommerceAccount(id), address);
	}

	private Address _addAddress(
			CommerceAccount commerceAccount, Address address)
		throws Exception {

		CommerceAddress commerceAddress =
			_commerceAddressService.addCommerceAddress(
				commerceAccount.getModelClassName(),
				commerceAccount.getCommerceAccountId(), address.getName(),
				address.getDescription(), address.getStreet1(),
				address.getStreet2(), address.getStreet3(), address.getCity(),
				address.getZip(), address.getCommerceRegionId(),
				address.getCommerceCountryId(), address.getPhoneNumber(),
				GetterUtil.get(address.getDefaultBilling(), false),
				GetterUtil.get(address.getDefaultShipping(), false),
				_serviceContextHelper.getServiceContext());

		DTOConverter addressDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceAddress.class.getName());

		return (Address)addressDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				commerceAddress.getCommerceAddressId()));
	}

	private Page<Address> _getAddressesPage(
			CommerceAccount commerceAccount, Pagination pagination)
		throws Exception {

		List<CommerceAddress> commerceAddresses =
			_commerceAddressService.getCommerceAddresses(
				commerceAccount.getModelClassName(),
				commerceAccount.getCommerceAccountId(),
				pagination.getStartPosition(), pagination.getEndPosition(),
				null);

		int totalItems = _commerceAddressService.getCommerceAddressesCount(
			commerceAccount.getModelClassName(),
			commerceAccount.getCommerceAccountId());

		return Page.of(_toAddresses(commerceAddresses), pagination, totalItems);
	}

	private List<Address> _toAddresses(List<CommerceAddress> commerceAddresses)
		throws Exception {

		List<Address> addresses = new ArrayList<>();

		DTOConverter addressDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceAddress.class.getName());

		for (CommerceAddress commerceAddress : commerceAddresses) {
			addresses.add(
				(Address)addressDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						contextAcceptLanguage.getPreferredLocale(),
						commerceAddress.getCommerceAddressId())));
		}

		return addresses;
	}

	@Reference
	private CommerceAccountService _commerceAccountService;

	@Reference
	private CommerceAddressService _commerceAddressService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}