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

package com.liferay.commerce.account.constants;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class CommerceAccountConstants {

	public static final String ACCOUNT_ADMINISTRATOR_ROLE_NAME =
		"Account Administrator";

	public static final String ACCOUNT_MANAGER_ROLE_NAME = "Account Manager";

	public static final int ACCOUNT_ROLE_TYPE = 100;

	public static final int ACCOUNT_TYPE_BUSINESS = 2;

	public static final int ACCOUNT_TYPE_GUEST = 0;

	public static final int ACCOUNT_TYPE_PERSONAL = 1;

	public static final String BUYER_ROLE_NAME = "Buyer";

	public static final long DEFAULT_PARENT_ACCOUNT_ID = 0;

	public static final long GUEST_ACCOUNT_ID = -1;

	public static final String ORDER_MANAGER_ROLE_NAME = "Order Manager";

	public static final String RESOURCE_NAME = "com.liferay.commerce.account";

	public static final String SERVICE_NAME = "com.liferay.commerce.account";

	public static final int SITE_TYPE_B2B = 1;

	public static final int SITE_TYPE_B2C = 0;

	public static final int SITE_TYPE_B2C_B2B = 2;

	public static final int[] SITE_TYPES = {
		SITE_TYPE_B2C, SITE_TYPE_B2B, SITE_TYPE_B2C_B2B
	};

	public static String getSiteTypeLabel(int siteType) {
		if (siteType == SITE_TYPE_B2C) {
			return "b2c";
		}
		else if (siteType == SITE_TYPE_B2B) {
			return "b2b";
		}
		else if (siteType == SITE_TYPE_B2C_B2B) {
			return "b2c-b2b";
		}

		return null;
	}

}