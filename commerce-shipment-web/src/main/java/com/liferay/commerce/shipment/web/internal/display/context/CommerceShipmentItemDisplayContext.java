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

import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseService;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.model.CommerceShipment;
import com.liferay.commerce.model.CommerceShipmentItem;
import com.liferay.commerce.service.CommerceCountryService;
import com.liferay.commerce.service.CommerceOrderItemService;
import com.liferay.commerce.service.CommerceRegionService;
import com.liferay.commerce.service.CommerceShipmentItemService;
import com.liferay.commerce.shipment.web.internal.portlet.action.ActionHelper;
import com.liferay.commerce.shipment.web.internal.util.CommerceShipmentPortletUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceShipmentItemDisplayContext
	extends BaseCommerceShipmentDisplayContext<CommerceShipmentItem> {

	public CommerceShipmentItemDisplayContext(
		ActionHelper actionHelper, HttpServletRequest httpServletRequest,
		CommerceCountryService commerceCountryService,
		CommerceInventoryWarehouseService commerceInventoryWarehouseService,
		CommerceOrderItemService commerceOrderItemService,
		CommerceRegionService commerceRegionService,
		CommerceShipmentItemService commerceShipmentItemService) {

		super(
			actionHelper, httpServletRequest,
			CommerceShipmentItem.class.getSimpleName());

		_commerceCountryService = commerceCountryService;
		_commerceInventoryWarehouseService = commerceInventoryWarehouseService;
		_commerceOrderItemService = commerceOrderItemService;
		_commerceRegionService = commerceRegionService;
		_commerceShipmentItemService = commerceShipmentItemService;
	}

	public String getAddCommerceShipmentItemsURL() throws PortalException {
		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		SearchContainer<CommerceShipmentItem>
			commerceShipmentItemSearchContainer = getSearchContainer();

		List<CommerceShipmentItem> commerceShipmentItems =
			commerceShipmentItemSearchContainer.getResults();

		portletURL.setParameter(
			"redirect", PortalUtil.getCurrentURL(httpServletRequest));

		if (getCommerceShipmentId() > 0) {
			portletURL.setParameter(
				"commerceShipmentId", String.valueOf(getCommerceShipmentId()));
		}

		if (commerceShipmentItems.isEmpty()) {
			portletURL.setParameter(
				"mvcRenderCommandName", "editCommerceShipment");
		}
		else {
			portletURL.setParameter(
				"mvcRenderCommandName", "selectCommerceShipmentItems");

			CommerceShipmentItem commerceShipmentItem =
				commerceShipmentItems.get(0);

			CommerceOrderItem commerceOrderItem =
				_commerceOrderItemService.getCommerceOrderItem(
					commerceShipmentItem.getCommerceOrderItemId());

			portletURL.setParameter(
				"commerceOrderId",
				String.valueOf(commerceOrderItem.getCommerceOrderId()));
		}

		return portletURL.toString();
	}

	public List<CommerceCountry> getCommerceCountries() {
		return _commerceCountryService.getShippingCommerceCountries(
			cpRequestHelper.getCompanyId(), true, true);
	}

	public CommerceInventoryWarehouse getCommerceInventoryWarehouse(
			long commerceInventoryWarehouseId)
		throws PortalException {

		return _commerceInventoryWarehouseService.getCommerceInventoryWarehouse(
			commerceInventoryWarehouseId);
	}

	public List<CommerceRegion> getCommerceRegions() throws PortalException {
		long commerceCountryId = 0;

		CommerceShipment commerceShipment = getCommerceShipment();

		CommerceAddress commerceAddress =
			commerceShipment.fetchCommerceAddress();

		if (commerceAddress != null) {
			commerceCountryId = commerceAddress.getCommerceCountryId();
		}

		return _commerceRegionService.getCommerceRegions(
			commerceCountryId, true);
	}

	public CommerceShipmentItem getCommerceShipmentItem()
		throws PortalException {

		if (_commerceShipmentItem != null) {
			return _commerceShipmentItem;
		}

		_commerceShipmentItem = actionHelper.getCommerceShipmentItem(
			cpRequestHelper.getRenderRequest());

		return _commerceShipmentItem;
	}

	public long getCommerceShipmentItemId() throws PortalException {
		CommerceShipmentItem commerceShipmentItem = getCommerceShipmentItem();

		if (commerceShipmentItem == null) {
			return 0;
		}

		return commerceShipmentItem.getCommerceShipmentItemId();
	}

	@Override
	public PortletURL getPortletURL() throws PortalException {
		PortletURL portletURL = super.getPortletURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "viewCommerceShipmentDetail");

		return portletURL;
	}

	@Override
	public SearchContainer<CommerceShipmentItem> getSearchContainer()
		throws PortalException {

		if (searchContainer != null) {
			return searchContainer;
		}

		searchContainer = new SearchContainer<>(
			liferayPortletRequest, getPortletURL(), null, null);

		OrderByComparator<CommerceShipmentItem> orderByComparator =
			CommerceShipmentPortletUtil.
				getCommerceShipmentItemOrderByComparator(
					getOrderByCol(), getOrderByType());

		searchContainer.setEmptyResultsMessage("no-shipment-items-were-found");
		searchContainer.setOrderByCol(getOrderByCol());
		searchContainer.setOrderByComparator(orderByComparator);
		searchContainer.setOrderByType(getOrderByType());

		int total = _commerceShipmentItemService.getCommerceShipmentItemsCount(
			getCommerceShipmentId());

		searchContainer.setTotal(total);

		List<CommerceShipmentItem> results =
			_commerceShipmentItemService.getCommerceShipmentItems(
				getCommerceShipmentId(), searchContainer.getStart(),
				searchContainer.getEnd(), orderByComparator);

		searchContainer.setResults(results);

		return searchContainer;
	}

	private final CommerceCountryService _commerceCountryService;
	private final CommerceInventoryWarehouseService
		_commerceInventoryWarehouseService;
	private final CommerceOrderItemService _commerceOrderItemService;
	private final CommerceRegionService _commerceRegionService;
	private CommerceShipmentItem _commerceShipmentItem;
	private final CommerceShipmentItemService _commerceShipmentItemService;

}