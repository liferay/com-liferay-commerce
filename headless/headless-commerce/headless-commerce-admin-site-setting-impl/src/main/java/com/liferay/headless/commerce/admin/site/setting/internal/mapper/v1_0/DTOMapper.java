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
import com.liferay.commerce.product.model.CPMeasurementUnit;
import com.liferay.commerce.product.model.CPRule;
import com.liferay.commerce.product.model.CPRuleAssetCategoryRel;
import com.liferay.commerce.product.model.CPRuleUserSegmentRel;
import com.liferay.commerce.product.model.CPTaxCategory;
import com.liferay.commerce.user.segment.model.CommerceUserSegmentCriterion;
import com.liferay.commerce.user.segment.model.CommerceUserSegmentEntry;
import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.AvailabilityEstimate;
import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.CatalogRule;
import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.Category;
import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.MeasurementUnit;
import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.TaxCategory;
import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.UserSegment;
import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.UserSegmentCriterion;
import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.Warehouse;
import com.liferay.headless.commerce.core.util.LanguageUtils;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 * @author Zoltán Takács
 */
@Component(immediate = true, service = DTOMapper.class)
public class DTOMapper {

	public Category[] modelsToCategoryArray(
		List<CPRuleAssetCategoryRel> cpRuleAssetCategoryRels) {

		if (cpRuleAssetCategoryRels == null) {
			return null;
		}

		List<Category> categories = new ArrayList<>();

		for (CPRuleAssetCategoryRel cpRuleAssetCategoryRel :
				cpRuleAssetCategoryRels) {

			categories.add(modelToDTO(cpRuleAssetCategoryRel));
		}

		Stream<Category> stream = categories.stream();

		return stream.toArray(Category[]::new);
	}

	public UserSegment[] modelsToUserSegmentArray(
		List<CPRuleUserSegmentRel> cpRuleUserSegmentRels) {

		if (cpRuleUserSegmentRels == null) {
			return null;
		}

		List<UserSegment> userSegments = new ArrayList<>();

		for (CPRuleUserSegmentRel cpRuleUserSegmentRel :
				cpRuleUserSegmentRels) {

			userSegments.add(modelToDTO(cpRuleUserSegmentRel));
		}

		Stream<UserSegment> stream = userSegments.stream();

		return stream.toArray(UserSegment[]::new);
	}

	public UserSegmentCriterion[] modelsToUserSegmentCriterionArray(
		List<CommerceUserSegmentCriterion> commerceUserSegmentCriteria) {

		if (commerceUserSegmentCriteria == null) {
			return null;
		}

		List<UserSegmentCriterion> userSegmentCriteria = new ArrayList<>();

		for (CommerceUserSegmentCriterion commerceUserSegmentCriterion :
				commerceUserSegmentCriteria) {

			userSegmentCriteria.add(modelToDTO(commerceUserSegmentCriterion));
		}

		Stream<UserSegmentCriterion> stream = userSegmentCriteria.stream();

		return stream.toArray(UserSegmentCriterion[]::new);
	}

	public AvailabilityEstimate modelToDTO(
		CommerceAvailabilityEstimate commerceAvailabilityEstimate) {

		AvailabilityEstimate availabilityEstimate = new AvailabilityEstimate();

		if (commerceAvailabilityEstimate == null) {
			return availabilityEstimate;
		}

		availabilityEstimate.setId(
			commerceAvailabilityEstimate.getCommerceAvailabilityEstimateId());
		availabilityEstimate.setGroupId(
			commerceAvailabilityEstimate.getGroupId());
		availabilityEstimate.setPriority(
			commerceAvailabilityEstimate.getPriority());
		availabilityEstimate.setTitle(
			LanguageUtils.getLanguageIdMap(
				commerceAvailabilityEstimate.getTitleMap()));

		return availabilityEstimate;
	}

	public UserSegmentCriterion modelToDTO(
		CommerceUserSegmentCriterion commerceUserSegmentCriterion) {

		UserSegmentCriterion userSegmentCriterion = new UserSegmentCriterion();

		if (commerceUserSegmentCriterion == null) {
			return userSegmentCriterion;
		}

		userSegmentCriterion.setCommerceUserSegmentEntryId(
			commerceUserSegmentCriterion.getCommerceUserSegmentEntryId());
		userSegmentCriterion.setId(
			commerceUserSegmentCriterion.getCommerceUserSegmentCriterionId());
		userSegmentCriterion.setPriority(
			commerceUserSegmentCriterion.getPriority());
		userSegmentCriterion.setType(commerceUserSegmentCriterion.getType());
		userSegmentCriterion.setTypeSettings(
			commerceUserSegmentCriterion.getTypeSettings());

		return userSegmentCriterion;
	}

	public UserSegment modelToDTO(
		CommerceUserSegmentEntry commerceUserSegmentEntry) {

		UserSegment userSegment = new UserSegment();

		if (commerceUserSegmentEntry == null) {
			return userSegment;
		}

		userSegment.setActive(commerceUserSegmentEntry.isActive());
		userSegment.setCriteria(
			modelsToUserSegmentCriterionArray(
				commerceUserSegmentEntry.getCommerceUserSegmentCriteria()));
		userSegment.setGroupId(commerceUserSegmentEntry.getGroupId());
		userSegment.setId(
			commerceUserSegmentEntry.getCommerceUserSegmentEntryId());
		userSegment.setKey(commerceUserSegmentEntry.getKey());
		userSegment.setName(
			LanguageUtils.getLanguageIdMap(
				commerceUserSegmentEntry.getNameMap()));
		userSegment.setPriority(commerceUserSegmentEntry.getPriority());
		userSegment.setSystem(commerceUserSegmentEntry.isSystem());

		return userSegment;
	}

