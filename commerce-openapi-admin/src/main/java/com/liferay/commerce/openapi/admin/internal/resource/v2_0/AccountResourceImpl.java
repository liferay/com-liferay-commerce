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

package com.liferay.commerce.openapi.admin.internal.resource.v2_0;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.openapi.admin.internal.resource.util.v2_0.AccountHelper;
import com.liferay.commerce.openapi.admin.internal.resource.util.v2_0.AddressHelper;
import com.liferay.commerce.openapi.admin.internal.resource.util.v2_0.OrderHelper;
import com.liferay.commerce.openapi.admin.model.v2_0.AccountDTO;
import com.liferay.commerce.openapi.admin.model.v2_0.AddressDTO;
import com.liferay.commerce.openapi.admin.model.v2_0.OrderDTO;
import com.liferay.commerce.openapi.admin.resource.v2_0.AccountResource;
import com.liferay.commerce.openapi.core.context.Language;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.oauth2.provider.scope.RequiresScope;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author Zoltán Takács
 * @author Alessio Antonio Rendina
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_SELECT + "=(osgi.jaxrs.name=CommerceOpenApiAdmin.Rest)",
		JaxrsWhiteboardConstants.JAX_RS_RESOURCE + "=true", "api.version=v2.0"
	},
	scope = ServiceScope.PROTOTYPE, service = AccountResource.class
)
public class AccountResourceImpl implements AccountResource {

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public AddressDTO addAddress(String id, AddressDTO addressDTO)
		throws Exception {

		CommerceAccount commerceAccount = _accountHelper.getAccountById(
			id, _company);

		return _addressHelper.addAddress(
			commerceAccount.getModelClassName(),
			commerceAccount.getCommerceAccountId(), addressDTO);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public Response deleteAccount(String id) throws Exception {
		_accountHelper.deleteAccount(id, _company);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.read")
	public AccountDTO getAccount(String id) throws Exception {
		return _accountHelper.getAccount(id, _company);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.read")
	public CollectionDTO<AccountDTO> getAccounts(Pagination pagination)
		throws Exception {

		return _accountHelper.getAccounts(_user, pagination);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.read")
	public CollectionDTO<AddressDTO> getAddresses(
			String id, Pagination pagination)
		throws Exception {

		CommerceAccount commerceAccount = _accountHelper.getAccountById(
			id, _company);

		return _addressHelper.getAddresses(
			commerceAccount.getModelClassName(),
			commerceAccount.getCommerceAccountId(), pagination);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.read")
	public CollectionDTO<OrderDTO> getOrders(
			String id, Long groupId, Language language, Pagination pagination)
		throws Exception {

		return _orderHelper.getOrders(
			id, groupId, language, pagination, _company);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public Response updateAccount(String id, AccountDTO accountDTO)
		throws Exception {

		_accountHelper.updateAccount(id, accountDTO, _company);

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.build();
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public AccountDTO upsertAccount(AccountDTO accountDTO) throws Exception {
		return _accountHelper.upsertAccount(accountDTO);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public Response upsertAccountLogo(String id, MultipartBody multipartBody)
		throws Exception {

		Attachment logo = multipartBody.getRootAttachment();

		_accountHelper.updateAccountLogo(id, logo, _company);

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.build();
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public OrderDTO upsertOrder(
			String id, Long groupId, OrderDTO orderDTO, Language language)
		throws Exception {

		return _orderHelper.upsertOrder(
			id, groupId, orderDTO, language, _company, _commerceContext);
	}

	@Reference
	private AccountHelper _accountHelper;

	@Reference
	private AddressHelper _addressHelper;

	@Context
	private CommerceContext _commerceContext;

	@Context
	private Company _company;

	@Reference
	private OrderHelper _orderHelper;

	@Context
	private User _user;

}