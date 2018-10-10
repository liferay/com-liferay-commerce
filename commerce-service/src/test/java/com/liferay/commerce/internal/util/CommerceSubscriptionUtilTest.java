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
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.TimeZoneUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceSubscriptionUtilTest {

	@Test
	public void testGetSubscriptionNextIterationDateDay() {
		_testGetSubscriptionNextIterationDate(
			CPConstants.SUBSCRIPTION_CYCLE_DAY, Calendar.DAY_OF_MONTH);
	}

	@Test
	public void testGetSubscriptionNextIterationDateMonth() {
		_testGetSubscriptionNextIterationDate(
			CPConstants.SUBSCRIPTION_CYCLE_MONTH, Calendar.MONTH);
	}

	@Test
	public void testGetSubscriptionNextIterationDateWeek() {
		_testGetSubscriptionNextIterationDate(
			CPConstants.SUBSCRIPTION_CYCLE_WEEK, Calendar.WEEK_OF_YEAR);
	}

	@Test
	public void testGetSubscriptionNextIterationDateYear() {
		_testGetSubscriptionNextIterationDate(
			CPConstants.SUBSCRIPTION_CYCLE_YEAR, Calendar.YEAR);
	}

	private void _testGetSubscriptionNextIterationDate(
		String subscriptionCyclePeriod, int calendarField) {

		GregorianCalendar gregorianCalendar = new GregorianCalendar(
			TimeZoneUtil.GMT);

		int length = RandomTestUtil.randomInt();

		Date subscriptionNextIterationDate =
			CommerceSubscriptionUtil.getSubscriptionNextIterationDate(
				TimeZoneUtil.GMT, length, subscriptionCyclePeriod);

		gregorianCalendar.setTime(subscriptionNextIterationDate);

		int actualDayOfMonth = gregorianCalendar.get(Calendar.DAY_OF_MONTH);
		int actualMonth = gregorianCalendar.get(Calendar.MONTH);
		int actualYear = gregorianCalendar.get(Calendar.YEAR);

		gregorianCalendar.setTime(new Date());

		gregorianCalendar.add(calendarField, length);

		int expectedDayOfMonth = gregorianCalendar.get(Calendar.DAY_OF_MONTH);
		int expectedMonth = gregorianCalendar.get(Calendar.MONTH);
		int expectedYear = gregorianCalendar.get(Calendar.YEAR);

		Assert.assertEquals(expectedDayOfMonth, actualDayOfMonth);
		Assert.assertEquals(expectedMonth, actualMonth);
		Assert.assertEquals(expectedYear, actualYear);
	}

}