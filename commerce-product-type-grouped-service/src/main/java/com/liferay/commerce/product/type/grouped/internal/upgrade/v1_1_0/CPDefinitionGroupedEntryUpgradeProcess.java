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

package com.liferay.commerce.product.type.grouped.internal.upgrade.v1_1_0;

import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.commerce.product.type.grouped.model.CPDefinitionGroupedEntry;
import com.liferay.commerce.product.type.grouped.model.impl.CPDefinitionGroupedEntryModelImpl;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author Ethan Bustad
 */
public class CPDefinitionGroupedEntryUpgradeProcess extends UpgradeProcess {

	public CPDefinitionGroupedEntryUpgradeProcess(
		CPDefinitionLocalService cpDefinitionLocalService) {

		_cpDefinitionLocalService = cpDefinitionLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		_addColumn(
			CPDefinitionGroupedEntry.class,
			CPDefinitionGroupedEntryModelImpl.TABLE_NAME, "entryCProductId",
			"long");

		_addIndexes(CPDefinitionGroupedEntryModelImpl.TABLE_NAME);

		PreparedStatement ps = null;
		ResultSet rs = null;
		Statement s = null;

		try {
			ps = connection.prepareStatement(
				"update CPDefinitionGroupedEntry set entryCProductId = ? " +
					"where entryCPDefinitionId = ?");

			s = connection.createStatement();

			rs = s.executeQuery("select * from CPDefinitionGroupedEntry");

			while (rs.next()) {
				long entryCPDefinitionId = rs.getLong("entryCPDefinitionId");

				CPDefinition cpDefinition =
					_cpDefinitionLocalService.fetchCPDefinition(
						entryCPDefinitionId);

				ps.setLong(1, cpDefinition.getCProductId());

				ps.setLong(2, entryCPDefinitionId);

				ps.execute();
			}
		}
		finally {
			DataAccess.cleanUp(ps);
			DataAccess.cleanUp(s, rs);
		}

		_dropIndex(CPDefinitionGroupedEntryModelImpl.TABLE_NAME, "IX_85D6EC94");

		_dropColumn(
			CPDefinitionGroupedEntryModelImpl.TABLE_NAME,
			"entryCPDefinitionId");
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

	private void _dropColumn(String tableName, String columnName) {
		if (_log.isInfoEnabled()) {
			_log.info(
				String.format(
					"Dropping column %s from table %s", columnName, tableName));
		}

		if (hasColumn(tableName, columnName)) {
			runSQL(
				StringBundler.concat(
					"alter table ", tableName, " drop column ", columnName));
		}
		else {
			if (_log.isInfoEnabled()) {
				_log.info(
					String.format(
						"Column %s already does not exist on table %s",
						columnName, tableName));
			}
		}
	}

	private void _dropIndex(String tableName, String indexName) {
		if (_log.isInfoEnabled()) {
			_log.info(
				String.format(
					"Dropping index %s from table %s", indexName, tableName));
		}

		if (_tableHasIndex(tableName, indexName)) {
			runSQL(
				StringBundler.concat(
					"drop index ", indexName, " on ", tableName));
		}
		else {
			if (_log.isInfoEnabled()) {
				_log.info(
					String.format(
						"Index %s already does not exist on table %s",
						indexName, tableName));
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
		CPDefinitionGroupedEntryUpgradeProcess.class);

	private final CPDefinitionLocalService _cpDefinitionLocalService;

}