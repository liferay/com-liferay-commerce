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

package com.liferay.commerce.product.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.commerce.product.constants.CPRuleConstants;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Ethan Bustad
 */
@ExtendedObjectClassDefinition(
	category = "orders", scope = ExtendedObjectClassDefinition.Scope.GROUP
)
@Meta.OCD(
	id = "com.liferay.commerce.product.configuration.CPRuleGroupServiceConfiguration",
	localization = "content/Language",
	name = "commerce-catalog-rule-group-service-configuration-name"
)
public interface CPRuleGroupServiceConfiguration {

	@Meta.AD(
		deflt = "" + CPRuleConstants.APPLICATION_TYPE_ALL,
		name = "catalog-rule-application-type",
		optionLabels = {
			"all-catalog-rules-must-be-satisfied",
			"one-or-more-catalog-rules-must-be-satisfied"
		},
		optionValues = {"0", "1"}, required = false
	)
	public int catalogRuleApplicationType();

}