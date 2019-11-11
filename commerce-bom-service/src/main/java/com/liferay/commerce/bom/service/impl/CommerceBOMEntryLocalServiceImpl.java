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

package com.liferay.commerce.bom.service.impl;

import com.liferay.commerce.bom.model.CommerceBOMEntry;
import com.liferay.commerce.bom.service.base.CommerceBOMEntryLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;

import java.util.List;

/**
 * @author Luca Pellizzon
 * @author Alessio Antonio Rendina
 */
public class CommerceBOMEntryLocalServiceImpl
	extends CommerceBOMEntryLocalServiceBaseImpl {

	@Override
	public CommerceBOMEntry addCommerceBOMEntry(
			long userId, int number, String cpInstanceUuid, long cProductId,
			long commerceBOMDefinitionId, double positionX, double positionY,
			double radius)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		long commerceBOMEntryId = counterLocalService.increment();

		CommerceBOMEntry commerceBOMEntry = commerceBOMEntryPersistence.create(
			commerceBOMEntryId);

		commerceBOMEntry.setCompanyId(user.getCompanyId());
		commerceBOMEntry.setUserId(user.getUserId());
		commerceBOMEntry.setUserName(user.getFullName());
		commerceBOMEntry.setNumber(number);
		commerceBOMEntry.setCPInstanceUuid(cpInstanceUuid);
		commerceBOMEntry.setCProductId(cProductId);
		commerceBOMEntry.setCommerceBOMDefinitionId(commerceBOMDefinitionId);
		commerceBOMEntry.setPositionX(positionX);
		commerceBOMEntry.setPositionY(positionY);
		commerceBOMEntry.setRadius(radius);

		return commerceBOMEntryPersistence.update(commerceBOMEntry);
	}

	@Override
	public List<CommerceBOMEntry> getCommerceBOMEntries(
		long commerceBOMDefinitionId, int start, int end) {

		return commerceBOMEntryPersistence.findByCommerceBOMDefinitionId(
			commerceBOMDefinitionId, start, end);
	}

	@Override
	public int getCommerceBOMEntriesCount(long commerceBOMDefinitionId) {
		return commerceBOMEntryPersistence.countByCommerceBOMDefinitionId(
			commerceBOMDefinitionId);
	}

	@Override
	public CommerceBOMEntry updateCommerceBOMEntry(
			long commerceBOMEntryId, int number, String cpInstanceUuid,
			long cProductId, double positionX, double positionY, double radius)
		throws PortalException {

		CommerceBOMEntry commerceBOMEntry =
			commerceBOMEntryLocalService.getCommerceBOMEntry(
				commerceBOMEntryId);

		commerceBOMEntry.setNumber(number);
		commerceBOMEntry.setCPInstanceUuid(cpInstanceUuid);
		commerceBOMEntry.setCProductId(cProductId);
		commerceBOMEntry.setPositionX(positionX);
		commerceBOMEntry.setPositionY(positionY);
		commerceBOMEntry.setRadius(radius);

		return commerceBOMEntryPersistence.update(commerceBOMEntry);
	}

}