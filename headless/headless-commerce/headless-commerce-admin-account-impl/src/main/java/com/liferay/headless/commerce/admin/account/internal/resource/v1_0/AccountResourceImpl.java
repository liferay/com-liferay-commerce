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

import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.account.exception.NoSuchAccountException;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountService;
import com.liferay.headless.commerce.admin.account.dto.v1_0.Account;
import com.liferay.headless.commerce.admin.account.resource.v1_0.AccountResource;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterRegistry;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.vulcan.multipart.MultipartBody;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/account.properties",
	scope = ServiceScope.PROTOTYPE, service = AccountResource.class
)
public class AccountResourceImpl extends BaseAccountResourceImpl {

	@Override
	public Response deleteAccount(Long id) throws Exception {
		_commerceAccountService.deleteCommerceAccount(id);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public Response deleteAccountByExternalReferenceCode(
			String externalReferenceCode)
		throws Exception {

		CommerceAccount commerceAccount =
			_commerceAccountService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceAccount == null) {
			throw new NoSuchAccountException(
				"Unable to find Account with externalReferenceCode: " +
					externalReferenceCode);
		}

		_commerceAccountService.deleteCommerceAccount(
			commerceAccount.getCommerceAccountId());

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public Account getAccount(Long id) throws Exception {
		DTOConverter accountDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceAccount.class.getName());

		return (Account)accountDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(), id));
	}

	@Override
	public Account getAccountByExternalReferenceCode(
			String externalReferenceCode)
		throws Exception {

		CommerceAccount commerceAccount =
			_commerceAccountService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceAccount == null) {
			throw new NoSuchAccountException(
				"Unable to find Account with externalReferenceCode: " +
					externalReferenceCode);
		}

		DTOConverter accountDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceAccount.class.getName());

		return (Account)accountDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				commerceAccount.getCommerceAccountId()));
	}

	@Override
	public Page<Account> getAccountsPage(Pagination pagination)
		throws Exception {

		List<CommerceAccount> commerceAccounts =
			_commerceAccountService.getUserCommerceAccounts(
				_user.getUserId(),
				CommerceAccountConstants.DEFAULT_PARENT_ACCOUNT_ID,
				CommerceAccountConstants.SITE_TYPE_B2C_B2B, null,
				pagination.getStartPosition(), pagination.getEndPosition());

		int totalItems = _commerceAccountService.getUserCommerceAccountsCount(
			_user.getUserId(),
			CommerceAccountConstants.DEFAULT_PARENT_ACCOUNT_ID,
			CommerceAccountConstants.SITE_TYPE_B2C_B2B, null);

		return Page.of(_toAccounts(commerceAccounts), pagination, totalItems);
	}

	@Override
	public Response patchAccount(Long id, Account account) throws Exception {
		_updateAccount(id, account);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public Response patchAccountByExternalReferenceCode(
			String externalReferenceCode, Account account)
		throws Exception {

		CommerceAccount commerceAccount =
			_commerceAccountService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceAccount == null) {
			throw new NoSuchAccountException(
				"Unable to find Account with externalReferenceCode: " +
					externalReferenceCode);
		}

		_updateAccount(commerceAccount.getCommerceAccountId(), account);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public Account postAccount(Account account) throws Exception {
		DTOConverter accountDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceAccount.class.getName());

		CommerceAccount commerceAccount =
			_commerceAccountService.upsertCommerceAccount(
				account.getName(),
				CommerceAccountConstants.DEFAULT_PARENT_ACCOUNT_ID, true, null,
				_getEmailAddress(account, null), account.getTaxId(),
				CommerceAccountConstants.ACCOUNT_TYPE_PERSONAL, true,
				account.getExternalReferenceCode(),
				_serviceContextHelper.getServiceContext());

		return (Account)accountDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				commerceAccount.getCommerceAccountId()));
	}

	@Override
	public Response postAccountByExternalReferenceCodeLogo(
			String externalReferenceCode, MultipartBody multipartBody)
		throws Exception {

		CommerceAccount commerceAccount =
			_commerceAccountService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceAccount == null) {
			throw new NoSuchAccountException(
				"Unable to find Account with externalReferenceCode: " +
					externalReferenceCode);
		}

		updateAccountLogo(commerceAccount, multipartBody);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Response postAccountLogo(Long id, MultipartBody multipartBody)
		throws Exception {

		updateAccountLogo(
			_commerceAccountService.getCommerceAccount(id), multipartBody);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	public void updateAccountLogo(
			CommerceAccount commerceAccount, MultipartBody multipartBody)
		throws IOException, PortalException {

		_commerceAccountService.updateCommerceAccount(
			commerceAccount.getCommerceAccountId(), commerceAccount.getName(),
			true, multipartBody.getBinaryFileAsBytes("logo"),
			commerceAccount.getEmail(), commerceAccount.getTaxId(),
			commerceAccount.isActive(),
			_serviceContextHelper.getServiceContext(
				commerceAccount.getCommerceAccountGroupId()));
	}

	private String _getEmailAddress(
		Account account, CommerceAccount commerceAccount) {

		String[] emailAddresses = new String[0];

		if (account.getEmailAddresses() != null) {
			emailAddresses = account.getEmailAddresses();
		}

		if (emailAddresses.length > 0) {
			return emailAddresses[0];
		}

		if (commerceAccount == null) {
			throw new ClientErrorException(
				"Email address should be specified in the request body",
				Response.Status.CONFLICT);
		}

		return commerceAccount.getEmail();
	}

	private List<Account> _toAccounts(List<CommerceAccount> commerceAccounts)
		throws Exception {

		List<Account> accounts = new ArrayList<>();

		DTOConverter accountDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceAccount.class.getName());

		for (CommerceAccount commerceAccount : commerceAccounts) {
			accounts.add(
				(Account)accountDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						contextAcceptLanguage.getPreferredLocale(),
						commerceAccount.getCommerceAccountId())));
		}

		return accounts;
	}

	private CommerceAccount _updateAccount(Long id, Account account)
		throws PortalException {

		CommerceAccount commerceAccount =
			_commerceAccountService.getCommerceAccount(id);

		return _commerceAccountService.updateCommerceAccount(
			commerceAccount.getCommerceAccountId(), account.getName(), true,
			null, _getEmailAddress(account, commerceAccount),
			GetterUtil.get(account.getTaxId(), commerceAccount.getTaxId()),
			commerceAccount.isActive(),
			_serviceContextHelper.getServiceContext(
				commerceAccount.getCommerceAccountGroupId()));
	}

	@Reference
	private CommerceAccountService _commerceAccountService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

	@Context
	private User _user;

}