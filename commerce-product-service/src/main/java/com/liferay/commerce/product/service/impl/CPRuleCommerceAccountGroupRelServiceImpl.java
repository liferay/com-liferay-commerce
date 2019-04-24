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

package com.liferay.commerce.product.service.impl;

import com.liferay.commerce.product.constants.CPActionKeys;
import com.liferay.commerce.product.model.CPRule;
import com.liferay.commerce.product.model.CPRuleCommerceAccountGroupRel;
import com.liferay.commerce.product.service.base.CPRuleCommerceAccountGroupRelServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * @author Marco Leo
 */
public class CPRuleCommerceAccountGroupRelServiceImpl
	extends CPRuleCommerceAccountGroupRelServiceBaseImpl {

	@Override
	public CPRuleCommerceAccountGroupRel addCPRuleCommerceAccountGroupRel(
			long cpRuleId, long commerceAccountGroupId,
			ServiceContext serviceContext)
		throws PortalException {

		_cpRuleModelResourcePermission.check(
			getPermissionChecker(), cpRuleId,
			CPActionKeys.ADD_COMMERCE_PRODUCT_RULE_ACCOUNT_GROUP);

		return cpRuleCommerceAccountGroupRelLocalService.
			addCPRuleCommerceAccountGroupRel(
				cpRuleId, commerceAccountGroupId, serviceContext);
	}

	@Override
	public void deleteCPRuleCommerceAccountGroupRel(
			long cpRuleCommerceAccountGroupRelId)
		throws PortalException {

		CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel =
			cpRuleCommerceAccountGroupRelLocalService.
				getCPRuleCommerceAccountGroupRel(
					cpRuleCommerceAccountGroupRelId);

		_cpRuleModelResourcePermission.check(
			getPermissionChecker(), cpRuleCommerceAccountGroupRel.getCPRuleId(),
			CPActionKeys.DELETE_COMMERCE_PRODUCT_RULE_ACCOUNT_GROUP);

		cpRuleCommerceAccountGroupRelLocalService.
			deleteCPRuleCommerceAccountGroupRel(cpRuleCommerceAccountGroupRel);
	}

	@Override
	public List<CPRuleCommerceAccountGroupRel>
			getCPRuleCommerceAccountGroupRels(
				long cpRuleId, int start, int end,
				OrderByComparator<CPRuleCommerceAccountGroupRel>
					orderByComparator)
		throws PortalException {

		_cpRuleModelResourcePermission.check(
			getPermissionChecker(), cpRuleId, ActionKeys.VIEW);

		return cpRuleCommerceAccountGroupRelLocalService.
			getCPRuleCommerceAccountGroupRels(
				cpRuleId, start, end, orderByComparator);
	}

	@Override
	public int getCPRuleCommerceAccountGroupRelsCount(long cpRuleId)
		throws PortalException {

		_cpRuleModelResourcePermission.check(
			getPermissionChecker(), cpRuleId, ActionKeys.VIEW);

		return cpRuleCommerceAccountGroupRelLocalService.
			getCPRuleCommerceAccountGroupRelsCount(cpRuleId);
	}

	private static volatile ModelResourcePermission<CPRule>
		_cpRuleModelResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				CPRuleCommerceAccountGroupRelServiceImpl.class,
				"_cpRuleModelResourcePermission", CPRule.class);

}