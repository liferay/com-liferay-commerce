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

package com.liferay.commerce.account.web.internal.model;

/**
 * @author Alessio Antonio Rendina
 */
public class Member {

	public Member(long memberId, String name, String email, String[] roles) {
		_memberId = memberId;
		_name = name;
		_email = email;
		_roles = roles;
	}

	public String getEmail() {
		return _email;
	}

	public long getMemberId() {
		return _memberId;
	}

	public String getName() {
		return _name;
	}

	public String[] getRoles() {
		return _roles;
	}

	private final String _email;
	private final long _memberId;
	private final String _name;
	private final String[] _roles;

}