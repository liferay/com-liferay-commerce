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

package com.liferay.commerce.cart.rest.internal.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Alessio Antonio Rendina
 */
@XmlRootElement(name = "cartItem")
public class CartItemUpdate {

	@XmlElement
	public String getOptionsJSON() {
		return _optionsJSON;
	}

	@XmlElement
	public long getProductId() {
		return _productId;
	}

	@XmlElement
	public int getQuantity() {
		return _quantity;
	}

	public void setOptionsJSON(String optionsJSON) {
		_optionsJSON = optionsJSON;
	}

	public void setProductId(long productId) {
		_productId = productId;
	}

	public void setQuantity(int quantity) {
		_quantity = quantity;
	}

	private String _optionsJSON;
	private long _productId;
	private int _quantity;

}