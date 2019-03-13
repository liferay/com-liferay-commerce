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

package com.liferay.headless.commerce.admin.internal.graphql.servlet.v2_0;

import com.liferay.headless.commerce.admin.internal.graphql.mutation.v2_0.Mutation;
import com.liferay.headless.commerce.admin.internal.graphql.query.v2_0.Query;
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
import com.liferay.portal.vulcan.graphql.servlet.ServletData;

import javax.annotation.Generated;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceScope;

/**
 * @author Igor Beslic
 * @generated
 */
@Component(immediate = true, service = ServletData.class)
@Generated("")
public class ServletDataImpl implements ServletData {

	@Activate
	public void activate(BundleContext bundleContext) {
		Mutation.setAccountResourceComponentServiceObjects(
			_accountResourceComponentServiceObjects);
		Mutation.setCountryResourceComponentServiceObjects(
			_countryResourceComponentServiceObjects);
		Mutation.setCurrencyResourceComponentServiceObjects(
			_currencyResourceComponentServiceObjects);
		Mutation.setInventoryResourceComponentServiceObjects(
			_inventoryResourceComponentServiceObjects);
		Mutation.setOrderResourceComponentServiceObjects(
			_orderResourceComponentServiceObjects);
		Mutation.setPriceEntryResourceComponentServiceObjects(
			_priceEntryResourceComponentServiceObjects);
		Mutation.setPriceListResourceComponentServiceObjects(
			_priceListResourceComponentServiceObjects);
		Mutation.setProductResourceComponentServiceObjects(
			_productResourceComponentServiceObjects);
		Mutation.setProductOptionResourceComponentServiceObjects(
			_productOptionResourceComponentServiceObjects);
		Mutation.setProductOptionValueResourceComponentServiceObjects(
			_productOptionValueResourceComponentServiceObjects);
		Mutation.setRegionResourceComponentServiceObjects(
			_regionResourceComponentServiceObjects);
		Mutation.setSkuResourceComponentServiceObjects(
			_skuResourceComponentServiceObjects);
		Mutation.setUserResourceComponentServiceObjects(
			_userResourceComponentServiceObjects);

		Query.setAccountResourceComponentServiceObjects(
			_accountResourceComponentServiceObjects);
		Query.setCountryResourceComponentServiceObjects(
			_countryResourceComponentServiceObjects);
		Query.setCurrencyResourceComponentServiceObjects(
			_currencyResourceComponentServiceObjects);
		Query.setInventoryResourceComponentServiceObjects(
			_inventoryResourceComponentServiceObjects);
		Query.setOrderResourceComponentServiceObjects(
			_orderResourceComponentServiceObjects);
		Query.setPriceEntryResourceComponentServiceObjects(
			_priceEntryResourceComponentServiceObjects);
		Query.setPriceListResourceComponentServiceObjects(
			_priceListResourceComponentServiceObjects);
		Query.setProductResourceComponentServiceObjects(
			_productResourceComponentServiceObjects);
		Query.setProductOptionResourceComponentServiceObjects(
			_productOptionResourceComponentServiceObjects);
		Query.setProductOptionValueResourceComponentServiceObjects(
			_productOptionValueResourceComponentServiceObjects);
		Query.setRegionResourceComponentServiceObjects(
			_regionResourceComponentServiceObjects);
		Query.setSkuResourceComponentServiceObjects(
			_skuResourceComponentServiceObjects);
		Query.setUserResourceComponentServiceObjects(
			_userResourceComponentServiceObjects);
		Query.setWebSiteResourceComponentServiceObjects(
			_webSiteResourceComponentServiceObjects);
	}

	@Override
	public Mutation getMutation() {
		return new Mutation();
	}

	@Override
	public String getPath() {
		return "/headless-commerce-admin-graphql/v2_0";
	}

	@Override
	public Query getQuery() {
		return new Query();
	}

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<AccountResource>
		_accountResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<CountryResource>
		_countryResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<CurrencyResource>
		_currencyResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<InventoryResource>
		_inventoryResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<OrderResource>
		_orderResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<PriceEntryResource>
		_priceEntryResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<PriceListResource>
		_priceListResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<ProductResource>
		_productResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<ProductOptionResource>
		_productOptionResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<ProductOptionValueResource>
		_productOptionValueResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<RegionResource>
		_regionResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<SkuResource>
		_skuResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<UserResource>
		_userResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<WebSiteResource>
		_webSiteResourceComponentServiceObjects;

}