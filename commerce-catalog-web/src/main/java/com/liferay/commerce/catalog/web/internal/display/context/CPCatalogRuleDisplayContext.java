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

package com.liferay.commerce.catalog.web.internal.display.context;

import com.liferay.commerce.catalog.web.internal.display.context.util.CPCatalogRuleRequestHelper;
import com.liferay.commerce.catalog.web.internal.util.CPCatalogRulePortletUtil;
import com.liferay.commerce.product.catalog.rule.CPRuleType;
import com.liferay.commerce.product.catalog.rule.CPRuleTypeDisplayContext;
import com.liferay.commerce.product.catalog.rule.CPRuleTypeJSPContributor;
import com.liferay.commerce.product.catalog.rule.CPRuleTypeJSPContributorRegistry;
import com.liferay.commerce.product.catalog.rule.CPRuleTypeRegistry;
import com.liferay.commerce.product.constants.CPRuleConstants;
import com.liferay.commerce.product.model.CPRule;
import com.liferay.commerce.product.service.CPRuleAssetCategoryRelService;
import com.liferay.commerce.product.service.CPRuleService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alec Sloan
 */
public class CPCatalogRuleDisplayContext implements CPRuleTypeDisplayContext {

	public CPCatalogRuleDisplayContext(
		CPRuleAssetCategoryRelService cpRuleAssetCategoryRelService,
		CPRuleService cpRuleService,
		CPRuleTypeJSPContributorRegistry cpRuleTypeJSPContributorRegistry,
		CPRuleTypeRegistry cpRuleTypeRegistry,
		HttpServletRequest httpServletRequest) {

		_cpRuleAssetCategoryRelService = cpRuleAssetCategoryRelService;
		_cpRuleService = cpRuleService;
		_cpRuleTypeJSPContributorRegistry = cpRuleTypeJSPContributorRegistry;
		_cpRuleTypeRegistry = cpRuleTypeRegistry;

		_cpCatalogRuleRequestHelper = new CPCatalogRuleRequestHelper(
			httpServletRequest);

		_httpServletRequest = httpServletRequest;
		_liferayPortletRequest =
			_cpCatalogRuleRequestHelper.getLiferayPortletRequest();
		_liferayPortletResponse =
			_cpCatalogRuleRequestHelper.getLiferayPortletResponse();
	}

	@Override
	public String getAssetCategoryIds() throws PortalException {
		if (getCPRuleId() <= 0) {
			return StringPool.BLANK;
		}

		long[] assetCategoryIds =
			_cpRuleAssetCategoryRelService.getAssetCategoryIds(getCPRuleId());

		return StringUtil.merge(assetCategoryIds, StringPool.COMMA);
	}

	public CPRule getCPRule() throws PortalException {
		if (_cpRule != null) {
			return _cpRule;
		}

		HttpServletRequest httpServletRequest =
			_cpCatalogRuleRequestHelper.getRequest();

		long cpRuleId = ParamUtil.getLong(httpServletRequest, "cpRuleId");

		if (cpRuleId > 0) {
			_cpRule = _cpRuleService.getCPRule(cpRuleId);
		}

		return _cpRule;
	}

	public long getCPRuleId() throws PortalException {
		CPRule cpRule = getCPRule();

		if (cpRule == null) {
			return 0;
		}

		return cpRule.getCPRuleId();
	}

	public CPRuleTypeJSPContributor getCPRuleTypeJSPContributor(String key) {
		return _cpRuleTypeJSPContributorRegistry.getCPRuleTypeJSPContributor(
			key);
	}

	public List<CPRuleType> getCPRuleTypes() {
		return _cpRuleTypeRegistry.getCPRuleTypes(
			CPRuleConstants.SCOPE_COMPANY);
	}

	@Override
	public String getCPRuleTypeSettingsProperty(String property)
		throws PortalException {

		CPRule cpRule = getCPRule();

		if (cpRule == null) {
			return null;
		}

		UnicodeProperties typeSettingsProperties =
			cpRule.getTypeSettingsProperties();

		return typeSettingsProperties.get(property);
	}

