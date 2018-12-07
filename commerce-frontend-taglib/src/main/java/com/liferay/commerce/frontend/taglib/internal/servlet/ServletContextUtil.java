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

import com.liferay.commerce.data.provider.CommerceDataProviderRegistry;
import com.liferay.commerce.frontend.taglib.internal.table.ClayTableUtil;
import com.liferay.commerce.frontend.taglib.table.ClayTableRegistry;
import com.liferay.commerce.frontend.taglib.table.ClayTableSerializer;

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

	public static final ClayTableRegistry getClayTableRegistry() {
		return _instance._getClayTableRegistry();
	}

	public static final ClayTableSerializer getClayTableSerializer() {
		return _instance._getClayTableSerializer();
	}

	public static final ClayTableUtil getClayTableUtil() {
		return _instance._getClayTableUtil();
	}

	public static final CommerceDataProviderRegistry
		getCommerceDataProviderRegistry() {

		return _instance._getCommerceDataProviderRegistry();
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
	protected void setClayTableRegistry(ClayTableRegistry clayTableRegistry) {
		_clayTableRegistry = clayTableRegistry;
	}

	@Reference(unbind = "-")
	protected void setClayTableSerializer(
		ClayTableSerializer clayTableSerializer) {

		_clayTableSerializer = clayTableSerializer;
	}

	@Reference(unbind = "-")
	protected void setClayTableUtil(ClayTableUtil clayTableUtil) {
		_clayTableUtil = clayTableUtil;
	}

	@Reference(unbind = "-")
	protected void setCommerceDataProviderRegistry(
		CommerceDataProviderRegistry commerceDataProviderRegistry) {

		_commerceDataProviderRegistry = commerceDataProviderRegistry;
	}

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.commerce.cart.taglib)",
		unbind = "-"
	)
	protected void setServletContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}

	private ClayTableRegistry _getClayTableRegistry() {
		return _clayTableRegistry;
	}

	private ClayTableSerializer _getClayTableSerializer() {
		return _clayTableSerializer;
	}

	private ClayTableUtil _getClayTableUtil() {
		return _clayTableUtil;
	}

	private CommerceDataProviderRegistry _getCommerceDataProviderRegistry() {
		return _commerceDataProviderRegistry;
	}

	private ServletContext _getServletContext() {
		return _servletContext;
	}

	private static ServletContextUtil _instance;

	private ClayTableRegistry _clayTableRegistry;
	private ClayTableSerializer _clayTableSerializer;
	private ClayTableUtil _clayTableUtil;
	private CommerceDataProviderRegistry _commerceDataProviderRegistry;
	private ServletContext _servletContext;

}