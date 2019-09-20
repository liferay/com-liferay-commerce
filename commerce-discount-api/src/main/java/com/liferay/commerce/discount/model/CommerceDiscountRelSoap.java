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

package com.liferay.commerce.discount.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.commerce.discount.service.http.CommerceDiscountRelServiceSoap}.
 *
 * @author Marco Leo
 * @generated
 */
public class CommerceDiscountRelSoap implements Serializable {

	public static CommerceDiscountRelSoap toSoapModel(
		CommerceDiscountRel model) {

		CommerceDiscountRelSoap soapModel = new CommerceDiscountRelSoap();

		soapModel.setCommerceDiscountRelId(model.getCommerceDiscountRelId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCommerceDiscountId(model.getCommerceDiscountId());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());

		return soapModel;
	}

	public static CommerceDiscountRelSoap[] toSoapModels(
		CommerceDiscountRel[] models) {

		CommerceDiscountRelSoap[] soapModels =
			new CommerceDiscountRelSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CommerceDiscountRelSoap[][] toSoapModels(
		CommerceDiscountRel[][] models) {

		CommerceDiscountRelSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new CommerceDiscountRelSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CommerceDiscountRelSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CommerceDiscountRelSoap[] toSoapModels(
		List<CommerceDiscountRel> models) {

		List<CommerceDiscountRelSoap> soapModels =
			new ArrayList<CommerceDiscountRelSoap>(models.size());

		for (CommerceDiscountRel model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new CommerceDiscountRelSoap[soapModels.size()]);
	}

	public CommerceDiscountRelSoap() {
	}

	public long getPrimaryKey() {
		return _commerceDiscountRelId;
	}

	public void setPrimaryKey(long pk) {
		setCommerceDiscountRelId(pk);
	}

	public long getCommerceDiscountRelId() {
		return _commerceDiscountRelId;
	}

	public void setCommerceDiscountRelId(long commerceDiscountRelId) {
		_commerceDiscountRelId = commerceDiscountRelId;
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

	public long getCommerceDiscountId() {
		return _commerceDiscountId;
	}

	public void setCommerceDiscountId(long commerceDiscountId) {
		_commerceDiscountId = commerceDiscountId;
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	private long _commerceDiscountRelId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _commerceDiscountId;
	private long _classNameId;
	private long _classPK;

}