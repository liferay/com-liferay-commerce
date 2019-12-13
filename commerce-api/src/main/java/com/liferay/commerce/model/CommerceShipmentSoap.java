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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.commerce.service.http.CommerceShipmentServiceSoap}.
 *
 * @author Alessio Antonio Rendina
 * @generated
 */
public class CommerceShipmentSoap implements Serializable {

	public static CommerceShipmentSoap toSoapModel(CommerceShipment model) {
		CommerceShipmentSoap soapModel = new CommerceShipmentSoap();

		soapModel.setCommerceShipmentId(model.getCommerceShipmentId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCommerceAccountId(model.getCommerceAccountId());
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
		soapModel.setCommerceShippingMethodId(
			model.getCommerceShippingMethodId());
		soapModel.setShippingOptionName(model.getShippingOptionName());
		soapModel.setCarrier(model.getCarrier());
		soapModel.setTrackingNumber(model.getTrackingNumber());
		soapModel.setShippingDate(model.getShippingDate());
		soapModel.setExpectedDate(model.getExpectedDate());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static CommerceShipmentSoap[] toSoapModels(
		CommerceShipment[] models) {

		CommerceShipmentSoap[] soapModels =
			new CommerceShipmentSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CommerceShipmentSoap[][] toSoapModels(
		CommerceShipment[][] models) {

		CommerceShipmentSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new CommerceShipmentSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CommerceShipmentSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CommerceShipmentSoap[] toSoapModels(
		List<CommerceShipment> models) {

		List<CommerceShipmentSoap> soapModels =
			new ArrayList<CommerceShipmentSoap>(models.size());

		for (CommerceShipment model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CommerceShipmentSoap[soapModels.size()]);
	}

	public CommerceShipmentSoap() {
	}

	public long getPrimaryKey() {
		return _commerceShipmentId;
	}

	public void setPrimaryKey(long pk) {
		setCommerceShipmentId(pk);
	}

	public long getCommerceShipmentId() {
		return _commerceShipmentId;
	}

	public void setCommerceShipmentId(long commerceShipmentId) {
		_commerceShipmentId = commerceShipmentId;
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

	public String getCarrier() {
		return _carrier;
	}

	public void setCarrier(String carrier) {
		_carrier = carrier;
	}

	public String getTrackingNumber() {
		return _trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		_trackingNumber = trackingNumber;
	}

	public Date getShippingDate() {
		return _shippingDate;
	}

	public void setShippingDate(Date shippingDate) {
		_shippingDate = shippingDate;
	}

	public Date getExpectedDate() {
		return _expectedDate;
	}

	public void setExpectedDate(Date expectedDate) {
		_expectedDate = expectedDate;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _commerceShipmentId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _commerceAccountId;
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
	private long _commerceShippingMethodId;
	private String _shippingOptionName;
	private String _carrier;
	private String _trackingNumber;
	private Date _shippingDate;
	private Date _expectedDate;
	private int _status;

}