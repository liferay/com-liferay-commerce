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

package com.liferay.headless.commerce.admin.catalog.internal.resource.v1_0;

import com.liferay.commerce.product.exception.NoSuchCPDefinitionException;
import com.liferay.commerce.product.model.CPAttachmentFileEntryConstants;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPDefinitionOptionRel;
import com.liferay.commerce.product.model.CPDefinitionSpecificationOptionValue;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.CPAttachmentFileEntryService;
import com.liferay.commerce.product.service.CPDefinitionLinkService;
import com.liferay.commerce.product.service.CPDefinitionOptionRelService;
import com.liferay.commerce.product.service.CPDefinitionOptionValueRelService;
import com.liferay.commerce.product.service.CPDefinitionService;
import com.liferay.commerce.product.service.CPDefinitionSpecificationOptionValueService;
import com.liferay.commerce.product.service.CPInstanceService;
import com.liferay.commerce.product.service.CPOptionService;
import com.liferay.commerce.product.service.CommerceCatalogLocalService;
import com.liferay.commerce.service.CPDefinitionInventoryService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Attachment;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Category;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Product;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductConfiguration;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductOption;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductOptionValue;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductShippingConfiguration;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductSpecification;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductSubscriptionConfiguration;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductTaxConfiguration;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.RelatedProduct;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Sku;
import com.liferay.headless.commerce.admin.catalog.internal.util.v1_0.AttachmentUtil;
import com.liferay.headless.commerce.admin.catalog.internal.util.v1_0.ProductConfigurationUtil;
import com.liferay.headless.commerce.admin.catalog.internal.util.v1_0.ProductOptionUtil;
import com.liferay.headless.commerce.admin.catalog.internal.util.v1_0.ProductOptionValueUtil;
import com.liferay.headless.commerce.admin.catalog.internal.util.v1_0.ProductShippingConfigurationUtil;
import com.liferay.headless.commerce.admin.catalog.internal.util.v1_0.ProductSpecificationUtil;
import com.liferay.headless.commerce.admin.catalog.internal.util.v1_0.ProductSubscriptionConfigurationUtil;
import com.liferay.headless.commerce.admin.catalog.internal.util.v1_0.ProductTaxConfigurationUtil;
import com.liferay.headless.commerce.admin.catalog.internal.util.v1_0.ProductUtil;
import com.liferay.headless.commerce.admin.catalog.internal.util.v1_0.RelatedProductUtil;
import com.liferay.headless.commerce.admin.catalog.internal.util.v1_0.SkuUtil;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.ProductResource;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterRegistry;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;
import com.liferay.headless.commerce.core.util.DateConfig;
import com.liferay.headless.commerce.core.util.ExpandoUtil;
import com.liferay.headless.commerce.core.util.LanguageUtils;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.upload.UniqueFileNameProvider;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Zoltán Takács
 * @author Alessio Antonio Rendina
 * @author Igor Beslic
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/product.properties",
	scope = ServiceScope.PROTOTYPE, service = ProductResource.class
)
public class ProductResourceImpl extends BaseProductResourceImpl {

	@Override
	public Response deleteProduct(Long id) throws Exception {
		CPDefinition cpDefinition =
			_cpDefinitionService.fetchCPDefinitionByCProductId(id);

		if (cpDefinition == null) {
			throw new NoSuchCPDefinitionException(
				"Unable to find Product with ID: " + id);
		}

		_cpDefinitionService.deleteCPDefinition(
			cpDefinition.getCPDefinitionId());

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Response deleteProductByExternalReferenceCode(
			String externalReferenceCode)
		throws Exception {

		CPDefinition cpDefinition =
			_cpDefinitionService.
				fetchCPDefinitionByCProductExternalReferenceCode(
					contextCompany.getCompanyId(), externalReferenceCode);

		if (cpDefinition == null) {
			throw new NoSuchCPDefinitionException(
				"Unable to find Product with externalReferenceCode: " +
					externalReferenceCode);
		}

		_cpDefinitionService.deleteCPDefinition(
			cpDefinition.getCPDefinitionId());

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Product getProduct(Long id) throws Exception {
		CPDefinition cpDefinition =
			_cpDefinitionService.fetchCPDefinitionByCProductId(id);

		if (cpDefinition == null) {
			throw new NoSuchCPDefinitionException(
				"Unable to find Product with ID: " + id);
		}

		DTOConverter productDTOConverter =
			_dtoConverterRegistry.getDTOConverter(CPDefinition.class.getName());

		return (Product)productDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				cpDefinition.getCPDefinitionId()));
	}

