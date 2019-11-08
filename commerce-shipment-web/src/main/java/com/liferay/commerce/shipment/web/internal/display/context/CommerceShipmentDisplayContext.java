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

package com.liferay.commerce.shipment.web.internal.display.context;

import com.liferay.commerce.constants.CommercePortletKeys;
import com.liferay.commerce.constants.CommerceShipmentConstants;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseService;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.model.CommerceShipment;
import com.liferay.commerce.model.CommerceShipmentItem;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CommerceChannelService;
import com.liferay.commerce.service.CommerceOrderItemService;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.commerce.service.CommerceShipmentItemLocalServiceUtil;
import com.liferay.commerce.service.CommerceShipmentService;
import com.liferay.commerce.shipment.web.internal.portlet.action.ActionHelper;
import com.liferay.commerce.shipment.web.internal.util.CommerceShipmentPortletUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceShipmentDisplayContext
	extends BaseCommerceShipmentDisplayContext<CommerceShipment> {

	public CommerceShipmentDisplayContext(
		ActionHelper actionHelper, HttpServletRequest httpServletRequest,
		CommerceChannelService commerceChannelService,
		CommerceOrderItemService commerceOrderItemService,
		CommerceOrderLocalService commerceOrderLocalService,
		CommerceShipmentService commerceShipmentService,
		CommerceInventoryWarehouseService commerceInventoryWarehouseService) {

		super(
			actionHelper, httpServletRequest,
			CommerceShipment.class.getSimpleName());

		_commerceChannelService = commerceChannelService;
		_commerceOrderItemService = commerceOrderItemService;
		_commerceOrderLocalService = commerceOrderLocalService;
		_commerceShipmentService = commerceShipmentService;
		_commerceInventoryWarehouseService = commerceInventoryWarehouseService;
	}

	public int getCommerceInventoryWarehouseItemQuantity(
			long commerceOrderItemId, long commerceInventoryWarehouseId)
		throws PortalException {

		return _commerceOrderItemService.
			getCommerceInventoryWarehouseItemQuantity(
				commerceOrderItemId, commerceInventoryWarehouseId);
	}

	public List<CommerceInventoryWarehouse> getCommerceInventoryWarehouses()
		throws PortalException {

		if (_commerceInventoryWarehouses != null) {
			return _commerceInventoryWarehouses;
		}

		_commerceInventoryWarehouses =
			_commerceInventoryWarehouseService.getCommerceInventoryWarehouses(
				cpRequestHelper.getCompanyId(),
				cpRequestHelper.getChannelGroupId(), true);

		return _commerceInventoryWarehouses;
	}

	public long getCommerceOrderId(long commerceShipmentId)
		throws PortalException {

		List<CommerceShipmentItem> commerceShipmentItems =
			CommerceShipmentItemLocalServiceUtil.getCommerceShipmentItems(
				commerceShipmentId, 0, 1, null);

		if (commerceShipmentItems.isEmpty()) {
			return 0;
		}

		CommerceShipmentItem commerceShipmentItem = commerceShipmentItems.get(
			0);

		CommerceOrderItem commerceOrderItem =
			_commerceOrderItemService.getCommerceOrderItem(
				commerceShipmentItem.getCommerceOrderItemId());

		return commerceOrderItem.getCommerceOrderId();
	}

	public List<CommerceOrderItem> getCommerceOrderItems(long commerceOrderId)
		throws PortalException {

		if (commerceOrderId <= 0) {
			return Collections.emptyList();
		}

		return _commerceOrderItemService.
			getAvailableForShipmentCommerceOrderItems(commerceOrderId);
	}

	public List<CommerceOrder> getCommerceOrders() throws PortalException {
		SearchContext searchContext = _buildSearchContext();

		BaseModelSearchResult<CommerceOrder> baseModelSearchResult =
			_commerceOrderLocalService.searchCommerceOrders(searchContext);

		return baseModelSearchResult.getBaseModels();
	}

	public String getCommerceOrderUrl(long commerceOrderId)
		throws PortalException {

		PortletURL portletURL = PortletURLFactoryUtil.create(
			httpServletRequest, CommercePortletKeys.COMMERCE_ORDER,
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter("mvcRenderCommandName", "editCommerceOrder");
		portletURL.setParameter(
			"commerceOrderId", String.valueOf(commerceOrderId));

		return portletURL.toString();
	}

	public String getCommerceShipmentStatusLabel(int status) {
		return LanguageUtil.get(
			cpRequestHelper.getLocale(),
			CommerceShipmentConstants.getShipmentStatusLabel(status));
	}

	public String getNavigation() {
		return ParamUtil.getString(
			cpRequestHelper.getRequest(), "navigation", "all");
	}

	public String[] getNavigationKeys() {
		int[] shipmentStatuses = CommerceShipmentConstants.SHIPMENT_STATUSES;

		String[] navigationKeys = new String[0];

		navigationKeys = ArrayUtil.append(navigationKeys, "all");

		for (int shipmentStatus : shipmentStatuses) {
			navigationKeys = ArrayUtil.append(
				navigationKeys,
				CommerceShipmentConstants.getShipmentStatusLabel(
					shipmentStatus));
		}

		return navigationKeys;
	}

	public int getNumberOfItemsShipped(long commerceShipmentId)
		throws PortalException {

		return CommerceShipmentItemLocalServiceUtil.
			getCommerceShipmentItemsCount(commerceShipmentId);
	}

	@Override
	public PortletURL getPortletURL() throws PortalException {
		PortletURL portletURL = super.getPortletURL();

		portletURL.setParameter("navigation", getNavigation());

		return portletURL;
	}

	@Override
	public SearchContainer<CommerceShipment> getSearchContainer()
		throws PortalException {

		if (searchContainer != null) {
			return searchContainer;
		}

		searchContainer = new SearchContainer<>(
			liferayPortletRequest, getPortletURL(), null, null);

		OrderByComparator<CommerceShipment> orderByComparator =
			CommerceShipmentPortletUtil.getCommerceShipmentOrderByComparator(
				getOrderByCol(), getOrderByType());

		String emptyResultsMessage = "no-shipments-were-found";

		String navigation = getNavigation();

		if (!navigation.equals("all")) {
			emptyResultsMessage = LanguageUtil.format(
				cpRequestHelper.getRequest(), "no-x-shipments-were-found",
				navigation, true);
		}

		searchContainer.setEmptyResultsMessage(emptyResultsMessage);
		searchContainer.setOrderByCol(getOrderByCol());
		searchContainer.setOrderByComparator(orderByComparator);
		searchContainer.setOrderByType(getOrderByType());

		int total;
		List<CommerceShipment> results;

		Integer shipmentStatus = CommerceShipmentConstants.getShipmentStatus(
			navigation);

		if (!navigation.equals("all") && (shipmentStatus != null)) {
			total = _commerceShipmentService.getCommerceShipmentsCount(
				cpRequestHelper.getCompanyId(), shipmentStatus);
			results = _commerceShipmentService.getCommerceShipments(
				cpRequestHelper.getCompanyId(), shipmentStatus,
				searchContainer.getStart(), searchContainer.getEnd(),
				orderByComparator);
		}
		else {
			total = _commerceShipmentService.getCommerceShipmentsCount(
				cpRequestHelper.getCompanyId());
			results = _commerceShipmentService.getCommerceShipments(
				cpRequestHelper.getCompanyId(), searchContainer.getStart(),
				searchContainer.getEnd(), orderByComparator);
		}

		searchContainer.setTotal(total);
		searchContainer.setResults(results);

		return searchContainer;
	}

	private SearchContext _buildSearchContext() throws PortalException {
		SearchContext searchContext = new SearchContext();

		searchContext.setAttribute(
			"orderStatuses", CommerceShipmentConstants.ALLOWED_ORDER_STATUSES);

		searchContext.setAttribute(
			"useSearchResultPermissionFilter", Boolean.FALSE);

		searchContext.setAttribute(
			Field.STATUS, WorkflowConstants.STATUS_APPROVED);

		searchContext.setCompanyId(cpRequestHelper.getCompanyId());
		searchContext.setStart(QueryUtil.ALL_POS);
		searchContext.setEnd(QueryUtil.ALL_POS);

		long[] commerceChannelGroupIds = _getCommerceChannelGroupIds();

		if ((commerceChannelGroupIds != null) &&
			(commerceChannelGroupIds.length > 0)) {

			searchContext.setGroupIds(commerceChannelGroupIds);
		}

		QueryConfig queryConfig = searchContext.getQueryConfig();

		queryConfig.setHighlightEnabled(false);
		queryConfig.setScoreEnabled(false);

		return searchContext;
	}

	private long[] _getCommerceChannelGroupIds() throws PortalException {
		List<CommerceChannel> commerceChannels =
			_commerceChannelService.searchCommerceChannels(
				cpRequestHelper.getCompanyId());

		Stream<CommerceChannel> stream = commerceChannels.stream();

		return stream.mapToLong(
			CommerceChannel::getGroupId
		).toArray();
	}

	private final CommerceChannelService _commerceChannelService;
	private List<CommerceInventoryWarehouse> _commerceInventoryWarehouses;
	private final CommerceInventoryWarehouseService
		_commerceInventoryWarehouseService;
	private final CommerceOrderItemService _commerceOrderItemService;
	private final CommerceOrderLocalService _commerceOrderLocalService;
	private final CommerceShipmentService _commerceShipmentService;

}