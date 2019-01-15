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

package com.liferay.commerce.product.internal.upgrade.v1_3_0;

import com.liferay.commerce.product.model.impl.CPDefinitionModelImpl;
import com.liferay.commerce.product.model.impl.CProductModelImpl;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.db.IndexMetadata;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.List;
import java.util.Objects;

/**
 * @author Ethan Bustad
 */
public class CProductUpgradeProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		runSQL(CProductModelImpl.TABLE_SQL_CREATE);

		_addIndexes(CProductModelImpl.TABLE_NAME);

		_addColumn(
			CPDefinitionModelImpl.class, CPDefinitionModelImpl.TABLE_NAME,
			"CProductId", "LONG");
		_addColumn(
			CPDefinitionModelImpl.class, CPDefinitionModelImpl.TABLE_NAME,
			"version", "INTEGER");

		PreparedStatement ps = null;
		ResultSet rs = null;
		Statement s = null;

		try {
			ps = connection.prepareStatement(
				"update CPDefinition set CProductId = ?, version = 1 where " +
					"CPDefinitionId = ?");

			s = connection.createStatement();

			rs = s.executeQuery("select * from CPDefinition");

			while (rs.next()) {
				String uuid = PortalUUIDUtil.generate();
				long cProductId = increment();
				long groupId = rs.getLong("groupId");
				long companyId = rs.getLong("companyId");
				long userId = rs.getLong("userId");
				String userName = rs.getString("userName");
				long cpDefinitionId = rs.getLong("CPDefinitionId");

				_addCProduct(
					uuid, cProductId, groupId, companyId, userId, userName,
					cpDefinitionId);

				ps.setLong(1, cProductId);
				ps.setLong(2, cpDefinitionId);

				ps.execute();
			}
		}
		finally {
			DataAccess.cleanUp(ps);
			DataAccess.cleanUp(s, rs);
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

	private void _addCProduct(
			String uuid, long cProductId, long groupId, long companyId,
			long userId, String userName, long publishedCPDefinitionId)
		throws Exception {

		PreparedStatement ps = null;

		try {
			ps = connection.prepareStatement(
				StringBundler.concat(
					"insert into CProduct (uuid_, CProductId, groupId, ",
					"companyId, userId, userName, createDate, modifiedDate, ",
					"publishedCPDefinitionId) values (?, ?, ?, ?, ?, ?, ?, ?, ?" +
					StringPool.CLOSE_PARENTHESIS));

			ps.setString(1, uuid);
			ps.setLong(2, cProductId);
			ps.setLong(3, groupId);
			ps.setLong(4, companyId);
			ps.setLong(5, userId);
			ps.setString(6, userName);

			Date now = new Date(System.currentTimeMillis());

			ps.setDate(7, now);
			ps.setDate(8, now);

			ps.setLong(9, publishedCPDefinitionId);

			ps.execute();
		}
		finally {
			DataAccess.cleanUp(ps);
		}
	}

	private void _addIndexes(String tableName) throws Exception {
		Class<?> clazz = getClass();

		List<ObjectValuePair<String, IndexMetadata>> indexesSQL = getIndexesSQL(
			clazz.getClassLoader(), tableName);

		for (ObjectValuePair<String, IndexMetadata> indexSQL : indexesSQL) {
			IndexMetadata indexMetadata = indexSQL.getValue();

			if (_log.isInfoEnabled()) {
				_log.info(
					String.format(
						"Adding index %s to table %s",
						indexMetadata.getIndexName(), tableName));
			}

			if (!_tableHasIndex(tableName, indexMetadata.getIndexName())) {
				runSQL(indexMetadata.getCreateSQL(null));
			}
			else if (_log.isInfoEnabled()) {
				_log.info(
					String.format(
						"Index %s already exists on table %s",
						indexMetadata.getIndexName(), tableName));
			}
		}
	}

	private boolean _tableHasIndex(String tableName, String indexName)
		throws Exception {

		ResultSet rs = null;

		try {
			DatabaseMetaData metadata = connection.getMetaData();

			rs = metadata.getIndexInfo(null, null, tableName, false, false);

			while (rs.next()) {
				String curIndexName = rs.getString("index_name");

				if (Objects.equals(indexName, curIndexName)) {
					return true;
				}
			}
		}
		finally {
			DataAccess.cleanUp(rs);
		}

		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CProductUpgradeProcess.class);

}