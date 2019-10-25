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

package com.liferay.commerce.product.internal.upgrade.v1_9_0;

import com.liferay.commerce.product.model.impl.CPDefinitionOptionRelModelImpl;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Marco Leo
 */
public class CPDefinitionOptionRelUpgradeProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		_addColumn(
			CPDefinitionOptionRelModelImpl.class,
			CPDefinitionOptionRelModelImpl.TABLE_NAME, "key_", "VARCHAR(75)");

		String selectCPOptionSQL =
			"select distinct CPOptionId, key_  from CPOption";
		String updateCPDefinitionOptionRelSQL =
			"update CPDefinitionOptionRel set key_ = ? WHERE CPOptionId = ?";

		try (PreparedStatement ps =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection, updateCPDefinitionOptionRelSQL);
			Statement s = connection.createStatement(
				ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = s.executeQuery(selectCPOptionSQL)) {

			while (rs.next()) {
				ps.setString(1, rs.getString("key_"));
				ps.setLong(2, rs.getLong("CPOptionId"));

				ps.executeUpdate();
			}

			ps.executeBatch();
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
		CPDefinitionOptionRelUpgradeProcess.class);

}