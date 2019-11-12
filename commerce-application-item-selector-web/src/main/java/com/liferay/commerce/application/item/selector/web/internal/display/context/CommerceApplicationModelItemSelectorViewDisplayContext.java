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

package com.liferay.commerce.application.item.selector.web.internal.display.context;

import com.liferay.commerce.application.item.selector.web.internal.search.CommerceApplicationModelItemSelectorChecker;
import com.liferay.commerce.application.model.CommerceApplicationModel;
import com.liferay.commerce.application.service.CommerceApplicationModelService;
import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceApplicationModelItemSelectorViewDisplayContext
	extends BaseCommerceApplicationItemSelectorViewDisplayContext
		<CommerceApplicationModel> {

	public CommerceApplicationModelItemSelectorViewDisplayContext(
		CommerceApplicationModelService commerceApplicationModelService,
		HttpServletRequest httpServletRequest, PortletURL portletURL,
		String itemSelectedEventName) {

		super(httpServletRequest, portletURL, itemSelectedEventName);

		_commerceApplicationModelService = commerceApplicationModelService;

		setDefaultOrderByCol("name");
		setDefaultOrderByType("asc");
	}

	@Override
	public PortletURL getPortletURL() {
		PortletURL portletURL = super.getPortletURL();

		String checkedCommerceApplicationModelIds = StringUtil.merge(
			getCheckedCommerceApplicationModelIds());

		portletURL.setParameter(
			"checkedCommerceApplicationModelIds",
			checkedCommerceApplicationModelIds);

		return portletURL;
	}

	public SearchContainer<CommerceApplicationModel> getSearchContainer()
		throws PortalException {

		if (searchContainer != null) {
			return searchContainer;
		}

		searchContainer = new SearchContainer<>(
			commerceApplicationItemSelectorRequestHelper.getRenderRequest(),
			getPortletURL(), null, null);

		searchContainer.setEmptyResultsMessage("there-are-no-models");

		searchContainer.setOrderByCol(getOrderByCol());
		searchContainer.setOrderByType(getOrderByType());

		RowChecker rowChecker = new CommerceApplicationModelItemSelectorChecker(
			commerceApplicationItemSelectorRequestHelper.getRenderResponse(),
			getCheckedCommerceApplicationModelIds());

		searchContainer.setRowChecker(rowChecker);

		List<CommerceApplicationModel> results =
			_commerceApplicationModelService.
				getCommerceApplicationModelsByCompanyId(
					commerceApplicationItemSelectorRequestHelper.getCompanyId(),
					searchContainer.getStart(), searchContainer.getEnd());

		searchContainer.setResults(results);

		int total =
			_commerceApplicationModelService.
				getCommerceApplicationModelsCountByCompanyId(
					commerceApplicationItemSelectorRequestHelper.
						getCompanyId());

		searchContainer.setTotal(total);

		return searchContainer;
	}

	protected long[] getCheckedCommerceApplicationModelIds() {
		return ParamUtil.getLongValues(
			commerceApplicationItemSelectorRequestHelper.getRenderRequest(),
			"checkedCommerceApplicationModelIds");
	}

	private final CommerceApplicationModelService
		_commerceApplicationModelService;

}