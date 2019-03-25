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
import com.liferay.headless.commerce.admin.site.setting.resource.v1_0.CatalogRuleResource;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import javax.validation.constraints.NotNull;

import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
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
		return super.deleteCatalogRule(id);
	}

	@Override
	public CatalogRule getCatalogRule(@NotNull Long id) throws Exception {
		return super.getCatalogRule(id);
	}

	@Override
	public Page<Category> getCatalogRuleCategories(
			@NotNull Long id, Pagination pagination)
		throws Exception {

		return super.getCatalogRuleCategories(id, pagination);
	}

	@Override
	public Page<CatalogRule> getCatalogRules(
			@NotNull Long groupId, Pagination pagination)
		throws Exception {

		return super.getCatalogRules(groupId, pagination);
	}

	@Override
	public Page<UserSegment> getCatalogRuleUserSegments(
			@NotNull Long id, Pagination pagination)
		throws Exception {

		return super.getCatalogRuleUserSegments(id, pagination);
	}

	@Override
	public Response updateCatalogRule(@NotNull Long id, CatalogRule catalogRule)
		throws Exception {

		return super.updateCatalogRule(id, catalogRule);
	}

	@Override
	public CatalogRule upsertCatalogRule(
			@NotNull Long groupId, CatalogRule catalogRule)
		throws Exception {

		return super.upsertCatalogRule(groupId, catalogRule);
	}

}