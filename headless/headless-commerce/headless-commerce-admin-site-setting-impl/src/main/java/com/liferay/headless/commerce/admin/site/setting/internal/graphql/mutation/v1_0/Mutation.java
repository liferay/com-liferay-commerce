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

package com.liferay.headless.commerce.admin.site.setting.internal.graphql.mutation.v1_0;

import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.AvailabilityEstimate;
import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.CatalogRule;
import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.MeasurementUnit;
import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.TaxCategory;
import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.UserSegment;
import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.UserSegmentCriterion;
import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.Warehouse;
import com.liferay.headless.commerce.admin.site.setting.resource.v1_0.AvailabilityEstimateResource;
import com.liferay.headless.commerce.admin.site.setting.resource.v1_0.CatalogRuleResource;
import com.liferay.headless.commerce.admin.site.setting.resource.v1_0.MeasurementUnitResource;
import com.liferay.headless.commerce.admin.site.setting.resource.v1_0.TaxCategoryResource;
import com.liferay.headless.commerce.admin.site.setting.resource.v1_0.UserSegmentResource;
import com.liferay.headless.commerce.admin.site.setting.resource.v1_0.WarehouseResource;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLInvokeDetached;
import graphql.annotations.annotationTypes.GraphQLName;

import javax.annotation.Generated;

import javax.ws.rs.core.Response;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author Zoltán Takács
 * @generated
 */
@Generated("")
public class Mutation {

	public static void setAvailabilityEstimateResourceComponentServiceObjects(
		ComponentServiceObjects<AvailabilityEstimateResource>
			availabilityEstimateResourceComponentServiceObjects) {

		_availabilityEstimateResourceComponentServiceObjects =
			availabilityEstimateResourceComponentServiceObjects;
	}

	public static void setCatalogRuleResourceComponentServiceObjects(
		ComponentServiceObjects<CatalogRuleResource>
			catalogRuleResourceComponentServiceObjects) {

		_catalogRuleResourceComponentServiceObjects =
			catalogRuleResourceComponentServiceObjects;
	}

	public static void setMeasurementUnitResourceComponentServiceObjects(
		ComponentServiceObjects<MeasurementUnitResource>
			measurementUnitResourceComponentServiceObjects) {

		_measurementUnitResourceComponentServiceObjects =
			measurementUnitResourceComponentServiceObjects;
	}

	public static void setTaxCategoryResourceComponentServiceObjects(
		ComponentServiceObjects<TaxCategoryResource>
			taxCategoryResourceComponentServiceObjects) {

		_taxCategoryResourceComponentServiceObjects =
			taxCategoryResourceComponentServiceObjects;
	}

	public static void setUserSegmentResourceComponentServiceObjects(
		ComponentServiceObjects<UserSegmentResource>
			userSegmentResourceComponentServiceObjects) {

		_userSegmentResourceComponentServiceObjects =
			userSegmentResourceComponentServiceObjects;
	}

	public static void setWarehouseResourceComponentServiceObjects(
		ComponentServiceObjects<WarehouseResource>
			warehouseResourceComponentServiceObjects) {

		_warehouseResourceComponentServiceObjects =
			warehouseResourceComponentServiceObjects;
	}

	@GraphQLInvokeDetached
	public Response deleteAvailabilityEstimate(@GraphQLName("id") Long id)
		throws Exception {

		return _applyComponentServiceObjects(
			_availabilityEstimateResourceComponentServiceObjects,
			this::_populateResourceContext,
			availabilityEstimateResource ->
				availabilityEstimateResource.deleteAvailabilityEstimate(id));
	}

