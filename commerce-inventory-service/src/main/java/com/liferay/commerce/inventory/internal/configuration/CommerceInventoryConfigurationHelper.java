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

package com.liferay.commerce.inventory.internal.configuration;

import com.liferay.commerce.inventory.configuration.CommerceInventoryGroupConfiguration;
import com.liferay.commerce.inventory.configuration.CommerceInventorySystemConfiguration;
import com.liferay.commerce.inventory.constants.CommerceInventoryConstants;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.settings.SystemSettingsLocator;

/**
 * @author Luca Pellizzon
 */
public class CommerceInventoryConfigurationHelper {

	public static String getCommerceInventoryEngineMethodKey(
			long groupId, ConfigurationProvider configurationProvider)
		throws ConfigurationException {

		CommerceInventoryGroupConfiguration groupConfiguration =
			configurationProvider.getConfiguration(
				CommerceInventoryGroupConfiguration.class,
				new GroupServiceSettingsLocator(
					groupId, CommerceInventoryConstants.SERVICE_NAME));

		if (groupConfiguration.overrideSystemConfiguration()) {
			return groupConfiguration.commerceInventoryEngineMethod();
		}

		CommerceInventorySystemConfiguration systemConfiguration =
			configurationProvider.getConfiguration(
				CommerceInventorySystemConfiguration.class,
				new SystemSettingsLocator(
					CommerceInventorySystemConfiguration.class.getName()));

		return systemConfiguration.commerceInventoryEngineMethod();
	}

}