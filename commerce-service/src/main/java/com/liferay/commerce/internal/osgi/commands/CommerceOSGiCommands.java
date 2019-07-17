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

package com.liferay.commerce.internal.osgi.commands;

import com.liferay.commerce.service.CommerceSubscriptionEntryLocalService;
import com.liferay.commerce.subscription.CommerceSubscriptionEntryHelper;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"osgi.command.function=checkSubscriptionEntriesStatus",
		"osgi.command.scope=commerce"
	},
	service = CommerceOSGiCommands.class
)
public class CommerceOSGiCommands {

	public void renewSubscriptionEntries() throws Exception {
		_commerceSubscriptionEntryHelper.checkSubscriptionEntriesStatus(
			_commerceSubscriptionEntryLocalService.
				getActiveCommerceSubscriptionEntries());
	}

	@Reference
	private CommerceSubscriptionEntryHelper _commerceSubscriptionEntryHelper;

	@Reference
	private CommerceSubscriptionEntryLocalService
		_commerceSubscriptionEntryLocalService;

}