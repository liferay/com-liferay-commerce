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

import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.CommercePriceListLocalService;
import com.liferay.commerce.price.list.service.CommercePriceListUserSegmentEntryRelLocalService;
import com.liferay.commerce.product.model.CPRule;
import com.liferay.commerce.product.service.CPRuleLocalService;
import com.liferay.commerce.product.service.CPRuleUserSegmentRelLocalService;
import com.liferay.commerce.user.segment.model.CommerceUserSegmentEntry;
import com.liferay.commerce.user.segment.service.CommerceUserSegmentEntryLocalService;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alec Sloan
 */
@Component(service = CommerceUserSegmentsImporter.class)
public class CommerceUserSegmentsImporter {

	public List<CommerceUserSegmentEntry> importCommerceUserSegments(
			JSONArray jsonArray, ServiceContext serviceContext)
		throws PortalException {

		List<CommerceUserSegmentEntry> organizations = new ArrayList<>(
			jsonArray.length());

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			CommerceUserSegmentEntry organization = _importCommerceUserSegment(
				jsonObject, serviceContext);

			organizations.add(organization);
		}

		return organizations;
	}

	protected CommercePriceList getCommercePriceListByName(
		String associatedPriceList) {

		DynamicQuery dynamicQuery =
			_commercePriceListLocalService.dynamicQuery();

		Property nameProperty = PropertyFactoryUtil.forName("name");

		dynamicQuery.add(nameProperty.eq(associatedPriceList));

		List<CommercePriceList> commercePriceLists =
			_commercePriceListLocalService.dynamicQuery(dynamicQuery);

		return commercePriceLists.get(0);
	}

	protected CPRule getCPRuleByName(String relatedCatalogRule) {
		DynamicQuery dynamicQuery = _cpRuleLocalService.dynamicQuery();

		Property nameProperty = PropertyFactoryUtil.forName("name");

		dynamicQuery.add(nameProperty.eq(relatedCatalogRule));

		List<CPRule> cpRules = _cpRuleLocalService.dynamicQuery(dynamicQuery);

		return cpRules.get(0);
	}

	private CommerceUserSegmentEntry _importCommerceUserSegment(
			JSONObject jsonObject, ServiceContext serviceContext)
		throws PortalException {

		String name = jsonObject.getString("Name");

		Map<Locale, String> nameMap = new HashMap<>();

		nameMap.put(serviceContext.getLocale(), name);

		// Add the user segment

		CommerceUserSegmentEntry commerceUserSegmentEntry =
			_commerceUserSegmentEntryLocalService.addCommerceUserSegmentEntry(
				nameMap, FriendlyURLNormalizerUtil.normalize(name), true, false,
				0, serviceContext);

		String relatedCatalogRule = jsonObject.getString("RelatedCatalogRule");

		// Add the user segment to respective CPRule

		if (!Validator.isBlank(relatedCatalogRule)) {
			CPRule cpRule = getCPRuleByName(relatedCatalogRule);

			_cpRuleUserSegmentRelLocalService.addCPRuleUserSegmentRel(
				cpRule.getCPRuleId(),
				commerceUserSegmentEntry.getCommerceUserSegmentEntryId(),
				serviceContext);
		}

		String associatedPriceList = jsonObject.getString(
			"AssociatedPriceList");

		//add the user segment to respective Price List

		if (!Validator.isBlank(relatedCatalogRule)) {
			CommercePriceList commercePriceList = getCommercePriceListByName(
				associatedPriceList);

			_commercePriceListUserSegmentEntryRelLocalService.
				addCommercePriceListUserSegmentEntryRel(
					commercePriceList.getCommercePriceListId(),
					commerceUserSegmentEntry.getCommerceUserSegmentEntryId(), 0,
					serviceContext);
		}

		return commerceUserSegmentEntry;
	}

	@Reference
	private CommercePriceListLocalService _commercePriceListLocalService;

	@Reference
	private CommercePriceListUserSegmentEntryRelLocalService
		_commercePriceListUserSegmentEntryRelLocalService;

	@Reference
	private CommerceUserSegmentEntryLocalService
		_commerceUserSegmentEntryLocalService;

	@Reference
	private CPRuleLocalService _cpRuleLocalService;

	@Reference
	private CPRuleUserSegmentRelLocalService _cpRuleUserSegmentRelLocalService;

}