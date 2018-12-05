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

package com.liferay.commerce.cart.rest.internal.provider;

import com.liferay.commerce.cart.rest.internal.domain.model.CommerceCart;
import com.liferay.commerce.cart.rest.internal.domain.model.CommerceCartProduct;
import com.liferay.commerce.cart.rest.internal.domain.model.CommerceCartProductPrice;
import com.liferay.commerce.cart.rest.internal.domain.model.CommerceCartProductSettings;
import com.liferay.commerce.cart.rest.internal.domain.model.CommerceCartSummary;
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
import com.liferay.commerce.product.model.CPAttachmentFileEntry;
import com.liferay.commerce.product.model.CPAttachmentFileEntryConstants;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.util.CPInstanceHelper;
import com.liferay.commerce.service.CPDefinitionInventoryLocalService;
import com.liferay.commerce.service.CommerceOrderItemService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = CommerceCartDataProvider.class)
public class CommerceCartDataProvider {

	public CommerceCart getCommerceCart(
			long commerceOrderId, ThemeDisplay themeDisplay,
			CommerceContext commerceContext)
		throws Exception {

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		return new CommerceCart(
			getCommerceCartProducts(
				commerceOrder, themeDisplay, commerceContext),
			getCommerceCartSummary(
				commerceOrder, themeDisplay.getLocale(), commerceContext));
	}

	protected CommerceCartProductPrice getCommerceCartProductPrice(
			CommerceOrderItem commerceOrderItem, Locale locale,
			CommerceContext commerceContext)
		throws PortalException {

		CommerceProductPrice commerceProductPrice =
			_commerceProductPriceCalculation.getCommerceProductPrice(
				commerceOrderItem.getCPInstanceId(),
				commerceOrderItem.getQuantity(), true, commerceContext);

		CommerceMoney unitPrice = commerceProductPrice.getUnitPrice();

		CommerceCartProductPrice commerceCartProductPrice =
			new CommerceCartProductPrice(unitPrice.format(locale));

		CommerceMoney unitPromoPrice = commerceProductPrice.getUnitPromoPrice();

		BigDecimal price = unitPromoPrice.getPrice();

		if ((BigDecimal.ZERO.compareTo(price) >= 0) &&
			(price.compareTo(unitPrice.getPrice()) >= 0)) {

			commerceCartProductPrice.setDiscount(unitPromoPrice.format(locale));
		}

		CommerceDiscountValue discountValue =
			commerceProductPrice.getDiscountValue();

		if (discountValue != null) {
			CommerceMoney discountAmount = discountValue.getDiscountAmount();

			commerceCartProductPrice.setDiscount(discountAmount.format(locale));
		}

		return commerceCartProductPrice;
	}

	protected List<CommerceCartProduct> getCommerceCartProducts(
			CommerceOrder commerceOrder, ThemeDisplay themeDisplay,
			CommerceContext commerceContext)
		throws Exception {

		List<CommerceCartProduct> commerceCartProducts = new ArrayList<>();

		List<CommerceOrderItem> commerceOrderItems =
			commerceOrder.getCommerceOrderItems();

		for (CommerceOrderItem commerceOrderItem : commerceOrderItems) {
			CommerceCartProductPrice commerceCartProductPrice =
				getCommerceCartProductPrice(
					commerceOrderItem, themeDisplay.getLocale(),
					commerceContext);

			CommerceCartProductSettings commerceCartProductSettings =
				getCommerceCartProductSettings(commerceOrderItem);

			commerceCartProducts.add(
				new CommerceCartProduct(
					commerceOrderItem.getCommerceOrderItemId(),
					commerceOrderItem.getName(themeDisplay.getLocale()),
					commerceOrderItem.getSku(), commerceOrderItem.getQuantity(),
					getCommerceOrderItemThumbnailSrc(
						commerceOrderItem, themeDisplay),
					commerceCartProductPrice, commerceCartProductSettings,
					getErrorMessages(commerceOrderItem)));
		}

		return commerceCartProducts;
	}

	protected CommerceCartProductSettings getCommerceCartProductSettings(
			CommerceOrderItem commerceOrderItem)
		throws PortalException {

		CommerceCartProductSettings commerceCartProductSettings =
			new CommerceCartProductSettings();

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

				commerceCartProductSettings.setAllowedQuantities(
					allowedQuantities);
			}
		}

		commerceCartProductSettings.setMaxQuantity(maxQuantity);
		commerceCartProductSettings.setMinQuantity(minQuantity);
		commerceCartProductSettings.setMultipleQuantity(multipleQuantity);

		return commerceCartProductSettings;
	}

	protected CommerceCartSummary getCommerceCartSummary(
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

		CommerceCartSummary commerceCartSummary = new CommerceCartSummary(
			subtotal.format(locale), total.format(locale), itemsQuantity);

		CommerceDiscountValue totalDiscountValue =
			commerceOrderPrice.getTotalDiscountValue();

		if (totalDiscountValue != null) {
			CommerceMoney discountAmount =
				totalDiscountValue.getDiscountAmount();

			commerceCartSummary.setDiscount(discountAmount.format(locale));
		}

		return commerceCartSummary;
	}

	protected String getCommerceOrderItemThumbnailSrc(
			CommerceOrderItem commerceOrderItem, ThemeDisplay themeDisplay)
		throws Exception {

		List<CPAttachmentFileEntry> cpAttachmentFileEntries =
			_cpInstanceHelper.getCPAttachmentFileEntries(
				commerceOrderItem.getCPDefinitionId(),
				commerceOrderItem.getJson(),
				CPAttachmentFileEntryConstants.TYPE_IMAGE);

		if (cpAttachmentFileEntries.isEmpty()) {
			CPDefinition cpDefinition = commerceOrderItem.getCPDefinition();

			return cpDefinition.getDefaultImageThumbnailSrc(themeDisplay);
		}

		CPAttachmentFileEntry cpAttachmentFileEntry =
			cpAttachmentFileEntries.get(0);

		FileEntry fileEntry = cpAttachmentFileEntry.getFileEntry();

		if (fileEntry == null) {
			return null;
		}

		return DLUtil.getThumbnailSrc(fileEntry, themeDisplay);
	}

	protected String[] getErrorMessages(CommerceOrderItem commerceOrderItem)
		throws PortalException {

		String[] errorMessages = new String[0];

		List<CommerceOrderValidator> commerceOrderValidators =
			_commerceOrderValidatorRegistry.getCommerceOrderValidators();

		for (CommerceOrderValidator commerceOrderValidator :
				commerceOrderValidators) {

			CommerceOrderValidatorResult commerceOrderValidatorResult =
				commerceOrderValidator.validate(commerceOrderItem);

			if (!commerceOrderValidatorResult.isValid() &&
				commerceOrderValidatorResult.hasMessageResult()) {

				errorMessages = ArrayUtil.append(
					errorMessages, commerceOrderValidatorResult.getMessage());
			}
		}

		return errorMessages;
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