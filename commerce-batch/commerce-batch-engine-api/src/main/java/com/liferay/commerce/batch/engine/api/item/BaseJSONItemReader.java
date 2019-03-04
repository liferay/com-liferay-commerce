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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import com.liferay.commerce.batch.engine.api.exception.ParseException;

import java.io.BufferedInputStream;
import java.io.IOException;

import java.util.List;

/**
 * @author Ivica Cardic
 */
public abstract class BaseJSONItemReader<T>
	extends BaseItemReader<T> implements ItemReader<T> {

	public abstract Class<T> getType();

	@Override
	protected T doRead() throws Exception, ParseException {
		List<T> items = _getItems();

		if (currentItemCount < items.size()) {
			return items.get(currentItemCount);
		}

		return null;
	}

	private List<T> _getItems() throws ParseException {
		try {
			if (_items == null) {
				TypeFactory typeFactory = _OBJECT_MAPPER.getTypeFactory();

				_items = _OBJECT_MAPPER.readValue(
					new BufferedInputStream(inputStream),
					typeFactory.constructCollectionType(List.class, getType()));
			}
		}
		catch (IOException ioe) {
			throw new ParseException(ioe.getMessage(), ioe);
		}

		return _items;
	}

	private static final ObjectMapper _OBJECT_MAPPER = new ObjectMapper();

	private List<T> _items;

}