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

package com.liferay.commerce.product.model;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link CPDefinitionOptionValueRel}.
 * </p>
 *
 * @author Marco Leo
 * @see CPDefinitionOptionValueRel
 * @generated
 */
public class CPDefinitionOptionValueRelWrapper
	implements CPDefinitionOptionValueRel,
			   ModelWrapper<CPDefinitionOptionValueRel> {

	public CPDefinitionOptionValueRelWrapper(
		CPDefinitionOptionValueRel cpDefinitionOptionValueRel) {

		_cpDefinitionOptionValueRel = cpDefinitionOptionValueRel;
	}

	@Override
	public Class<?> getModelClass() {
		return CPDefinitionOptionValueRel.class;
	}

	@Override
	public String getModelClassName() {
		return CPDefinitionOptionValueRel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put(
			"CPDefinitionOptionValueRelId", getCPDefinitionOptionValueRelId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("CPDefinitionOptionRelId", getCPDefinitionOptionRelId());
		attributes.put("name", getName());
		attributes.put("priority", getPriority());
		attributes.put("key", getKey());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long CPDefinitionOptionValueRelId = (Long)attributes.get(
			"CPDefinitionOptionValueRelId");

		if (CPDefinitionOptionValueRelId != null) {
			setCPDefinitionOptionValueRelId(CPDefinitionOptionValueRelId);
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

		Long CPDefinitionOptionRelId = (Long)attributes.get(
			"CPDefinitionOptionRelId");

		if (CPDefinitionOptionRelId != null) {
			setCPDefinitionOptionRelId(CPDefinitionOptionRelId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Double priority = (Double)attributes.get("priority");

		if (priority != null) {
			setPriority(priority);
		}

		String key = (String)attributes.get("key");

		if (key != null) {
			setKey(key);
		}
	}

	@Override
	public Object clone() {
		return new CPDefinitionOptionValueRelWrapper(
			(CPDefinitionOptionValueRel)_cpDefinitionOptionValueRel.clone());
	}

	@Override
	public int compareTo(
		CPDefinitionOptionValueRel cpDefinitionOptionValueRel) {

		return _cpDefinitionOptionValueRel.compareTo(
			cpDefinitionOptionValueRel);
	}

	@Override
	public String[] getAvailableLanguageIds() {
		return _cpDefinitionOptionValueRel.getAvailableLanguageIds();
	}

	/**
	 * Returns the company ID of this cp definition option value rel.
	 *
	 * @return the company ID of this cp definition option value rel
	 */
	@Override
	public long getCompanyId() {
		return _cpDefinitionOptionValueRel.getCompanyId();
	}

	@Override
	public CPDefinitionOptionRel getCPDefinitionOptionRel()
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionOptionValueRel.getCPDefinitionOptionRel();
	}

	/**
	 * Returns the cp definition option rel ID of this cp definition option value rel.
	 *
	 * @return the cp definition option rel ID of this cp definition option value rel
	 */
	@Override
	public long getCPDefinitionOptionRelId() {
		return _cpDefinitionOptionValueRel.getCPDefinitionOptionRelId();
	}

	/**
	 * Returns the cp definition option value rel ID of this cp definition option value rel.
	 *
	 * @return the cp definition option value rel ID of this cp definition option value rel
	 */
	@Override
	public long getCPDefinitionOptionValueRelId() {
		return _cpDefinitionOptionValueRel.getCPDefinitionOptionValueRelId();
	}

	/**
	 * Returns the create date of this cp definition option value rel.
	 *
	 * @return the create date of this cp definition option value rel
	 */
	@Override
	public Date getCreateDate() {
		return _cpDefinitionOptionValueRel.getCreateDate();
	}

	@Override
	public String getDefaultLanguageId() {
		return _cpDefinitionOptionValueRel.getDefaultLanguageId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _cpDefinitionOptionValueRel.getExpandoBridge();
	}

	/**
	 * Returns the group ID of this cp definition option value rel.
	 *
	 * @return the group ID of this cp definition option value rel
	 */
	@Override
	public long getGroupId() {
		return _cpDefinitionOptionValueRel.getGroupId();
	}

	/**
	 * Returns the key of this cp definition option value rel.
	 *
	 * @return the key of this cp definition option value rel
	 */
	@Override
	public String getKey() {
		return _cpDefinitionOptionValueRel.getKey();
	}

	/**
	 * Returns the modified date of this cp definition option value rel.
	 *
	 * @return the modified date of this cp definition option value rel
	 */
	@Override
	public Date getModifiedDate() {
		return _cpDefinitionOptionValueRel.getModifiedDate();
	}

	/**
	 * Returns the name of this cp definition option value rel.
	 *
	 * @return the name of this cp definition option value rel
	 */
	@Override
	public String getName() {
		return _cpDefinitionOptionValueRel.getName();
	}

	/**
	 * Returns the localized name of this cp definition option value rel in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized name of this cp definition option value rel
	 */
	@Override
	public String getName(java.util.Locale locale) {
		return _cpDefinitionOptionValueRel.getName(locale);
	}

	/**
	 * Returns the localized name of this cp definition option value rel in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this cp definition option value rel. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getName(java.util.Locale locale, boolean useDefault) {
		return _cpDefinitionOptionValueRel.getName(locale, useDefault);
	}

	/**
	 * Returns the localized name of this cp definition option value rel in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized name of this cp definition option value rel
	 */
	@Override
	public String getName(String languageId) {
		return _cpDefinitionOptionValueRel.getName(languageId);
	}

	/**
	 * Returns the localized name of this cp definition option value rel in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this cp definition option value rel
	 */
	@Override
	public String getName(String languageId, boolean useDefault) {
		return _cpDefinitionOptionValueRel.getName(languageId, useDefault);
	}

	@Override
	public String getNameCurrentLanguageId() {
		return _cpDefinitionOptionValueRel.getNameCurrentLanguageId();
	}

	@Override
	public String getNameCurrentValue() {
		return _cpDefinitionOptionValueRel.getNameCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized names of this cp definition option value rel.
	 *
	 * @return the locales and localized names of this cp definition option value rel
	 */
	@Override
	public Map<java.util.Locale, String> getNameMap() {
		return _cpDefinitionOptionValueRel.getNameMap();
	}

	/**
	 * Returns the primary key of this cp definition option value rel.
	 *
	 * @return the primary key of this cp definition option value rel
	 */
	@Override
	public long getPrimaryKey() {
		return _cpDefinitionOptionValueRel.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _cpDefinitionOptionValueRel.getPrimaryKeyObj();
	}

	/**
	 * Returns the priority of this cp definition option value rel.
	 *
	 * @return the priority of this cp definition option value rel
	 */
	@Override
	public double getPriority() {
		return _cpDefinitionOptionValueRel.getPriority();
	}

	/**
	 * Returns the user ID of this cp definition option value rel.
	 *
	 * @return the user ID of this cp definition option value rel
	 */
	@Override
	public long getUserId() {
		return _cpDefinitionOptionValueRel.getUserId();
	}

	/**
	 * Returns the user name of this cp definition option value rel.
	 *
	 * @return the user name of this cp definition option value rel
	 */
	@Override
	public String getUserName() {
		return _cpDefinitionOptionValueRel.getUserName();
	}

	/**
	 * Returns the user uuid of this cp definition option value rel.
	 *
	 * @return the user uuid of this cp definition option value rel
	 */
	@Override
	public String getUserUuid() {
		return _cpDefinitionOptionValueRel.getUserUuid();
	}

	/**
	 * Returns the uuid of this cp definition option value rel.
	 *
	 * @return the uuid of this cp definition option value rel
	 */
	@Override
	public String getUuid() {
		return _cpDefinitionOptionValueRel.getUuid();
	}

	@Override
	public int hashCode() {
		return _cpDefinitionOptionValueRel.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _cpDefinitionOptionValueRel.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _cpDefinitionOptionValueRel.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _cpDefinitionOptionValueRel.isNew();
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a cp definition option value rel model instance should use the <code>CPDefinitionOptionValueRel</code> interface instead.
	 */
	@Override
	public void persist() {
		_cpDefinitionOptionValueRel.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {

		_cpDefinitionOptionValueRel.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
			java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {

		_cpDefinitionOptionValueRel.prepareLocalizedFieldsForImport(
			defaultImportLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_cpDefinitionOptionValueRel.setCachedModel(cachedModel);
	}

	/**
	 * Sets the company ID of this cp definition option value rel.
	 *
	 * @param companyId the company ID of this cp definition option value rel
	 */
	@Override
	public void setCompanyId(long companyId) {
		_cpDefinitionOptionValueRel.setCompanyId(companyId);
	}

	/**
	 * Sets the cp definition option rel ID of this cp definition option value rel.
	 *
	 * @param CPDefinitionOptionRelId the cp definition option rel ID of this cp definition option value rel
	 */
	@Override
	public void setCPDefinitionOptionRelId(long CPDefinitionOptionRelId) {
		_cpDefinitionOptionValueRel.setCPDefinitionOptionRelId(
			CPDefinitionOptionRelId);
	}

	/**
	 * Sets the cp definition option value rel ID of this cp definition option value rel.
	 *
	 * @param CPDefinitionOptionValueRelId the cp definition option value rel ID of this cp definition option value rel
	 */
	@Override
	public void setCPDefinitionOptionValueRelId(
		long CPDefinitionOptionValueRelId) {

		_cpDefinitionOptionValueRel.setCPDefinitionOptionValueRelId(
			CPDefinitionOptionValueRelId);
	}

	/**
	 * Sets the create date of this cp definition option value rel.
	 *
	 * @param createDate the create date of this cp definition option value rel
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_cpDefinitionOptionValueRel.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_cpDefinitionOptionValueRel.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_cpDefinitionOptionValueRel.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_cpDefinitionOptionValueRel.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the group ID of this cp definition option value rel.
	 *
	 * @param groupId the group ID of this cp definition option value rel
	 */
	@Override
	public void setGroupId(long groupId) {
		_cpDefinitionOptionValueRel.setGroupId(groupId);
	}

	/**
	 * Sets the key of this cp definition option value rel.
	 *
	 * @param key the key of this cp definition option value rel
	 */
	@Override
	public void setKey(String key) {
		_cpDefinitionOptionValueRel.setKey(key);
	}

	/**
	 * Sets the modified date of this cp definition option value rel.
	 *
	 * @param modifiedDate the modified date of this cp definition option value rel
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_cpDefinitionOptionValueRel.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the name of this cp definition option value rel.
	 *
	 * @param name the name of this cp definition option value rel
	 */
	@Override
	public void setName(String name) {
		_cpDefinitionOptionValueRel.setName(name);
	}

	/**
	 * Sets the localized name of this cp definition option value rel in the language.
	 *
	 * @param name the localized name of this cp definition option value rel
	 * @param locale the locale of the language
	 */
	@Override
	public void setName(String name, java.util.Locale locale) {
		_cpDefinitionOptionValueRel.setName(name, locale);
	}

	/**
	 * Sets the localized name of this cp definition option value rel in the language, and sets the default locale.
	 *
	 * @param name the localized name of this cp definition option value rel
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setName(
		String name, java.util.Locale locale, java.util.Locale defaultLocale) {

		_cpDefinitionOptionValueRel.setName(name, locale, defaultLocale);
	}

	@Override
	public void setNameCurrentLanguageId(String languageId) {
		_cpDefinitionOptionValueRel.setNameCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized names of this cp definition option value rel from the map of locales and localized names.
	 *
	 * @param nameMap the locales and localized names of this cp definition option value rel
	 */
	@Override
	public void setNameMap(Map<java.util.Locale, String> nameMap) {
		_cpDefinitionOptionValueRel.setNameMap(nameMap);
	}

	/**
	 * Sets the localized names of this cp definition option value rel from the map of locales and localized names, and sets the default locale.
	 *
	 * @param nameMap the locales and localized names of this cp definition option value rel
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setNameMap(
		Map<java.util.Locale, String> nameMap, java.util.Locale defaultLocale) {

		_cpDefinitionOptionValueRel.setNameMap(nameMap, defaultLocale);
	}

	@Override
	public void setNew(boolean n) {
		_cpDefinitionOptionValueRel.setNew(n);
	}

	/**
	 * Sets the primary key of this cp definition option value rel.
	 *
	 * @param primaryKey the primary key of this cp definition option value rel
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_cpDefinitionOptionValueRel.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_cpDefinitionOptionValueRel.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the priority of this cp definition option value rel.
	 *
	 * @param priority the priority of this cp definition option value rel
	 */
	@Override
	public void setPriority(double priority) {
		_cpDefinitionOptionValueRel.setPriority(priority);
	}

	/**
	 * Sets the user ID of this cp definition option value rel.
	 *
	 * @param userId the user ID of this cp definition option value rel
	 */
	@Override
	public void setUserId(long userId) {
		_cpDefinitionOptionValueRel.setUserId(userId);
	}

	/**
	 * Sets the user name of this cp definition option value rel.
	 *
	 * @param userName the user name of this cp definition option value rel
	 */
	@Override
	public void setUserName(String userName) {
		_cpDefinitionOptionValueRel.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this cp definition option value rel.
	 *
	 * @param userUuid the user uuid of this cp definition option value rel
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_cpDefinitionOptionValueRel.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this cp definition option value rel.
	 *
	 * @param uuid the uuid of this cp definition option value rel
	 */
	@Override
	public void setUuid(String uuid) {
		_cpDefinitionOptionValueRel.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel
		<CPDefinitionOptionValueRel> toCacheModel() {

		return _cpDefinitionOptionValueRel.toCacheModel();
	}

	@Override
	public CPDefinitionOptionValueRel toEscapedModel() {
		return new CPDefinitionOptionValueRelWrapper(
			_cpDefinitionOptionValueRel.toEscapedModel());
	}

	@Override
	public String toString() {
		return _cpDefinitionOptionValueRel.toString();
	}

	@Override
	public CPDefinitionOptionValueRel toUnescapedModel() {
		return new CPDefinitionOptionValueRelWrapper(
			_cpDefinitionOptionValueRel.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _cpDefinitionOptionValueRel.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CPDefinitionOptionValueRelWrapper)) {
			return false;
		}

		CPDefinitionOptionValueRelWrapper cpDefinitionOptionValueRelWrapper =
			(CPDefinitionOptionValueRelWrapper)obj;

		if (Objects.equals(
				_cpDefinitionOptionValueRel,
				cpDefinitionOptionValueRelWrapper.
					_cpDefinitionOptionValueRel)) {

			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _cpDefinitionOptionValueRel.getStagedModelType();
	}

	@Override
	public CPDefinitionOptionValueRel getWrappedModel() {
		return _cpDefinitionOptionValueRel;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _cpDefinitionOptionValueRel.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _cpDefinitionOptionValueRel.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_cpDefinitionOptionValueRel.resetOriginalValues();
	}

	private final CPDefinitionOptionValueRel _cpDefinitionOptionValueRel;

}