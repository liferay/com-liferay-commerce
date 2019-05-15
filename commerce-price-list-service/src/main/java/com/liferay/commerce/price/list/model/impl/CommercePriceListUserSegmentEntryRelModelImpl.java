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

package com.liferay.commerce.price.list.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.price.list.model.CommercePriceListUserSegmentEntryRel;
import com.liferay.commerce.price.list.model.CommercePriceListUserSegmentEntryRelModel;
import com.liferay.commerce.price.list.model.CommercePriceListUserSegmentEntryRelSoap;
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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

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
 * The base model implementation for the CommercePriceListUserSegmentEntryRel service. Represents a row in the &quot;CPLUserSegmentEntryRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>CommercePriceListUserSegmentEntryRelModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommercePriceListUserSegmentEntryRelImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommercePriceListUserSegmentEntryRelImpl
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class CommercePriceListUserSegmentEntryRelModelImpl
	extends BaseModelImpl<CommercePriceListUserSegmentEntryRel>
	implements CommercePriceListUserSegmentEntryRelModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce price list user segment entry rel model instance should use the <code>CommercePriceListUserSegmentEntryRel</code> interface instead.
	 */
	public static final String TABLE_NAME = "CPLUserSegmentEntryRel";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"CPLUserSegmentEntryRelId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"commercePriceListId", Types.BIGINT},
		{"commerceUserSegmentEntryId", Types.BIGINT}, {"order_", Types.INTEGER},
		{"lastPublishDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("CPLUserSegmentEntryRelId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("commercePriceListId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("commerceUserSegmentEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("order_", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CPLUserSegmentEntryRel (uuid_ VARCHAR(75) null,CPLUserSegmentEntryRelId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,commercePriceListId LONG,commerceUserSegmentEntryId LONG,order_ INTEGER,lastPublishDate DATE null)";

	public static final String TABLE_SQL_DROP =
		"drop table CPLUserSegmentEntryRel";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commercePriceListUserSegmentEntryRel.order ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CPLUserSegmentEntryRel.order_ ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.commerce.price.list.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.com.liferay.commerce.price.list.model.CommercePriceListUserSegmentEntryRel"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.commerce.price.list.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.com.liferay.commerce.price.list.model.CommercePriceListUserSegmentEntryRel"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.commerce.price.list.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.com.liferay.commerce.price.list.model.CommercePriceListUserSegmentEntryRel"),
		true);

	public static final long COMMERCEPRICELISTID_COLUMN_BITMASK = 1L;

	public static final long COMMERCEUSERSEGMENTENTRYID_COLUMN_BITMASK = 2L;

	public static final long COMPANYID_COLUMN_BITMASK = 4L;

	public static final long GROUPID_COLUMN_BITMASK = 8L;

	public static final long UUID_COLUMN_BITMASK = 16L;

	public static final long ORDER_COLUMN_BITMASK = 32L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static CommercePriceListUserSegmentEntryRel toModel(
		CommercePriceListUserSegmentEntryRelSoap soapModel) {

		if (soapModel == null) {
			return null;
		}

		CommercePriceListUserSegmentEntryRel model =
			new CommercePriceListUserSegmentEntryRelImpl();

		model.setUuid(soapModel.getUuid());
		model.setCommercePriceListUserSegmentEntryRelId(
			soapModel.getCommercePriceListUserSegmentEntryRelId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setCommercePriceListId(soapModel.getCommercePriceListId());
		model.setCommerceUserSegmentEntryId(
			soapModel.getCommerceUserSegmentEntryId());
		model.setOrder(soapModel.getOrder());
		model.setLastPublishDate(soapModel.getLastPublishDate());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<CommercePriceListUserSegmentEntryRel> toModels(
		CommercePriceListUserSegmentEntryRelSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<CommercePriceListUserSegmentEntryRel> models =
			new ArrayList<CommercePriceListUserSegmentEntryRel>(
				soapModels.length);

		for (CommercePriceListUserSegmentEntryRelSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.commerce.price.list.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.commerce.price.list.model.CommercePriceListUserSegmentEntryRel"));

	public CommercePriceListUserSegmentEntryRelModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commercePriceListUserSegmentEntryRelId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommercePriceListUserSegmentEntryRelId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commercePriceListUserSegmentEntryRelId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommercePriceListUserSegmentEntryRel.class;
	}

	@Override
	public String getModelClassName() {
		return CommercePriceListUserSegmentEntryRel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommercePriceListUserSegmentEntryRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry
				<String, Function<CommercePriceListUserSegmentEntryRel, Object>>
					entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommercePriceListUserSegmentEntryRel, Object>
				attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply(
					(CommercePriceListUserSegmentEntryRel)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommercePriceListUserSegmentEntryRel, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommercePriceListUserSegmentEntryRel, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommercePriceListUserSegmentEntryRel)this,
					entry.getValue());
			}
		}
	}

	public Map<String, Function<CommercePriceListUserSegmentEntryRel, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommercePriceListUserSegmentEntryRel, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map
		<String, Function<CommercePriceListUserSegmentEntryRel, Object>>
			_attributeGetterFunctions;
	private static final Map
		<String, BiConsumer<CommercePriceListUserSegmentEntryRel, Object>>
			_attributeSetterBiConsumers;

	static {
		Map<String, Function<CommercePriceListUserSegmentEntryRel, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String,
					 Function<CommercePriceListUserSegmentEntryRel, Object>>();
		Map<String, BiConsumer<CommercePriceListUserSegmentEntryRel, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String,
					 BiConsumer<CommercePriceListUserSegmentEntryRel, ?>>();

		attributeGetterFunctions.put(
			"uuid",
			new Function<CommercePriceListUserSegmentEntryRel, Object>() {

				@Override
				public Object apply(
					CommercePriceListUserSegmentEntryRel
						commercePriceListUserSegmentEntryRel) {

					return commercePriceListUserSegmentEntryRel.getUuid();
				}

			});
		attributeSetterBiConsumers.put(
			"uuid",
			new BiConsumer<CommercePriceListUserSegmentEntryRel, Object>() {

				@Override
				public void accept(
					CommercePriceListUserSegmentEntryRel
						commercePriceListUserSegmentEntryRel,
					Object uuid) {

					commercePriceListUserSegmentEntryRel.setUuid((String)uuid);
				}

			});
		attributeGetterFunctions.put(
			"commercePriceListUserSegmentEntryRelId",
			new Function<CommercePriceListUserSegmentEntryRel, Object>() {

				@Override
				public Object apply(
					CommercePriceListUserSegmentEntryRel
						commercePriceListUserSegmentEntryRel) {

					return commercePriceListUserSegmentEntryRel.
						getCommercePriceListUserSegmentEntryRelId();
				}

			});
		attributeSetterBiConsumers.put(
			"commercePriceListUserSegmentEntryRelId",
			new BiConsumer<CommercePriceListUserSegmentEntryRel, Object>() {

				@Override
				public void accept(
					CommercePriceListUserSegmentEntryRel
						commercePriceListUserSegmentEntryRel,
					Object commercePriceListUserSegmentEntryRelId) {

					commercePriceListUserSegmentEntryRel.
						setCommercePriceListUserSegmentEntryRelId(
							(Long)commercePriceListUserSegmentEntryRelId);
				}

			});
		attributeGetterFunctions.put(
			"groupId",
			new Function<CommercePriceListUserSegmentEntryRel, Object>() {

				@Override
				public Object apply(
					CommercePriceListUserSegmentEntryRel
						commercePriceListUserSegmentEntryRel) {

					return commercePriceListUserSegmentEntryRel.getGroupId();
				}

			});
		attributeSetterBiConsumers.put(
			"groupId",
			new BiConsumer<CommercePriceListUserSegmentEntryRel, Object>() {

				@Override
				public void accept(
					CommercePriceListUserSegmentEntryRel
						commercePriceListUserSegmentEntryRel,
					Object groupId) {

					commercePriceListUserSegmentEntryRel.setGroupId(
						(Long)groupId);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<CommercePriceListUserSegmentEntryRel, Object>() {

				@Override
				public Object apply(
					CommercePriceListUserSegmentEntryRel
						commercePriceListUserSegmentEntryRel) {

					return commercePriceListUserSegmentEntryRel.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<CommercePriceListUserSegmentEntryRel, Object>() {

				@Override
				public void accept(
					CommercePriceListUserSegmentEntryRel
						commercePriceListUserSegmentEntryRel,
					Object companyId) {

					commercePriceListUserSegmentEntryRel.setCompanyId(
						(Long)companyId);
				}

			});
		attributeGetterFunctions.put(
			"userId",
			new Function<CommercePriceListUserSegmentEntryRel, Object>() {

				@Override
				public Object apply(
					CommercePriceListUserSegmentEntryRel
						commercePriceListUserSegmentEntryRel) {

					return commercePriceListUserSegmentEntryRel.getUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"userId",
			new BiConsumer<CommercePriceListUserSegmentEntryRel, Object>() {

				@Override
				public void accept(
					CommercePriceListUserSegmentEntryRel
						commercePriceListUserSegmentEntryRel,
					Object userId) {

					commercePriceListUserSegmentEntryRel.setUserId(
						(Long)userId);
				}

			});
		attributeGetterFunctions.put(
			"userName",
			new Function<CommercePriceListUserSegmentEntryRel, Object>() {

				@Override
				public Object apply(
					CommercePriceListUserSegmentEntryRel
						commercePriceListUserSegmentEntryRel) {

					return commercePriceListUserSegmentEntryRel.getUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"userName",
			new BiConsumer<CommercePriceListUserSegmentEntryRel, Object>() {

				@Override
				public void accept(
					CommercePriceListUserSegmentEntryRel
						commercePriceListUserSegmentEntryRel,
					Object userName) {

					commercePriceListUserSegmentEntryRel.setUserName(
						(String)userName);
				}

			});
		attributeGetterFunctions.put(
			"createDate",
			new Function<CommercePriceListUserSegmentEntryRel, Object>() {

				@Override
				public Object apply(
					CommercePriceListUserSegmentEntryRel
						commercePriceListUserSegmentEntryRel) {

					return commercePriceListUserSegmentEntryRel.getCreateDate();
				}

			});
		attributeSetterBiConsumers.put(
			"createDate",
			new BiConsumer<CommercePriceListUserSegmentEntryRel, Object>() {

				@Override
				public void accept(
					CommercePriceListUserSegmentEntryRel
						commercePriceListUserSegmentEntryRel,
					Object createDate) {

					commercePriceListUserSegmentEntryRel.setCreateDate(
						(Date)createDate);
				}

			});
		attributeGetterFunctions.put(
			"modifiedDate",
			new Function<CommercePriceListUserSegmentEntryRel, Object>() {

				@Override
				public Object apply(
					CommercePriceListUserSegmentEntryRel
						commercePriceListUserSegmentEntryRel) {

					return commercePriceListUserSegmentEntryRel.
						getModifiedDate();
				}

			});
		attributeSetterBiConsumers.put(
			"modifiedDate",
			new BiConsumer<CommercePriceListUserSegmentEntryRel, Object>() {

				@Override
				public void accept(
					CommercePriceListUserSegmentEntryRel
						commercePriceListUserSegmentEntryRel,
					Object modifiedDate) {

					commercePriceListUserSegmentEntryRel.setModifiedDate(
						(Date)modifiedDate);
				}

			});
		attributeGetterFunctions.put(
			"commercePriceListId",
			new Function<CommercePriceListUserSegmentEntryRel, Object>() {

				@Override
				public Object apply(
					CommercePriceListUserSegmentEntryRel
						commercePriceListUserSegmentEntryRel) {

					return commercePriceListUserSegmentEntryRel.
						getCommercePriceListId();
				}

			});
		attributeSetterBiConsumers.put(
			"commercePriceListId",
			new BiConsumer<CommercePriceListUserSegmentEntryRel, Object>() {

				@Override
				public void accept(
					CommercePriceListUserSegmentEntryRel
						commercePriceListUserSegmentEntryRel,
					Object commercePriceListId) {

					commercePriceListUserSegmentEntryRel.setCommercePriceListId(
						(Long)commercePriceListId);
				}

			});
		attributeGetterFunctions.put(
			"commerceUserSegmentEntryId",
			new Function<CommercePriceListUserSegmentEntryRel, Object>() {

				@Override
				public Object apply(
					CommercePriceListUserSegmentEntryRel
						commercePriceListUserSegmentEntryRel) {

					return commercePriceListUserSegmentEntryRel.
						getCommerceUserSegmentEntryId();
				}

			});
		attributeSetterBiConsumers.put(
			"commerceUserSegmentEntryId",
			new BiConsumer<CommercePriceListUserSegmentEntryRel, Object>() {

				@Override
				public void accept(
					CommercePriceListUserSegmentEntryRel
						commercePriceListUserSegmentEntryRel,
					Object commerceUserSegmentEntryId) {

					commercePriceListUserSegmentEntryRel.
						setCommerceUserSegmentEntryId(
							(Long)commerceUserSegmentEntryId);
				}

			});
		attributeGetterFunctions.put(
			"order",
			new Function<CommercePriceListUserSegmentEntryRel, Object>() {

				@Override
				public Object apply(
					CommercePriceListUserSegmentEntryRel
						commercePriceListUserSegmentEntryRel) {

					return commercePriceListUserSegmentEntryRel.getOrder();
				}

			});
		attributeSetterBiConsumers.put(
			"order",
			new BiConsumer<CommercePriceListUserSegmentEntryRel, Object>() {

				@Override
				public void accept(
					CommercePriceListUserSegmentEntryRel
						commercePriceListUserSegmentEntryRel,
					Object order) {

					commercePriceListUserSegmentEntryRel.setOrder(
						(Integer)order);
				}

			});
		attributeGetterFunctions.put(
			"lastPublishDate",
			new Function<CommercePriceListUserSegmentEntryRel, Object>() {

				@Override
				public Object apply(
					CommercePriceListUserSegmentEntryRel
						commercePriceListUserSegmentEntryRel) {

					return commercePriceListUserSegmentEntryRel.
						getLastPublishDate();
				}

			});
		attributeSetterBiConsumers.put(
			"lastPublishDate",
			new BiConsumer<CommercePriceListUserSegmentEntryRel, Object>() {

				@Override
				public void accept(
					CommercePriceListUserSegmentEntryRel
						commercePriceListUserSegmentEntryRel,
					Object lastPublishDate) {

					commercePriceListUserSegmentEntryRel.setLastPublishDate(
						(Date)lastPublishDate);
				}

			});

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
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
		_columnBitmask |= UUID_COLUMN_BITMASK;

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
	public long getCommercePriceListUserSegmentEntryRelId() {
		return _commercePriceListUserSegmentEntryRelId;
	}

	@Override
	public void setCommercePriceListUserSegmentEntryRelId(
		long commercePriceListUserSegmentEntryRelId) {

		_commercePriceListUserSegmentEntryRelId =
			commercePriceListUserSegmentEntryRelId;
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
	public long getCommercePriceListId() {
		return _commercePriceListId;
	}

	@Override
	public void setCommercePriceListId(long commercePriceListId) {
		_columnBitmask |= COMMERCEPRICELISTID_COLUMN_BITMASK;

		if (!_setOriginalCommercePriceListId) {
			_setOriginalCommercePriceListId = true;

			_originalCommercePriceListId = _commercePriceListId;
		}

		_commercePriceListId = commercePriceListId;
	}

	public long getOriginalCommercePriceListId() {
		return _originalCommercePriceListId;
	}

	@JSON
	@Override
	public long getCommerceUserSegmentEntryId() {
		return _commerceUserSegmentEntryId;
	}

	@Override
	public void setCommerceUserSegmentEntryId(long commerceUserSegmentEntryId) {
		_columnBitmask |= COMMERCEUSERSEGMENTENTRYID_COLUMN_BITMASK;

		if (!_setOriginalCommerceUserSegmentEntryId) {
			_setOriginalCommerceUserSegmentEntryId = true;

			_originalCommerceUserSegmentEntryId = _commerceUserSegmentEntryId;
		}

		_commerceUserSegmentEntryId = commerceUserSegmentEntryId;
	}

	public long getOriginalCommerceUserSegmentEntryId() {
		return _originalCommerceUserSegmentEntryId;
	}

	@JSON
	@Override
	public int getOrder() {
		return _order;
	}

	@Override
	public void setOrder(int order) {
		_columnBitmask = -1L;

		_order = order;
	}

	@JSON
	@Override
	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(
				CommercePriceListUserSegmentEntryRel.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(),
			CommercePriceListUserSegmentEntryRel.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommercePriceListUserSegmentEntryRel toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel =
				(CommercePriceListUserSegmentEntryRel)
					ProxyUtil.newProxyInstance(
						_classLoader, _escapedModelInterfaces,
						new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		CommercePriceListUserSegmentEntryRelImpl
			commercePriceListUserSegmentEntryRelImpl =
				new CommercePriceListUserSegmentEntryRelImpl();

		commercePriceListUserSegmentEntryRelImpl.setUuid(getUuid());
		commercePriceListUserSegmentEntryRelImpl.
			setCommercePriceListUserSegmentEntryRelId(
				getCommercePriceListUserSegmentEntryRelId());
		commercePriceListUserSegmentEntryRelImpl.setGroupId(getGroupId());
		commercePriceListUserSegmentEntryRelImpl.setCompanyId(getCompanyId());
		commercePriceListUserSegmentEntryRelImpl.setUserId(getUserId());
		commercePriceListUserSegmentEntryRelImpl.setUserName(getUserName());
		commercePriceListUserSegmentEntryRelImpl.setCreateDate(getCreateDate());
		commercePriceListUserSegmentEntryRelImpl.setModifiedDate(
			getModifiedDate());
		commercePriceListUserSegmentEntryRelImpl.setCommercePriceListId(
			getCommercePriceListId());
		commercePriceListUserSegmentEntryRelImpl.setCommerceUserSegmentEntryId(
			getCommerceUserSegmentEntryId());
		commercePriceListUserSegmentEntryRelImpl.setOrder(getOrder());
		commercePriceListUserSegmentEntryRelImpl.setLastPublishDate(
			getLastPublishDate());

		commercePriceListUserSegmentEntryRelImpl.resetOriginalValues();

		return commercePriceListUserSegmentEntryRelImpl;
	}

	@Override
	public int compareTo(
		CommercePriceListUserSegmentEntryRel
			commercePriceListUserSegmentEntryRel) {

		int value = 0;

		if (getOrder() < commercePriceListUserSegmentEntryRel.getOrder()) {
			value = -1;
		}
		else if (getOrder() > commercePriceListUserSegmentEntryRel.getOrder()) {
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

		if (!(obj instanceof CommercePriceListUserSegmentEntryRel)) {
			return false;
		}

		CommercePriceListUserSegmentEntryRel
			commercePriceListUserSegmentEntryRel =
				(CommercePriceListUserSegmentEntryRel)obj;

		long primaryKey = commercePriceListUserSegmentEntryRel.getPrimaryKey();

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
		CommercePriceListUserSegmentEntryRelModelImpl
			commercePriceListUserSegmentEntryRelModelImpl = this;

		commercePriceListUserSegmentEntryRelModelImpl._originalUuid =
			commercePriceListUserSegmentEntryRelModelImpl._uuid;

		commercePriceListUserSegmentEntryRelModelImpl._originalGroupId =
			commercePriceListUserSegmentEntryRelModelImpl._groupId;

		commercePriceListUserSegmentEntryRelModelImpl._setOriginalGroupId =
			false;

		commercePriceListUserSegmentEntryRelModelImpl._originalCompanyId =
			commercePriceListUserSegmentEntryRelModelImpl._companyId;

		commercePriceListUserSegmentEntryRelModelImpl._setOriginalCompanyId =
			false;

		commercePriceListUserSegmentEntryRelModelImpl._setModifiedDate = false;

		commercePriceListUserSegmentEntryRelModelImpl.
			_originalCommercePriceListId =
				commercePriceListUserSegmentEntryRelModelImpl.
					_commercePriceListId;

		commercePriceListUserSegmentEntryRelModelImpl.
			_setOriginalCommercePriceListId = false;

		commercePriceListUserSegmentEntryRelModelImpl.
			_originalCommerceUserSegmentEntryId =
				commercePriceListUserSegmentEntryRelModelImpl.
					_commerceUserSegmentEntryId;

		commercePriceListUserSegmentEntryRelModelImpl.
			_setOriginalCommerceUserSegmentEntryId = false;

		commercePriceListUserSegmentEntryRelModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<CommercePriceListUserSegmentEntryRel> toCacheModel() {
		CommercePriceListUserSegmentEntryRelCacheModel
			commercePriceListUserSegmentEntryRelCacheModel =
				new CommercePriceListUserSegmentEntryRelCacheModel();

		commercePriceListUserSegmentEntryRelCacheModel.uuid = getUuid();

		String uuid = commercePriceListUserSegmentEntryRelCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			commercePriceListUserSegmentEntryRelCacheModel.uuid = null;
		}

		commercePriceListUserSegmentEntryRelCacheModel.
			commercePriceListUserSegmentEntryRelId =
				getCommercePriceListUserSegmentEntryRelId();

		commercePriceListUserSegmentEntryRelCacheModel.groupId = getGroupId();

		commercePriceListUserSegmentEntryRelCacheModel.companyId =
			getCompanyId();

		commercePriceListUserSegmentEntryRelCacheModel.userId = getUserId();

		commercePriceListUserSegmentEntryRelCacheModel.userName = getUserName();

		String userName =
			commercePriceListUserSegmentEntryRelCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commercePriceListUserSegmentEntryRelCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commercePriceListUserSegmentEntryRelCacheModel.createDate =
				createDate.getTime();
		}
		else {
			commercePriceListUserSegmentEntryRelCacheModel.createDate =
				Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commercePriceListUserSegmentEntryRelCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			commercePriceListUserSegmentEntryRelCacheModel.modifiedDate =
				Long.MIN_VALUE;
		}

		commercePriceListUserSegmentEntryRelCacheModel.commercePriceListId =
			getCommercePriceListId();

		commercePriceListUserSegmentEntryRelCacheModel.
			commerceUserSegmentEntryId = getCommerceUserSegmentEntryId();

		commercePriceListUserSegmentEntryRelCacheModel.order = getOrder();

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			commercePriceListUserSegmentEntryRelCacheModel.lastPublishDate =
				lastPublishDate.getTime();
		}
		else {
			commercePriceListUserSegmentEntryRelCacheModel.lastPublishDate =
				Long.MIN_VALUE;
		}

		return commercePriceListUserSegmentEntryRelCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommercePriceListUserSegmentEntryRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry
				<String, Function<CommercePriceListUserSegmentEntryRel, Object>>
					entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommercePriceListUserSegmentEntryRel, Object>
				attributeGetterFunction = entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply(
					(CommercePriceListUserSegmentEntryRel)this));
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
		Map<String, Function<CommercePriceListUserSegmentEntryRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry
				<String, Function<CommercePriceListUserSegmentEntryRel, Object>>
					entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommercePriceListUserSegmentEntryRel, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply(
					(CommercePriceListUserSegmentEntryRel)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader =
		CommercePriceListUserSegmentEntryRel.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
		CommercePriceListUserSegmentEntryRel.class, ModelWrapper.class
	};

	private String _uuid;
	private String _originalUuid;
	private long _commercePriceListUserSegmentEntryRelId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _commercePriceListId;
	private long _originalCommercePriceListId;
	private boolean _setOriginalCommercePriceListId;
	private long _commerceUserSegmentEntryId;
	private long _originalCommerceUserSegmentEntryId;
	private boolean _setOriginalCommerceUserSegmentEntryId;
	private int _order;
	private Date _lastPublishDate;
	private long _columnBitmask;
	private CommercePriceListUserSegmentEntryRel _escapedModel;

}