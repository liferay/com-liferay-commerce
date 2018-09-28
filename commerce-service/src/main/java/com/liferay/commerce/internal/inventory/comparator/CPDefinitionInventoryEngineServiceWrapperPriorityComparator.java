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

package com.liferay.commerce.internal.inventory.comparator;

import com.liferay.commerce.inventory.CPDefinitionInventoryEngine;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerCustomizerFactory.ServiceWrapper;
import com.liferay.portal.kernel.util.MapUtil;

import java.io.Serializable;

import java.util.Comparator;

/**
 * @author Alessio Antonio Rendina
 */
public class CPDefinitionInventoryEngineServiceWrapperPriorityComparator
	implements Comparator<ServiceWrapper<CPDefinitionInventoryEngine>>,
			   Serializable {

	public CPDefinitionInventoryEngineServiceWrapperPriorityComparator() {
		this(true);
	}

	public CPDefinitionInventoryEngineServiceWrapperPriorityComparator(
		boolean ascending) {

		_ascending = ascending;
	}

	@Override
	public int compare(
		ServiceWrapper<CPDefinitionInventoryEngine> serviceWrapper1,
		ServiceWrapper<CPDefinitionInventoryEngine> serviceWrapper2) {

		int priority1 = MapUtil.getInteger(
			serviceWrapper1.getProperties(),
			"cp.definition.inventory.engine.priority", Integer.MAX_VALUE);
		int priority2 = MapUtil.getInteger(
			serviceWrapper2.getProperties(),
			"cp.definition.inventory.engine.priority", Integer.MAX_VALUE);

		int value = Integer.compare(priority1, priority2);

		if (_ascending) {
			return value;
		}

		return Math.negateExact(value);
	}

	public boolean isAscending() {
		return _ascending;
	}

	private final boolean _ascending;

}