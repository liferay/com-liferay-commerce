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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import com.liferay.commerce.payment.method.mercanet.internal.worldline.sips.configuration.Configuration;

import java.net.URL;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luca Pellizzon
 *
 * Request to initialize a session with the Worldline SIPS API.
 * The possible values of each field are described in the API doc.
 *
 * @see Configuration
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder(alphabetic = true)
public class PaymentRequest {

	public Integer getAmount() {
		return amount;
	}

	public URL getAutomaticResponseUrl() {
		return automaticResponseUrl;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public Contact getBillingContact() {
		return billingContact;
	}

	public Integer getCaptureDay() {
		return captureDay;
	}

	public CaptureMode getCaptureMode() {
		return captureMode;
	}

	public Currency getCurrencyCode() {
		return currencyCode;
	}

	public CustomerAddress getCustomerAddress() {
		return customerAddress;
	}

	public CustomerContact getCustomerContact() {
		return customerContact;
	}

	public String getCustomerId() {
		return customerId;
	}

	public Language getCustomerLanguage() {
		return customerLanguage;
	}

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public Contact getDeliveryContact() {
		return deliveryContact;
	}

	public Address getHolderAddress() {
		return holderAddress;
	}

	public Contact getHolderContact() {
		return holderContact;
	}

	@JsonProperty("interfaceVersion")
	public String getInterfaceVersion() {
		return INTERFACE_VERSION;
	}

	public String getIntermediateServiceProviderId() {
		return intermediateServiceProviderId;
	}

	public Integer getKeyVersion() {
		return keyVersion;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public String getMerchantWalletId() {
		return merchantWalletId;
	}

	public URL getNormalReturnUrl() {
		return normalReturnUrl;
	}

	public OrderChannel getOrderChannel() {
		return orderChannel;
	}

	public String getOrderId() {
		return orderId;
	}

	public List<PaymentMeanBrand> getPaymentMeanBrandList() {
		return paymentMeanBrandList;
	}

	public PaypageData getPaypageData() {
		return paypageData;
	}

	public String getSeal() {
		return seal;
	}

	public String getStatementReference() {
		return statementReference;
	}

	public String getTemplateName() {
		return templateName;
	}

	public String getTransactionReference() {
		return transactionReference;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public void setAutomaticResponseUrl(URL automaticResponseUrl) {
		this.automaticResponseUrl = automaticResponseUrl;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public void setBillingContact(Contact billingContact) {
		this.billingContact = billingContact;
	}

	public void setCaptureDay(Integer captureDay) {
		this.captureDay = captureDay;
	}

	public void setCaptureMode(CaptureMode captureMode) {
		this.captureMode = captureMode;
	}

	public void setCurrencyCode(Currency currencyCode) {
		this.currencyCode = currencyCode;
	}

	public void setCustomerAddress(CustomerAddress customerAddress) {
		this.customerAddress = customerAddress;
	}

	public void setCustomerContact(CustomerContact customerContact) {
		this.customerContact = customerContact;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public void setCustomerLanguage(Language customerLanguage) {
		this.customerLanguage = customerLanguage;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public void setDeliveryContact(Contact deliveryContact) {
		this.deliveryContact = deliveryContact;
	}

	public void setHolderAddress(Address holderAddress) {
		this.holderAddress = holderAddress;
	}

	public void setHolderContact(Contact holderContact) {
		this.holderContact = holderContact;
	}

	public void setIntermediateServiceProviderId(
		String intermediateServiceProviderId) {

		this.intermediateServiceProviderId = intermediateServiceProviderId;
	}

	public void setKeyVersion(Integer keyVersion) {
		this.keyVersion = keyVersion;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public void setMerchantWalletId(String merchantWalletId) {
		this.merchantWalletId = merchantWalletId;
	}

	public void setNormalReturnUrl(URL normalReturnUrl) {
		this.normalReturnUrl = normalReturnUrl;
	}

	public void setOrderChannel(OrderChannel orderChannel) {
		this.orderChannel = orderChannel;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setPaypageData(PaypageData paypageData) {
		this.paypageData = paypageData;
	}

	public void setSeal(String seal) {
		this.seal = seal;
	}

	public void setStatementReference(String statementReference) {
		this.statementReference = statementReference;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public void setTransactionReference(String transactionReference) {
		this.transactionReference = transactionReference;
	}

	protected static final String INTERFACE_VERSION =
		Configuration.INTERFACE_VERSION;

	protected Integer amount;
	protected URL automaticResponseUrl;
	protected Address billingAddress;
	protected Contact billingContact;
	protected Integer captureDay;
	protected CaptureMode captureMode;
	protected Currency currencyCode;
	protected CustomerAddress customerAddress;
	protected CustomerContact customerContact;
	protected String customerId;
	protected Language customerLanguage;
	protected Address deliveryAddress;
	protected Contact deliveryContact;
	protected Address holderAddress;
	protected Contact holderContact;
	protected String intermediateServiceProviderId;
	protected Integer keyVersion;
	protected String merchantId;
	protected String merchantWalletId;
	protected URL normalReturnUrl;
	protected OrderChannel orderChannel;
	protected String orderId;
	protected final List<PaymentMeanBrand> paymentMeanBrandList =
		new ArrayList<>();
	protected PaypageData paypageData;
	protected String seal;
	protected String statementReference;
	protected String templateName;
	protected String transactionReference;

}