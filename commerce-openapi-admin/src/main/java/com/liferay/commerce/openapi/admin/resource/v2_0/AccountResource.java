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

package com.liferay.commerce.openapi.admin.resource.v2_0;

import com.liferay.commerce.openapi.admin.model.v2_0.AccountDTO;
import com.liferay.commerce.openapi.admin.model.v2_0.AddressDTO;
import com.liferay.commerce.openapi.admin.model.v2_0.OrderDTO;
import com.liferay.commerce.openapi.core.context.Language;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;

import javax.annotation.Generated;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;

/**
 * @author Igor Beslic
 */
@Generated(value = "OSGiRESTModuleGenerator")
@Path("/v2.0/account")
public interface AccountResource {

	@Consumes("application/*")
	@Path("/{id}/address")
	@POST
	@Produces("application/*")
	public AddressDTO addAddress(
			@PathParam("id") String id, AddressDTO addressDTO)
		throws Exception;

	@DELETE
	@Path("/{id}")
	public Response deleteAccount(@PathParam("id") String id) throws Exception;

	@GET
	@Path("/{id}")
	@Produces("application/*")
	public AccountDTO getAccount(@PathParam("id") String id) throws Exception;

	@GET
	@Path("/")
	@Produces("application/*")
	public CollectionDTO<AccountDTO> getAccounts(@Context Pagination pagination)
		throws Exception;

	@GET
	@Path("/{id}/address")
	@Produces("application/*")
	public CollectionDTO<AddressDTO> getAddresses(
			@PathParam("id") String id, @Context Pagination pagination)
		throws Exception;

	@GET
	@Path("/{id}/order")
	@Produces("application/*")
	public CollectionDTO<OrderDTO> getOrder(
			@PathParam("id") String id, @QueryParam("groupId") Long groupId,
			@Context Pagination pagination)
		throws Exception;

	@Consumes("application/*")
	@Path("/{id}")
	@PUT
	public Response updateAccount(
			@PathParam("id") String id, AccountDTO accountDTO)
		throws Exception;

	@Consumes("application/*")
	@Path("/")
	@POST
	@Produces("application/*")
	public AccountDTO upsertAccount(AccountDTO accountDTO) throws Exception;

	@Consumes("multipart/form-data")
	@Path("/{id}/logo/")
	@POST
	public Response upsertAccountLogo(
			@PathParam("id") String id, MultipartBody multipartBody)
		throws Exception;

	@Consumes("application/*")
	@Path("/{id}/order")
	@POST
	@Produces("application/*")
	public OrderDTO upsertSku(
			@PathParam("id") String id, @QueryParam("groupId") Long groupId,
			OrderDTO orderDTO, @Context Language language)
		throws Exception;

}