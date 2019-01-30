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

package com.liferay.commerce.price.list.web.internal.display.context;

import com.liferay.commerce.account.item.selector.criterion.CommerceAccountItemSelectorCriterion;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountService;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.service.CommerceCurrencyService;
import com.liferay.commerce.currency.util.comparator.CommerceCurrencyPriorityComparator;
import com.liferay.commerce.item.selector.criterion.CommercePriceListItemSelectorCriterion;
import com.liferay.commerce.price.list.constants.CommercePriceListActionKeys;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.model.CommercePriceListAccountRel;
import com.liferay.commerce.price.list.model.CommercePriceListUserSegmentEntryRel;
import com.liferay.commerce.price.list.service.CommercePriceListAccountRelService;
import com.liferay.commerce.price.list.service.CommercePriceListService;
import com.liferay.commerce.price.list.service.CommercePriceListUserSegmentEntryRelService;
import com.liferay.commerce.price.list.util.comparator.CommercePriceListPriorityComparator;
import com.liferay.commerce.price.list.web.display.context.BaseCommercePriceListDisplayContext;
import com.liferay.commerce.price.list.web.internal.util.CommercePriceListPortletUtil;
import com.liferay.commerce.price.list.web.portlet.action.CommercePriceListActionHelper;
import com.liferay.commerce.user.segment.item.selector.criterion.CommerceUserSegmentEntryItemSelectorCriterion;
import com.liferay.commerce.user.segment.model.CommerceUserSegmentEntry;
import com.liferay.commerce.user.segment.service.CommerceUserSegmentEntryService;
import com.liferay.item.selector.ItemSelector;
import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.criteria.Base64ItemSelectorReturnType;
import com.liferay.item.selector.criteria.UUIDItemSelectorReturnType;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class CommercePriceListDisplayContext
	extends BaseCommercePriceListDisplayContext<CommercePriceList> {

	public CommercePriceListDisplayContext(
		CommercePriceListActionHelper commercePriceListActionHelper,
		CommerceAccountService commerceAccountService,
		CommerceCurrencyService commerceCurrencyService,
		CommerceUserSegmentEntryService commerceUserSegmentEntryService,
		CommercePriceListAccountRelService commercePriceListAccountRelService,
		CommercePriceListUserSegmentEntryRelService
			commercePriceListUserSegmentEntryRelService,
		CommercePriceListService commercePriceListService,
		HttpServletRequest httpServletRequest, ItemSelector itemSelector,
		PortletResourcePermission portletResourcePermission) {

		super(commercePriceListActionHelper, httpServletRequest);

		_commerceAccountService = commerceAccountService;
		_commerceCurrencyService = commerceCurrencyService;
		_commerceUserSegmentEntryService = commerceUserSegmentEntryService;
		_commercePriceListAccountRelService =
			commercePriceListAccountRelService;
		_commercePriceListUserSegmentEntryRelService =
			commercePriceListUserSegmentEntryRelService;
		_commercePriceListService = commercePriceListService;
		_itemSelector = itemSelector;
		_portletResourcePermission = portletResourcePermission;

		setDefaultOrderByCol("priority");
		setDefaultOrderByType("asc");
	}

	public CommerceAccount getCommerceAccount(long commerceAccountId)
		throws PortalException {

		return _commerceAccountService.getCommerceAccount(commerceAccountId);
	}

	public String getCommerceAccountSelectorUrl() throws PortalException {
		RequestBackedPortletURLFactory requestBackedPortletURLFactory =
			RequestBackedPortletURLFactoryUtil.create(httpServletRequest);

		CommerceAccountItemSelectorCriterion
			commerceAccountItemSelectorCriterion =
				new CommerceAccountItemSelectorCriterion();

		commerceAccountItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
			Collections.<ItemSelectorReturnType>singletonList(
				new Base64ItemSelectorReturnType()));

		PortletURL itemSelectorURL = _itemSelector.getItemSelectorURL(
			requestBackedPortletURLFactory, "accountsSelectItem",
			commerceAccountItemSelectorCriterion);

		String checkedCommerceAccountIds = StringUtil.merge(
			getCheckedCommerceAccountIds());

		itemSelectorURL.setParameter(
			"checkedCommerceAccountIds", checkedCommerceAccountIds);

		return itemSelectorURL.toString();
	}

	public List<CommerceCurrency> getCommerceCurrencies()
		throws PortalException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		return _commerceCurrencyService.getCommerceCurrencies(
			themeDisplay.getScopeGroupId(), true, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, new CommerceCurrencyPriorityComparator(true));
	}

	public List<CommercePriceListAccountRel> getCommercePriceListAccountRels()
		throws PortalException {

		long commercePriceListId = getCommercePriceListId();

		if (commercePriceListId <= 0) {
			return Collections.emptyList();
		}

		return _commercePriceListAccountRelService.
			getCommercePriceListAccountRels(commercePriceListId);
	}

	public List<CommercePriceList> getCommercePriceLists()
		throws PortalException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		List<CommercePriceList> commercePriceLists = ListUtil.copy(
			_commercePriceListService.getCommercePriceLists(
				themeDisplay.getScopeGroupId(), WorkflowConstants.STATUS_ANY,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new CommercePriceListPriorityComparator(true)));

		commercePriceLists.remove(getCommercePriceList());

		return commercePriceLists;
	}

	public List<CommercePriceListUserSegmentEntryRel>
			getCommercePriceListUserSegmentEntryRels()
		throws PortalException {

		long commercePriceListId = getCommercePriceListId();

		if (commercePriceListId <= 0) {
			return Collections.emptyList();
		}

		return _commercePriceListUserSegmentEntryRelService.
			getCommercePriceListUserSegmentEntryRels(commercePriceListId);
	}

	public CommerceUserSegmentEntry getCommerceUserSegmentEntry(
			long commerceUserSegmentEntryId)
		throws PortalException {

		return _commerceUserSegmentEntryService.getCommerceUserSegmentEntry(
			commerceUserSegmentEntryId);
	}

	public String getCommerceUserSegmentEntrySelectorUrl()
		throws PortalException {

		RequestBackedPortletURLFactory requestBackedPortletURLFactory =
			RequestBackedPortletURLFactoryUtil.create(httpServletRequest);

		CommerceUserSegmentEntryItemSelectorCriterion
			commerceUserSegmentEntryItemSelectorCriterion =
				new CommerceUserSegmentEntryItemSelectorCriterion();

		commerceUserSegmentEntryItemSelectorCriterion.
			setDesiredItemSelectorReturnTypes(
				Collections.<ItemSelectorReturnType>singletonList(
					new UUIDItemSelectorReturnType()));

		PortletURL itemSelectorURL = _itemSelector.getItemSelectorURL(
			requestBackedPortletURLFactory, "userSegmentsSelectItem",
			commerceUserSegmentEntryItemSelectorCriterion);

		String checkedCommerceUserSegmentEntryIds = StringUtil.merge(
			getCheckedCommerceUserSegmentEntryIds());

		itemSelectorURL.setParameter(
			"checkedCommerceUserSegmentEntryIds",
			checkedCommerceUserSegmentEntryIds);

		return itemSelectorURL.toString();
	}

	public CommercePriceList getParentCommercePriceList()
		throws PortalException {

		return _commercePriceListService.fetchCommercePriceList(
			getParentCommercePriceListId());
	}

	public long getParentCommercePriceListId() throws PortalException {
		CommercePriceList commercePriceList = getCommercePriceList();

		if (commercePriceList == null) {
			return 0;
		}

		return commercePriceList.getParentCommercePriceListId();
	}

	public String getPriceListItemSelectorUrl() throws PortalException {
		RequestBackedPortletURLFactory requestBackedPortletURLFactory =
			RequestBackedPortletURLFactoryUtil.create(httpServletRequest);

		CommercePriceListItemSelectorCriterion
			commercePriceListItemSelectorCriterion =
				new CommercePriceListItemSelectorCriterion();

		commercePriceListItemSelectorCriterion.
			setDesiredItemSelectorReturnTypes(
				Collections.<ItemSelectorReturnType>singletonList(
					new UUIDItemSelectorReturnType()));

		PortletURL itemSelectorURL = _itemSelector.getItemSelectorURL(
			requestBackedPortletURLFactory, "priceListsSelectItem",
			commercePriceListItemSelectorCriterion);

		String checkedCommercePriceListIds = String.valueOf(
			getParentCommercePriceListId());

		itemSelectorURL.setParameter(
			"checkedCommercePriceListIds", checkedCommercePriceListIds);

		return itemSelectorURL.toString();
	}

	@Override
	public SearchContainer<CommercePriceList> getSearchContainer()
		throws PortalException {

		if (searchContainer != null) {
			return searchContainer;
		}

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		searchContainer = new SearchContainer<>(
			liferayPortletRequest, getPortletURL(), null,
			"there-are-no-price-lists");

		OrderByComparator<CommercePriceList> orderByComparator =
			CommercePriceListPortletUtil.getCommercePriceListOrderByComparator(
				getOrderByCol(), getOrderByType());

		searchContainer.setOrderByCol(getOrderByCol());
		searchContainer.setOrderByComparator(orderByComparator);
		searchContainer.setOrderByType(getOrderByType());
		searchContainer.setRowChecker(getRowChecker());

		if (isSearch()) {
			Sort sort = CommercePriceListPortletUtil.getCommercePriceListSort(
				getOrderByCol(), getOrderByType());

			BaseModelSearchResult<CommercePriceList>
				commercePriceListBaseModelSearchResult =
					_commercePriceListService.searchCommercePriceLists(
						themeDisplay.getCompanyId(),
						themeDisplay.getScopeGroupId(), getKeywords(),
						getStatus(), searchContainer.getStart(),
						searchContainer.getEnd(), sort);

			searchContainer.setTotal(
				commercePriceListBaseModelSearchResult.getLength());
			searchContainer.setResults(
				commercePriceListBaseModelSearchResult.getBaseModels());
		}
		else {
			int total = _commercePriceListService.getCommercePriceListsCount(
				themeDisplay.getScopeGroupId(), getStatus());

			searchContainer.setTotal(total);

			List<CommercePriceList> results =
				_commercePriceListService.getCommercePriceLists(
					themeDisplay.getScopeGroupId(), getStatus(),
					searchContainer.getStart(), searchContainer.getEnd(),
					orderByComparator);

			searchContainer.setResults(results);
		}

		return searchContainer;
	}

	public String getSelectedScreenNavigationCategoryKey() {
		return ParamUtil.getString(
			httpServletRequest, "screenNavigationCategoryKey",
			getScreenNavigationCategoryKey());
	}

	public int getStatus() {
		if (_status != null) {
			return _status;
		}

		_status = ParamUtil.getInteger(
			httpServletRequest, "status", WorkflowConstants.STATUS_ANY);

		return _status;
	}

	public boolean hasManageCommercePriceListPermission() {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		return _portletResourcePermission.contains(
			themeDisplay.getPermissionChecker(), themeDisplay.getScopeGroupId(),
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);
	}

	protected long[] getCheckedCommerceAccountIds() throws PortalException {
		List<CommercePriceListAccountRel> commercePriceListAccountRels =
			getCommercePriceListAccountRels();

		if (commercePriceListAccountRels.isEmpty()) {
			return new long[0];
		}

		Stream<CommercePriceListAccountRel> stream =
			commercePriceListAccountRels.stream();

		return stream.mapToLong(
			CommercePriceListAccountRel::getCommerceAccountId
		).toArray();
	}

	protected long[] getCheckedCommerceUserSegmentEntryIds()
		throws PortalException {

		List<CommercePriceListUserSegmentEntryRel>
			commercePriceListUserSegmentEntryRels =
				getCommercePriceListUserSegmentEntryRels();

		if (commercePriceListUserSegmentEntryRels.isEmpty()) {
			return new long[0];
		}

		Stream<CommercePriceListUserSegmentEntryRel> stream =
			commercePriceListUserSegmentEntryRels.stream();

		return stream.mapToLong(
			CommercePriceListUserSegmentEntryRel::getCommerceUserSegmentEntryId
		).toArray();
	}

	private final CommerceAccountService _commerceAccountService;
	private final CommerceCurrencyService _commerceCurrencyService;
	private final CommercePriceListAccountRelService
		_commercePriceListAccountRelService;
	private final CommercePriceListService _commercePriceListService;
	private final CommercePriceListUserSegmentEntryRelService
		_commercePriceListUserSegmentEntryRelService;
	private final CommerceUserSegmentEntryService
		_commerceUserSegmentEntryService;
	private final ItemSelector _itemSelector;
	private final PortletResourcePermission _portletResourcePermission;
	private Integer _status;

}