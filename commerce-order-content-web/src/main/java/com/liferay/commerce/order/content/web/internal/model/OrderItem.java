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

package com.liferay.commerce.order.content.web.internal.model;

/**
 * @author Alessio Antonio Rendina
 */
public class OrderItem {

	public OrderItem(
		long orderItemId, long orderId, String sku, String name, String price,
		String discount, int quantity, String total, String thumbnail,
		String viewShipmentsURL) {

		_orderItemId = orderItemId;
		_orderId = orderId;
		_sku = sku;
		_name = name;
		_price = price;
		_discount = discount;
		_quantity = quantity;
		_total = total;
		_thumbnail = thumbnail;
		_viewShipmentsURL = viewShipmentsURL;
	}

	public String getDiscount() {
		return _discount;
	}

	public String getName() {
		return _name;
	}

	public long getOrderId() {
		return _orderId;
	}

	public long getOrderItemId() {
		return _orderItemId;
	}

	public String getPrice() {
		return _price;
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

	public String getTotal() {
		return _total;
	}

	public String getViewShipmentsURL() {
		return _viewShipmentsURL;
	}

	private final String _discount;
	private final String _name;
	private final long _orderId;
	private final long _orderItemId;
	private final String _price;
	private final int _quantity;
	private final String _sku;
	private final String _thumbnail;
	private final String _total;
	private final String _viewShipmentsURL;

}