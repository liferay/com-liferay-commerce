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

package com.liferay.commerce.internal.upgrade.v3_0_0;

import com.liferay.commerce.model.impl.CommerceSubscriptionEntryModelImpl;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Luca Pellizzon
 */
public class CommerceSubscriptionCycleEntryUpgradeProcess
	extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		_addColumn(
			CommerceSubscriptionEntryModelImpl.class,
			CommerceSubscriptionEntryModelImpl.TABLE_NAME, "currentCycle",
			"LONG");

		if (hasColumn(
				CommerceSubscriptionEntryModelImpl.TABLE_NAME,
				"currentCycle")) {

			runSQL(
				"UPDATE CommerceSubscriptionEntry SET currentCycle = 0 WHERE " +
					"currentCycle IS NULL");
		}

		if (hasTable("CSubscriptionCycleEntry")) {
			runSQL("drop table CSubscriptionCycleEntry");
		}
	}

	private void _addColumn(
			Class<?> entityClass, String tableName, String columnName,
			String columnType)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info(
				String.format(
					"Adding column %s to table %s", columnName, tableName));
		}

		if (!hasColumn(tableName, columnName)) {
			alter(
				entityClass,
				new AlterTableAddColumn(
					columnName + StringPool.SPACE + columnType));
		}
		else {
			if (_log.isInfoEnabled()) {
				_log.info(
					String.format(
						"Column %s already exists on table %s", columnName,
						tableName));
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceSubscriptionCycleEntryUpgradeProcess.class);

}