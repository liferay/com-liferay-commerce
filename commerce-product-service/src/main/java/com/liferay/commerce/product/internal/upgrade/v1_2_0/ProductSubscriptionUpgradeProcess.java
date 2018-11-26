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

package com.liferay.commerce.product.internal.upgrade.v1_2_0;

import com.liferay.commerce.product.model.impl.CPDefinitionImpl;
import com.liferay.commerce.product.model.impl.CPInstanceImpl;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Marco Leo
 */
public class ProductSubscriptionUpgradeProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		_addColumn(
			CPDefinitionImpl.class, CPDefinitionImpl.TABLE_NAME,
			"subscriptionEnabled", "BOOLEAN");
		_addColumn(
			CPDefinitionImpl.class, CPDefinitionImpl.TABLE_NAME,
			"subscriptionLength", "INTEGER");
		_addColumn(
			CPDefinitionImpl.class, CPDefinitionImpl.TABLE_NAME,
			"subscriptionType", "VARCHAR(75)");
		_addColumn(
			CPDefinitionImpl.class, CPDefinitionImpl.TABLE_NAME,
			"subscriptionTypeSettings", "TEXT");
		_addColumn(
			CPDefinitionImpl.class, CPDefinitionImpl.TABLE_NAME,
			"maxSubscriptionCycles", "LONG");

		_addColumn(
			CPInstanceImpl.class, CPInstanceImpl.TABLE_NAME,
			"overrideSubscriptionInfo", "BOOLEAN");
		_addColumn(
			CPInstanceImpl.class, CPInstanceImpl.TABLE_NAME,
			"subscriptionEnabled", "BOOLEAN");
		_addColumn(
			CPInstanceImpl.class, CPInstanceImpl.TABLE_NAME,
			"subscriptionLength", "INTEGER");
		_addColumn(
			CPInstanceImpl.class, CPInstanceImpl.TABLE_NAME, "subscriptionType",
			"VARCHAR(75)");
		_addColumn(
			CPInstanceImpl.class, CPInstanceImpl.TABLE_NAME,
			"subscriptionTypeSettings", "TEXT");
		_addColumn(
			CPInstanceImpl.class, CPInstanceImpl.TABLE_NAME,
			"maxSubscriptionCycles", "LONG");
	}

	private void _addColumn(
			Class<?> tableClass, String tableName, String columnName,
			String columnType)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info(
				String.format(
					"Adding column %s to table %s", columnName, tableName));
		}

		if (!hasColumn(tableName, columnName)) {
			alter(
				tableClass,
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
		ProductSubscriptionUpgradeProcess.class);

}