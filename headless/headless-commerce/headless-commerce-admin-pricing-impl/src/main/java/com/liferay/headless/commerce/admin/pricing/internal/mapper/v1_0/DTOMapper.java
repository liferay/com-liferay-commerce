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

package com.liferay.headless.commerce.admin.pricing.internal.mapper.v1_0;

import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.discount.model.CommerceDiscount;
import com.liferay.commerce.discount.model.CommerceDiscountRule;
import com.liferay.commerce.price.list.model.CommercePriceEntry;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.model.CommerceTierPriceEntry;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.CommerceCatalogLocalService;
import com.liferay.headless.commerce.admin.pricing.dto.v1_0.Discount;
import com.liferay.headless.commerce.admin.pricing.dto.v1_0.DiscountRule;
import com.liferay.headless.commerce.admin.pricing.dto.v1_0.PriceEntry;
import com.liferay.headless.commerce.admin.pricing.dto.v1_0.PriceList;
import com.liferay.headless.commerce.admin.pricing.dto.v1_0.TierPrice;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = DTOMapper.class)
public class DTOMapper {

	public Discount modelToDTO(CommerceDiscount commerceDiscount) {
		Discount discount = new Discount();

		discount.setActive(commerceDiscount.isActive());
		discount.setCouponCode(commerceDiscount.getCouponCode());
		discount.setDisplayDate(commerceDiscount.getDisplayDate());
		discount.setExpirationDate(commerceDiscount.getExpirationDate());
		discount.setId(commerceDiscount.getCommerceDiscountId());
		discount.setLimitationTimes(commerceDiscount.getLimitationTimes());
		discount.setLimitationType(commerceDiscount.getLimitationType());
		discount.setMaximumDiscountAmount(
			commerceDiscount.getMaximumDiscountAmount());
		discount.setNumberOfUse(commerceDiscount.getNumberOfUse());
		discount.setPercentageLevel1(commerceDiscount.getLevel1());
		discount.setPercentageLevel2(commerceDiscount.getLevel2());
		discount.setPercentageLevel3(commerceDiscount.getLevel3());
		discount.setPercentageLevel4(commerceDiscount.getLevel4());
		discount.setTarget(commerceDiscount.getTarget());
		discount.setTitle(commerceDiscount.getTitle());
		discount.setUseCouponCode(commerceDiscount.isUseCouponCode());
		discount.setUsePercentage(commerceDiscount.isUsePercentage());

		return discount;
	}

	public DiscountRule modelToDTO(CommerceDiscountRule commerceDiscountRule) {
		DiscountRule discountRule = new DiscountRule();

		discountRule.setDiscountId(
			commerceDiscountRule.getCommerceDiscountId());
		discountRule.setId(commerceDiscountRule.getCommerceDiscountRuleId());
		discountRule.setType(commerceDiscountRule.getType());
		discountRule.setTypeSettings(commerceDiscountRule.getTypeSettings());

		return discountRule;
	}

	public PriceEntry modelToDTO(CommercePriceEntry commercePriceEntry)
		throws PortalException {

		PriceEntry priceEntry = new PriceEntry();

		priceEntry.setCommercePriceListId(
			commercePriceEntry.getCommercePriceListId());
		priceEntry.setExternalReferenceCode(
			commercePriceEntry.getExternalReferenceCode());
		priceEntry.setHasTierPrice(commercePriceEntry.isHasTierPrice());
		priceEntry.setId(commercePriceEntry.getCommercePriceEntryId());
		priceEntry.setPrice(commercePriceEntry.getPrice());
		priceEntry.setPromoPrice(commercePriceEntry.getPromoPrice());

		CPInstance cpInstance = commercePriceEntry.getCPInstance();

		priceEntry.setSku(cpInstance.getSku());
		priceEntry.setSkuExternalReferenceCode(
			cpInstance.getExternalReferenceCode());

		return priceEntry;
	}

	public PriceList modelToDTO(
		CommercePriceList commercePriceList, String languageId) {

		PriceList priceList = new PriceList();

		try {
			priceList.setActive(!commercePriceList.isInactive());

			CommerceCatalog commerceCatalog =
				_commerceCatalogLocalService.fetchCommerceCatalogByGroupId(
					commercePriceList.getGroupId());

			long catalogId = 0;

			if (commerceCatalog != null) {
				commerceCatalog.getCommerceCatalogId();
			}

			priceList.setCatalogId(catalogId);

			priceList.setCommercePriceListId(
				commercePriceList.getCommercePriceListId());
			CommerceCurrency commerceCurrency =
				commercePriceList.getCommerceCurrency();

			priceList.setCurrency(commerceCurrency.getName(languageId));

			priceList.setDisplayDate(commercePriceList.getDisplayDate());
			priceList.setExpirationDate(commercePriceList.getExpirationDate());
			priceList.setExternalReferenceCode(
				commercePriceList.getExternalReferenceCode());
			priceList.setId(commercePriceList.getCommercePriceListId());
			priceList.setName(commercePriceList.getName());
			priceList.setPriority(commercePriceList.getPriority());
		}
		catch (Exception e) {
			_log.error("Cannot instantiate PriceList ", e);

			throw new RuntimeException(e);
		}

		return priceList;
	}

	public TierPrice modelToDTO(CommerceTierPriceEntry commerceTierPriceEntry)
		throws PortalException {

		TierPrice tierPrice = new TierPrice();

		tierPrice.setCommercePriceEntryId(
			commerceTierPriceEntry.getCommercePriceEntryId());
		tierPrice.setExternalReferenceCode(
			commerceTierPriceEntry.getExternalReferenceCode());
		tierPrice.setId(commerceTierPriceEntry.getCommercePriceEntryId());
		tierPrice.setMinimumQuantity(commerceTierPriceEntry.getMinQuantity());
		tierPrice.setPrice(commerceTierPriceEntry.getPrice());
		tierPrice.setPromoPrice(commerceTierPriceEntry.getPromoPrice());

		CommercePriceEntry commercePriceEntry =
			commerceTierPriceEntry.getCommercePriceEntry();

		tierPrice.setPriceEntryExternalReferenceCode(
			commercePriceEntry.getExternalReferenceCode());

		return tierPrice;
	}

	private static final Log _log = LogFactoryUtil.getLog(DTOMapper.class);

	@Reference
	private CommerceCatalogLocalService _commerceCatalogLocalService;

}