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

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceCartProduct {

	public CommerceCartProduct(
		long id, String name, String sku, int quantity, String thumbnail,
		CommerceCartProductPrice commerceCartProductPrice,
		CommerceCartProductSettings commerceCartProductSettings,
		String[] errorMessages) {

		_id = id;
		_name = name;
		_sku = sku;
		_quantity = quantity;
		_thumbnail = thumbnail;
		_commerceCartProductPrice = commerceCartProductPrice;
		_commerceCartProductSettings = commerceCartProductSettings;
		_errorMessages = errorMessages;
	}

	public CommerceCartProductPrice getCommerceCartProductPrice() {
		return _commerceCartProductPrice;
	}

	public CommerceCartProductSettings getCommerceCartProductSettings() {
		return _commerceCartProductSettings;
	}

	public String[] getErrorMessages() {
		return _errorMessages;
	}

	public long getId() {
		return _id;
	}

	public String getName() {
		return _name;
	}

	public int getQuantity() {
		return _quantity;
	}

	public String getSku() {
		return _sku;
	}

	public String getThumbnail() {
		return _thumbnail;
	}

	public void setCommerceCartProductPrice(
		CommerceCartProductPrice commerceCartProductPrice) {

		_commerceCartProductPrice = commerceCartProductPrice;
	}

	public void setCommerceCartProductSettings(
		CommerceCartProductSettings commerceCartProductSettings) {

		_commerceCartProductSettings = commerceCartProductSettings;
	}

	public void setErrorMessages(String[] errorMessages) {
		_errorMessages = errorMessages;
	}

	public void setId(long id) {
		_id = id;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setQuantity(int quantity) {
		_quantity = quantity;
	}

	public void setSku(String sku) {
		_sku = sku;
	}

	public void setThumbnail(String thumbnail) {
		_thumbnail = thumbnail;
	}

	private CommerceCartProductPrice _commerceCartProductPrice;
	private CommerceCartProductSettings _commerceCartProductSettings;
	private String[] _errorMessages;
	private long _id;
	private String _name;
	private int _quantity;
	private String _sku;
	private String _thumbnail;

}