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

package com.liferay.commerce.wish.list.internal.upgrade.v1_1_0;

import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.commerce.wish.list.model.impl.CommerceWishListItemModelImpl;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.db.IndexMetadata;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.List;
import java.util.Objects;

/**
 * @author Alec Sloan
 */
public class CommerceWishListItemUpgradeProcess extends UpgradeProcess {

	public CommerceWishListItemUpgradeProcess(
		CPDefinitionLocalService cpDefinitionLocalService,
		CPInstanceLocalService cpInstanceLocalService) {

		_cpDefinitionLocalService = cpDefinitionLocalService;
		_cpInstanceLocalService = cpInstanceLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		_addColumn(
			CommerceWishListItemModelImpl.class,
			CommerceWishListItemModelImpl.TABLE_NAME, "CPInstanceUuid",
			"VARCHAR(75)");
		_addColumn(
			CommerceWishListItemModelImpl.class,
			CommerceWishListItemModelImpl.TABLE_NAME, "CProductId", "LONG");

		_addIndexes(CommerceWishListItemModelImpl.TABLE_NAME);

		PreparedStatement ps = null;
		ResultSet rs = null;
		Statement s = null;

		try {
			ps = connection.prepareStatement(
				"update CommerceWishListItem set CProductId = ?," +
					"CPInstanceUuid = ? where CPInstanceId = ?");

			s = connection.createStatement();

			rs = s.executeQuery(
				"select distinct CPInstanceId from CommerceWishListItem");

			while (rs.next()) {
				long cpInstanceId = rs.getLong("CPInstanceId");

				CPInstance cpInstance = _cpInstanceLocalService.getCPInstance(
					cpInstanceId);

				CPDefinition cpDefinition =
					_cpDefinitionLocalService.getCPDefinition(
						cpInstance.getCPDefinitionId());

				ps.setLong(1, cpDefinition.getCProductId());

				ps.setString(2, cpInstance.getCPInstanceUuid());
				ps.setLong(3, cpInstanceId);

				ps.execute();
			}
		}
		finally {
			DataAccess.cleanUp(ps);
			DataAccess.cleanUp(s, rs);
		}

		if (_tableHasIndex(
				CommerceWishListItemModelImpl.TABLE_NAME, "IX_622C400A")) {

			runSQL("drop index IX_622C400A on CommerceWishListItem");
		}

		if (_tableHasIndex(
				CommerceWishListItemModelImpl.TABLE_NAME, "IX_6FB8DFC8")) {

			runSQL("drop index IX_6FB8DFC8 on CommerceWishListItem");
		}

		_dropColumn(CommerceWishListItemModelImpl.TABLE_NAME, "CPDefinitionId");
		_dropColumn(CommerceWishListItemModelImpl.TABLE_NAME, "CPInstanceId");
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

	private void _dropColumn(String tableName, String columnName)
		throws Exception {

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
		CommerceWishListItemUpgradeProcess.class);

	private final CPDefinitionLocalService _cpDefinitionLocalService;
	private final CPInstanceLocalService _cpInstanceLocalService;

}