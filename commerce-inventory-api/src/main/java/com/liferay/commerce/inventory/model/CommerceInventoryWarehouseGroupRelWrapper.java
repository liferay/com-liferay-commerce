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

package com.liferay.commerce.inventory.model;

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
 * This class is a wrapper for {@link CommerceInventoryWarehouseGroupRel}.
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryWarehouseGroupRel
 * @generated
 */
@ProviderType
public class CommerceInventoryWarehouseGroupRelWrapper
	implements CommerceInventoryWarehouseGroupRel,
		ModelWrapper<CommerceInventoryWarehouseGroupRel> {
	public CommerceInventoryWarehouseGroupRelWrapper(
		CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel) {
		_commerceInventoryWarehouseGroupRel = commerceInventoryWarehouseGroupRel;
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceInventoryWarehouseGroupRel.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceInventoryWarehouseGroupRel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("commerceInventoryWarehouseGroupRelId",
			getCommerceInventoryWarehouseGroupRelId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("commerceWarehouseId", getCommerceWarehouseId());
		attributes.put("primary", isPrimary());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long commerceInventoryWarehouseGroupRelId = (Long)attributes.get(
				"commerceInventoryWarehouseGroupRelId");

		if (commerceInventoryWarehouseGroupRelId != null) {
			setCommerceInventoryWarehouseGroupRelId(commerceInventoryWarehouseGroupRelId);
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

		Long commerceWarehouseId = (Long)attributes.get("commerceWarehouseId");

		if (commerceWarehouseId != null) {
			setCommerceWarehouseId(commerceWarehouseId);
		}

		Boolean primary = (Boolean)attributes.get("primary");

		if (primary != null) {
			setPrimary(primary);
		}
	}

	@Override
	public Object clone() {
		return new CommerceInventoryWarehouseGroupRelWrapper((CommerceInventoryWarehouseGroupRel)_commerceInventoryWarehouseGroupRel.clone());
	}

	@Override
	public int compareTo(
		CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel) {
		return _commerceInventoryWarehouseGroupRel.compareTo(commerceInventoryWarehouseGroupRel);
	}

	/**
	* Returns the commerce inventory warehouse group rel ID of this commerce inventory warehouse group rel.
	*
	* @return the commerce inventory warehouse group rel ID of this commerce inventory warehouse group rel
	*/
	@Override
	public long getCommerceInventoryWarehouseGroupRelId() {
		return _commerceInventoryWarehouseGroupRel.getCommerceInventoryWarehouseGroupRelId();
	}

	/**
	* Returns the commerce warehouse ID of this commerce inventory warehouse group rel.
	*
	* @return the commerce warehouse ID of this commerce inventory warehouse group rel
	*/
	@Override
	public long getCommerceWarehouseId() {
		return _commerceInventoryWarehouseGroupRel.getCommerceWarehouseId();
	}

	/**
	* Returns the company ID of this commerce inventory warehouse group rel.
	*
	* @return the company ID of this commerce inventory warehouse group rel
	*/
	@Override
	public long getCompanyId() {
		return _commerceInventoryWarehouseGroupRel.getCompanyId();
	}

	/**
	* Returns the create date of this commerce inventory warehouse group rel.
	*
	* @return the create date of this commerce inventory warehouse group rel
	*/
	@Override
	public Date getCreateDate() {
		return _commerceInventoryWarehouseGroupRel.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _commerceInventoryWarehouseGroupRel.getExpandoBridge();
	}

	/**
	* Returns the group ID of this commerce inventory warehouse group rel.
	*
	* @return the group ID of this commerce inventory warehouse group rel
	*/
	@Override
	public long getGroupId() {
		return _commerceInventoryWarehouseGroupRel.getGroupId();
	}

	/**
	* Returns the modified date of this commerce inventory warehouse group rel.
	*
	* @return the modified date of this commerce inventory warehouse group rel
	*/
	@Override
	public Date getModifiedDate() {
		return _commerceInventoryWarehouseGroupRel.getModifiedDate();
	}

	/**
	* Returns the primary of this commerce inventory warehouse group rel.
	*
	* @return the primary of this commerce inventory warehouse group rel
	*/
	@Override
	public boolean getPrimary() {
		return _commerceInventoryWarehouseGroupRel.getPrimary();
	}

	/**
	* Returns the primary key of this commerce inventory warehouse group rel.
	*
	* @return the primary key of this commerce inventory warehouse group rel
	*/
	@Override
	public long getPrimaryKey() {
		return _commerceInventoryWarehouseGroupRel.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceInventoryWarehouseGroupRel.getPrimaryKeyObj();
	}

	/**
	* Returns the user ID of this commerce inventory warehouse group rel.
	*
	* @return the user ID of this commerce inventory warehouse group rel
	*/
	@Override
	public long getUserId() {
		return _commerceInventoryWarehouseGroupRel.getUserId();
	}

	/**
	* Returns the user name of this commerce inventory warehouse group rel.
	*
	* @return the user name of this commerce inventory warehouse group rel
	*/
	@Override
	public String getUserName() {
		return _commerceInventoryWarehouseGroupRel.getUserName();
	}

	/**
	* Returns the user uuid of this commerce inventory warehouse group rel.
	*
	* @return the user uuid of this commerce inventory warehouse group rel
	*/
	@Override
	public String getUserUuid() {
		return _commerceInventoryWarehouseGroupRel.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _commerceInventoryWarehouseGroupRel.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _commerceInventoryWarehouseGroupRel.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _commerceInventoryWarehouseGroupRel.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _commerceInventoryWarehouseGroupRel.isNew();
	}

	/**
	* Returns <code>true</code> if this commerce inventory warehouse group rel is primary.
	*
	* @return <code>true</code> if this commerce inventory warehouse group rel is primary; <code>false</code> otherwise
	*/
	@Override
	public boolean isPrimary() {
		return _commerceInventoryWarehouseGroupRel.isPrimary();
	}

	@Override
	public void persist() {
		_commerceInventoryWarehouseGroupRel.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_commerceInventoryWarehouseGroupRel.setCachedModel(cachedModel);
	}

	/**
	* Sets the commerce inventory warehouse group rel ID of this commerce inventory warehouse group rel.
	*
	* @param commerceInventoryWarehouseGroupRelId the commerce inventory warehouse group rel ID of this commerce inventory warehouse group rel
	*/
	@Override
	public void setCommerceInventoryWarehouseGroupRelId(
		long commerceInventoryWarehouseGroupRelId) {
		_commerceInventoryWarehouseGroupRel.setCommerceInventoryWarehouseGroupRelId(commerceInventoryWarehouseGroupRelId);
	}

	/**
	* Sets the commerce warehouse ID of this commerce inventory warehouse group rel.
	*
	* @param commerceWarehouseId the commerce warehouse ID of this commerce inventory warehouse group rel
	*/
	@Override
	public void setCommerceWarehouseId(long commerceWarehouseId) {
		_commerceInventoryWarehouseGroupRel.setCommerceWarehouseId(commerceWarehouseId);
	}

	/**
	* Sets the company ID of this commerce inventory warehouse group rel.
	*
	* @param companyId the company ID of this commerce inventory warehouse group rel
	*/
	@Override
	public void setCompanyId(long companyId) {
		_commerceInventoryWarehouseGroupRel.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this commerce inventory warehouse group rel.
	*
	* @param createDate the create date of this commerce inventory warehouse group rel
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_commerceInventoryWarehouseGroupRel.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_commerceInventoryWarehouseGroupRel.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_commerceInventoryWarehouseGroupRel.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_commerceInventoryWarehouseGroupRel.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this commerce inventory warehouse group rel.
	*
	* @param groupId the group ID of this commerce inventory warehouse group rel
	*/
	@Override
	public void setGroupId(long groupId) {
		_commerceInventoryWarehouseGroupRel.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this commerce inventory warehouse group rel.
	*
	* @param modifiedDate the modified date of this commerce inventory warehouse group rel
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_commerceInventoryWarehouseGroupRel.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_commerceInventoryWarehouseGroupRel.setNew(n);
	}

	/**
	* Sets whether this commerce inventory warehouse group rel is primary.
	*
	* @param primary the primary of this commerce inventory warehouse group rel
	*/
	@Override
	public void setPrimary(boolean primary) {
		_commerceInventoryWarehouseGroupRel.setPrimary(primary);
	}

	/**
	* Sets the primary key of this commerce inventory warehouse group rel.
	*
	* @param primaryKey the primary key of this commerce inventory warehouse group rel
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_commerceInventoryWarehouseGroupRel.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_commerceInventoryWarehouseGroupRel.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this commerce inventory warehouse group rel.
	*
	* @param userId the user ID of this commerce inventory warehouse group rel
	*/
	@Override
	public void setUserId(long userId) {
		_commerceInventoryWarehouseGroupRel.setUserId(userId);
	}

	/**
	* Sets the user name of this commerce inventory warehouse group rel.
	*
	* @param userName the user name of this commerce inventory warehouse group rel
	*/
	@Override
	public void setUserName(String userName) {
		_commerceInventoryWarehouseGroupRel.setUserName(userName);
	}

	/**
	* Sets the user uuid of this commerce inventory warehouse group rel.
	*
	* @param userUuid the user uuid of this commerce inventory warehouse group rel
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_commerceInventoryWarehouseGroupRel.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CommerceInventoryWarehouseGroupRel> toCacheModel() {
		return _commerceInventoryWarehouseGroupRel.toCacheModel();
	}

	@Override
	public CommerceInventoryWarehouseGroupRel toEscapedModel() {
		return new CommerceInventoryWarehouseGroupRelWrapper(_commerceInventoryWarehouseGroupRel.toEscapedModel());
	}

	@Override
	public String toString() {
		return _commerceInventoryWarehouseGroupRel.toString();
	}

	@Override
	public CommerceInventoryWarehouseGroupRel toUnescapedModel() {
		return new CommerceInventoryWarehouseGroupRelWrapper(_commerceInventoryWarehouseGroupRel.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _commerceInventoryWarehouseGroupRel.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceInventoryWarehouseGroupRelWrapper)) {
			return false;
		}

		CommerceInventoryWarehouseGroupRelWrapper commerceInventoryWarehouseGroupRelWrapper =
			(CommerceInventoryWarehouseGroupRelWrapper)obj;

		if (Objects.equals(_commerceInventoryWarehouseGroupRel,
					commerceInventoryWarehouseGroupRelWrapper._commerceInventoryWarehouseGroupRel)) {
			return true;
		}

		return false;
	}

	@Override
	public CommerceInventoryWarehouseGroupRel getWrappedModel() {
		return _commerceInventoryWarehouseGroupRel;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _commerceInventoryWarehouseGroupRel.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _commerceInventoryWarehouseGroupRel.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_commerceInventoryWarehouseGroupRel.resetOriginalValues();
	}

	private final CommerceInventoryWarehouseGroupRel _commerceInventoryWarehouseGroupRel;
}