	@Override
	public Product getProductByExternalReferenceCode(
			String externalReferenceCode)
		throws Exception {

		CPDefinition cpDefinition =
			_cpDefinitionService.
				fetchCPDefinitionByCProductExternalReferenceCode(
					contextCompany.getCompanyId(), externalReferenceCode);

		if (cpDefinition == null) {
			throw new NoSuchCPDefinitionException(
				"Unable to find Product with externalReferenceCode: " +
					externalReferenceCode);
		}

		DTOConverter productDTOConverter =
			_dtoConverterRegistry.getDTOConverter(CPDefinition.class.getName());

		return (Product)productDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				cpDefinition.getCPDefinitionId()));
	}

	@Override
	public Page<Product> getProductsPage(Pagination pagination)
		throws Exception {

		BaseModelSearchResult<CPDefinition> cpDefinitionBaseModelSearchResult =
			_cpDefinitionService.searchCPDefinitions(
				contextCompany.getCompanyId(), null, null, null,
				pagination.getStartPosition(), pagination.getEndPosition(),
				null);

		return Page.of(
			_toProducts(cpDefinitionBaseModelSearchResult.getBaseModels()),
			pagination, cpDefinitionBaseModelSearchResult.getLength());
	}

	@Override
	public Response patchProduct(Long id, Product product) throws Exception {
		CPDefinition cpDefinition =
			_cpDefinitionService.fetchCPDefinitionByCProductId(id);

		if (cpDefinition == null) {
			throw new NoSuchCPDefinitionException(
				"Unable to find Product with ID: " + id);
		}

		_updateProduct(cpDefinition, product);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Response patchProductByExternalReferenceCode(
			String externalReferenceCode, Product product)
		throws Exception {

		CPDefinition cpDefinition =
			_cpDefinitionService.
				fetchCPDefinitionByCProductExternalReferenceCode(
					contextCompany.getCompanyId(), externalReferenceCode);

		if (cpDefinition == null) {
			throw new NoSuchCPDefinitionException(
				"Unable to find Product with externalReferenceCode: " +
					externalReferenceCode);
		}

		_updateProduct(cpDefinition, product);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Product postProduct(Product product) throws Exception {
		CPDefinition cpDefinition = _upsertProduct(product);
		DTOConverter productDTOConverter =
			_dtoConverterRegistry.getDTOConverter(CPDefinition.class.getName());

		return (Product)productDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				cpDefinition.getCPDefinitionId()));
	}

	private ProductShippingConfiguration _getProductShippingConfiguration(
		Product product) {

		ProductShippingConfiguration shippingConfiguration =
			product.getShippingConfiguration();

		if (shippingConfiguration != null) {
			return shippingConfiguration;
		}

		return new ProductShippingConfiguration();
	}

	private ProductSubscriptionConfiguration
		_getProductSubscriptionConfiguration(Product product) {

		ProductSubscriptionConfiguration subscriptionConfiguration =
			product.getSubscriptionConfiguration();

		if (subscriptionConfiguration != null) {
			return subscriptionConfiguration;
		}

		return new ProductSubscriptionConfiguration();
	}

	private ProductTaxConfiguration _getProductTaxConfiguration(
		Product product) {

		ProductTaxConfiguration taxConfiguration =
			product.getTaxConfiguration();

		if (taxConfiguration != null) {
			return taxConfiguration;
		}

		return new ProductTaxConfiguration();
	}

	private List<Product> _toProducts(List<CPDefinition> cpDefinitions)
		throws Exception {

		List<Product> products = new ArrayList<>();

		DTOConverter productDTOConverter =
			_dtoConverterRegistry.getDTOConverter(CPDefinition.class.getName());

		for (CPDefinition cpDefinition : cpDefinitions) {
			products.add(
				(Product)productDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						contextAcceptLanguage.getPreferredLocale(),
						cpDefinition.getCPDefinitionId())));
		}

		return products;
	}

	private CPDefinition _updateNestedResources(
			Product product, CPDefinition cpDefinition,
			ServiceContext serviceContext)
		throws Exception {

		// Product configuration

		ProductConfiguration productConfiguration = product.getConfiguration();

		if (productConfiguration != null) {
			ProductConfigurationUtil.updateCPDefinitionInventory(
				cpDefinition.getGroupId(), _cpDefinitionInventoryService,
				productConfiguration, cpDefinition.getCPDefinitionId());
		}

		// Product shipping configuration

		ProductShippingConfiguration productShippingConfiguration =
			product.getShippingConfiguration();

		if (productShippingConfiguration != null) {
			cpDefinition =
				ProductShippingConfigurationUtil.updateCPDefinitionShippingInfo(
					_cpDefinitionService, productShippingConfiguration,
					cpDefinition, serviceContext);
		}

		// Product subscription configuration

		ProductSubscriptionConfiguration productSubscriptionConfiguration =
			product.getSubscriptionConfiguration();

		if (productSubscriptionConfiguration != null) {
			cpDefinition =
				ProductSubscriptionConfigurationUtil.
					updateCPDefinitionSubscriptionInfo(
						_cpDefinitionService, productSubscriptionConfiguration,
						cpDefinition, serviceContext);
		}

		// Product tax configuration

		ProductTaxConfiguration productTaxConfiguration =
			product.getTaxConfiguration();

		if (productTaxConfiguration != null) {
			cpDefinition =
				ProductTaxConfigurationUtil.updateCPDefinitionTaxCategoryInfo(
					_cpDefinitionService, productTaxConfiguration,
					cpDefinition);
		}

		// Attachments

		Attachment[] attachments = product.getAttachments();

		if (attachments != null) {
			for (Attachment attachment : attachments) {
				AttachmentUtil.upsertCPAttachmentFileEntry(
					_cpAttachmentFileEntryService, _uniqueFileNameProvider,
					attachment,
					_classNameLocalService.getClassNameId(
						cpDefinition.getModelClassName()),
					cpDefinition.getCPDefinitionId(),
					CPAttachmentFileEntryConstants.TYPE_OTHER, serviceContext);
			}
		}

		// Images

		Attachment[] images = product.getImages();

		if (images != null) {
			for (Attachment image : images) {
				AttachmentUtil.upsertCPAttachmentFileEntry(
					_cpAttachmentFileEntryService, _uniqueFileNameProvider,
					image,
					_classNameLocalService.getClassNameId(
						cpDefinition.getModelClassName()),
					cpDefinition.getCPDefinitionId(),
					CPAttachmentFileEntryConstants.TYPE_IMAGE, serviceContext);
			}
		}

		// Product specifications

		ProductSpecification[] productSpecifications =
			product.getProductSpecifications();

		if (productSpecifications != null) {
			for (ProductSpecification productSpecification :
					productSpecifications) {

				CPDefinitionSpecificationOptionValue
					cpDefinitionSpecificationOptionValue =
						_cpDefinitionSpecificationOptionValueService.
							fetchCPDefinitionSpecificationOptionValue(
								productSpecification.getId());

				if (cpDefinitionSpecificationOptionValue == null) {
					ProductSpecificationUtil.
						addCPDefinitionSpecificationOptionValue(
							_cpDefinitionSpecificationOptionValueService,
							cpDefinition.getCPDefinitionId(),
							productSpecification, serviceContext);
				}
				else {
					ProductSpecificationUtil.
						updateCPDefinitionSpecificationOptionValue(
							_cpDefinitionSpecificationOptionValueService,
							cpDefinitionSpecificationOptionValue,
							productSpecification, serviceContext);
				}
			}
		}

		// Product options

		ProductOption[] productOptions = product.getOptions();

		if (productOptions != null) {
			for (ProductOption productOption : productOptions) {
				CPDefinitionOptionRel cpDefinitionOptionRel =
					ProductOptionUtil.upsertCPDefinitionOptionRel(
						_cpDefinitionOptionRelService, _cpOptionService,
						productOption, cpDefinition.getCPDefinitionId(),
						serviceContext);

				ProductOptionValue[] productOptionValues =
					productOption.getValues();

				if (productOptionValues != null) {
					for (ProductOptionValue productOptionValue :
							productOptionValues) {

						ProductOptionValueUtil.upsertCPDefinitionOptionValueRel(
							_cpDefinitionOptionValueRelService,
							productOptionValue,
							cpDefinitionOptionRel.getCPDefinitionOptionRelId(),
							serviceContext);
					}
				}
			}
		}

		// Related Products

		RelatedProduct[] relatedProducts = product.getRelatedProducts();

		if (relatedProducts != null) {
			for (RelatedProduct relatedProduct : relatedProducts) {
				RelatedProductUtil.upsertCPDefinitionLink(
					_cpDefinitionLinkService, relatedProduct,
					cpDefinition.getCPDefinitionId(),
					_serviceContextHelper.getServiceContext(
						cpDefinition.getGroupId()));
			}
		}

		// Skus

		Sku[] skus = product.getSkus();

		if (skus != null) {
			for (Sku sku : skus) {
				SkuUtil.upsertCPInstance(
					_cpInstanceService, sku, cpDefinition, serviceContext);
			}
		}

		// Categories

		Category[] categories = product.getCategories();

		if (categories != null) {

			// TODO upsert categories

		}

		return cpDefinition;
	}

	private CPDefinition _updateProduct(
			CPDefinition cpDefinition, Product product)
		throws Exception {

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			cpDefinition.getGroupId());

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
			LanguageUtils.getLocalizedMap(product.getName()),
			LanguageUtils.getLocalizedMap(product.getShortDescription()),
			LanguageUtils.getLocalizedMap(product.getDescription()),
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

		// Expando

		Map<String, ?> expando = product.getExpando();

		if ((expando != null) && !expando.isEmpty()) {
			ExpandoUtil.updateExpando(
				serviceContext.getCompanyId(), CPDefinition.class,
				cpDefinition.getPrimaryKey(), expando);
		}

		// Update nested resources

		cpDefinition = _updateNestedResources(
			product, cpDefinition, serviceContext);

		return cpDefinition;
	}

	private CPDefinition _upsertProduct(Product product) throws Exception {
		boolean neverExpire = Boolean.TRUE;

		CommerceCatalog commerceCatalog =
			_commerceCatalogLocalService.getCommerceCatalog(
				product.getCatalogId());

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			commerceCatalog.getGroupId());

		Calendar displayCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		DateConfig displayDateConfig = new DateConfig(displayCalendar);

		Calendar expirationCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		expirationCalendar.add(Calendar.MONTH, 1);

		DateConfig expirationDateConfig = new DateConfig(expirationCalendar);

		ProductShippingConfiguration shippingConfiguration =
			_getProductShippingConfiguration(product);
		ProductSubscriptionConfiguration subscriptionConfiguration =
			_getProductSubscriptionConfiguration(product);
		ProductTaxConfiguration taxConfiguration = _getProductTaxConfiguration(
			product);

		CPDefinition cpDefinition = _cpDefinitionService.upsertCPDefinition(
			commerceCatalog.getGroupId(), _user.getUserId(),
			LanguageUtils.getLocalizedMap(product.getName()),
			LanguageUtils.getLocalizedMap(product.getShortDescription()),
			LanguageUtils.getLocalizedMap(product.getDescription()), null,
			LanguageUtils.getLocalizedMap(product.getMetaTitle()),
			LanguageUtils.getLocalizedMap(product.getMetaDescription()),
			LanguageUtils.getLocalizedMap(product.getMetaKeyword()),
			product.getProductType(), true,
			GetterUtil.getBoolean(shippingConfiguration.getShippable(), true),
			GetterUtil.getBoolean(
				shippingConfiguration.getFreeShipping(), true),
			GetterUtil.getBoolean(
				shippingConfiguration.getShippingSeparately(), true),
			GetterUtil.getDouble(
				shippingConfiguration.getShippingExtraPrice(), 0D),
			GetterUtil.getDouble(shippingConfiguration.getWidth(), 0D),
			GetterUtil.getDouble(shippingConfiguration.getHeight(), 0D),
			GetterUtil.getDouble(shippingConfiguration.getDepth(), 0D),
			GetterUtil.getDouble(shippingConfiguration.getWeight(), 0D),
			GetterUtil.getLong(taxConfiguration.getId(), 0L),
			ProductUtil.isTaxExempt(null, taxConfiguration), false, null, true,
			displayDateConfig.getMonth(), displayDateConfig.getDay(),
			displayDateConfig.getYear(), displayDateConfig.getHour(),
			displayDateConfig.getMinute(), expirationDateConfig.getMonth(),
			expirationDateConfig.getDay(), expirationDateConfig.getYear(),
			expirationDateConfig.getHour(), expirationDateConfig.getMinute(),
			neverExpire, product.getDefaultSku(),
			GetterUtil.getBoolean(subscriptionConfiguration.getEnable(), false),
			GetterUtil.getInteger(subscriptionConfiguration.getLength(), 0),
			GetterUtil.getString(
				subscriptionConfiguration.getSubscriptionTypeAsString()),
			null,
			GetterUtil.getLong(
				subscriptionConfiguration.getNumberOfLength(), 0L),
			product.getExternalReferenceCode(), serviceContext);

		// Workflow

		if (!product.getActive()) {
			Map<String, Serializable> workflowContext = new HashMap<>();

			_cpDefinitionService.updateStatus(
				_user.getUserId(), cpDefinition.getCPDefinitionId(),
				WorkflowConstants.STATUS_INACTIVE, serviceContext,
				workflowContext);
		}

		// Expando

		Map<String, ?> expando = product.getExpando();

		if ((expando != null) && !expando.isEmpty()) {
			ExpandoUtil.updateExpando(
				serviceContext.getCompanyId(), CPDefinition.class,
				cpDefinition.getPrimaryKey(), expando);
		}

		// Update nested resources

		_updateNestedResources(product, cpDefinition, serviceContext);

		return cpDefinition;
	}

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private CommerceCatalogLocalService _commerceCatalogLocalService;

	@Reference
	private CPAttachmentFileEntryService _cpAttachmentFileEntryService;

	@Reference
	private CPDefinitionInventoryService _cpDefinitionInventoryService;

	@Reference
	private CPDefinitionLinkService _cpDefinitionLinkService;

	@Reference
	private CPDefinitionOptionRelService _cpDefinitionOptionRelService;

	@Reference
	private CPDefinitionOptionValueRelService
		_cpDefinitionOptionValueRelService;

	@Reference
	private CPDefinitionService _cpDefinitionService;

	@Reference
	private CPDefinitionSpecificationOptionValueService
		_cpDefinitionSpecificationOptionValueService;

	@Reference
	private CPInstanceService _cpInstanceService;

	@Reference
	private CPOptionService _cpOptionService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

	@Reference
	private UniqueFileNameProvider _uniqueFileNameProvider;

	@Context
	private User _user;

}