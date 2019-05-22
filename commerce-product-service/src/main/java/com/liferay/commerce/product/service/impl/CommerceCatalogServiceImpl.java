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

import com.liferay.commerce.product.constants.CPActionKeys;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.base.CommerceCatalogServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.permission.PortalPermissionUtil;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Alec Sloan
 * @author Alessio Antonio Rendina
 */
public class CommerceCatalogServiceImpl extends CommerceCatalogServiceBaseImpl {

	@Override
	public CommerceCatalog addCommerceCatalog(
			Map<Locale, String> nameMap, String catalogDefaultLanguageId,
			String externalReferenceCode, ServiceContext serviceContext)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(), CPActionKeys.ADD_COMMERCE_CATALOG);

		return commerceCatalogLocalService.addCommerceCatalog(
			nameMap, catalogDefaultLanguageId, externalReferenceCode,
			serviceContext);
	}

	@Override
	public CommerceCatalog deleteCommerceCatalog(long commerceCatalogId)
		throws PortalException {

		_commerceCatalogModelResourcePermission.check(
			getPermissionChecker(), commerceCatalogId, ActionKeys.DELETE);

		return commerceCatalogLocalService.deleteCommerceCatalog(
			commerceCatalogId);
	}

	@Override
	public CommerceCatalog fetchCommerceCatalog(long commerceCatalogId)
		throws PortalException {

		_commerceCatalogModelResourcePermission.check(
			getPermissionChecker(), commerceCatalogId, ActionKeys.VIEW);

		return commerceCatalogLocalService.fetchCommerceCatalog(
			commerceCatalogId);
	}

	@Override
	public Group getCommerceCatalogGroup(long commerceCatalogId)
		throws PortalException {

		_commerceCatalogModelResourcePermission.check(
			getPermissionChecker(), commerceCatalogId, ActionKeys.VIEW);

		return commerceCatalogLocalService.getCommerceCatalogGroup(
			commerceCatalogId);
	}

	@Override
	public List<CommerceCatalog> getCommerceCatalogs(
			long companyId, boolean system)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(), CPActionKeys.VIEW_COMMERCE_CATALOGS);

		return commerceCatalogLocalService.getCommerceCatalogs(
			companyId, system);
	}

	@Override
	public List<CommerceCatalog> searchCommerceCatalogs(long companyId)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(), CPActionKeys.VIEW_COMMERCE_CATALOGS);

		return commerceCatalogLocalService.searchCommerceCatalogs(companyId);
	}

	@Override
	public List<CommerceCatalog> searchCommerceCatalogs(
			long companyId, String keywords, int start, int end, Sort sort)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(), CPActionKeys.VIEW_COMMERCE_CATALOGS);

		return commerceCatalogLocalService.searchCommerceCatalogs(
			companyId, keywords, start, end, sort);
	}

	@Override
	public int searchCommerceCatalogsCount(long companyId, String keywords)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(), CPActionKeys.VIEW_COMMERCE_CATALOGS);

		return commerceCatalogLocalService.searchCommerceCatalogsCount(
			companyId, keywords);
	}

	@Override
	public CommerceCatalog updateCommerceCatalog(
			long commerceCatalogId, Map<Locale, String> nameMap,
			String catalogDefaultLanguageId)
		throws PortalException {

		_commerceCatalogModelResourcePermission.check(
			getPermissionChecker(), commerceCatalogId, ActionKeys.UPDATE);

		return commerceCatalogLocalService.updateCommerceCatalog(
			commerceCatalogId, nameMap, catalogDefaultLanguageId);
	}

	private static volatile ModelResourcePermission<CommerceCatalog>
		_commerceCatalogModelResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				CommerceCatalogServiceImpl.class,
				"_commerceCatalogModelResourcePermission",
				CommerceCatalog.class);

}