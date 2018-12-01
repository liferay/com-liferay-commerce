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

package com.liferay.commerce.data.integration.manager.constants;

/**
 * @author guywandji
 */
public enum Frequency {

	EXECUTE_ONCE("execute-once", "0 2 2 11 *"), HOURLY("hourly", "0 * * * *"),
	MONTHLY("monthly", "0 0 * 1-12 *");

	public static Frequency getByName(String name) {
		for (Frequency frequency : Frequency.values()) {
			if (name.equals(frequency.getName())) {
				return frequency;
			}
		}

		return EXECUTE_ONCE;
	}

	public String getCronExpression() {
		return _cronExpression;
	}

	public String getName() {
		return _name;
	}

	private Frequency(String name, String cronExpression) {
		_name = name;
		_cronExpression = cronExpression;
	}

	private final String _cronExpression;
	private final String _name;

}