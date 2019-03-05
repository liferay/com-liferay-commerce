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

package com.liferay.headless.commerce.admin.site.setting.internal.resource.v1_0;

import com.liferay.commerce.openapi.core.annotation.AsyncSupported;
import com.liferay.commerce.openapi.core.annotation.Nullable;
import com.liferay.commerce.openapi.core.annotation.Status;
import com.liferay.commerce.openapi.core.context.Async;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.headless.commerce.admin.site.setting.internal.resource.util.v1_0.AvailabilityEstimateHelper;
import com.liferay.headless.commerce.admin.site.setting.internal.resource.util.v1_0.CatalogRuleHelper;
import com.liferay.headless.commerce.admin.site.setting.internal.resource.util.v1_0.MeasurementUnitHelper;
import com.liferay.headless.commerce.admin.site.setting.internal.resource.util.v1_0.TaxCategoryHelper;
import com.liferay.headless.commerce.admin.site.setting.internal.resource.util.v1_0.UserSegmentHelper;
import com.liferay.headless.commerce.admin.site.setting.internal.resource.util.v1_0.WarehouseHelper;
import com.liferay.headless.commerce.admin.site.setting.model.v1_0.AvailabilityEstimateDTO;
import com.liferay.headless.commerce.admin.site.setting.model.v1_0.CatalogRuleDTO;
import com.liferay.headless.commerce.admin.site.setting.model.v1_0.MeasurementUnitDTO;
import com.liferay.headless.commerce.admin.site.setting.model.v1_0.TaxCategoryDTO;
import com.liferay.headless.commerce.admin.site.setting.model.v1_0.UserSegmentDTO;
import com.liferay.headless.commerce.admin.site.setting.model.v1_0.WarehouseDTO;
import com.liferay.headless.commerce.admin.site.setting.resource.v1_0.CommerceAdminSiteSettingResource;
import com.liferay.oauth2.provider.scope.RequiresScope;
import com.liferay.portal.kernel.model.User;

