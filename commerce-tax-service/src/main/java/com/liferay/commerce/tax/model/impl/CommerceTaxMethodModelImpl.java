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

package com.liferay.commerce.tax.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.tax.model.CommerceTaxMethod;
import com.liferay.commerce.tax.model.CommerceTaxMethodModel;
import com.liferay.commerce.tax.model.CommerceTaxMethodSoap;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.petra.string.StringBundler;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.LocaleException;
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
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * The base model implementation for the CommerceTaxMethod service. Represents a row in the &quot;CommerceTaxMethod&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link CommerceTaxMethodModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceTaxMethodImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see CommerceTaxMethodImpl
 * @see CommerceTaxMethod
 * @see CommerceTaxMethodModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class CommerceTaxMethodModelImpl extends BaseModelImpl<CommerceTaxMethod>
	implements CommerceTaxMethodModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce tax method model instance should use the {@link CommerceTaxMethod} interface instead.
	 */
	public static final String TABLE_NAME = "CommerceTaxMethod";
	public static final Object[][] TABLE_COLUMNS = {
			{ "commerceTaxMethodId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "name", Types.VARCHAR },
			{ "description", Types.VARCHAR },
			{ "engineKey", Types.VARCHAR },
			{ "percentage", Types.BOOLEAN },
			{ "active_", Types.BOOLEAN }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("commerceTaxMethodId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("engineKey", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("percentage", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("active_", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE = "create table CommerceTaxMethod (commerceTaxMethodId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,name STRING null,description STRING null,engineKey VARCHAR(75) null,percentage BOOLEAN,active_ BOOLEAN)";
	public static final String TABLE_SQL_DROP = "drop table CommerceTaxMethod";
	public static final String ORDER_BY_JPQL = " ORDER BY commerceTaxMethod.createDate DESC";
	public static final String ORDER_BY_SQL = " ORDER BY CommerceTaxMethod.createDate DESC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.tax.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.commerce.tax.model.CommerceTaxMethod"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.tax.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.commerce.tax.model.CommerceTaxMethod"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.tax.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.commerce.tax.model.CommerceTaxMethod"),
			true);
	public static final long ACTIVE_COLUMN_BITMASK = 1L;
	public static final long ENGINEKEY_COLUMN_BITMASK = 2L;
	public static final long GROUPID_COLUMN_BITMASK = 4L;
	public static final long CREATEDATE_COLUMN_BITMASK = 8L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static CommerceTaxMethod toModel(CommerceTaxMethodSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		CommerceTaxMethod model = new CommerceTaxMethodImpl();

		model.setCommerceTaxMethodId(soapModel.getCommerceTaxMethodId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setName(soapModel.getName());
		model.setDescription(soapModel.getDescription());
		model.setEngineKey(soapModel.getEngineKey());
		model.setPercentage(soapModel.isPercentage());
		model.setActive(soapModel.isActive());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<CommerceTaxMethod> toModels(
		CommerceTaxMethodSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<CommerceTaxMethod> models = new ArrayList<CommerceTaxMethod>(soapModels.length);

		for (CommerceTaxMethodSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.commerce.tax.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.commerce.tax.model.CommerceTaxMethod"));

	public CommerceTaxMethodModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceTaxMethodId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceTaxMethodId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceTaxMethodId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceTaxMethod.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceTaxMethod.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("commerceTaxMethodId", getCommerceTaxMethodId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("engineKey", getEngineKey());
		attributes.put("percentage", isPercentage());
		attributes.put("active", isActive());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long commerceTaxMethodId = (Long)attributes.get("commerceTaxMethodId");

		if (commerceTaxMethodId != null) {
			setCommerceTaxMethodId(commerceTaxMethodId);
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

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String engineKey = (String)attributes.get("engineKey");

		if (engineKey != null) {
			setEngineKey(engineKey);
		}

		Boolean percentage = (Boolean)attributes.get("percentage");

		if (percentage != null) {
			setPercentage(percentage);
		}

		Boolean active = (Boolean)attributes.get("active");

		if (active != null) {
			setActive(active);
		}
	}

	@JSON
	@Override
	public long getCommerceTaxMethodId() {
		return _commerceTaxMethodId;
	}

	@Override
	public void setCommerceTaxMethodId(long commerceTaxMethodId) {
		_commerceTaxMethodId = commerceTaxMethodId;
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
		_companyId = companyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
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
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public String getName(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getName(languageId);
	}

	@Override
	public String getName(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getName(languageId, useDefault);
	}

	@Override
	public String getName(String languageId) {
		return LocalizationUtil.getLocalization(getName(), languageId);
	}

	@Override
	public String getName(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getName(), languageId,
			useDefault);
	}

	@Override
	public String getNameCurrentLanguageId() {
		return _nameCurrentLanguageId;
	}

	@JSON
	@Override
	public String getNameCurrentValue() {
		Locale locale = getLocale(_nameCurrentLanguageId);

		return getName(locale);
	}

	@Override
	public Map<Locale, String> getNameMap() {
		return LocalizationUtil.getLocalizationMap(getName());
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

	@Override
	public void setName(String name, Locale locale) {
		setName(name, locale, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setName(String name, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(name)) {
			setName(LocalizationUtil.updateLocalization(getName(), "Name",
					name, languageId, defaultLanguageId));
		}
		else {
			setName(LocalizationUtil.removeLocalization(getName(), "Name",
					languageId));
		}
	}

	@Override
	public void setNameCurrentLanguageId(String languageId) {
		_nameCurrentLanguageId = languageId;
	}

	@Override
	public void setNameMap(Map<Locale, String> nameMap) {
		setNameMap(nameMap, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setNameMap(Map<Locale, String> nameMap, Locale defaultLocale) {
		if (nameMap == null) {
			return;
		}

		setName(LocalizationUtil.updateLocalization(nameMap, getName(), "Name",
				LocaleUtil.toLanguageId(defaultLocale)));
	}

	@JSON
	@Override
	public String getDescription() {
		if (_description == null) {
			return "";
		}
		else {
			return _description;
		}
	}

	@Override
	public String getDescription(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDescription(languageId);
	}

	@Override
	public String getDescription(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDescription(languageId, useDefault);
	}

	@Override
	public String getDescription(String languageId) {
		return LocalizationUtil.getLocalization(getDescription(), languageId);
	}

	@Override
	public String getDescription(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getDescription(), languageId,
			useDefault);
	}

	@Override
	public String getDescriptionCurrentLanguageId() {
		return _descriptionCurrentLanguageId;
	}

	@JSON
	@Override
	public String getDescriptionCurrentValue() {
		Locale locale = getLocale(_descriptionCurrentLanguageId);

		return getDescription(locale);
	}

	@Override
	public Map<Locale, String> getDescriptionMap() {
		return LocalizationUtil.getLocalizationMap(getDescription());
	}

	@Override
	public void setDescription(String description) {
		_description = description;
	}

	@Override
	public void setDescription(String description, Locale locale) {
		setDescription(description, locale, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setDescription(String description, Locale locale,
		Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(description)) {
			setDescription(LocalizationUtil.updateLocalization(
					getDescription(), "Description", description, languageId,
					defaultLanguageId));
		}
		else {
			setDescription(LocalizationUtil.removeLocalization(
					getDescription(), "Description", languageId));
		}
	}

	@Override
	public void setDescriptionCurrentLanguageId(String languageId) {
		_descriptionCurrentLanguageId = languageId;
	}

	@Override
	public void setDescriptionMap(Map<Locale, String> descriptionMap) {
		setDescriptionMap(descriptionMap, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setDescriptionMap(Map<Locale, String> descriptionMap,
		Locale defaultLocale) {
		if (descriptionMap == null) {
			return;
		}

		setDescription(LocalizationUtil.updateLocalization(descriptionMap,
				getDescription(), "Description",
				LocaleUtil.toLanguageId(defaultLocale)));
	}

	@JSON
	@Override
	public String getEngineKey() {
		if (_engineKey == null) {
			return "";
		}
		else {
			return _engineKey;
		}
	}

	@Override
	public void setEngineKey(String engineKey) {
		_columnBitmask |= ENGINEKEY_COLUMN_BITMASK;

		if (_originalEngineKey == null) {
			_originalEngineKey = _engineKey;
		}

		_engineKey = engineKey;
	}

	public String getOriginalEngineKey() {
		return GetterUtil.getString(_originalEngineKey);
	}

	@JSON
	@Override
	public boolean getPercentage() {
		return _percentage;
	}

	@JSON
	@Override
	public boolean isPercentage() {
		return _percentage;
	}

	@Override
	public void setPercentage(boolean percentage) {
		_percentage = percentage;
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

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			CommerceTaxMethod.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

		Map<Locale, String> nameMap = getNameMap();

		for (Map.Entry<Locale, String> entry : nameMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		Map<Locale, String> descriptionMap = getDescriptionMap();

		for (Map.Entry<Locale, String> entry : descriptionMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		return availableLanguageIds.toArray(new String[availableLanguageIds.size()]);
	}

	@Override
	public String getDefaultLanguageId() {
		String xml = getName();

		if (xml == null) {
			return "";
		}

		Locale defaultLocale = LocaleUtil.getSiteDefault();

		return LocalizationUtil.getDefaultLanguageId(xml, defaultLocale);
	}

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException {
		Locale defaultLocale = LocaleUtil.fromLanguageId(getDefaultLanguageId());

		Locale[] availableLocales = LocaleUtil.fromLanguageIds(getAvailableLanguageIds());

		Locale defaultImportLocale = LocalizationUtil.getDefaultImportLocale(CommerceTaxMethod.class.getName(),
				getPrimaryKey(), defaultLocale, availableLocales);

		prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	@SuppressWarnings("unused")
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException {
		Locale defaultLocale = LocaleUtil.getSiteDefault();

		String modelDefaultLanguageId = getDefaultLanguageId();

		String name = getName(defaultLocale);

		if (Validator.isNull(name)) {
			setName(getName(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setName(getName(defaultLocale), defaultLocale, defaultLocale);
		}

		String description = getDescription(defaultLocale);

		if (Validator.isNull(description)) {
			setDescription(getDescription(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setDescription(getDescription(defaultLocale), defaultLocale,
				defaultLocale);
		}
	}

	@Override
	public CommerceTaxMethod toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (CommerceTaxMethod)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		CommerceTaxMethodImpl commerceTaxMethodImpl = new CommerceTaxMethodImpl();

		commerceTaxMethodImpl.setCommerceTaxMethodId(getCommerceTaxMethodId());
		commerceTaxMethodImpl.setGroupId(getGroupId());
		commerceTaxMethodImpl.setCompanyId(getCompanyId());
		commerceTaxMethodImpl.setUserId(getUserId());
		commerceTaxMethodImpl.setUserName(getUserName());
		commerceTaxMethodImpl.setCreateDate(getCreateDate());
		commerceTaxMethodImpl.setModifiedDate(getModifiedDate());
		commerceTaxMethodImpl.setName(getName());
		commerceTaxMethodImpl.setDescription(getDescription());
		commerceTaxMethodImpl.setEngineKey(getEngineKey());
		commerceTaxMethodImpl.setPercentage(isPercentage());
		commerceTaxMethodImpl.setActive(isActive());

		commerceTaxMethodImpl.resetOriginalValues();

		return commerceTaxMethodImpl;
	}

	@Override
	public int compareTo(CommerceTaxMethod commerceTaxMethod) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(),
				commerceTaxMethod.getCreateDate());

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

		if (!(obj instanceof CommerceTaxMethod)) {
			return false;
		}

		CommerceTaxMethod commerceTaxMethod = (CommerceTaxMethod)obj;

		long primaryKey = commerceTaxMethod.getPrimaryKey();

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
		CommerceTaxMethodModelImpl commerceTaxMethodModelImpl = this;

		commerceTaxMethodModelImpl._originalGroupId = commerceTaxMethodModelImpl._groupId;

		commerceTaxMethodModelImpl._setOriginalGroupId = false;

		commerceTaxMethodModelImpl._setModifiedDate = false;

		commerceTaxMethodModelImpl._originalEngineKey = commerceTaxMethodModelImpl._engineKey;

		commerceTaxMethodModelImpl._originalActive = commerceTaxMethodModelImpl._active;

		commerceTaxMethodModelImpl._setOriginalActive = false;

		commerceTaxMethodModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<CommerceTaxMethod> toCacheModel() {
		CommerceTaxMethodCacheModel commerceTaxMethodCacheModel = new CommerceTaxMethodCacheModel();

		commerceTaxMethodCacheModel.commerceTaxMethodId = getCommerceTaxMethodId();

		commerceTaxMethodCacheModel.groupId = getGroupId();

		commerceTaxMethodCacheModel.companyId = getCompanyId();

		commerceTaxMethodCacheModel.userId = getUserId();

		commerceTaxMethodCacheModel.userName = getUserName();

		String userName = commerceTaxMethodCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceTaxMethodCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceTaxMethodCacheModel.createDate = createDate.getTime();
		}
		else {
			commerceTaxMethodCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceTaxMethodCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			commerceTaxMethodCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		commerceTaxMethodCacheModel.name = getName();

		String name = commerceTaxMethodCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			commerceTaxMethodCacheModel.name = null;
		}

		commerceTaxMethodCacheModel.description = getDescription();

		String description = commerceTaxMethodCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			commerceTaxMethodCacheModel.description = null;
		}

		commerceTaxMethodCacheModel.engineKey = getEngineKey();

		String engineKey = commerceTaxMethodCacheModel.engineKey;

		if ((engineKey != null) && (engineKey.length() == 0)) {
			commerceTaxMethodCacheModel.engineKey = null;
		}

		commerceTaxMethodCacheModel.percentage = isPercentage();

		commerceTaxMethodCacheModel.active = isActive();

		return commerceTaxMethodCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{commerceTaxMethodId=");
		sb.append(getCommerceTaxMethodId());
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
		sb.append(", name=");
		sb.append(getName());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", engineKey=");
		sb.append(getEngineKey());
		sb.append(", percentage=");
		sb.append(isPercentage());
		sb.append(", active=");
		sb.append(isActive());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(40);

		sb.append("<model><model-name>");
		sb.append("com.liferay.commerce.tax.model.CommerceTaxMethod");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>commerceTaxMethodId</column-name><column-value><![CDATA[");
		sb.append(getCommerceTaxMethodId());
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
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>engineKey</column-name><column-value><![CDATA[");
		sb.append(getEngineKey());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>percentage</column-name><column-value><![CDATA[");
		sb.append(isPercentage());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>active</column-name><column-value><![CDATA[");
		sb.append(isActive());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = CommerceTaxMethod.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			CommerceTaxMethod.class, ModelWrapper.class
		};
	private long _commerceTaxMethodId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _name;
	private String _nameCurrentLanguageId;
	private String _description;
	private String _descriptionCurrentLanguageId;
	private String _engineKey;
	private String _originalEngineKey;
	private boolean _percentage;
	private boolean _active;
	private boolean _originalActive;
	private boolean _setOriginalActive;
	private long _columnBitmask;
	private CommerceTaxMethod _escapedModel;
}