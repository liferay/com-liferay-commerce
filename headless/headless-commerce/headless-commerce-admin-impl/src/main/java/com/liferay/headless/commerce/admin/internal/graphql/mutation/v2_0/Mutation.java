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

package com.liferay.headless.commerce.admin.internal.graphql.mutation.v2_0;

import com.liferay.headless.commerce.admin.dto.v2_0.Account;
import com.liferay.headless.commerce.admin.dto.v2_0.Address;
import com.liferay.headless.commerce.admin.dto.v2_0.Country;
import com.liferay.headless.commerce.admin.dto.v2_0.Currency;
import com.liferay.headless.commerce.admin.dto.v2_0.Inventory;
import com.liferay.headless.commerce.admin.dto.v2_0.Order;
import com.liferay.headless.commerce.admin.dto.v2_0.OrderItem;
import com.liferay.headless.commerce.admin.dto.v2_0.OrderNote;
import com.liferay.headless.commerce.admin.dto.v2_0.PriceEntry;
import com.liferay.headless.commerce.admin.dto.v2_0.PriceList;
import com.liferay.headless.commerce.admin.dto.v2_0.Product;
import com.liferay.headless.commerce.admin.dto.v2_0.ProductOption;
import com.liferay.headless.commerce.admin.dto.v2_0.ProductOptionValue;
import com.liferay.headless.commerce.admin.dto.v2_0.Region;
import com.liferay.headless.commerce.admin.dto.v2_0.Sku;
import com.liferay.headless.commerce.admin.dto.v2_0.User;
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
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.vulcan.multipart.MultipartBody;

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
public class Mutation {

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

