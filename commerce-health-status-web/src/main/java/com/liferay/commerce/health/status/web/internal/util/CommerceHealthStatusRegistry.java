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

package com.liferay.commerce.health.status.web.internal.util;

import com.liferay.commerce.health.status.CommerceHealthStatus;
import com.liferay.commerce.health.status.web.internal.util.comparator.CommerceHealthStatusServiceWrapperDisplayOrderComparator;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerCustomizerFactory;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerCustomizerFactory.ServiceWrapper;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = CommerceHealthStatusRegistry.class)
public class CommerceHealthStatusRegistry {

	public CommerceHealthStatus getCommerceHealthStatus(String key) {
		if (Validator.isNull(key)) {
			return null;
		}

		ServiceWrapper<CommerceHealthStatus>
			commerceHealthStatusServiceWrapper =
				_commerceHealthStatusRegistryMap.getService(key);

		if (commerceHealthStatusServiceWrapper == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"No commerce health status registered with key " + key);
			}

			return null;
		}

		return commerceHealthStatusServiceWrapper.getService();
	}

	public List<CommerceHealthStatus> getCommerceHealthStatuses(int type) {
		List<CommerceHealthStatus> commerceHealthStatuses = new ArrayList<>();

		List<ServiceWrapper<CommerceHealthStatus>>
			commerceHealthStatusServiceWrappers = ListUtil.fromCollection(
				_commerceHealthStatusRegistryMap.values());

		Collections.sort(
			commerceHealthStatusServiceWrappers,
			_commerceHealthStatusServiceWrapperDisplayOrderComparator);

		for (ServiceWrapper<CommerceHealthStatus>
				commerceHealthStatusServiceWrapper :
					commerceHealthStatusServiceWrappers) {

			CommerceHealthStatus commerceHealthStatus =
				commerceHealthStatusServiceWrapper.getService();

			if (type == commerceHealthStatus.getType()) {
				commerceHealthStatuses.add(commerceHealthStatus);
			}
		}

		return Collections.unmodifiableList(commerceHealthStatuses);
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_commerceHealthStatusRegistryMap =
			ServiceTrackerMapFactory.openSingleValueMap(
				bundleContext, CommerceHealthStatus.class,
				"commerce.health.status.key",
				ServiceTrackerCustomizerFactory.
					<CommerceHealthStatus>serviceWrapper(bundleContext));
	}

	@Deactivate
	protected void deactivate() {
		_commerceHealthStatusRegistryMap.close();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceHealthStatusRegistry.class);

	private static final Comparator<ServiceWrapper<CommerceHealthStatus>>
		_commerceHealthStatusServiceWrapperDisplayOrderComparator =
			new CommerceHealthStatusServiceWrapperDisplayOrderComparator();

	private ServiceTrackerMap<String, ServiceWrapper<CommerceHealthStatus>>
		_commerceHealthStatusRegistryMap;

}