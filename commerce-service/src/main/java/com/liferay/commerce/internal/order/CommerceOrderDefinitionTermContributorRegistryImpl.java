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

package com.liferay.commerce.internal.order;

import com.liferay.commerce.order.CommerceDefinitionTermContributor;
import com.liferay.commerce.order.CommerceOrderDefinitionTermContributorRegistry;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Luca Pellizzon
 */
@Component(
	immediate = true,
	service = CommerceOrderDefinitionTermContributorRegistry.class
)
public class CommerceOrderDefinitionTermContributorRegistryImpl
	implements CommerceOrderDefinitionTermContributorRegistry {

	@Override
	public CommerceDefinitionTermContributor getDefinitionTermContributor(
		String key) {

		return _serviceTrackerMap.getService(key);
	}

	@Override
	public List<CommerceDefinitionTermContributor>
		getDefinitionTermContributors() {

		List<CommerceDefinitionTermContributor>
			commerceOrderDefinitionTermContributors = new ArrayList<>();

		for (String key : _serviceTrackerMap.keySet()) {
			commerceOrderDefinitionTermContributors.add(
				_serviceTrackerMap.getService(key));
		}

		return Collections.unmodifiableList(
			commerceOrderDefinitionTermContributors);
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext, CommerceDefinitionTermContributor.class,
			"commerce.definition.term.contributor.key");
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	private ServiceTrackerMap<String, CommerceDefinitionTermContributor>
		_serviceTrackerMap;

}