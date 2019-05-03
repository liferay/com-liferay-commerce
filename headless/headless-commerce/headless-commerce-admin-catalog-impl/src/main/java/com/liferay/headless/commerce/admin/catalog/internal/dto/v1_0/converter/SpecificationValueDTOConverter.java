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

package com.liferay.headless.commerce.admin.catalog.internal.dto.v1_0.converter;

import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPDefinitionSpecificationOptionValue;
import com.liferay.commerce.product.model.CPOptionCategory;
import com.liferay.commerce.product.model.CPSpecificationOption;
import com.liferay.commerce.product.service.CPDefinitionSpecificationOptionValueService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.OptionCategory;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Product;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Specification;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.SpecificationValue;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterContext;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterRegistry;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;
import com.liferay.headless.commerce.core.util.LanguageUtils;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = "model.class.name=com.liferay.commerce.product.model.CPDefinitionSpecificationOptionValue",
	service = {DTOConverter.class, SpecificationValueDTOConverter.class}
)
public class SpecificationValueDTOConverter implements DTOConverter {

	@Override
	public String getContentType() {
		return SpecificationValue.class.getSimpleName();
	}

	public SpecificationValue toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue =
				_cpDefinitionSpecificationOptionValueService.
					getCPDefinitionSpecificationOptionValue(
						dtoConverterContext.getResourcePrimKey());

		CPDefinition cpDefinition =
			cpDefinitionSpecificationOptionValue.getCPDefinition();
		CPOptionCategory cpOptionCategory =
			cpDefinitionSpecificationOptionValue.getCPOptionCategory();
		CPSpecificationOption cpSpecificationOption =
			cpDefinitionSpecificationOptionValue.getCPSpecificationOption();

		DTOConverter optionCategoryDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CPOptionCategory.class.getName());
		DTOConverter productDTOConverter =
			_dtoConverterRegistry.getDTOConverter(CPDefinition.class.getName());
		DTOConverter specificationDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CPSpecificationOption.class.getName());

		return new SpecificationValue() {
			{
				id =
					cpDefinitionSpecificationOptionValue.
						getCPDefinitionSpecificationOptionValueId();
				optionCategory =
					(OptionCategory)optionCategoryDTOConverter.toDTO(
						new DefaultDTOConverterContext(
							dtoConverterContext.getLocale(),
							cpOptionCategory.getCPOptionCategoryId()));
				priority = cpDefinitionSpecificationOptionValue.getPriority();
				product = (Product)productDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						dtoConverterContext.getLocale(),
						cpDefinition.getCPDefinitionId()));
				specification = (Specification)specificationDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						dtoConverterContext.getLocale(),
						cpSpecificationOption.getCPSpecificationOptionId()));
				value = LanguageUtils.getLanguageIdMap(
					cpDefinitionSpecificationOptionValue.getValueMap());
			}
		};
	}

	@Reference
	private CPDefinitionSpecificationOptionValueService
		_cpDefinitionSpecificationOptionValueService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

}