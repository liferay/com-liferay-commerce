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

import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.UserSegment;
import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.UserSegmentCriterion;
import com.liferay.headless.commerce.admin.site.setting.internal.util.v1_0.UserSegmentCriterionHelper;
import com.liferay.headless.commerce.admin.site.setting.internal.util.v1_0.UserSegmentHelper;
import com.liferay.headless.commerce.admin.site.setting.resource.v1_0.UserSegmentResource;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import javax.validation.constraints.NotNull;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Zoltán Takács
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/user-segment.properties",
	scope = ServiceScope.PROTOTYPE, service = UserSegmentResource.class
)
public class UserSegmentResourceImpl extends BaseUserSegmentResourceImpl {

	@Override
	public Response deleteUserSegment(@NotNull Long id) throws Exception {
		_userSegmentHelper.deleteUserSegment(id);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public Response deleteUserSegmentCriterion(
			@NotNull Long id, @NotNull Long criterionId)
		throws Exception {

		_userSegmentCriterionHelper.deleteUserSegmentCriterion(criterionId);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public UserSegment getUserSegment(@NotNull Long id) throws Exception {
		return _userSegmentHelper.getUserSegment(id);
	}

	@Override
	public Page<UserSegmentCriterion> getUserSegmentCriteria(
			@NotNull Long id, Pagination pagination)
		throws Exception {

		return _userSegmentCriterionHelper.getUserSegmentCriterions(
			id, pagination);
	}

	@Override
	public UserSegmentCriterion getUserSegmentCriterion(
			@NotNull Long id, @NotNull Long criterionId)
		throws Exception {

		return _userSegmentCriterionHelper.getUserSegmentCriterion(criterionId);
	}

	@Override
	public Page<UserSegment> getUserSegments(
			@NotNull Long groupId, Pagination pagination)
		throws Exception {

		return _userSegmentHelper.getUserSegments(groupId, pagination);
	}

	@Override
	public Response updateUserSegment(@NotNull Long id, UserSegment userSegment)
		throws Exception {

		_userSegmentHelper.updateUserSegment(id, userSegment, _user);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public UserSegmentCriterion updateUserSegmentCriterion(
			@NotNull Long id, @NotNull Long criterionId,
			UserSegmentCriterion userSegmentCriterion)
		throws Exception {

		return _userSegmentCriterionHelper.updateUserSegmentCriterion(
			criterionId, userSegmentCriterion, _user);
	}

	@Override
	public UserSegment upsertUserSegment(
			@NotNull Long groupId, UserSegment userSegment)
		throws Exception {

		return _userSegmentHelper.upsertUserSegment(
			groupId, userSegment, _user);
	}

	@Override
	public UserSegmentCriterion upsertUserSegmentCriterion(
			@NotNull Long id, UserSegmentCriterion userSegmentCriterion)
		throws Exception {

		return _userSegmentCriterionHelper.upsertUserSegmentCriterion(
			id, userSegmentCriterion, _user);
	}

	@Context
	private User _user;

	@Reference
	private UserSegmentCriterionHelper _userSegmentCriterionHelper;

	@Reference
	private UserSegmentHelper _userSegmentHelper;

}