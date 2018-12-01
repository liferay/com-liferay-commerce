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
 * @author Zoltán Takács
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
			CommerceOrderUpdaterForm::setExternalReferenceCode
		).addOptionalLong(
			"orderStatus", CommerceOrderUpdaterForm::setOrderStatus
		).addOptionalLong(
			"paymentStatus", CommerceOrderUpdaterForm::setPaymentStatus
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

	public void setExternalReferenceCode(String externalReferenceCode) {
		_externalReferenceCode = externalReferenceCode;
	}

	public void setOrderStatus(Long orderStatus) {
		_orderStatus = orderStatus;
	}

	public void setPaymentStatus(Long paymentStatus) {
		_paymentStatus = paymentStatus;
	}

	private String _externalReferenceCode;
	private Long _orderStatus;
	private Long _paymentStatus;

}