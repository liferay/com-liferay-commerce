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

package com.liferay.headless.commerce.admin.catalog.internal.resource.util.v1_0;

import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.commerce.openapi.core.util.LanguageUtils;
import com.liferay.commerce.openapi.core.util.ServiceContextHelper;
import com.liferay.commerce.product.exception.NoSuchCPOptionCategoryException;
import com.liferay.commerce.product.model.CPOptionCategory;
import com.liferay.commerce.product.service.CPOptionCategoryService;
import com.liferay.headless.commerce.admin.catalog.internal.mapper.v1_0.DTOMapper;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.OptionCategoryDTO;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;

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

	public CollectionDTO<OptionCategoryDTO> getOptionCategories(
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
				optionCategoryDTOs -> new CollectionDTO<>(
					optionCategoryDTOs, totalItems))
		);
	}

	public OptionCategoryDTO getOptionCategory(long id) throws PortalException {
		return _dtoMapper.modelToDTO(
			_cpOptionCategoryService.getCPOptionCategory(id));
	}

	public CPOptionCategory updateOptionCategory(
			Long id, OptionCategoryDTO optionCategoryDTO)
		throws PortalException {

		CPOptionCategory cpOptionCategory =
			_cpOptionCategoryService.getCPOptionCategory(id);

		return _cpOptionCategoryService.updateCPOptionCategory(
			cpOptionCategory.getCPOptionCategoryId(),
			LanguageUtils.getLocalizedMap(optionCategoryDTO.getTitle()),
			LanguageUtils.getLocalizedMap(optionCategoryDTO.getDescription()),
			GetterUtil.get(
				optionCategoryDTO.getPriority(),
				cpOptionCategory.getPriority()),
			optionCategoryDTO.getKey(),
			_serviceContextHelper.getServiceContext(
				cpOptionCategory.getGroupId()));
	}

	public OptionCategoryDTO upsertOptionCategory(
			Long groupId, OptionCategoryDTO optionCategoryDTO)
		throws PortalException {

		try {
			CPOptionCategory cpOptionCategory = updateOptionCategory(
				optionCategoryDTO.getId(), optionCategoryDTO);

			return _dtoMapper.modelToDTO(cpOptionCategory);
		}
		catch (NoSuchCPOptionCategoryException nscpoce) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to find optionCategory with ID: " +
						optionCategoryDTO.getId());
			}
		}

		CPOptionCategory cpOptionCategory =
			_cpOptionCategoryService.addCPOptionCategory(
				LanguageUtils.getLocalizedMap(optionCategoryDTO.getTitle()),
				LanguageUtils.getLocalizedMap(
					optionCategoryDTO.getDescription()),
				GetterUtil.get(optionCategoryDTO.getPriority(), 0D),
				optionCategoryDTO.getKey(),
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