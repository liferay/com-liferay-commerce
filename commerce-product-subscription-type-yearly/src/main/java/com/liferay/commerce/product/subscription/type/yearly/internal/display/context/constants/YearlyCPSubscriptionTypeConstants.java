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

package com.liferay.commerce.product.subscription.type.yearly.internal.display.context.constants;

/**
 * @author Alessio Antonio Rendina
 */
public class YearlyCPSubscriptionTypeConstants {

	public static final int MODE_EXACT_DAY_OF_YEAR = 1;

	public static final int MODE_ORDER_DATE = 0;

	public static final int[] MODES = {MODE_ORDER_DATE, MODE_EXACT_DAY_OF_YEAR};

	public static String getMonthlyCPSubscriptionTypeModeLabel(int mode) {
		if (mode == MODE_EXACT_DAY_OF_YEAR) {
			return "exact-day-of-year";
		}
		else if (mode == MODE_ORDER_DATE) {
			return "order-date";
		}

		return null;
	}

}