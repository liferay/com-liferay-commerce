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

package com.liferay.commerce.openapi.admin.internal.resource;

import com.liferay.commerce.openapi.admin.model.AccountDTO;
import com.liferay.commerce.openapi.admin.model.CollectionDTO;
import com.liferay.commerce.openapi.admin.resource.AccountResource;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;

import java.util.Collections;
import java.util.Locale;

import javax.annotation.Generated;

import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author Igor Beslic
 */
@Component(
	immediate = true,
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_SELECT + "=(osgi.jaxrs.name=CommerceOpenApiAdmin.Rest)",
		JaxrsWhiteboardConstants.JAX_RS_RESOURCE + "=true", "api.version=1.0"
	},
	service = AccountResource.class
)
@Generated(value = "OSGiRESTModuleGenerator")
public class AccountResourceImpl implements AccountResource {

	@Override
	public Response deleteAccount(
		String id, long groupId, User user, Locale locale, Company company) {

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public AccountDTO getAccount(
		String id, long groupId, User user, Locale locale, Company company) {

		return new AccountDTO();
	}

	@Override
	public CollectionDTO<AccountDTO> getAccounts(
		long groupId, User user, Locale locale, Company company,
		Pagination pagination) {

		return new CollectionDTO(Collections.emptyList(), 0);
	}

	@Override
	public Response updateAccount(
		String id, long groupId, AccountDTO accountDTO, User user,
		Locale locale, Company company) {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public AccountDTO upsertAccount(
		long groupId, AccountDTO accountDTO, User user, Locale locale,
		Company company) {

		return new AccountDTO();
	}

}