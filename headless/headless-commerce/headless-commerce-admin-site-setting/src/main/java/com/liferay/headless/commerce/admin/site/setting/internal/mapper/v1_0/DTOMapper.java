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

package com.liferay.headless.commerce.admin.site.setting.internal.mapper.v1_0;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.commerce.model.CommerceAvailabilityEstimate;
import com.liferay.commerce.model.CommerceWarehouse;
import com.liferay.commerce.openapi.core.util.LanguageUtils;
import com.liferay.commerce.product.model.CPMeasurementUnit;
import com.liferay.commerce.product.model.CPRule;
import com.liferay.commerce.product.model.CPRuleAssetCategoryRel;
import com.liferay.commerce.product.model.CPRuleUserSegmentRel;
import com.liferay.commerce.product.model.CPTaxCategory;
import com.liferay.commerce.user.segment.model.CommerceUserSegmentCriterion;
import com.liferay.commerce.user.segment.model.CommerceUserSegmentEntry;
import com.liferay.headless.commerce.admin.site.setting.model.v1_0.AvailabilityEstimateDTO;
import com.liferay.headless.commerce.admin.site.setting.model.v1_0.CatalogRuleDTO;
import com.liferay.headless.commerce.admin.site.setting.model.v1_0.CategoryDTO;
import com.liferay.headless.commerce.admin.site.setting.model.v1_0.MeasurementUnitDTO;
import com.liferay.headless.commerce.admin.site.setting.model.v1_0.TaxCategoryDTO;
import com.liferay.headless.commerce.admin.site.setting.model.v1_0.UserSegmentCriterionDTO;
import com.liferay.headless.commerce.admin.site.setting.model.v1_0.UserSegmentDTO;
import com.liferay.headless.commerce.admin.site.setting.model.v1_0.WarehouseDTO;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = DTOMapper.class)
public class DTOMapper {

	public CategoryDTO[] modelsToCategoryDTOArray(
		List<CPRuleAssetCategoryRel> cpRuleAssetCategoryRels) {

		if (cpRuleAssetCategoryRels == null) {
			return null;
		}

		List<CategoryDTO> categories = new ArrayList<>();

		for (CPRuleAssetCategoryRel cpRuleAssetCategoryRel :
				cpRuleAssetCategoryRels) {

			categories.add(modelToDTO(cpRuleAssetCategoryRel));
		}

		Stream<CategoryDTO> stream = categories.stream();

		return stream.toArray(CategoryDTO[]::new);
	}

	public UserSegmentCriterionDTO[] modelsToUserSegmentCriterionDTOArray(
		List<CommerceUserSegmentCriterion> commerceUserSegmentCriteria) {

		if (commerceUserSegmentCriteria == null) {
			return null;
		}

		List<UserSegmentCriterionDTO> userSegmentCriteria = new ArrayList<>();

		for (CommerceUserSegmentCriterion commerceUserSegmentCriterion :
				commerceUserSegmentCriteria) {

			userSegmentCriteria.add(modelToDTO(commerceUserSegmentCriterion));
		}

		Stream<UserSegmentCriterionDTO> stream = userSegmentCriteria.stream();

		return stream.toArray(UserSegmentCriterionDTO[]::new);
	}

	public UserSegmentDTO[] modelsToUserSegmentDTOArray(
		List<CPRuleUserSegmentRel> cpRuleUserSegmentRels) {

		if (cpRuleUserSegmentRels == null) {
			return null;
		}

		List<UserSegmentDTO> userSegments = new ArrayList<>();

		for (CPRuleUserSegmentRel cpRuleUserSegmentRel :
				cpRuleUserSegmentRels) {

			userSegments.add(modelToDTO(cpRuleUserSegmentRel));
		}

		Stream<UserSegmentDTO> stream = userSegments.stream();

		return stream.toArray(UserSegmentDTO[]::new);
	}

