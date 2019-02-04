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

package com.liferay.commerce.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Luca Pellizzon
 * @author Alessio Antonio Rendina
 */
@ExtendedObjectClassDefinition(
	category = "catalog", scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
	id = "com.liferay.commerce.configuration.CommerceSubscriptionConfiguration",
	localization = "content/Language",
	name = "commerce-subscription-configuration-name"
)
public interface CommerceSubscriptionConfiguration {

	@Meta.AD(deflt = "10", name = "check-renew-interval", required = false)
	public int checkRenewInterval();

	@Meta.AD(deflt = "10", name = "check-paid-order-interval", required = false)
	public int checkPaidOrderInterval();

	@Meta.AD(deflt = "false", name = "cancel-subscription", required = false)
	public boolean cancelSubscription();

	@Meta.AD(deflt = "false", name = "suspend-subscription", required = false)
	public boolean suspendSubscription();

	@Meta.AD(
		deflt = "99", name = "payment-attempts-max-count", required = false
	)
	public int paymentAttemptsMaxCount();

}