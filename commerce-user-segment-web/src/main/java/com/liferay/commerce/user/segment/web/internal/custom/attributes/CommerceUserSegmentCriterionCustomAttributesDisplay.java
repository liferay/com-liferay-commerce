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

package com.liferay.commerce.user.segment.web.internal.custom.attributes;

import com.liferay.commerce.user.segment.constants.CommerceUserSegmentPortletKeys;
import com.liferay.commerce.user.segment.model.CommerceUserSegmentCriterion;
import com.liferay.expando.kernel.model.BaseCustomAttributesDisplay;
import com.liferay.expando.kernel.model.CustomAttributesDisplay;

import org.osgi.service.component.annotations.Component;

/**
 * @author Ethan Bustad
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + CommerceUserSegmentPortletKeys.COMMERCE_USER_SEGMENT,
	service = CustomAttributesDisplay.class
)
public class CommerceUserSegmentCriterionCustomAttributesDisplay
	extends BaseCustomAttributesDisplay {

	@Override
	public String getClassName() {
		return CommerceUserSegmentCriterion.class.getName();
	}

	@Override
	public String getIconCssClass() {
		return "tag";
	}

}