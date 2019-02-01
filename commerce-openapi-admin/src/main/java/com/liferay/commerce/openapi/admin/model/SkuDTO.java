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

package com.liferay.commerce.openapi.admin.model;

import java.math.BigDecimal;

import java.util.Date;

import javax.annotation.Generated;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Igor Beslic
 */
@Generated(value = "OSGiRESTModuleGenerator")
@XmlRootElement(name = "Sku")
public class SkuDTO {

	public BigDecimal getCost() {
		return _cost;
	}

	public Double getDepth() {
		return _depth;
	}

	public Date getDisplayDate() {
		return _displayDate;
	}

	public Date getExpirationDate() {
		return _expirationDate;
	}

	public String getExternalReferenceCode() {
		return _externalReferenceCode;
	}

	public String getGtin() {
		return _gtin;
	}

	public Double getHeight() {
		return _height;
	}

	public Long getId() {
		return _id;
	}

	public String getManufacturerPartNumber() {
		return _manufacturerPartNumber;
	}

	public BigDecimal getPrice() {
		return _price;
	}

	public BigDecimal getPromoPrice() {
		return _promoPrice;
	}

	public String getSku() {
		return _sku;
	}

	public Double getWeight() {
		return _weight;
	}

	public Double getWidth() {
		return _width;
	}

	public Boolean isNeverExpire() {
		return _neverExpire;
	}

	public Boolean isPublished() {
		return _published;
	}

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

	private BigDecimal _cost;
	private Double _depth;
	private Date _displayDate;
	private Date _expirationDate;
	private String _externalReferenceCode;
	private String _gtin;
	private Double _height;
	private Long _id;
	private String _manufacturerPartNumber;
	private Boolean _neverExpire;
	private BigDecimal _price;
	private BigDecimal _promoPrice;
	private Boolean _published;
	private Boolean _purchasable;
	private String _sku;
	private Double _weight;
	private Double _width;

}