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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CPDefinitionService}.
 *
 * @author Marco Leo
 * @see CPDefinitionService
 * @generated
 */
public class CPDefinitionServiceWrapper
	implements CPDefinitionService, ServiceWrapper<CPDefinitionService> {

	public CPDefinitionServiceWrapper(CPDefinitionService cpDefinitionService) {
		_cpDefinitionService = cpDefinitionService;
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CPDefinitionServiceUtil} to access the cp definition remote service. Add custom service methods to <code>com.liferay.commerce.product.service.impl.CPDefinitionServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Override
	public com.liferay.commerce.product.model.CPDefinition addCPDefinition(
			long groupId, long userId,
			java.util.Map<java.util.Locale, String> nameMap,
			java.util.Map<java.util.Locale, String> shortDescriptionMap,
			java.util.Map<java.util.Locale, String> descriptionMap,
			java.util.Map<java.util.Locale, String> urlTitleMap,
			java.util.Map<java.util.Locale, String> metaTitleMap,
			java.util.Map<java.util.Locale, String> metaDescriptionMap,
			java.util.Map<java.util.Locale, String> metaKeywordsMap,
			String productTypeName, boolean ignoreSKUCombinations,
			boolean shippable, boolean freeShipping, boolean shipSeparately,
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
			com.liferay.portal.kernel.util.UnicodeProperties
				subscriptionTypeSettingsProperties,
			long maxSubscriptionCycles, String externalReferenceCode,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionService.addCPDefinition(
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
			long cpDefinitionId, long categoryId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		_cpDefinitionService.deleteAssetCategoryCPDefinition(
			cpDefinitionId, categoryId, serviceContext);
	}

	@Override
	public void deleteCPDefinition(long cpDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_cpDefinitionService.deleteCPDefinition(cpDefinitionId);
	}

	@Override
	public com.liferay.commerce.product.model.CPDefinition fetchCPDefinition(
			long cpDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionService.fetchCPDefinition(cpDefinitionId);
	}

	@Override
	public com.liferay.commerce.product.model.CPDefinition
			fetchCPDefinitionByCProductExternalReferenceCode(
				long companyId, String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionService.
			fetchCPDefinitionByCProductExternalReferenceCode(
				companyId, externalReferenceCode);
	}

	@Override
	public com.liferay.commerce.product.model.CPDefinition
			fetchCPDefinitionByCProductId(long cProductId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionService.fetchCPDefinitionByCProductId(cProductId);
	}

	@Override
	public com.liferay.commerce.product.model.CPDefinition getCPDefinition(
			long cpDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionService.getCPDefinition(cpDefinitionId);
	}

	@Override
	public java.util.List<com.liferay.commerce.product.model.CPDefinition>
			getCPDefinitions(
				long groupId, int status, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.product.model.CPDefinition>
						orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionService.getCPDefinitions(
			groupId, status, start, end, orderByComparator);
	}

	@Override
	public int getCPDefinitionsCount(long groupId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionService.getCPDefinitionsCount(groupId, status);
	}

	@Override
	public String getLayoutUuid(long cpDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionService.getLayoutUuid(cpDefinitionId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _cpDefinitionService.getOSGiServiceIdentifier();
	}

	@Override
	public String getUrlTitleMapAsXML(long cpDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionService.getUrlTitleMapAsXML(cpDefinitionId);
	}

	@Override
	public com.liferay.portal.kernel.search.BaseModelSearchResult
		<com.liferay.commerce.product.model.CPDefinition> searchCPDefinitions(
				long companyId, String keywords, int status, int start, int end,
				com.liferay.portal.kernel.search.Sort sort)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionService.searchCPDefinitions(
			companyId, keywords, status, start, end, sort);
	}

	@Override
	public com.liferay.portal.kernel.search.BaseModelSearchResult
		<com.liferay.commerce.product.model.CPDefinition> searchCPDefinitions(
				long companyId, String keywords, String filterFields,
				String filterValues, int start, int end,
				com.liferay.portal.kernel.search.Sort sort)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionService.searchCPDefinitions(
			companyId, keywords, filterFields, filterValues, start, end, sort);
	}

	@Override
	public com.liferay.commerce.product.model.CPDefinition updateCPDefinition(
			long cpDefinitionId,
			java.util.Map<java.util.Locale, String> nameMap,
			java.util.Map<java.util.Locale, String> shortDescriptionMap,
			java.util.Map<java.util.Locale, String> descriptionMap,
			java.util.Map<java.util.Locale, String> urlTitleMap,
			java.util.Map<java.util.Locale, String> metaTitleMap,
			java.util.Map<java.util.Locale, String> metaDescriptionMap,
			java.util.Map<java.util.Locale, String> metaKeywordsMap,
			boolean ignoreSKUCombinations, String ddmStructureKey,
			boolean published, int displayDateMonth, int displayDateDay,
			int displayDateYear, int displayDateHour, int displayDateMinute,
			int expirationDateMonth, int expirationDateDay,
			int expirationDateYear, int expirationDateHour,
			int expirationDateMinute, boolean neverExpire,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionService.updateCPDefinition(
			cpDefinitionId, nameMap, shortDescriptionMap, descriptionMap,
			urlTitleMap, metaTitleMap, metaDescriptionMap, metaKeywordsMap,
			ignoreSKUCombinations, ddmStructureKey, published, displayDateMonth,
			displayDateDay, displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, neverExpire,
			serviceContext);
	}

	@Override
	public com.liferay.commerce.product.model.CPDefinition
			updateCPDefinitionAccountGroupFilter(
				long cpDefinitionId, boolean enable)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionService.updateCPDefinitionAccountGroupFilter(
			cpDefinitionId, enable);
	}

	@Override
	public com.liferay.commerce.product.model.CPDefinition
			updateCPDefinitionCategorization(
				long cpDefinitionId,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionService.updateCPDefinitionCategorization(
			cpDefinitionId, serviceContext);
	}

	@Override
	public com.liferay.commerce.product.model.CPDefinition
			updateCPDefinitionChannelFilter(long cpDefinitionId, boolean enable)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionService.updateCPDefinitionChannelFilter(
			cpDefinitionId, enable);
	}

	@Override
	public void updateCPDisplayLayout(
			long cpDefinitionId, String layoutUuid,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		_cpDefinitionService.updateCPDisplayLayout(
			cpDefinitionId, layoutUuid, serviceContext);
	}

	@Override
	public com.liferay.commerce.product.model.CPDefinition updateShippingInfo(
			long cpDefinitionId, boolean shippable, boolean freeShipping,
			boolean shipSeparately, double shippingExtraPrice, double width,
			double height, double depth, double weight,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionService.updateShippingInfo(
			cpDefinitionId, shippable, freeShipping, shipSeparately,
			shippingExtraPrice, width, height, depth, weight, serviceContext);
	}

	@Override
	public com.liferay.commerce.product.model.CPDefinition updateStatus(
			long userId, long cpDefinitionId, int status,
			com.liferay.portal.kernel.service.ServiceContext serviceContext,
			java.util.Map<String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionService.updateStatus(
			userId, cpDefinitionId, status, serviceContext, workflowContext);
	}

	@Override
	public com.liferay.commerce.product.model.CPDefinition
			updateSubscriptionInfo(
				long cpDefinitionId, boolean subscriptionEnabled,
				int subscriptionLength, String subscriptionType,
				com.liferay.portal.kernel.util.UnicodeProperties
					subscriptionTypeSettingsProperties,
				long maxSubscriptionCycles,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionService.updateSubscriptionInfo(
			cpDefinitionId, subscriptionEnabled, subscriptionLength,
			subscriptionType, subscriptionTypeSettingsProperties,
			maxSubscriptionCycles, serviceContext);
	}

	@Override
	public com.liferay.commerce.product.model.CPDefinition
			updateTaxCategoryInfo(
				long cpDefinitionId, long cpTaxCategoryId, boolean taxExempt,
				boolean telcoOrElectronics)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionService.updateTaxCategoryInfo(
			cpDefinitionId, cpTaxCategoryId, taxExempt, telcoOrElectronics);
	}

	@Override
	public com.liferay.commerce.product.model.CPDefinition upsertCPDefinition(
			long groupId, long userId,
			java.util.Map<java.util.Locale, String> nameMap,
			java.util.Map<java.util.Locale, String> shortDescriptionMap,
			java.util.Map<java.util.Locale, String> descriptionMap,
			java.util.Map<java.util.Locale, String> urlTitleMap,
			java.util.Map<java.util.Locale, String> metaTitleMap,
			java.util.Map<java.util.Locale, String> metaDescriptionMap,
			java.util.Map<java.util.Locale, String> metaKeywordsMap,
			String productTypeName, boolean ignoreSKUCombinations,
			boolean shippable, boolean freeShipping, boolean shipSeparately,
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
			com.liferay.portal.kernel.util.UnicodeProperties
				subscriptionTypeSettingsProperties,
			long maxSubscriptionCycles, String externalReferenceCode,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionService.upsertCPDefinition(
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
	public CPDefinitionService getWrappedService() {
		return _cpDefinitionService;
	}

	@Override
	public void setWrappedService(CPDefinitionService cpDefinitionService) {
		_cpDefinitionService = cpDefinitionService;
	}

	private CPDefinitionService _cpDefinitionService;

}