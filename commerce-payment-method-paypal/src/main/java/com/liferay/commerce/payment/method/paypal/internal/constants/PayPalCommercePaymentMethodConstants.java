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

package com.liferay.commerce.payment.method.paypal.internal.constants;

import com.paypal.base.Constants;

/**
 * @author Luca Pellizzon
 */
public class PayPalCommercePaymentMethodConstants {

	public static final String AUTHORIZATION_STATE_COMPLETED = "completed";

	public static final String AUTHORIZATION_STATE_CREATED = "created";

	public static final String AUTHORIZATION_STATE_VOIDED = "voided";

	public static final String INTENTAUTHORIZE = "authorize";

	public static final String INTENTSALE = "sale";

	public static final String[] MODES = {Constants.LIVE, Constants.SANDBOX};

	public static final String PAYMENT_STATE_FAILED = "failed";

	public static final String SERVICE_NAME =
		"com.liferay.commerce.payment.engine.method.paypal";

	public static final String SERVLET_PATH = "commerce-paypal-payment";

}