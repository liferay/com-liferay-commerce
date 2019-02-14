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

package com.liferay.commerce.payment.method.paypal.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Luca Pellizzon
 */
@ExtendedObjectClassDefinition(
	category = "payment", scope = ExtendedObjectClassDefinition.Scope.GROUP
)
@Meta.OCD(
	id = "com.liferay.commerce.payment.method.paypal.internal.configuration.PayPalGroupServiceConfiguration",
	localization = "content/Language",
	name = "commerce-payment-method-paypal-group-service-configuration-name"
)
public interface PayPalGroupServiceConfiguration {

	@Meta.AD(name = "client-id", required = false)
	public String clientId();

	@Meta.AD(name = "mode", required = false)
	public String mode();

	@Meta.AD(name = "client-secret", required = false)
	public String clientSecret();

	@Meta.AD(deflt = "0", name = "payment-attempts-max-count", required = false)
	public String paymentAttemptsMaxCount();

}