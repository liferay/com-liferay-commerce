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
 * @author Alessio Antonio Rendina
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
		).addOptionalLongList(
			"commerceUserIds", CommerceAccountUpserterForm::setCommerceUserIds
		).addOptionalString(
			"taxId", CommerceAccountUpserterForm::setTaxId
		).addRequiredBoolean(
			"active", CommerceAccountUpserterForm::setActive
		).addRequiredLong(
			"type", CommerceAccountUpserterForm::setType
		).addRequiredString(
			"externalReferenceCode",
			CommerceAccountUpserterForm::setExternalReferenceCode
		).addRequiredString(
			"email", CommerceAccountUpserterForm::setEmail
		).addRequiredString(
			"name", CommerceAccountUpserterForm::setName
		).build();
	}

	public boolean getActive() {
		return _active;
	}

	public List<Long> getCommerceUserIds() {
		return _commerceUserIds;
	}

	public String getEmail() {
		return _email;
	}

	public String getExternalReferenceCode() {
		return _externalReferenceCode;
	}

	public String getName() {
		return _name;
	}

	public String getTaxId() {
		return _taxId;
	}

	public int getType() {
		return _type;
	}

	public void setActive(boolean active) {
		_active = active;
	}

	public void setCommerceUserIds(List<Long> commerceUserIds) {
		_commerceUserIds = commerceUserIds;
	}

	public void setEmail(String email) {
		_email = email;
	}

	public void setExternalReferenceCode(String externalReferenceCode) {
		_externalReferenceCode = externalReferenceCode;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setTaxId(String taxId) {
		_taxId = taxId;
	}

	public void setType(long type) {
		_type = Math.toIntExact(type);
	}

	private boolean _active;
	private List<Long> _commerceUserIds;
	private String _email;
	private String _externalReferenceCode;
	private String _name;
	private String _taxId;
	private int _type;

}