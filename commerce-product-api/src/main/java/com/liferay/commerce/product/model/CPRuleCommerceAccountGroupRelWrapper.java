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
 * This class is a wrapper for {@link CPRuleCommerceAccountGroupRel}.
 * </p>
 *
 * @author Marco Leo
 * @see CPRuleCommerceAccountGroupRel
 * @generated
 */
@ProviderType
public class CPRuleCommerceAccountGroupRelWrapper
	implements CPRuleCommerceAccountGroupRel,
		ModelWrapper<CPRuleCommerceAccountGroupRel> {
	public CPRuleCommerceAccountGroupRelWrapper(
		CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel) {
		_cpRuleCommerceAccountGroupRel = cpRuleCommerceAccountGroupRel;
	}

	@Override
	public Class<?> getModelClass() {
		return CPRuleCommerceAccountGroupRel.class;
	}

	@Override
	public String getModelClassName() {
		return CPRuleCommerceAccountGroupRel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("CPRuleCommerceAccountGroupRelId",
			getCPRuleCommerceAccountGroupRelId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("CPRuleId", getCPRuleId());
		attributes.put("commerceAccountGroupId", getCommerceAccountGroupId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long CPRuleCommerceAccountGroupRelId = (Long)attributes.get(
				"CPRuleCommerceAccountGroupRelId");

		if (CPRuleCommerceAccountGroupRelId != null) {
			setCPRuleCommerceAccountGroupRelId(CPRuleCommerceAccountGroupRelId);
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

		Long commerceAccountGroupId = (Long)attributes.get(
				"commerceAccountGroupId");

		if (commerceAccountGroupId != null) {
			setCommerceAccountGroupId(commerceAccountGroupId);
		}
	}

	@Override
	public Object clone() {
		return new CPRuleCommerceAccountGroupRelWrapper((CPRuleCommerceAccountGroupRel)_cpRuleCommerceAccountGroupRel.clone());
	}

	@Override
	public int compareTo(
		CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel) {
		return _cpRuleCommerceAccountGroupRel.compareTo(cpRuleCommerceAccountGroupRel);
	}

	@Override
	public com.liferay.commerce.account.model.CommerceAccountGroup getCommerceAccountGroup()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpRuleCommerceAccountGroupRel.getCommerceAccountGroup();
	}

	/**
	* Returns the commerce account group ID of this cp rule commerce account group rel.
	*
	* @return the commerce account group ID of this cp rule commerce account group rel
	*/
	@Override
	public long getCommerceAccountGroupId() {
		return _cpRuleCommerceAccountGroupRel.getCommerceAccountGroupId();
	}

	/**
	* Returns the company ID of this cp rule commerce account group rel.
	*
	* @return the company ID of this cp rule commerce account group rel
	*/
	@Override
	public long getCompanyId() {
		return _cpRuleCommerceAccountGroupRel.getCompanyId();
	}

	/**
	* Returns the cp rule commerce account group rel ID of this cp rule commerce account group rel.
	*
	* @return the cp rule commerce account group rel ID of this cp rule commerce account group rel
	*/
	@Override
	public long getCPRuleCommerceAccountGroupRelId() {
		return _cpRuleCommerceAccountGroupRel.getCPRuleCommerceAccountGroupRelId();
	}

	/**
	* Returns the cp rule ID of this cp rule commerce account group rel.
	*
	* @return the cp rule ID of this cp rule commerce account group rel
	*/
	@Override
	public long getCPRuleId() {
		return _cpRuleCommerceAccountGroupRel.getCPRuleId();
	}

	/**
	* Returns the create date of this cp rule commerce account group rel.
	*
	* @return the create date of this cp rule commerce account group rel
	*/
	@Override
	public Date getCreateDate() {
		return _cpRuleCommerceAccountGroupRel.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _cpRuleCommerceAccountGroupRel.getExpandoBridge();
	}

	/**
	* Returns the group ID of this cp rule commerce account group rel.
	*
	* @return the group ID of this cp rule commerce account group rel
	*/
	@Override
	public long getGroupId() {
		return _cpRuleCommerceAccountGroupRel.getGroupId();
	}

	/**
	* Returns the modified date of this cp rule commerce account group rel.
	*
	* @return the modified date of this cp rule commerce account group rel
	*/
	@Override
	public Date getModifiedDate() {
		return _cpRuleCommerceAccountGroupRel.getModifiedDate();
	}

	/**
	* Returns the primary key of this cp rule commerce account group rel.
	*
	* @return the primary key of this cp rule commerce account group rel
	*/
	@Override
	public long getPrimaryKey() {
		return _cpRuleCommerceAccountGroupRel.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _cpRuleCommerceAccountGroupRel.getPrimaryKeyObj();
	}

	/**
	* Returns the user ID of this cp rule commerce account group rel.
	*
	* @return the user ID of this cp rule commerce account group rel
	*/
	@Override
	public long getUserId() {
		return _cpRuleCommerceAccountGroupRel.getUserId();
	}

	/**
	* Returns the user name of this cp rule commerce account group rel.
	*
	* @return the user name of this cp rule commerce account group rel
	*/
	@Override
	public String getUserName() {
		return _cpRuleCommerceAccountGroupRel.getUserName();
	}

	/**
	* Returns the user uuid of this cp rule commerce account group rel.
	*
	* @return the user uuid of this cp rule commerce account group rel
	*/
	@Override
	public String getUserUuid() {
		return _cpRuleCommerceAccountGroupRel.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _cpRuleCommerceAccountGroupRel.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _cpRuleCommerceAccountGroupRel.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _cpRuleCommerceAccountGroupRel.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _cpRuleCommerceAccountGroupRel.isNew();
	}

	@Override
	public void persist() {
		_cpRuleCommerceAccountGroupRel.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_cpRuleCommerceAccountGroupRel.setCachedModel(cachedModel);
	}

	/**
	* Sets the commerce account group ID of this cp rule commerce account group rel.
	*
	* @param commerceAccountGroupId the commerce account group ID of this cp rule commerce account group rel
	*/
	@Override
	public void setCommerceAccountGroupId(long commerceAccountGroupId) {
		_cpRuleCommerceAccountGroupRel.setCommerceAccountGroupId(commerceAccountGroupId);
	}

	/**
	* Sets the company ID of this cp rule commerce account group rel.
	*
	* @param companyId the company ID of this cp rule commerce account group rel
	*/
	@Override
	public void setCompanyId(long companyId) {
		_cpRuleCommerceAccountGroupRel.setCompanyId(companyId);
	}

	/**
	* Sets the cp rule commerce account group rel ID of this cp rule commerce account group rel.
	*
	* @param CPRuleCommerceAccountGroupRelId the cp rule commerce account group rel ID of this cp rule commerce account group rel
	*/
	@Override
	public void setCPRuleCommerceAccountGroupRelId(
		long CPRuleCommerceAccountGroupRelId) {
		_cpRuleCommerceAccountGroupRel.setCPRuleCommerceAccountGroupRelId(CPRuleCommerceAccountGroupRelId);
	}

	/**
	* Sets the cp rule ID of this cp rule commerce account group rel.
	*
	* @param CPRuleId the cp rule ID of this cp rule commerce account group rel
	*/
	@Override
	public void setCPRuleId(long CPRuleId) {
		_cpRuleCommerceAccountGroupRel.setCPRuleId(CPRuleId);
	}

	/**
	* Sets the create date of this cp rule commerce account group rel.
	*
	* @param createDate the create date of this cp rule commerce account group rel
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_cpRuleCommerceAccountGroupRel.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_cpRuleCommerceAccountGroupRel.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_cpRuleCommerceAccountGroupRel.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_cpRuleCommerceAccountGroupRel.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this cp rule commerce account group rel.
	*
	* @param groupId the group ID of this cp rule commerce account group rel
	*/
	@Override
	public void setGroupId(long groupId) {
		_cpRuleCommerceAccountGroupRel.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this cp rule commerce account group rel.
	*
	* @param modifiedDate the modified date of this cp rule commerce account group rel
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_cpRuleCommerceAccountGroupRel.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_cpRuleCommerceAccountGroupRel.setNew(n);
	}

	/**
	* Sets the primary key of this cp rule commerce account group rel.
	*
	* @param primaryKey the primary key of this cp rule commerce account group rel
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_cpRuleCommerceAccountGroupRel.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_cpRuleCommerceAccountGroupRel.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this cp rule commerce account group rel.
	*
	* @param userId the user ID of this cp rule commerce account group rel
	*/
	@Override
	public void setUserId(long userId) {
		_cpRuleCommerceAccountGroupRel.setUserId(userId);
	}

	/**
	* Sets the user name of this cp rule commerce account group rel.
	*
	* @param userName the user name of this cp rule commerce account group rel
	*/
	@Override
	public void setUserName(String userName) {
		_cpRuleCommerceAccountGroupRel.setUserName(userName);
	}

	/**
	* Sets the user uuid of this cp rule commerce account group rel.
	*
	* @param userUuid the user uuid of this cp rule commerce account group rel
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_cpRuleCommerceAccountGroupRel.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CPRuleCommerceAccountGroupRel> toCacheModel() {
		return _cpRuleCommerceAccountGroupRel.toCacheModel();
	}

	@Override
	public CPRuleCommerceAccountGroupRel toEscapedModel() {
		return new CPRuleCommerceAccountGroupRelWrapper(_cpRuleCommerceAccountGroupRel.toEscapedModel());
	}

	@Override
	public String toString() {
		return _cpRuleCommerceAccountGroupRel.toString();
	}

	@Override
	public CPRuleCommerceAccountGroupRel toUnescapedModel() {
		return new CPRuleCommerceAccountGroupRelWrapper(_cpRuleCommerceAccountGroupRel.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _cpRuleCommerceAccountGroupRel.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CPRuleCommerceAccountGroupRelWrapper)) {
			return false;
		}

		CPRuleCommerceAccountGroupRelWrapper cpRuleCommerceAccountGroupRelWrapper =
			(CPRuleCommerceAccountGroupRelWrapper)obj;

		if (Objects.equals(_cpRuleCommerceAccountGroupRel,
					cpRuleCommerceAccountGroupRelWrapper._cpRuleCommerceAccountGroupRel)) {
			return true;
		}

		return false;
	}

	@Override
	public CPRuleCommerceAccountGroupRel getWrappedModel() {
		return _cpRuleCommerceAccountGroupRel;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _cpRuleCommerceAccountGroupRel.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _cpRuleCommerceAccountGroupRel.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_cpRuleCommerceAccountGroupRel.resetOriginalValues();
	}

	private final CPRuleCommerceAccountGroupRel _cpRuleCommerceAccountGroupRel;
}