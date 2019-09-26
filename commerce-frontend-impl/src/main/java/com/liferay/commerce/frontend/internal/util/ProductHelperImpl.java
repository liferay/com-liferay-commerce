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

package com.liferay.commerce.frontend.internal.util;

import com.liferay.commerce.constants.CPDefinitionInventoryConstants;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.configuration.RoundingTypeConfiguration;
import com.liferay.commerce.currency.constants.RoundingTypeConstants;
import com.liferay.commerce.currency.model.CommerceMoney;
import com.liferay.commerce.currency.model.CommerceMoneyFactoryUtil;
import com.liferay.commerce.discount.CommerceDiscountValue;
import com.liferay.commerce.frontend.model.PriceModel;
import com.liferay.commerce.frontend.model.ProductSettingsModel;
import com.liferay.commerce.frontend.util.ProductHelper;
import com.liferay.commerce.model.CPDefinitionInventory;
import com.liferay.commerce.price.CommerceProductPrice;
import com.liferay.commerce.price.CommerceProductPriceCalculation;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.commerce.service.CPDefinitionInventoryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.settings.SystemSettingsLocator;

import java.math.BigDecimal;

import java.text.NumberFormat;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 * @author Luca Pellizzon
 */
@Component(service = ProductHelper.class)
public class ProductHelperImpl implements ProductHelper {

	public PriceModel getPrice(
			long cpInstanceId, int quantity, CommerceContext commerceContext,
			Locale locale)
		throws PortalException {

		CommerceProductPrice commerceProductPrice =
			_commerceProductPriceCalculation.getCommerceProductPrice(
				cpInstanceId, quantity, true, commerceContext);

		if (commerceProductPrice == null) {
			return null;
		}

		CommerceMoney unitPriceMoney = commerceProductPrice.getUnitPrice();

		BigDecimal unitPrice = unitPriceMoney.getPrice();

		CommerceMoney price = CommerceMoneyFactoryUtil.create(
			unitPriceMoney.getCommerceCurrency(),
			unitPrice.multiply(new BigDecimal(quantity)));

		PriceModel priceModel = new PriceModel(price.format(locale));

		CommerceMoney unitPromoPriceMoney =
			commerceProductPrice.getUnitPromoPrice();

		BigDecimal unitPromoPrice = unitPromoPriceMoney.getPrice();

		if ((unitPromoPrice != null) &&
			(unitPromoPrice.compareTo(BigDecimal.ZERO) > 0) &&
			(unitPromoPrice.compareTo(unitPrice) < 0)) {

			priceModel.setPromoPrice(unitPromoPriceMoney.format(locale));
		}

		CommerceDiscountValue discountValue =
			commerceProductPrice.getDiscountValue();

		if (discountValue != null) {
			CommerceMoney discountAmount = discountValue.getDiscountAmount();

			priceModel.setDiscount(discountAmount.format(locale));
		}

		CommerceMoney finalPrice = commerceProductPrice.getFinalPrice();

		priceModel.setFinalPrice(finalPrice.format(locale));

		RoundingTypeConfiguration roundingTypeConfiguration =
			_configurationProvider.getConfiguration(
				RoundingTypeConfiguration.class,
				new SystemSettingsLocator(RoundingTypeConstants.SERVICE_NAME));

		BigDecimal priceValue = price.getPrice();

		BigDecimal difference = priceValue.subtract(finalPrice.getPrice());

		BigDecimal percentage = difference.divide(
			priceValue, 10, roundingTypeConfiguration.percentageRoundingMode());

		NumberFormat percentFormat = NumberFormat.getPercentInstance(locale);

		percentFormat.setMaximumFractionDigits(
			roundingTypeConfiguration.percentageFractionDigits());
		percentFormat.setRoundingMode(
			roundingTypeConfiguration.percentageRoundingMode());

		String result = percentFormat.format(percentage);

		priceModel.setTotalSavingPercentage(result);

		return priceModel;
	}

	public ProductSettingsModel getProductSettingsModel(long cpInstanceId)
		throws PortalException {

		ProductSettingsModel productSettingsModel = new ProductSettingsModel();

		int minOrderQuantity =
			CPDefinitionInventoryConstants.DEFAULT_MIN_ORDER_QUANTITY;
		int maxOrderQuantity =
			CPDefinitionInventoryConstants.DEFAULT_MAX_ORDER_QUANTITY;
		int multipleQuantity =
			CPDefinitionInventoryConstants.DEFAULT_MULTIPLE_ORDER_QUANTITY;

		CPDefinitionInventory cpDefinitionInventory = null;

		CPInstance cpInstance = _cpInstanceLocalService.fetchCPInstance(
			cpInstanceId);

		if (cpInstance != null) {
			cpDefinitionInventory =
				_cpDefinitionInventoryLocalService.
					fetchCPDefinitionInventoryByCPDefinitionId(
						cpInstance.getCPDefinitionId());
		}

		if (cpDefinitionInventory != null) {
			minOrderQuantity = cpDefinitionInventory.getMinOrderQuantity();
			maxOrderQuantity = cpDefinitionInventory.getMaxOrderQuantity();
			multipleQuantity = cpDefinitionInventory.getMultipleOrderQuantity();

			int[] allowedOrderQuantitiesArray =
				cpDefinitionInventory.getAllowedOrderQuantitiesArray();

			if ((allowedOrderQuantitiesArray != null) &&
				(allowedOrderQuantitiesArray.length > 0)) {

				productSettingsModel.setAllowedQuantities(
					allowedOrderQuantitiesArray);
			}

			productSettingsModel.setLowStockQuantity(
				cpDefinitionInventory.getMinStockQuantity());
			productSettingsModel.setShowAvailabilityDot(
				cpDefinitionInventory.isDisplayAvailability());
		}

		productSettingsModel.setMinQuantity(minOrderQuantity);
		productSettingsModel.setMaxQuantity(maxOrderQuantity);
		productSettingsModel.setMultipleQuantity(multipleQuantity);

		return productSettingsModel;
	}

	@Reference
	private CommerceProductPriceCalculation _commerceProductPriceCalculation;

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference
	private CPDefinitionInventoryLocalService
		_cpDefinitionInventoryLocalService;

	@Reference
	private CPInstanceLocalService _cpInstanceLocalService;

}