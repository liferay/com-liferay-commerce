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
import com.liferay.apio.architect.form.Form.Builder;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceOrderUpserterForm {

	public static Form<CommerceOrderUpserterForm> buildForm(
		Builder<CommerceOrderUpserterForm> formBuilder) {

		return formBuilder.title(
			__ -> "The order creator form"
		).description(
			__ -> "This form can be used to create an order"
		).constructor(
			CommerceOrderUpserterForm::new
		).addRequiredLong(
			"orderOrganizationId",
			CommerceOrderUpserterForm::_setOrderOrganizationId
		).addRequiredLong(
			"orderUserId", CommerceOrderUpserterForm::_setOrderUserId
		).addRequiredString(
			"currency", CommerceOrderUpserterForm::_setCurrency
		).addOptionalLong(
			"shippingAddressId",
			CommerceOrderUpserterForm::_setShippingAddressId
		).addOptionalString(
			"purchaseOrderNumber",
			CommerceOrderUpserterForm::_setPurchaseOrderNumber
		).build();
	}

	public String getCurrency() {
		return _currency;
	}

	public long getOrderOrganizationId() {
		return _orderOrganizationId;
	}

	public long getOrderUserId() {
		return _orderUserId;
	}

	public String getPurchaseOrderNumber() {
		return _purchaseOrderNumber;
	}

	public long getShippingAddressId() {
		return _shippingAddressId;
	}

	private void _setCurrency(String currency) {
		_currency = currency;
	}

	private void _setOrderOrganizationId(long orderOrganizationId) {
		_orderOrganizationId = orderOrganizationId;
	}

	private void _setOrderUserId(long orderUserId) {
		_orderUserId = orderUserId;
	}

	private void _setPurchaseOrderNumber(String purchaseOrderNumber) {
		_purchaseOrderNumber = purchaseOrderNumber;
	}

	private void _setShippingAddressId(long shippingAddressId) {
		_shippingAddressId = shippingAddressId;
	}

	private String _currency;
	private long _orderOrganizationId;
	private long _orderUserId;
	private String _purchaseOrderNumber;
	private long _shippingAddressId;

}