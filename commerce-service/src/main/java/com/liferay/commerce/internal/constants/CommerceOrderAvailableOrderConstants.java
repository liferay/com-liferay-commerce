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

package com.liferay.commerce.internal.constants;

import com.liferay.commerce.constants.CommerceOrderConstants;

/**
 * @author Alec Sloan
 */
public class CommerceOrderAvailableOrderConstants {

	public static final int[] getAvailableOrderStatuses() {
		return _AVAILABLE_ORDER_STATUSES.clone();
	}

	private static final int[] _AVAILABLE_ORDER_STATUSES = {
		CommerceOrderConstants.ORDER_STATUS_TO_TRANSMIT,
		CommerceOrderConstants.ORDER_STATUS_TRANSMITTED,
		CommerceOrderConstants.ORDER_STATUS_AWAITING_FULFILLMENT,
		CommerceOrderConstants.ORDER_STATUS_AWAITING_PICKUP,
		CommerceOrderConstants.ORDER_STATUS_AWAITING_SHIPMENT,
		CommerceOrderConstants.ORDER_STATUS_PARTIALLY_REFUNDED,
		CommerceOrderConstants.ORDER_STATUS_PARTIALLY_SHIPPED,
		CommerceOrderConstants.ORDER_STATUS_REFUNDED,
		CommerceOrderConstants.ORDER_STATUS_SHIPPED,
		CommerceOrderConstants.ORDER_STATUS_COMPLETED,
		CommerceOrderConstants.ORDER_STATUS_CANCELLED,
		CommerceOrderConstants.ORDER_STATUS_DECLINED,
		CommerceOrderConstants.ORDER_STATUS_DISPUTED
	};

}