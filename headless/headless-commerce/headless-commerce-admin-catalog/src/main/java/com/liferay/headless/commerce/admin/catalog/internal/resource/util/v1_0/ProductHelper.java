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

package com.liferay.headless.commerce.admin.catalog.internal.resource.util.v1_0;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryService;
import com.liferay.commerce.constants.CPDefinitionInventoryConstants;
import com.liferay.commerce.model.CPDefinitionInventory;
import com.liferay.commerce.openapi.core.context.Language;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.commerce.openapi.core.util.IdUtils;
import com.liferay.commerce.openapi.core.util.LanguageUtils;
import com.liferay.commerce.openapi.core.util.ServiceContextHelper;
import com.liferay.commerce.product.exception.NoSuchCPDefinitionException;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.service.CPDefinitionService;
import com.liferay.commerce.service.CPDefinitionInventoryService;
import com.liferay.headless.commerce.admin.catalog.internal.mapper.v1_0.DTOMapper;
import com.liferay.headless.commerce.admin.catalog.internal.resource.util.BaseHelper;
import com.liferay.headless.commerce.admin.catalog.internal.resource.util.DateConfig;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.CategoryDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.ProductConfigurationDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.ProductDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.ProductShippingConfigurationDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.ProductSubscriptionConfigurationDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.ProductTaxConfigurationDTO;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

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
		ProductShippingConfigurationDTO productShippingConfigurationDTO) {

		BigDecimal depth = productShippingConfigurationDTO.getDepth();

		if (depth == null) {
			return cpDefinition.getDepth();
		}

		return depth.doubleValue();
	}

	public double getHeight(
		CPDefinition cpDefinition,
		ProductShippingConfigurationDTO productShippingConfigurationDTO) {

		BigDecimal height = productShippingConfigurationDTO.getHeight();

		if (height == null) {
			return cpDefinition.getHeight();
		}

		return height.doubleValue();
	}

	public ProductDTO getProduct(String id, Company company, Language language)
		throws PortalException {

		return _dtoMapper.modelToDTO(
			getProductById(id, company), language.getLanguageId());
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

	public CollectionDTO<CategoryDTO> getProductCategories(
			String productId, Company company, Pagination pagination)
		throws PortalException {

		CPDefinition cpDefinition = getProductById(productId, company);

		List<AssetCategory> categories = _assetCategoryService.getCategories(
			_classNameLocalService.getClassNameId(cpDefinition.getModelClass()),
			cpDefinition.getCPDefinitionId(), pagination.getStartPosition(),
			pagination.getEndPosition());

		int totalItems = _assetCategoryService.getCategoriesCount(
			_classNameLocalService.getClassNameId(cpDefinition.getModelClass()),
			cpDefinition.getCPDefinitionId());

		List<CategoryDTO> categoryDTOs = new ArrayList<>();

		for (AssetCategory category : categories) {
			categoryDTOs.add(_dtoMapper.modelToDTO(category));
		}

		return new CollectionDTO<>(categoryDTOs, totalItems);
	}

	public ProductConfigurationDTO getProductConfiguration(
			String productId, Company company)
		throws PortalException {

		CPDefinition cpDefinition = getProductById(productId, company);

		CPDefinitionInventory cpDefinitionInventory =
			_cpDefinitionInventoryService.
				fetchCPDefinitionInventoryByCPDefinitionId(
					cpDefinition.getCPDefinitionId());

		return _dtoMapper.modelToProductConfigurationDTO(cpDefinitionInventory);
	}

	public CollectionDTO<ProductDTO> getProducts(
			long groupId, Language language, Pagination pagination)
		throws PortalException {

		List<CPDefinition> cpDefinitions =
			_cpDefinitionService.getCPDefinitions(
				groupId, WorkflowConstants.STATUS_APPROVED,
				pagination.getStartPosition(), pagination.getEndPosition(),
				null);

		int totalItems = _cpDefinitionService.getCPDefinitionsCount(
			groupId, WorkflowConstants.STATUS_APPROVED);

		List<ProductDTO> productDTOs = new ArrayList<>();

		for (CPDefinition cpDefinition : cpDefinitions) {
			productDTOs.add(
				_dtoMapper.modelToDTO(cpDefinition, language.getLanguageId()));
		}

		return new CollectionDTO<>(productDTOs, totalItems);
	}

	public ProductShippingConfigurationDTO getProductShippingConfiguration(
			String productId, Company company)
		throws PortalException {

		CPDefinition cpDefinition = getProductById(productId, company);

		return _dtoMapper.modelToProductShippingConfigurationDTO(cpDefinition);
	}

	public ProductSubscriptionConfigurationDTO
			getProductSubscriptionConfiguration(
				String productId, Company company)
		throws PortalException {

		CPDefinition cpDefinition = getProductById(productId, company);

		return _dtoMapper.modelToProductSubscriptionConfigurationDTO(
			cpDefinition);
	}

	public ProductTaxConfigurationDTO getProductTaxConfiguration(
			String productId, Company company, Language language)
		throws PortalException {

		CPDefinition cpDefinition = getProductById(productId, company);

		return _dtoMapper.modelToProductTaxConfigurationDTO(
			cpDefinition, language.getLanguageId());
	}

	public double getShippingExtraPrice(
		CPDefinition cpDefinition,
		ProductShippingConfigurationDTO productShippingConfigurationDTO) {

		BigDecimal shippingExtraPrice =
			productShippingConfigurationDTO.getShippingExtraPrice();

		if (shippingExtraPrice == null) {
			return cpDefinition.getShippingExtraPrice();
		}

		return shippingExtraPrice.doubleValue();
	}

	public double getWeight(
		CPDefinition cpDefinition,
		ProductShippingConfigurationDTO productShippingConfigurationDTO) {

		BigDecimal weight = productShippingConfigurationDTO.getWeight();

		if (weight == null) {
			return cpDefinition.getWeight();
		}

		return weight.doubleValue();
	}

	public double getWidth(
		CPDefinition cpDefinition,
		ProductShippingConfigurationDTO productShippingConfigurationDTO) {

		BigDecimal width = productShippingConfigurationDTO.getWidth();

		if (width == null) {
			return cpDefinition.getWidth();
		}

		return width.doubleValue();
	}

	public ProductDTO updateProductCategories(
			String productId, Company company, CategoryDTO[] categoryDTOs,
			Language language)
		throws PortalException {

		CPDefinition cpDefinition = getProductById(productId, company);

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			cpDefinition.getGroupId());

		for (CategoryDTO categoryDTO : categoryDTOs) {

			// TODO upsert categories

		}

		cpDefinition = _cpDefinitionService.updateCPDefinitionCategorization(
			cpDefinition.getCPDefinitionId(), serviceContext);

		return _dtoMapper.modelToDTO(cpDefinition, language.getLanguageId());
	}

	public ProductConfigurationDTO updateProductConfiguration(
			String productId, Company company,
			ProductConfigurationDTO productConfigurationDTO)
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
					productConfigurationDTO.getInventoryEngine(),
					productConfigurationDTO.getLowStockAction(),
					GetterUtil.get(
						productConfigurationDTO.isDisplayAvailability(), false),
					GetterUtil.get(
						productConfigurationDTO.isDisplayStockQuantity(),
						false),
					GetterUtil.get(
						productConfigurationDTO.getMinStockQuantity(), 0),
					GetterUtil.get(
						productConfigurationDTO.isAllowBackOrder(), false),
					GetterUtil.get(
						productConfigurationDTO.getMinOrderQuantity(),
						CPDefinitionInventoryConstants.
							DEFAULT_MIN_ORDER_QUANTITY),
					GetterUtil.get(
						productConfigurationDTO.getMaxOrderQuantity(),
						CPDefinitionInventoryConstants.
							DEFAULT_MAX_ORDER_QUANTITY),
					_getAllowedOrderQuantities(
						cpDefinitionInventory, productConfigurationDTO),
					GetterUtil.get(
						productConfigurationDTO.getMultipleOrderQuantity(),
						CPDefinitionInventoryConstants.
							DEFAULT_MULTIPLE_ORDER_QUANTITY),
					serviceContext);
		}
		else {
			cpDefinitionInventory =
				_cpDefinitionInventoryService.updateCPDefinitionInventory(
					cpDefinitionInventory.getCPDefinitionInventoryId(),
					GetterUtil.get(
						productConfigurationDTO.getInventoryEngine(),
						cpDefinitionInventory.getCPDefinitionInventoryEngine()),
					GetterUtil.get(
						productConfigurationDTO.getInventoryEngine(),
						cpDefinitionInventory.getCPDefinitionInventoryEngine()),
					GetterUtil.get(
						productConfigurationDTO.isDisplayAvailability(),
						cpDefinitionInventory.isDisplayAvailability()),
					GetterUtil.get(
						productConfigurationDTO.isDisplayStockQuantity(),
						cpDefinitionInventory.isDisplayStockQuantity()),
					GetterUtil.get(
						productConfigurationDTO.getMinStockQuantity(),
						cpDefinitionInventory.getMinStockQuantity()),
					GetterUtil.get(
						productConfigurationDTO.isAllowBackOrder(),
						cpDefinitionInventory.isBackOrders()),
					GetterUtil.get(
						productConfigurationDTO.getMinOrderQuantity(),
						cpDefinitionInventory.getMinOrderQuantity()),
					GetterUtil.get(
						productConfigurationDTO.getMaxOrderQuantity(),
						cpDefinitionInventory.getMaxOrderQuantity()),
					_getAllowedOrderQuantities(
						cpDefinitionInventory, productConfigurationDTO),
					GetterUtil.get(
						productConfigurationDTO.getMultipleOrderQuantity(),
						cpDefinitionInventory.getMultipleOrderQuantity()),
					serviceContext);
		}

		return _dtoMapper.modelToProductConfigurationDTO(cpDefinitionInventory);
	}

	public ProductDTO updateProductDTO(
			String id, ProductDTO productDTO, Company company,
			Language language)
		throws PortalException {

		Map<String, ?> expando = Collections.emptyMap();

		if (productDTO.getExpando() != null) {
			expando = productDTO.getExpando();
		}

		return _dtoMapper.modelToDTO(
			_updateProduct(
				id, company, productDTO.getDescription(),
				productDTO.getShortDescription(), productDTO.getName(),
				expando),
			language.getLanguageId());
	}

	public ProductShippingConfigurationDTO updateProductShippingConfiguration(
			String productId, Company company,
			ProductShippingConfigurationDTO productShippingConfigurationDTO)
		throws PortalException {

		CPDefinition cpDefinition = getProductById(productId, company);

		cpDefinition = _cpDefinitionService.updateShippingInfo(
			cpDefinition.getCPDefinitionId(),
			GetterUtil.get(
				productShippingConfigurationDTO.isShippable(),
				cpDefinition.isShippable()),
			GetterUtil.get(
				productShippingConfigurationDTO.isFreeShipping(),
				cpDefinition.isFreeShipping()),
			GetterUtil.get(
				productShippingConfigurationDTO.isShippingSeparately(),
				cpDefinition.isShipSeparately()),
			getShippingExtraPrice(
				cpDefinition, productShippingConfigurationDTO),
			getWidth(cpDefinition, productShippingConfigurationDTO),
			getHeight(cpDefinition, productShippingConfigurationDTO),
			getDepth(cpDefinition, productShippingConfigurationDTO),
			getWeight(cpDefinition, productShippingConfigurationDTO),
			_serviceContextHelper.getServiceContext(cpDefinition.getGroupId()));

		return _dtoMapper.modelToProductShippingConfigurationDTO(cpDefinition);
	}

	public ProductSubscriptionConfigurationDTO
			updateProductSubscriptionConfiguration(
				String productId, Company company,
				ProductSubscriptionConfigurationDTO
					productSubscriptionConfigurationDTO)
		throws PortalException {

		CPDefinition cpDefinition = getProductById(productId, company);

		cpDefinition = _cpDefinitionService.updateSubscriptionInfo(
			cpDefinition.getCPDefinitionId(),
			GetterUtil.get(
				productSubscriptionConfigurationDTO.isEnable(),
				cpDefinition.isSubscriptionEnabled()),
			GetterUtil.get(
				productSubscriptionConfigurationDTO.getLength(),
				cpDefinition.getSubscriptionLength()),
			productSubscriptionConfigurationDTO.getSubscriptionType(), null,
			GetterUtil.get(
				productSubscriptionConfigurationDTO.getNumberOfLength(),
				cpDefinition.getMaxSubscriptionCycles()),
			_serviceContextHelper.getServiceContext(cpDefinition.getGroupId()));

		return _dtoMapper.modelToProductSubscriptionConfigurationDTO(
			cpDefinition);
	}

	public ProductTaxConfigurationDTO updateProductTaxConfiguration(
			String productId, Company company,
			ProductTaxConfigurationDTO productTaxConfigurationDTO,
			Language language)
		throws PortalException {

		CPDefinition cpDefinition = getProductById(productId, company);

		cpDefinition = _cpDefinitionService.updateTaxCategoryInfo(
			cpDefinition.getCPDefinitionId(),
			productTaxConfigurationDTO.getId(),
			_isTaxable(cpDefinition, productTaxConfigurationDTO), false);

		return _dtoMapper.modelToProductTaxConfigurationDTO(
			cpDefinition, language.getLanguageId());
	}

	public ProductDTO upsertProduct(
			long groupId, ProductDTO productDTO, Language language, User user)
		throws PortalException {

		return _dtoMapper.modelToDTO(
			_upsertProduct(
				groupId, productDTO.isActive(), productDTO.getDefaultSku(),
				productDTO.getDescription(),
				productDTO.getExternalReferenceCode(),
				productDTO.getProductType(), productDTO.getShortDescription(),
				productDTO.getName(), user, productDTO.getExpando()),
			language.getLanguageId());
	}

	private String _getAllowedOrderQuantities(
		CPDefinitionInventory cpDefinitionInventory,
		ProductConfigurationDTO productConfigurationDTO) {

		if (productConfigurationDTO.getAllowedOrderQuantities() != null) {
			return StringUtil.merge(
				productConfigurationDTO.getAllowedOrderQuantities());
		}

		if (cpDefinitionInventory == null) {
			return null;
		}

		return cpDefinitionInventory.getAllowedOrderQuantities();
	}

	private boolean _isTaxable(
		CPDefinition cpDefinition,
		ProductTaxConfigurationDTO productTaxConfigurationDTO) {

		if (productTaxConfigurationDTO.isTaxable() != null) {
			return productTaxConfigurationDTO.isTaxable();
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