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
public class AccountOrganization {

	public AccountOrganization(
		long organizationId, String name, String email, String thumbnail) {

		_organizationId = organizationId;
		_name = name;
		_email = email;
		_success = true;
		_thumbnail = thumbnail;
	}

	public AccountOrganization(String[] errorMessages) {
		_errorMessages = errorMessages;
		_success = false;
	}

	public String getEmail() {
		return _email;
	}

	public String[] getErrorMessages() {
		return _errorMessages;
	}

	public String getName() {
		return _name;
	}

	public long getOrganizationId() {
		return _organizationId;
	}

	public boolean getSuccess() {
		return _success;
	}

	public String getThumbnail() {
		return _thumbnail;
	}

	public void setErrorMessages(String[] errorMessages) {
		_errorMessages = errorMessages;
	}

	public void setSuccess(boolean success) {
		_success = success;
	}

	private String _email;
	private String[] _errorMessages;
	private String _name;
	private long _organizationId;
	private boolean _success;
	private String _thumbnail;

}