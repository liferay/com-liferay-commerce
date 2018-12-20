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

import com.liferay.commerce.account.service.persistence.CommerceAccountOrganizationRelPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.commerce.account.service.http.CommerceAccountOrganizationRelServiceSoap}.
 *
 * @author Marco Leo
 * @see com.liferay.commerce.account.service.http.CommerceAccountOrganizationRelServiceSoap
 * @generated
 */
@ProviderType
public class CommerceAccountOrganizationRelSoap implements Serializable {
	public static CommerceAccountOrganizationRelSoap toSoapModel(
		CommerceAccountOrganizationRel model) {
		CommerceAccountOrganizationRelSoap soapModel = new CommerceAccountOrganizationRelSoap();

		soapModel.setCommerceAccountId(model.getCommerceAccountId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());

		return soapModel;
	}

	public static CommerceAccountOrganizationRelSoap[] toSoapModels(
		CommerceAccountOrganizationRel[] models) {
		CommerceAccountOrganizationRelSoap[] soapModels = new CommerceAccountOrganizationRelSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CommerceAccountOrganizationRelSoap[][] toSoapModels(
		CommerceAccountOrganizationRel[][] models) {
		CommerceAccountOrganizationRelSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CommerceAccountOrganizationRelSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CommerceAccountOrganizationRelSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CommerceAccountOrganizationRelSoap[] toSoapModels(
		List<CommerceAccountOrganizationRel> models) {
		List<CommerceAccountOrganizationRelSoap> soapModels = new ArrayList<CommerceAccountOrganizationRelSoap>(models.size());

		for (CommerceAccountOrganizationRel model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CommerceAccountOrganizationRelSoap[soapModels.size()]);
	}

	public CommerceAccountOrganizationRelSoap() {
	}

	public CommerceAccountOrganizationRelPK getPrimaryKey() {
		return new CommerceAccountOrganizationRelPK(_commerceAccountId, _userId);
	}

	public void setPrimaryKey(CommerceAccountOrganizationRelPK pk) {
		setCommerceAccountId(pk.commerceAccountId);
		setUserId(pk.userId);
	}

	public long getCommerceAccountId() {
		return _commerceAccountId;
	}

	public void setCommerceAccountId(long commerceAccountId) {
		_commerceAccountId = commerceAccountId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
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

	private long _commerceAccountId;
	private long _userId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
}