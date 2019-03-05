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

package com.liferay.commerce.openapi.core.internal.jaxrs.nested.resource;

import com.liferay.commerce.openapi.core.annotation.Nested;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.internal.jaxrs.nested.dto.ProductOptionDTO;
import com.liferay.commerce.openapi.core.internal.jaxrs.nested.dto.SkuDTO;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.core.Context;

/**
 * @author Ivica Cardic
 */
public class ProductResourceImpl implements ProductResource {

	@Nested("productOptions")
	@Override
	public List<ProductOptionDTO> getProductOptions(
		Long id, String name, Date createDate, Pagination pagination) {

		if (id != 1) {
			return Collections.emptyList();
		}

		List<ProductOptionDTO> productOptionDTOs = Arrays.asList(
			_getProductOptionDTO(1L, "test1"),
			_getProductOptionDTO(2L, "test2"),
			_getProductOptionDTO(3L, "test3"));

		if (name != null) {
			Stream<ProductOptionDTO> productOptionDTOStream =
				productOptionDTOs.stream();

			productOptionDTOs = productOptionDTOStream.filter(
				productOptionDTO ->
					Objects.equals(productOptionDTO.getName(), name)
			).collect(
				Collectors.toList()
			);
		}

		return productOptionDTOs;
	}

	@Nested("skus")
	@Override
	public CollectionDTO<SkuDTO> getSkus(String id, Pagination pagination) {
		if (!Objects.equals(id, "1")) {
			return new CollectionDTO<>(Collections.emptyList(), 0);
		}

		List<SkuDTO> skuDTOs = Arrays.asList(
			_getSkuDTO(1L), _getSkuDTO(2L), _getSkuDTO(3L), _getSkuDTO(4L));

		skuDTOs = skuDTOs.subList(
			pagination.getStartPosition(),
			Math.min(pagination.getEndPosition(), skuDTOs.size()));

		return new CollectionDTO<>(skuDTOs, skuDTOs.size());
	}

	@Context
	public ThemeDisplay themeDisplay;

	private ProductOptionDTO _getProductOptionDTO(long id, String name) {
		ProductOptionDTO productOptionDTO = new ProductOptionDTO();

		productOptionDTO.setId(id);
		productOptionDTO.setName(name);

		return productOptionDTO;
	}

	private SkuDTO _getSkuDTO(long id) {
		SkuDTO skuDTO = new SkuDTO();

		skuDTO.setId(id);

		return skuDTO;
	}

}