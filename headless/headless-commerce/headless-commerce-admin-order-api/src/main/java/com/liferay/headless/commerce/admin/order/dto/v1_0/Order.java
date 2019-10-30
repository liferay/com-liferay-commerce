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

package com.liferay.headless.commerce.admin.order.dto.v1_0;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.petra.string.StringBundler;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLName;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Generated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Alessio Antonio Rendina
 * @generated
 */
@Generated("")
@GraphQLName("Order")
@JsonFilter("Liferay.Vulcan")
@Schema(requiredProperties = {"channelId", "currencyCode"})
@XmlRootElement(name = "Order")
public class Order {

	@Schema
	public String getAccountExternalReferenceCode() {
		return accountExternalReferenceCode;
	}

	public void setAccountExternalReferenceCode(
		String accountExternalReferenceCode) {

		this.accountExternalReferenceCode = accountExternalReferenceCode;
	}

	@JsonIgnore
	public void setAccountExternalReferenceCode(
		UnsafeSupplier<String, Exception>
			accountExternalReferenceCodeUnsafeSupplier) {

		try {
			accountExternalReferenceCode =
				accountExternalReferenceCodeUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String accountExternalReferenceCode;

	@Schema
	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	@JsonIgnore
	public void setAccountId(
		UnsafeSupplier<Long, Exception> accountIdUnsafeSupplier) {

		try {
			accountId = accountIdUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Long accountId;

	@Schema
	public String getAdvanceStatus() {
		return advanceStatus;
	}

	public void setAdvanceStatus(String advanceStatus) {
		this.advanceStatus = advanceStatus;
	}

	@JsonIgnore
	public void setAdvanceStatus(
		UnsafeSupplier<String, Exception> advanceStatusUnsafeSupplier) {

		try {
			advanceStatus = advanceStatusUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String advanceStatus;

	@Schema
	public BillingAddress getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(BillingAddress billingAddress) {
		this.billingAddress = billingAddress;
	}

	@JsonIgnore
	public void setBillingAddress(
		UnsafeSupplier<BillingAddress, Exception>
			billingAddressUnsafeSupplier) {

		try {
			billingAddress = billingAddressUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected BillingAddress billingAddress;

	@Schema
	public Long getBillingAddressId() {
		return billingAddressId;
	}

	public void setBillingAddressId(Long billingAddressId) {
		this.billingAddressId = billingAddressId;
	}

	@JsonIgnore
	public void setBillingAddressId(
		UnsafeSupplier<Long, Exception> billingAddressIdUnsafeSupplier) {

		try {
			billingAddressId = billingAddressIdUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Long billingAddressId;

	@Schema
	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	@JsonIgnore
	public void setChannelId(
		UnsafeSupplier<Long, Exception> channelIdUnsafeSupplier) {

		try {
			channelId = channelIdUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	@NotNull
	protected Long channelId;

	@Schema
	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	@JsonIgnore
	public void setCouponCode(
		UnsafeSupplier<String, Exception> couponCodeUnsafeSupplier) {

		try {
			couponCode = couponCodeUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String couponCode;

	@Schema
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@JsonIgnore
	public void setCreateDate(
		UnsafeSupplier<Date, Exception> createDateUnsafeSupplier) {

		try {
			createDate = createDateUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Date createDate;

	@Schema
	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	@JsonIgnore
	public void setCurrencyCode(
		UnsafeSupplier<String, Exception> currencyCodeUnsafeSupplier) {

		try {
			currencyCode = currencyCodeUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	@NotEmpty
	protected String currencyCode;

	@Schema
	public Map<String, ?> getCustomFields() {
		return customFields;
	}

	public void setCustomFields(Map<String, ?> customFields) {
		this.customFields = customFields;
	}

	@JsonIgnore
	public void setCustomFields(
		UnsafeSupplier<Map<String, ?>, Exception> customFieldsUnsafeSupplier) {

		try {
			customFields = customFieldsUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Map<String, ?> customFields;

	@Schema
	public String getExternalReferenceCode() {
		return externalReferenceCode;
	}

	public void setExternalReferenceCode(String externalReferenceCode) {
		this.externalReferenceCode = externalReferenceCode;
	}

	@JsonIgnore
	public void setExternalReferenceCode(
		UnsafeSupplier<String, Exception> externalReferenceCodeUnsafeSupplier) {

		try {
			externalReferenceCode = externalReferenceCodeUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String externalReferenceCode;

	@Schema
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonIgnore
	public void setId(UnsafeSupplier<Long, Exception> idUnsafeSupplier) {
		try {
			id = idUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Long id;

	@Schema
	public OrderItem[] getItems() {
		return items;
	}

	public void setItems(OrderItem[] items) {
		this.items = items;
	}

	@JsonIgnore
	public void setItems(
		UnsafeSupplier<OrderItem[], Exception> itemsUnsafeSupplier) {

		try {
			items = itemsUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected OrderItem[] items;

	@Schema
	public Date getLastPriceUpdateDate() {
		return lastPriceUpdateDate;
	}

	public void setLastPriceUpdateDate(Date lastPriceUpdateDate) {
		this.lastPriceUpdateDate = lastPriceUpdateDate;
	}

	@JsonIgnore
	public void setLastPriceUpdateDate(
		UnsafeSupplier<Date, Exception> lastPriceUpdateDateUnsafeSupplier) {

		try {
			lastPriceUpdateDate = lastPriceUpdateDateUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Date lastPriceUpdateDate;

	@Schema
	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@JsonIgnore
	public void setModifiedDate(
		UnsafeSupplier<Date, Exception> modifiedDateUnsafeSupplier) {

		try {
			modifiedDate = modifiedDateUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Date modifiedDate;

	@Schema
	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@JsonIgnore
	public void setOrderDate(
		UnsafeSupplier<Date, Exception> orderDateUnsafeSupplier) {

		try {
			orderDate = orderDateUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Date orderDate;

	@Schema
	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	@JsonIgnore
	public void setOrderStatus(
		UnsafeSupplier<Integer, Exception> orderStatusUnsafeSupplier) {

		try {
			orderStatus = orderStatusUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Integer orderStatus;

	@Schema
	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@JsonIgnore
	public void setPaymentMethod(
		UnsafeSupplier<String, Exception> paymentMethodUnsafeSupplier) {

		try {
			paymentMethod = paymentMethodUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String paymentMethod;

	@Schema
	public Integer getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(Integer paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@JsonIgnore
	public void setPaymentStatus(
		UnsafeSupplier<Integer, Exception> paymentStatusUnsafeSupplier) {

		try {
			paymentStatus = paymentStatusUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Integer paymentStatus;

	@Schema
	public String getPrintedNote() {
		return printedNote;
	}

	public void setPrintedNote(String printedNote) {
		this.printedNote = printedNote;
	}

	@JsonIgnore
	public void setPrintedNote(
		UnsafeSupplier<String, Exception> printedNoteUnsafeSupplier) {

		try {
			printedNote = printedNoteUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String printedNote;

	@Schema
	public String getPurchaseOrderNumber() {
		return purchaseOrderNumber;
	}

	public void setPurchaseOrderNumber(String purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
	}

	@JsonIgnore
	public void setPurchaseOrderNumber(
		UnsafeSupplier<String, Exception> purchaseOrderNumberUnsafeSupplier) {

		try {
			purchaseOrderNumber = purchaseOrderNumberUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String purchaseOrderNumber;

	@Schema
	public Date getRequestedDeliveryDate() {
		return requestedDeliveryDate;
	}

	public void setRequestedDeliveryDate(Date requestedDeliveryDate) {
		this.requestedDeliveryDate = requestedDeliveryDate;
	}

	@JsonIgnore
	public void setRequestedDeliveryDate(
		UnsafeSupplier<Date, Exception> requestedDeliveryDateUnsafeSupplier) {

		try {
			requestedDeliveryDate = requestedDeliveryDateUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Date requestedDeliveryDate;

	@Schema
	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	@JsonIgnore
	public void setShippingAddress(
		UnsafeSupplier<ShippingAddress, Exception>
			shippingAddressUnsafeSupplier) {

		try {
			shippingAddress = shippingAddressUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected ShippingAddress shippingAddress;

	@Schema
	public Long getShippingAddressId() {
		return shippingAddressId;
	}

	public void setShippingAddressId(Long shippingAddressId) {
		this.shippingAddressId = shippingAddressId;
	}

	@JsonIgnore
	public void setShippingAddressId(
		UnsafeSupplier<Long, Exception> shippingAddressIdUnsafeSupplier) {

		try {
			shippingAddressId = shippingAddressIdUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Long shippingAddressId;

	@Schema
	public BigDecimal getShippingAmount() {
		return shippingAmount;
	}

	public void setShippingAmount(BigDecimal shippingAmount) {
		this.shippingAmount = shippingAmount;
	}

	@JsonIgnore
	public void setShippingAmount(
		UnsafeSupplier<BigDecimal, Exception> shippingAmountUnsafeSupplier) {

		try {
			shippingAmount = shippingAmountUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected BigDecimal shippingAmount;

	@Schema
	public BigDecimal getShippingDiscountAmount() {
		return shippingDiscountAmount;
	}

	public void setShippingDiscountAmount(BigDecimal shippingDiscountAmount) {
		this.shippingDiscountAmount = shippingDiscountAmount;
	}

	@JsonIgnore
	public void setShippingDiscountAmount(
		UnsafeSupplier<BigDecimal, Exception>
			shippingDiscountAmountUnsafeSupplier) {

		try {
			shippingDiscountAmount = shippingDiscountAmountUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected BigDecimal shippingDiscountAmount;

	@Schema
	public BigDecimal getShippingDiscountPercentageLevel1() {
		return shippingDiscountPercentageLevel1;
	}

	public void setShippingDiscountPercentageLevel1(
		BigDecimal shippingDiscountPercentageLevel1) {

		this.shippingDiscountPercentageLevel1 =
			shippingDiscountPercentageLevel1;
	}

	@JsonIgnore
	public void setShippingDiscountPercentageLevel1(
		UnsafeSupplier<BigDecimal, Exception>
			shippingDiscountPercentageLevel1UnsafeSupplier) {

		try {
			shippingDiscountPercentageLevel1 =
				shippingDiscountPercentageLevel1UnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected BigDecimal shippingDiscountPercentageLevel1;

	@Schema
	public BigDecimal getShippingDiscountPercentageLevel2() {
		return shippingDiscountPercentageLevel2;
	}

	public void setShippingDiscountPercentageLevel2(
		BigDecimal shippingDiscountPercentageLevel2) {

		this.shippingDiscountPercentageLevel2 =
			shippingDiscountPercentageLevel2;
	}

	@JsonIgnore
	public void setShippingDiscountPercentageLevel2(
		UnsafeSupplier<BigDecimal, Exception>
			shippingDiscountPercentageLevel2UnsafeSupplier) {

		try {
			shippingDiscountPercentageLevel2 =
				shippingDiscountPercentageLevel2UnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected BigDecimal shippingDiscountPercentageLevel2;

	@Schema
	public BigDecimal getShippingDiscountPercentageLevel3() {
		return shippingDiscountPercentageLevel3;
	}

	public void setShippingDiscountPercentageLevel3(
		BigDecimal shippingDiscountPercentageLevel3) {

		this.shippingDiscountPercentageLevel3 =
			shippingDiscountPercentageLevel3;
	}

	@JsonIgnore
	public void setShippingDiscountPercentageLevel3(
		UnsafeSupplier<BigDecimal, Exception>
			shippingDiscountPercentageLevel3UnsafeSupplier) {

		try {
			shippingDiscountPercentageLevel3 =
				shippingDiscountPercentageLevel3UnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected BigDecimal shippingDiscountPercentageLevel3;

	@Schema
	public BigDecimal getShippingDiscountPercentageLevel4() {
		return shippingDiscountPercentageLevel4;
	}

	public void setShippingDiscountPercentageLevel4(
		BigDecimal shippingDiscountPercentageLevel4) {

		this.shippingDiscountPercentageLevel4 =
			shippingDiscountPercentageLevel4;
	}

	@JsonIgnore
	public void setShippingDiscountPercentageLevel4(
		UnsafeSupplier<BigDecimal, Exception>
			shippingDiscountPercentageLevel4UnsafeSupplier) {

		try {
			shippingDiscountPercentageLevel4 =
				shippingDiscountPercentageLevel4UnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected BigDecimal shippingDiscountPercentageLevel4;

	@Schema
	public String getShippingMethod() {
		return shippingMethod;
	}

	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

	@JsonIgnore
	public void setShippingMethod(
		UnsafeSupplier<String, Exception> shippingMethodUnsafeSupplier) {

		try {
			shippingMethod = shippingMethodUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String shippingMethod;

	@Schema
	public String getShippingOption() {
		return shippingOption;
	}

	public void setShippingOption(String shippingOption) {
		this.shippingOption = shippingOption;
	}

	@JsonIgnore
	public void setShippingOption(
		UnsafeSupplier<String, Exception> shippingOptionUnsafeSupplier) {

		try {
			shippingOption = shippingOptionUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String shippingOption;

	@Schema
	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	@JsonIgnore
	public void setSubtotal(
		UnsafeSupplier<BigDecimal, Exception> subtotalUnsafeSupplier) {

		try {
			subtotal = subtotalUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected BigDecimal subtotal;

	@Schema
	public BigDecimal getSubtotalDiscountAmount() {
		return subtotalDiscountAmount;
	}

	public void setSubtotalDiscountAmount(BigDecimal subtotalDiscountAmount) {
		this.subtotalDiscountAmount = subtotalDiscountAmount;
	}

	@JsonIgnore
	public void setSubtotalDiscountAmount(
		UnsafeSupplier<BigDecimal, Exception>
			subtotalDiscountAmountUnsafeSupplier) {

		try {
			subtotalDiscountAmount = subtotalDiscountAmountUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected BigDecimal subtotalDiscountAmount;

	@Schema
	public BigDecimal getSubtotalDiscountPercentageLevel1() {
		return subtotalDiscountPercentageLevel1;
	}

	public void setSubtotalDiscountPercentageLevel1(
		BigDecimal subtotalDiscountPercentageLevel1) {

		this.subtotalDiscountPercentageLevel1 =
			subtotalDiscountPercentageLevel1;
	}

	@JsonIgnore
	public void setSubtotalDiscountPercentageLevel1(
		UnsafeSupplier<BigDecimal, Exception>
			subtotalDiscountPercentageLevel1UnsafeSupplier) {

		try {
			subtotalDiscountPercentageLevel1 =
				subtotalDiscountPercentageLevel1UnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected BigDecimal subtotalDiscountPercentageLevel1;

	@Schema
	public BigDecimal getSubtotalDiscountPercentageLevel2() {
		return subtotalDiscountPercentageLevel2;
	}

	public void setSubtotalDiscountPercentageLevel2(
		BigDecimal subtotalDiscountPercentageLevel2) {

		this.subtotalDiscountPercentageLevel2 =
			subtotalDiscountPercentageLevel2;
	}

	@JsonIgnore
	public void setSubtotalDiscountPercentageLevel2(
		UnsafeSupplier<BigDecimal, Exception>
			subtotalDiscountPercentageLevel2UnsafeSupplier) {

		try {
			subtotalDiscountPercentageLevel2 =
				subtotalDiscountPercentageLevel2UnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected BigDecimal subtotalDiscountPercentageLevel2;

	@Schema
	public BigDecimal getSubtotalDiscountPercentageLevel3() {
		return subtotalDiscountPercentageLevel3;
	}

	public void setSubtotalDiscountPercentageLevel3(
		BigDecimal subtotalDiscountPercentageLevel3) {

		this.subtotalDiscountPercentageLevel3 =
			subtotalDiscountPercentageLevel3;
	}

	@JsonIgnore
	public void setSubtotalDiscountPercentageLevel3(
		UnsafeSupplier<BigDecimal, Exception>
			subtotalDiscountPercentageLevel3UnsafeSupplier) {

		try {
			subtotalDiscountPercentageLevel3 =
				subtotalDiscountPercentageLevel3UnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected BigDecimal subtotalDiscountPercentageLevel3;

	@Schema
	public BigDecimal getSubtotalDiscountPercentageLevel4() {
		return subtotalDiscountPercentageLevel4;
	}

	public void setSubtotalDiscountPercentageLevel4(
		BigDecimal subtotalDiscountPercentageLevel4) {

		this.subtotalDiscountPercentageLevel4 =
			subtotalDiscountPercentageLevel4;
	}

	@JsonIgnore
	public void setSubtotalDiscountPercentageLevel4(
		UnsafeSupplier<BigDecimal, Exception>
			subtotalDiscountPercentageLevel4UnsafeSupplier) {

		try {
			subtotalDiscountPercentageLevel4 =
				subtotalDiscountPercentageLevel4UnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected BigDecimal subtotalDiscountPercentageLevel4;

	@Schema
	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	@JsonIgnore
	public void setTaxAmount(
		UnsafeSupplier<BigDecimal, Exception> taxAmountUnsafeSupplier) {

		try {
			taxAmount = taxAmountUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected BigDecimal taxAmount;

	@Schema
	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	@JsonIgnore
	public void setTotal(
		UnsafeSupplier<BigDecimal, Exception> totalUnsafeSupplier) {

		try {
			total = totalUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected BigDecimal total;

	@Schema
	public BigDecimal getTotalDiscountAmount() {
		return totalDiscountAmount;
	}

	public void setTotalDiscountAmount(BigDecimal totalDiscountAmount) {
		this.totalDiscountAmount = totalDiscountAmount;
	}

	@JsonIgnore
	public void setTotalDiscountAmount(
		UnsafeSupplier<BigDecimal, Exception>
			totalDiscountAmountUnsafeSupplier) {

		try {
			totalDiscountAmount = totalDiscountAmountUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected BigDecimal totalDiscountAmount;

	@Schema
	public BigDecimal getTotalDiscountPercentageLevel1() {
		return totalDiscountPercentageLevel1;
	}

	public void setTotalDiscountPercentageLevel1(
		BigDecimal totalDiscountPercentageLevel1) {

		this.totalDiscountPercentageLevel1 = totalDiscountPercentageLevel1;
	}

	@JsonIgnore
	public void setTotalDiscountPercentageLevel1(
		UnsafeSupplier<BigDecimal, Exception>
			totalDiscountPercentageLevel1UnsafeSupplier) {

		try {
			totalDiscountPercentageLevel1 =
				totalDiscountPercentageLevel1UnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected BigDecimal totalDiscountPercentageLevel1;

	@Schema
	public BigDecimal getTotalDiscountPercentageLevel2() {
		return totalDiscountPercentageLevel2;
	}

	public void setTotalDiscountPercentageLevel2(
		BigDecimal totalDiscountPercentageLevel2) {

		this.totalDiscountPercentageLevel2 = totalDiscountPercentageLevel2;
	}

	@JsonIgnore
	public void setTotalDiscountPercentageLevel2(
		UnsafeSupplier<BigDecimal, Exception>
			totalDiscountPercentageLevel2UnsafeSupplier) {

		try {
			totalDiscountPercentageLevel2 =
				totalDiscountPercentageLevel2UnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected BigDecimal totalDiscountPercentageLevel2;

	@Schema
	public BigDecimal getTotalDiscountPercentageLevel3() {
		return totalDiscountPercentageLevel3;
	}

	public void setTotalDiscountPercentageLevel3(
		BigDecimal totalDiscountPercentageLevel3) {

		this.totalDiscountPercentageLevel3 = totalDiscountPercentageLevel3;
	}

	@JsonIgnore
	public void setTotalDiscountPercentageLevel3(
		UnsafeSupplier<BigDecimal, Exception>
			totalDiscountPercentageLevel3UnsafeSupplier) {

		try {
			totalDiscountPercentageLevel3 =
				totalDiscountPercentageLevel3UnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected BigDecimal totalDiscountPercentageLevel3;

	@Schema
	public BigDecimal getTotalDiscountPercentageLevel4() {
		return totalDiscountPercentageLevel4;
	}

	public void setTotalDiscountPercentageLevel4(
		BigDecimal totalDiscountPercentageLevel4) {

		this.totalDiscountPercentageLevel4 = totalDiscountPercentageLevel4;
	}

	@JsonIgnore
	public void setTotalDiscountPercentageLevel4(
		UnsafeSupplier<BigDecimal, Exception>
			totalDiscountPercentageLevel4UnsafeSupplier) {

		try {
			totalDiscountPercentageLevel4 =
				totalDiscountPercentageLevel4UnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected BigDecimal totalDiscountPercentageLevel4;

	@Schema
	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	@JsonIgnore
	public void setTransactionId(
		UnsafeSupplier<String, Exception> transactionIdUnsafeSupplier) {

		try {
			transactionId = transactionIdUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String transactionId;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Order)) {
			return false;
		}

		Order order = (Order)object;

		return Objects.equals(toString(), order.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");

		if (accountExternalReferenceCode != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"accountExternalReferenceCode\": ");

			sb.append("\"");

			sb.append(_escape(accountExternalReferenceCode));

			sb.append("\"");
		}

		if (accountId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"accountId\": ");

			sb.append(accountId);
		}

		if (advanceStatus != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"advanceStatus\": ");

			sb.append("\"");

			sb.append(_escape(advanceStatus));

			sb.append("\"");
		}

		if (billingAddress != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"billingAddress\": ");

			sb.append(String.valueOf(billingAddress));
		}

		if (billingAddressId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"billingAddressId\": ");

			sb.append(billingAddressId);
		}

		if (channelId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"channelId\": ");

			sb.append(channelId);
		}

		if (couponCode != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"couponCode\": ");

			sb.append("\"");

			sb.append(_escape(couponCode));

			sb.append("\"");
		}

		if (createDate != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"createDate\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(createDate));

			sb.append("\"");
		}

		if (currencyCode != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"currencyCode\": ");

			sb.append("\"");

			sb.append(_escape(currencyCode));

			sb.append("\"");
		}

		if (customFields != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"customFields\": ");

			sb.append(_toJSON(customFields));
		}

		if (externalReferenceCode != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"externalReferenceCode\": ");

			sb.append("\"");

			sb.append(_escape(externalReferenceCode));

			sb.append("\"");
		}

		if (id != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(id);
		}

		if (items != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"items\": ");

			sb.append("[");

			for (int i = 0; i < items.length; i++) {
				sb.append(String.valueOf(items[i]));

				if ((i + 1) < items.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (lastPriceUpdateDate != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"lastPriceUpdateDate\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(lastPriceUpdateDate));

			sb.append("\"");
		}

		if (modifiedDate != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"modifiedDate\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(modifiedDate));

			sb.append("\"");
		}

		if (orderDate != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"orderDate\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(orderDate));

			sb.append("\"");
		}

		if (orderStatus != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"orderStatus\": ");

			sb.append(orderStatus);
		}

		if (paymentMethod != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"paymentMethod\": ");

			sb.append("\"");

			sb.append(_escape(paymentMethod));

			sb.append("\"");
		}

		if (paymentStatus != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"paymentStatus\": ");

			sb.append(paymentStatus);
		}

		if (printedNote != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"printedNote\": ");

			sb.append("\"");

			sb.append(_escape(printedNote));

			sb.append("\"");
		}

		if (purchaseOrderNumber != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"purchaseOrderNumber\": ");

			sb.append("\"");

			sb.append(_escape(purchaseOrderNumber));

			sb.append("\"");
		}

		if (requestedDeliveryDate != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"requestedDeliveryDate\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(requestedDeliveryDate));

			sb.append("\"");
		}

		if (shippingAddress != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"shippingAddress\": ");

			sb.append(String.valueOf(shippingAddress));
		}

		if (shippingAddressId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"shippingAddressId\": ");

			sb.append(shippingAddressId);
		}

		if (shippingAmount != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"shippingAmount\": ");

			sb.append(shippingAmount);
		}

		if (shippingDiscountAmount != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"shippingDiscountAmount\": ");

			sb.append(shippingDiscountAmount);
		}

		if (shippingDiscountPercentageLevel1 != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"shippingDiscountPercentageLevel1\": ");

			sb.append(shippingDiscountPercentageLevel1);
		}

		if (shippingDiscountPercentageLevel2 != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"shippingDiscountPercentageLevel2\": ");

			sb.append(shippingDiscountPercentageLevel2);
		}

		if (shippingDiscountPercentageLevel3 != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"shippingDiscountPercentageLevel3\": ");

			sb.append(shippingDiscountPercentageLevel3);
		}

		if (shippingDiscountPercentageLevel4 != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"shippingDiscountPercentageLevel4\": ");

			sb.append(shippingDiscountPercentageLevel4);
		}

		if (shippingMethod != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"shippingMethod\": ");

			sb.append("\"");

			sb.append(_escape(shippingMethod));

			sb.append("\"");
		}

		if (shippingOption != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"shippingOption\": ");

			sb.append("\"");

			sb.append(_escape(shippingOption));

			sb.append("\"");
		}

		if (subtotal != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"subtotal\": ");

			sb.append(subtotal);
		}

		if (subtotalDiscountAmount != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"subtotalDiscountAmount\": ");

			sb.append(subtotalDiscountAmount);
		}

		if (subtotalDiscountPercentageLevel1 != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"subtotalDiscountPercentageLevel1\": ");

			sb.append(subtotalDiscountPercentageLevel1);
		}

		if (subtotalDiscountPercentageLevel2 != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"subtotalDiscountPercentageLevel2\": ");

			sb.append(subtotalDiscountPercentageLevel2);
		}

		if (subtotalDiscountPercentageLevel3 != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"subtotalDiscountPercentageLevel3\": ");

			sb.append(subtotalDiscountPercentageLevel3);
		}

		if (subtotalDiscountPercentageLevel4 != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"subtotalDiscountPercentageLevel4\": ");

			sb.append(subtotalDiscountPercentageLevel4);
		}

		if (taxAmount != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"taxAmount\": ");

			sb.append(taxAmount);
		}

		if (total != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"total\": ");

			sb.append(total);
		}

		if (totalDiscountAmount != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"totalDiscountAmount\": ");

			sb.append(totalDiscountAmount);
		}

		if (totalDiscountPercentageLevel1 != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"totalDiscountPercentageLevel1\": ");

			sb.append(totalDiscountPercentageLevel1);
		}

		if (totalDiscountPercentageLevel2 != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"totalDiscountPercentageLevel2\": ");

			sb.append(totalDiscountPercentageLevel2);
		}

		if (totalDiscountPercentageLevel3 != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"totalDiscountPercentageLevel3\": ");

			sb.append(totalDiscountPercentageLevel3);
		}

		if (totalDiscountPercentageLevel4 != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"totalDiscountPercentageLevel4\": ");

			sb.append(totalDiscountPercentageLevel4);
		}

		if (transactionId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"transactionId\": ");

			sb.append("\"");

			sb.append(_escape(transactionId));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		return string.replaceAll("\"", "\\\\\"");
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\":");
			sb.append("\"");
			sb.append(entry.getValue());
			sb.append("\"");

			if (iterator.hasNext()) {
				sb.append(",");
			}
		}

		sb.append("}");

		return sb.toString();
	}

}