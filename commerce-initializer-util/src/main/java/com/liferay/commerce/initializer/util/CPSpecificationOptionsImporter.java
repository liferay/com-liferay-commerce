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

package com.liferay.commerce.initializer.util;

import com.liferay.commerce.product.model.CPOptionCategory;
import com.liferay.commerce.product.model.CPSpecificationOption;
import com.liferay.commerce.product.service.CPOptionCategoryLocalService;
import com.liferay.commerce.product.service.CPSpecificationOptionLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Andrea Di Giorgi
 */
@Component(service = CPSpecificationOptionsImporter.class)
public class CPSpecificationOptionsImporter {

	public List<CPSpecificationOption> importCPSpecificationOptions(
			JSONArray jsonArray, ServiceContext serviceContext)
		throws PortalException {

		List<CPSpecificationOption> cpSpecificationOptions = new ArrayList<>(
			jsonArray.length());

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			CPSpecificationOption cpSpecificationOption =
				_importCPSpecificationOption(jsonObject, serviceContext);

			cpSpecificationOptions.add(cpSpecificationOption);
		}

		return cpSpecificationOptions;
	}

	private CPSpecificationOption _importCPSpecificationOption(
			JSONObject jsonObject, ServiceContext serviceContext)
		throws PortalException {

		long cpOptionCategoryId = 0;

		String categoryKey = jsonObject.getString("categoryKey");

		if (Validator.isNotNull(categoryKey)) {
			CPOptionCategory cpOptionCategory =
				_cpOptionCategoryLocalService.getCPOptionCategory(
					serviceContext.getScopeGroupId(), categoryKey);

			cpOptionCategoryId = cpOptionCategory.getCPOptionCategoryId();
		}

		Locale locale = serviceContext.getLocale();

		Map<Locale, String> titleMap = Collections.singletonMap(
			locale, jsonObject.getString("Title"));
		Map<Locale, String> descriptionMap = Collections.singletonMap(
			locale, jsonObject.getString("Description"));

		boolean facetable = jsonObject.getBoolean("Facetable");
		String key = jsonObject.getString("Key");

		return _cpSpecificationOptionLocalService.addCPSpecificationOption(
			cpOptionCategoryId, titleMap, descriptionMap, facetable, key,
			serviceContext);
	}

	@Reference
	private CPOptionCategoryLocalService _cpOptionCategoryLocalService;

	@Reference
	private CPSpecificationOptionLocalService
		_cpSpecificationOptionLocalService;

}