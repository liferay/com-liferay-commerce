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

package com.liferay.headless.commerce.admin.pricing.internal.resource.v1_0;

import com.liferay.headless.commerce.admin.pricing.dto.v1_0.DiscountRule;
import com.liferay.headless.commerce.admin.pricing.internal.util.v1_0.DiscountRuleHelper;
import com.liferay.headless.commerce.admin.pricing.resource.v1_0.DiscountRuleResource;

import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Zoltán Takács
 * @author Alessio Antonio Rendina
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/discount-rule.properties",
	scope = ServiceScope.PROTOTYPE, service = DiscountRuleResource.class
)
public class DiscountRuleResourceImpl extends BaseDiscountRuleResourceImpl {

	@Override
	public Response deleteDiscountRule(Long id) throws Exception {
		_discountRuleHelper.deleteDiscountRule(id);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public DiscountRule getDiscountRule(Long id) throws Exception {
		return _discountRuleHelper.getDiscountRule(id);
	}

	@Override
	public DiscountRule postDiscountRule(Long id, DiscountRule discountRule)
		throws Exception {

		return _discountRuleHelper.updateDiscountRule(id, discountRule);
	}

	@Reference
	private DiscountRuleHelper _discountRuleHelper;

}