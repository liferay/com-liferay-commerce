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

package com.liferay.commerce.product.util;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItem;
import com.liferay.osgi.util.ServiceTrackerFactory;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

import javax.portlet.PortletRequest;

import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Alec Sloan
 */
public class ChannelNavigationItemRegistryUtil {

	public static List<NavigationItem> getNavigationItems(
			PortletRequest portletRequest)
		throws PortalException {

		ChannelNavigationItemRegistry channelNavigationItemRegistry =
			_serviceTracker.getService();

		return channelNavigationItemRegistry.getNavigationItems(portletRequest);
	}

	private static final ServiceTracker<?, ChannelNavigationItemRegistry>
		_serviceTracker = ServiceTrackerFactory.open(
			ChannelNavigationItemRegistry.class);

}