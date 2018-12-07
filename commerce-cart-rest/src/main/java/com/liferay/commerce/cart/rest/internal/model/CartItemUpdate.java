/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 * <p>
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * <p>
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.commerce.cart.rest.internal.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Marco Leo
 */
@XmlRootElement(name = "cartItem")
public class CartItemUpdate {

	public void setQuantity(int quantity){
		_quantity = quantity;
	}

	@XmlElement
	public int getQuantity(){
		return _quantity;
	}

	private int _quantity;
}
