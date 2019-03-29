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

package com.liferay.headless.commerce.admin.catalog.internal.resource.v1_0;

import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Specification;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.SpecificationValue;
import com.liferay.headless.commerce.admin.catalog.internal.util.v1_0.SpecificationHelper;
import com.liferay.headless.commerce.admin.catalog.internal.util.v1_0.SpecificationValueHelper;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.SpecificationResource;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import javax.validation.constraints.NotNull;

import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Zoltán Takács
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/specification.properties",
	scope = ServiceScope.PROTOTYPE, service = SpecificationResource.class
)
public class SpecificationResourceImpl extends BaseSpecificationResourceImpl {

	@Override
	public Response deleteSpecification(@NotNull Long id) throws Exception {
		_specificationHelper.deleteSpecification(id);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public Specification getSpecification(@NotNull Long id) throws Exception {
		return _specificationHelper.getSpecification(id);
	}

	@Override
	public Page<Specification> getSpecifications(
			@NotNull Long groupId, Pagination pagination)
		throws Exception {

		return _specificationHelper.getSpecifications(groupId, pagination);
	}

	@Override
	public Page<SpecificationValue> getSpecificationValues(
			@NotNull Long id, Pagination pagination)
		throws Exception {

		return _specificationValueHelper.getSpecificationValues(
			id, contextAcceptLanguage, pagination);
	}

	@Override
	public Response updateSpecification(
			@NotNull Long id, Specification specification)
		throws Exception {

		_specificationHelper.updateSpecification(id, specification);

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.build();
	}

	@Override
	public Specification upsertSpecification(
			@NotNull Long groupId, Specification specification)
		throws Exception {

		return _specificationHelper.upsertSpecification(groupId, specification);
	}

	@Override
	public SpecificationValue upsertSpecificationValue(
			@NotNull Long id, SpecificationValue specificationValue)
		throws Exception {

		return _specificationValueHelper.upsertSpecificationValue(
			id, specificationValue, contextAcceptLanguage);
	}

	@Reference
	private SpecificationHelper _specificationHelper;

	@Reference
	private SpecificationValueHelper _specificationValueHelper;

}