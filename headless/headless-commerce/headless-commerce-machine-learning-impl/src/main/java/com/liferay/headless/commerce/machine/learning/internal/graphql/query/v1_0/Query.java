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
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLInvokeDetached;
import graphql.annotations.annotationTypes.GraphQLName;

import java.util.Collection;
import java.util.Date;

import javax.annotation.Generated;

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

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<AccountForecast> getAccountForecastsByMonthlyRevenuePage(
			@GraphQLName("accountIds") Long[] accountIds,
			@GraphQLName("forecastStartDate") Date forecastStartDate,
			@GraphQLName("historyLength") Integer historyLength,
			@GraphQLName("forecastLength") Integer forecastLength,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountForecastResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountForecastResource -> {
				Page paginationPage =
					accountForecastResource.
						getAccountForecastsByMonthlyRevenuePage(
							accountIds, forecastStartDate, historyLength,
							forecastLength, Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<AccountCategoryForecast>
			getAccountCategoryForecastsByMonthlyRevenuePage(
				@GraphQLName("categoryIds") Long[] categoryIds,
				@GraphQLName("accountIds") Long[] accountIds,
				@GraphQLName("forecastStartDate") Date forecastStartDate,
				@GraphQLName("historyLength") Integer historyLength,
				@GraphQLName("forecastLength") Integer forecastLength,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountCategoryForecastResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountCategoryForecastResource -> {
				Page paginationPage =
					accountCategoryForecastResource.
						getAccountCategoryForecastsByMonthlyRevenuePage(
							categoryIds, accountIds, forecastStartDate,
							historyLength, forecastLength,
							Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
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

		accountCategoryForecastResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(
			AccountForecastResource accountForecastResource)
		throws Exception {

		accountForecastResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private static ComponentServiceObjects<AccountCategoryForecastResource>
		_accountCategoryForecastResourceComponentServiceObjects;
	private static ComponentServiceObjects<AccountForecastResource>
		_accountForecastResourceComponentServiceObjects;

}