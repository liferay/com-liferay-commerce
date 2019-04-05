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

import com.liferay.headless.commerce.admin.catalog.dto.v1_0.OptionCategory;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Product;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Specification;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.SpecificationValue;

/**
 * @author Alessio Antonio Rendina
 */
public class SpecificationValueUtil {

	public static long getCPDefinitionId(
		SpecificationValue specificationValue) {

		Product product = specificationValue.getProduct();

		if (product == null) {
			return 0;
		}

		return product.getId();
	}

	public static long getCPOptionCategoryId(
		SpecificationValue specificationValue) {

		OptionCategory optionCategory = specificationValue.getOptionCategory();

		if (optionCategory == null) {
			return 0;
		}

		return optionCategory.getId();
	}

	public static long getCPSpecificationOptionId(
		SpecificationValue specificationValue) {

		Specification specification = specificationValue.getSpecification();

		if (specification == null) {
			return 0;
		}

		return specification.getId();
	}

	public static double getPriority(SpecificationValue specificationValue) {
		double priority = 0;

		if (specificationValue.getPriority() != null) {
			priority = specificationValue.getPriority();
		}

		return priority;
	}

}