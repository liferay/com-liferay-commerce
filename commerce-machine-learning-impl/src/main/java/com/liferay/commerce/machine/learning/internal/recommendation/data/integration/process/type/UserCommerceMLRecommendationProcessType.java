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

package com.liferay.commerce.machine.learning.internal.recommendation.data.integration.process.type;

import com.liferay.commerce.data.integration.process.type.ProcessType;
import com.liferay.commerce.machine.learning.internal.data.integration.BaseCommerceMLProcessType;

import org.osgi.service.component.annotations.Component;

/**
 * @author Riccardo Ferrari
 */
@Component(
	immediate = true,
	property = {
		"commerce.data.integration.process.type.key=" + UserCommerceMLRecommendationProcessType.KEY,
		"commerce.data.integration.process.type.order=100"
	},
	service = ProcessType.class
)
public class UserCommerceMLRecommendationProcessType
	extends BaseCommerceMLProcessType {

	public static final String KEY = "user-commerce-ml-recommendation";

	@Override
	public String getKey() {
		return KEY;
	}

}