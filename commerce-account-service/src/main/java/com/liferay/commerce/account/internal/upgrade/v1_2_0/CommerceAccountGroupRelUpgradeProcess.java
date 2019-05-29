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

import com.liferay.commerce.account.model.impl.CommerceAccountGroupRelModelImpl;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceAccountGroupRelUpgradeProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasTable(CommerceAccountGroupRelModelImpl.TABLE_NAME)) {
			runSQL(CommerceAccountGroupRelModelImpl.TABLE_SQL_CREATE);
		}
	}

}