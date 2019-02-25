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
import com.liferay.commerce.openapi.core.context.Async;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.headless.commerce.admin.site.setting.internal.resource.util.v1_0.UserSegmentCriterionHelper;
import com.liferay.headless.commerce.admin.site.setting.internal.resource.util.v1_0.UserSegmentHelper;
import com.liferay.headless.commerce.admin.site.setting.model.v1_0.UserSegmentCriterionDTO;
import com.liferay.headless.commerce.admin.site.setting.model.v1_0.UserSegmentDTO;
import com.liferay.headless.commerce.admin.site.setting.resource.v1_0.UserSegmentResource;
import com.liferay.oauth2.provider.scope.RequiresScope;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.GetterUtil;

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
	scope = ServiceScope.PROTOTYPE, service = UserSegmentResource.class
)
@Generated(value = "OSGiRESTModuleGenerator")
public class UserSegmentResourceImpl implements UserSegmentResource {

	@Override
	@RequiresScope("HeadlessCommerceAdminSiteSetting.write")
	public Response deleteUserSegment(String id) throws Exception {
		_userSegmentHelper.deleteUserSegment(id);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	@RequiresScope("HeadlessCommerceAdminSiteSetting.write")
	public Response deleteUserSegmentCriterion(String id, String criterionId)
		throws Exception {

		_userSegmentCriterionHelper.deleteUserSegmentCriterion(criterionId);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	@RequiresScope("HeadlessCommerceAdminSiteSetting.read")
	public UserSegmentDTO getUserSegment(String id) throws Exception {
		return _userSegmentHelper.getUserSegmentDTO(id);
	}

	@Override
	@RequiresScope("HeadlessCommerceAdminSiteSetting.read")
	public CollectionDTO<UserSegmentCriterionDTO> getUserSegmentCriteria(
			String id, Pagination pagination)
		throws Exception {

		return _userSegmentCriterionHelper.getUserSegmentCriterionDTOs(
			GetterUtil.getLong(id), pagination);
	}

	@Override
	@RequiresScope("HeadlessCommerceAdminSiteSetting.read")
	public UserSegmentCriterionDTO getUserSegmentCriterion(
			String id, String criterionId)
		throws Exception {

		return _userSegmentCriterionHelper.getUserSegmentCriterionDTO(
			criterionId);
	}

	@Override
	@RequiresScope("HeadlessCommerceAdminSiteSetting.read")
	public CollectionDTO<UserSegmentDTO> getUserSegments(
			Long groupId, Pagination pagination)
		throws Exception {

		return _userSegmentHelper.getUserSegmentDTOs(groupId, pagination);
	}

	@AsyncSupported
	@Override
	@RequiresScope("HeadlessCommerceAdminSiteSetting.write")
	public Response updateUserSegment(String id, UserSegmentDTO userSegmentDTO)
		throws Exception {

		if (_async.isEnabled()) {
			new Thread() {

				public void run() {

					// TODO

				}

			}.start();

			return null;
		}

		_userSegmentHelper.updateUserSegment(id, userSegmentDTO, _user);

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.build();
	}

	@AsyncSupported
	@Override
	@RequiresScope("HeadlessCommerceAdminSiteSetting.write")
	public UserSegmentCriterionDTO updateUserSegmentCriterion(
			String id, String criterionId,
			UserSegmentCriterionDTO userSegmentCriterionDTO)
		throws Exception {

		if (_async.isEnabled()) {
			new Thread() {

				public void run() {

					// TODO

				}

			}.start();

			return null;
		}

		return _userSegmentCriterionHelper.updateUserSegmentCriterion(
			criterionId, userSegmentCriterionDTO, _user);
	}

	@AsyncSupported
	@Override
	@RequiresScope("HeadlessCommerceAdminSiteSetting.write")
	public UserSegmentDTO upsertUserSegment(
			Long groupId, UserSegmentDTO userSegmentDTO)
		throws Exception {

		if (_async.isEnabled()) {
			new Thread() {

				public void run() {

					// TODO

				}

			}.start();

			return null;
		}

		return _userSegmentHelper.upsertUserSegment(
			groupId, userSegmentDTO, _user);
	}

	@AsyncSupported
	@Override
	@RequiresScope("HeadlessCommerceAdminSiteSetting.write")
	public UserSegmentCriterionDTO upsertUserSegmentCriterion(
			String id, UserSegmentCriterionDTO userSegmentCriterionDTO)
		throws Exception {

		if (_async.isEnabled()) {
			new Thread() {

				public void run() {

					// TODO

				}

			}.start();

			return null;
		}

		return _userSegmentCriterionHelper.upsertUserSegmentCriterion(
			GetterUtil.getLong(id), userSegmentCriterionDTO, _user);
	}

	@Context
	private Async _async;

	@Context
	private User _user;

	@Reference
	private UserSegmentCriterionHelper _userSegmentCriterionHelper;

	@Reference
	private UserSegmentHelper _userSegmentHelper;

}