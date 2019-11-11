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

import com.liferay.commerce.machine.learning.forecast.model.CommerceAccountCommerceMLForecast;
import com.liferay.commerce.machine.learning.forecast.service.CommerceAccountCommerceMLForecastService;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterContext;
import com.liferay.headless.commerce.machine.learning.dto.v1_0.AccountForecast;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Ferrari
 */
@Component(
	property = "model.class.name=com.liferay.commerce.machine.learning.forecast.model.CommerceAccountCommerceMLForecast",
	service = {AccountForecastDTOConverter.class, DTOConverter.class}
)
public class AccountForecastDTOConverter implements DTOConverter {

	@Override
	public String getContentType() {
		return CommerceAccountCommerceMLForecast.class.getSimpleName();
	}

	@Override
	public Object toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CommerceMLForecastCompositeResourcePrimaryKey compositeResourcePrimKey =
			(CommerceMLForecastCompositeResourcePrimaryKey)
				dtoConverterContext.getCompositeResourcePrimKey();

		CommerceAccountCommerceMLForecast commerceAccountCommerceMLForecast =
			_commerceAccountCommerceMLForecastService.
				getCommerceAccountCommerceMLForecast(
					compositeResourcePrimKey.getCompanyId(),
					compositeResourcePrimKey.getForecastId());

		return new AccountForecast() {
			{
				account =
					commerceAccountCommerceMLForecast.getCommerceAccountId();
				actual = commerceAccountCommerceMLForecast.getActual();
				forecast = commerceAccountCommerceMLForecast.getForecast();
				forecastLowerBound =
					commerceAccountCommerceMLForecast.getForecastLowerBound();
				forecastUpperBound =
					commerceAccountCommerceMLForecast.getForecastUpperBound();
				timestamp = commerceAccountCommerceMLForecast.getTimestamp();
				unit = commerceAccountCommerceMLForecast.getTarget();
			}
		};
	}

	@Reference
	private CommerceAccountCommerceMLForecastService
		_commerceAccountCommerceMLForecastService;

}