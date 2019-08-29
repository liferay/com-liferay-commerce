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
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.service.CommerceAddressService;
import com.liferay.commerce.service.CommerceCountryService;
import com.liferay.commerce.service.CommerceRegionLocalService;
import com.liferay.headless.commerce.admin.account.dto.v1_0.AccountAddress;
import com.liferay.headless.commerce.admin.account.resource.v1_0.AccountAddressResource;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterRegistry;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
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
	properties = "OSGI-INF/liferay/rest/v1_0/account-address.properties",
	scope = ServiceScope.PROTOTYPE, service = AccountAddressResource.class
)
public class AccountAddressResourceImpl extends BaseAccountAddressResourceImpl {

	@Override
	public Page<AccountAddress>
			getAccountByExternalReferenceCodeAccountAddressesPage(
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

		return _getAccountAddressesPage(commerceAccount, pagination);
	}

	@Override
	public Page<AccountAddress> getAccountIdAccountAddressesPage(
			Long id, Pagination pagination)
		throws Exception {

		return _getAccountAddressesPage(
			_commerceAccountService.getCommerceAccount(id), pagination);
	}

	@Override
	public AccountAddress postAccountByExternalReferenceCodeAccountAddress(
			String externalReferenceCode, AccountAddress accountAddress)
		throws Exception {

		CommerceAccount commerceAccount =
			_commerceAccountService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceAccount == null) {
			throw new NoSuchAccountException(
				"Unable to find Account with externalReferenceCode: " +
					externalReferenceCode);
		}

		return _addAccountAddress(commerceAccount, accountAddress);
	}

	@Override
	public AccountAddress postAccountIdAccountAddress(
			Long id, AccountAddress accountAddress)
		throws Exception {

		return _addAccountAddress(
			_commerceAccountService.getCommerceAccount(id), accountAddress);
	}

	private AccountAddress _addAccountAddress(
			CommerceAccount commerceAccount, AccountAddress accountAddress)
		throws Exception {

		CommerceCountry commerceCountry =
			_commerceCountryService.getCommerceCountry(
				commerceAccount.getCompanyId(),
				accountAddress.getCountryISOCode());

		CommerceAddress commerceAddress =
			_commerceAddressService.addCommerceAddress(
				commerceAccount.getModelClassName(),
				commerceAccount.getCommerceAccountId(),
				accountAddress.getName(), accountAddress.getDescription(),
				accountAddress.getStreet1(), accountAddress.getStreet2(),
				accountAddress.getStreet3(), accountAddress.getCity(),
				accountAddress.getZip(),
				_getCommerceRegionId(commerceCountry, accountAddress),
				commerceCountry.getCommerceCountryId(),
				accountAddress.getPhoneNumber(),
				GetterUtil.get(accountAddress.getDefaultBilling(), false),
				GetterUtil.get(accountAddress.getDefaultShipping(), false),
				_serviceContextHelper.getServiceContext());

		DTOConverter accountAddressDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceAddress.class.getName());

		return (AccountAddress)accountAddressDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				commerceAddress.getCommerceAddressId()));
	}

	private Page<AccountAddress> _getAccountAddressesPage(
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

		return Page.of(
			_toAccountAddresses(commerceAddresses), pagination, totalItems);
	}

	private long _getCommerceRegionId(
			CommerceCountry commerceCountry, AccountAddress accountAddress)
		throws PortalException {

		if (Validator.isNull(accountAddress.getRegionISOCode()) ||
			(commerceCountry == null)) {

			return 0;
		}

		CommerceRegion commerceRegion =
			_commerceRegionLocalService.getCommerceRegion(
				commerceCountry.getCommerceCountryId(),
				accountAddress.getRegionISOCode());

		return commerceRegion.getCommerceRegionId();
	}

	private List<AccountAddress> _toAccountAddresses(
			List<CommerceAddress> commerceAddresses)
		throws Exception {

		List<AccountAddress> accountAddresses = new ArrayList<>();

		DTOConverter accountAddressDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceAddress.class.getName());

		for (CommerceAddress commerceAddress : commerceAddresses) {
			accountAddresses.add(
				(AccountAddress)accountAddressDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						contextAcceptLanguage.getPreferredLocale(),
						commerceAddress.getCommerceAddressId())));
		}

		return accountAddresses;
	}

	@Reference
	private CommerceAccountService _commerceAccountService;

	@Reference
	private CommerceAddressService _commerceAddressService;

	@Reference
	private CommerceCountryService _commerceCountryService;

	@Reference
	private CommerceRegionLocalService _commerceRegionLocalService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}
