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

package com.liferay.commerce.order.content.util;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderNote;
import com.liferay.commerce.model.CommerceShipmentItem;
import com.liferay.commerce.order.content.render.CommerceOrderDetailRenderer;
import com.liferay.commerce.price.CommerceOrderPrice;
import com.liferay.portal.kernel.exception.PortalException;

import java.math.BigDecimal;

import java.util.List;

import javax.portlet.RenderRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Alessio Antonio Rendina
 */
@ProviderType
public interface CommerceOrderDetailHelper {

	public List<CommerceAddress> getCommerceAddresses(
			long commerceAccountId, HttpServletRequest httpServletRequest)
		throws PortalException;

	public CommerceOrder getCommerceOrder(
		HttpServletRequest httpServletRequest);

	public String getCommerceOrderDate(
		CommerceOrder commerceOrder, HttpServletRequest httpServletRequest);

	public String getCommerceOrderDetailRendererKey(
		String prefix, RenderRequest renderRequest);

	public List<CommerceOrderDetailRenderer> getCommerceOrderDetailRenderers(
		String orderDetailStatus);

	public CommerceOrderNote getCommerceOrderNote(
			HttpServletRequest httpServletRequest)
		throws PortalException;

	public List<CommerceOrderNote> getCommerceOrderNotes(
			long commerceOrderId, HttpServletRequest httpServletRequest)
		throws PortalException;

	public int getCommerceOrderNotesCount(
			long commerceOrderId, HttpServletRequest httpServletRequest)
		throws PortalException;

	public CommerceOrderPrice getCommerceOrderPrice(
			HttpServletRequest httpServletRequest)
		throws PortalException;

	public String getCommerceOrderTime(
		CommerceOrder commerceOrder, HttpServletRequest httpServletRequest);

	public List<CommerceShipmentItem> getCommerceShipmentItems(
			long commerceOrderItemId)
		throws PortalException;

	public String getCommerceShipmentStatusLabel(
		int status, HttpServletRequest httpServletRequest);

	public String getFormattedPercentage(
			BigDecimal percentage, HttpServletRequest httpServletRequest)
		throws PortalException;

	public String getOrderDetailURL(
			long commerceOrderId, HttpServletRequest httpServletRequest)
		throws PortalException;

	public boolean hasPermission(
			long commerceOrderId, String actionId,
			HttpServletRequest httpServletRequest)
		throws PortalException;

	public void renderCommerceOrderDetail(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception;

}