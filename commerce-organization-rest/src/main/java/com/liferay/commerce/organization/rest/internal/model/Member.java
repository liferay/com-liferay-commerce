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

package com.liferay.commerce.organization.rest.internal.model;

/**
 * @author Alessio Antonio Rendina
 */
public class Member {

	public Member(
		long userId, String firstName, String lastName, String email) {

		_userId = userId;
		_firstName = firstName;
		_lastName = lastName;
		_email = email;
	}

	public String getEmail() {
		return _email;
	}

	public String getFirstName() {
		return _firstName;
	}

	public String getLastName() {
		return _lastName;
	}

	public long getUserId() {
		return _userId;
	}

	private final String _email;
	private final String _firstName;
	private final String _lastName;
	private final long _userId;

}