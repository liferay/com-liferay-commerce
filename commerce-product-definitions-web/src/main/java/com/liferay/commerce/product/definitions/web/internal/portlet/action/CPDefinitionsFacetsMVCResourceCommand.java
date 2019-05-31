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

package com.liferay.commerce.product.definitions.web.internal.portlet.action;

import com.liferay.commerce.product.constants.CPField;
import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPDefinitionOptionValueRel;
import com.liferay.commerce.product.model.CPOption;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.commerce.product.service.CPDefinitionOptionValueRelLocalService;
import com.liferay.commerce.product.service.CPOptionService;
import com.liferay.commerce.product.service.CommerceCatalogService;
import com.liferay.commerce.product.type.CPType;
import com.liferay.commerce.product.type.CPTypeServicesTracker;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.MultiValueFacet;
import com.liferay.portal.kernel.search.facet.collector.FacetCollector;
import com.liferay.portal.kernel.search.facet.collector.TermCollector;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.List;
import java.util.stream.Stream;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + CPPortletKeys.CP_DEFINITIONS,
		"mvc.command.name=cpDefinitionsFacets"
	},
	service = MVCResourceCommand.class
)
public class CPDefinitionsFacetsMVCResourceCommand
	extends BaseMVCResourceCommand {

	protected SearchContext buildSearchContext(
		ResourceRequest resourceRequest) {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(themeDisplay.getCompanyId());
		searchContext.setLayout(themeDisplay.getLayout());
		searchContext.setLocale(themeDisplay.getLocale());
		searchContext.setTimeZone(themeDisplay.getTimeZone());
		searchContext.setUserId(themeDisplay.getUserId());

		List<CommerceCatalog> commerceCatalogs =
			_commerceCatalogService.getCommerceCatalogs(
				themeDisplay.getCompanyId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		Stream<CommerceCatalog> stream = commerceCatalogs.stream();

		long[] groupIds = stream.mapToLong(
			CommerceCatalog::getGroupId
		).toArray();

		searchContext.setGroupIds(groupIds);

		searchContext.setAttribute(Field.STATUS, WorkflowConstants.STATUS_ANY);

		QueryConfig queryConfig = searchContext.getQueryConfig();

		queryConfig.setLocale(themeDisplay.getLocale());

		return searchContext;
	}

	@Override
	protected void doServeResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String fieldName = ParamUtil.getString(resourceRequest, "fieldName");
		String filterFields = ParamUtil.getString(
			resourceRequest, "filterFields");
		String filtersValues = ParamUtil.getString(
			resourceRequest, "filtersValues");
		String indexFieldName = _getIndexFieldName(
			fieldName, themeDisplay.getLanguageId());

		String currentOptionKey = StringPool.BLANK;

		if (fieldName.startsWith("OPTION_")) {
			currentOptionKey = StringUtil.replace(
				fieldName, "OPTION_", StringPool.BLANK);
		}

		JSONArray jsonArray = _jsonFactory.createJSONArray();

		SearchContext searchContext = buildSearchContext(resourceRequest);

		List<Facet> facets = _cpDefinitionLocalService.getFacets(
			filterFields, filtersValues, searchContext);

		searchContext.setFacets(facets);

		Facet facet = null;

		if (filterFields.contains(fieldName)) {
			for (Facet curFacet : facets) {
				if (indexFieldName.equals(curFacet.getFieldName())) {
					facet = curFacet;

					break;
				}
			}
		}

		if (facet == null) {
			facet = new MultiValueFacet(searchContext);

			facet.setFieldName(indexFieldName);

			searchContext.addFacet(facet);
		}

		QueryConfig queryConfig = searchContext.getQueryConfig();

		queryConfig.addSelectedFieldNames(indexFieldName);

		queryConfig.setHighlightEnabled(false);
		queryConfig.setScoreEnabled(false);

		Indexer<CPDefinition> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			CPDefinition.class);

		indexer.search(searchContext);

		FacetCollector facetCollector = facet.getFacetCollector();

		for (TermCollector termCollector : facetCollector.getTermCollectors()) {
			String label = termCollector.getTerm();

			JSONObject jsonObject = _jsonFactory.createJSONObject();

			jsonObject.put("term", termCollector.getTerm());

			if (fieldName.equals(Field.STATUS)) {
				int status = GetterUtil.getInteger(termCollector.getTerm());

				label = LanguageUtil.get(
					themeDisplay.getLocale(),
					WorkflowConstants.getStatusLabel(status));
			}
			else if (fieldName.equals(CPField.PRODUCT_TYPE_NAME)) {
				String productTypeName = termCollector.getTerm();

				CPType cpType = _cpTypeServicesTracker.getCPType(
					productTypeName);

				label = cpType.getLabel(themeDisplay.getLocale());
			}
			else if (fieldName.equals(CPField.OPTION_NAMES)) {
				String optionKey = termCollector.getTerm();

				CPOption cpOption = _cpOptionService.fetchCPOption(
					themeDisplay.getCompanyId(), optionKey);

				if (cpOption != null) {
					label = cpOption.getName(themeDisplay.getLocale());
				}
			}
			else if (Validator.isNotNull(currentOptionKey)) {
				List<CPDefinitionOptionValueRel> cpDefinitionOptionValueRels =
					_cpDefinitionOptionValueRelLocalService.
						getCPDefinitionOptionValueRels(
							termCollector.getTerm(), 0, 1);

				if (!cpDefinitionOptionValueRels.isEmpty()) {
					CPDefinitionOptionValueRel cpDefinitionOptionValueRel =
						cpDefinitionOptionValueRels.get(0);

					label = cpDefinitionOptionValueRel.getName(
						themeDisplay.getLocale());
				}
			}

			jsonObject.put("frequency", termCollector.getFrequency());
			jsonObject.put("label", label);

			jsonArray.put(jsonObject);
		}

		HttpServletResponse httpServletResponse =
			_portal.getHttpServletResponse(resourceResponse);

		httpServletResponse.setContentType(ContentTypes.APPLICATION_JSON);

		ServletResponseUtil.write(httpServletResponse, jsonArray.toString());
	}

	private String _getIndexFieldName(String fieldName, String languageId) {
		if (fieldName.startsWith("OPTION_")) {
			fieldName = StringUtil.replace(
				fieldName, "OPTION_", StringPool.BLANK);

			return StringBundler.concat(
				languageId, "_ATTRIBUTE_", fieldName, "_VALUES_NAMES");
		}

		return fieldName;
	}

	@Reference
	private CommerceCatalogService _commerceCatalogService;

	@Reference
	private CPDefinitionLocalService _cpDefinitionLocalService;

	@Reference
	private CPDefinitionOptionValueRelLocalService
		_cpDefinitionOptionValueRelLocalService;

	@Reference
	private CPOptionService _cpOptionService;

	@Reference
	private CPTypeServicesTracker _cpTypeServicesTracker;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private Portal _portal;

}