	public AvailabilityEstimateDTO modelToDTO(
		CommerceAvailabilityEstimate commerceAvailabilityEstimate) {

		AvailabilityEstimateDTO availabilityEstimateDTO =
			new AvailabilityEstimateDTO();

		if (commerceAvailabilityEstimate == null) {
			return availabilityEstimateDTO;
		}

		availabilityEstimateDTO.setId(
			commerceAvailabilityEstimate.getCommerceAvailabilityEstimateId());
		availabilityEstimateDTO.setGroupId(
			commerceAvailabilityEstimate.getGroupId());
		availabilityEstimateDTO.setPriority(
			commerceAvailabilityEstimate.getPriority());
		availabilityEstimateDTO.setTitle(
			LanguageUtils.getLanguageIdMap(
				commerceAvailabilityEstimate.getTitleMap()));

		return availabilityEstimateDTO;
	}

	public UserSegmentCriterionDTO modelToDTO(
		CommerceUserSegmentCriterion commerceUserSegmentCriterion) {

		UserSegmentCriterionDTO userSegmentCriterionDTO =
			new UserSegmentCriterionDTO();

		if (commerceUserSegmentCriterion == null) {
			return userSegmentCriterionDTO;
		}

		userSegmentCriterionDTO.setCommerceUserSegmentEntryId(
			commerceUserSegmentCriterion.getCommerceUserSegmentEntryId());
		userSegmentCriterionDTO.setId(
			commerceUserSegmentCriterion.getCommerceUserSegmentCriterionId());
		userSegmentCriterionDTO.setPriority(
			commerceUserSegmentCriterion.getPriority());
		userSegmentCriterionDTO.setType(commerceUserSegmentCriterion.getType());
		userSegmentCriterionDTO.setTypeSettings(
			commerceUserSegmentCriterion.getTypeSettings());

		return userSegmentCriterionDTO;
	}

	public UserSegmentDTO modelToDTO(
		CommerceUserSegmentEntry commerceUserSegmentEntry) {

		UserSegmentDTO userSegmentDTO = new UserSegmentDTO();

		if (commerceUserSegmentEntry == null) {
			return userSegmentDTO;
		}

		userSegmentDTO.setActive(commerceUserSegmentEntry.isActive());
		userSegmentDTO.setCriteria(
			modelsToUserSegmentCriterionDTOArray(
				commerceUserSegmentEntry.getCommerceUserSegmentCriteria()));
		userSegmentDTO.setGroupId(commerceUserSegmentEntry.getGroupId());
		userSegmentDTO.setId(
			commerceUserSegmentEntry.getCommerceUserSegmentEntryId());
		userSegmentDTO.setKey(commerceUserSegmentEntry.getKey());
		userSegmentDTO.setName(
			LanguageUtils.getLanguageIdMap(
				commerceUserSegmentEntry.getNameMap()));
		userSegmentDTO.setPriority(commerceUserSegmentEntry.getPriority());
		userSegmentDTO.setSystem(commerceUserSegmentEntry.isSystem());

		return userSegmentDTO;
	}

	public WarehouseDTO modelToDTO(CommerceWarehouse commerceWarehouse) {
		WarehouseDTO warehouseDTO = new WarehouseDTO();

		if (commerceWarehouse == null) {
			return warehouseDTO;
		}

		warehouseDTO.setActive(commerceWarehouse.isActive());
		warehouseDTO.setCity(commerceWarehouse.getCity());
		warehouseDTO.setCommerceCountryId(
			commerceWarehouse.getCommerceCountryId());
		warehouseDTO.setCommerceRegionId(
			commerceWarehouse.getCommerceRegionId());
		warehouseDTO.setDescription(commerceWarehouse.getDescription());
		warehouseDTO.setGroupId(commerceWarehouse.getGroupId());
		warehouseDTO.setId(commerceWarehouse.getCommerceWarehouseId());
		warehouseDTO.setLatitude(commerceWarehouse.getLatitude());
		warehouseDTO.setLongitude(commerceWarehouse.getLongitude());
		warehouseDTO.setName(commerceWarehouse.getName());
		warehouseDTO.setPrimary(commerceWarehouse.isPrimary());
		warehouseDTO.setStreet1(commerceWarehouse.getStreet1());
		warehouseDTO.setStreet2(commerceWarehouse.getStreet2());
		warehouseDTO.setStreet3(commerceWarehouse.getStreet3());
		warehouseDTO.setZip(commerceWarehouse.getZip());

		return warehouseDTO;
	}

