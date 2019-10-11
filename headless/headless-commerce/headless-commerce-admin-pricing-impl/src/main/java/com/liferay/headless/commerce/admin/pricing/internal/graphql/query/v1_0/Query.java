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

package com.liferay.headless.commerce.admin.pricing.internal.graphql.query.v1_0;

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
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLInvokeDetached;
import graphql.annotations.annotationTypes.GraphQLName;

import java.util.Collection;

import javax.annotation.Generated;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author Zoltán Takács
 * @generated
 */
@Generated("")
public class Query {

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
	public Collection<Discount> getDiscountsPage(
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_discountResourceComponentServiceObjects,
			this::_populateResourceContext,
			discountResource -> {
				Page paginationPage = discountResource.getDiscountsPage(
					Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Discount getDiscountByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode)
		throws Exception {

		return _applyComponentServiceObjects(
			_discountResourceComponentServiceObjects,
			this::_populateResourceContext,
			discountResource ->
				discountResource.getDiscountByExternalReferenceCode(
					externalReferenceCode));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Discount getDiscount(@GraphQLName("id") Long id) throws Exception {
		return _applyComponentServiceObjects(
			_discountResourceComponentServiceObjects,
			this::_populateResourceContext,
			discountResource -> discountResource.getDiscount(id));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<DiscountAccountGroup>
			getDiscountByExternalReferenceCodeDiscountAccountGroupsPage(
				@GraphQLName("externalReferenceCode") String
					externalReferenceCode,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_discountAccountGroupResourceComponentServiceObjects,
			this::_populateResourceContext,
			discountAccountGroupResource -> {
				Page paginationPage =
					discountAccountGroupResource.
						getDiscountByExternalReferenceCodeDiscountAccountGroupsPage(
							externalReferenceCode,
							Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<DiscountAccountGroup>
			getDiscountIdDiscountAccountGroupsPage(
				@GraphQLName("id") Long id,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_discountAccountGroupResourceComponentServiceObjects,
			this::_populateResourceContext,
			discountAccountGroupResource -> {
				Page paginationPage =
					discountAccountGroupResource.
						getDiscountIdDiscountAccountGroupsPage(
							id, Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<DiscountCategory>
			getDiscountByExternalReferenceCodeDiscountCategoriesPage(
				@GraphQLName("externalReferenceCode") String
					externalReferenceCode,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_discountCategoryResourceComponentServiceObjects,
			this::_populateResourceContext,
			discountCategoryResource -> {
				Page paginationPage =
					discountCategoryResource.
						getDiscountByExternalReferenceCodeDiscountCategoriesPage(
							externalReferenceCode,
							Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<DiscountCategory> getDiscountIdDiscountCategoriesPage(
			@GraphQLName("id") Long id, @GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_discountCategoryResourceComponentServiceObjects,
			this::_populateResourceContext,
			discountCategoryResource -> {
				Page paginationPage =
					discountCategoryResource.
						getDiscountIdDiscountCategoriesPage(
							id, Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<DiscountProduct>
			getDiscountByExternalReferenceCodeDiscountProductsPage(
				@GraphQLName("externalReferenceCode") String
					externalReferenceCode,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_discountProductResourceComponentServiceObjects,
			this::_populateResourceContext,
			discountProductResource -> {
				Page paginationPage =
					discountProductResource.
						getDiscountByExternalReferenceCodeDiscountProductsPage(
							externalReferenceCode,
							Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<DiscountProduct> getDiscountIdDiscountProductsPage(
			@GraphQLName("id") Long id, @GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_discountProductResourceComponentServiceObjects,
			this::_populateResourceContext,
			discountProductResource -> {
				Page paginationPage =
					discountProductResource.getDiscountIdDiscountProductsPage(
						id, Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<DiscountRule>
			getDiscountByExternalReferenceCodeDiscountRulesPage(
				@GraphQLName("externalReferenceCode") String
					externalReferenceCode,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_discountRuleResourceComponentServiceObjects,
			this::_populateResourceContext,
			discountRuleResource -> {
				Page paginationPage =
					discountRuleResource.
						getDiscountByExternalReferenceCodeDiscountRulesPage(
							externalReferenceCode,
							Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public DiscountRule getDiscountRule(@GraphQLName("id") Long id)
		throws Exception {

		return _applyComponentServiceObjects(
			_discountRuleResourceComponentServiceObjects,
			this::_populateResourceContext,
			discountRuleResource -> discountRuleResource.getDiscountRule(id));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<DiscountRule> getDiscountIdDiscountRulesPage(
			@GraphQLName("id") Long id, @GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_discountRuleResourceComponentServiceObjects,
			this::_populateResourceContext,
			discountRuleResource -> {
				Page paginationPage =
					discountRuleResource.getDiscountIdDiscountRulesPage(
						id, Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public PriceEntry getPriceEntryByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode)
		throws Exception {

		return _applyComponentServiceObjects(
			_priceEntryResourceComponentServiceObjects,
			this::_populateResourceContext,
			priceEntryResource ->
				priceEntryResource.getPriceEntryByExternalReferenceCode(
					externalReferenceCode));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public PriceEntry getPriceEntry(@GraphQLName("id") Long id)
		throws Exception {

		return _applyComponentServiceObjects(
			_priceEntryResourceComponentServiceObjects,
			this::_populateResourceContext,
			priceEntryResource -> priceEntryResource.getPriceEntry(id));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<PriceEntry>
			getPriceListByExternalReferenceCodePriceEntriesPage(
				@GraphQLName("externalReferenceCode") String
					externalReferenceCode,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_priceEntryResourceComponentServiceObjects,
			this::_populateResourceContext,
			priceEntryResource -> {
				Page paginationPage =
					priceEntryResource.
						getPriceListByExternalReferenceCodePriceEntriesPage(
							externalReferenceCode,
							Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<PriceEntry> getPriceListIdPriceEntriesPage(
			@GraphQLName("id") Long id, @GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_priceEntryResourceComponentServiceObjects,
			this::_populateResourceContext,
			priceEntryResource -> {
				Page paginationPage =
					priceEntryResource.getPriceListIdPriceEntriesPage(
						id, Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<PriceList> getPriceListsPage(
			@GraphQLName("filter") Filter filter,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page, @GraphQLName("sorts") Sort[] sorts)
		throws Exception {

		return _applyComponentServiceObjects(
			_priceListResourceComponentServiceObjects,
			this::_populateResourceContext,
			priceListResource -> {
				Page paginationPage = priceListResource.getPriceListsPage(
					filter, Pagination.of(pageSize, page), sorts);

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public PriceList getPriceListByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode)
		throws Exception {

		return _applyComponentServiceObjects(
			_priceListResourceComponentServiceObjects,
			this::_populateResourceContext,
			priceListResource ->
				priceListResource.getPriceListByExternalReferenceCode(
					externalReferenceCode));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public PriceList getPriceList(@GraphQLName("id") Long id) throws Exception {
		return _applyComponentServiceObjects(
			_priceListResourceComponentServiceObjects,
			this::_populateResourceContext,
			priceListResource -> priceListResource.getPriceList(id));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<PriceListAccountGroup>
			getPriceListByExternalReferenceCodePriceListAccountGroupPage(
				@GraphQLName("externalReferenceCode") String
					externalReferenceCode,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_priceListAccountGroupResourceComponentServiceObjects,
			this::_populateResourceContext,
			priceListAccountGroupResource -> {
				Page paginationPage =
					priceListAccountGroupResource.
						getPriceListByExternalReferenceCodePriceListAccountGroupPage(
							externalReferenceCode,
							Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<PriceListAccountGroup>
			getPriceListIdPriceListAccountGroupsPage(
				@GraphQLName("id") Long id,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_priceListAccountGroupResourceComponentServiceObjects,
			this::_populateResourceContext,
			priceListAccountGroupResource -> {
				Page paginationPage =
					priceListAccountGroupResource.
						getPriceListIdPriceListAccountGroupsPage(
							id, Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<TierPrice>
			getPriceEntryByExternalReferenceCodeTierPricesPage(
				@GraphQLName("externalReferenceCode") String
					externalReferenceCode,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_tierPriceResourceComponentServiceObjects,
			this::_populateResourceContext,
			tierPriceResource -> {
				Page paginationPage =
					tierPriceResource.
						getPriceEntryByExternalReferenceCodeTierPricesPage(
							externalReferenceCode,
							Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<TierPrice> getPriceEntryIdTierPricesPage(
			@GraphQLName("id") Long id, @GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_tierPriceResourceComponentServiceObjects,
			this::_populateResourceContext,
			tierPriceResource -> {
				Page paginationPage =
					tierPriceResource.getPriceEntryIdTierPricesPage(
						id, Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public TierPrice getTierPriceByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode)
		throws Exception {

		return _applyComponentServiceObjects(
			_tierPriceResourceComponentServiceObjects,
			this::_populateResourceContext,
			tierPriceResource ->
				tierPriceResource.getTierPriceByExternalReferenceCode(
					externalReferenceCode));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public TierPrice getTierPrice(@GraphQLName("id") Long id) throws Exception {
		return _applyComponentServiceObjects(
			_tierPriceResourceComponentServiceObjects,
			this::_populateResourceContext,
			tierPriceResource -> tierPriceResource.getTierPrice(id));
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