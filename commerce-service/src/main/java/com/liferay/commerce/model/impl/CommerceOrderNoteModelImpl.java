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

import com.liferay.commerce.model.CommerceOrderNote;
import com.liferay.commerce.model.CommerceOrderNoteModel;
import com.liferay.commerce.model.CommerceOrderNoteSoap;

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
 * The base model implementation for the CommerceOrderNote service. Represents a row in the &quot;CommerceOrderNote&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link CommerceOrderNoteModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceOrderNoteImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceOrderNoteImpl
 * @see CommerceOrderNote
 * @see CommerceOrderNoteModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class CommerceOrderNoteModelImpl extends BaseModelImpl<CommerceOrderNote>
	implements CommerceOrderNoteModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce order note model instance should use the {@link CommerceOrderNote} interface instead.
	 */
	public static final String TABLE_NAME = "CommerceOrderNote";
	public static final Object[][] TABLE_COLUMNS = {
			{ "externalReferenceCode", Types.VARCHAR },
			{ "commerceOrderNoteId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "commerceOrderId", Types.BIGINT },
			{ "content", Types.VARCHAR },
			{ "restricted", Types.BOOLEAN }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("externalReferenceCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("commerceOrderNoteId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("commerceOrderId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("content", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("restricted", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE = "create table CommerceOrderNote (externalReferenceCode VARCHAR(75) null,commerceOrderNoteId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,commerceOrderId LONG,content STRING null,restricted BOOLEAN)";
	public static final String TABLE_SQL_DROP = "drop table CommerceOrderNote";
	public static final String ORDER_BY_JPQL = " ORDER BY commerceOrderNote.createDate DESC";
	public static final String ORDER_BY_SQL = " ORDER BY CommerceOrderNote.createDate DESC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.commerce.model.CommerceOrderNote"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.commerce.model.CommerceOrderNote"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.commerce.model.CommerceOrderNote"),
			true);
	public static final long COMMERCEORDERID_COLUMN_BITMASK = 1L;
	public static final long COMPANYID_COLUMN_BITMASK = 2L;
	public static final long EXTERNALREFERENCECODE_COLUMN_BITMASK = 4L;
	public static final long RESTRICTED_COLUMN_BITMASK = 8L;
	public static final long CREATEDATE_COLUMN_BITMASK = 16L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static CommerceOrderNote toModel(CommerceOrderNoteSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		CommerceOrderNote model = new CommerceOrderNoteImpl();

		model.setExternalReferenceCode(soapModel.getExternalReferenceCode());
		model.setCommerceOrderNoteId(soapModel.getCommerceOrderNoteId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setCommerceOrderId(soapModel.getCommerceOrderId());
		model.setContent(soapModel.getContent());
		model.setRestricted(soapModel.isRestricted());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<CommerceOrderNote> toModels(
		CommerceOrderNoteSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<CommerceOrderNote> models = new ArrayList<CommerceOrderNote>(soapModels.length);

		for (CommerceOrderNoteSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.commerce.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.commerce.model.CommerceOrderNote"));

	public CommerceOrderNoteModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceOrderNoteId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceOrderNoteId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceOrderNoteId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceOrderNote.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceOrderNote.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("externalReferenceCode", getExternalReferenceCode());
		attributes.put("commerceOrderNoteId", getCommerceOrderNoteId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("commerceOrderId", getCommerceOrderId());
		attributes.put("content", getContent());
		attributes.put("restricted", isRestricted());

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

		Long commerceOrderNoteId = (Long)attributes.get("commerceOrderNoteId");

		if (commerceOrderNoteId != null) {
			setCommerceOrderNoteId(commerceOrderNoteId);
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

		Long commerceOrderId = (Long)attributes.get("commerceOrderId");

		if (commerceOrderId != null) {
			setCommerceOrderId(commerceOrderId);
		}

		String content = (String)attributes.get("content");

		if (content != null) {
			setContent(content);
		}

		Boolean restricted = (Boolean)attributes.get("restricted");

		if (restricted != null) {
			setRestricted(restricted);
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
	public long getCommerceOrderNoteId() {
		return _commerceOrderNoteId;
	}

	@Override
	public void setCommerceOrderNoteId(long commerceOrderNoteId) {
		_commerceOrderNoteId = commerceOrderNoteId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;
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
	public long getCommerceOrderId() {
		return _commerceOrderId;
	}

	@Override
	public void setCommerceOrderId(long commerceOrderId) {
		_columnBitmask |= COMMERCEORDERID_COLUMN_BITMASK;

		if (!_setOriginalCommerceOrderId) {
			_setOriginalCommerceOrderId = true;

			_originalCommerceOrderId = _commerceOrderId;
		}

		_commerceOrderId = commerceOrderId;
	}

	public long getOriginalCommerceOrderId() {
		return _originalCommerceOrderId;
	}

	@JSON
	@Override
	public String getContent() {
		if (_content == null) {
			return "";
		}
		else {
			return _content;
		}
	}

	@Override
	public void setContent(String content) {
		_content = content;
	}

	@JSON
	@Override
	public boolean getRestricted() {
		return _restricted;
	}

	@JSON
	@Override
	public boolean isRestricted() {
		return _restricted;
	}

	@Override
	public void setRestricted(boolean restricted) {
		_columnBitmask |= RESTRICTED_COLUMN_BITMASK;

		if (!_setOriginalRestricted) {
			_setOriginalRestricted = true;

			_originalRestricted = _restricted;
		}

		_restricted = restricted;
	}

	public boolean getOriginalRestricted() {
		return _originalRestricted;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			CommerceOrderNote.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceOrderNote toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (CommerceOrderNote)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		CommerceOrderNoteImpl commerceOrderNoteImpl = new CommerceOrderNoteImpl();

		commerceOrderNoteImpl.setExternalReferenceCode(getExternalReferenceCode());
		commerceOrderNoteImpl.setCommerceOrderNoteId(getCommerceOrderNoteId());
		commerceOrderNoteImpl.setGroupId(getGroupId());
		commerceOrderNoteImpl.setCompanyId(getCompanyId());
		commerceOrderNoteImpl.setUserId(getUserId());
		commerceOrderNoteImpl.setUserName(getUserName());
		commerceOrderNoteImpl.setCreateDate(getCreateDate());
		commerceOrderNoteImpl.setModifiedDate(getModifiedDate());
		commerceOrderNoteImpl.setCommerceOrderId(getCommerceOrderId());
		commerceOrderNoteImpl.setContent(getContent());
		commerceOrderNoteImpl.setRestricted(isRestricted());

		commerceOrderNoteImpl.resetOriginalValues();

		return commerceOrderNoteImpl;
	}

	@Override
	public int compareTo(CommerceOrderNote commerceOrderNote) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(),
				commerceOrderNote.getCreateDate());

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

		if (!(obj instanceof CommerceOrderNote)) {
			return false;
		}

		CommerceOrderNote commerceOrderNote = (CommerceOrderNote)obj;

		long primaryKey = commerceOrderNote.getPrimaryKey();

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
		CommerceOrderNoteModelImpl commerceOrderNoteModelImpl = this;

		commerceOrderNoteModelImpl._originalExternalReferenceCode = commerceOrderNoteModelImpl._externalReferenceCode;

		commerceOrderNoteModelImpl._originalCompanyId = commerceOrderNoteModelImpl._companyId;

		commerceOrderNoteModelImpl._setOriginalCompanyId = false;

		commerceOrderNoteModelImpl._setModifiedDate = false;

		commerceOrderNoteModelImpl._originalCommerceOrderId = commerceOrderNoteModelImpl._commerceOrderId;

		commerceOrderNoteModelImpl._setOriginalCommerceOrderId = false;

		commerceOrderNoteModelImpl._originalRestricted = commerceOrderNoteModelImpl._restricted;

		commerceOrderNoteModelImpl._setOriginalRestricted = false;

		commerceOrderNoteModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<CommerceOrderNote> toCacheModel() {
		CommerceOrderNoteCacheModel commerceOrderNoteCacheModel = new CommerceOrderNoteCacheModel();

		commerceOrderNoteCacheModel.externalReferenceCode = getExternalReferenceCode();

		String externalReferenceCode = commerceOrderNoteCacheModel.externalReferenceCode;

		if ((externalReferenceCode != null) &&
				(externalReferenceCode.length() == 0)) {
			commerceOrderNoteCacheModel.externalReferenceCode = null;
		}

		commerceOrderNoteCacheModel.commerceOrderNoteId = getCommerceOrderNoteId();

		commerceOrderNoteCacheModel.groupId = getGroupId();

		commerceOrderNoteCacheModel.companyId = getCompanyId();

		commerceOrderNoteCacheModel.userId = getUserId();

		commerceOrderNoteCacheModel.userName = getUserName();

		String userName = commerceOrderNoteCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceOrderNoteCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceOrderNoteCacheModel.createDate = createDate.getTime();
		}
		else {
			commerceOrderNoteCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceOrderNoteCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			commerceOrderNoteCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		commerceOrderNoteCacheModel.commerceOrderId = getCommerceOrderId();

		commerceOrderNoteCacheModel.content = getContent();

		String content = commerceOrderNoteCacheModel.content;

		if ((content != null) && (content.length() == 0)) {
			commerceOrderNoteCacheModel.content = null;
		}

		commerceOrderNoteCacheModel.restricted = isRestricted();

		return commerceOrderNoteCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{externalReferenceCode=");
		sb.append(getExternalReferenceCode());
		sb.append(", commerceOrderNoteId=");
		sb.append(getCommerceOrderNoteId());
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
		sb.append(", commerceOrderId=");
		sb.append(getCommerceOrderId());
		sb.append(", content=");
		sb.append(getContent());
		sb.append(", restricted=");
		sb.append(isRestricted());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(37);

		sb.append("<model><model-name>");
		sb.append("com.liferay.commerce.model.CommerceOrderNote");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>externalReferenceCode</column-name><column-value><![CDATA[");
		sb.append(getExternalReferenceCode());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>commerceOrderNoteId</column-name><column-value><![CDATA[");
		sb.append(getCommerceOrderNoteId());
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
			"<column><column-name>commerceOrderId</column-name><column-value><![CDATA[");
		sb.append(getCommerceOrderId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>content</column-name><column-value><![CDATA[");
		sb.append(getContent());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>restricted</column-name><column-value><![CDATA[");
		sb.append(isRestricted());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = CommerceOrderNote.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			CommerceOrderNote.class, ModelWrapper.class
		};
	private String _externalReferenceCode;
	private String _originalExternalReferenceCode;
	private long _commerceOrderNoteId;
	private long _groupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _commerceOrderId;
	private long _originalCommerceOrderId;
	private boolean _setOriginalCommerceOrderId;
	private String _content;
	private boolean _restricted;
	private boolean _originalRestricted;
	private boolean _setOriginalRestricted;
	private long _columnBitmask;
	private CommerceOrderNote _escapedModel;
}