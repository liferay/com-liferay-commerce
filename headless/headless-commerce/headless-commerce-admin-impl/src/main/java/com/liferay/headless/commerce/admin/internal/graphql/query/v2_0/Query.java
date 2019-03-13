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

package com.liferay.headless.commerce.admin.internal.graphql.query.v2_0;

import com.liferay.headless.commerce.admin.resource.v2_0.AccountResource;
import com.liferay.headless.commerce.admin.resource.v2_0.CountryResource;
import com.liferay.headless.commerce.admin.resource.v2_0.CurrencyResource;
import com.liferay.headless.commerce.admin.resource.v2_0.InventoryResource;
import com.liferay.headless.commerce.admin.resource.v2_0.OrderResource;
import com.liferay.headless.commerce.admin.resource.v2_0.PriceEntryResource;
import com.liferay.headless.commerce.admin.resource.v2_0.PriceListResource;
import com.liferay.headless.commerce.admin.resource.v2_0.ProductOptionResource;
import com.liferay.headless.commerce.admin.resource.v2_0.ProductOptionValueResource;
import com.liferay.headless.commerce.admin.resource.v2_0.ProductResource;
import com.liferay.headless.commerce.admin.resource.v2_0.RegionResource;
import com.liferay.headless.commerce.admin.resource.v2_0.SkuResource;
import com.liferay.headless.commerce.admin.resource.v2_0.UserResource;
import com.liferay.headless.commerce.admin.resource.v2_0.WebSiteResource;
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
 * @author Igor Beslic
 * @generated
 */
@Generated("")
public class Query {

	public static void setAccountResourceComponentServiceObjects(
		ComponentServiceObjects<AccountResource>
			accountResourceComponentServiceObjects) {

		_accountResourceComponentServiceObjects =
			accountResourceComponentServiceObjects;
	}

	public static void setCountryResourceComponentServiceObjects(
		ComponentServiceObjects<CountryResource>
			countryResourceComponentServiceObjects) {

		_countryResourceComponentServiceObjects =
			countryResourceComponentServiceObjects;
	}

	public static void setCurrencyResourceComponentServiceObjects(
		ComponentServiceObjects<CurrencyResource>
			currencyResourceComponentServiceObjects) {

		_currencyResourceComponentServiceObjects =
			currencyResourceComponentServiceObjects;
	}

	public static void setInventoryResourceComponentServiceObjects(
		ComponentServiceObjects<InventoryResource>
			inventoryResourceComponentServiceObjects) {

		_inventoryResourceComponentServiceObjects =
			inventoryResourceComponentServiceObjects;
	}

