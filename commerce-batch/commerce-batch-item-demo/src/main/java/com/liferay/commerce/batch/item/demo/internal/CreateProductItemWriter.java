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

package com.liferay.commerce.batch.item.demo.internal;

import com.liferay.commerce.batch.engine.api.item.ItemWriter;
import com.liferay.commerce.batch.item.demo.internal.model.ProductDTO;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Ivica Cardic
 */
@Component(
	property = {"operation=create", "type=product", "version=v1.0"},
	scope = ServiceScope.PROTOTYPE, service = ItemWriter.class
)
public class CreateProductItemWriter implements ItemWriter<ProductDTO> {

	@Override
	public void write(List<? extends ProductDTO> items) throws Exception {
		System.out.println(items);
	}

}