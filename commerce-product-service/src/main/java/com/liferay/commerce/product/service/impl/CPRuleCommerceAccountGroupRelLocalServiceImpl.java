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

import com.liferay.commerce.product.model.CPRule;
import com.liferay.commerce.product.model.CPRuleCommerceAccountGroupRel;
import com.liferay.commerce.product.service.base.CPRuleCommerceAccountGroupRelLocalServiceBaseImpl;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * @author Marco Leo
 */
public class CPRuleCommerceAccountGroupRelLocalServiceImpl
	extends CPRuleCommerceAccountGroupRelLocalServiceBaseImpl {

	@Override
	public CPRuleCommerceAccountGroupRel addCPRuleCommerceAccountGroupRel(
			long cpRuleId, long commerceAccountGroupId,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(serviceContext.getUserId());
		long groupId = serviceContext.getScopeGroupId();

		long cpRuleCommerceAccountGroupRelId = counterLocalService.increment();

		CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel =
			cpRuleCommerceAccountGroupRelPersistence.create(
				cpRuleCommerceAccountGroupRelId);

		cpRuleCommerceAccountGroupRel.setGroupId(groupId);
		cpRuleCommerceAccountGroupRel.setCompanyId(user.getCompanyId());
		cpRuleCommerceAccountGroupRel.setUserId(user.getUserId());
		cpRuleCommerceAccountGroupRel.setUserName(user.getFullName());
		cpRuleCommerceAccountGroupRel.setCPRuleId(cpRuleId);
		cpRuleCommerceAccountGroupRel.setCommerceAccountGroupId(
			commerceAccountGroupId);

		cpRuleCommerceAccountGroupRelPersistence.update(
			cpRuleCommerceAccountGroupRel);

		// Commerce product rule

		reindexCPRule(cpRuleCommerceAccountGroupRel);

		// Cache

		cpRuleLocalService.cleanCPRulesCache(groupId);

		return cpRuleCommerceAccountGroupRel;
	}

	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public CPRuleCommerceAccountGroupRel deleteCPRuleCommerceAccountGroupRel(
			CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel)
		throws PortalException {

		cpRuleCommerceAccountGroupRelPersistence.remove(
			cpRuleCommerceAccountGroupRel);

		// Commerce product rule

		reindexCPRule(cpRuleCommerceAccountGroupRel);

		// Cache

		cpRuleLocalService.cleanCPRulesCache(
			cpRuleCommerceAccountGroupRel.getGroupId());

		return cpRuleCommerceAccountGroupRel;
	}

	@Override
	public CPRuleCommerceAccountGroupRel deleteCPRuleCommerceAccountGroupRel(
			long cpRuleCommerceAccountGroupRelId)
		throws PortalException {

		CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel =
			cpRuleCommerceAccountGroupRelPersistence.findByPrimaryKey(
				cpRuleCommerceAccountGroupRelId);

		return cpRuleCommerceAccountGroupRelLocalService.
			deleteCPRuleCommerceAccountGroupRel(cpRuleCommerceAccountGroupRel);
	}

	@Override
	public void deleteCPRuleCommerceAccountGroupRelsBycommerceAccountGroupId(
			long commerceAccountGroupId)
		throws PortalException {

		cpRuleCommerceAccountGroupRelPersistence.removeByCommerceAccountGroupId(
			commerceAccountGroupId);
	}

	@Override
	public void deleteCPRuleCommerceAccountGroupRelsByCPRuleId(long cpRuleId)
		throws PortalException {

		cpRuleCommerceAccountGroupRelPersistence.removeByCPRuleId(cpRuleId);
	}

	@Override
	public List<CPRuleCommerceAccountGroupRel>
		getCPRuleCommerceAccountGroupRels(long cpRuleId) {

		return cpRuleCommerceAccountGroupRelPersistence.findByCPRuleId(
			cpRuleId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	@Override
	public List<CPRuleCommerceAccountGroupRel>
		getCPRuleCommerceAccountGroupRels(
			long cpRuleId, int start, int end,
			OrderByComparator<CPRuleCommerceAccountGroupRel>
				orderByComparator) {

		return cpRuleCommerceAccountGroupRelPersistence.findByCPRuleId(
			cpRuleId, start, end, orderByComparator);
	}

	@Override
	public int getCPRuleCommerceAccountGroupRelsCount(long cpRuleId) {
		return cpRuleCommerceAccountGroupRelPersistence.countByCPRuleId(
			cpRuleId);
	}

	protected void reindexCPRule(
			CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel)
		throws PortalException {

		Indexer<CPRule> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			CPRule.class);

		indexer.reindex(
			CPRule.class.getName(),
			cpRuleCommerceAccountGroupRel.getCPRuleId());
	}

}