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

package com.liferay.commerce.openapi.admin.internal.resource.util.v2_0;

import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.account.exception.NoSuchAccountException;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountService;
import com.liferay.commerce.openapi.admin.internal.resource.util.ServiceContextHelper;
import com.liferay.commerce.openapi.admin.internal.util.v2_0.DTOUtils;
import com.liferay.commerce.openapi.admin.model.v2_0.AccountDTO;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.commerce.openapi.core.util.IdUtils;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;

import java.io.IOException;
import java.io.InputStream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.activation.DataHandler;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Zoltán Takács
 */
@Component(immediate = true, service = AccountHelper.class)
public class AccountHelper {

	public void deleteAccount(String id, Company company)
		throws PortalException {

		CommerceAccount commerceAccount = null;

		try {
			commerceAccount = getAccountById(id, company);
		}
		catch (NoSuchAccountException nsae) {
			if (_log.isDebugEnabled()) {
				_log.debug("Account does not exist with ID: " + id, nsae);
			}

			return;
		}

		_commerceAccountService.deleteCommerceAccount(
			commerceAccount.getCommerceAccountId());
	}

	public AccountDTO getAccount(String id, Company company)
		throws PortalException {

		return DTOUtils.modelToDTO(getAccountById(id, company));
	}

	public CommerceAccount getAccountById(String id, Company company)
		throws PortalException {

		CommerceAccount commerceAccount = null;

		if (IdUtils.isLocalPK(id)) {
			commerceAccount = _commerceAccountService.fetchCommerceAccount(
				GetterUtil.getLong(id));
		}
		else {

			// Get Account by External Reference Code

			String erc = IdUtils.getExternalReferenceCodeFromId(id);

			commerceAccount =
				_commerceAccountService.fetchByExternalReferenceCode(
					company.getCompanyId(), erc);
		}

		if (commerceAccount == null) {
			throw new NoSuchAccountException(
				"Unable to find Account with ID: " + id);
		}

		return commerceAccount;
	}

	public CollectionDTO<AccountDTO> getAccounts(
			User user, Pagination pagination)
		throws PortalException {

		List<CommerceAccount> commerceAccounts =
			_commerceAccountService.getUserCommerceAccounts(
				user.getUserId(),
				CommerceAccountConstants.DEFAULT_PARENT_ACCOUNT_ID,
				CommerceAccountConstants.SITE_TYPE_B2C_B2B, null,
				pagination.getStartPosition(), pagination.getEndPosition());

		int totalItems = _commerceAccountService.getUserCommerceAccountsCount(
			user.getUserId(),
			CommerceAccountConstants.DEFAULT_PARENT_ACCOUNT_ID,
			CommerceAccountConstants.SITE_TYPE_B2C_B2B, null);

		Stream<CommerceAccount> stream = commerceAccounts.stream();

		return stream.map(
			DTOUtils::modelToDTO
		).collect(
			Collectors.collectingAndThen(
				Collectors.toList(),
				accountDTOs ->
					new CollectionDTO<>(accountDTOs, totalItems))
		);
	}

	public AccountDTO updateAccount(
			String id, AccountDTO accountDTO, Company company)
		throws Exception {

		CommerceAccount commerceAccount = getAccountById(id, company);

		return DTOUtils.modelToDTO(
			_commerceAccountService.updateCommerceAccount(
				commerceAccount.getCommerceAccountId(), accountDTO.getName(),
				true, null, _getEmailAddress(accountDTO, commerceAccount),
				accountDTO.getTaxId(), true,
				_serviceContextHelper.getServiceContext()));
	}

	public void updateAccountLogo(String id, Attachment logo, Company company)
		throws IOException, PortalException {

		CommerceAccount account = getAccountById(id, company);

		DataHandler dataHandler = logo.getDataHandler();

		InputStream inputStream = dataHandler.getInputStream();

		byte[] byteArray = FileUtil.getBytes(inputStream);

		_commerceAccountService.updateCommerceAccount(
			account.getCommerceAccountId(), account.getName(), true, byteArray,
			account.getEmail(), account.getTaxId(), account.isActive(),
			_serviceContextHelper.getServiceContext());
	}

	public AccountDTO upsertAccount(AccountDTO accountDTO)
		throws PortalException {

		return DTOUtils.modelToDTO(
			_commerceAccountService.upsertCommerceAccount(
				accountDTO.getName(),
				CommerceAccountConstants.DEFAULT_PARENT_ACCOUNT_ID, true, null,
				_getEmailAddress(accountDTO, null), accountDTO.getTaxId(),
				CommerceAccountConstants.ACCOUNT_TYPE_PERSONAL, true,
				accountDTO.getExternalReferenceCode(),
				_serviceContextHelper.getServiceContext()));
	}

	private String _getEmailAddress(
		AccountDTO accountDTO, CommerceAccount commerceAccount) {

		String[] emailAddresses = accountDTO.getEmailAddresses();

		if ((emailAddresses.length == 0) && (commerceAccount == null)) {
			throw new ClientErrorException(
				"Email address should be specified in the request body",
				Response.Status.CONFLICT);
		}

		if (emailAddresses.length == 0) {
			return commerceAccount.getEmail();
		}

		return emailAddresses[0];
	}

	private static final Log _log = LogFactoryUtil.getLog(AccountHelper.class);

	@Reference
	private CommerceAccountService _commerceAccountService;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}