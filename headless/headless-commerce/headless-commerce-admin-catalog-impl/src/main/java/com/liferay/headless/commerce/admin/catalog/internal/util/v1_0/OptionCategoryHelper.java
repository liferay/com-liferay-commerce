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

package com.liferay.headless.commerce.admin.catalog.internal.util.v1_0;

import com.liferay.commerce.product.exception.NoSuchCPOptionCategoryException;
import com.liferay.commerce.product.model.CPOptionCategory;
import com.liferay.commerce.product.service.CPOptionCategoryService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.OptionCategory;
import com.liferay.headless.commerce.admin.catalog.internal.mapper.v1_0.DTOMapper;
import com.liferay.headless.commerce.core.util.LanguageUtils;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = OptionCategoryHelper.class)
public class OptionCategoryHelper {

	public void deleteOptionCategory(Long id) throws PortalException {
		_cpOptionCategoryService.deleteCPOptionCategory(id);
	}

	public Page<OptionCategory> getOptionCategories(
			Long groupId, Pagination pagination)
		throws PortalException {

		List<CPOptionCategory> cpOptionCategories =
			_cpOptionCategoryService.getCPOptionCategories(
				groupId, pagination.getStartPosition(),
				pagination.getEndPosition());

		int totalItems = _cpOptionCategoryService.getCPOptionCategoriesCount(
			groupId);

		Stream<CPOptionCategory> stream = cpOptionCategories.stream();

		return stream.map(
			cpOptionCategory -> _dtoMapper.modelToDTO(cpOptionCategory)
		).collect(
			Collectors.collectingAndThen(
				Collectors.toList(),
				optionCategories -> Page.of(
					optionCategories, pagination, totalItems))
		);
	}

	public OptionCategory getOptionCategory(long id) throws PortalException {
		return _dtoMapper.modelToDTO(
			_cpOptionCategoryService.getCPOptionCategory(id));
	}

	public CPOptionCategory updateOptionCategory(
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

	public OptionCategory upsertOptionCategory(
			Long groupId, OptionCategory optionCategory)
		throws PortalException {

		try {
			CPOptionCategory cpOptionCategory = updateOptionCategory(
				optionCategory.getId(), optionCategory);

			return _dtoMapper.modelToDTO(cpOptionCategory);
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
				_serviceContextHelper.getServiceContext(groupId));

		return _dtoMapper.modelToDTO(cpOptionCategory);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		OptionCategoryHelper.class);

	@Reference
	private CPOptionCategoryService _cpOptionCategoryService;

	@Reference
	private DTOMapper _dtoMapper;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}