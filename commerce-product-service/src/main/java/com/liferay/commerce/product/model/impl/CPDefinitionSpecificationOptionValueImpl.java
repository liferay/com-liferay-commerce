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

package com.liferay.commerce.product.model.impl;

import com.liferay.commerce.product.constants.CPOptionCategoryConstants;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPOptionCategory;
import com.liferay.commerce.product.model.CPSpecificationOption;
import com.liferay.commerce.product.service.CPDefinitionLocalServiceUtil;
import com.liferay.commerce.product.service.CPOptionCategoryLocalServiceUtil;
import com.liferay.commerce.product.service.CPSpecificationOptionLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Andrea Di Giorgi
 */
public class CPDefinitionSpecificationOptionValueImpl
	extends CPDefinitionSpecificationOptionValueBaseImpl {

	public CPDefinitionSpecificationOptionValueImpl() {
	}

	@Override
	public CPDefinition getCPDefinition() throws PortalException {
		return CPDefinitionLocalServiceUtil.getCPDefinition(
			getCPDefinitionId());
	}

	@Override
	public CPOptionCategory getCPOptionCategory() throws PortalException {
		long cpOptionCategoryId = getCPOptionCategoryId();

		if (cpOptionCategoryId !=
				CPOptionCategoryConstants.DEFAULT_CP_OPTION_CATEGORY_ID) {

			return CPOptionCategoryLocalServiceUtil.getCPOptionCategory(
				cpOptionCategoryId);
		}

		return null;
	}

	@Override
	public CPSpecificationOption getCPSpecificationOption()
		throws PortalException {

		return CPSpecificationOptionLocalServiceUtil.getCPSpecificationOption(
			getCPSpecificationOptionId());
	}

}