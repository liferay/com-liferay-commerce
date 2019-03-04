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
 * This class is used by SOAP remote services, specifically {@link com.liferay.commerce.batch.service.http.CommerceBatchJobExecutionServiceSoap}.
 *
 * @author Matija Petanjek
 * @see com.liferay.commerce.batch.service.http.CommerceBatchJobExecutionServiceSoap
 * @generated
 */
@ProviderType
public class CommerceBatchJobExecutionSoap implements Serializable {
	public static CommerceBatchJobExecutionSoap toSoapModel(
		CommerceBatchJobExecution model) {
		CommerceBatchJobExecutionSoap soapModel = new CommerceBatchJobExecutionSoap();

		soapModel.setCommerceBatchJobExecutionId(model.getCommerceBatchJobExecutionId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCommerceBatchJobInstanceId(model.getCommerceBatchJobInstanceId());
		soapModel.setStartTime(model.getStartTime());
		soapModel.setEndTime(model.getEndTime());
		soapModel.setStatus(model.getStatus());
		soapModel.setExitCode(model.getExitCode());
		soapModel.setExitMessage(model.getExitMessage());

		return soapModel;
	}

	public static CommerceBatchJobExecutionSoap[] toSoapModels(
		CommerceBatchJobExecution[] models) {
		CommerceBatchJobExecutionSoap[] soapModels = new CommerceBatchJobExecutionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CommerceBatchJobExecutionSoap[][] toSoapModels(
		CommerceBatchJobExecution[][] models) {
		CommerceBatchJobExecutionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CommerceBatchJobExecutionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CommerceBatchJobExecutionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CommerceBatchJobExecutionSoap[] toSoapModels(
		List<CommerceBatchJobExecution> models) {
		List<CommerceBatchJobExecutionSoap> soapModels = new ArrayList<CommerceBatchJobExecutionSoap>(models.size());

		for (CommerceBatchJobExecution model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CommerceBatchJobExecutionSoap[soapModels.size()]);
	}

	public CommerceBatchJobExecutionSoap() {
	}

	public long getPrimaryKey() {
		return _commerceBatchJobExecutionId;
	}

	public void setPrimaryKey(long pk) {
		setCommerceBatchJobExecutionId(pk);
	}

	public long getCommerceBatchJobExecutionId() {
		return _commerceBatchJobExecutionId;
	}

	public void setCommerceBatchJobExecutionId(long commerceBatchJobExecutionId) {
		_commerceBatchJobExecutionId = commerceBatchJobExecutionId;
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

	public long getCommerceBatchJobInstanceId() {
		return _commerceBatchJobInstanceId;
	}

	public void setCommerceBatchJobInstanceId(long commerceBatchJobInstanceId) {
		_commerceBatchJobInstanceId = commerceBatchJobInstanceId;
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

	public String getExitCode() {
		return _exitCode;
	}

	public void setExitCode(String exitCode) {
		_exitCode = exitCode;
	}

	public String getExitMessage() {
		return _exitMessage;
	}

	public void setExitMessage(String exitMessage) {
		_exitMessage = exitMessage;
	}

	private long _commerceBatchJobExecutionId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _commerceBatchJobInstanceId;
	private Date _startTime;
	private Date _endTime;
	private String _status;
	private String _exitCode;
	private String _exitMessage;
}