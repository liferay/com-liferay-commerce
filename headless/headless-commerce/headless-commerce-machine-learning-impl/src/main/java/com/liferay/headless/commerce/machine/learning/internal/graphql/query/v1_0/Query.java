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

package com.liferay.headless.commerce.machine.learning.internal.graphql.query.v1_0;

import com.liferay.headless.commerce.machine.learning.dto.v1_0.AccountCategoryForecast;
import com.liferay.headless.commerce.machine.learning.dto.v1_0.AccountForecast;
import com.liferay.headless.commerce.machine.learning.resource.v1_0.AccountCategoryForecastResource;
import com.liferay.headless.commerce.machine.learning.resource.v1_0.AccountForecastResource;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.Date;
import java.util.function.BiFunction;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author Riccardo Ferrari
 * @generated
 */
@Generated("")
public class Query {

	public static void
		setAccountCategoryForecastResourceComponentServiceObjects(
			ComponentServiceObjects<AccountCategoryForecastResource>
				accountCategoryForecastResourceComponentServiceObjects) {

		_accountCategoryForecastResourceComponentServiceObjects =
			accountCategoryForecastResourceComponentServiceObjects;
	}

	public static void setAccountForecastResourceComponentServiceObjects(
		ComponentServiceObjects<AccountForecastResource>
			accountForecastResourceComponentServiceObjects) {

		_accountForecastResourceComponentServiceObjects =
			accountForecastResourceComponentServiceObjects;
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountCategoryForecastsByMonthlyRevenue(accountIds: ___, categoryIds: ___, forecastLength: ___, forecastStartDate: ___, historyLength: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public AccountCategoryForecastPage accountCategoryForecastsByMonthlyRevenue(
			@GraphQLName("accountIds") Long[] accountIds,
			@GraphQLName("categoryIds") Long[] categoryIds,
			@GraphQLName("forecastLength") Integer forecastLength,
			@GraphQLName("forecastStartDate") Date forecastStartDate,
			@GraphQLName("historyLength") Integer historyLength,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountCategoryForecastResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountCategoryForecastResource -> new AccountCategoryForecastPage(
				accountCategoryForecastResource.
					getAccountCategoryForecastsByMonthlyRevenuePage(
						accountIds, categoryIds, forecastLength,
						forecastStartDate, historyLength,
						Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountForecastsByMonthlyRevenue(accountIds: ___, forecastLength: ___, forecastStartDate: ___, historyLength: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public AccountForecastPage accountForecastsByMonthlyRevenue(
			@GraphQLName("accountIds") Long[] accountIds,
			@GraphQLName("forecastLength") Integer forecastLength,
			@GraphQLName("forecastStartDate") Date forecastStartDate,
			@GraphQLName("historyLength") Integer historyLength,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountForecastResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountForecastResource -> new AccountForecastPage(
				accountForecastResource.getAccountForecastsByMonthlyRevenuePage(
					accountIds, forecastLength, forecastStartDate,
					historyLength, Pagination.of(page, pageSize))));
	}

	@GraphQLName("AccountCategoryForecastPage")
	public class AccountCategoryForecastPage {

		public AccountCategoryForecastPage(Page accountCategoryForecastPage) {
			items = accountCategoryForecastPage.getItems();
			page = accountCategoryForecastPage.getPage();
			pageSize = accountCategoryForecastPage.getPageSize();
			totalCount = accountCategoryForecastPage.getTotalCount();
		}

		@GraphQLField
		protected java.util.Collection<AccountCategoryForecast> items;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("AccountForecastPage")
	public class AccountForecastPage {

		public AccountForecastPage(Page accountForecastPage) {
			items = accountForecastPage.getItems();
			page = accountForecastPage.getPage();
			pageSize = accountForecastPage.getPageSize();
			totalCount = accountForecastPage.getTotalCount();
		}

		@GraphQLField
		protected java.util.Collection<AccountForecast> items;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	private <T, R, E1 extends Throwable, E2 extends Throwable> R
			_applyComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeFunction<T, R, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			return unsafeFunction.apply(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private void _populateResourceContext(
			AccountCategoryForecastResource accountCategoryForecastResource)
		throws Exception {

		accountCategoryForecastResource.setContextAcceptLanguage(
			_acceptLanguage);
		accountCategoryForecastResource.setContextCompany(_company);
		accountCategoryForecastResource.setContextHttpServletRequest(
			_httpServletRequest);
		accountCategoryForecastResource.setContextHttpServletResponse(
			_httpServletResponse);
		accountCategoryForecastResource.setContextUriInfo(_uriInfo);
		accountCategoryForecastResource.setContextUser(_user);
	}

	private void _populateResourceContext(
			AccountForecastResource accountForecastResource)
		throws Exception {

		accountForecastResource.setContextAcceptLanguage(_acceptLanguage);
		accountForecastResource.setContextCompany(_company);
		accountForecastResource.setContextHttpServletRequest(
			_httpServletRequest);
		accountForecastResource.setContextHttpServletResponse(
			_httpServletResponse);
		accountForecastResource.setContextUriInfo(_uriInfo);
		accountForecastResource.setContextUser(_user);
	}

	private static ComponentServiceObjects<AccountCategoryForecastResource>
		_accountCategoryForecastResourceComponentServiceObjects;
	private static ComponentServiceObjects<AccountForecastResource>
		_accountForecastResourceComponentServiceObjects;

	private AcceptLanguage _acceptLanguage;
	private BiFunction<Object, String, Filter> _filterBiFunction;
	private BiFunction<Object, String, Sort[]> _sortsBiFunction;
	private Company _company;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private UriInfo _uriInfo;
	private User _user;

}