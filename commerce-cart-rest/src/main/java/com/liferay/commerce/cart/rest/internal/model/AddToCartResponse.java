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
public class AddToCartResponse {

	public AddToCartResponse(
		Cart cart, int productsQuantity, boolean success, String[] messages) {

		_cart = cart;
		_productsQuantity = productsQuantity;
		_success = success;
		_messages = messages;
	}

	public AddToCartResponse(String[] errorMessages) {
		this(null, 0, false, errorMessages);
	}

	public Cart getCart() {
		return _cart;
	}

	public String[] getMessages() {
		return _messages;
	}

	public int getProductsQuantity() {
		return _productsQuantity;
	}

	public boolean getSuccess() {
		return _success;
	}

	public void setProductsQuantity(int productsQuantity) {
		_productsQuantity = productsQuantity;
	}

	private Cart _cart;
	private String[] _messages;
	private int _productsQuantity;
	private boolean _success;

}