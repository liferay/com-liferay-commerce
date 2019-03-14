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

import com.liferay.commerce.openapi.core.context.Language;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.commerce.openapi.core.util.LanguageUtils;
import com.liferay.commerce.openapi.core.util.ServiceContextHelper;
import com.liferay.commerce.product.exception.NoSuchCPDefinitionSpecificationOptionValueException;
import com.liferay.commerce.product.model.CPDefinitionSpecificationOptionValue;
import com.liferay.commerce.product.model.CPSpecificationOption;
import com.liferay.commerce.product.service.CPDefinitionSpecificationOptionValueService;
import com.liferay.commerce.product.service.CPSpecificationOptionService;
import com.liferay.headless.commerce.admin.catalog.internal.mapper.v1_0.DTOMapper;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.OptionCategoryDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.ProductDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.SpecificationDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.SpecificationValueDTO;
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
@Component(immediate = true, service = SpecificationValueHelper.class)
public class SpecificationValueHelper {

	public void deleteSpecificationValue(Long id) throws PortalException {
		_cpDefinitionSpecificationOptionValueService.
			deleteCPDefinitionSpecificationOptionValue(id);
	}

	public SpecificationValueDTO getSpecificationValue(
			Long id, Language language)
		throws PortalException {

		return _dtoMapper.modelToDTO(
			_cpDefinitionSpecificationOptionValueService.
				getCPDefinitionSpecificationOptionValue(id),
			language.getLanguageId());
	}

	public CollectionDTO<SpecificationValueDTO> getSpecificationValues(
			Long specificationId, Language language, Pagination pagination)
		throws PortalException {

		SpecificationDTO specification = _specificationHelper.getSpecification(
			specificationId);

		List<CPDefinitionSpecificationOptionValue>
			cpDefinitionSpecificationOptionValues =
				_cpDefinitionSpecificationOptionValueService.
					getCPDefinitionSpecificationOptionValues(
						specification.getId(), pagination.getStartPosition(),
						pagination.getEndPosition());

		int totalItems =
			_cpDefinitionSpecificationOptionValueService.
				getCPDefinitionSpecificationOptionValuesCount(
					specification.getId());

		Stream<CPDefinitionSpecificationOptionValue> stream =
			cpDefinitionSpecificationOptionValues.stream();

		return stream.map(
			cpDefinitionSpecificationOptionValue -> _dtoMapper.modelToDTO(
				cpDefinitionSpecificationOptionValue, language.getLanguageId())
		).collect(
			Collectors.collectingAndThen(
				Collectors.toList(),
				specificationValueDTOs -> new CollectionDTO<>(
					specificationValueDTOs, totalItems))
		);
	}

	public CPDefinitionSpecificationOptionValue updateSpecificationValue(
			Long id, SpecificationValueDTO specificationValueDTO)
		throws PortalException {

		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue =
				_cpDefinitionSpecificationOptionValueService.
					getCPDefinitionSpecificationOptionValue(id);

		return _cpDefinitionSpecificationOptionValueService.
			updateCPDefinitionSpecificationOptionValue(
				cpDefinitionSpecificationOptionValue.
					getCPDefinitionSpecificationOptionValueId(),
				_getCPOptionCategoryId(specificationValueDTO),
				LanguageUtils.getLocalizedMap(specificationValueDTO.getValue()),
				_getPriority(specificationValueDTO),
				_serviceContextHelper.getServiceContext(
					cpDefinitionSpecificationOptionValue.getGroupId()));
	}

	public SpecificationValueDTO upsertSpecificationValue(
			Long specificationId, SpecificationValueDTO specificationValueDTO,
			Language language)
		throws PortalException {

		try {
			CPDefinitionSpecificationOptionValue
				cpDefinitionSpecificationOptionValue = updateSpecificationValue(
					specificationValueDTO.getId(), specificationValueDTO);

			return _dtoMapper.modelToDTO(
				cpDefinitionSpecificationOptionValue, language.getLanguageId());
		}
		catch (NoSuchCPDefinitionSpecificationOptionValueException nscpdsove) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to find specificationValue with ID: " +
						specificationValueDTO.getId());
			}
		}

		CPSpecificationOption cpSpecificationOption =
			_cpSpecificationOptionService.getCPSpecificationOption(
				specificationId);

		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue =
				_cpDefinitionSpecificationOptionValueService.
					addCPDefinitionSpecificationOptionValue(
						_getCPDefinitionId(specificationValueDTO),
						_getCPSpecificationOptionId(specificationValueDTO),
						_getCPOptionCategoryId(specificationValueDTO),
						LanguageUtils.getLocalizedMap(
							specificationValueDTO.getValue()),
						_getPriority(specificationValueDTO),
						_serviceContextHelper.getServiceContext(
							cpSpecificationOption.getGroupId()));

		return _dtoMapper.modelToDTO(
			cpDefinitionSpecificationOptionValue, language.getLanguageId());
	}

	private long _getCPDefinitionId(
		SpecificationValueDTO specificationValueDTO) {

		ProductDTO productDTO = specificationValueDTO.getProductDTO();

		if (productDTO == null) {
			return 0;
		}

		return productDTO.getId();
	}

	private long _getCPOptionCategoryId(
		SpecificationValueDTO specificationValueDTO) {

		OptionCategoryDTO optionCategoryDTO =
			specificationValueDTO.getOptionCategoryDTO();

		if (optionCategoryDTO == null) {
			return 0;
		}

		return optionCategoryDTO.getId();
	}

	private long _getCPSpecificationOptionId(
		SpecificationValueDTO specificationValueDTO) {

		SpecificationDTO specificationDTO =
			specificationValueDTO.getSpecificationDTO();

		if (specificationDTO == null) {
			return 0;
		}

		return specificationDTO.getId();
	}

	private double _getPriority(SpecificationValueDTO specificationValueDTO) {
		double priority = 0;

		if (specificationValueDTO.getPriority() != null) {
			priority = specificationValueDTO.getPriority();
		}

		return priority;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SpecificationValueHelper.class);

	@Reference
	private CPDefinitionSpecificationOptionValueService
		_cpDefinitionSpecificationOptionValueService;

	@Reference
	private CPSpecificationOptionService _cpSpecificationOptionService;

	@Reference
	private DTOMapper _dtoMapper;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

	@Reference
	private SpecificationHelper _specificationHelper;

}