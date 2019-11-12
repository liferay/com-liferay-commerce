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
import com.liferay.commerce.frontend.ClayTableSchema;
import com.liferay.commerce.frontend.ClayTableSchemaBuilder;
import com.liferay.commerce.frontend.ClayTableSchemaBuilderFactory;
import com.liferay.commerce.frontend.ClayTableSchemaField;
import com.liferay.commerce.frontend.CommerceDataSetDataProvider;
import com.liferay.commerce.frontend.Filter;
import com.liferay.commerce.frontend.Pagination;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.order.content.web.internal.frontend.util.CommerceOrderClayTableUtil;
import com.liferay.commerce.order.content.web.internal.model.OrderItem;
import com.liferay.commerce.product.util.CPInstanceHelper;
import com.liferay.commerce.service.CommerceOrderItemService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
	implements ClayTable, CommerceDataSetDataProvider<OrderItem> {

	public static final String NAME = "commercePlacedOrderItems";

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

		clayTableSchemaBuilder.addField("name", "name");

		ClayTableSchemaField priceField = clayTableSchemaBuilder.addField(
			"price", "price");

		priceField.setContentRenderer("commerceTablePrice");

		clayTableSchemaBuilder.addField("discount", "discount");

		clayTableSchemaBuilder.addField("quantity", "quantity");

		clayTableSchemaBuilder.addField("total", "total");

		clayTableSchemaBuilder.addField("shippedQuantity", "shippedQuantity");

		ClayTableSchemaField clayTableSchemaField =
			clayTableSchemaBuilder.addField(
				"viewShipmentsURL", "viewShipmentsURL");

		clayTableSchemaField.setContentRenderer("commerceTableCellModalLink");

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

		try {
			for (CommerceOrderItem commerceOrderItem : commerceOrderItems) {
				CommerceMoney unitPriceMoney =
					commerceOrderItem.getUnitPriceMoney();
				CommerceMoney promoPriceMoney =
					commerceOrderItem.getPromoPriceMoney();
				CommerceMoney discountAmountMoney =
					commerceOrderItem.getDiscountAmountMoney();
				CommerceMoney finalPriceMoney =
					commerceOrderItem.getFinalPriceMoney();

				Locale locale = themeDisplay.getLocale();

				String formattedUnitPrice = unitPriceMoney.format(locale);
				String formattedDiscountAmount = discountAmountMoney.format(
					locale);
				String formattedFinalPrice = finalPriceMoney.format(locale);

				String formattedPromoPrice = StringPool.BLANK;

				BigDecimal promoPriceValue = promoPriceMoney.getPrice();

				if ((promoPriceMoney != null) &&
					(promoPriceValue.compareTo(BigDecimal.ZERO) > 0)) {

					formattedPromoPrice = promoPriceMoney.format(
						themeDisplay.getLocale());
				}

				String viewShipmentURL = null;

				if (commerceOrderItem.getShippedQuantity() > 0) {
					viewShipmentURL =
						CommerceOrderClayTableUtil.getViewShipmentURL(
							commerceOrderItem.getCommerceOrderItemId(),
							themeDisplay);
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
						viewShipmentURL, commerceOrderItem.getShippedQuantity(),
						null));
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return orderItems;
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
	private CommerceOrderItemService _commerceOrderItemService;

	@Reference
	private CPInstanceHelper _cpInstanceHelper;

}