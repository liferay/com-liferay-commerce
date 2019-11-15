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
public class Organization {

	public Organization(
		long organizationId, String name, List<Organization> organizations,
		int organizationsTotal, int accountsTotal, int usersTotal) {

		_organizationId = organizationId;
		_name = name;
		_organizations = organizations;
		_organizationsTotal = organizationsTotal;
		_accountsTotal = accountsTotal;
		_usersTotal = usersTotal;

		if (_organizationsTotal > 0) {
			_lastLevel = false;
		}
		else {
			_lastLevel = true;
		}

		_success = true;
	}

	public Organization(String[] errorMessages) {
		_errorMessages = errorMessages;
		_success = false;
	}

	public int getAccountsTotal() {
		return _accountsTotal;
	}

	public String[] getErrorMessages() {
		return _errorMessages;
	}

	public boolean getLastLevel() {
		return _lastLevel;
	}

	public String getName() {
		return _name;
	}

	public long getOrganizationId() {
		return _organizationId;
	}

	public List<Organization> getOrganizations() {
		return _organizations;
	}

	public int getOrganizationsTotal() {
		return _organizationsTotal;
	}

	public boolean getSuccess() {
		return _success;
	}

	public int getTotal() {
		return _total;
	}

	public int getUsersTotal() {
		return _usersTotal;
	}

	public void setErrorMessages(String[] errorMessages) {
		_errorMessages = errorMessages;
	}

	public void setSuccess(boolean success) {
		_success = success;
	}

	private int _accountsTotal;
	private String[] _errorMessages;
	private boolean _lastLevel;
	private String _name;
	private long _organizationId;
	private List<Organization> _organizations;
	private int _organizationsTotal;
	private boolean _success;
	private int _total;
	private int _usersTotal;

}