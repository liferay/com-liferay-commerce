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
import java.util.Locale;
import java.util.Map;

/**
 * @author Zoltán Takács
 */
public class CommerceCountryUpserterForm {

	public static Form<CommerceCountryUpserterForm> buildForm(
		Form.Builder<CommerceCountryUpserterForm> formBuilder) {

		return formBuilder.title(
			__ -> "The commerce country upserter form"
		).description(
			__ -> "This form can be used to create or update a commerce country"
		).constructor(
			CommerceCountryUpserterForm::new
		).addOptionalBoolean(
			"billingAllowed", CommerceCountryUpserterForm::setBillingAllowed
		).addOptionalBoolean(
			"shippingAllowed", CommerceCountryUpserterForm::setShippingAllowed
		).addOptionalBoolean(
			"subjectToVAT", CommerceCountryUpserterForm::setSubjectToVAT
		).addRequiredLong(
			"numericISOCode", CommerceCountryUpserterForm::setNumericISOCode
		).addRequiredString(
			"name", CommerceCountryUpserterForm::setName
		).addRequiredString(
			"threeLettersISOCode",
			CommerceCountryUpserterForm::setThreeLettersISOCode
		).addRequiredString(
			"twoLettersISOCode",
			CommerceCountryUpserterForm::setTwoLettersISOCode
		).build();
	}

	public Map<Locale, String> getNameMap() {
		return Collections.singletonMap(LocaleUtil.getDefault(), _name);
	}

	public int getNumericISOCode() {
		return Math.toIntExact(_numericISOCode);
	}

	public String getThreeLettersISOCode() {
		return _threeLettersISOCode;
	}

	public String getTwoLettersISOCode() {
		return _twoLettersISOCode;
	}

	public boolean isActive() {
		return _active;
	}

	public boolean isBillingAllowed() {
		return _billingAllowed;
	}

	public boolean isShippingAllowed() {
		return _shippingAllowed;
	}

	public boolean isSubjectToVAT() {
		return _subjectToVAT;
	}

	public void setActive(boolean active) {
		_active = active;
	}

	public void setBillingAllowed(boolean billingAllowed) {
		_billingAllowed = billingAllowed;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setNumericISOCode(long numericISOCode) {
		_numericISOCode = numericISOCode;
	}

	public void setShippingAllowed(boolean shippingAllowed) {
		_shippingAllowed = shippingAllowed;
	}

	public void setSubjectToVAT(boolean subjectToVAT) {
		_subjectToVAT = subjectToVAT;
	}

	public void setThreeLettersISOCode(String threeLettersISOCode) {
		_threeLettersISOCode = threeLettersISOCode;
	}

	public void setTwoLettersISOCode(String twoLettersISOCode) {
		_twoLettersISOCode = twoLettersISOCode;
	}

	private boolean _active;
	private boolean _billingAllowed;
	private String _name;
	private long _numericISOCode;
	private boolean _shippingAllowed;
	private boolean _subjectToVAT;
	private String _threeLettersISOCode;
	private String _twoLettersISOCode;

}