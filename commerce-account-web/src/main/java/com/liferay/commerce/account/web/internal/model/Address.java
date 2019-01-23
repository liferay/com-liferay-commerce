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
public class Address {

	public Address(
		long addressId, String address, String referent, String phoneNumber) {

		_addressId = addressId;
		_address = address;
		_referent = referent;
		_phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return _address;
	}

	public long getAddressId() {
		return _addressId;
	}

	public String getPhoneNumber() {
		return _phoneNumber;
	}

	public String getReferent() {
		return _referent;
	}

	private final String _address;
	private final long _addressId;
	private final String _phoneNumber;
	private final String _referent;

}