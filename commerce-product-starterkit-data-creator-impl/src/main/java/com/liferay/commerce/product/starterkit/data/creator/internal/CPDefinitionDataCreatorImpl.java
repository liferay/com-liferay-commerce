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

package com.liferay.commerce.product.starterkit.data.creator.internal;

import com.liferay.commerce.constants.CPDefinitionInventoryConstants;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPDefinitionLinkLocalService;
import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.commerce.product.starterkit.data.creator.AssetCategoryDataCreator;
import com.liferay.commerce.product.starterkit.data.creator.CPDefinitionDataCreator;
import com.liferay.commerce.product.starterkit.data.creator.internal.util.BaseCPDemoDataCreatorHelper;
import com.liferay.commerce.product.starterkit.data.creator.internal.util.CPOptionCategoryDemoDataCreatorHelper;
import com.liferay.commerce.product.starterkit.data.creator.internal.util.CPOptionDemoDataCreatorHelper;
import com.liferay.commerce.product.starterkit.data.creator.internal.util.CPSpecificationOptionDemoDataCreatorHelper;
import com.liferay.commerce.service.CPDefinitionInventoryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;

import java.math.BigDecimal;
import java.math.MathContext;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 * @author Daniel de Francisco
 */
@Component(immediate = true, service = CPDefinitionDataCreator.class)
public class CPDefinitionDataCreatorImpl implements CPDefinitionDataCreator {

	@Override
	public CPDefinition addCPDefinition(
			long userId, long groupId, boolean buildSkus,
			JSONObject productJSONObject)
		throws Exception {

		ServiceContext serviceContext =
			_baseCPDemoDataCreatorHelper.getServiceContext(userId, groupId);

		// Product definitions

		String title = productJSONObject.getString("title");
		String description = productJSONObject.getString("description");
		String productTypeName = productJSONObject.getString("productTypeName");

		// Asset Categories

		JSONArray productJSONArray = productJSONObject.getJSONArray(
			"categories");

		long[] assetCategoryIds =
			_assetCategoryDataCreator.getProductAssetCategoryIds(
				productJSONArray);

		// Commerce product definition

		CPDefinition cpDefinition = createCPDefinition(
			userId, groupId, title, description, productTypeName,
			assetCategoryIds);

		// Commerce product definition inventory

		_cpDefinitionInventoryLocalService.addCPDefinitionInventory(
			cpDefinition.getCPDefinitionId(), null, null, false, false, 0, true,
			CPDefinitionInventoryConstants.DEFAULT_MIN_ORDER_QUANTITY,
			CPDefinitionInventoryConstants.DEFAULT_MAX_ORDER_QUANTITY, null,
			CPDefinitionInventoryConstants.DEFAULT_MULTIPLE_ORDER_QUANTITY,
			serviceContext);

		// Commerce product specifications

		JSONArray cpOptionCategories = productJSONObject.getJSONArray(
			"optionCategories");

		_cpOptionCategoryDemoDataCreatorHelper.addCPOptionCategories(
			userId, groupId, cpOptionCategories);

		// Commerce product options

		JSONArray cpOptionsJSONArray = productJSONObject.getJSONArray(
			"options");

		_cpOptionDemoDataCreatorHelper.addCPOptions(
			Locale.US, userId, groupId, cpDefinition.getCPDefinitionId(),
			cpOptionsJSONArray);

		// Commerce product specification options

		JSONArray cpSpecificationOptions = productJSONObject.getJSONArray(
			"specificationOptions");

		_cpSpecificationOptionDemoDataCreatorHelper.addCPSpecificationOptions(
			Locale.US, userId, groupId, cpDefinition.getCPDefinitionId(),
			cpSpecificationOptions);

		if (buildSkus) {

			// Commerce product instances

			_cpInstanceLocalService.buildCPInstances(
				cpDefinition.getCPDefinitionId(), serviceContext);

			// Update commerce product instances price

			String priceString = "0";
			BigDecimal price = new BigDecimal(0);

			priceString = productJSONObject.getString("price");

			try {
				price = new BigDecimal(
					Double.valueOf(priceString), MathContext.DECIMAL32);
			}
			catch (NumberFormatException nfe) {
				//price is set to 0
			}

			double promoPrice = productJSONObject.getDouble("promoPrice", 0);

			List<CPInstance> cpInstances =
				_cpInstanceLocalService.getCPDefinitionInstances(
					cpDefinition.getCPDefinitionId());

			for (CPInstance cpInstance : cpInstances) {
				_cpInstanceLocalService.updatePricingInfo(
					cpInstance.getCPInstanceId(), price,
					BigDecimal.valueOf(promoPrice), cpInstance.getCost(),
					serviceContext);
			}
		}

		return cpDefinition;
	}

