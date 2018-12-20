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

package com.liferay.commerce.internal.context;

import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.service.CommerceCurrencyLocalService;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.organization.service.CommerceOrganizationService;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.CommercePriceListLocalService;
import com.liferay.commerce.product.model.CPRule;
import com.liferay.commerce.product.service.CPRuleLocalService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.commerce.user.segment.util.CommerceUserSegmentHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Organization;

import java.util.List;
import java.util.Optional;

/**
 * @author Marco Leo
 */
public class CommerceContextImpl implements CommerceContext {

	public CommerceContextImpl(
		long groupId, long userId, long orderId, long organizationId,
		String couponCode,
		CommerceCurrencyLocalService commerceCurrencyLocalService,
		CommerceOrderService commerceOrderService,
		CommerceOrganizationService commerceOrganizationService,
		CommercePriceListLocalService commercePriceListLocalService,
		CommerceUserSegmentHelper commerceUserSegmentHelper,
		CPRuleLocalService cpRuleLocalService) {

		_groupId = groupId;
		_userId = userId;
		_orderId = orderId;
		_organizationId = organizationId;
		_couponCode = couponCode;
		_commerceCurrencyLocalService = commerceCurrencyLocalService;
		_commerceOrderService = commerceOrderService;
		_commerceOrganizationService = commerceOrganizationService;
		_commercePriceListLocalService = commercePriceListLocalService;
		_commerceUserSegmentHelper = commerceUserSegmentHelper;
		_cpRuleLocalService = cpRuleLocalService;
	}

	@Override
	public CommerceCurrency getCommerceCurrency() throws PortalException {
		if (_commerceCurrency != null) {
			return _commerceCurrency;
		}

		_commerceCurrency =
			_commerceCurrencyLocalService.fetchPrimaryCommerceCurrency(
				_groupId);

		return _commerceCurrency;
	}

	@Override
	public CommerceOrder getCommerceOrder() throws PortalException {
		if (_commerceOrder != null) {
			return _commerceOrder;
		}

		_commerceOrder = _commerceOrderService.getCommerceOrder(_orderId);

		return _commerceOrder;
	}

	@Override
	public Optional<CommercePriceList> getCommercePriceList()
		throws PortalException {

		if (_commercePriceList != null) {
			return _commercePriceList;
		}

		_commercePriceList =
			_commercePriceListLocalService.getCommercePriceList(
				_groupId, getCommerceUserSegmentEntryIds());

		return _commercePriceList;
	}

	@Override
	public long[] getCommerceUserSegmentEntryIds() throws PortalException {
		if (_commerceUserSegmentEntryIds != null) {
			return _commerceUserSegmentEntryIds;
		}

		_commerceUserSegmentEntryIds =
			_commerceUserSegmentHelper.getCommerceUserSegmentIds(
				_groupId, _organizationId, _userId);

		return _commerceUserSegmentEntryIds;
	}

	@Override
	public String getCouponCode() throws PortalException {
		return _couponCode;
	}

	@Override
	public List<CPRule> getCPRules() throws PortalException {
		if (_cpRules != null) {
			return _cpRules;
		}

		_cpRules = _cpRuleLocalService.getCPRules(
			_groupId, getCommerceUserSegmentEntryIds());

		return null;
	}

	@Override
	public Organization getOrganization() throws PortalException {
		if (_organization != null) {
			return _organization;
		}

		_organization = _commerceOrganizationService.getOrganization(
			_organizationId);

		return _organization;
	}

	@Override
	public long getSiteGroupId() throws PortalException {
		return _groupId;
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	private CommerceCurrency _commerceCurrency;
	private final CommerceCurrencyLocalService _commerceCurrencyLocalService;
	private CommerceOrder _commerceOrder;
	private final CommerceOrderService _commerceOrderService;
	private final CommerceOrganizationService _commerceOrganizationService;
	private Optional<CommercePriceList> _commercePriceList;
	private final CommercePriceListLocalService _commercePriceListLocalService;
	private long[] _commerceUserSegmentEntryIds;
	private final CommerceUserSegmentHelper _commerceUserSegmentHelper;
	private final String _couponCode;
	private final CPRuleLocalService _cpRuleLocalService;
	private List<CPRule> _cpRules;
	private final long _groupId;
	private final long _orderId;
	private Organization _organization;
	private final long _organizationId;
	private final long _userId;

}