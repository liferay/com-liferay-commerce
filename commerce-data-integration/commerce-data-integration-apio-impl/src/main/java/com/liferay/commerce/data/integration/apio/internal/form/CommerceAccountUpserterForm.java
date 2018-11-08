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

import java.util.List;

/**
 * @author Rodrigo Guedes de Souza
 * @author Zoltán Takács
 */
public class CommerceAccountUpserterForm {

	public static Form<CommerceAccountUpserterForm> buildForm(
		Form.Builder<CommerceAccountUpserterForm> formBuilder) {

		return formBuilder.title(
			__ -> "The account upserter form"
		).description(
			__ -> "This form can be used to create or update an account"
		).constructor(
			CommerceAccountUpserterForm::new
		).addOptionalLong(
			"countryId", CommerceAccountUpserterForm::setCountryId
		).addOptionalLong(
			"regionId", CommerceAccountUpserterForm::setRegionId
		).addOptionalLongList(
			"commerceUserIds", CommerceAccountUpserterForm::setCommerceUserIds
		).addRequiredString(
			"externalReferenceCode",
			CommerceAccountUpserterForm::setExternalReferenceCode
		).addRequiredString(
			"name", CommerceAccountUpserterForm::setName
		).build();
	}

	public List<Long> getCommerceUserIds() {
		return _commerceUserIds;
	}

	public long getCountryId() {
		return _countryId;
	}

	public String getExternalReferenceCode() {
		return _externalReferenceCode;
	}

	public String getName() {
		return _name;
	}

	public long getRegionId() {
		return _regionId;
	}

	public void setCommerceUserIds(List<Long> commerceUserIds) {
		_commerceUserIds = commerceUserIds;
	}

	public void setCountryId(long countryId) {
		_countryId = countryId;
	}

	public void setExternalReferenceCode(String externalReferenceCode) {
		_externalReferenceCode = externalReferenceCode;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setRegionId(long regionId) {
		_regionId = regionId;
	}

	private List<Long> _commerceUserIds;
	private long _countryId;
	private String _externalReferenceCode;
	private String _name;
	private long _regionId;

}