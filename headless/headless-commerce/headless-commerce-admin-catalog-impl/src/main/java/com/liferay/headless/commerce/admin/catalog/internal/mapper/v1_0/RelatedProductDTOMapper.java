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

package com.liferay.headless.commerce.admin.catalog.internal.mapper.v1_0;

import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPDefinitionLink;
import com.liferay.commerce.product.model.CProduct;
import com.liferay.commerce.product.service.CPDefinitionLinkService;
import com.liferay.commerce.product.service.CPDefinitionService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.RelatedProduct;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = RelatedProductDTOMapper.class)
public class RelatedProductDTOMapper {

	public RelatedProduct toRelatedProduct(
		CPDefinitionLink cpDefinitionLink, String languageId) {

		RelatedProduct relatedProduct = new RelatedProduct();

		if (cpDefinitionLink == null) {
			return relatedProduct;
		}

		try {
			CProduct cProduct = cpDefinitionLink.getCProduct();

			CPDefinition cpDefinition = _cpDefinitionService.getCPDefinition(
				cProduct.getPublishedCPDefinitionId());

			relatedProduct.setProduct(
				_productDTOMapper.toProduct(cpDefinition, languageId));
		}
		catch (Exception e) {
			_log.error("Cannot instantiate Related Product ", e);

			throw new RuntimeException(e);
		}

		relatedProduct.setId(cpDefinitionLink.getCPDefinitionLinkId());
		relatedProduct.setPriority(cpDefinitionLink.getPriority());
		relatedProduct.setProductId(cpDefinitionLink.getCPDefinitionId());
		relatedProduct.setType(cpDefinitionLink.getType());

		return relatedProduct;
	}

	public RelatedProduct[] toRelatedProducts(
		CPDefinition cpDefinition, String languageId) {

		List<RelatedProduct> relatedProducts = new ArrayList<>();

		try {
			List<CPDefinitionLink> cpDefinitionLinks =
				_cpDefinitionLinkService.getCPDefinitionLinks(
					cpDefinition.getCPDefinitionId(), QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);

			for (CPDefinitionLink cpDefinitionLink : cpDefinitionLinks) {
				relatedProducts.add(
					toRelatedProduct(cpDefinitionLink, languageId));
			}

			Stream<RelatedProduct> stream = relatedProducts.stream();

			return stream.toArray(RelatedProduct[]::new);
		}
		catch (Exception e) {
			_log.error("Cannot instantiate Related Products ", e);

			throw new RuntimeException(e);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		RelatedProductDTOMapper.class);

	@Reference
	private CPDefinitionLinkService _cpDefinitionLinkService;

	@Reference
	private CPDefinitionService _cpDefinitionService;

	@Reference
	private ProductDTOMapper _productDTOMapper;

}