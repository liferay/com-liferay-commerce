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
import com.liferay.commerce.currency.model.CommerceMoneyFactory;
import com.liferay.commerce.frontend.ClayTable;
import com.liferay.commerce.frontend.ClayTableSchema;
import com.liferay.commerce.frontend.ClayTableSchemaBuilder;
import com.liferay.commerce.frontend.ClayTableSchemaBuilderFactory;
import com.liferay.commerce.frontend.ClayTableSchemaField;
import com.liferay.commerce.frontend.CommerceDataSetDataProvider;
import com.liferay.commerce.frontend.Filter;
import com.liferay.commerce.frontend.Pagination;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.order.content.web.internal.frontend.util.CommerceOrderClayTableUtil;
import com.liferay.commerce.order.content.web.internal.model.PlacedOrderItem;
import com.liferay.commerce.price.CommerceProductPriceCalculation;
import com.liferay.commerce.product.util.CPInstanceHelper;
import com.liferay.commerce.service.CommerceOrderItemService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
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
		"commerce.data.provider.key=" + CommercePlacedOrderItemClayTable.NAME,
		"commerce.table.name=" + CommercePlacedOrderItemClayTable.NAME
	},
	service = {ClayTable.class, CommerceDataSetDataProvider.class}
)
public class CommercePlacedOrderItemClayTable
	implements ClayTable, CommerceDataSetDataProvider<PlacedOrderItem> {

	public static final String NAME = "commercePlacedOrderItems";

	@Override
	public int countItems(HttpServletRequest httpServletRequest, Filter filter)
		throws PortalException {

		OrderFilterImpl orderFilter = (OrderFilterImpl)filter;

		return _commerceOrderItemService.getCommerceOrderItemsCount(
			orderFilter.getOrderId());
	}

	@Override
	public ClayTableSchema getClayTableSchema() {
		ClayTableSchemaBuilder clayTableSchemaBuilder =
			_clayTableSchemaBuilderFactory.clayTableSchemaBuilder();

		ClayTableSchemaField skuField = clayTableSchemaBuilder.addField(
			"sku", "sku");

		skuField.setContentRenderer("imageName");

		clayTableSchemaBuilder.addField("name", "name");

		clayTableSchemaBuilder.addField("price", "price");

		clayTableSchemaBuilder.addField("discount", "discount");

		clayTableSchemaBuilder.addField("quantity", "quantity");

		clayTableSchemaBuilder.addField("total", "total");

		clayTableSchemaBuilder.addField("total", "total");

		clayTableSchemaBuilder.addField("shippedQuantity", "shippedQuantity");

		ClayTableSchemaField clayTableSchemaField =
			clayTableSchemaBuilder.addField("shipmentUrl", "shipment");

		clayTableSchemaField.setContentRenderer("modalLink");

		return clayTableSchemaBuilder.build();
	}

	@Override
	public String getId() {
		return NAME;
	}

	@Override
	public List<PlacedOrderItem> getItems(
			HttpServletRequest httpServletRequest, Filter filter,
			Pagination pagination, Sort sort)
		throws PortalException {

		List<PlacedOrderItem> placedOrderItems = new ArrayList<>();

		OrderFilterImpl orderFilter = (OrderFilterImpl)filter;

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			orderFilter.getOrderId());

		List<CommerceOrderItem> commerceOrderItems =
			_commerceOrderItemService.getCommerceOrderItems(
				orderFilter.getOrderId(), pagination.getStartPosition(),
				pagination.getEndPosition());

		try {
			for (CommerceOrderItem commerceOrderItem : commerceOrderItems) {
				String price = StringPool.BLANK;
				String discount = StringPool.BLANK;
				String total = StringPool.BLANK;

				CommerceMoney unitPriceMoney =
					commerceOrderItem.getUnitPriceMoney();

				if (unitPriceMoney != null) {
					price = unitPriceMoney.format(themeDisplay.getLocale());
				}

				CommerceMoney finalPrice =
					commerceOrderItem.getFinalPriceMoney();

				if (finalPrice != null) {
					total = finalPrice.format(themeDisplay.getLocale());
				}

				CommerceMoney discountAmount = _commerceMoneyFactory.create(
					commerceOrder.getCommerceCurrency(),
					commerceOrderItem.getDiscountAmount());

				discount = discountAmount.format(themeDisplay.getLocale());

				String viewShipmentURL = null;

				if (commerceOrderItem.getShippedQuantity() > 0) {
					viewShipmentURL =
						CommerceOrderClayTableUtil.getViewShipmentURL(
							commerceOrderItem.getCommerceOrderItemId(),
							themeDisplay);
				}

				placedOrderItems.add(
					new PlacedOrderItem(
						commerceOrderItem.getCommerceOrderItemId(),
						commerceOrderItem.getCommerceOrderId(),
						commerceOrderItem.getSku(),
						commerceOrderItem.getName(themeDisplay.getLocale()),
						price, discount, commerceOrderItem.getQuantity(), total,
						_cpInstanceHelper.getCPInstanceThumbnailSrc(
							commerceOrderItem.getCPInstanceId()),
						viewShipmentURL,
						commerceOrderItem.getShippedQuantity()));
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return placedOrderItems;
	}

	@Override
	public boolean isShowActionsMenu() {
		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommercePlacedOrderItemClayTable.class);

	@Reference
	private ClayTableSchemaBuilderFactory _clayTableSchemaBuilderFactory;

	@Reference
	private CommerceMoneyFactory _commerceMoneyFactory;

	@Reference
	private CommerceOrderItemService _commerceOrderItemService;

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference
	private CommerceProductPriceCalculation _commerceProductPriceCalculation;

	@Reference
	private CPInstanceHelper _cpInstanceHelper;

}