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

package com.liferay.commerce.product.internal.upgrade.v1_10_1;

import com.liferay.commerce.account.configuration.CommerceAccountGroupServiceConfiguration;
import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.settings.ModifiableSettings;
import com.liferay.portal.kernel.settings.Settings;
import com.liferay.portal.kernel.settings.SettingsFactory;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Alec Sloan
 */
public class CommerceSiteTypeUpgradeProcess extends UpgradeProcess {

	public CommerceSiteTypeUpgradeProcess(
		CommerceChannelLocalService commerceChannelLocalService,
		ConfigurationProvider configurationProvider,
		SettingsFactory settingsFactory) {

		_commerceChannelLocalService = commerceChannelLocalService;
		_configurationProvider = configurationProvider;
		_settingsFactory = settingsFactory;
	}

	@Override
	protected void doUpgrade() throws Exception {
		Statement s = connection.createStatement();

		ResultSet rs = s.executeQuery(
			"select siteGroupId from CommerceChannel");

		while (rs.next()) {
			long groupId = rs.getLong("siteGroupId");

			CommerceAccountGroupServiceConfiguration
				commerceAccountGroupServiceConfiguration =
					_configurationProvider.getConfiguration(
						CommerceAccountGroupServiceConfiguration.class,
						new GroupServiceSettingsLocator(
							groupId, CommerceAccountConstants.SERVICE_NAME));

			Settings settings = _settingsFactory.getSettings(
				new GroupServiceSettingsLocator(
					_commerceChannelLocalService.
						getCommerceChannelGroupIdBySiteGroupId(groupId),
					CommerceAccountConstants.SERVICE_NAME));

			ModifiableSettings modifiableSettings =
				settings.getModifiableSettings();

			modifiableSettings.setValue(
				"commerceSiteType",
				String.valueOf(
					commerceAccountGroupServiceConfiguration.
						commerceSiteType()));

			modifiableSettings.store();
		}
	}

	private final CommerceChannelLocalService _commerceChannelLocalService;
	private final ConfigurationProvider _configurationProvider;
	private final SettingsFactory _settingsFactory;

}