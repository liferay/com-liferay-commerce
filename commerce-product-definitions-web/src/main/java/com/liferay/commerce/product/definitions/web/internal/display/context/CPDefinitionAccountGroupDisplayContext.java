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

package com.liferay.commerce.product.definitions.web.internal.display.context;

import com.liferay.commerce.account.item.selector.criterion.CommerceAccountGroupItemSelectorCriterion;
import com.liferay.commerce.account.model.CommerceAccountGroup;
import com.liferay.commerce.account.model.CommerceAccountGroupRel;
import com.liferay.commerce.account.service.CommerceAccountGroupRelService;
import com.liferay.commerce.account.service.CommerceAccountGroupService;
import com.liferay.commerce.product.definitions.web.portlet.action.ActionHelper;
import com.liferay.commerce.product.definitions.web.servlet.taglib.ui.CPDefinitionScreenNavigationConstants;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.service.CPDefinitionService;
import com.liferay.commerce.product.service.CommerceCatalogService;
import com.liferay.item.selector.ItemSelector;
import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.criteria.UUIDItemSelectorReturnType;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.util.Validator;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class CPDefinitionAccountGroupDisplayContext
	extends CPDefinitionsDisplayContext {

	public CPDefinitionAccountGroupDisplayContext(
		ActionHelper actionHelper, HttpServletRequest httpServletRequest,
		CommerceCatalogService commerceCatalogService,
		CPDefinitionService cpDefinitionService, ItemSelector itemSelector,
		CommerceAccountGroupRelService commerceAccountGroupRelService,
		CommerceAccountGroupService commerceAccountGroupService) {

		super(
			actionHelper, httpServletRequest, commerceCatalogService,
			cpDefinitionService);

		_itemSelector = itemSelector;
		_commerceAccountGroupRelService = commerceAccountGroupRelService;
		_commerceAccountGroupService = commerceAccountGroupService;
	}

	public CommerceAccountGroup getCommerceAccountGroup(
			long commerceAccountGroupId)
		throws PortalException {

		return _commerceAccountGroupService.getCommerceAccountGroup(
			commerceAccountGroupId);
	}

	public long[] getCommerceAccountGroupRelCommerceAccountGroupIds()
		throws PortalException {

		List<CommerceAccountGroupRel> commerceAccountGroupRels =
			_commerceAccountGroupRelService.getCommerceAccountGroupRels(
				CPDefinition.class.getName(), getCPDefinitionId(),
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		Stream<CommerceAccountGroupRel> stream =
			commerceAccountGroupRels.stream();

		return stream.mapToLong(
			CommerceAccountGroupRel::getCommerceAccountGroupId
		).toArray();
	}

	public List<CommerceAccountGroup> getCommerceAccountGroups()
		throws PortalException {

		return _commerceAccountGroupService.getCommerceAccountGroups(
			cpRequestHelper.getCompanyId(), QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	public SearchContainer<CommerceAccountGroupRel>
			getCPDefinitionAccountGroupSearchContainer()
		throws PortalException {

		if (_searchContainer != null) {
			return _searchContainer;
		}

		_searchContainer = new SearchContainer<>(
			liferayPortletRequest, getPortletURL(), null,
			"there-are-no-account-groups-related-to-this-product");

		_setOrderByColAndType(
			CommerceAccountGroupRel.class, _searchContainer, "name", "asc");

		_searchContainer.setRowChecker(
			new EmptyOnClickRowChecker(liferayPortletResponse));

		String cpDefinitionClassName = CPDefinition.class.getName();

		long cpDefinitionId = getCPDefinitionId();

		int total =
			_commerceAccountGroupRelService.getCommerceAccountGroupRelsCount(
				cpDefinitionClassName, cpDefinitionId);

		List<CommerceAccountGroupRel> results =
			_commerceAccountGroupRelService.getCommerceAccountGroupRels(
				cpDefinitionClassName, cpDefinitionId,
				_searchContainer.getStart(), _searchContainer.getEnd(), null);

		_searchContainer.setTotal(total);
		_searchContainer.setResults(results);

		return _searchContainer;
	}

	public String getItemSelectorUrl() {
		RequestBackedPortletURLFactory requestBackedPortletURLFactory =
			RequestBackedPortletURLFactoryUtil.create(httpServletRequest);

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

		try {
			String checkedCommerceAccountGroupIds = StringUtil.merge(
				getCommerceAccountGroupRelCommerceAccountGroupIds());

			itemSelectorURL.setParameter(
				"checkedCommerceAccountGroupIds",
				checkedCommerceAccountGroupIds);
		}
		catch (PortalException pe) {
			_log.error(pe, pe);
		}

		return itemSelectorURL.toString();
	}

	@Override
	public PortletURL getPortletURL() throws PortalException {
		PortletURL portletURL = super.getPortletURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "editProductDefinition");
		portletURL.setParameter(
			"screenNavigationCategoryKey", getScreenNavigationCategoryKey());
		portletURL.setParameter(
			"screenNavigationEntryKey",
			CPDefinitionScreenNavigationConstants.ENTRY_KEY_ACCOUNT_GROUPS);

		return portletURL;
	}

	@Override
	public String getScreenNavigationCategoryKey() {
		return CPDefinitionScreenNavigationConstants.CATEGORY_KEY_CONFIGURATION;
	}

	private void _setOrderByColAndType(
		Class<CommerceAccountGroupRel> clazz,
		SearchContainer<CommerceAccountGroupRel> searchContainer,
		String defaultOrderByCol, String defaultOrderByType) {

		String orderByCol = ParamUtil.getString(
			httpServletRequest, searchContainer.getOrderByColParam());
		String orderByType = ParamUtil.getString(
			httpServletRequest, searchContainer.getOrderByTypeParam());

		String namespace = PortalUtil.getPortletId(httpServletRequest);
		String prefix = TextFormatter.format(
			clazz.getSimpleName(), TextFormatter.K);

		if (Validator.isNotNull(orderByCol) &&
			Validator.isNotNull(orderByType)) {

			portalPreferences.setValue(
				namespace, prefix + "-order-by-col", orderByCol);
			portalPreferences.setValue(
				namespace, prefix + "-order-by-type", orderByType);
		}
		else {
			orderByCol = portalPreferences.getValue(
				namespace, prefix + "-order-by-col", defaultOrderByCol);
			orderByType = portalPreferences.getValue(
				namespace, prefix + "-order-by-type", defaultOrderByType);
		}

		searchContainer.setOrderByCol(orderByCol);
		searchContainer.setOrderByType(orderByType);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CPDefinitionAccountGroupDisplayContext.class);

	private final CommerceAccountGroupRelService
		_commerceAccountGroupRelService;
	private final CommerceAccountGroupService _commerceAccountGroupService;
	private final ItemSelector _itemSelector;
	private SearchContainer<CommerceAccountGroupRel> _searchContainer;

}