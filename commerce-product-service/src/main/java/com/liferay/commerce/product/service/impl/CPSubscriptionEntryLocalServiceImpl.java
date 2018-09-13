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

import com.liferay.commerce.product.exception.CPSubscriptionCPInstanceIdException;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CPSubscriptionEntry;
import com.liferay.commerce.product.service.base.CPSubscriptionEntryLocalServiceBaseImpl;
import com.liferay.commerce.product.util.CPSubscriptionUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.Date;
import java.util.List;

/**
 * @author Alessio Antonio Rendina
 */
public class CPSubscriptionEntryLocalServiceImpl
	extends CPSubscriptionEntryLocalServiceBaseImpl {

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPSubscriptionEntry addCPSubscriptionEntry(
			long cpInstanceId, long commerceOrderItemId,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(serviceContext.getUserId());
		long groupId = serviceContext.getScopeGroupId();

		long subscriptionCycleLength;
		String subscriptionCyclePeriod;
		long maxSubscriptionCyclesNumber;

		CPInstance cpInstance = cpInstanceLocalService.getCPInstance(
			cpInstanceId);

		if (!cpInstance.isSubscriptionEnabled()) {
			CPDefinition cpDefinition = cpInstance.getCPDefinition();

			if (!cpDefinition.isSubscriptionEnabled()) {
				throw new CPSubscriptionCPInstanceIdException();
			}
			else {
				subscriptionCycleLength =
					cpDefinition.getSubscriptionCycleLength();
				subscriptionCyclePeriod =
					cpDefinition.getSubscriptionCyclePeriod();
				maxSubscriptionCyclesNumber =
					cpDefinition.getMaxSubscriptionCyclesNumber();
			}
		}
		else {
			subscriptionCycleLength = cpInstance.getSubscriptionCycleLength();
			subscriptionCyclePeriod = cpInstance.getSubscriptionCyclePeriod();
			maxSubscriptionCyclesNumber =
				cpInstance.getMaxSubscriptionCyclesNumber();
		}

		long cpSubscriptionEntryId = counterLocalService.increment();

		CPSubscriptionEntry cpSubscriptionEntry =
			cpSubscriptionEntryPersistence.create(cpSubscriptionEntryId);

		cpSubscriptionEntry.setUuid(serviceContext.getUuid());
		cpSubscriptionEntry.setGroupId(groupId);
		cpSubscriptionEntry.setCompanyId(user.getCompanyId());
		cpSubscriptionEntry.setUserId(user.getUserId());
		cpSubscriptionEntry.setUserName(user.getFullName());
		cpSubscriptionEntry.setCPInstanceId(cpInstanceId);
		cpSubscriptionEntry.setCommerceOrderItemId(commerceOrderItemId);
		cpSubscriptionEntry.setSubscriptionCycleLength(subscriptionCycleLength);
		cpSubscriptionEntry.setSubscriptionCyclePeriod(subscriptionCyclePeriod);
		cpSubscriptionEntry.setMaxSubscriptionCyclesNumber(
			maxSubscriptionCyclesNumber);
		cpSubscriptionEntry.setActive(true);

		Date subscriptionNextIterationDate =
			CPSubscriptionUtil.getSubscriptionNextIterationDate(
				user.getUserId(), subscriptionCycleLength,
				subscriptionCyclePeriod);

		cpSubscriptionEntry.setNextIterationDate(subscriptionNextIterationDate);

		cpSubscriptionEntryPersistence.update(cpSubscriptionEntry);

		return cpSubscriptionEntry;
	}

	@Override
	public List<CPSubscriptionEntry> getCPSubscriptionEntries(
		long groupId, long userId, int start, int end,
		OrderByComparator<CPSubscriptionEntry> orderByComparator) {

		return cpSubscriptionEntryPersistence.findByG_U(
			groupId, userId, start, end, orderByComparator);
	}

	@Override
	public int getCPSubscriptionEntriesCount(long groupId, long userId) {
		return cpSubscriptionEntryPersistence.countByG_U(groupId, userId);
	}

}