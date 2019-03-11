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
 * This class is a wrapper for {@link CommerceBatchJob}.
 * </p>
 *
 * @author Matija Petanjek
 * @see CommerceBatchJob
 * @generated
 */
@ProviderType
public class CommerceBatchJobWrapper implements CommerceBatchJob,
	ModelWrapper<CommerceBatchJob> {
	public CommerceBatchJobWrapper(CommerceBatchJob commerceBatchJob) {
		_commerceBatchJob = commerceBatchJob;
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceBatchJob.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceBatchJob.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("commerceBatchJobId", getCommerceBatchJobId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("key", getKey());
		attributes.put("name", getName());
		attributes.put("startTime", getStartTime());
		attributes.put("endTime", getEndTime());
		attributes.put("status", getStatus());
		attributes.put("callbackURL", getCallbackURL());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long commerceBatchJobId = (Long)attributes.get("commerceBatchJobId");

		if (commerceBatchJobId != null) {
			setCommerceBatchJobId(commerceBatchJobId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String key = (String)attributes.get("key");

		if (key != null) {
			setKey(key);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
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

		String callbackURL = (String)attributes.get("callbackURL");

		if (callbackURL != null) {
			setCallbackURL(callbackURL);
		}
	}

	@Override
	public Object clone() {
		return new CommerceBatchJobWrapper((CommerceBatchJob)_commerceBatchJob.clone());
	}

	@Override
	public int compareTo(CommerceBatchJob commerceBatchJob) {
		return _commerceBatchJob.compareTo(commerceBatchJob);
	}

	/**
	* Returns the callback url of this commerce batch job.
	*
	* @return the callback url of this commerce batch job
	*/
	@Override
	public String getCallbackURL() {
		return _commerceBatchJob.getCallbackURL();
	}

	/**
	* Returns the commerce batch job ID of this commerce batch job.
	*
	* @return the commerce batch job ID of this commerce batch job
	*/
	@Override
	public long getCommerceBatchJobId() {
		return _commerceBatchJob.getCommerceBatchJobId();
	}

	/**
	* Returns the create date of this commerce batch job.
	*
	* @return the create date of this commerce batch job
	*/
	@Override
	public Date getCreateDate() {
		return _commerceBatchJob.getCreateDate();
	}

	/**
	* Returns the end time of this commerce batch job.
	*
	* @return the end time of this commerce batch job
	*/
	@Override
	public Date getEndTime() {
		return _commerceBatchJob.getEndTime();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _commerceBatchJob.getExpandoBridge();
	}

	/**
	* Returns the key of this commerce batch job.
	*
	* @return the key of this commerce batch job
	*/
	@Override
	public String getKey() {
		return _commerceBatchJob.getKey();
	}

	/**
	* Returns the modified date of this commerce batch job.
	*
	* @return the modified date of this commerce batch job
	*/
	@Override
	public Date getModifiedDate() {
		return _commerceBatchJob.getModifiedDate();
	}

	/**
	* Returns the name of this commerce batch job.
	*
	* @return the name of this commerce batch job
	*/
	@Override
	public String getName() {
		return _commerceBatchJob.getName();
	}

	/**
	* Returns the primary key of this commerce batch job.
	*
	* @return the primary key of this commerce batch job
	*/
	@Override
	public long getPrimaryKey() {
		return _commerceBatchJob.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceBatchJob.getPrimaryKeyObj();
	}

	/**
	* Returns the start time of this commerce batch job.
	*
	* @return the start time of this commerce batch job
	*/
	@Override
	public Date getStartTime() {
		return _commerceBatchJob.getStartTime();
	}

	/**
	* Returns the status of this commerce batch job.
	*
	* @return the status of this commerce batch job
	*/
	@Override
	public String getStatus() {
		return _commerceBatchJob.getStatus();
	}

	@Override
	public int hashCode() {
		return _commerceBatchJob.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _commerceBatchJob.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _commerceBatchJob.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _commerceBatchJob.isNew();
	}

	@Override
	public void persist() {
		_commerceBatchJob.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_commerceBatchJob.setCachedModel(cachedModel);
	}

	/**
	* Sets the callback url of this commerce batch job.
	*
	* @param callbackURL the callback url of this commerce batch job
	*/
	@Override
	public void setCallbackURL(String callbackURL) {
		_commerceBatchJob.setCallbackURL(callbackURL);
	}

	/**
	* Sets the commerce batch job ID of this commerce batch job.
	*
	* @param commerceBatchJobId the commerce batch job ID of this commerce batch job
	*/
	@Override
	public void setCommerceBatchJobId(long commerceBatchJobId) {
		_commerceBatchJob.setCommerceBatchJobId(commerceBatchJobId);
	}

	/**
	* Sets the create date of this commerce batch job.
	*
	* @param createDate the create date of this commerce batch job
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_commerceBatchJob.setCreateDate(createDate);
	}

	/**
	* Sets the end time of this commerce batch job.
	*
	* @param endTime the end time of this commerce batch job
	*/
	@Override
	public void setEndTime(Date endTime) {
		_commerceBatchJob.setEndTime(endTime);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_commerceBatchJob.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_commerceBatchJob.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_commerceBatchJob.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the key of this commerce batch job.
	*
	* @param key the key of this commerce batch job
	*/
	@Override
	public void setKey(String key) {
		_commerceBatchJob.setKey(key);
	}

	/**
	* Sets the modified date of this commerce batch job.
	*
	* @param modifiedDate the modified date of this commerce batch job
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_commerceBatchJob.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this commerce batch job.
	*
	* @param name the name of this commerce batch job
	*/
	@Override
	public void setName(String name) {
		_commerceBatchJob.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_commerceBatchJob.setNew(n);
	}

	/**
	* Sets the primary key of this commerce batch job.
	*
	* @param primaryKey the primary key of this commerce batch job
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_commerceBatchJob.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_commerceBatchJob.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the start time of this commerce batch job.
	*
	* @param startTime the start time of this commerce batch job
	*/
	@Override
	public void setStartTime(Date startTime) {
		_commerceBatchJob.setStartTime(startTime);
	}

	/**
	* Sets the status of this commerce batch job.
	*
	* @param status the status of this commerce batch job
	*/
	@Override
	public void setStatus(String status) {
		_commerceBatchJob.setStatus(status);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CommerceBatchJob> toCacheModel() {
		return _commerceBatchJob.toCacheModel();
	}

	@Override
	public CommerceBatchJob toEscapedModel() {
		return new CommerceBatchJobWrapper(_commerceBatchJob.toEscapedModel());
	}

	@Override
	public String toString() {
		return _commerceBatchJob.toString();
	}

	@Override
	public CommerceBatchJob toUnescapedModel() {
		return new CommerceBatchJobWrapper(_commerceBatchJob.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _commerceBatchJob.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceBatchJobWrapper)) {
			return false;
		}

		CommerceBatchJobWrapper commerceBatchJobWrapper = (CommerceBatchJobWrapper)obj;

		if (Objects.equals(_commerceBatchJob,
					commerceBatchJobWrapper._commerceBatchJob)) {
			return true;
		}

		return false;
	}

	@Override
	public CommerceBatchJob getWrappedModel() {
		return _commerceBatchJob;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _commerceBatchJob.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _commerceBatchJob.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_commerceBatchJob.resetOriginalValues();
	}

	private final CommerceBatchJob _commerceBatchJob;
}