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

package com.liferay.commerce.model;

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
 * This class is a wrapper for {@link CommerceSubscriptionEntry}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceSubscriptionEntry
 * @generated
 */
@ProviderType
public class CommerceSubscriptionEntryWrapper
	implements CommerceSubscriptionEntry,
		ModelWrapper<CommerceSubscriptionEntry> {
	public CommerceSubscriptionEntryWrapper(
		CommerceSubscriptionEntry commerceSubscriptionEntry) {
		_commerceSubscriptionEntry = commerceSubscriptionEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceSubscriptionEntry.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceSubscriptionEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("commerceSubscriptionEntryId",
			getCommerceSubscriptionEntryId());
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

		Long commerceSubscriptionEntryId = (Long)attributes.get(
				"commerceSubscriptionEntryId");

		if (commerceSubscriptionEntryId != null) {
			setCommerceSubscriptionEntryId(commerceSubscriptionEntryId);
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
		return new CommerceSubscriptionEntryWrapper((CommerceSubscriptionEntry)_commerceSubscriptionEntry.clone());
	}

	@Override
	public int compareTo(CommerceSubscriptionEntry commerceSubscriptionEntry) {
		return _commerceSubscriptionEntry.compareTo(commerceSubscriptionEntry);
	}

	/**
	* Returns the active of this commerce subscription entry.
	*
	* @return the active of this commerce subscription entry
	*/
	@Override
	public boolean getActive() {
		return _commerceSubscriptionEntry.getActive();
	}

	@Override
	public CommerceOrderItem getCommerceOrderItem()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceSubscriptionEntry.getCommerceOrderItem();
	}

	/**
	* Returns the commerce order item ID of this commerce subscription entry.
	*
	* @return the commerce order item ID of this commerce subscription entry
	*/
	@Override
	public long getCommerceOrderItemId() {
		return _commerceSubscriptionEntry.getCommerceOrderItemId();
	}

	/**
	* Returns the commerce subscription entry ID of this commerce subscription entry.
	*
	* @return the commerce subscription entry ID of this commerce subscription entry
	*/
	@Override
	public long getCommerceSubscriptionEntryId() {
		return _commerceSubscriptionEntry.getCommerceSubscriptionEntryId();
	}

	/**
	* Returns the company ID of this commerce subscription entry.
	*
	* @return the company ID of this commerce subscription entry
	*/
	@Override
	public long getCompanyId() {
		return _commerceSubscriptionEntry.getCompanyId();
	}

	@Override
	public com.liferay.commerce.product.model.CPDefinition getCPDefinition()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceSubscriptionEntry.getCPDefinition();
	}

	@Override
	public long getCPDefinitionId()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceSubscriptionEntry.getCPDefinitionId();
	}

	@Override
	public com.liferay.commerce.product.model.CPInstance getCPInstance()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceSubscriptionEntry.getCPInstance();
	}

	/**
	* Returns the cp instance ID of this commerce subscription entry.
	*
	* @return the cp instance ID of this commerce subscription entry
	*/
	@Override
	public long getCPInstanceId() {
		return _commerceSubscriptionEntry.getCPInstanceId();
	}

	/**
	* Returns the create date of this commerce subscription entry.
	*
	* @return the create date of this commerce subscription entry
	*/
	@Override
	public Date getCreateDate() {
		return _commerceSubscriptionEntry.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _commerceSubscriptionEntry.getExpandoBridge();
	}

	/**
	* Returns the group ID of this commerce subscription entry.
	*
	* @return the group ID of this commerce subscription entry
	*/
	@Override
	public long getGroupId() {
		return _commerceSubscriptionEntry.getGroupId();
	}

	/**
	* Returns the max subscription cycles number of this commerce subscription entry.
	*
	* @return the max subscription cycles number of this commerce subscription entry
	*/
	@Override
	public long getMaxSubscriptionCyclesNumber() {
		return _commerceSubscriptionEntry.getMaxSubscriptionCyclesNumber();
	}

	/**
	* Returns the modified date of this commerce subscription entry.
	*
	* @return the modified date of this commerce subscription entry
	*/
	@Override
	public Date getModifiedDate() {
		return _commerceSubscriptionEntry.getModifiedDate();
	}

	/**
	* Returns the next iteration date of this commerce subscription entry.
	*
	* @return the next iteration date of this commerce subscription entry
	*/
	@Override
	public Date getNextIterationDate() {
		return _commerceSubscriptionEntry.getNextIterationDate();
	}

	/**
	* Returns the primary key of this commerce subscription entry.
	*
	* @return the primary key of this commerce subscription entry
	*/
	@Override
	public long getPrimaryKey() {
		return _commerceSubscriptionEntry.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceSubscriptionEntry.getPrimaryKeyObj();
	}

	/**
	* Returns the subscription cycle length of this commerce subscription entry.
	*
	* @return the subscription cycle length of this commerce subscription entry
	*/
	@Override
	public long getSubscriptionCycleLength() {
		return _commerceSubscriptionEntry.getSubscriptionCycleLength();
	}

	/**
	* Returns the subscription cycle period of this commerce subscription entry.
	*
	* @return the subscription cycle period of this commerce subscription entry
	*/
	@Override
	public String getSubscriptionCyclePeriod() {
		return _commerceSubscriptionEntry.getSubscriptionCyclePeriod();
	}

	/**
	* Returns the user ID of this commerce subscription entry.
	*
	* @return the user ID of this commerce subscription entry
	*/
	@Override
	public long getUserId() {
		return _commerceSubscriptionEntry.getUserId();
	}

	/**
	* Returns the user name of this commerce subscription entry.
	*
	* @return the user name of this commerce subscription entry
	*/
	@Override
	public String getUserName() {
		return _commerceSubscriptionEntry.getUserName();
	}

	/**
	* Returns the user uuid of this commerce subscription entry.
	*
	* @return the user uuid of this commerce subscription entry
	*/
	@Override
	public String getUserUuid() {
		return _commerceSubscriptionEntry.getUserUuid();
	}

	/**
	* Returns the uuid of this commerce subscription entry.
	*
	* @return the uuid of this commerce subscription entry
	*/
	@Override
	public String getUuid() {
		return _commerceSubscriptionEntry.getUuid();
	}

	@Override
	public int hashCode() {
		return _commerceSubscriptionEntry.hashCode();
	}

	/**
	* Returns <code>true</code> if this commerce subscription entry is active.
	*
	* @return <code>true</code> if this commerce subscription entry is active; <code>false</code> otherwise
	*/
	@Override
	public boolean isActive() {
		return _commerceSubscriptionEntry.isActive();
	}

	@Override
	public boolean isCachedModel() {
		return _commerceSubscriptionEntry.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _commerceSubscriptionEntry.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _commerceSubscriptionEntry.isNew();
	}

	@Override
	public void persist() {
		_commerceSubscriptionEntry.persist();
	}

	/**
	* Sets whether this commerce subscription entry is active.
	*
	* @param active the active of this commerce subscription entry
	*/
	@Override
	public void setActive(boolean active) {
		_commerceSubscriptionEntry.setActive(active);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_commerceSubscriptionEntry.setCachedModel(cachedModel);
	}

	/**
	* Sets the commerce order item ID of this commerce subscription entry.
	*
	* @param commerceOrderItemId the commerce order item ID of this commerce subscription entry
	*/
	@Override
	public void setCommerceOrderItemId(long commerceOrderItemId) {
		_commerceSubscriptionEntry.setCommerceOrderItemId(commerceOrderItemId);
	}

	/**
	* Sets the commerce subscription entry ID of this commerce subscription entry.
	*
	* @param commerceSubscriptionEntryId the commerce subscription entry ID of this commerce subscription entry
	*/
	@Override
	public void setCommerceSubscriptionEntryId(long commerceSubscriptionEntryId) {
		_commerceSubscriptionEntry.setCommerceSubscriptionEntryId(commerceSubscriptionEntryId);
	}

	/**
	* Sets the company ID of this commerce subscription entry.
	*
	* @param companyId the company ID of this commerce subscription entry
	*/
	@Override
	public void setCompanyId(long companyId) {
		_commerceSubscriptionEntry.setCompanyId(companyId);
	}

	/**
	* Sets the cp instance ID of this commerce subscription entry.
	*
	* @param CPInstanceId the cp instance ID of this commerce subscription entry
	*/
	@Override
	public void setCPInstanceId(long CPInstanceId) {
		_commerceSubscriptionEntry.setCPInstanceId(CPInstanceId);
	}

	/**
	* Sets the create date of this commerce subscription entry.
	*
	* @param createDate the create date of this commerce subscription entry
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_commerceSubscriptionEntry.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_commerceSubscriptionEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_commerceSubscriptionEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_commerceSubscriptionEntry.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this commerce subscription entry.
	*
	* @param groupId the group ID of this commerce subscription entry
	*/
	@Override
	public void setGroupId(long groupId) {
		_commerceSubscriptionEntry.setGroupId(groupId);
	}

	/**
	* Sets the max subscription cycles number of this commerce subscription entry.
	*
	* @param maxSubscriptionCyclesNumber the max subscription cycles number of this commerce subscription entry
	*/
	@Override
	public void setMaxSubscriptionCyclesNumber(long maxSubscriptionCyclesNumber) {
		_commerceSubscriptionEntry.setMaxSubscriptionCyclesNumber(maxSubscriptionCyclesNumber);
	}

	/**
	* Sets the modified date of this commerce subscription entry.
	*
	* @param modifiedDate the modified date of this commerce subscription entry
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_commerceSubscriptionEntry.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_commerceSubscriptionEntry.setNew(n);
	}

	/**
	* Sets the next iteration date of this commerce subscription entry.
	*
	* @param nextIterationDate the next iteration date of this commerce subscription entry
	*/
	@Override
	public void setNextIterationDate(Date nextIterationDate) {
		_commerceSubscriptionEntry.setNextIterationDate(nextIterationDate);
	}

	/**
	* Sets the primary key of this commerce subscription entry.
	*
	* @param primaryKey the primary key of this commerce subscription entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_commerceSubscriptionEntry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_commerceSubscriptionEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the subscription cycle length of this commerce subscription entry.
	*
	* @param subscriptionCycleLength the subscription cycle length of this commerce subscription entry
	*/
	@Override
	public void setSubscriptionCycleLength(long subscriptionCycleLength) {
		_commerceSubscriptionEntry.setSubscriptionCycleLength(subscriptionCycleLength);
	}

	/**
	* Sets the subscription cycle period of this commerce subscription entry.
	*
	* @param subscriptionCyclePeriod the subscription cycle period of this commerce subscription entry
	*/
	@Override
	public void setSubscriptionCyclePeriod(String subscriptionCyclePeriod) {
		_commerceSubscriptionEntry.setSubscriptionCyclePeriod(subscriptionCyclePeriod);
	}

	/**
	* Sets the user ID of this commerce subscription entry.
	*
	* @param userId the user ID of this commerce subscription entry
	*/
	@Override
	public void setUserId(long userId) {
		_commerceSubscriptionEntry.setUserId(userId);
	}

	/**
	* Sets the user name of this commerce subscription entry.
	*
	* @param userName the user name of this commerce subscription entry
	*/
	@Override
	public void setUserName(String userName) {
		_commerceSubscriptionEntry.setUserName(userName);
	}

	/**
	* Sets the user uuid of this commerce subscription entry.
	*
	* @param userUuid the user uuid of this commerce subscription entry
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_commerceSubscriptionEntry.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this commerce subscription entry.
	*
	* @param uuid the uuid of this commerce subscription entry
	*/
	@Override
	public void setUuid(String uuid) {
		_commerceSubscriptionEntry.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CommerceSubscriptionEntry> toCacheModel() {
		return _commerceSubscriptionEntry.toCacheModel();
	}

	@Override
	public CommerceSubscriptionEntry toEscapedModel() {
		return new CommerceSubscriptionEntryWrapper(_commerceSubscriptionEntry.toEscapedModel());
	}

	@Override
	public String toString() {
		return _commerceSubscriptionEntry.toString();
	}

	@Override
	public CommerceSubscriptionEntry toUnescapedModel() {
		return new CommerceSubscriptionEntryWrapper(_commerceSubscriptionEntry.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _commerceSubscriptionEntry.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceSubscriptionEntryWrapper)) {
			return false;
		}

		CommerceSubscriptionEntryWrapper commerceSubscriptionEntryWrapper = (CommerceSubscriptionEntryWrapper)obj;

		if (Objects.equals(_commerceSubscriptionEntry,
					commerceSubscriptionEntryWrapper._commerceSubscriptionEntry)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _commerceSubscriptionEntry.getStagedModelType();
	}

	@Override
	public CommerceSubscriptionEntry getWrappedModel() {
		return _commerceSubscriptionEntry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _commerceSubscriptionEntry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _commerceSubscriptionEntry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_commerceSubscriptionEntry.resetOriginalValues();
	}

	private final CommerceSubscriptionEntry _commerceSubscriptionEntry;
}