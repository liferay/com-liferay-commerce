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

import com.liferay.commerce.machine.learning.forecast.model.CommerceMLForecast;
import com.liferay.commerce.machine.learning.internal.forecast.constants.CommerceMLForecastField;
import com.liferay.commerce.machine.learning.internal.forecast.constants.CommerceMLForecastPeriod;
import com.liferay.commerce.machine.learning.internal.search.api.CommerceMLIndexer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.RangeTermFilter;
import com.liferay.portal.kernel.search.filter.TermFilter;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.search.engine.adapter.SearchEngineAdapter;
import com.liferay.portal.search.engine.adapter.search.CountSearchRequest;
import com.liferay.portal.search.engine.adapter.search.CountSearchResponse;
import com.liferay.portal.search.engine.adapter.search.SearchSearchRequest;
import com.liferay.portal.search.engine.adapter.search.SearchSearchResponse;

import java.text.DateFormat;
import java.text.ParseException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Ferrari
 */
public abstract class BaseCommerceMLForecastServiceImpl
	<T extends CommerceMLForecast> {

	protected BooleanFilter getBaseBooleanFilter(
		String scope, String period, String target) {

		BooleanFilter booleanFilter = new BooleanFilter();

		TermFilter scopeTermFilter = new TermFilter(
			CommerceMLForecastField.SCOPE, scope);

		booleanFilter.add(scopeTermFilter, BooleanClauseOccur.MUST);

		TermFilter periodTermFilter = new TermFilter(
			CommerceMLForecastField.PERIOD, period);

		booleanFilter.add(periodTermFilter, BooleanClauseOccur.MUST);

		TermFilter targetTermFilter = new TermFilter(
			CommerceMLForecastField.TARGET, target);

		booleanFilter.add(targetTermFilter, BooleanClauseOccur.MUST);

		return booleanFilter;
	}

	protected BooleanFilter getBaseBooleanFilter(
		String scope, String period, String target, Date startDate,
		Date endDate) {

		BooleanFilter baseBooleanFilter = getBaseBooleanFilter(
			scope, period, target);

		RangeTermFilter rangeTermFilter = new RangeTermFilter(
			CommerceMLForecastField.TIMESTAMP, true, true,
			_formatSearchDate(startDate), _formatSearchDate(endDate));

		baseBooleanFilter.add(rangeTermFilter, BooleanClauseOccur.MUST);

		return baseBooleanFilter;
	}

	protected T getBaseCommerceMLForecastModel(
		T commerceMLForecast, Document document) {

		commerceMLForecast.setActual(
			GetterUtil.getFloat(
				document.get(CommerceMLForecastField.ACTUAL), Float.MIN_VALUE));

		commerceMLForecast.setCompanyId(
			GetterUtil.getLong(document.get(Field.COMPANY_ID)));

		commerceMLForecast.setForecast(
			GetterUtil.getFloat(
				document.get(CommerceMLForecastField.FORECAST),
				Float.MIN_VALUE));

		commerceMLForecast.setForecastId(
			GetterUtil.getLong(
				document.get(CommerceMLForecastField.FORECAST_ID)));

		commerceMLForecast.setForecastLowerBound(
			GetterUtil.getFloat(
				document.get(CommerceMLForecastField.FORECAST_LOWER_BOUND)));

		commerceMLForecast.setForecastUpperBound(
			GetterUtil.getFloat(
				document.get(CommerceMLForecastField.FORECAST_UPPER_BOUND)));

		commerceMLForecast.setJobId(
			document.get(CommerceMLForecastField.JOB_ID));

		commerceMLForecast.setScope(
			document.get(CommerceMLForecastField.SCOPE));

		commerceMLForecast.setPeriod(
			document.get(CommerceMLForecastField.PERIOD));

		commerceMLForecast.setTarget(
			document.get(CommerceMLForecastField.TARGET));

		try {
			commerceMLForecast.setTimestamp(
				document.getDate(CommerceMLForecastField.TIMESTAMP));
		}
		catch (ParseException pe) {
		}

		return commerceMLForecast;
	}

	protected BooleanQuery getBaseQuery(
		String scope, String period, String target) {

		BooleanQuery booleanQuery = new BooleanQueryImpl();

		BooleanFilter baseBooleanFilter = getBaseBooleanFilter(
			scope, period, target);

		booleanQuery.setPreBooleanFilter(baseBooleanFilter);

		return booleanQuery;
	}

	protected BooleanQuery getBaseQuery(
			String scope, String period, String target, Date startDate,
			Date endDate)
		throws com.liferay.portal.kernel.search.ParseException {

		BooleanQuery booleanQuery = new BooleanQueryImpl();

		BooleanFilter baseBooleanFilter = getBaseBooleanFilter(
			scope, period, target, startDate, endDate);

		booleanQuery.setPreBooleanFilter(baseBooleanFilter);

		return booleanQuery;
	}

	protected T getCommerceMLForecast(long companyId, long forecastId)
		throws PortalException {

		BooleanQuery booleanQuery = new BooleanQueryImpl();

		BooleanFilter booleanFilter = new BooleanFilter();

		TermFilter termFilter = new TermFilter(
			CommerceMLForecastField.FORECAST_ID, String.valueOf(forecastId));

		booleanFilter.add(termFilter, BooleanClauseOccur.MUST);

		booleanQuery.setPreBooleanFilter(booleanFilter);

		SearchSearchRequest searchSearchRequest = getSearchSearchRequest(
			commerceMLIndexer.getIndexName(companyId), booleanQuery, 0, 1,
			getDefaultSort(true));

		List<T> searchResults = getSearchResults(searchSearchRequest);

		if (searchResults.isEmpty()) {
			return null;
		}

		return searchResults.get(0);
	}

	protected long getCountResult(CountSearchRequest countSearchRequest) {
		CountSearchResponse countSearchResponse = searchEngineAdapter.execute(
			countSearchRequest);

		return countSearchResponse.getCount();
	}

	protected CountSearchRequest getCountSearchRequest(
		String indexName, Query query) {

		CountSearchRequest countSearchRequest = new CountSearchRequest();

		countSearchRequest.setIndexNames(new String[] {indexName});

		countSearchRequest.setQuery(query);

		return countSearchRequest;
	}

	protected Sort[] getDefaultSort(boolean reverse) {
		Sort sort = SortFactoryUtil.create(
			CommerceMLForecastField.TIMESTAMP.concat(SORTABLE_FIELD_SUFFIX),
			reverse);

		return new Sort[] {sort};
	}

	protected Date getEndDate(
		Date endDate, CommerceMLForecastPeriod commerceMLForecastPeriod,
		int stepCount) {

		Instant endDateInstant = endDate.toInstant();

		LocalDateTime endLocalDateTime = LocalDateTime.ofInstant(
			endDateInstant, DEFAULT_ZONE_OFFSET);

		endLocalDateTime = endLocalDateTime.truncatedTo(ChronoUnit.DAYS);

		if (commerceMLForecastPeriod.equals(CommerceMLForecastPeriod.MONTH)) {
			endLocalDateTime = endLocalDateTime.with(
				ChronoField.DAY_OF_MONTH, 1);

			endLocalDateTime = endLocalDateTime.plusMonths(stepCount);
		}
		else {
			endLocalDateTime = endLocalDateTime.with(
				ChronoField.DAY_OF_WEEK, 1);

			endLocalDateTime = endLocalDateTime.plusWeeks(stepCount);
		}

		return _toDate(endLocalDateTime);
	}

	protected List<T> getForecastList(Hits hits) {
		List<Document> documents = _getDocumentList(hits);

		Stream<Document> documentsStream = documents.stream();

		return documentsStream.map(
			this::toForecastModel
		).collect(
			Collectors.toList()
		);
	}

	protected List<T> getSearchResults(
		SearchSearchRequest searchSearchRequest) {

		SearchSearchResponse searchSearchResponse = searchEngineAdapter.execute(
			searchSearchRequest);

		return getForecastList(searchSearchResponse.getHits());
	}

	protected SearchSearchRequest getSearchSearchRequest(
		String indexName, Query query, int start, int size, Sort[] sorts) {

		SearchSearchRequest searchSearchRequest = new SearchSearchRequest();

		searchSearchRequest.setIndexNames(new String[] {indexName});

		searchSearchRequest.setQuery(query);

		searchSearchRequest.setStart(start);

		searchSearchRequest.setSize(size);

		searchSearchRequest.setSorts(sorts);

		searchSearchRequest.setStats(Collections.emptyMap());

		return searchSearchRequest;
	}

	protected Date getStartDate(
		Date startDate, CommerceMLForecastPeriod commerceMLForecastPeriod,
		int stepCount) {

		Instant startDateInstant = startDate.toInstant();

		LocalDateTime startLocalDateTime = LocalDateTime.ofInstant(
			startDateInstant, DEFAULT_ZONE_OFFSET);

		startLocalDateTime = startLocalDateTime.truncatedTo(ChronoUnit.DAYS);

		if (commerceMLForecastPeriod.equals(CommerceMLForecastPeriod.MONTH)) {
			startLocalDateTime = startLocalDateTime.with(
				ChronoField.DAY_OF_MONTH, 1);

			startLocalDateTime = startLocalDateTime.minusMonths(stepCount);
		}
		else {
			startLocalDateTime = startLocalDateTime.with(
				ChronoField.DAY_OF_WEEK, 1);

			startLocalDateTime = startLocalDateTime.minusWeeks(stepCount);
		}

		return _toDate(startLocalDateTime);
	}

	protected abstract T toForecastModel(Document document);

	protected static final ZoneId DEFAULT_ZONE_OFFSET =
		ZoneOffset.systemDefault();

	protected static final String SORTABLE_FIELD_SUFFIX = "_sortable";

	@Reference(
		target = "(component.name=com.liferay.commerce.machine.learning.internal.forecast.search.index.CommerceMLForecastIndexer)"
	)
	protected volatile CommerceMLIndexer commerceMLIndexer;

	@Reference
	protected volatile SearchEngineAdapter searchEngineAdapter;

	private String _formatSearchDate(Date searchDate) {
		DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			_INDEX_DATE_FORMAT_PATTERN);

		return dateFormat.format(searchDate);
	}

	private List<Document> _getDocumentList(Hits hits) {
		List<Document> list = new ArrayList<>(hits.toList());

		Map<String, Hits> groupedHits = hits.getGroupedHits();

		for (Map.Entry<String, Hits> entry : groupedHits.entrySet()) {
			list.addAll(_getDocumentList(entry.getValue()));
		}

		return list;
	}

	private Date _toDate(LocalDateTime localDateTime) {
		ZonedDateTime zonedDateTime = localDateTime.atZone(DEFAULT_ZONE_OFFSET);

		return Date.from(zonedDateTime.toInstant());
	}

	private static final String _INDEX_DATE_FORMAT_PATTERN = "yyyyMMddHHmmss";

}