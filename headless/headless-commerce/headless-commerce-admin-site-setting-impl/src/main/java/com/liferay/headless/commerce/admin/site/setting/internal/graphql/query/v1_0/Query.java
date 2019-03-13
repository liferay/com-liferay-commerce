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

package com.liferay.headless.commerce.admin.site.setting.internal.graphql.query.v1_0;

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
public class Query {

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

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getAvailabilityEstimate(@GraphQLName("id") Long id)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getAvailabilityEstimates(
			@GraphQLName("groupId") Long groupId)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getCatalogRule(@GraphQLName("id") Long id)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getCatalogRuleCategories(@GraphQLName("id") Long id)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getCatalogRuleUserSegments(@GraphQLName("id") Long id)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getCatalogRules(@GraphQLName("groupId") Long groupId)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getMeasurementUnit(@GraphQLName("id") Long id)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getMeasurementUnits(
			@GraphQLName("groupId") Long groupId,
			@GraphQLName("type") Integer type)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getUserSegment(@GraphQLName("id") Long id)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getUserSegmentCriterion(
			@GraphQLName("id") Long id,
			@GraphQLName("criterionId") Long criterionId)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getUserSegmentCriteria(@GraphQLName("id") Long id)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getUserSegments(@GraphQLName("groupId") Long groupId)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getTaxCategory(@GraphQLName("id") Long id)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getTaxCategories(@GraphQLName("groupId") Long groupId)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getWarehouse(@GraphQLName("id") Long id) throws Exception {
		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getWarehouses(
			@GraphQLName("groupId") Long groupId,
			@GraphQLName("active") Boolean active)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
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