	@GraphQLInvokeDetached
	public Response updateAvailabilityEstimate(
			@GraphQLName("id") Long id,
			@GraphQLName("AvailabilityEstimate") AvailabilityEstimate
				availabilityEstimate)
		throws Exception {

		return _applyComponentServiceObjects(
			_availabilityEstimateResourceComponentServiceObjects,
			this::_populateResourceContext,
			availabilityEstimateResource ->
				availabilityEstimateResource.updateAvailabilityEstimate(
					id, availabilityEstimate));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public AvailabilityEstimate upsertAvailabilityEstimate(
			@GraphQLName("groupId") Long groupId,
			@GraphQLName("AvailabilityEstimate") AvailabilityEstimate
				availabilityEstimate)
		throws Exception {

		return _applyComponentServiceObjects(
			_availabilityEstimateResourceComponentServiceObjects,
			this::_populateResourceContext,
			availabilityEstimateResource ->
				availabilityEstimateResource.upsertAvailabilityEstimate(
					groupId, availabilityEstimate));
	}

	@GraphQLInvokeDetached
	public Response deleteCatalogRule(@GraphQLName("id") Long id)
		throws Exception {

		return _applyComponentServiceObjects(
			_catalogRuleResourceComponentServiceObjects,
			this::_populateResourceContext,
			catalogRuleResource -> catalogRuleResource.deleteCatalogRule(id));
	}

	@GraphQLInvokeDetached
	public Response updateCatalogRule(
			@GraphQLName("id") Long id,
			@GraphQLName("CatalogRule") CatalogRule catalogRule)
		throws Exception {

		return _applyComponentServiceObjects(
			_catalogRuleResourceComponentServiceObjects,
			this::_populateResourceContext,
			catalogRuleResource -> catalogRuleResource.updateCatalogRule(
				id, catalogRule));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public CatalogRule upsertCatalogRule(
			@GraphQLName("groupId") Long groupId,
			@GraphQLName("CatalogRule") CatalogRule catalogRule)
		throws Exception {

		return _applyComponentServiceObjects(
			_catalogRuleResourceComponentServiceObjects,
			this::_populateResourceContext,
			catalogRuleResource -> catalogRuleResource.upsertCatalogRule(
				groupId, catalogRule));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public MeasurementUnit upsertMeasurementUnit(
			@GraphQLName("groupId") Long groupId,
			@GraphQLName("MeasurementUnit") MeasurementUnit measurementUnit)
		throws Exception {

		return _applyComponentServiceObjects(
			_measurementUnitResourceComponentServiceObjects,
			this::_populateResourceContext,
			measurementUnitResource ->
				measurementUnitResource.upsertMeasurementUnit(
					groupId, measurementUnit));
	}

	@GraphQLInvokeDetached
	public Response deleteMeasurementUnit(@GraphQLName("id") Long id)
		throws Exception {

		return _applyComponentServiceObjects(
			_measurementUnitResourceComponentServiceObjects,
			this::_populateResourceContext,
			measurementUnitResource ->
				measurementUnitResource.deleteMeasurementUnit(id));
	}

	@GraphQLInvokeDetached
	public Response updateMeasurementUnit(
			@GraphQLName("id") Long id,
			@GraphQLName("MeasurementUnit") MeasurementUnit measurementUnit)
		throws Exception {

		return _applyComponentServiceObjects(
			_measurementUnitResourceComponentServiceObjects,
			this::_populateResourceContext,
			measurementUnitResource ->
				measurementUnitResource.updateMeasurementUnit(
					id, measurementUnit));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public TaxCategory upsertTaxCategory(
			@GraphQLName("groupId") Long groupId,
			@GraphQLName("TaxCategory") TaxCategory taxCategory)
		throws Exception {

		return _applyComponentServiceObjects(
			_taxCategoryResourceComponentServiceObjects,
			this::_populateResourceContext,
			taxCategoryResource -> taxCategoryResource.upsertTaxCategory(
				groupId, taxCategory));
	}

	@GraphQLInvokeDetached
	public Response deleteTaxCategory(@GraphQLName("id") Long id)
		throws Exception {

		return _applyComponentServiceObjects(
			_taxCategoryResourceComponentServiceObjects,
			this::_populateResourceContext,
			taxCategoryResource -> taxCategoryResource.deleteTaxCategory(id));
	}

	@GraphQLInvokeDetached
	public Response updateTaxCategory(
			@GraphQLName("id") Long id,
			@GraphQLName("TaxCategory") TaxCategory taxCategory)
		throws Exception {

		return _applyComponentServiceObjects(
			_taxCategoryResourceComponentServiceObjects,
			this::_populateResourceContext,
			taxCategoryResource -> taxCategoryResource.updateTaxCategory(
				id, taxCategory));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public UserSegment upsertUserSegment(
			@GraphQLName("groupId") Long groupId,
			@GraphQLName("UserSegment") UserSegment userSegment)
		throws Exception {

		return _applyComponentServiceObjects(
			_userSegmentResourceComponentServiceObjects,
			this::_populateResourceContext,
			userSegmentResource -> userSegmentResource.upsertUserSegment(
				groupId, userSegment));
	}

	@GraphQLInvokeDetached
	public Response deleteUserSegmentCriterion(
			@GraphQLName("criterionId") Long criterionId,
			@GraphQLName("id") Long id)
		throws Exception {

		return _applyComponentServiceObjects(
			_userSegmentResourceComponentServiceObjects,
			this::_populateResourceContext,
			userSegmentResource ->
				userSegmentResource.deleteUserSegmentCriterion(
					criterionId, id));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public UserSegmentCriterion updateUserSegmentCriterion(
			@GraphQLName("criterionId") Long criterionId,
			@GraphQLName("id") Long id,
			@GraphQLName("UserSegmentCriterion") UserSegmentCriterion
				userSegmentCriterion)
		throws Exception {

		return _applyComponentServiceObjects(
			_userSegmentResourceComponentServiceObjects,
			this::_populateResourceContext,
			userSegmentResource ->
				userSegmentResource.updateUserSegmentCriterion(
					criterionId, id, userSegmentCriterion));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public UserSegmentCriterion upsertUserSegmentCriterion(
			@GraphQLName("id") Long id,
			@GraphQLName("UserSegmentCriterion") UserSegmentCriterion
				userSegmentCriterion)
		throws Exception {

		return _applyComponentServiceObjects(
			_userSegmentResourceComponentServiceObjects,
			this::_populateResourceContext,
			userSegmentResource ->
				userSegmentResource.upsertUserSegmentCriterion(
					id, userSegmentCriterion));
	}

	@GraphQLInvokeDetached
	public Response deleteUserSegment(@GraphQLName("id") Long id)
		throws Exception {

		return _applyComponentServiceObjects(
			_userSegmentResourceComponentServiceObjects,
			this::_populateResourceContext,
			userSegmentResource -> userSegmentResource.deleteUserSegment(id));
	}

	@GraphQLInvokeDetached
	public Response updateUserSegment(
			@GraphQLName("id") Long id,
			@GraphQLName("UserSegment") UserSegment userSegment)
		throws Exception {

		return _applyComponentServiceObjects(
			_userSegmentResourceComponentServiceObjects,
			this::_populateResourceContext,
			userSegmentResource -> userSegmentResource.updateUserSegment(
				id, userSegment));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Warehouse upsertWarehouse(
			@GraphQLName("groupId") Long groupId,
			@GraphQLName("Warehouse") Warehouse warehouse)
		throws Exception {

		return _applyComponentServiceObjects(
			_warehouseResourceComponentServiceObjects,
			this::_populateResourceContext,
			warehouseResource -> warehouseResource.upsertWarehouse(
				groupId, warehouse));
	}

	@GraphQLInvokeDetached
	public Response deleteWarehouse(@GraphQLName("id") Long id)
		throws Exception {

		return _applyComponentServiceObjects(
			_warehouseResourceComponentServiceObjects,
			this::_populateResourceContext,
			warehouseResource -> warehouseResource.deleteWarehouse(id));
	}

	@GraphQLInvokeDetached
	public Response updateWarehouse(
			@GraphQLName("id") Long id,
			@GraphQLName("Warehouse") Warehouse warehouse)
		throws Exception {

		return _applyComponentServiceObjects(
			_warehouseResourceComponentServiceObjects,
			this::_populateResourceContext,
			warehouseResource -> warehouseResource.updateWarehouse(
				id, warehouse));
	}

	private <T, R, E1 extends Throwable, E2 extends Throwable> R
			_applyComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeFunction<T, R, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			return unsafeFunction.apply(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private <T, E1 extends Throwable, E2 extends Throwable> void
			_applyVoidComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeConsumer<T, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			unsafeFunction.accept(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private void _populateResourceContext(
			AvailabilityEstimateResource availabilityEstimateResource)
		throws Exception {

		availabilityEstimateResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(
			CatalogRuleResource catalogRuleResource)
		throws Exception {

		catalogRuleResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(
			MeasurementUnitResource measurementUnitResource)
		throws Exception {

		measurementUnitResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(
			TaxCategoryResource taxCategoryResource)
		throws Exception {

		taxCategoryResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(
			UserSegmentResource userSegmentResource)
		throws Exception {

		userSegmentResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(WarehouseResource warehouseResource)
		throws Exception {

		warehouseResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private static ComponentServiceObjects<AvailabilityEstimateResource>
		_availabilityEstimateResourceComponentServiceObjects;
	private static ComponentServiceObjects<CatalogRuleResource>
		_catalogRuleResourceComponentServiceObjects;
	private static ComponentServiceObjects<MeasurementUnitResource>
		_measurementUnitResourceComponentServiceObjects;
	private static ComponentServiceObjects<TaxCategoryResource>
		_taxCategoryResourceComponentServiceObjects;
	private static ComponentServiceObjects<UserSegmentResource>
		_userSegmentResourceComponentServiceObjects;
	private static ComponentServiceObjects<WarehouseResource>
		_warehouseResourceComponentServiceObjects;

}