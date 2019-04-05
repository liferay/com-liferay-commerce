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

import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPDefinitionOptionRel;
import com.liferay.commerce.product.model.CPOption;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductOption;
import com.liferay.headless.commerce.core.util.LanguageUtils;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = ProductOptionDTOMapper.class)
public class ProductOptionDTOMapper {

	public ProductOption toProductOption(
		CPDefinitionOptionRel cpDefinitionOptionRel) {

		ProductOption productOption = new ProductOption();

		if (cpDefinitionOptionRel == null) {
			return productOption;
		}

		try {
			CPOption cpOption = cpDefinitionOptionRel.getCPOption();

			productOption.setKey(cpOption.getKey());
			productOption.setOptionId(cpOption.getCPOptionId());
		}
		catch (Exception e) {
			_log.error("Cannot instantiate ProductOption ", e);

			throw new RuntimeException(e);
		}

		productOption.setDescription(
			LanguageUtils.getLanguageIdMap(
				cpDefinitionOptionRel.getDescriptionMap()));
		productOption.setFacetable(cpDefinitionOptionRel.isFacetable());
		productOption.setFieldType(
			ProductOption.FieldType.create(
				cpDefinitionOptionRel.getDDMFormFieldTypeName()));
		productOption.setId(cpDefinitionOptionRel.getCPDefinitionOptionRelId());
		productOption.setName(
			LanguageUtils.getLanguageIdMap(cpDefinitionOptionRel.getNameMap()));
		productOption.setRequired(cpDefinitionOptionRel.isRequired());
		productOption.setSkuContributor(
			cpDefinitionOptionRel.isSkuContributor());

		return productOption;
	}

	public ProductOption[] toProductOptions(CPDefinition cpDefinition) {
		if (cpDefinition == null) {
			return null;
		}

		List<ProductOption> productOptions = new ArrayList<>();

		for (CPDefinitionOptionRel cpDefinitionOptionRel :
				cpDefinition.getCPDefinitionOptionRels()) {

			productOptions.add(toProductOption(cpDefinitionOptionRel));
		}

		Stream<ProductOption> stream = productOptions.stream();

		return stream.toArray(ProductOption[]::new);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ProductOptionDTOMapper.class);

}