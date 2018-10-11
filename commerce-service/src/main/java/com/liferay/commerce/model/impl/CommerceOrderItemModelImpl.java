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

import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.model.CommerceOrderItemModel;
import com.liferay.commerce.model.CommerceOrderItemSoap;

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
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.math.BigDecimal;

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
 * The base model implementation for the CommerceOrderItem service. Represents a row in the &quot;CommerceOrderItem&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link CommerceOrderItemModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceOrderItemImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceOrderItemImpl
 * @see CommerceOrderItem
 * @see CommerceOrderItemModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class CommerceOrderItemModelImpl extends BaseModelImpl<CommerceOrderItem>
	implements CommerceOrderItemModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce order item model instance should use the {@link CommerceOrderItem} interface instead.
	 */
	public static final String TABLE_NAME = "CommerceOrderItem";
	public static final Object[][] TABLE_COLUMNS = {
			{ "externalReferenceCode", Types.VARCHAR },
			{ "commerceOrderItemId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "commerceOrderId", Types.BIGINT },
			{ "CPInstanceId", Types.BIGINT },
			{ "quantity", Types.INTEGER },
			{ "shippedQuantity", Types.INTEGER },
			{ "json", Types.CLOB },
			{ "name", Types.VARCHAR },
			{ "sku", Types.VARCHAR },
			{ "unitPrice", Types.DECIMAL },
			{ "discountAmount", Types.DECIMAL },
			{ "finalPrice", Types.DECIMAL },
			{ "discountPercentageLevel1", Types.DECIMAL },
			{ "discountPercentageLevel2", Types.DECIMAL },
			{ "discountPercentageLevel3", Types.DECIMAL },
			{ "discountPercentageLevel4", Types.DECIMAL },
			{ "subscription", Types.BOOLEAN }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("externalReferenceCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("commerceOrderItemId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("commerceOrderId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("CPInstanceId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("quantity", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("shippedQuantity", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("json", Types.CLOB);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("sku", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("unitPrice", Types.DECIMAL);
		TABLE_COLUMNS_MAP.put("discountAmount", Types.DECIMAL);
		TABLE_COLUMNS_MAP.put("finalPrice", Types.DECIMAL);
		TABLE_COLUMNS_MAP.put("discountPercentageLevel1", Types.DECIMAL);
		TABLE_COLUMNS_MAP.put("discountPercentageLevel2", Types.DECIMAL);
		TABLE_COLUMNS_MAP.put("discountPercentageLevel3", Types.DECIMAL);
		TABLE_COLUMNS_MAP.put("discountPercentageLevel4", Types.DECIMAL);
		TABLE_COLUMNS_MAP.put("subscription", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE = "create table CommerceOrderItem (externalReferenceCode VARCHAR(75) null,commerceOrderItemId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,commerceOrderId LONG,CPInstanceId LONG,quantity INTEGER,shippedQuantity INTEGER,json TEXT null,name STRING null,sku VARCHAR(75) null,unitPrice DECIMAL(30, 16) null,discountAmount DECIMAL(30, 16) null,finalPrice DECIMAL(30, 16) null,discountPercentageLevel1 DECIMAL(30, 16) null,discountPercentageLevel2 DECIMAL(30, 16) null,discountPercentageLevel3 DECIMAL(30, 16) null,discountPercentageLevel4 DECIMAL(30, 16) null,subscription BOOLEAN)";
	public static final String TABLE_SQL_DROP = "drop table CommerceOrderItem";
	public static final String ORDER_BY_JPQL = " ORDER BY commerceOrderItem.createDate ASC";
	public static final String ORDER_BY_SQL = " ORDER BY CommerceOrderItem.createDate ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.commerce.model.CommerceOrderItem"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.commerce.model.CommerceOrderItem"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.commerce.model.CommerceOrderItem"),
			true);
	public static final long CPINSTANCEID_COLUMN_BITMASK = 1L;
	public static final long COMMERCEORDERID_COLUMN_BITMASK = 2L;
	public static final long COMPANYID_COLUMN_BITMASK = 4L;
	public static final long EXTERNALREFERENCECODE_COLUMN_BITMASK = 8L;
	public static final long SUBSCRIPTION_COLUMN_BITMASK = 16L;
	public static final long CREATEDATE_COLUMN_BITMASK = 32L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static CommerceOrderItem toModel(CommerceOrderItemSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		CommerceOrderItem model = new CommerceOrderItemImpl();

		model.setExternalReferenceCode(soapModel.getExternalReferenceCode());
		model.setCommerceOrderItemId(soapModel.getCommerceOrderItemId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setCommerceOrderId(soapModel.getCommerceOrderId());
		model.setCPInstanceId(soapModel.getCPInstanceId());
		model.setQuantity(soapModel.getQuantity());
		model.setShippedQuantity(soapModel.getShippedQuantity());
		model.setJson(soapModel.getJson());
		model.setName(soapModel.getName());
		model.setSku(soapModel.getSku());
		model.setUnitPrice(soapModel.getUnitPrice());
		model.setDiscountAmount(soapModel.getDiscountAmount());
		model.setFinalPrice(soapModel.getFinalPrice());
		model.setDiscountPercentageLevel1(soapModel.getDiscountPercentageLevel1());
		model.setDiscountPercentageLevel2(soapModel.getDiscountPercentageLevel2());
		model.setDiscountPercentageLevel3(soapModel.getDiscountPercentageLevel3());
		model.setDiscountPercentageLevel4(soapModel.getDiscountPercentageLevel4());
		model.setSubscription(soapModel.isSubscription());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<CommerceOrderItem> toModels(
		CommerceOrderItemSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<CommerceOrderItem> models = new ArrayList<CommerceOrderItem>(soapModels.length);

		for (CommerceOrderItemSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.commerce.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.commerce.model.CommerceOrderItem"));

	public CommerceOrderItemModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceOrderItemId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceOrderItemId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceOrderItemId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceOrderItem.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceOrderItem.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("externalReferenceCode", getExternalReferenceCode());
		attributes.put("commerceOrderItemId", getCommerceOrderItemId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("commerceOrderId", getCommerceOrderId());
		attributes.put("CPInstanceId", getCPInstanceId());
		attributes.put("quantity", getQuantity());
		attributes.put("shippedQuantity", getShippedQuantity());
		attributes.put("json", getJson());
		attributes.put("name", getName());
		attributes.put("sku", getSku());
		attributes.put("unitPrice", getUnitPrice());
		attributes.put("discountAmount", getDiscountAmount());
		attributes.put("finalPrice", getFinalPrice());
		attributes.put("discountPercentageLevel1", getDiscountPercentageLevel1());
		attributes.put("discountPercentageLevel2", getDiscountPercentageLevel2());
		attributes.put("discountPercentageLevel3", getDiscountPercentageLevel3());
		attributes.put("discountPercentageLevel4", getDiscountPercentageLevel4());
		attributes.put("subscription", isSubscription());

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

		Long commerceOrderItemId = (Long)attributes.get("commerceOrderItemId");

		if (commerceOrderItemId != null) {
			setCommerceOrderItemId(commerceOrderItemId);
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

		Long CPInstanceId = (Long)attributes.get("CPInstanceId");

		if (CPInstanceId != null) {
			setCPInstanceId(CPInstanceId);
		}

		Integer quantity = (Integer)attributes.get("quantity");

		if (quantity != null) {
			setQuantity(quantity);
		}

		Integer shippedQuantity = (Integer)attributes.get("shippedQuantity");

		if (shippedQuantity != null) {
			setShippedQuantity(shippedQuantity);
		}

		String json = (String)attributes.get("json");

		if (json != null) {
			setJson(json);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String sku = (String)attributes.get("sku");

		if (sku != null) {
			setSku(sku);
		}

		BigDecimal unitPrice = (BigDecimal)attributes.get("unitPrice");

		if (unitPrice != null) {
			setUnitPrice(unitPrice);
		}

		BigDecimal discountAmount = (BigDecimal)attributes.get("discountAmount");

		if (discountAmount != null) {
			setDiscountAmount(discountAmount);
		}

		BigDecimal finalPrice = (BigDecimal)attributes.get("finalPrice");

		if (finalPrice != null) {
			setFinalPrice(finalPrice);
		}

		BigDecimal discountPercentageLevel1 = (BigDecimal)attributes.get(
				"discountPercentageLevel1");

		if (discountPercentageLevel1 != null) {
			setDiscountPercentageLevel1(discountPercentageLevel1);
		}

		BigDecimal discountPercentageLevel2 = (BigDecimal)attributes.get(
				"discountPercentageLevel2");

		if (discountPercentageLevel2 != null) {
			setDiscountPercentageLevel2(discountPercentageLevel2);
		}

		BigDecimal discountPercentageLevel3 = (BigDecimal)attributes.get(
				"discountPercentageLevel3");

		if (discountPercentageLevel3 != null) {
			setDiscountPercentageLevel3(discountPercentageLevel3);
		}

		BigDecimal discountPercentageLevel4 = (BigDecimal)attributes.get(
				"discountPercentageLevel4");

		if (discountPercentageLevel4 != null) {
			setDiscountPercentageLevel4(discountPercentageLevel4);
		}

		Boolean subscription = (Boolean)attributes.get("subscription");

		if (subscription != null) {
			setSubscription(subscription);
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
	public long getCommerceOrderItemId() {
		return _commerceOrderItemId;
	}

	@Override
	public void setCommerceOrderItemId(long commerceOrderItemId) {
		_commerceOrderItemId = commerceOrderItemId;
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
	public long getCPInstanceId() {
		return _CPInstanceId;
	}

	@Override
	public void setCPInstanceId(long CPInstanceId) {
		_columnBitmask |= CPINSTANCEID_COLUMN_BITMASK;

		if (!_setOriginalCPInstanceId) {
			_setOriginalCPInstanceId = true;

			_originalCPInstanceId = _CPInstanceId;
		}

		_CPInstanceId = CPInstanceId;
	}

	public long getOriginalCPInstanceId() {
		return _originalCPInstanceId;
	}

	@JSON
	@Override
	public int getQuantity() {
		return _quantity;
	}

	@Override
	public void setQuantity(int quantity) {
		_quantity = quantity;
	}

	@JSON
	@Override
	public int getShippedQuantity() {
		return _shippedQuantity;
	}

	@Override
	public void setShippedQuantity(int shippedQuantity) {
		_shippedQuantity = shippedQuantity;
	}

	@JSON
	@Override
	public String getJson() {
		if (_json == null) {
			return "";
		}
		else {
			return _json;
		}
	}

	@Override
	public void setJson(String json) {
		_json = json;
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
	public String getSku() {
		if (_sku == null) {
			return "";
		}
		else {
			return _sku;
		}
	}

	@Override
	public void setSku(String sku) {
		_sku = sku;
	}

	@JSON
	@Override
	public BigDecimal getUnitPrice() {
		return _unitPrice;
	}

	@Override
	public void setUnitPrice(BigDecimal unitPrice) {
		_unitPrice = unitPrice;
	}

	@JSON
	@Override
	public BigDecimal getDiscountAmount() {
		return _discountAmount;
	}

	@Override
	public void setDiscountAmount(BigDecimal discountAmount) {
		_discountAmount = discountAmount;
	}

	@JSON
	@Override
	public BigDecimal getFinalPrice() {
		return _finalPrice;
	}

	@Override
	public void setFinalPrice(BigDecimal finalPrice) {
		_finalPrice = finalPrice;
	}

	@JSON
	@Override
	public BigDecimal getDiscountPercentageLevel1() {
		return _discountPercentageLevel1;
	}

	@Override
	public void setDiscountPercentageLevel1(BigDecimal discountPercentageLevel1) {
		_discountPercentageLevel1 = discountPercentageLevel1;
	}

	@JSON
	@Override
	public BigDecimal getDiscountPercentageLevel2() {
		return _discountPercentageLevel2;
	}

	@Override
	public void setDiscountPercentageLevel2(BigDecimal discountPercentageLevel2) {
		_discountPercentageLevel2 = discountPercentageLevel2;
	}

	@JSON
	@Override
	public BigDecimal getDiscountPercentageLevel3() {
		return _discountPercentageLevel3;
	}

	@Override
	public void setDiscountPercentageLevel3(BigDecimal discountPercentageLevel3) {
		_discountPercentageLevel3 = discountPercentageLevel3;
	}

	@JSON
	@Override
	public BigDecimal getDiscountPercentageLevel4() {
		return _discountPercentageLevel4;
	}

	@Override
	public void setDiscountPercentageLevel4(BigDecimal discountPercentageLevel4) {
		_discountPercentageLevel4 = discountPercentageLevel4;
	}

	@JSON
	@Override
	public boolean getSubscription() {
		return _subscription;
	}

	@JSON
	@Override
	public boolean isSubscription() {
		return _subscription;
	}

	@Override
	public void setSubscription(boolean subscription) {
		_columnBitmask |= SUBSCRIPTION_COLUMN_BITMASK;

		if (!_setOriginalSubscription) {
			_setOriginalSubscription = true;

			_originalSubscription = _subscription;
		}

		_subscription = subscription;
	}

	public boolean getOriginalSubscription() {
		return _originalSubscription;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			CommerceOrderItem.class.getName(), getPrimaryKey());
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

		Locale defaultImportLocale = LocalizationUtil.getDefaultImportLocale(CommerceOrderItem.class.getName(),
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
	}

	@Override
	public CommerceOrderItem toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (CommerceOrderItem)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		CommerceOrderItemImpl commerceOrderItemImpl = new CommerceOrderItemImpl();

		commerceOrderItemImpl.setExternalReferenceCode(getExternalReferenceCode());
		commerceOrderItemImpl.setCommerceOrderItemId(getCommerceOrderItemId());
		commerceOrderItemImpl.setGroupId(getGroupId());
		commerceOrderItemImpl.setCompanyId(getCompanyId());
		commerceOrderItemImpl.setUserId(getUserId());
		commerceOrderItemImpl.setUserName(getUserName());
		commerceOrderItemImpl.setCreateDate(getCreateDate());
		commerceOrderItemImpl.setModifiedDate(getModifiedDate());
		commerceOrderItemImpl.setCommerceOrderId(getCommerceOrderId());
		commerceOrderItemImpl.setCPInstanceId(getCPInstanceId());
		commerceOrderItemImpl.setQuantity(getQuantity());
		commerceOrderItemImpl.setShippedQuantity(getShippedQuantity());
		commerceOrderItemImpl.setJson(getJson());
		commerceOrderItemImpl.setName(getName());
		commerceOrderItemImpl.setSku(getSku());
		commerceOrderItemImpl.setUnitPrice(getUnitPrice());
		commerceOrderItemImpl.setDiscountAmount(getDiscountAmount());
		commerceOrderItemImpl.setFinalPrice(getFinalPrice());
		commerceOrderItemImpl.setDiscountPercentageLevel1(getDiscountPercentageLevel1());
		commerceOrderItemImpl.setDiscountPercentageLevel2(getDiscountPercentageLevel2());
		commerceOrderItemImpl.setDiscountPercentageLevel3(getDiscountPercentageLevel3());
		commerceOrderItemImpl.setDiscountPercentageLevel4(getDiscountPercentageLevel4());
		commerceOrderItemImpl.setSubscription(isSubscription());

		commerceOrderItemImpl.resetOriginalValues();

		return commerceOrderItemImpl;
	}

	@Override
	public int compareTo(CommerceOrderItem commerceOrderItem) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(),
				commerceOrderItem.getCreateDate());

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

		if (!(obj instanceof CommerceOrderItem)) {
			return false;
		}

		CommerceOrderItem commerceOrderItem = (CommerceOrderItem)obj;

		long primaryKey = commerceOrderItem.getPrimaryKey();

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
		CommerceOrderItemModelImpl commerceOrderItemModelImpl = this;

		commerceOrderItemModelImpl._originalExternalReferenceCode = commerceOrderItemModelImpl._externalReferenceCode;

		commerceOrderItemModelImpl._originalCompanyId = commerceOrderItemModelImpl._companyId;

		commerceOrderItemModelImpl._setOriginalCompanyId = false;

		commerceOrderItemModelImpl._setModifiedDate = false;

		commerceOrderItemModelImpl._originalCommerceOrderId = commerceOrderItemModelImpl._commerceOrderId;

		commerceOrderItemModelImpl._setOriginalCommerceOrderId = false;

		commerceOrderItemModelImpl._originalCPInstanceId = commerceOrderItemModelImpl._CPInstanceId;

		commerceOrderItemModelImpl._setOriginalCPInstanceId = false;

		commerceOrderItemModelImpl._originalSubscription = commerceOrderItemModelImpl._subscription;

		commerceOrderItemModelImpl._setOriginalSubscription = false;

		commerceOrderItemModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<CommerceOrderItem> toCacheModel() {
		CommerceOrderItemCacheModel commerceOrderItemCacheModel = new CommerceOrderItemCacheModel();

		commerceOrderItemCacheModel.externalReferenceCode = getExternalReferenceCode();

		String externalReferenceCode = commerceOrderItemCacheModel.externalReferenceCode;

		if ((externalReferenceCode != null) &&
				(externalReferenceCode.length() == 0)) {
			commerceOrderItemCacheModel.externalReferenceCode = null;
		}

		commerceOrderItemCacheModel.commerceOrderItemId = getCommerceOrderItemId();

		commerceOrderItemCacheModel.groupId = getGroupId();

		commerceOrderItemCacheModel.companyId = getCompanyId();

		commerceOrderItemCacheModel.userId = getUserId();

		commerceOrderItemCacheModel.userName = getUserName();

		String userName = commerceOrderItemCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceOrderItemCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceOrderItemCacheModel.createDate = createDate.getTime();
		}
		else {
			commerceOrderItemCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceOrderItemCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			commerceOrderItemCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		commerceOrderItemCacheModel.commerceOrderId = getCommerceOrderId();

		commerceOrderItemCacheModel.CPInstanceId = getCPInstanceId();

		commerceOrderItemCacheModel.quantity = getQuantity();

		commerceOrderItemCacheModel.shippedQuantity = getShippedQuantity();

		commerceOrderItemCacheModel.json = getJson();

		String json = commerceOrderItemCacheModel.json;

		if ((json != null) && (json.length() == 0)) {
			commerceOrderItemCacheModel.json = null;
		}

		commerceOrderItemCacheModel.name = getName();

		String name = commerceOrderItemCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			commerceOrderItemCacheModel.name = null;
		}

		commerceOrderItemCacheModel.sku = getSku();

		String sku = commerceOrderItemCacheModel.sku;

		if ((sku != null) && (sku.length() == 0)) {
			commerceOrderItemCacheModel.sku = null;
		}

		commerceOrderItemCacheModel.unitPrice = getUnitPrice();

		commerceOrderItemCacheModel.discountAmount = getDiscountAmount();

		commerceOrderItemCacheModel.finalPrice = getFinalPrice();

		commerceOrderItemCacheModel.discountPercentageLevel1 = getDiscountPercentageLevel1();

		commerceOrderItemCacheModel.discountPercentageLevel2 = getDiscountPercentageLevel2();

		commerceOrderItemCacheModel.discountPercentageLevel3 = getDiscountPercentageLevel3();

		commerceOrderItemCacheModel.discountPercentageLevel4 = getDiscountPercentageLevel4();

		commerceOrderItemCacheModel.subscription = isSubscription();

		return commerceOrderItemCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(47);

		sb.append("{externalReferenceCode=");
		sb.append(getExternalReferenceCode());
		sb.append(", commerceOrderItemId=");
		sb.append(getCommerceOrderItemId());
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
		sb.append(", CPInstanceId=");
		sb.append(getCPInstanceId());
		sb.append(", quantity=");
		sb.append(getQuantity());
		sb.append(", shippedQuantity=");
		sb.append(getShippedQuantity());
		sb.append(", json=");
		sb.append(getJson());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", sku=");
		sb.append(getSku());
		sb.append(", unitPrice=");
		sb.append(getUnitPrice());
		sb.append(", discountAmount=");
		sb.append(getDiscountAmount());
		sb.append(", finalPrice=");
		sb.append(getFinalPrice());
		sb.append(", discountPercentageLevel1=");
		sb.append(getDiscountPercentageLevel1());
		sb.append(", discountPercentageLevel2=");
		sb.append(getDiscountPercentageLevel2());
		sb.append(", discountPercentageLevel3=");
		sb.append(getDiscountPercentageLevel3());
		sb.append(", discountPercentageLevel4=");
		sb.append(getDiscountPercentageLevel4());
		sb.append(", subscription=");
		sb.append(isSubscription());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(73);

		sb.append("<model><model-name>");
		sb.append("com.liferay.commerce.model.CommerceOrderItem");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>externalReferenceCode</column-name><column-value><![CDATA[");
		sb.append(getExternalReferenceCode());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>commerceOrderItemId</column-name><column-value><![CDATA[");
		sb.append(getCommerceOrderItemId());
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
			"<column><column-name>CPInstanceId</column-name><column-value><![CDATA[");
		sb.append(getCPInstanceId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>quantity</column-name><column-value><![CDATA[");
		sb.append(getQuantity());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>shippedQuantity</column-name><column-value><![CDATA[");
		sb.append(getShippedQuantity());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>json</column-name><column-value><![CDATA[");
		sb.append(getJson());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sku</column-name><column-value><![CDATA[");
		sb.append(getSku());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>unitPrice</column-name><column-value><![CDATA[");
		sb.append(getUnitPrice());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>discountAmount</column-name><column-value><![CDATA[");
		sb.append(getDiscountAmount());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>finalPrice</column-name><column-value><![CDATA[");
		sb.append(getFinalPrice());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>discountPercentageLevel1</column-name><column-value><![CDATA[");
		sb.append(getDiscountPercentageLevel1());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>discountPercentageLevel2</column-name><column-value><![CDATA[");
		sb.append(getDiscountPercentageLevel2());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>discountPercentageLevel3</column-name><column-value><![CDATA[");
		sb.append(getDiscountPercentageLevel3());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>discountPercentageLevel4</column-name><column-value><![CDATA[");
		sb.append(getDiscountPercentageLevel4());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>subscription</column-name><column-value><![CDATA[");
		sb.append(isSubscription());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = CommerceOrderItem.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			CommerceOrderItem.class, ModelWrapper.class
		};
	private String _externalReferenceCode;
	private String _originalExternalReferenceCode;
	private long _commerceOrderItemId;
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
	private long _CPInstanceId;
	private long _originalCPInstanceId;
	private boolean _setOriginalCPInstanceId;
	private int _quantity;
	private int _shippedQuantity;
	private String _json;
	private String _name;
	private String _nameCurrentLanguageId;
	private String _sku;
	private BigDecimal _unitPrice;
	private BigDecimal _discountAmount;
	private BigDecimal _finalPrice;
	private BigDecimal _discountPercentageLevel1;
	private BigDecimal _discountPercentageLevel2;
	private BigDecimal _discountPercentageLevel3;
	private BigDecimal _discountPercentageLevel4;
	private boolean _subscription;
	private boolean _originalSubscription;
	private boolean _setOriginalSubscription;
	private long _columnBitmask;
	private CommerceOrderItem _escapedModel;
}