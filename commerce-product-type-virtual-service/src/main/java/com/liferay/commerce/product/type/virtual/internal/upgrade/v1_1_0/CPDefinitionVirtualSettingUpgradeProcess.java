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

package com.liferay.commerce.product.type.virtual.internal.upgrade.v1_1_0;

import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.type.virtual.model.impl.CPDefinitionVirtualSettingImpl;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Alessio Antonio Rendina
 */
public class CPDefinitionVirtualSettingUpgradeProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		_addColumn(
			CPDefinitionVirtualSettingImpl.class,
			CPDefinitionVirtualSettingImpl.TABLE_NAME, "classNameId", "LONG");
		_addColumn(
			CPDefinitionVirtualSettingImpl.class,
			CPDefinitionVirtualSettingImpl.TABLE_NAME, "override", "BOOLEAN");

		_renameColumn(
			CPDefinitionVirtualSettingImpl.class,
			CPDefinitionVirtualSettingImpl.TABLE_NAME, "CPDefinitionId",
			"classPK LONG");

		if (hasColumn(
				CPDefinitionVirtualSettingImpl.TABLE_NAME, "classNameId")) {

			String template = StringUtil.read(
				CPDefinitionVirtualSettingUpgradeProcess.class.
					getResourceAsStream(
						"dependencies/CPDefinitionVirtualSetting.sql"));

			long classNameId = ClassNameLocalServiceUtil.getClassNameId(
				CPDefinition.class.getName());

			template = StringUtil.replace(
				template, "(?)", "\'" + classNameId + "\'");

			runSQLTemplateString(template, false, false);
		}

		upgradeIndexes();
	}

	protected void upgradeIndexes() throws Exception {
		runSQL("drop index IX_9C5C8C9A on CPDefinitionVirtualSetting");

		runSQL(
			"create unique index IX_19B2FD20 on CPDefinitionVirtualSetting " +
				"(classNameId, classPK)");
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

	private void _renameColumn(
			Class<?> tableClass, String tableName, String oldColumnName,
			String newColumnName)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info(
				String.format(
					"Renaming column %s to table %s", oldColumnName,
					tableName));
		}

		if (!hasColumn(tableName, newColumnName)) {
			alter(
				tableClass, new AlterColumnName(oldColumnName, newColumnName));
		}
		else {
			if (_log.isInfoEnabled()) {
				_log.info(
					String.format(
						"Column %s already exists on table %s", newColumnName,
						tableName));
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CPDefinitionVirtualSettingUpgradeProcess.class);

}