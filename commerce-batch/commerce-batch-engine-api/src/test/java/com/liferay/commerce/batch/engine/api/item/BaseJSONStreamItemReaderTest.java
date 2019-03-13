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

package com.liferay.commerce.batch.engine.api.item;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import com.liferay.commerce.batch.engine.api.item.model.ProductDTO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Ivica Cardic
 */
public class BaseJSONStreamItemReaderTest {

	@Test
	public void testRead() throws Exception {
		URL url = BaseJSONStreamItemReaderTest.class.getResource(
			"dependencies/Product_v1_0_CREATE.json");

		_productItemReader.setInputStream(
			new FileInputStream(new File(url.getFile())));

		ProductDTO product = _productItemReader.read();

		Assert.assertEquals(1, product.getId());
		Assert.assertEquals("product1", product.getName());

		product = _productItemReader.read();

		Assert.assertEquals(2, product.getId());
		Assert.assertEquals("product2", product.getName());

		product = _productItemReader.read();

		Assert.assertNull(product);
	}

	private final ProductItemReader _productItemReader =
		new ProductItemReader();

	private static class ProductItemReader
		extends BaseJSONStreamItemReader<ProductDTO> {

		@Override
		protected ProductDTO doRead(JsonParser jsonParser) throws IOException {
			ProductDTO productDTO = new ProductDTO();

			while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
				String currentName = jsonParser.getCurrentName();

				if ("id".equals(currentName)) {
					jsonParser.nextToken();

					productDTO.setId(jsonParser.getLongValue());
				}

				if ("name".equals(currentName)) {
					jsonParser.nextToken();

					productDTO.setName(jsonParser.getText());
				}

				if ("statusIds".equals(currentName)) {
					List<Long> statusIds = new ArrayList<>();

					jsonParser.nextToken();

					while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
						statusIds.add(jsonParser.getLongValue());
					}

					Stream<Long> statusIdsStream = statusIds.stream();

					productDTO.setStatusIds(
						statusIdsStream.mapToLong(
							Long::longValue
						).toArray());
				}
			}

			return productDTO;
		}

	}

}