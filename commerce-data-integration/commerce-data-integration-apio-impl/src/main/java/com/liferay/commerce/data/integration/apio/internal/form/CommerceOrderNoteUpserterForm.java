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
 * @author Rodrigo Guedes de Souza
 */
public class CommerceOrderNoteUpserterForm {

	public static Form<CommerceOrderNoteUpserterForm> buildForm(
		Form.Builder<CommerceOrderNoteUpserterForm> formBuilder) {

		return formBuilder.title(
			__ -> "The order note upserter form"
		).description(
			__ -> "This form can be used to update an order"
		).constructor(
			CommerceOrderNoteUpserterForm::new
		).addOptionalLong(
			"commerceOrderNoteId",
			CommerceOrderNoteUpserterForm::_setCommerceOrderNoteId
		).addRequiredString(
			"externalReferenceCode",
			CommerceOrderNoteUpserterForm::_setExternalReferenceCode
		).addRequiredString(
			"content", CommerceOrderNoteUpserterForm::_setContent
		).addRequiredBoolean(
			"restricted", CommerceOrderNoteUpserterForm::_setRestricted
		).build();
	}

	public Long getCommerceOrderNoteId() {
		return _commerceOrderNoteId;
	}

	public String getContent() {
		return _content;
	}

	public String getExternalReferenceCode() {
		return _externalReferenceCode;
	}

	public Boolean getRestricted() {
		return _restricted;
	}

	private void _setCommerceOrderNoteId(Long commerceOrderNoteId) {
		_commerceOrderNoteId = commerceOrderNoteId;
	}

	private void _setContent(String content) {
		_content = content;
	}

	private void _setExternalReferenceCode(String externalReferenceCode) {
		_externalReferenceCode = externalReferenceCode;
	}

	private void _setRestricted(Boolean restricted) {
		_restricted = restricted;
	}

	private Long _commerceOrderNoteId = 0L;
	private String _content;
	private String _externalReferenceCode;
	private Boolean _restricted;

}