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
import com.liferay.commerce.product.exception.NoSuchCPDefinitionSpecificationOptionValueException;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPDefinitionSpecificationOptionValue;
import com.liferay.commerce.product.service.CPDefinitionService;
import com.liferay.commerce.product.service.CPDefinitionSpecificationOptionValueService;
import com.liferay.commerce.product.service.CPSpecificationOptionService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductSpecification;
import com.liferay.headless.commerce.admin.catalog.internal.util.v1_0.ProductSpecificationUtil;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.ProductSpecificationResource;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterRegistry;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/product-specification.properties",
	scope = ServiceScope.PROTOTYPE, service = ProductSpecificationResource.class
)
public class ProductSpecificationResourceImpl
	extends BaseProductSpecificationResourceImpl {

	@Override
	public Page<ProductSpecification> getProductIdProductSpecificationsPage(
			Long id, Pagination pagination)
		throws Exception {

		CPDefinition cpDefinition =
			_cpDefinitionService.fetchCPDefinitionByCProductId(id);

		if (cpDefinition == null) {
			throw new NoSuchCPDefinitionException(
				"Unable to find Product with ID: " + id);
		}

		List<CPDefinitionSpecificationOptionValue>
			cpDefinitionSpecificationOptionValues =
				_cpDefinitionSpecificationOptionValueService.
					getCPDefinitionSpecificationOptionValues(
						cpDefinition.getCPDefinitionId(),
						pagination.getStartPosition(),
						pagination.getEndPosition(), null);

		int totalItems =
			_cpDefinitionSpecificationOptionValueService.
				getCPDefinitionSpecificationOptionValuesCount(
					cpDefinition.getCPDefinitionId());

		return Page.of(
			_toProductSpecifications(cpDefinitionSpecificationOptionValues),
			pagination, totalItems);
	}

	@Override
	public ProductSpecification postProductIdProductSpecification(
			Long id, ProductSpecification productSpecification)
		throws Exception {

		return _upsertProductSpecification(id, productSpecification);
	}

	private List<ProductSpecification> _toProductSpecifications(
			List<CPDefinitionSpecificationOptionValue>
				cpDefinitionSpecificationOptionValues)
		throws Exception {

		List<ProductSpecification> productSpecifications = new ArrayList<>();

		DTOConverter productSpecificationDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CPDefinitionSpecificationOptionValue.class.getName());

		for (CPDefinitionSpecificationOptionValue
				cpDefinitionSpecificationOptionValue :
					cpDefinitionSpecificationOptionValues) {

			productSpecifications.add(
				(ProductSpecification)productSpecificationDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						contextAcceptLanguage.getPreferredLocale(),
						cpDefinitionSpecificationOptionValue.
							getCPDefinitionSpecificationOptionValueId())));
		}

		return productSpecifications;
	}

	private CPDefinitionSpecificationOptionValue _updateProductSpecification(
			Long id, ProductSpecification productSpecification)
		throws PortalException {

		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue =
				_cpDefinitionSpecificationOptionValueService.
					getCPDefinitionSpecificationOptionValue(id);

		return ProductSpecificationUtil.
			updateCPDefinitionSpecificationOptionValue(
				_cpDefinitionSpecificationOptionValueService,
				cpDefinitionSpecificationOptionValue, productSpecification,
				_serviceContextHelper.getServiceContext());
	}

	private ProductSpecification _upsertProductSpecification(
			Long id, ProductSpecification productSpecification)
		throws Exception {

		try {
			CPDefinitionSpecificationOptionValue
				cpDefinitionSpecificationOptionValue =
					_updateProductSpecification(
						productSpecification.getId(), productSpecification);

			DTOConverter productSpecificationDTOConverter =
				_dtoConverterRegistry.getDTOConverter(
					CPDefinitionSpecificationOptionValue.class.getName());

			return (ProductSpecification)productSpecificationDTOConverter.toDTO(
				new DefaultDTOConverterContext(
					contextAcceptLanguage.getPreferredLocale(),
					cpDefinitionSpecificationOptionValue.
						getCPDefinitionSpecificationOptionValueId()));
		}
		catch (NoSuchCPDefinitionSpecificationOptionValueException nscpdsove) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to find productSpecification with ID: " +
						productSpecification.getId());
			}
		}

		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue =
				ProductSpecificationUtil.
					addCPDefinitionSpecificationOptionValue(
						_cpDefinitionSpecificationOptionValueService,
						_cpSpecificationOptionService, id, productSpecification,
						_serviceContextHelper.getServiceContext());

		DTOConverter productSpecificationDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CPDefinitionSpecificationOptionValue.class.getName());

		return (ProductSpecification)productSpecificationDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				cpDefinitionSpecificationOptionValue.
					getCPDefinitionSpecificationOptionValueId()));
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ProductSpecificationResourceImpl.class);

	@Reference
	private CPDefinitionService _cpDefinitionService;

	@Reference
	private CPDefinitionSpecificationOptionValueService
		_cpDefinitionSpecificationOptionValueService;

	@Reference
	private CPSpecificationOptionService _cpSpecificationOptionService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}