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

package com.liferay.commerce.batch.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.batch.model.CommerceBatchEntry;
import com.liferay.commerce.batch.model.CommerceBatchEntryModel;
import com.liferay.commerce.batch.model.CommerceBatchEntrySoap;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the CommerceBatchEntry service. Represents a row in the &quot;CommerceBatchEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link CommerceBatchEntryModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceBatchEntryImpl}.
 * </p>
 *
 * @author Matija Petanjek
 * @see CommerceBatchEntryImpl
 * @see CommerceBatchEntry
 * @see CommerceBatchEntryModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class CommerceBatchEntryModelImpl extends BaseModelImpl<CommerceBatchEntry>
	implements CommerceBatchEntryModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce batch entry model instance should use the {@link CommerceBatchEntry} interface instead.
	 */
	public static final String TABLE_NAME = "CommerceBatchEntry";
	public static final Object[][] TABLE_COLUMNS = {
			{ "commerceBatchEntryId", Types.BIGINT },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "key_", Types.VARCHAR },
			{ "name", Types.VARCHAR },
			{ "startTime", Types.TIMESTAMP },
			{ "endTime", Types.TIMESTAMP },
			{ "status", Types.VARCHAR },
			{ "callbackURL", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("commerceBatchEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("key_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("startTime", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("endTime", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("status", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("callbackURL", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table CommerceBatchEntry (commerceBatchEntryId LONG not null primary key,createDate DATE null,modifiedDate DATE null,key_ VARCHAR(75) null,name VARCHAR(75) null,startTime DATE null,endTime DATE null,status VARCHAR(75) null,callbackURL VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table CommerceBatchEntry";
	public static final String ORDER_BY_JPQL = " ORDER BY commerceBatchEntry.commerceBatchEntryId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY CommerceBatchEntry.commerceBatchEntryId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.batch.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.commerce.batch.model.CommerceBatchEntry"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.batch.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.commerce.batch.model.CommerceBatchEntry"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = false;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static CommerceBatchEntry toModel(CommerceBatchEntrySoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		CommerceBatchEntry model = new CommerceBatchEntryImpl();

		model.setCommerceBatchEntryId(soapModel.getCommerceBatchEntryId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setKey(soapModel.getKey());
		model.setName(soapModel.getName());
		model.setStartTime(soapModel.getStartTime());
		model.setEndTime(soapModel.getEndTime());
		model.setStatus(soapModel.getStatus());
		model.setCallbackURL(soapModel.getCallbackURL());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<CommerceBatchEntry> toModels(
		CommerceBatchEntrySoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<CommerceBatchEntry> models = new ArrayList<CommerceBatchEntry>(soapModels.length);

		for (CommerceBatchEntrySoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.commerce.batch.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.commerce.batch.model.CommerceBatchEntry"));

	public CommerceBatchEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceBatchEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceBatchEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceBatchEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceBatchEntry.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceBatchEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("commerceBatchEntryId", getCommerceBatchEntryId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("key", getKey());
		attributes.put("name", getName());
		attributes.put("startTime", getStartTime());
		attributes.put("endTime", getEndTime());
		attributes.put("status", getStatus());
		attributes.put("callbackURL", getCallbackURL());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long commerceBatchEntryId = (Long)attributes.get("commerceBatchEntryId");

		if (commerceBatchEntryId != null) {
			setCommerceBatchEntryId(commerceBatchEntryId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String key = (String)attributes.get("key");

		if (key != null) {
			setKey(key);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Date startTime = (Date)attributes.get("startTime");

		if (startTime != null) {
			setStartTime(startTime);
		}

		Date endTime = (Date)attributes.get("endTime");

		if (endTime != null) {
			setEndTime(endTime);
		}

		String status = (String)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		String callbackURL = (String)attributes.get("callbackURL");

		if (callbackURL != null) {
			setCallbackURL(callbackURL);
		}
	}

	@JSON
	@Override
	public long getCommerceBatchEntryId() {
		return _commerceBatchEntryId;
	}

	@Override
	public void setCommerceBatchEntryId(long commerceBatchEntryId) {
		_commerceBatchEntryId = commerceBatchEntryId;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public String getKey() {
		if (_key == null) {
			return "";
		}
		else {
			return _key;
		}
	}

	@Override
	public void setKey(String key) {
		_key = key;
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

	@JSON
	@Override
	public Date getStartTime() {
		return _startTime;
	}

	@Override
	public void setStartTime(Date startTime) {
		_startTime = startTime;
	}

	@JSON
	@Override
	public Date getEndTime() {
		return _endTime;
	}

	@Override
	public void setEndTime(Date endTime) {
		_endTime = endTime;
	}

	@JSON
	@Override
	public String getStatus() {
		if (_status == null) {
			return "";
		}
		else {
			return _status;
		}
	}

	@Override
	public void setStatus(String status) {
		_status = status;
	}

	@JSON
	@Override
	public String getCallbackURL() {
		if (_callbackURL == null) {
			return "";
		}
		else {
			return _callbackURL;
		}
	}

	@Override
	public void setCallbackURL(String callbackURL) {
		_callbackURL = callbackURL;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			CommerceBatchEntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceBatchEntry toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (CommerceBatchEntry)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		CommerceBatchEntryImpl commerceBatchEntryImpl = new CommerceBatchEntryImpl();

		commerceBatchEntryImpl.setCommerceBatchEntryId(getCommerceBatchEntryId());
		commerceBatchEntryImpl.setCreateDate(getCreateDate());
		commerceBatchEntryImpl.setModifiedDate(getModifiedDate());
		commerceBatchEntryImpl.setKey(getKey());
		commerceBatchEntryImpl.setName(getName());
		commerceBatchEntryImpl.setStartTime(getStartTime());
		commerceBatchEntryImpl.setEndTime(getEndTime());
		commerceBatchEntryImpl.setStatus(getStatus());
		commerceBatchEntryImpl.setCallbackURL(getCallbackURL());

		commerceBatchEntryImpl.resetOriginalValues();

		return commerceBatchEntryImpl;
	}

	@Override
	public int compareTo(CommerceBatchEntry commerceBatchEntry) {
		long primaryKey = commerceBatchEntry.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceBatchEntry)) {
			return false;
		}

		CommerceBatchEntry commerceBatchEntry = (CommerceBatchEntry)obj;

		long primaryKey = commerceBatchEntry.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		CommerceBatchEntryModelImpl commerceBatchEntryModelImpl = this;

		commerceBatchEntryModelImpl._setModifiedDate = false;
	}

	@Override
	public CacheModel<CommerceBatchEntry> toCacheModel() {
		CommerceBatchEntryCacheModel commerceBatchEntryCacheModel = new CommerceBatchEntryCacheModel();

		commerceBatchEntryCacheModel.commerceBatchEntryId = getCommerceBatchEntryId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceBatchEntryCacheModel.createDate = createDate.getTime();
		}
		else {
			commerceBatchEntryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceBatchEntryCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			commerceBatchEntryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		commerceBatchEntryCacheModel.key = getKey();

		String key = commerceBatchEntryCacheModel.key;

		if ((key != null) && (key.length() == 0)) {
			commerceBatchEntryCacheModel.key = null;
		}

		commerceBatchEntryCacheModel.name = getName();

		String name = commerceBatchEntryCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			commerceBatchEntryCacheModel.name = null;
		}

		Date startTime = getStartTime();

		if (startTime != null) {
			commerceBatchEntryCacheModel.startTime = startTime.getTime();
		}
		else {
			commerceBatchEntryCacheModel.startTime = Long.MIN_VALUE;
		}

		Date endTime = getEndTime();

		if (endTime != null) {
			commerceBatchEntryCacheModel.endTime = endTime.getTime();
		}
		else {
			commerceBatchEntryCacheModel.endTime = Long.MIN_VALUE;
		}

		commerceBatchEntryCacheModel.status = getStatus();

		String status = commerceBatchEntryCacheModel.status;

		if ((status != null) && (status.length() == 0)) {
			commerceBatchEntryCacheModel.status = null;
		}

		commerceBatchEntryCacheModel.callbackURL = getCallbackURL();

		String callbackURL = commerceBatchEntryCacheModel.callbackURL;

		if ((callbackURL != null) && (callbackURL.length() == 0)) {
			commerceBatchEntryCacheModel.callbackURL = null;
		}

		return commerceBatchEntryCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{commerceBatchEntryId=");
		sb.append(getCommerceBatchEntryId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", key=");
		sb.append(getKey());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", startTime=");
		sb.append(getStartTime());
		sb.append(", endTime=");
		sb.append(getEndTime());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append(", callbackURL=");
		sb.append(getCallbackURL());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(31);

		sb.append("<model><model-name>");
		sb.append("com.liferay.commerce.batch.model.CommerceBatchEntry");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>commerceBatchEntryId</column-name><column-value><![CDATA[");
		sb.append(getCommerceBatchEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>key</column-name><column-value><![CDATA[");
		sb.append(getKey());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>startTime</column-name><column-value><![CDATA[");
		sb.append(getStartTime());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>endTime</column-name><column-value><![CDATA[");
		sb.append(getEndTime());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>callbackURL</column-name><column-value><![CDATA[");
		sb.append(getCallbackURL());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = CommerceBatchEntry.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			CommerceBatchEntry.class, ModelWrapper.class
		};
	private long _commerceBatchEntryId;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _key;
	private String _name;
	private Date _startTime;
	private Date _endTime;
	private String _status;
	private String _callbackURL;
	private CommerceBatchEntry _escapedModel;
}