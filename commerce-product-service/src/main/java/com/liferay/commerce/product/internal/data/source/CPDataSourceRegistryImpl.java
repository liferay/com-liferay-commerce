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

package com.liferay.commerce.product.internal.data.source;

import com.liferay.commerce.product.data.source.CPDataSourceRegistry;
import com.liferay.commerce.product.data.source.CPHttpDataSource;
import com.liferay.osgi.service.tracker.collections.list.ServiceTrackerList;
import com.liferay.osgi.service.tracker.collections.list.ServiceTrackerListFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(immediate = true, service = CPDataSourceRegistry.class)
public class CPDataSourceRegistryImpl implements CPDataSourceRegistry {

	@Override
	public List<CPHttpDataSource> getCPHttpDataSources() {
		List<CPHttpDataSource> cpHttpDataSources = new ArrayList<>();

		for (CPHttpDataSource cpHttpDataSource : _serviceTrackerList) {
			if (Validator.isNotNull(cpHttpDataSource.getName())) {
				cpHttpDataSources.add(cpHttpDataSource);
			}
		}

		return Collections.unmodifiableList(cpHttpDataSources);
	}

	@Override
	public CPHttpDataSource getCPHttpDataSource(String key) {
		if (Validator.isNull(key)) {
			return null;
		}

		Iterator<CPHttpDataSource> iterator = _serviceTrackerList.iterator();

		while (iterator.hasNext()) {
			CPHttpDataSource cpHttpDataSource = iterator.next();

			if (key.equals(cpHttpDataSource.getName())) {
				return cpHttpDataSource;
			}
		}

		if (_log.isDebugEnabled()) {
			_log.debug(
				"No commerce product data source registered with key " + key);
		}

		return null;
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerList = ServiceTrackerListFactory.open(
			bundleContext, CPHttpDataSource.class);
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerList.close();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CPDataSourceRegistryImpl.class);

	@Reference
	private Portal _portal;

	private ServiceTrackerList<CPHttpDataSource, CPHttpDataSource>
		_serviceTrackerList;

}