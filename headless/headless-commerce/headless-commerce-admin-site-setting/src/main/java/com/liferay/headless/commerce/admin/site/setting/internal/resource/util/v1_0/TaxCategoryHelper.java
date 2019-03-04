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

package com.liferay.headless.commerce.admin.site.setting.internal.resource.util.v1_0;

import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.commerce.openapi.core.util.LanguageUtils;
import com.liferay.commerce.openapi.core.util.ServiceContextHelper;
import com.liferay.commerce.product.exception.NoSuchCPTaxCategoryException;
import com.liferay.commerce.product.model.CPTaxCategory;
import com.liferay.commerce.product.service.CPTaxCategoryService;
import com.liferay.headless.commerce.admin.site.setting.internal.mapper.v1_0.DTOMapper;
import com.liferay.headless.commerce.admin.site.setting.model.v1_0.TaxCategoryDTO;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = TaxCategoryHelper.class)
public class TaxCategoryHelper {

	public void deleteTaxCategory(String id) throws PortalException {
		_cpTaxCategoryService.deleteCPTaxCategory(GetterUtil.getLong(id));
	}

	public TaxCategoryDTO getTaxCategoryDTO(String id) throws PortalException {
		CPTaxCategory cpTaxCategory = _cpTaxCategoryService.getCPTaxCategory(
			GetterUtil.getLong(id));

		return _dtoMapper.modelToDTO(cpTaxCategory);
	}

	public CollectionDTO<TaxCategoryDTO> getTaxCategoryDTOs(
			Long groupId, Pagination pagination)
		throws PortalException {

		List<CPTaxCategory> cpTaxCategories =
			_cpTaxCategoryService.getCPTaxCategories(
				groupId, pagination.getStartPosition(),
				pagination.getEndPosition(), null);

		int count = _cpTaxCategoryService.getCPTaxCategoriesCount(groupId);

		List<TaxCategoryDTO> taxCategoryDTOs = new ArrayList<>();

		for (CPTaxCategory cpTaxCategory : cpTaxCategories) {
			taxCategoryDTOs.add(_dtoMapper.modelToDTO(cpTaxCategory));
		}

		return new CollectionDTO<>(taxCategoryDTOs, count);
	}

	public CPTaxCategory updateTaxCategory(
			String id, TaxCategoryDTO taxCategoryDTO)
		throws PortalException {

		CPTaxCategory cpTaxCategory = _cpTaxCategoryService.getCPTaxCategory(
			GetterUtil.getLong(id));

		return _cpTaxCategoryService.updateCPTaxCategory(
			cpTaxCategory.getCPTaxCategoryId(),
			LanguageUtils.getLocalizedMap(taxCategoryDTO.getName()),
			LanguageUtils.getLocalizedMap(taxCategoryDTO.getDescription()));
	}

	public TaxCategoryDTO upsertTaxCategory(
			Long groupId, TaxCategoryDTO taxCategoryDTO, User user)
		throws PortalException {

		try {
			CPTaxCategory cpTaxCategory = updateTaxCategory(
				String.valueOf(taxCategoryDTO.getId()), taxCategoryDTO);

			return _dtoMapper.modelToDTO(cpTaxCategory);
		}
		catch (NoSuchCPTaxCategoryException nscptce) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to find taxCategory with ID: " +
						taxCategoryDTO.getId());
			}
		}

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			groupId, new long[0], user, true);

		CPTaxCategory cpTaxCategory = _cpTaxCategoryService.addCPTaxCategory(
			LanguageUtils.getLocalizedMap(taxCategoryDTO.getName()),
			LanguageUtils.getLocalizedMap(taxCategoryDTO.getDescription()),
			serviceContext);

		return _dtoMapper.modelToDTO(cpTaxCategory);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		TaxCategoryHelper.class);

	@Reference
	private CPTaxCategoryService _cpTaxCategoryService;

	@Reference
	private DTOMapper _dtoMapper;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}