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

package com.liferay.commerce.organization.web.internal.organization.model;

import java.util.List;

/**
 * @author Alessio Antonio Rendina
 */
public class AccountList {

	public AccountList(List<Account> accounts, int total) {
		_accounts = accounts;
		_total = total;
	}

	public AccountList(String[] errorMessages) {
		_errorMessages = errorMessages;
		_success = false;
	}

	public List<Account> getAccounts() {
		return _accounts;
	}

	public int getTotal() {
		return _total;
	}

	public void setErrorMessages(String[] errorMessages) {
		_errorMessages = errorMessages;
	}

	public void setSuccess(boolean success) {
		_success = success;
	}

	private List<Account> _accounts;
	private String[] _errorMessages;
	private boolean _success;
	private int _total;

}