import javax.annotation.Generated;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_SELECT + "=(osgi.jaxrs.name=HeadlessCommerceAdminSiteSetting.Rest)",
		JaxrsWhiteboardConstants.JAX_RS_RESOURCE + "=true", "api.version=v1.0"
	},
	scope = ServiceScope.PROTOTYPE,
	service = CommerceAdminSiteSettingResource.class
)
@Generated(value = "OSGiRESTModuleGenerator")
public class CommerceAdminSiteSettingResourceImpl
	implements CommerceAdminSiteSettingResource {

	@Override
	@RequiresScope("HeadlessCommerceAdminSiteSetting.read")
	public CollectionDTO<AvailabilityEstimateDTO> getAvailabilityEstimates(
			 Long groupId, Pagination pagination)
		throws Exception {

		return _availabilityEstimateHelper.getAvailabilityEstimateDTOs(
			groupId, pagination);
	}

	@Override
	@RequiresScope("HeadlessCommerceAdminSiteSetting.read")
	public CollectionDTO<CatalogRuleDTO> getCatalogRules(
			 Long groupId, Pagination pagination)
		throws Exception {

		return _catalogRuleHelper.getCatalogRuleDTOs(groupId, pagination);
	}

	@Override
	@RequiresScope("HeadlessCommerceAdminSiteSetting.read")
	public CollectionDTO<MeasurementUnitDTO> getMeasurementUnits(
			 Long groupId, @Nullable Integer type, Pagination pagination)
		throws Exception {

		return _measurementUnitHelper.getMeasurementUnitDTOs(
			groupId, type, pagination);
	}

	@Override
	@RequiresScope("HeadlessCommerceAdminSiteSetting.read")
	public CollectionDTO<TaxCategoryDTO> getTaxCategories(
			 Long groupId, Pagination pagination)
		throws Exception {

		return _taxCategoryHelper.getTaxCategoryDTOs(groupId, pagination);
	}

	@Override
	@RequiresScope("HeadlessCommerceAdminSiteSetting.read")
	public CollectionDTO<UserSegmentDTO> getUserSegments(
			 Long groupId, Pagination pagination)
		throws Exception {

		return _userSegmentHelper.getUserSegmentDTOs(groupId, pagination);
	}

	@Override
	@RequiresScope("HeadlessCommerceAdminSiteSetting.read")
	public CollectionDTO<WarehouseDTO> getWarehouses(
			 Long groupId, @Nullable Boolean active, Pagination pagination)
		throws Exception {

		return _warehouseHelper.getWarehouseDTOs(groupId, active, pagination);
	}

	@AsyncSupported
	@Override
	@RequiresScope("HeadlessCommerceAdminSiteSetting.write")
	@Status(Response.Status.CREATED)
	public AvailabilityEstimateDTO upsertAvailabilityEstimate(
			 Long groupId, AvailabilityEstimateDTO availabilityEstimateDTO)
		throws Exception {

		if (_async.isEnabled()) {
			new Thread() {

				public void run() {

					// TODO

				}

			}.start();
			
			return null;
		}

		return _availabilityEstimateHelper.upsertAvailabilityEstimate(
			groupId, availabilityEstimateDTO, _user);
	}

	@AsyncSupported
	@Override
	@RequiresScope("HeadlessCommerceAdminSiteSetting.write")
	@Status(Response.Status.CREATED)
	public CatalogRuleDTO upsertCatalogRule(
			 Long groupId, CatalogRuleDTO catalogRuleDTO)
		throws Exception {

		if (_async.isEnabled()) {
			new Thread() {

				public void run() {

					// TODO

				}

			}.start();
			
			return null;
		}

		return _catalogRuleHelper.upsertCatalogRule(
			groupId, catalogRuleDTO, _user);
	}

	@AsyncSupported
	@Override
	@RequiresScope("HeadlessCommerceAdminSiteSetting.write")
	@Status(Response.Status.CREATED)
	public MeasurementUnitDTO upsertMeasurementUnit(
			 Long groupId, MeasurementUnitDTO measurementUnitDTO)
		throws Exception {

		if (_async.isEnabled()) {
			new Thread() {

				public void run() {

					// TODO

				}

			}.start();
			
			return null;
		}

		return _measurementUnitHelper.upsertMeasurementUnit(
			groupId, measurementUnitDTO, _user);
	}

	@AsyncSupported
	@Override
	@RequiresScope("HeadlessCommerceAdminSiteSetting.write")
	@Status(Response.Status.CREATED)
	public TaxCategoryDTO upsertTaxCategory(
			 Long groupId, TaxCategoryDTO taxCategoryDTO)
		throws Exception {

		if (_async.isEnabled()) {
			new Thread() {

				public void run() {

					// TODO

				}

			}.start();
			
			return null;
		}

		return _taxCategoryHelper.upsertTaxCategory(
			groupId, taxCategoryDTO, _user);
	}

	@AsyncSupported
	@Override
	@RequiresScope("HeadlessCommerceAdminSiteSetting.write")
	@Status(Response.Status.CREATED)
	public UserSegmentDTO upsertUserSegment(
			 Long groupId, UserSegmentDTO userSegmentDTO)
		throws Exception {

		if (_async.isEnabled()) {
			new Thread() {

				public void run() {

					// TODO

				}

			}.start();
			
			return null;
		}

		return _userSegmentHelper.upsertUserSegment(
			groupId, userSegmentDTO, _user);
	}

	@AsyncSupported
	@Override
	@RequiresScope("HeadlessCommerceAdminSiteSetting.write")
	@Status(Response.Status.CREATED)
	public WarehouseDTO upsertWarehouse(
			 Long groupId, WarehouseDTO warehouseDTO)
		throws Exception {

		if (_async.isEnabled()) {
			new Thread() {

				public void run() {

					// TODO

				}

			}.start();
			
			return null;
		}

		return _warehouseHelper.upsertWarehouse(groupId, warehouseDTO, _user);
	}

	@Context
	private Async _async;

	@Reference
	private AvailabilityEstimateHelper _availabilityEstimateHelper;

	@Reference
	private CatalogRuleHelper _catalogRuleHelper;

	@Reference
	private MeasurementUnitHelper _measurementUnitHelper;


	@Reference
	private TaxCategoryHelper _taxCategoryHelper;

	@Context
	private User _user;

	@Reference
	private UserSegmentHelper _userSegmentHelper;

	@Reference
	private WarehouseHelper _warehouseHelper;

}