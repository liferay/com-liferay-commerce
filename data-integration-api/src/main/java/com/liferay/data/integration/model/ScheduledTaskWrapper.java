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

package com.liferay.data.integration.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link ScheduledTask}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ScheduledTask
 * @generated
 */
@ProviderType
public class ScheduledTaskWrapper implements ScheduledTask,
	ModelWrapper<ScheduledTask> {
	public ScheduledTaskWrapper(ScheduledTask scheduledTask) {
		_scheduledTask = scheduledTask;
	}

	@Override
	public Class<?> getModelClass() {
		return ScheduledTask.class;
	}

	@Override
	public String getModelClassName() {
		return ScheduledTask.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("scheduledTaskId", getScheduledTaskId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("frequency", getFrequency());
		attributes.put("processId", getProcessId());
		attributes.put("status", getStatus());
		attributes.put("active", isActive());
		attributes.put("runStartDate", getRunStartDate());
		attributes.put("runEndDate", getRunEndDate());
		attributes.put("startDate", getStartDate());
		attributes.put("startHour", getStartHour());
		attributes.put("enabled", isEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long scheduledTaskId = (Long)attributes.get("scheduledTaskId");

		if (scheduledTaskId != null) {
			setScheduledTaskId(scheduledTaskId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String frequency = (String)attributes.get("frequency");

		if (frequency != null) {
			setFrequency(frequency);
		}

		Long processId = (Long)attributes.get("processId");

		if (processId != null) {
			setProcessId(processId);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Boolean active = (Boolean)attributes.get("active");

		if (active != null) {
			setActive(active);
		}

		Date runStartDate = (Date)attributes.get("runStartDate");

		if (runStartDate != null) {
			setRunStartDate(runStartDate);
		}

		Date runEndDate = (Date)attributes.get("runEndDate");

		if (runEndDate != null) {
			setRunEndDate(runEndDate);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		String startHour = (String)attributes.get("startHour");

		if (startHour != null) {
			setStartHour(startHour);
		}

		Boolean enabled = (Boolean)attributes.get("enabled");

		if (enabled != null) {
			setEnabled(enabled);
		}
	}

	@Override
	public Object clone() {
		return new ScheduledTaskWrapper((ScheduledTask)_scheduledTask.clone());
	}

	@Override
	public int compareTo(ScheduledTask scheduledTask) {
		return _scheduledTask.compareTo(scheduledTask);
	}

	/**
	* Returns the active of this scheduled task.
	*
	* @return the active of this scheduled task
	*/
	@Override
	public boolean getActive() {
		return _scheduledTask.getActive();
	}

	/**
	* Returns the company ID of this scheduled task.
	*
	* @return the company ID of this scheduled task
	*/
	@Override
	public long getCompanyId() {
		return _scheduledTask.getCompanyId();
	}

	/**
	* Returns the create date of this scheduled task.
	*
	* @return the create date of this scheduled task
	*/
	@Override
	public Date getCreateDate() {
		return _scheduledTask.getCreateDate();
	}

	/**
	* Returns the enabled of this scheduled task.
	*
	* @return the enabled of this scheduled task
	*/
	@Override
	public boolean getEnabled() {
		return _scheduledTask.getEnabled();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _scheduledTask.getExpandoBridge();
	}

	/**
	* Returns the frequency of this scheduled task.
	*
	* @return the frequency of this scheduled task
	*/
	@Override
	public String getFrequency() {
		return _scheduledTask.getFrequency();
	}

	/**
	* Returns the group ID of this scheduled task.
	*
	* @return the group ID of this scheduled task
	*/
	@Override
	public long getGroupId() {
		return _scheduledTask.getGroupId();
	}

	/**
	* Returns the modified date of this scheduled task.
	*
	* @return the modified date of this scheduled task
	*/
	@Override
	public Date getModifiedDate() {
		return _scheduledTask.getModifiedDate();
	}

	/**
	* Returns the name of this scheduled task.
	*
	* @return the name of this scheduled task
	*/
	@Override
	public String getName() {
		return _scheduledTask.getName();
	}

	/**
	* Returns the primary key of this scheduled task.
	*
	* @return the primary key of this scheduled task
	*/
	@Override
	public long getPrimaryKey() {
		return _scheduledTask.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _scheduledTask.getPrimaryKeyObj();
	}

	/**
	* Returns the process ID of this scheduled task.
	*
	* @return the process ID of this scheduled task
	*/
	@Override
	public long getProcessId() {
		return _scheduledTask.getProcessId();
	}

	/**
	* Returns the run end date of this scheduled task.
	*
	* @return the run end date of this scheduled task
	*/
	@Override
	public Date getRunEndDate() {
		return _scheduledTask.getRunEndDate();
	}

	/**
	* Returns the run start date of this scheduled task.
	*
	* @return the run start date of this scheduled task
	*/
	@Override
	public Date getRunStartDate() {
		return _scheduledTask.getRunStartDate();
	}

	/**
	* Returns the scheduled task ID of this scheduled task.
	*
	* @return the scheduled task ID of this scheduled task
	*/
	@Override
	public long getScheduledTaskId() {
		return _scheduledTask.getScheduledTaskId();
	}

	/**
	* Returns the start date of this scheduled task.
	*
	* @return the start date of this scheduled task
	*/
	@Override
	public Date getStartDate() {
		return _scheduledTask.getStartDate();
	}

	/**
	* Returns the start hour of this scheduled task.
	*
	* @return the start hour of this scheduled task
	*/
	@Override
	public String getStartHour() {
		return _scheduledTask.getStartHour();
	}

	/**
	* Returns the status of this scheduled task.
	*
	* @return the status of this scheduled task
	*/
	@Override
	public int getStatus() {
		return _scheduledTask.getStatus();
	}

	/**
	* Returns the user ID of this scheduled task.
	*
	* @return the user ID of this scheduled task
	*/
	@Override
	public long getUserId() {
		return _scheduledTask.getUserId();
	}

	/**
	* Returns the user name of this scheduled task.
	*
	* @return the user name of this scheduled task
	*/
	@Override
	public String getUserName() {
		return _scheduledTask.getUserName();
	}

	/**
	* Returns the user uuid of this scheduled task.
	*
	* @return the user uuid of this scheduled task
	*/
	@Override
	public String getUserUuid() {
		return _scheduledTask.getUserUuid();
	}

	/**
	* Returns the uuid of this scheduled task.
	*
	* @return the uuid of this scheduled task
	*/
	@Override
	public String getUuid() {
		return _scheduledTask.getUuid();
	}

	@Override
	public int hashCode() {
		return _scheduledTask.hashCode();
	}

	/**
	* Returns <code>true</code> if this scheduled task is active.
	*
	* @return <code>true</code> if this scheduled task is active; <code>false</code> otherwise
	*/
	@Override
	public boolean isActive() {
		return _scheduledTask.isActive();
	}

	@Override
	public boolean isCachedModel() {
		return _scheduledTask.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this scheduled task is enabled.
	*
	* @return <code>true</code> if this scheduled task is enabled; <code>false</code> otherwise
	*/
	@Override
	public boolean isEnabled() {
		return _scheduledTask.isEnabled();
	}

	@Override
	public boolean isEscapedModel() {
		return _scheduledTask.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _scheduledTask.isNew();
	}

	@Override
	public void persist() {
		_scheduledTask.persist();
	}

	/**
	* Sets whether this scheduled task is active.
	*
	* @param active the active of this scheduled task
	*/
	@Override
	public void setActive(boolean active) {
		_scheduledTask.setActive(active);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_scheduledTask.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this scheduled task.
	*
	* @param companyId the company ID of this scheduled task
	*/
	@Override
	public void setCompanyId(long companyId) {
		_scheduledTask.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this scheduled task.
	*
	* @param createDate the create date of this scheduled task
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_scheduledTask.setCreateDate(createDate);
	}

	/**
	* Sets whether this scheduled task is enabled.
	*
	* @param enabled the enabled of this scheduled task
	*/
	@Override
	public void setEnabled(boolean enabled) {
		_scheduledTask.setEnabled(enabled);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_scheduledTask.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_scheduledTask.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_scheduledTask.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the frequency of this scheduled task.
	*
	* @param frequency the frequency of this scheduled task
	*/
	@Override
	public void setFrequency(String frequency) {
		_scheduledTask.setFrequency(frequency);
	}

	/**
	* Sets the group ID of this scheduled task.
	*
	* @param groupId the group ID of this scheduled task
	*/
	@Override
	public void setGroupId(long groupId) {
		_scheduledTask.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this scheduled task.
	*
	* @param modifiedDate the modified date of this scheduled task
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_scheduledTask.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this scheduled task.
	*
	* @param name the name of this scheduled task
	*/
	@Override
	public void setName(String name) {
		_scheduledTask.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_scheduledTask.setNew(n);
	}

	/**
	* Sets the primary key of this scheduled task.
	*
	* @param primaryKey the primary key of this scheduled task
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_scheduledTask.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_scheduledTask.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the process ID of this scheduled task.
	*
	* @param processId the process ID of this scheduled task
	*/
	@Override
	public void setProcessId(long processId) {
		_scheduledTask.setProcessId(processId);
	}

	/**
	* Sets the run end date of this scheduled task.
	*
	* @param runEndDate the run end date of this scheduled task
	*/
	@Override
	public void setRunEndDate(Date runEndDate) {
		_scheduledTask.setRunEndDate(runEndDate);
	}

	/**
	* Sets the run start date of this scheduled task.
	*
	* @param runStartDate the run start date of this scheduled task
	*/
	@Override
	public void setRunStartDate(Date runStartDate) {
		_scheduledTask.setRunStartDate(runStartDate);
	}

	/**
	* Sets the scheduled task ID of this scheduled task.
	*
	* @param scheduledTaskId the scheduled task ID of this scheduled task
	*/
	@Override
	public void setScheduledTaskId(long scheduledTaskId) {
		_scheduledTask.setScheduledTaskId(scheduledTaskId);
	}

	/**
	* Sets the start date of this scheduled task.
	*
	* @param startDate the start date of this scheduled task
	*/
	@Override
	public void setStartDate(Date startDate) {
		_scheduledTask.setStartDate(startDate);
	}

	/**
	* Sets the start hour of this scheduled task.
	*
	* @param startHour the start hour of this scheduled task
	*/
	@Override
	public void setStartHour(String startHour) {
		_scheduledTask.setStartHour(startHour);
	}

	/**
	* Sets the status of this scheduled task.
	*
	* @param status the status of this scheduled task
	*/
	@Override
	public void setStatus(int status) {
		_scheduledTask.setStatus(status);
	}

	/**
	* Sets the user ID of this scheduled task.
	*
	* @param userId the user ID of this scheduled task
	*/
	@Override
	public void setUserId(long userId) {
		_scheduledTask.setUserId(userId);
	}

	/**
	* Sets the user name of this scheduled task.
	*
	* @param userName the user name of this scheduled task
	*/
	@Override
	public void setUserName(String userName) {
		_scheduledTask.setUserName(userName);
	}

	/**
	* Sets the user uuid of this scheduled task.
	*
	* @param userUuid the user uuid of this scheduled task
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_scheduledTask.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this scheduled task.
	*
	* @param uuid the uuid of this scheduled task
	*/
	@Override
	public void setUuid(String uuid) {
		_scheduledTask.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ScheduledTask> toCacheModel() {
		return _scheduledTask.toCacheModel();
	}

	@Override
	public ScheduledTask toEscapedModel() {
		return new ScheduledTaskWrapper(_scheduledTask.toEscapedModel());
	}

	@Override
	public String toString() {
		return _scheduledTask.toString();
	}

	@Override
	public ScheduledTask toUnescapedModel() {
		return new ScheduledTaskWrapper(_scheduledTask.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _scheduledTask.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ScheduledTaskWrapper)) {
			return false;
		}

		ScheduledTaskWrapper scheduledTaskWrapper = (ScheduledTaskWrapper)obj;

		if (Objects.equals(_scheduledTask, scheduledTaskWrapper._scheduledTask)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _scheduledTask.getStagedModelType();
	}

	@Override
	public ScheduledTask getWrappedModel() {
		return _scheduledTask;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _scheduledTask.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _scheduledTask.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_scheduledTask.resetOriginalValues();
	}

	private final ScheduledTask _scheduledTask;
}