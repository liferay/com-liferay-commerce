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

import com.liferay.commerce.openapi.admin.internal.resource.util.v2_0.UserHelper;
import com.liferay.commerce.openapi.admin.model.v2_0.UserDTO;
import com.liferay.commerce.openapi.admin.resource.v2_0.UserResource;
import com.liferay.commerce.openapi.core.annotation.Status;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.oauth2.provider.scope.RequiresScope;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.theme.ThemeDisplay;

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
		JaxrsWhiteboardConstants.JAX_RS_RESOURCE + "=true", "api.version=v2.0"
	},
	scope = ServiceScope.PROTOTYPE, service = UserResource.class
)
public class UserResourceImpl implements UserResource {

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public Response deleteUser(String id) throws Exception {
		_userHelper.deleteUser(_company.getCompanyId(), id);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.read")
	public UserDTO getUser(String id, ThemeDisplay themeDisplay)
		throws Exception {

		return _userHelper.getUserDTO(
			_company.getCompanyId(), id, _permissionChecker, themeDisplay);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.read")
	public CollectionDTO<UserDTO> getUsers(
			ThemeDisplay themeDisplay, Pagination pagination)
		throws Exception {

		return _userHelper.getUserDTOs(
			_company.getCompanyId(), pagination, _permissionChecker,
			themeDisplay);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public Response updateUser(String id, UserDTO userDTO) throws Exception {
		_userHelper.updateUser(
			_company.getCompanyId(), id, _permissionChecker, userDTO);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	@Status(Response.Status.CREATED)
	public UserDTO upsertUser(UserDTO userDTO, ThemeDisplay themeDisplay)
		throws Exception {

		return _userHelper.upsertUser(
			_company.getCompanyId(), _permissionChecker, userDTO, themeDisplay);
	}

	@Context
	private Company _company;

	@Context
	private PermissionChecker _permissionChecker;

	@Reference
	private UserHelper _userHelper;

}