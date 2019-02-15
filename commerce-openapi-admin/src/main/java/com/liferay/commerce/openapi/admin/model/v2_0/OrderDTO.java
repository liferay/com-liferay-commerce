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

import java.math.BigDecimal;

import javax.annotation.Generated;

/**
 * @author Igor Beslic
 */
@Generated(value = "OSGiRESTModuleGenerator")
@JacksonXmlRootElement(localName = "Order")
public class OrderDTO {

	public String getAdvanceStatus() {
		return _advanceStatus;
	}

	public AddressDTO getBillingAddressDTO() {
		return _billingAddressDTO;
	}

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

	public Long getId() {
		return _id;
	}

	public Integer getOrderStatus() {
		return _orderStatus;
	}

	public String getPaymentMethod() {
		return _paymentMethod;
	}

	public Integer getPaymentStatus() {
		return _paymentStatus;
	}

	public String getPurchaseOrderNumber() {
		return _purchaseOrderNumber;
	}

	public AddressDTO getShippingAddressDTO() {
		return _shippingAddressDTO;
	}

	public Long getShippingAddressId() {
		return _shippingAddressId;
	}

	public BigDecimal getShippingAmount() {
		return _shippingAmount;
	}

	public String getShippingMethod() {
		return _shippingMethod;
	}

	public String getShippingOption() {
		return _shippingOption;
	}

	public BigDecimal getSubtotal() {
		return _subtotal;
	}

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

	private String _advanceStatus;
	private AddressDTO _billingAddressDTO;
	private Long _billingAddressId;
	private Long _commerceAccountId;
	private String _currency;
	private String _externalReferenceCode;
	private Long _id;
	private Integer _orderStatus;
	private String _paymentMethod;
	private Integer _paymentStatus;
	private String _purchaseOrderNumber;
	private AddressDTO _shippingAddressDTO;
	private Long _shippingAddressId;
	private BigDecimal _shippingAmount;
	private String _shippingMethod;
	private String _shippingOption;
	private BigDecimal _subtotal;
	private BigDecimal _total;

}