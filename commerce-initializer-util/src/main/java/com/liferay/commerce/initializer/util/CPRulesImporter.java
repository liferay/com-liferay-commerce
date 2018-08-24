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

import com.liferay.commerce.product.model.CPRule;
import com.liferay.commerce.product.service.CPRuleLocalService;
import com.liferay.commerce.product.service.CPRuleUserSegmentRelLocalService;
import com.liferay.commerce.user.segment.model.CommerceUserSegmentEntry;
import com.liferay.commerce.user.segment.service.CommerceUserSegmentEntryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(service = CPRulesImporter.class)
public class CPRulesImporter {

	public List<CPRule> importCPRules(
			JSONArray jsonArray, ServiceContext serviceContext)
		throws PortalException {

		List<CPRule> cpRules = new ArrayList<>(jsonArray.length());

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			CPRule cpRule = _importCPRule(jsonObject, serviceContext);

			cpRules.add(cpRule);
		}

		return cpRules;
	}

	private CPRule _importCPRule(
			JSONObject jsonObject, ServiceContext serviceContext)
		throws PortalException {

		// Commerce product rule

		String name = jsonObject.getString("name");
		boolean active = jsonObject.getBoolean("active", true);
		String type = jsonObject.getString("type");

		CPRule cpRule = _cpRuleLocalService.addCPRule(
			name, active, type, serviceContext);

		// Commerce product rule user segment rels

		JSONArray userSegmentsJSONArray = jsonObject.getJSONArray(
			"userSegments");

		if (userSegmentsJSONArray != null) {
			for (int i = 0; i < userSegmentsJSONArray.length(); i++) {
				String userSegmentName = userSegmentsJSONArray.getString(i);

				CommerceUserSegmentEntry commerceUserSegmentEntry =
					_commerceUserSegmentEntryLocalService.
						fetchCommerceUserSegmentEntry(
							serviceContext.getScopeGroupId(), userSegmentName);

				if (commerceUserSegmentEntry == null) {
					continue;
				}

				_cpRuleUserSegmentRelLocalService.addCPRuleUserSegmentRel(
					cpRule.getCPRuleId(),
					commerceUserSegmentEntry.getCommerceUserSegmentEntryId(),
					serviceContext);
			}
		}

		return cpRule;
	}

	@Reference
	private CommerceUserSegmentEntryLocalService
		_commerceUserSegmentEntryLocalService;

	@Reference
	private CPRuleLocalService _cpRuleLocalService;

	@Reference
	private CPRuleUserSegmentRelLocalService _cpRuleUserSegmentRelLocalService;

}