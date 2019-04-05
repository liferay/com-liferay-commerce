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

import com.liferay.commerce.model.CPDefinitionInventory;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.service.CPDefinitionInventoryService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductConfiguration;
import com.liferay.headless.commerce.admin.catalog.internal.mapper.v1_0.ProductDTOMapper;
import com.liferay.headless.commerce.admin.catalog.internal.util.v1_0.ProductConfigurationUtil;
import com.liferay.headless.commerce.admin.catalog.internal.util.v1_0.ProductUtil;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.ProductConfigurationResource;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;

import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/product-configuration.properties",
	scope = ServiceScope.PROTOTYPE, service = ProductConfigurationResource.class
)
public class ProductConfigurationResourceImpl
	extends BaseProductConfigurationResourceImpl {

	@Override
	public ProductConfiguration getProductByExternalReferenceCodeConfiguration(
			String externalReferenceCode)
		throws Exception {

		CPDefinition cpDefinition =
			ProductUtil.getProductByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		CPDefinitionInventory cpDefinitionInventory =
			_cpDefinitionInventoryService.
				fetchCPDefinitionInventoryByCPDefinitionId(
					cpDefinition.getCPDefinitionId());

		return _productDTOMapper.toProductConfiguration(cpDefinitionInventory);
	}

	@Override
	public ProductConfiguration getProductIdConfiguration(Long id)
		throws Exception {

		CPDefinition cpDefinition = ProductUtil.getProductById(id);

		CPDefinitionInventory cpDefinitionInventory =
			_cpDefinitionInventoryService.
				fetchCPDefinitionInventoryByCPDefinitionId(
					cpDefinition.getCPDefinitionId());

		return _productDTOMapper.toProductConfiguration(cpDefinitionInventory);
	}

	@Override
	public Response patchProductByExternalReferenceCodeConfiguration(
			String externalReferenceCode,
			ProductConfiguration productConfiguration)
		throws Exception {

		CPDefinition cpDefinition =
			ProductUtil.getProductByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		ProductConfigurationUtil.updateCPDefinitionInventory(
			_cpDefinitionInventoryService, productConfiguration,
			cpDefinition.getCPDefinitionId(),
			_serviceContextHelper.getServiceContext(cpDefinition.getGroupId()));

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public Response patchProductIdConfiguration(
			Long id, ProductConfiguration productConfiguration)
		throws Exception {

		CPDefinition cpDefinition = ProductUtil.getProductById(id);

		ProductConfigurationUtil.updateCPDefinitionInventory(
			_cpDefinitionInventoryService, productConfiguration,
			cpDefinition.getCPDefinitionId(),
			_serviceContextHelper.getServiceContext(cpDefinition.getGroupId()));

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Reference
	private CPDefinitionInventoryService _cpDefinitionInventoryService;

	@Reference
	private ProductDTOMapper _productDTOMapper;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}