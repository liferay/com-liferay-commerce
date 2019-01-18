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

package com.liferay.commerce.frontend.internal.cart.model;

/**
 * @author Alessio Antonio Rendina
 */
public class Product {

	public Product(
		long id, String name, String sku, int quantity, String thumbnail,
		Prices prices, Settings settings, String[] errorMessages) {

		_id = id;
		_name = name;
		_sku = sku;
		_quantity = quantity;
		_thumbnail = thumbnail;
		_prices = prices;
		_settings = settings;
		_errorMessages = errorMessages;
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

	public Prices getPrices() {
		return _prices;
	}

	public int getQuantity() {
		return _quantity;
	}

	public Settings getSettings() {
		return _settings;
	}

	public String getSku() {
		return _sku;
	}

	public String getThumbnail() {
		return _thumbnail;
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

	public void setPrices(Prices prices) {
		_prices = prices;
	}

	public void setQuantity(int quantity) {
		_quantity = quantity;
	}

	public void setSettings(Settings settings) {
		_settings = settings;
	}

	public void setSku(String sku) {
		_sku = sku;
	}

	public void setThumbnail(String thumbnail) {
		_thumbnail = thumbnail;
	}

	private String[] _errorMessages;
	private long _id;
	private String _name;
	private Prices _prices;
	private int _quantity;
	private Settings _settings;
	private String _sku;
	private String _thumbnail;

}