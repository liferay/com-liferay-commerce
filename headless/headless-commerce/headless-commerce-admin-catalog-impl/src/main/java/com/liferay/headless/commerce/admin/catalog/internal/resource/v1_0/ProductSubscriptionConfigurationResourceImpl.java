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
import com.liferay.commerce.product.service.CPDefinitionService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductSubscriptionConfiguration;
import com.liferay.headless.commerce.admin.catalog.internal.util.v1_0.ProductSubscriptionConfigurationUtil;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.ProductSubscriptionConfigurationResource;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterRegistry;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;

import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/product-subscription-configuration.properties",
	scope = ServiceScope.PROTOTYPE,
	service = ProductSubscriptionConfigurationResource.class
)
public class ProductSubscriptionConfigurationResourceImpl
	extends BaseProductSubscriptionConfigurationResourceImpl {

	@Override
	public ProductSubscriptionConfiguration
			getProductByExternalReferenceCodeSubscriptionConfiguration(
				String externalReferenceCode)
		throws Exception {

		CPDefinition cpDefinition =
			_cpDefinitionService.
				fetchCPDefinitionByCProductExternalReferenceCode(
					contextCompany.getCompanyId(), externalReferenceCode);

		DTOConverter productSubscriptionConfigurationDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				"ProductSubscriptionConfiguration");

		return (ProductSubscriptionConfiguration)
			productSubscriptionConfigurationDTOConverter.toDTO(
				new DefaultDTOConverterContext(
					contextAcceptLanguage.getPreferredLocale(),
					cpDefinition.getCPDefinitionId()));
	}

	@Override
	public ProductSubscriptionConfiguration
			getProductIdSubscriptionConfiguration(Long id)
		throws Exception {

		CPDefinition cpDefinition =
			_cpDefinitionService.fetchCPDefinitionByCProductId(id);

		if (cpDefinition == null) {
			throw new NoSuchCPDefinitionException(
				"Unable to find Product with ID: " + id);
		}

		DTOConverter productSubscriptionConfigurationDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				"ProductSubscriptionConfiguration");

		return (ProductSubscriptionConfiguration)
			productSubscriptionConfigurationDTOConverter.toDTO(
				new DefaultDTOConverterContext(
					contextAcceptLanguage.getPreferredLocale(),
					cpDefinition.getCPDefinitionId()));
	}

	@Override
	public Response
			patchProductByExternalReferenceCodeSubscriptionConfiguration(
				String externalReferenceCode,
				ProductSubscriptionConfiguration
					productSubscriptionConfiguration)
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

		_updateProductSubscriptionConfiguration(
			cpDefinition, productSubscriptionConfiguration);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Response patchProductIdSubscriptionConfiguration(
			Long id,
			ProductSubscriptionConfiguration productSubscriptionConfiguration)
		throws Exception {

		CPDefinition cpDefinition =
			_cpDefinitionService.fetchCPDefinitionByCProductId(id);

		if (cpDefinition == null) {
			throw new NoSuchCPDefinitionException(
				"Unable to find Product with ID: " + id);
		}

		_updateProductSubscriptionConfiguration(
			cpDefinition, productSubscriptionConfiguration);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	private ProductSubscriptionConfiguration
			_updateProductSubscriptionConfiguration(
				CPDefinition cpDefinition,
				ProductSubscriptionConfiguration
					productSubscriptionConfiguration)
		throws Exception {

		cpDefinition =
			ProductSubscriptionConfigurationUtil.
				updateCPDefinitionSubscriptionInfo(
					_cpDefinitionService, productSubscriptionConfiguration,
					cpDefinition,
					_serviceContextHelper.getServiceContext(
						cpDefinition.getGroupId()));

		DTOConverter productSubscriptionConfigurationDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				"ProductSubscriptionConfiguration");

		return (ProductSubscriptionConfiguration)
			productSubscriptionConfigurationDTOConverter.toDTO(
				new DefaultDTOConverterContext(
					contextAcceptLanguage.getPreferredLocale(),
					cpDefinition.getCPDefinitionId()));
	}

	@Reference
	private CPDefinitionService _cpDefinitionService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}