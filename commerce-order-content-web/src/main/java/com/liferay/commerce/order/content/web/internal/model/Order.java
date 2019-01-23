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
public class Order {

	public Order(
		long orderId, String accountName, String createDate, String author,
		String orderStatus, String status, String amount, String href) {

		_orderId = orderId;
		_accountName = accountName;
		_createDate = createDate;
		_author = author;
		_orderStatus = orderStatus;
		_status = status;
		_amount = amount;
		_href = href;
	}

	public String getAccountName() {
		return _accountName;
	}

	public String getAmount() {
		return _amount;
	}

	public String getAuthor() {
		return _author;
	}

	public String getCreateDate() {
		return _createDate;
	}

	public String getHref() {
		return _href;
	}

	public long getOrderId() {
		return _orderId;
	}

	public String getOrderStatus() {
		return _orderStatus;
	}

	public String getStatus() {
		return _status;
	}

	private final String _accountName;
	private final String _amount;
	private final String _author;
	private final String _createDate;
	private final String _href;
	private final long _orderId;
	private final String _orderStatus;
	private final String _status;

}