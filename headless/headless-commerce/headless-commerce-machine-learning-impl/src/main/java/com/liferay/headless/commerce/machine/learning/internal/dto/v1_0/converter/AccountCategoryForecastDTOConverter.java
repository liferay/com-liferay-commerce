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

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.commerce.machine.learning.forecast.model.AssetCategoryCommerceMLForecast;
import com.liferay.commerce.machine.learning.forecast.service.AssetCategoryCommerceMLForecastService;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterContext;
import com.liferay.headless.commerce.machine.learning.dto.v1_0.AccountCategoryForecast;
import com.liferay.portal.kernel.util.LocaleUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Ferrari
 */
@Component(
	property = "model.class.name=com.liferay.commerce.machine.learning.forecast.model.AssetCategoryCommerceMLForecast",
	service = {AccountCategoryForecastDTOConverter.class, DTOConverter.class}
)
public class AccountCategoryForecastDTOConverter implements DTOConverter {

	@Override
	public String getContentType() {
		return AssetCategoryCommerceMLForecast.class.getSimpleName();
	}

	@Override
	public Object toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CommerceMLForecastCompositeResourcePrimaryKey compositeResourcePrimKey =
			(CommerceMLForecastCompositeResourcePrimaryKey)
				dtoConverterContext.getCompositeResourcePrimKey();

		AssetCategoryCommerceMLForecast assetCategoryCommerceMLForecast =
			_assetCategoryCommerceMLForecastService.
				getAssetCategoryCommerceMLForecast(
					compositeResourcePrimKey.getCompanyId(),
					compositeResourcePrimKey.getForecastId());

		AssetCategory assetCategory =
			_assetCategoryLocalService.fetchAssetCategory(
				assetCategoryCommerceMLForecast.getAssetCategoryId());

		return new AccountCategoryForecast() {
			{
				account =
					assetCategoryCommerceMLForecast.getCommerceAccountId();
				actual = assetCategoryCommerceMLForecast.getActual();
				category = assetCategoryCommerceMLForecast.getAssetCategoryId();

				if (assetCategory != null) {
					categoryTitle = assetCategory.getTitle(
						LocaleUtil.toLanguageId(
							dtoConverterContext.getLocale()));
				}

				forecast = assetCategoryCommerceMLForecast.getForecast();
				forecastLowerBound =
					assetCategoryCommerceMLForecast.getForecastLowerBound();
				forecastUpperBound =
					assetCategoryCommerceMLForecast.getForecastUpperBound();
				timestamp = assetCategoryCommerceMLForecast.getTimestamp();
				unit = assetCategoryCommerceMLForecast.getTarget();
			}
		};
	}

	@Reference
	private AssetCategoryCommerceMLForecastService
		_assetCategoryCommerceMLForecastService;

	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;

}