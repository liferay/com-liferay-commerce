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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.LocalizedModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedAuditedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * The base model interface for the CPOptionCategory service. Represents a row in the &quot;CPOptionCategory&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.commerce.product.model.impl.CPOptionCategoryModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.commerce.product.model.impl.CPOptionCategoryImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see CPOptionCategory
 * @see com.liferay.commerce.product.model.impl.CPOptionCategoryImpl
 * @see com.liferay.commerce.product.model.impl.CPOptionCategoryModelImpl
 * @generated
 */
@ProviderType
public interface CPOptionCategoryModel extends BaseModel<CPOptionCategory>,
	LocalizedModel, ShardedModel, StagedAuditedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a cp option category model instance should use the {@link CPOptionCategory} interface instead.
	 */

	/**
	 * Returns the primary key of this cp option category.
	 *
	 * @return the primary key of this cp option category
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this cp option category.
	 *
	 * @param primaryKey the primary key of this cp option category
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this cp option category.
	 *
	 * @return the uuid of this cp option category
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this cp option category.
	 *
	 * @param uuid the uuid of this cp option category
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the cp option category ID of this cp option category.
	 *
	 * @return the cp option category ID of this cp option category
	 */
	public long getCPOptionCategoryId();

	/**
	 * Sets the cp option category ID of this cp option category.
	 *
	 * @param CPOptionCategoryId the cp option category ID of this cp option category
	 */
	public void setCPOptionCategoryId(long CPOptionCategoryId);

	/**
	 * Returns the company ID of this cp option category.
	 *
	 * @return the company ID of this cp option category
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this cp option category.
	 *
	 * @param companyId the company ID of this cp option category
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this cp option category.
	 *
	 * @return the user ID of this cp option category
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this cp option category.
	 *
	 * @param userId the user ID of this cp option category
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this cp option category.
	 *
	 * @return the user uuid of this cp option category
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this cp option category.
	 *
	 * @param userUuid the user uuid of this cp option category
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this cp option category.
	 *
	 * @return the user name of this cp option category
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this cp option category.
	 *
	 * @param userName the user name of this cp option category
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this cp option category.
	 *
	 * @return the create date of this cp option category
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this cp option category.
	 *
	 * @param createDate the create date of this cp option category
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this cp option category.
	 *
	 * @return the modified date of this cp option category
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this cp option category.
	 *
	 * @param modifiedDate the modified date of this cp option category
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the title of this cp option category.
	 *
	 * @return the title of this cp option category
	 */
	public String getTitle();

	/**
	 * Returns the localized title of this cp option category in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized title of this cp option category
	 */
	@AutoEscape
	public String getTitle(Locale locale);

	/**
	 * Returns the localized title of this cp option category in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this cp option category. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getTitle(Locale locale, boolean useDefault);

	/**
	 * Returns the localized title of this cp option category in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized title of this cp option category
	 */
	@AutoEscape
	public String getTitle(String languageId);

	/**
	 * Returns the localized title of this cp option category in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this cp option category
	 */
	@AutoEscape
	public String getTitle(String languageId, boolean useDefault);

	@AutoEscape
	public String getTitleCurrentLanguageId();

	@AutoEscape
	public String getTitleCurrentValue();

	/**
	 * Returns a map of the locales and localized titles of this cp option category.
	 *
	 * @return the locales and localized titles of this cp option category
	 */
	public Map<Locale, String> getTitleMap();

	/**
	 * Sets the title of this cp option category.
	 *
	 * @param title the title of this cp option category
	 */
	public void setTitle(String title);

	/**
	 * Sets the localized title of this cp option category in the language.
	 *
	 * @param title the localized title of this cp option category
	 * @param locale the locale of the language
	 */
	public void setTitle(String title, Locale locale);

	/**
	 * Sets the localized title of this cp option category in the language, and sets the default locale.
	 *
	 * @param title the localized title of this cp option category
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setTitle(String title, Locale locale, Locale defaultLocale);

	public void setTitleCurrentLanguageId(String languageId);

	/**
	 * Sets the localized titles of this cp option category from the map of locales and localized titles.
	 *
	 * @param titleMap the locales and localized titles of this cp option category
	 */
	public void setTitleMap(Map<Locale, String> titleMap);

	/**
	 * Sets the localized titles of this cp option category from the map of locales and localized titles, and sets the default locale.
	 *
	 * @param titleMap the locales and localized titles of this cp option category
	 * @param defaultLocale the default locale
	 */
	public void setTitleMap(Map<Locale, String> titleMap, Locale defaultLocale);

	/**
	 * Returns the description of this cp option category.
	 *
	 * @return the description of this cp option category
	 */
	public String getDescription();

	/**
	 * Returns the localized description of this cp option category in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized description of this cp option category
	 */
	@AutoEscape
	public String getDescription(Locale locale);

	/**
	 * Returns the localized description of this cp option category in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this cp option category. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getDescription(Locale locale, boolean useDefault);

	/**
	 * Returns the localized description of this cp option category in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized description of this cp option category
	 */
	@AutoEscape
	public String getDescription(String languageId);

	/**
	 * Returns the localized description of this cp option category in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this cp option category
	 */
	@AutoEscape
	public String getDescription(String languageId, boolean useDefault);

	@AutoEscape
	public String getDescriptionCurrentLanguageId();

	@AutoEscape
	public String getDescriptionCurrentValue();

	/**
	 * Returns a map of the locales and localized descriptions of this cp option category.
	 *
	 * @return the locales and localized descriptions of this cp option category
	 */
	public Map<Locale, String> getDescriptionMap();

	/**
	 * Sets the description of this cp option category.
	 *
	 * @param description the description of this cp option category
	 */
	public void setDescription(String description);

	/**
	 * Sets the localized description of this cp option category in the language.
	 *
	 * @param description the localized description of this cp option category
	 * @param locale the locale of the language
	 */
	public void setDescription(String description, Locale locale);

	/**
	 * Sets the localized description of this cp option category in the language, and sets the default locale.
	 *
	 * @param description the localized description of this cp option category
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setDescription(String description, Locale locale,
		Locale defaultLocale);

	public void setDescriptionCurrentLanguageId(String languageId);

	/**
	 * Sets the localized descriptions of this cp option category from the map of locales and localized descriptions.
	 *
	 * @param descriptionMap the locales and localized descriptions of this cp option category
	 */
	public void setDescriptionMap(Map<Locale, String> descriptionMap);

	/**
	 * Sets the localized descriptions of this cp option category from the map of locales and localized descriptions, and sets the default locale.
	 *
	 * @param descriptionMap the locales and localized descriptions of this cp option category
	 * @param defaultLocale the default locale
	 */
	public void setDescriptionMap(Map<Locale, String> descriptionMap,
		Locale defaultLocale);

	/**
	 * Returns the priority of this cp option category.
	 *
	 * @return the priority of this cp option category
	 */
	public double getPriority();

	/**
	 * Sets the priority of this cp option category.
	 *
	 * @param priority the priority of this cp option category
	 */
	public void setPriority(double priority);

	/**
	 * Returns the key of this cp option category.
	 *
	 * @return the key of this cp option category
	 */
	@AutoEscape
	public String getKey();

	/**
	 * Sets the key of this cp option category.
	 *
	 * @param key the key of this cp option category
	 */
	public void setKey(String key);

	/**
	 * Returns the last publish date of this cp option category.
	 *
	 * @return the last publish date of this cp option category
	 */
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this cp option category.
	 *
	 * @param lastPublishDate the last publish date of this cp option category
	 */
	public void setLastPublishDate(Date lastPublishDate);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public String[] getAvailableLanguageIds();

	@Override
	public String getDefaultLanguageId();

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException;

	@Override
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException;

	@Override
	public Object clone();

	@Override
	public int compareTo(CPOptionCategory cpOptionCategory);

	@Override
	public int hashCode();

	@Override
	public CacheModel<CPOptionCategory> toCacheModel();

	@Override
	public CPOptionCategory toEscapedModel();

	@Override
	public CPOptionCategory toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}