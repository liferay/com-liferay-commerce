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

import com.liferay.commerce.account.exception.NoSuchAccountGroupException;
import com.liferay.commerce.account.model.CommerceAccountGroup;
import com.liferay.commerce.account.service.CommerceAccountGroupService;
import com.liferay.headless.commerce.admin.account.dto.v1_0.AccountGroup;
import com.liferay.headless.commerce.admin.account.resource.v1_0.AccountGroupResource;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterRegistry;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
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
	properties = "OSGI-INF/liferay/rest/v1_0/account-group.properties",
	scope = ServiceScope.PROTOTYPE, service = AccountGroupResource.class
)
public class AccountGroupResourceImpl extends BaseAccountGroupResourceImpl {

	@Override
	public Response deleteAccountGroup(Long id) throws Exception {
		_commerceAccountGroupService.deleteCommerceAccountGroup(id);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Response deleteAccountGroupByExternalReferenceCode(
			String externalReferenceCode)
		throws Exception {

		CommerceAccountGroup commerceAccountGroup =
			_commerceAccountGroupService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceAccountGroup == null) {
			throw new NoSuchAccountGroupException(
				"Unable to find AccountGroup with externalReferenceCode: " +
					externalReferenceCode);
		}

		_commerceAccountGroupService.deleteCommerceAccountGroup(
			commerceAccountGroup.getCommerceAccountGroupId());

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public AccountGroup getAccountGroup(Long id) throws Exception {
		DTOConverter accountGroupDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceAccountGroup.class.getName());

		return (AccountGroup)accountGroupDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				GetterUtil.getLong(id)));
	}

	@Override
	public AccountGroup getAccountGroupByExternalReferenceCode(
			String externalReferenceCode)
		throws Exception {

		CommerceAccountGroup commerceAccountGroup =
			_commerceAccountGroupService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceAccountGroup == null) {
			throw new NoSuchAccountGroupException(
				"Unable to find AccountGroup with externalReferenceCode: " +
					externalReferenceCode);
		}

		DTOConverter accountGroupDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceAccountGroup.class.getName());

		return (AccountGroup)accountGroupDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				commerceAccountGroup.getCommerceAccountGroupId()));
	}

	@Override
	public Page<AccountGroup> getAccountGroupsPage(Pagination pagination)
		throws Exception {

		List<CommerceAccountGroup> commerceAccountGroups =
			_commerceAccountGroupService.getCommerceAccountGroups(
				contextCompany.getCompanyId(), pagination.getStartPosition(),
				pagination.getEndPosition(), null);

		int totalItems =
			_commerceAccountGroupService.getCommerceAccountGroupsCount(
				contextCompany.getCompanyId());

		return Page.of(
			_toAccountGroups(commerceAccountGroups), pagination, totalItems);
	}

	@Override
	public Response patchAccountGroup(Long id, AccountGroup accountGroup)
		throws Exception {

		_commerceAccountGroupService.updateCommerceAccountGroup(
			id, accountGroup.getName(),
			_serviceContextHelper.getServiceContext());

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Response patchAccountGroupByExternalReferenceCode(
			String externalReferenceCode, AccountGroup accountGroup)
		throws Exception {

		CommerceAccountGroup commerceAccountGroup =
			_commerceAccountGroupService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceAccountGroup == null) {
			throw new NoSuchAccountGroupException(
				"Unable to find AccountGroup with externalReferenceCode: " +
					externalReferenceCode);
		}

		_commerceAccountGroupService.updateCommerceAccountGroup(
			commerceAccountGroup.getCommerceAccountGroupId(),
			accountGroup.getName(), _serviceContextHelper.getServiceContext());

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public AccountGroup postAccountGroup(AccountGroup accountGroup)
		throws Exception {

		CommerceAccountGroup commerceAccountGroup = null;

		if (Validator.isNotNull(accountGroup.getExternalReferenceCode())) {
			commerceAccountGroup =
				_commerceAccountGroupService.fetchByExternalReferenceCode(
					contextCompany.getCompanyId(),
					accountGroup.getExternalReferenceCode());

			if (commerceAccountGroup == null) {
				throw new NoSuchAccountGroupException(
					"Unable to find AccountGroup with externalReferenceCode: " +
						accountGroup.getExternalReferenceCode());
			}
		}

		if (commerceAccountGroup == null) {
			commerceAccountGroup =
				_commerceAccountGroupService.addCommerceAccountGroup(
					contextCompany.getCompanyId(), accountGroup.getName(), 0,
					accountGroup.getExternalReferenceCode(),
					_serviceContextHelper.getServiceContext());
		}
		else {
			commerceAccountGroup =
				_commerceAccountGroupService.updateCommerceAccountGroup(
					commerceAccountGroup.getCommerceAccountGroupId(),
					accountGroup.getName(),
					_serviceContextHelper.getServiceContext());
		}

		DTOConverter accountGroupDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceAccountGroup.class.getName());

		return (AccountGroup)accountGroupDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				commerceAccountGroup.getCommerceAccountGroupId()));
	}

	private List<AccountGroup> _toAccountGroups(
			List<CommerceAccountGroup> commerceAccountGroups)
		throws Exception {

		List<AccountGroup> accountGroups = new ArrayList<>();

		DTOConverter accountGroupDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceAccountGroup.class.getName());

		for (CommerceAccountGroup commerceAccountGroup :
				commerceAccountGroups) {

			accountGroups.add(
				(AccountGroup)accountGroupDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						contextAcceptLanguage.getPreferredLocale(),
						commerceAccountGroup.getCommerceAccountGroupId())));
		}

		return accountGroups;
	}

	@Reference
	private CommerceAccountGroupService _commerceAccountGroupService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}