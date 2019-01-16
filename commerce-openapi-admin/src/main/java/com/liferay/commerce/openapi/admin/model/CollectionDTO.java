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

package com.liferay.commerce.openapi.admin.model;

import java.util.Collection;
import java.util.Collections;

import javax.annotation.Generated;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * @author Igor Beslic
 */
@Generated(value = "OSGiRESTModuleGenerator")
@XmlRootElement(name = "collection")
@XmlSeeAlso({PriceEntryDTO.class, WebSiteDTO.class, ProductDTO.class, PriceListDTO.class})
public class CollectionDTO<T> {

	public CollectionDTO() {
		_items = Collections.emptyList();
		_totalItems = 0;
	}

	public CollectionDTO(Collection<T> items, int totalItems) {
		_items = items;
		_totalItems = totalItems;
	}

	@XmlElement(name = "item")
	public Collection<T> getItems() {
		return _items;
	}

	@XmlElement
	public int getNumberOfItems() {
		return _items.size();
	}

	@XmlElement
	public int getTotalItems() {
		return _totalItems;
	}

	private final Collection<T> _items;
	private final int _totalItems;

}