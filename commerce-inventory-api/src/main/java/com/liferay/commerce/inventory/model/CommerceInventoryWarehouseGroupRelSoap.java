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

package com.liferay.commerce.inventory.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.commerce.inventory.service.http.CommerceInventoryWarehouseGroupRelServiceSoap}.
 *
 * @author Luca Pellizzon
 * @see com.liferay.commerce.inventory.service.http.CommerceInventoryWarehouseGroupRelServiceSoap
 * @generated
 */
@ProviderType
public class CommerceInventoryWarehouseGroupRelSoap implements Serializable {
	public static CommerceInventoryWarehouseGroupRelSoap toSoapModel(
		CommerceInventoryWarehouseGroupRel model) {
		CommerceInventoryWarehouseGroupRelSoap soapModel = new CommerceInventoryWarehouseGroupRelSoap();

		soapModel.setCommerceInventoryWarehouseGroupRelId(model.getCommerceInventoryWarehouseGroupRelId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCommerceWarehouseId(model.getCommerceWarehouseId());
		soapModel.setPrimary(model.isPrimary());

		return soapModel;
	}

	public static CommerceInventoryWarehouseGroupRelSoap[] toSoapModels(
		CommerceInventoryWarehouseGroupRel[] models) {
		CommerceInventoryWarehouseGroupRelSoap[] soapModels = new CommerceInventoryWarehouseGroupRelSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CommerceInventoryWarehouseGroupRelSoap[][] toSoapModels(
		CommerceInventoryWarehouseGroupRel[][] models) {
		CommerceInventoryWarehouseGroupRelSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CommerceInventoryWarehouseGroupRelSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CommerceInventoryWarehouseGroupRelSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CommerceInventoryWarehouseGroupRelSoap[] toSoapModels(
		List<CommerceInventoryWarehouseGroupRel> models) {
		List<CommerceInventoryWarehouseGroupRelSoap> soapModels = new ArrayList<CommerceInventoryWarehouseGroupRelSoap>(models.size());

		for (CommerceInventoryWarehouseGroupRel model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CommerceInventoryWarehouseGroupRelSoap[soapModels.size()]);
	}

	public CommerceInventoryWarehouseGroupRelSoap() {
	}

	public long getPrimaryKey() {
		return _commerceInventoryWarehouseGroupRelId;
	}

	public void setPrimaryKey(long pk) {
		setCommerceInventoryWarehouseGroupRelId(pk);
	}

	public long getCommerceInventoryWarehouseGroupRelId() {
		return _commerceInventoryWarehouseGroupRelId;
	}

	public void setCommerceInventoryWarehouseGroupRelId(
		long commerceInventoryWarehouseGroupRelId) {
		_commerceInventoryWarehouseGroupRelId = commerceInventoryWarehouseGroupRelId;
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

	public long getCommerceWarehouseId() {
		return _commerceWarehouseId;
	}

	public void setCommerceWarehouseId(long commerceWarehouseId) {
		_commerceWarehouseId = commerceWarehouseId;
	}

	public boolean getPrimary() {
		return _primary;
	}

	public boolean isPrimary() {
		return _primary;
	}

	public void setPrimary(boolean primary) {
		_primary = primary;
	}

	private long _commerceInventoryWarehouseGroupRelId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _commerceWarehouseId;
	private boolean _primary;
}