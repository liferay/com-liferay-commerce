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

package com.liferay.commerce.discount.service.impl;

import com.liferay.commerce.discount.exception.CommerceDiscountRuleTypeException;
import com.liferay.commerce.discount.model.CommerceDiscount;
import com.liferay.commerce.discount.model.CommerceDiscountRule;
import com.liferay.commerce.discount.rule.type.CommerceDiscountRuleType;
import com.liferay.commerce.discount.rule.type.CommerceDiscountRuleTypeRegistry;
import com.liferay.commerce.discount.service.base.CommerceDiscountRuleLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.List;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class CommerceDiscountRuleLocalServiceImpl
	extends CommerceDiscountRuleLocalServiceBaseImpl {

	@Override
	public CommerceDiscountRule addCommerceDiscountRule(
			long commerceDiscountId, String type, String typeSettings,
			ServiceContext serviceContext)
		throws PortalException {

		// Commerce discount rule

		User user = userLocalService.getUser(serviceContext.getUserId());

		validate(type);

		long commerceDiscountRuleId = counterLocalService.increment();

		CommerceDiscountRule commerceDiscountRule =
			commerceDiscountRulePersistence.create(commerceDiscountRuleId);

		commerceDiscountRule.setCompanyId(user.getCompanyId());
		commerceDiscountRule.setUserId(user.getUserId());
		commerceDiscountRule.setUserName(user.getFullName());
		commerceDiscountRule.setCommerceDiscountId(commerceDiscountId);
		commerceDiscountRule.setType(type);

		UnicodeProperties settingsProperties =
			commerceDiscountRule.getSettingsProperties();

		settingsProperties.put(type, typeSettings);

		commerceDiscountRule.setSettingsProperties(settingsProperties);

		commerceDiscountRulePersistence.update(commerceDiscountRule);

		// Commerce discount

		reindexCommerceDiscount(commerceDiscountId);

		return commerceDiscountRule;
	}

	@Override
	public CommerceDiscountRule deleteCommerceDiscountRule(
			CommerceDiscountRule commerceDiscountRule)
		throws PortalException {

		// Commerce discount rule

		commerceDiscountRulePersistence.remove(commerceDiscountRule);

		// Commerce discount

		reindexCommerceDiscount(commerceDiscountRule.getCommerceDiscountId());

		return commerceDiscountRule;
	}

	@Override
	public CommerceDiscountRule deleteCommerceDiscountRule(
			long commerceDiscountRuleId)
		throws PortalException {

		CommerceDiscountRule commerceDiscountRule =
			commerceDiscountRulePersistence.findByPrimaryKey(
				commerceDiscountRuleId);

		return commerceDiscountRuleLocalService.deleteCommerceDiscountRule(
			commerceDiscountRule);
	}

	@Override
	public void deleteCommerceDiscountRules(long commerceDiscountId)
		throws PortalException {

		List<CommerceDiscountRule> commerceDiscountRules =
			commerceDiscountRulePersistence.findByCommerceDiscountId(
				commerceDiscountId);

		for (CommerceDiscountRule commerceDiscountRule :
				commerceDiscountRules) {

			commerceDiscountRuleLocalService.deleteCommerceDiscountRule(
				commerceDiscountRule);
		}
	}

	@Override
	public List<CommerceDiscountRule> getCommerceDiscountRules(
		long commerceDiscountId, int start, int end,
		OrderByComparator<CommerceDiscountRule> orderByComparator) {

		return commerceDiscountRulePersistence.findByCommerceDiscountId(
			commerceDiscountId, start, end, orderByComparator);
	}

	@Override
	public int getCommerceDiscountRulesCount(long commerceDiscountId) {
		return commerceDiscountRulePersistence.countByCommerceDiscountId(
			commerceDiscountId);
	}

	@Override
	public CommerceDiscountRule updateCommerceDiscountRule(
			long commerceDiscountRuleId, String type, String typeSettings)
		throws PortalException {

		// Commerce discount rule

		CommerceDiscountRule commerceDiscountRule =
			commerceDiscountRulePersistence.findByPrimaryKey(
				commerceDiscountRuleId);

		validate(type);

		commerceDiscountRule.setType(type);

		UnicodeProperties settingsProperties =
			commerceDiscountRule.getSettingsProperties();

		settingsProperties.put(type, typeSettings);

		commerceDiscountRule.setSettingsProperties(settingsProperties);

		commerceDiscountRulePersistence.update(commerceDiscountRule);

		// Commerce discount

		reindexCommerceDiscount(commerceDiscountRule.getCommerceDiscountId());

		return commerceDiscountRule;
	}

	protected void reindexCommerceDiscount(long commerceDiscountId)
		throws PortalException {

		CommerceDiscount commerceDiscount =
			commerceDiscountLocalService.getCommerceDiscount(
				commerceDiscountId);

		Indexer<CommerceDiscount> indexer =
			IndexerRegistryUtil.nullSafeGetIndexer(CommerceDiscount.class);

		indexer.reindex(commerceDiscount);
	}

	protected void validate(String type) throws PortalException {
		CommerceDiscountRuleType commerceDiscountRuleType =
			_commerceDiscountRuleTypeRegistry.getCommerceDiscountRuleType(type);

		if (commerceDiscountRuleType == null) {
			throw new CommerceDiscountRuleTypeException();
		}
	}

	@ServiceReference(type = CommerceDiscountRuleTypeRegistry.class)
	private CommerceDiscountRuleTypeRegistry _commerceDiscountRuleTypeRegistry;

}