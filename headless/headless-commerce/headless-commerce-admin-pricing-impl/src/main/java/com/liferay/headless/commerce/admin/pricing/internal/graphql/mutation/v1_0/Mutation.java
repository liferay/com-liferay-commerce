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

package com.liferay.headless.commerce.admin.pricing.internal.graphql.mutation.v1_0;

import com.liferay.headless.commerce.admin.pricing.dto.v1_0.Discount;
import com.liferay.headless.commerce.admin.pricing.dto.v1_0.DiscountRule;
import com.liferay.headless.commerce.admin.pricing.dto.v1_0.PriceEntry;
import com.liferay.headless.commerce.admin.pricing.dto.v1_0.PriceList;
import com.liferay.headless.commerce.admin.pricing.dto.v1_0.TierPrice;
import com.liferay.headless.commerce.admin.pricing.resource.v1_0.DiscountResource;
import com.liferay.headless.commerce.admin.pricing.resource.v1_0.DiscountRuleResource;
import com.liferay.headless.commerce.admin.pricing.resource.v1_0.PriceEntryResource;
import com.liferay.headless.commerce.admin.pricing.resource.v1_0.PriceListResource;
import com.liferay.headless.commerce.admin.pricing.resource.v1_0.TierPriceResource;
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

	public static void setDiscountResourceComponentServiceObjects(
		ComponentServiceObjects<DiscountResource>
			discountResourceComponentServiceObjects) {

		_discountResourceComponentServiceObjects =
			discountResourceComponentServiceObjects;
	}

	public static void setDiscountRuleResourceComponentServiceObjects(
		ComponentServiceObjects<DiscountRuleResource>
			discountRuleResourceComponentServiceObjects) {

		_discountRuleResourceComponentServiceObjects =
			discountRuleResourceComponentServiceObjects;
	}

	public static void setPriceEntryResourceComponentServiceObjects(
		ComponentServiceObjects<PriceEntryResource>
			priceEntryResourceComponentServiceObjects) {

		_priceEntryResourceComponentServiceObjects =
			priceEntryResourceComponentServiceObjects;
	}

	public static void setPriceListResourceComponentServiceObjects(
		ComponentServiceObjects<PriceListResource>
			priceListResourceComponentServiceObjects) {

		_priceListResourceComponentServiceObjects =
			priceListResourceComponentServiceObjects;
	}

	public static void setTierPriceResourceComponentServiceObjects(
		ComponentServiceObjects<TierPriceResource>
			tierPriceResourceComponentServiceObjects) {

		_tierPriceResourceComponentServiceObjects =
			tierPriceResourceComponentServiceObjects;
	}

	@GraphQLInvokeDetached
	public Response deleteDiscount(@GraphQLName("id") Long id)
		throws Exception {

		return _applyComponentServiceObjects(
			_discountResourceComponentServiceObjects,
			this::_populateResourceContext,
			discountResource -> discountResource.deleteDiscount(id));
	}

	@GraphQLInvokeDetached
	public Response patchDiscount(
			@GraphQLName("id") Long id,
			@GraphQLName("discount") Discount discount)
		throws Exception {

		return _applyComponentServiceObjects(
			_discountResourceComponentServiceObjects,
			this::_populateResourceContext,
			discountResource -> discountResource.patchDiscount(id, discount));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public DiscountRule postDiscountDiscountRule(
			@GraphQLName("id") Long id,
			@GraphQLName("discountRule") DiscountRule discountRule)
		throws Exception {

		return _applyComponentServiceObjects(
			_discountResourceComponentServiceObjects,
			this::_populateResourceContext,
			discountResource -> discountResource.postDiscountDiscountRule(
				id, discountRule));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Discount postDiscount(@GraphQLName("discount") Discount discount)
		throws Exception {

		return _applyComponentServiceObjects(
			_discountResourceComponentServiceObjects,
			this::_populateResourceContext,
			discountResource -> discountResource.postDiscount(discount));
	}

	@GraphQLInvokeDetached
	public Response deleteDiscountRule(@GraphQLName("id") Long id)
		throws Exception {

		return _applyComponentServiceObjects(
			_discountRuleResourceComponentServiceObjects,
			this::_populateResourceContext,
			discountRuleResource -> discountRuleResource.deleteDiscountRule(
				id));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public DiscountRule postDiscountRule(
			@GraphQLName("id") Long id,
			@GraphQLName("discountRule") DiscountRule discountRule)
		throws Exception {

		return _applyComponentServiceObjects(
			_discountRuleResourceComponentServiceObjects,
			this::_populateResourceContext,
			discountRuleResource -> discountRuleResource.postDiscountRule(
				id, discountRule));
	}

	@GraphQLInvokeDetached
	public Response deletePriceEntry(@GraphQLName("id") String id)
		throws Exception {

		return _applyComponentServiceObjects(
			_priceEntryResourceComponentServiceObjects,
			this::_populateResourceContext,
			priceEntryResource -> priceEntryResource.deletePriceEntry(id));
	}

	@GraphQLInvokeDetached
	public Response patchPriceEntry(
			@GraphQLName("id") String id,
			@GraphQLName("priceEntry") PriceEntry priceEntry)
		throws Exception {

		return _applyComponentServiceObjects(
			_priceEntryResourceComponentServiceObjects,
			this::_populateResourceContext,
			priceEntryResource -> priceEntryResource.patchPriceEntry(
				id, priceEntry));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public TierPrice postPriceEntryTierPrice(
			@GraphQLName("id") String id,
			@GraphQLName("tierPrice") TierPrice tierPrice)
		throws Exception {

		return _applyComponentServiceObjects(
			_priceEntryResourceComponentServiceObjects,
			this::_populateResourceContext,
			priceEntryResource -> priceEntryResource.postPriceEntryTierPrice(
				id, tierPrice));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public PriceEntry postPriceListPriceEntry(
			@GraphQLName("id") String id,
			@GraphQLName("priceEntry") PriceEntry priceEntry)
		throws Exception {

		return _applyComponentServiceObjects(
			_priceListResourceComponentServiceObjects,
			this::_populateResourceContext,
			priceListResource -> priceListResource.postPriceListPriceEntry(
				id, priceEntry));
	}

	@GraphQLInvokeDetached
	public Response deletePriceList(@GraphQLName("id") String id)
		throws Exception {

		return _applyComponentServiceObjects(
			_priceListResourceComponentServiceObjects,
			this::_populateResourceContext,
			priceListResource -> priceListResource.deletePriceList(id));
	}

	@GraphQLInvokeDetached
	public Response patchPriceList(
			@GraphQLName("id") String id,
			@GraphQLName("priceList") PriceList priceList)
		throws Exception {

		return _applyComponentServiceObjects(
			_priceListResourceComponentServiceObjects,
			this::_populateResourceContext,
			priceListResource -> priceListResource.patchPriceList(
				id, priceList));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public PriceList postPriceList(
			@GraphQLName("priceList") PriceList priceList)
		throws Exception {

		return _applyComponentServiceObjects(
			_priceListResourceComponentServiceObjects,
			this::_populateResourceContext,
			priceListResource -> priceListResource.postPriceList(priceList));
	}

	@GraphQLInvokeDetached
	public Response deleteTierPrice(@GraphQLName("id") String id)
		throws Exception {

		return _applyComponentServiceObjects(
			_tierPriceResourceComponentServiceObjects,
			this::_populateResourceContext,
			tierPriceResource -> tierPriceResource.deleteTierPrice(id));
	}

	@GraphQLInvokeDetached
	public Response patchTierPrice(
			@GraphQLName("id") String id,
			@GraphQLName("tierPrice") TierPrice tierPrice)
		throws Exception {

		return _applyComponentServiceObjects(
			_tierPriceResourceComponentServiceObjects,
			this::_populateResourceContext,
			tierPriceResource -> tierPriceResource.patchTierPrice(
				id, tierPrice));
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

	private void _populateResourceContext(DiscountResource discountResource)
		throws Exception {

		discountResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(
			DiscountRuleResource discountRuleResource)
		throws Exception {

		discountRuleResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(PriceEntryResource priceEntryResource)
		throws Exception {

		priceEntryResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(PriceListResource priceListResource)
		throws Exception {

		priceListResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(TierPriceResource tierPriceResource)
		throws Exception {

		tierPriceResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private static ComponentServiceObjects<DiscountResource>
		_discountResourceComponentServiceObjects;
	private static ComponentServiceObjects<DiscountRuleResource>
		_discountRuleResourceComponentServiceObjects;
	private static ComponentServiceObjects<PriceEntryResource>
		_priceEntryResourceComponentServiceObjects;
	private static ComponentServiceObjects<PriceListResource>
		_priceListResourceComponentServiceObjects;
	private static ComponentServiceObjects<TierPriceResource>
		_tierPriceResourceComponentServiceObjects;

}