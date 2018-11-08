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
 * @author Alessio Antonio Rendina
 * @author Zoltán Takács
 */
public class CommerceOrderUpserterForm {

	public static Form<CommerceOrderUpserterForm> buildForm(
		Form.Builder<CommerceOrderUpserterForm> formBuilder) {

		return formBuilder.title(
			__ -> "The order upserter form"
		).description(
			__ -> "This form can be used to create or update an order"
		).constructor(
			CommerceOrderUpserterForm::new
		).addOptionalLong(
			"shippingAddressId", CommerceOrderUpserterForm::setShippingAddressId
		).addOptionalString(
			"purchaseOrderNumber",
			CommerceOrderUpserterForm::setPurchaseOrderNumber
		).addRequiredLong(
			"orderOrganizationId",
			CommerceOrderUpserterForm::setOrderOrganizationId
		).addRequiredLong(
			"orderUserId", CommerceOrderUpserterForm::setOrderUserId
		).addRequiredString(
			"currency", CommerceOrderUpserterForm::setCurrency
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

	public void setCurrency(String currency) {
		_currency = currency;
	}

	public void setOrderOrganizationId(long orderOrganizationId) {
		_orderOrganizationId = orderOrganizationId;
	}

	public void setOrderUserId(long orderUserId) {
		_orderUserId = orderUserId;
	}

	public void setPurchaseOrderNumber(String purchaseOrderNumber) {
		_purchaseOrderNumber = purchaseOrderNumber;
	}

	public void setShippingAddressId(long shippingAddressId) {
		_shippingAddressId = shippingAddressId;
	}

	private String _currency;
	private long _orderOrganizationId;
	private long _orderUserId;
	private String _purchaseOrderNumber;
	private long _shippingAddressId;

}