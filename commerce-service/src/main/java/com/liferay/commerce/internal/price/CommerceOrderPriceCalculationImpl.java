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

package com.liferay.commerce.internal.price;

import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.model.CommerceMoney;
import com.liferay.commerce.currency.model.CommerceMoneyFactory;
import com.liferay.commerce.discount.CommerceDiscountCalculation;
import com.liferay.commerce.discount.CommerceDiscountValue;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.price.CommerceOrderPrice;
import com.liferay.commerce.price.CommerceOrderPriceCalculation;
import com.liferay.commerce.price.CommerceProductPriceCalculation;
import com.liferay.commerce.tax.CommerceTaxCalculation;
import com.liferay.portal.kernel.exception.PortalException;

import java.math.BigDecimal;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 * @author Marco Leo
 */
@Component
public class CommerceOrderPriceCalculationImpl
	implements CommerceOrderPriceCalculation {

	@Override
	public CommerceOrderPrice getCommerceOrderPrice(
			CommerceOrder commerceOrder, CommerceContext commerceContext)
		throws PortalException {

		if (commerceOrder == null) {
			return _getEmptyCommerceOrderPrice(
				commerceContext.getCommerceCurrency());
		}

		CommerceMoney subtotalMoney = getSubtotal(
			commerceOrder, commerceContext);
		CommerceMoney taxValue = getTaxValue(commerceOrder, commerceContext);

		BigDecimal shippingAmount = commerceOrder.getShippingAmount();
		BigDecimal subtotalAmount = subtotalMoney.getPrice();

		CommerceDiscountValue orderShippingCommerceDiscountValue =
			_commerceDiscountCalculation.getOrderShippingCommerceDiscountValue(
				commerceOrder.getShippingAmount(), commerceContext);

		CommerceDiscountValue orderSubtotalCommerceDiscountValue =
			_commerceDiscountCalculation.getOrderSubtotalCommerceDiscountValue(
				subtotalAmount, commerceContext);

		BigDecimal totalAmount = subtotalAmount;

		if (orderSubtotalCommerceDiscountValue != null) {
			CommerceMoney discountAmount =
				orderSubtotalCommerceDiscountValue.getDiscountAmount();

			totalAmount = totalAmount.subtract(discountAmount.getPrice());
		}

		totalAmount = totalAmount.add(shippingAmount);

		if (orderShippingCommerceDiscountValue != null) {
			CommerceMoney discountAmount =
				orderShippingCommerceDiscountValue.getDiscountAmount();

			totalAmount = totalAmount.subtract(discountAmount.getPrice());
		}

		totalAmount = totalAmount.add(taxValue.getPrice());

		CommerceDiscountValue orderTotalCommerceDiscountValue =
			_commerceDiscountCalculation.getOrderTotalCommerceDiscountValue(
				totalAmount, commerceContext);

		if (orderTotalCommerceDiscountValue != null) {
			CommerceMoney discountAmount =
				orderTotalCommerceDiscountValue.getDiscountAmount();

			totalAmount = totalAmount.subtract(discountAmount.getPrice());
		}

		CommerceOrderPriceImpl commerceOrderPrice =
			new CommerceOrderPriceImpl();

		commerceOrderPrice.setShippingDiscountValue(
			orderShippingCommerceDiscountValue);
		commerceOrderPrice.setSubtotalDiscountValue(
			orderSubtotalCommerceDiscountValue);
		commerceOrderPrice.setTotalDiscountValue(
			orderTotalCommerceDiscountValue);

		commerceOrderPrice.setShippingValue(
			_commerceMoneyFactory.create(
				commerceOrder.getCommerceCurrency(), shippingAmount));
		commerceOrderPrice.setSubtotal(subtotalMoney);
		commerceOrderPrice.setTaxValue(taxValue);
		commerceOrderPrice.setTotal(
			_commerceMoneyFactory.create(
				commerceOrder.getCommerceCurrency(), totalAmount));

		return commerceOrderPrice;
	}

	@Override
	public CommerceMoney getSubtotal(
			CommerceOrder commerceOrder, CommerceContext commerceContext)
		throws PortalException {

		BigDecimal subtotal = BigDecimal.ZERO;

		if (commerceOrder == null) {
			return _commerceMoneyFactory.create(
				commerceContext.getCommerceCurrency(), subtotal);
		}

		if (!commerceOrder.isOpen()) {
			return _commerceMoneyFactory.create(
				commerceContext.getCommerceCurrency(),
				commerceOrder.getSubtotal());
		}

		for (CommerceOrderItem commerceOrderItem :
				commerceOrder.getCommerceOrderItems()) {

			subtotal = subtotal.add(commerceOrderItem.getFinalPrice());
		}

		return _commerceMoneyFactory.create(
			commerceContext.getCommerceCurrency(), subtotal);
	}

	@Override
	public CommerceMoney getTaxValue(
			CommerceOrder commerceOrder, CommerceContext commerceContext)
		throws PortalException {

		if (commerceOrder == null) {
			_commerceMoneyFactory.create(
				commerceContext.getCommerceCurrency(), BigDecimal.ZERO);
		}

		if (!commerceOrder.isOpen()) {
			return _commerceMoneyFactory.create(
				commerceContext.getCommerceCurrency(),
				commerceOrder.getTaxAmount());
		}

		return _commerceTaxCalculation.getTaxAmount(
			commerceOrder, commerceContext);
	}

	@Override
	public CommerceMoney getTotal(
			CommerceOrder commerceOrder, CommerceContext commerceContext)
		throws PortalException {

		if (!commerceOrder.isOpen()) {
			return _commerceMoneyFactory.create(
				commerceContext.getCommerceCurrency(),
				commerceOrder.getTotal());
		}

		CommerceOrderPrice commerceOrderPrice = getCommerceOrderPrice(
			commerceOrder, commerceContext);

		return commerceOrderPrice.getTotal();
	}

	private CommerceOrderPriceImpl _getEmptyCommerceOrderPrice(
		CommerceCurrency commerceCurrency) {

		CommerceMoney zero = _commerceMoneyFactory.create(
			commerceCurrency, BigDecimal.ZERO);

		CommerceOrderPriceImpl commerceOrderPrice =
			new CommerceOrderPriceImpl();

		commerceOrderPrice.setShippingDiscountValue(null);
		commerceOrderPrice.setSubtotalDiscountValue(null);
		commerceOrderPrice.setTotalDiscountValue(null);
		commerceOrderPrice.setShippingValue(zero);
		commerceOrderPrice.setSubtotal(zero);
		commerceOrderPrice.setTaxValue(zero);
		commerceOrderPrice.setTotal(zero);

		return commerceOrderPrice;
	}

	@Reference
	private CommerceDiscountCalculation _commerceDiscountCalculation;

	@Reference
	private CommerceMoneyFactory _commerceMoneyFactory;

	@Reference
	private CommerceProductPriceCalculation _commerceProductPriceCalculation;

	@Reference
	private CommerceTaxCalculation _commerceTaxCalculation;

}