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

package com.liferay.commerce.product.constants;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class CPRuleConstants {

	public static final int APPLICATION_TYPE_ALL = 0;

	public static final int APPLICATION_TYPE_ANY = 1;

	public static final int[] APPLICATION_TYPES = {
		APPLICATION_TYPE_ALL, APPLICATION_TYPE_ANY
	};

	public static final int SCOPE_COMPANY = 1;

	public static final int SCOPE_GROUP = 2;

	public static final String SERVICE_NAME =
		"com.liferay.commerce.product.rule";

	public static final String TYPE_ALL_PRODUCTS = "all-products";

	public static final String TYPE_ASSET_CATEGORY = "category";

	public static String getApplicationTypeLabel(int applicationType) {
		if (applicationType == APPLICATION_TYPE_ALL) {
			return "all-catalog-rules-must-be-satisfied";
		}
		else if (applicationType == APPLICATION_TYPE_ANY) {
			return "one-or-more-catalog-rules-must-be-satisfied";
		}

		return null;
	}

}