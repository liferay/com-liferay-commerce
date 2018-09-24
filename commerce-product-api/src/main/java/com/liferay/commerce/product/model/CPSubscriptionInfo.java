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

package com.liferay.commerce.product.model;

/**
 * @author Alessio Antonio Rendina
 */
public class CPSubscriptionInfo {

	public CPSubscriptionInfo(
		long subscriptionCycleLength, String subscriptionCyclePeriod,
		long maxSubscriptionCycleNumber) {

		_subscriptionCycleLength = subscriptionCycleLength;
		_subscriptionCyclePeriod = subscriptionCyclePeriod;
		_maxSubscriptionCyclesNumber = maxSubscriptionCycleNumber;
	}

	public long getMaxSubscriptionCyclesNumber() {
		return _maxSubscriptionCyclesNumber;
	}

	public long getSubscriptionCycleLength() {
		return _subscriptionCycleLength;
	}

	public String getSubscriptionCyclePeriod() {
		return _subscriptionCyclePeriod;
	}

	private final long _maxSubscriptionCyclesNumber;
	private final long _subscriptionCycleLength;
	private final String _subscriptionCyclePeriod;

}