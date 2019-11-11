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

package com.liferay.commerce.application.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.commerce.application.service.http.CommerceApplicationBrandServiceSoap}.
 *
 * @author Luca Pellizzon
 * @see com.liferay.commerce.application.service.http.CommerceApplicationBrandServiceSoap
 * @generated
 */
@ProviderType
public class CommerceApplicationBrandSoap implements Serializable {
	public static CommerceApplicationBrandSoap toSoapModel(
		CommerceApplicationBrand model) {
		CommerceApplicationBrandSoap soapModel = new CommerceApplicationBrandSoap();

		soapModel.setCommerceApplicationBrandId(model.getCommerceApplicationBrandId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setLogoId(model.getLogoId());

		return soapModel;
	}

	public static CommerceApplicationBrandSoap[] toSoapModels(
		CommerceApplicationBrand[] models) {
		CommerceApplicationBrandSoap[] soapModels = new CommerceApplicationBrandSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CommerceApplicationBrandSoap[][] toSoapModels(
		CommerceApplicationBrand[][] models) {
		CommerceApplicationBrandSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CommerceApplicationBrandSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CommerceApplicationBrandSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CommerceApplicationBrandSoap[] toSoapModels(
		List<CommerceApplicationBrand> models) {
		List<CommerceApplicationBrandSoap> soapModels = new ArrayList<CommerceApplicationBrandSoap>(models.size());

		for (CommerceApplicationBrand model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CommerceApplicationBrandSoap[soapModels.size()]);
	}

	public CommerceApplicationBrandSoap() {
	}

	public long getPrimaryKey() {
		return _commerceApplicationBrandId;
	}

	public void setPrimaryKey(long pk) {
		setCommerceApplicationBrandId(pk);
	}

	public long getCommerceApplicationBrandId() {
		return _commerceApplicationBrandId;
	}

	public void setCommerceApplicationBrandId(long commerceApplicationBrandId) {
		_commerceApplicationBrandId = commerceApplicationBrandId;
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

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public long getLogoId() {
		return _logoId;
	}

	public void setLogoId(long logoId) {
		_logoId = logoId;
	}

	private long _commerceApplicationBrandId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private long _logoId;
}