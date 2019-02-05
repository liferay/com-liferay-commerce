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

import com.liferay.commerce.openapi.admin.internal.resource.util.CurrencyHelper;
import com.liferay.commerce.openapi.admin.model.CurrencyDTO;
import com.liferay.commerce.openapi.admin.resource.CurrencyResource;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.oauth2.provider.scope.RequiresScope;
import com.liferay.portal.kernel.model.User;

import javax.annotation.Generated;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author Igor Beslic
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_SELECT + "=(osgi.jaxrs.name=CommerceOpenApiAdmin.Rest)",
		JaxrsWhiteboardConstants.JAX_RS_RESOURCE + "=true", "api.version=v1.0"
	},
	scope = ServiceScope.PROTOTYPE, service = CurrencyResource.class
)
@Generated(value = "OSGiRESTModuleGenerator")
public class CurrencyResourceImpl implements CurrencyResource {

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public Response deleteCurrency(String id) throws Exception {
		_currencyHelper.deleteCurrency(id);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.read")
	public CollectionDTO<CurrencyDTO> getCurrencies(
			Long groupId, Pagination pagination)
		throws Exception {

		return _currencyHelper.getCurrencyDTOs(groupId, pagination);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.read")
	public CurrencyDTO getCurrency(String id) throws Exception {
		return _currencyHelper.getCurrencyDTO(id);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public Response updateCurrency(
			Long groupId, String id, CurrencyDTO currencyDTO)
		throws Exception {

		_currencyHelper.updateCurrency(groupId, id, currencyDTO, _user);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public CurrencyDTO upsertCurrency(Long groupId, CurrencyDTO currencyDTO)
		throws Exception {

		return _currencyHelper.upsertCurrency(groupId, currencyDTO, _user);
	}

	@Reference
	private CurrencyHelper _currencyHelper;

	@Context
	private User _user;

}