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

package com.liferay.commerce.service.impl;

import com.liferay.commerce.internal.notification.type.SubscriptionRenewedCommerceNotificationTypeImpl;
import com.liferay.commerce.model.CPSubscriptionCycleEntry;
import com.liferay.commerce.model.CPSubscriptionEntry;
import com.liferay.commerce.notification.util.CommerceNotificationHelper;
import com.liferay.commerce.service.base.CPSubscriptionCycleEntryLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.List;

/**
 * @author Alessio Antonio Rendina
 */
public class CPSubscriptionCycleEntryLocalServiceImpl
	extends CPSubscriptionCycleEntryLocalServiceBaseImpl {

	@Override
	public CPSubscriptionCycleEntry addCPSubscriptionCycleEntry(
			long cpSubscriptionEntryId, long commerceOrderItemId, boolean renew)
		throws PortalException {

		CPSubscriptionEntry cpSubscriptionEntry =
			cpSubscriptionEntryLocalService.getCPSubscriptionEntry(
				cpSubscriptionEntryId);

		long cpSubscriptionCycleEntryId = counterLocalService.increment();

		CPSubscriptionCycleEntry cpSubscriptionCycleEntry =
			cpSubscriptionCycleEntryPersistence.create(
				cpSubscriptionCycleEntryId);

		cpSubscriptionCycleEntry.setGroupId(cpSubscriptionEntry.getGroupId());
		cpSubscriptionCycleEntry.setCompanyId(
			cpSubscriptionEntry.getCompanyId());
		cpSubscriptionCycleEntry.setUserId(cpSubscriptionEntry.getUserId());
		cpSubscriptionCycleEntry.setUserName(cpSubscriptionEntry.getUserName());
		cpSubscriptionCycleEntry.setCPSubscriptionEntryId(
			cpSubscriptionEntryId);
		cpSubscriptionCycleEntry.setCommerceOrderItemId(commerceOrderItemId);
		cpSubscriptionCycleEntry.setRenew(renew);

		cpSubscriptionCycleEntryPersistence.update(cpSubscriptionCycleEntry);

		// Send commerce notifications

		_commerceNotificationHelper.sendNotifications(
			cpSubscriptionCycleEntry.getGroupId(),
			SubscriptionRenewedCommerceNotificationTypeImpl.KEY,
			cpSubscriptionCycleEntry);

		// Commerce product subscription entry

		reindexCPSubscriptionEntry(cpSubscriptionEntryId);

		return cpSubscriptionCycleEntry;
	}

	@Override
	public CPSubscriptionCycleEntry
		fetchCPCpSubscriptionCycleEntryByCommerceOrderItemId(
			long commerceOrderItemId) {

		return cpSubscriptionCycleEntryPersistence.fetchByCommerceOrderItemId(
			commerceOrderItemId);
	}

	@Override
	public List<CPSubscriptionCycleEntry> getCPSubscriptionCycleEntries(
		long cpSubscriptionEntryId, int start, int end,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator) {

		return cpSubscriptionCycleEntryPersistence.findByCPSubscriptionEntryId(
			cpSubscriptionEntryId, start, end, orderByComparator);
	}

	@Override
	public int getCPSubscriptionCycleEntriesCount(long cpSubscriptionEntryId) {
		return cpSubscriptionCycleEntryPersistence.countByCPSubscriptionEntryId(
			cpSubscriptionEntryId);
	}

	protected void reindexCPSubscriptionEntry(long cpSubscriptionEntryId)
		throws PortalException {

		Indexer<CPSubscriptionEntry> indexer =
			IndexerRegistryUtil.nullSafeGetIndexer(CPSubscriptionEntry.class);

		indexer.reindex(
			CPSubscriptionEntry.class.getName(), cpSubscriptionEntryId);
	}

	@ServiceReference(type = CommerceNotificationHelper.class)
	private CommerceNotificationHelper _commerceNotificationHelper;

}