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

package com.liferay.commerce.payment.method.impl;

import com.liferay.commerce.payment.method.CommercePaymentEngineMethod;
import com.liferay.commerce.payment.method.CommercePaymentEngineMethodRegistry;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Luca Pellizzon
 */
@Component(
	immediate = true, service = CommercePaymentEngineMethodRegistry.class
)
public class CommercePaymentEngineMethodRegistryImpl
	implements CommercePaymentEngineMethodRegistry {

	@Override
	public CommercePaymentEngineMethod getCommercePaymentEngineMethod(
		String key) {

		return _serviceTrackerMap.getService(key);
	}

	@Override
	public Map<String, CommercePaymentEngineMethod>
		getCommercePaymentEngineMethods() {

		Map<String, CommercePaymentEngineMethod>
			commercePaymentMethodGroupRelMap = new HashMap<>();

		for (String key : _serviceTrackerMap.keySet()) {
			commercePaymentMethodGroupRelMap.put(
				key, _serviceTrackerMap.getService(key));
		}

		return Collections.unmodifiableMap(commercePaymentMethodGroupRelMap);
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext, CommercePaymentEngineMethod.class,
			"commerce.payment.engine.method.key");
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	private ServiceTrackerMap<String, CommercePaymentEngineMethod>
		_serviceTrackerMap;

}