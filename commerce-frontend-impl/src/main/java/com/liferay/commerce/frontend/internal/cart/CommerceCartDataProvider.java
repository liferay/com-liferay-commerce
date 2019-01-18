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

package com.liferay.commerce.frontend.internal.cart;

import com.liferay.commerce.frontend.internal.cart.model.Cart;
import com.liferay.commerce.frontend.internal.cart.model.Prices;
import com.liferay.commerce.frontend.internal.cart.model.Product;
import com.liferay.commerce.frontend.internal.cart.model.Settings;
import com.liferay.commerce.frontend.internal.cart.model.Summary;
import com.liferay.commerce.constants.CPDefinitionInventoryConstants;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.model.CommerceMoney;
import com.liferay.commerce.discount.CommerceDiscountValue;
import com.liferay.commerce.model.CPDefinitionInventory;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.order.CommerceOrderValidator;
import com.liferay.commerce.order.CommerceOrderValidatorRegistry;
import com.liferay.commerce.order.CommerceOrderValidatorResult;
import com.liferay.commerce.price.CommerceOrderPrice;
import com.liferay.commerce.price.CommerceOrderPriceCalculation;
import com.liferay.commerce.price.CommerceProductPrice;
import com.liferay.commerce.price.CommerceProductPriceCalculation;
import com.liferay.commerce.product.util.CPInstanceHelper;
import com.liferay.commerce.service.CPDefinitionInventoryLocalService;
import com.liferay.commerce.service.CommerceOrderItemService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = CommerceCartDataProvider.class)
public class CommerceCartDataProvider {

	public Cart getCart(
			long commerceOrderId, ThemeDisplay themeDisplay,
			CommerceContext commerceContext)
		throws Exception {

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		return new Cart(
			getProducts(commerceOrder, themeDisplay, commerceContext),
			getSummary(
				commerceOrder, themeDisplay.getLocale(), commerceContext));
	}

	protected String[] getErrorMessages(
			Locale locale, CommerceOrderItem commerceOrderItem)
		throws PortalException {

		String[] errorMessages = new String[0];

		List<CommerceOrderValidator> commerceOrderValidators =
			_commerceOrderValidatorRegistry.getCommerceOrderValidators();

		for (CommerceOrderValidator commerceOrderValidator :
				commerceOrderValidators) {

			CommerceOrderValidatorResult commerceOrderValidatorResult =
				commerceOrderValidator.validate(locale, commerceOrderItem);

			if (!commerceOrderValidatorResult.isValid() &&
				commerceOrderValidatorResult.hasMessageResult()) {

				errorMessages = ArrayUtil.append(
					errorMessages,
					commerceOrderValidatorResult.getLocalizedMessage());
			}
		}

		return errorMessages;
	}

	protected Prices getPrice(
			CommerceOrderItem commerceOrderItem, Locale locale,
			CommerceContext commerceContext)
		throws PortalException {

		CommerceProductPrice commerceProductPrice =
			_commerceProductPriceCalculation.getCommerceProductPrice(
				commerceOrderItem.getCPInstanceId(),
				commerceOrderItem.getQuantity(), true, commerceContext);

		CommerceMoney unitPrice = commerceProductPrice.getUnitPrice();

		Prices prices = new Prices(unitPrice.format(locale));

		CommerceMoney unitPromoPrice = commerceProductPrice.getUnitPromoPrice();

		BigDecimal price = unitPromoPrice.getPrice();

		if ((BigDecimal.ZERO.compareTo(price) >= 0) &&
			(price.compareTo(unitPrice.getPrice()) >= 0)) {

			prices.setDiscount(unitPromoPrice.format(locale));
		}

		CommerceDiscountValue discountValue =
			commerceProductPrice.getDiscountValue();

		if (discountValue != null) {
			CommerceMoney discountAmount = discountValue.getDiscountAmount();

			prices.setDiscount(discountAmount.format(locale));
		}

		return prices;
	}

