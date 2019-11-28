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

package com.liferay.commerce.model;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.commerce.service.http.CommerceOrderServiceSoap}.
 *
 * @author Alessio Antonio Rendina
 * @generated
 */
public class CommerceOrderSoap implements Serializable {

	public static CommerceOrderSoap toSoapModel(CommerceOrder model) {
		CommerceOrderSoap soapModel = new CommerceOrderSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setExternalReferenceCode(model.getExternalReferenceCode());
		soapModel.setCommerceOrderId(model.getCommerceOrderId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCommerceAccountId(model.getCommerceAccountId());
		soapModel.setCommerceCurrencyId(model.getCommerceCurrencyId());
		soapModel.setBillingAddressId(model.getBillingAddressId());
		soapModel.setShippingAddressId(model.getShippingAddressId());
		soapModel.setCommercePaymentMethodKey(
			model.getCommercePaymentMethodKey());
		soapModel.setTransactionId(model.getTransactionId());
		soapModel.setCommerceShippingMethodId(
			model.getCommerceShippingMethodId());
		soapModel.setShippingOptionName(model.getShippingOptionName());
		soapModel.setPurchaseOrderNumber(model.getPurchaseOrderNumber());
		soapModel.setCouponCode(model.getCouponCode());
		soapModel.setLastPriceUpdateDate(model.getLastPriceUpdateDate());
		soapModel.setSubtotal(model.getSubtotal());
		soapModel.setSubtotalDiscountAmount(model.getSubtotalDiscountAmount());
		soapModel.setSubtotalDiscountPercentageLevel1(
			model.getSubtotalDiscountPercentageLevel1());
		soapModel.setSubtotalDiscountPercentageLevel2(
			model.getSubtotalDiscountPercentageLevel2());
		soapModel.setSubtotalDiscountPercentageLevel3(
			model.getSubtotalDiscountPercentageLevel3());
		soapModel.setSubtotalDiscountPercentageLevel4(
			model.getSubtotalDiscountPercentageLevel4());
		soapModel.setShippingAmount(model.getShippingAmount());
		soapModel.setShippingDiscountAmount(model.getShippingDiscountAmount());
		soapModel.setShippingDiscountPercentageLevel1(
			model.getShippingDiscountPercentageLevel1());
		soapModel.setShippingDiscountPercentageLevel2(
			model.getShippingDiscountPercentageLevel2());
		soapModel.setShippingDiscountPercentageLevel3(
			model.getShippingDiscountPercentageLevel3());
		soapModel.setShippingDiscountPercentageLevel4(
			model.getShippingDiscountPercentageLevel4());
		soapModel.setTaxAmount(model.getTaxAmount());
		soapModel.setTotal(model.getTotal());
		soapModel.setTotalDiscountAmount(model.getTotalDiscountAmount());
		soapModel.setTotalDiscountPercentageLevel1(
			model.getTotalDiscountPercentageLevel1());
		soapModel.setTotalDiscountPercentageLevel2(
			model.getTotalDiscountPercentageLevel2());
		soapModel.setTotalDiscountPercentageLevel3(
			model.getTotalDiscountPercentageLevel3());
		soapModel.setTotalDiscountPercentageLevel4(
			model.getTotalDiscountPercentageLevel4());
		soapModel.setBillingName(model.getBillingName());
		soapModel.setBillingDescription(model.getBillingDescription());
		soapModel.setBillingStreet1(model.getBillingStreet1());
		soapModel.setBillingStreet2(model.getBillingStreet2());
		soapModel.setBillingStreet3(model.getBillingStreet3());
		soapModel.setBillingCity(model.getBillingCity());
		soapModel.setBillingZip(model.getBillingZip());
		soapModel.setBillingRegionId(model.getBillingRegionId());
		soapModel.setBillingCountryId(model.getBillingCountryId());
		soapModel.setBillingPhoneNumber(model.getBillingPhoneNumber());
		soapModel.setShippingName(model.getShippingName());
		soapModel.setShippingDescription(model.getShippingDescription());
		soapModel.setShippingStreet1(model.getShippingStreet1());
		soapModel.setShippingStreet2(model.getShippingStreet2());
		soapModel.setShippingStreet3(model.getShippingStreet3());
		soapModel.setShippingCity(model.getShippingCity());
		soapModel.setShippingZip(model.getShippingZip());
		soapModel.setShippingRegionId(model.getShippingRegionId());
		soapModel.setShippingCountryId(model.getShippingCountryId());
		soapModel.setShippingPhoneNumber(model.getShippingPhoneNumber());
		soapModel.setAdvanceStatus(model.getAdvanceStatus());
		soapModel.setPaymentStatus(model.getPaymentStatus());
		soapModel.setOrderDate(model.getOrderDate());
		soapModel.setOrderStatus(model.getOrderStatus());
		soapModel.setPrintedNote(model.getPrintedNote());
		soapModel.setRequestedDeliveryDate(model.getRequestedDeliveryDate());
		soapModel.setManuallyAdjusted(model.isManuallyAdjusted());
		soapModel.setStatus(model.getStatus());
		soapModel.setStatusByUserId(model.getStatusByUserId());
		soapModel.setStatusByUserName(model.getStatusByUserName());
		soapModel.setStatusDate(model.getStatusDate());

		return soapModel;
	}

	public static CommerceOrderSoap[] toSoapModels(CommerceOrder[] models) {
		CommerceOrderSoap[] soapModels = new CommerceOrderSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CommerceOrderSoap[][] toSoapModels(CommerceOrder[][] models) {
		CommerceOrderSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CommerceOrderSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CommerceOrderSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CommerceOrderSoap[] toSoapModels(List<CommerceOrder> models) {
		List<CommerceOrderSoap> soapModels = new ArrayList<CommerceOrderSoap>(
			models.size());

		for (CommerceOrder model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CommerceOrderSoap[soapModels.size()]);
	}

	public CommerceOrderSoap() {
	}

	public long getPrimaryKey() {
		return _commerceOrderId;
	}

	public void setPrimaryKey(long pk) {
		setCommerceOrderId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public String getExternalReferenceCode() {
		return _externalReferenceCode;
	}

	public void setExternalReferenceCode(String externalReferenceCode) {
		_externalReferenceCode = externalReferenceCode;
	}

	public long getCommerceOrderId() {
		return _commerceOrderId;
	}

	public void setCommerceOrderId(long commerceOrderId) {
		_commerceOrderId = commerceOrderId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getCommerceAccountId() {
		return _commerceAccountId;
	}

	public void setCommerceAccountId(long commerceAccountId) {
		_commerceAccountId = commerceAccountId;
	}

	public long getCommerceCurrencyId() {
		return _commerceCurrencyId;
	}

	public void setCommerceCurrencyId(long commerceCurrencyId) {
		_commerceCurrencyId = commerceCurrencyId;
	}

	public long getBillingAddressId() {
		return _billingAddressId;
	}

	public void setBillingAddressId(long billingAddressId) {
		_billingAddressId = billingAddressId;
	}

	public long getShippingAddressId() {
		return _shippingAddressId;
	}

	public void setShippingAddressId(long shippingAddressId) {
		_shippingAddressId = shippingAddressId;
	}

	public String getCommercePaymentMethodKey() {
		return _commercePaymentMethodKey;
	}

	public void setCommercePaymentMethodKey(String commercePaymentMethodKey) {
		_commercePaymentMethodKey = commercePaymentMethodKey;
	}

	public String getTransactionId() {
		return _transactionId;
	}

	public void setTransactionId(String transactionId) {
		_transactionId = transactionId;
	}

	public long getCommerceShippingMethodId() {
		return _commerceShippingMethodId;
	}

	public void setCommerceShippingMethodId(long commerceShippingMethodId) {
		_commerceShippingMethodId = commerceShippingMethodId;
	}

	public String getShippingOptionName() {
		return _shippingOptionName;
	}

	public void setShippingOptionName(String shippingOptionName) {
		_shippingOptionName = shippingOptionName;
	}

	public String getPurchaseOrderNumber() {
		return _purchaseOrderNumber;
	}

	public void setPurchaseOrderNumber(String purchaseOrderNumber) {
		_purchaseOrderNumber = purchaseOrderNumber;
	}

	public String getCouponCode() {
		return _couponCode;
	}

	public void setCouponCode(String couponCode) {
		_couponCode = couponCode;
	}

	public Date getLastPriceUpdateDate() {
		return _lastPriceUpdateDate;
	}

	public void setLastPriceUpdateDate(Date lastPriceUpdateDate) {
		_lastPriceUpdateDate = lastPriceUpdateDate;
	}

	public BigDecimal getSubtotal() {
		return _subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		_subtotal = subtotal;
	}

	public BigDecimal getSubtotalDiscountAmount() {
		return _subtotalDiscountAmount;
	}

	public void setSubtotalDiscountAmount(BigDecimal subtotalDiscountAmount) {
		_subtotalDiscountAmount = subtotalDiscountAmount;
	}

	public BigDecimal getSubtotalDiscountPercentageLevel1() {
		return _subtotalDiscountPercentageLevel1;
	}

	public void setSubtotalDiscountPercentageLevel1(
		BigDecimal subtotalDiscountPercentageLevel1) {

		_subtotalDiscountPercentageLevel1 = subtotalDiscountPercentageLevel1;
	}

	public BigDecimal getSubtotalDiscountPercentageLevel2() {
		return _subtotalDiscountPercentageLevel2;
	}

	public void setSubtotalDiscountPercentageLevel2(
		BigDecimal subtotalDiscountPercentageLevel2) {

		_subtotalDiscountPercentageLevel2 = subtotalDiscountPercentageLevel2;
	}

	public BigDecimal getSubtotalDiscountPercentageLevel3() {
		return _subtotalDiscountPercentageLevel3;
	}

	public void setSubtotalDiscountPercentageLevel3(
		BigDecimal subtotalDiscountPercentageLevel3) {

		_subtotalDiscountPercentageLevel3 = subtotalDiscountPercentageLevel3;
	}

	public BigDecimal getSubtotalDiscountPercentageLevel4() {
		return _subtotalDiscountPercentageLevel4;
	}

	public void setSubtotalDiscountPercentageLevel4(
		BigDecimal subtotalDiscountPercentageLevel4) {

		_subtotalDiscountPercentageLevel4 = subtotalDiscountPercentageLevel4;
	}

	public BigDecimal getShippingAmount() {
		return _shippingAmount;
	}

	public void setShippingAmount(BigDecimal shippingAmount) {
		_shippingAmount = shippingAmount;
	}

	public BigDecimal getShippingDiscountAmount() {
		return _shippingDiscountAmount;
	}

	public void setShippingDiscountAmount(BigDecimal shippingDiscountAmount) {
		_shippingDiscountAmount = shippingDiscountAmount;
	}

	public BigDecimal getShippingDiscountPercentageLevel1() {
		return _shippingDiscountPercentageLevel1;
	}

	public void setShippingDiscountPercentageLevel1(
		BigDecimal shippingDiscountPercentageLevel1) {

		_shippingDiscountPercentageLevel1 = shippingDiscountPercentageLevel1;
	}

	public BigDecimal getShippingDiscountPercentageLevel2() {
		return _shippingDiscountPercentageLevel2;
	}

	public void setShippingDiscountPercentageLevel2(
		BigDecimal shippingDiscountPercentageLevel2) {

		_shippingDiscountPercentageLevel2 = shippingDiscountPercentageLevel2;
	}

	public BigDecimal getShippingDiscountPercentageLevel3() {
		return _shippingDiscountPercentageLevel3;
	}

	public void setShippingDiscountPercentageLevel3(
		BigDecimal shippingDiscountPercentageLevel3) {

		_shippingDiscountPercentageLevel3 = shippingDiscountPercentageLevel3;
	}

	public BigDecimal getShippingDiscountPercentageLevel4() {
		return _shippingDiscountPercentageLevel4;
	}

	public void setShippingDiscountPercentageLevel4(
		BigDecimal shippingDiscountPercentageLevel4) {

		_shippingDiscountPercentageLevel4 = shippingDiscountPercentageLevel4;
	}

	public BigDecimal getTaxAmount() {
		return _taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		_taxAmount = taxAmount;
	}

	public BigDecimal getTotal() {
		return _total;
	}

	public void setTotal(BigDecimal total) {
		_total = total;
	}

	public BigDecimal getTotalDiscountAmount() {
		return _totalDiscountAmount;
	}

	public void setTotalDiscountAmount(BigDecimal totalDiscountAmount) {
		_totalDiscountAmount = totalDiscountAmount;
	}

	public BigDecimal getTotalDiscountPercentageLevel1() {
		return _totalDiscountPercentageLevel1;
	}

	public void setTotalDiscountPercentageLevel1(
		BigDecimal totalDiscountPercentageLevel1) {

		_totalDiscountPercentageLevel1 = totalDiscountPercentageLevel1;
	}

	public BigDecimal getTotalDiscountPercentageLevel2() {
		return _totalDiscountPercentageLevel2;
	}

	public void setTotalDiscountPercentageLevel2(
		BigDecimal totalDiscountPercentageLevel2) {

		_totalDiscountPercentageLevel2 = totalDiscountPercentageLevel2;
	}

	public BigDecimal getTotalDiscountPercentageLevel3() {
		return _totalDiscountPercentageLevel3;
	}

	public void setTotalDiscountPercentageLevel3(
		BigDecimal totalDiscountPercentageLevel3) {

		_totalDiscountPercentageLevel3 = totalDiscountPercentageLevel3;
	}

	public BigDecimal getTotalDiscountPercentageLevel4() {
		return _totalDiscountPercentageLevel4;
	}

	public void setTotalDiscountPercentageLevel4(
		BigDecimal totalDiscountPercentageLevel4) {

		_totalDiscountPercentageLevel4 = totalDiscountPercentageLevel4;
	}

	public String getBillingName() {
		return _billingName;
	}

	public void setBillingName(String billingName) {
		_billingName = billingName;
	}

	public String getBillingDescription() {
		return _billingDescription;
	}

	public void setBillingDescription(String billingDescription) {
		_billingDescription = billingDescription;
	}

	public String getBillingStreet1() {
		return _billingStreet1;
	}

	public void setBillingStreet1(String billingStreet1) {
		_billingStreet1 = billingStreet1;
	}

	public String getBillingStreet2() {
		return _billingStreet2;
	}

	public void setBillingStreet2(String billingStreet2) {
		_billingStreet2 = billingStreet2;
	}

	public String getBillingStreet3() {
		return _billingStreet3;
	}

	public void setBillingStreet3(String billingStreet3) {
		_billingStreet3 = billingStreet3;
	}

	public String getBillingCity() {
		return _billingCity;
	}

	public void setBillingCity(String billingCity) {
		_billingCity = billingCity;
	}

	public String getBillingZip() {
		return _billingZip;
	}

	public void setBillingZip(String billingZip) {
		_billingZip = billingZip;
	}

	public long getBillingRegionId() {
		return _billingRegionId;
	}

	public void setBillingRegionId(long billingRegionId) {
		_billingRegionId = billingRegionId;
	}

	public long getBillingCountryId() {
		return _billingCountryId;
	}

	public void setBillingCountryId(long billingCountryId) {
		_billingCountryId = billingCountryId;
	}

	public String getBillingPhoneNumber() {
		return _billingPhoneNumber;
	}

	public void setBillingPhoneNumber(String billingPhoneNumber) {
		_billingPhoneNumber = billingPhoneNumber;
	}

	public String getShippingName() {
		return _shippingName;
	}

	public void setShippingName(String shippingName) {
		_shippingName = shippingName;
	}

	public String getShippingDescription() {
		return _shippingDescription;
	}

	public void setShippingDescription(String shippingDescription) {
		_shippingDescription = shippingDescription;
	}

	public String getShippingStreet1() {
		return _shippingStreet1;
	}

	public void setShippingStreet1(String shippingStreet1) {
		_shippingStreet1 = shippingStreet1;
	}

	public String getShippingStreet2() {
		return _shippingStreet2;
	}

	public void setShippingStreet2(String shippingStreet2) {
		_shippingStreet2 = shippingStreet2;
	}

	public String getShippingStreet3() {
		return _shippingStreet3;
	}

	public void setShippingStreet3(String shippingStreet3) {
		_shippingStreet3 = shippingStreet3;
	}

	public String getShippingCity() {
		return _shippingCity;
	}

	public void setShippingCity(String shippingCity) {
		_shippingCity = shippingCity;
	}

	public String getShippingZip() {
		return _shippingZip;
	}

	public void setShippingZip(String shippingZip) {
		_shippingZip = shippingZip;
	}

	public long getShippingRegionId() {
		return _shippingRegionId;
	}

	public void setShippingRegionId(long shippingRegionId) {
		_shippingRegionId = shippingRegionId;
	}

	public long getShippingCountryId() {
		return _shippingCountryId;
	}

	public void setShippingCountryId(long shippingCountryId) {
		_shippingCountryId = shippingCountryId;
	}

	public String getShippingPhoneNumber() {
		return _shippingPhoneNumber;
	}

	public void setShippingPhoneNumber(String shippingPhoneNumber) {
		_shippingPhoneNumber = shippingPhoneNumber;
	}

	public String getAdvanceStatus() {
		return _advanceStatus;
	}

	public void setAdvanceStatus(String advanceStatus) {
		_advanceStatus = advanceStatus;
	}

	public int getPaymentStatus() {
		return _paymentStatus;
	}

	public void setPaymentStatus(int paymentStatus) {
		_paymentStatus = paymentStatus;
	}

	public Date getOrderDate() {
		return _orderDate;
	}

	public void setOrderDate(Date orderDate) {
		_orderDate = orderDate;
	}

	public int getOrderStatus() {
		return _orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		_orderStatus = orderStatus;
	}

	public String getPrintedNote() {
		return _printedNote;
	}

	public void setPrintedNote(String printedNote) {
		_printedNote = printedNote;
	}

	public Date getRequestedDeliveryDate() {
		return _requestedDeliveryDate;
	}

	public void setRequestedDeliveryDate(Date requestedDeliveryDate) {
		_requestedDeliveryDate = requestedDeliveryDate;
	}

	public boolean getManuallyAdjusted() {
		return _manuallyAdjusted;
	}

	public boolean isManuallyAdjusted() {
		return _manuallyAdjusted;
	}

	public void setManuallyAdjusted(boolean manuallyAdjusted) {
		_manuallyAdjusted = manuallyAdjusted;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public long getStatusByUserId() {
		return _statusByUserId;
	}

	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;
	}

	public String getStatusByUserName() {
		return _statusByUserName;
	}

	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;
	}

	public Date getStatusDate() {
		return _statusDate;
	}

	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;
	}

	private String _uuid;
	private String _externalReferenceCode;
	private long _commerceOrderId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _commerceAccountId;
	private long _commerceCurrencyId;
	private long _billingAddressId;
	private long _shippingAddressId;
	private String _commercePaymentMethodKey;
	private String _transactionId;
	private long _commerceShippingMethodId;
	private String _shippingOptionName;
	private String _purchaseOrderNumber;
	private String _couponCode;
	private Date _lastPriceUpdateDate;
	private BigDecimal _subtotal;
	private BigDecimal _subtotalDiscountAmount;
	private BigDecimal _subtotalDiscountPercentageLevel1;
	private BigDecimal _subtotalDiscountPercentageLevel2;
	private BigDecimal _subtotalDiscountPercentageLevel3;
	private BigDecimal _subtotalDiscountPercentageLevel4;
	private BigDecimal _shippingAmount;
	private BigDecimal _shippingDiscountAmount;
	private BigDecimal _shippingDiscountPercentageLevel1;
	private BigDecimal _shippingDiscountPercentageLevel2;
	private BigDecimal _shippingDiscountPercentageLevel3;
	private BigDecimal _shippingDiscountPercentageLevel4;
	private BigDecimal _taxAmount;
	private BigDecimal _total;
	private BigDecimal _totalDiscountAmount;
	private BigDecimal _totalDiscountPercentageLevel1;
	private BigDecimal _totalDiscountPercentageLevel2;
	private BigDecimal _totalDiscountPercentageLevel3;
	private BigDecimal _totalDiscountPercentageLevel4;
	private String _billingName;
	private String _billingDescription;
	private String _billingStreet1;
	private String _billingStreet2;
	private String _billingStreet3;
	private String _billingCity;
	private String _billingZip;
	private long _billingRegionId;
	private long _billingCountryId;
	private String _billingPhoneNumber;
	private String _shippingName;
	private String _shippingDescription;
	private String _shippingStreet1;
	private String _shippingStreet2;
	private String _shippingStreet3;
	private String _shippingCity;
	private String _shippingZip;
	private long _shippingRegionId;
	private long _shippingCountryId;
	private String _shippingPhoneNumber;
	private String _advanceStatus;
	private int _paymentStatus;
	private Date _orderDate;
	private int _orderStatus;
	private String _printedNote;
	private Date _requestedDeliveryDate;
	private boolean _manuallyAdjusted;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;

}