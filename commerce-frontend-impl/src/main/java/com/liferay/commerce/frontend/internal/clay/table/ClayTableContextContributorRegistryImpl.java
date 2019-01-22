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

import com.liferay.commerce.frontend.ClayTableContextContributor;
import com.liferay.commerce.frontend.ClayTableContextContributorRegistry;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerCustomizerFactory;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerCustomizerFactory.ServiceWrapper;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ListUtil;

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
@Component(
	immediate = true, service = ClayTableContextContributorRegistry.class
)
public class ClayTableContextContributorRegistryImpl
	implements ClayTableContextContributorRegistry {

	@Override
	public List<ClayTableContextContributor> getClayTableContextContributors(
		String key) {

		List<ServiceWrapper<ClayTableContextContributor>>
			clayTableContextContributorServiceWrappers =
				ListUtil.fromCollection(_serviceTrackerMap.getService(key));

		if (clayTableContextContributorServiceWrappers == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"No ClayTableContextContributor registered with key " +
						key);
			}

			return Collections.emptyList();
		}

		List<ClayTableContextContributor> clayTableContextContributors =
			new ArrayList<>();

		for (ServiceWrapper<ClayTableContextContributor>
				tableActionProviderServiceWrapper :
					clayTableContextContributorServiceWrappers) {

			clayTableContextContributors.add(
				tableActionProviderServiceWrapper.getService());
		}

		return clayTableContextContributors;
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openMultiValueMap(
			bundleContext, ClayTableContextContributor.class,
			"commerce.table.name",
			ServiceTrackerCustomizerFactory.
				<ClayTableContextContributor>serviceWrapper(bundleContext));
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ClayTableContextContributorRegistryImpl.class);

	private ServiceTrackerMap
		<String, List<ServiceWrapper<ClayTableContextContributor>>>
			_serviceTrackerMap;

}