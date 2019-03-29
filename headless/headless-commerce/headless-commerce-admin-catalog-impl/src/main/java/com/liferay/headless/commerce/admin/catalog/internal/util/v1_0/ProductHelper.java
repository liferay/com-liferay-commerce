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

package com.liferay.headless.commerce.admin.catalog.internal.util.v1_0;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryService;
import com.liferay.commerce.constants.CPDefinitionInventoryConstants;
import com.liferay.commerce.model.CPDefinitionInventory;
import com.liferay.commerce.product.exception.NoSuchCPDefinitionException;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.service.CPDefinitionService;
import com.liferay.commerce.service.CPDefinitionInventoryService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Category;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Product;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductConfiguration;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductShippingConfiguration;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductSubscriptionConfiguration;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductTaxConfiguration;
import com.liferay.headless.commerce.admin.catalog.internal.mapper.v1_0.DTOMapper;
import com.liferay.headless.commerce.admin.catalog.internal.util.BaseHelper;
import com.liferay.headless.commerce.core.util.DateConfig;
import com.liferay.headless.commerce.core.util.IdUtils;
import com.liferay.headless.commerce.core.util.LanguageUtils;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = ProductHelper.class)
public class ProductHelper extends BaseHelper {

	public void deleteProduct(String id, Company company)
		throws PortalException {

		CPDefinition cpDefinition = getProductById(id, company);

		_cpDefinitionService.deleteCPDefinition(
			cpDefinition.getCPDefinitionId());
	}

	public double getDepth(
		CPDefinition cpDefinition,
		ProductShippingConfiguration productShippingConfiguration) {

		BigDecimal depth = productShippingConfiguration.getDepth();

		if (depth == null) {
			return cpDefinition.getDepth();
		}

		return depth.doubleValue();
	}

	public double getHeight(
		CPDefinition cpDefinition,
		ProductShippingConfiguration productShippingConfiguration) {

		BigDecimal height = productShippingConfiguration.getHeight();

		if (height == null) {
			return cpDefinition.getHeight();
		}

		return height.doubleValue();
	}

	public Product getProduct(
			String id, Company company, AcceptLanguage acceptLanguage)
		throws PortalException {

		return _dtoMapper.modelToDTO(
			getProductById(id, company),
			acceptLanguage.getPreferredLanguageId());
	}

	public CPDefinition getProductById(String id, Company company)
		throws PortalException {

		CPDefinition cpDefinition;

		if (IdUtils.isLocalPK(id)) {
			cpDefinition = _cpDefinitionService.getCPDefinition(
				GetterUtil.getLong(id));
		}
		else {

			// Get Product by External Reference Code

			String erc = IdUtils.getExternalReferenceCodeFromId(id);

			cpDefinition = _cpDefinitionService.fetchByExternalReferenceCode(
				company.getCompanyId(), erc);
		}

		if (cpDefinition == null) {
			throw new NoSuchCPDefinitionException(
				"Unable to find Product with ID: " + id);
		}

		return cpDefinition;
	}

	public Page<Category> getProductCategories(
			String productId, Company company, Pagination pagination)
		throws PortalException {

		CPDefinition cpDefinition = getProductById(productId, company);

		List<AssetCategory> assetCategories =
			_assetCategoryService.getCategories(
				_classNameLocalService.getClassNameId(
					cpDefinition.getModelClass()),
				cpDefinition.getCPDefinitionId(), pagination.getStartPosition(),
				pagination.getEndPosition());

		int totalItems = _assetCategoryService.getCategoriesCount(
			_classNameLocalService.getClassNameId(cpDefinition.getModelClass()),
			cpDefinition.getCPDefinitionId());

		List<Category> categories = new ArrayList<>();

		for (AssetCategory category : assetCategories) {
			categories.add(_dtoMapper.modelToDTO(category));
		}

		return Page.of(categories, pagination, totalItems);
	}

