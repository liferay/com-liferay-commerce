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

package com.liferay.commerce.item.selector.web.internal.display.context;

import com.liferay.commerce.item.selector.web.internal.search.CommercePriceListItemSelectorChecker;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.CommercePriceListService;
import com.liferay.commerce.price.list.util.comparator.CommercePriceListCreateDateComparator;
import com.liferay.commerce.price.list.util.comparator.CommercePriceListDisplayDateComparator;
import com.liferay.commerce.price.list.util.comparator.CommercePriceListPriorityComparator;
import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class CommercePriceListItemSelectorViewDisplayContext
	extends BaseCommerceItemSelectorViewDisplayContext<CommercePriceList> {

	public CommercePriceListItemSelectorViewDisplayContext(
		CommercePriceListService commercePriceListService,
		HttpServletRequest httpServletRequest, PortletURL portletURL,
		String itemSelectedEventName) {

		super(httpServletRequest, portletURL, itemSelectedEventName);

		_commercePriceListService = commercePriceListService;

		setDefaultOrderByCol("create-date");
		setDefaultOrderByType("desc");
	}

	public SearchContainer<CommercePriceList> getSearchContainer()
		throws PortalException {

		if (searchContainer != null) {
			return searchContainer;
		}

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		searchContainer = new SearchContainer<>(
			cpRequestHelper.getRenderRequest(), getPortletURL(), null, null);

		searchContainer.setEmptyResultsMessage("there-are-no-price-lists");

		searchContainer.setOrderByCol(getOrderByCol());

		OrderByComparator<CommercePriceList> orderByComparator =
			getCommercePriceListOrderByComparator(
				getOrderByCol(), getOrderByType());

		searchContainer.setOrderByComparator(orderByComparator);

		searchContainer.setOrderByType(getOrderByType());

		RowChecker rowChecker = new CommercePriceListItemSelectorChecker(
			cpRequestHelper.getRenderResponse(),
			getCheckedCommercePriceListIds());

		searchContainer.setRowChecker(rowChecker);

		if (searchContainer.isSearch()) {
			Sort sort = getCommercePriceListSort(
				getOrderByCol(), getOrderByType());

			BaseModelSearchResult<CommercePriceList>
				commercePriceListBaseModelSearchResult =
					_commercePriceListService.searchCommercePriceLists(
						themeDisplay.getCompanyId(), getKeywords(),
						WorkflowConstants.STATUS_APPROVED,
						searchContainer.getStart(), searchContainer.getEnd(),
						sort);

			searchContainer.setResults(
				commercePriceListBaseModelSearchResult.getBaseModels());
			searchContainer.setTotal(
				commercePriceListBaseModelSearchResult.getLength());
		}
		else {
			List<CommercePriceList> results =
				_commercePriceListService.getCommercePriceLists(
					themeDisplay.getCompanyId(),
					WorkflowConstants.STATUS_APPROVED,
					searchContainer.getStart(), searchContainer.getEnd(),
					orderByComparator);

			searchContainer.setResults(results);

			int total = _commercePriceListService.getCommercePriceListsCount(
				themeDisplay.getCompanyId(), WorkflowConstants.STATUS_APPROVED);

			searchContainer.setTotal(total);
		}

		return searchContainer;
	}

	protected static OrderByComparator<CommercePriceList>
		getCommercePriceListOrderByComparator(
			String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		if (orderByCol.equals("create-date")) {
			return new CommercePriceListCreateDateComparator(orderByAsc);
		}
		else if (orderByCol.equals("display-date")) {
			return new CommercePriceListDisplayDateComparator(orderByAsc);
		}
		else if (orderByCol.equals("priority")) {
			return new CommercePriceListPriorityComparator(orderByAsc);
		}

		return null;
	}

	protected static Sort getCommercePriceListSort(
		String orderByCol, String orderByType) {

		boolean reverse = true;

		if (orderByType.equals("asc")) {
			reverse = false;
		}

		if (orderByCol.equals("create-date")) {
			return SortFactoryUtil.create(Field.CREATE_DATE, reverse);
		}
		else if (orderByCol.equals("display-date")) {
			return SortFactoryUtil.create("display-date", reverse);
		}
		else if (orderByCol.equals("priority")) {
			return SortFactoryUtil.create(Field.PRIORITY, reverse);
		}

		return null;
	}

	protected long[] getCheckedCommercePriceListIds() {
		return ParamUtil.getLongValues(
			cpRequestHelper.getRenderRequest(), "checkedCommercePriceListIds");
	}

	private final CommercePriceListService _commercePriceListService;

}