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

import com.liferay.commerce.product.model.CPDefinitionOptionValueRel;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductOptionValue;
import com.liferay.headless.commerce.core.util.LanguageUtils;

import org.osgi.service.component.annotations.Component;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = ProductOptionValueDTOMapper.class)
public class ProductOptionValueDTOMapper {

	public ProductOptionValue toProductOptionValue(
		CPDefinitionOptionValueRel cpDefinitionOptionValueRel) {

		ProductOptionValue productOptionValue = new ProductOptionValue();

		if (cpDefinitionOptionValueRel == null) {
			return productOptionValue;
		}

		productOptionValue.setId(
			cpDefinitionOptionValueRel.getCPDefinitionOptionValueRelId());
		productOptionValue.setKey(cpDefinitionOptionValueRel.getKey());
		productOptionValue.setName(
			LanguageUtils.getLanguageIdMap(
				cpDefinitionOptionValueRel.getNameMap()));
		productOptionValue.setPriority(
			cpDefinitionOptionValueRel.getPriority());

		return productOptionValue;
	}

}