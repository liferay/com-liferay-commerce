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

package com.liferay.commerce.discount.model;

import aQute.bnd.annotation.ProviderType;

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
 * This class is a wrapper for {@link CommerceDiscount}.
 * </p>
 *
 * @author Marco Leo
 * @see CommerceDiscount
 * @generated
 */
@ProviderType
public class CommerceDiscountWrapper
	implements CommerceDiscount, ModelWrapper<CommerceDiscount> {

	public CommerceDiscountWrapper(CommerceDiscount commerceDiscount) {
		_commerceDiscount = commerceDiscount;
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceDiscount.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceDiscount.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("externalReferenceCode", getExternalReferenceCode());
		attributes.put("commerceDiscountId", getCommerceDiscountId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("title", getTitle());
		attributes.put("target", getTarget());
		attributes.put("useCouponCode", isUseCouponCode());
		attributes.put("couponCode", getCouponCode());
		attributes.put("usePercentage", isUsePercentage());
		attributes.put("maximumDiscountAmount", getMaximumDiscountAmount());
		attributes.put("level1", getLevel1());
		attributes.put("level2", getLevel2());
		attributes.put("level3", getLevel3());
		attributes.put("level4", getLevel4());
		attributes.put("limitationType", getLimitationType());
		attributes.put("limitationTimes", getLimitationTimes());
		attributes.put("numberOfUse", getNumberOfUse());
		attributes.put("active", isActive());
		attributes.put("displayDate", getDisplayDate());
		attributes.put("expirationDate", getExpirationDate());
		attributes.put("lastPublishDate", getLastPublishDate());
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

		Long commerceDiscountId = (Long)attributes.get("commerceDiscountId");

		if (commerceDiscountId != null) {
			setCommerceDiscountId(commerceDiscountId);
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

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String target = (String)attributes.get("target");

		if (target != null) {
			setTarget(target);
		}

		Boolean useCouponCode = (Boolean)attributes.get("useCouponCode");

		if (useCouponCode != null) {
			setUseCouponCode(useCouponCode);
		}

		String couponCode = (String)attributes.get("couponCode");

		if (couponCode != null) {
			setCouponCode(couponCode);
		}

		Boolean usePercentage = (Boolean)attributes.get("usePercentage");

		if (usePercentage != null) {
			setUsePercentage(usePercentage);
		}

		BigDecimal maximumDiscountAmount = (BigDecimal)attributes.get(
			"maximumDiscountAmount");

		if (maximumDiscountAmount != null) {
			setMaximumDiscountAmount(maximumDiscountAmount);
		}

		BigDecimal level1 = (BigDecimal)attributes.get("level1");

		if (level1 != null) {
			setLevel1(level1);
		}

		BigDecimal level2 = (BigDecimal)attributes.get("level2");

		if (level2 != null) {
			setLevel2(level2);
		}

		BigDecimal level3 = (BigDecimal)attributes.get("level3");

		if (level3 != null) {
			setLevel3(level3);
		}

		BigDecimal level4 = (BigDecimal)attributes.get("level4");

		if (level4 != null) {
			setLevel4(level4);
		}

		String limitationType = (String)attributes.get("limitationType");

		if (limitationType != null) {
			setLimitationType(limitationType);
		}

		Integer limitationTimes = (Integer)attributes.get("limitationTimes");

		if (limitationTimes != null) {
			setLimitationTimes(limitationTimes);
		}

		Integer numberOfUse = (Integer)attributes.get("numberOfUse");

		if (numberOfUse != null) {
			setNumberOfUse(numberOfUse);
		}

		Boolean active = (Boolean)attributes.get("active");

		if (active != null) {
			setActive(active);
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
		return new CommerceDiscountWrapper(
			(CommerceDiscount)_commerceDiscount.clone());
	}

	@Override
	public int compareTo(CommerceDiscount commerceDiscount) {
		return _commerceDiscount.compareTo(commerceDiscount);
	}

	/**
	 * Returns the active of this commerce discount.
	 *
	 * @return the active of this commerce discount
	 */
	@Override
	public boolean getActive() {
		return _commerceDiscount.getActive();
	}

	@Override
	public java.util.List<CommerceDiscountCommerceAccountGroupRel>
		getCommerceDiscountCommerceAccountGroupRels() {

		return _commerceDiscount.getCommerceDiscountCommerceAccountGroupRels();
	}

	/**
	 * Returns the commerce discount ID of this commerce discount.
	 *
	 * @return the commerce discount ID of this commerce discount
	 */
	@Override
	public long getCommerceDiscountId() {
		return _commerceDiscount.getCommerceDiscountId();
	}

	/**
	 * Returns the company ID of this commerce discount.
	 *
	 * @return the company ID of this commerce discount
	 */
	@Override
	public long getCompanyId() {
		return _commerceDiscount.getCompanyId();
	}

	/**
	 * Returns the coupon code of this commerce discount.
	 *
	 * @return the coupon code of this commerce discount
	 */
	@Override
	public String getCouponCode() {
		return _commerceDiscount.getCouponCode();
	}

	/**
	 * Returns the create date of this commerce discount.
	 *
	 * @return the create date of this commerce discount
	 */
	@Override
	public Date getCreateDate() {
		return _commerceDiscount.getCreateDate();
	}

	/**
	 * Returns the display date of this commerce discount.
	 *
	 * @return the display date of this commerce discount
	 */
	@Override
	public Date getDisplayDate() {
		return _commerceDiscount.getDisplayDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _commerceDiscount.getExpandoBridge();
	}

	/**
	 * Returns the expiration date of this commerce discount.
	 *
	 * @return the expiration date of this commerce discount
	 */
	@Override
	public Date getExpirationDate() {
		return _commerceDiscount.getExpirationDate();
	}

	/**
	 * Returns the external reference code of this commerce discount.
	 *
	 * @return the external reference code of this commerce discount
	 */
	@Override
	public String getExternalReferenceCode() {
		return _commerceDiscount.getExternalReferenceCode();
	}

	/**
	 * Returns the last publish date of this commerce discount.
	 *
	 * @return the last publish date of this commerce discount
	 */
	@Override
	public Date getLastPublishDate() {
		return _commerceDiscount.getLastPublishDate();
	}

	/**
	 * Returns the level1 of this commerce discount.
	 *
	 * @return the level1 of this commerce discount
	 */
	@Override
	public BigDecimal getLevel1() {
		return _commerceDiscount.getLevel1();
	}

	/**
	 * Returns the level2 of this commerce discount.
	 *
	 * @return the level2 of this commerce discount
	 */
	@Override
	public BigDecimal getLevel2() {
		return _commerceDiscount.getLevel2();
	}

	/**
	 * Returns the level3 of this commerce discount.
	 *
	 * @return the level3 of this commerce discount
	 */
	@Override
	public BigDecimal getLevel3() {
		return _commerceDiscount.getLevel3();
	}

	/**
	 * Returns the level4 of this commerce discount.
	 *
	 * @return the level4 of this commerce discount
	 */
	@Override
	public BigDecimal getLevel4() {
		return _commerceDiscount.getLevel4();
	}

	/**
	 * Returns the limitation times of this commerce discount.
	 *
	 * @return the limitation times of this commerce discount
	 */
	@Override
	public int getLimitationTimes() {
		return _commerceDiscount.getLimitationTimes();
	}

	/**
	 * Returns the limitation type of this commerce discount.
	 *
	 * @return the limitation type of this commerce discount
	 */
	@Override
	public String getLimitationType() {
		return _commerceDiscount.getLimitationType();
	}

	/**
	 * Returns the maximum discount amount of this commerce discount.
	 *
	 * @return the maximum discount amount of this commerce discount
	 */
	@Override
	public BigDecimal getMaximumDiscountAmount() {
		return _commerceDiscount.getMaximumDiscountAmount();
	}

	/**
	 * Returns the modified date of this commerce discount.
	 *
	 * @return the modified date of this commerce discount
	 */
	@Override
	public Date getModifiedDate() {
		return _commerceDiscount.getModifiedDate();
	}

	/**
	 * Returns the number of use of this commerce discount.
	 *
	 * @return the number of use of this commerce discount
	 */
	@Override
	public int getNumberOfUse() {
		return _commerceDiscount.getNumberOfUse();
	}

	/**
	 * Returns the primary key of this commerce discount.
	 *
	 * @return the primary key of this commerce discount
	 */
	@Override
	public long getPrimaryKey() {
		return _commerceDiscount.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceDiscount.getPrimaryKeyObj();
	}

	/**
	 * Returns the status of this commerce discount.
	 *
	 * @return the status of this commerce discount
	 */
	@Override
	public int getStatus() {
		return _commerceDiscount.getStatus();
	}

	/**
	 * Returns the status by user ID of this commerce discount.
	 *
	 * @return the status by user ID of this commerce discount
	 */
	@Override
	public long getStatusByUserId() {
		return _commerceDiscount.getStatusByUserId();
	}

	/**
	 * Returns the status by user name of this commerce discount.
	 *
	 * @return the status by user name of this commerce discount
	 */
	@Override
	public String getStatusByUserName() {
		return _commerceDiscount.getStatusByUserName();
	}

	/**
	 * Returns the status by user uuid of this commerce discount.
	 *
	 * @return the status by user uuid of this commerce discount
	 */
	@Override
	public String getStatusByUserUuid() {
		return _commerceDiscount.getStatusByUserUuid();
	}

	/**
	 * Returns the status date of this commerce discount.
	 *
	 * @return the status date of this commerce discount
	 */
	@Override
	public Date getStatusDate() {
		return _commerceDiscount.getStatusDate();
	}

	/**
	 * Returns the target of this commerce discount.
	 *
	 * @return the target of this commerce discount
	 */
	@Override
	public String getTarget() {
		return _commerceDiscount.getTarget();
	}

	/**
	 * Returns the title of this commerce discount.
	 *
	 * @return the title of this commerce discount
	 */
	@Override
	public String getTitle() {
		return _commerceDiscount.getTitle();
	}

	/**
	 * Returns the use coupon code of this commerce discount.
	 *
	 * @return the use coupon code of this commerce discount
	 */
	@Override
	public boolean getUseCouponCode() {
		return _commerceDiscount.getUseCouponCode();
	}

	/**
	 * Returns the use percentage of this commerce discount.
	 *
	 * @return the use percentage of this commerce discount
	 */
	@Override
	public boolean getUsePercentage() {
		return _commerceDiscount.getUsePercentage();
	}

	/**
	 * Returns the user ID of this commerce discount.
	 *
	 * @return the user ID of this commerce discount
	 */
	@Override
	public long getUserId() {
		return _commerceDiscount.getUserId();
	}

	/**
	 * Returns the user name of this commerce discount.
	 *
	 * @return the user name of this commerce discount
	 */
	@Override
	public String getUserName() {
		return _commerceDiscount.getUserName();
	}

	/**
	 * Returns the user uuid of this commerce discount.
	 *
	 * @return the user uuid of this commerce discount
	 */
	@Override
	public String getUserUuid() {
		return _commerceDiscount.getUserUuid();
	}

	/**
	 * Returns the uuid of this commerce discount.
	 *
	 * @return the uuid of this commerce discount
	 */
	@Override
	public String getUuid() {
		return _commerceDiscount.getUuid();
	}

	@Override
	public int hashCode() {
		return _commerceDiscount.hashCode();
	}

	/**
	 * Returns <code>true</code> if this commerce discount is active.
	 *
	 * @return <code>true</code> if this commerce discount is active; <code>false</code> otherwise
	 */
	@Override
	public boolean isActive() {
		return _commerceDiscount.isActive();
	}

	/**
	 * Returns <code>true</code> if this commerce discount is approved.
	 *
	 * @return <code>true</code> if this commerce discount is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved() {
		return _commerceDiscount.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _commerceDiscount.isCachedModel();
	}

	/**
	 * Returns <code>true</code> if this commerce discount is denied.
	 *
	 * @return <code>true</code> if this commerce discount is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied() {
		return _commerceDiscount.isDenied();
	}

	/**
	 * Returns <code>true</code> if this commerce discount is a draft.
	 *
	 * @return <code>true</code> if this commerce discount is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft() {
		return _commerceDiscount.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _commerceDiscount.isEscapedModel();
	}

	/**
	 * Returns <code>true</code> if this commerce discount is expired.
	 *
	 * @return <code>true</code> if this commerce discount is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired() {
		return _commerceDiscount.isExpired();
	}

	/**
	 * Returns <code>true</code> if this commerce discount is inactive.
	 *
	 * @return <code>true</code> if this commerce discount is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive() {
		return _commerceDiscount.isInactive();
	}

	/**
	 * Returns <code>true</code> if this commerce discount is incomplete.
	 *
	 * @return <code>true</code> if this commerce discount is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete() {
		return _commerceDiscount.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _commerceDiscount.isNew();
	}

	/**
	 * Returns <code>true</code> if this commerce discount is pending.
	 *
	 * @return <code>true</code> if this commerce discount is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending() {
		return _commerceDiscount.isPending();
	}

	/**
	 * Returns <code>true</code> if this commerce discount is scheduled.
	 *
	 * @return <code>true</code> if this commerce discount is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled() {
		return _commerceDiscount.isScheduled();
	}

	/**
	 * Returns <code>true</code> if this commerce discount is use coupon code.
	 *
	 * @return <code>true</code> if this commerce discount is use coupon code; <code>false</code> otherwise
	 */
	@Override
	public boolean isUseCouponCode() {
		return _commerceDiscount.isUseCouponCode();
	}

	/**
	 * Returns <code>true</code> if this commerce discount is use percentage.
	 *
	 * @return <code>true</code> if this commerce discount is use percentage; <code>false</code> otherwise
	 */
	@Override
	public boolean isUsePercentage() {
		return _commerceDiscount.isUsePercentage();
	}

	@Override
	public void persist() {
		_commerceDiscount.persist();
	}

	/**
	 * Sets whether this commerce discount is active.
	 *
	 * @param active the active of this commerce discount
	 */
	@Override
	public void setActive(boolean active) {
		_commerceDiscount.setActive(active);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_commerceDiscount.setCachedModel(cachedModel);
	}

	/**
	 * Sets the commerce discount ID of this commerce discount.
	 *
	 * @param commerceDiscountId the commerce discount ID of this commerce discount
	 */
	@Override
	public void setCommerceDiscountId(long commerceDiscountId) {
		_commerceDiscount.setCommerceDiscountId(commerceDiscountId);
	}

	/**
	 * Sets the company ID of this commerce discount.
	 *
	 * @param companyId the company ID of this commerce discount
	 */
	@Override
	public void setCompanyId(long companyId) {
		_commerceDiscount.setCompanyId(companyId);
	}

	/**
	 * Sets the coupon code of this commerce discount.
	 *
	 * @param couponCode the coupon code of this commerce discount
	 */
	@Override
	public void setCouponCode(String couponCode) {
		_commerceDiscount.setCouponCode(couponCode);
	}

	/**
	 * Sets the create date of this commerce discount.
	 *
	 * @param createDate the create date of this commerce discount
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_commerceDiscount.setCreateDate(createDate);
	}

	/**
	 * Sets the display date of this commerce discount.
	 *
	 * @param displayDate the display date of this commerce discount
	 */
	@Override
	public void setDisplayDate(Date displayDate) {
		_commerceDiscount.setDisplayDate(displayDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_commerceDiscount.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_commerceDiscount.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_commerceDiscount.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the expiration date of this commerce discount.
	 *
	 * @param expirationDate the expiration date of this commerce discount
	 */
	@Override
	public void setExpirationDate(Date expirationDate) {
		_commerceDiscount.setExpirationDate(expirationDate);
	}

	/**
	 * Sets the external reference code of this commerce discount.
	 *
	 * @param externalReferenceCode the external reference code of this commerce discount
	 */
	@Override
	public void setExternalReferenceCode(String externalReferenceCode) {
		_commerceDiscount.setExternalReferenceCode(externalReferenceCode);
	}

	/**
	 * Sets the last publish date of this commerce discount.
	 *
	 * @param lastPublishDate the last publish date of this commerce discount
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_commerceDiscount.setLastPublishDate(lastPublishDate);
	}

	/**
	 * Sets the level1 of this commerce discount.
	 *
	 * @param level1 the level1 of this commerce discount
	 */
	@Override
	public void setLevel1(BigDecimal level1) {
		_commerceDiscount.setLevel1(level1);
	}

	/**
	 * Sets the level2 of this commerce discount.
	 *
	 * @param level2 the level2 of this commerce discount
	 */
	@Override
	public void setLevel2(BigDecimal level2) {
		_commerceDiscount.setLevel2(level2);
	}

	/**
	 * Sets the level3 of this commerce discount.
	 *
	 * @param level3 the level3 of this commerce discount
	 */
	@Override
	public void setLevel3(BigDecimal level3) {
		_commerceDiscount.setLevel3(level3);
	}

	/**
	 * Sets the level4 of this commerce discount.
	 *
	 * @param level4 the level4 of this commerce discount
	 */
	@Override
	public void setLevel4(BigDecimal level4) {
		_commerceDiscount.setLevel4(level4);
	}

	/**
	 * Sets the limitation times of this commerce discount.
	 *
	 * @param limitationTimes the limitation times of this commerce discount
	 */
	@Override
	public void setLimitationTimes(int limitationTimes) {
		_commerceDiscount.setLimitationTimes(limitationTimes);
	}

	/**
	 * Sets the limitation type of this commerce discount.
	 *
	 * @param limitationType the limitation type of this commerce discount
	 */
	@Override
	public void setLimitationType(String limitationType) {
		_commerceDiscount.setLimitationType(limitationType);
	}

	/**
	 * Sets the maximum discount amount of this commerce discount.
	 *
	 * @param maximumDiscountAmount the maximum discount amount of this commerce discount
	 */
	@Override
	public void setMaximumDiscountAmount(BigDecimal maximumDiscountAmount) {
		_commerceDiscount.setMaximumDiscountAmount(maximumDiscountAmount);
	}

	/**
	 * Sets the modified date of this commerce discount.
	 *
	 * @param modifiedDate the modified date of this commerce discount
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_commerceDiscount.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_commerceDiscount.setNew(n);
	}

	/**
	 * Sets the number of use of this commerce discount.
	 *
	 * @param numberOfUse the number of use of this commerce discount
	 */
	@Override
	public void setNumberOfUse(int numberOfUse) {
		_commerceDiscount.setNumberOfUse(numberOfUse);
	}

	/**
	 * Sets the primary key of this commerce discount.
	 *
	 * @param primaryKey the primary key of this commerce discount
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_commerceDiscount.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_commerceDiscount.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the status of this commerce discount.
	 *
	 * @param status the status of this commerce discount
	 */
	@Override
	public void setStatus(int status) {
		_commerceDiscount.setStatus(status);
	}

	/**
	 * Sets the status by user ID of this commerce discount.
	 *
	 * @param statusByUserId the status by user ID of this commerce discount
	 */
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_commerceDiscount.setStatusByUserId(statusByUserId);
	}

	/**
	 * Sets the status by user name of this commerce discount.
	 *
	 * @param statusByUserName the status by user name of this commerce discount
	 */
	@Override
	public void setStatusByUserName(String statusByUserName) {
		_commerceDiscount.setStatusByUserName(statusByUserName);
	}

	/**
	 * Sets the status by user uuid of this commerce discount.
	 *
	 * @param statusByUserUuid the status by user uuid of this commerce discount
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
		_commerceDiscount.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	 * Sets the status date of this commerce discount.
	 *
	 * @param statusDate the status date of this commerce discount
	 */
	@Override
	public void setStatusDate(Date statusDate) {
		_commerceDiscount.setStatusDate(statusDate);
	}

	/**
	 * Sets the target of this commerce discount.
	 *
	 * @param target the target of this commerce discount
	 */
	@Override
	public void setTarget(String target) {
		_commerceDiscount.setTarget(target);
	}

	/**
	 * Sets the title of this commerce discount.
	 *
	 * @param title the title of this commerce discount
	 */
	@Override
	public void setTitle(String title) {
		_commerceDiscount.setTitle(title);
	}

	/**
	 * Sets whether this commerce discount is use coupon code.
	 *
	 * @param useCouponCode the use coupon code of this commerce discount
	 */
	@Override
	public void setUseCouponCode(boolean useCouponCode) {
		_commerceDiscount.setUseCouponCode(useCouponCode);
	}

	/**
	 * Sets whether this commerce discount is use percentage.
	 *
	 * @param usePercentage the use percentage of this commerce discount
	 */
	@Override
	public void setUsePercentage(boolean usePercentage) {
		_commerceDiscount.setUsePercentage(usePercentage);
	}

	/**
	 * Sets the user ID of this commerce discount.
	 *
	 * @param userId the user ID of this commerce discount
	 */
	@Override
	public void setUserId(long userId) {
		_commerceDiscount.setUserId(userId);
	}

	/**
	 * Sets the user name of this commerce discount.
	 *
	 * @param userName the user name of this commerce discount
	 */
	@Override
	public void setUserName(String userName) {
		_commerceDiscount.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this commerce discount.
	 *
	 * @param userUuid the user uuid of this commerce discount
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_commerceDiscount.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this commerce discount.
	 *
	 * @param uuid the uuid of this commerce discount
	 */
	@Override
	public void setUuid(String uuid) {
		_commerceDiscount.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CommerceDiscount>
		toCacheModel() {

		return _commerceDiscount.toCacheModel();
	}

	@Override
	public CommerceDiscount toEscapedModel() {
		return new CommerceDiscountWrapper(_commerceDiscount.toEscapedModel());
	}

	@Override
	public String toString() {
		return _commerceDiscount.toString();
	}

	@Override
	public CommerceDiscount toUnescapedModel() {
		return new CommerceDiscountWrapper(
			_commerceDiscount.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _commerceDiscount.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceDiscountWrapper)) {
			return false;
		}

		CommerceDiscountWrapper commerceDiscountWrapper =
			(CommerceDiscountWrapper)obj;

		if (Objects.equals(
				_commerceDiscount, commerceDiscountWrapper._commerceDiscount)) {

			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _commerceDiscount.getStagedModelType();
	}

	@Override
	public CommerceDiscount getWrappedModel() {
		return _commerceDiscount;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _commerceDiscount.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _commerceDiscount.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_commerceDiscount.resetOriginalValues();
	}

	private final CommerceDiscount _commerceDiscount;

}