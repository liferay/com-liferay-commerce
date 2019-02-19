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

import com.liferay.commerce.price.list.exception.NoSuchPriceListException;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.CommercePriceListLocalService;
import com.liferay.commerce.price.list.service.CommercePriceListUserSegmentEntryRelLocalService;
import com.liferay.commerce.product.exception.NoSuchCPRuleException;
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

		List<CommerceUserSegmentEntry> commerceUserSegmentEntries =
			new ArrayList<>(jsonArray.length());

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			CommerceUserSegmentEntry commerceUserSegmentEntry =
				_importCommerceUserSegment(jsonObject, serviceContext);

			commerceUserSegmentEntries.add(commerceUserSegmentEntry);
		}

		return commerceUserSegmentEntries;
	}

	protected CommercePriceList getCommercePriceList(String name)
		throws PortalException {

		DynamicQuery dynamicQuery =
			_commercePriceListLocalService.dynamicQuery();

		Property nameProperty = PropertyFactoryUtil.forName("name");

		dynamicQuery.add(nameProperty.eq(name));

		List<CommercePriceList> commercePriceLists =
			_commercePriceListLocalService.dynamicQuery(dynamicQuery, 0, 1);

		if (commercePriceLists.isEmpty()) {
			throw new NoSuchPriceListException(
				"No price list found with name " + name);
		}

		return commercePriceLists.get(0);
	}

	protected CPRule getCPRule(String name) throws PortalException {
		DynamicQuery dynamicQuery = _cpRuleLocalService.dynamicQuery();

		Property nameProperty = PropertyFactoryUtil.forName("name");

		dynamicQuery.add(nameProperty.eq(name));

		List<CPRule> cpRules = _cpRuleLocalService.dynamicQuery(
			dynamicQuery, 0, 1);

		if (cpRules.isEmpty()) {
			throw new NoSuchCPRuleException("No rule found with name " + name);
		}

		return cpRules.get(0);
	}

	private CommerceUserSegmentEntry _importCommerceUserSegment(
			JSONObject jsonObject, ServiceContext serviceContext)
		throws PortalException {

		Map<Locale, String> nameMap = new HashMap<>();

		String name = jsonObject.getString("Name");

		nameMap.put(serviceContext.getLocale(), name);

		// Add User Segment

		CommerceUserSegmentEntry commerceUserSegmentEntry =
			_commerceUserSegmentEntryLocalService.addCommerceUserSegmentEntry(
				nameMap, FriendlyURLNormalizerUtil.normalize(name), true, false,
				0, serviceContext);

		// Add User Segment Rels

		String relatedCatalogRuleName = jsonObject.getString(
			"RelatedCatalogRule");

		if (!Validator.isBlank(relatedCatalogRuleName)) {
			CPRule cpRule = getCPRule(relatedCatalogRuleName);

			_cpRuleUserSegmentRelLocalService.addCPRuleUserSegmentRel(
				cpRule.getCPRuleId(),
				commerceUserSegmentEntry.getCommerceUserSegmentEntryId(),
				serviceContext);
		}

		String associatedPriceListName = jsonObject.getString(
			"AssociatedPriceList");

		if (!Validator.isBlank(associatedPriceListName)) {
			CommercePriceList commercePriceList = getCommercePriceList(
				associatedPriceListName);

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