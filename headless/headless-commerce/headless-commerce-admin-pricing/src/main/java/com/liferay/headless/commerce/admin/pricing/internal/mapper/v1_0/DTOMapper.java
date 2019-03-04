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
import com.liferay.commerce.openapi.core.util.LanguageUtils;
import com.liferay.commerce.price.list.model.CommercePriceEntry;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.model.CommerceTierPriceEntry;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.user.segment.model.CommerceUserSegmentCriterion;
import com.liferay.commerce.user.segment.model.CommerceUserSegmentEntry;
import com.liferay.headless.commerce.admin.pricing.model.v1_0.DiscountDTO;
import com.liferay.headless.commerce.admin.pricing.model.v1_0.DiscountRuleDTO;
import com.liferay.headless.commerce.admin.pricing.model.v1_0.PriceEntryDTO;
import com.liferay.headless.commerce.admin.pricing.model.v1_0.PriceListDTO;
import com.liferay.headless.commerce.admin.pricing.model.v1_0.TierPriceDTO;
import com.liferay.headless.commerce.admin.pricing.model.v1_0.UserSegmentCriterionDTO;
import com.liferay.headless.commerce.admin.pricing.model.v1_0.UserSegmentDTO;
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

	public UserSegmentCriterionDTO[] modelsToUserSegmentCriterionDTOArray(
		List<CommerceUserSegmentCriterion> commerceUserSegmentCriteria) {

		if (commerceUserSegmentCriteria == null) {
			return null;
		}

		List<UserSegmentCriterionDTO> userSegmentCriteria = new ArrayList<>();

		for (CommerceUserSegmentCriterion commerceUserSegmentCriterion :
				commerceUserSegmentCriteria) {

			userSegmentCriteria.add(modelToDTO(commerceUserSegmentCriterion));
		}

		Stream<UserSegmentCriterionDTO> stream = userSegmentCriteria.stream();

		return stream.toArray(UserSegmentCriterionDTO[]::new);
	}

	public UserSegmentDTO[] modelsToUserSegmentDTOArray(
		List<CommerceDiscountUserSegmentRel> commerceDiscountUserSegmentRels) {

		if (commerceDiscountUserSegmentRels == null) {
			return null;
		}

		List<UserSegmentDTO> userSegments = new ArrayList<>();

		for (CommerceDiscountUserSegmentRel commerceDiscountUserSegmentRel :
				commerceDiscountUserSegmentRels) {

			userSegments.add(modelToDTO(commerceDiscountUserSegmentRel));
		}

		Stream<UserSegmentDTO> stream = userSegments.stream();

		return stream.toArray(UserSegmentDTO[]::new);
	}

	public DiscountDTO modelToDTO(CommerceDiscount commerceDiscount) {
		DiscountDTO discountDTO = new DiscountDTO();

		discountDTO.setActive(commerceDiscount.isActive());
		discountDTO.setCouponCode(commerceDiscount.getCouponCode());
		discountDTO.setDisplayDate(commerceDiscount.getDisplayDate());
		discountDTO.setExpirationDate(commerceDiscount.getExpirationDate());
		discountDTO.setId(commerceDiscount.getCommerceDiscountId());
		discountDTO.setLimitationTimes(commerceDiscount.getLimitationTimes());
		discountDTO.setLimitationType(commerceDiscount.getLimitationType());
		discountDTO.setMaximumDiscountAmount(
			commerceDiscount.getMaximumDiscountAmount());
		discountDTO.setNumberOfUse(commerceDiscount.getNumberOfUse());
		discountDTO.setPercentageLevel1(commerceDiscount.getLevel1());
		discountDTO.setPercentageLevel2(commerceDiscount.getLevel2());
		discountDTO.setPercentageLevel3(commerceDiscount.getLevel3());
		discountDTO.setPercentageLevel4(commerceDiscount.getLevel4());
		discountDTO.setTarget(commerceDiscount.getTarget());
		discountDTO.setTitle(commerceDiscount.getTitle());
		discountDTO.setUseCouponCode(commerceDiscount.isUseCouponCode());
		discountDTO.setUsePercentage(commerceDiscount.isUsePercentage());
		discountDTO.setUserSegments(
			modelsToUserSegmentDTOArray(
				commerceDiscount.getCommerceDiscountUserSegmentRels()));

		return discountDTO;
	}

	public DiscountRuleDTO modelToDTO(
		CommerceDiscountRule commerceDiscountRule) {

		DiscountRuleDTO discountRuleDTO = new DiscountRuleDTO();

		discountRuleDTO.setDiscountId(
			commerceDiscountRule.getCommerceDiscountId());
		discountRuleDTO.setId(commerceDiscountRule.getCommerceDiscountRuleId());
		discountRuleDTO.setType(commerceDiscountRule.getType());
		discountRuleDTO.setTypeSettings(commerceDiscountRule.getTypeSettings());

		return discountRuleDTO;
	}

	public UserSegmentDTO modelToDTO(
		CommerceDiscountUserSegmentRel commerceDiscountUserSegmentRel) {

		try {
			return modelToDTO(
				commerceDiscountUserSegmentRel.getCommerceUserSegmentEntry());
		}
		catch (Exception e) {
			_log.error("Cannot instantiate UserSegmentDTO ", e);

			throw new RuntimeException(e);
		}
	}

	public PriceEntryDTO modelToDTO(CommercePriceEntry commercePriceEntry)
		throws PortalException {

		PriceEntryDTO priceEntryDTO = new PriceEntryDTO();

		priceEntryDTO.setCommercePriceListId(
			commercePriceEntry.getCommercePriceListId());
		priceEntryDTO.setExternalReferenceCode(
			commercePriceEntry.getExternalReferenceCode());
		priceEntryDTO.setHasTierPrice(commercePriceEntry.isHasTierPrice());
		priceEntryDTO.setId(commercePriceEntry.getCommercePriceEntryId());
		priceEntryDTO.setPrice(commercePriceEntry.getPrice());
		priceEntryDTO.setPromoPrice(commercePriceEntry.getPromoPrice());

		CPInstance cpInstance = commercePriceEntry.getCPInstance();

		priceEntryDTO.setSku(cpInstance.getSku());
		priceEntryDTO.setSkuExternalReferenceCode(
			cpInstance.getExternalReferenceCode());

		return priceEntryDTO;
	}

	public PriceListDTO modelToDTO(
		CommercePriceList commercePriceList, String languageId) {

		PriceListDTO priceListDTO = new PriceListDTO();

		try {
			priceListDTO.setActive(!commercePriceList.isInactive());
			priceListDTO.setCommercePriceListId(
				commercePriceList.getCommercePriceListId());
			CommerceCurrency commerceCurrency =
				commercePriceList.getCommerceCurrency();

			priceListDTO.setCurrency(commerceCurrency.getName(languageId));

			priceListDTO.setDisplayDate(commercePriceList.getDisplayDate());
			priceListDTO.setExpirationDate(
				commercePriceList.getExpirationDate());
			priceListDTO.setExternalReferenceCode(
				commercePriceList.getExternalReferenceCode());
			priceListDTO.setId(commercePriceList.getCommercePriceListId());
			priceListDTO.setName(commercePriceList.getName());
			priceListDTO.setPriority(commercePriceList.getPriority());
		}
		catch (Exception e) {
			_log.error("Cannot instantiate PriceListDTO ", e);

			throw new RuntimeException(e);
		}

		return priceListDTO;
	}

	public TierPriceDTO modelToDTO(
			CommerceTierPriceEntry commerceTierPriceEntry)
		throws PortalException {

		TierPriceDTO tierPriceDTO = new TierPriceDTO();

		tierPriceDTO.setCommercePriceEntryId(
			commerceTierPriceEntry.getCommercePriceEntryId());
		tierPriceDTO.setExternalReferenceCode(
			commerceTierPriceEntry.getExternalReferenceCode());
		tierPriceDTO.setId(commerceTierPriceEntry.getCommercePriceEntryId());
		tierPriceDTO.setMinimumQuantity(
			commerceTierPriceEntry.getMinQuantity());
		tierPriceDTO.setPrice(commerceTierPriceEntry.getPrice());
		tierPriceDTO.setPromoPrice(commerceTierPriceEntry.getPromoPrice());

		CommercePriceEntry commercePriceEntry =
			commerceTierPriceEntry.getCommercePriceEntry();

		tierPriceDTO.setPriceEntryExternalReferenceCode(
			commercePriceEntry.getExternalReferenceCode());

		return tierPriceDTO;
	}

	public UserSegmentCriterionDTO modelToDTO(
		CommerceUserSegmentCriterion commerceUserSegmentCriterion) {

		UserSegmentCriterionDTO userSegmentCriterionDTO =
			new UserSegmentCriterionDTO();

		if (commerceUserSegmentCriterion == null) {
			return userSegmentCriterionDTO;
		}

		userSegmentCriterionDTO.setCommerceUserSegmentEntryId(
			commerceUserSegmentCriterion.getCommerceUserSegmentEntryId());
		userSegmentCriterionDTO.setId(
			commerceUserSegmentCriterion.getCommerceUserSegmentCriterionId());
		userSegmentCriterionDTO.setPriority(
			commerceUserSegmentCriterion.getPriority());
		userSegmentCriterionDTO.setType(commerceUserSegmentCriterion.getType());
		userSegmentCriterionDTO.setTypeSettings(
			commerceUserSegmentCriterion.getTypeSettings());

		return userSegmentCriterionDTO;
	}

	public UserSegmentDTO modelToDTO(
		CommerceUserSegmentEntry commerceUserSegmentEntry) {

		UserSegmentDTO userSegmentDTO = new UserSegmentDTO();

		if (commerceUserSegmentEntry == null) {
			return userSegmentDTO;
		}

		userSegmentDTO.setActive(commerceUserSegmentEntry.isActive());
		userSegmentDTO.setCriteria(
			modelsToUserSegmentCriterionDTOArray(
				commerceUserSegmentEntry.getCommerceUserSegmentCriteria()));
		userSegmentDTO.setId(
			commerceUserSegmentEntry.getCommerceUserSegmentEntryId());
		userSegmentDTO.setKey(commerceUserSegmentEntry.getKey());
		userSegmentDTO.setName(
			LanguageUtils.getLanguageIdMap(
				commerceUserSegmentEntry.getNameMap()));
		userSegmentDTO.setPriority(commerceUserSegmentEntry.getPriority());
		userSegmentDTO.setSystem(commerceUserSegmentEntry.isSystem());

		return userSegmentDTO;
	}

	private static final Log _log = LogFactoryUtil.getLog(DTOMapper.class);

}