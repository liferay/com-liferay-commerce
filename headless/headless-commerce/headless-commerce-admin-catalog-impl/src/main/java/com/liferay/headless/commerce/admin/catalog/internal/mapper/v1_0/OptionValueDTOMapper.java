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

import com.liferay.commerce.product.model.CPOptionValue;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.OptionValue;
import com.liferay.headless.commerce.core.util.LanguageUtils;

import org.osgi.service.component.annotations.Component;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = OptionValueDTOMapper.class)
public class OptionValueDTOMapper {

	public OptionValue toOptionValue(CPOptionValue cpOptionValue) {
		OptionValue optionValue = new OptionValue();

		if (cpOptionValue == null) {
			return optionValue;
		}

		optionValue.setExternalReferenceCode(
			cpOptionValue.getExternalReferenceCode());
		optionValue.setId(cpOptionValue.getCPOptionValueId());
		optionValue.setKey(cpOptionValue.getKey());
		optionValue.setName(
			LanguageUtils.getLanguageIdMap(cpOptionValue.getNameMap()));
		optionValue.setPriority(cpOptionValue.getPriority());

		return optionValue;
	}

}