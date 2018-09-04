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

package com.liferay.commerce.product.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for CPDefinition. This utility wraps
 * {@link com.liferay.commerce.product.service.impl.CPDefinitionServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Marco Leo
 * @see CPDefinitionService
 * @see com.liferay.commerce.product.service.base.CPDefinitionServiceBaseImpl
 * @see com.liferay.commerce.product.service.impl.CPDefinitionServiceImpl
 * @generated
 */
@ProviderType
public class CPDefinitionServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.commerce.product.service.impl.CPDefinitionServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.commerce.product.model.CPDefinition addCPDefinition(
		java.util.Map<java.util.Locale, String> nameMap,
		java.util.Map<java.util.Locale, String> shortDescriptionMap,
		java.util.Map<java.util.Locale, String> descriptionMap,
		java.util.Map<java.util.Locale, String> urlTitleMap,
		java.util.Map<java.util.Locale, String> metaTitleMap,
		java.util.Map<java.util.Locale, String> metaDescriptionMap,
		java.util.Map<java.util.Locale, String> metaKeywordsMap,
		String productTypeName, boolean ignoreSKUCombinations,
		boolean shippable, boolean freeShipping, boolean shipSeparately,
		double shippingExtraPrice, double width, double height, double depth,
		double weight, long cpTaxCategoryId, boolean taxExempt,
		boolean telcoOrElectronics, String ddmStructureKey, boolean published,
		int displayDateMonth, int displayDateDay, int displayDateYear,
		int displayDateHour, int displayDateMinute, int expirationDateMonth,
		int expirationDateDay, int expirationDateYear, int expirationDateHour,
		int expirationDateMinute, boolean neverExpire,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCPDefinition(nameMap, shortDescriptionMap,
			descriptionMap, urlTitleMap, metaTitleMap, metaDescriptionMap,
			metaKeywordsMap, productTypeName, ignoreSKUCombinations, shippable,
			freeShipping, shipSeparately, shippingExtraPrice, width, height,
			depth, weight, cpTaxCategoryId, taxExempt, telcoOrElectronics,
			ddmStructureKey, published, displayDateMonth, displayDateDay,
			displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, neverExpire,
			serviceContext);
	}

	public static com.liferay.commerce.product.model.CPDefinition addCPDefinition(
		java.util.Map<java.util.Locale, String> nameMap,
		java.util.Map<java.util.Locale, String> shortDescriptionMap,
		java.util.Map<java.util.Locale, String> descriptionMap,
		java.util.Map<java.util.Locale, String> urlTitleMap,
		java.util.Map<java.util.Locale, String> metaTitleMap,
		java.util.Map<java.util.Locale, String> metaDescriptionMap,
		java.util.Map<java.util.Locale, String> metaKeywordsMap,
		String productTypeName, boolean ignoreSKUCombinations,
		String ddmStructureKey, boolean published, int displayDateMonth,
		int displayDateDay, int displayDateYear, int displayDateHour,
		int displayDateMinute, int expirationDateMonth, int expirationDateDay,
		int expirationDateYear, int expirationDateHour,
		int expirationDateMinute, boolean neverExpire,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCPDefinition(nameMap, shortDescriptionMap,
			descriptionMap, urlTitleMap, metaTitleMap, metaDescriptionMap,
			metaKeywordsMap, productTypeName, ignoreSKUCombinations,
			ddmStructureKey, published, displayDateMonth, displayDateDay,
			displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, neverExpire,
			serviceContext);
	}

	public static void deleteAssetCategoryCPDefinition(long cpDefinitionId,
		long categoryId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.deleteAssetCategoryCPDefinition(cpDefinitionId, categoryId,
			serviceContext);
	}

	public static void deleteCPDefinition(long cpDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteCPDefinition(cpDefinitionId);
	}

	public static com.liferay.commerce.product.model.CPDefinition fetchCPDefinition(
		long cpDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().fetchCPDefinition(cpDefinitionId);
	}

	public static com.liferay.commerce.product.model.CPDefinition getCPDefinition(
		long cpDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCPDefinition(cpDefinitionId);
	}

	public static java.util.List<com.liferay.commerce.product.model.CPDefinition> getCPDefinitions(
		long groupId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCPDefinitions(groupId, status, start, end);
	}

	public static java.util.List<com.liferay.commerce.product.model.CPDefinition> getCPDefinitions(
		long groupId, String productTypeName, String languageId, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.product.model.CPDefinition> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getCPDefinitions(groupId, productTypeName, languageId,
			status, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.commerce.product.model.CPDefinition> getCPDefinitionsByCategoryId(
		long categoryId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCPDefinitionsByCategoryId(categoryId, start, end);
	}

	public static int getCPDefinitionsCount(long groupId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCPDefinitionsCount(groupId, status);
	}

	public static int getCPDefinitionsCount(long groupId,
		String productTypeName, String languageId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getCPDefinitionsCount(groupId, productTypeName, languageId,
			status);
	}

	public static int getCPDefinitionsCountByCategoryId(long categoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCPDefinitionsCountByCategoryId(categoryId);
	}

	public static String getLayoutUuid(long cpDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLayoutUuid(cpDefinitionId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static String getUrlTitleMapAsXML(long cpDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getUrlTitleMapAsXML(cpDefinitionId);
	}

	public static com.liferay.commerce.product.model.CPDefinition moveCPDefinitionToTrash(
		long cpDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().moveCPDefinitionToTrash(cpDefinitionId);
	}

	public static void restoreCPDefinitionFromTrash(long cpDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().restoreCPDefinitionFromTrash(cpDefinitionId);
	}

	public static com.liferay.portal.kernel.search.BaseModelSearchResult<com.liferay.commerce.product.model.CPDefinition> searchCPDefinitions(
		long companyId, long groupId, String keywords, int status, int start,
		int end, com.liferay.portal.kernel.search.Sort sort)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .searchCPDefinitions(companyId, groupId, keywords, status,
			start, end, sort);
	}

	public static com.liferay.portal.kernel.search.BaseModelSearchResult<com.liferay.commerce.product.model.CPDefinition> searchCPDefinitions(
		long companyId, long groupId, String keywords, String filterFields,
		String filterValues, int start, int end,
		com.liferay.portal.kernel.search.Sort sort)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .searchCPDefinitions(companyId, groupId, keywords,
			filterFields, filterValues, start, end, sort);
	}

	public static com.liferay.commerce.product.model.CPDefinition updateCPDefinition(
		long cpDefinitionId, java.util.Map<java.util.Locale, String> nameMap,
		java.util.Map<java.util.Locale, String> shortDescriptionMap,
		java.util.Map<java.util.Locale, String> descriptionMap,
		java.util.Map<java.util.Locale, String> urlTitleMap,
		java.util.Map<java.util.Locale, String> metaTitleMap,
		java.util.Map<java.util.Locale, String> metaDescriptionMap,
		java.util.Map<java.util.Locale, String> metaKeywordsMap,
		boolean ignoreSKUCombinations, String ddmStructureKey,
		boolean published, int displayDateMonth, int displayDateDay,
		int displayDateYear, int displayDateHour, int displayDateMinute,
		int expirationDateMonth, int expirationDateDay, int expirationDateYear,
		int expirationDateHour, int expirationDateMinute, boolean neverExpire,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCPDefinition(cpDefinitionId, nameMap,
			shortDescriptionMap, descriptionMap, urlTitleMap, metaTitleMap,
			metaDescriptionMap, metaKeywordsMap, ignoreSKUCombinations,
			ddmStructureKey, published, displayDateMonth, displayDateDay,
			displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, neverExpire,
			serviceContext);
	}

	public static com.liferay.commerce.product.model.CPDefinition updateCPDefinitionCategorization(
		long cpDefinitionId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCPDefinitionCategorization(cpDefinitionId,
			serviceContext);
	}

	public static void updateCPDisplayLayout(long cpDefinitionId,
		String layoutUuid,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.updateCPDisplayLayout(cpDefinitionId, layoutUuid, serviceContext);
	}

	public static com.liferay.commerce.product.model.CPDefinition updateShippingInfo(
		long cpDefinitionId, boolean shippable, boolean freeShipping,
		boolean shipSeparately, double shippingExtraPrice, double width,
		double height, double depth, double weight,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateShippingInfo(cpDefinitionId, shippable, freeShipping,
			shipSeparately, shippingExtraPrice, width, height, depth, weight,
			serviceContext);
	}

	public static com.liferay.commerce.product.model.CPDefinition updateTaxCategoryInfo(
		long cpDefinitionId, long cpTaxCategoryId, boolean taxExempt,
		boolean telcoOrElectronics)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateTaxCategoryInfo(cpDefinitionId, cpTaxCategoryId,
			taxExempt, telcoOrElectronics);
	}

	public static com.liferay.commerce.product.model.CPDefinition upsertCPDefinition(
		java.util.Map<java.util.Locale, String> nameMap,
		java.util.Map<java.util.Locale, String> shortDescriptionMap,
		java.util.Map<java.util.Locale, String> descriptionMap,
		java.util.Map<java.util.Locale, String> urlTitleMap,
		java.util.Map<java.util.Locale, String> metaTitleMap,
		java.util.Map<java.util.Locale, String> metaDescriptionMap,
		java.util.Map<java.util.Locale, String> metaKeywordsMap,
		String productTypeName, boolean ignoreSKUCombinations,
		boolean shippable, boolean freeShipping, boolean shipSeparately,
		double shippingExtraPrice, double width, double height, double depth,
		double weight, long cpTaxCategoryId, boolean taxExempt,
		boolean telcoOrElectronics, String ddmStructureKey, boolean published,
		int displayDateMonth, int displayDateDay, int displayDateYear,
		int displayDateHour, int displayDateMinute, int expirationDateMonth,
		int expirationDateDay, int expirationDateYear, int expirationDateHour,
		int expirationDateMinute, boolean neverExpire, String defaultSKU,
		String externalReferenceCode,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .upsertCPDefinition(nameMap, shortDescriptionMap,
			descriptionMap, urlTitleMap, metaTitleMap, metaDescriptionMap,
			metaKeywordsMap, productTypeName, ignoreSKUCombinations, shippable,
			freeShipping, shipSeparately, shippingExtraPrice, width, height,
			depth, weight, cpTaxCategoryId, taxExempt, telcoOrElectronics,
			ddmStructureKey, published, displayDateMonth, displayDateDay,
			displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, neverExpire, defaultSKU,
			externalReferenceCode, serviceContext);
	}

	public static CPDefinitionService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CPDefinitionService, CPDefinitionService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CPDefinitionService.class);

		ServiceTracker<CPDefinitionService, CPDefinitionService> serviceTracker = new ServiceTracker<CPDefinitionService, CPDefinitionService>(bundle.getBundleContext(),
				CPDefinitionService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}