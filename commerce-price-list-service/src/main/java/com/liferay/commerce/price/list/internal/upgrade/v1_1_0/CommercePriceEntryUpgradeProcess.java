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

package com.liferay.commerce.price.list.internal.upgrade.v1_1_0;

import com.liferay.commerce.price.list.model.impl.CommercePriceEntryModelImpl;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Alec Sloan
 * @author Alessio Antonio Rendina
 */
public class CommercePriceEntryUpgradeProcess extends UpgradeProcess {

	public CommercePriceEntryUpgradeProcess(
		CPDefinitionLocalService cpDefinitionLocalService,
		CPInstanceLocalService cpInstanceLocalService) {

		_cpDefinitionLocalService = cpDefinitionLocalService;
		_cpInstanceLocalService = cpInstanceLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		_addColumn(
			CommercePriceEntryModelImpl.class,
			CommercePriceEntryModelImpl.TABLE_NAME, "CPInstanceUuid",
			"VARCHAR(75)");
		_addColumn(
			CommercePriceEntryModelImpl.class,
			CommercePriceEntryModelImpl.TABLE_NAME, "CProductId", "LONG");

		PreparedStatement ps = null;
		ResultSet rs = null;
		Statement s = null;

		try {
			ps = connection.prepareStatement(
				"update CommercePriceEntry set CProductId = ?," +
					"CPInstanceUuid = ? where CPInstanceId = ?");

			s = connection.createStatement();

			rs = s.executeQuery(
				"select distinct CPInstanceId from CommercePriceEntry");

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

		runSQL("alter table CommercePriceEntry drop column CPInstanceId");
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
		CommercePriceEntryUpgradeProcess.class);

	private final CPDefinitionLocalService _cpDefinitionLocalService;
	private final CPInstanceLocalService _cpInstanceLocalService;

}