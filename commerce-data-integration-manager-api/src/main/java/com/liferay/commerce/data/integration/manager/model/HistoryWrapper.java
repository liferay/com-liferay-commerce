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
 * This class is a wrapper for {@link History}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see History
 * @generated
 */
@ProviderType
public class HistoryWrapper implements History, ModelWrapper<History> {
	public HistoryWrapper(History history) {
		_history = history;
	}

	@Override
	public Class<?> getModelClass() {
		return History.class;
	}

	@Override
	public String getModelClassName() {
		return History.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("historyId", getHistoryId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("launchType", getLaunchType());
		attributes.put("scheduledTaskId", getScheduledTaskId());
		attributes.put("status", getStatus());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());
		attributes.put("executionType", getExecutionType());
		attributes.put("errorLogFileEntryId", getErrorLogFileEntryId());
		attributes.put("runtimeLogFileEntryId", getRuntimeLogFileEntryId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long historyId = (Long)attributes.get("historyId");

		if (historyId != null) {
			setHistoryId(historyId);
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

		String launchType = (String)attributes.get("launchType");

		if (launchType != null) {
			setLaunchType(launchType);
		}

		Long scheduledTaskId = (Long)attributes.get("scheduledTaskId");

		if (scheduledTaskId != null) {
			setScheduledTaskId(scheduledTaskId);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		String executionType = (String)attributes.get("executionType");

		if (executionType != null) {
			setExecutionType(executionType);
		}

		Long errorLogFileEntryId = (Long)attributes.get("errorLogFileEntryId");

		if (errorLogFileEntryId != null) {
			setErrorLogFileEntryId(errorLogFileEntryId);
		}

		Long runtimeLogFileEntryId = (Long)attributes.get(
				"runtimeLogFileEntryId");

		if (runtimeLogFileEntryId != null) {
			setRuntimeLogFileEntryId(runtimeLogFileEntryId);
		}
	}

	@Override
	public Object clone() {
		return new HistoryWrapper((History)_history.clone());
	}

	@Override
	public int compareTo(History history) {
		return _history.compareTo(history);
	}

	/**
	* Returns the company ID of this history.
	*
	* @return the company ID of this history
	*/
	@Override
	public long getCompanyId() {
		return _history.getCompanyId();
	}

	/**
	* Returns the create date of this history.
	*
	* @return the create date of this history
	*/
	@Override
	public Date getCreateDate() {
		return _history.getCreateDate();
	}

	/**
	* Returns the end date of this history.
	*
	* @return the end date of this history
	*/
	@Override
	public Date getEndDate() {
		return _history.getEndDate();
	}

	/**
	* Returns the error log file entry ID of this history.
	*
	* @return the error log file entry ID of this history
	*/
	@Override
	public long getErrorLogFileEntryId() {
		return _history.getErrorLogFileEntryId();
	}

	/**
	* Returns the execution type of this history.
	*
	* @return the execution type of this history
	*/
	@Override
	public String getExecutionType() {
		return _history.getExecutionType();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _history.getExpandoBridge();
	}

	/**
	* Returns the group ID of this history.
	*
	* @return the group ID of this history
	*/
	@Override
	public long getGroupId() {
		return _history.getGroupId();
	}

	/**
	* Returns the history ID of this history.
	*
	* @return the history ID of this history
	*/
	@Override
	public long getHistoryId() {
		return _history.getHistoryId();
	}

	/**
	* Returns the launch type of this history.
	*
	* @return the launch type of this history
	*/
	@Override
	public String getLaunchType() {
		return _history.getLaunchType();
	}

	/**
	* Returns the modified date of this history.
	*
	* @return the modified date of this history
	*/
	@Override
	public Date getModifiedDate() {
		return _history.getModifiedDate();
	}

	/**
	* Returns the primary key of this history.
	*
	* @return the primary key of this history
	*/
	@Override
	public long getPrimaryKey() {
		return _history.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _history.getPrimaryKeyObj();
	}

	/**
	* Returns the runtime log file entry ID of this history.
	*
	* @return the runtime log file entry ID of this history
	*/
	@Override
	public long getRuntimeLogFileEntryId() {
		return _history.getRuntimeLogFileEntryId();
	}

	@Override
	public ScheduledTask getScheduledTask() {
		return _history.getScheduledTask();
	}

	/**
	* Returns the scheduled task ID of this history.
	*
	* @return the scheduled task ID of this history
	*/
	@Override
	public long getScheduledTaskId() {
		return _history.getScheduledTaskId();
	}

	@Override
	public String getScheduledTaskName() {
		return _history.getScheduledTaskName();
	}

	/**
	* Returns the start date of this history.
	*
	* @return the start date of this history
	*/
	@Override
	public Date getStartDate() {
		return _history.getStartDate();
	}

	/**
	* Returns the status of this history.
	*
	* @return the status of this history
	*/
	@Override
	public int getStatus() {
		return _history.getStatus();
	}

	/**
	* Returns the user ID of this history.
	*
	* @return the user ID of this history
	*/
	@Override
	public long getUserId() {
		return _history.getUserId();
	}

	/**
	* Returns the user name of this history.
	*
	* @return the user name of this history
	*/
	@Override
	public String getUserName() {
		return _history.getUserName();
	}

	/**
	* Returns the user uuid of this history.
	*
	* @return the user uuid of this history
	*/
	@Override
	public String getUserUuid() {
		return _history.getUserUuid();
	}

	/**
	* Returns the uuid of this history.
	*
	* @return the uuid of this history
	*/
	@Override
	public String getUuid() {
		return _history.getUuid();
	}

	@Override
	public int hashCode() {
		return _history.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _history.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _history.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _history.isNew();
	}

	@Override
	public void persist() {
		_history.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_history.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this history.
	*
	* @param companyId the company ID of this history
	*/
	@Override
	public void setCompanyId(long companyId) {
		_history.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this history.
	*
	* @param createDate the create date of this history
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_history.setCreateDate(createDate);
	}

	/**
	* Sets the end date of this history.
	*
	* @param endDate the end date of this history
	*/
	@Override
	public void setEndDate(Date endDate) {
		_history.setEndDate(endDate);
	}

	/**
	* Sets the error log file entry ID of this history.
	*
	* @param errorLogFileEntryId the error log file entry ID of this history
	*/
	@Override
	public void setErrorLogFileEntryId(long errorLogFileEntryId) {
		_history.setErrorLogFileEntryId(errorLogFileEntryId);
	}

	/**
	* Sets the execution type of this history.
	*
	* @param executionType the execution type of this history
	*/
	@Override
	public void setExecutionType(String executionType) {
		_history.setExecutionType(executionType);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_history.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_history.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_history.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this history.
	*
	* @param groupId the group ID of this history
	*/
	@Override
	public void setGroupId(long groupId) {
		_history.setGroupId(groupId);
	}

	/**
	* Sets the history ID of this history.
	*
	* @param historyId the history ID of this history
	*/
	@Override
	public void setHistoryId(long historyId) {
		_history.setHistoryId(historyId);
	}

	/**
	* Sets the launch type of this history.
	*
	* @param launchType the launch type of this history
	*/
	@Override
	public void setLaunchType(String launchType) {
		_history.setLaunchType(launchType);
	}

	/**
	* Sets the modified date of this history.
	*
	* @param modifiedDate the modified date of this history
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_history.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_history.setNew(n);
	}

	/**
	* Sets the primary key of this history.
	*
	* @param primaryKey the primary key of this history
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_history.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_history.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the runtime log file entry ID of this history.
	*
	* @param runtimeLogFileEntryId the runtime log file entry ID of this history
	*/
	@Override
	public void setRuntimeLogFileEntryId(long runtimeLogFileEntryId) {
		_history.setRuntimeLogFileEntryId(runtimeLogFileEntryId);
	}

	/**
	* Sets the scheduled task ID of this history.
	*
	* @param scheduledTaskId the scheduled task ID of this history
	*/
	@Override
	public void setScheduledTaskId(long scheduledTaskId) {
		_history.setScheduledTaskId(scheduledTaskId);
	}

	/**
	* Sets the start date of this history.
	*
	* @param startDate the start date of this history
	*/
	@Override
	public void setStartDate(Date startDate) {
		_history.setStartDate(startDate);
	}

	/**
	* Sets the status of this history.
	*
	* @param status the status of this history
	*/
	@Override
	public void setStatus(int status) {
		_history.setStatus(status);
	}

	/**
	* Sets the user ID of this history.
	*
	* @param userId the user ID of this history
	*/
	@Override
	public void setUserId(long userId) {
		_history.setUserId(userId);
	}

	/**
	* Sets the user name of this history.
	*
	* @param userName the user name of this history
	*/
	@Override
	public void setUserName(String userName) {
		_history.setUserName(userName);
	}

	/**
	* Sets the user uuid of this history.
	*
	* @param userUuid the user uuid of this history
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_history.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this history.
	*
	* @param uuid the uuid of this history
	*/
	@Override
	public void setUuid(String uuid) {
		_history.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<History> toCacheModel() {
		return _history.toCacheModel();
	}

	@Override
	public History toEscapedModel() {
		return new HistoryWrapper(_history.toEscapedModel());
	}

	@Override
	public String toString() {
		return _history.toString();
	}

	@Override
	public History toUnescapedModel() {
		return new HistoryWrapper(_history.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _history.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof HistoryWrapper)) {
			return false;
		}

		HistoryWrapper historyWrapper = (HistoryWrapper)obj;

		if (Objects.equals(_history, historyWrapper._history)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _history.getStagedModelType();
	}

	@Override
	public History getWrappedModel() {
		return _history;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _history.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _history.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_history.resetOriginalValues();
	}

	private final History _history;
}