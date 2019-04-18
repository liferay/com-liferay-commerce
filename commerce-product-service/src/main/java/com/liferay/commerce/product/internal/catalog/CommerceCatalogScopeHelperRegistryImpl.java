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

package com.liferay.commerce.product.internal.catalog;

import com.liferay.commerce.product.catalog.CommerceCatalogScopeHelperRegistry;
import com.liferay.commerce.product.indexer.CommerceCatalogScopeHelper;
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
 * @author Alec Sloan
 */
@Component(immediate = true, service = CommerceCatalogScopeHelperRegistry.class)
public class CommerceCatalogScopeHelperRegistryImpl
	implements CommerceCatalogScopeHelperRegistry {

	@Override
	public CommerceCatalogScopeHelper getCommerceCatalogScopeHelper(
		String key) {

		ServiceWrapper<CommerceCatalogScopeHelper>
			commerceCatalogScopeHelperServiceWrapper =
				_serviceTrackerMap.getService(key);

		if (commerceCatalogScopeHelperServiceWrapper == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"No CommerceCatalogScopeHelper registered with key " + key);
			}

			return null;
		}

		return commerceCatalogScopeHelperServiceWrapper.getService();
	}

	@Override
	public List<CommerceCatalogScopeHelper> getCommerceCatalogScopeHelpers() {
		List<CommerceCatalogScopeHelper> commerceCatalogScopeHelpers =
			new ArrayList<>();

		List<ServiceWrapper<CommerceCatalogScopeHelper>>
			commerceCatalogScopeHelperServiceWrappers = ListUtil.fromCollection(
				_serviceTrackerMap.values());

		for (ServiceWrapper<CommerceCatalogScopeHelper>
				commerceCatalogScopeHelperServiceWrapper :
					commerceCatalogScopeHelperServiceWrappers) {

			commerceCatalogScopeHelpers.add(
				commerceCatalogScopeHelperServiceWrapper.getService());
		}

		return Collections.unmodifiableList(commerceCatalogScopeHelpers);
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext, CommerceCatalogScopeHelper.class,
			"commerce.index.class.name",
			ServiceTrackerCustomizerFactory.
				<CommerceCatalogScopeHelper>serviceWrapper(bundleContext));
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceCatalogScopeHelperRegistryImpl.class);

	private ServiceTrackerMap
		<String, ServiceWrapper<CommerceCatalogScopeHelper>> _serviceTrackerMap;

}