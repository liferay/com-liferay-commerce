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

import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CProduct;
import com.liferay.commerce.product.model.impl.CPFriendlyURLEntryModelImpl;
import com.liferay.portal.kernel.dao.db.IndexMetadata;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ClassNameLocalService;
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
 * @author Ethan Bustad
 */
public class CPFriendlyURLEntryUpgradeProcess extends UpgradeProcess {

	public CPFriendlyURLEntryUpgradeProcess(
		ClassNameLocalService classNameLocalService) {

		_classNameLocalService = classNameLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		_addIndexes(CPFriendlyURLEntryModelImpl.TABLE_NAME);

		long cpDefinitionClassNameId = _classNameLocalService.getClassNameId(
			CPDefinition.class);
		long cProductClassNameId = _classNameLocalService.getClassNameId(
			CProduct.class);

		String updateCPFriendlyURLSQL =
			"update CPFriendlyURLEntry set classNameId = ?, classPK = ? " +
				"where classNameId = ? and classPK = ?";

		String selectCPFriendlyURLEntrySQL = StringBundler.concat(
			"select distinct classPK, CProductId from CPFriendlyURLEntry ",
			"inner join CPDefinition on CPDefinition.CPDefinitionId = ",
			"CPFriendlyURLEntry.classPK where classNameId = ",
			String.valueOf(cpDefinitionClassNameId));

		try (PreparedStatement ps =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection, updateCPFriendlyURLSQL);
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(selectCPFriendlyURLEntrySQL)) {

			while (rs.next()) {
				long classPK = rs.getLong("classPK");
				long cProductId = rs.getLong("CProductId");

				ps.setLong(1, cProductClassNameId);
				ps.setLong(2, cProductId);
				ps.setLong(3, cpDefinitionClassNameId);
				ps.setLong(4, classPK);

				ps.executeUpdate();
			}

			ps.executeBatch();
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
		CPFriendlyURLEntryUpgradeProcess.class);

	private ClassNameLocalService _classNameLocalService;

}