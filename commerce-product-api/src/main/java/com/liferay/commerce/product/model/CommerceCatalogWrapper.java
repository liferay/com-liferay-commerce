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

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link CommerceCatalog}.
 * </p>
 *
 * @author Marco Leo
 * @see CommerceCatalog
 * @generated
 */
@ProviderType
public class CommerceCatalogWrapper implements CommerceCatalog,
	ModelWrapper<CommerceCatalog> {
	public CommerceCatalogWrapper(CommerceCatalog commerceCatalog) {
		_commerceCatalog = commerceCatalog;
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceCatalog.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceCatalog.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("commerceCatalogId", getCommerceCatalogId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("catalogDefaultLanguageId", getCatalogDefaultLanguageId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long commerceCatalogId = (Long)attributes.get("commerceCatalogId");

		if (commerceCatalogId != null) {
			setCommerceCatalogId(commerceCatalogId);
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

		String catalogDefaultLanguageId = (String)attributes.get(
				"catalogDefaultLanguageId");

		if (catalogDefaultLanguageId != null) {
			setCatalogDefaultLanguageId(catalogDefaultLanguageId);
		}
	}

	@Override
	public Object clone() {
		return new CommerceCatalogWrapper((CommerceCatalog)_commerceCatalog.clone());
	}

	@Override
	public int compareTo(CommerceCatalog commerceCatalog) {
		return _commerceCatalog.compareTo(commerceCatalog);
	}

	@Override
	public String[] getAvailableLanguageIds() {
		return _commerceCatalog.getAvailableLanguageIds();
	}

	/**
	* Returns the catalog default language ID of this commerce catalog.
	*
	* @return the catalog default language ID of this commerce catalog
	*/
	@Override
	public String getCatalogDefaultLanguageId() {
		return _commerceCatalog.getCatalogDefaultLanguageId();
	}

	/**
	* Returns the commerce catalog ID of this commerce catalog.
	*
	* @return the commerce catalog ID of this commerce catalog
	*/
	@Override
	public long getCommerceCatalogId() {
		return _commerceCatalog.getCommerceCatalogId();
	}

	/**
	* Returns the company ID of this commerce catalog.
	*
	* @return the company ID of this commerce catalog
	*/
	@Override
	public long getCompanyId() {
		return _commerceCatalog.getCompanyId();
	}

	/**
	* Returns the create date of this commerce catalog.
	*
	* @return the create date of this commerce catalog
	*/
	@Override
	public Date getCreateDate() {
		return _commerceCatalog.getCreateDate();
	}

	@Override
	public String getDefaultLanguageId() {
		return _commerceCatalog.getDefaultLanguageId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _commerceCatalog.getExpandoBridge();
	}

	/**
	* Returns the modified date of this commerce catalog.
	*
	* @return the modified date of this commerce catalog
	*/
	@Override
	public Date getModifiedDate() {
		return _commerceCatalog.getModifiedDate();
	}

	/**
	* Returns the name of this commerce catalog.
	*
	* @return the name of this commerce catalog
	*/
	@Override
	public String getName() {
		return _commerceCatalog.getName();
	}

	/**
	* Returns the localized name of this commerce catalog in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized name of this commerce catalog
	*/
	@Override
	public String getName(java.util.Locale locale) {
		return _commerceCatalog.getName(locale);
	}

	/**
	* Returns the localized name of this commerce catalog in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this commerce catalog. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public String getName(java.util.Locale locale, boolean useDefault) {
		return _commerceCatalog.getName(locale, useDefault);
	}

	/**
	* Returns the localized name of this commerce catalog in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized name of this commerce catalog
	*/
	@Override
	public String getName(String languageId) {
		return _commerceCatalog.getName(languageId);
	}

	/**
	* Returns the localized name of this commerce catalog in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this commerce catalog
	*/
	@Override
	public String getName(String languageId, boolean useDefault) {
		return _commerceCatalog.getName(languageId, useDefault);
	}

	@Override
	public String getNameCurrentLanguageId() {
		return _commerceCatalog.getNameCurrentLanguageId();
	}

	@Override
	public String getNameCurrentValue() {
		return _commerceCatalog.getNameCurrentValue();
	}

	/**
	* Returns a map of the locales and localized names of this commerce catalog.
	*
	* @return the locales and localized names of this commerce catalog
	*/
	@Override
	public Map<java.util.Locale, String> getNameMap() {
		return _commerceCatalog.getNameMap();
	}

	/**
	* Returns the primary key of this commerce catalog.
	*
	* @return the primary key of this commerce catalog
	*/
	@Override
	public long getPrimaryKey() {
		return _commerceCatalog.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceCatalog.getPrimaryKeyObj();
	}

	/**
	* Returns the user ID of this commerce catalog.
	*
	* @return the user ID of this commerce catalog
	*/
	@Override
	public long getUserId() {
		return _commerceCatalog.getUserId();
	}

	/**
	* Returns the user name of this commerce catalog.
	*
	* @return the user name of this commerce catalog
	*/
	@Override
	public String getUserName() {
		return _commerceCatalog.getUserName();
	}

	/**
	* Returns the user uuid of this commerce catalog.
	*
	* @return the user uuid of this commerce catalog
	*/
	@Override
	public String getUserUuid() {
		return _commerceCatalog.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _commerceCatalog.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _commerceCatalog.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _commerceCatalog.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _commerceCatalog.isNew();
	}

	@Override
	public void persist() {
		_commerceCatalog.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_commerceCatalog.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_commerceCatalog.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_commerceCatalog.setCachedModel(cachedModel);
	}

	/**
	* Sets the catalog default language ID of this commerce catalog.
	*
	* @param catalogDefaultLanguageId the catalog default language ID of this commerce catalog
	*/
	@Override
	public void setCatalogDefaultLanguageId(String catalogDefaultLanguageId) {
		_commerceCatalog.setCatalogDefaultLanguageId(catalogDefaultLanguageId);
	}

	/**
	* Sets the commerce catalog ID of this commerce catalog.
	*
	* @param commerceCatalogId the commerce catalog ID of this commerce catalog
	*/
	@Override
	public void setCommerceCatalogId(long commerceCatalogId) {
		_commerceCatalog.setCommerceCatalogId(commerceCatalogId);
	}

	/**
	* Sets the company ID of this commerce catalog.
	*
	* @param companyId the company ID of this commerce catalog
	*/
	@Override
	public void setCompanyId(long companyId) {
		_commerceCatalog.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this commerce catalog.
	*
	* @param createDate the create date of this commerce catalog
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_commerceCatalog.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_commerceCatalog.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_commerceCatalog.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_commerceCatalog.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the modified date of this commerce catalog.
	*
	* @param modifiedDate the modified date of this commerce catalog
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_commerceCatalog.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this commerce catalog.
	*
	* @param name the name of this commerce catalog
	*/
	@Override
	public void setName(String name) {
		_commerceCatalog.setName(name);
	}

	/**
	* Sets the localized name of this commerce catalog in the language.
	*
	* @param name the localized name of this commerce catalog
	* @param locale the locale of the language
	*/
	@Override
	public void setName(String name, java.util.Locale locale) {
		_commerceCatalog.setName(name, locale);
	}

	/**
	* Sets the localized name of this commerce catalog in the language, and sets the default locale.
	*
	* @param name the localized name of this commerce catalog
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setName(String name, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_commerceCatalog.setName(name, locale, defaultLocale);
	}

	@Override
	public void setNameCurrentLanguageId(String languageId) {
		_commerceCatalog.setNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized names of this commerce catalog from the map of locales and localized names.
	*
	* @param nameMap the locales and localized names of this commerce catalog
	*/
	@Override
	public void setNameMap(Map<java.util.Locale, String> nameMap) {
		_commerceCatalog.setNameMap(nameMap);
	}

	/**
	* Sets the localized names of this commerce catalog from the map of locales and localized names, and sets the default locale.
	*
	* @param nameMap the locales and localized names of this commerce catalog
	* @param defaultLocale the default locale
	*/
	@Override
	public void setNameMap(Map<java.util.Locale, String> nameMap,
		java.util.Locale defaultLocale) {
		_commerceCatalog.setNameMap(nameMap, defaultLocale);
	}

	@Override
	public void setNew(boolean n) {
		_commerceCatalog.setNew(n);
	}

	/**
	* Sets the primary key of this commerce catalog.
	*
	* @param primaryKey the primary key of this commerce catalog
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_commerceCatalog.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_commerceCatalog.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this commerce catalog.
	*
	* @param userId the user ID of this commerce catalog
	*/
	@Override
	public void setUserId(long userId) {
		_commerceCatalog.setUserId(userId);
	}

	/**
	* Sets the user name of this commerce catalog.
	*
	* @param userName the user name of this commerce catalog
	*/
	@Override
	public void setUserName(String userName) {
		_commerceCatalog.setUserName(userName);
	}

	/**
	* Sets the user uuid of this commerce catalog.
	*
	* @param userUuid the user uuid of this commerce catalog
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_commerceCatalog.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CommerceCatalog> toCacheModel() {
		return _commerceCatalog.toCacheModel();
	}

	@Override
	public CommerceCatalog toEscapedModel() {
		return new CommerceCatalogWrapper(_commerceCatalog.toEscapedModel());
	}

	@Override
	public String toString() {
		return _commerceCatalog.toString();
	}

	@Override
	public CommerceCatalog toUnescapedModel() {
		return new CommerceCatalogWrapper(_commerceCatalog.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _commerceCatalog.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceCatalogWrapper)) {
			return false;
		}

		CommerceCatalogWrapper commerceCatalogWrapper = (CommerceCatalogWrapper)obj;

		if (Objects.equals(_commerceCatalog,
					commerceCatalogWrapper._commerceCatalog)) {
			return true;
		}

		return false;
	}

	@Override
	public CommerceCatalog getWrappedModel() {
		return _commerceCatalog;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _commerceCatalog.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _commerceCatalog.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_commerceCatalog.resetOriginalValues();
	}

	private final CommerceCatalog _commerceCatalog;
}