	protected List<Product> getProducts(
			CommerceOrder commerceOrder, ThemeDisplay themeDisplay,
			CommerceContext commerceContext)
		throws Exception {

		List<Product> products = new ArrayList<>();

		List<CommerceOrderItem> commerceOrderItems =
			commerceOrder.getCommerceOrderItems();

		for (CommerceOrderItem commerceOrderItem : commerceOrderItems) {
			Locale locale = themeDisplay.getLocale();

			Prices prices = getPrice(
				commerceOrderItem, locale, commerceContext);

			Settings settings = getSettings(commerceOrderItem);

			products.add(
				new Product(
					commerceOrderItem.getCommerceOrderItemId(),
					commerceOrderItem.getName(locale),
					commerceOrderItem.getSku(), commerceOrderItem.getQuantity(),
					_cpInstanceHelper.getCPInstanceThumbnailSrc(
						commerceOrderItem.getCPInstanceId(), themeDisplay),
					prices, settings,
					getErrorMessages(locale, commerceOrderItem)));
		}

		return products;
	}

	protected Settings getSettings(CommerceOrderItem commerceOrderItem)
		throws PortalException {

		Settings settings = new Settings();

		CPDefinitionInventory cpDefinitionInventory =
			_cpDefinitionInventoryLocalService.
				fetchCPDefinitionInventoryByCPDefinitionId(
					commerceOrderItem.getCPDefinitionId());

		int maxQuantity =
			CPDefinitionInventoryConstants.DEFAULT_MAX_ORDER_QUANTITY;
		int minQuantity =
			CPDefinitionInventoryConstants.DEFAULT_MIN_ORDER_QUANTITY;
		int multipleQuantity =
			CPDefinitionInventoryConstants.DEFAULT_MULTIPLE_ORDER_QUANTITY;

		if (cpDefinitionInventory != null) {
			maxQuantity = cpDefinitionInventory.getMaxOrderQuantity();
			minQuantity = cpDefinitionInventory.getMinOrderQuantity();
			multipleQuantity = cpDefinitionInventory.getMultipleOrderQuantity();

			String allowedOrderQuantities =
				cpDefinitionInventory.getAllowedOrderQuantities();

			if (Validator.isNotNull(allowedOrderQuantities)) {
				allowedOrderQuantities = allowedOrderQuantities.replaceAll(
					" *(, *)|(\\. *)|( +)", StringPool.COMMA);

				int[] allowedQuantities = StringUtil.split(
					allowedOrderQuantities, 0);

				Arrays.sort(allowedQuantities);

				settings.setAllowedQuantities(allowedQuantities);
			}
		}

		settings.setMaxQuantity(maxQuantity);
		settings.setMinQuantity(minQuantity);
		settings.setMultipleQuantity(multipleQuantity);

		return settings;
	}

	protected Summary getSummary(
			CommerceOrder commerceOrder, Locale locale,
			CommerceContext commerceContext)
		throws PortalException {

		CommerceOrderPrice commerceOrderPrice =
			_commerceOrderPriceCalculation.getCommerceOrderPrice(
				commerceOrder, commerceContext);

		CommerceMoney subtotal = commerceOrderPrice.getSubtotal();
		CommerceMoney total = commerceOrderPrice.getTotal();

		int itemsQuantity =
			_commerceOrderItemService.getCommerceOrderItemsQuantity(
				commerceOrder.getCommerceOrderId());

		Summary summary = new Summary(
			subtotal.format(locale), total.format(locale), itemsQuantity);

		CommerceDiscountValue totalDiscountValue =
			commerceOrderPrice.getTotalDiscountValue();

		if (totalDiscountValue != null) {
			CommerceMoney discountAmount =
				totalDiscountValue.getDiscountAmount();

			summary.setDiscount(discountAmount.format(locale));
		}

		return summary;
	}

	@Reference
	private CommerceOrderItemService _commerceOrderItemService;

	@Reference
	private CommerceOrderPriceCalculation _commerceOrderPriceCalculation;

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference
	private CommerceOrderValidatorRegistry _commerceOrderValidatorRegistry;

	@Reference
	private CommerceProductPriceCalculation _commerceProductPriceCalculation;

	@Reference
	private CPDefinitionInventoryLocalService
		_cpDefinitionInventoryLocalService;

	@Reference
	private CPInstanceHelper _cpInstanceHelper;

}