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

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.product.service.CommerceCatalogLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;

/**
 * @author Alessio Antonio Rendina
 */
@ProviderType
public class CommerceCatalogImpl extends CommerceCatalogBaseImpl {

	public CommerceCatalogImpl() {
	}

	@Override
	public Group getCommerceCatalogGroup() throws PortalException {
		return CommerceCatalogLocalServiceUtil.getCommerceCatalogGroup(
			getCommerceCatalogId());
	}

	@Override
	public long getCommerceCatalogGroupId() throws PortalException {
		Group group = CommerceCatalogLocalServiceUtil.getCommerceCatalogGroup(
			getCommerceCatalogId());

		return group.getGroupId();
	}

}