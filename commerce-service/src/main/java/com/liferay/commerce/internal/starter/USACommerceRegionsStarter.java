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

package com.liferay.commerce.internal.starter;

import com.liferay.commerce.service.CommerceCountryLocalService;
import com.liferay.commerce.service.CommerceRegionLocalService;
import com.liferay.commerce.starter.CommerceRegionsStarter;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.service.ServiceContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(
	immediate = true,
	property = "commerce.region.starter.key=" + USACommerceRegionsStarter.USA_NUMERIC_ISO_CODE,
	service = CommerceRegionsStarter.class
)
public class USACommerceRegionsStarter
	extends CommerceRegionsStarterBase implements CommerceRegionsStarter {

	public static final int USA_NUMERIC_ISO_CODE = 840;

	@Override
	public void start(ServiceContext serviceContext) throws Exception {
		start(
			_commerceCountryLocalService, _commerceRegionLocalService,
			_jsonFactory, serviceContext, USA_NUMERIC_ISO_CODE, layoutsPath);
	}

	private static final String layoutsPath =
		"com/liferay/commerce/internal/usa.json";

	@Reference
	private CommerceCountryLocalService _commerceCountryLocalService;

	@Reference
	private CommerceRegionLocalService _commerceRegionLocalService;

	@Reference
	private JSONFactory _jsonFactory;

}