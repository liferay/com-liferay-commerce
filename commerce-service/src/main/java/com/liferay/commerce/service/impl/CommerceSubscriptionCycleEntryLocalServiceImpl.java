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
import com.liferay.commerce.model.CommerceSubscriptionCycleEntry;
import com.liferay.commerce.model.CommerceSubscriptionEntry;
import com.liferay.commerce.notification.util.CommerceNotificationHelper;
import com.liferay.commerce.service.base.CommerceSubscriptionCycleEntryLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.List;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceSubscriptionCycleEntryLocalServiceImpl
	extends CommerceSubscriptionCycleEntryLocalServiceBaseImpl {

	@Override
	public CommerceSubscriptionCycleEntry addCommerceSubscriptionCycleEntry(
			long commerceSubscriptionEntryId, long commerceOrderItemId,
			boolean renew)
		throws PortalException {

		CommerceSubscriptionEntry commerceSubscriptionEntry =
			commerceSubscriptionEntryLocalService.getCommerceSubscriptionEntry(
				commerceSubscriptionEntryId);

		long commerceSubscriptionCycleEntryId = counterLocalService.increment();

		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry =
			commerceSubscriptionCycleEntryPersistence.create(
				commerceSubscriptionCycleEntryId);

		commerceSubscriptionCycleEntry.setGroupId(
			commerceSubscriptionEntry.getGroupId());
		commerceSubscriptionCycleEntry.setCompanyId(
			commerceSubscriptionEntry.getCompanyId());
		commerceSubscriptionCycleEntry.setUserId(
			commerceSubscriptionEntry.getUserId());
		commerceSubscriptionCycleEntry.setUserName(
			commerceSubscriptionEntry.getUserName());
		commerceSubscriptionCycleEntry.setCommerceSubscriptionEntryId(
			commerceSubscriptionEntryId);
		commerceSubscriptionCycleEntry.setCommerceOrderItemId(
			commerceOrderItemId);
		commerceSubscriptionCycleEntry.setRenew(renew);

		commerceSubscriptionCycleEntryPersistence.update(
			commerceSubscriptionCycleEntry);

		// Send commerce notifications

		_commerceNotificationHelper.sendNotifications(
			commerceSubscriptionCycleEntry.getGroupId(),
			SubscriptionRenewedCommerceNotificationTypeImpl.KEY,
			commerceSubscriptionCycleEntry);

		// Commerce product subscription entry

		reindexCommerceSubscriptionEntry(commerceSubscriptionEntryId);

		return commerceSubscriptionCycleEntry;
	}

	@Override
	public CommerceSubscriptionCycleEntry
		fetchCommerceSubscriptionCycleEntryByCommerceOrderItemId(
			long commerceOrderItemId) {

		return commerceSubscriptionCycleEntryPersistence.
			fetchByCommerceOrderItemId(commerceOrderItemId);
	}

	@Override
	public List<CommerceSubscriptionCycleEntry>
		getCommerceSubscriptionCycleEntries(long commerceSubscriptionEntryId) {

		return commerceSubscriptionCycleEntryPersistence.
			findBycommerceSubscriptionEntryId(commerceSubscriptionEntryId);
	}

	@Override
	public List<CommerceSubscriptionCycleEntry>
		getCommerceSubscriptionCycleEntries(
			long commerceSubscriptionEntryId, int start, int end,
			OrderByComparator<CommerceSubscriptionCycleEntry>
				orderByComparator) {

		return commerceSubscriptionCycleEntryPersistence.
			findBycommerceSubscriptionEntryId(
				commerceSubscriptionEntryId, start, end, orderByComparator);
	}

	@Override
	public int getCommerceSubscriptionCycleEntriesCount(
		long commerceSubscriptionEntryId) {

		return commerceSubscriptionCycleEntryPersistence.
			countBycommerceSubscriptionEntryId(commerceSubscriptionEntryId);
	}

	protected void reindexCommerceSubscriptionEntry(
			long commerceSubscriptionEntryId)
		throws PortalException {

		Indexer<CommerceSubscriptionEntry> indexer =
			IndexerRegistryUtil.nullSafeGetIndexer(
				CommerceSubscriptionEntry.class);

		indexer.reindex(
			CommerceSubscriptionEntry.class.getName(),
			commerceSubscriptionEntryId);
	}

	@ServiceReference(type = CommerceNotificationHelper.class)
	private CommerceNotificationHelper _commerceNotificationHelper;

}