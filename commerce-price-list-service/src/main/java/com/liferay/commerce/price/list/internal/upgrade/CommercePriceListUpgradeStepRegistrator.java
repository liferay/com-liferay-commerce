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

package com.liferay.commerce.price.list.internal.upgrade;

import com.liferay.commerce.price.list.internal.upgrade.v1_1_0.CommercePriceEntryUpgradeProcess;
import com.liferay.commerce.price.list.internal.upgrade.v1_2_0.CommercePriceListAccountRelUpgradeProcess;
import com.liferay.commerce.price.list.internal.upgrade.v2_0_0.CommercePriceListCommerceAccountGroupRelUpgradeProcess;
import com.liferay.commerce.price.list.internal.upgrade.v2_0_0.CommerceTierPriceEntryUpgradeProcess;
import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alec Sloan
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class CommercePriceListUpgradeStepRegistrator
	implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		if (_log.isInfoEnabled()) {
			_log.info("COMMERCE PRICE LIST UPGRADE STEP REGISTRATOR STARTED");
		}

		registry.register(
			_SCHEMA_VERSION_1_0_0, _SCHEMA_VERSION_1_1_0,
			new CommercePriceEntryUpgradeProcess(
				_cpDefinitionLocalService, _cpInstanceLocalService));

		registry.register(
			_SCHEMA_VERSION_1_1_0, _SCHEMA_VERSION_1_2_0,
			new CommercePriceListAccountRelUpgradeProcess());

		registry.register(
			_SCHEMA_VERSION_1_2_0, _SCHEMA_VERSION_2_0_0,
			new com.liferay.commerce.price.list.internal.upgrade.v2_0_0.
				CommercePriceEntryUpgradeProcess(),
			new com.liferay.commerce.price.list.internal.upgrade.v2_0_0.
				CommercePriceListAccountRelUpgradeProcess(),
			new CommercePriceListCommerceAccountGroupRelUpgradeProcess(),
			new CommerceTierPriceEntryUpgradeProcess());

		if (_log.isInfoEnabled()) {
			_log.info("COMMERCE PRICE LIST UPGRADE STEP REGISTRATOR FINISHED");
		}
	}

	private static final String _SCHEMA_VERSION_1_0_0 = "1.0.0";

	private static final String _SCHEMA_VERSION_1_1_0 = "1.1.0";

	private static final String _SCHEMA_VERSION_1_2_0 = "1.2.0";

	private static final String _SCHEMA_VERSION_2_0_0 = "2.0.0";

	private static final Log _log = LogFactoryUtil.getLog(
		CommercePriceListUpgradeStepRegistrator.class);

	@Reference
	private CPDefinitionLocalService _cpDefinitionLocalService;

	@Reference
	private CPInstanceLocalService _cpInstanceLocalService;

}