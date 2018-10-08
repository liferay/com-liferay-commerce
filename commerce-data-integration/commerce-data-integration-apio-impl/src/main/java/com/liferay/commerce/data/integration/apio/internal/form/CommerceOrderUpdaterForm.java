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
public class CommerceOrderUpdaterForm {

	public static Form<CommerceOrderUpdaterForm> buildForm(
		Form.Builder<CommerceOrderUpdaterForm> formBuilder) {

		return formBuilder.title(
			__ -> "The order updater form"
		).description(
			__ -> "This form can be used to update an order"
		).constructor(
			CommerceOrderUpdaterForm::new
		).addOptionalString(
			"externalReferenceCode",
			CommerceOrderUpdaterForm::_setExternalReferenceCode
		).addOptionalLong(
			"orderStatus", CommerceOrderUpdaterForm::_setOrderStatus
		).addOptionalLong(
			"paymentStatus", CommerceOrderUpdaterForm::_setPaymentStatus
		).build();
	}

	public String getExternalReferenceCode() {
		return _externalReferenceCode;
	}

	public Long getOrderStatus() {
		return _orderStatus;
	}

	public Long getPaymentStatus() {
		return _paymentStatus;
	}

	private void _setExternalReferenceCode(String externalReferenceCode) {
		_externalReferenceCode = externalReferenceCode;
	}

	private void _setOrderStatus(Long orderStatus) {
		_orderStatus = orderStatus;
	}

	private void _setPaymentStatus(Long paymentStatus) {
		_paymentStatus = paymentStatus;
	}

	private String _externalReferenceCode;
	private Long _orderStatus;
	private Long _paymentStatus;

}