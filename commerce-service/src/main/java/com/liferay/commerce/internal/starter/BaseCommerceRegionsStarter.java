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

import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.service.CommerceCountryLocalService;
import com.liferay.commerce.service.CommerceRegionLocalService;
import com.liferay.commerce.starter.CommerceRegionsStarter;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Riccardo Alberti
 */
public abstract class BaseCommerceRegionsStarter
	implements CommerceRegionsStarter {

	protected void start(
			CommerceCountryLocalService commerceCountryLocalService,
			CommerceRegionLocalService commerceRegionLocalService,
			JSONFactory jsonFactory, ServiceContext serviceContext, int isoCode,
			String layoutsPath)
		throws Exception {

		_init(
			commerceCountryLocalService, commerceRegionLocalService,
			jsonFactory);

		CommerceCountry commerceCountry = _getCommerceCountry(
			serviceContext.getCompanyId(), isoCode);

		if (commerceCountry == null) {
			return;
		}

		_parseAndAddCommerceRegions(
			commerceCountry, serviceContext, layoutsPath);
	}

	private CommerceCountry _getCommerceCountry(long companyId, int isoCode)
		throws PortalException {

		return _commerceCountryLocalService.fetchCommerceCountry(
			companyId, isoCode);
	}

	private JSONArray _getCommerceRegionsJSONArray(String layoutsPath)
		throws Exception {

		Class<?> clazz = getClass();

		String regionsJSON = StringUtil.read(
			clazz.getClassLoader(), layoutsPath, false);

		return _jsonFactory.createJSONArray(regionsJSON);
	}

	private void _init(
		CommerceCountryLocalService commerceCountryLocalService,
		CommerceRegionLocalService commerceRegionLocalService,
		JSONFactory jsonFactory) {

		_commerceCountryLocalService = commerceCountryLocalService;
		_commerceRegionLocalService = commerceRegionLocalService;
		_jsonFactory = jsonFactory;
	}

	private void _parseAndAddCommerceRegions(
			CommerceCountry commerceCountry, ServiceContext serviceContext,
			String layoutsPath)
		throws Exception {

		JSONArray jsonArray = _getCommerceRegionsJSONArray(layoutsPath);

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			String code = jsonObject.getString("code");
			String name = jsonObject.getString("name");
			double priority = jsonObject.getDouble("priority");

			_commerceRegionLocalService.addCommerceRegion(
				commerceCountry.getCommerceCountryId(), name, code, priority,
				true, serviceContext);
		}
	}

	private CommerceCountryLocalService _commerceCountryLocalService;
	private CommerceRegionLocalService _commerceRegionLocalService;
	private JSONFactory _jsonFactory;

}