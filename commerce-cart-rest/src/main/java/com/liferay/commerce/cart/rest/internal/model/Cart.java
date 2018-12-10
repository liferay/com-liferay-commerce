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

import java.util.List;

/**
 * @author Alessio Antonio Rendina
 */
public class Cart {

	public Cart(
		List<Product> products, Summary summary, boolean success,
		String[] errorMessages) {

		_products = products;
		_summary = summary;
		_success = success;
		_errorMessages = errorMessages;
	}

	public String[] getErrorMessages() {
		return _errorMessages;
	}

	public List<Product> getProducts() {
		return _products;
	}

	public boolean getSuccess() {
		return _success;
	}

	public Summary getSummary() {
		return _summary;
	}

	public void setErrorMessages(String[] errorMessages) {
		_errorMessages = errorMessages;
	}

	public void setSuccess(boolean success) {
		_success = success;
	}

	private String[] _errorMessages;
	private final List<Product> _products;
	private boolean _success;
	private final Summary _summary;

}