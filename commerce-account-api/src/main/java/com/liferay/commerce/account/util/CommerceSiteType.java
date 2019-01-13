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

package com.liferay.commerce.account.util;

import com.liferay.commerce.account.constants.CommerceAccountConstants;

/**
 * @author Alessio Antonio Rendina
 */
public enum CommerceSiteType {

	B2B(CommerceAccountConstants.B2B_SITE_TYPE),
	B2C(CommerceAccountConstants.B2C_SITE_TYPE),
	B2C_B2B(CommerceAccountConstants.B2C_B2B_SITE_TYPE);

	public static CommerceSiteType valueOf(int cst) {
		if (cst == CommerceAccountConstants.B2C_SITE_TYPE) {
			return B2C;
		}
		else if (cst == CommerceAccountConstants.B2B_SITE_TYPE) {
			return B2B;
		}
		else if (cst == CommerceAccountConstants.B2C_B2B_SITE_TYPE) {
			return B2C_B2B;
		}

		throw new IllegalArgumentException("argument out of range");
	}

	protected final int oldType;

	private CommerceSiteType(int oldType) {
		this.oldType = oldType;
	}

}