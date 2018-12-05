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

package com.liferay.commerce.cart.taglib.servlet.taglib.internal.application.domain.model;

import java.util.List;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceCart {

	public CommerceCart(
		List<CommerceCartProduct> commerceCartProducts,
		CommerceCartSummary commerceCartSummary) {

		_commerceCartProducts = commerceCartProducts;
		_commerceCartSummary = commerceCartSummary;
		_success = true;
	}

	public CommerceCart(String[] errorMessages) {
		_errorMessages = errorMessages;
		_success = false;
	}

	public List<CommerceCartProduct> getCommerceCartProducts() {
		return _commerceCartProducts;
	}

	public CommerceCartSummary getCommerceCartSummary() {
		return _commerceCartSummary;
	}

	public String[] getErrorMessages() {
		return _errorMessages;
	}

	public boolean getSuccess() {
		return _success;
	}

	public void setErrorMessages(String[] errorMessages) {
		_errorMessages = errorMessages;
	}

	public void setSuccess(boolean success) {
		_success = success;
	}

	private List<CommerceCartProduct> _commerceCartProducts;
	private CommerceCartSummary _commerceCartSummary;
	private String[] _errorMessages;
	private boolean _success;

}