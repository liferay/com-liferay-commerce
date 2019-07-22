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

package com.liferay.headless.commerce.admin.pricing.internal.dto.v1_0.converter;

import com.liferay.commerce.discount.model.CommerceDiscountRule;
import com.liferay.commerce.discount.service.CommerceDiscountRuleService;
import com.liferay.headless.commerce.admin.pricing.dto.v1_0.DiscountRule;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = "model.class.name=com.liferay.commerce.discount.model.CommerceDiscountRule",
	service = {DiscountRuleDTOConverter.class, DTOConverter.class}
)
public class DiscountRuleDTOConverter implements DTOConverter {

	@Override
	public String getContentType() {
		return DiscountRule.class.getSimpleName();
	}

	public DiscountRule toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CommerceDiscountRule commerceDiscountRule =
			_commerceDiscountRuleService.getCommerceDiscountRule(
				dtoConverterContext.getResourcePrimKey());

		return new DiscountRule() {
			{
				discountId = commerceDiscountRule.getCommerceDiscountId();
				id = commerceDiscountRule.getCommerceDiscountRuleId();
				type = commerceDiscountRule.getType();
				typeSettings = commerceDiscountRule.getTypeSettings();
			}
		};
	}

	@Reference
	private CommerceDiscountRuleService _commerceDiscountRuleService;

}