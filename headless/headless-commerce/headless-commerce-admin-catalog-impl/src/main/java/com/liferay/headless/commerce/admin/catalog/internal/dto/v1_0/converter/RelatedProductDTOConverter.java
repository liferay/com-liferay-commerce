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

package com.liferay.headless.commerce.admin.catalog.internal.dto.v1_0.converter;

import com.liferay.commerce.product.model.CPDefinitionLink;
import com.liferay.commerce.product.model.CProduct;
import com.liferay.commerce.product.service.CPDefinitionLinkService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.RelatedProduct;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = "model.class.name=com.liferay.commerce.product.model.CPDefinitionLink",
	service = {DTOConverter.class, RelatedProductDTOConverter.class}
)
public class RelatedProductDTOConverter implements DTOConverter {

	@Override
	public String getContentType() {
		return RelatedProduct.class.getSimpleName();
	}

	public RelatedProduct toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CPDefinitionLink cpDefinitionLink =
			_cpDefinitionLinkService.getCPDefinitionLink(
				dtoConverterContext.getResourcePrimKey());

		CProduct cProduct = cpDefinitionLink.getCProduct();

		return new RelatedProduct() {
			{
				id = cpDefinitionLink.getCPDefinitionLinkId();
				priority = cpDefinitionLink.getPriority();
				productExternalReferenceCode =
					cProduct.getExternalReferenceCode();
				productId = cProduct.getCProductId();
				type = cpDefinitionLink.getType();
			}
		};
	}

	@Reference
	private CPDefinitionLinkService _cpDefinitionLinkService;

}