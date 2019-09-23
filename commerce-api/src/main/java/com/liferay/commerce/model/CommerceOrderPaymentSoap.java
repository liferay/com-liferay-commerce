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
 * This class is used by SOAP remote services.
 *
 * @author Alessio Antonio Rendina
 * @generated
 */
public class CommerceOrderPaymentSoap implements Serializable {

	public static CommerceOrderPaymentSoap toSoapModel(
		CommerceOrderPayment model) {

		CommerceOrderPaymentSoap soapModel = new CommerceOrderPaymentSoap();

		soapModel.setCommerceOrderPaymentId(model.getCommerceOrderPaymentId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCommerceOrderId(model.getCommerceOrderId());
		soapModel.setCommercePaymentMethodKey(
			model.getCommercePaymentMethodKey());
		soapModel.setContent(model.getContent());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static CommerceOrderPaymentSoap[] toSoapModels(
		CommerceOrderPayment[] models) {

		CommerceOrderPaymentSoap[] soapModels =
			new CommerceOrderPaymentSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CommerceOrderPaymentSoap[][] toSoapModels(
		CommerceOrderPayment[][] models) {

		CommerceOrderPaymentSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new CommerceOrderPaymentSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CommerceOrderPaymentSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CommerceOrderPaymentSoap[] toSoapModels(
		List<CommerceOrderPayment> models) {

		List<CommerceOrderPaymentSoap> soapModels =
			new ArrayList<CommerceOrderPaymentSoap>(models.size());

		for (CommerceOrderPayment model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new CommerceOrderPaymentSoap[soapModels.size()]);
	}

	public CommerceOrderPaymentSoap() {
	}

	public long getPrimaryKey() {
		return _commerceOrderPaymentId;
	}

	public void setPrimaryKey(long pk) {
		setCommerceOrderPaymentId(pk);
	}

	public long getCommerceOrderPaymentId() {
		return _commerceOrderPaymentId;
	}

	public void setCommerceOrderPaymentId(long commerceOrderPaymentId) {
		_commerceOrderPaymentId = commerceOrderPaymentId;
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

	public long getCommerceOrderId() {
		return _commerceOrderId;
	}

	public void setCommerceOrderId(long commerceOrderId) {
		_commerceOrderId = commerceOrderId;
	}

	public String getCommercePaymentMethodKey() {
		return _commercePaymentMethodKey;
	}

	public void setCommercePaymentMethodKey(String commercePaymentMethodKey) {
		_commercePaymentMethodKey = commercePaymentMethodKey;
	}

	public String getContent() {
		return _content;
	}

	public void setContent(String content) {
		_content = content;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _commerceOrderPaymentId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _commerceOrderId;
	private String _commercePaymentMethodKey;
	private String _content;
	private int _status;

}