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

package com.liferay.commerce.batch.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.commerce.batch.service.http.CommerceBatchEntryServiceSoap}.
 *
 * @author Matija Petanjek
 * @see com.liferay.commerce.batch.service.http.CommerceBatchEntryServiceSoap
 * @generated
 */
@ProviderType
public class CommerceBatchEntrySoap implements Serializable {
	public static CommerceBatchEntrySoap toSoapModel(CommerceBatchEntry model) {
		CommerceBatchEntrySoap soapModel = new CommerceBatchEntrySoap();

		soapModel.setCommerceBatchEntryId(model.getCommerceBatchEntryId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setKey(model.getKey());
		soapModel.setName(model.getName());
		soapModel.setStartTime(model.getStartTime());
		soapModel.setEndTime(model.getEndTime());
		soapModel.setStatus(model.getStatus());
		soapModel.setCallbackURL(model.getCallbackURL());

		return soapModel;
	}

	public static CommerceBatchEntrySoap[] toSoapModels(
		CommerceBatchEntry[] models) {
		CommerceBatchEntrySoap[] soapModels = new CommerceBatchEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CommerceBatchEntrySoap[][] toSoapModels(
		CommerceBatchEntry[][] models) {
		CommerceBatchEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CommerceBatchEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new CommerceBatchEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CommerceBatchEntrySoap[] toSoapModels(
		List<CommerceBatchEntry> models) {
		List<CommerceBatchEntrySoap> soapModels = new ArrayList<CommerceBatchEntrySoap>(models.size());

		for (CommerceBatchEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CommerceBatchEntrySoap[soapModels.size()]);
	}

	public CommerceBatchEntrySoap() {
	}

	public long getPrimaryKey() {
		return _commerceBatchEntryId;
	}

	public void setPrimaryKey(long pk) {
		setCommerceBatchEntryId(pk);
	}

	public long getCommerceBatchEntryId() {
		return _commerceBatchEntryId;
	}

	public void setCommerceBatchEntryId(long commerceBatchEntryId) {
		_commerceBatchEntryId = commerceBatchEntryId;
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

	public String getKey() {
		return _key;
	}

	public void setKey(String key) {
		_key = key;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public Date getStartTime() {
		return _startTime;
	}

	public void setStartTime(Date startTime) {
		_startTime = startTime;
	}

	public Date getEndTime() {
		return _endTime;
	}

	public void setEndTime(Date endTime) {
		_endTime = endTime;
	}

	public String getStatus() {
		return _status;
	}

	public void setStatus(String status) {
		_status = status;
	}

	public String getCallbackURL() {
		return _callbackURL;
	}

	public void setCallbackURL(String callbackURL) {
		_callbackURL = callbackURL;
	}

	private long _commerceBatchEntryId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _key;
	private String _name;
	private Date _startTime;
	private Date _endTime;
	private String _status;
	private String _callbackURL;
}