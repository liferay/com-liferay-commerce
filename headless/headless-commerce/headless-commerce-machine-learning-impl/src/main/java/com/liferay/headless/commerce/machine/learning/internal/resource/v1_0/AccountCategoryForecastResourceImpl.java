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

package com.liferay.headless.commerce.machine.learning.internal.resource.v1_0;

import com.liferay.commerce.machine.learning.forecast.model.AssetCategoryCommerceMLForecast;
import com.liferay.commerce.machine.learning.forecast.service.AssetCategoryCommerceMLForecastService;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterRegistry;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;
import com.liferay.headless.commerce.machine.learning.dto.v1_0.AccountCategoryForecast;
import com.liferay.headless.commerce.machine.learning.internal.constants.CommerceMLForecastConstants;
import com.liferay.headless.commerce.machine.learning.internal.dto.v1_0.converter.CommerceMLForecastCompositeResourcePrimaryKey;
import com.liferay.headless.commerce.machine.learning.internal.util.v1_0.CommerceAccountPermissionHelper;
import com.liferay.headless.commerce.machine.learning.resource.v1_0.AccountCategoryForecastResource;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Riccardo Ferrari
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/account-category-forecast.properties",
	scope = ServiceScope.PROTOTYPE,
	service = AccountCategoryForecastResource.class
)
public class AccountCategoryForecastResourceImpl
	extends BaseAccountCategoryForecastResourceImpl {

	@Override
	public Page<AccountCategoryForecast>
			getAccountCategoryForecastsByMonthlyRevenuePage(
				Long[] categoryIds, Long[] accountIds, Date forecastStartDate,
				Integer historyLength, Integer forecastLength,
				Pagination pagination)
		throws Exception {

		List<Long> commerceAccountIds =
			_commerceAccountPermissionHelper.filterCommerceAccountIds(
				Arrays.asList(accountIds));

		if (commerceAccountIds.isEmpty()) {
			return Page.of(Collections.emptyList());
		}

		Date startDate = forecastStartDate;

		if (startDate == null) {
			startDate = new Date();
		}

		if (historyLength == null) {
			historyLength = CommerceMLForecastConstants.HISTORY_LENGTH_DEFAULT;
		}

		if (forecastLength == null) {
			forecastLength =
				CommerceMLForecastConstants.FORECAST_LENGTH_DEFAULT;
		}

		long[] assetCategoryIds = ArrayUtil.toArray(categoryIds);

		List<AssetCategoryCommerceMLForecast> assetCategoryCommerceMLForecasts =
			_assetCategoryCommerceMLForecastService.
				getMonthlyRevenueAssetCategoryCommerceMLForecasts(
					contextCompany.getCompanyId(), assetCategoryIds,
					ArrayUtil.toLongArray(commerceAccountIds), startDate,
					historyLength, forecastLength,
					pagination.getStartPosition(), pagination.getEndPosition());

		long totalItems =
			_assetCategoryCommerceMLForecastService.
				getMonthlyRevenueAssetCategoryCommerceMLForecastsCount(
					contextCompany.getCompanyId(), assetCategoryIds,
					ArrayUtil.toLongArray(commerceAccountIds), startDate,
					historyLength, forecastLength);

		return Page.of(
			_toAccountCategoryForecasts(assetCategoryCommerceMLForecasts),
			pagination, totalItems);
	}

	private List<AccountCategoryForecast> _toAccountCategoryForecasts(
			List<AssetCategoryCommerceMLForecast>
				commerceAccountCommerceMLForecasts)
		throws Exception {

		List<AccountCategoryForecast> accountForecasts = new ArrayList<>();

		DTOConverter accountForecastDtoConverter =
			_dtoConverterRegistry.getDTOConverter(
				AssetCategoryCommerceMLForecast.class.getName());

		for (AssetCategoryCommerceMLForecast assetCategoryCommerceMLForecast :
				commerceAccountCommerceMLForecasts) {

			CommerceMLForecastCompositeResourcePrimaryKey
				commerceMLForecastCompositeResourcePrimaryKey =
					new CommerceMLForecastCompositeResourcePrimaryKey(
						assetCategoryCommerceMLForecast.getCompanyId(),
						assetCategoryCommerceMLForecast.getForecastId());

			accountForecasts.add(
				(AccountCategoryForecast)accountForecastDtoConverter.toDTO(
					new DefaultDTOConverterContext(
						contextAcceptLanguage.getPreferredLocale(),
						commerceMLForecastCompositeResourcePrimaryKey)));
		}

		return accountForecasts;
	}

	@Reference
	private AssetCategoryCommerceMLForecastService
		_assetCategoryCommerceMLForecastService;

	@Reference
	private CommerceAccountPermissionHelper _commerceAccountPermissionHelper;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

}