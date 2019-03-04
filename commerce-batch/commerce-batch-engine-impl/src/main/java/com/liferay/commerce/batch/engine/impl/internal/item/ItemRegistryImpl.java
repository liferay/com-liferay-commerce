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

package com.liferay.commerce.batch.engine.impl.internal.item;

import com.liferay.commerce.batch.engine.api.item.ItemRegistry;
import com.liferay.commerce.batch.engine.api.item.Operation;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceObjects;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Ivica Cardic
 */
@Component(immediate = true, service = ItemRegistry.class)
public class ItemRegistryImpl implements ItemRegistry {

	@Activate
	public void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T get(
			String type, String version, Operation operation,
			Class<T> serviceClass)
		throws Exception {

		ServiceReference<?>[] serviceReferences =
			_bundleContext.getAllServiceReferences(
				serviceClass.getName(),
				String.format(
					"(&(type=%s)(version=%s)(operation=%s))", type, version,
					operation));

		if ((serviceReferences == null) || (serviceReferences.length == 0)) {
			throw new IllegalArgumentException(
				String.format(
					"Service %s does not exist for type=%s, version=%s, " +
						"operation=%s",
					serviceClass.getName(), type, version, operation));
		}

		ServiceObjects<?> serviceObjects = _bundleContext.getServiceObjects(
			serviceReferences[0]);

		T service = (T)serviceObjects.getService();

		_serviceReferenceMap.put(service, serviceReferences[0]);

		return service;
	}

	@Override
	public void unget(Object service) {
		_bundleContext.ungetService(_serviceReferenceMap.remove(service));
	}

	private BundleContext _bundleContext;
	private final Map<Object, ServiceReference<?>> _serviceReferenceMap =
		new ConcurrentHashMap<>();

}