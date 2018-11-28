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

import com.liferay.portal.kernel.workflow.WorkflowConstants;

/**
 * @author Andrea Di Giorgi
 */
public class CommerceOrderConstants {

	public static final int ORDER_STATUS_ANY = WorkflowConstants.STATUS_ANY;

	public static final int ORDER_STATUS_AWAITING_FULFILLMENT = 11;

	public static final int ORDER_STATUS_AWAITING_PICKUP = 13;

	public static final int ORDER_STATUS_AWAITING_SHIPMENT = 12;

	public static final int ORDER_STATUS_CANCELLED =
		WorkflowConstants.STATUS_IN_TRASH;

	public static final int ORDER_STATUS_COMPLETED =
		WorkflowConstants.STATUS_APPROVED;

	public static final int ORDER_STATUS_DECLINED = 16;

	public static final int ORDER_STATUS_DISPUTED = 18;

	public static final int ORDER_STATUS_IN_PROGRESS =
		WorkflowConstants.STATUS_INCOMPLETE;

	public static final int ORDER_STATUS_OPEN = WorkflowConstants.STATUS_DRAFT;

	public static final int ORDER_STATUS_PARTIALLY_REFUNDED = 19;

	public static final int ORDER_STATUS_PARTIALLY_SHIPPED = 14;

	public static final int ORDER_STATUS_REFUNDED = 17;

	public static final int ORDER_STATUS_SHIPPED = 15;

	public static final int ORDER_STATUS_SUBSCRIPTION = 9;

	public static final int ORDER_STATUS_TO_TRANSMIT =
		WorkflowConstants.STATUS_PENDING;

	public static final int ORDER_STATUS_TRANSMITTED = 10;

	public static final int PAYMENT_STATUS_AUTHORIZED =
		WorkflowConstants.STATUS_DRAFT;

	public static final int PAYMENT_STATUS_PAID =
		WorkflowConstants.STATUS_APPROVED;

	public static final int PAYMENT_STATUS_PENDING =
		WorkflowConstants.STATUS_PENDING;

	public static final int[] PAYMENT_STATUSES = {
		PAYMENT_STATUS_AUTHORIZED, PAYMENT_STATUS_PAID, PAYMENT_STATUS_PENDING
	};

	public static final String RESOURCE_NAME = "com.liferay.commerce.order";

	public static final long TYPE_PK_APPROVAL = 0;

	public static final long TYPE_PK_TRANSMISSION = 1;

	public static String getOrderStatusLabel(int orderStatus) {
		if (orderStatus == ORDER_STATUS_AWAITING_FULFILLMENT) {
			return "awaiting-fulfillment";
		}
		else if (orderStatus == ORDER_STATUS_AWAITING_PICKUP) {
			return "awaiting-pickup";
		}
		else if (orderStatus == ORDER_STATUS_AWAITING_SHIPMENT) {
			return "awaiting-shipment";
		}
		else if (orderStatus == ORDER_STATUS_CANCELLED) {
			return "cancelled";
		}
		else if (orderStatus == ORDER_STATUS_COMPLETED) {
			return "completed";
		}
		else if (orderStatus == ORDER_STATUS_DECLINED) {
			return "declined";
		}
		else if (orderStatus == ORDER_STATUS_DISPUTED) {
			return "disputed";
		}
		else if (orderStatus == ORDER_STATUS_IN_PROGRESS) {
			return "in-progress";
		}
		else if (orderStatus == ORDER_STATUS_OPEN) {
			return "open";
		}
		else if (orderStatus == ORDER_STATUS_PARTIALLY_REFUNDED) {
			return "partially-refunded";
		}
		else if (orderStatus == ORDER_STATUS_PARTIALLY_SHIPPED) {
			return "partially-shipped";
		}
		else if (orderStatus == ORDER_STATUS_REFUNDED) {
			return "refunded";
		}
		else if (orderStatus == ORDER_STATUS_SHIPPED) {
			return "shipped";
		}
		else if (orderStatus == ORDER_STATUS_TO_TRANSMIT) {
			return "to-transmit";
		}
		else if (orderStatus == ORDER_STATUS_TRANSMITTED) {
			return "transmitted";
		}

		return null;
	}

	public static String getPaymentStatusLabel(int paymentStatus) {
		if (paymentStatus == PAYMENT_STATUS_AUTHORIZED) {
			return "authorized";
		}
		else if (paymentStatus == PAYMENT_STATUS_PAID) {
			return "paid";
		}
		else if (paymentStatus == PAYMENT_STATUS_PENDING) {
			return WorkflowConstants.LABEL_PENDING;
		}

		return null;
	}

}