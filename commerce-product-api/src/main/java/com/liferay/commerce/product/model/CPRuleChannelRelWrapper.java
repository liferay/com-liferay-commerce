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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link CPRuleChannelRel}.
 * </p>
 *
 * @author Marco Leo
 * @see CPRuleChannelRel
 * @generated
 */
@ProviderType
public class CPRuleChannelRelWrapper implements CPRuleChannelRel,
	ModelWrapper<CPRuleChannelRel> {
	public CPRuleChannelRelWrapper(CPRuleChannelRel cpRuleChannelRel) {
		_cpRuleChannelRel = cpRuleChannelRel;
	}

	@Override
	public Class<?> getModelClass() {
		return CPRuleChannelRel.class;
	}

	@Override
	public String getModelClassName() {
		return CPRuleChannelRel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("CPRuleChannelRelId", getCPRuleChannelRelId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("CPRuleId", getCPRuleId());
		attributes.put("commerceChannelId", getCommerceChannelId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long CPRuleChannelRelId = (Long)attributes.get("CPRuleChannelRelId");

		if (CPRuleChannelRelId != null) {
			setCPRuleChannelRelId(CPRuleChannelRelId);
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

		Long CPRuleId = (Long)attributes.get("CPRuleId");

		if (CPRuleId != null) {
			setCPRuleId(CPRuleId);
		}

		Long commerceChannelId = (Long)attributes.get("commerceChannelId");

		if (commerceChannelId != null) {
			setCommerceChannelId(commerceChannelId);
		}
	}

	@Override
	public Object clone() {
		return new CPRuleChannelRelWrapper((CPRuleChannelRel)_cpRuleChannelRel.clone());
	}

	@Override
	public int compareTo(CPRuleChannelRel cpRuleChannelRel) {
		return _cpRuleChannelRel.compareTo(cpRuleChannelRel);
	}

	/**
	* Returns the commerce channel ID of this cp rule channel rel.
	*
	* @return the commerce channel ID of this cp rule channel rel
	*/
	@Override
	public long getCommerceChannelId() {
		return _cpRuleChannelRel.getCommerceChannelId();
	}

	/**
	* Returns the company ID of this cp rule channel rel.
	*
	* @return the company ID of this cp rule channel rel
	*/
	@Override
	public long getCompanyId() {
		return _cpRuleChannelRel.getCompanyId();
	}

	/**
	* Returns the cp rule channel rel ID of this cp rule channel rel.
	*
	* @return the cp rule channel rel ID of this cp rule channel rel
	*/
	@Override
	public long getCPRuleChannelRelId() {
		return _cpRuleChannelRel.getCPRuleChannelRelId();
	}

	/**
	* Returns the cp rule ID of this cp rule channel rel.
	*
	* @return the cp rule ID of this cp rule channel rel
	*/
	@Override
	public long getCPRuleId() {
		return _cpRuleChannelRel.getCPRuleId();
	}

	/**
	* Returns the create date of this cp rule channel rel.
	*
	* @return the create date of this cp rule channel rel
	*/
	@Override
	public Date getCreateDate() {
		return _cpRuleChannelRel.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _cpRuleChannelRel.getExpandoBridge();
	}

	/**
	* Returns the group ID of this cp rule channel rel.
	*
	* @return the group ID of this cp rule channel rel
	*/
	@Override
	public long getGroupId() {
		return _cpRuleChannelRel.getGroupId();
	}

	/**
	* Returns the modified date of this cp rule channel rel.
	*
	* @return the modified date of this cp rule channel rel
	*/
	@Override
	public Date getModifiedDate() {
		return _cpRuleChannelRel.getModifiedDate();
	}

	/**
	* Returns the primary key of this cp rule channel rel.
	*
	* @return the primary key of this cp rule channel rel
	*/
	@Override
	public long getPrimaryKey() {
		return _cpRuleChannelRel.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _cpRuleChannelRel.getPrimaryKeyObj();
	}

	/**
	* Returns the user ID of this cp rule channel rel.
	*
	* @return the user ID of this cp rule channel rel
	*/
	@Override
	public long getUserId() {
		return _cpRuleChannelRel.getUserId();
	}

	/**
	* Returns the user name of this cp rule channel rel.
	*
	* @return the user name of this cp rule channel rel
	*/
	@Override
	public String getUserName() {
		return _cpRuleChannelRel.getUserName();
	}

	/**
	* Returns the user uuid of this cp rule channel rel.
	*
	* @return the user uuid of this cp rule channel rel
	*/
	@Override
	public String getUserUuid() {
		return _cpRuleChannelRel.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _cpRuleChannelRel.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _cpRuleChannelRel.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _cpRuleChannelRel.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _cpRuleChannelRel.isNew();
	}

	@Override
	public void persist() {
		_cpRuleChannelRel.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_cpRuleChannelRel.setCachedModel(cachedModel);
	}

	/**
	* Sets the commerce channel ID of this cp rule channel rel.
	*
	* @param commerceChannelId the commerce channel ID of this cp rule channel rel
	*/
	@Override
	public void setCommerceChannelId(long commerceChannelId) {
		_cpRuleChannelRel.setCommerceChannelId(commerceChannelId);
	}

	/**
	* Sets the company ID of this cp rule channel rel.
	*
	* @param companyId the company ID of this cp rule channel rel
	*/
	@Override
	public void setCompanyId(long companyId) {
		_cpRuleChannelRel.setCompanyId(companyId);
	}

	/**
	* Sets the cp rule channel rel ID of this cp rule channel rel.
	*
	* @param CPRuleChannelRelId the cp rule channel rel ID of this cp rule channel rel
	*/
	@Override
	public void setCPRuleChannelRelId(long CPRuleChannelRelId) {
		_cpRuleChannelRel.setCPRuleChannelRelId(CPRuleChannelRelId);
	}

	/**
	* Sets the cp rule ID of this cp rule channel rel.
	*
	* @param CPRuleId the cp rule ID of this cp rule channel rel
	*/
	@Override
	public void setCPRuleId(long CPRuleId) {
		_cpRuleChannelRel.setCPRuleId(CPRuleId);
	}

	/**
	* Sets the create date of this cp rule channel rel.
	*
	* @param createDate the create date of this cp rule channel rel
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_cpRuleChannelRel.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_cpRuleChannelRel.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_cpRuleChannelRel.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_cpRuleChannelRel.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this cp rule channel rel.
	*
	* @param groupId the group ID of this cp rule channel rel
	*/
	@Override
	public void setGroupId(long groupId) {
		_cpRuleChannelRel.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this cp rule channel rel.
	*
	* @param modifiedDate the modified date of this cp rule channel rel
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_cpRuleChannelRel.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_cpRuleChannelRel.setNew(n);
	}

	/**
	* Sets the primary key of this cp rule channel rel.
	*
	* @param primaryKey the primary key of this cp rule channel rel
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_cpRuleChannelRel.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_cpRuleChannelRel.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this cp rule channel rel.
	*
	* @param userId the user ID of this cp rule channel rel
	*/
	@Override
	public void setUserId(long userId) {
		_cpRuleChannelRel.setUserId(userId);
	}

	/**
	* Sets the user name of this cp rule channel rel.
	*
	* @param userName the user name of this cp rule channel rel
	*/
	@Override
	public void setUserName(String userName) {
		_cpRuleChannelRel.setUserName(userName);
	}

	/**
	* Sets the user uuid of this cp rule channel rel.
	*
	* @param userUuid the user uuid of this cp rule channel rel
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_cpRuleChannelRel.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CPRuleChannelRel> toCacheModel() {
		return _cpRuleChannelRel.toCacheModel();
	}

	@Override
	public CPRuleChannelRel toEscapedModel() {
		return new CPRuleChannelRelWrapper(_cpRuleChannelRel.toEscapedModel());
	}

	@Override
	public String toString() {
		return _cpRuleChannelRel.toString();
	}

	@Override
	public CPRuleChannelRel toUnescapedModel() {
		return new CPRuleChannelRelWrapper(_cpRuleChannelRel.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _cpRuleChannelRel.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CPRuleChannelRelWrapper)) {
			return false;
		}

		CPRuleChannelRelWrapper cpRuleChannelRelWrapper = (CPRuleChannelRelWrapper)obj;

		if (Objects.equals(_cpRuleChannelRel,
					cpRuleChannelRelWrapper._cpRuleChannelRel)) {
			return true;
		}

		return false;
	}

	@Override
	public CPRuleChannelRel getWrappedModel() {
		return _cpRuleChannelRel;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _cpRuleChannelRel.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _cpRuleChannelRel.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_cpRuleChannelRel.resetOriginalValues();
	}

	private final CPRuleChannelRel _cpRuleChannelRel;
}