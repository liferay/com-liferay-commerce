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

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

/**
 * @author Andrea Di Giorgi
 * @author Alessio Antonio Rendina
 */
public class CommerceOrderPaymentConstants {

	public static final int STATUS_ANY = WorkflowConstants.STATUS_ANY;

	public static final int STATUS_CANCELLED =
		WorkflowConstants.STATUS_IN_TRASH;

	public static final int STATUS_COMPLETED =
		WorkflowConstants.STATUS_APPROVED;

	public static final int STATUS_FAILED = WorkflowConstants.STATUS_DENIED;

	public static final int STATUS_PENDING = WorkflowConstants.STATUS_PENDING;

	public static String getOrderPaymentLabelStyle(int orderPaymentStatus) {
		if ((orderPaymentStatus == STATUS_ANY) ||
			(orderPaymentStatus == STATUS_CANCELLED)) {

			return "info";
		}
		else if (orderPaymentStatus == STATUS_COMPLETED) {
			return "success";
		}
		else if (orderPaymentStatus == STATUS_PENDING) {
			return "warning";
		}
		else if (orderPaymentStatus == STATUS_FAILED) {
			return "danger";
		}

		return StringPool.BLANK;
	}

	public static String getOrderPaymentStatusLabel(int orderPaymentStatus) {
		if (orderPaymentStatus == STATUS_ANY) {
			return WorkflowConstants.LABEL_ANY;
		}
		else if (orderPaymentStatus == STATUS_CANCELLED) {
			return "cancelled";
		}
		else if (orderPaymentStatus == STATUS_COMPLETED) {
			return "completed";
		}
		else if (orderPaymentStatus == STATUS_FAILED) {
			return "failed";
		}
		else if (orderPaymentStatus == STATUS_PENDING) {
			return WorkflowConstants.LABEL_PENDING;
		}

		return null;
	}

}