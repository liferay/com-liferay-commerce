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

package com.liferay.commerce.payment.method.mercanet.internal.worldline.sips.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author Luca Pellizzon
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class Address {

	public String getAddressAdditional1() {
		return addressAdditional1;
	}

	public String getAddressAdditional2() {
		return addressAdditional2;
	}

	public String getAddressAdditional3() {
		return addressAdditional3;
	}

	public String getCity() {
		return city;
	}

	public String getCompany() {
		return company;
	}

	public String getCountry() {
		return country;
	}

	public String getPostBox() {
		return postBox;
	}

	public String getState() {
		return state;
	}

	public String getStreet() {
		return street;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setAddressAdditional1(String addressAdditional1) {
		this.addressAdditional1 = addressAdditional1;
	}

	public void setAddressAdditional2(String addressAdditional2) {
		this.addressAdditional2 = addressAdditional2;
	}

	public void setAddressAdditional3(String addressAdditional3) {
		this.addressAdditional3 = addressAdditional3;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setPostBox(String postBox) {
		this.postBox = postBox;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	protected String addressAdditional1;
	protected String addressAdditional2;
	protected String addressAdditional3;
	protected String city;
	protected String company;
	protected String country;
	protected String postBox;
	protected String state;
	protected String street;
	protected String streetNumber;
	protected String zipcode;

}