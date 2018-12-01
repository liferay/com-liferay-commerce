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
 * This class is a wrapper for {@link ProcessType}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProcessType
 * @generated
 */
@ProviderType
public class ProcessTypeWrapper implements ProcessType,
	ModelWrapper<ProcessType> {
	public ProcessTypeWrapper(ProcessType processType) {
		_processType = processType;
	}

	@Override
	public Class<?> getModelClass() {
		return ProcessType.class;
	}

	@Override
	public String getModelClassName() {
		return ProcessType.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("processTypeId", getProcessTypeId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long processTypeId = (Long)attributes.get("processTypeId");

		if (processTypeId != null) {
			setProcessTypeId(processTypeId);
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
	}

	@Override
	public Object clone() {
		return new ProcessTypeWrapper((ProcessType)_processType.clone());
	}

	@Override
	public int compareTo(ProcessType processType) {
		return _processType.compareTo(processType);
	}

	/**
	* Returns the company ID of this process type.
	*
	* @return the company ID of this process type
	*/
	@Override
	public long getCompanyId() {
		return _processType.getCompanyId();
	}

	/**
	* Returns the create date of this process type.
	*
	* @return the create date of this process type
	*/
	@Override
	public Date getCreateDate() {
		return _processType.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _processType.getExpandoBridge();
	}

	/**
	* Returns the group ID of this process type.
	*
	* @return the group ID of this process type
	*/
	@Override
	public long getGroupId() {
		return _processType.getGroupId();
	}

	/**
	* Returns the modified date of this process type.
	*
	* @return the modified date of this process type
	*/
	@Override
	public Date getModifiedDate() {
		return _processType.getModifiedDate();
	}

	/**
	* Returns the name of this process type.
	*
	* @return the name of this process type
	*/
	@Override
	public String getName() {
		return _processType.getName();
	}

	/**
	* Returns the primary key of this process type.
	*
	* @return the primary key of this process type
	*/
	@Override
	public long getPrimaryKey() {
		return _processType.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _processType.getPrimaryKeyObj();
	}

	/**
	* Returns the process type ID of this process type.
	*
	* @return the process type ID of this process type
	*/
	@Override
	public long getProcessTypeId() {
		return _processType.getProcessTypeId();
	}

	/**
	* Returns the user ID of this process type.
	*
	* @return the user ID of this process type
	*/
	@Override
	public long getUserId() {
		return _processType.getUserId();
	}

	/**
	* Returns the user name of this process type.
	*
	* @return the user name of this process type
	*/
	@Override
	public String getUserName() {
		return _processType.getUserName();
	}

	/**
	* Returns the user uuid of this process type.
	*
	* @return the user uuid of this process type
	*/
	@Override
	public String getUserUuid() {
		return _processType.getUserUuid();
	}

	/**
	* Returns the uuid of this process type.
	*
	* @return the uuid of this process type
	*/
	@Override
	public String getUuid() {
		return _processType.getUuid();
	}

	@Override
	public int hashCode() {
		return _processType.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _processType.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _processType.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _processType.isNew();
	}

	@Override
	public void persist() {
		_processType.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_processType.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this process type.
	*
	* @param companyId the company ID of this process type
	*/
	@Override
	public void setCompanyId(long companyId) {
		_processType.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this process type.
	*
	* @param createDate the create date of this process type
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_processType.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_processType.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_processType.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_processType.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this process type.
	*
	* @param groupId the group ID of this process type
	*/
	@Override
	public void setGroupId(long groupId) {
		_processType.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this process type.
	*
	* @param modifiedDate the modified date of this process type
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_processType.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this process type.
	*
	* @param name the name of this process type
	*/
	@Override
	public void setName(String name) {
		_processType.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_processType.setNew(n);
	}

	/**
	* Sets the primary key of this process type.
	*
	* @param primaryKey the primary key of this process type
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_processType.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_processType.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the process type ID of this process type.
	*
	* @param processTypeId the process type ID of this process type
	*/
	@Override
	public void setProcessTypeId(long processTypeId) {
		_processType.setProcessTypeId(processTypeId);
	}

	/**
	* Sets the user ID of this process type.
	*
	* @param userId the user ID of this process type
	*/
	@Override
	public void setUserId(long userId) {
		_processType.setUserId(userId);
	}

	/**
	* Sets the user name of this process type.
	*
	* @param userName the user name of this process type
	*/
	@Override
	public void setUserName(String userName) {
		_processType.setUserName(userName);
	}

	/**
	* Sets the user uuid of this process type.
	*
	* @param userUuid the user uuid of this process type
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_processType.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this process type.
	*
	* @param uuid the uuid of this process type
	*/
	@Override
	public void setUuid(String uuid) {
		_processType.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ProcessType> toCacheModel() {
		return _processType.toCacheModel();
	}

	@Override
	public ProcessType toEscapedModel() {
		return new ProcessTypeWrapper(_processType.toEscapedModel());
	}

	@Override
	public String toString() {
		return _processType.toString();
	}

	@Override
	public ProcessType toUnescapedModel() {
		return new ProcessTypeWrapper(_processType.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _processType.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProcessTypeWrapper)) {
			return false;
		}

		ProcessTypeWrapper processTypeWrapper = (ProcessTypeWrapper)obj;

		if (Objects.equals(_processType, processTypeWrapper._processType)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _processType.getStagedModelType();
	}

	@Override
	public ProcessType getWrappedModel() {
		return _processType;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _processType.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _processType.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_processType.resetOriginalValues();
	}

	private final ProcessType _processType;
}