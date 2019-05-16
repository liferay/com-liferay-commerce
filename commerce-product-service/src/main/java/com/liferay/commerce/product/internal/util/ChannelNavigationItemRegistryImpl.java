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

package com.liferay.commerce.product.internal.util;

import com.liferay.commerce.product.internal.util.comparator.ChannelNavigationItemServiceWrapperOrderComparator;
import com.liferay.commerce.product.util.ChannelNavigationItem;
import com.liferay.commerce.product.util.ChannelNavigationItemRegistry;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItem;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerCustomizerFactory;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerCustomizerFactory.ServiceWrapper;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.portlet.PortletRequest;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Alec Sloan
 */
@Component(immediate = true, service = ChannelNavigationItemRegistry.class)
public class ChannelNavigationItemRegistryImpl
	implements ChannelNavigationItemRegistry {

	@Override
	public List<NavigationItem> getNavigationItems(
			PortletRequest portletRequest)
		throws PortalException {

		List<NavigationItem> navigationItems = new ArrayList<>();

		List<ServiceWrapper<ChannelNavigationItem>>
			channelNavigationItemServiceWrappers = ListUtil.fromCollection(
				_serviceTrackerMap.values());

		Collections.sort(
			channelNavigationItemServiceWrappers,
			_channelNavigationItemServiceWrapperOrderComparator);

		for (ServiceWrapper<ChannelNavigationItem>
				channelNavigationItemServiceWrapper :
					channelNavigationItemServiceWrappers) {

			ChannelNavigationItem channelNavigationItem =
				channelNavigationItemServiceWrapper.getService();

			NavigationItem navigationItem =
				channelNavigationItem.getNavigationItem(portletRequest);

			if (navigationItem == null) {
				continue;
			}

			navigationItems.add(navigationItem);
		}

		return navigationItems;
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext, ChannelNavigationItem.class,
			"commerce.channel.navigation.item.key",
			ServiceTrackerCustomizerFactory.
				<ChannelNavigationItem>serviceWrapper(bundleContext));
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	private static final Comparator<ServiceWrapper<ChannelNavigationItem>>
		_channelNavigationItemServiceWrapperOrderComparator =
			new ChannelNavigationItemServiceWrapperOrderComparator();

	private ServiceTrackerMap<String, ServiceWrapper<ChannelNavigationItem>>
		_serviceTrackerMap;

}