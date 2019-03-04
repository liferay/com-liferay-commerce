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
import com.liferay.commerce.batch.engine.api.item.ItemComponentFactory;
import com.liferay.commerce.batch.engine.api.item.Operation;
import com.liferay.portal.kernel.util.StringUtil;

import org.osgi.service.component.annotations.Component;

/**
 * @author Ivica Cardic
 */
@Component(immediate = true, service = ItemComponentFactory.class)
public class ItemComponentFactoryImpl implements ItemComponentFactory {

	public ItemComponent getItemComponent(String fileName) {
		ItemComponent itemComponent = new ItemComponent();

		String[] items = fileName.split("\\.");

		try {
			itemComponent.setContentType(StringUtil.toLowerCase(items[1]));

			items = items[0].split("_");

			itemComponent.setType(StringUtil.toLowerCase(items[0]));
			itemComponent.setVersion(
				StringUtil.toLowerCase(items[1]) + "." +
					StringUtil.toLowerCase(items[2]));
			itemComponent.setOperation(
				Operation.valueOf(StringUtil.toUpperCase(items[3])));
		}
		catch (IndexOutOfBoundsException ioobe) {
			throw new IllegalFileNameException(fileName + " is illegal", ioobe);
		}

		return itemComponent;
	}

}