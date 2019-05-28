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

import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.commerce.model.CommerceAvailabilityEstimate;
import com.liferay.commerce.model.CommerceWarehouse;
import com.liferay.commerce.product.model.CPMeasurementUnit;
import com.liferay.commerce.product.model.CPTaxCategory;
import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.AvailabilityEstimate;
import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.MeasurementUnit;
import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.TaxCategory;
import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.Warehouse;
import com.liferay.headless.commerce.core.util.LanguageUtils;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 * @author Zoltán Takács
 */
@Component(immediate = true, service = DTOMapper.class)
public class DTOMapper {

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

	@Reference
	private AssetVocabularyLocalService _assetVocabularyLocalService;

}