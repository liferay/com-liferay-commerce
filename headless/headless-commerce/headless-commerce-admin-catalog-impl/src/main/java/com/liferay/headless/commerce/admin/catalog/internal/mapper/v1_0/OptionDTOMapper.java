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

import com.liferay.commerce.product.model.CPOption;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Option;
import com.liferay.headless.commerce.core.util.LanguageUtils;

import org.osgi.service.component.annotations.Component;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = OptionDTOMapper.class)
public class OptionDTOMapper {

	public Option toOption(CPOption cpOption) {
		Option option = new Option();

		if (cpOption == null) {
			return option;
		}

		option.setDescription(
			LanguageUtils.getLanguageIdMap(cpOption.getDescriptionMap()));
		option.setExternalReferenceCode(cpOption.getExternalReferenceCode());
		option.setFacetable(cpOption.isFacetable());
		option.setFieldType(
			Option.FieldType.create(cpOption.getDDMFormFieldTypeName()));
		option.setId(cpOption.getCPOptionId());
		option.setKey(cpOption.getKey());
		option.setName(LanguageUtils.getLanguageIdMap(cpOption.getNameMap()));
		option.setRequired(cpOption.isRequired());
		option.setSkuContributor(cpOption.isSkuContributor());

		return option;
	}

}