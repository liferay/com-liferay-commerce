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
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductShippingConfiguration;
import com.liferay.headless.commerce.admin.catalog.internal.mapper.v1_0.ProductDTOMapper;
import com.liferay.headless.commerce.admin.catalog.internal.util.v1_0.ProductShippingConfigurationUtil;
import com.liferay.headless.commerce.admin.catalog.internal.util.v1_0.ProductUtil;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.ProductShippingConfigurationResource;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.exception.PortalException;

import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/product-shipping-configuration.properties",
	scope = ServiceScope.PROTOTYPE,
	service = ProductShippingConfigurationResource.class
)
public class ProductShippingConfigurationResourceImpl
	extends BaseProductShippingConfigurationResourceImpl {

	@Override
	public ProductShippingConfiguration
			getProductByExternalReferenceCodeShippingConfiguration(
				String externalReferenceCode)
		throws Exception {

		CPDefinition cpDefinition =
			ProductUtil.getProductByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		return _productDTOMapper.toProductShippingConfiguration(cpDefinition);
	}

	@Override
	public ProductShippingConfiguration getProductIdShippingConfiguration(
			Long id)
		throws Exception {

		CPDefinition cpDefinition = ProductUtil.getProductById(id);

		return _productDTOMapper.toProductShippingConfiguration(cpDefinition);
	}

	@Override
	public Response patchProductByExternalReferenceCodeShippingConfiguration(
			String externalReferenceCode,
			ProductShippingConfiguration productShippingConfiguration)
		throws Exception {

		CPDefinition cpDefinition =
			ProductUtil.getProductByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		_updateProductShippingConfiguration(
			cpDefinition, productShippingConfiguration);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public Response patchProductIdShippingConfiguration(
			Long id, ProductShippingConfiguration productShippingConfiguration)
		throws Exception {

		CPDefinition cpDefinition = ProductUtil.getProductById(id);

		_updateProductShippingConfiguration(
			cpDefinition, productShippingConfiguration);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	private ProductShippingConfiguration _updateProductShippingConfiguration(
			CPDefinition cpDefinition,
			ProductShippingConfiguration productShippingConfiguration)
		throws PortalException {

		cpDefinition =
			ProductShippingConfigurationUtil.updateCPDefinitionShippingInfo(
				_cpDefinitionService, productShippingConfiguration,
				cpDefinition,
				_serviceContextHelper.getServiceContext(
					cpDefinition.getGroupId()));

		return _productDTOMapper.toProductShippingConfiguration(cpDefinition);
	}

	@Reference
	private CPDefinitionService _cpDefinitionService;

	@Reference
	private ProductDTOMapper _productDTOMapper;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}