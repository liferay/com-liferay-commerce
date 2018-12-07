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

/**
 * @author Alessio Antonio Rendina
 */
public class Prices {

	public Prices(String price) {
		_price = price;
	}

	public String getDiscount() {
		return _discount;
	}

	public String getPrice() {
		return _price;
	}

	public String getPromoPrice() {
		return _promoPrice;
	}

	public void sePrice(String price) {
		_price = price;
	}

	public void setDiscount(String discount) {
		_discount = discount;
	}

	public void setPromoPrice(String promoPrice) {
		_promoPrice = promoPrice;
	}

	private String _discount;
	private String _price;
	private String _promoPrice;

}