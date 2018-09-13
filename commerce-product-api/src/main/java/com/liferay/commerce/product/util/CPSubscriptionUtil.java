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

package com.liferay.commerce.product.util;

import com.liferay.commerce.product.constants.CPConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Alessio Antonio Rendina
 */
public class CPSubscriptionUtil {

	public static Date getSubscriptionNextIterationDate(
			long userId, long subscriptionCycleLength,
			String subscriptionCyclePeriod)
		throws PortalException {

		User user = UserLocalServiceUtil.getUser(userId);

		GregorianCalendar gregorianCalendar = new GregorianCalendar(
			user.getTimeZone());

		gregorianCalendar.setTime(new Date());

		int amount = (int)subscriptionCycleLength;

		if (subscriptionCyclePeriod.equals(CPConstants.SUBSCRIPTION_DAY)) {
			gregorianCalendar.add(Calendar.DAY_OF_MONTH, amount);

			return gregorianCalendar.getTime();
		}
		else if (subscriptionCyclePeriod.equals(
					 CPConstants.SUBSCRIPTION_WEEK)) {

			gregorianCalendar.add(Calendar.WEEK_OF_MONTH, amount);

			return gregorianCalendar.getTime();
		}
		else if (subscriptionCyclePeriod.equals(
					 CPConstants.SUBSCRIPTION_MONTH)) {

			gregorianCalendar.add(Calendar.MONTH, amount);

			return gregorianCalendar.getTime();
		}
		else if (subscriptionCyclePeriod.equals(
					 CPConstants.SUBSCRIPTION_YEAR)) {

			gregorianCalendar.add(Calendar.YEAR, amount);

			return gregorianCalendar.getTime();
		}

		return null;
	}

}