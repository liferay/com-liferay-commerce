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

import com.liferay.commerce.product.definitions.web.portlet.action.ActionHelper;
import com.liferay.commerce.product.model.CPTaxCategory;
import com.liferay.commerce.product.service.CPDefinitionService;
import com.liferay.commerce.product.service.CPTaxCategoryService;
import com.liferay.commerce.product.service.CommerceCatalogService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class CPDefinitionTaxCategoryDisplayContext
	extends CPDefinitionsDisplayContext {

	public CPDefinitionTaxCategoryDisplayContext(
		ActionHelper actionHelper, HttpServletRequest httpServletRequest,
		CommerceCatalogService commerceCatalogService,
		CPDefinitionService cpDefinitionService,
		CPTaxCategoryService cpTaxCategoryService) {

		super(
			actionHelper, httpServletRequest, commerceCatalogService,
			cpDefinitionService);

		_cpTaxCategoryService = cpTaxCategoryService;
	}

	public List<CPTaxCategory> getCPTaxCategories() throws PortalException {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		return _cpTaxCategoryService.getCPTaxCategories(
			themeDisplay.getCompanyId());
	}

	private final CPTaxCategoryService _cpTaxCategoryService;

}