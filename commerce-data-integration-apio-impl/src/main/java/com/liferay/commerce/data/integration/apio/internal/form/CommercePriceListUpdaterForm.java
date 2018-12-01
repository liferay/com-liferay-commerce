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
			"displayDate", CommercePriceListUpdaterForm::setDisplayDate
		).addOptionalDate(
			"expirationDate", CommercePriceListUpdaterForm::setExpirationDate
		).addRequiredBoolean(
			"neverExpire", CommercePriceListUpdaterForm::setNeverExpire
		).addRequiredDouble(
			"priority", CommercePriceListUpdaterForm::setPriority
		).addRequiredString(
			"currency", CommercePriceListUpdaterForm::setCurrency
		).addRequiredString(
			"name", CommercePriceListUpdaterForm::setName
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

	public void setCurrency(String currency) {
		_currency = currency;
	}

	public void setDisplayDate(Date display) {
		_displayDate = display;
	}

	public void setExpirationDate(Date expirationDate) {
		_expirationDate = expirationDate;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setNeverExpire(Boolean neverExpire) {
		_neverExpire = neverExpire;
	}

	public void setPriority(Double priority) {
		_priority = priority;
	}

	private String _currency;
	private Date _displayDate;
	private Date _expirationDate;
	private String _name;
	private Boolean _neverExpire;
	private Double _priority;

}