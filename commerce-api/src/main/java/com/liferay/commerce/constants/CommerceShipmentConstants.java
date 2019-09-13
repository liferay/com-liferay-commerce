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

package com.liferay.commerce.constants;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceShipmentConstants {

	public static final int[] ALLOWED_ORDER_STATUSES = {
		CommerceOrderConstants.ORDER_STATUS_AWAITING_FULFILLMENT,
		CommerceOrderConstants.ORDER_STATUS_AWAITING_SHIPMENT,
		CommerceOrderConstants.ORDER_STATUS_DISPUTED,
		CommerceOrderConstants.ORDER_STATUS_PARTIALLY_REFUNDED,
		CommerceOrderConstants.ORDER_STATUS_PARTIALLY_SHIPPED,
		CommerceOrderConstants.ORDER_STATUS_TRANSMITTED
	};

	public static final int SHIPMENT_STATUS_DELIVERED = 3;

	public static final int SHIPMENT_STATUS_PROCESSING = 0;

	public static final int SHIPMENT_STATUS_READY_TO_BE_SHIPPED = 1;

	public static final int SHIPMENT_STATUS_SHIPPED = 2;

	public static final int[] SHIPMENT_STATUSES = {
		SHIPMENT_STATUS_DELIVERED, SHIPMENT_STATUS_PROCESSING,
		SHIPMENT_STATUS_READY_TO_BE_SHIPPED, SHIPMENT_STATUS_SHIPPED
	};

	public static Integer getShipmentStatus(String label) {
		if (label.equals("delivered")) {
			return SHIPMENT_STATUS_DELIVERED;
		}
		else if (label.equals("processing")) {
			return SHIPMENT_STATUS_PROCESSING;
		}
		else if (label.equals("ready-to-be-shipped")) {
			return SHIPMENT_STATUS_READY_TO_BE_SHIPPED;
		}
		else if (label.equals("shipped")) {
			return SHIPMENT_STATUS_SHIPPED;
		}

		return null;
	}

	public static String getShipmentStatusLabel(int shipmentStatus) {
		if (shipmentStatus == SHIPMENT_STATUS_DELIVERED) {
			return "delivered";
		}
		else if (shipmentStatus == SHIPMENT_STATUS_PROCESSING) {
			return "processing";
		}
		else if (shipmentStatus == SHIPMENT_STATUS_READY_TO_BE_SHIPPED) {
			return "ready-to-be-shipped";
		}
		else if (shipmentStatus == SHIPMENT_STATUS_SHIPPED) {
			return "shipped";
		}

		return null;
	}

}