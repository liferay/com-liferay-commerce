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

import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.service.CPDefinitionService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductTaxConfiguration;
import com.liferay.headless.commerce.admin.catalog.internal.mapper.v1_0.ProductDTOMapper;
import com.liferay.headless.commerce.admin.catalog.internal.util.v1_0.ProductTaxConfigurationUtil;
import com.liferay.headless.commerce.admin.catalog.internal.util.v1_0.ProductUtil;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.ProductTaxConfigurationResource;
import com.liferay.portal.kernel.exception.PortalException;

import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/product-tax-configuration.properties",
	scope = ServiceScope.PROTOTYPE,
	service = ProductTaxConfigurationResource.class
)
public class ProductTaxConfigurationResourceImpl
	extends BaseProductTaxConfigurationResourceImpl {

	@Override
	public ProductTaxConfiguration
			getProductByExternalReferenceCodeTaxConfiguration(
				String externalReferenceCode)
		throws Exception {

		CPDefinition cpDefinition =
			ProductUtil.getProductByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		return _productDTOMapper.toProductTaxConfiguration(
			cpDefinition, contextAcceptLanguage.getPreferredLanguageId());
	}

	@Override
	public ProductTaxConfiguration getProductIdTaxConfiguration(Long id)
		throws Exception {

		CPDefinition cpDefinition = ProductUtil.getProductById(id);

		return _productDTOMapper.toProductTaxConfiguration(
			cpDefinition, contextAcceptLanguage.getPreferredLanguageId());
	}

	@Override
	public Response patchProductByExternalReferenceCodeTaxConfiguration(
			String externalReferenceCode,
			ProductTaxConfiguration productTaxConfiguration)
		throws Exception {

		CPDefinition cpDefinition =
			ProductUtil.getProductByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		_updateProductTaxConfiguration(cpDefinition, productTaxConfiguration);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public Response patchProductIdTaxConfiguration(
			Long id, ProductTaxConfiguration productTaxConfiguration)
		throws Exception {

		CPDefinition cpDefinition = ProductUtil.getProductById(id);

		_updateProductTaxConfiguration(cpDefinition, productTaxConfiguration);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	private ProductTaxConfiguration _updateProductTaxConfiguration(
			CPDefinition cpDefinition,
			ProductTaxConfiguration productTaxConfiguration)
		throws PortalException {

		cpDefinition =
			ProductTaxConfigurationUtil.updateCPDefinitionTaxCategoryInfo(
				_cpDefinitionService, productTaxConfiguration, cpDefinition);

		return _productDTOMapper.toProductTaxConfiguration(
			cpDefinition, contextAcceptLanguage.getPreferredLanguageId());
	}

	@Reference
	private CPDefinitionService _cpDefinitionService;

	@Reference
	private ProductDTOMapper _productDTOMapper;

}