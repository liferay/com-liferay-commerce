package com.liferay.headless.commerce.core.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static int DEFAULT_INCREMENT_DAY = 30;

	/**
	 * Add increment days to the date.
	 * Negative increment subracts days to the date.
	 *
	 * @param date
	 * @param increment
	 * */
	public static Date addDays(Date date, int increment) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(date);
		cal.add(Calendar.DATE, increment);

		return cal.getTime();
	}

}