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

package com.liferay.headless.commerce.bom.internal.resource.v1_0;

import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPInstanceService;
import com.liferay.headless.commerce.bom.dto.v1_0.Product;
import com.liferay.headless.commerce.bom.resource.v1_0.ProductResource;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterRegistry;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.vulcan.pagination.Page;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/product.properties",
	scope = ServiceScope.PROTOTYPE, service = ProductResource.class
)
public class ProductResourceImpl extends BaseProductResourceImpl {

	@Override
	public Page<Product> getProductsPage(String q) throws Exception {
		BaseModelSearchResult<CPInstance> cpInstanceBaseModelSearchResult =
			_cpInstanceService.searchCPInstances(
				contextCompany.getCompanyId(), q,
				WorkflowConstants.STATUS_APPROVED, 0, 20, null);

		return Page.of(
			_toProducts(cpInstanceBaseModelSearchResult.getBaseModels()), null,
			cpInstanceBaseModelSearchResult.getLength());
	}

	private List<Product> _toProducts(List<CPInstance> cpInstances)
		throws Exception {

		List<Product> products = new ArrayList<>();

		DTOConverter productDTOConverter =
			_dtoConverterRegistry.getDTOConverter("commerceProductInstance");

		for (CPInstance cpInstance : cpInstances) {
			products.add(
				(Product)productDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						contextAcceptLanguage.getPreferredLocale(),
						cpInstance.getCPInstanceId())));
		}

		return products;
	}

	@Reference
	private CPInstanceService _cpInstanceService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

}