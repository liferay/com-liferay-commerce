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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link CommerceBatchJobExecution}.
 * </p>
 *
 * @author Matija Petanjek
 * @see CommerceBatchJobExecution
 * @generated
 */
@ProviderType
public class CommerceBatchJobExecutionWrapper
	implements CommerceBatchJobExecution,
		ModelWrapper<CommerceBatchJobExecution> {
	public CommerceBatchJobExecutionWrapper(
		CommerceBatchJobExecution commerceBatchJobExecution) {
		_commerceBatchJobExecution = commerceBatchJobExecution;
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceBatchJobExecution.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceBatchJobExecution.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("commerceBatchJobExecutionId",
			getCommerceBatchJobExecutionId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("commerceBatchJobInstanceId",
			getCommerceBatchJobInstanceId());
		attributes.put("startTime", getStartTime());
		attributes.put("endTime", getEndTime());
		attributes.put("status", getStatus());
		attributes.put("exitCode", getExitCode());
		attributes.put("exitMessage", getExitMessage());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long commerceBatchJobExecutionId = (Long)attributes.get(
				"commerceBatchJobExecutionId");

		if (commerceBatchJobExecutionId != null) {
			setCommerceBatchJobExecutionId(commerceBatchJobExecutionId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long commerceBatchJobInstanceId = (Long)attributes.get(
				"commerceBatchJobInstanceId");

		if (commerceBatchJobInstanceId != null) {
			setCommerceBatchJobInstanceId(commerceBatchJobInstanceId);
		}

		Date startTime = (Date)attributes.get("startTime");

		if (startTime != null) {
			setStartTime(startTime);
		}

		Date endTime = (Date)attributes.get("endTime");

		if (endTime != null) {
			setEndTime(endTime);
		}

		String status = (String)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		String exitCode = (String)attributes.get("exitCode");

		if (exitCode != null) {
			setExitCode(exitCode);
		}

		String exitMessage = (String)attributes.get("exitMessage");

		if (exitMessage != null) {
			setExitMessage(exitMessage);
		}
	}

	@Override
	public Object clone() {
		return new CommerceBatchJobExecutionWrapper((CommerceBatchJobExecution)_commerceBatchJobExecution.clone());
	}

	@Override
	public int compareTo(CommerceBatchJobExecution commerceBatchJobExecution) {
		return _commerceBatchJobExecution.compareTo(commerceBatchJobExecution);
	}

	/**
	* Returns the commerce batch job execution ID of this commerce batch job execution.
	*
	* @return the commerce batch job execution ID of this commerce batch job execution
	*/
	@Override
	public long getCommerceBatchJobExecutionId() {
		return _commerceBatchJobExecution.getCommerceBatchJobExecutionId();
	}

	/**
	* Returns the commerce batch job instance ID of this commerce batch job execution.
	*
	* @return the commerce batch job instance ID of this commerce batch job execution
	*/
	@Override
	public long getCommerceBatchJobInstanceId() {
		return _commerceBatchJobExecution.getCommerceBatchJobInstanceId();
	}

	/**
	* Returns the create date of this commerce batch job execution.
	*
	* @return the create date of this commerce batch job execution
	*/
	@Override
	public Date getCreateDate() {
		return _commerceBatchJobExecution.getCreateDate();
	}

	/**
	* Returns the end time of this commerce batch job execution.
	*
	* @return the end time of this commerce batch job execution
	*/
	@Override
	public Date getEndTime() {
		return _commerceBatchJobExecution.getEndTime();
	}

	/**
	* Returns the exit code of this commerce batch job execution.
	*
	* @return the exit code of this commerce batch job execution
	*/
	@Override
	public String getExitCode() {
		return _commerceBatchJobExecution.getExitCode();
	}

	/**
	* Returns the exit message of this commerce batch job execution.
	*
	* @return the exit message of this commerce batch job execution
	*/
	@Override
	public String getExitMessage() {
		return _commerceBatchJobExecution.getExitMessage();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _commerceBatchJobExecution.getExpandoBridge();
	}

	/**
	* Returns the modified date of this commerce batch job execution.
	*
	* @return the modified date of this commerce batch job execution
	*/
	@Override
	public Date getModifiedDate() {
		return _commerceBatchJobExecution.getModifiedDate();
	}

	/**
	* Returns the primary key of this commerce batch job execution.
	*
	* @return the primary key of this commerce batch job execution
	*/
	@Override
	public long getPrimaryKey() {
		return _commerceBatchJobExecution.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceBatchJobExecution.getPrimaryKeyObj();
	}

	/**
	* Returns the start time of this commerce batch job execution.
	*
	* @return the start time of this commerce batch job execution
	*/
	@Override
	public Date getStartTime() {
		return _commerceBatchJobExecution.getStartTime();
	}

	/**
	* Returns the status of this commerce batch job execution.
	*
	* @return the status of this commerce batch job execution
	*/
	@Override
	public String getStatus() {
		return _commerceBatchJobExecution.getStatus();
	}

	@Override
	public int hashCode() {
		return _commerceBatchJobExecution.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _commerceBatchJobExecution.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _commerceBatchJobExecution.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _commerceBatchJobExecution.isNew();
	}

	@Override
	public void persist() {
		_commerceBatchJobExecution.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_commerceBatchJobExecution.setCachedModel(cachedModel);
	}

	/**
	* Sets the commerce batch job execution ID of this commerce batch job execution.
	*
	* @param commerceBatchJobExecutionId the commerce batch job execution ID of this commerce batch job execution
	*/
	@Override
	public void setCommerceBatchJobExecutionId(long commerceBatchJobExecutionId) {
		_commerceBatchJobExecution.setCommerceBatchJobExecutionId(commerceBatchJobExecutionId);
	}

	/**
	* Sets the commerce batch job instance ID of this commerce batch job execution.
	*
	* @param commerceBatchJobInstanceId the commerce batch job instance ID of this commerce batch job execution
	*/
	@Override
	public void setCommerceBatchJobInstanceId(long commerceBatchJobInstanceId) {
		_commerceBatchJobExecution.setCommerceBatchJobInstanceId(commerceBatchJobInstanceId);
	}

	/**
	* Sets the create date of this commerce batch job execution.
	*
	* @param createDate the create date of this commerce batch job execution
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_commerceBatchJobExecution.setCreateDate(createDate);
	}

	/**
	* Sets the end time of this commerce batch job execution.
	*
	* @param endTime the end time of this commerce batch job execution
	*/
	@Override
	public void setEndTime(Date endTime) {
		_commerceBatchJobExecution.setEndTime(endTime);
	}

	/**
	* Sets the exit code of this commerce batch job execution.
	*
	* @param exitCode the exit code of this commerce batch job execution
	*/
	@Override
	public void setExitCode(String exitCode) {
		_commerceBatchJobExecution.setExitCode(exitCode);
	}

	/**
	* Sets the exit message of this commerce batch job execution.
	*
	* @param exitMessage the exit message of this commerce batch job execution
	*/
	@Override
	public void setExitMessage(String exitMessage) {
		_commerceBatchJobExecution.setExitMessage(exitMessage);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_commerceBatchJobExecution.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_commerceBatchJobExecution.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_commerceBatchJobExecution.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the modified date of this commerce batch job execution.
	*
	* @param modifiedDate the modified date of this commerce batch job execution
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_commerceBatchJobExecution.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_commerceBatchJobExecution.setNew(n);
	}

	/**
	* Sets the primary key of this commerce batch job execution.
	*
	* @param primaryKey the primary key of this commerce batch job execution
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_commerceBatchJobExecution.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_commerceBatchJobExecution.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the start time of this commerce batch job execution.
	*
	* @param startTime the start time of this commerce batch job execution
	*/
	@Override
	public void setStartTime(Date startTime) {
		_commerceBatchJobExecution.setStartTime(startTime);
	}

	/**
	* Sets the status of this commerce batch job execution.
	*
	* @param status the status of this commerce batch job execution
	*/
	@Override
	public void setStatus(String status) {
		_commerceBatchJobExecution.setStatus(status);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CommerceBatchJobExecution> toCacheModel() {
		return _commerceBatchJobExecution.toCacheModel();
	}

	@Override
	public CommerceBatchJobExecution toEscapedModel() {
		return new CommerceBatchJobExecutionWrapper(_commerceBatchJobExecution.toEscapedModel());
	}

	@Override
	public String toString() {
		return _commerceBatchJobExecution.toString();
	}

	@Override
	public CommerceBatchJobExecution toUnescapedModel() {
		return new CommerceBatchJobExecutionWrapper(_commerceBatchJobExecution.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _commerceBatchJobExecution.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceBatchJobExecutionWrapper)) {
			return false;
		}

		CommerceBatchJobExecutionWrapper commerceBatchJobExecutionWrapper = (CommerceBatchJobExecutionWrapper)obj;

		if (Objects.equals(_commerceBatchJobExecution,
					commerceBatchJobExecutionWrapper._commerceBatchJobExecution)) {
			return true;
		}

		return false;
	}

	@Override
	public CommerceBatchJobExecution getWrappedModel() {
		return _commerceBatchJobExecution;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _commerceBatchJobExecution.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _commerceBatchJobExecution.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_commerceBatchJobExecution.resetOriginalValues();
	}

	private final CommerceBatchJobExecution _commerceBatchJobExecution;
}