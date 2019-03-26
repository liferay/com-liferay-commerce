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

import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.MeasurementUnit;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import javax.annotation.Generated;

/**
 * To access this resource, run:
 *
 *     curl -u your@email.com:yourpassword -D - http://localhost:8080/o/headless-commerce-admin-site-setting/v1.0
 *
 * @author Zoltán Takács
 * @generated
 */
@Generated("")
public interface MeasurementUnitResource {

	public boolean deleteMeasurementUnit(Long id) throws Exception;

	public MeasurementUnit getMeasurementUnit(Long id) throws Exception;

	public boolean updateMeasurementUnit(
			Long id, MeasurementUnit measurementUnit)
		throws Exception;

	public Page<MeasurementUnit> getMeasurementUnits(
			Long groupId, Integer type, Pagination pagination)
		throws Exception;

	public MeasurementUnit upsertMeasurementUnit(
			Long groupId, MeasurementUnit measurementUnit)
		throws Exception;

	public void setContextCompany(Company contextCompany);

}