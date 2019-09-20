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

import com.liferay.commerce.account.configuration.CommerceAccountGroupServiceConfiguration;
import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.model.CommerceAccountModel;
import com.liferay.commerce.account.service.CommerceAccountLocalService;
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
import com.liferay.commerce.order.content.web.internal.frontend.util.CommerceOrderClayTableUtil;
import com.liferay.commerce.order.content.web.internal.model.Order;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"commerce.data.provider.key=" + CommercePlacedOrderClayTable.NAME,
		"commerce.table.name=" + CommercePlacedOrderClayTable.NAME
	},
	service = {
		ClayTable.class, ClayTableActionProvider.class,
		CommerceDataSetDataProvider.class
	}
)
public class CommercePlacedOrderClayTable
	implements ClayTable, ClayTableActionProvider,
			   CommerceDataSetDataProvider<Order> {

	public static final String NAME = "commercePlacedOrders";

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

			String viewURL = CommerceOrderClayTableUtil.getOrderViewDetailURL(
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

		CommerceChannel commerceChannel =
			_commerceChannelLocalService.fetchCommerceChannelBySiteGroupId(
				themeDisplay.getScopeGroupId());

		if (commerceChannel == null) {
			return 0;
		}

		long[] commerceAccountIds = getUserCommerceAccountIds(
			themeDisplay.getUserId(), themeDisplay.getScopeGroupId());

		return (int)_commerceOrderService.getPlacedCommerceOrdersCount(
			commerceChannel.getCompanyId(), commerceChannel.getGroupId(),
			commerceAccountIds);
	}

	@Override
	public ClayTableSchema getClayTableSchema() {
		ClayTableSchemaBuilder clayTableSchemaBuilder =
			_clayTableSchemaBuilderFactory.clayTableSchemaBuilder();

		clayTableSchemaBuilder.addField("title", "order-id");

		clayTableSchemaBuilder.addField("createDate", "create-date");

		clayTableSchemaBuilder.addField("accountName", "account");

		clayTableSchemaBuilder.addField("author", "author");

		clayTableSchemaBuilder.addField("orderStatus", "status");

		clayTableSchemaBuilder.addField("amount", "amount");

		return clayTableSchemaBuilder.build();
	}

	@Override
	public String getElementClasses() {
		return "table-nowrap";
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

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		CommerceChannel commerceChannel =
			_commerceChannelLocalService.fetchCommerceChannelBySiteGroupId(
				themeDisplay.getScopeGroupId());

		if (commerceChannel == null) {
			return Collections.emptyList();
		}

		long[] commerceAccountIds = getUserCommerceAccountIds(
			themeDisplay.getUserId(), themeDisplay.getScopeGroupId());

		List<CommerceOrder> commerceOrders =
			_commerceOrderService.getPlacedCommerceOrders(
				commerceChannel.getCompanyId(), commerceChannel.getGroupId(),
				commerceAccountIds, pagination.getStartPosition(),
				pagination.getEndPosition());

		return CommerceOrderClayTableUtil.getOrders(
			commerceOrders, themeDisplay, false);
	}

	@Override
	public boolean isShowActionsMenu() {
		return true;
	}

	protected int getCommerceSiteType(long groupId)
		throws ConfigurationException {

		CommerceAccountGroupServiceConfiguration
			commerceAccountGroupServiceConfiguration =
				_configurationProvider.getConfiguration(
					CommerceAccountGroupServiceConfiguration.class,
					new GroupServiceSettingsLocator(
						groupId, CommerceAccountConstants.SERVICE_NAME));

		return commerceAccountGroupServiceConfiguration.commerceSiteType();
	}

	protected long[] getUserCommerceAccountIds(long userId, long groupId)
		throws PortalException {

		List<CommerceAccount> commerceAccounts =
			_commerceAccountLocalService.getUserCommerceAccounts(
				userId, CommerceAccountConstants.DEFAULT_PARENT_ACCOUNT_ID,
				getCommerceSiteType(groupId), StringPool.BLANK,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		long[] commerceAccountIds = new long[0];

		if (!commerceAccounts.isEmpty()) {
			commerceAccountIds = ListUtil.toLongArray(
				commerceAccounts, CommerceAccountModel::getCommerceAccountId);
		}

		return commerceAccountIds;
	}

	@Reference
	private ClayTableSchemaBuilderFactory _clayTableSchemaBuilderFactory;

	@Reference
	private CommerceAccountLocalService _commerceAccountLocalService;

	@Reference
	private CommerceChannelLocalService _commerceChannelLocalService;

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.model.CommerceOrder)"
	)
	private ModelResourcePermission<CommerceOrder> _modelResourcePermission;

}