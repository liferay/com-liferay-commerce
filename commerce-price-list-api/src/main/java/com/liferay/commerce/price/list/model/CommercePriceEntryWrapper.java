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

package com.liferay.commerce.price.list.model;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link CommercePriceEntry}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommercePriceEntry
 * @generated
 */
public class CommercePriceEntryWrapper
	implements CommercePriceEntry, ModelWrapper<CommercePriceEntry> {

	public CommercePriceEntryWrapper(CommercePriceEntry commercePriceEntry) {
		_commercePriceEntry = commercePriceEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return CommercePriceEntry.class;
	}

	@Override
	public String getModelClassName() {
		return CommercePriceEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("externalReferenceCode", getExternalReferenceCode());
		attributes.put("commercePriceEntryId", getCommercePriceEntryId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("commercePriceListId", getCommercePriceListId());
		attributes.put("CPInstanceUuid", getCPInstanceUuid());
		attributes.put("CProductId", getCProductId());
		attributes.put("price", getPrice());
		attributes.put("promoPrice", getPromoPrice());
		attributes.put("hasTierPrice", isHasTierPrice());
		attributes.put("lastPublishDate", getLastPublishDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		String externalReferenceCode = (String)attributes.get(
			"externalReferenceCode");

		if (externalReferenceCode != null) {
			setExternalReferenceCode(externalReferenceCode);
		}

		Long commercePriceEntryId = (Long)attributes.get(
			"commercePriceEntryId");

		if (commercePriceEntryId != null) {
			setCommercePriceEntryId(commercePriceEntryId);
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

		Long commercePriceListId = (Long)attributes.get("commercePriceListId");

		if (commercePriceListId != null) {
			setCommercePriceListId(commercePriceListId);
		}

		String CPInstanceUuid = (String)attributes.get("CPInstanceUuid");

		if (CPInstanceUuid != null) {
			setCPInstanceUuid(CPInstanceUuid);
		}

		Long CProductId = (Long)attributes.get("CProductId");

		if (CProductId != null) {
			setCProductId(CProductId);
		}

		BigDecimal price = (BigDecimal)attributes.get("price");

		if (price != null) {
			setPrice(price);
		}

		BigDecimal promoPrice = (BigDecimal)attributes.get("promoPrice");

		if (promoPrice != null) {
			setPromoPrice(promoPrice);
		}

		Boolean hasTierPrice = (Boolean)attributes.get("hasTierPrice");

		if (hasTierPrice != null) {
			setHasTierPrice(hasTierPrice);
		}

		Date lastPublishDate = (Date)attributes.get("lastPublishDate");

		if (lastPublishDate != null) {
			setLastPublishDate(lastPublishDate);
		}
	}

	@Override
	public Object clone() {
		return new CommercePriceEntryWrapper(
			(CommercePriceEntry)_commercePriceEntry.clone());
	}

	@Override
	public int compareTo(CommercePriceEntry commercePriceEntry) {
		return _commercePriceEntry.compareTo(commercePriceEntry);
	}

	/**
	 * Returns the commerce price entry ID of this commerce price entry.
	 *
	 * @return the commerce price entry ID of this commerce price entry
	 */
	@Override
	public long getCommercePriceEntryId() {
		return _commercePriceEntry.getCommercePriceEntryId();
	}

	@Override
	public CommercePriceList getCommercePriceList()
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceEntry.getCommercePriceList();
	}

	/**
	 * Returns the commerce price list ID of this commerce price entry.
	 *
	 * @return the commerce price list ID of this commerce price entry
	 */
	@Override
	public long getCommercePriceListId() {
		return _commercePriceEntry.getCommercePriceListId();
	}

	/**
	 * Returns the company ID of this commerce price entry.
	 *
	 * @return the company ID of this commerce price entry
	 */
	@Override
	public long getCompanyId() {
		return _commercePriceEntry.getCompanyId();
	}

	@Override
	public com.liferay.commerce.product.model.CPInstance getCPInstance()
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceEntry.getCPInstance();
	}

	/**
	 * Returns the cp instance uuid of this commerce price entry.
	 *
	 * @return the cp instance uuid of this commerce price entry
	 */
	@Override
	public String getCPInstanceUuid() {
		return _commercePriceEntry.getCPInstanceUuid();
	}

	/**
	 * Returns the c product ID of this commerce price entry.
	 *
	 * @return the c product ID of this commerce price entry
	 */
	@Override
	public long getCProductId() {
		return _commercePriceEntry.getCProductId();
	}

	/**
	 * Returns the create date of this commerce price entry.
	 *
	 * @return the create date of this commerce price entry
	 */
	@Override
	public Date getCreateDate() {
		return _commercePriceEntry.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _commercePriceEntry.getExpandoBridge();
	}

	/**
	 * Returns the external reference code of this commerce price entry.
	 *
	 * @return the external reference code of this commerce price entry
	 */
	@Override
	public String getExternalReferenceCode() {
		return _commercePriceEntry.getExternalReferenceCode();
	}

	/**
	 * Returns the has tier price of this commerce price entry.
	 *
	 * @return the has tier price of this commerce price entry
	 */
	@Override
	public boolean getHasTierPrice() {
		return _commercePriceEntry.getHasTierPrice();
	}

	/**
	 * Returns the last publish date of this commerce price entry.
	 *
	 * @return the last publish date of this commerce price entry
	 */
	@Override
	public Date getLastPublishDate() {
		return _commercePriceEntry.getLastPublishDate();
	}

	/**
	 * Returns the modified date of this commerce price entry.
	 *
	 * @return the modified date of this commerce price entry
	 */
	@Override
	public Date getModifiedDate() {
		return _commercePriceEntry.getModifiedDate();
	}

	/**
	 * Returns the price of this commerce price entry.
	 *
	 * @return the price of this commerce price entry
	 */
	@Override
	public BigDecimal getPrice() {
		return _commercePriceEntry.getPrice();
	}

	@Override
	public com.liferay.commerce.currency.model.CommerceMoney getPriceMoney(
			long commerceCurrencyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceEntry.getPriceMoney(commerceCurrencyId);
	}

	/**
	 * Returns the primary key of this commerce price entry.
	 *
	 * @return the primary key of this commerce price entry
	 */
	@Override
	public long getPrimaryKey() {
		return _commercePriceEntry.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commercePriceEntry.getPrimaryKeyObj();
	}

	/**
	 * Returns the promo price of this commerce price entry.
	 *
	 * @return the promo price of this commerce price entry
	 */
	@Override
	public BigDecimal getPromoPrice() {
		return _commercePriceEntry.getPromoPrice();
	}

	@Override
	public com.liferay.commerce.currency.model.CommerceMoney getPromoPriceMoney(
			long commerceCurrencyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceEntry.getPromoPriceMoney(commerceCurrencyId);
	}

	/**
	 * Returns the user ID of this commerce price entry.
	 *
	 * @return the user ID of this commerce price entry
	 */
	@Override
	public long getUserId() {
		return _commercePriceEntry.getUserId();
	}

	/**
	 * Returns the user name of this commerce price entry.
	 *
	 * @return the user name of this commerce price entry
	 */
	@Override
	public String getUserName() {
		return _commercePriceEntry.getUserName();
	}

	/**
	 * Returns the user uuid of this commerce price entry.
	 *
	 * @return the user uuid of this commerce price entry
	 */
	@Override
	public String getUserUuid() {
		return _commercePriceEntry.getUserUuid();
	}

	/**
	 * Returns the uuid of this commerce price entry.
	 *
	 * @return the uuid of this commerce price entry
	 */
	@Override
	public String getUuid() {
		return _commercePriceEntry.getUuid();
	}

	@Override
	public int hashCode() {
		return _commercePriceEntry.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _commercePriceEntry.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _commercePriceEntry.isEscapedModel();
	}

	/**
	 * Returns <code>true</code> if this commerce price entry is has tier price.
	 *
	 * @return <code>true</code> if this commerce price entry is has tier price; <code>false</code> otherwise
	 */
	@Override
	public boolean isHasTierPrice() {
		return _commercePriceEntry.isHasTierPrice();
	}

	@Override
	public boolean isNew() {
		return _commercePriceEntry.isNew();
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce price entry model instance should use the <code>CommercePriceEntry</code> interface instead.
	 */
	@Override
	public void persist() {
		_commercePriceEntry.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_commercePriceEntry.setCachedModel(cachedModel);
	}

	/**
	 * Sets the commerce price entry ID of this commerce price entry.
	 *
	 * @param commercePriceEntryId the commerce price entry ID of this commerce price entry
	 */
	@Override
	public void setCommercePriceEntryId(long commercePriceEntryId) {
		_commercePriceEntry.setCommercePriceEntryId(commercePriceEntryId);
	}

	/**
	 * Sets the commerce price list ID of this commerce price entry.
	 *
	 * @param commercePriceListId the commerce price list ID of this commerce price entry
	 */
	@Override
	public void setCommercePriceListId(long commercePriceListId) {
		_commercePriceEntry.setCommercePriceListId(commercePriceListId);
	}

	/**
	 * Sets the company ID of this commerce price entry.
	 *
	 * @param companyId the company ID of this commerce price entry
	 */
	@Override
	public void setCompanyId(long companyId) {
		_commercePriceEntry.setCompanyId(companyId);
	}

	/**
	 * Sets the cp instance uuid of this commerce price entry.
	 *
	 * @param CPInstanceUuid the cp instance uuid of this commerce price entry
	 */
	@Override
	public void setCPInstanceUuid(String CPInstanceUuid) {
		_commercePriceEntry.setCPInstanceUuid(CPInstanceUuid);
	}

	/**
	 * Sets the c product ID of this commerce price entry.
	 *
	 * @param CProductId the c product ID of this commerce price entry
	 */
	@Override
	public void setCProductId(long CProductId) {
		_commercePriceEntry.setCProductId(CProductId);
	}

	/**
	 * Sets the create date of this commerce price entry.
	 *
	 * @param createDate the create date of this commerce price entry
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_commercePriceEntry.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_commercePriceEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_commercePriceEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_commercePriceEntry.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the external reference code of this commerce price entry.
	 *
	 * @param externalReferenceCode the external reference code of this commerce price entry
	 */
	@Override
	public void setExternalReferenceCode(String externalReferenceCode) {
		_commercePriceEntry.setExternalReferenceCode(externalReferenceCode);
	}

	/**
	 * Sets whether this commerce price entry is has tier price.
	 *
	 * @param hasTierPrice the has tier price of this commerce price entry
	 */
	@Override
	public void setHasTierPrice(boolean hasTierPrice) {
		_commercePriceEntry.setHasTierPrice(hasTierPrice);
	}

	/**
	 * Sets the last publish date of this commerce price entry.
	 *
	 * @param lastPublishDate the last publish date of this commerce price entry
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_commercePriceEntry.setLastPublishDate(lastPublishDate);
	}

	/**
	 * Sets the modified date of this commerce price entry.
	 *
	 * @param modifiedDate the modified date of this commerce price entry
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_commercePriceEntry.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_commercePriceEntry.setNew(n);
	}

	/**
	 * Sets the price of this commerce price entry.
	 *
	 * @param price the price of this commerce price entry
	 */
	@Override
	public void setPrice(BigDecimal price) {
		_commercePriceEntry.setPrice(price);
	}

	/**
	 * Sets the primary key of this commerce price entry.
	 *
	 * @param primaryKey the primary key of this commerce price entry
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_commercePriceEntry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_commercePriceEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the promo price of this commerce price entry.
	 *
	 * @param promoPrice the promo price of this commerce price entry
	 */
	@Override
	public void setPromoPrice(BigDecimal promoPrice) {
		_commercePriceEntry.setPromoPrice(promoPrice);
	}

	/**
	 * Sets the user ID of this commerce price entry.
	 *
	 * @param userId the user ID of this commerce price entry
	 */
	@Override
	public void setUserId(long userId) {
		_commercePriceEntry.setUserId(userId);
	}

	/**
	 * Sets the user name of this commerce price entry.
	 *
	 * @param userName the user name of this commerce price entry
	 */
	@Override
	public void setUserName(String userName) {
		_commercePriceEntry.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this commerce price entry.
	 *
	 * @param userUuid the user uuid of this commerce price entry
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_commercePriceEntry.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this commerce price entry.
	 *
	 * @param uuid the uuid of this commerce price entry
	 */
	@Override
	public void setUuid(String uuid) {
		_commercePriceEntry.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CommercePriceEntry>
		toCacheModel() {

		return _commercePriceEntry.toCacheModel();
	}

	@Override
	public CommercePriceEntry toEscapedModel() {
		return new CommercePriceEntryWrapper(
			_commercePriceEntry.toEscapedModel());
	}

	@Override
	public String toString() {
		return _commercePriceEntry.toString();
	}

	@Override
	public CommercePriceEntry toUnescapedModel() {
		return new CommercePriceEntryWrapper(
			_commercePriceEntry.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _commercePriceEntry.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommercePriceEntryWrapper)) {
			return false;
		}

		CommercePriceEntryWrapper commercePriceEntryWrapper =
			(CommercePriceEntryWrapper)obj;

		if (Objects.equals(
				_commercePriceEntry,
				commercePriceEntryWrapper._commercePriceEntry)) {

			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _commercePriceEntry.getStagedModelType();
	}

	@Override
	public CommercePriceEntry getWrappedModel() {
		return _commercePriceEntry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _commercePriceEntry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _commercePriceEntry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_commercePriceEntry.resetOriginalValues();
	}

	private final CommercePriceEntry _commercePriceEntry;

}