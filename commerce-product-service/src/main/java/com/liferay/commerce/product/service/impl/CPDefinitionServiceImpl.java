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

import com.liferay.commerce.product.exception.NoSuchCPDefinitionException;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.base.CPDefinitionServiceBaseImpl;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.io.Serializable;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class CPDefinitionServiceImpl extends CPDefinitionServiceBaseImpl {

	@Override
	public CPDefinition addCPDefinition(
			long groupId, long userId, Map<Locale, String> nameMap,
			Map<Locale, String> shortDescriptionMap,
			Map<Locale, String> descriptionMap, Map<Locale, String> urlTitleMap,
			Map<Locale, String> metaTitleMap,
			Map<Locale, String> metaDescriptionMap,
			Map<Locale, String> metaKeywordsMap, String productTypeName,
			boolean ignoreSKUCombinations, boolean shippable,
			boolean freeShipping, boolean shipSeparately,
			double shippingExtraPrice, double width, double height,
			double depth, double weight, long cpTaxCategoryId,
			boolean taxExempt, boolean telcoOrElectronics,
			String ddmStructureKey, boolean published, int displayDateMonth,
			int displayDateDay, int displayDateYear, int displayDateHour,
			int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, String defaultSku, boolean subscriptionEnabled,
			int subscriptionLength, String subscriptionType,
			UnicodeProperties subscriptionTypeSettingsProperties,
			long maxSubscriptionCycles, String externalReferenceCode,
			ServiceContext serviceContext)
		throws PortalException {

		_checkCommerceCatalogPermission(groupId, ActionKeys.UPDATE);

		return cpDefinitionLocalService.addCPDefinition(
			groupId, userId, nameMap, shortDescriptionMap, descriptionMap,
			urlTitleMap, metaTitleMap, metaDescriptionMap, metaKeywordsMap,
			productTypeName, ignoreSKUCombinations, shippable, freeShipping,
			shipSeparately, shippingExtraPrice, width, height, depth, weight,
			cpTaxCategoryId, taxExempt, telcoOrElectronics, ddmStructureKey,
			published, displayDateMonth, displayDateDay, displayDateYear,
			displayDateHour, displayDateMinute, expirationDateMonth,
			expirationDateDay, expirationDateYear, expirationDateHour,
			expirationDateMinute, neverExpire, defaultSku, subscriptionEnabled,
			subscriptionLength, subscriptionType,
			subscriptionTypeSettingsProperties, maxSubscriptionCycles,
			externalReferenceCode, serviceContext);
	}

	@Override
	public void deleteAssetCategoryCPDefinition(
			long cpDefinitionId, long categoryId, ServiceContext serviceContext)
		throws PortalException {

		_checkCommerceCatalogPermissionByCPDefinitionId(
			cpDefinitionId, ActionKeys.UPDATE);

		cpDefinitionLocalService.deleteAssetCategoryCPDefinition(
			cpDefinitionId, categoryId, serviceContext);
	}

	@Override
	public void deleteCPDefinition(long cpDefinitionId) throws PortalException {
		_checkCommerceCatalogPermissionByCPDefinitionId(
			cpDefinitionId, ActionKeys.UPDATE);

		cpDefinitionLocalService.deleteCPDefinition(cpDefinitionId);
	}

	@Override
	public CPDefinition fetchCPDefinition(long cpDefinitionId)
		throws PortalException {

		CPDefinition cpDefinition = cpDefinitionLocalService.fetchCPDefinition(
			cpDefinitionId);

		if (cpDefinition != null) {
			_checkCommerceCatalogPermissionByCPDefinitionId(
				cpDefinitionId, ActionKeys.VIEW);
		}

		return cpDefinition;
	}

	@Override
	public CPDefinition fetchCPDefinitionByCProductExternalReferenceCode(
			long companyId, String externalReferenceCode)
		throws PortalException {

		CPDefinition cpDefinition =
			cpDefinitionLocalService.
				fetchCPDefinitionByCProductExternalReferenceCode(
					companyId, externalReferenceCode);

		if (cpDefinition != null) {
			_checkCommerceCatalogPermissionByCPDefinitionId(
				cpDefinition.getCPDefinitionId(), ActionKeys.VIEW);
		}

		return cpDefinition;
	}

	@Override
	public CPDefinition fetchCPDefinitionByCProductId(long cProductId)
		throws PortalException {

		CPDefinition cpDefinition =
			cpDefinitionLocalService.fetchCPDefinitionByCProductId(cProductId);

		if (cpDefinition != null) {
			_checkCommerceCatalogPermissionByCPDefinitionId(
				cpDefinition.getCPDefinitionId(), ActionKeys.VIEW);
		}

		return cpDefinition;
	}

	@Override
	public CPDefinition getCPDefinition(long cpDefinitionId)
		throws PortalException {

		_checkCommerceCatalogPermissionByCPDefinitionId(
			cpDefinitionId, ActionKeys.VIEW);

		return cpDefinitionLocalService.getCPDefinition(cpDefinitionId);
	}

	@Override
	public List<CPDefinition> getCPDefinitions(
			long groupId, int status, int start, int end,
			OrderByComparator<CPDefinition> orderByComparator)
		throws PortalException {

		_checkCommerceCatalogPermission(groupId, ActionKeys.VIEW);

		return cpDefinitionLocalService.getCPDefinitions(
			groupId, status, start, end, orderByComparator);
	}

	@Override
	public int getCPDefinitionsCount(long groupId, int status)
		throws PortalException {

		_checkCommerceCatalogPermission(groupId, ActionKeys.VIEW);

		return cpDefinitionLocalService.getCPDefinitionsCount(groupId, status);
	}

	@Override
	public String getLayoutUuid(long cpDefinitionId) throws PortalException {
		_checkCommerceCatalogPermissionByCPDefinitionId(
			cpDefinitionId, ActionKeys.VIEW);

		return cpDefinitionLocalService.getLayoutUuid(cpDefinitionId);
	}

	@Override
	public String getUrlTitleMapAsXML(long cpDefinitionId)
		throws PortalException {

		_checkCommerceCatalogPermissionByCPDefinitionId(
			cpDefinitionId, ActionKeys.VIEW);

		return cpDefinitionLocalService.getUrlTitleMapAsXML(cpDefinitionId);
	}

	@Override
	public BaseModelSearchResult<CPDefinition> searchCPDefinitions(
			long companyId, String keywords, int status, int start, int end,
			Sort sort)
		throws PortalException {

		List<CommerceCatalog> commerceCatalogs =
			commerceCatalogService.getCommerceCatalogs(
				companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		Stream<CommerceCatalog> stream = commerceCatalogs.stream();

		long[] groupIds = stream.mapToLong(
			CommerceCatalog::getGroupId
		).toArray();

		return cpDefinitionLocalService.searchCPDefinitions(
			companyId, groupIds, keywords, status, start, end, sort);
	}

	@Override
	public BaseModelSearchResult<CPDefinition> searchCPDefinitions(
			long companyId, String keywords, String filterFields,
			String filterValues, int start, int end, Sort sort)
		throws PortalException {

		List<CommerceCatalog> commerceCatalogs =
			commerceCatalogService.getCommerceCatalogs(
				companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		Stream<CommerceCatalog> stream = commerceCatalogs.stream();

		long[] groupIds = stream.mapToLong(
			CommerceCatalog::getGroupId
		).toArray();

		return cpDefinitionLocalService.searchCPDefinitions(
			companyId, groupIds, keywords, filterFields, filterValues, start,
			end, sort);
	}

	@Override
	public CPDefinition updateCPDefinition(
			long cpDefinitionId, Map<Locale, String> nameMap,
			Map<Locale, String> shortDescriptionMap,
			Map<Locale, String> descriptionMap, Map<Locale, String> urlTitleMap,
			Map<Locale, String> metaTitleMap,
			Map<Locale, String> metaDescriptionMap,
			Map<Locale, String> metaKeywordsMap, boolean ignoreSKUCombinations,
			String ddmStructureKey, boolean published, int displayDateMonth,
			int displayDateDay, int displayDateYear, int displayDateHour,
			int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, ServiceContext serviceContext)
		throws PortalException {

		_checkCommerceCatalogPermissionByCPDefinitionId(
			cpDefinitionId, ActionKeys.UPDATE);

		return cpDefinitionLocalService.updateCPDefinition(
			cpDefinitionId, nameMap, shortDescriptionMap, descriptionMap,
			urlTitleMap, metaTitleMap, metaDescriptionMap, metaKeywordsMap,
			ignoreSKUCombinations, ddmStructureKey, published, displayDateMonth,
			displayDateDay, displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, neverExpire,
			serviceContext);
	}

	@Override
	public CPDefinition updateCPDefinitionCategorization(
			long cpDefinitionId, ServiceContext serviceContext)
		throws PortalException {

		_checkCommerceCatalogPermissionByCPDefinitionId(
			cpDefinitionId, ActionKeys.UPDATE);

		return cpDefinitionLocalService.updateCPDefinitionCategorization(
			cpDefinitionId, serviceContext);
	}

	@Override
	public void updateCPDisplayLayout(
			long cpDefinitionId, String layoutUuid,
			ServiceContext serviceContext)
		throws PortalException {

		_checkCommerceCatalogPermissionByCPDefinitionId(
			cpDefinitionId, ActionKeys.UPDATE);

		cpDisplayLayoutLocalService.addCPDisplayLayout(
			CPDefinition.class, cpDefinitionId, layoutUuid, serviceContext);
	}

	@Override
	public CPDefinition updateShippingInfo(
			long cpDefinitionId, boolean shippable, boolean freeShipping,
			boolean shipSeparately, double shippingExtraPrice, double width,
			double height, double depth, double weight,
			ServiceContext serviceContext)
		throws PortalException {

		_checkCommerceCatalogPermissionByCPDefinitionId(
			cpDefinitionId, ActionKeys.UPDATE);

		return cpDefinitionLocalService.updateShippingInfo(
			cpDefinitionId, shippable, freeShipping, shipSeparately,
			shippingExtraPrice, width, height, depth, weight, serviceContext);
	}

	public CPDefinition updateStatus(
			long userId, long cpDefinitionId, int status,
			ServiceContext serviceContext,
			Map<String, Serializable> workflowContext)
		throws PortalException {

		_checkCommerceCatalogPermissionByCPDefinitionId(
			cpDefinitionId, ActionKeys.UPDATE);

		return cpDefinitionLocalService.updateStatus(
			userId, cpDefinitionId, status, serviceContext, workflowContext);
	}

	@Override
	public CPDefinition updateSubscriptionInfo(
			long cpDefinitionId, boolean subscriptionEnabled,
			int subscriptionLength, String subscriptionType,
			UnicodeProperties subscriptionTypeSettingsProperties,
			long maxSubscriptionCycles, ServiceContext serviceContext)
		throws PortalException {

		_checkCommerceCatalogPermissionByCPDefinitionId(
			cpDefinitionId, ActionKeys.UPDATE);

		return cpDefinitionLocalService.updateSubscriptionInfo(
			cpDefinitionId, subscriptionEnabled, subscriptionLength,
			subscriptionType, subscriptionTypeSettingsProperties,
			maxSubscriptionCycles, serviceContext);
	}

	@Override
	public CPDefinition updateTaxCategoryInfo(
			long cpDefinitionId, long cpTaxCategoryId, boolean taxExempt,
			boolean telcoOrElectronics)
		throws PortalException {

		_checkCommerceCatalogPermissionByCPDefinitionId(
			cpDefinitionId, ActionKeys.UPDATE);

		return cpDefinitionLocalService.updateTaxCategoryInfo(
			cpDefinitionId, cpTaxCategoryId, taxExempt, telcoOrElectronics);
	}

	@Override
	public CPDefinition upsertCPDefinition(
			long groupId, long userId, Map<Locale, String> nameMap,
			Map<Locale, String> shortDescriptionMap,
			Map<Locale, String> descriptionMap, Map<Locale, String> urlTitleMap,
			Map<Locale, String> metaTitleMap,
			Map<Locale, String> metaDescriptionMap,
			Map<Locale, String> metaKeywordsMap, String productTypeName,
			boolean ignoreSKUCombinations, boolean shippable,
			boolean freeShipping, boolean shipSeparately,
			double shippingExtraPrice, double width, double height,
			double depth, double weight, long cpTaxCategoryId,
			boolean taxExempt, boolean telcoOrElectronics,
			String ddmStructureKey, boolean published, int displayDateMonth,
			int displayDateDay, int displayDateYear, int displayDateHour,
			int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, String defaultSku, boolean subscriptionEnabled,
			int subscriptionLength, String subscriptionType,
			UnicodeProperties subscriptionTypeSettingsProperties,
			long maxSubscriptionCycles, String externalReferenceCode,
			ServiceContext serviceContext)
		throws PortalException {

		_checkCommerceCatalogPermission(groupId, ActionKeys.UPDATE);

		return cpDefinitionLocalService.upsertCPDefinition(
			groupId, userId, nameMap, shortDescriptionMap, descriptionMap,
			urlTitleMap, metaTitleMap, metaDescriptionMap, metaKeywordsMap,
			productTypeName, ignoreSKUCombinations, shippable, freeShipping,
			shipSeparately, shippingExtraPrice, width, height, depth, weight,
			cpTaxCategoryId, taxExempt, telcoOrElectronics, ddmStructureKey,
			published, displayDateMonth, displayDateDay, displayDateYear,
			displayDateHour, displayDateMinute, expirationDateMonth,
			expirationDateDay, expirationDateYear, expirationDateHour,
			expirationDateMinute, neverExpire, defaultSku, subscriptionEnabled,
			subscriptionLength, subscriptionType,
			subscriptionTypeSettingsProperties, maxSubscriptionCycles,
			externalReferenceCode, serviceContext);
	}

	private void _checkCommerceCatalogPermission(long groupId, String actionId)
		throws PortalException {

		CommerceCatalog commerceCatalog =
			commerceCatalogLocalService.fetchCommerceCatalogByGroupId(groupId);

		_commerceCatalogModelResourcePermission.check(
			getPermissionChecker(), commerceCatalog, actionId);
	}

	private void _checkCommerceCatalogPermissionByCPDefinitionId(
			long cpDefinitionId, String actionId)
		throws PortalException {

		CPDefinition cpDefinition = cpDefinitionLocalService.fetchCPDefinition(
			cpDefinitionId);

		if (cpDefinition == null) {
			throw new NoSuchCPDefinitionException();
		}

		CommerceCatalog commerceCatalog =
			commerceCatalogLocalService.fetchCommerceCatalogByGroupId(
				cpDefinition.getGroupId());

		_commerceCatalogModelResourcePermission.check(
			getPermissionChecker(), commerceCatalog, actionId);
	}

	private static volatile ModelResourcePermission<CommerceCatalog>
		_commerceCatalogModelResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				CPDefinitionServiceImpl.class,
				"_commerceCatalogModelResourcePermission",
				CommerceCatalog.class);

}