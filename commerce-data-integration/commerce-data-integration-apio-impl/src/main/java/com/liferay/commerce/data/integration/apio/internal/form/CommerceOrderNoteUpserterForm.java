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