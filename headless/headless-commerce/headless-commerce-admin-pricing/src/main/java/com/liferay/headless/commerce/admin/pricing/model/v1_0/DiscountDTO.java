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

package com.liferay.headless.commerce.admin.pricing.model.v1_0;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import com.liferay.commerce.openapi.core.annotation.Nullable;

import java.math.BigDecimal;

import java.util.Date;

import javax.annotation.Generated;

/**
 * @author Alessio Antonio Rendina
 */
@Generated(value = "OSGiRESTModuleGenerator")
@JacksonXmlRootElement(localName = "Discount")
public class DiscountDTO {

	@Nullable
	public String getCouponCode() {
		return _couponCode;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
	@Nullable
	public Date getDisplayDate() {
		return _displayDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
	@Nullable
	public Date getExpirationDate() {
		return _expirationDate;
	}

	@Nullable
	public Long getGroupId() {
		return _groupId;
	}

	@Nullable
	public Long getId() {
		return _id;
	}

	@Nullable
	public Integer getLimitationTimes() {
		return _limitationTimes;
	}

	public String getLimitationType() {
		return _limitationType;
	}

	@Nullable
	public BigDecimal getMaximumDiscountAmount() {
		return _maximumDiscountAmount;
	}

	@Nullable
	public Integer getNumberOfUse() {
		return _numberOfUse;
	}

	@Nullable
	public BigDecimal getPercentageLevel1() {
		return _percentageLevel1;
	}

	@Nullable
	public BigDecimal getPercentageLevel2() {
		return _percentageLevel2;
	}

	@Nullable
	public BigDecimal getPercentageLevel3() {
		return _percentageLevel3;
	}

	@Nullable
	public BigDecimal getPercentageLevel4() {
		return _percentageLevel4;
	}

	public String getTarget() {
		return _target;
	}

	public String getTitle() {
		return _title;
	}

	@Nullable
	public UserSegmentDTO[] getUserSegments() {
		return _userSegments;
	}

	@Nullable
	public Boolean isActive() {
		return _active;
	}

	@Nullable
	public Boolean isNeverExpire() {
		return _neverExpire;
	}

	@Nullable
	public Boolean isUseCouponCode() {
		return _useCouponCode;
	}

	@Nullable
	public Boolean isUsePercentage() {
		return _usePercentage;
	}

	public void setActive(Boolean active) {
		_active = active;
	}

	public void setCouponCode(String couponCode) {
		_couponCode = couponCode;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
	public void setDisplayDate(Date displayDate) {
		_displayDate = displayDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
	public void setExpirationDate(Date expirationDate) {
		_expirationDate = expirationDate;
	}

	public void setGroupId(Long groupId) {
		_groupId = groupId;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setLimitationTimes(Integer limitationTimes) {
		_limitationTimes = limitationTimes;
	}

	public void setLimitationType(String limitationType) {
		_limitationType = limitationType;
	}

	public void setMaximumDiscountAmount(BigDecimal maximumDiscountAmount) {
		_maximumDiscountAmount = maximumDiscountAmount;
	}

	public void setNeverExpire(Boolean neverExpire) {
		_neverExpire = neverExpire;
	}

	public void setNumberOfUse(Integer numberOfUse) {
		_numberOfUse = numberOfUse;
	}

	public void setPercentageLevel1(BigDecimal percentageLevel1) {
		_percentageLevel1 = percentageLevel1;
	}

	public void setPercentageLevel2(BigDecimal percentageLevel2) {
		_percentageLevel2 = percentageLevel2;
	}

	public void setPercentageLevel3(BigDecimal percentageLevel3) {
		_percentageLevel3 = percentageLevel3;
	}

	public void setPercentageLevel4(BigDecimal percentageLevel4) {
		_percentageLevel4 = percentageLevel4;
	}

	public void setTarget(String target) {
		_target = target;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public void setUseCouponCode(Boolean useCouponCode) {
		_useCouponCode = useCouponCode;
	}

	public void setUsePercentage(Boolean usePercentage) {
		_usePercentage = usePercentage;
	}

	public void setUserSegments(UserSegmentDTO[] userSegments) {
		_userSegments = userSegments;
	}

	@Nullable
	private Boolean _active;

	@Nullable
	private String _couponCode;

	@Nullable
	private Date _displayDate;

	@Nullable
	private Date _expirationDate;

	@Nullable
	private Long _groupId;

	@Nullable
	private Long _id;

	@Nullable
	private Integer _limitationTimes;

	private String _limitationType;

	@Nullable
	private BigDecimal _maximumDiscountAmount;

	@Nullable
	private Boolean _neverExpire;

	@Nullable
	private Integer _numberOfUse;

	@Nullable
	private BigDecimal _percentageLevel1;

	@Nullable
	private BigDecimal _percentageLevel2;

	@Nullable
	private BigDecimal _percentageLevel3;

	@Nullable
	private BigDecimal _percentageLevel4;

	private String _target;
	private String _title;

	@Nullable
	private Boolean _useCouponCode;

	@Nullable
	private Boolean _usePercentage;

	@Nullable
	private UserSegmentDTO[] _userSegments;

}