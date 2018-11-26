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

package com.liferay.commerce.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.model.CommerceSubscriptionEntry;
import com.liferay.commerce.model.CommerceSubscriptionEntryModel;
import com.liferay.commerce.model.CommerceSubscriptionEntrySoap;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
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
 * The base model implementation for the CommerceSubscriptionEntry service. Represents a row in the &quot;CommerceSubscriptionEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link CommerceSubscriptionEntryModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceSubscriptionEntryImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceSubscriptionEntryImpl
 * @see CommerceSubscriptionEntry
 * @see CommerceSubscriptionEntryModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class CommerceSubscriptionEntryModelImpl extends BaseModelImpl<CommerceSubscriptionEntry>
	implements CommerceSubscriptionEntryModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce subscription entry model instance should use the {@link CommerceSubscriptionEntry} interface instead.
	 */
	public static final String TABLE_NAME = "CommerceSubscriptionEntry";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "commerceSubscriptionEntryId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "CPInstanceId", Types.BIGINT },
			{ "commerceOrderItemId", Types.BIGINT },
			{ "subscriptionLength", Types.INTEGER },
			{ "subscriptionType", Types.VARCHAR },
			{ "subscriptionTypeSettings", Types.CLOB },
			{ "maxSubscriptionCycles", Types.BIGINT },
			{ "active_", Types.BOOLEAN },
			{ "lastIterationDate", Types.TIMESTAMP },
			{ "nextIterationDate", Types.TIMESTAMP },
			{ "startDate", Types.TIMESTAMP }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("commerceSubscriptionEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("CPInstanceId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("commerceOrderItemId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("subscriptionLength", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("subscriptionType", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("subscriptionTypeSettings", Types.CLOB);
		TABLE_COLUMNS_MAP.put("maxSubscriptionCycles", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("active_", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("lastIterationDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("nextIterationDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("startDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE = "create table CommerceSubscriptionEntry (uuid_ VARCHAR(75) null,commerceSubscriptionEntryId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,CPInstanceId LONG,commerceOrderItemId LONG,subscriptionLength INTEGER,subscriptionType VARCHAR(75) null,subscriptionTypeSettings TEXT null,maxSubscriptionCycles LONG,active_ BOOLEAN,lastIterationDate DATE null,nextIterationDate DATE null,startDate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table CommerceSubscriptionEntry";
	public static final String ORDER_BY_JPQL = " ORDER BY commerceSubscriptionEntry.createDate DESC";
	public static final String ORDER_BY_SQL = " ORDER BY CommerceSubscriptionEntry.createDate DESC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.commerce.model.CommerceSubscriptionEntry"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.commerce.model.CommerceSubscriptionEntry"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.commerce.model.CommerceSubscriptionEntry"),
			true);
	public static final long ACTIVE_COLUMN_BITMASK = 1L;
	public static final long COMPANYID_COLUMN_BITMASK = 2L;
	public static final long GROUPID_COLUMN_BITMASK = 4L;
	public static final long USERID_COLUMN_BITMASK = 8L;
	public static final long UUID_COLUMN_BITMASK = 16L;
	public static final long CREATEDATE_COLUMN_BITMASK = 32L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static CommerceSubscriptionEntry toModel(
		CommerceSubscriptionEntrySoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		CommerceSubscriptionEntry model = new CommerceSubscriptionEntryImpl();

		model.setUuid(soapModel.getUuid());
		model.setCommerceSubscriptionEntryId(soapModel.getCommerceSubscriptionEntryId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setCPInstanceId(soapModel.getCPInstanceId());
		model.setCommerceOrderItemId(soapModel.getCommerceOrderItemId());
		model.setSubscriptionLength(soapModel.getSubscriptionLength());
		model.setSubscriptionType(soapModel.getSubscriptionType());
		model.setSubscriptionTypeSettings(soapModel.getSubscriptionTypeSettings());
		model.setMaxSubscriptionCycles(soapModel.getMaxSubscriptionCycles());
		model.setActive(soapModel.isActive());
		model.setLastIterationDate(soapModel.getLastIterationDate());
		model.setNextIterationDate(soapModel.getNextIterationDate());
		model.setStartDate(soapModel.getStartDate());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<CommerceSubscriptionEntry> toModels(
		CommerceSubscriptionEntrySoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<CommerceSubscriptionEntry> models = new ArrayList<CommerceSubscriptionEntry>(soapModels.length);

		for (CommerceSubscriptionEntrySoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.commerce.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.commerce.model.CommerceSubscriptionEntry"));

	public CommerceSubscriptionEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceSubscriptionEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceSubscriptionEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceSubscriptionEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
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
		attributes.put("subscriptionLength", getSubscriptionLength());
		attributes.put("subscriptionType", getSubscriptionType());
		attributes.put("subscriptionTypeSettings", getSubscriptionTypeSettings());
		attributes.put("maxSubscriptionCycles", getMaxSubscriptionCycles());
		attributes.put("active", isActive());
		attributes.put("lastIterationDate", getLastIterationDate());
		attributes.put("nextIterationDate", getNextIterationDate());
		attributes.put("startDate", getStartDate());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

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

		Integer subscriptionLength = (Integer)attributes.get(
				"subscriptionLength");

		if (subscriptionLength != null) {
			setSubscriptionLength(subscriptionLength);
		}

		String subscriptionType = (String)attributes.get("subscriptionType");

		if (subscriptionType != null) {
			setSubscriptionType(subscriptionType);
		}

		String subscriptionTypeSettings = (String)attributes.get(
				"subscriptionTypeSettings");

		if (subscriptionTypeSettings != null) {
			setSubscriptionTypeSettings(subscriptionTypeSettings);
		}

		Long maxSubscriptionCycles = (Long)attributes.get(
				"maxSubscriptionCycles");

		if (maxSubscriptionCycles != null) {
			setMaxSubscriptionCycles(maxSubscriptionCycles);
		}

		Boolean active = (Boolean)attributes.get("active");

		if (active != null) {
			setActive(active);
		}

		Date lastIterationDate = (Date)attributes.get("lastIterationDate");

		if (lastIterationDate != null) {
			setLastIterationDate(lastIterationDate);
		}

		Date nextIterationDate = (Date)attributes.get("nextIterationDate");

		if (nextIterationDate != null) {
			setNextIterationDate(nextIterationDate);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@JSON
	@Override
	public long getCommerceSubscriptionEntryId() {
		return _commerceSubscriptionEntryId;
	}

	@Override
	public void setCommerceSubscriptionEntryId(long commerceSubscriptionEntryId) {
		_commerceSubscriptionEntryId = commerceSubscriptionEntryId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_columnBitmask |= USERID_COLUMN_BITMASK;

		if (!_setOriginalUserId) {
			_setOriginalUserId = true;

			_originalUserId = _userId;
		}

		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	public long getOriginalUserId() {
		return _originalUserId;
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_columnBitmask = -1L;

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
	public long getCPInstanceId() {
		return _CPInstanceId;
	}

	@Override
	public void setCPInstanceId(long CPInstanceId) {
		_CPInstanceId = CPInstanceId;
	}

	@JSON
	@Override
	public long getCommerceOrderItemId() {
		return _commerceOrderItemId;
	}

	@Override
	public void setCommerceOrderItemId(long commerceOrderItemId) {
		_commerceOrderItemId = commerceOrderItemId;
	}

	@JSON
	@Override
	public int getSubscriptionLength() {
		return _subscriptionLength;
	}

	@Override
	public void setSubscriptionLength(int subscriptionLength) {
		_subscriptionLength = subscriptionLength;
	}

	@JSON
	@Override
	public String getSubscriptionType() {
		if (_subscriptionType == null) {
			return "";
		}
		else {
			return _subscriptionType;
		}
	}

	@Override
	public void setSubscriptionType(String subscriptionType) {
		_subscriptionType = subscriptionType;
	}

	@JSON
	@Override
	public String getSubscriptionTypeSettings() {
		if (_subscriptionTypeSettings == null) {
			return "";
		}
		else {
			return _subscriptionTypeSettings;
		}
	}

	@Override
	public void setSubscriptionTypeSettings(String subscriptionTypeSettings) {
		_subscriptionTypeSettings = subscriptionTypeSettings;
	}

	@JSON
	@Override
	public long getMaxSubscriptionCycles() {
		return _maxSubscriptionCycles;
	}

	@Override
	public void setMaxSubscriptionCycles(long maxSubscriptionCycles) {
		_maxSubscriptionCycles = maxSubscriptionCycles;
	}

	@JSON
	@Override
	public boolean getActive() {
		return _active;
	}

	@JSON
	@Override
	public boolean isActive() {
		return _active;
	}

	@Override
	public void setActive(boolean active) {
		_columnBitmask |= ACTIVE_COLUMN_BITMASK;

		if (!_setOriginalActive) {
			_setOriginalActive = true;

			_originalActive = _active;
		}

		_active = active;
	}

	public boolean getOriginalActive() {
		return _originalActive;
	}

	@JSON
	@Override
	public Date getLastIterationDate() {
		return _lastIterationDate;
	}

	@Override
	public void setLastIterationDate(Date lastIterationDate) {
		_lastIterationDate = lastIterationDate;
	}

	@JSON
	@Override
	public Date getNextIterationDate() {
		return _nextIterationDate;
	}

	@Override
	public void setNextIterationDate(Date nextIterationDate) {
		_nextIterationDate = nextIterationDate;
	}

	@JSON
	@Override
	public Date getStartDate() {
		return _startDate;
	}

	@Override
	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				CommerceSubscriptionEntry.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			CommerceSubscriptionEntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceSubscriptionEntry toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (CommerceSubscriptionEntry)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		CommerceSubscriptionEntryImpl commerceSubscriptionEntryImpl = new CommerceSubscriptionEntryImpl();

		commerceSubscriptionEntryImpl.setUuid(getUuid());
		commerceSubscriptionEntryImpl.setCommerceSubscriptionEntryId(getCommerceSubscriptionEntryId());
		commerceSubscriptionEntryImpl.setGroupId(getGroupId());
		commerceSubscriptionEntryImpl.setCompanyId(getCompanyId());
		commerceSubscriptionEntryImpl.setUserId(getUserId());
		commerceSubscriptionEntryImpl.setUserName(getUserName());
		commerceSubscriptionEntryImpl.setCreateDate(getCreateDate());
		commerceSubscriptionEntryImpl.setModifiedDate(getModifiedDate());
		commerceSubscriptionEntryImpl.setCPInstanceId(getCPInstanceId());
		commerceSubscriptionEntryImpl.setCommerceOrderItemId(getCommerceOrderItemId());
		commerceSubscriptionEntryImpl.setSubscriptionLength(getSubscriptionLength());
		commerceSubscriptionEntryImpl.setSubscriptionType(getSubscriptionType());
		commerceSubscriptionEntryImpl.setSubscriptionTypeSettings(getSubscriptionTypeSettings());
		commerceSubscriptionEntryImpl.setMaxSubscriptionCycles(getMaxSubscriptionCycles());
		commerceSubscriptionEntryImpl.setActive(isActive());
		commerceSubscriptionEntryImpl.setLastIterationDate(getLastIterationDate());
		commerceSubscriptionEntryImpl.setNextIterationDate(getNextIterationDate());
		commerceSubscriptionEntryImpl.setStartDate(getStartDate());

		commerceSubscriptionEntryImpl.resetOriginalValues();

		return commerceSubscriptionEntryImpl;
	}

	@Override
	public int compareTo(CommerceSubscriptionEntry commerceSubscriptionEntry) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(),
				commerceSubscriptionEntry.getCreateDate());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceSubscriptionEntry)) {
			return false;
		}

		CommerceSubscriptionEntry commerceSubscriptionEntry = (CommerceSubscriptionEntry)obj;

		long primaryKey = commerceSubscriptionEntry.getPrimaryKey();

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
		CommerceSubscriptionEntryModelImpl commerceSubscriptionEntryModelImpl = this;

		commerceSubscriptionEntryModelImpl._originalUuid = commerceSubscriptionEntryModelImpl._uuid;

		commerceSubscriptionEntryModelImpl._originalGroupId = commerceSubscriptionEntryModelImpl._groupId;

		commerceSubscriptionEntryModelImpl._setOriginalGroupId = false;

		commerceSubscriptionEntryModelImpl._originalCompanyId = commerceSubscriptionEntryModelImpl._companyId;

		commerceSubscriptionEntryModelImpl._setOriginalCompanyId = false;

		commerceSubscriptionEntryModelImpl._originalUserId = commerceSubscriptionEntryModelImpl._userId;

		commerceSubscriptionEntryModelImpl._setOriginalUserId = false;

		commerceSubscriptionEntryModelImpl._setModifiedDate = false;

		commerceSubscriptionEntryModelImpl._originalActive = commerceSubscriptionEntryModelImpl._active;

		commerceSubscriptionEntryModelImpl._setOriginalActive = false;

		commerceSubscriptionEntryModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<CommerceSubscriptionEntry> toCacheModel() {
		CommerceSubscriptionEntryCacheModel commerceSubscriptionEntryCacheModel = new CommerceSubscriptionEntryCacheModel();

		commerceSubscriptionEntryCacheModel.uuid = getUuid();

		String uuid = commerceSubscriptionEntryCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			commerceSubscriptionEntryCacheModel.uuid = null;
		}

		commerceSubscriptionEntryCacheModel.commerceSubscriptionEntryId = getCommerceSubscriptionEntryId();

		commerceSubscriptionEntryCacheModel.groupId = getGroupId();

		commerceSubscriptionEntryCacheModel.companyId = getCompanyId();

		commerceSubscriptionEntryCacheModel.userId = getUserId();

		commerceSubscriptionEntryCacheModel.userName = getUserName();

		String userName = commerceSubscriptionEntryCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceSubscriptionEntryCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceSubscriptionEntryCacheModel.createDate = createDate.getTime();
		}
		else {
			commerceSubscriptionEntryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceSubscriptionEntryCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			commerceSubscriptionEntryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		commerceSubscriptionEntryCacheModel.CPInstanceId = getCPInstanceId();

		commerceSubscriptionEntryCacheModel.commerceOrderItemId = getCommerceOrderItemId();

		commerceSubscriptionEntryCacheModel.subscriptionLength = getSubscriptionLength();

		commerceSubscriptionEntryCacheModel.subscriptionType = getSubscriptionType();

		String subscriptionType = commerceSubscriptionEntryCacheModel.subscriptionType;

		if ((subscriptionType != null) && (subscriptionType.length() == 0)) {
			commerceSubscriptionEntryCacheModel.subscriptionType = null;
		}

		commerceSubscriptionEntryCacheModel.subscriptionTypeSettings = getSubscriptionTypeSettings();

		String subscriptionTypeSettings = commerceSubscriptionEntryCacheModel.subscriptionTypeSettings;

		if ((subscriptionTypeSettings != null) &&
				(subscriptionTypeSettings.length() == 0)) {
			commerceSubscriptionEntryCacheModel.subscriptionTypeSettings = null;
		}

		commerceSubscriptionEntryCacheModel.maxSubscriptionCycles = getMaxSubscriptionCycles();

		commerceSubscriptionEntryCacheModel.active = isActive();

		Date lastIterationDate = getLastIterationDate();

		if (lastIterationDate != null) {
			commerceSubscriptionEntryCacheModel.lastIterationDate = lastIterationDate.getTime();
		}
		else {
			commerceSubscriptionEntryCacheModel.lastIterationDate = Long.MIN_VALUE;
		}

		Date nextIterationDate = getNextIterationDate();

		if (nextIterationDate != null) {
			commerceSubscriptionEntryCacheModel.nextIterationDate = nextIterationDate.getTime();
		}
		else {
			commerceSubscriptionEntryCacheModel.nextIterationDate = Long.MIN_VALUE;
		}

		Date startDate = getStartDate();

		if (startDate != null) {
			commerceSubscriptionEntryCacheModel.startDate = startDate.getTime();
		}
		else {
			commerceSubscriptionEntryCacheModel.startDate = Long.MIN_VALUE;
		}

		return commerceSubscriptionEntryCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(37);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", commerceSubscriptionEntryId=");
		sb.append(getCommerceSubscriptionEntryId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", CPInstanceId=");
		sb.append(getCPInstanceId());
		sb.append(", commerceOrderItemId=");
		sb.append(getCommerceOrderItemId());
		sb.append(", subscriptionLength=");
		sb.append(getSubscriptionLength());
		sb.append(", subscriptionType=");
		sb.append(getSubscriptionType());
		sb.append(", subscriptionTypeSettings=");
		sb.append(getSubscriptionTypeSettings());
		sb.append(", maxSubscriptionCycles=");
		sb.append(getMaxSubscriptionCycles());
		sb.append(", active=");
		sb.append(isActive());
		sb.append(", lastIterationDate=");
		sb.append(getLastIterationDate());
		sb.append(", nextIterationDate=");
		sb.append(getNextIterationDate());
		sb.append(", startDate=");
		sb.append(getStartDate());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(58);

		sb.append("<model><model-name>");
		sb.append("com.liferay.commerce.model.CommerceSubscriptionEntry");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>commerceSubscriptionEntryId</column-name><column-value><![CDATA[");
		sb.append(getCommerceSubscriptionEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
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
			"<column><column-name>CPInstanceId</column-name><column-value><![CDATA[");
		sb.append(getCPInstanceId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>commerceOrderItemId</column-name><column-value><![CDATA[");
		sb.append(getCommerceOrderItemId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>subscriptionLength</column-name><column-value><![CDATA[");
		sb.append(getSubscriptionLength());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>subscriptionType</column-name><column-value><![CDATA[");
		sb.append(getSubscriptionType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>subscriptionTypeSettings</column-name><column-value><![CDATA[");
		sb.append(getSubscriptionTypeSettings());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>maxSubscriptionCycles</column-name><column-value><![CDATA[");
		sb.append(getMaxSubscriptionCycles());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>active</column-name><column-value><![CDATA[");
		sb.append(isActive());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>lastIterationDate</column-name><column-value><![CDATA[");
		sb.append(getLastIterationDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>nextIterationDate</column-name><column-value><![CDATA[");
		sb.append(getNextIterationDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>startDate</column-name><column-value><![CDATA[");
		sb.append(getStartDate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = CommerceSubscriptionEntry.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			CommerceSubscriptionEntry.class, ModelWrapper.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _commerceSubscriptionEntryId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private long _originalUserId;
	private boolean _setOriginalUserId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _CPInstanceId;
	private long _commerceOrderItemId;
	private int _subscriptionLength;
	private String _subscriptionType;
	private String _subscriptionTypeSettings;
	private long _maxSubscriptionCycles;
	private boolean _active;
	private boolean _originalActive;
	private boolean _setOriginalActive;
	private Date _lastIterationDate;
	private Date _nextIterationDate;
	private Date _startDate;
	private long _columnBitmask;
	private CommerceSubscriptionEntry _escapedModel;
}