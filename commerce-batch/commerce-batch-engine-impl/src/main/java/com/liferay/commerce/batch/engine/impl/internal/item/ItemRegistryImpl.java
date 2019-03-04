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

import com.liferay.commerce.batch.engine.api.exception.NoSuchItemException;
import com.liferay.commerce.batch.engine.api.item.ItemRegistry;
import com.liferay.commerce.batch.engine.api.item.Operation;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
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
			String contentType, Class<T> serviceClass)
		throws NoSuchItemException {

		Objects.requireNonNull(type);
		Objects.requireNonNull(version);
		Objects.requireNonNull(operation);
		Objects.requireNonNull(serviceClass);

		ServiceReference<?>[] serviceReferences = null;

		try {
			String operationName = operation.name();

			if (contentType == null) {
				serviceReferences = _bundleContext.getAllServiceReferences(
					serviceClass.getName(),
					String.format(
						"(&(type=%s)(version=%s)(operation=%s))", type, version,
						StringUtil.toLowerCase(operationName)));
			}
			else {
				serviceReferences = _bundleContext.getAllServiceReferences(
					serviceClass.getName(),
					String.format(
						"(&(type=%s)(version=%s)(operation=%s)(contentType=" +
							"%s))",
						type, version, StringUtil.toLowerCase(operationName),
						contentType));
			}
		}
		catch (InvalidSyntaxException ise) {
			throw new SystemException(ise);
		}

		if ((serviceReferences == null) || (serviceReferences.length == 0)) {
			throw new NoSuchItemException(
				String.format(
					"Item service %s does not exist for type=%s, version=%s, " +
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
		ServiceReference<?> serviceReference = _serviceReferenceMap.remove(
			service);

		if (serviceReference != null) {
			_bundleContext.ungetService(serviceReference);
		}
	}

	private BundleContext _bundleContext;
	private final Map<Object, ServiceReference<?>> _serviceReferenceMap =
		new ConcurrentHashMap<>();

}