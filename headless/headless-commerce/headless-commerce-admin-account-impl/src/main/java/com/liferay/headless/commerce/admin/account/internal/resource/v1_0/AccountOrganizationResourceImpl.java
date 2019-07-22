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
import com.liferay.commerce.account.model.CommerceAccountOrganizationRel;
import com.liferay.commerce.account.service.CommerceAccountOrganizationRelService;
import com.liferay.commerce.account.service.CommerceAccountService;
import com.liferay.commerce.account.service.persistence.CommerceAccountOrganizationRelPK;
import com.liferay.headless.commerce.admin.account.dto.v1_0.AccountOrganization;
import com.liferay.headless.commerce.admin.account.internal.util.v1_0.AccountOrganizationUtil;
import com.liferay.headless.commerce.admin.account.resource.v1_0.AccountOrganizationResource;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterRegistry;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/account-organization.properties",
	scope = ServiceScope.PROTOTYPE, service = AccountOrganizationResource.class
)
public class AccountOrganizationResourceImpl
	extends BaseAccountOrganizationResourceImpl {

	@Override
	public Response deleteAccountByExternalReferenceCodeAccountOrganization(
			String externalReferenceCode, Long organizationId)
		throws Exception {

		CommerceAccount commerceAccount =
			_commerceAccountService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceAccount == null) {
			throw new NoSuchAccountException(
				"Unable to find Account with externalReferenceCode: " +
					externalReferenceCode);
		}

		_commerceAccountOrganizationRelService.
			deleteCommerceAccountOrganizationRel(
				commerceAccount.getCommerceAccountId(), organizationId);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Response deleteAccountIdAccountOrganization(
			Long id, Long organizationId)
		throws Exception {

		_commerceAccountOrganizationRelService.
			deleteCommerceAccountOrganizationRel(id, organizationId);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public AccountOrganization
			getAccountByExternalReferenceCodeAccountOrganization(
				String externalReferenceCode, Long organizationId)
		throws Exception {

		CommerceAccount commerceAccount =
			_commerceAccountService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceAccount == null) {
			throw new NoSuchAccountException(
				"Unable to find Account with externalReferenceCode: " +
					externalReferenceCode);
		}

		CommerceAccountOrganizationRel commerceAccountOrganizationRel =
			_commerceAccountOrganizationRelService.
				getCommerceAccountOrganizationRel(
					new CommerceAccountOrganizationRelPK(
						commerceAccount.getCommerceAccountId(),
						organizationId));

		DTOConverter accountOrganizationDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceAccountOrganizationRel.class.getName());

		return (AccountOrganization)accountOrganizationDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				commerceAccountOrganizationRel.getPrimaryKey()));
	}

	@Override
	public Page<AccountOrganization>
			getAccountByExternalReferenceCodeAccountOrganizationsPage(
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

		List<CommerceAccountOrganizationRel> commerceAccountOrganizationRels =
			_commerceAccountOrganizationRelService.
				getCommerceAccountOrganizationRels(
					commerceAccount.getCommerceAccountId(),
					pagination.getStartPosition(), pagination.getEndPosition());

		int totalItems =
			_commerceAccountOrganizationRelService.
				getCommerceAccountOrganizationRelsCount(
					commerceAccount.getCommerceAccountId());

		return Page.of(
			_toAccountOrganizations(commerceAccountOrganizationRels),
			pagination, totalItems);
	}

	@Override
	public AccountOrganization getAccountIdAccountOrganization(
			Long id, Long organizationId)
		throws Exception {

		DTOConverter accountOrganizationDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceAccountOrganizationRel.class.getName());

		return (AccountOrganization)accountOrganizationDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				new CommerceAccountOrganizationRelPK(id, organizationId)));
	}

	@Override
	public Page<AccountOrganization> getAccountIdAccountOrganizationsPage(
			Long id, Pagination pagination)
		throws Exception {

		List<CommerceAccountOrganizationRel> commerceAccountOrganizationRels =
			_commerceAccountOrganizationRelService.
				getCommerceAccountOrganizationRels(
					id, pagination.getStartPosition(),
					pagination.getEndPosition());

		int totalItems =
			_commerceAccountOrganizationRelService.
				getCommerceAccountOrganizationRelsCount(id);

		return Page.of(
			_toAccountOrganizations(commerceAccountOrganizationRels),
			pagination, totalItems);
	}

	@Override
	public AccountOrganization
			postAccountByExternalReferenceCodeAccountOrganization(
				String externalReferenceCode,
				AccountOrganization accountOrganization)
		throws Exception {

		CommerceAccount commerceAccount =
			_commerceAccountService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceAccount == null) {
			throw new NoSuchAccountException(
				"Unable to find Account with externalReferenceCode: " +
					externalReferenceCode);
		}

		CommerceAccountOrganizationRel commerceAccountOrganizationRel =
			_commerceAccountOrganizationRelService.
				addCommerceAccountOrganizationRel(
					commerceAccount.getCommerceAccountId(),
					AccountOrganizationUtil.getOrganizationId(
						_organizationLocalService, accountOrganization,
						contextCompany.getCompanyId()),
					_serviceContextHelper.getServiceContext());

		DTOConverter accountOrganizationDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceAccountOrganizationRel.class.getName());

		return (AccountOrganization)accountOrganizationDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				commerceAccountOrganizationRel.getPrimaryKey()));
	}

	@Override
	public AccountOrganization postAccountIdAccountOrganization(
			Long id, AccountOrganization accountOrganization)
		throws Exception {

		CommerceAccountOrganizationRel commerceAccountOrganizationRel =
			_commerceAccountOrganizationRelService.
				addCommerceAccountOrganizationRel(
					id,
					AccountOrganizationUtil.getOrganizationId(
						_organizationLocalService, accountOrganization,
						contextCompany.getCompanyId()),
					_serviceContextHelper.getServiceContext());

		DTOConverter accountOrganizationDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceAccountOrganizationRel.class.getName());

		return (AccountOrganization)accountOrganizationDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				commerceAccountOrganizationRel.getPrimaryKey()));
	}

	private List<AccountOrganization> _toAccountOrganizations(
			List<CommerceAccountOrganizationRel>
				commerceAccountOrganizationRels)
		throws Exception {

		List<AccountOrganization> accountOrganizations = new ArrayList<>();

		DTOConverter accountOrganizationDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceAccountOrganizationRel.class.getName());

		for (CommerceAccountOrganizationRel commerceAccountOrganizationRel :
				commerceAccountOrganizationRels) {

			accountOrganizations.add(
				(AccountOrganization)accountOrganizationDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						contextAcceptLanguage.getPreferredLocale(),
						commerceAccountOrganizationRel.getPrimaryKey())));
		}

		return accountOrganizations;
	}

	@Reference
	private CommerceAccountOrganizationRelService
		_commerceAccountOrganizationRelService;

	@Reference
	private CommerceAccountService _commerceAccountService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private OrganizationLocalService _organizationLocalService;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}