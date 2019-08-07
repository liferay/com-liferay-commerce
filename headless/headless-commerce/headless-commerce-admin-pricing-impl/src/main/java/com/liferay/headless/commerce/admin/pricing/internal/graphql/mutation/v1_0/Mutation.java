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
import com.liferay.headless.commerce.admin.pricing.dto.v1_0.DiscountAccountGroup;
import com.liferay.headless.commerce.admin.pricing.dto.v1_0.DiscountCategory;
import com.liferay.headless.commerce.admin.pricing.dto.v1_0.DiscountProduct;
import com.liferay.headless.commerce.admin.pricing.dto.v1_0.DiscountRule;
import com.liferay.headless.commerce.admin.pricing.dto.v1_0.PriceEntry;
import com.liferay.headless.commerce.admin.pricing.dto.v1_0.PriceList;
import com.liferay.headless.commerce.admin.pricing.dto.v1_0.PriceListAccountGroup;
import com.liferay.headless.commerce.admin.pricing.dto.v1_0.TierPrice;
import com.liferay.headless.commerce.admin.pricing.resource.v1_0.DiscountAccountGroupResource;
import com.liferay.headless.commerce.admin.pricing.resource.v1_0.DiscountCategoryResource;
import com.liferay.headless.commerce.admin.pricing.resource.v1_0.DiscountProductResource;
import com.liferay.headless.commerce.admin.pricing.resource.v1_0.DiscountResource;
import com.liferay.headless.commerce.admin.pricing.resource.v1_0.DiscountRuleResource;
import com.liferay.headless.commerce.admin.pricing.resource.v1_0.PriceEntryResource;
import com.liferay.headless.commerce.admin.pricing.resource.v1_0.PriceListAccountGroupResource;
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

	public static void setDiscountAccountGroupResourceComponentServiceObjects(
		ComponentServiceObjects<DiscountAccountGroupResource>
			discountAccountGroupResourceComponentServiceObjects) {

		_discountAccountGroupResourceComponentServiceObjects =
			discountAccountGroupResourceComponentServiceObjects;
	}

	public static void setDiscountCategoryResourceComponentServiceObjects(
		ComponentServiceObjects<DiscountCategoryResource>
			discountCategoryResourceComponentServiceObjects) {

		_discountCategoryResourceComponentServiceObjects =
			discountCategoryResourceComponentServiceObjects;
	}

	public static void setDiscountProductResourceComponentServiceObjects(
		ComponentServiceObjects<DiscountProductResource>
			discountProductResourceComponentServiceObjects) {

		_discountProductResourceComponentServiceObjects =
			discountProductResourceComponentServiceObjects;
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

	public static void setPriceListAccountGroupResourceComponentServiceObjects(
		ComponentServiceObjects<PriceListAccountGroupResource>
			priceListAccountGroupResourceComponentServiceObjects) {

		_priceListAccountGroupResourceComponentServiceObjects =
			priceListAccountGroupResourceComponentServiceObjects;
	}

	public static void setTierPriceResourceComponentServiceObjects(
		ComponentServiceObjects<TierPriceResource>
			tierPriceResourceComponentServiceObjects) {

		_tierPriceResourceComponentServiceObjects =
			tierPriceResourceComponentServiceObjects;
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
	public Response deleteDiscountByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode)
		throws Exception {

		return _applyComponentServiceObjects(
			_discountResourceComponentServiceObjects,
			this::_populateResourceContext,
			discountResource ->
				discountResource.deleteDiscountByExternalReferenceCode(
					externalReferenceCode));
	}

	@GraphQLInvokeDetached
	public Response patchDiscountByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("discount") Discount discount)
		throws Exception {

		return _applyComponentServiceObjects(
			_discountResourceComponentServiceObjects,
			this::_populateResourceContext,
			discountResource ->
				discountResource.patchDiscountByExternalReferenceCode(
					externalReferenceCode, discount));
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

	@GraphQLInvokeDetached
	public Response deleteDiscountAccountGroup(@GraphQLName("id") Long id)
		throws Exception {

		return _applyComponentServiceObjects(
			_discountAccountGroupResourceComponentServiceObjects,
			this::_populateResourceContext,
			discountAccountGroupResource ->
				discountAccountGroupResource.deleteDiscountAccountGroup(id));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public DiscountAccountGroup
			postDiscountByExternalReferenceCodeDiscountAccountGroup(
				@GraphQLName("externalReferenceCode") String
					externalReferenceCode,
				@GraphQLName("discountAccountGroup") DiscountAccountGroup
					discountAccountGroup)
		throws Exception {

		return _applyComponentServiceObjects(
			_discountAccountGroupResourceComponentServiceObjects,
			this::_populateResourceContext,
			discountAccountGroupResource ->
				discountAccountGroupResource.
					postDiscountByExternalReferenceCodeDiscountAccountGroup(
						externalReferenceCode, discountAccountGroup));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public DiscountAccountGroup postDiscountIdDiscountAccountGroup(
			@GraphQLName("id") Long id,
			@GraphQLName("discountAccountGroup") DiscountAccountGroup
				discountAccountGroup)
		throws Exception {

		return _applyComponentServiceObjects(
			_discountAccountGroupResourceComponentServiceObjects,
			this::_populateResourceContext,
			discountAccountGroupResource ->
				discountAccountGroupResource.postDiscountIdDiscountAccountGroup(
					id, discountAccountGroup));
	}

	@GraphQLInvokeDetached
	public Response deleteDiscountCategory(@GraphQLName("id") Long id)
		throws Exception {

		return _applyComponentServiceObjects(
			_discountCategoryResourceComponentServiceObjects,
			this::_populateResourceContext,
			discountCategoryResource ->
				discountCategoryResource.deleteDiscountCategory(id));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public DiscountCategory postDiscountByExternalReferenceCodeDiscountCategory(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("discountCategory") DiscountCategory discountCategory)
		throws Exception {

		return _applyComponentServiceObjects(
			_discountCategoryResourceComponentServiceObjects,
			this::_populateResourceContext,
			discountCategoryResource ->
				discountCategoryResource.
					postDiscountByExternalReferenceCodeDiscountCategory(
						externalReferenceCode, discountCategory));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public DiscountCategory postDiscountIdDiscountCategory(
			@GraphQLName("id") Long id,
			@GraphQLName("discountCategory") DiscountCategory discountCategory)
		throws Exception {

		return _applyComponentServiceObjects(
			_discountCategoryResourceComponentServiceObjects,
			this::_populateResourceContext,
			discountCategoryResource ->
				discountCategoryResource.postDiscountIdDiscountCategory(
					id, discountCategory));
	}

	@GraphQLInvokeDetached
	public Response deleteDiscountProduct(@GraphQLName("id") Long id)
		throws Exception {

		return _applyComponentServiceObjects(
			_discountProductResourceComponentServiceObjects,
			this::_populateResourceContext,
			discountProductResource ->
				discountProductResource.deleteDiscountProduct(id));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public DiscountProduct postDiscountByExternalReferenceCodeDiscountProduct(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("discountProduct") DiscountProduct discountProduct)
		throws Exception {

		return _applyComponentServiceObjects(
			_discountProductResourceComponentServiceObjects,
			this::_populateResourceContext,
			discountProductResource ->
				discountProductResource.
					postDiscountByExternalReferenceCodeDiscountProduct(
						externalReferenceCode, discountProduct));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public DiscountProduct postDiscountIdDiscountProduct(
			@GraphQLName("id") Long id,
			@GraphQLName("discountProduct") DiscountProduct discountProduct)
		throws Exception {

		return _applyComponentServiceObjects(
			_discountProductResourceComponentServiceObjects,
			this::_populateResourceContext,
			discountProductResource ->
				discountProductResource.postDiscountIdDiscountProduct(
					id, discountProduct));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public DiscountRule postDiscountByExternalReferenceCodeDiscountRule(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("discountRule") DiscountRule discountRule)
		throws Exception {

		return _applyComponentServiceObjects(
			_discountRuleResourceComponentServiceObjects,
			this::_populateResourceContext,
			discountRuleResource ->
				discountRuleResource.
					postDiscountByExternalReferenceCodeDiscountRule(
						externalReferenceCode, discountRule));
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

	@GraphQLInvokeDetached
	public Response patchDiscountRule(
			@GraphQLName("id") Long id,
			@GraphQLName("discountRule") DiscountRule discountRule)
		throws Exception {

		return _applyComponentServiceObjects(
			_discountRuleResourceComponentServiceObjects,
			this::_populateResourceContext,
			discountRuleResource -> discountRuleResource.patchDiscountRule(
				id, discountRule));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public DiscountRule postDiscountIdDiscountRule(
			@GraphQLName("id") Long id,
			@GraphQLName("discountRule") DiscountRule discountRule)
		throws Exception {

		return _applyComponentServiceObjects(
			_discountRuleResourceComponentServiceObjects,
			this::_populateResourceContext,
			discountRuleResource ->
				discountRuleResource.postDiscountIdDiscountRule(
					id, discountRule));
	}

	@GraphQLInvokeDetached
	public Response deletePriceEntryByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode)
		throws Exception {

		return _applyComponentServiceObjects(
			_priceEntryResourceComponentServiceObjects,
			this::_populateResourceContext,
			priceEntryResource ->
				priceEntryResource.deletePriceEntryByExternalReferenceCode(
					externalReferenceCode));
	}

	@GraphQLInvokeDetached
	public Response patchPriceEntryByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("priceEntry") PriceEntry priceEntry)
		throws Exception {

		return _applyComponentServiceObjects(
			_priceEntryResourceComponentServiceObjects,
			this::_populateResourceContext,
			priceEntryResource ->
				priceEntryResource.patchPriceEntryByExternalReferenceCode(
					externalReferenceCode, priceEntry));
	}

	@GraphQLInvokeDetached
	public Response deletePriceEntry(@GraphQLName("id") Long id)
		throws Exception {

		return _applyComponentServiceObjects(
			_priceEntryResourceComponentServiceObjects,
			this::_populateResourceContext,
			priceEntryResource -> priceEntryResource.deletePriceEntry(id));
	}

	@GraphQLInvokeDetached
	public Response patchPriceEntry(
			@GraphQLName("id") Long id,
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
	public PriceEntry postPriceListByExternalReferenceCodePriceEntry(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("priceEntry") PriceEntry priceEntry)
		throws Exception {

		return _applyComponentServiceObjects(
			_priceEntryResourceComponentServiceObjects,
			this::_populateResourceContext,
			priceEntryResource ->
				priceEntryResource.
					postPriceListByExternalReferenceCodePriceEntry(
						externalReferenceCode, priceEntry));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public PriceEntry postPriceListIdPriceEntry(
			@GraphQLName("id") Long id,
			@GraphQLName("priceEntry") PriceEntry priceEntry)
		throws Exception {

		return _applyComponentServiceObjects(
			_priceEntryResourceComponentServiceObjects,
			this::_populateResourceContext,
			priceEntryResource -> priceEntryResource.postPriceListIdPriceEntry(
				id, priceEntry));
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
	public Response deletePriceListByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode)
		throws Exception {

		return _applyComponentServiceObjects(
			_priceListResourceComponentServiceObjects,
			this::_populateResourceContext,
			priceListResource ->
				priceListResource.deletePriceListByExternalReferenceCode(
					externalReferenceCode));
	}

	@GraphQLInvokeDetached
	public Response patchPriceListByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("priceList") PriceList priceList)
		throws Exception {

		return _applyComponentServiceObjects(
			_priceListResourceComponentServiceObjects,
			this::_populateResourceContext,
			priceListResource ->
				priceListResource.patchPriceListByExternalReferenceCode(
					externalReferenceCode, priceList));
	}

	@GraphQLInvokeDetached
	public Response deletePriceList(@GraphQLName("id") Long id)
		throws Exception {

		return _applyComponentServiceObjects(
			_priceListResourceComponentServiceObjects,
			this::_populateResourceContext,
			priceListResource -> priceListResource.deletePriceList(id));
	}

	@GraphQLInvokeDetached
	public Response patchPriceList(
			@GraphQLName("id") Long id,
			@GraphQLName("priceList") PriceList priceList)
		throws Exception {

		return _applyComponentServiceObjects(
			_priceListResourceComponentServiceObjects,
			this::_populateResourceContext,
			priceListResource -> priceListResource.patchPriceList(
				id, priceList));
	}

	@GraphQLInvokeDetached
	public Response deletePriceListAccountGroup(@GraphQLName("id") Long id)
		throws Exception {

		return _applyComponentServiceObjects(
			_priceListAccountGroupResourceComponentServiceObjects,
			this::_populateResourceContext,
			priceListAccountGroupResource ->
				priceListAccountGroupResource.deletePriceListAccountGroup(id));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public PriceListAccountGroup
			postPriceListByExternalReferenceCodePriceListAccountGroup(
				@GraphQLName("externalReferenceCode") String
					externalReferenceCode,
				@GraphQLName("priceListAccountGroup") PriceListAccountGroup
					priceListAccountGroup)
		throws Exception {

		return _applyComponentServiceObjects(
			_priceListAccountGroupResourceComponentServiceObjects,
			this::_populateResourceContext,
			priceListAccountGroupResource ->
				priceListAccountGroupResource.
					postPriceListByExternalReferenceCodePriceListAccountGroup(
						externalReferenceCode, priceListAccountGroup));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public PriceListAccountGroup postPriceListIdPriceListAccountGroup(
			@GraphQLName("id") Long id,
			@GraphQLName("priceListAccountGroup") PriceListAccountGroup
				priceListAccountGroup)
		throws Exception {

		return _applyComponentServiceObjects(
			_priceListAccountGroupResourceComponentServiceObjects,
			this::_populateResourceContext,
			priceListAccountGroupResource ->
				priceListAccountGroupResource.
					postPriceListIdPriceListAccountGroup(
						id, priceListAccountGroup));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public TierPrice postPriceEntryByExternalReferenceCodeTierPrice(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("tierPrice") TierPrice tierPrice)
		throws Exception {

		return _applyComponentServiceObjects(
			_tierPriceResourceComponentServiceObjects,
			this::_populateResourceContext,
			tierPriceResource ->
				tierPriceResource.
					postPriceEntryByExternalReferenceCodeTierPrice(
						externalReferenceCode, tierPrice));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public TierPrice postPriceEntryIdTierPrice(
			@GraphQLName("id") Long id,
			@GraphQLName("tierPrice") TierPrice tierPrice)
		throws Exception {

		return _applyComponentServiceObjects(
			_tierPriceResourceComponentServiceObjects,
			this::_populateResourceContext,
			tierPriceResource -> tierPriceResource.postPriceEntryIdTierPrice(
				id, tierPrice));
	}

	@GraphQLInvokeDetached
	public Response deleteTierPriceByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode)
		throws Exception {

		return _applyComponentServiceObjects(
			_tierPriceResourceComponentServiceObjects,
			this::_populateResourceContext,
			tierPriceResource ->
				tierPriceResource.deleteTierPriceByExternalReferenceCode(
					externalReferenceCode));
	}

	@GraphQLInvokeDetached
	public Response patchTierPriceByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("tierPrice") TierPrice tierPrice)
		throws Exception {

		return _applyComponentServiceObjects(
			_tierPriceResourceComponentServiceObjects,
			this::_populateResourceContext,
			tierPriceResource ->
				tierPriceResource.patchTierPriceByExternalReferenceCode(
					externalReferenceCode, tierPrice));
	}

	@GraphQLInvokeDetached
	public Response deleteTierPrice(@GraphQLName("id") Long id)
		throws Exception {

		return _applyComponentServiceObjects(
			_tierPriceResourceComponentServiceObjects,
			this::_populateResourceContext,
			tierPriceResource -> tierPriceResource.deleteTierPrice(id));
	}

	@GraphQLInvokeDetached
	public Response patchTierPrice(
			@GraphQLName("id") Long id,
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
			DiscountAccountGroupResource discountAccountGroupResource)
		throws Exception {

		discountAccountGroupResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(
			DiscountCategoryResource discountCategoryResource)
		throws Exception {

		discountCategoryResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(
			DiscountProductResource discountProductResource)
		throws Exception {

		discountProductResource.setContextCompany(
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

	private void _populateResourceContext(
			PriceListAccountGroupResource priceListAccountGroupResource)
		throws Exception {

		priceListAccountGroupResource.setContextCompany(
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
	private static ComponentServiceObjects<DiscountAccountGroupResource>
		_discountAccountGroupResourceComponentServiceObjects;
	private static ComponentServiceObjects<DiscountCategoryResource>
		_discountCategoryResourceComponentServiceObjects;
	private static ComponentServiceObjects<DiscountProductResource>
		_discountProductResourceComponentServiceObjects;
	private static ComponentServiceObjects<DiscountRuleResource>
		_discountRuleResourceComponentServiceObjects;
	private static ComponentServiceObjects<PriceEntryResource>
		_priceEntryResourceComponentServiceObjects;
	private static ComponentServiceObjects<PriceListResource>
		_priceListResourceComponentServiceObjects;
	private static ComponentServiceObjects<PriceListAccountGroupResource>
		_priceListAccountGroupResourceComponentServiceObjects;
	private static ComponentServiceObjects<TierPriceResource>
		_tierPriceResourceComponentServiceObjects;

}