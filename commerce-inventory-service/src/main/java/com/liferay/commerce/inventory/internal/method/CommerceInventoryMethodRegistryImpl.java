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

package com.liferay.commerce.inventory.internal.method;

import com.liferay.commerce.inventory.method.CommerceInventoryMethod;
import com.liferay.commerce.inventory.method.CommerceInventoryMethodRegistry;
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
@Component(immediate = true, service = CommerceInventoryMethodRegistry.class)
public class CommerceInventoryMethodRegistryImpl
	implements CommerceInventoryMethodRegistry {

	@Override
	public CommerceInventoryMethod getCommerceInventoryMethod(String key) {
		return _serviceTrackerMap.getService(key);
	}

	@Override
	public Map<String, CommerceInventoryMethod> getCommerceInventoryMethods() {
		Map<String, CommerceInventoryMethod> commercePaymentMethodGroupRelMap =
			new HashMap<>();

		for (String key : _serviceTrackerMap.keySet()) {
			commercePaymentMethodGroupRelMap.put(
				key, _serviceTrackerMap.getService(key));
		}

		return Collections.unmodifiableMap(commercePaymentMethodGroupRelMap);
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext, CommerceInventoryMethod.class,
			"commerce.inventory.method.key");
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	private ServiceTrackerMap<String, CommerceInventoryMethod>
		_serviceTrackerMap;

}