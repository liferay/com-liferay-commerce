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

import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.CatalogRule;
import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.Category;
import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.UserSegment;
import com.liferay.headless.commerce.admin.site.setting.internal.util.v1_0.CatalogRuleHelper;
import com.liferay.headless.commerce.admin.site.setting.resource.v1_0.CatalogRuleResource;
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
	properties = "OSGI-INF/liferay/rest/v1_0/catalog-rule.properties",
	scope = ServiceScope.PROTOTYPE, service = CatalogRuleResource.class
)
public class CatalogRuleResourceImpl extends BaseCatalogRuleResourceImpl {

	@Override
	public Response deleteCatalogRule(@NotNull Long id) throws Exception {
		_catalogRuleHelper.deleteCatalogRule(id);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public CatalogRule getCatalogRule(@NotNull Long id) throws Exception {
		return _catalogRuleHelper.getCatalogRule(id);
	}

	@Override
	public Page<Category> getCatalogRuleCategories(
			@NotNull Long id, Pagination pagination)
		throws Exception {

		return _catalogRuleHelper.getCategories(id, pagination);
	}

	@Override
	public Page<CatalogRule> getCatalogRules(
			@NotNull Long groupId, Pagination pagination)
		throws Exception {

		return _catalogRuleHelper.getCatalogRules(groupId, pagination);
	}

	@Override
	public Page<UserSegment> getCatalogRuleUserSegments(
			@NotNull Long id, Pagination pagination)
		throws Exception {

		return _catalogRuleHelper.getUserSegments(id, pagination);
	}

	@Override
	public Response updateCatalogRule(@NotNull Long id, CatalogRule catalogRule)
		throws Exception {

		_catalogRuleHelper.updateCatalogRule(id, catalogRule, _user);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public CatalogRule upsertCatalogRule(
			@NotNull Long groupId, CatalogRule catalogRule)
		throws Exception {

		return _catalogRuleHelper.upsertCatalogRule(
			groupId, catalogRule, _user);
	}

	@Reference
	private CatalogRuleHelper _catalogRuleHelper;

	@Context
	private User _user;

}