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

import com.liferay.commerce.catalog.web.display.context.BaseCommerceCatalogSearchContainerDisplayContext;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.CommerceCatalogLocalServiceUtil;
import com.liferay.commerce.product.service.CommerceCatalogService;
import com.liferay.frontend.taglib.servlet.taglib.ManagementBarFilterItem;
import com.liferay.item.selector.ItemSelector;
import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.criteria.UUIDItemSelectorReturnType;
import com.liferay.layout.item.selector.criterion.LayoutItemSelectorCriterion;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Collections;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alec Sloan
 */
public class CommerceCatalogDisplayContext
	extends BaseCommerceCatalogSearchContainerDisplayContext<CommerceCatalog> {

	public CommerceCatalogDisplayContext(
			HttpServletRequest httpServletRequest,
			CommerceCatalogService commerceCatalogService,
			ItemSelector itemSelector,
			PortletResourcePermission portletResourcePermission)
		throws PortalException {

		super(httpServletRequest, CommerceCatalog.class.getSimpleName());

		setDefaultOrderByType("desc");

		_commerceCatalogService = commerceCatalogService;
		_itemSelector = itemSelector;
		_portletResourcePermission = portletResourcePermission;
	}

	public String getCatalogURL(HttpServletRequest httpServletRequest)
		throws PortalException {

		CommerceCatalog commerceCatalog = null;

		if (commerceCatalog == null) {
			return StringPool.BLANK;
		}

		PortletURL portletURL = PortletProviderUtil.getPortletURL(
			httpServletRequest, CommerceCatalog.class.getName(),
			PortletProvider.Action.VIEW);

		portletURL.setParameter(
			"commerceCatalogId",
			String.valueOf(commerceCatalog.getCommerceCatalogId()));

		return portletURL.toString();
	}

	public CommerceCatalog getCommerceCatalog(
			HttpServletRequest httpServletRequest)
		throws PortalException {

		long commerceCatalogId = ParamUtil.getLong(
			httpServletRequest, "commerceCatalogId");

		return CommerceCatalogLocalServiceUtil.fetchCommerceCatalog(
			commerceCatalogId);
	}

	public String getItemSelectorUrl() {
		RequestBackedPortletURLFactory requestBackedPortletURLFactory =
			RequestBackedPortletURLFactoryUtil.create(
				cpRequestHelper.getRenderRequest());

		LayoutItemSelectorCriterion layoutItemSelectorCriterion =
			new LayoutItemSelectorCriterion();

		layoutItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
			Collections.<ItemSelectorReturnType>singletonList(
				new UUIDItemSelectorReturnType()));

		PortletURL itemSelectorURL = _itemSelector.getItemSelectorURL(
			requestBackedPortletURLFactory, "selectDisplayPage",
			layoutItemSelectorCriterion);

		return itemSelectorURL.toString();
	}

	@Override
	public List<ManagementBarFilterItem> getManagementBarStatusFilterItems()
		throws PortalException, PortletException {

		return null;
	}

	@Override
	public PortletURL getPortletURL() throws PortalException {
		PortletURL portletURL = super.getPortletURL();

		String filterFields = ParamUtil.getString(
			httpServletRequest, "filterFields");

		if (Validator.isNotNull(filterFields)) {
			portletURL.setParameter("filterFields", filterFields);
		}

		String filtersLabels = ParamUtil.getString(
			httpServletRequest, "filtersLabels");

		if (Validator.isNotNull(filtersLabels)) {
			portletURL.setParameter("filtersLabels", filtersLabels);
		}

		String filtersValues = ParamUtil.getString(
			httpServletRequest, "filtersValues");

		if (Validator.isNotNull(filtersValues)) {
			portletURL.setParameter("filtersValues", filtersValues);
		}

		return portletURL;
	}

	@Override
	public SearchContainer<CommerceCatalog> getSearchContainer()
		throws PortalException {

		if (searchContainer != null) {
			return searchContainer;
		}

		searchContainer = new SearchContainer<>(
			liferayPortletRequest, getPortletURL(), null, null);

		searchContainer.setOrderByCol(getOrderByCol());
		searchContainer.setOrderByType(getOrderByType());
		searchContainer.setRowChecker(getRowChecker());

		searchContainer.setTotal(0);
		searchContainer.setResults(null);

		return searchContainer;
	}

	private final CommerceCatalogService _commerceCatalogService;
	private final ItemSelector _itemSelector;
	private final PortletResourcePermission _portletResourcePermission;

}