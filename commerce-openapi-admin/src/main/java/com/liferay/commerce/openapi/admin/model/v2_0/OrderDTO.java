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

package com.liferay.commerce.openapi.admin.model.v2_0;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import com.liferay.commerce.openapi.core.annotation.Nullable;

import java.util.Map;

import javax.annotation.Generated;

/**
 * @author Igor Beslic
 */
@Generated(value = "OSGiRESTModuleGenerator")
@JacksonXmlRootElement(localName = "Order")
public class OrderDTO {

	@Nullable
	public Map<String, String> getBillingAddress() {
		return _billingAddress;
	}

	@Nullable
	public Long getBillingAddressId() {
		return _billingAddressId;
	}

	public Long getCommerceAccountId() {
		return _commerceAccountId;
	}

	public String getCurrency() {
		return _currency;
	}

	public String getExternalReferenceCode() {
		return _externalReferenceCode;
	}

	@Nullable
	public Long getId() {
		return _id;
	}

	@Nullable
	public Map<String, String> getShippingAddress() {
		return _shippingAddress;
	}

	@Nullable
	public Long getShippingAddressId() {
		return _shippingAddressId;
	}

	public void setBillingAddress(Map<String, String> billingAddress) {
		_billingAddress = billingAddress;
	}

	public void setBillingAddressId(Long billingAddressId) {
		_billingAddressId = billingAddressId;
	}

	public void setCommerceAccountId(Long commerceAccountId) {
		_commerceAccountId = commerceAccountId;
	}

	public void setCurrency(String currency) {
		_currency = currency;
	}

	public void setExternalReferenceCode(String externalReferenceCode) {
		_externalReferenceCode = externalReferenceCode;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setShippingAddress(Map<String, String> shippingAddress) {
		_shippingAddress = shippingAddress;
	}

	public void setShippingAddressId(Long shippingAddressId) {
		_shippingAddressId = shippingAddressId;
	}

	@Nullable
	private Map<String, String> _billingAddress;

	@Nullable
	private Long _billingAddressId;

	private Long _commerceAccountId;
	private String _currency;
	private String _externalReferenceCode;

	@Nullable
	private Long _id;

	@Nullable
	private Map<String, String> _shippingAddress;

	@Nullable
	private Long _shippingAddressId;

}