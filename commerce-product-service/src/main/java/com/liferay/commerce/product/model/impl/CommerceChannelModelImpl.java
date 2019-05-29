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

package com.liferay.commerce.product.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.model.CommerceChannelModel;
import com.liferay.commerce.product.model.CommerceChannelSoap;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

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
 * The base model implementation for the CommerceChannel service. Represents a row in the &quot;CommerceChannel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link CommerceChannelModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceChannelImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see CommerceChannelImpl
 * @see CommerceChannel
 * @see CommerceChannelModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class CommerceChannelModelImpl extends BaseModelImpl<CommerceChannel>
	implements CommerceChannelModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce channel model instance should use the {@link CommerceChannel} interface instead.
	 */
	public static final String TABLE_NAME = "CommerceChannel";
	public static final Object[][] TABLE_COLUMNS = {
			{ "externalReferenceCode", Types.VARCHAR },
			{ "commerceChannelId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "name", Types.VARCHAR },
			{ "siteGroupId", Types.BIGINT },
			{ "type_", Types.VARCHAR },
			{ "typeSettings", Types.VARCHAR },
			{ "commerceCurrencyCode", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("externalReferenceCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("commerceChannelId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("siteGroupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("type_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("typeSettings", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("commerceCurrencyCode", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table CommerceChannel (externalReferenceCode VARCHAR(75) null,commerceChannelId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,name VARCHAR(75) null,siteGroupId LONG,type_ VARCHAR(75) null,typeSettings VARCHAR(75) null,commerceCurrencyCode VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table CommerceChannel";
	public static final String ORDER_BY_JPQL = " ORDER BY commerceChannel.createDate DESC";
	public static final String ORDER_BY_SQL = " ORDER BY CommerceChannel.createDate DESC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.product.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.commerce.product.model.CommerceChannel"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.product.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.commerce.product.model.CommerceChannel"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.product.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.commerce.product.model.CommerceChannel"),
			true);
	public static final long COMPANYID_COLUMN_BITMASK = 1L;
	public static final long EXTERNALREFERENCECODE_COLUMN_BITMASK = 2L;
	public static final long SITEGROUPID_COLUMN_BITMASK = 4L;
	public static final long CREATEDATE_COLUMN_BITMASK = 8L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static CommerceChannel toModel(CommerceChannelSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		CommerceChannel model = new CommerceChannelImpl();

		model.setExternalReferenceCode(soapModel.getExternalReferenceCode());
		model.setCommerceChannelId(soapModel.getCommerceChannelId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setName(soapModel.getName());
		model.setSiteGroupId(soapModel.getSiteGroupId());
		model.setType(soapModel.getType());
		model.setTypeSettings(soapModel.getTypeSettings());
		model.setCommerceCurrencyCode(soapModel.getCommerceCurrencyCode());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<CommerceChannel> toModels(
		CommerceChannelSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<CommerceChannel> models = new ArrayList<CommerceChannel>(soapModels.length);

		for (CommerceChannelSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.commerce.product.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.commerce.product.model.CommerceChannel"));

	public CommerceChannelModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceChannelId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceChannelId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceChannelId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
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

		attributes.put("externalReferenceCode", getExternalReferenceCode());
		attributes.put("commerceChannelId", getCommerceChannelId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("siteGroupId", getSiteGroupId());
		attributes.put("type", getType());
		attributes.put("typeSettings", getTypeSettings());
		attributes.put("commerceCurrencyCode", getCommerceCurrencyCode());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String externalReferenceCode = (String)attributes.get(
				"externalReferenceCode");

		if (externalReferenceCode != null) {
			setExternalReferenceCode(externalReferenceCode);
		}

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

		Long siteGroupId = (Long)attributes.get("siteGroupId");

		if (siteGroupId != null) {
			setSiteGroupId(siteGroupId);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String typeSettings = (String)attributes.get("typeSettings");

		if (typeSettings != null) {
			setTypeSettings(typeSettings);
		}

		String commerceCurrencyCode = (String)attributes.get(
				"commerceCurrencyCode");

		if (commerceCurrencyCode != null) {
			setCommerceCurrencyCode(commerceCurrencyCode);
		}
	}

	@JSON
	@Override
	public String getExternalReferenceCode() {
		if (_externalReferenceCode == null) {
			return "";
		}
		else {
			return _externalReferenceCode;
		}
	}

	@Override
	public void setExternalReferenceCode(String externalReferenceCode) {
		_columnBitmask |= EXTERNALREFERENCECODE_COLUMN_BITMASK;

		if (_originalExternalReferenceCode == null) {
			_originalExternalReferenceCode = _externalReferenceCode;
		}

		_externalReferenceCode = externalReferenceCode;
	}

	public String getOriginalExternalReferenceCode() {
		return GetterUtil.getString(_originalExternalReferenceCode);
	}

	@JSON
	@Override
	public long getCommerceChannelId() {
		return _commerceChannelId;
	}

	@Override
	public void setCommerceChannelId(long commerceChannelId) {
		_commerceChannelId = commerceChannelId;
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
	public void setName(String name) {
		_name = name;
	}

	@JSON
	@Override
	public long getSiteGroupId() {
		return _siteGroupId;
	}

	@Override
	public void setSiteGroupId(long siteGroupId) {
		_columnBitmask |= SITEGROUPID_COLUMN_BITMASK;

		if (!_setOriginalSiteGroupId) {
			_setOriginalSiteGroupId = true;

			_originalSiteGroupId = _siteGroupId;
		}

		_siteGroupId = siteGroupId;
	}

	public long getOriginalSiteGroupId() {
		return _originalSiteGroupId;
	}

	@JSON
	@Override
	public String getType() {
		if (_type == null) {
			return "";
		}
		else {
			return _type;
		}
	}

	@Override
	public void setType(String type) {
		_type = type;
	}

	@JSON
	@Override
	public String getTypeSettings() {
		if (_typeSettings == null) {
			return "";
		}
		else {
			return _typeSettings;
		}
	}

	@Override
	public void setTypeSettings(String typeSettings) {
		_typeSettings = typeSettings;
	}

	@JSON
	@Override
	public String getCommerceCurrencyCode() {
		if (_commerceCurrencyCode == null) {
			return "";
		}
		else {
			return _commerceCurrencyCode;
		}
	}

	@Override
	public void setCommerceCurrencyCode(String commerceCurrencyCode) {
		_commerceCurrencyCode = commerceCurrencyCode;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			CommerceChannel.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceChannel toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (CommerceChannel)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		CommerceChannelImpl commerceChannelImpl = new CommerceChannelImpl();

		commerceChannelImpl.setExternalReferenceCode(getExternalReferenceCode());
		commerceChannelImpl.setCommerceChannelId(getCommerceChannelId());
		commerceChannelImpl.setCompanyId(getCompanyId());
		commerceChannelImpl.setUserId(getUserId());
		commerceChannelImpl.setUserName(getUserName());
		commerceChannelImpl.setCreateDate(getCreateDate());
		commerceChannelImpl.setModifiedDate(getModifiedDate());
		commerceChannelImpl.setName(getName());
		commerceChannelImpl.setSiteGroupId(getSiteGroupId());
		commerceChannelImpl.setType(getType());
		commerceChannelImpl.setTypeSettings(getTypeSettings());
		commerceChannelImpl.setCommerceCurrencyCode(getCommerceCurrencyCode());

		commerceChannelImpl.resetOriginalValues();

		return commerceChannelImpl;
	}

	@Override
	public int compareTo(CommerceChannel commerceChannel) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(),
				commerceChannel.getCreateDate());

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

		if (!(obj instanceof CommerceChannel)) {
			return false;
		}

		CommerceChannel commerceChannel = (CommerceChannel)obj;

		long primaryKey = commerceChannel.getPrimaryKey();

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
		CommerceChannelModelImpl commerceChannelModelImpl = this;

		commerceChannelModelImpl._originalExternalReferenceCode = commerceChannelModelImpl._externalReferenceCode;

		commerceChannelModelImpl._originalCompanyId = commerceChannelModelImpl._companyId;

		commerceChannelModelImpl._setOriginalCompanyId = false;

		commerceChannelModelImpl._setModifiedDate = false;

		commerceChannelModelImpl._originalSiteGroupId = commerceChannelModelImpl._siteGroupId;

		commerceChannelModelImpl._setOriginalSiteGroupId = false;

		commerceChannelModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<CommerceChannel> toCacheModel() {
		CommerceChannelCacheModel commerceChannelCacheModel = new CommerceChannelCacheModel();

		commerceChannelCacheModel.externalReferenceCode = getExternalReferenceCode();

		String externalReferenceCode = commerceChannelCacheModel.externalReferenceCode;

		if ((externalReferenceCode != null) &&
				(externalReferenceCode.length() == 0)) {
			commerceChannelCacheModel.externalReferenceCode = null;
		}

		commerceChannelCacheModel.commerceChannelId = getCommerceChannelId();

		commerceChannelCacheModel.companyId = getCompanyId();

		commerceChannelCacheModel.userId = getUserId();

		commerceChannelCacheModel.userName = getUserName();

		String userName = commerceChannelCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceChannelCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceChannelCacheModel.createDate = createDate.getTime();
		}
		else {
			commerceChannelCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceChannelCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			commerceChannelCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		commerceChannelCacheModel.name = getName();

		String name = commerceChannelCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			commerceChannelCacheModel.name = null;
		}

		commerceChannelCacheModel.siteGroupId = getSiteGroupId();

		commerceChannelCacheModel.type = getType();

		String type = commerceChannelCacheModel.type;

		if ((type != null) && (type.length() == 0)) {
			commerceChannelCacheModel.type = null;
		}

		commerceChannelCacheModel.typeSettings = getTypeSettings();

		String typeSettings = commerceChannelCacheModel.typeSettings;

		if ((typeSettings != null) && (typeSettings.length() == 0)) {
			commerceChannelCacheModel.typeSettings = null;
		}

		commerceChannelCacheModel.commerceCurrencyCode = getCommerceCurrencyCode();

		String commerceCurrencyCode = commerceChannelCacheModel.commerceCurrencyCode;

		if ((commerceCurrencyCode != null) &&
				(commerceCurrencyCode.length() == 0)) {
			commerceChannelCacheModel.commerceCurrencyCode = null;
		}

		return commerceChannelCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{externalReferenceCode=");
		sb.append(getExternalReferenceCode());
		sb.append(", commerceChannelId=");
		sb.append(getCommerceChannelId());
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
		sb.append(", siteGroupId=");
		sb.append(getSiteGroupId());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", typeSettings=");
		sb.append(getTypeSettings());
		sb.append(", commerceCurrencyCode=");
		sb.append(getCommerceCurrencyCode());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(40);

		sb.append("<model><model-name>");
		sb.append("com.liferay.commerce.product.model.CommerceChannel");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>externalReferenceCode</column-name><column-value><![CDATA[");
		sb.append(getExternalReferenceCode());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>commerceChannelId</column-name><column-value><![CDATA[");
		sb.append(getCommerceChannelId());
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
			"<column><column-name>siteGroupId</column-name><column-value><![CDATA[");
		sb.append(getSiteGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>typeSettings</column-name><column-value><![CDATA[");
		sb.append(getTypeSettings());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>commerceCurrencyCode</column-name><column-value><![CDATA[");
		sb.append(getCommerceCurrencyCode());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = CommerceChannel.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			CommerceChannel.class, ModelWrapper.class
		};
	private String _externalReferenceCode;
	private String _originalExternalReferenceCode;
	private long _commerceChannelId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _name;
	private long _siteGroupId;
	private long _originalSiteGroupId;
	private boolean _setOriginalSiteGroupId;
	private String _type;
	private String _typeSettings;
	private String _commerceCurrencyCode;
	private long _columnBitmask;
	private CommerceChannel _escapedModel;
}