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
 * This class is a wrapper for {@link CommerceChannelFilter}.
 * </p>
 *
 * @author Marco Leo
 * @see CommerceChannelFilter
 * @generated
 */
@ProviderType
public class CommerceChannelFilterWrapper implements CommerceChannelFilter,
	ModelWrapper<CommerceChannelFilter> {
	public CommerceChannelFilterWrapper(
		CommerceChannelFilter commerceChannelFilter) {
		_commerceChannelFilter = commerceChannelFilter;
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceChannelFilter.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceChannelFilter.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("commerceChannelFilterId", getCommerceChannelFilterId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("commerceChannelId", getCommerceChannelId());
		attributes.put("type", getType());
		attributes.put("typeSettings", getTypeSettings());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long commerceChannelFilterId = (Long)attributes.get(
				"commerceChannelFilterId");

		if (commerceChannelFilterId != null) {
			setCommerceChannelFilterId(commerceChannelFilterId);
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

		Long commerceChannelId = (Long)attributes.get("commerceChannelId");

		if (commerceChannelId != null) {
			setCommerceChannelId(commerceChannelId);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String typeSettings = (String)attributes.get("typeSettings");

		if (typeSettings != null) {
			setTypeSettings(typeSettings);
		}
	}

	@Override
	public Object clone() {
		return new CommerceChannelFilterWrapper((CommerceChannelFilter)_commerceChannelFilter.clone());
	}

	@Override
	public int compareTo(CommerceChannelFilter commerceChannelFilter) {
		return _commerceChannelFilter.compareTo(commerceChannelFilter);
	}

	/**
	* Returns the commerce channel filter ID of this commerce channel filter.
	*
	* @return the commerce channel filter ID of this commerce channel filter
	*/
	@Override
	public long getCommerceChannelFilterId() {
		return _commerceChannelFilter.getCommerceChannelFilterId();
	}

	/**
	* Returns the commerce channel ID of this commerce channel filter.
	*
	* @return the commerce channel ID of this commerce channel filter
	*/
	@Override
	public long getCommerceChannelId() {
		return _commerceChannelFilter.getCommerceChannelId();
	}

	/**
	* Returns the company ID of this commerce channel filter.
	*
	* @return the company ID of this commerce channel filter
	*/
	@Override
	public long getCompanyId() {
		return _commerceChannelFilter.getCompanyId();
	}

	/**
	* Returns the create date of this commerce channel filter.
	*
	* @return the create date of this commerce channel filter
	*/
	@Override
	public Date getCreateDate() {
		return _commerceChannelFilter.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _commerceChannelFilter.getExpandoBridge();
	}

	/**
	* Returns the modified date of this commerce channel filter.
	*
	* @return the modified date of this commerce channel filter
	*/
	@Override
	public Date getModifiedDate() {
		return _commerceChannelFilter.getModifiedDate();
	}

	/**
	* Returns the primary key of this commerce channel filter.
	*
	* @return the primary key of this commerce channel filter
	*/
	@Override
	public long getPrimaryKey() {
		return _commerceChannelFilter.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceChannelFilter.getPrimaryKeyObj();
	}

	/**
	* Returns the type of this commerce channel filter.
	*
	* @return the type of this commerce channel filter
	*/
	@Override
	public String getType() {
		return _commerceChannelFilter.getType();
	}

	/**
	* Returns the type settings of this commerce channel filter.
	*
	* @return the type settings of this commerce channel filter
	*/
	@Override
	public String getTypeSettings() {
		return _commerceChannelFilter.getTypeSettings();
	}

	/**
	* Returns the user ID of this commerce channel filter.
	*
	* @return the user ID of this commerce channel filter
	*/
	@Override
	public long getUserId() {
		return _commerceChannelFilter.getUserId();
	}

	/**
	* Returns the user name of this commerce channel filter.
	*
	* @return the user name of this commerce channel filter
	*/
	@Override
	public String getUserName() {
		return _commerceChannelFilter.getUserName();
	}

	/**
	* Returns the user uuid of this commerce channel filter.
	*
	* @return the user uuid of this commerce channel filter
	*/
	@Override
	public String getUserUuid() {
		return _commerceChannelFilter.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _commerceChannelFilter.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _commerceChannelFilter.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _commerceChannelFilter.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _commerceChannelFilter.isNew();
	}

	@Override
	public void persist() {
		_commerceChannelFilter.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_commerceChannelFilter.setCachedModel(cachedModel);
	}

	/**
	* Sets the commerce channel filter ID of this commerce channel filter.
	*
	* @param commerceChannelFilterId the commerce channel filter ID of this commerce channel filter
	*/
	@Override
	public void setCommerceChannelFilterId(long commerceChannelFilterId) {
		_commerceChannelFilter.setCommerceChannelFilterId(commerceChannelFilterId);
	}

	/**
	* Sets the commerce channel ID of this commerce channel filter.
	*
	* @param commerceChannelId the commerce channel ID of this commerce channel filter
	*/
	@Override
	public void setCommerceChannelId(long commerceChannelId) {
		_commerceChannelFilter.setCommerceChannelId(commerceChannelId);
	}

	/**
	* Sets the company ID of this commerce channel filter.
	*
	* @param companyId the company ID of this commerce channel filter
	*/
	@Override
	public void setCompanyId(long companyId) {
		_commerceChannelFilter.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this commerce channel filter.
	*
	* @param createDate the create date of this commerce channel filter
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_commerceChannelFilter.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_commerceChannelFilter.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_commerceChannelFilter.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_commerceChannelFilter.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the modified date of this commerce channel filter.
	*
	* @param modifiedDate the modified date of this commerce channel filter
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_commerceChannelFilter.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_commerceChannelFilter.setNew(n);
	}

	/**
	* Sets the primary key of this commerce channel filter.
	*
	* @param primaryKey the primary key of this commerce channel filter
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_commerceChannelFilter.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_commerceChannelFilter.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the type of this commerce channel filter.
	*
	* @param type the type of this commerce channel filter
	*/
	@Override
	public void setType(String type) {
		_commerceChannelFilter.setType(type);
	}

	/**
	* Sets the type settings of this commerce channel filter.
	*
	* @param typeSettings the type settings of this commerce channel filter
	*/
	@Override
	public void setTypeSettings(String typeSettings) {
		_commerceChannelFilter.setTypeSettings(typeSettings);
	}

	/**
	* Sets the user ID of this commerce channel filter.
	*
	* @param userId the user ID of this commerce channel filter
	*/
	@Override
	public void setUserId(long userId) {
		_commerceChannelFilter.setUserId(userId);
	}

	/**
	* Sets the user name of this commerce channel filter.
	*
	* @param userName the user name of this commerce channel filter
	*/
	@Override
	public void setUserName(String userName) {
		_commerceChannelFilter.setUserName(userName);
	}

	/**
	* Sets the user uuid of this commerce channel filter.
	*
	* @param userUuid the user uuid of this commerce channel filter
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_commerceChannelFilter.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CommerceChannelFilter> toCacheModel() {
		return _commerceChannelFilter.toCacheModel();
	}

	@Override
	public CommerceChannelFilter toEscapedModel() {
		return new CommerceChannelFilterWrapper(_commerceChannelFilter.toEscapedModel());
	}

	@Override
	public String toString() {
		return _commerceChannelFilter.toString();
	}

	@Override
	public CommerceChannelFilter toUnescapedModel() {
		return new CommerceChannelFilterWrapper(_commerceChannelFilter.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _commerceChannelFilter.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceChannelFilterWrapper)) {
			return false;
		}

		CommerceChannelFilterWrapper commerceChannelFilterWrapper = (CommerceChannelFilterWrapper)obj;

		if (Objects.equals(_commerceChannelFilter,
					commerceChannelFilterWrapper._commerceChannelFilter)) {
			return true;
		}

		return false;
	}

	@Override
	public CommerceChannelFilter getWrappedModel() {
		return _commerceChannelFilter;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _commerceChannelFilter.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _commerceChannelFilter.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_commerceChannelFilter.resetOriginalValues();
	}

	private final CommerceChannelFilter _commerceChannelFilter;
}