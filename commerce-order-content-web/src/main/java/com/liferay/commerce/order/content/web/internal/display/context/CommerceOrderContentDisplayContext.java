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

package com.liferay.commerce.order.content.web.internal.display.context;

import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.constants.CommercePortletKeys;
import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.order.content.web.internal.frontend.OrderFilterImpl;
import com.liferay.commerce.order.content.web.internal.portlet.configuration.CommerceOrderContentPortletInstanceConfiguration;
import com.liferay.commerce.product.display.context.util.CPRequestHelper;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceOrderContentDisplayContext {

	public CommerceOrderContentDisplayContext(
			CommerceOrderService commerceOrderService,
			HttpServletRequest httpServletRequest,
			PortletResourcePermission portletResourcePermission)
		throws PortalException {

		_commerceOrderService = commerceOrderService;
		_httpServletRequest = httpServletRequest;
		_portletResourcePermission = portletResourcePermission;

		_cpRequestHelper = new CPRequestHelper(httpServletRequest);

		PortletDisplay portletDisplay = _cpRequestHelper.getPortletDisplay();

		_commerceOrderContentPortletInstanceConfiguration =
			portletDisplay.getPortletInstanceConfiguration(
				CommerceOrderContentPortletInstanceConfiguration.class);

		_commerceContext = (CommerceContext)_httpServletRequest.getAttribute(
			CommerceWebKeys.COMMERCE_CONTEXT);

		_commerceAccount = _commerceContext.getCommerceAccount();
	}

	public long getCommerceAccountId() {
		long commerceAccountId = 0;

		if (_commerceAccount != null) {
			commerceAccountId = _commerceAccount.getCommerceAccountId();
		}

		return commerceAccountId;
	}

	public CommerceOrder getCommerceOrder() throws PortalException {
		return _commerceOrderService.fetchCommerceOrder(getCommerceOrderId());
	}

	public long getCommerceOrderId() {
		return ParamUtil.getLong(_httpServletRequest, "commerceOrderId");
	}

	public List<CommerceOrder> getCommerceOrders() throws PortalException {
		if (_commerceOrders != null) {
			return _commerceOrders;
		}

		String keywords = ParamUtil.getString(_httpServletRequest, "keywords");

		if (isOpenOrderContentPortlet()) {
			_commerceOrders = _commerceOrderService.getPendingCommerceOrders(
				_cpRequestHelper.getScopeGroupId(), getCommerceAccountId(),
				keywords, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		}
		else {
			_commerceOrders = _commerceOrderService.getPlacedCommerceOrders(
				_cpRequestHelper.getScopeGroupId(), getCommerceAccountId(),
				keywords, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		}

		return _commerceOrders;
	}

	public String getDisplayStyle() {
		return _commerceOrderContentPortletInstanceConfiguration.displayStyle();
	}

	public long getDisplayStyleGroupId() {
		if (_displayStyleGroupId > 0) {
			return _displayStyleGroupId;
		}

		_displayStyleGroupId =
			_commerceOrderContentPortletInstanceConfiguration.
				displayStyleGroupId();

		if (_displayStyleGroupId <= 0) {
			_displayStyleGroupId = _cpRequestHelper.getScopeGroupId();
		}

		return _displayStyleGroupId;
	}

	public OrderFilterImpl getOrderFilter() {
		OrderFilterImpl orderFilter = new OrderFilterImpl();

		if (_commerceAccount != null) {
			orderFilter.setAccountId(_commerceAccount.getCommerceAccountId());
		}

		return orderFilter;
	}

	public PortletURL getPortletURL() {
		LiferayPortletResponse liferayPortletResponse =
			_cpRequestHelper.getLiferayPortletResponse();

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		String delta = ParamUtil.getString(_httpServletRequest, "delta");

		if (Validator.isNotNull(delta)) {
			portletURL.setParameter("delta", delta);
		}

		String deltaEntry = ParamUtil.getString(
			_httpServletRequest, "deltaEntry");

		if (Validator.isNotNull(deltaEntry)) {
			portletURL.setParameter("deltaEntry", deltaEntry);
		}

		return portletURL;
	}

	public boolean hasPermission(String actionId) {
		return _portletResourcePermission.contains(
			_cpRequestHelper.getPermissionChecker(),
			_cpRequestHelper.getScopeGroupId(), actionId);
	}

	public boolean isCommerceSiteTypeB2C() {
		if (_commerceContext.getCommerceSiteType() ==
				CommerceAccountConstants.SITE_TYPE_B2C) {

			return true;
		}

		return false;
	}

	public boolean isOpenOrderContentPortlet() {
		String portletName = _cpRequestHelper.getPortletName();

		return portletName.equals(
			CommercePortletKeys.COMMERCE_OPEN_ORDER_CONTENT);
	}

	private final CommerceAccount _commerceAccount;
	private final CommerceContext _commerceContext;
	private final CommerceOrderContentPortletInstanceConfiguration
		_commerceOrderContentPortletInstanceConfiguration;
	private List<CommerceOrder> _commerceOrders;
	private final CommerceOrderService _commerceOrderService;
	private final CPRequestHelper _cpRequestHelper;
	private long _displayStyleGroupId;
	private final HttpServletRequest _httpServletRequest;
	private final PortletResourcePermission _portletResourcePermission;

}