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
 * This class is used by SOAP remote services, specifically {@link com.liferay.commerce.bom.service.http.CommerceBOMEntryServiceSoap}.
 *
 * @author Luca Pellizzon
 * @see com.liferay.commerce.bom.service.http.CommerceBOMEntryServiceSoap
 * @generated
 */
@ProviderType
public class CommerceBOMEntrySoap implements Serializable {
	public static CommerceBOMEntrySoap toSoapModel(CommerceBOMEntry model) {
		CommerceBOMEntrySoap soapModel = new CommerceBOMEntrySoap();

		soapModel.setCommerceBOMEntryId(model.getCommerceBOMEntryId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setNumber(model.getNumber());
		soapModel.setCPInstanceUuid(model.getCPInstanceUuid());
		soapModel.setCProductId(model.getCProductId());
		soapModel.setCommerceBOMDefinitionId(model.getCommerceBOMDefinitionId());
		soapModel.setPositionX(model.getPositionX());
		soapModel.setPositionY(model.getPositionY());
		soapModel.setRadius(model.getRadius());

		return soapModel;
	}

	public static CommerceBOMEntrySoap[] toSoapModels(CommerceBOMEntry[] models) {
		CommerceBOMEntrySoap[] soapModels = new CommerceBOMEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CommerceBOMEntrySoap[][] toSoapModels(
		CommerceBOMEntry[][] models) {
		CommerceBOMEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CommerceBOMEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new CommerceBOMEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CommerceBOMEntrySoap[] toSoapModels(
		List<CommerceBOMEntry> models) {
		List<CommerceBOMEntrySoap> soapModels = new ArrayList<CommerceBOMEntrySoap>(models.size());

		for (CommerceBOMEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CommerceBOMEntrySoap[soapModels.size()]);
	}

	public CommerceBOMEntrySoap() {
	}

	public long getPrimaryKey() {
		return _commerceBOMEntryId;
	}

	public void setPrimaryKey(long pk) {
		setCommerceBOMEntryId(pk);
	}

	public long getCommerceBOMEntryId() {
		return _commerceBOMEntryId;
	}

	public void setCommerceBOMEntryId(long commerceBOMEntryId) {
		_commerceBOMEntryId = commerceBOMEntryId;
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

	public int getNumber() {
		return _number;
	}

	public void setNumber(int number) {
		_number = number;
	}

	public String getCPInstanceUuid() {
		return _CPInstanceUuid;
	}

	public void setCPInstanceUuid(String CPInstanceUuid) {
		_CPInstanceUuid = CPInstanceUuid;
	}

	public long getCProductId() {
		return _CProductId;
	}

	public void setCProductId(long CProductId) {
		_CProductId = CProductId;
	}

	public long getCommerceBOMDefinitionId() {
		return _commerceBOMDefinitionId;
	}

	public void setCommerceBOMDefinitionId(long commerceBOMDefinitionId) {
		_commerceBOMDefinitionId = commerceBOMDefinitionId;
	}

	public double getPositionX() {
		return _positionX;
	}

	public void setPositionX(double positionX) {
		_positionX = positionX;
	}

	public double getPositionY() {
		return _positionY;
	}

	public void setPositionY(double positionY) {
		_positionY = positionY;
	}

	public double getRadius() {
		return _radius;
	}

	public void setRadius(double radius) {
		_radius = radius;
	}

	private long _commerceBOMEntryId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private int _number;
	private String _CPInstanceUuid;
	private long _CProductId;
	private long _commerceBOMDefinitionId;
	private double _positionX;
	private double _positionY;
	private double _radius;
}