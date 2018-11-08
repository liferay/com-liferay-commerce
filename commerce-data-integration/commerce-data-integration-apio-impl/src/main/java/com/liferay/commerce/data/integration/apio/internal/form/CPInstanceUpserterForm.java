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

package com.liferay.commerce.data.integration.apio.internal.form;

import com.liferay.apio.architect.form.Form;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * @author Rodrigo Guedes de Souza
 * @author Zoltán Takács
 */
public class CPInstanceUpserterForm {

	public static Form<CPInstanceUpserterForm> buildForm(
		Form.Builder<CPInstanceUpserterForm> formBuilder) {

		return formBuilder.title(
			__ -> "The product instance upserter form"
		).description(
			__ -> "This form can be used to create or update a product instance"
		).constructor(
			CPInstanceUpserterForm::new
		).addOptionalBoolean(
			"neverExpire", CPInstanceUpserterForm::setNeverExpire
		).addOptionalBoolean(
			"published", CPInstanceUpserterForm::setPublished
		).addOptionalBoolean(
			"purchasable", CPInstanceUpserterForm::setPurchasable
		).addOptionalDouble(
			"cost", CPInstanceUpserterForm::setCost
		).addOptionalDouble(
			"depth", CPInstanceUpserterForm::setDepth
		).addOptionalDouble(
			"height", CPInstanceUpserterForm::setHeight
		).addOptionalDouble(
			"price", CPInstanceUpserterForm::setPrice
		).addOptionalDouble(
			"promoPrice", CPInstanceUpserterForm::setPromoPrice
		).addOptionalDouble(
			"weight", CPInstanceUpserterForm::setWeight
		).addOptionalDouble(
			"width", CPInstanceUpserterForm::setWidth
		).addOptionalDate(
			"displayDate", CPInstanceUpserterForm::setDisplayDate
		).addOptionalDate(
			"expirationDate", CPInstanceUpserterForm::setExpirationDate
		).addOptionalString(
			"description", CPInstanceUpserterForm::setDescription
		).addOptionalString(
			"gtin", CPInstanceUpserterForm::setGtin
		).addOptionalString(
			"manufacturerPartNumber",
			CPInstanceUpserterForm::setManufacturerPartNumber
		).addOptionalString(
			"productTypeName", CPInstanceUpserterForm::setProductTypeName
		).addOptionalString(
			"shortDescription", CPInstanceUpserterForm::setShortDescription
		).addRequiredBoolean(
			"active", CPInstanceUpserterForm::setActive
		).addRequiredString(
			"externalReferenceCode",
			CPInstanceUpserterForm::setExternalReferenceCode
		).addRequiredString(
			"sku", CPInstanceUpserterForm::setSku
		).addRequiredString(
			"title", CPInstanceUpserterForm::setTitle
		).build();
	}

	public Boolean getActive() {
		return _active;
	}

	public double getCost() {
		return _cost;
	}

	public double getDepth() {
		return _depth;
	}

	public Map<Locale, String> getDescriptionMap() {
		return Collections.singletonMap(LocaleUtil.getDefault(), _description);
	}

	public Date getDisplayDate() {
		if (_displayDate != null) {
			return new Date(_displayDate.getTime());
		}

		return null;
	}

	public Date getExpirationDate() {
		if (_expirationDate != null) {
			return new Date(_expirationDate.getTime());
		}

		return null;
	}

	public String getExternalReferenceCode() {
		return _externalReferenceCode;
	}

	public String getGtin() {
		return _gtin;
	}

	public double getHeight() {
		return _height;
	}

	public String getManufacturerPartNumber() {
		return _manufacturerPartNumber;
	}

	public boolean getNeverExpire() {
		return _neverExpire;
	}

	public double getPrice() {
		return _price;
	}

	public String getProductTypeName() {
		return _productTypeName;
	}

	public double getPromoPrice() {
		return _promoPrice;
	}

	public boolean getPublished() {
		return _published;
	}

	public boolean getPurchasable() {
		return _purchasable;
	}

	public Map<Locale, String> getShortDescriptionMap() {
		return Collections.singletonMap(
			LocaleUtil.getDefault(), _shortDescription);
	}

	public String getSku() {
		return _sku;
	}

	public Map<Locale, String> getTitleMap() {
		return Collections.singletonMap(LocaleUtil.getDefault(), _title);
	}

	public double getWeight() {
		return _weight;
	}

	public double getWidth() {
		return _width;
	}

	public void setActive(Boolean active) {
		_active = active;
	}

	public void setCost(double cost) {
		_cost = cost;
	}

	public void setDepth(double depth) {
		_depth = depth;
	}

	public void setDescription(String description) {
		_description = description;
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

	public void setHeight(double height) {
		_height = height;
	}

	public void setManufacturerPartNumber(String manufacturerPartNumber) {
		_manufacturerPartNumber = manufacturerPartNumber;
	}

	public void setNeverExpire(boolean neverExpire) {
		_neverExpire = neverExpire;
	}

	public void setPrice(double price) {
		_price = price;
	}

	public void setProductTypeName(String productTypeName) {
		_productTypeName = productTypeName;
	}

	public void setPromoPrice(double promoPrice) {
		_promoPrice = promoPrice;
	}

	public void setPublished(boolean published) {
		_published = published;
	}

	public void setPurchasable(boolean purchasable) {
		_purchasable = purchasable;
	}

	public void setShortDescription(String shortDescription) {
		_shortDescription = shortDescription;
	}

	public void setSku(String sku) {
		_sku = sku;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public void setWeight(double weight) {
		_weight = weight;
	}

	public void setWidth(double width) {
		_width = width;
	}

	private Boolean _active;
	private double _cost;
	private double _depth;
	private String _description;
	private Date _displayDate;
	private Date _expirationDate;
	private String _externalReferenceCode;
	private String _gtin;
	private double _height;
	private String _manufacturerPartNumber;
	private boolean _neverExpire;
	private double _price;
	private String _productTypeName;
	private double _promoPrice;
	private boolean _published;
	private boolean _purchasable;
	private String _shortDescription;
	private String _sku;
	private String _title;
	private double _weight;
	private double _width;

}