	public ProductConfiguration getProductConfiguration(
			String productId, Company company)
		throws PortalException {

		CPDefinition cpDefinition = getProductById(productId, company);

		CPDefinitionInventory cpDefinitionInventory =
			_cpDefinitionInventoryService.
				fetchCPDefinitionInventoryByCPDefinitionId(
					cpDefinition.getCPDefinitionId());

		return _dtoMapper.modelToProductConfigurationDTO(cpDefinitionInventory);
	}

	public Page<Product> getProducts(
			long groupId, AcceptLanguage acceptLanguage, Pagination pagination)
		throws PortalException {

		List<CPDefinition> cpDefinitions =
			_cpDefinitionService.getCPDefinitions(
				groupId, WorkflowConstants.STATUS_APPROVED,
				pagination.getStartPosition(), pagination.getEndPosition(),
				null);

		int totalItems = _cpDefinitionService.getCPDefinitionsCount(
			groupId, WorkflowConstants.STATUS_APPROVED);

		List<Product> products = new ArrayList<>();

		for (CPDefinition cpDefinition : cpDefinitions) {
			products.add(
				_dtoMapper.modelToDTO(
					cpDefinition, acceptLanguage.getPreferredLanguageId()));
		}

		return Page.of(products, pagination, totalItems);
	}

	public ProductShippingConfiguration getProductShippingConfiguration(
			String productId, Company company)
		throws PortalException {

		CPDefinition cpDefinition = getProductById(productId, company);

		return _dtoMapper.modelToProductShippingConfigurationDTO(cpDefinition);
	}

	public ProductSubscriptionConfiguration getProductSubscriptionConfiguration(
			String productId, Company company)
		throws PortalException {

		CPDefinition cpDefinition = getProductById(productId, company);

		return _dtoMapper.modelToProductSubscriptionConfigurationDTO(
			cpDefinition);
	}

	public ProductTaxConfiguration getProductTaxConfiguration(
			String productId, Company company, AcceptLanguage acceptLanguage)
		throws PortalException {

		CPDefinition cpDefinition = getProductById(productId, company);

		return _dtoMapper.modelToProductTaxConfigurationDTO(
			cpDefinition, acceptLanguage.getPreferredLanguageId());
	}

	public double getShippingExtraPrice(
		CPDefinition cpDefinition,
		ProductShippingConfiguration productShippingConfiguration) {

		BigDecimal shippingExtraPrice =
			productShippingConfiguration.getShippingExtraPrice();

		if (shippingExtraPrice == null) {
			return cpDefinition.getShippingExtraPrice();
		}

		return shippingExtraPrice.doubleValue();
	}

	public double getWeight(
		CPDefinition cpDefinition,
		ProductShippingConfiguration productShippingConfiguration) {

		BigDecimal weight = productShippingConfiguration.getWeight();

		if (weight == null) {
			return cpDefinition.getWeight();
		}

		return weight.doubleValue();
	}

	public double getWidth(
		CPDefinition cpDefinition,
		ProductShippingConfiguration productShippingConfiguration) {

		BigDecimal width = productShippingConfiguration.getWidth();

		if (width == null) {
			return cpDefinition.getWidth();
		}

		return width.doubleValue();
	}

	public Product updateProduct(
			String id, Product product, Company company,
			AcceptLanguage acceptLanguage)
		throws PortalException {

		Map<String, ?> expando = Collections.emptyMap();

		if (product.getExpando() != null) {
			expando = product.getExpando();
		}

		return _dtoMapper.modelToDTO(
			_updateProduct(
				id, company, product.getDescription(),
				product.getShortDescription(), product.getName(), expando),
			acceptLanguage.getPreferredLanguageId());
	}

	public Product updateProductCategories(
			String productId, Company company, Category[] categories,
			AcceptLanguage acceptLanguage)
		throws PortalException {

		CPDefinition cpDefinition = getProductById(productId, company);

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			cpDefinition.getGroupId());

		// TODO upsert categories

		cpDefinition = _cpDefinitionService.updateCPDefinitionCategorization(
			cpDefinition.getCPDefinitionId(), serviceContext);

