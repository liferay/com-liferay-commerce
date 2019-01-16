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

import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
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
import com.liferay.commerce.order.content.web.internal.model.OrderItem;
import com.liferay.commerce.price.CommerceProductPrice;
import com.liferay.commerce.price.CommerceProductPriceCalculation;
import com.liferay.commerce.product.util.CPInstanceHelper;
import com.liferay.commerce.service.CommerceOrderItemService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.portlet.PortletQName;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.List;

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
		"commerce.data.provider.key=" + CommerceOrderItemClayTable.NAME,
		"commerce.table.name=" + CommerceOrderItemClayTable.NAME
	},
	service = {
		ClayTable.class, ClayTableActionProvider.class,
		CommerceDataSetDataProvider.class
	}
)
public class CommerceOrderItemClayTable
	implements CommerceDataSetDataProvider<OrderItem>, ClayTable,
			   ClayTableActionProvider {

	public static final String NAME = "commerceOrderItems";

	@Override
	public List<ClayTableAction> clayTableActions(
			HttpServletRequest httpServletRequest, long groupId, Object model)
		throws PortalException {

		List<ClayTableAction> clayTableActions = new ArrayList<>();

		OrderItem orderItem = (OrderItem)model;

		String viewURL = StringPool.BLANK;

		try {
			viewURL = _getViewShipmentURL(
				orderItem.getOrderItemId(), httpServletRequest);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		ClayTableAction clayTableAction = new ClayTableAction(
			viewURL, StringPool.BLANK,
			LanguageUtil.get(httpServletRequest, "view-shipments"), false,
			false);

		clayTableActions.add(clayTableAction);

		return clayTableActions;
	}

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

		OrderFilterImpl orderFilter = (OrderFilterImpl)filter;

		CommerceContext commerceContext =
			(CommerceContext)httpServletRequest.getAttribute(
				CommerceWebKeys.COMMERCE_CONTEXT);
		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		List<CommerceOrderItem> commerceOrderItems =
			_commerceOrderItemService.getCommerceOrderItems(
				orderFilter.getOrderId(), pagination.getStartPosition(),
				pagination.getEndPosition());

		try {
			for (CommerceOrderItem commerceOrderItem : commerceOrderItems) {
				String price = StringPool.BLANK;
				String discount = StringPool.BLANK;
				String total = StringPool.BLANK;

				CommerceProductPrice commerceProductPrice =
					_commerceProductPriceCalculation.getCommerceProductPrice(
						commerceOrderItem.getCPInstanceId(),
						commerceOrderItem.getQuantity(), commerceContext);

				CommerceMoney unitPrice = commerceProductPrice.getUnitPrice();
				CommerceMoney finalPrice = commerceProductPrice.getFinalPrice();

				CommerceDiscountValue discountValue =
					commerceProductPrice.getDiscountValue();

				if (unitPrice != null) {
					price = unitPrice.format(themeDisplay.getLocale());
				}

				if (finalPrice != null) {
					total = finalPrice.format(themeDisplay.getLocale());
				}

				if (discountValue != null) {
					CommerceMoney discountAmount =
						discountValue.getDiscountAmount();

					discount = discountAmount.format(themeDisplay.getLocale());
				}

				orderItems.add(
					new OrderItem(
						commerceOrderItem.getCommerceOrderItemId(),
						commerceOrderItem.getCommerceOrderId(),
						commerceOrderItem.getSku(),
						commerceOrderItem.getName(themeDisplay.getLocale()),
						price, discount, commerceOrderItem.getQuantity(), total,
						_cpInstanceHelper.getCPInstanceThumbnailSrc(
							commerceOrderItem.getCPInstanceId(), themeDisplay),
						_getViewShipmentURL(
							commerceOrderItem.getCommerceOrderId(),
							httpServletRequest)));
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

	private String _getViewShipmentURL(
			long commerceOrderItemId, HttpServletRequest httpServletRequest)
		throws Exception {

		PortletURL viewShipmentURL = PortletProviderUtil.getPortletURL(
			httpServletRequest, CommerceOrder.class.getName(),
			PortletProvider.Action.VIEW);

		viewShipmentURL.setParameter(
			"mvcRenderCommandName", "viewCommerceOrderShipments");
		viewShipmentURL.setParameter(
			"commerceOrderItemId", String.valueOf(commerceOrderItemId));
		viewShipmentURL.setWindowState(LiferayWindowState.POP_UP);

		viewShipmentURL.setParameter(
			PortletQName.PUBLIC_RENDER_PARAMETER_NAMESPACE + "backURL",
			_portal.getCurrentURL(httpServletRequest));

		return viewShipmentURL.toString();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceOrderItemClayTable.class);

	@Reference
	private ClayTableSchemaBuilderFactory _clayTableSchemaBuilderFactory;

	@Reference
	private CommerceOrderItemService _commerceOrderItemService;

	@Reference
	private CommerceProductPriceCalculation _commerceProductPriceCalculation;

	@Reference
	private CPInstanceHelper _cpInstanceHelper;

	@Reference
	private Portal _portal;

}