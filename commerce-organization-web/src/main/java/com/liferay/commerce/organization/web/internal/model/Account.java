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

package com.liferay.commerce.organization.web.internal.model;

/**
 * @author Alessio Antonio Rendina
 */
public class Account {

	public Account(
		long accountId, String name, String email, String address,
		String thumbnail, String href) {

		_accountId = accountId;
		_name = name;
		_email = email;
		_address = address;
		_thumbnail = thumbnail;
		_href = href;
	}

	public long getAccountId() {
		return _accountId;
	}

	public String getAddress() {
		return _address;
	}

	public String getEmail() {
		return _email;
	}

	public String getHref() {
		return _href;
	}

	public String getName() {
		return _name;
	}

	public String getThumbnail() {
		return _thumbnail;
	}

	private final long _accountId;
	private final String _address;
	private final String _email;
	private final String _href;
	private final String _name;
	private final String _thumbnail;

}