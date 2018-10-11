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

package com.liferay.commerce.subscription;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceSubscriptionEntry;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * @author Alessio Antonio Rendina
 */
@ProviderType
public interface CommerceSubscriptionEntryHelper {

	public void checkCommerceSubscriptions(CommerceOrder commerceOrder)
		throws PortalException;

	public void renewSubscriptionEntries(
			List<CommerceSubscriptionEntry> commerceSubscriptionEntries)
		throws PortalException;

	public void renewSubscriptionEntry(
			CommerceSubscriptionEntry commerceSubscriptionEntry)
		throws PortalException;

}