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
 * This class is used by SOAP remote services, specifically {@link com.liferay.data.integration.service.http.ScheduledTaskServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.data.integration.service.http.ScheduledTaskServiceSoap
 * @generated
 */
@ProviderType
public class ScheduledTaskSoap implements Serializable {
	public static ScheduledTaskSoap toSoapModel(ScheduledTask model) {
		ScheduledTaskSoap soapModel = new ScheduledTaskSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setScheduledTaskId(model.getScheduledTaskId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setFrequency(model.getFrequency());
		soapModel.setProcessId(model.getProcessId());
		soapModel.setStatus(model.getStatus());
		soapModel.setActive(model.isActive());
		soapModel.setRunStartDate(model.getRunStartDate());
		soapModel.setRunEndDate(model.getRunEndDate());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setStartHour(model.getStartHour());
		soapModel.setEnabled(model.isEnabled());

		return soapModel;
	}

	public static ScheduledTaskSoap[] toSoapModels(ScheduledTask[] models) {
		ScheduledTaskSoap[] soapModels = new ScheduledTaskSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ScheduledTaskSoap[][] toSoapModels(ScheduledTask[][] models) {
		ScheduledTaskSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ScheduledTaskSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ScheduledTaskSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ScheduledTaskSoap[] toSoapModels(List<ScheduledTask> models) {
		List<ScheduledTaskSoap> soapModels = new ArrayList<ScheduledTaskSoap>(models.size());

		for (ScheduledTask model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ScheduledTaskSoap[soapModels.size()]);
	}

	public ScheduledTaskSoap() {
	}

	public long getPrimaryKey() {
		return _scheduledTaskId;
	}

	public void setPrimaryKey(long pk) {
		setScheduledTaskId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getScheduledTaskId() {
		return _scheduledTaskId;
	}

	public void setScheduledTaskId(long scheduledTaskId) {
		_scheduledTaskId = scheduledTaskId;
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

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getFrequency() {
		return _frequency;
	}

	public void setFrequency(String frequency) {
		_frequency = frequency;
	}

	public long getProcessId() {
		return _processId;
	}

	public void setProcessId(long processId) {
		_processId = processId;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
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

	public Date getRunStartDate() {
		return _runStartDate;
	}

	public void setRunStartDate(Date runStartDate) {
		_runStartDate = runStartDate;
	}

	public Date getRunEndDate() {
		return _runEndDate;
	}

	public void setRunEndDate(Date runEndDate) {
		_runEndDate = runEndDate;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public String getStartHour() {
		return _startHour;
	}

	public void setStartHour(String startHour) {
		_startHour = startHour;
	}

	public boolean getEnabled() {
		return _enabled;
	}

	public boolean isEnabled() {
		return _enabled;
	}

	public void setEnabled(boolean enabled) {
		_enabled = enabled;
	}

	private String _uuid;
	private long _scheduledTaskId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _frequency;
	private long _processId;
	private int _status;
	private boolean _active;
	private Date _runStartDate;
	private Date _runEndDate;
	private Date _startDate;
	private String _startHour;
	private boolean _enabled;
}