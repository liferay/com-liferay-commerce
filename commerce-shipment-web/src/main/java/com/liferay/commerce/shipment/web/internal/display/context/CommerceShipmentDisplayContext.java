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

import com.liferay.commerce.constants.CommerceShipmentConstants;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseService;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.model.CommerceShipment;
import com.liferay.commerce.service.CommerceOrderItemService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.commerce.service.CommerceShipmentService;
import com.liferay.commerce.shipment.web.internal.portlet.action.ActionHelper;
import com.liferay.commerce.shipment.web.internal.util.CommerceShipmentPortletUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.Collections;
import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceShipmentDisplayContext
	extends BaseCommerceShipmentDisplayContext<CommerceShipment> {

	public CommerceShipmentDisplayContext(
		ActionHelper actionHelper, HttpServletRequest httpServletRequest,
		CommerceOrderItemService commerceOrderItemService,
		CommerceOrderService commerceOrderService,
		CommerceShipmentService commerceShipmentService,
		CommerceInventoryWarehouseService commerceInventoryWarehouseService,
		PortletResourcePermission portletResourcePermission) {

		super(
			actionHelper, httpServletRequest,
			CommerceShipment.class.getSimpleName(), portletResourcePermission);

		_commerceOrderItemService = commerceOrderItemService;
		_commerceOrderService = commerceOrderService;
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
				cpRequestHelper.getCompanyId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		return _commerceInventoryWarehouses;
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
		return _commerceOrderService.getCommerceOrders(
			cpRequestHelper.getChannelGroupId(),
			CommerceShipmentConstants.ALLOWED_ORDER_STATUSES);
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
				cpRequestHelper.getChannelGroupId(), shipmentStatus);
			results = _commerceShipmentService.getCommerceShipments(
				cpRequestHelper.getChannelGroupId(), shipmentStatus,
				searchContainer.getStart(), searchContainer.getEnd(),
				orderByComparator);
		}
		else {
			total = _commerceShipmentService.getCommerceShipmentsCount(
				cpRequestHelper.getChannelGroupId());
			results = _commerceShipmentService.getCommerceShipments(
				cpRequestHelper.getChannelGroupId(), searchContainer.getStart(),
				searchContainer.getEnd(), orderByComparator);
		}

		searchContainer.setTotal(total);
		searchContainer.setResults(results);

		return searchContainer;
	}

	private List<CommerceInventoryWarehouse> _commerceInventoryWarehouses;
	private final CommerceInventoryWarehouseService
		_commerceInventoryWarehouseService;
	private final CommerceOrderItemService _commerceOrderItemService;
	private final CommerceOrderService _commerceOrderService;
	private final CommerceShipmentService _commerceShipmentService;

}