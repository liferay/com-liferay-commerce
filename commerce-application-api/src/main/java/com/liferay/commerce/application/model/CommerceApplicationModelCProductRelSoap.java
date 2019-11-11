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
 * This class is used by SOAP remote services, specifically {@link com.liferay.commerce.application.service.http.CommerceApplicationModelCProductRelServiceSoap}.
 *
 * @author Luca Pellizzon
 * @see com.liferay.commerce.application.service.http.CommerceApplicationModelCProductRelServiceSoap
 * @generated
 */
@ProviderType
public class CommerceApplicationModelCProductRelSoap implements Serializable {
	public static CommerceApplicationModelCProductRelSoap toSoapModel(
		CommerceApplicationModelCProductRel model) {
		CommerceApplicationModelCProductRelSoap soapModel = new CommerceApplicationModelCProductRelSoap();

		soapModel.setCommerceApplicationModelCProductRelId(model.getCommerceApplicationModelCProductRelId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCommerceApplicationModelId(model.getCommerceApplicationModelId());
		soapModel.setCProductId(model.getCProductId());

		return soapModel;
	}

	public static CommerceApplicationModelCProductRelSoap[] toSoapModels(
		CommerceApplicationModelCProductRel[] models) {
		CommerceApplicationModelCProductRelSoap[] soapModels = new CommerceApplicationModelCProductRelSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CommerceApplicationModelCProductRelSoap[][] toSoapModels(
		CommerceApplicationModelCProductRel[][] models) {
		CommerceApplicationModelCProductRelSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CommerceApplicationModelCProductRelSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CommerceApplicationModelCProductRelSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CommerceApplicationModelCProductRelSoap[] toSoapModels(
		List<CommerceApplicationModelCProductRel> models) {
		List<CommerceApplicationModelCProductRelSoap> soapModels = new ArrayList<CommerceApplicationModelCProductRelSoap>(models.size());

		for (CommerceApplicationModelCProductRel model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CommerceApplicationModelCProductRelSoap[soapModels.size()]);
	}

	public CommerceApplicationModelCProductRelSoap() {
	}

	public long getPrimaryKey() {
		return _commerceApplicationModelCProductRelId;
	}

	public void setPrimaryKey(long pk) {
		setCommerceApplicationModelCProductRelId(pk);
	}

	public long getCommerceApplicationModelCProductRelId() {
		return _commerceApplicationModelCProductRelId;
	}

	public void setCommerceApplicationModelCProductRelId(
		long commerceApplicationModelCProductRelId) {
		_commerceApplicationModelCProductRelId = commerceApplicationModelCProductRelId;
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

	public long getCommerceApplicationModelId() {
		return _commerceApplicationModelId;
	}

	public void setCommerceApplicationModelId(long commerceApplicationModelId) {
		_commerceApplicationModelId = commerceApplicationModelId;
	}

	public long getCProductId() {
		return _CProductId;
	}

	public void setCProductId(long CProductId) {
		_CProductId = CProductId;
	}

	private long _commerceApplicationModelCProductRelId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _commerceApplicationModelId;
	private long _CProductId;
}