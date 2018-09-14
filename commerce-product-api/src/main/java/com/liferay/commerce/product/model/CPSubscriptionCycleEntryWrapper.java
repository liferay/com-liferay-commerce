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

package com.liferay.commerce.product.model;

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
 * This class is a wrapper for {@link CPSubscriptionCycleEntry}.
 * </p>
 *
 * @author Marco Leo
 * @see CPSubscriptionCycleEntry
 * @generated
 */
@ProviderType
public class CPSubscriptionCycleEntryWrapper implements CPSubscriptionCycleEntry,
	ModelWrapper<CPSubscriptionCycleEntry> {
	public CPSubscriptionCycleEntryWrapper(
		CPSubscriptionCycleEntry cpSubscriptionCycleEntry) {
		_cpSubscriptionCycleEntry = cpSubscriptionCycleEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return CPSubscriptionCycleEntry.class;
	}

	@Override
	public String getModelClassName() {
		return CPSubscriptionCycleEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("CPSubscriptionCycleEntryId",
			getCPSubscriptionCycleEntryId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("CPSubscriptionEntryId", getCPSubscriptionEntryId());
		attributes.put("commerceOrderItemId", getCommerceOrderItemId());
		attributes.put("renew", isRenew());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long CPSubscriptionCycleEntryId = (Long)attributes.get(
				"CPSubscriptionCycleEntryId");

		if (CPSubscriptionCycleEntryId != null) {
			setCPSubscriptionCycleEntryId(CPSubscriptionCycleEntryId);
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

		Long CPSubscriptionEntryId = (Long)attributes.get(
				"CPSubscriptionEntryId");

		if (CPSubscriptionEntryId != null) {
			setCPSubscriptionEntryId(CPSubscriptionEntryId);
		}

		Long commerceOrderItemId = (Long)attributes.get("commerceOrderItemId");

		if (commerceOrderItemId != null) {
			setCommerceOrderItemId(commerceOrderItemId);
		}

		Boolean renew = (Boolean)attributes.get("renew");

		if (renew != null) {
			setRenew(renew);
		}
	}

	@Override
	public Object clone() {
		return new CPSubscriptionCycleEntryWrapper((CPSubscriptionCycleEntry)_cpSubscriptionCycleEntry.clone());
	}

	@Override
	public int compareTo(CPSubscriptionCycleEntry cpSubscriptionCycleEntry) {
		return _cpSubscriptionCycleEntry.compareTo(cpSubscriptionCycleEntry);
	}

	/**
	* Returns the commerce order item ID of this cp subscription cycle entry.
	*
	* @return the commerce order item ID of this cp subscription cycle entry
	*/
	@Override
	public long getCommerceOrderItemId() {
		return _cpSubscriptionCycleEntry.getCommerceOrderItemId();
	}

	/**
	* Returns the company ID of this cp subscription cycle entry.
	*
	* @return the company ID of this cp subscription cycle entry
	*/
	@Override
	public long getCompanyId() {
		return _cpSubscriptionCycleEntry.getCompanyId();
	}

	/**
	* Returns the cp subscription cycle entry ID of this cp subscription cycle entry.
	*
	* @return the cp subscription cycle entry ID of this cp subscription cycle entry
	*/
	@Override
	public long getCPSubscriptionCycleEntryId() {
		return _cpSubscriptionCycleEntry.getCPSubscriptionCycleEntryId();
	}

	/**
	* Returns the cp subscription entry ID of this cp subscription cycle entry.
	*
	* @return the cp subscription entry ID of this cp subscription cycle entry
	*/
	@Override
	public long getCPSubscriptionEntryId() {
		return _cpSubscriptionCycleEntry.getCPSubscriptionEntryId();
	}

	/**
	* Returns the create date of this cp subscription cycle entry.
	*
	* @return the create date of this cp subscription cycle entry
	*/
	@Override
	public Date getCreateDate() {
		return _cpSubscriptionCycleEntry.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _cpSubscriptionCycleEntry.getExpandoBridge();
	}

	/**
	* Returns the group ID of this cp subscription cycle entry.
	*
	* @return the group ID of this cp subscription cycle entry
	*/
	@Override
	public long getGroupId() {
		return _cpSubscriptionCycleEntry.getGroupId();
	}

	/**
	* Returns the modified date of this cp subscription cycle entry.
	*
	* @return the modified date of this cp subscription cycle entry
	*/
	@Override
	public Date getModifiedDate() {
		return _cpSubscriptionCycleEntry.getModifiedDate();
	}

	/**
	* Returns the primary key of this cp subscription cycle entry.
	*
	* @return the primary key of this cp subscription cycle entry
	*/
	@Override
	public long getPrimaryKey() {
		return _cpSubscriptionCycleEntry.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _cpSubscriptionCycleEntry.getPrimaryKeyObj();
	}

	/**
	* Returns the renew of this cp subscription cycle entry.
	*
	* @return the renew of this cp subscription cycle entry
	*/
	@Override
	public boolean getRenew() {
		return _cpSubscriptionCycleEntry.getRenew();
	}

	/**
	* Returns the user ID of this cp subscription cycle entry.
	*
	* @return the user ID of this cp subscription cycle entry
	*/
	@Override
	public long getUserId() {
		return _cpSubscriptionCycleEntry.getUserId();
	}

	/**
	* Returns the user name of this cp subscription cycle entry.
	*
	* @return the user name of this cp subscription cycle entry
	*/
	@Override
	public String getUserName() {
		return _cpSubscriptionCycleEntry.getUserName();
	}

	/**
	* Returns the user uuid of this cp subscription cycle entry.
	*
	* @return the user uuid of this cp subscription cycle entry
	*/
	@Override
	public String getUserUuid() {
		return _cpSubscriptionCycleEntry.getUserUuid();
	}

	/**
	* Returns the uuid of this cp subscription cycle entry.
	*
	* @return the uuid of this cp subscription cycle entry
	*/
	@Override
	public String getUuid() {
		return _cpSubscriptionCycleEntry.getUuid();
	}

	@Override
	public int hashCode() {
		return _cpSubscriptionCycleEntry.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _cpSubscriptionCycleEntry.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _cpSubscriptionCycleEntry.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _cpSubscriptionCycleEntry.isNew();
	}

	/**
	* Returns <code>true</code> if this cp subscription cycle entry is renew.
	*
	* @return <code>true</code> if this cp subscription cycle entry is renew; <code>false</code> otherwise
	*/
	@Override
	public boolean isRenew() {
		return _cpSubscriptionCycleEntry.isRenew();
	}

	@Override
	public void persist() {
		_cpSubscriptionCycleEntry.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_cpSubscriptionCycleEntry.setCachedModel(cachedModel);
	}

	/**
	* Sets the commerce order item ID of this cp subscription cycle entry.
	*
	* @param commerceOrderItemId the commerce order item ID of this cp subscription cycle entry
	*/
	@Override
	public void setCommerceOrderItemId(long commerceOrderItemId) {
		_cpSubscriptionCycleEntry.setCommerceOrderItemId(commerceOrderItemId);
	}

	/**
	* Sets the company ID of this cp subscription cycle entry.
	*
	* @param companyId the company ID of this cp subscription cycle entry
	*/
	@Override
	public void setCompanyId(long companyId) {
		_cpSubscriptionCycleEntry.setCompanyId(companyId);
	}

	/**
	* Sets the cp subscription cycle entry ID of this cp subscription cycle entry.
	*
	* @param CPSubscriptionCycleEntryId the cp subscription cycle entry ID of this cp subscription cycle entry
	*/
	@Override
	public void setCPSubscriptionCycleEntryId(long CPSubscriptionCycleEntryId) {
		_cpSubscriptionCycleEntry.setCPSubscriptionCycleEntryId(CPSubscriptionCycleEntryId);
	}

	/**
	* Sets the cp subscription entry ID of this cp subscription cycle entry.
	*
	* @param CPSubscriptionEntryId the cp subscription entry ID of this cp subscription cycle entry
	*/
	@Override
	public void setCPSubscriptionEntryId(long CPSubscriptionEntryId) {
		_cpSubscriptionCycleEntry.setCPSubscriptionEntryId(CPSubscriptionEntryId);
	}

	/**
	* Sets the create date of this cp subscription cycle entry.
	*
	* @param createDate the create date of this cp subscription cycle entry
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_cpSubscriptionCycleEntry.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_cpSubscriptionCycleEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_cpSubscriptionCycleEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_cpSubscriptionCycleEntry.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this cp subscription cycle entry.
	*
	* @param groupId the group ID of this cp subscription cycle entry
	*/
	@Override
	public void setGroupId(long groupId) {
		_cpSubscriptionCycleEntry.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this cp subscription cycle entry.
	*
	* @param modifiedDate the modified date of this cp subscription cycle entry
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_cpSubscriptionCycleEntry.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_cpSubscriptionCycleEntry.setNew(n);
	}

	/**
	* Sets the primary key of this cp subscription cycle entry.
	*
	* @param primaryKey the primary key of this cp subscription cycle entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_cpSubscriptionCycleEntry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_cpSubscriptionCycleEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this cp subscription cycle entry is renew.
	*
	* @param renew the renew of this cp subscription cycle entry
	*/
	@Override
	public void setRenew(boolean renew) {
		_cpSubscriptionCycleEntry.setRenew(renew);
	}

	/**
	* Sets the user ID of this cp subscription cycle entry.
	*
	* @param userId the user ID of this cp subscription cycle entry
	*/
	@Override
	public void setUserId(long userId) {
		_cpSubscriptionCycleEntry.setUserId(userId);
	}

	/**
	* Sets the user name of this cp subscription cycle entry.
	*
	* @param userName the user name of this cp subscription cycle entry
	*/
	@Override
	public void setUserName(String userName) {
		_cpSubscriptionCycleEntry.setUserName(userName);
	}

	/**
	* Sets the user uuid of this cp subscription cycle entry.
	*
	* @param userUuid the user uuid of this cp subscription cycle entry
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_cpSubscriptionCycleEntry.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this cp subscription cycle entry.
	*
	* @param uuid the uuid of this cp subscription cycle entry
	*/
	@Override
	public void setUuid(String uuid) {
		_cpSubscriptionCycleEntry.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CPSubscriptionCycleEntry> toCacheModel() {
		return _cpSubscriptionCycleEntry.toCacheModel();
	}

	@Override
	public CPSubscriptionCycleEntry toEscapedModel() {
		return new CPSubscriptionCycleEntryWrapper(_cpSubscriptionCycleEntry.toEscapedModel());
	}

	@Override
	public String toString() {
		return _cpSubscriptionCycleEntry.toString();
	}

	@Override
	public CPSubscriptionCycleEntry toUnescapedModel() {
		return new CPSubscriptionCycleEntryWrapper(_cpSubscriptionCycleEntry.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _cpSubscriptionCycleEntry.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CPSubscriptionCycleEntryWrapper)) {
			return false;
		}

		CPSubscriptionCycleEntryWrapper cpSubscriptionCycleEntryWrapper = (CPSubscriptionCycleEntryWrapper)obj;

		if (Objects.equals(_cpSubscriptionCycleEntry,
					cpSubscriptionCycleEntryWrapper._cpSubscriptionCycleEntry)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _cpSubscriptionCycleEntry.getStagedModelType();
	}

	@Override
	public CPSubscriptionCycleEntry getWrappedModel() {
		return _cpSubscriptionCycleEntry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _cpSubscriptionCycleEntry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _cpSubscriptionCycleEntry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_cpSubscriptionCycleEntry.resetOriginalValues();
	}

	private final CPSubscriptionCycleEntry _cpSubscriptionCycleEntry;
}