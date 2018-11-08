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
public class CommercePriceListUpserterForm {

	/**
	 * Builds a {@code Form} that generates {@code
	 * CommercePriceListUpserterForm} depending on the HTTP body.
	 *
	 * @param  formBuilder the {@code Form} builder
	 * @return a product upserter form
	 * @review
	 */
	public static Form<CommercePriceListUpserterForm> buildForm(
		Form.Builder<CommercePriceListUpserterForm> formBuilder) {

		return formBuilder.title(
			__ -> "The price list upserter form"
		).description(
			__ -> "This form can be used to create or update a price list"
		).constructor(
			CommercePriceListUpserterForm::new
		).addOptionalDate(
			"displayDate", CommercePriceListUpserterForm::setDisplayDate
		).addOptionalDate(
			"expirationDate", CommercePriceListUpserterForm::setExpirationDate
		).addOptionalLong(
			"commercePriceListId",
			CommercePriceListUpserterForm::setCommercePriceListId
		).addRequiredBoolean(
			"active", CommercePriceListUpserterForm::setActive
		).addRequiredBoolean(
			"neverExpire", CommercePriceListUpserterForm::setNeverExpire
		).addRequiredDouble(
			"priority", CommercePriceListUpserterForm::setPriority
		).addRequiredString(
			"currency", CommercePriceListUpserterForm::setCurrency
		).addRequiredString(
			"externalReferenceCode",
			CommercePriceListUpserterForm::setExternalReferenceCode
		).addRequiredString(
			"name", CommercePriceListUpserterForm::setName
		).build();
	}

	public Boolean getActive() {
		return _active;
	}

	public Long getCommercePriceListId() {
		return _commercePriceListId;
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

	public String getExternalReferenceCode() {
		return _externalReferenceCode;
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

	public void setActive(Boolean active) {
		_active = active;
	}

	public void setCommercePriceListId(Long commercePriceListId) {
		_commercePriceListId = commercePriceListId;
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

	public void setExternalReferenceCode(String externalReferenceCode) {
		_externalReferenceCode = externalReferenceCode;
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

	private Boolean _active;
	private Long _commercePriceListId = 0L;
	private String _currency;
	private Date _displayDate;
	private Date _expirationDate;
	private String _externalReferenceCode;
	private String _name;
	private Boolean _neverExpire;
	private Double _priority;

}