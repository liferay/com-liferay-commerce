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

package com.liferay.commerce.product.internal.upgrade.v1_6_0;

import com.liferay.commerce.product.model.impl.CommerceChannelModelImpl;
import com.liferay.commerce.product.model.impl.CommerceChannelRelModelImpl;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Alec Sloan
 */
public class CommerceChannelUpgradeProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasTable(CommerceChannelModelImpl.TABLE_NAME)) {
			runSQL(CommerceChannelModelImpl.TABLE_SQL_CREATE);
		}

		if (!hasTable(CommerceChannelRelModelImpl.TABLE_NAME)) {
			runSQL(CommerceChannelRelModelImpl.TABLE_SQL_CREATE);
		}
	}

}