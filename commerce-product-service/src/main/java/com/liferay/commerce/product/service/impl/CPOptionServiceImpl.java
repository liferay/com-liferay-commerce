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
import com.liferay.commerce.product.constants.CPConstants;
import com.liferay.commerce.product.model.CPOption;
import com.liferay.commerce.product.service.base.CPOptionServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Marco Leo
 */
public class CPOptionServiceImpl extends CPOptionServiceBaseImpl {

	@Override
	public CPOption addCPOption(
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
			String ddmFormFieldTypeName, boolean facetable, boolean required,
			boolean skuContributor, String key, ServiceContext serviceContext)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			CPActionKeys.ADD_COMMERCE_PRODUCT_OPTION);

		return cpOptionLocalService.addCPOption(
			nameMap, descriptionMap, ddmFormFieldTypeName, facetable, required,
			skuContributor, key, serviceContext);
	}

	@Override
	public void deleteCPOption(long cpOptionId) throws PortalException {
		CPOption cpOption = cpOptionService.getCPOption(cpOptionId);

		cpOptionLocalService.deleteCPOption(cpOption);
	}

	@Override
	public CPOption fetchByExternalReferenceCode(
			long companyId, String externalReferenceCode)
		throws PortalException {

		CPOption cpOption = cpOptionLocalService.fetchByExternalReferenceCode(
			companyId, externalReferenceCode);

		if (cpOption != null) {
			_portletResourcePermission.check(
				getPermissionChecker(), cpOption.getGroupId(),
				CPActionKeys.MANAGE_CATALOG);
		}

		return cpOption;
	}

	@Override
	public CPOption fetchCPOption(long cpOptionId) throws PortalException {
		CPOption cpOption = cpOptionLocalService.fetchCPOption(cpOptionId);

		if (cpOption != null) {
			_portletResourcePermission.check(
				getPermissionChecker(), cpOption.getGroupId(),
				CPActionKeys.MANAGE_CATALOG);
		}

		return cpOption;
	}

	@Override
	public CPOption fetchCPOption(long groupId, String key)
		throws PortalException {

		CPOption cpOption = cpOptionLocalService.fetchCPOption(groupId, key);

		if (cpOption != null) {
			_portletResourcePermission.check(
				getPermissionChecker(), cpOption.getGroupId(),
				CPActionKeys.MANAGE_CATALOG);
		}

		return cpOption;
	}

	@Override
	public CPOption getCPOption(long cpOptionId) throws PortalException {
		CPOption cpOption = cpOptionLocalService.getCPOption(cpOptionId);

		_portletResourcePermission.check(
			getPermissionChecker(), cpOption.getGroupId(),
			CPActionKeys.MANAGE_CATALOG);

		return cpOption;
	}

	@Override
	public List<CPOption> getCPOptions(
			long companyId, int start, int end,
			OrderByComparator<CPOption> orderByComparator)
		throws PortalException {

		return cpOptionLocalService.getCPOptions(
			companyId, start, end, orderByComparator);
	}

	@Override
	public List<CPOption> getCPOptionsByCatalogGroupId(
			long groupId, int start, int end,
			OrderByComparator<CPOption> orderByComparator)
		throws PortalException {

		return cpOptionLocalService.getCPOptionsByCatalogGroupId(
			groupId, start, end, orderByComparator);
	}

	@Override
	public int getCPOptionsCount(long companyId) throws PortalException {
		return cpOptionLocalService.getCPOptionsCount(companyId);
	}

	@Override
	public int getCPOptionsCountByCatalogGroupId(long groupId)
		throws PortalException {

		return cpOptionLocalService.getCPOptionsCountByCatalogGroupId(groupId);
	}

	@Override
	public BaseModelSearchResult<CPOption> searchCPOptions(
			long companyId, String keywords, int start, int end, Sort sort)
		throws PortalException {

		return cpOptionLocalService.searchCPOptions(
			companyId, keywords, start, end, sort);
	}

	@Override
	public CPOption updateCPOption(
			long cpOptionId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String ddmFormFieldTypeName,
			boolean facetable, boolean required, boolean skuContributor,
			String key, ServiceContext serviceContext)
		throws PortalException {

		CPOption cpOption = cpOptionService.getCPOption(cpOptionId);

		return cpOptionLocalService.updateCPOption(
			cpOption.getCPOptionId(), nameMap, descriptionMap,
			ddmFormFieldTypeName, facetable, required, skuContributor, key,
			serviceContext);
	}

	@Override
	public CPOption upsertCPOption(
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
			String ddmFormFieldTypeName, boolean facetable, boolean required,
			boolean skuContributor, String key, String externalReferenceCode,
			ServiceContext serviceContext)
		throws PortalException {

		CPOption cpOption = cpOptionLocalService.fetchByExternalReferenceCode(
			serviceContext.getCompanyId(), externalReferenceCode);

		if (cpOption == null) {
			_portletResourcePermission.check(
				getPermissionChecker(), serviceContext.getScopeGroupId(),
				CPActionKeys.ADD_COMMERCE_PRODUCT_OPTION);
		}

		return cpOptionLocalService.upsertCPOption(
			nameMap, descriptionMap, ddmFormFieldTypeName, facetable, required,
			skuContributor, key, externalReferenceCode, serviceContext);
	}

	private static volatile PortletResourcePermission
		_portletResourcePermission =
			PortletResourcePermissionFactory.getInstance(
				CPOptionServiceImpl.class, "_portletResourcePermission",
				CPConstants.RESOURCE_NAME);

}