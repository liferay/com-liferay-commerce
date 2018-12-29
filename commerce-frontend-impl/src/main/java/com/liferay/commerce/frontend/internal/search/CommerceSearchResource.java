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

package com.liferay.commerce.frontend.internal.search;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.liferay.commerce.frontend.internal.search.model.SearchItemModel;
import com.liferay.commerce.frontend.internal.search.util.CommerceSearchUtil;
import com.liferay.commerce.product.catalog.CPCatalogEntry;
import com.liferay.commerce.product.catalog.CPQuery;
import com.liferay.commerce.product.data.source.CPDataSourceResult;
import com.liferay.commerce.product.util.CPDefinitionHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(service = CommerceSearchResource.class)
public class CommerceSearchResource {

	@GET
	@Path("/search/{plid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(
		@PathParam("plid") long plid, @QueryParam("q") String queryString,
		@Context ThemeDisplay themeDisplay) {

		try {
			Layout layout = _layoutLocalService.getLayout(plid);

			themeDisplay.setScopeGroupId(layout.getGroupId());
			themeDisplay.setLayout(layout);
			themeDisplay.setLayoutSet(layout.getLayoutSet());

			List<SearchItemModel> searchItemModels = new ArrayList<>();

			searchItemModels.addAll(
				searchProducts(
					themeDisplay.getCompanyId(), layout.getGroupId(),
					queryString, themeDisplay));

			String url = _commerceSearchUtil.getSearchFriendlyURL(themeDisplay);

			if (Validator.isNotNull(url)) {
				url = _http.addParameter(url, "q", queryString);

				SearchItemModel searchItemModel = new SearchItemModel(
					"category",
					LanguageUtil.get(themeDisplay.getLocale(), "all-content"));

				searchItemModel.setUrl(url);

				searchItemModels.add(searchItemModel);
			}

			String json = _OBJECT_MAPPER.writeValueAsString(searchItemModels);

			return Response.ok(
				json, MediaType.APPLICATION_JSON
			).build();
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return Response.status(
			Status.SERVICE_UNAVAILABLE
		).build();
	}

	protected List<SearchItemModel> searchProducts(
			long companyId, long groupId, String queryString,
			ThemeDisplay themeDisplay)
		throws PortalException {

		List<SearchItemModel> searchItemModels = new ArrayList<>();

		SearchContext searchContext = new SearchContext();

		Map<String, Serializable> attributes = new HashMap<>();

		attributes.put(Field.STATUS, WorkflowConstants.STATUS_APPROVED);

		searchContext.setAttributes(attributes);

		searchContext.setCompanyId(companyId);
		searchContext.setGroupIds(new long[] {groupId});

		searchContext.setKeywords(queryString);

		CPQuery cpQuery = new CPQuery();

		cpQuery.setOrderByCol1("title");
		cpQuery.setOrderByCol2("modifiedDate");
		cpQuery.setOrderByType1("ASC");
		cpQuery.setOrderByType2("DESC");

		CPDataSourceResult cpDataSourceResult = _cpDefinitionHelper.search(
			groupId, searchContext, cpQuery, 0, 5);

		searchItemModels.add(
			new SearchItemModel(
				"label",
				LanguageUtil.get(themeDisplay.getLocale(), "catalog")));

		for (CPCatalogEntry cpCatalogEntry :
				cpDataSourceResult.getCPCatalogEntries()) {

			searchItemModels.add(
				_getSearchItemModel(cpCatalogEntry, themeDisplay));
		}

		String url = _commerceSearchUtil.getCatalogFriendlyURL(themeDisplay);

		if (Validator.isNotNull(url)) {
			url = _http.addParameter(url, "q", queryString);

			SearchItemModel searchItemModel = new SearchItemModel(
				"category",
				LanguageUtil.get(themeDisplay.getLocale(), "catalog"));

			searchItemModel.setUrl(url);

			searchItemModels.add(searchItemModel);
		}

		return searchItemModels;
	}

	private SearchItemModel _getSearchItemModel(
			CPCatalogEntry cpCatalogEntry, ThemeDisplay themeDisplay)
		throws PortalException {

		SearchItemModel searchItemModel = new SearchItemModel(
			"item", HtmlUtil.escape(cpCatalogEntry.getName()));

		String subtitle = cpCatalogEntry.getShortDescription();

		if (Validator.isNull(subtitle)) {
			subtitle = cpCatalogEntry.getDescription();
		}

		searchItemModel.setSubtitle(HtmlUtil.escape(subtitle));

		searchItemModel.setImage(cpCatalogEntry.getDefaultImageFileUrl());

		String url = _cpDefinitionHelper.getFriendlyURL(
			cpCatalogEntry.getCPDefinitionId(), themeDisplay);

		searchItemModel.setUrl(url);

		return searchItemModel;
	}

	private static final ObjectMapper _OBJECT_MAPPER = new ObjectMapper() {
		{
			configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
			enable(SerializationFeature.INDENT_OUTPUT);
		}
	};

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceSearchResource.class);

	@Reference
	private CommerceSearchUtil _commerceSearchUtil;

	@Reference
	private CPDefinitionHelper _cpDefinitionHelper;

	@Reference
	private Http _http;

	@Reference
	private LayoutLocalService _layoutLocalService;

}