	public Warehouse modelToDTO(CommerceWarehouse commerceWarehouse) {
		Warehouse warehouse = new Warehouse();

		if (commerceWarehouse == null) {
			return warehouse;
		}

		warehouse.setActive(commerceWarehouse.isActive());
		warehouse.setCity(commerceWarehouse.getCity());
		warehouse.setCommerceCountryId(
			commerceWarehouse.getCommerceCountryId());
		warehouse.setCommerceRegionId(commerceWarehouse.getCommerceRegionId());
		warehouse.setDescription(commerceWarehouse.getDescription());
		warehouse.setGroupId(commerceWarehouse.getGroupId());
		warehouse.setId(commerceWarehouse.getCommerceWarehouseId());
		warehouse.setLatitude(commerceWarehouse.getLatitude());
		warehouse.setLongitude(commerceWarehouse.getLongitude());
		warehouse.setName(commerceWarehouse.getName());
		warehouse.setPrimary(commerceWarehouse.isPrimary());
		warehouse.setStreet1(commerceWarehouse.getStreet1());
		warehouse.setStreet2(commerceWarehouse.getStreet2());
		warehouse.setStreet3(commerceWarehouse.getStreet3());
		warehouse.setZip(commerceWarehouse.getZip());

		return warehouse;
	}

	public MeasurementUnit modelToDTO(CPMeasurementUnit cpMeasurementUnit) {
		MeasurementUnit measurementUnit = new MeasurementUnit();

		if (cpMeasurementUnit == null) {
			return measurementUnit;
		}

		measurementUnit.setGroupId(cpMeasurementUnit.getGroupId());
		measurementUnit.setId(cpMeasurementUnit.getCPMeasurementUnitId());
		measurementUnit.setKey(cpMeasurementUnit.getKey());
		measurementUnit.setName(
			LanguageUtils.getLanguageIdMap(cpMeasurementUnit.getNameMap()));
		measurementUnit.setPrimary(cpMeasurementUnit.isPrimary());
		measurementUnit.setPriority(cpMeasurementUnit.getPriority());
		measurementUnit.setRate(cpMeasurementUnit.getRate());
		measurementUnit.setType(cpMeasurementUnit.getType());

		return measurementUnit;
	}

	public CatalogRule modelToDTO(CPRule cpRule) {
		CatalogRule catalogRule = new CatalogRule();

		if (cpRule == null) {
			return catalogRule;
		}

		catalogRule.setActive(cpRule.isActive());
		catalogRule.setCategories(
			modelsToCategoryArray(cpRule.getCPRuleAssetCategoryRels()));
		catalogRule.setGroupId(catalogRule.getGroupId());
		catalogRule.setId(cpRule.getCPRuleId());
		catalogRule.setName(cpRule.getName());
		catalogRule.setType(cpRule.getType());
		catalogRule.setTypeSettings(cpRule.getTypeSettingsProperties());
		catalogRule.setUserSegments(
			modelsToUserSegmentArray(cpRule.getCPRuleUserSegmentRels()));

		return catalogRule;
	}

	public Category modelToDTO(CPRuleAssetCategoryRel cpRuleAssetCategoryRel) {
		Category category = new Category();

		if (cpRuleAssetCategoryRel == null) {
			return category;
		}

		try {
			AssetCategory assetCategory =
				cpRuleAssetCategoryRel.getAssetCategory();

			category.setExternalReferenceCode(
				assetCategory.getExternalReferenceCode());
			category.setGroupId(assetCategory.getGroupId());
			category.setId(assetCategory.getCategoryId());
			category.setName(assetCategory.getName());

			AssetVocabulary assetVocabulary =
				_assetVocabularyLocalService.getAssetVocabulary(
					assetCategory.getVocabularyId());

			category.setVocabulary(assetVocabulary.getName());
		}
		catch (Exception e) {
			_log.error("Cannot instantiate Category ", e);

			throw new RuntimeException(e);
		}

		return category;
	}

	public UserSegment modelToDTO(CPRuleUserSegmentRel cpRuleUserSegmentRel) {
		try {
			return modelToDTO(
				cpRuleUserSegmentRel.getCommerceUserSegmentEntry());
		}
		catch (Exception e) {
			_log.error("Cannot instantiate UserSegment ", e);

			throw new RuntimeException(e);
		}
	}

	public TaxCategory modelToDTO(CPTaxCategory cpTaxCategory) {
		TaxCategory taxCategory = new TaxCategory();

		if (cpTaxCategory == null) {
			return taxCategory;
		}

		taxCategory.setDescription(
			LanguageUtils.getLanguageIdMap(cpTaxCategory.getDescriptionMap()));
		taxCategory.setGroupId(cpTaxCategory.getGroupId());
		taxCategory.setId(cpTaxCategory.getCPTaxCategoryId());
		taxCategory.setName(
			LanguageUtils.getLanguageIdMap(cpTaxCategory.getNameMap()));

		return taxCategory;
	}

	private static final Log _log = LogFactoryUtil.getLog(DTOMapper.class);

	@Reference
	private AssetVocabularyLocalService _assetVocabularyLocalService;

}