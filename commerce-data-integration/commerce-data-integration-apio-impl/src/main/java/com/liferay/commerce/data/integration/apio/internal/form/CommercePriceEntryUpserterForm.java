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

/**
 * Instances of this class represent the values extracted from a price entry
 * upserter form.
 *
 * @author Zoltán Takács
 * @review
 */
public class CommercePriceEntryUpserterForm {

	/**
	 * Builds a {@code Form} that generates {@code
	 * CommercePriceEntryUpserterForm} depending on the HTTP body.
	 *
	 * @param  formBuilder the {@code Form} builder
	 * @return a product upserter form
	 * @review
	 */
	public static Form<CommercePriceEntryUpserterForm> buildForm(
		Form.Builder<CommercePriceEntryUpserterForm> formBuilder) {

		return formBuilder.title(
			__ -> "The price entry upserter form"
		).description(
			__ -> "This form can be used to create or update a price entry"
		).constructor(
			CommercePriceEntryUpserterForm::new
		).addOptionalBoolean(
			"standardPrice", CommercePriceEntryUpserterForm::setStandardPrice
		).addOptionalLong(
			"commercePriceEntryId",
			CommercePriceEntryUpserterForm::setCommercePriceEntryId
		).addOptionalLong(
			"commerceProductInstanceId",
			CommercePriceEntryUpserterForm::setCommerceProductInstanceId
		).addOptionalString(
			"skuExternalReferenceCode",
			CommercePriceEntryUpserterForm::setSkuExternalReferenceCode
		).addRequiredBoolean(
			"standardPrice", CommercePriceEntryUpserterForm::setStandardPrice
		).addRequiredDouble(
			"price", CommercePriceEntryUpserterForm::setPrice
		).addRequiredDouble(
			"promoPrice", CommercePriceEntryUpserterForm::setPromoPrice
		).addRequiredString(
			"externalReferenceCode",
			CommercePriceEntryUpserterForm::setExternalReferenceCode
		).build();
	}

	public Long getCommercePriceEntryId() {
		return _commercePriceEntryId;
	}

	public Long getCommerceProductInstanceId() {
		return _commerceProductInstanceId;
	}

	public String getExternalReferenceCode() {
		return _externalReferenceCode;
	}

	public Double getPrice() {
		return _price;
	}

	public Double getPromoPrice() {
		return _promoPrice;
	}

	public String getSkuExternalReferenceCode() {
		return _skuExternalReferenceCode;
	}

	public Boolean getStandardPrice() {
		return _standardPrice;
	}

	public void setCommercePriceEntryId(Long commercePriceEntryId) {
		_commercePriceEntryId = commercePriceEntryId;
	}

	public void setCommerceProductInstanceId(Long commerceProductInstanceId) {
		_commerceProductInstanceId = commerceProductInstanceId;
	}

	public void setExternalReferenceCode(String externalReferenceCode) {
		_externalReferenceCode = externalReferenceCode;
	}

	public void setPrice(Double price) {
		_price = price;
	}

	public void setPromoPrice(Double promoPrice) {
		_promoPrice = promoPrice;
	}

	public void setSkuExternalReferenceCode(String skuExternalReferenceCode) {
		_skuExternalReferenceCode = skuExternalReferenceCode;
	}

	public void setStandardPrice(Boolean standardPrice) {
		_standardPrice = standardPrice;
	}

	private Long _commercePriceEntryId = 0L;
	private Long _commerceProductInstanceId = 0L;
	private String _externalReferenceCode;
	private Double _price;
	private Double _promoPrice;
	private String _skuExternalReferenceCode;
	private Boolean _standardPrice;

}