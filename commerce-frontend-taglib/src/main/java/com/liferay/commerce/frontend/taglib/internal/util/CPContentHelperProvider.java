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

package com.liferay.commerce.frontend.taglib.internal.util;

import com.liferay.commerce.product.content.util.CPContentHelper;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 * @author Alec Sloan
 */
@Component(immediate = true, service = CPContentHelperProvider.class)
public class CPContentHelperProvider {

	public static CPContentHelper getCPContentHelper() {
		if (_cpContentHelperProvider == null) {
			return null;
		}

		return _cpContentHelperProvider._cpContentHelper;
	}

	protected static void setCPContentHelperProvider(
		CPContentHelperProvider cpContentHelperProvider) {

		_cpContentHelperProvider = cpContentHelperProvider;
	}

	@Activate
	protected void activate() {
	}

	@Reference(unbind = "-")
	protected void setProductHelper(CPContentHelper cpContentHelper) {
		_cpContentHelper = cpContentHelper;
	}

	private static CPContentHelperProvider _cpContentHelperProvider;

	private CPContentHelper _cpContentHelper;

}