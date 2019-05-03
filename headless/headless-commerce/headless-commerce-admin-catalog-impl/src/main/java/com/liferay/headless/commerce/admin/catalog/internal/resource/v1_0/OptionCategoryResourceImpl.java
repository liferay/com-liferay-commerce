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

import com.liferay.commerce.product.exception.NoSuchCPOptionCategoryException;
import com.liferay.commerce.product.model.CPOptionCategory;
import com.liferay.commerce.product.service.CPOptionCategoryService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.OptionCategory;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.OptionCategoryResource;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterRegistry;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;
import com.liferay.headless.commerce.core.util.LanguageUtils;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Zoltán Takács
 * @author Alessio Antonio Rendina
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/option-category.properties",
	scope = ServiceScope.PROTOTYPE, service = OptionCategoryResource.class
)
public class OptionCategoryResourceImpl extends BaseOptionCategoryResourceImpl {

	@Override
	public Response deleteOptionCategory(Long id) throws Exception {
		_cpOptionCategoryService.deleteCPOptionCategory(id);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public Page<OptionCategory> getCatalogSiteOptionCategoriesPage(
			Long siteId, Pagination pagination)
		throws Exception {

		List<CPOptionCategory> cpOptionCategories =
			_cpOptionCategoryService.getCPOptionCategories(
				siteId, pagination.getStartPosition(),
				pagination.getEndPosition());

		int totalItems = _cpOptionCategoryService.getCPOptionCategoriesCount(
			siteId);

		return Page.of(
			_toOptionCategories(cpOptionCategories), pagination, totalItems);
	}

	@Override
	public OptionCategory getOptionCategory(Long id) throws Exception {
		DTOConverter optionCategoryDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CPOptionCategory.class.getName());

		return (OptionCategory)optionCategoryDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(), id));
	}

	@Override
	public Response patchOptionCategory(Long id, OptionCategory optionCategory)
		throws Exception {

		_updateOptionCategory(id, optionCategory);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public OptionCategory postCatalogSiteOptionCategory(
			Long siteId, OptionCategory optionCategory)
		throws Exception {

		return _upsertOptionCategory(siteId, optionCategory);
	}

	private List<OptionCategory> _toOptionCategories(
			List<CPOptionCategory> cpOptionCategories)
		throws Exception {

		List<OptionCategory> optionCategories = new ArrayList<>();

		DTOConverter optionCategoryDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CPOptionCategory.class.getName());

		for (CPOptionCategory cpOptionCategory : cpOptionCategories) {
			optionCategories.add(
				(OptionCategory)optionCategoryDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						contextAcceptLanguage.getPreferredLocale(),
						cpOptionCategory.getCPOptionCategoryId())));
		}

		return optionCategories;
	}

	private CPOptionCategory _updateOptionCategory(
			Long id, OptionCategory optionCategory)
		throws PortalException {

		CPOptionCategory cpOptionCategory =
			_cpOptionCategoryService.getCPOptionCategory(id);

		return _cpOptionCategoryService.updateCPOptionCategory(
			cpOptionCategory.getCPOptionCategoryId(),
			LanguageUtils.getLocalizedMap(optionCategory.getTitle()),
			LanguageUtils.getLocalizedMap(optionCategory.getDescription()),
			GetterUtil.get(
				optionCategory.getPriority(), cpOptionCategory.getPriority()),
			optionCategory.getKey(),
			_serviceContextHelper.getServiceContext(
				cpOptionCategory.getGroupId()));
	}

	private OptionCategory _upsertOptionCategory(
			Long siteId, OptionCategory optionCategory)
		throws Exception {

		DTOConverter optionCategoryDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CPOptionCategory.class.getName());

		try {
			CPOptionCategory cpOptionCategory = _updateOptionCategory(
				optionCategory.getId(), optionCategory);

			return (OptionCategory)optionCategoryDTOConverter.toDTO(
				new DefaultDTOConverterContext(
					contextAcceptLanguage.getPreferredLocale(),
					cpOptionCategory.getCPOptionCategoryId()));
		}
		catch (NoSuchCPOptionCategoryException nscpoce) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to find optionCategory with ID: " +
						optionCategory.getId());
			}
		}

		CPOptionCategory cpOptionCategory =
			_cpOptionCategoryService.addCPOptionCategory(
				LanguageUtils.getLocalizedMap(optionCategory.getTitle()),
				LanguageUtils.getLocalizedMap(optionCategory.getDescription()),
				GetterUtil.get(optionCategory.getPriority(), 0D),
				optionCategory.getKey(),
				_serviceContextHelper.getServiceContext(siteId));

		return (OptionCategory)optionCategoryDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				cpOptionCategory.getCPOptionCategoryId()));
	}

	private static final Log _log = LogFactoryUtil.getLog(
		OptionCategoryResourceImpl.class);

	@Reference
	private CPOptionCategoryService _cpOptionCategoryService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}