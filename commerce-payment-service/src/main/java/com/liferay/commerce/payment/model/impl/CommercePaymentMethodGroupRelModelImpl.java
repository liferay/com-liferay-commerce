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

package com.liferay.commerce.payment.model.impl;

import com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel;
import com.liferay.commerce.payment.model.CommercePaymentMethodGroupRelModel;
import com.liferay.commerce.payment.model.CommercePaymentMethodGroupRelSoap;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

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
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the CommercePaymentMethodGroupRel service. Represents a row in the &quot;CommercePaymentMethodGroupRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>CommercePaymentMethodGroupRelModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommercePaymentMethodGroupRelImpl}.
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommercePaymentMethodGroupRelImpl
 * @generated
 */
@JSON(strict = true)
public class CommercePaymentMethodGroupRelModelImpl
	extends BaseModelImpl<CommercePaymentMethodGroupRel>
	implements CommercePaymentMethodGroupRelModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce payment method group rel model instance should use the <code>CommercePaymentMethodGroupRel</code> interface instead.
	 */
	public static final String TABLE_NAME = "CommercePaymentMethodGroupRel";

	public static final Object[][] TABLE_COLUMNS = {
		{"CPaymentMethodGroupRelId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"name", Types.VARCHAR},
		{"description", Types.VARCHAR}, {"imageId", Types.BIGINT},
		{"engineKey", Types.VARCHAR}, {"priority", Types.DOUBLE},
		{"active_", Types.BOOLEAN}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("CPaymentMethodGroupRelId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("imageId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("engineKey", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("priority", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("active_", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CommercePaymentMethodGroupRel (CPaymentMethodGroupRelId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,name STRING null,description STRING null,imageId LONG,engineKey VARCHAR(75) null,priority DOUBLE,active_ BOOLEAN)";

	public static final String TABLE_SQL_DROP =
		"drop table CommercePaymentMethodGroupRel";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commercePaymentMethodGroupRel.priority ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CommercePaymentMethodGroupRel.priority ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.commerce.payment.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.commerce.payment.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.commerce.payment.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel"),
		true);

	public static final long ACTIVE_COLUMN_BITMASK = 1L;

	public static final long ENGINEKEY_COLUMN_BITMASK = 2L;

	public static final long GROUPID_COLUMN_BITMASK = 4L;

	public static final long PRIORITY_COLUMN_BITMASK = 8L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static CommercePaymentMethodGroupRel toModel(
		CommercePaymentMethodGroupRelSoap soapModel) {

		if (soapModel == null) {
			return null;
		}

		CommercePaymentMethodGroupRel model =
			new CommercePaymentMethodGroupRelImpl();

		model.setCommercePaymentMethodGroupRelId(
			soapModel.getCommercePaymentMethodGroupRelId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setName(soapModel.getName());
		model.setDescription(soapModel.getDescription());
		model.setImageId(soapModel.getImageId());
		model.setEngineKey(soapModel.getEngineKey());
		model.setPriority(soapModel.getPriority());
		model.setActive(soapModel.isActive());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<CommercePaymentMethodGroupRel> toModels(
		CommercePaymentMethodGroupRelSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<CommercePaymentMethodGroupRel> models =
			new ArrayList<CommercePaymentMethodGroupRel>(soapModels.length);

		for (CommercePaymentMethodGroupRelSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.commerce.payment.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel"));

	public CommercePaymentMethodGroupRelModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commercePaymentMethodGroupRelId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommercePaymentMethodGroupRelId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commercePaymentMethodGroupRelId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommercePaymentMethodGroupRel.class;
	}

	@Override
	public String getModelClassName() {
		return CommercePaymentMethodGroupRel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommercePaymentMethodGroupRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CommercePaymentMethodGroupRel, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommercePaymentMethodGroupRel, Object>
				attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply(
					(CommercePaymentMethodGroupRel)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommercePaymentMethodGroupRel, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommercePaymentMethodGroupRel, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommercePaymentMethodGroupRel)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommercePaymentMethodGroupRel, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommercePaymentMethodGroupRel, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, CommercePaymentMethodGroupRel>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			CommercePaymentMethodGroupRel.class.getClassLoader(),
			CommercePaymentMethodGroupRel.class, ModelWrapper.class);

		try {
			Constructor<CommercePaymentMethodGroupRel> constructor =
				(Constructor<CommercePaymentMethodGroupRel>)
					proxyClass.getConstructor(InvocationHandler.class);

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

	private static final Map
		<String, Function<CommercePaymentMethodGroupRel, Object>>
			_attributeGetterFunctions;
	private static final Map
		<String, BiConsumer<CommercePaymentMethodGroupRel, Object>>
			_attributeSetterBiConsumers;

	static {
		Map<String, Function<CommercePaymentMethodGroupRel, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<CommercePaymentMethodGroupRel, Object>>();
		Map<String, BiConsumer<CommercePaymentMethodGroupRel, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<CommercePaymentMethodGroupRel, ?>>();

		attributeGetterFunctions.put(
			"commercePaymentMethodGroupRelId",
			new Function<CommercePaymentMethodGroupRel, Object>() {

				@Override
				public Object apply(
					CommercePaymentMethodGroupRel
						commercePaymentMethodGroupRel) {

					return commercePaymentMethodGroupRel.
						getCommercePaymentMethodGroupRelId();
				}

			});
		attributeSetterBiConsumers.put(
			"commercePaymentMethodGroupRelId",
			new BiConsumer<CommercePaymentMethodGroupRel, Object>() {

				@Override
				public void accept(
					CommercePaymentMethodGroupRel commercePaymentMethodGroupRel,
					Object commercePaymentMethodGroupRelIdObject) {

					commercePaymentMethodGroupRel.
						setCommercePaymentMethodGroupRelId(
							(Long)commercePaymentMethodGroupRelIdObject);
				}

			});
		attributeGetterFunctions.put(
			"groupId",
			new Function<CommercePaymentMethodGroupRel, Object>() {

				@Override
				public Object apply(
					CommercePaymentMethodGroupRel
						commercePaymentMethodGroupRel) {

					return commercePaymentMethodGroupRel.getGroupId();
				}

			});
		attributeSetterBiConsumers.put(
			"groupId",
			new BiConsumer<CommercePaymentMethodGroupRel, Object>() {

				@Override
				public void accept(
					CommercePaymentMethodGroupRel commercePaymentMethodGroupRel,
					Object groupIdObject) {

					commercePaymentMethodGroupRel.setGroupId(
						(Long)groupIdObject);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<CommercePaymentMethodGroupRel, Object>() {

				@Override
				public Object apply(
					CommercePaymentMethodGroupRel
						commercePaymentMethodGroupRel) {

					return commercePaymentMethodGroupRel.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<CommercePaymentMethodGroupRel, Object>() {

				@Override
				public void accept(
					CommercePaymentMethodGroupRel commercePaymentMethodGroupRel,
					Object companyIdObject) {

					commercePaymentMethodGroupRel.setCompanyId(
						(Long)companyIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userId",
			new Function<CommercePaymentMethodGroupRel, Object>() {

				@Override
				public Object apply(
					CommercePaymentMethodGroupRel
						commercePaymentMethodGroupRel) {

					return commercePaymentMethodGroupRel.getUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"userId",
			new BiConsumer<CommercePaymentMethodGroupRel, Object>() {

				@Override
				public void accept(
					CommercePaymentMethodGroupRel commercePaymentMethodGroupRel,
					Object userIdObject) {

					commercePaymentMethodGroupRel.setUserId((Long)userIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userName",
			new Function<CommercePaymentMethodGroupRel, Object>() {

				@Override
				public Object apply(
					CommercePaymentMethodGroupRel
						commercePaymentMethodGroupRel) {

					return commercePaymentMethodGroupRel.getUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"userName",
			new BiConsumer<CommercePaymentMethodGroupRel, Object>() {

				@Override
				public void accept(
					CommercePaymentMethodGroupRel commercePaymentMethodGroupRel,
					Object userNameObject) {

					commercePaymentMethodGroupRel.setUserName(
						(String)userNameObject);
				}

			});
		attributeGetterFunctions.put(
			"createDate",
			new Function<CommercePaymentMethodGroupRel, Object>() {

				@Override
				public Object apply(
					CommercePaymentMethodGroupRel
						commercePaymentMethodGroupRel) {

					return commercePaymentMethodGroupRel.getCreateDate();
				}

			});
		attributeSetterBiConsumers.put(
			"createDate",
			new BiConsumer<CommercePaymentMethodGroupRel, Object>() {

				@Override
				public void accept(
					CommercePaymentMethodGroupRel commercePaymentMethodGroupRel,
					Object createDateObject) {

					commercePaymentMethodGroupRel.setCreateDate(
						(Date)createDateObject);
				}

			});
		attributeGetterFunctions.put(
			"modifiedDate",
			new Function<CommercePaymentMethodGroupRel, Object>() {

				@Override
				public Object apply(
					CommercePaymentMethodGroupRel
						commercePaymentMethodGroupRel) {

					return commercePaymentMethodGroupRel.getModifiedDate();
				}

			});
		attributeSetterBiConsumers.put(
			"modifiedDate",
			new BiConsumer<CommercePaymentMethodGroupRel, Object>() {

				@Override
				public void accept(
					CommercePaymentMethodGroupRel commercePaymentMethodGroupRel,
					Object modifiedDateObject) {

					commercePaymentMethodGroupRel.setModifiedDate(
						(Date)modifiedDateObject);
				}

			});
		attributeGetterFunctions.put(
			"name",
			new Function<CommercePaymentMethodGroupRel, Object>() {

				@Override
				public Object apply(
					CommercePaymentMethodGroupRel
						commercePaymentMethodGroupRel) {

					return commercePaymentMethodGroupRel.getName();
				}

			});
		attributeSetterBiConsumers.put(
			"name",
			new BiConsumer<CommercePaymentMethodGroupRel, Object>() {

				@Override
				public void accept(
					CommercePaymentMethodGroupRel commercePaymentMethodGroupRel,
					Object nameObject) {

					commercePaymentMethodGroupRel.setName((String)nameObject);
				}

			});
		attributeGetterFunctions.put(
			"description",
			new Function<CommercePaymentMethodGroupRel, Object>() {

				@Override
				public Object apply(
					CommercePaymentMethodGroupRel
						commercePaymentMethodGroupRel) {

					return commercePaymentMethodGroupRel.getDescription();
				}

			});
		attributeSetterBiConsumers.put(
			"description",
			new BiConsumer<CommercePaymentMethodGroupRel, Object>() {

				@Override
				public void accept(
					CommercePaymentMethodGroupRel commercePaymentMethodGroupRel,
					Object descriptionObject) {

					commercePaymentMethodGroupRel.setDescription(
						(String)descriptionObject);
				}

			});
		attributeGetterFunctions.put(
			"imageId",
			new Function<CommercePaymentMethodGroupRel, Object>() {

				@Override
				public Object apply(
					CommercePaymentMethodGroupRel
						commercePaymentMethodGroupRel) {

					return commercePaymentMethodGroupRel.getImageId();
				}

			});
		attributeSetterBiConsumers.put(
			"imageId",
			new BiConsumer<CommercePaymentMethodGroupRel, Object>() {

				@Override
				public void accept(
					CommercePaymentMethodGroupRel commercePaymentMethodGroupRel,
					Object imageIdObject) {

					commercePaymentMethodGroupRel.setImageId(
						(Long)imageIdObject);
				}

			});
		attributeGetterFunctions.put(
			"engineKey",
			new Function<CommercePaymentMethodGroupRel, Object>() {

				@Override
				public Object apply(
					CommercePaymentMethodGroupRel
						commercePaymentMethodGroupRel) {

					return commercePaymentMethodGroupRel.getEngineKey();
				}

			});
		attributeSetterBiConsumers.put(
			"engineKey",
			new BiConsumer<CommercePaymentMethodGroupRel, Object>() {

				@Override
				public void accept(
					CommercePaymentMethodGroupRel commercePaymentMethodGroupRel,
					Object engineKeyObject) {

					commercePaymentMethodGroupRel.setEngineKey(
						(String)engineKeyObject);
				}

			});
		attributeGetterFunctions.put(
			"priority",
			new Function<CommercePaymentMethodGroupRel, Object>() {

				@Override
				public Object apply(
					CommercePaymentMethodGroupRel
						commercePaymentMethodGroupRel) {

					return commercePaymentMethodGroupRel.getPriority();
				}

			});
		attributeSetterBiConsumers.put(
			"priority",
			new BiConsumer<CommercePaymentMethodGroupRel, Object>() {

				@Override
				public void accept(
					CommercePaymentMethodGroupRel commercePaymentMethodGroupRel,
					Object priorityObject) {

					commercePaymentMethodGroupRel.setPriority(
						(Double)priorityObject);
				}

			});
		attributeGetterFunctions.put(
			"active",
			new Function<CommercePaymentMethodGroupRel, Object>() {

				@Override
				public Object apply(
					CommercePaymentMethodGroupRel
						commercePaymentMethodGroupRel) {

					return commercePaymentMethodGroupRel.getActive();
				}

			});
		attributeSetterBiConsumers.put(
			"active",
			new BiConsumer<CommercePaymentMethodGroupRel, Object>() {

				@Override
				public void accept(
					CommercePaymentMethodGroupRel commercePaymentMethodGroupRel,
					Object activeObject) {

					commercePaymentMethodGroupRel.setActive(
						(Boolean)activeObject);
				}

			});

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getCommercePaymentMethodGroupRelId() {
		return _commercePaymentMethodGroupRelId;
	}

	@Override
	public void setCommercePaymentMethodGroupRelId(
		long commercePaymentMethodGroupRelId) {

		_commercePaymentMethodGroupRelId = commercePaymentMethodGroupRelId;
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
		return LocalizationUtil.getLocalization(
			getName(), languageId, useDefault);
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
			setName(
				LocalizationUtil.updateLocalization(
					getName(), "Name", name, languageId, defaultLanguageId));
		}
		else {
			setName(
				LocalizationUtil.removeLocalization(
					getName(), "Name", languageId));
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

		setName(
			LocalizationUtil.updateLocalization(
				nameMap, getName(), "Name",
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
		return LocalizationUtil.getLocalization(
			getDescription(), languageId, useDefault);
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
	public void setDescription(
		String description, Locale locale, Locale defaultLocale) {

		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(description)) {
			setDescription(
				LocalizationUtil.updateLocalization(
					getDescription(), "Description", description, languageId,
					defaultLanguageId));
		}
		else {
			setDescription(
				LocalizationUtil.removeLocalization(
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
	public void setDescriptionMap(
		Map<Locale, String> descriptionMap, Locale defaultLocale) {

		if (descriptionMap == null) {
			return;
		}

		setDescription(
			LocalizationUtil.updateLocalization(
				descriptionMap, getDescription(), "Description",
				LocaleUtil.toLanguageId(defaultLocale)));
	}

	@JSON
	@Override
	public long getImageId() {
		return _imageId;
	}

	@Override
	public void setImageId(long imageId) {
		_imageId = imageId;
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
	public double getPriority() {
		return _priority;
	}

	@Override
	public void setPriority(double priority) {
		_columnBitmask = -1L;

		_priority = priority;
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
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), CommercePaymentMethodGroupRel.class.getName(),
			getPrimaryKey());
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

		return availableLanguageIds.toArray(
			new String[availableLanguageIds.size()]);
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
		Locale defaultLocale = LocaleUtil.fromLanguageId(
			getDefaultLanguageId());

		Locale[] availableLocales = LocaleUtil.fromLanguageIds(
			getAvailableLanguageIds());

		Locale defaultImportLocale = LocalizationUtil.getDefaultImportLocale(
			CommercePaymentMethodGroupRel.class.getName(), getPrimaryKey(),
			defaultLocale, availableLocales);

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
			setDescription(
				getDescription(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setDescription(
				getDescription(defaultLocale), defaultLocale, defaultLocale);
		}
	}

	@Override
	public CommercePaymentMethodGroupRel toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommercePaymentMethodGroupRel>
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
		CommercePaymentMethodGroupRelImpl commercePaymentMethodGroupRelImpl =
			new CommercePaymentMethodGroupRelImpl();

		commercePaymentMethodGroupRelImpl.setCommercePaymentMethodGroupRelId(
			getCommercePaymentMethodGroupRelId());
		commercePaymentMethodGroupRelImpl.setGroupId(getGroupId());
		commercePaymentMethodGroupRelImpl.setCompanyId(getCompanyId());
		commercePaymentMethodGroupRelImpl.setUserId(getUserId());
		commercePaymentMethodGroupRelImpl.setUserName(getUserName());
		commercePaymentMethodGroupRelImpl.setCreateDate(getCreateDate());
		commercePaymentMethodGroupRelImpl.setModifiedDate(getModifiedDate());
		commercePaymentMethodGroupRelImpl.setName(getName());
		commercePaymentMethodGroupRelImpl.setDescription(getDescription());
		commercePaymentMethodGroupRelImpl.setImageId(getImageId());
		commercePaymentMethodGroupRelImpl.setEngineKey(getEngineKey());
		commercePaymentMethodGroupRelImpl.setPriority(getPriority());
		commercePaymentMethodGroupRelImpl.setActive(isActive());

		commercePaymentMethodGroupRelImpl.resetOriginalValues();

		return commercePaymentMethodGroupRelImpl;
	}

	@Override
	public int compareTo(
		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel) {

		int value = 0;

		if (getPriority() < commercePaymentMethodGroupRel.getPriority()) {
			value = -1;
		}
		else if (getPriority() > commercePaymentMethodGroupRel.getPriority()) {
			value = 1;
		}
		else {
			value = 0;
		}

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

		if (!(obj instanceof CommercePaymentMethodGroupRel)) {
			return false;
		}

		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel =
			(CommercePaymentMethodGroupRel)obj;

		long primaryKey = commercePaymentMethodGroupRel.getPrimaryKey();

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
		CommercePaymentMethodGroupRelModelImpl
			commercePaymentMethodGroupRelModelImpl = this;

		commercePaymentMethodGroupRelModelImpl._originalGroupId =
			commercePaymentMethodGroupRelModelImpl._groupId;

		commercePaymentMethodGroupRelModelImpl._setOriginalGroupId = false;

		commercePaymentMethodGroupRelModelImpl._setModifiedDate = false;

		commercePaymentMethodGroupRelModelImpl._originalEngineKey =
			commercePaymentMethodGroupRelModelImpl._engineKey;

		commercePaymentMethodGroupRelModelImpl._originalActive =
			commercePaymentMethodGroupRelModelImpl._active;

		commercePaymentMethodGroupRelModelImpl._setOriginalActive = false;

		commercePaymentMethodGroupRelModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<CommercePaymentMethodGroupRel> toCacheModel() {
		CommercePaymentMethodGroupRelCacheModel
			commercePaymentMethodGroupRelCacheModel =
				new CommercePaymentMethodGroupRelCacheModel();

		commercePaymentMethodGroupRelCacheModel.
			commercePaymentMethodGroupRelId =
				getCommercePaymentMethodGroupRelId();

		commercePaymentMethodGroupRelCacheModel.groupId = getGroupId();

		commercePaymentMethodGroupRelCacheModel.companyId = getCompanyId();

		commercePaymentMethodGroupRelCacheModel.userId = getUserId();

		commercePaymentMethodGroupRelCacheModel.userName = getUserName();

		String userName = commercePaymentMethodGroupRelCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commercePaymentMethodGroupRelCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commercePaymentMethodGroupRelCacheModel.createDate =
				createDate.getTime();
		}
		else {
			commercePaymentMethodGroupRelCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commercePaymentMethodGroupRelCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			commercePaymentMethodGroupRelCacheModel.modifiedDate =
				Long.MIN_VALUE;
		}

		commercePaymentMethodGroupRelCacheModel.name = getName();

		String name = commercePaymentMethodGroupRelCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			commercePaymentMethodGroupRelCacheModel.name = null;
		}

		commercePaymentMethodGroupRelCacheModel.description = getDescription();

		String description =
			commercePaymentMethodGroupRelCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			commercePaymentMethodGroupRelCacheModel.description = null;
		}

		commercePaymentMethodGroupRelCacheModel.imageId = getImageId();

		commercePaymentMethodGroupRelCacheModel.engineKey = getEngineKey();

		String engineKey = commercePaymentMethodGroupRelCacheModel.engineKey;

		if ((engineKey != null) && (engineKey.length() == 0)) {
			commercePaymentMethodGroupRelCacheModel.engineKey = null;
		}

		commercePaymentMethodGroupRelCacheModel.priority = getPriority();

		commercePaymentMethodGroupRelCacheModel.active = isActive();

		return commercePaymentMethodGroupRelCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommercePaymentMethodGroupRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CommercePaymentMethodGroupRel, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommercePaymentMethodGroupRel, Object>
				attributeGetterFunction = entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply(
					(CommercePaymentMethodGroupRel)this));
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
		Map<String, Function<CommercePaymentMethodGroupRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<CommercePaymentMethodGroupRel, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommercePaymentMethodGroupRel, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply(
					(CommercePaymentMethodGroupRel)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function
			<InvocationHandler, CommercePaymentMethodGroupRel>
				_escapedModelProxyProviderFunction =
					_getProxyProviderFunction();

	}

	private long _commercePaymentMethodGroupRelId;
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
	private long _imageId;
	private String _engineKey;
	private String _originalEngineKey;
	private double _priority;
	private boolean _active;
	private boolean _originalActive;
	private boolean _setOriginalActive;
	private long _columnBitmask;
	private CommercePaymentMethodGroupRel _escapedModel;

}