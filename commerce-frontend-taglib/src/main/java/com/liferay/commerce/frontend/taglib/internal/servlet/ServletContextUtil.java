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

package com.liferay.commerce.frontend.taglib.internal.servlet;

import com.liferay.commerce.frontend.ClayTableContextContributorRegistry;
import com.liferay.commerce.frontend.ClayTableDataJSONBuilder;
import com.liferay.commerce.frontend.ClayTableRegistry;
import com.liferay.commerce.frontend.ClayTableSerializer;
import com.liferay.commerce.frontend.CommerceDataProviderRegistry;
import com.liferay.commerce.frontend.FilterFactoryRegistry;
import com.liferay.commerce.order.CommerceOrderHttpHelper;
import com.liferay.commerce.price.CommerceProductPriceCalculation;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(immediate = true, service = ServletContextUtil.class)
public class ServletContextUtil {

	public static final ClayTableContextContributorRegistry
		getClayTableContextContributorRegistry() {

		return _instance._getClayTableContextContributorRegistry();
	}

	public static final ClayTableDataJSONBuilder getClayTableDataJSONBuilder() {
		return _instance._getClayTableDataJSONBuilder();
	}

	public static final ClayTableRegistry getClayTableRegistry() {
		return _instance._getClayTableRegistry();
	}

	public static final ClayTableSerializer getClayTableSerializer() {
		return _instance._getClayTableSerializer();
	}

	public static final CommerceDataProviderRegistry
		getCommerceDataProviderRegistry() {

		return _instance._getCommerceDataProviderRegistry();
	}

	public static final CommerceOrderHttpHelper getCommerceOrderHttpHelper() {
		return _instance._getCommerceOrderHttpHelper();
	}

	public static final CommerceProductPriceCalculation
		getCommerceProductPriceCalculation() {

		return _instance._getCommerceProductPriceCalculation();
	}

	public static final FilterFactoryRegistry getFilterFactoryRegistry() {
		return _instance._getFilterFactoryRegistry();
	}

	public static final ServletContext getServletContext() {
		return _instance._getServletContext();
	}

	@Activate
	protected void activate() {
		_instance = this;
	}

	@Deactivate
	protected void deactivate() {
		_instance = null;
	}

	@Reference(unbind = "-")
	protected void setClayTableContextContributorRegistry(
		ClayTableContextContributorRegistry
			clayTableContextContributorRegistry) {

		_clayTableContextContributorRegistry =
			clayTableContextContributorRegistry;
	}

	@Reference(unbind = "-")
	protected void setClayTableDataJSONBuilder(
		ClayTableDataJSONBuilder clayTableDataJSONBuilder) {

		_clayTableDataJSONBuilder = clayTableDataJSONBuilder;
	}

	@Reference(unbind = "-")
	protected void setClayTableRegistry(ClayTableRegistry clayTableRegistry) {
		_clayTableRegistry = clayTableRegistry;
	}

	@Reference(unbind = "-")
	protected void setClayTableSerializer(
		ClayTableSerializer clayTableSerializer) {

		_clayTableSerializer = clayTableSerializer;
	}

	@Reference(unbind = "-")
	protected void setCommerceDataProviderRegistry(
		CommerceDataProviderRegistry commerceDataProviderRegistry) {

		_commerceDataProviderRegistry = commerceDataProviderRegistry;
	}

	@Reference(unbind = "-")
	protected void setCommerceOrderHttpHelper(
		CommerceOrderHttpHelper commerceOrderHttpHelper) {

		_commerceOrderHttpHelper = commerceOrderHttpHelper;
	}

	@Reference(unbind = "-")
	protected void setCommerceProductPriceCalculation(
		CommerceProductPriceCalculation commerceProductPriceCalculation) {

		_commerceProductPriceCalculation = commerceProductPriceCalculation;
	}

	@Reference(unbind = "-")
	protected void setFilterFactoryRegistry(
		FilterFactoryRegistry filterFactoryRegistry) {

		_filterFactoryRegistry = filterFactoryRegistry;
	}

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.commerce.frontend.taglib)",
		unbind = "-"
	)
	protected void setServletContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}

	private ClayTableContextContributorRegistry
		_getClayTableContextContributorRegistry() {

		return _clayTableContextContributorRegistry;
	}

	private ClayTableDataJSONBuilder _getClayTableDataJSONBuilder() {
		return _clayTableDataJSONBuilder;
	}

	private ClayTableRegistry _getClayTableRegistry() {
		return _clayTableRegistry;
	}

	private ClayTableSerializer _getClayTableSerializer() {
		return _clayTableSerializer;
	}

	private CommerceDataProviderRegistry _getCommerceDataProviderRegistry() {
		return _commerceDataProviderRegistry;
	}

	private CommerceOrderHttpHelper _getCommerceOrderHttpHelper() {
		return _commerceOrderHttpHelper;
	}

	private CommerceProductPriceCalculation
		_getCommerceProductPriceCalculation() {

		return _commerceProductPriceCalculation;
	}

	private FilterFactoryRegistry _getFilterFactoryRegistry() {
		return _filterFactoryRegistry;
	}

	private ServletContext _getServletContext() {
		return _servletContext;
	}

	private static ServletContextUtil _instance;

	private ClayTableContextContributorRegistry
		_clayTableContextContributorRegistry;
	private ClayTableDataJSONBuilder _clayTableDataJSONBuilder;
	private ClayTableRegistry _clayTableRegistry;
	private ClayTableSerializer _clayTableSerializer;
	private CommerceDataProviderRegistry _commerceDataProviderRegistry;
	private CommerceOrderHttpHelper _commerceOrderHttpHelper;
	private CommerceProductPriceCalculation _commerceProductPriceCalculation;
	private FilterFactoryRegistry _filterFactoryRegistry;
	private ServletContext _servletContext;

}