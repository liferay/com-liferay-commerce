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

package com.liferay.commerce.inventory.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Luca Pellizzon
 */
@ExtendedObjectClassDefinition(
	category = "inventory", scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
	id = "com.liferay.commerce.inventory.configuration.CommerceInventorySystemConfiguration",
	localization = "content/Language",
	name = "commerce-inventory-system-configuration-name"
)
public interface CommerceInventorySystemConfiguration {

	@Meta.AD(
		deflt = "default", name = "inventory-engine-method", required = false
	)
	public String commerceInventoryEngineMethod();

	@Meta.AD(
		deflt = "60", name = "check-temporary-booked-quantity-interval",
		required = false
	)
	public int checkCommerceInventoryTemporaryBookedQuantityInterval();

	@Meta.AD(
		deflt = "60", name = "check-inventory-audit-interval", required = false
	)
	public int checkCommerceInventoryAuditQuantityInterval();

	@Meta.AD(
		deflt = "12", name = "delete-inventory-audit-month-interval",
		required = false
	)
	public int deleteAuditMonthInterval();

}