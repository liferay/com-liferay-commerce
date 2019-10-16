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

package com.liferay.commerce.account.model.impl;

import com.liferay.commerce.account.model.CommerceAccountGroup;
import com.liferay.commerce.account.model.CommerceAccountGroupModel;
import com.liferay.commerce.account.model.CommerceAccountGroupSoap;
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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the CommerceAccountGroup service. Represents a row in the &quot;CommerceAccountGroup&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>CommerceAccountGroupModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceAccountGroupImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see CommerceAccountGroupImpl
 * @generated
 */
@JSON(strict = true)
public class CommerceAccountGroupModelImpl
	extends BaseModelImpl<CommerceAccountGroup>
	implements CommerceAccountGroupModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce account group model instance should use the <code>CommerceAccountGroup</code> interface instead.
	 */
	public static final String TABLE_NAME = "CommerceAccountGroup";

	public static final Object[][] TABLE_COLUMNS = {
		{"externalReferenceCode", Types.VARCHAR},
		{"commerceAccountGroupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"name", Types.VARCHAR}, {"type_", Types.INTEGER},
		{"system", Types.BOOLEAN}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("externalReferenceCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("commerceAccountGroupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("type_", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("system", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CommerceAccountGroup (externalReferenceCode VARCHAR(75) null,commerceAccountGroupId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,name VARCHAR(75) null,type_ INTEGER,system BOOLEAN)";

	public static final String TABLE_SQL_DROP =
		"drop table CommerceAccountGroup";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commerceAccountGroup.name ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CommerceAccountGroup.name ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.commerce.account.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.com.liferay.commerce.account.model.CommerceAccountGroup"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.commerce.account.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.com.liferay.commerce.account.model.CommerceAccountGroup"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.commerce.account.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.com.liferay.commerce.account.model.CommerceAccountGroup"),
		true);

	public static final long COMMERCEACCOUNTGROUPID_COLUMN_BITMASK = 1L;

	public static final long COMPANYID_COLUMN_BITMASK = 2L;

	public static final long EXTERNALREFERENCECODE_COLUMN_BITMASK = 4L;

	public static final long TYPE_COLUMN_BITMASK = 8L;

	public static final long NAME_COLUMN_BITMASK = 16L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static CommerceAccountGroup toModel(
		CommerceAccountGroupSoap soapModel) {

		if (soapModel == null) {
			return null;
		}

		CommerceAccountGroup model = new CommerceAccountGroupImpl();

		model.setExternalReferenceCode(soapModel.getExternalReferenceCode());
		model.setCommerceAccountGroupId(soapModel.getCommerceAccountGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setName(soapModel.getName());
		model.setType(soapModel.getType());
		model.setSystem(soapModel.isSystem());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<CommerceAccountGroup> toModels(
		CommerceAccountGroupSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<CommerceAccountGroup> models = new ArrayList<CommerceAccountGroup>(
			soapModels.length);

		for (CommerceAccountGroupSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.commerce.account.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.commerce.account.model.CommerceAccountGroup"));

	public CommerceAccountGroupModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceAccountGroupId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceAccountGroupId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceAccountGroupId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceAccountGroup.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceAccountGroup.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommerceAccountGroup, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CommerceAccountGroup, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceAccountGroup, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((CommerceAccountGroup)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommerceAccountGroup, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommerceAccountGroup, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommerceAccountGroup)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommerceAccountGroup, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommerceAccountGroup, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, CommerceAccountGroup>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			CommerceAccountGroup.class.getClassLoader(),
			CommerceAccountGroup.class, ModelWrapper.class);

		try {
			Constructor<CommerceAccountGroup> constructor =
				(Constructor<CommerceAccountGroup>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException roe) {
					throw new InternalError(roe);
				}
			};
		}
		catch (NoSuchMethodException nsme) {
			throw new InternalError(nsme);
		}
	}

	private static final Map<String, Function<CommerceAccountGroup, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<CommerceAccountGroup, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<CommerceAccountGroup, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<CommerceAccountGroup, Object>>();
		Map<String, BiConsumer<CommerceAccountGroup, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<CommerceAccountGroup, ?>>();

		attributeGetterFunctions.put(
			"externalReferenceCode",
			new Function<CommerceAccountGroup, Object>() {

				@Override
				public Object apply(CommerceAccountGroup commerceAccountGroup) {
					return commerceAccountGroup.getExternalReferenceCode();
				}

			});
		attributeSetterBiConsumers.put(
			"externalReferenceCode",
			new BiConsumer<CommerceAccountGroup, Object>() {

				@Override
				public void accept(
					CommerceAccountGroup commerceAccountGroup,
					Object externalReferenceCodeObject) {

					commerceAccountGroup.setExternalReferenceCode(
						(String)externalReferenceCodeObject);
				}

			});
		attributeGetterFunctions.put(
			"commerceAccountGroupId",
			new Function<CommerceAccountGroup, Object>() {

				@Override
				public Object apply(CommerceAccountGroup commerceAccountGroup) {
					return commerceAccountGroup.getCommerceAccountGroupId();
				}

			});
		attributeSetterBiConsumers.put(
			"commerceAccountGroupId",
			new BiConsumer<CommerceAccountGroup, Object>() {

				@Override
				public void accept(
					CommerceAccountGroup commerceAccountGroup,
					Object commerceAccountGroupIdObject) {

					commerceAccountGroup.setCommerceAccountGroupId(
						(Long)commerceAccountGroupIdObject);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<CommerceAccountGroup, Object>() {

				@Override
				public Object apply(CommerceAccountGroup commerceAccountGroup) {
					return commerceAccountGroup.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<CommerceAccountGroup, Object>() {

				@Override
				public void accept(
					CommerceAccountGroup commerceAccountGroup,
					Object companyIdObject) {

					commerceAccountGroup.setCompanyId((Long)companyIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userId",
			new Function<CommerceAccountGroup, Object>() {

				@Override
				public Object apply(CommerceAccountGroup commerceAccountGroup) {
					return commerceAccountGroup.getUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"userId",
			new BiConsumer<CommerceAccountGroup, Object>() {

				@Override
				public void accept(
					CommerceAccountGroup commerceAccountGroup,
					Object userIdObject) {

					commerceAccountGroup.setUserId((Long)userIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userName",
			new Function<CommerceAccountGroup, Object>() {

				@Override
				public Object apply(CommerceAccountGroup commerceAccountGroup) {
					return commerceAccountGroup.getUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"userName",
			new BiConsumer<CommerceAccountGroup, Object>() {

				@Override
				public void accept(
					CommerceAccountGroup commerceAccountGroup,
					Object userNameObject) {

					commerceAccountGroup.setUserName((String)userNameObject);
				}

			});
		attributeGetterFunctions.put(
			"createDate",
			new Function<CommerceAccountGroup, Object>() {

				@Override
				public Object apply(CommerceAccountGroup commerceAccountGroup) {
					return commerceAccountGroup.getCreateDate();
				}

			});
		attributeSetterBiConsumers.put(
			"createDate",
			new BiConsumer<CommerceAccountGroup, Object>() {

				@Override
				public void accept(
					CommerceAccountGroup commerceAccountGroup,
					Object createDateObject) {

					commerceAccountGroup.setCreateDate((Date)createDateObject);
				}

			});
		attributeGetterFunctions.put(
			"modifiedDate",
			new Function<CommerceAccountGroup, Object>() {

				@Override
				public Object apply(CommerceAccountGroup commerceAccountGroup) {
					return commerceAccountGroup.getModifiedDate();
				}

			});
		attributeSetterBiConsumers.put(
			"modifiedDate",
			new BiConsumer<CommerceAccountGroup, Object>() {

				@Override
				public void accept(
					CommerceAccountGroup commerceAccountGroup,
					Object modifiedDateObject) {

					commerceAccountGroup.setModifiedDate(
						(Date)modifiedDateObject);
				}

			});
		attributeGetterFunctions.put(
			"name",
			new Function<CommerceAccountGroup, Object>() {

				@Override
				public Object apply(CommerceAccountGroup commerceAccountGroup) {
					return commerceAccountGroup.getName();
				}

			});
		attributeSetterBiConsumers.put(
			"name",
			new BiConsumer<CommerceAccountGroup, Object>() {

				@Override
				public void accept(
					CommerceAccountGroup commerceAccountGroup,
					Object nameObject) {

					commerceAccountGroup.setName((String)nameObject);
				}

			});
		attributeGetterFunctions.put(
			"type",
			new Function<CommerceAccountGroup, Object>() {

				@Override
				public Object apply(CommerceAccountGroup commerceAccountGroup) {
					return commerceAccountGroup.getType();
				}

			});
		attributeSetterBiConsumers.put(
			"type",
			new BiConsumer<CommerceAccountGroup, Object>() {

				@Override
				public void accept(
					CommerceAccountGroup commerceAccountGroup,
					Object typeObject) {

					commerceAccountGroup.setType((Integer)typeObject);
				}

			});
		attributeGetterFunctions.put(
			"system",
			new Function<CommerceAccountGroup, Object>() {

				@Override
				public Object apply(CommerceAccountGroup commerceAccountGroup) {
					return commerceAccountGroup.getSystem();
				}

			});
		attributeSetterBiConsumers.put(
			"system",
			new BiConsumer<CommerceAccountGroup, Object>() {

				@Override
				public void accept(
					CommerceAccountGroup commerceAccountGroup,
					Object systemObject) {

					commerceAccountGroup.setSystem((Boolean)systemObject);
				}

			});

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
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
	public long getCommerceAccountGroupId() {
		return _commerceAccountGroupId;
	}

	@Override
	public void setCommerceAccountGroupId(long commerceAccountGroupId) {
		_columnBitmask |= COMMERCEACCOUNTGROUPID_COLUMN_BITMASK;

		if (!_setOriginalCommerceAccountGroupId) {
			_setOriginalCommerceAccountGroupId = true;

			_originalCommerceAccountGroupId = _commerceAccountGroupId;
		}

		_commerceAccountGroupId = commerceAccountGroupId;
	}

	public long getOriginalCommerceAccountGroupId() {
		return _originalCommerceAccountGroupId;
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
	public int getType() {
		return _type;
	}

	@Override
	public void setType(int type) {
		_columnBitmask |= TYPE_COLUMN_BITMASK;

		if (!_setOriginalType) {
			_setOriginalType = true;

			_originalType = _type;
		}

		_type = type;
	}

	public int getOriginalType() {
		return _originalType;
	}

	@JSON
	@Override
	public boolean getSystem() {
		return _system;
	}

	@JSON
	@Override
	public boolean isSystem() {
		return _system;
	}

	@Override
	public void setSystem(boolean system) {
		_system = system;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), CommerceAccountGroup.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceAccountGroup toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommerceAccountGroup>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		CommerceAccountGroupImpl commerceAccountGroupImpl =
			new CommerceAccountGroupImpl();

		commerceAccountGroupImpl.setExternalReferenceCode(
			getExternalReferenceCode());
		commerceAccountGroupImpl.setCommerceAccountGroupId(
			getCommerceAccountGroupId());
		commerceAccountGroupImpl.setCompanyId(getCompanyId());
		commerceAccountGroupImpl.setUserId(getUserId());
		commerceAccountGroupImpl.setUserName(getUserName());
		commerceAccountGroupImpl.setCreateDate(getCreateDate());
		commerceAccountGroupImpl.setModifiedDate(getModifiedDate());
		commerceAccountGroupImpl.setName(getName());
		commerceAccountGroupImpl.setType(getType());
		commerceAccountGroupImpl.setSystem(isSystem());

		commerceAccountGroupImpl.resetOriginalValues();

		return commerceAccountGroupImpl;
	}

	@Override
	public int compareTo(CommerceAccountGroup commerceAccountGroup) {
		int value = 0;

		value = getName().compareTo(commerceAccountGroup.getName());

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

		if (!(obj instanceof CommerceAccountGroup)) {
			return false;
		}

		CommerceAccountGroup commerceAccountGroup = (CommerceAccountGroup)obj;

		long primaryKey = commerceAccountGroup.getPrimaryKey();

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
		CommerceAccountGroupModelImpl commerceAccountGroupModelImpl = this;

		commerceAccountGroupModelImpl._originalExternalReferenceCode =
			commerceAccountGroupModelImpl._externalReferenceCode;

		commerceAccountGroupModelImpl._originalCommerceAccountGroupId =
			commerceAccountGroupModelImpl._commerceAccountGroupId;

		commerceAccountGroupModelImpl._setOriginalCommerceAccountGroupId =
			false;

		commerceAccountGroupModelImpl._originalCompanyId =
			commerceAccountGroupModelImpl._companyId;

		commerceAccountGroupModelImpl._setOriginalCompanyId = false;

		commerceAccountGroupModelImpl._setModifiedDate = false;

		commerceAccountGroupModelImpl._originalType =
			commerceAccountGroupModelImpl._type;

		commerceAccountGroupModelImpl._setOriginalType = false;

		commerceAccountGroupModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<CommerceAccountGroup> toCacheModel() {
		CommerceAccountGroupCacheModel commerceAccountGroupCacheModel =
			new CommerceAccountGroupCacheModel();

		commerceAccountGroupCacheModel.externalReferenceCode =
			getExternalReferenceCode();

		String externalReferenceCode =
			commerceAccountGroupCacheModel.externalReferenceCode;

		if ((externalReferenceCode != null) &&
			(externalReferenceCode.length() == 0)) {

			commerceAccountGroupCacheModel.externalReferenceCode = null;
		}

		commerceAccountGroupCacheModel.commerceAccountGroupId =
			getCommerceAccountGroupId();

		commerceAccountGroupCacheModel.companyId = getCompanyId();

		commerceAccountGroupCacheModel.userId = getUserId();

		commerceAccountGroupCacheModel.userName = getUserName();

		String userName = commerceAccountGroupCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceAccountGroupCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceAccountGroupCacheModel.createDate = createDate.getTime();
		}
		else {
			commerceAccountGroupCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceAccountGroupCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			commerceAccountGroupCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		commerceAccountGroupCacheModel.name = getName();

		String name = commerceAccountGroupCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			commerceAccountGroupCacheModel.name = null;
		}

		commerceAccountGroupCacheModel.type = getType();

		commerceAccountGroupCacheModel.system = isSystem();

		return commerceAccountGroupCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommerceAccountGroup, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CommerceAccountGroup, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceAccountGroup, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply((CommerceAccountGroup)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<CommerceAccountGroup, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<CommerceAccountGroup, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceAccountGroup, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply((CommerceAccountGroup)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, CommerceAccountGroup>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private String _externalReferenceCode;
	private String _originalExternalReferenceCode;
	private long _commerceAccountGroupId;
	private long _originalCommerceAccountGroupId;
	private boolean _setOriginalCommerceAccountGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _name;
	private int _type;
	private int _originalType;
	private boolean _setOriginalType;
	private boolean _system;
	private long _columnBitmask;
	private CommerceAccountGroup _escapedModel;

}