	public MeasurementUnitDTO modelToDTO(CPMeasurementUnit cpMeasurementUnit) {
		MeasurementUnitDTO measurementUnitDTO = new MeasurementUnitDTO();

		if (cpMeasurementUnit == null) {
			return measurementUnitDTO;
		}

		measurementUnitDTO.setGroupId(cpMeasurementUnit.getGroupId());
		measurementUnitDTO.setId(cpMeasurementUnit.getCPMeasurementUnitId());
		measurementUnitDTO.setKey(cpMeasurementUnit.getKey());
		measurementUnitDTO.setName(
			LanguageUtils.getLanguageIdMap(cpMeasurementUnit.getNameMap()));
		measurementUnitDTO.setPrimary(cpMeasurementUnit.isPrimary());
		measurementUnitDTO.setPriority(cpMeasurementUnit.getPriority());
		measurementUnitDTO.setRate(cpMeasurementUnit.getRate());
		measurementUnitDTO.setType(cpMeasurementUnit.getType());

		return measurementUnitDTO;
	}

	public CatalogRuleDTO modelToDTO(CPRule cpRule) {
		CatalogRuleDTO catalogRuleDTO = new CatalogRuleDTO();

		if (cpRule == null) {
			return catalogRuleDTO;
		}

		catalogRuleDTO.setActive(cpRule.isActive());
		catalogRuleDTO.setCategories(
			modelsToCategoryDTOArray(cpRule.getCPRuleAssetCategoryRels()));
		catalogRuleDTO.setGroupId(catalogRuleDTO.getGroupId());
		catalogRuleDTO.setId(cpRule.getCPRuleId());
		catalogRuleDTO.setName(cpRule.getName());
		catalogRuleDTO.setType(cpRule.getType());
		catalogRuleDTO.setTypeSettings(cpRule.getTypeSettingsProperties());
		catalogRuleDTO.setUserSegments(
			modelsToUserSegmentDTOArray(cpRule.getCPRuleUserSegmentRels()));

		return catalogRuleDTO;
	}

	public CategoryDTO modelToDTO(
		CPRuleAssetCategoryRel cpRuleAssetCategoryRel) {

		CategoryDTO categoryDTO = new CategoryDTO();

		if (cpRuleAssetCategoryRel == null) {
			return categoryDTO;
		}

		try {
			AssetCategory assetCategory =
				cpRuleAssetCategoryRel.getAssetCategory();

			categoryDTO.setExternalReferenceCode(
				assetCategory.getExternalReferenceCode());
			categoryDTO.setGroupId(assetCategory.getGroupId());
			categoryDTO.setId(assetCategory.getCategoryId());
			categoryDTO.setName(assetCategory.getName());

			AssetVocabulary assetVocabulary =
				_assetVocabularyLocalService.getAssetVocabulary(
					assetCategory.getVocabularyId());

			categoryDTO.setVocabulary(assetVocabulary.getName());
		}
		catch (Exception e) {
			_log.error("Cannot instantiate CategoryDTO ", e);

			throw new RuntimeException(e);
		}

		return categoryDTO;
	}

	public UserSegmentDTO modelToDTO(
		CPRuleUserSegmentRel cpRuleUserSegmentRel) {

		try {
			return modelToDTO(
				cpRuleUserSegmentRel.getCommerceUserSegmentEntry());
		}
		catch (Exception e) {
			_log.error("Cannot instantiate UserSegmentDTO ", e);

			throw new RuntimeException(e);
		}
	}

	public TaxCategoryDTO modelToDTO(CPTaxCategory cpTaxCategory) {
		TaxCategoryDTO taxCategoryDTO = new TaxCategoryDTO();

		if (cpTaxCategory == null) {
			return taxCategoryDTO;
		}

		taxCategoryDTO.setDescription(
			LanguageUtils.getLanguageIdMap(cpTaxCategory.getDescriptionMap()));
		taxCategoryDTO.setGroupId(cpTaxCategory.getGroupId());
		taxCategoryDTO.setId(cpTaxCategory.getCPTaxCategoryId());
		taxCategoryDTO.setName(
			LanguageUtils.getLanguageIdMap(cpTaxCategory.getNameMap()));

		return taxCategoryDTO;
	}

	private static final Log _log = LogFactoryUtil.getLog(DTOMapper.class);

	@Reference
	private AssetVocabularyLocalService _assetVocabularyLocalService;

}