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

package com.liferay.headless.commerce.admin.site.setting.internal.resource.v1_0;

import com.liferay.commerce.openapi.core.annotation.AsyncSupported;
import com.liferay.commerce.openapi.core.annotation.Status;
import com.liferay.commerce.openapi.core.context.Async;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.headless.commerce.admin.site.setting.internal.resource.util.v1_0.CatalogRuleHelper;
import com.liferay.headless.commerce.admin.site.setting.model.v1_0.CatalogRuleDTO;
import com.liferay.headless.commerce.admin.site.setting.model.v1_0.CategoryDTO;
import com.liferay.headless.commerce.admin.site.setting.model.v1_0.UserSegmentDTO;
import com.liferay.headless.commerce.admin.site.setting.resource.v1_0.CatalogRuleResource;
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
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_SELECT + "=(osgi.jaxrs.name=HeadlessCommerceAdminSiteSetting.Rest)",
		JaxrsWhiteboardConstants.JAX_RS_RESOURCE + "=true", "api.version=v1.0"
	},
	scope = ServiceScope.PROTOTYPE, service = CatalogRuleResource.class
)
@Generated(value = "OSGiRESTModuleGenerator")
public class CatalogRuleResourceImpl implements CatalogRuleResource {

	@Override
	@RequiresScope("HeadlessCommerceAdminSiteSetting.write")
	public Response deleteCatalogRule(String id) throws Exception {
		_catalogRuleHelper.deleteCatalogRule(id);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	@RequiresScope("HeadlessCommerceAdminSiteSetting.read")
	public CatalogRuleDTO getCatalogRule(String id) throws Exception {
		return _catalogRuleHelper.getCatalogRuleDTO(id);
	}

	@Override
	@RequiresScope("HeadlessCommerceAdminSiteSetting.read")
	public CollectionDTO<CategoryDTO> getCatalogRuleCategories(
			String id, Pagination pagination)
		throws Exception {

		return _catalogRuleHelper.getCategoryDTOs(id, pagination);
	}

	@Override
	@RequiresScope("HeadlessCommerceAdminSiteSetting.read")
	public CollectionDTO<CatalogRuleDTO> getCatalogRules(
			Long groupId, Pagination pagination)
		throws Exception {

		return _catalogRuleHelper.getCatalogRuleDTOs(groupId, pagination);
	}

	@Override
	@RequiresScope("HeadlessCommerceAdminSiteSetting.read")
	public CollectionDTO<UserSegmentDTO> getCatalogRuleUserSegments(
			String id, Pagination pagination)
		throws Exception {

		return _catalogRuleHelper.getUserSegmentDTOs(id, pagination);
	}

	@AsyncSupported
	@Override
	@RequiresScope("HeadlessCommerceAdminSiteSetting.write")
	public Response updateCatalogRule(String id, CatalogRuleDTO catalogRuleDTO)
		throws Exception {

		_catalogRuleHelper.updateCatalogRule(id, catalogRuleDTO, _user);

		if (_async.isEnabled()) {
			new Thread() {

				public void run() {

					// TODO

				}

			}.start();

			return null;
		}

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.build();
	}

	@AsyncSupported
	@Override
	@RequiresScope("HeadlessCommerceAdminSiteSetting.write")
	@Status(Response.Status.CREATED)
	public CatalogRuleDTO upsertCatalogRule(
			Long groupId, CatalogRuleDTO catalogRuleDTO)
		throws Exception {

		if (_async.isEnabled()) {
			new Thread() {

				public void run() {

					// TODO

				}

			}.start();

			return null;
		}

		return _catalogRuleHelper.upsertCatalogRule(
			groupId, catalogRuleDTO, _user);
	}

	@Context
	private Async _async;

	@Reference
	private CatalogRuleHelper _catalogRuleHelper;

	@Context
	private User _user;

}