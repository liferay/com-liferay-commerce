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

import com.liferay.commerce.product.exception.NoSuchCPOptionException;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPDefinitionOptionRel;
import com.liferay.commerce.product.model.CPOption;
import com.liferay.commerce.product.service.CPDefinitionOptionRelService;
import com.liferay.commerce.product.service.CPOptionService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductOption;
import com.liferay.headless.commerce.admin.catalog.internal.mapper.v1_0.DTOMapper;
import com.liferay.headless.commerce.core.util.IdUtils;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = ProductOptionHelper.class)
public class ProductOptionHelper {

	public void deleteProductOption(String id, Company company)
		throws PortalException {

		CPOption cpOption = getCPOptionById(id, company);

		_cpOptionService.deleteCPOption(cpOption.getCPOptionId());
	}

	public CPOption getCPOptionById(String id, Company company)
		throws PortalException {

		CPOption cpOption = null;

		if (IdUtils.isLocalPK(id)) {
			cpOption = _cpOptionService.getCPOption(GetterUtil.getLong(id));
		}
		else {
			cpOption = _cpOptionService.fetchByExternalReferenceCode(
				company.getCompanyId(),
				IdUtils.getExternalReferenceCodeFromId(id));
		}

		if (cpOption == null) {
			throw new NoSuchCPOptionException(
				"Unable to find Product Option with ID: " + id);
		}

		return cpOption;
	}

	public ProductOption getProductOption(
			String id, AcceptLanguage acceptLanguage, Company company)
		throws PortalException {

		return _dtoMapper.modelToDTO(
			getCPOptionById(id, company),
			acceptLanguage.getPreferredLanguageId());
	}

	public Page<ProductOption> getProductOptions(
			long groupId, AcceptLanguage acceptLanguage, Pagination pagination)
		throws PortalException {

		List<CPOption> cpOptions = _cpOptionService.getCPOptions(
			groupId, pagination.getStartPosition(), pagination.getEndPosition(),
			null);

		int totalItems = _cpOptionService.getCPOptionsCount(groupId);

		Stream<CPOption> stream = cpOptions.stream();

		return stream.map(
			cpOption -> _dtoMapper.modelToDTO(
				cpOption, acceptLanguage.getPreferredLanguageId())
		).collect(
			Collectors.collectingAndThen(
				Collectors.toList(),
				productOptions -> Page.of(
					productOptions, pagination, totalItems))
		);
	}

	public Page<ProductOption> getProductOptions(
			String productId, Company company, AcceptLanguage acceptLanguage,
			Pagination pagination)
		throws PortalException {

		CPDefinition cpDefinition = _productHelper.getProductById(
			productId, company);

		List<CPDefinitionOptionRel> cpDefinitionOptionRels =
			_cpDefinitionOptionRelService.getCPDefinitionOptionRels(
				cpDefinition.getCPDefinitionId(), pagination.getStartPosition(),
				pagination.getEndPosition());

		int totalItems =
			_cpDefinitionOptionRelService.getCPDefinitionOptionRelsCount(
				cpDefinition.getCPDefinitionId());

		Stream<CPDefinitionOptionRel> stream = cpDefinitionOptionRels.stream();

		return stream.map(
			cpDefinitionOptionRel -> _dtoMapper.modelToDTO(
				cpDefinitionOptionRel, acceptLanguage.getPreferredLanguageId())
		).collect(
			Collectors.collectingAndThen(
				Collectors.toList(),
				productOptions -> Page.of(
					productOptions, pagination, totalItems))
		);
	}

	public ProductOption updateProductOption(
			String id, ProductOption productOption,
			AcceptLanguage acceptLanguage, Company company)
		throws PortalException {

		CPOption cpOption = getCPOptionById(id, company);

		Locale locale = LocaleUtil.fromLanguageId(
			acceptLanguage.getPreferredLanguageId());

		Map<Locale, String> nameMap = new HashMap<Locale, String>() {
			{
				Map<String, String> optionNameMap = productOption.getName();

				Set<String> keySet = optionNameMap.keySet();

				if (keySet.contains(LocaleUtil.toLanguageId(locale))) {
					put(
						locale,
						optionNameMap.get(LocaleUtil.toLanguageId(locale)));
				}
			}
		};

		Map<Locale, String> descriptionMap = new HashMap<Locale, String>() {
			{
				String optionDescription = productOption.getDescription();

				if (optionDescription != null) {
					put(locale, productOption.getDescription());
				}
			}
		};

		ProductOption.FieldType fieldType = productOption.getFieldType();

		cpOption = _cpOptionService.updateCPOption(
			cpOption.getCPOptionId(), nameMap, descriptionMap,
			fieldType.getValue(), _isFacetable(productOption),
			_isRequired(productOption), _isSkuContributor(productOption),
			productOption.getKey(),
			_serviceContextHelper.getServiceContext(cpOption.getGroupId()));

		return _dtoMapper.modelToDTO(
			cpOption, acceptLanguage.getPreferredLanguageId());
	}

