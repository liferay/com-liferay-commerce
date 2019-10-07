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

package com.liferay.commerce.order.web.internal.model;

/**
 * @author Alessio Antonio Rendina
 */
public class ShipmentItem {

	public ShipmentItem(
		long shipmentItemId, long orderId, String sku, int orderedCount,
		int shippedCount, int toSend) {

		_shipmentItemId = shipmentItemId;
		_orderId = orderId;
		_sku = sku;
		_orderedCount = orderedCount;
		_shippedCount = shippedCount;
		_toSend = toSend;
	}

	public int getOrderedCount() {
		return _orderedCount;
	}

	public long getOrderId() {
		return _orderId;
	}

	public long getShipmentItemId() {
		return _shipmentItemId;
	}

	public int getShippedCount() {
		return _shippedCount;
	}

	public String getSku() {
		return _sku;
	}

	public int getToSend() {
		return _toSend;
	}

	private final int _orderedCount;
	private final long _orderId;
	private final long _shipmentItemId;
	private final int _shippedCount;
	private final String _sku;
	private final int _toSend;

}