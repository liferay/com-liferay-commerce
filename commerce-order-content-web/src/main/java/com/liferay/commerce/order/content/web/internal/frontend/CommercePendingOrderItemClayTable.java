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
import com.liferay.commerce.frontend.ClayTableSchemaField;
import com.liferay.commerce.frontend.CommerceDataSetDataProvider;
import com.liferay.commerce.frontend.Filter;
import com.liferay.commerce.frontend.Pagination;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.order.CommerceOrderValidatorRegistry;
import com.liferay.commerce.order.CommerceOrderValidatorResult;
import com.liferay.commerce.order.content.web.internal.frontend.util.CommerceOrderClayTableUtil;
import com.liferay.commerce.order.content.web.internal.model.OrderItem;
import com.liferay.commerce.price.CommerceProductPriceCalculation;
import com.liferay.commerce.product.util.CPInstanceHelper;
import com.liferay.commerce.service.CommerceOrderItemService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.WebKeys;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
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
		"commerce.data.provider.key=" + CommercePendingOrderItemClayTable.NAME,
		"commerce.table.name=" + CommercePendingOrderItemClayTable.NAME
	},
	service = {
		ClayTable.class, ClayTableActionProvider.class,
		CommerceDataSetDataProvider.class
	}
)
public class CommercePendingOrderItemClayTable
	implements ClayTable, ClayTableActionProvider,
			   CommerceDataSetDataProvider<OrderItem> {

	public static final String NAME = "commercePendingOrderItems";

	@Override
	public List<ClayTableAction> clayTableActions(
			HttpServletRequest httpServletRequest, long groupId, Object model)
		throws PortalException {

		List<ClayTableAction> clayTableActions = new ArrayList<>();

		OrderItem orderItem = (OrderItem)model;

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			orderItem.getOrderId());

		if (_modelResourcePermission.contains(
				themeDisplay.getPermissionChecker(), commerceOrder,
				ActionKeys.UPDATE) &&
			commerceOrder.isOpen()) {

			ClayTableAction clayTableAction = new ClayTableAction(
				StringPool.BLANK,
				_getDeleteCommerceOrderItemURL(
					orderItem.getOrderItemId(), themeDisplay),
				StringPool.BLANK,
				LanguageUtil.get(httpServletRequest, "delete"), null, false,
				false);

			clayTableActions.add(clayTableAction);
		}

		return clayTableActions;
	}

	@Override
	public int countItems(HttpServletRequest httpServletRequest, Filter filter)
		throws PortalException {

		OrderFilterImpl orderFilterImpl = (OrderFilterImpl)filter;

		return _commerceOrderItemService.getCommerceOrderItemsCount(
			orderFilterImpl.getCommerceOrderId());
	}

	@Override
	public ClayTableSchema getClayTableSchema() {
		ClayTableSchemaBuilder clayTableSchemaBuilder =
			_clayTableSchemaBuilderFactory.clayTableSchemaBuilder();

		ClayTableSchemaField skuField = clayTableSchemaBuilder.addField(
			"sku", "sku");

		skuField.setContentRenderer("commerceTableCellImageName");

		ClayTableSchemaField nameField = clayTableSchemaBuilder.addField(
			"name", "name");

		nameField.setContentRenderer("commerceTableCellNameWithError");

		ClayTableSchemaField priceField = clayTableSchemaBuilder.addField(
			"price", "price");

		priceField.setContentRenderer("commerceTablePrice");

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

		OrderFilterImpl orderFilterImpl = (OrderFilterImpl)filter;

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		List<CommerceOrderItem> commerceOrderItems =
			_commerceOrderItemService.getCommerceOrderItems(
				orderFilterImpl.getCommerceOrderId(),
				pagination.getStartPosition(), pagination.getEndPosition());

		CommerceOrder commerceOrder = null;
		Map<Long, List<CommerceOrderValidatorResult>>
			commerceOrderValidatorResultMap = null;

		if (!commerceOrderItems.isEmpty()) {
			CommerceOrderItem firstCommerceOrderItem = commerceOrderItems.get(
				0);

			commerceOrder = _commerceOrderService.getCommerceOrder(
				firstCommerceOrderItem.getCommerceOrderId());

			commerceOrderValidatorResultMap =
				_commerceOrderValidatorRegistry.
					getCommerceOrderValidatorResults(
						themeDisplay.getLocale(), commerceOrder);
		}

		try {
			for (CommerceOrderItem commerceOrderItem : commerceOrderItems) {
				String formattedUnitPrice = StringPool.BLANK;
				String formattedPromoPrice = StringPool.BLANK;
				String formattedDiscountAmount = StringPool.BLANK;
				String formattedFinalPrice = StringPool.BLANK;

				CommerceMoney unitPriceMoney =
					commerceOrderItem.getUnitPriceMoney();

				if (unitPriceMoney != null) {
					formattedUnitPrice = unitPriceMoney.format(
						themeDisplay.getLocale());
				}

				CommerceMoney promoPriceMoney =
					commerceOrderItem.getPromoPriceMoney();

				BigDecimal promoPrice = promoPriceMoney.getPrice();

				if ((promoPriceMoney != null) &&
					(promoPrice.compareTo(BigDecimal.ZERO) > 0)) {

					formattedPromoPrice = promoPriceMoney.format(
						themeDisplay.getLocale());
				}

				CommerceMoney finalPriceMoney =
					commerceOrderItem.getFinalPriceMoney();

				if (finalPriceMoney != null) {
					formattedFinalPrice = finalPriceMoney.format(
						themeDisplay.getLocale());
				}

				CommerceMoney discountAmount =
					commerceOrderItem.getDiscountAmountMoney();

				if (discountAmount != null) {
					formattedDiscountAmount = discountAmount.format(
						themeDisplay.getLocale());
				}

				List<CommerceOrderValidatorResult>
					commerceOrderValidatorResults =
						commerceOrderValidatorResultMap.get(
							commerceOrderItem.getCommerceOrderItemId());

				List<String> errorMessages = new ArrayList<>();

				for (CommerceOrderValidatorResult commerceOrderValidatorResult :
						commerceOrderValidatorResults) {

					errorMessages.add(
						commerceOrderValidatorResult.getLocalizedMessage());
				}

				orderItems.add(
					new OrderItem(
						commerceOrderItem.getCommerceOrderItemId(),
						commerceOrderItem.getCommerceOrderId(),
						commerceOrderItem.getSku(),
						commerceOrderItem.getName(themeDisplay.getLocale()),
						formattedUnitPrice, formattedPromoPrice,
						formattedDiscountAmount,
						commerceOrderItem.getQuantity(), formattedFinalPrice,
						_cpInstanceHelper.getCPInstanceThumbnailSrc(
							commerceOrderItem.getCPInstanceId()),
						CommerceOrderClayTableUtil.getViewShipmentURL(
							commerceOrderItem.getCommerceOrderId(),
							themeDisplay),
						0, ArrayUtil.toStringArray(errorMessages)));
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return orderItems;
	}

	@Override
	public boolean isShowActionsMenu() {
		return true;
	}

	private String _getDeleteCommerceOrderItemURL(
		long commerceOrderItemId, ThemeDisplay themeDisplay) {

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		PortletURL portletURL = PortletURLFactoryUtil.create(
			themeDisplay.getRequest(), portletDisplay.getId(),
			themeDisplay.getPlid(), PortletRequest.ACTION_PHASE);

		portletURL.setParameter(
			ActionRequest.ACTION_NAME, "editCommerceOrderItem");
		portletURL.setParameter(Constants.CMD, Constants.DELETE);
		portletURL.setParameter("redirect", themeDisplay.getURLCurrent());
		portletURL.setParameter(
			"commerceOrderItemId", String.valueOf(commerceOrderItemId));

		return portletURL.toString();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommercePendingOrderItemClayTable.class);

	@Reference
	private ClayTableSchemaBuilderFactory _clayTableSchemaBuilderFactory;

	@Reference
	private CommerceOrderItemService _commerceOrderItemService;

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference
	private CommerceOrderValidatorRegistry _commerceOrderValidatorRegistry;

	@Reference
	private CommerceProductPriceCalculation _commerceProductPriceCalculation;

	@Reference
	private CPInstanceHelper _cpInstanceHelper;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.model.CommerceOrder)"
	)
	private ModelResourcePermission<CommerceOrder> _modelResourcePermission;

}