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

package com.liferay.commerce.account.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.commerce.account.service.http.CommerceAccountServiceSoap}.
 *
 * @author Marco Leo
 * @see com.liferay.commerce.account.service.http.CommerceAccountServiceSoap
 * @generated
 */
@ProviderType
public class CommerceAccountSoap implements Serializable {
	public static CommerceAccountSoap toSoapModel(CommerceAccount model) {
		CommerceAccountSoap soapModel = new CommerceAccountSoap();

		soapModel.setExternalReferenceCode(model.getExternalReferenceCode());
		soapModel.setCommerceAccountId(model.getCommerceAccountId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setParentCommerceAccountId(model.getParentCommerceAccountId());
		soapModel.setTaxId(model.getTaxId());
		soapModel.setActive(model.isActive());

		return soapModel;
	}

	public static CommerceAccountSoap[] toSoapModels(CommerceAccount[] models) {
		CommerceAccountSoap[] soapModels = new CommerceAccountSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CommerceAccountSoap[][] toSoapModels(
		CommerceAccount[][] models) {
		CommerceAccountSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CommerceAccountSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CommerceAccountSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CommerceAccountSoap[] toSoapModels(
		List<CommerceAccount> models) {
		List<CommerceAccountSoap> soapModels = new ArrayList<CommerceAccountSoap>(models.size());

		for (CommerceAccount model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CommerceAccountSoap[soapModels.size()]);
	}

	public CommerceAccountSoap() {
	}

	public long getPrimaryKey() {
		return _commerceAccountId;
	}

	public void setPrimaryKey(long pk) {
		setCommerceAccountId(pk);
	}

	public String getExternalReferenceCode() {
		return _externalReferenceCode;
	}

	public void setExternalReferenceCode(String externalReferenceCode) {
		_externalReferenceCode = externalReferenceCode;
	}

	public long getCommerceAccountId() {
		return _commerceAccountId;
	}

	public void setCommerceAccountId(long commerceAccountId) {
		_commerceAccountId = commerceAccountId;
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

	public long getParentCommerceAccountId() {
		return _parentCommerceAccountId;
	}

	public void setParentCommerceAccountId(long parentCommerceAccountId) {
		_parentCommerceAccountId = parentCommerceAccountId;
	}

	public String getTaxId() {
		return _taxId;
	}

	public void setTaxId(String taxId) {
		_taxId = taxId;
	}

	public boolean getActive() {
		return _active;
	}

	public boolean isActive() {
		return _active;
	}

	public void setActive(boolean active) {
		_active = active;
	}

	private String _externalReferenceCode;
	private long _commerceAccountId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private long _parentCommerceAccountId;
	private String _taxId;
	private boolean _active;
}