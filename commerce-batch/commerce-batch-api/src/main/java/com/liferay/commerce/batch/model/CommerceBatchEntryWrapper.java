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
 * This class is a wrapper for {@link CommerceBatchEntry}.
 * </p>
 *
 * @author Matija Petanjek
 * @see CommerceBatchEntry
 * @generated
 */
@ProviderType
public class CommerceBatchEntryWrapper implements CommerceBatchEntry,
	ModelWrapper<CommerceBatchEntry> {
	public CommerceBatchEntryWrapper(CommerceBatchEntry commerceBatchEntry) {
		_commerceBatchEntry = commerceBatchEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceBatchEntry.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceBatchEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("commerceBatchEntryId", getCommerceBatchEntryId());
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
		Long commerceBatchEntryId = (Long)attributes.get("commerceBatchEntryId");

		if (commerceBatchEntryId != null) {
			setCommerceBatchEntryId(commerceBatchEntryId);
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
		return new CommerceBatchEntryWrapper((CommerceBatchEntry)_commerceBatchEntry.clone());
	}

	@Override
	public int compareTo(CommerceBatchEntry commerceBatchEntry) {
		return _commerceBatchEntry.compareTo(commerceBatchEntry);
	}

	/**
	* Returns the callback url of this commerce batch entry.
	*
	* @return the callback url of this commerce batch entry
	*/
	@Override
	public String getCallbackURL() {
		return _commerceBatchEntry.getCallbackURL();
	}

	/**
	* Returns the commerce batch entry ID of this commerce batch entry.
	*
	* @return the commerce batch entry ID of this commerce batch entry
	*/
	@Override
	public long getCommerceBatchEntryId() {
		return _commerceBatchEntry.getCommerceBatchEntryId();
	}

	/**
	* Returns the create date of this commerce batch entry.
	*
	* @return the create date of this commerce batch entry
	*/
	@Override
	public Date getCreateDate() {
		return _commerceBatchEntry.getCreateDate();
	}

	/**
	* Returns the end time of this commerce batch entry.
	*
	* @return the end time of this commerce batch entry
	*/
	@Override
	public Date getEndTime() {
		return _commerceBatchEntry.getEndTime();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _commerceBatchEntry.getExpandoBridge();
	}

	/**
	* Returns the key of this commerce batch entry.
	*
	* @return the key of this commerce batch entry
	*/
	@Override
	public String getKey() {
		return _commerceBatchEntry.getKey();
	}

	/**
	* Returns the modified date of this commerce batch entry.
	*
	* @return the modified date of this commerce batch entry
	*/
	@Override
	public Date getModifiedDate() {
		return _commerceBatchEntry.getModifiedDate();
	}

	/**
	* Returns the name of this commerce batch entry.
	*
	* @return the name of this commerce batch entry
	*/
	@Override
	public String getName() {
		return _commerceBatchEntry.getName();
	}

	/**
	* Returns the primary key of this commerce batch entry.
	*
	* @return the primary key of this commerce batch entry
	*/
	@Override
	public long getPrimaryKey() {
		return _commerceBatchEntry.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceBatchEntry.getPrimaryKeyObj();
	}

	/**
	* Returns the start time of this commerce batch entry.
	*
	* @return the start time of this commerce batch entry
	*/
	@Override
	public Date getStartTime() {
		return _commerceBatchEntry.getStartTime();
	}

	/**
	* Returns the status of this commerce batch entry.
	*
	* @return the status of this commerce batch entry
	*/
	@Override
	public String getStatus() {
		return _commerceBatchEntry.getStatus();
	}

	@Override
	public int hashCode() {
		return _commerceBatchEntry.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _commerceBatchEntry.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _commerceBatchEntry.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _commerceBatchEntry.isNew();
	}

	@Override
	public void persist() {
		_commerceBatchEntry.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_commerceBatchEntry.setCachedModel(cachedModel);
	}

	/**
	* Sets the callback url of this commerce batch entry.
	*
	* @param callbackURL the callback url of this commerce batch entry
	*/
	@Override
	public void setCallbackURL(String callbackURL) {
		_commerceBatchEntry.setCallbackURL(callbackURL);
	}

	/**
	* Sets the commerce batch entry ID of this commerce batch entry.
	*
	* @param commerceBatchEntryId the commerce batch entry ID of this commerce batch entry
	*/
	@Override
	public void setCommerceBatchEntryId(long commerceBatchEntryId) {
		_commerceBatchEntry.setCommerceBatchEntryId(commerceBatchEntryId);
	}

	/**
	* Sets the create date of this commerce batch entry.
	*
	* @param createDate the create date of this commerce batch entry
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_commerceBatchEntry.setCreateDate(createDate);
	}

	/**
	* Sets the end time of this commerce batch entry.
	*
	* @param endTime the end time of this commerce batch entry
	*/
	@Override
	public void setEndTime(Date endTime) {
		_commerceBatchEntry.setEndTime(endTime);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_commerceBatchEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_commerceBatchEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_commerceBatchEntry.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the key of this commerce batch entry.
	*
	* @param key the key of this commerce batch entry
	*/
	@Override
	public void setKey(String key) {
		_commerceBatchEntry.setKey(key);
	}

	/**
	* Sets the modified date of this commerce batch entry.
	*
	* @param modifiedDate the modified date of this commerce batch entry
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_commerceBatchEntry.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this commerce batch entry.
	*
	* @param name the name of this commerce batch entry
	*/
	@Override
	public void setName(String name) {
		_commerceBatchEntry.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_commerceBatchEntry.setNew(n);
	}

	/**
	* Sets the primary key of this commerce batch entry.
	*
	* @param primaryKey the primary key of this commerce batch entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_commerceBatchEntry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_commerceBatchEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the start time of this commerce batch entry.
	*
	* @param startTime the start time of this commerce batch entry
	*/
	@Override
	public void setStartTime(Date startTime) {
		_commerceBatchEntry.setStartTime(startTime);
	}

	/**
	* Sets the status of this commerce batch entry.
	*
	* @param status the status of this commerce batch entry
	*/
	@Override
	public void setStatus(String status) {
		_commerceBatchEntry.setStatus(status);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CommerceBatchEntry> toCacheModel() {
		return _commerceBatchEntry.toCacheModel();
	}

	@Override
	public CommerceBatchEntry toEscapedModel() {
		return new CommerceBatchEntryWrapper(_commerceBatchEntry.toEscapedModel());
	}

	@Override
	public String toString() {
		return _commerceBatchEntry.toString();
	}

	@Override
	public CommerceBatchEntry toUnescapedModel() {
		return new CommerceBatchEntryWrapper(_commerceBatchEntry.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _commerceBatchEntry.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceBatchEntryWrapper)) {
			return false;
		}

		CommerceBatchEntryWrapper commerceBatchEntryWrapper = (CommerceBatchEntryWrapper)obj;

		if (Objects.equals(_commerceBatchEntry,
					commerceBatchEntryWrapper._commerceBatchEntry)) {
			return true;
		}

		return false;
	}

	@Override
	public CommerceBatchEntry getWrappedModel() {
		return _commerceBatchEntry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _commerceBatchEntry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _commerceBatchEntry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_commerceBatchEntry.resetOriginalValues();
	}

	private final CommerceBatchEntry _commerceBatchEntry;
}