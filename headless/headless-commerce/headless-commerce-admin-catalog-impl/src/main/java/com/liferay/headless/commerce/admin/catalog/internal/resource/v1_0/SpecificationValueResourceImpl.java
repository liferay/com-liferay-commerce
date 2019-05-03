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

import com.liferay.commerce.product.exception.NoSuchCPDefinitionSpecificationOptionValueException;
import com.liferay.commerce.product.model.CPDefinitionSpecificationOptionValue;
import com.liferay.commerce.product.model.CPSpecificationOption;
import com.liferay.commerce.product.service.CPDefinitionSpecificationOptionValueService;
import com.liferay.commerce.product.service.CPSpecificationOptionService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.OptionCategory;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Product;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Specification;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.SpecificationValue;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.SpecificationValueResource;
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

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/specification-value.properties",
	scope = ServiceScope.PROTOTYPE, service = SpecificationValueResource.class
)
public class SpecificationValueResourceImpl
	extends BaseSpecificationValueResourceImpl {

	@Override
	public Page<SpecificationValue> getSpecificationIdSpecificationValuesPage(
			Long id, Pagination pagination)
		throws Exception {

		DTOConverter specificationDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CPSpecificationOption.class.getName());

		Specification specification =
			(Specification)specificationDTOConverter.toDTO(
				new DefaultDTOConverterContext(
					contextAcceptLanguage.getPreferredLocale(), id));

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

		return Page.of(
			_toSpecificationValues(cpDefinitionSpecificationOptionValues),
			pagination, totalItems);
	}

	@Override
	public SpecificationValue postSpecificationIdSpecificationValue(
			Long id, SpecificationValue specificationValue)
		throws Exception {

		return _upsertSpecificationValue(id, specificationValue);
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

	private List<SpecificationValue> _toSpecificationValues(
			List<CPDefinitionSpecificationOptionValue>
				cpDefinitionSpecificationOptionValues)
		throws Exception {

		List<SpecificationValue> specificationValues = new ArrayList<>();

		DTOConverter specificationValueDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CPDefinitionSpecificationOptionValue.class.getName());

		for (CPDefinitionSpecificationOptionValue
				cpDefinitionSpecificationOptionValue :
					cpDefinitionSpecificationOptionValues) {

			specificationValues.add(
				(SpecificationValue)specificationValueDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						contextAcceptLanguage.getPreferredLocale(),
						cpDefinitionSpecificationOptionValue.
							getCPDefinitionSpecificationOptionValueId())));
		}

		return specificationValues;
	}

	private CPDefinitionSpecificationOptionValue _updateSpecificationValue(
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
				GetterUtil.get(
					specificationValue.getPriority(),
					cpDefinitionSpecificationOptionValue.getPriority()),
				_serviceContextHelper.getServiceContext(
					cpDefinitionSpecificationOptionValue.getGroupId()));
	}

	private SpecificationValue _upsertSpecificationValue(
			Long specificationId, SpecificationValue specificationValue)
		throws Exception {

		try {
			CPDefinitionSpecificationOptionValue
				cpDefinitionSpecificationOptionValue =
					_updateSpecificationValue(
						specificationValue.getId(), specificationValue);

			DTOConverter specificationValueDTOConverter =
				_dtoConverterRegistry.getDTOConverter(
					CPDefinitionSpecificationOptionValue.class.getName());

			return (SpecificationValue)specificationValueDTOConverter.toDTO(
				new DefaultDTOConverterContext(
					contextAcceptLanguage.getPreferredLocale(),
					cpDefinitionSpecificationOptionValue.
						getCPDefinitionSpecificationOptionValueId()));
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
						GetterUtil.get(specificationValue.getPriority(), 0D),
						_serviceContextHelper.getServiceContext(
							cpSpecificationOption.getGroupId()));

		DTOConverter specificationValueDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CPDefinitionSpecificationOptionValue.class.getName());

		return (SpecificationValue)specificationValueDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				cpDefinitionSpecificationOptionValue.
					getCPDefinitionSpecificationOptionValueId()));
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SpecificationValueResourceImpl.class);

	@Reference
	private CPDefinitionSpecificationOptionValueService
		_cpDefinitionSpecificationOptionValueService;

	@Reference
	private CPSpecificationOptionService _cpSpecificationOptionService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}