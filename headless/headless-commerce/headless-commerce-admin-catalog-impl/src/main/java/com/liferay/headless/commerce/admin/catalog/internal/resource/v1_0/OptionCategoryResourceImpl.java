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

import com.liferay.headless.commerce.admin.catalog.dto.v1_0.OptionCategory;
import com.liferay.headless.commerce.admin.catalog.internal.util.v1_0.OptionCategoryHelper;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.OptionCategoryResource;
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
	properties = "OSGI-INF/liferay/rest/v1_0/option-category.properties",
	scope = ServiceScope.PROTOTYPE, service = OptionCategoryResource.class
)
public class OptionCategoryResourceImpl extends BaseOptionCategoryResourceImpl {

	@Override
	public Response deleteOptionCategory(@NotNull Long id) throws Exception {
		_optionCategoryHelper.deleteOptionCategory(id);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public Page<OptionCategory> getOptionCategories(
			@NotNull Long groupId, Pagination pagination)
		throws Exception {

		return _optionCategoryHelper.getOptionCategories(groupId, pagination);
	}

	@Override
	public OptionCategory getOptionCategory(@NotNull Long id) throws Exception {
		return _optionCategoryHelper.getOptionCategory(id);
	}

	@Override
	public Response updateOptionCategory(
			@NotNull Long id, OptionCategory optionCategory)
		throws Exception {

		_optionCategoryHelper.updateOptionCategory(id, optionCategory);

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.build();
	}

	@Override
	public OptionCategory upsertOptionCategory(
			@NotNull Long groupId, OptionCategory optionCategory)
		throws Exception {

		return _optionCategoryHelper.upsertOptionCategory(
			groupId, optionCategory);
	}

	@Reference
	private OptionCategoryHelper _optionCategoryHelper;

}