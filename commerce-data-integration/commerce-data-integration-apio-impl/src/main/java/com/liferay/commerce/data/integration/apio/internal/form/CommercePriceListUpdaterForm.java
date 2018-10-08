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

import java.util.Date;

/**
 * Instances of this class represent the values extracted from a price list
 * form.
 *
 * @author Zoltán Takács
 * @review
 */
public class CommercePriceListUpdaterForm {

	/**
	 * Builds a {@code Form} that generates {@code CommercePriceListUpdaterForm}
	 * depending on the HTTP body.
	 *
	 * @param  formBuilder the {@code Form} builder
	 * @return a product updater form
	 * @review
	 */
	public static Form<CommercePriceListUpdaterForm> buildForm(
		Form.Builder<CommercePriceListUpdaterForm> formBuilder) {

		return formBuilder.title(
			__ -> "The price list updater form"
		).description(
			__ -> "This form can be used to update a price list"
		).constructor(
			CommercePriceListUpdaterForm::new
		).addOptionalDate(
			"displayDate", CommercePriceListUpdaterForm::_setDisplayDate
		).addOptionalDate(
			"expirationDate", CommercePriceListUpdaterForm::_setExpirationDate
		).addRequiredBoolean(
			"neverExpire", CommercePriceListUpdaterForm::_setNeverExpire
		).addRequiredDouble(
			"priority", CommercePriceListUpdaterForm::_setPriority
		).addRequiredString(
			"currency", CommercePriceListUpdaterForm::_setCurrency
		).addRequiredString(
			"name", CommercePriceListUpdaterForm::_setName
		).build();
	}

	public String getCurrency() {
		return _currency;
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

	public String getName() {
		return _name;
	}

	public Double getPriority() {
		return _priority;
	}

	public Boolean isNeverExpire() {
		return _neverExpire;
	}

	private void _setCurrency(String currency) {
		_currency = currency;
	}

	private void _setDisplayDate(Date display) {
		_displayDate = display;
	}

	private void _setExpirationDate(Date expirationDate) {
		_expirationDate = expirationDate;
	}

	private void _setName(String name) {
		_name = name;
	}

	private void _setNeverExpire(Boolean neverExpire) {
		_neverExpire = neverExpire;
	}

	private void _setPriority(Double priority) {
		_priority = priority;
	}

	private String _currency;
	private Date _displayDate;
	private Date _expirationDate;
	private String _name;
	private Boolean _neverExpire;
	private Double _priority;

}