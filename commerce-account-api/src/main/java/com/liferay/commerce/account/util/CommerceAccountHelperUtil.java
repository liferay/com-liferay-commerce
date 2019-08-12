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

package com.liferay.commerce.account.util;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.osgi.util.ServiceTrackerFactory;
import com.liferay.portal.kernel.exception.PortalException;

import javax.servlet.http.HttpServletRequest;

import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Alec Sloan
 */
public class CommerceAccountHelperUtil {

	public String getAccountManagementPortletURL(
			HttpServletRequest httpServletRequest)
		throws PortalException {

		CommerceAccountHelper commerceAccountHelper =
			_serviceTracker.getService();

		return commerceAccountHelper.getAccountManagementPortletURL(
			httpServletRequest);
	}

	public long[] getCommerceAccountGroupIds(long commerceAccountId) {
		CommerceAccountHelper commerceAccountHelper =
			_serviceTracker.getService();

		return commerceAccountHelper.getCommerceAccountGroupIds(
			commerceAccountId);
	}

	public CommerceAccount getCurrentCommerceAccount(
			HttpServletRequest httpServletRequest)
		throws PortalException {

		CommerceAccountHelper commerceAccountHelper =
			_serviceTracker.getService();

		return commerceAccountHelper.getCurrentCommerceAccount(
			httpServletRequest);
	}

	public CommerceAccount getCurrentCommerceAccount(
			long groupId, HttpServletRequest httpServletRequest)
		throws PortalException {

		CommerceAccountHelper commerceAccountHelper =
			_serviceTracker.getService();

		return commerceAccountHelper.getCurrentCommerceAccount(
			groupId, httpServletRequest);
	}

	public long[] getUserCommerceAccountIds(long userId, long groupId)
		throws PortalException {

		CommerceAccountHelper commerceAccountHelper =
			_serviceTracker.getService();

		return commerceAccountHelper.getUserCommerceAccountIds(userId, groupId);
	}

	public void setCurrentCommerceAccount(
			HttpServletRequest httpServletRequest, long groupId,
			long commerceAccountId)
		throws PortalException {

		CommerceAccountHelper commerceAccountHelper =
			_serviceTracker.getService();

		commerceAccountHelper.setCurrentCommerceAccount(
			httpServletRequest, groupId, commerceAccountId);
	}

	private static final ServiceTracker<?, CommerceAccountHelper>
		_serviceTracker = ServiceTrackerFactory.open(
			FrameworkUtil.getBundle(CommerceAccountHelperUtil.class),
			CommerceAccountHelper.class);

}