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

package com.liferay.commerce.account.internal.upgrade.v1_2_0;

import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author Ethan Bustad
 */
public class RoleUpgradeProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		runSQL(
			StringBundler.concat(
				"update Role_ set subtype = '",
				CommerceAccountConstants.ROLE_SUBTYPE_ACCOUNT,
				"' where name in ('", String.join("','", _ACCOUNT_ROLE_NAMES),
				"')"));
	}

	private static final String[] _ACCOUNT_ROLE_NAMES = {
		"Account Administrator", "Buyer", "Order Manager"
	};

}