	@GraphQLField
	@GraphQLInvokeDetached
	public Response upsertAccountLogo(
			@GraphQLName("id") String id,
			@GraphQLName("MultipartBody") MultipartBody multipartBody)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLInvokeDetached
	public Response deleteAccount(@GraphQLName("id") String id)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLInvokeDetached
	public Response updateMediaType1Account(
			@GraphQLName("id") String id,
			@GraphQLName("Account") Account account)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLInvokeDetached
	public Response updateMediaType2Account(
			@GraphQLName("id") String id,
			@GraphQLName("Account") Account account)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response addMediaType1Address(
			@GraphQLName("id") String id,
			@GraphQLName("Address") Address address)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response addMediaType2Address(
			@GraphQLName("id") String id,
			@GraphQLName("Address") Address address)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response upsertMediaType1Order(
			@GraphQLName("id") String id, @GraphQLName("groupId") Long groupId,
			@GraphQLName("Order") Order order)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response upsertMediaType2Order(
			@GraphQLName("id") String id, @GraphQLName("groupId") Long groupId,
			@GraphQLName("Order") Order order)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response upsertMediaType1Account(
			@GraphQLName("Account") Account account)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response upsertMediaType2Account(
			@GraphQLName("Account") Account account)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLInvokeDetached
	public Response deleteCountry(@GraphQLName("id") String id)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLInvokeDetached
	public Response updateMediaType1Country(
			@GraphQLName("groupId") Long groupId, @GraphQLName("id") String id,
			@GraphQLName("Country") Country country)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLInvokeDetached
	public Response updateMediaType2Country(
			@GraphQLName("groupId") Long groupId, @GraphQLName("id") String id,
			@GraphQLName("Country") Country country)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response upsertMediaType1Region(
			@GraphQLName("id") String id, @GraphQLName("Region") Region region)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response upsertMediaType2Region(
			@GraphQLName("id") String id, @GraphQLName("Region") Region region)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response upsertMediaType1Country(
			@GraphQLName("groupId") Long groupId,
			@GraphQLName("Country") Country country)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response upsertMediaType2Country(
			@GraphQLName("groupId") Long groupId,
			@GraphQLName("Country") Country country)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLInvokeDetached
	public Response deleteCurrency(@GraphQLName("id") String id)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLInvokeDetached
	public Response updateMediaType1Currency(
			@GraphQLName("groupId") Long groupId, @GraphQLName("id") String id,
			@GraphQLName("Currency") Currency currency)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLInvokeDetached
	public Response updateMediaType2Currency(
			@GraphQLName("groupId") Long groupId, @GraphQLName("id") String id,
			@GraphQLName("Currency") Currency currency)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response upsertMediaType1Currency(
			@GraphQLName("groupId") Long groupId,
			@GraphQLName("Currency") Currency currency)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response upsertMediaType2Currency(
			@GraphQLName("groupId") Long groupId,
			@GraphQLName("Currency") Currency currency)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLInvokeDetached
	public Response deleteInventory(@GraphQLName("id") String id)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLInvokeDetached
	public Response updateMediaType1Inventory(
			@GraphQLName("id") String id, @GraphQLName("groupId") Long groupId,
			@GraphQLName("Inventory") Inventory inventory)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLInvokeDetached
	public Response updateMediaType2Inventory(
			@GraphQLName("id") String id, @GraphQLName("groupId") Long groupId,
			@GraphQLName("Inventory") Inventory inventory)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLInvokeDetached
	public Response deleteOrder(@GraphQLName("id") String id) throws Exception {
		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLInvokeDetached
	public Response updateMediaType1Order(
			@GraphQLName("id") String id, @GraphQLName("Order") Order order)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLInvokeDetached
	public Response updateMediaType2Order(
			@GraphQLName("id") String id, @GraphQLName("Order") Order order)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLInvokeDetached
	public Response deleteOrderItem(
			@GraphQLName("id") String id,
			@GraphQLName("orderItemId") String orderItemId)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response updateMediaType1OrderItem(
			@GraphQLName("id") String id,
			@GraphQLName("orderItemId") String orderItemId,
			@GraphQLName("OrderItem") OrderItem orderItem)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response updateMediaType2OrderItem(
			@GraphQLName("id") String id,
			@GraphQLName("orderItemId") String orderItemId,
			@GraphQLName("OrderItem") OrderItem orderItem)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response upsertMediaType1OrderItem(
			@GraphQLName("id") String id,
			@GraphQLName("OrderItem") OrderItem orderItem)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response upsertMediaType2OrderItem(
			@GraphQLName("id") String id,
			@GraphQLName("OrderItem") OrderItem orderItem)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLInvokeDetached
	public Response deleteOrderNote(
			@GraphQLName("id") String id,
			@GraphQLName("orderNoteId") String orderNoteId)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response updateMediaType1OrderNote(
			@GraphQLName("id") String id,
			@GraphQLName("orderNoteId") String orderNoteId,
			@GraphQLName("OrderNote") OrderNote orderNote)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response updateMediaType2OrderNote(
			@GraphQLName("id") String id,
			@GraphQLName("orderNoteId") String orderNoteId,
			@GraphQLName("OrderNote") OrderNote orderNote)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response upsertMediaType1OrderNote(
			@GraphQLName("id") String id,
			@GraphQLName("OrderNote") OrderNote orderNote)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response upsertMediaType2OrderNote(
			@GraphQLName("id") String id,
			@GraphQLName("OrderNote") OrderNote orderNote)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response updateMediaType1BillingAddress(
			@GraphQLName("id") String id,
			@GraphQLName("Address") Address address)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response updateMediaType2BillingAddress(
			@GraphQLName("id") String id,
			@GraphQLName("Address") Address address)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response updateMediaType1ShippingAddress(
			@GraphQLName("id") String id,
			@GraphQLName("Address") Address address)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response updateMediaType2ShippingAddress(
			@GraphQLName("id") String id,
			@GraphQLName("Address") Address address)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLInvokeDetached
	public Response deletePriceEntry(@GraphQLName("id") String id)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLInvokeDetached
	public Response updateMediaType1PriceEntry(
			@GraphQLName("id") String id,
			@GraphQLName("PriceEntry") PriceEntry priceEntry)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLInvokeDetached
	public Response updateMediaType2PriceEntry(
			@GraphQLName("id") String id,
			@GraphQLName("PriceEntry") PriceEntry priceEntry)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response upsertMediaType1PriceEntry(
			@GraphQLName("groupId") Long groupId,
			@GraphQLName("PriceEntry") PriceEntry priceEntry)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response upsertMediaType2PriceEntry(
			@GraphQLName("groupId") Long groupId,
			@GraphQLName("PriceEntry") PriceEntry priceEntry)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLInvokeDetached
	public Response deletePriceList(@GraphQLName("id") String id)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLInvokeDetached
	public Response updateMediaType1PriceList(
			@GraphQLName("id") String id, @GraphQLName("groupId") Long groupId,
			@GraphQLName("PriceList") PriceList priceList)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLInvokeDetached
	public Response updateMediaType2PriceList(
			@GraphQLName("id") String id, @GraphQLName("groupId") Long groupId,
			@GraphQLName("PriceList") PriceList priceList)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response upsertMediaType1PriceList(
			@GraphQLName("groupId") Long groupId,
			@GraphQLName("PriceList") PriceList priceList)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response upsertMediaType2PriceList(
			@GraphQLName("groupId") Long groupId,
			@GraphQLName("PriceList") PriceList priceList)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLInvokeDetached
	public Response deleteProduct(@GraphQLName("id") String id)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLInvokeDetached
	public Response updateMediaType1Product(
			@GraphQLName("id") String id, @GraphQLName("groupId") Long groupId,
			@GraphQLName("Product") Product product)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLInvokeDetached
	public Response updateMediaType2Product(
			@GraphQLName("id") String id, @GraphQLName("groupId") Long groupId,
			@GraphQLName("Product") Product product)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response upsertMediaType1Product(
			@GraphQLName("groupId") Long groupId,
			@GraphQLName("Product") Product product)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response upsertMediaType2Product(
			@GraphQLName("groupId") Long groupId,
			@GraphQLName("Product") Product product)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response upsertMediaType1Sku(
			@GraphQLName("id") String id, @GraphQLName("groupId") Long groupId,
			@GraphQLName("Sku") Sku sku)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response upsertMediaType2Sku(
			@GraphQLName("id") String id, @GraphQLName("groupId") Long groupId,
			@GraphQLName("Sku") Sku sku)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLInvokeDetached
	public Response deleteProductOption(@GraphQLName("id") String id)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLInvokeDetached
	public Response updateMediaType1ProductOption(
			@GraphQLName("id") String id, @GraphQLName("groupId") Long groupId,
			@GraphQLName("ProductOption") ProductOption productOption)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLInvokeDetached
	public Response updateMediaType2ProductOption(
			@GraphQLName("id") String id, @GraphQLName("groupId") Long groupId,
			@GraphQLName("ProductOption") ProductOption productOption)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response upsertMediaType1ProductOption(
			@GraphQLName("groupId") Long groupId,
			@GraphQLName("ProductOption") ProductOption productOption)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response upsertMediaType2ProductOption(
			@GraphQLName("groupId") Long groupId,
			@GraphQLName("ProductOption") ProductOption productOption)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLInvokeDetached
	public Response deleteProductOptionValue(@GraphQLName("id") String id)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLInvokeDetached
	public Response updateMediaType1ProductOptionValue(
			@GraphQLName("id") String id, @GraphQLName("groupId") Long groupId,
			@GraphQLName("ProductOptionValue") ProductOptionValue
				productOptionValue)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLInvokeDetached
	public Response updateMediaType2ProductOptionValue(
			@GraphQLName("id") String id, @GraphQLName("groupId") Long groupId,
			@GraphQLName("ProductOptionValue") ProductOptionValue
				productOptionValue)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response upsertMediaType1ProductOptionValue(
			@GraphQLName("id") String id, @GraphQLName("groupId") Long groupId,
			@GraphQLName("ProductOptionValue") ProductOptionValue
				productOptionValue)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response upsertMediaType2ProductOptionValue(
			@GraphQLName("id") String id, @GraphQLName("groupId") Long groupId,
			@GraphQLName("ProductOptionValue") ProductOptionValue
				productOptionValue)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLInvokeDetached
	public Response deleteRegion(@GraphQLName("id") String id)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLInvokeDetached
	public Response updateMediaType1Region(
			@GraphQLName("id") String id, @GraphQLName("Region") Region region)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLInvokeDetached
	public Response updateMediaType2Region(
			@GraphQLName("id") String id, @GraphQLName("Region") Region region)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response upsertMediaType1Inventory(
			@GraphQLName("id") String id, @GraphQLName("groupId") Long groupId,
			@GraphQLName("Inventory") Inventory inventory)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response upsertMediaType2Inventory(
			@GraphQLName("id") String id, @GraphQLName("groupId") Long groupId,
			@GraphQLName("Inventory") Inventory inventory)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLInvokeDetached
	public Response deleteSku(@GraphQLName("id") String id) throws Exception {
		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLInvokeDetached
	public Response updateMediaType1Sku(
			@GraphQLName("id") String id, @GraphQLName("groupId") Long groupId,
			@GraphQLName("Sku") Sku sku)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLInvokeDetached
	public Response updateMediaType2Sku(
			@GraphQLName("id") String id, @GraphQLName("groupId") Long groupId,
			@GraphQLName("Sku") Sku sku)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLInvokeDetached
	public Response deleteUser(@GraphQLName("id") String id) throws Exception {
		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLInvokeDetached
	public Response updateMediaType1User(
			@GraphQLName("id") String id, @GraphQLName("User") User user)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLInvokeDetached
	public Response updateMediaType2User(
			@GraphQLName("id") String id, @GraphQLName("User") User user)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response upsertMediaType1User(@GraphQLName("User") User user)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Response upsertMediaType2User(@GraphQLName("User") User user)
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

}