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

package com.liferay.commerce.currency.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Alessio Antonio Rendina
 */
@ExtendedObjectClassDefinition(
	category = "pricing", scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
	id = "com.liferay.commerce.currency.configuration.CommerceCurrencyConfiguration",
	localization = "content/Language",
	name = "commerce-currency-configuration-name"
)
public interface CommerceCurrencyConfiguration {

	@Meta.AD(name = "default-exchange-rate-provider-key", required = false)
	public String defaultExchangeRateProviderKey();

	@Meta.AD(
		deflt = StringPool.FALSE, name = "enable-auto-update", required = false
	)
	public boolean enableAutoUpdate();

	@Meta.AD(deflt = "60", name = "update-interval", required = false)
	public int updateInterval();

}