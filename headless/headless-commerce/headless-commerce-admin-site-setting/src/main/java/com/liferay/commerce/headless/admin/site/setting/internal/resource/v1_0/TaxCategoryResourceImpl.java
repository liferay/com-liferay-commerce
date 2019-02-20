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

package com.liferay.commerce.headless.admin.site.setting.internal.resource.v1_0;

import com.liferay.commerce.headless.admin.site.setting.internal.resource.util.v1_0.TaxCategoryHelper;
import com.liferay.commerce.headless.admin.site.setting.model.v1_0.TaxCategoryDTO;
import com.liferay.commerce.headless.admin.site.setting.resource.v1_0.TaxCategoryResource;
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
 * @author Alessio Antonio Rendina
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_SELECT + "=(osgi.jaxrs.name=CommerceHeadlessAdminSiteSetting.Rest)",
		JaxrsWhiteboardConstants.JAX_RS_RESOURCE + "=true", "api.version=v1.0"
	},
	scope = ServiceScope.PROTOTYPE, service = TaxCategoryResource.class
)
@Generated(value = "OSGiRESTModuleGenerator")
public class TaxCategoryResourceImpl implements TaxCategoryResource {

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public Response deleteTaxCategory(String id) throws Exception {
		_taxCategoryHelper.deleteTaxCategory(id);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.read")
	public CollectionDTO<TaxCategoryDTO> getTaxCategories(
			Long groupId, Pagination pagination)
		throws Exception {

		return _taxCategoryHelper.getTaxCategoryDTOs(groupId, pagination);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.read")
	public TaxCategoryDTO getTaxCategory(String id) throws Exception {
		return _taxCategoryHelper.getTaxCategoryDTO(id);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public Response updateTaxCategory(String id, TaxCategoryDTO taxCategoryDTO)
		throws Exception {

		_taxCategoryHelper.updateTaxCategory(id, taxCategoryDTO);

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.build();
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public TaxCategoryDTO upsertTaxCategory(
			Long groupId, TaxCategoryDTO taxCategoryDTO)
		throws Exception {

		return _taxCategoryHelper.upsertTaxCategory(
			groupId, taxCategoryDTO, _user);
	}

	@Reference
	private TaxCategoryHelper _taxCategoryHelper;

	@Context
	private User _user;

}