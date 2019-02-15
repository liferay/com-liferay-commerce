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

import java.util.Map;

import javax.annotation.Generated;

/**
 * @author Igor Beslic
 */
@Generated(value = "OSGiRESTModuleGenerator")
@JacksonXmlRootElement(localName = "OrderItem")
public class OrderItemDTO {

	public Long getCommerceOrderId() {
		return _commerceOrderId;
	}

	public BigDecimal getDiscountAmount() {
		return _discountAmount;
	}

	public String getExternalReferenceCode() {
		return _externalReferenceCode;
	}

	public BigDecimal getFinalPrice() {
		return _finalPrice;
	}

	public Long getId() {
		return _id;
	}

	public Map<String, String> getName() {
		return _name;
	}

	public Integer getQuantity() {
		return _quantity;
	}

	public Integer getShippedQuantity() {
		return _shippedQuantity;
	}

	public String getSku() {
		return _sku;
	}

	public Long getSkuId() {
		return _skuId;
	}

	public BigDecimal getUnitPrice() {
		return _unitPrice;
	}

	public Boolean isSubscription() {
		return _subscription;
	}

	public void setCommerceOrderId(Long commerceOrderId) {
		_commerceOrderId = commerceOrderId;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		_discountAmount = discountAmount;
	}

	public void setExternalReferenceCode(String externalReferenceCode) {
		_externalReferenceCode = externalReferenceCode;
	}

	public void setFinalPrice(BigDecimal finalPrice) {
		_finalPrice = finalPrice;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setName(Map<String, String> name) {
		_name = name;
	}

	public void setQuantity(Integer quantity) {
		_quantity = quantity;
	}

	public void setShippedQuantity(Integer shippedQuantity) {
		_shippedQuantity = shippedQuantity;
	}

	public void setSku(String sku) {
		_sku = sku;
	}

	public void setSkuId(Long skuId) {
		_skuId = skuId;
	}

	public void setSubscription(Boolean subscription) {
		_subscription = subscription;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		_unitPrice = unitPrice;
	}

	private Long _commerceOrderId;
	private BigDecimal _discountAmount;
	private String _externalReferenceCode;
	private BigDecimal _finalPrice;
	private Long _id;
	private Map<String, String> _name;
	private Integer _quantity;
	private Integer _shippedQuantity;
	private String _sku;
	private Long _skuId;
	private Boolean _subscription;
	private BigDecimal _unitPrice;

}