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
 * This class is a wrapper for {@link CPDefinitionLink}.
 * </p>
 *
 * @author Marco Leo
 * @see CPDefinitionLink
 * @generated
 */
public class CPDefinitionLinkWrapper
	implements CPDefinitionLink, ModelWrapper<CPDefinitionLink> {

	public CPDefinitionLinkWrapper(CPDefinitionLink cpDefinitionLink) {
		_cpDefinitionLink = cpDefinitionLink;
	}

	@Override
	public Class<?> getModelClass() {
		return CPDefinitionLink.class;
	}

	@Override
	public String getModelClassName() {
		return CPDefinitionLink.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("CPDefinitionLinkId", getCPDefinitionLinkId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("CPDefinitionId", getCPDefinitionId());
		attributes.put("CProductId", getCProductId());
		attributes.put("priority", getPriority());
		attributes.put("type", getType());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long CPDefinitionLinkId = (Long)attributes.get("CPDefinitionLinkId");

		if (CPDefinitionLinkId != null) {
			setCPDefinitionLinkId(CPDefinitionLinkId);
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

		Long CPDefinitionId = (Long)attributes.get("CPDefinitionId");

		if (CPDefinitionId != null) {
			setCPDefinitionId(CPDefinitionId);
		}

		Long CProductId = (Long)attributes.get("CProductId");

		if (CProductId != null) {
			setCProductId(CProductId);
		}

		Double priority = (Double)attributes.get("priority");

		if (priority != null) {
			setPriority(priority);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}
	}

	@Override
	public Object clone() {
		return new CPDefinitionLinkWrapper(
			(CPDefinitionLink)_cpDefinitionLink.clone());
	}

	@Override
	public int compareTo(CPDefinitionLink cpDefinitionLink) {
		return _cpDefinitionLink.compareTo(cpDefinitionLink);
	}

	/**
	 * Returns the company ID of this cp definition link.
	 *
	 * @return the company ID of this cp definition link
	 */
	@Override
	public long getCompanyId() {
		return _cpDefinitionLink.getCompanyId();
	}

	@Override
	public CPDefinition getCPDefinition() {
		return _cpDefinitionLink.getCPDefinition();
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	@Override
	public CPDefinition getCPDefinition1() {
		return _cpDefinitionLink.getCPDefinition1();
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	@Override
	public CPDefinition getCPDefinition2() {
		return _cpDefinitionLink.getCPDefinition2();
	}

	/**
	 * Returns the cp definition ID of this cp definition link.
	 *
	 * @return the cp definition ID of this cp definition link
	 */
	@Override
	public long getCPDefinitionId() {
		return _cpDefinitionLink.getCPDefinitionId();
	}

	/**
	 * Returns the cp definition link ID of this cp definition link.
	 *
	 * @return the cp definition link ID of this cp definition link
	 */
	@Override
	public long getCPDefinitionLinkId() {
		return _cpDefinitionLink.getCPDefinitionLinkId();
	}

	@Override
	public CProduct getCProduct() {
		return _cpDefinitionLink.getCProduct();
	}

	/**
	 * Returns the c product ID of this cp definition link.
	 *
	 * @return the c product ID of this cp definition link
	 */
	@Override
	public long getCProductId() {
		return _cpDefinitionLink.getCProductId();
	}

	/**
	 * Returns the create date of this cp definition link.
	 *
	 * @return the create date of this cp definition link
	 */
	@Override
	public Date getCreateDate() {
		return _cpDefinitionLink.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _cpDefinitionLink.getExpandoBridge();
	}

	/**
	 * Returns the group ID of this cp definition link.
	 *
	 * @return the group ID of this cp definition link
	 */
	@Override
	public long getGroupId() {
		return _cpDefinitionLink.getGroupId();
	}

	/**
	 * Returns the modified date of this cp definition link.
	 *
	 * @return the modified date of this cp definition link
	 */
	@Override
	public Date getModifiedDate() {
		return _cpDefinitionLink.getModifiedDate();
	}

	/**
	 * Returns the primary key of this cp definition link.
	 *
	 * @return the primary key of this cp definition link
	 */
	@Override
	public long getPrimaryKey() {
		return _cpDefinitionLink.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _cpDefinitionLink.getPrimaryKeyObj();
	}

	/**
	 * Returns the priority of this cp definition link.
	 *
	 * @return the priority of this cp definition link
	 */
	@Override
	public double getPriority() {
		return _cpDefinitionLink.getPriority();
	}

	/**
	 * Returns the type of this cp definition link.
	 *
	 * @return the type of this cp definition link
	 */
	@Override
	public String getType() {
		return _cpDefinitionLink.getType();
	}

	/**
	 * Returns the user ID of this cp definition link.
	 *
	 * @return the user ID of this cp definition link
	 */
	@Override
	public long getUserId() {
		return _cpDefinitionLink.getUserId();
	}

	/**
	 * Returns the user name of this cp definition link.
	 *
	 * @return the user name of this cp definition link
	 */
	@Override
	public String getUserName() {
		return _cpDefinitionLink.getUserName();
	}

	/**
	 * Returns the user uuid of this cp definition link.
	 *
	 * @return the user uuid of this cp definition link
	 */
	@Override
	public String getUserUuid() {
		return _cpDefinitionLink.getUserUuid();
	}

	/**
	 * Returns the uuid of this cp definition link.
	 *
	 * @return the uuid of this cp definition link
	 */
	@Override
	public String getUuid() {
		return _cpDefinitionLink.getUuid();
	}

	@Override
	public int hashCode() {
		return _cpDefinitionLink.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _cpDefinitionLink.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _cpDefinitionLink.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _cpDefinitionLink.isNew();
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a cp definition link model instance should use the <code>CPDefinitionLink</code> interface instead.
	 */
	@Override
	public void persist() {
		_cpDefinitionLink.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_cpDefinitionLink.setCachedModel(cachedModel);
	}

	/**
	 * Sets the company ID of this cp definition link.
	 *
	 * @param companyId the company ID of this cp definition link
	 */
	@Override
	public void setCompanyId(long companyId) {
		_cpDefinitionLink.setCompanyId(companyId);
	}

	/**
	 * Sets the cp definition ID of this cp definition link.
	 *
	 * @param CPDefinitionId the cp definition ID of this cp definition link
	 */
	@Override
	public void setCPDefinitionId(long CPDefinitionId) {
		_cpDefinitionLink.setCPDefinitionId(CPDefinitionId);
	}

	/**
	 * Sets the cp definition link ID of this cp definition link.
	 *
	 * @param CPDefinitionLinkId the cp definition link ID of this cp definition link
	 */
	@Override
	public void setCPDefinitionLinkId(long CPDefinitionLinkId) {
		_cpDefinitionLink.setCPDefinitionLinkId(CPDefinitionLinkId);
	}

	/**
	 * Sets the c product ID of this cp definition link.
	 *
	 * @param CProductId the c product ID of this cp definition link
	 */
	@Override
	public void setCProductId(long CProductId) {
		_cpDefinitionLink.setCProductId(CProductId);
	}

	/**
	 * Sets the create date of this cp definition link.
	 *
	 * @param createDate the create date of this cp definition link
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_cpDefinitionLink.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_cpDefinitionLink.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_cpDefinitionLink.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_cpDefinitionLink.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the group ID of this cp definition link.
	 *
	 * @param groupId the group ID of this cp definition link
	 */
	@Override
	public void setGroupId(long groupId) {
		_cpDefinitionLink.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this cp definition link.
	 *
	 * @param modifiedDate the modified date of this cp definition link
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_cpDefinitionLink.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_cpDefinitionLink.setNew(n);
	}

	/**
	 * Sets the primary key of this cp definition link.
	 *
	 * @param primaryKey the primary key of this cp definition link
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_cpDefinitionLink.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_cpDefinitionLink.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the priority of this cp definition link.
	 *
	 * @param priority the priority of this cp definition link
	 */
	@Override
	public void setPriority(double priority) {
		_cpDefinitionLink.setPriority(priority);
	}

	/**
	 * Sets the type of this cp definition link.
	 *
	 * @param type the type of this cp definition link
	 */
	@Override
	public void setType(String type) {
		_cpDefinitionLink.setType(type);
	}

	/**
	 * Sets the user ID of this cp definition link.
	 *
	 * @param userId the user ID of this cp definition link
	 */
	@Override
	public void setUserId(long userId) {
		_cpDefinitionLink.setUserId(userId);
	}

	/**
	 * Sets the user name of this cp definition link.
	 *
	 * @param userName the user name of this cp definition link
	 */
	@Override
	public void setUserName(String userName) {
		_cpDefinitionLink.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this cp definition link.
	 *
	 * @param userUuid the user uuid of this cp definition link
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_cpDefinitionLink.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this cp definition link.
	 *
	 * @param uuid the uuid of this cp definition link
	 */
	@Override
	public void setUuid(String uuid) {
		_cpDefinitionLink.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CPDefinitionLink>
		toCacheModel() {

		return _cpDefinitionLink.toCacheModel();
	}

	@Override
	public CPDefinitionLink toEscapedModel() {
		return new CPDefinitionLinkWrapper(_cpDefinitionLink.toEscapedModel());
	}

	@Override
	public String toString() {
		return _cpDefinitionLink.toString();
	}

	@Override
	public CPDefinitionLink toUnescapedModel() {
		return new CPDefinitionLinkWrapper(
			_cpDefinitionLink.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _cpDefinitionLink.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CPDefinitionLinkWrapper)) {
			return false;
		}

		CPDefinitionLinkWrapper cpDefinitionLinkWrapper =
			(CPDefinitionLinkWrapper)obj;

		if (Objects.equals(
				_cpDefinitionLink, cpDefinitionLinkWrapper._cpDefinitionLink)) {

			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _cpDefinitionLink.getStagedModelType();
	}

	@Override
	public CPDefinitionLink getWrappedModel() {
		return _cpDefinitionLink;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _cpDefinitionLink.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _cpDefinitionLink.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_cpDefinitionLink.resetOriginalValues();
	}

	private final CPDefinitionLink _cpDefinitionLink;

}