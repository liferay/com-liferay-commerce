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

package com.liferay.commerce.bom.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.commerce.bom.service.http.CommerceBOMFolderApplicationRelServiceSoap}.
 *
 * @author Luca Pellizzon
 * @see com.liferay.commerce.bom.service.http.CommerceBOMFolderApplicationRelServiceSoap
 * @generated
 */
@ProviderType
public class CommerceBOMFolderApplicationRelSoap implements Serializable {
	public static CommerceBOMFolderApplicationRelSoap toSoapModel(
		CommerceBOMFolderApplicationRel model) {
		CommerceBOMFolderApplicationRelSoap soapModel = new CommerceBOMFolderApplicationRelSoap();

		soapModel.setCommerceBOMFolderApplicationRelId(model.getCommerceBOMFolderApplicationRelId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCommerceBOMFolderId(model.getCommerceBOMFolderId());
		soapModel.setCommerceApplicationModelId(model.getCommerceApplicationModelId());

		return soapModel;
	}

	public static CommerceBOMFolderApplicationRelSoap[] toSoapModels(
		CommerceBOMFolderApplicationRel[] models) {
		CommerceBOMFolderApplicationRelSoap[] soapModels = new CommerceBOMFolderApplicationRelSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CommerceBOMFolderApplicationRelSoap[][] toSoapModels(
		CommerceBOMFolderApplicationRel[][] models) {
		CommerceBOMFolderApplicationRelSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CommerceBOMFolderApplicationRelSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CommerceBOMFolderApplicationRelSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CommerceBOMFolderApplicationRelSoap[] toSoapModels(
		List<CommerceBOMFolderApplicationRel> models) {
		List<CommerceBOMFolderApplicationRelSoap> soapModels = new ArrayList<CommerceBOMFolderApplicationRelSoap>(models.size());

		for (CommerceBOMFolderApplicationRel model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CommerceBOMFolderApplicationRelSoap[soapModels.size()]);
	}

	public CommerceBOMFolderApplicationRelSoap() {
	}

	public long getPrimaryKey() {
		return _commerceBOMFolderApplicationRelId;
	}

	public void setPrimaryKey(long pk) {
		setCommerceBOMFolderApplicationRelId(pk);
	}

	public long getCommerceBOMFolderApplicationRelId() {
		return _commerceBOMFolderApplicationRelId;
	}

	public void setCommerceBOMFolderApplicationRelId(
		long commerceBOMFolderApplicationRelId) {
		_commerceBOMFolderApplicationRelId = commerceBOMFolderApplicationRelId;
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

	public long getCommerceBOMFolderId() {
		return _commerceBOMFolderId;
	}

	public void setCommerceBOMFolderId(long commerceBOMFolderId) {
		_commerceBOMFolderId = commerceBOMFolderId;
	}

	public long getCommerceApplicationModelId() {
		return _commerceApplicationModelId;
	}

	public void setCommerceApplicationModelId(long commerceApplicationModelId) {
		_commerceApplicationModelId = commerceApplicationModelId;
	}

	private long _commerceBOMFolderApplicationRelId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _commerceBOMFolderId;
	private long _commerceApplicationModelId;
}