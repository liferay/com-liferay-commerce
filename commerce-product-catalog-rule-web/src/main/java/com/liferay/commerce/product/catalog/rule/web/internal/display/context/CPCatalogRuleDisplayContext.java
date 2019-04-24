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

package com.liferay.commerce.product.catalog.rule.web.internal.display.context;

import com.liferay.commerce.account.item.selector.criterion.CommerceAccountGroupItemSelectorCriterion;
import com.liferay.commerce.product.catalog.rule.CPRuleType;
import com.liferay.commerce.product.catalog.rule.CPRuleTypeJSPContributor;
import com.liferay.commerce.product.catalog.rule.CPRuleTypeJSPContributorRegistry;
import com.liferay.commerce.product.catalog.rule.CPRuleTypeRegistry;
import com.liferay.commerce.product.catalog.rule.web.internal.display.context.util.CPCatalogRuleRequestHelper;
import com.liferay.commerce.product.catalog.rule.web.internal.util.CPCatalogRulePortletUtil;
import com.liferay.commerce.product.model.CPRule;
import com.liferay.commerce.product.model.CPRuleCommerceAccountGroupRel;
import com.liferay.commerce.product.service.CPRuleAssetCategoryRelService;
import com.liferay.commerce.product.service.CPRuleCommerceAccountGroupRelService;
import com.liferay.commerce.product.service.CPRuleService;
import com.liferay.commerce.product.util.comparator.CPRuleCommerceAccountGroupRelCreateDateComparator;
import com.liferay.item.selector.ItemSelector;
import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.criteria.UUIDItemSelectorReturnType;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class CPCatalogRuleDisplayContext {

	public CPCatalogRuleDisplayContext(
		CPRuleAssetCategoryRelService cpRuleAssetCategoryRelService,
		ModelResourcePermission<CPRule> cpRuleModelResourcePermission,
		CPRuleService cpRuleService,
		CPRuleTypeJSPContributorRegistry cpRuleTypeJSPContributorRegistry,
		CPRuleTypeRegistry cpRuleTypeRegistry,
		CPRuleCommerceAccountGroupRelService
			cpRuleCommerceAccountGroupRelService,
		HttpServletRequest httpServletRequest, ItemSelector itemSelector,
		PortletResourcePermission portletResourcePermission) {

		_cpRuleAssetCategoryRelService = cpRuleAssetCategoryRelService;
		_cpRuleModelResourcePermission = cpRuleModelResourcePermission;
		_cpRuleService = cpRuleService;
		_cpRuleTypeJSPContributorRegistry = cpRuleTypeJSPContributorRegistry;
		_cpRuleTypeRegistry = cpRuleTypeRegistry;
		_cpRuleCommerceAccountGroupRelService =
			cpRuleCommerceAccountGroupRelService;
		_itemSelector = itemSelector;
		_portletResourcePermission = portletResourcePermission;

		_cpCatalogRuleRequestHelper = new CPCatalogRuleRequestHelper(
			httpServletRequest);
		_portalPreferences = PortletPreferencesFactoryUtil.getPortalPreferences(
			httpServletRequest);
	}

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

	public SearchContainer<CPRuleCommerceAccountGroupRel>
			getCPRuleCommerceAccountGroupRelsSearchContainer()
		throws PortalException {

		if (_cpRuleCommerceAccountGroupRelsSearchContainer != null) {
			return _cpRuleCommerceAccountGroupRelsSearchContainer;
		}

		_cpRuleCommerceAccountGroupRelsSearchContainer = new SearchContainer<>(
			_cpCatalogRuleRequestHelper.getLiferayPortletRequest(),
			getPortletURL(), null, "there-are-no-account-group-catalog-rules");

		setOrderByColAndType(
			CPRuleCommerceAccountGroupRel.class,
			_cpRuleCommerceAccountGroupRelsSearchContainer, "create-date",
			"desc");

		OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator =
			CPCatalogRulePortletUtil.
				getCPRuleCommerceAccountGroupRelOrderByComparator(
					_cpRuleCommerceAccountGroupRelsSearchContainer.
						getOrderByCol(),
					_cpRuleCommerceAccountGroupRelsSearchContainer.
						getOrderByType());

		_cpRuleCommerceAccountGroupRelsSearchContainer.setOrderByComparator(
			orderByComparator);

		_cpRuleCommerceAccountGroupRelsSearchContainer.setRowChecker(
			new EmptyOnClickRowChecker(
				_cpCatalogRuleRequestHelper.getLiferayPortletResponse()));

		int total =
			_cpRuleCommerceAccountGroupRelService.
				getCPRuleCommerceAccountGroupRelsCount(getCPRuleId());

		_cpRuleCommerceAccountGroupRelsSearchContainer.setTotal(total);

		List<CPRuleCommerceAccountGroupRel> results =
			getCPRuleCommerceAccountGroupRels(
				_cpRuleCommerceAccountGroupRelsSearchContainer.getStart(),
				_cpRuleCommerceAccountGroupRelsSearchContainer.getEnd(),
				orderByComparator);

		_cpRuleCommerceAccountGroupRelsSearchContainer.setResults(results);

		return _cpRuleCommerceAccountGroupRelsSearchContainer;
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
		return _cpRuleTypeRegistry.getCPRuleTypes();
	}

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

	public String getItemSelectorUrl() throws PortalException {
		RequestBackedPortletURLFactory requestBackedPortletURLFactory =
			RequestBackedPortletURLFactoryUtil.create(
				_cpCatalogRuleRequestHelper.getRequest());

		CommerceAccountGroupItemSelectorCriterion
			commerceAccountGroupItemSelectorCriterion =
				new CommerceAccountGroupItemSelectorCriterion();

		commerceAccountGroupItemSelectorCriterion.
			setDesiredItemSelectorReturnTypes(
				Collections.<ItemSelectorReturnType>singletonList(
					new UUIDItemSelectorReturnType()));

		PortletURL itemSelectorURL = _itemSelector.getItemSelectorURL(
			requestBackedPortletURLFactory, "accountGroupSelectItem",
			commerceAccountGroupItemSelectorCriterion);

		String checkedcommerceAccountGroupIds = StringUtil.merge(
			getCheckedcommerceAccountGroupIds());

		itemSelectorURL.setParameter(
			"checkedcommerceAccountGroupIds", checkedcommerceAccountGroupIds);

		return itemSelectorURL.toString();
	}

	public PortletURL getPortletURL() {
		LiferayPortletResponse liferayPortletResponse =
			_cpCatalogRuleRequestHelper.getLiferayPortletResponse();

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		HttpServletRequest httpServletRequest =
			_cpCatalogRuleRequestHelper.getRequest();

		long cpRuleId = ParamUtil.getLong(httpServletRequest, "cpRuleId");

		if (cpRuleId > 0) {
			portletURL.setParameter("cpRuleId", String.valueOf(cpRuleId));
		}

		String delta = ParamUtil.getString(httpServletRequest, "delta");

		if (Validator.isNotNull(delta)) {
			portletURL.setParameter("delta", delta);
		}

		String deltaEntry = ParamUtil.getString(
			httpServletRequest, "deltaEntry");

		if (Validator.isNotNull(deltaEntry)) {
			portletURL.setParameter("deltaEntry", deltaEntry);
		}

		String orderByCol = ParamUtil.getString(
			httpServletRequest, "orderByCol");

		if (Validator.isNotNull(orderByCol)) {
			portletURL.setParameter("orderByCol", orderByCol);
		}

		String orderByType = ParamUtil.getString(
			httpServletRequest, "orderByType");

		if (Validator.isNotNull(orderByType)) {
			portletURL.setParameter("orderByType", orderByType);
		}

		String redirect = ParamUtil.getString(httpServletRequest, "redirect");

		if (Validator.isNotNull(redirect)) {
			portletURL.setParameter("redirect", redirect);
		}

		String screenNavigationCategoryKey = ParamUtil.getString(
			httpServletRequest, "screenNavigationCategoryKey");

		if (Validator.isNotNull(screenNavigationCategoryKey)) {
			portletURL.setParameter(
				"screenNavigationCategoryKey", screenNavigationCategoryKey);
		}

		String screenNavigationEntryKey = ParamUtil.getString(
			httpServletRequest, "screenNavigationEntryKey");

		if (Validator.isNotNull(screenNavigationEntryKey)) {
			portletURL.setParameter(
				"screenNavigationEntryKey", screenNavigationEntryKey);
		}

		return portletURL;
	}

	public SearchContainer<CPRule> getSearchContainer() throws PortalException {
		if (_searchContainer != null) {
			return _searchContainer;
		}

		_searchContainer = new SearchContainer<>(
			_cpCatalogRuleRequestHelper.getLiferayPortletRequest(),
			getPortletURL(), null, "there-are-no-catalog-rules");

		setOrderByColAndType(
			CPRule.class, _searchContainer, "create-date", "desc");

		OrderByComparator<CPRule> orderByComparator =
			CPCatalogRulePortletUtil.getCPRuleOrderByComparator(
				_searchContainer.getOrderByCol(),
				_searchContainer.getOrderByType());

		_searchContainer.setOrderByComparator(orderByComparator);

		_searchContainer.setRowChecker(
			new EmptyOnClickRowChecker(
				_cpCatalogRuleRequestHelper.getLiferayPortletResponse()));

		long groupId = _cpCatalogRuleRequestHelper.getScopeGroupId();

		if (isSearch()) {
			Sort sort = CPCatalogRulePortletUtil.getCPRuleSort(
				_searchContainer.getOrderByCol(),
				_searchContainer.getOrderByType());

			BaseModelSearchResult<CPRule> baseModelSearchResult =
				_cpRuleService.searchCPRules(
					_cpCatalogRuleRequestHelper.getCompanyId(), groupId,
					getKeywords(), _searchContainer.getStart(),
					_searchContainer.getEnd(), sort);

			_searchContainer.setTotal(baseModelSearchResult.getLength());
			_searchContainer.setResults(baseModelSearchResult.getBaseModels());
		}
		else {
			int total = _cpRuleService.getCPRulesCount(groupId);

			_searchContainer.setTotal(total);

			List<CPRule> results = _cpRuleService.getCPRules(
				groupId, _searchContainer.getStart(), _searchContainer.getEnd(),
				orderByComparator);

			_searchContainer.setResults(results);
		}

		return _searchContainer;
	}

	public boolean hasPermission(long cpRuleId, String actionId)
		throws PortalException {

		return _cpRuleModelResourcePermission.contains(
			_cpCatalogRuleRequestHelper.getPermissionChecker(), cpRuleId,
			actionId);
	}

	public boolean hasPermission(String actionId) {
		return _portletResourcePermission.contains(
			_cpCatalogRuleRequestHelper.getPermissionChecker(),
			_cpCatalogRuleRequestHelper.getScopeGroupId(), actionId);
	}

	protected long[] getCheckedcommerceAccountGroupIds()
		throws PortalException {

		List<Long> commerceAccountGroupIdsList = new ArrayList<>();

		List<CPRuleCommerceAccountGroupRel> cpRuleCommerceAccountGroupRels =
			getCPRuleCommerceAccountGroupRels();

		for (CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel :
				cpRuleCommerceAccountGroupRels) {

			commerceAccountGroupIdsList.add(
				cpRuleCommerceAccountGroupRel.getCommerceAccountGroupId());
		}

		if (commerceAccountGroupIdsList.isEmpty()) {
			return new long[0];
		}

		Stream<Long> stream = commerceAccountGroupIdsList.stream();

		LongStream longStream = stream.mapToLong(l -> l);

		return longStream.toArray();
	}

	protected List<CPRuleCommerceAccountGroupRel>
			getCPRuleCommerceAccountGroupRels()
		throws PortalException {

		return getCPRuleCommerceAccountGroupRels(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new CPRuleCommerceAccountGroupRelCreateDateComparator());
	}

	protected List<CPRuleCommerceAccountGroupRel>
			getCPRuleCommerceAccountGroupRels(
				int start, int end,
				OrderByComparator<CPRuleCommerceAccountGroupRel>
					orderByComparator)
		throws PortalException {

		return _cpRuleCommerceAccountGroupRelService.
			getCPRuleCommerceAccountGroupRels(
				getCPRuleId(), start, end, orderByComparator);
	}

	protected String getKeywords() {
		if (Validator.isNotNull(_keywords)) {
			return _keywords;
		}

		_keywords = ParamUtil.getString(
			_cpCatalogRuleRequestHelper.getRequest(), "keywords");

		return _keywords;
	}

	protected boolean isSearch() {
		if (Validator.isNotNull(getKeywords())) {
			return true;
		}

		return false;
	}

	protected <T> void setOrderByColAndType(
		Class<T> clazz, SearchContainer<T> searchContainer,
		String defaultOrderByCol, String defaultOrderByType) {

		HttpServletRequest httpServletRequest =
			_cpCatalogRuleRequestHelper.getRequest();

		String orderByCol = ParamUtil.getString(
			httpServletRequest, searchContainer.getOrderByColParam());
		String orderByType = ParamUtil.getString(
			httpServletRequest, searchContainer.getOrderByTypeParam());

		String namespace = _cpCatalogRuleRequestHelper.getPortletId();
		String prefix = TextFormatter.format(
			clazz.getSimpleName(), TextFormatter.K);

		if (Validator.isNotNull(orderByCol) &&
			Validator.isNotNull(orderByType)) {

			_portalPreferences.setValue(
				namespace, prefix + "-order-by-col", orderByCol);
			_portalPreferences.setValue(
				namespace, prefix + "-order-by-type", orderByType);
		}
		else {
			orderByCol = _portalPreferences.getValue(
				namespace, prefix + "-order-by-col", defaultOrderByCol);
			orderByType = _portalPreferences.getValue(
				namespace, prefix + "-order-by-type", defaultOrderByType);
		}

		searchContainer.setOrderByCol(orderByCol);
		searchContainer.setOrderByType(orderByType);
	}

	private final CPCatalogRuleRequestHelper _cpCatalogRuleRequestHelper;
	private CPRule _cpRule;
	private final CPRuleAssetCategoryRelService _cpRuleAssetCategoryRelService;
	private final CPRuleCommerceAccountGroupRelService
		_cpRuleCommerceAccountGroupRelService;
	private SearchContainer<CPRuleCommerceAccountGroupRel>
		_cpRuleCommerceAccountGroupRelsSearchContainer;
	private final ModelResourcePermission<CPRule>
		_cpRuleModelResourcePermission;
	private final CPRuleService _cpRuleService;
	private final CPRuleTypeJSPContributorRegistry
		_cpRuleTypeJSPContributorRegistry;
	private final CPRuleTypeRegistry _cpRuleTypeRegistry;
	private final ItemSelector _itemSelector;
	private String _keywords;
	private final PortalPreferences _portalPreferences;
	private final PortletResourcePermission _portletResourcePermission;
	private SearchContainer<CPRule> _searchContainer;

}