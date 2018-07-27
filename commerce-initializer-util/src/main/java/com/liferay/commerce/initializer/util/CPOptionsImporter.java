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

import com.liferay.commerce.initializer.util.internal.CommerceInitializerUtil;
import com.liferay.commerce.product.model.CPOption;
import com.liferay.commerce.product.model.CPOptionValue;
import com.liferay.commerce.product.service.CPOptionLocalService;
import com.liferay.commerce.product.service.CPOptionValueLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;

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
@Component(service = CPOptionsImporter.class)
public class CPOptionsImporter {

	public List<CPOption> importCPOptions(
			JSONArray jsonArray, ServiceContext serviceContext)
		throws PortalException {

		List<CPOption> cpOptions = new ArrayList<>(jsonArray.length());

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			CPOption cpOption = _importCPOption(jsonObject, serviceContext);

			cpOptions.add(cpOption);
		}

		return cpOptions;
	}

	private CPOption _importCPOption(
			JSONObject jsonObject, ServiceContext serviceContext)
		throws PortalException {

		// Commerce product option

		String key = jsonObject.getString("Key");

		Locale locale = serviceContext.getLocale();

		Map<Locale, String> nameMap = Collections.singletonMap(
			locale, CommerceInitializerUtil.getValue(jsonObject, "Name", key));

		Map<Locale, String> descriptionMap = Collections.singletonMap(
			locale, jsonObject.getString("Description"));

		String ddmFormFieldTypeName = jsonObject.getString(
			"DDMFormFieldTypeName", "select");
		boolean facetable = jsonObject.getBoolean("Facetable");
		boolean required = jsonObject.getBoolean("Required");
		boolean skuContributor = jsonObject.getBoolean("SkuContributor");

		CPOption cpOption = _cpOptionLocalService.addCPOption(
			nameMap, descriptionMap, ddmFormFieldTypeName, facetable, required,
			skuContributor, key, serviceContext);

		// Commerce product option values

		JSONArray valuesJSONArray = jsonObject.getJSONArray("Values");

		if (valuesJSONArray != null) {
			for (int i = 0; i < valuesJSONArray.length(); i++) {
				JSONObject valueJSONObject = valuesJSONArray.getJSONObject(i);

				_importCPOptionValue(
					valueJSONObject, cpOption, i, serviceContext);
			}
		}

		return cpOption;
	}

	private CPOptionValue _importCPOptionValue(
			JSONObject jsonObject, CPOption cpOption, double defaultPriority,
			ServiceContext serviceContext)
		throws PortalException {

		String key = jsonObject.getString("Key");

		Map<Locale, String> nameMap = Collections.singletonMap(
			serviceContext.getLocale(),
			CommerceInitializerUtil.getValue(jsonObject, "Name", key));

		double priority = jsonObject.getDouble("Priority", defaultPriority);

		return _cpOptionValueLocalService.addCPOptionValue(
			cpOption.getCPOptionId(), nameMap, priority, key, serviceContext);
	}

	@Reference
	private CPOptionLocalService _cpOptionLocalService;

	@Reference
	private CPOptionValueLocalService _cpOptionValueLocalService;

}