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

package com.liferay.commerce.notification.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.notification.model.CommerceNotificationTemplate;
import com.liferay.commerce.notification.service.CommerceNotificationTemplateLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

/**
 * @author Alessio Antonio Rendina
 */
@ProviderType
public class CommerceNotificationQueueEntryImpl
	extends CommerceNotificationQueueEntryBaseImpl {

	public CommerceNotificationQueueEntryImpl() {
	}

	@Override
	public String getCommerceNotificationTemplateType() throws PortalException {
		if (getCommerceNotificationTemplateId() == 0) {
			return StringPool.BLANK;
		}

		CommerceNotificationTemplate commerceNotificationTemplate =
			CommerceNotificationTemplateLocalServiceUtil.
				getCommerceNotificationTemplate(
					getCommerceNotificationTemplateId());

		return commerceNotificationTemplate.getType();
	}

	@Override
	public User getUser() throws PortalException {
		return UserLocalServiceUtil.getUser(getUserId());
	}

}