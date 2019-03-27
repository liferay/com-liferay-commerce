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
import com.liferay.commerce.discount.model.CommerceDiscountUserSegmentRel;
import com.liferay.commerce.price.list.model.CommercePriceEntry;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.model.CommerceTierPriceEntry;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.user.segment.model.CommerceUserSegmentCriterion;
import com.liferay.commerce.user.segment.model.CommerceUserSegmentEntry;
import com.liferay.headless.commerce.admin.pricing.dto.v1_0.Discount;
import com.liferay.headless.commerce.admin.pricing.dto.v1_0.DiscountRule;
import com.liferay.headless.commerce.admin.pricing.dto.v1_0.PriceEntry;
import com.liferay.headless.commerce.admin.pricing.dto.v1_0.PriceList;
import com.liferay.headless.commerce.admin.pricing.dto.v1_0.TierPrice;
import com.liferay.headless.commerce.admin.pricing.dto.v1_0.UserSegment;
import com.liferay.headless.commerce.admin.pricing.dto.v1_0.UserSegmentCriterion;
import com.liferay.headless.commerce.core.util.LanguageUtils;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = DTOMapper.class)
public class DTOMapper {

	public UserSegment[] modelsToUserSegmentArray(
		List<CommerceDiscountUserSegmentRel> commerceDiscountUserSegmentRels) {

		if (commerceDiscountUserSegmentRels == null) {
			return null;
		}

		List<UserSegment> userSegments = new ArrayList<>();

		for (CommerceDiscountUserSegmentRel commerceDiscountUserSegmentRel :
				commerceDiscountUserSegmentRels) {

			userSegments.add(modelToDTO(commerceDiscountUserSegmentRel));
		}

		Stream<UserSegment> stream = userSegments.stream();

		return stream.toArray(UserSegment[]::new);
	}

	public UserSegmentCriterion[] modelsToUserSegmentCriterionArray(
		List<CommerceUserSegmentCriterion> commerceUserSegmentCriteria) {

		if (commerceUserSegmentCriteria == null) {
			return null;
		}

		List<UserSegmentCriterion> userSegmentCriteria = new ArrayList<>();

		for (CommerceUserSegmentCriterion commerceUserSegmentCriterion :
				commerceUserSegmentCriteria) {

			userSegmentCriteria.add(modelToDTO(commerceUserSegmentCriterion));
		}

		Stream<UserSegmentCriterion> stream = userSegmentCriteria.stream();

		return stream.toArray(UserSegmentCriterion[]::new);
	}

	public Discount modelToDTO(CommerceDiscount commerceDiscount) {
		Discount discount = new Discount();

		discount.setActive(commerceDiscount.isActive());
		discount.setCouponCode(commerceDiscount.getCouponCode());
		discount.setDisplayDate(commerceDiscount.getDisplayDate());
		discount.setExpirationDate(commerceDiscount.getExpirationDate());
		discount.setGroupId(commerceDiscount.getGroupId());
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
		discount.setUserSegments(
			modelsToUserSegmentArray(
				commerceDiscount.getCommerceDiscountUserSegmentRels()));

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

	public UserSegment modelToDTO(
		CommerceDiscountUserSegmentRel commerceDiscountUserSegmentRel) {

		try {
			return modelToDTO(
				commerceDiscountUserSegmentRel.getCommerceUserSegmentEntry());
		}
		catch (Exception e) {
			_log.error("Cannot instantiate UserSegment ", e);

			throw new RuntimeException(e);
		}
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
			priceList.setCommercePriceListId(
				commercePriceList.getCommercePriceListId());
			CommerceCurrency commerceCurrency =
				commercePriceList.getCommerceCurrency();

			priceList.setCurrency(commerceCurrency.getName(languageId));

			priceList.setDisplayDate(commercePriceList.getDisplayDate());
			priceList.setExpirationDate(commercePriceList.getExpirationDate());
			priceList.setExternalReferenceCode(
				commercePriceList.getExternalReferenceCode());
			priceList.setGroupId(commercePriceList.getGroupId());
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

	public UserSegmentCriterion modelToDTO(
		CommerceUserSegmentCriterion commerceUserSegmentCriterion) {

		UserSegmentCriterion userSegmentCriterion = new UserSegmentCriterion();

		if (commerceUserSegmentCriterion == null) {
			return userSegmentCriterion;
		}

		userSegmentCriterion.setCommerceUserSegmentEntryId(
			commerceUserSegmentCriterion.getCommerceUserSegmentEntryId());
		userSegmentCriterion.setId(
			commerceUserSegmentCriterion.getCommerceUserSegmentCriterionId());
		userSegmentCriterion.setPriority(
			commerceUserSegmentCriterion.getPriority());
		userSegmentCriterion.setType(commerceUserSegmentCriterion.getType());
		userSegmentCriterion.setTypeSettings(
			commerceUserSegmentCriterion.getTypeSettings());

		return userSegmentCriterion;
	}

	public UserSegment modelToDTO(
		CommerceUserSegmentEntry commerceUserSegmentEntry) {

		UserSegment userSegment = new UserSegment();

		if (commerceUserSegmentEntry == null) {
			return userSegment;
		}

		userSegment.setActive(commerceUserSegmentEntry.isActive());
		userSegment.setCriteria(
			modelsToUserSegmentCriterionArray(
				commerceUserSegmentEntry.getCommerceUserSegmentCriteria()));
		userSegment.setId(
			commerceUserSegmentEntry.getCommerceUserSegmentEntryId());
		userSegment.setKey(commerceUserSegmentEntry.getKey());
		userSegment.setName(
			LanguageUtils.getLanguageIdMap(
				commerceUserSegmentEntry.getNameMap()));
		userSegment.setPriority(commerceUserSegmentEntry.getPriority());
		userSegment.setSystem(commerceUserSegmentEntry.isSystem());

		return userSegment;
	}

	private static final Log _log = LogFactoryUtil.getLog(DTOMapper.class);

}