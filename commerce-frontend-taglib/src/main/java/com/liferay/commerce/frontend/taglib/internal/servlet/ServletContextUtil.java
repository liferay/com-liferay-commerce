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
import com.liferay.commerce.frontend.util.ProductHelper;
import com.liferay.commerce.order.CommerceOrderHttpHelper;
import com.liferay.commerce.product.content.util.CPContentHelper;
import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;

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

		return _servletContextUtil._getClayTableContextContributorRegistry();
	}

	public static final ClayTableDataJSONBuilder getClayTableDataJSONBuilder() {
		return _servletContextUtil._getClayTableDataJSONBuilder();
	}

	public static final ClayTableRegistry getClayTableRegistry() {
		return _servletContextUtil._getClayTableRegistry();
	}

	public static final ClayTableSerializer getClayTableSerializer() {
		return _servletContextUtil._getClayTableSerializer();
	}

	public static final CommerceDataProviderRegistry
		getCommerceDataProviderRegistry() {

		return _servletContextUtil._getCommerceDataProviderRegistry();
	}

	public static final CommerceOrderHttpHelper getCommerceOrderHttpHelper() {
		return _servletContextUtil._getCommerceOrderHttpHelper();
	}

	public static final ConfigurationProvider getConfigurationProvider() {
		return _servletContextUtil._getConfigurationProvider();
	}

	public static final CPContentHelper getCPContentHelper() {
		return _servletContextUtil._getCPContentHelper();
	}

	public static final FilterFactoryRegistry getFilterFactoryRegistry() {
		return _servletContextUtil._getFilterFactoryRegistry();
	}

	public static final NPMResolver getNPMResolver() {
		return _servletContextUtil._getNPMResolver();
	}

	public static final ProductHelper getProductHelper() {
		return _servletContextUtil._getProductHelper();
	}

	public static final ServletContext getServletContext() {
		return _servletContextUtil._getServletContext();
	}

	@Activate
	protected void activate() {
		_servletContextUtil = this;
	}

	@Deactivate
	protected void deactivate() {
		_servletContextUtil = null;
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
	protected void setConfigurationProvider(
		ConfigurationProvider configurationProvider) {

		_configurationProvider = configurationProvider;
	}

	@Reference(unbind = "-")
	protected void setCPContentHelper(CPContentHelper cpContentHelper) {
		_cpContentHelper = cpContentHelper;
	}

	@Reference(unbind = "-")
	protected void setFilterFactoryRegistry(
		FilterFactoryRegistry filterFactoryRegistry) {

		_filterFactoryRegistry = filterFactoryRegistry;
	}

	@Reference(unbind = "-")
	protected void setNPMResolver(NPMResolver npmResolver) {
		_npmResolver = npmResolver;
	}

	@Reference(unbind = "-")
	protected void setProductHelper(ProductHelper productHelper) {
		_productHelper = productHelper;
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

	private ConfigurationProvider _getConfigurationProvider() {
		return _configurationProvider;
	}

	private CPContentHelper _getCPContentHelper() {
		return _cpContentHelper;
	}

	private FilterFactoryRegistry _getFilterFactoryRegistry() {
		return _filterFactoryRegistry;
	}

	private NPMResolver _getNPMResolver() {
		return _npmResolver;
	}

	private ProductHelper _getProductHelper() {
		return _productHelper;
	}

	private ServletContext _getServletContext() {
		return _servletContext;
	}

	private static ServletContextUtil _servletContextUtil;

	private ClayTableContextContributorRegistry
		_clayTableContextContributorRegistry;
	private ClayTableDataJSONBuilder _clayTableDataJSONBuilder;
	private ClayTableRegistry _clayTableRegistry;
	private ClayTableSerializer _clayTableSerializer;
	private CommerceDataProviderRegistry _commerceDataProviderRegistry;
	private CommerceOrderHttpHelper _commerceOrderHttpHelper;
	private ConfigurationProvider _configurationProvider;
	private CPContentHelper _cpContentHelper;
	private FilterFactoryRegistry _filterFactoryRegistry;
	private NPMResolver _npmResolver;
	private ProductHelper _productHelper;
	private ServletContext _servletContext;

}