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

package com.liferay.commerce.frontend.internal.clay.table;

import com.liferay.commerce.frontend.ClayTableActionProvider;
import com.liferay.commerce.frontend.ClayTableActionProviderRegistry;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerCustomizerFactory;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerCustomizerFactory.ServiceWrapper;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Marco Leo
 */
@Component(immediate = true, service = ClayTableActionProviderRegistry.class)
public class ClayTableActionProviderRegistryImpl
	implements ClayTableActionProviderRegistry {

	@Override
	public List<ClayTableActionProvider> getClayTableActionProviders(
		String key) {

		List<ServiceWrapper<ClayTableActionProvider>>
			clayTableActionProviderServiceWrappers =
				_serviceTrackerMap.getService(key);

		if (clayTableActionProviderServiceWrappers == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"No ClayTableActionProvider registered with key " + key);
			}

			return Collections.emptyList();
		}

		List<ClayTableActionProvider> clayTableActionProviders =
			new ArrayList<>();

		for (ServiceWrapper<ClayTableActionProvider>
				tableActionProviderServiceWrapper :
					clayTableActionProviderServiceWrappers) {

			clayTableActionProviders.add(
				tableActionProviderServiceWrapper.getService());
		}

		return clayTableActionProviders;
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openMultiValueMap(
			bundleContext, ClayTableActionProvider.class, "commerce.table.name",
			ServiceTrackerCustomizerFactory.
				<ClayTableActionProvider>serviceWrapper(bundleContext));
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ClayTableActionProviderRegistryImpl.class);

	private ServiceTrackerMap
		<String, List<ServiceWrapper<ClayTableActionProvider>>>
			_serviceTrackerMap;

}