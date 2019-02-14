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

package com.liferay.commerce.openapi.admin.model.v2_0;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import com.liferay.commerce.openapi.core.annotation.Nullable;

import java.math.BigDecimal;

import java.util.Date;

import javax.annotation.Generated;

/**
 * @author Igor Beslic
 */
@Generated(value = "OSGiRESTModuleGenerator")
@JacksonXmlRootElement(localName = "Sku")
public class SkuDTO {

	@Nullable
	public BigDecimal getCost() {
		return _cost;
	}

	@Nullable
	public Double getDepth() {
		return _depth;
	}

	@Nullable
	public Date getDisplayDate() {
		return _displayDate;
	}

	@Nullable
	public Date getExpirationDate() {
		return _expirationDate;
	}

	public String getExternalReferenceCode() {
		return _externalReferenceCode;
	}

	@Nullable
	public String getGtin() {
		return _gtin;
	}

	@Nullable
	public Double getHeight() {
		return _height;
	}

	@Nullable
	public Long getId() {
		return _id;
	}

	@Nullable
	public String getManufacturerPartNumber() {
		return _manufacturerPartNumber;
	}

	@Nullable
	public BigDecimal getPrice() {
		return _price;
	}

	@Nullable
	public BigDecimal getPromoPrice() {
		return _promoPrice;
	}

	public String getSku() {
		return _sku;
	}

	@Nullable
	public Double getWeight() {
		return _weight;
	}

	@Nullable
	public Double getWidth() {
		return _width;
	}

	@Nullable
	public Boolean isNeverExpire() {
		return _neverExpire;
	}

	@Nullable
	public Boolean isPublished() {
		return _published;
	}

	@Nullable
	public Boolean isPurchasable() {
		return _purchasable;
	}

	public void setCost(BigDecimal cost) {
		_cost = cost;
	}

	public void setDepth(Double depth) {
		_depth = depth;
	}

	public void setDisplayDate(Date displayDate) {
		_displayDate = displayDate;
	}

	public void setExpirationDate(Date expirationDate) {
		_expirationDate = expirationDate;
	}

	public void setExternalReferenceCode(String externalReferenceCode) {
		_externalReferenceCode = externalReferenceCode;
	}

	public void setGtin(String gtin) {
		_gtin = gtin;
	}

	public void setHeight(Double height) {
		_height = height;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setManufacturerPartNumber(String manufacturerPartNumber) {
		_manufacturerPartNumber = manufacturerPartNumber;
	}

	public void setNeverExpire(Boolean neverExpire) {
		_neverExpire = neverExpire;
	}

	public void setPrice(BigDecimal price) {
		_price = price;
	}

	public void setPromoPrice(BigDecimal promoPrice) {
		_promoPrice = promoPrice;
	}

	public void setPublished(Boolean published) {
		_published = published;
	}

	public void setPurchasable(Boolean purchasable) {
		_purchasable = purchasable;
	}

	public void setSku(String sku) {
		_sku = sku;
	}

	public void setWeight(Double weight) {
		_weight = weight;
	}

	public void setWidth(Double width) {
		_width = width;
	}

	@Nullable
	private BigDecimal _cost;

	@Nullable
	private Double _depth;

	@Nullable
	private Date _displayDate;

	@Nullable
	private Date _expirationDate;

	private String _externalReferenceCode;

	@Nullable
	private String _gtin;

	@Nullable
	private Double _height;

	@Nullable
	private Long _id;

	@Nullable
	private String _manufacturerPartNumber;

	@Nullable
	private Boolean _neverExpire;

	@Nullable
	private BigDecimal _price;

	@Nullable
	private BigDecimal _promoPrice;

	@Nullable
	private Boolean _published;

	@Nullable
	private Boolean _purchasable;

	private String _sku;

	@Nullable
	private Double _weight;

	@Nullable
	private Double _width;

}