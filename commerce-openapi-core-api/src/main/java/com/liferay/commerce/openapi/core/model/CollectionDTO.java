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

package com.liferay.commerce.openapi.core.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.Collection;
import java.util.Collections;

/**
 * @author Ivica Cardic
 */
@JacksonXmlRootElement(localName = "collection")
public class CollectionDTO<T> {

	public CollectionDTO() {
		_items = Collections.emptyList();
		_totalItems = 0;
	}

	public CollectionDTO(Collection<T> items, int totalItems) {
		_items = items;
		_totalItems = totalItems;
	}

	@JacksonXmlElementWrapper(localName = "items")
	@JacksonXmlProperty(localName = "item")
	public Collection<T> getItems() {
		return _items;
	}

	public int getNumberOfItems() {
		return _items.size();
	}

	public int getTotalItems() {
		return _totalItems;
	}

	private final Collection<T> _items;
	private final int _totalItems;

}