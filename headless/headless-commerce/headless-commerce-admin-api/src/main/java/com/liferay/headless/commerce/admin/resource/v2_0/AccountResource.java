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

package com.liferay.headless.commerce.admin.resource.v2_0;

import com.liferay.headless.commerce.admin.dto.v2_0.Account;
import com.liferay.headless.commerce.admin.dto.v2_0.Address;
import com.liferay.headless.commerce.admin.dto.v2_0.Order;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.vulcan.multipart.MultipartBody;

import javax.annotation.Generated;

import javax.ws.rs.core.Response;

/**
 * To access this resource, run:
 *
 *     curl -u your@email.com:yourpassword -D - http://localhost:8080/o/headless-commerce-admin/v2.0
 *
 * @author Igor Beslic
 * @generated
 */
@Generated("")
public interface AccountResource {

	public Response upsertAccountLogo(String id, MultipartBody multipartBody)
		throws Exception;

	public Response deleteAccount(String id) throws Exception;

	public Response getAccount(String id) throws Exception;

	public Response updateMediaType1Account(String id, Account account)
		throws Exception;

	public Response updateMediaType2Account(String id, Account account)
		throws Exception;

	public Response getAddresses(String id) throws Exception;

	public Response addMediaType1Address(String id, Address address)
		throws Exception;

	public Response addMediaType2Address(String id, Address address)
		throws Exception;

	public Response getAccountOrders(String id, Long groupId) throws Exception;

	public Response upsertMediaType1Order(String id, Long groupId, Order order)
		throws Exception;

	public Response upsertMediaType2Order(String id, Long groupId, Order order)
		throws Exception;

	public Response getAccounts() throws Exception;

	public Response upsertMediaType1Account(Account account) throws Exception;

	public Response upsertMediaType2Account(Account account) throws Exception;

	public void setContextCompany(Company contextCompany);

}