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

import com.liferay.commerce.product.exception.NoSuchCPDefinitionSpecificationOptionValueException;
import com.liferay.commerce.product.model.CPDefinitionSpecificationOptionValue;
import com.liferay.commerce.product.model.CPSpecificationOption;
import com.liferay.commerce.product.service.CPDefinitionSpecificationOptionValueService;
import com.liferay.commerce.product.service.CPSpecificationOptionService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.OptionCategory;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Product;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Specification;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.SpecificationValue;
import com.liferay.headless.commerce.admin.catalog.internal.mapper.v1_0.DTOMapper;
import com.liferay.headless.commerce.core.util.LanguageUtils;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
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
@Component(immediate = true, service = SpecificationValueHelper.class)
public class SpecificationValueHelper {

	public void deleteSpecificationValue(Long id) throws PortalException {
		_cpDefinitionSpecificationOptionValueService.
			deleteCPDefinitionSpecificationOptionValue(id);
	}

	public SpecificationValue getSpecificationValue(
			Long id, AcceptLanguage acceptLanguage)
		throws PortalException {

		return _dtoMapper.modelToDTO(
			_cpDefinitionSpecificationOptionValueService.
				getCPDefinitionSpecificationOptionValue(id),
			acceptLanguage.getPreferredLanguageId());
	}

	public Page<SpecificationValue> getSpecificationValues(
			Long specificationId, AcceptLanguage acceptLanguage,
			Pagination pagination)
		throws PortalException {

		Specification specification = _specificationHelper.getSpecification(
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
				cpDefinitionSpecificationOptionValue,
				acceptLanguage.getPreferredLanguageId())
		).collect(
			Collectors.collectingAndThen(
				Collectors.toList(),
				specificationValues -> Page.of(
					specificationValues, pagination, totalItems))
		);
	}

	public CPDefinitionSpecificationOptionValue updateSpecificationValue(
			Long id, SpecificationValue specificationValue)
		throws PortalException {

		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue =
				_cpDefinitionSpecificationOptionValueService.
					getCPDefinitionSpecificationOptionValue(id);

		return _cpDefinitionSpecificationOptionValueService.
			updateCPDefinitionSpecificationOptionValue(
				cpDefinitionSpecificationOptionValue.
					getCPDefinitionSpecificationOptionValueId(),
				_getCPOptionCategoryId(specificationValue),
				LanguageUtils.getLocalizedMap(specificationValue.getValue()),
				_getPriority(specificationValue),
				_serviceContextHelper.getServiceContext(
					cpDefinitionSpecificationOptionValue.getGroupId()));
	}

	public SpecificationValue upsertSpecificationValue(
			Long specificationId, SpecificationValue specificationValue,
			AcceptLanguage acceptLanguage)
		throws PortalException {

		try {
			CPDefinitionSpecificationOptionValue
				cpDefinitionSpecificationOptionValue = updateSpecificationValue(
					specificationValue.getId(), specificationValue);

			return _dtoMapper.modelToDTO(
				cpDefinitionSpecificationOptionValue,
				acceptLanguage.getPreferredLanguageId());
		}
		catch (NoSuchCPDefinitionSpecificationOptionValueException nscpdsove) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to find specificationValue with ID: " +
						specificationValue.getId());
			}
		}

		CPSpecificationOption cpSpecificationOption =
			_cpSpecificationOptionService.getCPSpecificationOption(
				specificationId);

		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue =
				_cpDefinitionSpecificationOptionValueService.
					addCPDefinitionSpecificationOptionValue(
						_getCPDefinitionId(specificationValue),
						_getCPSpecificationOptionId(specificationValue),
						_getCPOptionCategoryId(specificationValue),
						LanguageUtils.getLocalizedMap(
							specificationValue.getValue()),
						_getPriority(specificationValue),
						_serviceContextHelper.getServiceContext(
							cpSpecificationOption.getGroupId()));

		return _dtoMapper.modelToDTO(
			cpDefinitionSpecificationOptionValue,
			acceptLanguage.getPreferredLanguageId());
	}

	private long _getCPDefinitionId(SpecificationValue specificationValue) {
		Product product = specificationValue.getProduct();

		if (product == null) {
			return 0;
		}

		return product.getId();
	}

	private long _getCPOptionCategoryId(SpecificationValue specificationValue) {
		OptionCategory optionCategory = specificationValue.getOptionCategory();

		if (optionCategory == null) {
			return 0;
		}

		return optionCategory.getId();
	}

	private long _getCPSpecificationOptionId(
		SpecificationValue specificationValue) {

		Specification specification = specificationValue.getSpecification();

		if (specification == null) {
			return 0;
		}

		return specification.getId();
	}

	private double _getPriority(SpecificationValue specificationValue) {
		double priority = 0;

		if (specificationValue.getPriority() != null) {
			priority = specificationValue.getPriority();
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