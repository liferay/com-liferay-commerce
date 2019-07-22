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
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPDefinitionOptionRel;
import com.liferay.commerce.product.service.CPDefinitionOptionRelService;
import com.liferay.commerce.product.service.CPDefinitionService;
import com.liferay.commerce.product.service.CPOptionService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductOption;
import com.liferay.headless.commerce.admin.catalog.internal.util.v1_0.ProductOptionUtil;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.ProductOptionResource;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterRegistry;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;
import com.liferay.headless.commerce.core.util.LanguageUtils;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Zoltán Takács
 * @author Alessio Antonio Rendina
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/product-option.properties",
	scope = ServiceScope.PROTOTYPE, service = ProductOptionResource.class
)
public class ProductOptionResourceImpl extends BaseProductOptionResourceImpl {

	@Override
	public Response deleteProductOption(Long id) throws Exception {
		CPDefinitionOptionRel cpDefinitionOptionRel =
			_cpDefinitionOptionRelService.getCPDefinitionOptionRel(id);

		_cpDefinitionOptionRelService.deleteCPDefinitionOptionRel(
			cpDefinitionOptionRel.getCPDefinitionOptionRelId());

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Page<ProductOption>
			getProductByExternalReferenceCodeProductOptionsPage(
				String externalReferenceCode, Pagination pagination)
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

		List<CPDefinitionOptionRel> cpDefinitionOptionRels =
			_cpDefinitionOptionRelService.getCPDefinitionOptionRels(
				cpDefinition.getCPDefinitionId(), pagination.getStartPosition(),
				pagination.getEndPosition());

		int totalItems =
			_cpDefinitionOptionRelService.getCPDefinitionOptionRelsCount(
				cpDefinition.getCPDefinitionId());

		return Page.of(
			_toProductOptions(cpDefinitionOptionRels), pagination, totalItems);
	}

	@Override
	public Page<ProductOption> getProductIdProductOptionsPage(
			Long id, Pagination pagination)
		throws Exception {

		CPDefinition cpDefinition =
			_cpDefinitionService.fetchCPDefinitionByCProductId(id);

		if (cpDefinition == null) {
			throw new NoSuchCPDefinitionException(
				"Unable to find Product with ID: " + id);
		}

		List<CPDefinitionOptionRel> cpDefinitionOptionRels =
			_cpDefinitionOptionRelService.getCPDefinitionOptionRels(
				cpDefinition.getCPDefinitionId(), pagination.getStartPosition(),
				pagination.getEndPosition());

		int totalItems =
			_cpDefinitionOptionRelService.getCPDefinitionOptionRelsCount(
				cpDefinition.getCPDefinitionId());

		return Page.of(
			_toProductOptions(cpDefinitionOptionRels), pagination, totalItems);
	}

	@Override
	public ProductOption getProductOption(Long id) throws Exception {
		DTOConverter productOptionDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CPDefinitionOptionRel.class.getName());

		return (ProductOption)productOptionDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(), id));
	}

	@Override
	public Response patchProductOption(Long id, ProductOption productOption)
		throws Exception {

		_updateProductOption(id, productOption);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Page<ProductOption>
			postProductByExternalReferenceCodeProductOptionsPage(
				String externalReferenceCode, ProductOption[] productOptions)
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

		return Page.of(_upsertProductOptions(cpDefinition, productOptions));
	}

	@Override
	public Page<ProductOption> postProductIdProductOptionsPage(
			Long id, ProductOption[] productOptions)
		throws Exception {

		CPDefinition cpDefinition =
			_cpDefinitionService.fetchCPDefinitionByCProductId(id);

		if (cpDefinition == null) {
			throw new NoSuchCPDefinitionException(
				"Unable to find Product with ID: " + id);
		}

		return Page.of(_upsertProductOptions(cpDefinition, productOptions));
	}

	private List<ProductOption> _toProductOptions(
			List<CPDefinitionOptionRel> cpDefinitionOptionRels)
		throws Exception {

		List<ProductOption> productOptions = new ArrayList<>();

		DTOConverter productOptionDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CPDefinitionOptionRel.class.getName());

		for (CPDefinitionOptionRel cpDefinitionOptionRel :
				cpDefinitionOptionRels) {

			productOptions.add(
				(ProductOption)productOptionDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						contextAcceptLanguage.getPreferredLocale(),
						cpDefinitionOptionRel.getCPDefinitionOptionRelId())));
		}

		return productOptions;
	}

	private ProductOption _updateProductOption(
			long id, ProductOption productOption)
		throws Exception {

		CPDefinitionOptionRel cpDefinitionOptionRel =
			_cpDefinitionOptionRelService.getCPDefinitionOptionRel(id);

		ProductOption.FieldType fieldType = productOption.getFieldType();

		cpDefinitionOptionRel =
			_cpDefinitionOptionRelService.updateCPDefinitionOptionRel(
				cpDefinitionOptionRel.getCPDefinitionOptionRelId(),
				productOption.getOptionId(),
				LanguageUtils.getLocalizedMap(productOption.getName()),
				LanguageUtils.getLocalizedMap(productOption.getDescription()),
				fieldType.getValue(),
				GetterUtil.get(
					productOption.getPriority(),
					cpDefinitionOptionRel.getPriority()),
				GetterUtil.get(
					productOption.getFacetable(),
					cpDefinitionOptionRel.isFacetable()),
				GetterUtil.get(
					productOption.getRequired(),
					cpDefinitionOptionRel.isRequired()),
				GetterUtil.get(
					productOption.getSkuContributor(),
					cpDefinitionOptionRel.isSkuContributor()),
				_serviceContextHelper.getServiceContext(
					cpDefinitionOptionRel.getGroupId()));

		DTOConverter productOptionDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CPDefinitionOptionRel.class.getName());

		return (ProductOption)productOptionDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				cpDefinitionOptionRel.getCPDefinitionOptionRelId()));
	}

	private List<ProductOption> _upsertProductOptions(
			CPDefinition cpDefinition, ProductOption[] productOptions)
		throws Exception {

		for (ProductOption productOption : productOptions) {
			ProductOptionUtil.upsertCPDefinitionOptionRel(
				_cpDefinitionOptionRelService, _cpOptionService, productOption,
				cpDefinition.getCPDefinitionId(),
				_serviceContextHelper.getServiceContext(
					cpDefinition.getGroupId()));
		}

		List<ProductOption> productOptionList = new ArrayList<>();

		cpDefinition = _cpDefinitionService.getCPDefinition(
			cpDefinition.getCPDefinitionId());

		DTOConverter productOptionDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CPDefinitionOptionRel.class.getName());

		for (CPDefinitionOptionRel cpDefinitionOptionRel :
				cpDefinition.getCPDefinitionOptionRels()) {

			productOptionList.add(
				(ProductOption)productOptionDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						contextAcceptLanguage.getPreferredLocale(),
						cpDefinitionOptionRel.getCPDefinitionOptionRelId())));
		}

		return productOptionList;
	}

	@Reference
	private CPDefinitionOptionRelService _cpDefinitionOptionRelService;

	@Reference
	private CPDefinitionService _cpDefinitionService;

	@Reference
	private CPOptionService _cpOptionService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}