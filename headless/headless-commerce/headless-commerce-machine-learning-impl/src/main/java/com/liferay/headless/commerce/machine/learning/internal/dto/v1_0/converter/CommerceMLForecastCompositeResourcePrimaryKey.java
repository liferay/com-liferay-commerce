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

package com.liferay.headless.commerce.machine.learning.internal.dto.v1_0.converter;

/**
 * @author Riccardo Ferrari
 */
public class CommerceMLForecastCompositeResourcePrimaryKey {

	public CommerceMLForecastCompositeResourcePrimaryKey(
		long companyId, long forecastId) {

		_companyId = companyId;
		_forecastId = forecastId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public long getForecastId() {
		return _forecastId;
	}

	private final long _companyId;
	private final long _forecastId;

}