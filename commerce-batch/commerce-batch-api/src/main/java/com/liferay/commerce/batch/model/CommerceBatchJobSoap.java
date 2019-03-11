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
 * This class is used by SOAP remote services, specifically {@link com.liferay.commerce.batch.service.http.CommerceBatchJobServiceSoap}.
 *
 * @author Matija Petanjek
 * @see com.liferay.commerce.batch.service.http.CommerceBatchJobServiceSoap
 * @generated
 */
@ProviderType
public class CommerceBatchJobSoap implements Serializable {
	public static CommerceBatchJobSoap toSoapModel(CommerceBatchJob model) {
		CommerceBatchJobSoap soapModel = new CommerceBatchJobSoap();

		soapModel.setCommerceBatchJobId(model.getCommerceBatchJobId());
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

	public static CommerceBatchJobSoap[] toSoapModels(CommerceBatchJob[] models) {
		CommerceBatchJobSoap[] soapModels = new CommerceBatchJobSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CommerceBatchJobSoap[][] toSoapModels(
		CommerceBatchJob[][] models) {
		CommerceBatchJobSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CommerceBatchJobSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CommerceBatchJobSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CommerceBatchJobSoap[] toSoapModels(
		List<CommerceBatchJob> models) {
		List<CommerceBatchJobSoap> soapModels = new ArrayList<CommerceBatchJobSoap>(models.size());

		for (CommerceBatchJob model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CommerceBatchJobSoap[soapModels.size()]);
	}

	public CommerceBatchJobSoap() {
	}

	public long getPrimaryKey() {
		return _commerceBatchJobId;
	}

	public void setPrimaryKey(long pk) {
		setCommerceBatchJobId(pk);
	}

	public long getCommerceBatchJobId() {
		return _commerceBatchJobId;
	}

	public void setCommerceBatchJobId(long commerceBatchJobId) {
		_commerceBatchJobId = commerceBatchJobId;
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

	private long _commerceBatchJobId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _key;
	private String _name;
	private Date _startTime;
	private Date _endTime;
	private String _status;
	private String _callbackURL;
}