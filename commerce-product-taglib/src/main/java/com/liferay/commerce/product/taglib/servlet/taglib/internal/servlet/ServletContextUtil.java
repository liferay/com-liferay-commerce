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

package com.liferay.commerce.product.taglib.servlet.taglib.internal.servlet;

import com.liferay.commerce.product.content.render.list.CPContentListRendererRegistry;
import com.liferay.commerce.product.content.render.list.entry.CPContentListEntryRendererRegistry;
import com.liferay.commerce.product.content.util.CPHttpContentHelper;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = ServletContextUtil.class)
public class ServletContextUtil {

	public static final CPContentListEntryRendererRegistry
		getCPContentListEntryRendererRegistry() {

		return _instance._getCPContentListEntryRendererRegistry();
	}

	public static final CPContentListRendererRegistry
		getCPContentListRendererRegistry() {

		return _instance._getCPContentListRendererRegistry();
	}

	public static final CPHttpContentHelper getCPHttpContentHelper() {
		return _instance._getCPHttpContentHelper();
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
	protected void setCPContentListEntryRendererRegistry(
		CPContentListEntryRendererRegistry cpContentListEntryRendererRegistry) {

		_cpContentListEntryRendererRegistry =
			cpContentListEntryRendererRegistry;
	}

	@Reference(unbind = "-")
	protected void setCPContentListRendererRegistry(
		CPContentListRendererRegistry cpContentListRendererRegistry) {

		_cpContentListRendererRegistry = cpContentListRendererRegistry;
	}

	@Reference(unbind = "-")
	protected void setCPHttpContentHelper(
		CPHttpContentHelper cpHttpContentHelper) {

		_cpHttpContentHelper = cpHttpContentHelper;
	}

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.commerce.product.taglib)",
		unbind = "-"
	)
	protected void setServletContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}

	private CPContentListEntryRendererRegistry
		_getCPContentListEntryRendererRegistry() {

		return _cpContentListEntryRendererRegistry;
	}

	private CPContentListRendererRegistry _getCPContentListRendererRegistry() {
		return _cpContentListRendererRegistry;
	}

	private CPHttpContentHelper _getCPHttpContentHelper() {
		return _cpHttpContentHelper;
	}

	private ServletContext _getServletContext() {
		return _servletContext;
	}

	private static ServletContextUtil _instance;

	private CPContentListEntryRendererRegistry
		_cpContentListEntryRendererRegistry;
	private CPContentListRendererRegistry _cpContentListRendererRegistry;
	private CPHttpContentHelper _cpHttpContentHelper;
	private ServletContext _servletContext;

}