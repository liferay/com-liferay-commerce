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

package com.liferay.headless.commerce.admin.site.setting.resource.v1_0;

import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.UserSegment;
import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.UserSegmentCriterion;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import javax.annotation.Generated;

import javax.ws.rs.core.Response;

/**
 * To access this resource, run:
 *
 *     curl -u your@email.com:yourpassword -D - http://localhost:8080/o/headless-commerce-admin-site-setting/v1.0
 *
 * @author Zoltán Takács
 * @generated
 */
@Generated("")
public interface UserSegmentResource {

	public Page<UserSegment> getUserSegments(
			Long groupId, Pagination pagination)
		throws Exception;

	public UserSegment upsertUserSegment(Long groupId, UserSegment userSegment)
		throws Exception;

	public Response deleteUserSegmentCriterion(Long criterionId, Long id)
		throws Exception;

	public UserSegmentCriterion getUserSegmentCriterion(
			Long criterionId, Long id)
		throws Exception;

	public UserSegmentCriterion updateUserSegmentCriterion(
			Long criterionId, Long id,
			UserSegmentCriterion userSegmentCriterion)
		throws Exception;

	public Page<UserSegmentCriterion> getUserSegmentCriteria(
			Long id, Pagination pagination)
		throws Exception;

	public UserSegmentCriterion upsertUserSegmentCriterion(
			Long id, UserSegmentCriterion userSegmentCriterion)
		throws Exception;

	public Response deleteUserSegment(Long id) throws Exception;

	public UserSegment getUserSegment(Long id) throws Exception;

	public Response updateUserSegment(Long id, UserSegment userSegment)
		throws Exception;

	public void setContextCompany(Company contextCompany);

}