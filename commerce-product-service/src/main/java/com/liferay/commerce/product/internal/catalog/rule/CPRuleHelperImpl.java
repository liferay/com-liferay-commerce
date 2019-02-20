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

package com.liferay.commerce.product.internal.catalog.rule;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountService;
import com.liferay.commerce.product.catalog.rule.CPRuleHelper;
import com.liferay.commerce.product.model.CPRule;
import com.liferay.commerce.product.service.CPRuleLocalService;
import com.liferay.commerce.product.util.CPRulesThreadLocal;
import com.liferay.commerce.user.segment.util.CommerceUserSegmentHelper;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(service = CPRuleHelper.class)
public class CPRuleHelperImpl implements CPRuleHelper {

	public List<CPRule> initializeCPRules(
			long userId, long commerceAccountId, long groupId)
		throws PortalException {

		List<CPRule> cpRules = CPRulesThreadLocal.getCPRules();

		if (cpRules != null) {
			return cpRules;
		}

		CommerceAccount commerceAccount =
			_commerceAccountService.getCommerceAccount(commerceAccountId);

		long[] cpRuleIds = CPRuleCacheUtil.getCommerceAccountGroupCPRuleIds(
			commerceAccountId, groupId);

		if (cpRuleIds != null) {
			cpRules = _getCPRules(cpRuleIds);

			CPRulesThreadLocal.setCPRules(cpRules);

			return cpRules;
		}

		long[] commerceUserSegmentEntryIds =
			_commerceUserSegmentHelper.getCommerceUserSegmentIds(
				groupId, commerceAccount.getCommerceAccountId(), userId);

		cpRules = _cpRuleLocalService.getCPRules(
			groupId, commerceUserSegmentEntryIds);

		Stream<CPRule> stream = cpRules.stream();

		cpRuleIds = stream.mapToLong(
			CPRule::getCPRuleId
		).toArray();

		CPRuleCacheUtil.putCommerceAccountGroupCPRuleIds(
			commerceAccountId, groupId, cpRuleIds);

		cpRules = _getCPRules(cpRuleIds);

		CPRulesThreadLocal.setCPRules(cpRules);

		return cpRules;
	}

	@Deactivate
	protected void deactivate() {
		CPRuleCacheUtil.clearCommerceAccountGroupCPRuleIds();
	}

	private List<CPRule> _getCPRules(long[] cpRuleIds) throws PortalException {
		List<CPRule> cpRules = new ArrayList<>();

		for (long cpRuleId : cpRuleIds) {
			cpRules.add(_cpRuleLocalService.getCPRule(cpRuleId));
		}

		return cpRules;
	}

	@Reference
	private CommerceAccountService _commerceAccountService;

	@Reference
	private CommerceUserSegmentHelper _commerceUserSegmentHelper;

	@Reference
	private CPRuleLocalService _cpRuleLocalService;

}