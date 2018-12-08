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

package com.liferay.commerce.product.subscription.type.web.internal.display.context;

import com.liferay.commerce.product.subscription.type.web.internal.constants.CPSubscriptionTypeConstants;
import com.liferay.commerce.util.CommerceSubscriptionTypeUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

/**
 * @author Alessio Antonio Rendina
 */
public class MonthlyCPSubscriptionTypeDisplayContext {

	public MonthlyCPSubscriptionTypeDisplayContext(Object object) {
		_object = object;
	}

	public int getMonthDay() {
		UnicodeProperties subscriptionTypeSettingsProperties =
			CommerceSubscriptionTypeUtil.getSubscriptionTypeSettingsProperties(
				_object);

		if ((subscriptionTypeSettingsProperties == null) ||
			subscriptionTypeSettingsProperties.isEmpty()) {

			return 1;
		}

		return GetterUtil.getInteger(
			subscriptionTypeSettingsProperties.get("monthDay"));
	}

	public int getSelectedMonthlyMode() {
		UnicodeProperties subscriptionTypeSettingsProperties =
			CommerceSubscriptionTypeUtil.getSubscriptionTypeSettingsProperties(
				_object);

		if (subscriptionTypeSettingsProperties == null) {
			return CPSubscriptionTypeConstants.MODE_ORDER_DATE;
		}

		return GetterUtil.getInteger(
			subscriptionTypeSettingsProperties.get("monthlyMode"));
	}

	private final Object _object;

}