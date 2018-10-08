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
 */
public class CPInstanceUpserterForm {

	public static Form<CPInstanceUpserterForm> buildForm(
		Form.Builder<CPInstanceUpserterForm> formBuilder) {

		return formBuilder.title(
			__ -> "The product instance upserter form"
		).description(
			__ -> "This form can be used to upsert a product instance"
		).constructor(
			CPInstanceUpserterForm::new
		).addRequiredString(
			"sku", CPInstanceUpserterForm::_setSku
		).addOptionalString(
			"gtin", CPInstanceUpserterForm::_setGtin
		).addOptionalString(
			"manufacturerPartNumber",
			CPInstanceUpserterForm::_setManufacturerPartNumber
		).addOptionalBoolean(
			"purchasable", CPInstanceUpserterForm::_setPurchasable
		).addOptionalDouble(
			"width", CPInstanceUpserterForm::_setWidth
		).addOptionalDouble(
			"height", CPInstanceUpserterForm::_setHeight
		).addOptionalDouble(
			"depth", CPInstanceUpserterForm::_setDepth
		).addOptionalDouble(
			"weight", CPInstanceUpserterForm::_setWeight
		).addOptionalDouble(
			"cost", CPInstanceUpserterForm::_setCost
		).addOptionalDouble(
			"price", CPInstanceUpserterForm::_setPrice
		).addOptionalDouble(
			"promoPrice", CPInstanceUpserterForm::_setPromoPrice
		).addOptionalBoolean(
			"published", CPInstanceUpserterForm::_setPublished
		).addOptionalDate(
			"displayDate", CPInstanceUpserterForm::_setDisplayDate
		).addOptionalDate(
			"expirationDate", CPInstanceUpserterForm::_setExpirationDate
		).addOptionalBoolean(
			"neverExpire", CPInstanceUpserterForm::_setNeverExpire
		).addRequiredString(
			"externalReferenceCode",
			CPInstanceUpserterForm::_setExternalReferenceCode
		).addOptionalString(
			"description", CPInstanceUpserterForm::_setDescription
		).addOptionalString(
			"shortDescription", CPInstanceUpserterForm::_setShortDescription
		).addOptionalString(
			"productTypeName", CPInstanceUpserterForm::_setProductTypeName
		).addRequiredString(
			"title", CPInstanceUpserterForm::_setTitle
		).addRequiredBoolean(
			"active", CPInstanceUpserterForm::_setActive
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

	private void _setActive(Boolean active) {
		_active = active;
	}

	private void _setCost(double cost) {
		_cost = cost;
	}

	private void _setDepth(double depth) {
		_depth = depth;
	}

	private void _setDescription(String description) {
		_description = description;
	}

	private void _setDisplayDate(Date displayDate) {
		_displayDate = displayDate;
	}

	private void _setExpirationDate(Date expirationDate) {
		_expirationDate = expirationDate;
	}

	private void _setExternalReferenceCode(String externalReferenceCode) {
		_externalReferenceCode = externalReferenceCode;
	}

	private void _setGtin(String gtin) {
		_gtin = gtin;
	}

	private void _setHeight(double height) {
		_height = height;
	}

	private void _setManufacturerPartNumber(String manufacturerPartNumber) {
		_manufacturerPartNumber = manufacturerPartNumber;
	}

	private void _setNeverExpire(boolean neverExpire) {
		_neverExpire = neverExpire;
	}

	private void _setPrice(double price) {
		_price = price;
	}

	private void _setProductTypeName(String productTypeName) {
		_productTypeName = productTypeName;
	}

	private void _setPromoPrice(double promoPrice) {
		_promoPrice = promoPrice;
	}

	private void _setPublished(boolean published) {
		_published = published;
	}

	private void _setPurchasable(boolean purchasable) {
		_purchasable = purchasable;
	}

	private void _setShortDescription(String shortDescription) {
		_shortDescription = shortDescription;
	}

	private void _setSku(String sku) {
		_sku = sku;
	}

	private void _setTitle(String title) {
		_title = title;
	}

	private void _setWeight(double weight) {
		_weight = weight;
	}

	private void _setWidth(double width) {
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