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

import com.liferay.commerce.frontend.model.PriceModel;
import com.liferay.commerce.frontend.model.ProductSettingsModel;
import com.liferay.portal.kernel.util.KeyValuePair;

import java.util.List;

/**
 * @author Alessio Antonio Rendina
 */
public class Product {

	public Product(
		long id, String name, String sku, int quantity, String thumbnail,
		PriceModel prices, ProductSettingsModel settings,
		String[] errorMessages, long cpInstanceId) {

		_id = id;
		_name = name;
		_sku = sku;
		_quantity = quantity;
		_thumbnail = thumbnail;
		_prices = prices;
		_settings = settings;
		_errorMessages = errorMessages;
		_cpInstanceId = cpInstanceId;
	}

	public long getCPInstanceId() {
		return _cpInstanceId;
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

	public List<KeyValuePair> getOptions() {
		return _options;
	}

	public PriceModel getPrices() {
		return _prices;
	}

	public int getQuantity() {
		return _quantity;
	}

	public ProductSettingsModel getSettings() {
		return _settings;
	}

	public String getSku() {
		return _sku;
	}

	public String getThumbnail() {
		return _thumbnail;
	}

	public void setCPInstanceId(long cpInstanceId) {
		_cpInstanceId = cpInstanceId;
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

	public void setOptions(List<KeyValuePair> options) {
		_options = options;
	}

	public void setPrices(PriceModel prices) {
		_prices = prices;
	}

	public void setQuantity(int quantity) {
		_quantity = quantity;
	}

	public void setSettings(ProductSettingsModel settings) {
		_settings = settings;
	}

	public void setSku(String sku) {
		_sku = sku;
	}

	public void setThumbnail(String thumbnail) {
		_thumbnail = thumbnail;
	}

	private long _cpInstanceId;
	private String[] _errorMessages;
	private long _id;
	private String _name;
	private List<KeyValuePair> _options;
	private PriceModel _prices;
	private int _quantity;
	private ProductSettingsModel _settings;
	private String _sku;
	private String _thumbnail;

}