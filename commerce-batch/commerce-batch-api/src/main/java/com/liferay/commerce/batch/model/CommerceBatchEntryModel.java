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

package com.liferay.commerce.batch.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the CommerceBatchEntry service. Represents a row in the &quot;CommerceBatchEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.commerce.batch.model.impl.CommerceBatchEntryModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.commerce.batch.model.impl.CommerceBatchEntryImpl}.
 * </p>
 *
 * @author Matija Petanjek
 * @see CommerceBatchEntry
 * @see com.liferay.commerce.batch.model.impl.CommerceBatchEntryImpl
 * @see com.liferay.commerce.batch.model.impl.CommerceBatchEntryModelImpl
 * @generated
 */
@ProviderType
public interface CommerceBatchEntryModel extends BaseModel<CommerceBatchEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a commerce batch entry model instance should use the {@link CommerceBatchEntry} interface instead.
	 */

	/**
	 * Returns the primary key of this commerce batch entry.
	 *
	 * @return the primary key of this commerce batch entry
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this commerce batch entry.
	 *
	 * @param primaryKey the primary key of this commerce batch entry
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the commerce batch entry ID of this commerce batch entry.
	 *
	 * @return the commerce batch entry ID of this commerce batch entry
	 */
	public long getCommerceBatchEntryId();

	/**
	 * Sets the commerce batch entry ID of this commerce batch entry.
	 *
	 * @param commerceBatchEntryId the commerce batch entry ID of this commerce batch entry
	 */
	public void setCommerceBatchEntryId(long commerceBatchEntryId);

	/**
	 * Returns the create date of this commerce batch entry.
	 *
	 * @return the create date of this commerce batch entry
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this commerce batch entry.
	 *
	 * @param createDate the create date of this commerce batch entry
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this commerce batch entry.
	 *
	 * @return the modified date of this commerce batch entry
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this commerce batch entry.
	 *
	 * @param modifiedDate the modified date of this commerce batch entry
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the key of this commerce batch entry.
	 *
	 * @return the key of this commerce batch entry
	 */
	@AutoEscape
	public String getKey();

	/**
	 * Sets the key of this commerce batch entry.
	 *
	 * @param key the key of this commerce batch entry
	 */
	public void setKey(String key);

	/**
	 * Returns the name of this commerce batch entry.
	 *
	 * @return the name of this commerce batch entry
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this commerce batch entry.
	 *
	 * @param name the name of this commerce batch entry
	 */
	public void setName(String name);

	/**
	 * Returns the start time of this commerce batch entry.
	 *
	 * @return the start time of this commerce batch entry
	 */
	public Date getStartTime();

	/**
	 * Sets the start time of this commerce batch entry.
	 *
	 * @param startTime the start time of this commerce batch entry
	 */
	public void setStartTime(Date startTime);

	/**
	 * Returns the end time of this commerce batch entry.
	 *
	 * @return the end time of this commerce batch entry
	 */
	public Date getEndTime();

	/**
	 * Sets the end time of this commerce batch entry.
	 *
	 * @param endTime the end time of this commerce batch entry
	 */
	public void setEndTime(Date endTime);

	/**
	 * Returns the status of this commerce batch entry.
	 *
	 * @return the status of this commerce batch entry
	 */
	@AutoEscape
	public String getStatus();

	/**
	 * Sets the status of this commerce batch entry.
	 *
	 * @param status the status of this commerce batch entry
	 */
	public void setStatus(String status);

	/**
	 * Returns the callback url of this commerce batch entry.
	 *
	 * @return the callback url of this commerce batch entry
	 */
	@AutoEscape
	public String getCallbackURL();

	/**
	 * Sets the callback url of this commerce batch entry.
	 *
	 * @param callbackURL the callback url of this commerce batch entry
	 */
	public void setCallbackURL(String callbackURL);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(CommerceBatchEntry commerceBatchEntry);

	@Override
	public int hashCode();

	@Override
	public CacheModel<CommerceBatchEntry> toCacheModel();

	@Override
	public CommerceBatchEntry toEscapedModel();

	@Override
	public CommerceBatchEntry toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}