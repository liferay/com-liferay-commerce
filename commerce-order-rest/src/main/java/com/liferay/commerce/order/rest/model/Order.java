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

package com.liferay.commerce.order.rest.model;

import java.util.Date;

/**
 * @author Alessio Antonio Rendina
 */
public class Order {

	public Order(long orderId, int orderStatus, Date modifiedDate) {
		_orderId = orderId;
		_orderStatus = orderStatus;
		_modifiedDate = modifiedDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public long getOrderId() {
		return _orderId;
	}

	public int getOrderStatus() {
		return _orderStatus;
	}

	private final Date _modifiedDate;
	private final long _orderId;
	private final int _orderStatus;

}