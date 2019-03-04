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
 * This class is a wrapper for {@link CommerceBatchJobInstance}.
 * </p>
 *
 * @author Matija Petanjek
 * @see CommerceBatchJobInstance
 * @generated
 */
@ProviderType
public class CommerceBatchJobInstanceWrapper implements CommerceBatchJobInstance,
	ModelWrapper<CommerceBatchJobInstance> {
	public CommerceBatchJobInstanceWrapper(
		CommerceBatchJobInstance commerceBatchJobInstance) {
		_commerceBatchJobInstance = commerceBatchJobInstance;
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceBatchJobInstance.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceBatchJobInstance.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("commerceBatchJobInstanceId",
			getCommerceBatchJobInstanceId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("batchJobName", getBatchJobName());
		attributes.put("key", getKey());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long commerceBatchJobInstanceId = (Long)attributes.get(
				"commerceBatchJobInstanceId");

		if (commerceBatchJobInstanceId != null) {
			setCommerceBatchJobInstanceId(commerceBatchJobInstanceId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String batchJobName = (String)attributes.get("batchJobName");

		if (batchJobName != null) {
			setBatchJobName(batchJobName);
		}

		String key = (String)attributes.get("key");

		if (key != null) {
			setKey(key);
		}
	}

	@Override
	public Object clone() {
		return new CommerceBatchJobInstanceWrapper((CommerceBatchJobInstance)_commerceBatchJobInstance.clone());
	}

	@Override
	public int compareTo(CommerceBatchJobInstance commerceBatchJobInstance) {
		return _commerceBatchJobInstance.compareTo(commerceBatchJobInstance);
	}

	/**
	* Returns the batch job name of this commerce batch job instance.
	*
	* @return the batch job name of this commerce batch job instance
	*/
	@Override
	public String getBatchJobName() {
		return _commerceBatchJobInstance.getBatchJobName();
	}

	/**
	* Returns the commerce batch job instance ID of this commerce batch job instance.
	*
	* @return the commerce batch job instance ID of this commerce batch job instance
	*/
	@Override
	public long getCommerceBatchJobInstanceId() {
		return _commerceBatchJobInstance.getCommerceBatchJobInstanceId();
	}

	/**
	* Returns the create date of this commerce batch job instance.
	*
	* @return the create date of this commerce batch job instance
	*/
	@Override
	public Date getCreateDate() {
		return _commerceBatchJobInstance.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _commerceBatchJobInstance.getExpandoBridge();
	}

	/**
	* Returns the key of this commerce batch job instance.
	*
	* @return the key of this commerce batch job instance
	*/
	@Override
	public String getKey() {
		return _commerceBatchJobInstance.getKey();
	}

	/**
	* Returns the modified date of this commerce batch job instance.
	*
	* @return the modified date of this commerce batch job instance
	*/
	@Override
	public Date getModifiedDate() {
		return _commerceBatchJobInstance.getModifiedDate();
	}

	/**
	* Returns the primary key of this commerce batch job instance.
	*
	* @return the primary key of this commerce batch job instance
	*/
	@Override
	public long getPrimaryKey() {
		return _commerceBatchJobInstance.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceBatchJobInstance.getPrimaryKeyObj();
	}

	@Override
	public int hashCode() {
		return _commerceBatchJobInstance.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _commerceBatchJobInstance.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _commerceBatchJobInstance.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _commerceBatchJobInstance.isNew();
	}

	@Override
	public void persist() {
		_commerceBatchJobInstance.persist();
	}

	/**
	* Sets the batch job name of this commerce batch job instance.
	*
	* @param batchJobName the batch job name of this commerce batch job instance
	*/
	@Override
	public void setBatchJobName(String batchJobName) {
		_commerceBatchJobInstance.setBatchJobName(batchJobName);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_commerceBatchJobInstance.setCachedModel(cachedModel);
	}

	/**
	* Sets the commerce batch job instance ID of this commerce batch job instance.
	*
	* @param commerceBatchJobInstanceId the commerce batch job instance ID of this commerce batch job instance
	*/
	@Override
	public void setCommerceBatchJobInstanceId(long commerceBatchJobInstanceId) {
		_commerceBatchJobInstance.setCommerceBatchJobInstanceId(commerceBatchJobInstanceId);
	}

	/**
	* Sets the create date of this commerce batch job instance.
	*
	* @param createDate the create date of this commerce batch job instance
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_commerceBatchJobInstance.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_commerceBatchJobInstance.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_commerceBatchJobInstance.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_commerceBatchJobInstance.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the key of this commerce batch job instance.
	*
	* @param key the key of this commerce batch job instance
	*/
	@Override
	public void setKey(String key) {
		_commerceBatchJobInstance.setKey(key);
	}

	/**
	* Sets the modified date of this commerce batch job instance.
	*
	* @param modifiedDate the modified date of this commerce batch job instance
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_commerceBatchJobInstance.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_commerceBatchJobInstance.setNew(n);
	}

	/**
	* Sets the primary key of this commerce batch job instance.
	*
	* @param primaryKey the primary key of this commerce batch job instance
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_commerceBatchJobInstance.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_commerceBatchJobInstance.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CommerceBatchJobInstance> toCacheModel() {
		return _commerceBatchJobInstance.toCacheModel();
	}

	@Override
	public CommerceBatchJobInstance toEscapedModel() {
		return new CommerceBatchJobInstanceWrapper(_commerceBatchJobInstance.toEscapedModel());
	}

	@Override
	public String toString() {
		return _commerceBatchJobInstance.toString();
	}

	@Override
	public CommerceBatchJobInstance toUnescapedModel() {
		return new CommerceBatchJobInstanceWrapper(_commerceBatchJobInstance.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _commerceBatchJobInstance.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceBatchJobInstanceWrapper)) {
			return false;
		}

		CommerceBatchJobInstanceWrapper commerceBatchJobInstanceWrapper = (CommerceBatchJobInstanceWrapper)obj;

		if (Objects.equals(_commerceBatchJobInstance,
					commerceBatchJobInstanceWrapper._commerceBatchJobInstance)) {
			return true;
		}

		return false;
	}

	@Override
	public CommerceBatchJobInstance getWrappedModel() {
		return _commerceBatchJobInstance;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _commerceBatchJobInstance.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _commerceBatchJobInstance.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_commerceBatchJobInstance.resetOriginalValues();
	}

	private final CommerceBatchJobInstance _commerceBatchJobInstance;
}