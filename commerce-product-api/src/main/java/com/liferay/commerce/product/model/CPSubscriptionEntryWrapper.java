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
 * This class is a wrapper for {@link CPSubscriptionEntry}.
 * </p>
 *
 * @author Marco Leo
 * @see CPSubscriptionEntry
 * @generated
 */
@ProviderType
public class CPSubscriptionEntryWrapper implements CPSubscriptionEntry,
	ModelWrapper<CPSubscriptionEntry> {
	public CPSubscriptionEntryWrapper(CPSubscriptionEntry cpSubscriptionEntry) {
		_cpSubscriptionEntry = cpSubscriptionEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return CPSubscriptionEntry.class;
	}

	@Override
	public String getModelClassName() {
		return CPSubscriptionEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("CPSubscriptionEntryId", getCPSubscriptionEntryId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("CPInstanceId", getCPInstanceId());
		attributes.put("commerceOrderItemId", getCommerceOrderItemId());
		attributes.put("subscriptionCycleLength", getSubscriptionCycleLength());
		attributes.put("subscriptionCyclePeriod", getSubscriptionCyclePeriod());
		attributes.put("maxSubscriptionCyclesNumber",
			getMaxSubscriptionCyclesNumber());
		attributes.put("active", isActive());
		attributes.put("nextIterationDate", getNextIterationDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long CPSubscriptionEntryId = (Long)attributes.get(
				"CPSubscriptionEntryId");

		if (CPSubscriptionEntryId != null) {
			setCPSubscriptionEntryId(CPSubscriptionEntryId);
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

		Long CPInstanceId = (Long)attributes.get("CPInstanceId");

		if (CPInstanceId != null) {
			setCPInstanceId(CPInstanceId);
		}

		Long commerceOrderItemId = (Long)attributes.get("commerceOrderItemId");

		if (commerceOrderItemId != null) {
			setCommerceOrderItemId(commerceOrderItemId);
		}

		Long subscriptionCycleLength = (Long)attributes.get(
				"subscriptionCycleLength");

		if (subscriptionCycleLength != null) {
			setSubscriptionCycleLength(subscriptionCycleLength);
		}

		String subscriptionCyclePeriod = (String)attributes.get(
				"subscriptionCyclePeriod");

		if (subscriptionCyclePeriod != null) {
			setSubscriptionCyclePeriod(subscriptionCyclePeriod);
		}

		Long maxSubscriptionCyclesNumber = (Long)attributes.get(
				"maxSubscriptionCyclesNumber");

		if (maxSubscriptionCyclesNumber != null) {
			setMaxSubscriptionCyclesNumber(maxSubscriptionCyclesNumber);
		}

		Boolean active = (Boolean)attributes.get("active");

		if (active != null) {
			setActive(active);
		}

		Date nextIterationDate = (Date)attributes.get("nextIterationDate");

		if (nextIterationDate != null) {
			setNextIterationDate(nextIterationDate);
		}
	}

	@Override
	public Object clone() {
		return new CPSubscriptionEntryWrapper((CPSubscriptionEntry)_cpSubscriptionEntry.clone());
	}

	@Override
	public int compareTo(CPSubscriptionEntry cpSubscriptionEntry) {
		return _cpSubscriptionEntry.compareTo(cpSubscriptionEntry);
	}

	/**
	* Returns the active of this cp subscription entry.
	*
	* @return the active of this cp subscription entry
	*/
	@Override
	public boolean getActive() {
		return _cpSubscriptionEntry.getActive();
	}

	/**
	* Returns the commerce order item ID of this cp subscription entry.
	*
	* @return the commerce order item ID of this cp subscription entry
	*/
	@Override
	public long getCommerceOrderItemId() {
		return _cpSubscriptionEntry.getCommerceOrderItemId();
	}

	/**
	* Returns the company ID of this cp subscription entry.
	*
	* @return the company ID of this cp subscription entry
	*/
	@Override
	public long getCompanyId() {
		return _cpSubscriptionEntry.getCompanyId();
	}

	@Override
	public CPDefinition getCPDefinition()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpSubscriptionEntry.getCPDefinition();
	}

	@Override
	public long getCPDefinitionId()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpSubscriptionEntry.getCPDefinitionId();
	}

	@Override
	public CPInstance getCPInstance()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpSubscriptionEntry.getCPInstance();
	}

	/**
	* Returns the cp instance ID of this cp subscription entry.
	*
	* @return the cp instance ID of this cp subscription entry
	*/
	@Override
	public long getCPInstanceId() {
		return _cpSubscriptionEntry.getCPInstanceId();
	}

	/**
	* Returns the cp subscription entry ID of this cp subscription entry.
	*
	* @return the cp subscription entry ID of this cp subscription entry
	*/
	@Override
	public long getCPSubscriptionEntryId() {
		return _cpSubscriptionEntry.getCPSubscriptionEntryId();
	}

	/**
	* Returns the create date of this cp subscription entry.
	*
	* @return the create date of this cp subscription entry
	*/
	@Override
	public Date getCreateDate() {
		return _cpSubscriptionEntry.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _cpSubscriptionEntry.getExpandoBridge();
	}

	/**
	* Returns the group ID of this cp subscription entry.
	*
	* @return the group ID of this cp subscription entry
	*/
	@Override
	public long getGroupId() {
		return _cpSubscriptionEntry.getGroupId();
	}

	/**
	* Returns the max subscription cycles number of this cp subscription entry.
	*
	* @return the max subscription cycles number of this cp subscription entry
	*/
	@Override
	public long getMaxSubscriptionCyclesNumber() {
		return _cpSubscriptionEntry.getMaxSubscriptionCyclesNumber();
	}

	/**
	* Returns the modified date of this cp subscription entry.
	*
	* @return the modified date of this cp subscription entry
	*/
	@Override
	public Date getModifiedDate() {
		return _cpSubscriptionEntry.getModifiedDate();
	}

	/**
	* Returns the next iteration date of this cp subscription entry.
	*
	* @return the next iteration date of this cp subscription entry
	*/
	@Override
	public Date getNextIterationDate() {
		return _cpSubscriptionEntry.getNextIterationDate();
	}

	/**
	* Returns the primary key of this cp subscription entry.
	*
	* @return the primary key of this cp subscription entry
	*/
	@Override
	public long getPrimaryKey() {
		return _cpSubscriptionEntry.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _cpSubscriptionEntry.getPrimaryKeyObj();
	}

	/**
	* Returns the subscription cycle length of this cp subscription entry.
	*
	* @return the subscription cycle length of this cp subscription entry
	*/
	@Override
	public long getSubscriptionCycleLength() {
		return _cpSubscriptionEntry.getSubscriptionCycleLength();
	}

	/**
	* Returns the subscription cycle period of this cp subscription entry.
	*
	* @return the subscription cycle period of this cp subscription entry
	*/
	@Override
	public String getSubscriptionCyclePeriod() {
		return _cpSubscriptionEntry.getSubscriptionCyclePeriod();
	}

	/**
	* Returns the user ID of this cp subscription entry.
	*
	* @return the user ID of this cp subscription entry
	*/
	@Override
	public long getUserId() {
		return _cpSubscriptionEntry.getUserId();
	}

	/**
	* Returns the user name of this cp subscription entry.
	*
	* @return the user name of this cp subscription entry
	*/
	@Override
	public String getUserName() {
		return _cpSubscriptionEntry.getUserName();
	}

	/**
	* Returns the user uuid of this cp subscription entry.
	*
	* @return the user uuid of this cp subscription entry
	*/
	@Override
	public String getUserUuid() {
		return _cpSubscriptionEntry.getUserUuid();
	}

	/**
	* Returns the uuid of this cp subscription entry.
	*
	* @return the uuid of this cp subscription entry
	*/
	@Override
	public String getUuid() {
		return _cpSubscriptionEntry.getUuid();
	}

	@Override
	public int hashCode() {
		return _cpSubscriptionEntry.hashCode();
	}

	/**
	* Returns <code>true</code> if this cp subscription entry is active.
	*
	* @return <code>true</code> if this cp subscription entry is active; <code>false</code> otherwise
	*/
	@Override
	public boolean isActive() {
		return _cpSubscriptionEntry.isActive();
	}

	@Override
	public boolean isCachedModel() {
		return _cpSubscriptionEntry.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _cpSubscriptionEntry.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _cpSubscriptionEntry.isNew();
	}

	@Override
	public void persist() {
		_cpSubscriptionEntry.persist();
	}

	/**
	* Sets whether this cp subscription entry is active.
	*
	* @param active the active of this cp subscription entry
	*/
	@Override
	public void setActive(boolean active) {
		_cpSubscriptionEntry.setActive(active);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_cpSubscriptionEntry.setCachedModel(cachedModel);
	}

	/**
	* Sets the commerce order item ID of this cp subscription entry.
	*
	* @param commerceOrderItemId the commerce order item ID of this cp subscription entry
	*/
	@Override
	public void setCommerceOrderItemId(long commerceOrderItemId) {
		_cpSubscriptionEntry.setCommerceOrderItemId(commerceOrderItemId);
	}

	/**
	* Sets the company ID of this cp subscription entry.
	*
	* @param companyId the company ID of this cp subscription entry
	*/
	@Override
	public void setCompanyId(long companyId) {
		_cpSubscriptionEntry.setCompanyId(companyId);
	}

	/**
	* Sets the cp instance ID of this cp subscription entry.
	*
	* @param CPInstanceId the cp instance ID of this cp subscription entry
	*/
	@Override
	public void setCPInstanceId(long CPInstanceId) {
		_cpSubscriptionEntry.setCPInstanceId(CPInstanceId);
	}

	/**
	* Sets the cp subscription entry ID of this cp subscription entry.
	*
	* @param CPSubscriptionEntryId the cp subscription entry ID of this cp subscription entry
	*/
	@Override
	public void setCPSubscriptionEntryId(long CPSubscriptionEntryId) {
		_cpSubscriptionEntry.setCPSubscriptionEntryId(CPSubscriptionEntryId);
	}

	/**
	* Sets the create date of this cp subscription entry.
	*
	* @param createDate the create date of this cp subscription entry
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_cpSubscriptionEntry.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_cpSubscriptionEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_cpSubscriptionEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_cpSubscriptionEntry.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this cp subscription entry.
	*
	* @param groupId the group ID of this cp subscription entry
	*/
	@Override
	public void setGroupId(long groupId) {
		_cpSubscriptionEntry.setGroupId(groupId);
	}

	/**
	* Sets the max subscription cycles number of this cp subscription entry.
	*
	* @param maxSubscriptionCyclesNumber the max subscription cycles number of this cp subscription entry
	*/
	@Override
	public void setMaxSubscriptionCyclesNumber(long maxSubscriptionCyclesNumber) {
		_cpSubscriptionEntry.setMaxSubscriptionCyclesNumber(maxSubscriptionCyclesNumber);
	}

	/**
	* Sets the modified date of this cp subscription entry.
	*
	* @param modifiedDate the modified date of this cp subscription entry
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_cpSubscriptionEntry.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_cpSubscriptionEntry.setNew(n);
	}

	/**
	* Sets the next iteration date of this cp subscription entry.
	*
	* @param nextIterationDate the next iteration date of this cp subscription entry
	*/
	@Override
	public void setNextIterationDate(Date nextIterationDate) {
		_cpSubscriptionEntry.setNextIterationDate(nextIterationDate);
	}

	/**
	* Sets the primary key of this cp subscription entry.
	*
	* @param primaryKey the primary key of this cp subscription entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_cpSubscriptionEntry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_cpSubscriptionEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the subscription cycle length of this cp subscription entry.
	*
	* @param subscriptionCycleLength the subscription cycle length of this cp subscription entry
	*/
	@Override
	public void setSubscriptionCycleLength(long subscriptionCycleLength) {
		_cpSubscriptionEntry.setSubscriptionCycleLength(subscriptionCycleLength);
	}

	/**
	* Sets the subscription cycle period of this cp subscription entry.
	*
	* @param subscriptionCyclePeriod the subscription cycle period of this cp subscription entry
	*/
	@Override
	public void setSubscriptionCyclePeriod(String subscriptionCyclePeriod) {
		_cpSubscriptionEntry.setSubscriptionCyclePeriod(subscriptionCyclePeriod);
	}

	/**
	* Sets the user ID of this cp subscription entry.
	*
	* @param userId the user ID of this cp subscription entry
	*/
	@Override
	public void setUserId(long userId) {
		_cpSubscriptionEntry.setUserId(userId);
	}

	/**
	* Sets the user name of this cp subscription entry.
	*
	* @param userName the user name of this cp subscription entry
	*/
	@Override
	public void setUserName(String userName) {
		_cpSubscriptionEntry.setUserName(userName);
	}

	/**
	* Sets the user uuid of this cp subscription entry.
	*
	* @param userUuid the user uuid of this cp subscription entry
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_cpSubscriptionEntry.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this cp subscription entry.
	*
	* @param uuid the uuid of this cp subscription entry
	*/
	@Override
	public void setUuid(String uuid) {
		_cpSubscriptionEntry.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CPSubscriptionEntry> toCacheModel() {
		return _cpSubscriptionEntry.toCacheModel();
	}

	@Override
	public CPSubscriptionEntry toEscapedModel() {
		return new CPSubscriptionEntryWrapper(_cpSubscriptionEntry.toEscapedModel());
	}

	@Override
	public String toString() {
		return _cpSubscriptionEntry.toString();
	}

	@Override
	public CPSubscriptionEntry toUnescapedModel() {
		return new CPSubscriptionEntryWrapper(_cpSubscriptionEntry.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _cpSubscriptionEntry.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CPSubscriptionEntryWrapper)) {
			return false;
		}

		CPSubscriptionEntryWrapper cpSubscriptionEntryWrapper = (CPSubscriptionEntryWrapper)obj;

		if (Objects.equals(_cpSubscriptionEntry,
					cpSubscriptionEntryWrapper._cpSubscriptionEntry)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _cpSubscriptionEntry.getStagedModelType();
	}

	@Override
	public CPSubscriptionEntry getWrappedModel() {
		return _cpSubscriptionEntry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _cpSubscriptionEntry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _cpSubscriptionEntry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_cpSubscriptionEntry.resetOriginalValues();
	}

	private final CPSubscriptionEntry _cpSubscriptionEntry;
}