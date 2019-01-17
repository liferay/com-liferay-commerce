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
import com.liferay.commerce.currency.service.CommerceCurrencyLocalService;
import com.liferay.commerce.discount.CommerceDiscountCalculation;
import com.liferay.commerce.discount.CommerceDiscountValue;
import com.liferay.commerce.price.CommerceProductPrice;
import com.liferay.commerce.price.CommerceProductPriceCalculation;
import com.liferay.commerce.price.list.model.CommercePriceEntry;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.model.CommerceTierPriceEntry;
import com.liferay.commerce.price.list.service.CommercePriceEntryLocalService;
import com.liferay.commerce.price.list.service.CommerceTierPriceEntryLocalService;
import com.liferay.commerce.product.constants.CPActionKeys;
import com.liferay.commerce.product.constants.CPConstants;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPInstanceService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.util.List;
import java.util.Optional;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(service = CommerceProductPriceCalculation.class)
public class CommerceProductPriceCalculationImpl
	implements CommerceProductPriceCalculation {

	@Override
	public CommerceProductPrice getCommerceProductPrice(
			long cpInstanceId, int quantity, boolean secure,
			CommerceContext commerceContext)
		throws PortalException {

		if (secure && !_hasViewPricePermission(commerceContext)) {
			throw new PrincipalException();
		}

		CommerceMoney unitPrice = getUnitPrice(
			cpInstanceId, quantity, commerceContext.getCommercePriceList(),
			commerceContext.getCommerceCurrency(), secure, commerceContext);

		CommerceMoney promoPrice = getPromoPrice(
			cpInstanceId, quantity, commerceContext.getCommercePriceList(),
			commerceContext.getCommerceCurrency(), secure, commerceContext);

		CommerceProductPriceImpl commerceProductPrice =
			new CommerceProductPriceImpl();

		commerceProductPrice.setQuantity(quantity);
		commerceProductPrice.setUnitPrice(unitPrice);
		commerceProductPrice.setUnitPromoPrice(promoPrice);

		CommerceDiscountValue commerceDiscountValue =
			_commerceDiscountCalculation.getProductCommerceDiscountValue(
				cpInstanceId, quantity, unitPrice.getPrice(), commerceContext);

		BigDecimal finalPrice = unitPrice.getPrice();

		BigDecimal promo = promoPrice.getPrice();

		if ((promo.compareTo(BigDecimal.ZERO) > 0) &&
			(promo.compareTo(unitPrice.getPrice()) <= 0)) {

			finalPrice = promoPrice.getPrice();
		}

		finalPrice = finalPrice.multiply(BigDecimal.valueOf(quantity));

		if (commerceDiscountValue != null) {
			CommerceMoney discountAmount =
				commerceDiscountValue.getDiscountAmount();

			finalPrice = finalPrice.subtract(discountAmount.getPrice());
		}

		commerceProductPrice.setCommerceDiscountValue(commerceDiscountValue);
		commerceProductPrice.setFinalPrice(
			_commerceMoneyFactory.create(
				commerceContext.getCommerceCurrency(), finalPrice));

		return commerceProductPrice;
	}

	@Override
	public CommerceProductPrice getCommerceProductPrice(
			long cpInstanceId, int quantity, CommerceContext commerceContext)
		throws PortalException {

		return getCommerceProductPrice(
			cpInstanceId, quantity, true, commerceContext);
	}

	@Override
	public CommerceMoney getFinalPrice(
			long cpInstanceId, int quantity, boolean secure,
			CommerceContext commerceContext)
		throws PortalException {

		if (secure && !_hasViewPricePermission(commerceContext)) {
			throw new PrincipalException();
		}

		CommerceProductPrice commerceProductPrice = getCommerceProductPrice(
			cpInstanceId, quantity, commerceContext);

		if (commerceProductPrice == null) {
			return null;
		}

		return commerceProductPrice.getFinalPrice();
	}

	@Override
	public CommerceMoney getFinalPrice(
			long cpInstanceId, int quantity, CommerceContext commerceContext)
		throws PortalException {

		return getFinalPrice(cpInstanceId, quantity, true, commerceContext);
	}

	@Override
	public CommerceMoney getPromoPrice(
			long cpInstanceId, int quantity,
			Optional<CommercePriceList> commercePriceList,
			CommerceCurrency commerceCurrency, boolean secure,
			CommerceContext commerceContext)
		throws PortalException {

		if (secure && !_hasViewPricePermission(commerceContext)) {
			throw new PrincipalException();
		}

		CPInstance cpInstance = _cpInstanceService.getCPInstance(cpInstanceId);

		BigDecimal price = cpInstance.getPromoPrice();

		if (commercePriceList.isPresent()) {
			BigDecimal priceListPrice = _getPriceListPrice(
				cpInstanceId, quantity, commercePriceList.get(), true);

			if (priceListPrice != null) {
				price = priceListPrice;
			}
		}

		if ((commerceCurrency != null) && !commerceCurrency.isPrimary()) {
			price = price.multiply(commerceCurrency.getRate());
		}

		return _commerceMoneyFactory.create(commerceCurrency, price);
	}

	@Override
	public CommerceMoney getPromoPrice(
			long cpInstanceId, int quantity,
			Optional<CommercePriceList> commercePriceList,
			CommerceCurrency commerceCurrency, CommerceContext commerceContext)
		throws PortalException {

		return getPromoPrice(
			cpInstanceId, quantity, commercePriceList, commerceCurrency, true,
			commerceContext);
	}

	@Override
	public CommerceMoney getUnitMaxPrice(
			long cpDefinitionId, int quantity, boolean secure,
			CommerceContext commerceContext)
		throws PortalException {

		if (secure && !_hasViewPricePermission(commerceContext)) {
			throw new PrincipalException();
		}

		CommerceMoney commerceMoney = null;
		BigDecimal maxPrice = BigDecimal.ZERO;

		List<CPInstance> cpInstances =
			_cpInstanceService.getCPDefinitionInstances(
				cpDefinitionId, WorkflowConstants.STATUS_APPROVED,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		for (CPInstance cpInstance : cpInstances) {
			CommerceMoney cpInstanceCommerceMoney = getUnitPrice(
				cpInstance.getCPInstanceId(), quantity,
				commerceContext.getCommercePriceList(),
				commerceContext.getCommerceCurrency(), secure, commerceContext);

			if (maxPrice.compareTo(cpInstanceCommerceMoney.getPrice()) < 0) {
				commerceMoney = cpInstanceCommerceMoney;

				maxPrice = commerceMoney.getPrice();
			}
		}

		return commerceMoney;
	}

	@Override
	public CommerceMoney getUnitMaxPrice(
			long cpDefinitionId, int quantity, CommerceContext commerceContext)
		throws PortalException {

		return getUnitMaxPrice(cpDefinitionId, quantity, true, commerceContext);
	}

	@Override
	public CommerceMoney getUnitMinPrice(
			long cpDefinitionId, int quantity, boolean secure,
			CommerceContext commerceContext)
		throws PortalException {

		if (secure && !_hasViewPricePermission(commerceContext)) {
			throw new PrincipalException();
		}

		CommerceMoney commerceMoney = null;
		BigDecimal minPrice = BigDecimal.ZERO;

		List<CPInstance> cpInstances =
			_cpInstanceService.getCPDefinitionInstances(
				cpDefinitionId, WorkflowConstants.STATUS_APPROVED,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		for (CPInstance cpInstance : cpInstances) {
			CommerceMoney cpInstanceCommerceMoney = getUnitPrice(
				cpInstance.getCPInstanceId(), quantity,
				commerceContext.getCommercePriceList(),
				commerceContext.getCommerceCurrency(), secure, commerceContext);

			if ((commerceMoney == null) ||
				(minPrice.compareTo(cpInstanceCommerceMoney.getPrice()) > 0)) {

				commerceMoney = cpInstanceCommerceMoney;

				minPrice = commerceMoney.getPrice();
			}
		}

		return commerceMoney;
	}

	@Override
	public CommerceMoney getUnitMinPrice(
			long cpDefinitionId, int quantity, CommerceContext commerceContext)
		throws PortalException {

		return getUnitMinPrice(cpDefinitionId, quantity, true, commerceContext);
	}

	@Override
	public CommerceMoney getUnitPrice(
			long cpInstanceId, int quantity,
			Optional<CommercePriceList> commercePriceList,
			CommerceCurrency commerceCurrency, boolean secure,
			CommerceContext commerceContext)
		throws PortalException {

		if (secure && !_hasViewPricePermission(commerceContext)) {
			throw new PrincipalException();
		}

		CPInstance cpInstance = _cpInstanceService.getCPInstance(cpInstanceId);

		BigDecimal price = cpInstance.getPrice();

		if (commercePriceList.isPresent()) {
			BigDecimal priceListPrice = _getPriceListPrice(
				cpInstanceId, quantity, commercePriceList.get(), false);

			if (priceListPrice != null) {
				price = priceListPrice;
			}
		}

		if ((commerceCurrency != null) && !commerceCurrency.isPrimary()) {
			price = price.multiply(commerceCurrency.getRate());
		}

		return _commerceMoneyFactory.create(commerceCurrency, price);
	}

	@Override
	public CommerceMoney getUnitPrice(
			long cpInstanceId, int quantity,
			Optional<CommercePriceList> commercePriceList,
			CommerceCurrency commerceCurrency, CommerceContext commerceContext)
		throws PortalException {

		return getUnitPrice(
			cpInstanceId, quantity, commercePriceList, commerceCurrency, true,
			commerceContext);
	}

	private BigDecimal _getPriceListPrice(
			long cpInstanceId, int quantity,
			CommercePriceList commercePriceList, boolean promo)
		throws PortalException {

		BigDecimal price = null;

		CommercePriceEntry commercePriceEntry =
			_commercePriceEntryLocalService.fetchCommercePriceEntry(
				cpInstanceId, commercePriceList.getCommercePriceListId());

		if (commercePriceEntry != null) {
			if (promo) {
				price = commercePriceEntry.getPromoPrice();
			}
			else {
				price = commercePriceEntry.getPrice();
			}

			if (commercePriceEntry.isHasTierPrice()) {
				CommerceTierPriceEntry commerceTierPriceEntry =
					_commerceTierPriceEntryLocalService.
						findClosestCommerceTierPriceEntry(
							commercePriceEntry.getCommercePriceEntryId(),
							quantity);

				if (commerceTierPriceEntry != null) {
					if (promo) {
						price = commerceTierPriceEntry.getPromoPrice();
					}
					else {
						price = commerceTierPriceEntry.getPrice();
					}
				}
			}

			CommerceCurrency priceListCurrency =
				_commerceCurrencyLocalService.getCommerceCurrency(
					commercePriceList.getCommerceCurrencyId());

			if (!priceListCurrency.isPrimary()) {
				price = price.divide(
					priceListCurrency.getRate(),
					RoundingMode.valueOf(priceListCurrency.getRoundingMode()));
			}
		}

		return price;
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
	private CommerceCurrencyLocalService _commerceCurrencyLocalService;

	@Reference
	private CommerceDiscountCalculation _commerceDiscountCalculation;

	@Reference
	private CommerceMoneyFactory _commerceMoneyFactory;

	@Reference
	private CommercePriceEntryLocalService _commercePriceEntryLocalService;

	@Reference
	private CommerceTierPriceEntryLocalService
		_commerceTierPriceEntryLocalService;

	@Reference
	private CPInstanceService _cpInstanceService;

	@Reference(target = "(resource.name=" + CPConstants.RESOURCE_NAME + ")")
	private PortletResourcePermission _portletResourcePermission;

}