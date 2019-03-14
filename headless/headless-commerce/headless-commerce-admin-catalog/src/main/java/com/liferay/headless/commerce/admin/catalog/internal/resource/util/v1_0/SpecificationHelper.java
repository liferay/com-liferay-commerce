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
import com.liferay.commerce.product.exception.NoSuchCPSpecificationOptionException;
import com.liferay.commerce.product.model.CPSpecificationOption;
import com.liferay.commerce.product.service.CPSpecificationOptionService;
import com.liferay.headless.commerce.admin.catalog.internal.mapper.v1_0.DTOMapper;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.OptionCategoryDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.SpecificationDTO;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = SpecificationHelper.class)
public class SpecificationHelper {

	public void deleteSpecification(Long id) throws PortalException {
		_cpSpecificationOptionService.deleteCPSpecificationOption(id);
	}

	public SpecificationDTO getSpecification(Long id) throws PortalException {
		return _dtoMapper.modelToDTO(
			_cpSpecificationOptionService.getCPSpecificationOption(id));
	}

	public CollectionDTO<SpecificationDTO> getSpecifications(
			long groupId, Pagination pagination)
		throws PortalException {

		List<CPSpecificationOption> cpSpecificationOptions =
			_cpSpecificationOptionService.getCPSpecificationOptions(
				groupId, pagination.getStartPosition(),
				pagination.getEndPosition(), null);

		int totalItems =
			_cpSpecificationOptionService.getCPSpecificationOptionsCount(
				groupId);

		Stream<CPSpecificationOption> stream = cpSpecificationOptions.stream();

		return stream.map(
			cpSpecificationOption -> _dtoMapper.modelToDTO(
				cpSpecificationOption)
		).collect(
			Collectors.collectingAndThen(
				Collectors.toList(),
				specificationDTOs -> new CollectionDTO<>(
					specificationDTOs, totalItems))
		);
	}

	public CPSpecificationOption updateSpecification(
			Long id, SpecificationDTO specificationDTO)
		throws PortalException {

		CPSpecificationOption cpSpecificationOption =
			_cpSpecificationOptionService.getCPSpecificationOption(id);

		return _cpSpecificationOptionService.updateCPSpecificationOption(
			cpSpecificationOption.getCPSpecificationOptionId(),
			_getCPOptionCategoryId(specificationDTO),
			LanguageUtils.getLocalizedMap(specificationDTO.getTitle()),
			LanguageUtils.getLocalizedMap(specificationDTO.getDescription()),
			_isFacetable(specificationDTO), specificationDTO.getKey(),
			_serviceContextHelper.getServiceContext(
				cpSpecificationOption.getGroupId()));
	}

	public SpecificationDTO upsertSpecification(
			long groupId, SpecificationDTO specificationDTO)
		throws PortalException {

		try {
			CPSpecificationOption cpSpecificationOption = updateSpecification(
				specificationDTO.getId(), specificationDTO);

			return _dtoMapper.modelToDTO(cpSpecificationOption);
		}
		catch (NoSuchCPSpecificationOptionException nscpsoe) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to find specification with ID: " +
						specificationDTO.getId());
			}
		}

		CPSpecificationOption cpSpecificationOption =
			_cpSpecificationOptionService.addCPSpecificationOption(
				_getCPOptionCategoryId(specificationDTO),
				LanguageUtils.getLocalizedMap(specificationDTO.getTitle()),
				LanguageUtils.getLocalizedMap(
					specificationDTO.getDescription()),
				_isFacetable(specificationDTO), specificationDTO.getKey(),
				_serviceContextHelper.getServiceContext(groupId));

		return _dtoMapper.modelToDTO(cpSpecificationOption);
	}

	private long _getCPOptionCategoryId(SpecificationDTO specificationDTO) {
		OptionCategoryDTO optionCategoryDTO =
			specificationDTO.getOptionCategoryDTO();

		if (optionCategoryDTO == null) {
			return 0;
		}

		return optionCategoryDTO.getId();
	}

	private boolean _isFacetable(SpecificationDTO specificationDTO) {
		boolean facetable = false;

		if (specificationDTO.isFacetable() != null) {
			facetable = specificationDTO.isFacetable();
		}

		return facetable;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SpecificationHelper.class);

	@Reference
	private CPSpecificationOptionService _cpSpecificationOptionService;

	@Reference
	private DTOMapper _dtoMapper;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}