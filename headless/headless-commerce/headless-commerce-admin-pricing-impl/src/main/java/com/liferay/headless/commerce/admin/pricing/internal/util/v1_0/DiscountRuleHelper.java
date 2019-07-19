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

package com.liferay.headless.commerce.admin.pricing.internal.util.v1_0;

import com.liferay.commerce.discount.exception.NoSuchDiscountRuleException;
import com.liferay.commerce.discount.model.CommerceDiscount;
import com.liferay.commerce.discount.model.CommerceDiscountRule;
import com.liferay.commerce.discount.service.CommerceDiscountRuleService;
import com.liferay.commerce.discount.service.CommerceDiscountService;
import com.liferay.headless.commerce.admin.pricing.dto.v1_0.DiscountRule;
import com.liferay.headless.commerce.admin.pricing.internal.mapper.v1_0.DTOMapper;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 * @author Zoltán Takács
 */
@Component(immediate = true, service = DiscountRuleHelper.class)
public class DiscountRuleHelper {

	public void deleteDiscountRule(Long id) throws PortalException {
		_commerceDiscountRuleService.deleteCommerceDiscountRule(id);
	}

	public DiscountRule getDiscountRule(Long id) throws PortalException {
		return _dtoMapper.modelToDTO(
			_commerceDiscountRuleService.getCommerceDiscountRule(id));
	}

	public Page<DiscountRule> getDiscountRules(
			long commerceDiscountId, Pagination pagination)
		throws PortalException {

		List<CommerceDiscountRule> commerceDiscountRules =
			_commerceDiscountRuleService.getCommerceDiscountRules(
				commerceDiscountId, pagination.getStartPosition(),
				pagination.getEndPosition(), null);

		int count = _commerceDiscountRuleService.getCommerceDiscountRulesCount(
			commerceDiscountId);

		List<DiscountRule> discountRules = new ArrayList<>();

		for (CommerceDiscountRule commerceDiscountRule :
				commerceDiscountRules) {

			discountRules.add(_dtoMapper.modelToDTO(commerceDiscountRule));
		}

		return Page.of(discountRules, pagination, count);
	}

	public DiscountRule updateDiscountRule(Long id, DiscountRule discountRule)
		throws PortalException {

		return _dtoMapper.modelToDTO(_updateDiscountRule(id, discountRule));
	}

	public DiscountRule upsertDiscountRule(
			long commerceDiscountId, DiscountRule discountRule, User user)
		throws PortalException {

		try {
			CommerceDiscountRule commerceDiscountRule = _updateDiscountRule(
				discountRule.getId(), discountRule);

			return _dtoMapper.modelToDTO(commerceDiscountRule);
		}
		catch (NoSuchDiscountRuleException nsdre) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to find discountRule with ID: " +
						discountRule.getId());
			}
		}

		CommerceDiscount commerceDiscount =
			_commerceDiscountService.getCommerceDiscount(commerceDiscountId);

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			0L, new long[0], user, true);

		CommerceDiscountRule commerceDiscountRule =
			_commerceDiscountRuleService.addCommerceDiscountRule(
				commerceDiscount.getCommerceDiscountId(),
				discountRule.getType(), discountRule.getTypeSettings(),
				serviceContext);

		return _dtoMapper.modelToDTO(commerceDiscountRule);
	}

	private CommerceDiscountRule _updateDiscountRule(
			Long id, DiscountRule discountRule)
		throws PortalException {

		return _commerceDiscountRuleService.updateCommerceDiscountRule(
			id, discountRule.getType(), discountRule.getTypeSettings());
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