	public ProductOption[] updateProductOptions(
			String productId, ProductOption[] productOptions,
			AcceptLanguage acceptLanguage, Company company)
		throws PortalException {

		CPDefinition cpDefinition = _productHelper.getProductById(
			productId, company);

		for (ProductOption productOption : productOptions) {
			Locale locale = LocaleUtil.fromLanguageId(
				acceptLanguage.getPreferredLanguageId());

			Map<Locale, String> nameMap = new HashMap<Locale, String>() {
				{
					Map<String, String> optionNameMap = productOption.getName();

					Set<String> keySet = optionNameMap.keySet();

					if (keySet.contains(LocaleUtil.toLanguageId(locale))) {
						put(
							locale,
							optionNameMap.get(LocaleUtil.toLanguageId(locale)));
					}
				}
			};

			Map<Locale, String> descriptionMap = new HashMap<Locale, String>() {
				{
					String optionDescription = productOption.getDescription();

					if (optionDescription != null) {
						put(locale, productOption.getDescription());
					}
				}
			};

			CPOption cpOption = getCPOptionById(
				productOption.getExternalReferenceCode(), company);

			ProductOption.FieldType fieldType = productOption.getFieldType();

			_cpDefinitionOptionRelService.addCPDefinitionOptionRel(
				cpDefinition.getCPDefinitionId(), cpOption.getCPOptionId(),
				nameMap, descriptionMap, fieldType.getValue(),
				GetterUtil.getDouble(productOption.getPriority()),
				_isFacetable(productOption), _isRequired(productOption),
				_isSkuContributor(productOption), true,
				_serviceContextHelper.getServiceContext(
					cpDefinition.getGroupId()));
		}

		return _dtoMapper.modelsToProductOptionDTOArray(
			cpDefinition.getCPDefinitionOptionRels(),
			acceptLanguage.getPreferredLanguageId());
	}

	public ProductOption upsertProductOption(
			long groupId, ProductOption productOption,
			AcceptLanguage acceptLanguage)
		throws PortalException {

		Locale locale = LocaleUtil.fromLanguageId(
			acceptLanguage.getPreferredLanguageId());

		Map<Locale, String> nameMap = new HashMap<Locale, String>() {
			{
				Map<String, String> optionNameMap = productOption.getName();

				put(locale, optionNameMap.get(LocaleUtil.toLanguageId(locale)));
			}
		};

		Map<Locale, String> descriptionMap = new HashMap<Locale, String>() {
			{
				put(locale, productOption.getDescription());
			}
		};

		ProductOption.FieldType fieldType = productOption.getFieldType();

		CPOption cpOption = _cpOptionService.upsertCPOption(
			nameMap, descriptionMap, fieldType.getValue(),
			_isFacetable(productOption), _isRequired(productOption),
			_isSkuContributor(productOption), productOption.getKey(),
			productOption.getExternalReferenceCode(),
			_serviceContextHelper.getServiceContext(groupId));

		return _dtoMapper.modelToDTO(
			cpOption, acceptLanguage.getPreferredLanguageId());
	}

	private boolean _isFacetable(ProductOption productOption) {
		boolean facetable = false;

		if (productOption.getFacetable() != null) {
			facetable = productOption.getFacetable();
		}

		return facetable;
	}

	private boolean _isRequired(ProductOption productOption) {
		boolean required = false;

		if (productOption.getRequired() != null) {
			required = productOption.getRequired();
		}

		return required;
	}

	private boolean _isSkuContributor(ProductOption productOption) {
		boolean skuContributor = false;

		if (productOption.getSkuContributor() != null) {
			skuContributor = productOption.getSkuContributor();
		}

		return skuContributor;
	}

	@Reference
	private CPDefinitionOptionRelService _cpDefinitionOptionRelService;

	@Reference
	private CPOptionService _cpOptionService;

	@Reference
	private DTOMapper _dtoMapper;

	@Reference
	private ProductHelper _productHelper;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}