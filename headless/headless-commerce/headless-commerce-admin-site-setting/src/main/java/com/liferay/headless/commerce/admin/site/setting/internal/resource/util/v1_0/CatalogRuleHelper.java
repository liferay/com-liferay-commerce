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

package com.liferay.headless.commerce.admin.site.setting.internal.resource.util.v1_0;

import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.commerce.openapi.core.util.ServiceContextHelper;
import com.liferay.commerce.product.exception.NoSuchCPRuleException;
import com.liferay.commerce.product.model.CPRule;
import com.liferay.commerce.product.model.CPRuleAssetCategoryRel;
import com.liferay.commerce.product.model.CPRuleUserSegmentRel;
import com.liferay.commerce.product.service.CPRuleAssetCategoryRelService;
import com.liferay.commerce.product.service.CPRuleService;
import com.liferay.commerce.product.service.CPRuleUserSegmentRelService;
import com.liferay.headless.commerce.admin.site.setting.internal.mapper.v1_0.DTOMapper;
import com.liferay.headless.commerce.admin.site.setting.model.v1_0.CatalogRuleDTO;
import com.liferay.headless.commerce.admin.site.setting.model.v1_0.CategoryDTO;
import com.liferay.headless.commerce.admin.site.setting.model.v1_0.UserSegmentDTO;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = CatalogRuleHelper.class)
public class CatalogRuleHelper {

	public void deleteCatalogRule(String id) throws PortalException {
		_cpRuleService.deleteCPRule(GetterUtil.getLong(id));
	}

	public CatalogRuleDTO getCatalogRuleDTO(String id) throws PortalException {
		CPRule cpRule = _cpRuleService.getCPRule(GetterUtil.getLong(id));

		return _dtoMapper.modelToDTO(cpRule);
	}

	public CollectionDTO<CatalogRuleDTO> getCatalogRuleDTOs(
			Long groupId, Pagination pagination)
		throws PortalException {

		List<CPRule> cpRules = _cpRuleService.getCPRules(
			groupId, pagination.getStartPosition(), pagination.getEndPosition(),
			null);

		int count = _cpRuleService.getCPRulesCount(groupId);

		List<CatalogRuleDTO> catalogRuleDTOs = new ArrayList<>();

		for (CPRule cpRule : cpRules) {
			catalogRuleDTOs.add(_dtoMapper.modelToDTO(cpRule));
		}

		return new CollectionDTO<>(catalogRuleDTOs, count);
	}

	public CollectionDTO<CategoryDTO> getCategoryDTOs(
			String id, Pagination pagination)
		throws PortalException {

		List<CPRuleAssetCategoryRel> ruleAssetCategoryRels =
			_cpRuleAssetCategoryRelService.getCPRuleAssetCategoryRels(
				GetterUtil.getLong(id), pagination.getStartPosition(),
				pagination.getEndPosition());

		int count =
			_cpRuleAssetCategoryRelService.getCPRuleAssetCategoryRelsCount(
				GetterUtil.getLong(id));

		List<CategoryDTO> categoryDTOs = new ArrayList<>();

		for (CPRuleAssetCategoryRel cpRuleAssetCategoryRel :
				ruleAssetCategoryRels) {

			categoryDTOs.add(_dtoMapper.modelToDTO(cpRuleAssetCategoryRel));
		}

		return new CollectionDTO<>(categoryDTOs, count);
	}

	public CollectionDTO<UserSegmentDTO> getUserSegmentDTOs(
			String id, Pagination pagination)
		throws PortalException {

		List<CPRuleUserSegmentRel> cpRuleUserSegmentRels =
			_cpRuleUserSegmentRelService.getCPRuleUserSegmentRels(
				GetterUtil.getLong(id), pagination.getStartPosition(),
				pagination.getEndPosition(), null);

		int count = _cpRuleUserSegmentRelService.getCPRuleUserSegmentRelsCount(
			GetterUtil.getLong(id));

		List<UserSegmentDTO> userSegmentDTOs = new ArrayList<>();

		for (CPRuleUserSegmentRel cpRuleUserSegmentRel :
				cpRuleUserSegmentRels) {

			userSegmentDTOs.add(_dtoMapper.modelToDTO(cpRuleUserSegmentRel));
		}

		return new CollectionDTO<>(userSegmentDTOs, count);
	}

	public CPRule updateCatalogRule(
			String id, CatalogRuleDTO catalogRuleDTO, User user)
		throws PortalException {

		CPRule cpRule = _cpRuleService.getCPRule(GetterUtil.getLong(id));

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			cpRule.getGroupId(), new long[0], user, true);

		return _cpRuleService.updateCPRule(
			cpRule.getCPRuleId(), catalogRuleDTO.getName(),
			GetterUtil.get(catalogRuleDTO.isActive(), cpRule.isActive()),
			catalogRuleDTO.getType(),
			_getTypeSettingsProperties(catalogRuleDTO, cpRule), serviceContext);
	}

	public CatalogRuleDTO upsertCatalogRule(
			Long groupId, CatalogRuleDTO catalogRuleDTO, User user)
		throws PortalException {

		try {
			CPRule cpRule = updateCatalogRule(
				String.valueOf(catalogRuleDTO.getId()), catalogRuleDTO, user);

			return _dtoMapper.modelToDTO(cpRule);
		}
		catch (NoSuchCPRuleException nscpre) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to find catalogRule with ID: " +
						catalogRuleDTO.getId());
			}
		}

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			groupId, new long[0], user, true);

		CPRule cpRule = _cpRuleService.addCPRule(
			catalogRuleDTO.getName(),
			GetterUtil.get(catalogRuleDTO.isActive(), false),
			catalogRuleDTO.getType(),
			_getTypeSettingsProperties(catalogRuleDTO, null), serviceContext);

		return _dtoMapper.modelToDTO(cpRule);
	}

	private UnicodeProperties _getTypeSettingsProperties(
		CatalogRuleDTO catalogRuleDTO, CPRule cpRule) {

		Map<String, String> typeSettings = catalogRuleDTO.getTypeSettings();

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