	@Override
	public void createCPDefinitionLinks(
			long userId, long groupId, JSONArray catalogJSONArray)
		throws Exception {

		ServiceContext serviceContext =
			_baseCPDemoDataCreatorHelper.getServiceContext(userId, groupId);

		for (int i = 0; i < catalogJSONArray.length(); i++) {
			JSONObject productJSONObject = catalogJSONArray.getJSONObject(i);

			String title = productJSONObject.getString("title");

			CPDefinition cpDefinition = getCPDefinitionByTitle(title);

			JSONArray cpDefinitionLinksJSONArray =
				productJSONObject.getJSONArray("relatedProducts");

			if (cpDefinitionLinksJSONArray == null) {
				continue;
			}

			List<Long> cpDefinitionIdsList = new ArrayList<>();

			for (int j = 0; j < cpDefinitionLinksJSONArray.length(); j++) {
				CPDefinition cpDefinitionEntry = getCPDefinitionByTitle(
					cpDefinitionLinksJSONArray.getString(j));

				if (cpDefinitionEntry != null) {
					cpDefinitionIdsList.add(
						cpDefinitionEntry.getCPDefinitionId());
				}
			}

			long[] cpDefinitionEntryIds = ArrayUtil.toLongArray(
				cpDefinitionIdsList);

			_cpDefinitionLinkLocalService.updateCPDefinitionLinks(
				cpDefinition.getCPDefinitionId(), cpDefinitionEntryIds,
				"related", serviceContext);
		}
	}

	public CPDefinition getCPDefinitionByTitle(String title) {
		return _cpDefinitions.get(title);
	}

	public void init() {
		_cpDefinitions = new HashMap<>();
		_cpOptionDemoDataCreatorHelper.init();
		_cpSpecificationOptionDemoDataCreatorHelper.init();
		_cpOptionCategoryDemoDataCreatorHelper.init();
	}

	@Activate
	protected void activate() {
		init();
	}

	protected CPDefinition createCPDefinition(
			long userId, long groupId, String title, String description,
			String productTypeName, long[] assetCategoryIds)
		throws PortalException {

		CPDefinition cpDefinition = getCPDefinitionByTitle(title);

		if (cpDefinition != null) {
			return cpDefinition;
		}

		ServiceContext serviceContext =
			_baseCPDemoDataCreatorHelper.getServiceContext(userId, groupId);

		serviceContext.setAssetCategoryIds(assetCategoryIds);

		Calendar displayCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		displayCalendar.add(Calendar.YEAR, -1);

		int displayDateMonth = displayCalendar.get(Calendar.MONTH);
		int displayDateDay = displayCalendar.get(Calendar.DAY_OF_MONTH);
		int displayDateYear = displayCalendar.get(Calendar.YEAR);
		int displayDateHour = displayCalendar.get(Calendar.HOUR);
		int displayDateMinute = displayCalendar.get(Calendar.MINUTE);
		int displayDateAmPm = displayCalendar.get(Calendar.AM_PM);

		if (displayDateAmPm == Calendar.PM) {
			displayDateHour += 12;
		}

		Calendar expirationCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		expirationCalendar.add(Calendar.MONTH, 1);

		int expirationDateMonth = expirationCalendar.get(Calendar.MONTH);
		int expirationDateDay = expirationCalendar.get(Calendar.DAY_OF_MONTH);
		int expirationDateYear = expirationCalendar.get(Calendar.YEAR);
		int expirationDateHour = expirationCalendar.get(Calendar.HOUR);
		int expirationDateMinute = expirationCalendar.get(Calendar.MINUTE);
		int expirationDateAmPm = expirationCalendar.get(Calendar.AM_PM);

		if (expirationDateAmPm == Calendar.PM) {
			expirationDateHour += 12;
		}

		Map<Locale, String> titleMap = Collections.singletonMap(
			Locale.US, title);
		Map<Locale, String> descriptionMap = Collections.singletonMap(
			Locale.US, description);

		cpDefinition = _cpDefinitionLocalService.addCPDefinition(
			titleMap, null, descriptionMap, titleMap, null, null, null,
			productTypeName, false, true, false, false, 0, 10, 10, 10, 10, 0,
			false, false, null, true, displayDateMonth, displayDateDay,
			displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, true, false,
			serviceContext);

		_cpDefinitions.put(title, cpDefinition);

		return cpDefinition;
	}

	@Deactivate
	protected void deactivate() {
		_cpDefinitions = null;
	}

	@Reference
	private AssetCategoryDataCreator _assetCategoryDataCreator;

	@Reference
	private BaseCPDemoDataCreatorHelper _baseCPDemoDataCreatorHelper;

	@Reference
	private CPDefinitionInventoryLocalService
		_cpDefinitionInventoryLocalService;

	@Reference
	private CPDefinitionLinkLocalService _cpDefinitionLinkLocalService;

	@Reference
	private CPDefinitionLocalService _cpDefinitionLocalService;

	private Map<String, CPDefinition> _cpDefinitions;

	@Reference
	private CPInstanceLocalService _cpInstanceLocalService;

	@Reference
	private CPOptionCategoryDemoDataCreatorHelper
		_cpOptionCategoryDemoDataCreatorHelper;

	@Reference
	private CPOptionDemoDataCreatorHelper _cpOptionDemoDataCreatorHelper;

	@Reference
	private CPSpecificationOptionDemoDataCreatorHelper
		_cpSpecificationOptionDemoDataCreatorHelper;

}