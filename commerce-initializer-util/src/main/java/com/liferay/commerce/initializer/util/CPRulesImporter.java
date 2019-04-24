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

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetCategoryConstants;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.commerce.account.model.CommerceAccountGroup;
import com.liferay.commerce.account.service.CommerceAccountGroupLocalService;
import com.liferay.commerce.product.model.CPRule;
import com.liferay.commerce.product.service.CPRuleAssetCategoryRelLocalService;
import com.liferay.commerce.product.service.CPRuleCommerceAccountGroupRelLocalService;
import com.liferay.commerce.product.service.CPRuleLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.UnicodeProperties;

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
			JSONArray jsonArray, long scopeGroupId, long userId)
		throws PortalException {

		User user = _userLocalService.getUser(userId);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setScopeGroupId(scopeGroupId);
		serviceContext.setUserId(userId);
		serviceContext.setCompanyId(user.getCompanyId());

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

		JSONArray typeSettingsJSONArray = jsonObject.getJSONArray(
			"typeSettings");

		UnicodeProperties typeSettings = null;

		if (typeSettingsJSONArray != null) {
			typeSettings = new UnicodeProperties(true);

			for (int i = 0; i < typeSettingsJSONArray.length(); i++) {
				JSONObject typeSettingJSONObject =
					typeSettingsJSONArray.getJSONObject(i);

				typeSettings.setProperty(
					typeSettingJSONObject.getString("name"),
					typeSettingJSONObject.getString("value"));
			}
		}

		CPRule cpRule = _cpRuleLocalService.addCPRule(
			name, active, type, typeSettings, serviceContext);

		// Commerce Product Rule Account groups Rels

		JSONArray accountGroupsJSONArray = jsonObject.getJSONArray(
			"accountGroups");

		if (accountGroupsJSONArray != null) {
			for (int i = 0; i < accountGroupsJSONArray.length(); i++) {
				String AccountGroupExternalReferenceCode =
					accountGroupsJSONArray.getString(i);

				CommerceAccountGroup commerceAccountGroup =
					_commerceAccountGroupLocalService.
						fetchCommerceAccountGroupByReferenceCode(
							serviceContext.getCompanyId(),
							AccountGroupExternalReferenceCode);

				if (commerceAccountGroup == null) {
					continue;
				}

				_cpRuleCommerceAccountGroupRelLocalService.
					addCPRuleCommerceAccountGroupRel(
						cpRule.getCPRuleId(),
						commerceAccountGroup.getCommerceAccountGroupId(),
						serviceContext);
			}
		}

		// Commerce Product Rule Asset Category Rels

		JSONArray assetCategoryJSONArray = jsonObject.getJSONArray(
			"assetCategories");

		if (assetCategoryJSONArray != null) {
			for (int i = 0; i < assetCategoryJSONArray.length(); i++) {
				String assetCategoryName = assetCategoryJSONArray.getString(i);

				AssetVocabulary assetVocabulary =
					_assetVocabularyLocalService.fetchGroupVocabulary(
						serviceContext.getScopeGroupId(), "Commerce");

				AssetCategory assetCategory =
					_assetCategoryLocalService.fetchCategory(
						serviceContext.getScopeGroupId(),
						AssetCategoryConstants.DEFAULT_PARENT_CATEGORY_ID,
						assetCategoryName, assetVocabulary.getVocabularyId());

				_cpRuleAssetCategoryRelLocalService.addCPRuleAssetCategoryRel(
					cpRule.getCPRuleId(), assetCategory.getCategoryId(),
					serviceContext);
			}
		}

		return cpRule;
	}

	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;

	@Reference
	private AssetVocabularyLocalService _assetVocabularyLocalService;

	@Reference
	private CommerceAccountGroupLocalService _commerceAccountGroupLocalService;

	@Reference
	private CPRuleAssetCategoryRelLocalService
		_cpRuleAssetCategoryRelLocalService;

	@Reference
	private CPRuleCommerceAccountGroupRelLocalService
		_cpRuleCommerceAccountGroupRelLocalService;

	@Reference
	private CPRuleLocalService _cpRuleLocalService;

	@Reference
	private UserLocalService _userLocalService;

}