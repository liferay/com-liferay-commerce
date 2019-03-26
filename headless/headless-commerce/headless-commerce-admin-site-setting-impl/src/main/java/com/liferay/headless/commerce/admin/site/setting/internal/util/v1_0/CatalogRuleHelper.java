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

package com.liferay.headless.commerce.admin.site.setting.internal.util.v1_0;

import com.liferay.commerce.product.exception.NoSuchCPRuleException;
import com.liferay.commerce.product.model.CPRule;
import com.liferay.commerce.product.model.CPRuleAssetCategoryRel;
import com.liferay.commerce.product.model.CPRuleUserSegmentRel;
import com.liferay.commerce.product.service.CPRuleAssetCategoryRelService;
import com.liferay.commerce.product.service.CPRuleService;
import com.liferay.commerce.product.service.CPRuleUserSegmentRelService;
import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.CatalogRule;
import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.Category;
import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.UserSegment;
import com.liferay.headless.commerce.admin.site.setting.internal.mapper.v1_0.DTOMapper;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 * @author Zoltán Takács
 */
@Component(immediate = true, service = CatalogRuleHelper.class)
public class CatalogRuleHelper {

	public void deleteCatalogRule(Long id) throws PortalException {
		_cpRuleService.deleteCPRule(id);
	}

	public CatalogRule getCatalogRule(Long id) throws PortalException {
		CPRule cpRule = _cpRuleService.getCPRule(id);

		return _dtoMapper.modelToDTO(cpRule);
	}

	public Page<CatalogRule> getCatalogRules(
			Long groupId, Pagination pagination)
		throws PortalException {

		List<CPRule> cpRules = _cpRuleService.getCPRules(
			groupId, pagination.getStartPosition(), pagination.getEndPosition(),
			null);

		int count = _cpRuleService.getCPRulesCount(groupId);

		List<CatalogRule> catalogRules = new ArrayList<>();

		for (CPRule cpRule : cpRules) {
			catalogRules.add(_dtoMapper.modelToDTO(cpRule));
		}

		return Page.of(catalogRules, pagination, count);
	}

	public Page<Category> getCategories(Long id, Pagination pagination)
		throws PortalException {

		List<CPRuleAssetCategoryRel> ruleAssetCategoryRels =
			_cpRuleAssetCategoryRelService.getCPRuleAssetCategoryRels(
				id, pagination.getStartPosition(), pagination.getEndPosition());

		int count =
			_cpRuleAssetCategoryRelService.getCPRuleAssetCategoryRelsCount(id);

		List<Category> categories = new ArrayList<>();

		for (CPRuleAssetCategoryRel cpRuleAssetCategoryRel :
				ruleAssetCategoryRels) {

			categories.add(_dtoMapper.modelToDTO(cpRuleAssetCategoryRel));
		}

		return Page.of(categories, pagination, count);
	}

	public Page<UserSegment> getUserSegments(Long id, Pagination pagination)
		throws PortalException {

		List<CPRuleUserSegmentRel> cpRuleUserSegmentRels =
			_cpRuleUserSegmentRelService.getCPRuleUserSegmentRels(
				id, pagination.getStartPosition(), pagination.getEndPosition(),
				null);

		int count = _cpRuleUserSegmentRelService.getCPRuleUserSegmentRelsCount(
			id);

		List<UserSegment> userSegments = new ArrayList<>();

		for (CPRuleUserSegmentRel cpRuleUserSegmentRel :
				cpRuleUserSegmentRels) {

			userSegments.add(_dtoMapper.modelToDTO(cpRuleUserSegmentRel));
		}

		return Page.of(userSegments, pagination, count);
	}

	public CPRule updateCatalogRule(Long id, CatalogRule catalogRule, User user)
		throws PortalException {

		CPRule cpRule = _cpRuleService.getCPRule(id);

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			cpRule.getGroupId(), new long[0], user, true);

		return _cpRuleService.updateCPRule(
			cpRule.getCPRuleId(), catalogRule.getName(),
			GetterUtil.get(catalogRule.getActive(), cpRule.isActive()),
			catalogRule.getType(),
			_getTypeSettingsProperties(catalogRule, cpRule), serviceContext);
	}

	public CatalogRule upsertCatalogRule(
			Long groupId, CatalogRule catalogRule, User user)
		throws PortalException {

		try {
			CPRule cpRule = updateCatalogRule(
				catalogRule.getId(), catalogRule, user);

			return _dtoMapper.modelToDTO(cpRule);
		}
		catch (NoSuchCPRuleException nscpre) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to find catalogRule with ID: " +
						catalogRule.getId());
			}
		}

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			groupId, new long[0], user, true);

		CPRule cpRule = _cpRuleService.addCPRule(
			catalogRule.getName(),
			GetterUtil.get(catalogRule.getActive(), false),
			catalogRule.getType(),
			_getTypeSettingsProperties(catalogRule, null), serviceContext);

		return _dtoMapper.modelToDTO(cpRule);
	}

	private UnicodeProperties _getTypeSettingsProperties(
		CatalogRule catalogRule, CPRule cpRule) {

		Map<String, String> typeSettings = catalogRule.getTypeSettings();

		UnicodeProperties typeSettingsProperties;

		if (typeSettings == null) {
			typeSettingsProperties = new UnicodeProperties(true);

			for (Map.Entry<String, String> entry : typeSettings.entrySet()) {
				typeSettingsProperties.put(entry.getKey(), entry.getValue());
			}

			return typeSettingsProperties;
		}

		if (cpRule == null) {
			return null;
		}

		return cpRule.getTypeSettingsProperties();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CatalogRuleHelper.class);

	@Reference
	private CPRuleAssetCategoryRelService _cpRuleAssetCategoryRelService;

	@Reference
	private CPRuleService _cpRuleService;

	@Reference
	private CPRuleUserSegmentRelService _cpRuleUserSegmentRelService;

	@Reference
	private DTOMapper _dtoMapper;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}