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

package com.liferay.commerce.application.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.application.model.CommerceApplicationModel;
import com.liferay.commerce.application.model.CommerceApplicationModelModel;
import com.liferay.commerce.application.model.CommerceApplicationModelSoap;

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
 * The base model implementation for the CommerceApplicationModel service. Represents a row in the &quot;CommerceApplicationModel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link CommerceApplicationModelModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceApplicationModelImpl}.
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceApplicationModelImpl
 * @see CommerceApplicationModel
 * @see CommerceApplicationModelModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class CommerceApplicationModelModelImpl extends BaseModelImpl<CommerceApplicationModel>
	implements CommerceApplicationModelModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce application model model instance should use the {@link CommerceApplicationModel} interface instead.
	 */
	public static final String TABLE_NAME = "CommerceApplicationModel";
	public static final Object[][] TABLE_COLUMNS = {
			{ "commerceApplicationModelId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "commerceApplicationBrandId", Types.BIGINT },
			{ "name", Types.VARCHAR },
			{ "year", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("commerceApplicationModelId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("commerceApplicationBrandId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("year", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table CommerceApplicationModel (commerceApplicationModelId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,commerceApplicationBrandId LONG,name VARCHAR(75) null,year VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table CommerceApplicationModel";
	public static final String ORDER_BY_JPQL = " ORDER BY commerceApplicationModel.name ASC";
	public static final String ORDER_BY_SQL = " ORDER BY CommerceApplicationModel.name ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.application.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.commerce.application.model.CommerceApplicationModel"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.application.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.commerce.application.model.CommerceApplicationModel"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.application.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.commerce.application.model.CommerceApplicationModel"),
			true);
	public static final long COMMERCEAPPLICATIONBRANDID_COLUMN_BITMASK = 1L;
	public static final long COMPANYID_COLUMN_BITMASK = 2L;
	public static final long NAME_COLUMN_BITMASK = 4L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static CommerceApplicationModel toModel(
		CommerceApplicationModelSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		CommerceApplicationModel model = new CommerceApplicationModelImpl();

		model.setCommerceApplicationModelId(soapModel.getCommerceApplicationModelId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setCommerceApplicationBrandId(soapModel.getCommerceApplicationBrandId());
		model.setName(soapModel.getName());
		model.setYear(soapModel.getYear());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<CommerceApplicationModel> toModels(
		CommerceApplicationModelSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<CommerceApplicationModel> models = new ArrayList<CommerceApplicationModel>(soapModels.length);

		for (CommerceApplicationModelSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.commerce.application.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.commerce.application.model.CommerceApplicationModel"));

	public CommerceApplicationModelModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceApplicationModelId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceApplicationModelId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceApplicationModelId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceApplicationModel.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceApplicationModel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("commerceApplicationModelId",
			getCommerceApplicationModelId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("commerceApplicationBrandId",
			getCommerceApplicationBrandId());
		attributes.put("name", getName());
		attributes.put("year", getYear());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long commerceApplicationModelId = (Long)attributes.get(
				"commerceApplicationModelId");

		if (commerceApplicationModelId != null) {
			setCommerceApplicationModelId(commerceApplicationModelId);
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

		Long commerceApplicationBrandId = (Long)attributes.get(
				"commerceApplicationBrandId");

		if (commerceApplicationBrandId != null) {
			setCommerceApplicationBrandId(commerceApplicationBrandId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String year = (String)attributes.get("year");

		if (year != null) {
			setYear(year);
		}
	}

	@JSON
	@Override
	public long getCommerceApplicationModelId() {
		return _commerceApplicationModelId;
	}

	@Override
	public void setCommerceApplicationModelId(long commerceApplicationModelId) {
		_commerceApplicationModelId = commerceApplicationModelId;
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
	public long getCommerceApplicationBrandId() {
		return _commerceApplicationBrandId;
	}

	@Override
	public void setCommerceApplicationBrandId(long commerceApplicationBrandId) {
		_columnBitmask |= COMMERCEAPPLICATIONBRANDID_COLUMN_BITMASK;

		if (!_setOriginalCommerceApplicationBrandId) {
			_setOriginalCommerceApplicationBrandId = true;

			_originalCommerceApplicationBrandId = _commerceApplicationBrandId;
		}

		_commerceApplicationBrandId = commerceApplicationBrandId;
	}

	public long getOriginalCommerceApplicationBrandId() {
		return _originalCommerceApplicationBrandId;
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
		_columnBitmask = -1L;

		_name = name;
	}

	@JSON
	@Override
	public String getYear() {
		if (_year == null) {
			return "";
		}
		else {
			return _year;
		}
	}

	@Override
	public void setYear(String year) {
		_year = year;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			CommerceApplicationModel.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceApplicationModel toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (CommerceApplicationModel)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		CommerceApplicationModelImpl commerceApplicationModelImpl = new CommerceApplicationModelImpl();

		commerceApplicationModelImpl.setCommerceApplicationModelId(getCommerceApplicationModelId());
		commerceApplicationModelImpl.setCompanyId(getCompanyId());
		commerceApplicationModelImpl.setUserId(getUserId());
		commerceApplicationModelImpl.setUserName(getUserName());
		commerceApplicationModelImpl.setCreateDate(getCreateDate());
		commerceApplicationModelImpl.setModifiedDate(getModifiedDate());
		commerceApplicationModelImpl.setCommerceApplicationBrandId(getCommerceApplicationBrandId());
		commerceApplicationModelImpl.setName(getName());
		commerceApplicationModelImpl.setYear(getYear());

		commerceApplicationModelImpl.resetOriginalValues();

		return commerceApplicationModelImpl;
	}

	@Override
	public int compareTo(CommerceApplicationModel commerceApplicationModel) {
		int value = 0;

		value = getName().compareTo(commerceApplicationModel.getName());

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

		if (!(obj instanceof CommerceApplicationModel)) {
			return false;
		}

		CommerceApplicationModel commerceApplicationModel = (CommerceApplicationModel)obj;

		long primaryKey = commerceApplicationModel.getPrimaryKey();

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
		CommerceApplicationModelModelImpl commerceApplicationModelModelImpl = this;

		commerceApplicationModelModelImpl._originalCompanyId = commerceApplicationModelModelImpl._companyId;

		commerceApplicationModelModelImpl._setOriginalCompanyId = false;

		commerceApplicationModelModelImpl._setModifiedDate = false;

		commerceApplicationModelModelImpl._originalCommerceApplicationBrandId = commerceApplicationModelModelImpl._commerceApplicationBrandId;

		commerceApplicationModelModelImpl._setOriginalCommerceApplicationBrandId = false;

		commerceApplicationModelModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<CommerceApplicationModel> toCacheModel() {
		CommerceApplicationModelCacheModel commerceApplicationModelCacheModel = new CommerceApplicationModelCacheModel();

		commerceApplicationModelCacheModel.commerceApplicationModelId = getCommerceApplicationModelId();

		commerceApplicationModelCacheModel.companyId = getCompanyId();

		commerceApplicationModelCacheModel.userId = getUserId();

		commerceApplicationModelCacheModel.userName = getUserName();

		String userName = commerceApplicationModelCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceApplicationModelCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceApplicationModelCacheModel.createDate = createDate.getTime();
		}
		else {
			commerceApplicationModelCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceApplicationModelCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			commerceApplicationModelCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		commerceApplicationModelCacheModel.commerceApplicationBrandId = getCommerceApplicationBrandId();

		commerceApplicationModelCacheModel.name = getName();

		String name = commerceApplicationModelCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			commerceApplicationModelCacheModel.name = null;
		}

		commerceApplicationModelCacheModel.year = getYear();

		String year = commerceApplicationModelCacheModel.year;

		if ((year != null) && (year.length() == 0)) {
			commerceApplicationModelCacheModel.year = null;
		}

		return commerceApplicationModelCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{commerceApplicationModelId=");
		sb.append(getCommerceApplicationModelId());
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
		sb.append(", commerceApplicationBrandId=");
		sb.append(getCommerceApplicationBrandId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", year=");
		sb.append(getYear());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(31);

		sb.append("<model><model-name>");
		sb.append(
			"com.liferay.commerce.application.model.CommerceApplicationModel");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>commerceApplicationModelId</column-name><column-value><![CDATA[");
		sb.append(getCommerceApplicationModelId());
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
			"<column><column-name>commerceApplicationBrandId</column-name><column-value><![CDATA[");
		sb.append(getCommerceApplicationBrandId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>year</column-name><column-value><![CDATA[");
		sb.append(getYear());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = CommerceApplicationModel.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			CommerceApplicationModel.class, ModelWrapper.class
		};
	private long _commerceApplicationModelId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _commerceApplicationBrandId;
	private long _originalCommerceApplicationBrandId;
	private boolean _setOriginalCommerceApplicationBrandId;
	private String _name;
	private String _year;
	private long _columnBitmask;
	private CommerceApplicationModel _escapedModel;
}