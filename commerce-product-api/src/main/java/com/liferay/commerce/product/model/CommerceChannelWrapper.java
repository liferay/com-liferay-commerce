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
 * This class is a wrapper for {@link CommerceChannel}.
 * </p>
 *
 * @author Marco Leo
 * @see CommerceChannel
 * @generated
 */
@ProviderType
public class CommerceChannelWrapper implements CommerceChannel,
	ModelWrapper<CommerceChannel> {
	public CommerceChannelWrapper(CommerceChannel commerceChannel) {
		_commerceChannel = commerceChannel;
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceChannel.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceChannel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("commerceChannelId", getCommerceChannelId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("filterType", getFilterType());
		attributes.put("type", getType());
		attributes.put("typeSettings", getTypeSettings());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long commerceChannelId = (Long)attributes.get("commerceChannelId");

		if (commerceChannelId != null) {
			setCommerceChannelId(commerceChannelId);
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

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String filterType = (String)attributes.get("filterType");

		if (filterType != null) {
			setFilterType(filterType);
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
		return new CommerceChannelWrapper((CommerceChannel)_commerceChannel.clone());
	}

	@Override
	public int compareTo(CommerceChannel commerceChannel) {
		return _commerceChannel.compareTo(commerceChannel);
	}

	/**
	* Returns the commerce channel ID of this commerce channel.
	*
	* @return the commerce channel ID of this commerce channel
	*/
	@Override
	public long getCommerceChannelId() {
		return _commerceChannel.getCommerceChannelId();
	}

	/**
	* Returns the company ID of this commerce channel.
	*
	* @return the company ID of this commerce channel
	*/
	@Override
	public long getCompanyId() {
		return _commerceChannel.getCompanyId();
	}

	/**
	* Returns the create date of this commerce channel.
	*
	* @return the create date of this commerce channel
	*/
	@Override
	public Date getCreateDate() {
		return _commerceChannel.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _commerceChannel.getExpandoBridge();
	}

	/**
	* Returns the filter type of this commerce channel.
	*
	* @return the filter type of this commerce channel
	*/
	@Override
	public String getFilterType() {
		return _commerceChannel.getFilterType();
	}

	/**
	* Returns the modified date of this commerce channel.
	*
	* @return the modified date of this commerce channel
	*/
	@Override
	public Date getModifiedDate() {
		return _commerceChannel.getModifiedDate();
	}

	/**
	* Returns the name of this commerce channel.
	*
	* @return the name of this commerce channel
	*/
	@Override
	public String getName() {
		return _commerceChannel.getName();
	}

	/**
	* Returns the primary key of this commerce channel.
	*
	* @return the primary key of this commerce channel
	*/
	@Override
	public long getPrimaryKey() {
		return _commerceChannel.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceChannel.getPrimaryKeyObj();
	}

	/**
	* Returns the type of this commerce channel.
	*
	* @return the type of this commerce channel
	*/
	@Override
	public String getType() {
		return _commerceChannel.getType();
	}

	/**
	* Returns the type settings of this commerce channel.
	*
	* @return the type settings of this commerce channel
	*/
	@Override
	public String getTypeSettings() {
		return _commerceChannel.getTypeSettings();
	}

	@Override
	public com.liferay.portal.kernel.util.UnicodeProperties getTypeSettingsProperties() {
		return _commerceChannel.getTypeSettingsProperties();
	}

	/**
	* Returns the user ID of this commerce channel.
	*
	* @return the user ID of this commerce channel
	*/
	@Override
	public long getUserId() {
		return _commerceChannel.getUserId();
	}

	/**
	* Returns the user name of this commerce channel.
	*
	* @return the user name of this commerce channel
	*/
	@Override
	public String getUserName() {
		return _commerceChannel.getUserName();
	}

	/**
	* Returns the user uuid of this commerce channel.
	*
	* @return the user uuid of this commerce channel
	*/
	@Override
	public String getUserUuid() {
		return _commerceChannel.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _commerceChannel.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _commerceChannel.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _commerceChannel.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _commerceChannel.isNew();
	}

	@Override
	public void persist() {
		_commerceChannel.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_commerceChannel.setCachedModel(cachedModel);
	}

	/**
	* Sets the commerce channel ID of this commerce channel.
	*
	* @param commerceChannelId the commerce channel ID of this commerce channel
	*/
	@Override
	public void setCommerceChannelId(long commerceChannelId) {
		_commerceChannel.setCommerceChannelId(commerceChannelId);
	}

	/**
	* Sets the company ID of this commerce channel.
	*
	* @param companyId the company ID of this commerce channel
	*/
	@Override
	public void setCompanyId(long companyId) {
		_commerceChannel.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this commerce channel.
	*
	* @param createDate the create date of this commerce channel
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_commerceChannel.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_commerceChannel.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_commerceChannel.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_commerceChannel.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the filter type of this commerce channel.
	*
	* @param filterType the filter type of this commerce channel
	*/
	@Override
	public void setFilterType(String filterType) {
		_commerceChannel.setFilterType(filterType);
	}

	/**
	* Sets the modified date of this commerce channel.
	*
	* @param modifiedDate the modified date of this commerce channel
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_commerceChannel.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this commerce channel.
	*
	* @param name the name of this commerce channel
	*/
	@Override
	public void setName(String name) {
		_commerceChannel.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_commerceChannel.setNew(n);
	}

	/**
	* Sets the primary key of this commerce channel.
	*
	* @param primaryKey the primary key of this commerce channel
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_commerceChannel.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_commerceChannel.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the type of this commerce channel.
	*
	* @param type the type of this commerce channel
	*/
	@Override
	public void setType(String type) {
		_commerceChannel.setType(type);
	}

	/**
	* Sets the type settings of this commerce channel.
	*
	* @param typeSettings the type settings of this commerce channel
	*/
	@Override
	public void setTypeSettings(String typeSettings) {
		_commerceChannel.setTypeSettings(typeSettings);
	}

	@Override
	public void setTypeSettingsProperties(
		com.liferay.portal.kernel.util.UnicodeProperties typeSettingsProperties) {
		_commerceChannel.setTypeSettingsProperties(typeSettingsProperties);
	}

	/**
	* Sets the user ID of this commerce channel.
	*
	* @param userId the user ID of this commerce channel
	*/
	@Override
	public void setUserId(long userId) {
		_commerceChannel.setUserId(userId);
	}

	/**
	* Sets the user name of this commerce channel.
	*
	* @param userName the user name of this commerce channel
	*/
	@Override
	public void setUserName(String userName) {
		_commerceChannel.setUserName(userName);
	}

	/**
	* Sets the user uuid of this commerce channel.
	*
	* @param userUuid the user uuid of this commerce channel
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_commerceChannel.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CommerceChannel> toCacheModel() {
		return _commerceChannel.toCacheModel();
	}

	@Override
	public CommerceChannel toEscapedModel() {
		return new CommerceChannelWrapper(_commerceChannel.toEscapedModel());
	}

	@Override
	public String toString() {
		return _commerceChannel.toString();
	}

	@Override
	public CommerceChannel toUnescapedModel() {
		return new CommerceChannelWrapper(_commerceChannel.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _commerceChannel.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceChannelWrapper)) {
			return false;
		}

		CommerceChannelWrapper commerceChannelWrapper = (CommerceChannelWrapper)obj;

		if (Objects.equals(_commerceChannel,
					commerceChannelWrapper._commerceChannel)) {
			return true;
		}

		return false;
	}

	@Override
	public CommerceChannel getWrappedModel() {
		return _commerceChannel;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _commerceChannel.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _commerceChannel.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_commerceChannel.resetOriginalValues();
	}

	private final CommerceChannel _commerceChannel;
}