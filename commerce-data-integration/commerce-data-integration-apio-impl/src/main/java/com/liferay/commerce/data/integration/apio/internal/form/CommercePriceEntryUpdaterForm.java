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
 * updater form.
 *
 * @author Zoltán Takács
 * @review
 */
public class CommercePriceEntryUpdaterForm {

	/**
	 * Builds a {@code Form} that generates {@code CommercePriceEntryUpdaterForm}
	 * depending on the HTTP body.
	 *
	 * @param  formBuilder the {@code Form} builder
	 * @return a product updater form
	 * @review
	 */
	public static Form<CommercePriceEntryUpdaterForm> buildForm(
		Form.Builder<CommercePriceEntryUpdaterForm> formBuilder) {

		return formBuilder.title(
			__ -> "The price entry updater form"
		).description(
			__ -> "This form can be used to update a price entry"
		).constructor(
			CommercePriceEntryUpdaterForm::new
		).addRequiredDouble(
			"price", CommercePriceEntryUpdaterForm::_setPrice
		).addRequiredDouble(
			"promoPrice", CommercePriceEntryUpdaterForm::_setPromoPrice
		).build();
	}

	public Double getPrice() {
		return _price;
	}

	public Double getPromoPrice() {
		return _promoPrice;
	}

	private void _setPrice(Double price) {
		_price = price;
	}

	private void _setPromoPrice(Double promoPrice) {
		_promoPrice = promoPrice;
	}

	private Double _price;
	private Double _promoPrice;

}