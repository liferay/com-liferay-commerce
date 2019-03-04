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

package com.liferay.commerce.batch.engine.impl.internal.item;

import com.liferay.commerce.batch.engine.api.exception.IllegalFileNameException;
import com.liferay.commerce.batch.engine.api.item.ItemComponent;
import com.liferay.commerce.batch.engine.api.item.Operation;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Ivica Cardic
 */
public class ItemComponentFactoryImplTest {

	@Test(expected = IllegalFileNameException.class)
	public void testInvalidFileName() {
		_itemComponentFactory.getItemComponent("Product_v10_create.json");
	}

	@Test
	public void testValidFileName() {
		ItemComponent itemComponent = _itemComponentFactory.getItemComponent(
			"product_v1_0_create.json");

		Assert.assertEquals(Operation.CREATE, itemComponent.getOperation());
		Assert.assertEquals("product", itemComponent.getType());
		Assert.assertEquals("v1.0", itemComponent.getVersion());

		itemComponent = _itemComponentFactory.getItemComponent(
			"Product_v11_2_CREATE.json");

		Assert.assertEquals(Operation.CREATE, itemComponent.getOperation());
		Assert.assertEquals("product", itemComponent.getType());
		Assert.assertEquals("v11.2", itemComponent.getVersion());
	}

	private final ItemComponentFactoryImpl _itemComponentFactory =
		new ItemComponentFactoryImpl();

}