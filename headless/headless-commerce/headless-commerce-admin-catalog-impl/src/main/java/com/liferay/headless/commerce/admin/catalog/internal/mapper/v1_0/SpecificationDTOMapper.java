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

import com.liferay.commerce.product.model.CPSpecificationOption;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Specification;
import com.liferay.headless.commerce.core.util.LanguageUtils;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = SpecificationDTOMapper.class)
public class SpecificationDTOMapper {

	public Specification toSpecification(
		CPSpecificationOption cpSpecificationOption) {

		Specification specification = new Specification();

		if (cpSpecificationOption == null) {
			return specification;
		}

		try {
			specification.setOptionCategory(
				_optionCategoryDTOMapper.toOptionCategory(
					cpSpecificationOption.getCPOptionCategory()));
		}
		catch (Exception e) {
			_log.error("Cannot instantiate Specification ", e);

			throw new RuntimeException(e);
		}

		specification.setDescription(
			LanguageUtils.getLanguageIdMap(
				cpSpecificationOption.getDescriptionMap()));
		specification.setFacetable(cpSpecificationOption.isFacetable());
		specification.setId(cpSpecificationOption.getCPOptionCategoryId());
		specification.setKey(cpSpecificationOption.getKey());
		specification.setTitle(
			LanguageUtils.getLanguageIdMap(
				cpSpecificationOption.getTitleMap()));

		return specification;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SpecificationDTOMapper.class);

	@Reference
	private OptionCategoryDTOMapper _optionCategoryDTOMapper;

}