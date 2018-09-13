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

package com.liferay.commerce.product.content.web.internal.util;

import com.liferay.commerce.product.content.util.CPSubscriptionContentHelper;
import com.liferay.commerce.product.model.CPSubscriptionEntry;
import com.liferay.commerce.product.service.CPSubscriptionEntryLocalService;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = CPSubscriptionContentHelper.class)
public class CPSubscriptionContentHelperImpl
	implements CPSubscriptionContentHelper {

	@Override
	public List<CPSubscriptionEntry> getCPSubscriptionEntries(
		long groupId, long userId, int start, int end,
		OrderByComparator<CPSubscriptionEntry> orderByComparator) {

		return _cpSubscriptionEntryLocalService.getCPSubscriptionEntries(
			groupId, userId, start, end, orderByComparator);
	}

	@Override
	public int getCPSubscriptionEntriesCount(long groupId, long userId) {
		return _cpSubscriptionEntryLocalService.getCPSubscriptionEntriesCount(
			groupId, userId);
	}

	@Reference
	private CPSubscriptionEntryLocalService _cpSubscriptionEntryLocalService;

}