	public static void setOrderResourceComponentServiceObjects(
		ComponentServiceObjects<OrderResource>
			orderResourceComponentServiceObjects) {

		_orderResourceComponentServiceObjects =
			orderResourceComponentServiceObjects;
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

	public static void setProductResourceComponentServiceObjects(
		ComponentServiceObjects<ProductResource>
			productResourceComponentServiceObjects) {

		_productResourceComponentServiceObjects =
			productResourceComponentServiceObjects;
	}

	public static void setProductOptionResourceComponentServiceObjects(
		ComponentServiceObjects<ProductOptionResource>
			productOptionResourceComponentServiceObjects) {

		_productOptionResourceComponentServiceObjects =
			productOptionResourceComponentServiceObjects;
	}

	public static void setProductOptionValueResourceComponentServiceObjects(
		ComponentServiceObjects<ProductOptionValueResource>
			productOptionValueResourceComponentServiceObjects) {

		_productOptionValueResourceComponentServiceObjects =
			productOptionValueResourceComponentServiceObjects;
	}

	public static void setRegionResourceComponentServiceObjects(
		ComponentServiceObjects<RegionResource>
			regionResourceComponentServiceObjects) {

		_regionResourceComponentServiceObjects =
			regionResourceComponentServiceObjects;
	}

	public static void setSkuResourceComponentServiceObjects(
		ComponentServiceObjects<SkuResource>
			skuResourceComponentServiceObjects) {

		_skuResourceComponentServiceObjects =
			skuResourceComponentServiceObjects;
	}

	public static void setUserResourceComponentServiceObjects(
		ComponentServiceObjects<UserResource>
			userResourceComponentServiceObjects) {

		_userResourceComponentServiceObjects =
			userResourceComponentServiceObjects;
	}

	public static void setWebSiteResourceComponentServiceObjects(
		ComponentServiceObjects<WebSiteResource>
			webSiteResourceComponentServiceObjects) {

		_webSiteResourceComponentServiceObjects =
			webSiteResourceComponentServiceObjects;
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getAccount(@GraphQLName("id") String id) throws Exception {
		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getAddresses(@GraphQLName("id") String id)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getAccountOrders(
			@GraphQLName("id") String id, @GraphQLName("groupId") Long groupId)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getAccounts() throws Exception {
		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getCountry(@GraphQLName("id") String id) throws Exception {
		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getRegions(@GraphQLName("id") String id) throws Exception {
		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getCountries(@GraphQLName("groupId") Long groupId)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getCurrency(@GraphQLName("id") String id) throws Exception {
		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getCurrencies(@GraphQLName("groupId") Long groupId)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getInventory(@GraphQLName("id") String id)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getOrder(@GraphQLName("id") String id) throws Exception {
		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getOrderItem(
			@GraphQLName("id") String id,
			@GraphQLName("orderItemId") String orderItemId)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getOrderItems(@GraphQLName("id") String id)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getOrderNote(
			@GraphQLName("id") String id,
			@GraphQLName("orderNoteId") String orderNoteId)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getOrderNotes(@GraphQLName("id") String id)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getBillingAddress(@GraphQLName("id") String id)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getShippingAddress(@GraphQLName("id") String id)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getOrders(@GraphQLName("groupId") Long groupId)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getPriceEntry(@GraphQLName("id") String id)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getPriceEntries(@GraphQLName("groupId") Long groupId)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getPriceList(
			@GraphQLName("id") String id, @GraphQLName("groupId") Long groupId)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getPriceLists(@GraphQLName("groupId") Long groupId)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getProduct(@GraphQLName("id") String id) throws Exception {
		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getProducts(@GraphQLName("groupId") Long groupId)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getSkus(@GraphQLName("id") String id) throws Exception {
		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getProductOption(@GraphQLName("id") String id)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getProductOptions(@GraphQLName("groupId") Long groupId)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getProductOptionValue(@GraphQLName("id") String id)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getProductOptionValues(@GraphQLName("id") String id)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getRegion(@GraphQLName("id") String id) throws Exception {
		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getInventories(@GraphQLName("id") String id)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getSku(@GraphQLName("id") String id) throws Exception {
		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getUser(@GraphQLName("id") String id) throws Exception {
		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getUsers() throws Exception {
		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getWebSite(@GraphQLName("id") String id) throws Exception {
		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response getWebSites() throws Exception {
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

	private void _populateResourceContext(AccountResource accountResource)
		throws Exception {

		accountResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(CountryResource countryResource)
		throws Exception {

		countryResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(CurrencyResource currencyResource)
		throws Exception {

		currencyResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(InventoryResource inventoryResource)
		throws Exception {

		inventoryResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(OrderResource orderResource)
		throws Exception {

		orderResource.setContextCompany(
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

	private void _populateResourceContext(ProductResource productResource)
		throws Exception {

		productResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(
			ProductOptionResource productOptionResource)
		throws Exception {

		productOptionResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(
			ProductOptionValueResource productOptionValueResource)
		throws Exception {

		productOptionValueResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(RegionResource regionResource)
		throws Exception {

		regionResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(SkuResource skuResource)
		throws Exception {

		skuResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(UserResource userResource)
		throws Exception {

		userResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(WebSiteResource webSiteResource)
		throws Exception {

		webSiteResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private static ComponentServiceObjects<AccountResource>
		_accountResourceComponentServiceObjects;
	private static ComponentServiceObjects<CountryResource>
		_countryResourceComponentServiceObjects;
	private static ComponentServiceObjects<CurrencyResource>
		_currencyResourceComponentServiceObjects;
	private static ComponentServiceObjects<InventoryResource>
		_inventoryResourceComponentServiceObjects;
	private static ComponentServiceObjects<OrderResource>
		_orderResourceComponentServiceObjects;
	private static ComponentServiceObjects<PriceEntryResource>
		_priceEntryResourceComponentServiceObjects;
	private static ComponentServiceObjects<PriceListResource>
		_priceListResourceComponentServiceObjects;
	private static ComponentServiceObjects<ProductResource>
		_productResourceComponentServiceObjects;
	private static ComponentServiceObjects<ProductOptionResource>
		_productOptionResourceComponentServiceObjects;
	private static ComponentServiceObjects<ProductOptionValueResource>
		_productOptionValueResourceComponentServiceObjects;
	private static ComponentServiceObjects<RegionResource>
		_regionResourceComponentServiceObjects;
	private static ComponentServiceObjects<SkuResource>
		_skuResourceComponentServiceObjects;
	private static ComponentServiceObjects<UserResource>
		_userResourceComponentServiceObjects;
	private static ComponentServiceObjects<WebSiteResource>
		_webSiteResourceComponentServiceObjects;

}