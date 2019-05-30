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
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CProduct;
import com.liferay.commerce.product.service.base.CPInstanceServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.math.BigDecimal;

import java.util.List;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class CPInstanceServiceImpl extends CPInstanceServiceBaseImpl {

	@Override
	public CPInstance addCPInstance(
			long cpDefinitionId, long groupId, String sku, String gtin,
			String manufacturerPartNumber, boolean purchasable, String json,
			boolean published, int displayDateMonth, int displayDateDay,
			int displayDateYear, int displayDateHour, int displayDateMinute,
			int expirationDateMonth, int expirationDateDay,
			int expirationDateYear, int expirationDateHour,
			int expirationDateMinute, boolean neverExpire,
			ServiceContext serviceContext)
		throws PortalException {

		_cpDefinitionModelResourcePermission.check(
			getPermissionChecker(), cpDefinitionId, ActionKeys.UPDATE);

		return cpInstanceLocalService.addCPInstance(
			cpDefinitionId, groupId, sku, gtin, manufacturerPartNumber,
			purchasable, json, published, displayDateMonth, displayDateDay,
			displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, neverExpire,
			serviceContext);
	}

	@Override
	public void buildCPInstances(
			long cpDefinitionId, ServiceContext serviceContext)
		throws PortalException {

		_cpDefinitionModelResourcePermission.check(
			getPermissionChecker(), cpDefinitionId, ActionKeys.UPDATE);

		cpInstanceLocalService.buildCPInstances(cpDefinitionId, serviceContext);
	}

	@Override
	public void deleteCPInstance(long cpInstanceId) throws PortalException {
		CPInstance cpInstance = cpInstanceService.getCPInstance(cpInstanceId);

		_cpDefinitionModelResourcePermission.check(
			getPermissionChecker(), cpInstance.getCPDefinitionId(),
			ActionKeys.UPDATE);

		cpInstanceLocalService.deleteCPInstance(cpInstance);
	}

	@Override
	public CPInstance fetchByExternalReferenceCode(
			long companyId, String externalReferenceCode)
		throws PortalException {

		CPInstance cpInstance =
			cpInstanceLocalService.fetchByExternalReferenceCode(
				companyId, externalReferenceCode);

		if (cpInstance != null) {
			_cpDefinitionModelResourcePermission.check(
				getPermissionChecker(), cpInstance.getCPDefinitionId(),
				ActionKeys.VIEW);
		}

		return cpInstance;
	}

	@Override
	public CPInstance fetchCPInstance(long cpInstanceId)
		throws PortalException {

		CPInstance cpInstance = cpInstanceLocalService.fetchCPInstance(
			cpInstanceId);

		if (cpInstance != null) {
			_cpDefinitionModelResourcePermission.check(
				getPermissionChecker(), cpInstance.getCPDefinitionId(),
				ActionKeys.VIEW);
		}

		return cpInstance;
	}

	@Override
	public CPInstance fetchCProductInstance(
			long cProductId, String cpInstanceUuid)
		throws PortalException {

		CProduct cProduct = cProductLocalService.fetchCProduct(cProductId);

		if (cProduct == null) {
			return null;
		}

		_cpDefinitionModelResourcePermission.check(
			getPermissionChecker(), cProduct.getPublishedCPDefinitionId(),
			ActionKeys.VIEW);

		return cpInstanceLocalService.fetchCProductInstance(
			cProductId, cpInstanceUuid);
	}

	@Override
	public List<CPInstance> getCPDefinitionInstances(
			long cpDefinitionId, int status, int start, int end,
			OrderByComparator<CPInstance> orderByComparator)
		throws PortalException {

		_cpDefinitionModelResourcePermission.check(
			getPermissionChecker(), cpDefinitionId, ActionKeys.VIEW);

		return cpInstanceLocalService.getCPDefinitionInstances(
			cpDefinitionId, status, start, end, orderByComparator);
	}

	@Override
	public int getCPDefinitionInstancesCount(long cpDefinitionId, int status)
		throws PortalException {

		_cpDefinitionModelResourcePermission.check(
			getPermissionChecker(), cpDefinitionId, ActionKeys.VIEW);

		return cpInstanceLocalService.getCPDefinitionInstancesCount(
			cpDefinitionId, status);
	}

	@Override
	public CPInstance getCPInstance(long cpInstanceId) throws PortalException {
		CPInstance cpInstance = cpInstanceLocalService.getCPInstance(
			cpInstanceId);

		_cpDefinitionModelResourcePermission.check(
			getPermissionChecker(), cpInstance.getCPDefinitionId(),
			ActionKeys.VIEW);

		return cpInstance;
	}

	@Override
	public List<CPInstance> getCPInstances(
			long groupId, int status, int start, int end,
			OrderByComparator<CPInstance> orderByComparator)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId, CPActionKeys.MANAGE_CATALOG);

		return cpInstanceLocalService.getCPInstances(
			groupId, status, start, end, orderByComparator);
	}

	@Override
	public int getCPInstancesCount(long groupId, int status)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId, CPActionKeys.MANAGE_CATALOG);

		return cpInstanceLocalService.getCPInstancesCount(groupId, status);
	}

	@Override
	public BaseModelSearchResult<CPInstance> searchCPDefinitionInstances(
			long companyId, long cpDefinitionId, String keywords, int status,
			int start, int end, Sort sort)
		throws PortalException {

		if (cpDefinitionId > 0) {
			_cpDefinitionModelResourcePermission.check(
				getPermissionChecker(), cpDefinitionId, ActionKeys.VIEW);
		}

		return cpInstanceLocalService.searchCPDefinitionInstances(
			companyId, cpDefinitionId, keywords, status, start, end, sort);
	}

	@Override
	public BaseModelSearchResult<CPInstance> searchCPInstances(
			long companyId, String keywords, int status, int start, int end,
			Sort sort)
		throws PortalException {

		return cpInstanceLocalService.searchCPInstances(
			companyId, keywords, status, start, end, sort);
	}

	@Override
	public CPInstance updateCPInstance(
			long cpInstanceId, String sku, String gtin,
			String manufacturerPartNumber, boolean purchasable,
			boolean published, int displayDateMonth, int displayDateDay,
			int displayDateYear, int displayDateHour, int displayDateMinute,
			int expirationDateMonth, int expirationDateDay,
			int expirationDateYear, int expirationDateHour,
			int expirationDateMinute, boolean neverExpire,
			ServiceContext serviceContext)
		throws PortalException {

		CPInstance cpInstance = cpInstanceLocalService.getCPInstance(
			cpInstanceId);

		_cpDefinitionModelResourcePermission.check(
			getPermissionChecker(), cpInstance.getCPDefinitionId(),
			ActionKeys.UPDATE);

		return cpInstanceLocalService.updateCPInstance(
			cpInstanceId, sku, gtin, manufacturerPartNumber, purchasable,
			published, displayDateMonth, displayDateDay, displayDateYear,
			displayDateHour, displayDateMinute, expirationDateMonth,
			expirationDateDay, expirationDateYear, expirationDateHour,
			expirationDateMinute, neverExpire, serviceContext);
	}

	@Override
	public CPInstance updatePricingInfo(
			long cpInstanceId, BigDecimal price, BigDecimal promoPrice,
			BigDecimal cost, ServiceContext serviceContext)
		throws PortalException {

		CPInstance cpInstance = cpInstanceLocalService.getCPInstance(
			cpInstanceId);

		_cpDefinitionModelResourcePermission.check(
			getPermissionChecker(), cpInstance.getCPDefinitionId(),
			ActionKeys.UPDATE);

		return cpInstanceLocalService.updatePricingInfo(
			cpInstanceId, price, promoPrice, cost, serviceContext);
	}

	@Override
	public CPInstance updateShippingInfo(
			long cpInstanceId, double width, double height, double depth,
			double weight, ServiceContext serviceContext)
		throws PortalException {

		CPInstance cpInstance = cpInstanceLocalService.getCPInstance(
			cpInstanceId);

		_cpDefinitionModelResourcePermission.check(
			getPermissionChecker(), cpInstance.getCPDefinitionId(),
			ActionKeys.UPDATE);

		return cpInstanceLocalService.updateShippingInfo(
			cpInstanceId, width, height, depth, weight, serviceContext);
	}

	@Override
	public CPInstance updateSubscriptionInfo(
			long cpInstanceId, boolean overrideSubscriptionInfo,
			boolean subscriptionEnabled, int subscriptionLength,
			String subscriptionType,
			UnicodeProperties subscriptionTypeSettingsProperties,
			long maxSubscriptionCycles, ServiceContext serviceContext)
		throws PortalException {

		CPInstance cpInstance = cpInstanceLocalService.getCPInstance(
			cpInstanceId);

		_cpDefinitionModelResourcePermission.check(
			getPermissionChecker(), cpInstance.getCPDefinitionId(),
			ActionKeys.UPDATE);

		return cpInstanceLocalService.updateSubscriptionInfo(
			cpInstanceId, overrideSubscriptionInfo, subscriptionEnabled,
			subscriptionLength, subscriptionType,
			subscriptionTypeSettingsProperties, maxSubscriptionCycles,
			serviceContext);
	}

	@Override
	public CPInstance upsertCPInstance(
			long cpDefinitionId, long groupId, String sku, String gtin,
			String manufacturerPartNumber, boolean purchasable, String json,
			double width, double height, double depth, double weight,
			BigDecimal price, BigDecimal promoPrice, BigDecimal cost,
			boolean published, String externalReferenceCode,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, ServiceContext serviceContext)
		throws PortalException {

		CPInstance cpInstance =
			cpInstanceLocalService.fetchByExternalReferenceCode(
				serviceContext.getCompanyId(), externalReferenceCode);

		if (cpInstance == null) {
			_cpDefinitionModelResourcePermission.check(
				getPermissionChecker(), cpDefinitionId,
				CPActionKeys.ADD_COMMERCE_PRODUCT_INSTANCE);
		}
		else {
			_cpDefinitionModelResourcePermission.check(
				getPermissionChecker(), cpInstance.getCPDefinitionId(),
				CPActionKeys.UPDATE_COMMERCE_PRODUCT_INSTANCE);
		}

		return cpInstanceLocalService.upsertCPInstance(
			cpDefinitionId, groupId, sku, gtin, manufacturerPartNumber,
			purchasable, json, width, height, depth, weight, price, promoPrice,
			cost, published, externalReferenceCode, displayDateMonth,
			displayDateDay, displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, neverExpire,
			serviceContext);
	}

	private static volatile ModelResourcePermission<CPDefinition>
		_cpDefinitionModelResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				CPInstanceServiceImpl.class,
				"_cpDefinitionModelResourcePermission", CPDefinition.class);
	private static volatile PortletResourcePermission
		_portletResourcePermission =
			PortletResourcePermissionFactory.getInstance(
				CPDefinitionServiceImpl.class, "_portletResourcePermission",
				CPConstants.RESOURCE_NAME);

}