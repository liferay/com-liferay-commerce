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

package com.liferay.commerce.initializer.breccia.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Daniel de Francisco
 * @author Guy Wandji
 */
@ExtendedObjectClassDefinition(
	category = "commerce", scope = ExtendedObjectClassDefinition.Scope.GROUP
)
@Meta.OCD(
	id = "com.liferay.commerce.initializer.breccia.internal.configuration.BrecciaInitializerConfiguration",
	localization = "content/Language",
	name = "commerce-breccia-initializer-configuration"
)
public interface BrecciaInitializerConfiguration {

	@Meta.AD(
		deflt = "Canada Mega Office,QuickOffice,Workfors", name = "accounts",
		required = false
	)
	public String[] accounts();

	@Meta.AD(deflt = "Buyer", name = "buyer-role-name", required = false)
	public String buyerRoleName();

	@Meta.AD(
		deflt = "Account-manager", name = "account-manager-role-name",
		required = false
	)
	public String accountManagerRoleName();

	@Meta.AD(
		deflt = "Regular", name = "demo-tax-category-name", required = false
	)
	public String demoTaxCategoryName();

	@Meta.AD(
		deflt = "Default Tax Category", name = "demo-tax-category-descripttion",
		required = false
	)
	public String demoTaxCategoryDescription();

	@Meta.AD(
		deflt = "Gold Program", name = "user-segment-name", required = false
	)
	public String userSegmentName();

	@Meta.AD(deflt = "12000", name = "address-type-id", required = false)
	public long addressTypeId();

	@Meta.AD(deflt = "Chicago", name = "city", required = false)
	public String city();

	@Meta.AD(
		deflt = "205 W. Wacker Dr Suite 720", name = "street1", required = false
	)
	public String street1();

	@Meta.AD(deflt = "60606", name = "zip", required = false)
	public String zip();

	@Meta.AD(deflt = "IL", name = "regionCode", required = false)
	public String regionCode();

	@Meta.AD(deflt = "840", name = "countryNumericISOCode", required = false)
	public int countryISOCode();

	@Meta.AD(deflt = "US", name = "country2LettersISOCode", required = false)
	public String country2LettersISOCode();

}