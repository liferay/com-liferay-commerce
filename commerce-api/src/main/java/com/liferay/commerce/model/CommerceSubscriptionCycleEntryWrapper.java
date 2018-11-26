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
 * This class is a wrapper for {@link CommerceSubscriptionCycleEntry}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceSubscriptionCycleEntry
 * @generated
 */
@ProviderType
public class CommerceSubscriptionCycleEntryWrapper
	implements CommerceSubscriptionCycleEntry,
		ModelWrapper<CommerceSubscriptionCycleEntry> {
	public CommerceSubscriptionCycleEntryWrapper(
		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry) {
		_commerceSubscriptionCycleEntry = commerceSubscriptionCycleEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceSubscriptionCycleEntry.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceSubscriptionCycleEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("commerceSubscriptionCycleEntryId",
			getCommerceSubscriptionCycleEntryId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("commerceSubscriptionEntryId",
			getCommerceSubscriptionEntryId());
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

		Long commerceSubscriptionCycleEntryId = (Long)attributes.get(
				"commerceSubscriptionCycleEntryId");

		if (commerceSubscriptionCycleEntryId != null) {
			setCommerceSubscriptionCycleEntryId(commerceSubscriptionCycleEntryId);
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

		Long commerceSubscriptionEntryId = (Long)attributes.get(
				"commerceSubscriptionEntryId");

		if (commerceSubscriptionEntryId != null) {
			setCommerceSubscriptionEntryId(commerceSubscriptionEntryId);
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
		return new CommerceSubscriptionCycleEntryWrapper((CommerceSubscriptionCycleEntry)_commerceSubscriptionCycleEntry.clone());
	}

	@Override
	public int compareTo(
		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry) {
		return _commerceSubscriptionCycleEntry.compareTo(commerceSubscriptionCycleEntry);
	}

	/**
	* Returns the commerce order item ID of this commerce subscription cycle entry.
	*
	* @return the commerce order item ID of this commerce subscription cycle entry
	*/
	@Override
	public long getCommerceOrderItemId() {
		return _commerceSubscriptionCycleEntry.getCommerceOrderItemId();
	}

	/**
	* Returns the commerce subscription cycle entry ID of this commerce subscription cycle entry.
	*
	* @return the commerce subscription cycle entry ID of this commerce subscription cycle entry
	*/
	@Override
	public long getCommerceSubscriptionCycleEntryId() {
		return _commerceSubscriptionCycleEntry.getCommerceSubscriptionCycleEntryId();
	}

	@Override
	public CommerceSubscriptionEntry getCommerceSubscriptionEntry()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceSubscriptionCycleEntry.getCommerceSubscriptionEntry();
	}

	/**
	* Returns the commerce subscription entry ID of this commerce subscription cycle entry.
	*
	* @return the commerce subscription entry ID of this commerce subscription cycle entry
	*/
	@Override
	public long getCommerceSubscriptionEntryId() {
		return _commerceSubscriptionCycleEntry.getCommerceSubscriptionEntryId();
	}

	/**
	* Returns the company ID of this commerce subscription cycle entry.
	*
	* @return the company ID of this commerce subscription cycle entry
	*/
	@Override
	public long getCompanyId() {
		return _commerceSubscriptionCycleEntry.getCompanyId();
	}

	/**
	* Returns the create date of this commerce subscription cycle entry.
	*
	* @return the create date of this commerce subscription cycle entry
	*/
	@Override
	public Date getCreateDate() {
		return _commerceSubscriptionCycleEntry.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _commerceSubscriptionCycleEntry.getExpandoBridge();
	}

	/**
	* Returns the group ID of this commerce subscription cycle entry.
	*
	* @return the group ID of this commerce subscription cycle entry
	*/
	@Override
	public long getGroupId() {
		return _commerceSubscriptionCycleEntry.getGroupId();
	}

	/**
	* Returns the modified date of this commerce subscription cycle entry.
	*
	* @return the modified date of this commerce subscription cycle entry
	*/
	@Override
	public Date getModifiedDate() {
		return _commerceSubscriptionCycleEntry.getModifiedDate();
	}

	/**
	* Returns the primary key of this commerce subscription cycle entry.
	*
	* @return the primary key of this commerce subscription cycle entry
	*/
	@Override
	public long getPrimaryKey() {
		return _commerceSubscriptionCycleEntry.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceSubscriptionCycleEntry.getPrimaryKeyObj();
	}

	/**
	* Returns the renew of this commerce subscription cycle entry.
	*
	* @return the renew of this commerce subscription cycle entry
	*/
	@Override
	public boolean getRenew() {
		return _commerceSubscriptionCycleEntry.getRenew();
	}

	/**
	* Returns the user ID of this commerce subscription cycle entry.
	*
	* @return the user ID of this commerce subscription cycle entry
	*/
	@Override
	public long getUserId() {
		return _commerceSubscriptionCycleEntry.getUserId();
	}

	/**
	* Returns the user name of this commerce subscription cycle entry.
	*
	* @return the user name of this commerce subscription cycle entry
	*/
	@Override
	public String getUserName() {
		return _commerceSubscriptionCycleEntry.getUserName();
	}

	/**
	* Returns the user uuid of this commerce subscription cycle entry.
	*
	* @return the user uuid of this commerce subscription cycle entry
	*/
	@Override
	public String getUserUuid() {
		return _commerceSubscriptionCycleEntry.getUserUuid();
	}

	/**
	* Returns the uuid of this commerce subscription cycle entry.
	*
	* @return the uuid of this commerce subscription cycle entry
	*/
	@Override
	public String getUuid() {
		return _commerceSubscriptionCycleEntry.getUuid();
	}

	@Override
	public int hashCode() {
		return _commerceSubscriptionCycleEntry.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _commerceSubscriptionCycleEntry.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _commerceSubscriptionCycleEntry.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _commerceSubscriptionCycleEntry.isNew();
	}

	/**
	* Returns <code>true</code> if this commerce subscription cycle entry is renew.
	*
	* @return <code>true</code> if this commerce subscription cycle entry is renew; <code>false</code> otherwise
	*/
	@Override
	public boolean isRenew() {
		return _commerceSubscriptionCycleEntry.isRenew();
	}

	@Override
	public void persist() {
		_commerceSubscriptionCycleEntry.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_commerceSubscriptionCycleEntry.setCachedModel(cachedModel);
	}

	/**
	* Sets the commerce order item ID of this commerce subscription cycle entry.
	*
	* @param commerceOrderItemId the commerce order item ID of this commerce subscription cycle entry
	*/
	@Override
	public void setCommerceOrderItemId(long commerceOrderItemId) {
		_commerceSubscriptionCycleEntry.setCommerceOrderItemId(commerceOrderItemId);
	}

	/**
	* Sets the commerce subscription cycle entry ID of this commerce subscription cycle entry.
	*
	* @param commerceSubscriptionCycleEntryId the commerce subscription cycle entry ID of this commerce subscription cycle entry
	*/
	@Override
	public void setCommerceSubscriptionCycleEntryId(
		long commerceSubscriptionCycleEntryId) {
		_commerceSubscriptionCycleEntry.setCommerceSubscriptionCycleEntryId(commerceSubscriptionCycleEntryId);
	}

	/**
	* Sets the commerce subscription entry ID of this commerce subscription cycle entry.
	*
	* @param commerceSubscriptionEntryId the commerce subscription entry ID of this commerce subscription cycle entry
	*/
	@Override
	public void setCommerceSubscriptionEntryId(long commerceSubscriptionEntryId) {
		_commerceSubscriptionCycleEntry.setCommerceSubscriptionEntryId(commerceSubscriptionEntryId);
	}

	/**
	* Sets the company ID of this commerce subscription cycle entry.
	*
	* @param companyId the company ID of this commerce subscription cycle entry
	*/
	@Override
	public void setCompanyId(long companyId) {
		_commerceSubscriptionCycleEntry.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this commerce subscription cycle entry.
	*
	* @param createDate the create date of this commerce subscription cycle entry
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_commerceSubscriptionCycleEntry.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_commerceSubscriptionCycleEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_commerceSubscriptionCycleEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_commerceSubscriptionCycleEntry.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this commerce subscription cycle entry.
	*
	* @param groupId the group ID of this commerce subscription cycle entry
	*/
	@Override
	public void setGroupId(long groupId) {
		_commerceSubscriptionCycleEntry.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this commerce subscription cycle entry.
	*
	* @param modifiedDate the modified date of this commerce subscription cycle entry
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_commerceSubscriptionCycleEntry.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_commerceSubscriptionCycleEntry.setNew(n);
	}

	/**
	* Sets the primary key of this commerce subscription cycle entry.
	*
	* @param primaryKey the primary key of this commerce subscription cycle entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_commerceSubscriptionCycleEntry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_commerceSubscriptionCycleEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this commerce subscription cycle entry is renew.
	*
	* @param renew the renew of this commerce subscription cycle entry
	*/
	@Override
	public void setRenew(boolean renew) {
		_commerceSubscriptionCycleEntry.setRenew(renew);
	}

	/**
	* Sets the user ID of this commerce subscription cycle entry.
	*
	* @param userId the user ID of this commerce subscription cycle entry
	*/
	@Override
	public void setUserId(long userId) {
		_commerceSubscriptionCycleEntry.setUserId(userId);
	}

	/**
	* Sets the user name of this commerce subscription cycle entry.
	*
	* @param userName the user name of this commerce subscription cycle entry
	*/
	@Override
	public void setUserName(String userName) {
		_commerceSubscriptionCycleEntry.setUserName(userName);
	}

	/**
	* Sets the user uuid of this commerce subscription cycle entry.
	*
	* @param userUuid the user uuid of this commerce subscription cycle entry
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_commerceSubscriptionCycleEntry.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this commerce subscription cycle entry.
	*
	* @param uuid the uuid of this commerce subscription cycle entry
	*/
	@Override
	public void setUuid(String uuid) {
		_commerceSubscriptionCycleEntry.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CommerceSubscriptionCycleEntry> toCacheModel() {
		return _commerceSubscriptionCycleEntry.toCacheModel();
	}

	@Override
	public CommerceSubscriptionCycleEntry toEscapedModel() {
		return new CommerceSubscriptionCycleEntryWrapper(_commerceSubscriptionCycleEntry.toEscapedModel());
	}

	@Override
	public String toString() {
		return _commerceSubscriptionCycleEntry.toString();
	}

	@Override
	public CommerceSubscriptionCycleEntry toUnescapedModel() {
		return new CommerceSubscriptionCycleEntryWrapper(_commerceSubscriptionCycleEntry.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _commerceSubscriptionCycleEntry.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceSubscriptionCycleEntryWrapper)) {
			return false;
		}

		CommerceSubscriptionCycleEntryWrapper commerceSubscriptionCycleEntryWrapper =
			(CommerceSubscriptionCycleEntryWrapper)obj;

		if (Objects.equals(_commerceSubscriptionCycleEntry,
					commerceSubscriptionCycleEntryWrapper._commerceSubscriptionCycleEntry)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _commerceSubscriptionCycleEntry.getStagedModelType();
	}

	@Override
	public CommerceSubscriptionCycleEntry getWrappedModel() {
		return _commerceSubscriptionCycleEntry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _commerceSubscriptionCycleEntry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _commerceSubscriptionCycleEntry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_commerceSubscriptionCycleEntry.resetOriginalValues();
	}

	private final CommerceSubscriptionCycleEntry _commerceSubscriptionCycleEntry;
}