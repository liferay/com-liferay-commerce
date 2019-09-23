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

package com.liferay.commerce.product.type.virtual.order.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceVirtualOrderItemException extends PortalException {

	public CommerceVirtualOrderItemException() {
	}

	public CommerceVirtualOrderItemException(String msg) {
		super(msg);
	}

	public CommerceVirtualOrderItemException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public CommerceVirtualOrderItemException(Throwable cause) {
		super(cause);
	}

}