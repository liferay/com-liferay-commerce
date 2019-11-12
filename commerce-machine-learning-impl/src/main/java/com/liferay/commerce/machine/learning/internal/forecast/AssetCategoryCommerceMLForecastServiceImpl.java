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

package com.liferay.commerce.machine.learning.internal.forecast;

import com.liferay.commerce.machine.learning.forecast.model.AssetCategoryCommerceMLForecast;
import com.liferay.commerce.machine.learning.forecast.service.AssetCategoryCommerceMLForecastService;
import com.liferay.commerce.machine.learning.internal.forecast.constants.CommerceMLForecastField;
import com.liferay.commerce.machine.learning.internal.forecast.constants.CommerceMLForecastPeriod;
import com.liferay.commerce.machine.learning.internal.forecast.constants.CommerceMLForecastScope;
import com.liferay.commerce.machine.learning.internal.forecast.constants.CommerceMLForecastTarget;
import com.liferay.commerce.machine.learning.internal.forecast.model.AssetCategoryCommerceMLForecastImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.TermsFilter;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.search.engine.adapter.search.CountSearchRequest;
import com.liferay.portal.search.engine.adapter.search.SearchSearchRequest;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Riccardo Ferrari
 */
@Component(
	immediate = true, service = AssetCategoryCommerceMLForecastService.class
)
public class AssetCategoryCommerceMLForecastServiceImpl
	extends BaseCommerceMLForecastServiceImpl<AssetCategoryCommerceMLForecast>
	implements AssetCategoryCommerceMLForecastService {

	@Override
	public AssetCategoryCommerceMLForecast getAssetCategoryCommerceMLForecast(
			long companyId, long forecastId)
		throws PortalException {

		return getCommerceMLForecast(companyId, forecastId);
	}

	@Override
	public List<AssetCategoryCommerceMLForecast>
			getMonthlyRevenueAssetCategoryCommerceMLForecasts(
				long companyId, long[] assetCategoryIds,
				long[] commerceAccountIds, Date actualDate, int historyLength,
				int forecastLength)
		throws PortalException {

		int size =
			assetCategoryIds.length * commerceAccountIds.length *
				(historyLength + forecastLength);

		return getMonthlyRevenueAssetCategoryCommerceMLForecasts(
			companyId, assetCategoryIds, commerceAccountIds, actualDate,
			historyLength, forecastLength, 0, size);
	}

	@Override
	public List<AssetCategoryCommerceMLForecast>
			getMonthlyRevenueAssetCategoryCommerceMLForecasts(
				long companyId, long[] assetCategoryIds,
				long[] commerceAccountIds, Date actualDate, int historyLength,
				int forecastLength, int start, int end)
		throws PortalException {

		Query query = _getMonthlyRevenueQuery(
			actualDate, assetCategoryIds, commerceAccountIds, historyLength,
			forecastLength);

		int size = end - start;

		SearchSearchRequest searchSearchRequest = getSearchSearchRequest(
			commerceMLIndexer.getIndexName(companyId), query, start, size,
			getDefaultSort(true));

		return getSearchResults(searchSearchRequest);
	}

	@Override
	public long getMonthlyRevenueAssetCategoryCommerceMLForecastsCount(
			long companyId, long[] assetCategoryIds, long[] commerceAccountIds,
			Date actualDate, int historyLength, int forecastLength)
		throws PortalException {

		Query query = _getMonthlyRevenueQuery(
			actualDate, assetCategoryIds, commerceAccountIds, historyLength,
			forecastLength);

		CountSearchRequest countSearchRequest = getCountSearchRequest(
			commerceMLIndexer.getIndexName(companyId), query);

		return getCountResult(countSearchRequest);
	}

	@Override
	protected AssetCategoryCommerceMLForecast toForecastModel(
		Document document) {

		AssetCategoryCommerceMLForecast assetCategoryCommerceMLForecast =
			getBaseCommerceMLForecastModel(
				new AssetCategoryCommerceMLForecastImpl(), document);

		assetCategoryCommerceMLForecast.setAssetCategoryId(
			GetterUtil.getLong(document.get(Field.ASSET_CATEGORY_ID)));

		assetCategoryCommerceMLForecast.setCommerceAccountId(
			GetterUtil.getLong(
				document.get(CommerceMLForecastField.COMMERCE_ACCOUNT_ID)));

		return assetCategoryCommerceMLForecast;
	}

	private Query _getMonthlyRevenueQuery(
			Date actualDate, long[] assetCategoryIds, long[] commerceAccountIds,
			int historyLength, int forecastLength)
		throws ParseException {

		CommerceMLForecastPeriod commerceMLForecastPeriod =
			CommerceMLForecastPeriod.MONTH;

		CommerceMLForecastTarget commerceMLForecastTarget =
			CommerceMLForecastTarget.REVENUE;

		Date endDate = getEndDate(
			actualDate, commerceMLForecastPeriod, forecastLength);

		Date startDate = getStartDate(
			actualDate, commerceMLForecastPeriod, historyLength);

		BooleanQuery booleanQuery = getBaseQuery(
			_commerceMLForecastScope.getLabel(),
			commerceMLForecastPeriod.getLabel(),
			commerceMLForecastTarget.getLabel(), startDate, endDate);

		BooleanFilter preBooleanFilter = booleanQuery.getPreBooleanFilter();

		if (assetCategoryIds.length > 0) {
			TermsFilter assetCategoryIdsTermsFilter = new TermsFilter(
				Field.ASSET_CATEGORY_ID);

			assetCategoryIdsTermsFilter.addValues(
				ArrayUtil.toStringArray(assetCategoryIds));

			preBooleanFilter.add(
				assetCategoryIdsTermsFilter, BooleanClauseOccur.MUST);
		}

		TermsFilter commerceAccountIdsTermsFilter = new TermsFilter(
			CommerceMLForecastField.COMMERCE_ACCOUNT_ID);

		commerceAccountIdsTermsFilter.addValues(
			ArrayUtil.toStringArray(commerceAccountIds));

		preBooleanFilter.add(
			commerceAccountIdsTermsFilter, BooleanClauseOccur.MUST);

		return booleanQuery;
	}

	private static final CommerceMLForecastScope _commerceMLForecastScope =
		CommerceMLForecastScope.ASSET_CATEGORY;

}