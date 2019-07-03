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

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.commerce.product.exception.NoSuchCPDefinitionException;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPDisplayLayout;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.base.CPDisplayLayoutServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portlet.asset.service.permission.AssetCategoryPermission;

/**
 * @author Alessio Antonio Rendina
 */
public class CPDisplayLayoutServiceImpl extends CPDisplayLayoutServiceBaseImpl {

	@Override
	public CPDisplayLayout addCPDisplayLayout(
			Class<?> clazz, long classPK, String layoutUuid,
			ServiceContext serviceContext)
		throws PortalException {

		_checkPermissionByC_C(clazz.getName(), classPK, ActionKeys.UPDATE);

		return cpDisplayLayoutLocalService.addCPDisplayLayout(
			clazz, classPK, layoutUuid, serviceContext);
	}

	@Override
	public void deleteCPDisplayLayout(Class<?> clazz, long classPK)
		throws PortalException {

		_checkPermissionByC_C(clazz.getName(), classPK, ActionKeys.UPDATE);

		cpDisplayLayoutLocalService.deleteCPDisplayLayout(clazz, classPK);
	}

	@Override
	public void deleteCPDisplayLayout(long cpDisplayLayoutId)
		throws PortalException {

		CPDisplayLayout cpDisplayLayout =
			cpDisplayLayoutLocalService.getCPDisplayLayout(cpDisplayLayoutId);

		_checkPermissionByC_C(
			cpDisplayLayout.getClassName(), cpDisplayLayout.getClassPK(),
			ActionKeys.UPDATE);

		cpDisplayLayoutLocalService.deleteCPDisplayLayout(cpDisplayLayout);
	}

	@Override
	public CPDisplayLayout fetchCPDisplayLayout(long cpDisplayLayoutId)
		throws PortalException {

		CPDisplayLayout cpDisplayLayout =
			cpDisplayLayoutLocalService.fetchCPDisplayLayout(cpDisplayLayoutId);

		if (cpDisplayLayout != null) {
			_checkPermissionByC_C(
				cpDisplayLayout.getClassName(), cpDisplayLayout.getClassPK(),
				ActionKeys.VIEW);
		}

		return cpDisplayLayout;
	}

	@Override
	public CPDisplayLayout updateCPDisplayLayout(
			long cpDisplayLayoutId, String layoutUuid)
		throws PortalException {

		CPDisplayLayout cpDisplayLayout =
			cpDisplayLayoutLocalService.getCPDisplayLayout(cpDisplayLayoutId);

		_checkPermissionByC_C(
			cpDisplayLayout.getClassName(), cpDisplayLayout.getClassPK(),
			ActionKeys.UPDATE);

		return cpDisplayLayoutLocalService.updateCPDisplayLayout(
			cpDisplayLayout.getCPDisplayLayoutId(), layoutUuid);
	}

	private void _checkPermissionByC_C(
			String className, long classPK, String actionId)
		throws PortalException {

		if (className.equals(CPDefinition.class.getName())) {
			CPDefinition cpDefinition =
				cpDefinitionLocalService.fetchCPDefinition(classPK);

			if (cpDefinition == null) {
				throw new NoSuchCPDefinitionException();
			}

			CommerceCatalog commerceCatalog =
				commerceCatalogLocalService.fetchCommerceCatalogByGroupId(
					cpDefinition.getGroupId());

			_commerceCatalogModelResourcePermission.check(
				getPermissionChecker(), commerceCatalog, actionId);
		}
		else if (className.equals(AssetCategory.class.getName())) {
			AssetCategoryPermission.check(
				getPermissionChecker(), classPK, actionId);
		}
	}

	private static volatile ModelResourcePermission<CommerceCatalog>
		_commerceCatalogModelResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				CPDisplayLayoutServiceImpl.class,
				"_commerceCatalogModelResourcePermission",
				CommerceCatalog.class);

}