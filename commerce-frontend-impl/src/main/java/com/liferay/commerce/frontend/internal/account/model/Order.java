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

package com.liferay.commerce.frontend.internal.account.model;

/**
 * @author Alessio Antonio Rendina
 */
public class Order {

	public Order(long orderId, long accountId, String purchaseOrderNumber) {
		_orderId = orderId;
		_accountId = accountId;
		_purchaseOrderNumber = purchaseOrderNumber;
		_success = true;
	}

	public Order(String[] errorMessages) {
		_errorMessages = errorMessages;
		_success = false;
	}

	public long getAccountId() {
		return _accountId;
	}

	public String[] getErrorMessages() {
		return _errorMessages;
	}

	public long getOrderId() {
		return _orderId;
	}

	public String getPurchaseOrderNumber() {
		return _purchaseOrderNumber;
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

	private long _accountId;
	private String[] _errorMessages;
	private long _orderId;
	private String _purchaseOrderNumber;
	private boolean _success;

}