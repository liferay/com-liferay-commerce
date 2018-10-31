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
 * This class is a wrapper for {@link Process}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Process
 * @generated
 */
@ProviderType
public class ProcessWrapper implements Process, ModelWrapper<Process> {
	public ProcessWrapper(Process process) {
		_process = process;
	}

	@Override
	public Class<?> getModelClass() {
		return Process.class;
	}

	@Override
	public String getModelClassName() {
		return Process.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("processId", getProcessId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("version", getVersion());
		attributes.put("className", getClassName());
		attributes.put("processType", getProcessType());
		attributes.put("contextPropertiesFileEntryId",
			getContextPropertiesFileEntryId());
		attributes.put("srcArchiveFileEntryId", getSrcArchiveFileEntryId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long processId = (Long)attributes.get("processId");

		if (processId != null) {
			setProcessId(processId);
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

		String version = (String)attributes.get("version");

		if (version != null) {
			setVersion(version);
		}

		String className = (String)attributes.get("className");

		if (className != null) {
			setClassName(className);
		}

		String processType = (String)attributes.get("processType");

		if (processType != null) {
			setProcessType(processType);
		}

		Long contextPropertiesFileEntryId = (Long)attributes.get(
				"contextPropertiesFileEntryId");

		if (contextPropertiesFileEntryId != null) {
			setContextPropertiesFileEntryId(contextPropertiesFileEntryId);
		}

		Long srcArchiveFileEntryId = (Long)attributes.get(
				"srcArchiveFileEntryId");

		if (srcArchiveFileEntryId != null) {
			setSrcArchiveFileEntryId(srcArchiveFileEntryId);
		}
	}

	@Override
	public Object clone() {
		return new ProcessWrapper((Process)_process.clone());
	}

	@Override
	public int compareTo(Process process) {
		return _process.compareTo(process);
	}

	/**
	* Returns the class name of this process.
	*
	* @return the class name of this process
	*/
	@Override
	public String getClassName() {
		return _process.getClassName();
	}

	/**
	* Returns the company ID of this process.
	*
	* @return the company ID of this process
	*/
	@Override
	public long getCompanyId() {
		return _process.getCompanyId();
	}

	/**
	* Returns the context properties file entry ID of this process.
	*
	* @return the context properties file entry ID of this process
	*/
	@Override
	public long getContextPropertiesFileEntryId() {
		return _process.getContextPropertiesFileEntryId();
	}

	/**
	* Returns the create date of this process.
	*
	* @return the create date of this process
	*/
	@Override
	public Date getCreateDate() {
		return _process.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _process.getExpandoBridge();
	}

	/**
	* Returns the group ID of this process.
	*
	* @return the group ID of this process
	*/
	@Override
	public long getGroupId() {
		return _process.getGroupId();
	}

	/**
	* Returns the modified date of this process.
	*
	* @return the modified date of this process
	*/
	@Override
	public Date getModifiedDate() {
		return _process.getModifiedDate();
	}

	/**
	* Returns the name of this process.
	*
	* @return the name of this process
	*/
	@Override
	public String getName() {
		return _process.getName();
	}

	/**
	* Returns the primary key of this process.
	*
	* @return the primary key of this process
	*/
	@Override
	public long getPrimaryKey() {
		return _process.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _process.getPrimaryKeyObj();
	}

	/**
	* Returns the process ID of this process.
	*
	* @return the process ID of this process
	*/
	@Override
	public long getProcessId() {
		return _process.getProcessId();
	}

	/**
	* Returns the process type of this process.
	*
	* @return the process type of this process
	*/
	@Override
	public String getProcessType() {
		return _process.getProcessType();
	}

	/**
	* Returns the src archive file entry ID of this process.
	*
	* @return the src archive file entry ID of this process
	*/
	@Override
	public long getSrcArchiveFileEntryId() {
		return _process.getSrcArchiveFileEntryId();
	}

	/**
	* Returns the user ID of this process.
	*
	* @return the user ID of this process
	*/
	@Override
	public long getUserId() {
		return _process.getUserId();
	}

	/**
	* Returns the user name of this process.
	*
	* @return the user name of this process
	*/
	@Override
	public String getUserName() {
		return _process.getUserName();
	}

	/**
	* Returns the user uuid of this process.
	*
	* @return the user uuid of this process
	*/
	@Override
	public String getUserUuid() {
		return _process.getUserUuid();
	}

	/**
	* Returns the uuid of this process.
	*
	* @return the uuid of this process
	*/
	@Override
	public String getUuid() {
		return _process.getUuid();
	}

	/**
	* Returns the version of this process.
	*
	* @return the version of this process
	*/
	@Override
	public String getVersion() {
		return _process.getVersion();
	}

	@Override
	public int hashCode() {
		return _process.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _process.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _process.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _process.isNew();
	}

	@Override
	public void persist() {
		_process.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_process.setCachedModel(cachedModel);
	}

	/**
	* Sets the class name of this process.
	*
	* @param className the class name of this process
	*/
	@Override
	public void setClassName(String className) {
		_process.setClassName(className);
	}

	/**
	* Sets the company ID of this process.
	*
	* @param companyId the company ID of this process
	*/
	@Override
	public void setCompanyId(long companyId) {
		_process.setCompanyId(companyId);
	}

	/**
	* Sets the context properties file entry ID of this process.
	*
	* @param contextPropertiesFileEntryId the context properties file entry ID of this process
	*/
	@Override
	public void setContextPropertiesFileEntryId(
		long contextPropertiesFileEntryId) {
		_process.setContextPropertiesFileEntryId(contextPropertiesFileEntryId);
	}

	/**
	* Sets the create date of this process.
	*
	* @param createDate the create date of this process
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_process.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_process.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_process.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_process.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this process.
	*
	* @param groupId the group ID of this process
	*/
	@Override
	public void setGroupId(long groupId) {
		_process.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this process.
	*
	* @param modifiedDate the modified date of this process
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_process.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this process.
	*
	* @param name the name of this process
	*/
	@Override
	public void setName(String name) {
		_process.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_process.setNew(n);
	}

	/**
	* Sets the primary key of this process.
	*
	* @param primaryKey the primary key of this process
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_process.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_process.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the process ID of this process.
	*
	* @param processId the process ID of this process
	*/
	@Override
	public void setProcessId(long processId) {
		_process.setProcessId(processId);
	}

	/**
	* Sets the process type of this process.
	*
	* @param processType the process type of this process
	*/
	@Override
	public void setProcessType(String processType) {
		_process.setProcessType(processType);
	}

	/**
	* Sets the src archive file entry ID of this process.
	*
	* @param srcArchiveFileEntryId the src archive file entry ID of this process
	*/
	@Override
	public void setSrcArchiveFileEntryId(long srcArchiveFileEntryId) {
		_process.setSrcArchiveFileEntryId(srcArchiveFileEntryId);
	}

	/**
	* Sets the user ID of this process.
	*
	* @param userId the user ID of this process
	*/
	@Override
	public void setUserId(long userId) {
		_process.setUserId(userId);
	}

	/**
	* Sets the user name of this process.
	*
	* @param userName the user name of this process
	*/
	@Override
	public void setUserName(String userName) {
		_process.setUserName(userName);
	}

	/**
	* Sets the user uuid of this process.
	*
	* @param userUuid the user uuid of this process
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_process.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this process.
	*
	* @param uuid the uuid of this process
	*/
	@Override
	public void setUuid(String uuid) {
		_process.setUuid(uuid);
	}

	/**
	* Sets the version of this process.
	*
	* @param version the version of this process
	*/
	@Override
	public void setVersion(String version) {
		_process.setVersion(version);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Process> toCacheModel() {
		return _process.toCacheModel();
	}

	@Override
	public Process toEscapedModel() {
		return new ProcessWrapper(_process.toEscapedModel());
	}

	@Override
	public String toString() {
		return _process.toString();
	}

	@Override
	public Process toUnescapedModel() {
		return new ProcessWrapper(_process.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _process.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProcessWrapper)) {
			return false;
		}

		ProcessWrapper processWrapper = (ProcessWrapper)obj;

		if (Objects.equals(_process, processWrapper._process)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _process.getStagedModelType();
	}

	@Override
	public Process getWrappedModel() {
		return _process;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _process.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _process.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_process.resetOriginalValues();
	}

	private final Process _process;
}