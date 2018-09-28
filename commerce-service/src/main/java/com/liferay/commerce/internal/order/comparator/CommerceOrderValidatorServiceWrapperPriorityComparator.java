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

package com.liferay.commerce.internal.order.comparator;

import com.liferay.commerce.order.CommerceOrderValidator;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerCustomizerFactory.ServiceWrapper;
import com.liferay.portal.kernel.util.MapUtil;

import java.io.Serializable;

import java.util.Comparator;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceOrderValidatorServiceWrapperPriorityComparator
	implements Comparator<ServiceWrapper<CommerceOrderValidator>>,
			   Serializable {

	public CommerceOrderValidatorServiceWrapperPriorityComparator() {
		this(true);
	}

	public CommerceOrderValidatorServiceWrapperPriorityComparator(
		boolean ascending) {

		_ascending = ascending;
	}

	@Override
	public int compare(
		ServiceWrapper<CommerceOrderValidator> serviceWrapper1,
		ServiceWrapper<CommerceOrderValidator> serviceWrapper2) {

		int priority1 = MapUtil.getInteger(
			serviceWrapper1.getProperties(),
			"commerce.order.validator.priority", Integer.MAX_VALUE);
		int priority2 = MapUtil.getInteger(
			serviceWrapper2.getProperties(),
			"commerce.order.validator.priority", Integer.MAX_VALUE);

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