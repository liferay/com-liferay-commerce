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

package com.liferay.commerce.product.internal.upgrade.v1_5_0;

import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.model.impl.CommerceCatalogModelImpl;
import com.liferay.portal.kernel.dao.db.IndexMetadata;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.List;
import java.util.Objects;

/**
 * @author Alec Sloan
 */
public class CommerceCatalogUpgradeProcess extends UpgradeProcess {

	public CommerceCatalogUpgradeProcess(GroupLocalService groupLocalService) {
		_groupLocalService = groupLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasTable(CommerceCatalogModelImpl.TABLE_NAME)) {
			runSQL(CommerceCatalogModelImpl.TABLE_SQL_CREATE);
		}

		_addIndexes(CommerceCatalogModelImpl.TABLE_NAME);

		String insertCommerceCatalogSQL = StringBundler.concat(
			"insert into CommerceCatalog (commerceCatalogId, companyId, ",
			"userId, userName, createDate, modifiedDate, name, ",
			"catalogDefaultLanguageId) values (?, ?, ?, ?, ?, ?, ?, ?)");

		String insertCommerceChannelSQL = StringBundler.concat(
			"insert into CommerceChannel (commerceChannelId, companyId, ",
			"userId, userName, createDate, modifiedDate, name, type_, ",
			"typeSettings) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");

		String updateCPDefinitionSQL =
			"update CPDefinition set groupId = ? where groupId = ?";

		try (PreparedStatement ps1 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection, insertCommerceCatalogSQL);
			PreparedStatement ps2 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection, insertCommerceChannelSQL);
			PreparedStatement ps3 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection, updateCPDefinitionSQL);
			Statement s = connection.createStatement(
				ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = s.executeQuery(
				"select distinct groupId, companyId, userId, userName, " +
					"defaultLanguageId from CPDefinition")) {

			while (rs.next()) {
				long commerceCatalogId = increment();
				long groupId = rs.getLong("groupId");
				long companyId = rs.getLong("companyId");
				long userId = rs.getLong("userId");
				String userName = rs.getString("userName");
				Date now = new Date(System.currentTimeMillis());
				String defaultLanguageId = rs.getString("defaultLanguageId");

				ps1.setLong(1, commerceCatalogId);
				ps1.setLong(2, companyId);
				ps1.setLong(3, userId);
				ps1.setString(4, userName);
				ps1.setDate(5, now);
				ps1.setDate(6, now);

				Group siteGroup = _groupLocalService.getGroup(groupId);

				ps1.setString(7, siteGroup.getName(defaultLanguageId));

				ps1.setString(8, defaultLanguageId);

				ps1.executeUpdate();

				ps2.setLong(1, increment());
				ps2.setLong(2, companyId);
				ps2.setLong(3, userId);
				ps2.setString(4, userName);
				ps2.setDate(5, now);
				ps2.setDate(6, now);
				ps2.setString(7, siteGroup.getName(defaultLanguageId));
				ps2.setString(8, "site");
				ps2.setString(9, "groupId=" + siteGroup.getGroupId());

				ps2.executeUpdate();

				Group catalogGroup = _groupLocalService.addGroup(
					userId, GroupConstants.DEFAULT_PARENT_GROUP_ID,
					CommerceCatalog.class.getName(), commerceCatalogId,
					GroupConstants.DEFAULT_LIVE_GROUP_ID,
					siteGroup.getNameMap(), null,
					GroupConstants.TYPE_SITE_PRIVATE, false,
					GroupConstants.DEFAULT_MEMBERSHIP_RESTRICTION, null, false,
					true, null);

				ps3.setLong(1, catalogGroup.getGroupId());

				ps3.setLong(2, groupId);

				ps3.executeUpdate();
			}

			ps1.executeBatch();
			ps2.executeBatch();
			ps3.executeBatch();
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
		CommerceCatalogUpgradeProcess.class);

	private final GroupLocalService _groupLocalService;

}