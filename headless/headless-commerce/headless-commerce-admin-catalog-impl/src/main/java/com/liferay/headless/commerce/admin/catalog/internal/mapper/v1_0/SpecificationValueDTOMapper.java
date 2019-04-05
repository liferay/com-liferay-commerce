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

package com.liferay.headless.commerce.admin.catalog.internal.mapper.v1_0;

import com.liferay.commerce.product.model.CPDefinitionSpecificationOptionValue;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.SpecificationValue;
import com.liferay.headless.commerce.core.util.LanguageUtils;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = SpecificationValueDTOMapper.class)
public class SpecificationValueDTOMapper {

	public SpecificationValue toSpecificationValue(
		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue,
		String languageId) {

		SpecificationValue specificationValue = new SpecificationValue();

		if (cpDefinitionSpecificationOptionValue == null) {
			return specificationValue;
		}

		try {
			specificationValue.setOptionCategory(
				_optionCategoryDTOMapper.toOptionCategory(
					cpDefinitionSpecificationOptionValue.
						getCPOptionCategory()));
			specificationValue.setProduct(
				_productDTOMapper.toProduct(
					cpDefinitionSpecificationOptionValue.getCPDefinition(),
					languageId));
			specificationValue.setSpecification(
				_specificationDTOMapper.toSpecification(
					cpDefinitionSpecificationOptionValue.
						getCPSpecificationOption()));
		}
		catch (Exception e) {
			_log.error("Cannot instantiate Specification ", e);

			throw new RuntimeException(e);
		}

		specificationValue.setId(
			cpDefinitionSpecificationOptionValue.
				getCPDefinitionSpecificationOptionValueId());
		specificationValue.setPriority(
			cpDefinitionSpecificationOptionValue.getPriority());
		specificationValue.setValue(
			LanguageUtils.getLanguageIdMap(
				cpDefinitionSpecificationOptionValue.getValueMap()));

		return specificationValue;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SpecificationValueDTOMapper.class);

	@Reference
	private OptionCategoryDTOMapper _optionCategoryDTOMapper;

	@Reference
	private ProductDTOMapper _productDTOMapper;

	@Reference
	private SpecificationDTOMapper _specificationDTOMapper;

}