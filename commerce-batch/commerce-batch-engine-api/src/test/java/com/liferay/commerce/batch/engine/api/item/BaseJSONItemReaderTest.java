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

import com.liferay.commerce.batch.engine.api.item.model.ProductDTO;

import java.io.File;
import java.io.FileInputStream;

import java.net.URL;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Ivica Cardic
 */
public class BaseJSONItemReaderTest {

	@Test
	public void testRead() throws Exception {
		URL url = BaseJSONItemReaderTest.class.getResource(
			"dependencies/Product_v1_0_CREATE.json");

		_productItemReader.setInputStream(
			new FileInputStream(new File(url.getFile())));

		ProductDTO product = _productItemReader.read();

		Assert.assertEquals(1, product.getId());

		product = _productItemReader.read();

		Assert.assertEquals(2, product.getId());

		product = _productItemReader.read();

		Assert.assertNull(product);
	}

	private final ProductItemReader _productItemReader =
		new ProductItemReader();

	private static class ProductItemReader
		extends BaseJSONItemReader<ProductDTO> {

		@Override
		public Class<ProductDTO> getType() {
			return ProductDTO.class;
		}

	}

}