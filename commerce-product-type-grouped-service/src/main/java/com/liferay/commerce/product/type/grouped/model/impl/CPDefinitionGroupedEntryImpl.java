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

package com.liferay.commerce.product.type.grouped.model.impl;

import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CProduct;
import com.liferay.commerce.product.service.CPDefinitionLocalServiceUtil;
import com.liferay.commerce.product.service.CProductLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Andrea Di Giorgi
 * @author Ethan Bustad
 */
public class CPDefinitionGroupedEntryImpl
	extends CPDefinitionGroupedEntryBaseImpl {

	public CPDefinitionGroupedEntryImpl() {
	}

	@Override
	public CPDefinition getCPDefinition() throws PortalException {
		return CPDefinitionLocalServiceUtil.getCPDefinition(
			getCPDefinitionId());
	}

	@Override
	public CPDefinition getEntryCPDefinition() throws PortalException {
		CProduct cProduct = getEntryCProduct();

		return CPDefinitionLocalServiceUtil.fetchCPDefinition(
			cProduct.getPublishedCPDefinitionId());
	}

	@Override
	public long getEntryCPDefinitionId() throws PortalException {
		CProduct cProduct = getEntryCProduct();

		return cProduct.getPublishedCPDefinitionId();
	}

	@Override
	public CProduct getEntryCProduct() throws PortalException {
		return CProductLocalServiceUtil.getCProduct(getEntryCProductId());
	}

}