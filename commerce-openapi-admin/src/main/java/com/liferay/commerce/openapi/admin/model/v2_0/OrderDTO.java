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

import java.math.BigDecimal;

import javax.annotation.Generated;

/**
 * @author Igor Beslic
 */
@Generated(value = "OSGiRESTModuleGenerator")
@JacksonXmlRootElement(localName = "Order")
public class OrderDTO {

	@Nullable
	public String getAdvanceStatus() {
		return _advanceStatus;
	}

	@Nullable
	public AddressDTO getBillingAddressDTO() {
		return _billingAddressDTO;
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
	public Integer getOrderStatus() {
		return _orderStatus;
	}

	@Nullable
	public String getPaymentMethod() {
		return _paymentMethod;
	}

	@Nullable
	public Integer getPaymentStatus() {
		return _paymentStatus;
	}

	@Nullable
	public String getPurchaseOrderNumber() {
		return _purchaseOrderNumber;
	}

	@Nullable
	public AddressDTO getShippingAddressDTO() {
		return _shippingAddressDTO;
	}

	@Nullable
	public Long getShippingAddressId() {
		return _shippingAddressId;
	}

	@Nullable
	public BigDecimal getShippingAmount() {
		return _shippingAmount;
	}

	@Nullable
	public String getShippingMethod() {
		return _shippingMethod;
	}

	@Nullable
	public String getShippingOption() {
		return _shippingOption;
	}

	@Nullable
	public BigDecimal getSubtotal() {
		return _subtotal;
	}

	@Nullable
	public BigDecimal getTotal() {
		return _total;
	}

	public void setAdvanceStatus(String advanceStatus) {
		_advanceStatus = advanceStatus;
	}

	public void setBillingAddressDTO(AddressDTO billingAddressDTO) {
		_billingAddressDTO = billingAddressDTO;
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

	public void setOrderStatus(Integer orderStatus) {
		_orderStatus = orderStatus;
	}

	public void setPaymentMethod(String paymentMethod) {
		_paymentMethod = paymentMethod;
	}

	public void setPaymentStatus(Integer paymentStatus) {
		_paymentStatus = paymentStatus;
	}

	public void setPurchaseOrderNumber(String purchaseOrderNumber) {
		_purchaseOrderNumber = purchaseOrderNumber;
	}

	public void setShippingAddressDTO(AddressDTO shippingAddressDTO) {
		_shippingAddressDTO = shippingAddressDTO;
	}

	public void setShippingAddressId(Long shippingAddressId) {
		_shippingAddressId = shippingAddressId;
	}

	public void setShippingAmount(BigDecimal shippingAmount) {
		_shippingAmount = shippingAmount;
	}

	public void setShippingMethod(String shippingMethod) {
		_shippingMethod = shippingMethod;
	}

	public void setShippingOption(String shippingOption) {
		_shippingOption = shippingOption;
	}

	public void setSubtotal(BigDecimal subtotal) {
		_subtotal = subtotal;
	}

	public void setTotal(BigDecimal total) {
		_total = total;
	}

	@Nullable
	private String _advanceStatus;

	@Nullable
	private AddressDTO _billingAddressDTO;

	@Nullable
	private Long _billingAddressId;

	private Long _commerceAccountId;
	private String _currency;
	private String _externalReferenceCode;

	@Nullable
	private Long _id;

	@Nullable
	private Integer _orderStatus;

	@Nullable
	private String _paymentMethod;

	@Nullable
	private Integer _paymentStatus;

	@Nullable
	private String _purchaseOrderNumber;

	@Nullable
	private AddressDTO _shippingAddressDTO;

	@Nullable
	private Long _shippingAddressId;

	@Nullable
	private BigDecimal _shippingAmount;

	@Nullable
	private String _shippingMethod;

	@Nullable
	private String _shippingOption;

	@Nullable
	private BigDecimal _subtotal;

	@Nullable
	private BigDecimal _total;

}