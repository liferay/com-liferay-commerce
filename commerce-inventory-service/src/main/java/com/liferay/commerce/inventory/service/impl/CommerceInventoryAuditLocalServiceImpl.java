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

package com.liferay.commerce.inventory.service.impl;

import com.liferay.commerce.inventory.model.CommerceInventoryAudit;
import com.liferay.commerce.inventory.service.base.CommerceInventoryAuditLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;

import java.util.Date;
import java.util.List;

/**
 * @author Luca Pellizzon
 */
public class CommerceInventoryAuditLocalServiceImpl
	extends CommerceInventoryAuditLocalServiceBaseImpl {

	@Override
	public CommerceInventoryAudit addCommerceInventoryItemEntry(
			String description, String sku, int quantity, long userId)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		long commerceInventoryItemEntryId = counterLocalService.increment();

		CommerceInventoryAudit commerceInventoryItemEntry =
			commerceInventoryAuditPersistence.create(
				commerceInventoryItemEntryId);

		commerceInventoryItemEntry.setCompanyId(user.getCompanyId());
		commerceInventoryItemEntry.setUserId(user.getUserId());
		commerceInventoryItemEntry.setUserName(user.getFullName());
		commerceInventoryItemEntry.setSku(sku);
		commerceInventoryItemEntry.setDescription(description);
		commerceInventoryItemEntry.setQuantity(quantity);

		return commerceInventoryItemEntry;
	}

	@Override
	public void removeOldCommerceInventoryAudit(Date olderThan) {
		List<CommerceInventoryAudit> oldInventoryAudit =
			commerceInventoryAuditFinder.findOldInventoryAudit(olderThan);

		for (CommerceInventoryAudit commerceInventoryAudit :
				oldInventoryAudit) {

			commerceInventoryAuditPersistence.remove(commerceInventoryAudit);
		}
	}

}