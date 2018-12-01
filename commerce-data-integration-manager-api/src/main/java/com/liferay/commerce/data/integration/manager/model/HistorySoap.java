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

package com.liferay.commerce.data.integration.manager.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.data.integration.service.http.HistoryServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.data.integration.service.http.HistoryServiceSoap
 * @generated
 */
@ProviderType
public class HistorySoap implements Serializable {
	public static HistorySoap toSoapModel(History model) {
		HistorySoap soapModel = new HistorySoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setHistoryId(model.getHistoryId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setLaunchType(model.getLaunchType());
		soapModel.setScheduledTaskId(model.getScheduledTaskId());
		soapModel.setStatus(model.getStatus());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setEndDate(model.getEndDate());
		soapModel.setExecutionType(model.getExecutionType());
		soapModel.setErrorLogFileEntryId(model.getErrorLogFileEntryId());
		soapModel.setRuntimeLogFileEntryId(model.getRuntimeLogFileEntryId());

		return soapModel;
	}

	public static HistorySoap[] toSoapModels(History[] models) {
		HistorySoap[] soapModels = new HistorySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static HistorySoap[][] toSoapModels(History[][] models) {
		HistorySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new HistorySoap[models.length][models[0].length];
		}
		else {
			soapModels = new HistorySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static HistorySoap[] toSoapModels(List<History> models) {
		List<HistorySoap> soapModels = new ArrayList<HistorySoap>(models.size());

		for (History model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new HistorySoap[soapModels.size()]);
	}

	public HistorySoap() {
	}

	public long getPrimaryKey() {
		return _historyId;
	}

	public void setPrimaryKey(long pk) {
		setHistoryId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getHistoryId() {
		return _historyId;
	}

	public void setHistoryId(long historyId) {
		_historyId = historyId;
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

	public String getLaunchType() {
		return _launchType;
	}

	public void setLaunchType(String launchType) {
		_launchType = launchType;
	}

	public long getScheduledTaskId() {
		return _scheduledTaskId;
	}

	public void setScheduledTaskId(long scheduledTaskId) {
		_scheduledTaskId = scheduledTaskId;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	public String getExecutionType() {
		return _executionType;
	}

	public void setExecutionType(String executionType) {
		_executionType = executionType;
	}

	public long getErrorLogFileEntryId() {
		return _errorLogFileEntryId;
	}

	public void setErrorLogFileEntryId(long errorLogFileEntryId) {
		_errorLogFileEntryId = errorLogFileEntryId;
	}

	public long getRuntimeLogFileEntryId() {
		return _runtimeLogFileEntryId;
	}

	public void setRuntimeLogFileEntryId(long runtimeLogFileEntryId) {
		_runtimeLogFileEntryId = runtimeLogFileEntryId;
	}

	private String _uuid;
	private long _historyId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _launchType;
	private long _scheduledTaskId;
	private int _status;
	private Date _startDate;
	private Date _endDate;
	private String _executionType;
	private long _errorLogFileEntryId;
	private long _runtimeLogFileEntryId;
}