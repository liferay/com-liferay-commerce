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

import com.liferay.commerce.frontend.util.ProductHelper;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 * @author Alec Sloan
 */
@Component(immediate = true, service = ProductHelperProvider.class)
public class ProductHelperProvider {

	public static ProductHelper getProductHelper() {
		if (_productHelperProvider == null) {
			return null;
		}

		return _productHelperProvider._productHelper;
	}

	protected static void setProductHelperProvider(
		ProductHelperProvider productHelperProvider) {

		_productHelperProvider = productHelperProvider;
	}

	@Activate
	protected void activate() {
	}

	@Reference(unbind = "-")
	protected void setProductHelper(ProductHelper productHelper) {
		_productHelper = productHelper;
	}

	private static ProductHelperProvider _productHelperProvider;

	private ProductHelper _productHelper;

}