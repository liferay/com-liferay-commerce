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

package com.liferay.commerce.headless.graphql.internal.schema;

import com.liferay.commerce.headless.graphql.internal.model.Product;
import com.liferay.commerce.product.catalog.CPCatalogEntry;
import com.liferay.commerce.product.catalog.CPQuery;
import com.liferay.commerce.product.data.source.CPDataSourceResult;
import com.liferay.commerce.product.util.CPDefinitionHelper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(immediate = true, service = ProductDataFetcher.class)
public class ProductDataFetcher implements DataFetcher<List<Product>> {

	@Override
	public List<Product> get(DataFetchingEnvironment environment) {
		List<Product> lst = new ArrayList<>();

		try {
			ServiceContext serviceContext = environment.getContext();

			SearchContext searchContext = new SearchContext();

			LinkedHashMap<String, Object> params = new LinkedHashMap<>();

			params.put("keywords", StringPool.STAR);

			Map<String, Serializable> attributes = new HashMap<>();

			attributes.put(Field.STATUS, WorkflowConstants.STATUS_APPROVED);

			attributes.put("params", params);

			searchContext.setAttributes(attributes);

			searchContext.setCompanyId(serviceContext.getCompanyId());
			searchContext.setGroupIds(
				new long[] {serviceContext.getScopeGroupId()});

			searchContext.setKeywords(StringPool.STAR);

			CPQuery cpQuery = new CPQuery();

			CPDataSourceResult cpDataSourceResult = null;

			cpDataSourceResult = _cpDefinitionHelper.search(
				serviceContext.getScopeGroupId(), searchContext, cpQuery,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (CPCatalogEntry cpCatalogEntry :
					cpDataSourceResult.getCPCatalogEntries()) {

				Product a = new Product();

				a.setCpDefinitionId(
					String.valueOf(cpCatalogEntry.getCPDefinitionId()));
				a.setTitle(cpCatalogEntry.getName());

				lst.add(a);
			}
		}
		catch (PortalException pe) {
			pe.printStackTrace();
		}

		return lst;
	}

	@Reference
	private CPDefinitionHelper _cpDefinitionHelper;

}