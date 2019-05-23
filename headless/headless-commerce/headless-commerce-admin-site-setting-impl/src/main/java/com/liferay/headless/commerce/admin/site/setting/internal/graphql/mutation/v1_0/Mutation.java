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

import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.AccountGroup;
import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.AccountGroupCriterion;
import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.AvailabilityEstimate;
import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.MeasurementUnit;
import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.TaxCategory;
import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.Warehouse;
import com.liferay.headless.commerce.admin.site.setting.resource.v1_0.AccountGroupResource;
import com.liferay.headless.commerce.admin.site.setting.resource.v1_0.AvailabilityEstimateResource;
import com.liferay.headless.commerce.admin.site.setting.resource.v1_0.MeasurementUnitResource;
import com.liferay.headless.commerce.admin.site.setting.resource.v1_0.TaxCategoryResource;
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

	public static void setAccountGroupResourceComponentServiceObjects(
		ComponentServiceObjects<AccountGroupResource>
			accountGroupResourceComponentServiceObjects) {

		_accountGroupResourceComponentServiceObjects =
			accountGroupResourceComponentServiceObjects;
	}

	public static void setAvailabilityEstimateResourceComponentServiceObjects(
		ComponentServiceObjects<AvailabilityEstimateResource>
			availabilityEstimateResourceComponentServiceObjects) {

		_availabilityEstimateResourceComponentServiceObjects =
			availabilityEstimateResourceComponentServiceObjects;
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

	public static void setWarehouseResourceComponentServiceObjects(
		ComponentServiceObjects<WarehouseResource>
			warehouseResourceComponentServiceObjects) {

		_warehouseResourceComponentServiceObjects =
			warehouseResourceComponentServiceObjects;
	}

	@GraphQLInvokeDetached
	public Response deleteAccountGroupAccountGroupCriterion(
			@GraphQLName("criterionId") Long criterionId,
			@GraphQLName("id") Long id)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountGroupResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountGroupResource ->
				accountGroupResource.deleteAccountGroupAccountGroupCriterion(
					criterionId, id));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public AccountGroupCriterion postAccountGroupAccountGroupCriterion(
			@GraphQLName("criterionId") Long criterionId,
			@GraphQLName("id") Long id,
			@GraphQLName("accountGroupCriterion") AccountGroupCriterion
				accountGroupCriterion)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountGroupResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountGroupResource ->
				accountGroupResource.postAccountGroupAccountGroupCriterion(
					criterionId, id, accountGroupCriterion));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public AccountGroupCriterion postAccountGroupAccountGroupCriterion(
			@GraphQLName("id") Long id,
			@GraphQLName("accountGroupCriterion") AccountGroupCriterion
				accountGroupCriterion)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountGroupResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountGroupResource ->
				accountGroupResource.postAccountGroupAccountGroupCriterion(
					id, accountGroupCriterion));
	}

	@GraphQLInvokeDetached
	public Response deleteAccountGroup(@GraphQLName("id") Long id)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountGroupResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountGroupResource -> accountGroupResource.deleteAccountGroup(
				id));
	}

	@GraphQLInvokeDetached
	public Response putAccountGroup(
			@GraphQLName("id") Long id,
			@GraphQLName("accountGroup") AccountGroup accountGroup)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountGroupResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountGroupResource -> accountGroupResource.putAccountGroup(
				id, accountGroup));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public AccountGroup postCommerceAdminSiteSettingGroupAccountGroup(
			@GraphQLName("groupId") Long groupId,
			@GraphQLName("accountGroup") AccountGroup accountGroup)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountGroupResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountGroupResource ->
				accountGroupResource.
					postCommerceAdminSiteSettingGroupAccountGroup(
						groupId, accountGroup));
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
	public Response putAvailabilityEstimate(
			@GraphQLName("id") Long id,
			@GraphQLName("availabilityEstimate") AvailabilityEstimate
				availabilityEstimate)
		throws Exception {

		return _applyComponentServiceObjects(
			_availabilityEstimateResourceComponentServiceObjects,
			this::_populateResourceContext,
			availabilityEstimateResource ->
				availabilityEstimateResource.putAvailabilityEstimate(
					id, availabilityEstimate));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public AvailabilityEstimate
			postCommerceAdminSiteSettingGroupAvailabilityEstimate(
				@GraphQLName("groupId") Long groupId,
				@GraphQLName("availabilityEstimate") AvailabilityEstimate
					availabilityEstimate)
		throws Exception {

		return _applyComponentServiceObjects(
			_availabilityEstimateResourceComponentServiceObjects,
			this::_populateResourceContext,
			availabilityEstimateResource ->
				availabilityEstimateResource.
					postCommerceAdminSiteSettingGroupAvailabilityEstimate(
						groupId, availabilityEstimate));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public MeasurementUnit postCommerceAdminSiteSettingGroupMeasurementUnit(
			@GraphQLName("groupId") Long groupId,
			@GraphQLName("measurementUnit") MeasurementUnit measurementUnit)
		throws Exception {

		return _applyComponentServiceObjects(
			_measurementUnitResourceComponentServiceObjects,
			this::_populateResourceContext,
			measurementUnitResource ->
				measurementUnitResource.
					postCommerceAdminSiteSettingGroupMeasurementUnit(
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
	public Response putMeasurementUnit(
			@GraphQLName("id") Long id,
			@GraphQLName("measurementUnit") MeasurementUnit measurementUnit)
		throws Exception {

		return _applyComponentServiceObjects(
			_measurementUnitResourceComponentServiceObjects,
			this::_populateResourceContext,
			measurementUnitResource ->
				measurementUnitResource.putMeasurementUnit(
					id, measurementUnit));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public TaxCategory postCommerceAdminSiteSettingGroupTaxCategory(
			@GraphQLName("groupId") Long groupId,
			@GraphQLName("taxCategory") TaxCategory taxCategory)
		throws Exception {

		return _applyComponentServiceObjects(
			_taxCategoryResourceComponentServiceObjects,
			this::_populateResourceContext,
			taxCategoryResource ->
				taxCategoryResource.
					postCommerceAdminSiteSettingGroupTaxCategory(
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
	public Response putTaxCategory(
			@GraphQLName("id") Long id,
			@GraphQLName("taxCategory") TaxCategory taxCategory)
		throws Exception {

		return _applyComponentServiceObjects(
			_taxCategoryResourceComponentServiceObjects,
			this::_populateResourceContext,
			taxCategoryResource -> taxCategoryResource.putTaxCategory(
				id, taxCategory));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Warehouse postCommerceAdminSiteSettingGroupWarehouse(
			@GraphQLName("groupId") Long groupId,
			@GraphQLName("warehouse") Warehouse warehouse)
		throws Exception {

		return _applyComponentServiceObjects(
			_warehouseResourceComponentServiceObjects,
			this::_populateResourceContext,
			warehouseResource ->
				warehouseResource.postCommerceAdminSiteSettingGroupWarehouse(
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
	public Response putWarehouse(
			@GraphQLName("id") Long id,
			@GraphQLName("warehouse") Warehouse warehouse)
		throws Exception {

		return _applyComponentServiceObjects(
			_warehouseResourceComponentServiceObjects,
			this::_populateResourceContext,
			warehouseResource -> warehouseResource.putWarehouse(id, warehouse));
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
			AccountGroupResource accountGroupResource)
		throws Exception {

		accountGroupResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(
			AvailabilityEstimateResource availabilityEstimateResource)
		throws Exception {

		availabilityEstimateResource.setContextCompany(
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

	private void _populateResourceContext(WarehouseResource warehouseResource)
		throws Exception {

		warehouseResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private static ComponentServiceObjects<AccountGroupResource>
		_accountGroupResourceComponentServiceObjects;
	private static ComponentServiceObjects<AvailabilityEstimateResource>
		_availabilityEstimateResourceComponentServiceObjects;
	private static ComponentServiceObjects<MeasurementUnitResource>
		_measurementUnitResourceComponentServiceObjects;
	private static ComponentServiceObjects<TaxCategoryResource>
		_taxCategoryResourceComponentServiceObjects;
	private static ComponentServiceObjects<WarehouseResource>
		_warehouseResourceComponentServiceObjects;

}