		return _dtoMapper.modelToDTO(
			cpDefinition, acceptLanguage.getPreferredLanguageId());
	}

	public ProductConfiguration updateProductConfiguration(
			String productId, Company company,
			ProductConfiguration productConfiguration)
		throws PortalException {

		CPDefinition cpDefinition = getProductById(productId, company);

		CPDefinitionInventory cpDefinitionInventory =
			_cpDefinitionInventoryService.
				fetchCPDefinitionInventoryByCPDefinitionId(
					cpDefinition.getCPDefinitionId());

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			cpDefinitionInventory.getGroupId());

		if (cpDefinitionInventory == null) {
			cpDefinitionInventory =
				_cpDefinitionInventoryService.addCPDefinitionInventory(
					cpDefinition.getCPDefinitionId(),
					productConfiguration.getInventoryEngine(),
					productConfiguration.getLowStockAction(),
					GetterUtil.get(
						productConfiguration.getDisplayAvailability(), false),
					GetterUtil.get(
						productConfiguration.getDisplayStockQuantity(), false),
					GetterUtil.get(
						productConfiguration.getMinStockQuantity(), 0),
					GetterUtil.get(
						productConfiguration.getAllowBackOrder(), false),
					GetterUtil.get(
						productConfiguration.getMinOrderQuantity(),
						CPDefinitionInventoryConstants.
							DEFAULT_MIN_ORDER_QUANTITY),
					GetterUtil.get(
						productConfiguration.getMaxOrderQuantity(),
						CPDefinitionInventoryConstants.
							DEFAULT_MAX_ORDER_QUANTITY),
					_getAllowedOrderQuantities(
						cpDefinitionInventory, productConfiguration),
					GetterUtil.get(
						productConfiguration.getMultipleOrderQuantity(),
						CPDefinitionInventoryConstants.
							DEFAULT_MULTIPLE_ORDER_QUANTITY),
					serviceContext);
		}
		else {
			cpDefinitionInventory =
				_cpDefinitionInventoryService.updateCPDefinitionInventory(
					cpDefinitionInventory.getCPDefinitionInventoryId(),
					GetterUtil.get(
						productConfiguration.getInventoryEngine(),
						cpDefinitionInventory.getCPDefinitionInventoryEngine()),
					GetterUtil.get(
						productConfiguration.getInventoryEngine(),
						cpDefinitionInventory.getCPDefinitionInventoryEngine()),
					GetterUtil.get(
						productConfiguration.getDisplayAvailability(),
						cpDefinitionInventory.isDisplayAvailability()),
					GetterUtil.get(
						productConfiguration.getDisplayStockQuantity(),
						cpDefinitionInventory.isDisplayStockQuantity()),
					GetterUtil.get(
						productConfiguration.getMinStockQuantity(),
						cpDefinitionInventory.getMinStockQuantity()),
					GetterUtil.get(
						productConfiguration.getAllowBackOrder(),
						cpDefinitionInventory.isBackOrders()),
					GetterUtil.get(
						productConfiguration.getMinOrderQuantity(),
						cpDefinitionInventory.getMinOrderQuantity()),
					GetterUtil.get(
						productConfiguration.getMaxOrderQuantity(),
						cpDefinitionInventory.getMaxOrderQuantity()),
					_getAllowedOrderQuantities(
						cpDefinitionInventory, productConfiguration),
					GetterUtil.get(
						productConfiguration.getMultipleOrderQuantity(),
						cpDefinitionInventory.getMultipleOrderQuantity()),
					serviceContext);
		}

		return _dtoMapper.modelToProductConfigurationDTO(cpDefinitionInventory);
	}

	public ProductShippingConfiguration updateProductShippingConfiguration(
			String productId, Company company,
			ProductShippingConfiguration productShippingConfiguration)
		throws PortalException {

		CPDefinition cpDefinition = getProductById(productId, company);

		cpDefinition = _cpDefinitionService.updateShippingInfo(
			cpDefinition.getCPDefinitionId(),
			GetterUtil.get(
				productShippingConfiguration.getShippable(),
				cpDefinition.isShippable()),
			GetterUtil.get(
				productShippingConfiguration.getFreeShipping(),
				cpDefinition.isFreeShipping()),
			GetterUtil.get(
				productShippingConfiguration.getShippingSeparately(),
				cpDefinition.isShipSeparately()),
			getShippingExtraPrice(cpDefinition, productShippingConfiguration),
			getWidth(cpDefinition, productShippingConfiguration),
			getHeight(cpDefinition, productShippingConfiguration),
			getDepth(cpDefinition, productShippingConfiguration),
			getWeight(cpDefinition, productShippingConfiguration),
			_serviceContextHelper.getServiceContext(cpDefinition.getGroupId()));

		return _dtoMapper.modelToProductShippingConfigurationDTO(cpDefinition);
	}

	public ProductSubscriptionConfiguration
			updateProductSubscriptionConfiguration(
				String productId, Company company,
				ProductSubscriptionConfiguration
					productSubscriptionConfiguration)
		throws PortalException {

		CPDefinition cpDefinition = getProductById(productId, company);

		ProductSubscriptionConfiguration.SubscriptionType subscriptionType =
			productSubscriptionConfiguration.getSubscriptionType();

		cpDefinition = _cpDefinitionService.updateSubscriptionInfo(
			cpDefinition.getCPDefinitionId(),
			GetterUtil.get(
				productSubscriptionConfiguration.getEnable(),
				cpDefinition.isSubscriptionEnabled()),
			GetterUtil.get(
				productSubscriptionConfiguration.getLength(),
				cpDefinition.getSubscriptionLength()),
			subscriptionType.getValue(), null,
			GetterUtil.get(
				productSubscriptionConfiguration.getNumberOfLength(),
				cpDefinition.getMaxSubscriptionCycles()),
			_serviceContextHelper.getServiceContext(cpDefinition.getGroupId()));

		return _dtoMapper.modelToProductSubscriptionConfigurationDTO(
			cpDefinition);
	}

	public ProductTaxConfiguration updateProductTaxConfiguration(
			String productId, Company company,
			ProductTaxConfiguration productTaxConfiguration,
			AcceptLanguage acceptLanguage)
		throws PortalException {

		CPDefinition cpDefinition = getProductById(productId, company);

		cpDefinition = _cpDefinitionService.updateTaxCategoryInfo(
			cpDefinition.getCPDefinitionId(), productTaxConfiguration.getId(),
			_isTaxable(cpDefinition, productTaxConfiguration), false);

		return _dtoMapper.modelToProductTaxConfigurationDTO(
			cpDefinition, acceptLanguage.getPreferredLanguageId());
	}

	public Product upsertProduct(
			long groupId, Product product, AcceptLanguage acceptLanguage,
			User user)
		throws PortalException {

		return _dtoMapper.modelToDTO(
			_upsertProduct(
				groupId, product.getActive(), product.getDefaultSku(),
				product.getDescription(), product.getExternalReferenceCode(),
				product.getProductType(), product.getShortDescription(),
				product.getName(), user, product.getExpando()),
			acceptLanguage.getPreferredLanguageId());
	}

	private String _getAllowedOrderQuantities(
		CPDefinitionInventory cpDefinitionInventory,
		ProductConfiguration productConfiguration) {

		if (productConfiguration.getAllowedOrderQuantities() != null) {
			return StringUtil.merge(
				productConfiguration.getAllowedOrderQuantities());
		}

		if (cpDefinitionInventory == null) {
			return null;
		}

		return cpDefinitionInventory.getAllowedOrderQuantities();
	}

	private boolean _isTaxable(
		CPDefinition cpDefinition,
		ProductTaxConfiguration productTaxConfiguration) {

		if (productTaxConfiguration.getTaxable() != null) {
			return productTaxConfiguration.getTaxable();
		}

		if (cpDefinition != null) {
			return !cpDefinition.isTaxExempt();
		}

		return false;
	}

	private CPDefinition _updateProduct(
			String id, Company company, Map<String, String> description,
			Map<String, String> shortDescription, Map<String, String> name,
			Map<String, ?> expando)
		throws PortalException {

		CPDefinition cpDefinition = getProductById(id, company);

		long groupId = cpDefinition.getGroupId();

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			groupId);

		Calendar displayCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		DateConfig displayDateConfig = new DateConfig(displayCalendar);

		Calendar expirationCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		expirationCalendar.add(Calendar.MONTH, 1);

		DateConfig expirationDateConfig = new DateConfig(expirationCalendar);

		boolean neverExpire = Boolean.TRUE;

		cpDefinition = _cpDefinitionService.updateCPDefinition(
			cpDefinition.getCPDefinitionId(),
			LanguageUtils.getLocalizedMap(name),
			LanguageUtils.getLocalizedMap(shortDescription),
			LanguageUtils.getLocalizedMap(description),
			cpDefinition.getUrlTitleMap(), cpDefinition.getMetaTitleMap(),
			cpDefinition.getMetaDescriptionMap(),
			cpDefinition.getMetaKeywordsMap(),
			cpDefinition.isIgnoreSKUCombinations(),
			cpDefinition.getDDMStructureKey(), true,
			displayDateConfig.getMonth(), displayDateConfig.getDay(),
			displayDateConfig.getYear(), displayDateConfig.getHour(),
			displayDateConfig.getMinute(), expirationDateConfig.getMonth(),
			expirationDateConfig.getDay(), expirationDateConfig.getYear(),
			expirationDateConfig.getHour(), expirationDateConfig.getMinute(),
			neverExpire, serviceContext);

		if (!expando.isEmpty()) {
			updateExpando(
				serviceContext.getCompanyId(), CPDefinition.class,
				cpDefinition.getPrimaryKey(), expando);
		}

		return cpDefinition;
	}

	private CPDefinition _upsertProduct(
			Long groupId, boolean active, String defaultSku,
			Map<String, String> description, String externalReferenceCode,
			String productTypeName, Map<String, String> shortDescription,
			Map<String, String> name, User currentUser, Map<String, ?> expando)
		throws PortalException {

		boolean neverExpire = Boolean.TRUE;

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			groupId);

		Calendar displayCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		DateConfig displayDateConfig = new DateConfig(displayCalendar);

		Calendar expirationCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		expirationCalendar.add(Calendar.MONTH, 1);

		DateConfig expirationDateConfig = new DateConfig(expirationCalendar);

		String ddmStructureKey = null;

		CPDefinition cpDefinition = _cpDefinitionService.upsertCPDefinition(
			LanguageUtils.getLocalizedMap(name),
			LanguageUtils.getLocalizedMap(shortDescription),
			LanguageUtils.getLocalizedMap(description), null,
			LanguageUtils.getLocalizedMap(name), null, null, productTypeName,
			true, true, true, true, 0.0, 0.0, 0.0, 0.0, 0.0, 0L, false, false,
			ddmStructureKey, true, displayDateConfig.getMonth(),
			displayDateConfig.getDay(), displayDateConfig.getYear(),
			displayDateConfig.getHour(), displayDateConfig.getMinute(),
			expirationDateConfig.getMonth(), expirationDateConfig.getDay(),
			expirationDateConfig.getYear(), expirationDateConfig.getHour(),
			expirationDateConfig.getMinute(), neverExpire, defaultSku,
			externalReferenceCode, serviceContext);

		if (!active) {
			Map<String, Serializable> workflowContext = new HashMap<>();

			_cpDefinitionService.updateStatus(
				currentUser.getUserId(), cpDefinition.getCPDefinitionId(),
				WorkflowConstants.STATUS_INACTIVE, serviceContext,
				workflowContext);
		}

		if (!expando.isEmpty()) {
			updateExpando(
				serviceContext.getCompanyId(), CPDefinition.class,
				cpDefinition.getPrimaryKey(), expando);
		}

		return cpDefinition;
	}

	@Reference
	private AssetCategoryService _assetCategoryService;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private CPDefinitionInventoryService _cpDefinitionInventoryService;

	@Reference
	private CPDefinitionService _cpDefinitionService;

	@Reference
	private DTOMapper _dtoMapper;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}