	public String getCPRuleURL(CPRule cpRule) {
		if (cpRule == null) {
			return StringPool.BLANK;
		}

		PortletURL portletURL = _liferayPortletResponse.createRenderURL();

		long commerceChannelId = ParamUtil.getLong(
			_httpServletRequest, "commerceChannelId");

		portletURL.setParameter(
			"commerceChannelId", String.valueOf(commerceChannelId));

		portletURL.setParameter(
			"cpRuleId", String.valueOf(cpRule.getCPRuleId()));
		portletURL.setParameter(
			"mvcRenderCommandName", "editCommerceChannelFilter");

		return portletURL.toString();
	}

	public String getKeywords() {
		if (Validator.isNotNull(_keywords)) {
			return _keywords;
		}

		_keywords = ParamUtil.getString(_httpServletRequest, "keywords");

		return _keywords;
	}

	public String getOrderByCol() {
		return ParamUtil.getString(
			_httpServletRequest, SearchContainer.DEFAULT_ORDER_BY_COL_PARAM,
			"name");
	}

	public String getOrderByType() {
		return ParamUtil.getString(
			_httpServletRequest, SearchContainer.DEFAULT_ORDER_BY_TYPE_PARAM,
			"asc");
	}

	public PortletURL getPortletURL() throws PortalException {
		PortletURL portletURL = _liferayPortletResponse.createRenderURL();

		long commerceChannelId = ParamUtil.getLong(
			_httpServletRequest, "commerceChannelId");

		portletURL.setParameter(
			"commerceChannelId", String.valueOf(commerceChannelId));

		portletURL.setParameter(
			"mvcRenderCommandName", "viewCommerceChannelFilters");

		String filterFields = ParamUtil.getString(
			_httpServletRequest, "filterFields");

		if (Validator.isNotNull(filterFields)) {
			portletURL.setParameter("filterFields", filterFields);
		}

		String filtersLabels = ParamUtil.getString(
			_httpServletRequest, "filtersLabels");

		if (Validator.isNotNull(filtersLabels)) {
			portletURL.setParameter("filtersLabels", filtersLabels);
		}

		String filtersValues = ParamUtil.getString(
			_httpServletRequest, "filtersValues");

		if (Validator.isNotNull(filtersValues)) {
			portletURL.setParameter("filtersValues", filtersValues);
		}

		return portletURL;
	}

	public SearchContainer<CPRule> getSearchContainer() throws PortalException {
		if (_searchContainer != null) {
			return _searchContainer;
		}

		ThemeDisplay themeDisplay =
			(ThemeDisplay)_httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		_searchContainer = new SearchContainer<>(
			_liferayPortletRequest, getPortletURL(), null, null);

		Sort sort = CPCatalogRulePortletUtil.getCPRuleSort(
			getOrderByCol(), getOrderByType());

		BaseModelSearchResult<CPRule> cpRulesBaseModelSearchResult =
			_cpRuleService.searchCPRules(
				themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(),
				getKeywords(), _searchContainer.getStart(),
				_searchContainer.getEnd(), sort);

		_searchContainer.setTotal(cpRulesBaseModelSearchResult.getLength());
		_searchContainer.setResults(
			cpRulesBaseModelSearchResult.getBaseModels());

		return _searchContainer;
	}

	private final CPCatalogRuleRequestHelper _cpCatalogRuleRequestHelper;
	private CPRule _cpRule;
	private final CPRuleAssetCategoryRelService _cpRuleAssetCategoryRelService;
	private final CPRuleService _cpRuleService;
	private final CPRuleTypeJSPContributorRegistry
		_cpRuleTypeJSPContributorRegistry;
	private final CPRuleTypeRegistry _cpRuleTypeRegistry;
	private final HttpServletRequest _httpServletRequest;
	private String _keywords;
	private final LiferayPortletRequest _liferayPortletRequest;
	private final LiferayPortletResponse _liferayPortletResponse;
	private SearchContainer<CPRule> _searchContainer;

}