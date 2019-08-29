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
import com.liferay.commerce.account.model.CommerceAccountOrganizationRel;
import com.liferay.commerce.account.model.CommerceAccountUserRel;
import com.liferay.commerce.account.service.CommerceAccountOrganizationRelService;
import com.liferay.commerce.account.service.CommerceAccountService;
import com.liferay.commerce.account.service.CommerceAccountUserRelService;
import com.liferay.commerce.account.service.persistence.CommerceAccountOrganizationRelPK;
import com.liferay.commerce.account.service.persistence.CommerceAccountUserRelPK;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.service.CommerceAddressService;
import com.liferay.commerce.service.CommerceCountryService;
import com.liferay.commerce.service.CommerceRegionLocalService;
import com.liferay.headless.commerce.admin.account.dto.v1_0.Account;
import com.liferay.headless.commerce.admin.account.dto.v1_0.AccountAddress;
import com.liferay.headless.commerce.admin.account.dto.v1_0.AccountMember;
import com.liferay.headless.commerce.admin.account.dto.v1_0.AccountOrganization;
import com.liferay.headless.commerce.admin.account.internal.util.v1_0.AccountMemberUtil;
import com.liferay.headless.commerce.admin.account.internal.util.v1_0.AccountOrganizationUtil;
import com.liferay.headless.commerce.admin.account.resource.v1_0.AccountResource;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterRegistry;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;
import com.liferay.headless.commerce.core.util.ExpandoUtil;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.vulcan.multipart.MultipartBody;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

		Response.ResponseBuilder responseBuilder = Response.ok();

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

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Account getAccount(Long id) throws Exception {
		DTOConverter accountDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceAccount.class.getName());

		return (Account)accountDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				GetterUtil.getLong(id)));
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

		Response.ResponseBuilder responseBuilder = Response.ok();

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

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Account postAccount(Account account) throws Exception {
		CommerceAccount commerceAccount =
			_commerceAccountService.upsertCommerceAccount(
				account.getName(),
				CommerceAccountConstants.DEFAULT_PARENT_ACCOUNT_ID, true, null,
				_getEmailAddress(account, null), account.getTaxId(),
				GetterUtil.get(
					account.getType(),
					CommerceAccountConstants.ACCOUNT_TYPE_PERSONAL),
				true, account.getExternalReferenceCode(),
				_serviceContextHelper.getServiceContext());

		// Expando

		Map<String, ?> customFields = account.getCustomFields();

		if ((customFields != null) && !customFields.isEmpty()) {
			ExpandoUtil.updateExpando(
				contextCompany.getCompanyId(), CommerceAccount.class,
				commerceAccount.getPrimaryKey(), customFields);
		}

		// Update nested resources

		_updateNestedResources(
			account, commerceAccount,
			_serviceContextHelper.getServiceContext());

		DTOConverter accountDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceAccount.class.getName());

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
			return "";
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

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			commerceAccount.getCommerceAccountGroupId());

		commerceAccount = _commerceAccountService.updateCommerceAccount(
			commerceAccount.getCommerceAccountId(), account.getName(), true,
			null, _getEmailAddress(account, commerceAccount),
			GetterUtil.get(account.getTaxId(), commerceAccount.getTaxId()),
			commerceAccount.isActive(), serviceContext);

		// Expando

		Map<String, ?> customFields = account.getCustomFields();

		if ((customFields != null) && !customFields.isEmpty()) {
			ExpandoUtil.updateExpando(
				serviceContext.getCompanyId(), CommerceAccount.class,
				commerceAccount.getPrimaryKey(), customFields);
		}

		// Update nested resources

		_updateNestedResources(account, commerceAccount, serviceContext);

		return commerceAccount;
	}

	private CommerceAccount _updateNestedResources(
			Account account, CommerceAccount commerceAccount,
			ServiceContext serviceContext)
		throws PortalException {

		// Account addresses

		AccountAddress[] accountAddresses = account.getAddresses();

		if (accountAddresses != null) {
			List<CommerceAddress> commerceAddresses =
				_commerceAddressService.getCommerceAddresses(
					commerceAccount.getModelClassName(),
					commerceAccount.getCommerceAccountId(), QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null);

			for (CommerceAddress commerceAddress : commerceAddresses) {
				_commerceAddressService.deleteCommerceAddress(
					commerceAddress.getCommerceAddressId());
			}

			for (AccountAddress accountAddress : accountAddresses) {
				CommerceCountry commerceCountry =
					_commerceCountryService.getCommerceCountry(
						commerceAccount.getCompanyId(),
						accountAddress.getCountryISOCode());

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
					serviceContext);
			}
		}

		// Account members

		AccountMember[] accountMembers = account.getUsers();

		if (accountMembers != null) {
			for (AccountMember accountMember : accountMembers) {
				User user = AccountMemberUtil.getUser(
					_userLocalService, accountMember,
					contextCompany.getCompanyId());

				CommerceAccountUserRel commerceAccountUserRel =
					_commerceAccountUserRelService.fetchCommerceAccountUserRel(
						new CommerceAccountUserRelPK(
							commerceAccount.getCommerceAccountId(),
							user.getUserId()));

				if (commerceAccountUserRel != null) {
					continue;
				}

				AccountMemberUtil.addCommerceAccountUserRel(
					_commerceAccountUserRelService, accountMember,
					commerceAccount, user, serviceContext);
			}
		}

		// Account organizations

		AccountOrganization[] accountOrganizations = account.getOrganizations();

		if (accountOrganizations != null) {
			for (AccountOrganization accountOrganization :
					accountOrganizations) {

				long organizationId = AccountOrganizationUtil.getOrganizationId(
					_organizationLocalService, accountOrganization,
					contextCompany.getCompanyId());

				CommerceAccountOrganizationRel commerceAccountOrganizationRel =
					_commerceAccountOrganizationRelService.
						fetchCommerceAccountOrganizationRel(
							new CommerceAccountOrganizationRelPK(
								commerceAccount.getCommerceAccountId(),
								organizationId));

				if (commerceAccountOrganizationRel != null) {
					continue;
				}

				_commerceAccountOrganizationRelService.
					addCommerceAccountOrganizationRel(
						commerceAccount.getCommerceAccountId(), organizationId,
						serviceContext);
			}
		}

		return commerceAccount;
	}

	@Reference
	private CommerceAccountOrganizationRelService
		_commerceAccountOrganizationRelService;

	@Reference
	private CommerceAccountService _commerceAccountService;

	@Reference
	private CommerceAccountUserRelService _commerceAccountUserRelService;

	@Reference
	private CommerceAddressService _commerceAddressService;

	@Reference
	private CommerceCountryService _commerceCountryService;

	@Reference
	private CommerceRegionLocalService _commerceRegionLocalService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private OrganizationLocalService _organizationLocalService;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

	@Context
	private User _user;

	@Reference
	private UserLocalService _userLocalService;

}