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
public class User {

	public User(
		long userId, long organizationId, String name, String email,
		String[] roles, String href) {

		_userId = userId;
		_organizationId = organizationId;
		_name = name;
		_email = email;
		_roles = roles;
		_href = href;
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

	public long getOrganizationId() {
		return _organizationId;
	}

	public String[] getRoles() {
		return _roles;
	}

	public long getUserId() {
		return _userId;
	}

	private final String _email;
	private final String _href;
	private final String _name;
	private final long _organizationId;
	private final String[] _roles;
	private final long _userId;

}