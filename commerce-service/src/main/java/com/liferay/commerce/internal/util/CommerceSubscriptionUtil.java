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

package com.liferay.commerce.internal.util;

import com.liferay.commerce.product.constants.CPConstants;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceSubscriptionUtil {

	public static Date getSubscriptionNextIterationDate(
		TimeZone timeZone, long subscriptionCycleLength,
		String subscriptionCyclePeriod) {

		GregorianCalendar gregorianCalendar = new GregorianCalendar(timeZone);

		gregorianCalendar.setTime(new Date());

		int amount = (int)subscriptionCycleLength;

		if (subscriptionCyclePeriod.equals(
				CPConstants.SUBSCRIPTION_CYCLE_DAY)) {

			gregorianCalendar.add(Calendar.DAY_OF_MONTH, amount);

			return gregorianCalendar.getTime();
		}
		else if (subscriptionCyclePeriod.equals(
					CPConstants.SUBSCRIPTION_CYCLE_WEEK)) {

			gregorianCalendar.add(Calendar.WEEK_OF_YEAR, amount);

			return gregorianCalendar.getTime();
		}
		else if (subscriptionCyclePeriod.equals(
					CPConstants.SUBSCRIPTION_CYCLE_MONTH)) {

			gregorianCalendar.add(Calendar.MONTH, amount);

			return gregorianCalendar.getTime();
		}
		else if (subscriptionCyclePeriod.equals(
					CPConstants.SUBSCRIPTION_CYCLE_YEAR)) {

			gregorianCalendar.add(Calendar.YEAR, amount);

			return gregorianCalendar.getTime();
		}

		return null;
	}

}