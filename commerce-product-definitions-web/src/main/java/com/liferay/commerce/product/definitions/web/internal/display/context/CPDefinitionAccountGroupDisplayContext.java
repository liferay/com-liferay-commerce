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

package com.liferay.commerce.product.definitions.web.internal.display.context;

import com.liferay.commerce.account.model.CommerceAccountGroup;
import com.liferay.commerce.account.model.CommerceAccountGroupRel;
import com.liferay.commerce.account.service.CommerceAccountGroupRelService;
import com.liferay.commerce.account.service.CommerceAccountGroupService;
import com.liferay.commerce.product.definitions.web.portlet.action.ActionHelper;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.service.CPDefinitionService;
import com.liferay.commerce.product.service.CommerceCatalogService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class CPDefinitionAccountGroupDisplayContext
	extends CPDefinitionsDisplayContext {

	public CPDefinitionAccountGroupDisplayContext(
		ActionHelper actionHelper, HttpServletRequest httpServletRequest,
		CommerceCatalogService commerceCatalogService,
		CPDefinitionService cpDefinitionService,
		CommerceAccountGroupRelService commerceAccountGroupRelService,
		CommerceAccountGroupService commerceAccountGroupService) {

		super(
			actionHelper, httpServletRequest, commerceCatalogService,
			cpDefinitionService);

		_commerceAccountGroupRelService = commerceAccountGroupRelService;
		_commerceAccountGroupService = commerceAccountGroupService;
	}

	public long[] getCommerceAccountGroupRelCommerceAccountGroupIds()
		throws PortalException {

		List<CommerceAccountGroupRel> commerceAccountGroupRels =
			_commerceAccountGroupRelService.getCommerceAccountGroupRels(
				CPDefinition.class.getName(), getCPDefinitionId(),
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		Stream<CommerceAccountGroupRel> stream =
			commerceAccountGroupRels.stream();

		return stream.mapToLong(
			CommerceAccountGroupRel::getCommerceAccountGroupId
		).toArray();
	}

	public List<CommerceAccountGroup> getCommerceAccountGroups()
		throws PortalException {

		return _commerceAccountGroupService.getCommerceAccountGroups(
			cpRequestHelper.getCompanyId(), QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	private final CommerceAccountGroupRelService
		_commerceAccountGroupRelService;
	private final CommerceAccountGroupService _commerceAccountGroupService;

}