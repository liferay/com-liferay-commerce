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

package com.liferay.commerce.product.service.impl;

import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.base.CommerceCatalogServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Locale;
import java.util.Map;

/**
 * @author Alec Sloan
 */
public class CommerceCatalogServiceImpl extends CommerceCatalogServiceBaseImpl {

	public CommerceCatalog addCommerceCatalog(
			Map<Locale, String> nameMap, String catalogDefaultLanguageId,
			ServiceContext serviceContext)
		throws PortalException {

		return commerceCatalogLocalService.addCommerceCatalog(
			nameMap, catalogDefaultLanguageId, serviceContext);
	}

	public CommerceCatalog deleteCommerceCatalog(long commerceCatalogId)
		throws PortalException {

		return commerceCatalogLocalService.deleteCommerceCatalog(
			commerceCatalogId);
	}

	public CommerceCatalog updateCommerceCatalog(
			long commerceCatalogId, String catalogDefaultLanguageId,
			Map<Locale, String> nameMap, ServiceContext serviceContext)
		throws PortalException {

		return commerceCatalogLocalService.updateCommerceCatalog(
			commerceCatalogId, catalogDefaultLanguageId, nameMap,
			serviceContext);
	}

}