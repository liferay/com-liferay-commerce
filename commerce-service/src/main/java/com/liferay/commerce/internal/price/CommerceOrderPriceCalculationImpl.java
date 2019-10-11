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

import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.account.model.CommerceAccount;
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
import com.liferay.commerce.product.constants.CPActionKeys;
import com.liferay.commerce.product.constants.CPConstants;
import com.liferay.commerce.tax.CommerceTaxCalculation;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 * @author Marco Leo
 */
@Component(service = CommerceOrderPriceCalculation.class)
public class CommerceOrderPriceCalculationImpl
	implements CommerceOrderPriceCalculation {

	@Override
	public CommerceOrderPrice getCommerceOrderPrice(
			CommerceOrder commerceOrder, boolean secure,
			CommerceContext commerceContext)
		throws PortalException {

		if (secure && !_hasViewPricePermission(commerceContext)) {
			return null;
		}

		if (commerceOrder == null) {
			return _getEmptyCommerceOrderPrice(
				commerceContext.getCommerceCurrency());
		}

		if (!commerceOrder.isOpen()) {
			return _getCommerceOrderPriceFromOrder(commerceOrder);
		}

		CommerceMoney subtotalMoney = getSubtotal(
			commerceOrder, secure, commerceContext);
		CommerceMoney taxValue = getTaxValue(
			commerceOrder, secure, commerceContext);

		BigDecimal shippingAmount = commerceOrder.getShippingAmount();
		BigDecimal subtotalAmount = subtotalMoney.getPrice();

		CommerceDiscountValue orderShippingCommerceDiscountValue =
			_commerceDiscountCalculation.getOrderShippingCommerceDiscountValue(
				commerceOrder, shippingAmount, commerceContext);

		CommerceDiscountValue orderSubtotalCommerceDiscountValue =
			_commerceDiscountCalculation.getOrderSubtotalCommerceDiscountValue(
				commerceOrder, subtotalAmount, commerceContext);

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
				commerceOrder, totalAmount, commerceContext);

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
	public CommerceOrderPrice getCommerceOrderPrice(
			CommerceOrder commerceOrder, CommerceContext commerceContext)
		throws PortalException {

		return getCommerceOrderPrice(commerceOrder, true, commerceContext);
	}

	@Override
	public CommerceMoney getSubtotal(
			CommerceOrder commerceOrder, boolean secure,
			CommerceContext commerceContext)
		throws PortalException {

		if (secure && !_hasViewPricePermission(commerceContext)) {
			return null;
		}

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
	public CommerceMoney getSubtotal(
			CommerceOrder commerceOrder, CommerceContext commerceContext)
		throws PortalException {

		return getSubtotal(commerceOrder, true, commerceContext);
	}

	@Override
	public CommerceMoney getTaxValue(
			CommerceOrder commerceOrder, boolean secure,
			CommerceContext commerceContext)
		throws PortalException {

		if (secure && !_hasViewPricePermission(commerceContext)) {
			return null;
		}

		if (commerceOrder == null) {
			return _commerceMoneyFactory.create(
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
	public CommerceMoney getTaxValue(
			CommerceOrder commerceOrder, CommerceContext commerceContext)
		throws PortalException {

		return getTaxValue(commerceOrder, true, commerceContext);
	}

	@Override
	public CommerceMoney getTotal(
			CommerceOrder commerceOrder, boolean secure,
			CommerceContext commerceContext)
		throws PortalException {

		if (secure && !_hasViewPricePermission(commerceContext)) {
			return null;
		}

		if (!commerceOrder.isOpen()) {
			return _commerceMoneyFactory.create(
				commerceContext.getCommerceCurrency(),
				commerceOrder.getTotal());
		}

		CommerceOrderPrice commerceOrderPrice = getCommerceOrderPrice(
			commerceOrder, commerceContext);

		return commerceOrderPrice.getTotal();
	}

	@Override
	public CommerceMoney getTotal(
			CommerceOrder commerceOrder, CommerceContext commerceContext)
		throws PortalException {

		return getTotal(commerceOrder, true, commerceContext);
	}

	private CommerceDiscountValue _createCommerceDiscountValue(
		BigDecimal amount, CommerceCurrency commerceCurrency,
		BigDecimal discountAmount, BigDecimal level1, BigDecimal level2,
		BigDecimal level3, BigDecimal level4) {

		if ((discountAmount == null) || (amount == null) ||
			(amount.compareTo(BigDecimal.ZERO) <= 0)) {

			return new CommerceDiscountValue(
				0,
				_commerceMoneyFactory.create(commerceCurrency, BigDecimal.ZERO),
				BigDecimal.ZERO,
				new BigDecimal[] {
					BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
					BigDecimal.ZERO
				});
		}

		BigDecimal[] discountPercentageValues = {
			level1, level2, level3, level4
		};

		BigDecimal discountPercentage = discountAmount.divide(
			amount.add(discountAmount),
			RoundingMode.valueOf(commerceCurrency.getRoundingMode()));

		discountPercentage = discountPercentage.multiply(
			BigDecimal.valueOf(100));

		return new CommerceDiscountValue(
			0, _commerceMoneyFactory.create(commerceCurrency, discountAmount),
			discountPercentage, discountPercentageValues);
	}

	private CommerceOrderPrice _getCommerceOrderPriceFromOrder(
			CommerceOrder commerceOrder)
		throws PortalException {

		CommerceCurrency commerceCurrency = commerceOrder.getCommerceCurrency();

		CommerceDiscountValue shippingDiscountValue =
			_createCommerceDiscountValue(
				commerceOrder.getShippingAmount(), commerceCurrency,
				commerceOrder.getShippingDiscountAmount(),
				commerceOrder.getShippingDiscountPercentageLevel1(),
				commerceOrder.getShippingDiscountPercentageLevel2(),
				commerceOrder.getShippingDiscountPercentageLevel3(),
				commerceOrder.getShippingDiscountPercentageLevel4());

		CommerceDiscountValue subtotalDiscountValue =
			_createCommerceDiscountValue(
				commerceOrder.getSubtotal(), commerceCurrency,
				commerceOrder.getSubtotalDiscountAmount(),
				commerceOrder.getSubtotalDiscountPercentageLevel1(),
				commerceOrder.getSubtotalDiscountPercentageLevel2(),
				commerceOrder.getSubtotalDiscountPercentageLevel3(),
				commerceOrder.getSubtotalDiscountPercentageLevel4());

		CommerceDiscountValue totalDiscountValue = _createCommerceDiscountValue(
			commerceOrder.getTotal(), commerceCurrency,
			commerceOrder.getTotalDiscountAmount(),
			commerceOrder.getTotalDiscountPercentageLevel1(),
			commerceOrder.getTotalDiscountPercentageLevel2(),
			commerceOrder.getTotalDiscountPercentageLevel3(),
			commerceOrder.getTotalDiscountPercentageLevel4());

		CommerceOrderPriceImpl commerceOrderPrice =
			new CommerceOrderPriceImpl();

		commerceOrderPrice.setShippingDiscountValue(shippingDiscountValue);
		commerceOrderPrice.setSubtotalDiscountValue(subtotalDiscountValue);
		commerceOrderPrice.setTotalDiscountValue(totalDiscountValue);

		commerceOrderPrice.setShippingValue(
			_commerceMoneyFactory.create(
				commerceOrder.getCommerceCurrency(),
				commerceOrder.getShippingAmount()));
		commerceOrderPrice.setSubtotal(
			_commerceMoneyFactory.create(
				commerceOrder.getCommerceCurrency(),
				commerceOrder.getSubtotal()));
		commerceOrderPrice.setTaxValue(
			_commerceMoneyFactory.create(
				commerceOrder.getCommerceCurrency(),
				commerceOrder.getTaxAmount()));
		commerceOrderPrice.setTotal(
			_commerceMoneyFactory.create(
				commerceOrder.getCommerceCurrency(), commerceOrder.getTotal()));

		return commerceOrderPrice;
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

	private boolean _hasViewPricePermission(CommerceContext commerceContext)
		throws PortalException {

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		CommerceAccount commerceAccount = commerceContext.getCommerceAccount();

		if ((commerceAccount != null) &&
			(commerceAccount.getType() ==
				CommerceAccountConstants.ACCOUNT_TYPE_BUSINESS)) {

			return _portletResourcePermission.contains(
				permissionChecker, commerceAccount.getCommerceAccountGroupId(),
				CPActionKeys.VIEW_PRICE);
		}

		return _portletResourcePermission.contains(
			permissionChecker, commerceContext.getSiteGroupId(),
			CPActionKeys.VIEW_PRICE);
	}

	@Reference
	private CommerceDiscountCalculation _commerceDiscountCalculation;

	@Reference
	private CommerceMoneyFactory _commerceMoneyFactory;

	@Reference
	private CommerceTaxCalculation _commerceTaxCalculation;

	@Reference(target = "(resource.name=" + CPConstants.RESOURCE_NAME + ")")
	private PortletResourcePermission _portletResourcePermission;

}