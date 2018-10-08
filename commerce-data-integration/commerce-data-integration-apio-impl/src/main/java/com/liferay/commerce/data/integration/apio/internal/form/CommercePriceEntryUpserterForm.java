/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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
	 * Builds a {@code Form} that generates {@code CommercePriceEntryUpserterForm}
	 * depending on the HTTP body.
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
			__ -> "This form can be used to upsert a price entry"
		).constructor(
			CommercePriceEntryUpserterForm::new
		).addOptionalBoolean(
			"standardPrice", CommercePriceEntryUpserterForm::_setStandardPrice
		).addOptionalLong(
			"commercePriceEntryId",
			CommercePriceEntryUpserterForm::_setCommercePriceEntryId
		).addOptionalLong(
			"commerceProductInstanceId",
			CommercePriceEntryUpserterForm::_setCommerceProductInstanceId
		).addOptionalString(
			"externalReferenceCode",
			CommercePriceEntryUpserterForm::_setExternalReferenceCode
		).addOptionalString(
			"skuExternalReferenceCode",
			CommercePriceEntryUpserterForm::_setSkuExternalReferenceCode
		).addRequiredDouble(
			"price", CommercePriceEntryUpserterForm::_setPrice
		).addRequiredDouble(
			"promoPrice", CommercePriceEntryUpserterForm::_setPromoPrice
		).addRequiredBoolean(
			"standardPrice", CommercePriceEntryUpserterForm::_setStandardPrice
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

	private void _setCommercePriceEntryId(Long commercePriceEntryId) {
		_commercePriceEntryId = commercePriceEntryId;
	}

	private void _setCommerceProductInstanceId(Long commerceProductInstanceId) {
		_commerceProductInstanceId = commerceProductInstanceId;
	}

	private void _setExternalReferenceCode(String externalReferenceCode) {
		_externalReferenceCode = externalReferenceCode;
	}

	private void _setPrice(Double price) {
		_price = price;
	}

	private void _setPromoPrice(Double promoPrice) {
		_promoPrice = promoPrice;
	}

	private void _setSkuExternalReferenceCode(String skuExternalReferenceCode) {
		_skuExternalReferenceCode = skuExternalReferenceCode;
	}

	private void _setStandardPrice(Boolean standardPrice) {
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