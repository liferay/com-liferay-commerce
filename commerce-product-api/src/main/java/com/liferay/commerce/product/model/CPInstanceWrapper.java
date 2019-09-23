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

import java.math.BigDecimal;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link CPInstance}.
 * </p>
 *
 * @author Marco Leo
 * @see CPInstance
 * @generated
 */
public class CPInstanceWrapper implements CPInstance, ModelWrapper<CPInstance> {

	public CPInstanceWrapper(CPInstance cpInstance) {
		_cpInstance = cpInstance;
	}

	@Override
	public Class<?> getModelClass() {
		return CPInstance.class;
	}

	@Override
	public String getModelClassName() {
		return CPInstance.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("externalReferenceCode", getExternalReferenceCode());
		attributes.put("CPInstanceId", getCPInstanceId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("CPDefinitionId", getCPDefinitionId());
		attributes.put("CPInstanceUuid", getCPInstanceUuid());
		attributes.put("sku", getSku());
		attributes.put("gtin", getGtin());
		attributes.put("manufacturerPartNumber", getManufacturerPartNumber());
		attributes.put("purchasable", isPurchasable());
		attributes.put("json", getJson());
		attributes.put("width", getWidth());
		attributes.put("height", getHeight());
		attributes.put("depth", getDepth());
		attributes.put("weight", getWeight());
		attributes.put("price", getPrice());
		attributes.put("promoPrice", getPromoPrice());
		attributes.put("cost", getCost());
		attributes.put("published", isPublished());
		attributes.put("displayDate", getDisplayDate());
		attributes.put("expirationDate", getExpirationDate());
		attributes.put("lastPublishDate", getLastPublishDate());
		attributes.put(
			"overrideSubscriptionInfo", isOverrideSubscriptionInfo());
		attributes.put("subscriptionEnabled", isSubscriptionEnabled());
		attributes.put("subscriptionLength", getSubscriptionLength());
		attributes.put("subscriptionType", getSubscriptionType());
		attributes.put(
			"subscriptionTypeSettings", getSubscriptionTypeSettings());
		attributes.put("maxSubscriptionCycles", getMaxSubscriptionCycles());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());

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

		Long CPInstanceId = (Long)attributes.get("CPInstanceId");

		if (CPInstanceId != null) {
			setCPInstanceId(CPInstanceId);
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

		Long CPDefinitionId = (Long)attributes.get("CPDefinitionId");

		if (CPDefinitionId != null) {
			setCPDefinitionId(CPDefinitionId);
		}

		String CPInstanceUuid = (String)attributes.get("CPInstanceUuid");

		if (CPInstanceUuid != null) {
			setCPInstanceUuid(CPInstanceUuid);
		}

		String sku = (String)attributes.get("sku");

		if (sku != null) {
			setSku(sku);
		}

		String gtin = (String)attributes.get("gtin");

		if (gtin != null) {
			setGtin(gtin);
		}

		String manufacturerPartNumber = (String)attributes.get(
			"manufacturerPartNumber");

		if (manufacturerPartNumber != null) {
			setManufacturerPartNumber(manufacturerPartNumber);
		}

		Boolean purchasable = (Boolean)attributes.get("purchasable");

		if (purchasable != null) {
			setPurchasable(purchasable);
		}

		String json = (String)attributes.get("json");

		if (json != null) {
			setJson(json);
		}

		Double width = (Double)attributes.get("width");

		if (width != null) {
			setWidth(width);
		}

		Double height = (Double)attributes.get("height");

		if (height != null) {
			setHeight(height);
		}

		Double depth = (Double)attributes.get("depth");

		if (depth != null) {
			setDepth(depth);
		}

		Double weight = (Double)attributes.get("weight");

		if (weight != null) {
			setWeight(weight);
		}

		BigDecimal price = (BigDecimal)attributes.get("price");

		if (price != null) {
			setPrice(price);
		}

		BigDecimal promoPrice = (BigDecimal)attributes.get("promoPrice");

		if (promoPrice != null) {
			setPromoPrice(promoPrice);
		}

		BigDecimal cost = (BigDecimal)attributes.get("cost");

		if (cost != null) {
			setCost(cost);
		}

		Boolean published = (Boolean)attributes.get("published");

		if (published != null) {
			setPublished(published);
		}

		Date displayDate = (Date)attributes.get("displayDate");

		if (displayDate != null) {
			setDisplayDate(displayDate);
		}

		Date expirationDate = (Date)attributes.get("expirationDate");

		if (expirationDate != null) {
			setExpirationDate(expirationDate);
		}

		Date lastPublishDate = (Date)attributes.get("lastPublishDate");

		if (lastPublishDate != null) {
			setLastPublishDate(lastPublishDate);
		}

		Boolean overrideSubscriptionInfo = (Boolean)attributes.get(
			"overrideSubscriptionInfo");

		if (overrideSubscriptionInfo != null) {
			setOverrideSubscriptionInfo(overrideSubscriptionInfo);
		}

		Boolean subscriptionEnabled = (Boolean)attributes.get(
			"subscriptionEnabled");

		if (subscriptionEnabled != null) {
			setSubscriptionEnabled(subscriptionEnabled);
		}

		Integer subscriptionLength = (Integer)attributes.get(
			"subscriptionLength");

		if (subscriptionLength != null) {
			setSubscriptionLength(subscriptionLength);
		}

		String subscriptionType = (String)attributes.get("subscriptionType");

		if (subscriptionType != null) {
			setSubscriptionType(subscriptionType);
		}

		String subscriptionTypeSettings = (String)attributes.get(
			"subscriptionTypeSettings");

		if (subscriptionTypeSettings != null) {
			setSubscriptionTypeSettings(subscriptionTypeSettings);
		}

		Long maxSubscriptionCycles = (Long)attributes.get(
			"maxSubscriptionCycles");

		if (maxSubscriptionCycles != null) {
			setMaxSubscriptionCycles(maxSubscriptionCycles);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long statusByUserId = (Long)attributes.get("statusByUserId");

		if (statusByUserId != null) {
			setStatusByUserId(statusByUserId);
		}

		String statusByUserName = (String)attributes.get("statusByUserName");

		if (statusByUserName != null) {
			setStatusByUserName(statusByUserName);
		}

		Date statusDate = (Date)attributes.get("statusDate");

		if (statusDate != null) {
			setStatusDate(statusDate);
		}
	}

	@Override
	public Object clone() {
		return new CPInstanceWrapper((CPInstance)_cpInstance.clone());
	}

	@Override
	public int compareTo(CPInstance cpInstance) {
		return _cpInstance.compareTo(cpInstance);
	}

	@Override
	public CommerceCatalog getCommerceCatalog()
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpInstance.getCommerceCatalog();
	}

	/**
	 * Returns the company ID of this cp instance.
	 *
	 * @return the company ID of this cp instance
	 */
	@Override
	public long getCompanyId() {
		return _cpInstance.getCompanyId();
	}

	/**
	 * Returns the cost of this cp instance.
	 *
	 * @return the cost of this cp instance
	 */
	@Override
	public BigDecimal getCost() {
		return _cpInstance.getCost();
	}

	@Override
	public CPDefinition getCPDefinition()
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpInstance.getCPDefinition();
	}

