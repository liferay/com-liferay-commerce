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

package com.liferay.commerce.order.content.web.internal.frontend;

import com.liferay.commerce.currency.model.CommerceMoney;
import com.liferay.commerce.frontend.ClayTable;
import com.liferay.commerce.frontend.ClayTableAction;
import com.liferay.commerce.frontend.ClayTableActionProvider;
import com.liferay.commerce.frontend.ClayTableSchema;
import com.liferay.commerce.frontend.ClayTableSchemaBuilder;
import com.liferay.commerce.frontend.ClayTableSchemaBuilderFactory;
import com.liferay.commerce.frontend.CommerceDataSetDataProvider;
import com.liferay.commerce.frontend.Filter;
import com.liferay.commerce.frontend.Pagination;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.order.content.web.internal.model.Order;
import com.liferay.commerce.order.content.web.internal.portlet.configuration.CommerceOrderContentPortletInstanceConfiguration;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.portlet.PortletQName;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.text.DateFormat;
import java.text.Format;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"commerce.data.provider.key=" + CommerceOrderClayTable.NAME,
		"commerce.table.name=" + CommerceOrderClayTable.NAME
	},
	service = {
		ClayTable.class, ClayTableActionProvider.class,
		CommerceDataSetDataProvider.class
	}
)
public class CommerceOrderClayTable
	implements CommerceDataSetDataProvider<Order>, ClayTable,
			   ClayTableActionProvider {

	public static final String NAME = "commerceOrders";

	@Override
	public List<ClayTableAction> clayTableActions(
			HttpServletRequest httpServletRequest, long groupId, Object model)
		throws PortalException {

		List<ClayTableAction> clayTableActions = new ArrayList<>();

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		Order order = (Order)model;

		if (_modelResourcePermission.contains(
				themeDisplay.getPermissionChecker(), order.getOrderId(),
				ActionKeys.VIEW)) {

			String viewURL = _getOrderViewDetailURL(
				order.getOrderId(), themeDisplay);

			ClayTableAction clayTableAction = new ClayTableAction(
				viewURL, StringPool.BLANK,
				LanguageUtil.get(httpServletRequest, "view"), false, false);

			clayTableActions.add(clayTableAction);
		}

		return clayTableActions;
	}

	@Override
	public int countItems(HttpServletRequest httpServletRequest, Filter filter)
		throws PortalException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		CommerceOrderContentPortletInstanceConfiguration
			commerceOrderContentPortletInstanceConfiguration =
				_configurationProvider.getPortletInstanceConfiguration(
					CommerceOrderContentPortletInstanceConfiguration.class,
					themeDisplay.getLayout(), portletDisplay.getId());

		if (commerceOrderContentPortletInstanceConfiguration.exclude()) {
			return _commerceOrderService.getUserCommerceOrdersCount(
				themeDisplay.getScopeGroupId(), themeDisplay.getUserId(),
				commerceOrderContentPortletInstanceConfiguration.orderStatus(),
				commerceOrderContentPortletInstanceConfiguration.exclude(),
				filter.getKeywords());
		}

		return _commerceOrderService.getUserCommerceOrdersCount(
			themeDisplay.getScopeGroupId(), themeDisplay.getUserId(),
			commerceOrderContentPortletInstanceConfiguration.orderStatus(),
			filter.getKeywords());
	}

	@Override
	public ClayTableSchema getClayTableSchema() {
		ClayTableSchemaBuilder clayTableSchemaBuilder =
			_clayTableSchemaBuilderFactory.clayTableSchemaBuilder();

		clayTableSchemaBuilder.addField("createDate", "create-date");
		clayTableSchemaBuilder.addField("orderId", "order-id");
		clayTableSchemaBuilder.addField("accountName", "account");
		clayTableSchemaBuilder.addField("author", "author");
		clayTableSchemaBuilder.addField("status", "status");
		clayTableSchemaBuilder.addField("amount", "amount");

		return clayTableSchemaBuilder.build();
	}

	@Override
	public String getId() {
		return NAME;
	}

	@Override
	public List<Order> getItems(
			HttpServletRequest httpServletRequest, Filter filter,
			Pagination pagination, Sort sort)
		throws PortalException {

		List<Order> orders = new ArrayList<>();

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		CommerceOrderContentPortletInstanceConfiguration
			commerceOrderContentPortletInstanceConfiguration =
				_configurationProvider.getPortletInstanceConfiguration(
					CommerceOrderContentPortletInstanceConfiguration.class,
					themeDisplay.getLayout(), portletDisplay.getId());

		List<CommerceOrder> commerceOrders = null;

		if (commerceOrderContentPortletInstanceConfiguration.exclude()) {
			commerceOrders = _commerceOrderService.getUserCommerceOrders(
				themeDisplay.getScopeGroupId(), themeDisplay.getUserId(),
				commerceOrderContentPortletInstanceConfiguration.orderStatus(),
				commerceOrderContentPortletInstanceConfiguration.exclude(),
				filter.getKeywords(), pagination.getStartPosition(),
				pagination.getEndPosition());
		}
		else {
			commerceOrders = _commerceOrderService.getUserCommerceOrders(
				themeDisplay.getScopeGroupId(), themeDisplay.getUserId(),
				commerceOrderContentPortletInstanceConfiguration.orderStatus(),
				filter.getKeywords(), pagination.getStartPosition(),
				pagination.getEndPosition());
		}

		for (CommerceOrder commerceOrder : commerceOrders) {
			String amount = StringPool.BLANK;

			CommerceMoney totalMoney = commerceOrder.getTotalMoney();

			if (totalMoney != null) {
				amount = totalMoney.format(themeDisplay.getLocale());
			}

			Format dateFormat = FastDateFormatFactoryUtil.getDate(
				DateFormat.MEDIUM, themeDisplay.getLocale(),
				themeDisplay.getTimeZone());

			orders.add(
				new Order(
					commerceOrder.getCommerceOrderId(),
					commerceOrder.getCommerceAccountName(),
					dateFormat.format(commerceOrder.getCreateDate()),
					commerceOrder.getUserName(),
					WorkflowConstants.getStatusLabel(commerceOrder.getStatus()),
					amount,
					_getOrderViewDetailURL(
						commerceOrder.getCommerceOrderId(), themeDisplay)));
		}

		return orders;
	}

	@Override
	public boolean isShowActionsMenu() {
		return true;
	}

	private String _getOrderViewDetailURL(
			long commerceOrderId, ThemeDisplay themeDisplay)
		throws PortalException {

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		PortletURL portletURL = PortletURLFactoryUtil.create(
			themeDisplay.getRequest(), portletDisplay.getId(),
			themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);

		portletURL.setParameter("mvcRenderCommandName", "editCommerceOrder");
		portletURL.setParameter(
			"commerceOrderId", String.valueOf(commerceOrderId));

		portletURL.setParameter(
			PortletQName.PUBLIC_RENDER_PARAMETER_NAMESPACE + "backURL",
			_portal.getCurrentURL(themeDisplay.getRequest()));

		return portletURL.toString();
	}

	@Reference
	private ClayTableSchemaBuilderFactory _clayTableSchemaBuilderFactory;

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.model.CommerceOrder)"
	)
	private ModelResourcePermission<CommerceOrder> _modelResourcePermission;

	@Reference
	private Portal _portal;

}