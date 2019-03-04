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

package com.liferay.headless.commerce.admin.pricing.internal.resource.util.v1_0;

import com.liferay.commerce.discount.exception.NoSuchDiscountRuleException;
import com.liferay.commerce.discount.model.CommerceDiscount;
import com.liferay.commerce.discount.model.CommerceDiscountRule;
import com.liferay.commerce.discount.service.CommerceDiscountRuleService;
import com.liferay.commerce.discount.service.CommerceDiscountService;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.commerce.openapi.core.util.ServiceContextHelper;
import com.liferay.headless.commerce.admin.pricing.internal.mapper.v1_0.DTOMapper;
import com.liferay.headless.commerce.admin.pricing.model.v1_0.DiscountRuleDTO;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = DiscountRuleHelper.class)
public class DiscountRuleHelper {

	public void deleteDiscountRule(String id) throws PortalException {
		_commerceDiscountRuleService.deleteCommerceDiscountRule(
			GetterUtil.getLong(id));
	}

	public DiscountRuleDTO getDiscountRuleDTO(String id)
		throws PortalException {

		CommerceDiscountRule commerceDiscountRule =
			_commerceDiscountRuleService.getCommerceDiscountRule(
				GetterUtil.getLong(id));

		return _dtoMapper.modelToDTO(commerceDiscountRule);
	}

	public CollectionDTO<DiscountRuleDTO> getDiscountRuleDTOs(
			long commerceDiscountId, Pagination pagination)
		throws PortalException {

		List<CommerceDiscountRule> commerceDiscountRules =
			_commerceDiscountRuleService.getCommerceDiscountRules(
				commerceDiscountId, pagination.getStartPosition(),
				pagination.getEndPosition(), null);

		int count = _commerceDiscountRuleService.getCommerceDiscountRulesCount(
			commerceDiscountId);

		List<DiscountRuleDTO> discountRuleDTOs = new ArrayList<>();

		for (CommerceDiscountRule commerceDiscountRule :
				commerceDiscountRules) {

			discountRuleDTOs.add(_dtoMapper.modelToDTO(commerceDiscountRule));
		}

		return new CollectionDTO<>(discountRuleDTOs, count);
	}

	public DiscountRuleDTO updateDiscountRule(
			String id, DiscountRuleDTO discountRuleDTO)
		throws PortalException {

		return _dtoMapper.modelToDTO(_updateDiscountRule(id, discountRuleDTO));
	}

	public DiscountRuleDTO upsertDiscountRule(
			long commerceDiscountId, DiscountRuleDTO discountRuleDTO, User user)
		throws PortalException {

		try {
			CommerceDiscountRule commerceDiscountRule = _updateDiscountRule(
				String.valueOf(discountRuleDTO.getId()), discountRuleDTO);

			return _dtoMapper.modelToDTO(commerceDiscountRule);
		}
		catch (NoSuchDiscountRuleException nsdre) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to find discountRule with ID: " +
						discountRuleDTO.getId());
			}
		}

		CommerceDiscount commerceDiscount =
			_commerceDiscountService.getCommerceDiscount(commerceDiscountId);

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			commerceDiscount.getGroupId(), new long[0], user, true);

		CommerceDiscountRule commerceDiscountRule =
			_commerceDiscountRuleService.addCommerceDiscountRule(
				commerceDiscount.getCommerceDiscountId(),
				discountRuleDTO.getType(), discountRuleDTO.getTypeSettings(),
				serviceContext);

		return _dtoMapper.modelToDTO(commerceDiscountRule);
	}

	private CommerceDiscountRule _updateDiscountRule(
			String id, DiscountRuleDTO discountRuleDTO)
		throws PortalException {

		return _commerceDiscountRuleService.updateCommerceDiscountRule(
			GetterUtil.getLong(id), discountRuleDTO.getType(),
			discountRuleDTO.getTypeSettings());
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DiscountRuleHelper.class);

	@Reference
	private CommerceDiscountRuleService _commerceDiscountRuleService;

	@Reference
	private CommerceDiscountService _commerceDiscountService;

	@Reference
	private DTOMapper _dtoMapper;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}