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

package com.liferay.commerce.order.web.internal.frontend;

import com.liferay.commerce.constants.CommercePortletKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.context.CommerceContextFactory;
import com.liferay.commerce.currency.model.CommerceMoney;
import com.liferay.commerce.discount.CommerceDiscountValue;
import com.liferay.commerce.frontend.ClayTable;
import com.liferay.commerce.frontend.ClayTableAction;
import com.liferay.commerce.frontend.ClayTableActionProvider;
import com.liferay.commerce.frontend.ClayTableSchema;
import com.liferay.commerce.frontend.ClayTableSchemaBuilder;
import com.liferay.commerce.frontend.ClayTableSchemaBuilderFactory;
import com.liferay.commerce.frontend.ClayTableSchemaField;
import com.liferay.commerce.frontend.CommerceDataSetDataProvider;
import com.liferay.commerce.frontend.Filter;
import com.liferay.commerce.frontend.Pagination;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.model.CommerceSubscriptionEntry;
import com.liferay.commerce.order.web.internal.model.OrderItem;
import com.liferay.commerce.order.web.internal.security.permission.resource.CommerceOrderPermission;
import com.liferay.commerce.price.CommerceProductPrice;
import com.liferay.commerce.price.CommerceProductPriceCalculation;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CPSubscriptionInfo;
import com.liferay.commerce.product.util.CPSubscriptionType;
import com.liferay.commerce.product.util.CPSubscriptionTypeRegistry;
import com.liferay.commerce.service.CommerceOrderItemService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.commerce.service.CommerceSubscriptionEntryLocalService;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.portlet.ActionRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderResponse;
import javax.portlet.WindowStateException;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"commerce.data.provider.key=" + CommerceOrderItemClayTable.NAME,
		"commerce.table.name=" + CommerceOrderItemClayTable.NAME
	},
	service = {
		ClayTable.class, ClayTableActionProvider.class,
		CommerceDataSetDataProvider.class
	}
)
public class CommerceOrderItemClayTable
	implements ClayTable, ClayTableActionProvider,
			   CommerceDataSetDataProvider<OrderItem> {

	public static final String NAME = "commerceOrderItems";

	@Override
	public List<ClayTableAction> clayTableActions(
			HttpServletRequest httpServletRequest, long groupId, Object model)
		throws PortalException {

		List<ClayTableAction> clayTableActions = new ArrayList<>();

		OrderItem orderItem = (OrderItem)model;

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		if (CommerceOrderPermission.contains(
				themeDisplay.getPermissionChecker(), orderItem.getOrderId(),
				ActionKeys.UPDATE)) {

			PortletURL deleteURL = _getOrderItemDeleteURL(
				orderItem.getOrderItemId(), httpServletRequest);

			ClayTableAction deleteClayTableAction = new ClayTableAction(
				deleteURL.toString(), StringPool.BLANK,
				LanguageUtil.get(httpServletRequest, "delete"), false, false);

			clayTableActions.add(deleteClayTableAction);
		}

		return clayTableActions;
	}

	@Override
	public int countItems(HttpServletRequest httpServletRequest, Filter filter)
		throws PortalException {

		BaseModelSearchResult<CommerceOrderItem> baseModelSearchResult =
			_getBaseModelSearchResult(httpServletRequest, filter, null);

		return baseModelSearchResult.getLength();
	}

	@Override
	public ClayTableSchema getClayTableSchema() {
		ClayTableSchemaBuilder clayTableSchemaBuilder =
			_clayTableSchemaBuilderFactory.clayTableSchemaBuilder();

		ClayTableSchemaField skuField = clayTableSchemaBuilder.addField(
			"sku", "sku");

		skuField.setContentRenderer("commerceTableCellSidePanelLink");

		clayTableSchemaBuilder.addField("name", "name");

		ClayTableSchemaField priceField = clayTableSchemaBuilder.addField(
			"price", "price");

		priceField.setContentRenderer("commerceTableCellSubscription");

		clayTableSchemaBuilder.addField("discount", "discount");

		clayTableSchemaBuilder.addField("quantity", "quantity");

		clayTableSchemaBuilder.addField("total", "total");

		return clayTableSchemaBuilder.build();
	}

	@Override
	public String getId() {
		return NAME;
	}

	@Override
	public List<OrderItem> getItems(
			HttpServletRequest httpServletRequest, Filter filter,
			Pagination pagination, Sort sort)
		throws PortalException {

		List<OrderItem> orderItems = new ArrayList<>();

		long companyId = _portal.getCompanyId(httpServletRequest);

		long controlPanelPlid = _portal.getControlPanelPlid(companyId);

		Locale locale = _portal.getLocale(httpServletRequest);

		CommerceContext commerceContext = _commerceContextFactory.create(
			companyId, _portal.getScopeGroupId(controlPanelPlid),
			_portal.getUserId(httpServletRequest), 0, 0);

		RenderResponse renderResponse =
			(RenderResponse)httpServletRequest.getAttribute(
				JavaConstants.JAVAX_PORTLET_RESPONSE);

		BaseModelSearchResult<CommerceOrderItem> baseModelSearchResult =
			_getBaseModelSearchResult(httpServletRequest, filter, pagination);

		for (CommerceOrderItem commerceOrderItem :
				baseModelSearchResult.getBaseModels()) {

			String price = StringPool.BLANK;
			String total = StringPool.BLANK;
			String discount = StringPool.BLANK;

			CommerceProductPrice commerceProductPrice =
				_commerceProductPriceCalculation.getCommerceProductPrice(
					commerceOrderItem.getCPInstanceId(),
					commerceOrderItem.getQuantity(), commerceContext);

			if (commerceProductPrice != null) {
				CommerceMoney unitPrice = commerceProductPrice.getUnitPrice();
				CommerceMoney finalPrice = commerceProductPrice.getFinalPrice();

				price = HtmlUtil.escape(unitPrice.format(locale));
				total = HtmlUtil.escape(finalPrice.format(locale));

				CommerceDiscountValue discountValue =
					commerceProductPrice.getDiscountValue();

				if (discountValue != null) {
					CommerceMoney discountAmount =
						discountValue.getDiscountAmount();

					discount = HtmlUtil.escape(discountAmount.format(locale));
				}
			}

			orderItems.add(
				new OrderItem(
					commerceOrderItem.getCommerceOrderItemId(),
					commerceOrderItem.getCommerceOrderId(),
					commerceOrderItem.getSku(),
					_getOrderItemPanelURL(
						commerceOrderItem.getCommerceOrderItemId(),
						httpServletRequest),
					renderResponse.getNamespace() + "sidePanel",
					commerceOrderItem.getName(locale), price,
					_getSubscriptionDuration(
						commerceOrderItem, locale, httpServletRequest),
					_getSubscriptionPeriod(
						commerceOrderItem, locale, httpServletRequest),
					discount, commerceOrderItem.getQuantity(), total));
		}

		return orderItems;
	}

	@Override
	public boolean isShowActionsMenu() {
		return true;
	}

	private BaseModelSearchResult<CommerceOrderItem> _getBaseModelSearchResult(
			HttpServletRequest httpServletRequest, Filter filter,
			Pagination pagination)
		throws PortalException {

		BaseModelSearchResult<CommerceOrderItem> baseModelSearchResult = null;

		long commerceOrderId = ParamUtil.getLong(
			httpServletRequest, "commerceOrderId");

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		int start = QueryUtil.ALL_POS;
		int end = QueryUtil.ALL_POS;

		if (pagination != null) {
			start = pagination.getStartPosition();
			end = pagination.getEndPosition();
		}

		_setSortPreferences(httpServletRequest);

		Sort sort = SortFactoryUtil.getSort(
			CommerceOrderItem.class, _orderByCol, _orderByType);

		OrderItemFilterImpl orderItemFilter = (OrderItemFilterImpl)filter;

		if (orderItemFilter.isAdvancedSearch()) {
			baseModelSearchResult = _commerceOrderItemService.search(
				commerceOrder.getCommerceOrderId(), orderItemFilter.getSku(),
				orderItemFilter.getName(), orderItemFilter.isAndOperator(),
				start, end, sort);
		}
		else {
			baseModelSearchResult = _commerceOrderItemService.search(
				commerceOrder.getCommerceOrderId(),
				orderItemFilter.getKeywords(), start, end, sort);
		}

		return baseModelSearchResult;
	}

	private PortletURL _getOrderItemDeleteURL(
			long commerceOrderItemId, HttpServletRequest httpServletRequest)
		throws PortalException {

		PortletURL portletURL = PortletProviderUtil.getPortletURL(
			httpServletRequest, CommerceOrder.class.getName(),
			PortletProvider.Action.MANAGE);

		portletURL.setParameter(
			ActionRequest.ACTION_NAME, "editCommerceOrderItem");
		portletURL.setParameter(Constants.CMD, Constants.DELETE);
		portletURL.setParameter(
			"redirect", _portal.getCurrentURL(httpServletRequest));
		portletURL.setParameter(
			"commerceOrderItemId", String.valueOf(commerceOrderItemId));

		return portletURL;
	}

	private String _getOrderItemPanelURL(
			long commerceOrderItemId, HttpServletRequest httpServletRequest)
		throws PortalException {

		PortletURL portletURL = PortletProviderUtil.getPortletURL(
			httpServletRequest, CommerceOrder.class.getName(),
			PortletProvider.Action.MANAGE);

		portletURL.setParameter(
			"mvcRenderCommandName", "editCommerceOrderItem");
		portletURL.setParameter(
			"redirect", _portal.getCurrentURL(httpServletRequest));

		long commerceOrderId = ParamUtil.getLong(
			httpServletRequest, "commerceOrderId");

		portletURL.setParameter(
			"commerceOrderId", String.valueOf(commerceOrderId));

		portletURL.setParameter(
			"commerceOrderItemId", String.valueOf(commerceOrderItemId));

		try {
			portletURL.setWindowState(LiferayWindowState.POP_UP);
		}
		catch (WindowStateException wse) {
			_log.error(wse, wse);
		}

		return portletURL.toString();
	}

	private String _getPeriodKey(
		String period, boolean plural, HttpServletRequest httpServletRequest) {

		if (plural) {
			return LanguageUtil.get(
				httpServletRequest,
				StringUtil.toLowerCase(period) + CharPool.LOWER_CASE_S);
		}

		return period;
	}

	private String _getSubscriptionDuration(
			CommerceOrderItem commerceOrderItem, Locale locale,
			HttpServletRequest httpServletRequest)
		throws PortalException {

		String subscriptionDuration = StringPool.BLANK;

		if (commerceOrderItem.isSubscription()) {
			CommerceOrder commerceOrder = commerceOrderItem.getCommerceOrder();

			if (commerceOrder.isOpen()) {
				String period = StringPool.BLANK;

				CPInstance cpInstance = commerceOrderItem.getCPInstance();

				CPSubscriptionInfo cpSubscriptionInfo =
					cpInstance.getCPSubscriptionInfo();

				if (cpSubscriptionInfo == null) {
					return subscriptionDuration;
				}

				CPSubscriptionType cpSubscriptionType =
					_cpSubscriptionTypeRegistry.getCPSubscriptionType(
						cpSubscriptionInfo.getSubscriptionType());

				if (cpSubscriptionType != null) {
					period = cpSubscriptionType.getLabel(locale);
				}

				long duration = cpSubscriptionInfo.getMaxSubscriptionCycles();

				if (duration > 0) {
					subscriptionDuration = LanguageUtil.format(
						httpServletRequest, "duration-x-x",
						new Object[] {
							duration,
							_getPeriodKey(
								period, duration != 1, httpServletRequest)
						});
				}
			}
			else {
				String period = StringPool.BLANK;

				CommerceSubscriptionEntry commerceSubscriptionEntry =
					_commerceSubscriptionEntryLocalService.
						fetchCommerceSubscriptionEntry(
							commerceOrderItem.getCommerceOrderItemId());

				if (commerceSubscriptionEntry == null) {
					return subscriptionDuration;
				}

				long duration =
					commerceSubscriptionEntry.getMaxSubscriptionCycles();

				subscriptionDuration = LanguageUtil.format(
					httpServletRequest, "duration-x-x",
					new Object[] {
						duration,
						_getPeriodKey(period, duration != 1, httpServletRequest)
					});
			}
		}

		return subscriptionDuration;
	}

	private String _getSubscriptionPeriod(
			CommerceOrderItem commerceOrderItem, Locale locale,
			HttpServletRequest httpServletRequest)
		throws PortalException {

		String subscriptionPeriod = StringPool.BLANK;

		if (commerceOrderItem.isSubscription()) {
			CommerceOrder commerceOrder = commerceOrderItem.getCommerceOrder();

			if (commerceOrder.isOpen()) {
				String period = StringPool.BLANK;

				CPInstance cpInstance = commerceOrderItem.getCPInstance();

				CPSubscriptionInfo cpSubscriptionInfo =
					cpInstance.getCPSubscriptionInfo();

				if (cpSubscriptionInfo == null) {
					return subscriptionPeriod;
				}

				CPSubscriptionType cpSubscriptionType =
					_cpSubscriptionTypeRegistry.getCPSubscriptionType(
						cpSubscriptionInfo.getSubscriptionType());

				if (cpSubscriptionType != null) {
					period = cpSubscriptionType.getLabel(locale);
				}

				int subscriptionLength =
					cpSubscriptionInfo.getSubscriptionLength();

				subscriptionPeriod = LanguageUtil.format(
					httpServletRequest, "every-x-x",
					new Object[] {
						subscriptionLength,
						_getPeriodKey(
							period, subscriptionLength != 1, httpServletRequest)
					});
			}
			else {
				String period = StringPool.BLANK;

				CommerceSubscriptionEntry commerceSubscriptionEntry =
					_commerceSubscriptionEntryLocalService.
						fetchCommerceSubscriptionEntry(
							commerceOrderItem.getCommerceOrderItemId());

				if (commerceSubscriptionEntry == null) {
					return subscriptionPeriod;
				}

				int subscriptionLength =
					commerceSubscriptionEntry.getSubscriptionLength();

				subscriptionPeriod = LanguageUtil.format(
					httpServletRequest, "every-x-x",
					new Object[] {
						subscriptionLength,
						_getPeriodKey(
							period, subscriptionLength != 1, httpServletRequest)
					});
			}
		}

		return subscriptionPeriod;
	}

	private void _setOrderByCol(String orderByCol) {
		_orderByCol = orderByCol;
	}

	private void _setOrderByType(String orderByType) {
		_orderByType = orderByType;
	}

	private void _setSortPreferences(HttpServletRequest httpServletRequest) {
		PortalPreferences preferences =
			PortletPreferencesFactoryUtil.getPortalPreferences(
				httpServletRequest);

		String orderByCol = ParamUtil.getString(
			httpServletRequest, "orderByCol");
		String orderByType = ParamUtil.getString(
			httpServletRequest, "orderByType");

		if (Validator.isNotNull(orderByCol) &&
			Validator.isNotNull(orderByType)) {

			preferences.setValue(
				CommercePortletKeys.COMMERCE_ORDER,
				"commerce-order-items-order-by-col", orderByCol);
			preferences.setValue(
				CommercePortletKeys.COMMERCE_ORDER,
				"commerce-order-items-order-by-type", orderByType);
		}
		else {
			orderByCol = preferences.getValue(
				CommercePortletKeys.COMMERCE_ORDER,
				"commerce-order-items-order-by-col", "sku");
			orderByType = preferences.getValue(
				CommercePortletKeys.COMMERCE_ORDER,
				"commerce-order-items-order-by-type", "asc");
		}

		_setOrderByCol(orderByCol);
		_setOrderByType(orderByType);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceOrderItemClayTable.class);

	@Reference
	private ClayTableSchemaBuilderFactory _clayTableSchemaBuilderFactory;

	@Reference
	private CommerceContextFactory _commerceContextFactory;

	@Reference
	private CommerceOrderItemService _commerceOrderItemService;

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference
	private CommerceProductPriceCalculation _commerceProductPriceCalculation;

	@Reference
	private CommerceSubscriptionEntryLocalService
		_commerceSubscriptionEntryLocalService;

	@Reference
	private CPSubscriptionTypeRegistry _cpSubscriptionTypeRegistry;

	private String _orderByCol;
	private String _orderByType;

	@Reference
	private Portal _portal;

}