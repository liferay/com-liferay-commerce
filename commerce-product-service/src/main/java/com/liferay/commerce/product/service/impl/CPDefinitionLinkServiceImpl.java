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

import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPDefinitionLink;
import com.liferay.commerce.product.model.CProduct;
import com.liferay.commerce.product.service.base.CPDefinitionLinkServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * @author Alessio Antonio Rendina
 * @author Marco Leo
 */
public class CPDefinitionLinkServiceImpl
	extends CPDefinitionLinkServiceBaseImpl {

	@Override
	public void deleteCPDefinitionLink(long cpDefinitionLinkId)
		throws PortalException {

		CPDefinitionLink cpDefinitionLink =
			cpDefinitionLinkLocalService.getCPDefinitionLink(
				cpDefinitionLinkId);

		_cpDefinitionModelResourcePermission.check(
			getPermissionChecker(), cpDefinitionLink.getCPDefinition(),
			ActionKeys.UPDATE);

		CProduct cProduct = cpDefinitionLink.getCProduct();

		_cpDefinitionModelResourcePermission.check(
			getPermissionChecker(), cProduct.getPublishedDefinitionId(),
			ActionKeys.UPDATE);

		cpDefinitionLinkLocalService.deleteCPDefinitionLink(cpDefinitionLinkId);
	}

	@Override
	public CPDefinitionLink fetchCPDefinitionLink(long cpDefinitionLinkId)
		throws PortalException {

		CPDefinitionLink cpDefinitionLink =
			cpDefinitionLinkLocalService.fetchCPDefinitionLink(
				cpDefinitionLinkId);

		if (cpDefinitionLink != null) {
			_cpDefinitionModelResourcePermission.check(
				getPermissionChecker(), cpDefinitionLink.getCPDefinition(),
				ActionKeys.VIEW);

			CProduct cProduct = cpDefinitionLink.getCProduct();

			_cpDefinitionModelResourcePermission.check(
				getPermissionChecker(), cProduct.getPublishedDefinitionId(),
				ActionKeys.VIEW);
		}

		return cpDefinitionLink;
	}

	@Override
	public CPDefinitionLink getCPDefinitionLink(long cpDefinitionLinkId)
		throws PortalException {

		CPDefinitionLink cpDefinitionLink =
			cpDefinitionLinkLocalService.getCPDefinitionLink(
				cpDefinitionLinkId);

		CProduct cProduct = cpDefinitionLink.getCProduct();

		_cpDefinitionModelResourcePermission.check(
			getPermissionChecker(), cProduct.getPublishedDefinitionId(),
			ActionKeys.VIEW);

		_cpDefinitionModelResourcePermission.check(
			getPermissionChecker(), cpDefinitionLink.getCProductId(),
			ActionKeys.VIEW);

		return cpDefinitionLinkLocalService.getCPDefinitionLink(
			cpDefinitionLinkId);
	}

	@Override
	public List<CPDefinitionLink> getCPDefinitionLinks(
			long cpDefinitionId, String type)
		throws PortalException {

		_cpDefinitionModelResourcePermission.check(
			getPermissionChecker(), cpDefinitionId, ActionKeys.VIEW);

		return cpDefinitionLinkLocalService.getCPDefinitionLinks(
			cpDefinitionId, type);
	}

	@Override
	public List<CPDefinitionLink> getCPDefinitionLinks(
			long cpDefinitionId, String type, int start, int end,
			OrderByComparator<CPDefinitionLink> orderByComparator)
		throws PortalException {

		_cpDefinitionModelResourcePermission.check(
			getPermissionChecker(), cpDefinitionId, ActionKeys.VIEW);

		return cpDefinitionLinkLocalService.getCPDefinitionLinks(
			cpDefinitionId, type, start, end, orderByComparator);
	}

	@Override
	public int getCPDefinitionLinksCount(long cpDefinitionId, String type)
		throws PortalException {

		_cpDefinitionModelResourcePermission.check(
			getPermissionChecker(), cpDefinitionId, ActionKeys.VIEW);

		return cpDefinitionLinkLocalService.getCPDefinitionLinksCount(
			cpDefinitionId, type);
	}

	@Override
	public CPDefinitionLink updateCPDefinitionLink(
			long cpDefinitionLinkId, double priority,
			ServiceContext serviceContext)
		throws PortalException {

		CPDefinitionLink cpDefinitionLink =
			cpDefinitionLinkLocalService.getCPDefinitionLink(
				cpDefinitionLinkId);

		_cpDefinitionModelResourcePermission.check(
			getPermissionChecker(), cpDefinitionLink.getCPDefinition(),
			ActionKeys.UPDATE);

		CProduct cProduct = cpDefinitionLink.getCProduct();

		_cpDefinitionModelResourcePermission.check(
			getPermissionChecker(), cProduct.getPublishedDefinitionId(),
			ActionKeys.UPDATE);

		return cpDefinitionLinkLocalService.updateCPDefinitionLink(
			cpDefinitionLinkId, priority, serviceContext);
	}

	@Override
	public void updateCPDefinitionLinks(
			long cpDefinitionId, long[] cpDefinitionIds2, String type,
			ServiceContext serviceContext)
		throws PortalException {

		_cpDefinitionModelResourcePermission.check(
			getPermissionChecker(), cpDefinitionId, ActionKeys.UPDATE);

		cpDefinitionLinkLocalService.updateCPDefinitionLinks(
			cpDefinitionId, cpDefinitionIds2, type, serviceContext);
	}

	private static volatile ModelResourcePermission<CPDefinition>
		_cpDefinitionModelResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				CPDefinitionLinkServiceImpl.class,
				"_cpDefinitionModelResourcePermission", CPDefinition.class);

}