	/**
	 * Returns the cp definition ID of this cp instance.
	 *
	 * @return the cp definition ID of this cp instance
	 */
	@Override
	public long getCPDefinitionId() {
		return _cpInstance.getCPDefinitionId();
	}

	/**
	 * Returns the cp instance ID of this cp instance.
	 *
	 * @return the cp instance ID of this cp instance
	 */
	@Override
	public long getCPInstanceId() {
		return _cpInstance.getCPInstanceId();
	}

	/**
	 * Returns the cp instance uuid of this cp instance.
	 *
	 * @return the cp instance uuid of this cp instance
	 */
	@Override
	public String getCPInstanceUuid() {
		return _cpInstance.getCPInstanceUuid();
	}

	@Override
	public CPSubscriptionInfo getCPSubscriptionInfo()
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpInstance.getCPSubscriptionInfo();
	}

	/**
	 * Returns the create date of this cp instance.
	 *
	 * @return the create date of this cp instance
	 */
	@Override
	public Date getCreateDate() {
		return _cpInstance.getCreateDate();
	}

	/**
	 * Returns the depth of this cp instance.
	 *
	 * @return the depth of this cp instance
	 */
	@Override
	public double getDepth() {
		return _cpInstance.getDepth();
	}

	/**
	 * Returns the display date of this cp instance.
	 *
	 * @return the display date of this cp instance
	 */
	@Override
	public Date getDisplayDate() {
		return _cpInstance.getDisplayDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _cpInstance.getExpandoBridge();
	}

	/**
	 * Returns the expiration date of this cp instance.
	 *
	 * @return the expiration date of this cp instance
	 */
	@Override
	public Date getExpirationDate() {
		return _cpInstance.getExpirationDate();
	}

	/**
	 * Returns the external reference code of this cp instance.
	 *
	 * @return the external reference code of this cp instance
	 */
	@Override
	public String getExternalReferenceCode() {
		return _cpInstance.getExternalReferenceCode();
	}

	/**
	 * Returns the group ID of this cp instance.
	 *
	 * @return the group ID of this cp instance
	 */
	@Override
	public long getGroupId() {
		return _cpInstance.getGroupId();
	}

	/**
	 * Returns the gtin of this cp instance.
	 *
	 * @return the gtin of this cp instance
	 */
	@Override
	public String getGtin() {
		return _cpInstance.getGtin();
	}

	/**
	 * Returns the height of this cp instance.
	 *
	 * @return the height of this cp instance
	 */
	@Override
	public double getHeight() {
		return _cpInstance.getHeight();
	}

	/**
	 * Returns the json of this cp instance.
	 *
	 * @return the json of this cp instance
	 */
	@Override
	public String getJson() {
		return _cpInstance.getJson();
	}

	/**
	 * Returns the last publish date of this cp instance.
	 *
	 * @return the last publish date of this cp instance
	 */
	@Override
	public Date getLastPublishDate() {
		return _cpInstance.getLastPublishDate();
	}

	/**
	 * Returns the manufacturer part number of this cp instance.
	 *
	 * @return the manufacturer part number of this cp instance
	 */
	@Override
	public String getManufacturerPartNumber() {
		return _cpInstance.getManufacturerPartNumber();
	}

	/**
	 * Returns the max subscription cycles of this cp instance.
	 *
	 * @return the max subscription cycles of this cp instance
	 */
	@Override
	public long getMaxSubscriptionCycles() {
		return _cpInstance.getMaxSubscriptionCycles();
	}

	/**
	 * Returns the modified date of this cp instance.
	 *
	 * @return the modified date of this cp instance
	 */
	@Override
	public Date getModifiedDate() {
		return _cpInstance.getModifiedDate();
	}

	/**
	 * Returns the override subscription info of this cp instance.
	 *
	 * @return the override subscription info of this cp instance
	 */
	@Override
	public boolean getOverrideSubscriptionInfo() {
		return _cpInstance.getOverrideSubscriptionInfo();
	}

	/**
	 * Returns the price of this cp instance.
	 *
	 * @return the price of this cp instance
	 */
	@Override
	public BigDecimal getPrice() {
		return _cpInstance.getPrice();
	}

	/**
	 * Returns the primary key of this cp instance.
	 *
	 * @return the primary key of this cp instance
	 */
	@Override
	public long getPrimaryKey() {
		return _cpInstance.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _cpInstance.getPrimaryKeyObj();
	}

	/**
	 * Returns the promo price of this cp instance.
	 *
	 * @return the promo price of this cp instance
	 */
	@Override
	public BigDecimal getPromoPrice() {
		return _cpInstance.getPromoPrice();
	}

	/**
	 * Returns the published of this cp instance.
	 *
	 * @return the published of this cp instance
	 */
	@Override
	public boolean getPublished() {
		return _cpInstance.getPublished();
	}

	/**
	 * Returns the purchasable of this cp instance.
	 *
	 * @return the purchasable of this cp instance
	 */
	@Override
	public boolean getPurchasable() {
		return _cpInstance.getPurchasable();
	}

	/**
	 * Returns the sku of this cp instance.
	 *
	 * @return the sku of this cp instance
	 */
	@Override
	public String getSku() {
		return _cpInstance.getSku();
	}

	/**
	 * Returns the status of this cp instance.
	 *
	 * @return the status of this cp instance
	 */
	@Override
	public int getStatus() {
		return _cpInstance.getStatus();
	}

	/**
	 * Returns the status by user ID of this cp instance.
	 *
	 * @return the status by user ID of this cp instance
	 */
	@Override
	public long getStatusByUserId() {
		return _cpInstance.getStatusByUserId();
	}

	/**
	 * Returns the status by user name of this cp instance.
	 *
	 * @return the status by user name of this cp instance
	 */
	@Override
	public String getStatusByUserName() {
		return _cpInstance.getStatusByUserName();
	}

	/**
	 * Returns the status by user uuid of this cp instance.
	 *
	 * @return the status by user uuid of this cp instance
	 */
	@Override
	public String getStatusByUserUuid() {
		return _cpInstance.getStatusByUserUuid();
	}

	/**
	 * Returns the status date of this cp instance.
	 *
	 * @return the status date of this cp instance
	 */
	@Override
	public Date getStatusDate() {
		return _cpInstance.getStatusDate();
	}

	/**
	 * Returns the subscription enabled of this cp instance.
	 *
	 * @return the subscription enabled of this cp instance
	 */
	@Override
	public boolean getSubscriptionEnabled() {
		return _cpInstance.getSubscriptionEnabled();
	}

	/**
	 * Returns the subscription length of this cp instance.
	 *
	 * @return the subscription length of this cp instance
	 */
	@Override
	public int getSubscriptionLength() {
		return _cpInstance.getSubscriptionLength();
	}

	/**
	 * Returns the subscription type of this cp instance.
	 *
	 * @return the subscription type of this cp instance
	 */
	@Override
	public String getSubscriptionType() {
		return _cpInstance.getSubscriptionType();
	}

	/**
	 * Returns the subscription type settings of this cp instance.
	 *
	 * @return the subscription type settings of this cp instance
	 */
	@Override
	public String getSubscriptionTypeSettings() {
		return _cpInstance.getSubscriptionTypeSettings();
	}

	@Override
	public com.liferay.portal.kernel.util.UnicodeProperties
		getSubscriptionTypeSettingsProperties() {

		return _cpInstance.getSubscriptionTypeSettingsProperties();
	}

	/**
	 * Returns the user ID of this cp instance.
	 *
	 * @return the user ID of this cp instance
	 */
	@Override
	public long getUserId() {
		return _cpInstance.getUserId();
	}

	/**
	 * Returns the user name of this cp instance.
	 *
	 * @return the user name of this cp instance
	 */
	@Override
	public String getUserName() {
		return _cpInstance.getUserName();
	}

	/**
	 * Returns the user uuid of this cp instance.
	 *
	 * @return the user uuid of this cp instance
	 */
	@Override
	public String getUserUuid() {
		return _cpInstance.getUserUuid();
	}

	/**
	 * Returns the uuid of this cp instance.
	 *
	 * @return the uuid of this cp instance
	 */
	@Override
	public String getUuid() {
		return _cpInstance.getUuid();
	}

	/**
	 * Returns the weight of this cp instance.
	 *
	 * @return the weight of this cp instance
	 */
	@Override
	public double getWeight() {
		return _cpInstance.getWeight();
	}

	/**
	 * Returns the width of this cp instance.
	 *
	 * @return the width of this cp instance
	 */
	@Override
	public double getWidth() {
		return _cpInstance.getWidth();
	}

	@Override
	public int hashCode() {
		return _cpInstance.hashCode();
	}

	/**
	 * Returns <code>true</code> if this cp instance is approved.
	 *
	 * @return <code>true</code> if this cp instance is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved() {
		return _cpInstance.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _cpInstance.isCachedModel();
	}

	/**
	 * Returns <code>true</code> if this cp instance is denied.
	 *
	 * @return <code>true</code> if this cp instance is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied() {
		return _cpInstance.isDenied();
	}

	/**
	 * Returns <code>true</code> if this cp instance is a draft.
	 *
	 * @return <code>true</code> if this cp instance is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft() {
		return _cpInstance.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _cpInstance.isEscapedModel();
	}

	/**
	 * Returns <code>true</code> if this cp instance is expired.
	 *
	 * @return <code>true</code> if this cp instance is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired() {
		return _cpInstance.isExpired();
	}

	/**
	 * Returns <code>true</code> if this cp instance is inactive.
	 *
	 * @return <code>true</code> if this cp instance is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive() {
		return _cpInstance.isInactive();
	}

	/**
	 * Returns <code>true</code> if this cp instance is incomplete.
	 *
	 * @return <code>true</code> if this cp instance is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete() {
		return _cpInstance.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _cpInstance.isNew();
	}

	/**
	 * Returns <code>true</code> if this cp instance is override subscription info.
	 *
	 * @return <code>true</code> if this cp instance is override subscription info; <code>false</code> otherwise
	 */
	@Override
	public boolean isOverrideSubscriptionInfo() {
		return _cpInstance.isOverrideSubscriptionInfo();
	}

	/**
	 * Returns <code>true</code> if this cp instance is pending.
	 *
	 * @return <code>true</code> if this cp instance is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending() {
		return _cpInstance.isPending();
	}

	/**
	 * Returns <code>true</code> if this cp instance is published.
	 *
	 * @return <code>true</code> if this cp instance is published; <code>false</code> otherwise
	 */
	@Override
	public boolean isPublished() {
		return _cpInstance.isPublished();
	}

	/**
	 * Returns <code>true</code> if this cp instance is purchasable.
	 *
	 * @return <code>true</code> if this cp instance is purchasable; <code>false</code> otherwise
	 */
	@Override
	public boolean isPurchasable() {
		return _cpInstance.isPurchasable();
	}

	/**
	 * Returns <code>true</code> if this cp instance is scheduled.
	 *
	 * @return <code>true</code> if this cp instance is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled() {
		return _cpInstance.isScheduled();
	}

	/**
	 * Returns <code>true</code> if this cp instance is subscription enabled.
	 *
	 * @return <code>true</code> if this cp instance is subscription enabled; <code>false</code> otherwise
	 */
	@Override
	public boolean isSubscriptionEnabled() {
		return _cpInstance.isSubscriptionEnabled();
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a cp instance model instance should use the <code>CPInstance</code> interface instead.
	 */
	@Override
	public void persist() {
		_cpInstance.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_cpInstance.setCachedModel(cachedModel);
	}

	/**
	 * Sets the company ID of this cp instance.
	 *
	 * @param companyId the company ID of this cp instance
	 */
	@Override
	public void setCompanyId(long companyId) {
		_cpInstance.setCompanyId(companyId);
	}

	/**
	 * Sets the cost of this cp instance.
	 *
	 * @param cost the cost of this cp instance
	 */
	@Override
	public void setCost(BigDecimal cost) {
		_cpInstance.setCost(cost);
	}

	/**
	 * Sets the cp definition ID of this cp instance.
	 *
	 * @param CPDefinitionId the cp definition ID of this cp instance
	 */
	@Override
	public void setCPDefinitionId(long CPDefinitionId) {
		_cpInstance.setCPDefinitionId(CPDefinitionId);
	}

	/**
	 * Sets the cp instance ID of this cp instance.
	 *
	 * @param CPInstanceId the cp instance ID of this cp instance
	 */
	@Override
	public void setCPInstanceId(long CPInstanceId) {
		_cpInstance.setCPInstanceId(CPInstanceId);
	}

	/**
	 * Sets the cp instance uuid of this cp instance.
	 *
	 * @param CPInstanceUuid the cp instance uuid of this cp instance
	 */
	@Override
	public void setCPInstanceUuid(String CPInstanceUuid) {
		_cpInstance.setCPInstanceUuid(CPInstanceUuid);
	}

	/**
	 * Sets the create date of this cp instance.
	 *
	 * @param createDate the create date of this cp instance
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_cpInstance.setCreateDate(createDate);
	}

	/**
	 * Sets the depth of this cp instance.
	 *
	 * @param depth the depth of this cp instance
	 */
	@Override
	public void setDepth(double depth) {
		_cpInstance.setDepth(depth);
	}

	/**
	 * Sets the display date of this cp instance.
	 *
	 * @param displayDate the display date of this cp instance
	 */
	@Override
	public void setDisplayDate(Date displayDate) {
		_cpInstance.setDisplayDate(displayDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_cpInstance.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_cpInstance.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_cpInstance.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the expiration date of this cp instance.
	 *
	 * @param expirationDate the expiration date of this cp instance
	 */
	@Override
	public void setExpirationDate(Date expirationDate) {
		_cpInstance.setExpirationDate(expirationDate);
	}

	/**
	 * Sets the external reference code of this cp instance.
	 *
	 * @param externalReferenceCode the external reference code of this cp instance
	 */
	@Override
	public void setExternalReferenceCode(String externalReferenceCode) {
		_cpInstance.setExternalReferenceCode(externalReferenceCode);
	}

	/**
	 * Sets the group ID of this cp instance.
	 *
	 * @param groupId the group ID of this cp instance
	 */
	@Override
	public void setGroupId(long groupId) {
		_cpInstance.setGroupId(groupId);
	}

	/**
	 * Sets the gtin of this cp instance.
	 *
	 * @param gtin the gtin of this cp instance
	 */
	@Override
	public void setGtin(String gtin) {
		_cpInstance.setGtin(gtin);
	}

	/**
	 * Sets the height of this cp instance.
	 *
	 * @param height the height of this cp instance
	 */
	@Override
	public void setHeight(double height) {
		_cpInstance.setHeight(height);
	}

	/**
	 * Sets the json of this cp instance.
	 *
	 * @param json the json of this cp instance
	 */
	@Override
	public void setJson(String json) {
		_cpInstance.setJson(json);
	}

	/**
	 * Sets the last publish date of this cp instance.
	 *
	 * @param lastPublishDate the last publish date of this cp instance
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_cpInstance.setLastPublishDate(lastPublishDate);
	}

	/**
	 * Sets the manufacturer part number of this cp instance.
	 *
	 * @param manufacturerPartNumber the manufacturer part number of this cp instance
	 */
	@Override
	public void setManufacturerPartNumber(String manufacturerPartNumber) {
		_cpInstance.setManufacturerPartNumber(manufacturerPartNumber);
	}

	/**
	 * Sets the max subscription cycles of this cp instance.
	 *
	 * @param maxSubscriptionCycles the max subscription cycles of this cp instance
	 */
	@Override
	public void setMaxSubscriptionCycles(long maxSubscriptionCycles) {
		_cpInstance.setMaxSubscriptionCycles(maxSubscriptionCycles);
	}

	/**
	 * Sets the modified date of this cp instance.
	 *
	 * @param modifiedDate the modified date of this cp instance
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_cpInstance.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_cpInstance.setNew(n);
	}

	/**
	 * Sets whether this cp instance is override subscription info.
	 *
	 * @param overrideSubscriptionInfo the override subscription info of this cp instance
	 */
	@Override
	public void setOverrideSubscriptionInfo(boolean overrideSubscriptionInfo) {
		_cpInstance.setOverrideSubscriptionInfo(overrideSubscriptionInfo);
	}

	/**
	 * Sets the price of this cp instance.
	 *
	 * @param price the price of this cp instance
	 */
	@Override
	public void setPrice(BigDecimal price) {
		_cpInstance.setPrice(price);
	}

	/**
	 * Sets the primary key of this cp instance.
	 *
	 * @param primaryKey the primary key of this cp instance
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_cpInstance.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_cpInstance.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the promo price of this cp instance.
	 *
	 * @param promoPrice the promo price of this cp instance
	 */
	@Override
	public void setPromoPrice(BigDecimal promoPrice) {
		_cpInstance.setPromoPrice(promoPrice);
	}

	/**
	 * Sets whether this cp instance is published.
	 *
	 * @param published the published of this cp instance
	 */
	@Override
	public void setPublished(boolean published) {
		_cpInstance.setPublished(published);
	}

	/**
	 * Sets whether this cp instance is purchasable.
	 *
	 * @param purchasable the purchasable of this cp instance
	 */
	@Override
	public void setPurchasable(boolean purchasable) {
		_cpInstance.setPurchasable(purchasable);
	}

	/**
	 * Sets the sku of this cp instance.
	 *
	 * @param sku the sku of this cp instance
	 */
	@Override
	public void setSku(String sku) {
		_cpInstance.setSku(sku);
	}

	/**
	 * Sets the status of this cp instance.
	 *
	 * @param status the status of this cp instance
	 */
	@Override
	public void setStatus(int status) {
		_cpInstance.setStatus(status);
	}

	/**
	 * Sets the status by user ID of this cp instance.
	 *
	 * @param statusByUserId the status by user ID of this cp instance
	 */
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_cpInstance.setStatusByUserId(statusByUserId);
	}

	/**
	 * Sets the status by user name of this cp instance.
	 *
	 * @param statusByUserName the status by user name of this cp instance
	 */
	@Override
	public void setStatusByUserName(String statusByUserName) {
		_cpInstance.setStatusByUserName(statusByUserName);
	}

	/**
	 * Sets the status by user uuid of this cp instance.
	 *
	 * @param statusByUserUuid the status by user uuid of this cp instance
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
		_cpInstance.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	 * Sets the status date of this cp instance.
	 *
	 * @param statusDate the status date of this cp instance
	 */
	@Override
	public void setStatusDate(Date statusDate) {
		_cpInstance.setStatusDate(statusDate);
	}

	/**
	 * Sets whether this cp instance is subscription enabled.
	 *
	 * @param subscriptionEnabled the subscription enabled of this cp instance
	 */
	@Override
	public void setSubscriptionEnabled(boolean subscriptionEnabled) {
		_cpInstance.setSubscriptionEnabled(subscriptionEnabled);
	}

	/**
	 * Sets the subscription length of this cp instance.
	 *
	 * @param subscriptionLength the subscription length of this cp instance
	 */
	@Override
	public void setSubscriptionLength(int subscriptionLength) {
		_cpInstance.setSubscriptionLength(subscriptionLength);
	}

	/**
	 * Sets the subscription type of this cp instance.
	 *
	 * @param subscriptionType the subscription type of this cp instance
	 */
	@Override
	public void setSubscriptionType(String subscriptionType) {
		_cpInstance.setSubscriptionType(subscriptionType);
	}

	/**
	 * Sets the subscription type settings of this cp instance.
	 *
	 * @param subscriptionTypeSettings the subscription type settings of this cp instance
	 */
	@Override
	public void setSubscriptionTypeSettings(String subscriptionTypeSettings) {
		_cpInstance.setSubscriptionTypeSettings(subscriptionTypeSettings);
	}

	@Override
	public void setSubscriptionTypeSettingsProperties(
		com.liferay.portal.kernel.util.UnicodeProperties
			subscriptionTypeSettingsProperties) {

		_cpInstance.setSubscriptionTypeSettingsProperties(
			subscriptionTypeSettingsProperties);
	}

	/**
	 * Sets the user ID of this cp instance.
	 *
	 * @param userId the user ID of this cp instance
	 */
	@Override
	public void setUserId(long userId) {
		_cpInstance.setUserId(userId);
	}

	/**
	 * Sets the user name of this cp instance.
	 *
	 * @param userName the user name of this cp instance
	 */
	@Override
	public void setUserName(String userName) {
		_cpInstance.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this cp instance.
	 *
	 * @param userUuid the user uuid of this cp instance
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_cpInstance.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this cp instance.
	 *
	 * @param uuid the uuid of this cp instance
	 */
	@Override
	public void setUuid(String uuid) {
		_cpInstance.setUuid(uuid);
	}

	/**
	 * Sets the weight of this cp instance.
	 *
	 * @param weight the weight of this cp instance
	 */
	@Override
	public void setWeight(double weight) {
		_cpInstance.setWeight(weight);
	}

	/**
	 * Sets the width of this cp instance.
	 *
	 * @param width the width of this cp instance
	 */
	@Override
	public void setWidth(double width) {
		_cpInstance.setWidth(width);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CPInstance>
		toCacheModel() {

		return _cpInstance.toCacheModel();
	}

	@Override
	public CPInstance toEscapedModel() {
		return new CPInstanceWrapper(_cpInstance.toEscapedModel());
	}

	@Override
	public String toString() {
		return _cpInstance.toString();
	}

	@Override
	public CPInstance toUnescapedModel() {
		return new CPInstanceWrapper(_cpInstance.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _cpInstance.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CPInstanceWrapper)) {
			return false;
		}

		CPInstanceWrapper cpInstanceWrapper = (CPInstanceWrapper)obj;

		if (Objects.equals(_cpInstance, cpInstanceWrapper._cpInstance)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _cpInstance.getStagedModelType();
	}

	@Override
	public CPInstance getWrappedModel() {
		return _cpInstance;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _cpInstance.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _cpInstance.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_cpInstance.resetOriginalValues();
	}

	private final CPInstance _cpInstance;

}