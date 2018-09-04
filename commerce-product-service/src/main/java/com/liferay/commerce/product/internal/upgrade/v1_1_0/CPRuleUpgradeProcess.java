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

package com.liferay.commerce.product.internal.upgrade.v1_1_0;

import com.liferay.commerce.product.model.impl.CPRuleModelImpl;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Ethan Bustad
 */
public class CPRuleUpgradeProcess extends UpgradeProcess {

	public CPRuleUpgradeProcess() {
		_columnName = "typeSettings";
		_columnType = "TEXT";
		_entityClass = CPRuleModelImpl.class;
		_tableName = CPRuleModelImpl.TABLE_NAME;
	}

	@Override
	protected void doUpgrade() throws Exception {
		_addColumn();
	}

	private void _addColumn() throws Exception {
		if (_log.isInfoEnabled()) {
			_log.info(
				String.format(
					"Adding column %s to table %s", _columnName, _tableName));
		}

		if (!hasColumn(_tableName, _columnName)) {
			alter(
				_entityClass,
				new AlterTableAddColumn(
					_columnName + StringPool.SPACE + _columnType));
		}
		else {
			if (_log.isInfoEnabled()) {
				_log.info(
					String.format(
						"Column %s already exists on table %s", _columnName,
						_tableName));
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CPRuleUpgradeProcess.class);

	private final String _columnName;
	private final String _columnType;
	private final Class _entityClass;
	private final String _tableName;

}