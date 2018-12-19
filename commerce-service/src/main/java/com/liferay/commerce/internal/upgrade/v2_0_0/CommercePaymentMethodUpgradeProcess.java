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

package com.liferay.commerce.internal.upgrade.v2_0_0;

import com.liferay.commerce.model.impl.CommerceOrderImpl;
import com.liferay.commerce.model.impl.CommerceOrderPaymentImpl;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Luca Pellizzon
 */
public class CommercePaymentMethodUpgradeProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasColumn(CommerceOrderImpl.TABLE_NAME, "transactionId")) {
			_addColumn(
				CommerceOrderImpl.class, CommerceOrderImpl.TABLE_NAME,
				"transactionId", "STRING");
		}

		if (hasColumn(
				CommerceOrderImpl.TABLE_NAME, "commercePaymentMethodId")) {

			_addColumn(
				CommerceOrderImpl.class, CommerceOrderImpl.TABLE_NAME,
				"commercePaymentMethodKey", "STRING");

			String template = StringUtil.read(
				CommercePaymentMethodUpgradeProcess.class.getResourceAsStream(
					"dependencies/CommerceOrderUpgradeProcess.sql"));

			runSQLTemplateString(template, false, false);

			alter(
				CommerceOrderImpl.class,
				new AlterTableDropColumn("commercePaymentMethodId"));
		}

		if (hasColumn(
				CommerceOrderPaymentImpl.TABLE_NAME,
				"commercePaymentMethodId")) {

			_addColumn(
				CommerceOrderPaymentImpl.class,
				CommerceOrderPaymentImpl.TABLE_NAME, "commercePaymentMethodKey",
				"STRING");

			String template = StringUtil.read(
				CommercePaymentMethodUpgradeProcess.class.getResourceAsStream(
					"dependencies/CommerceOrderPaymentUpgradeProcess.sql"));

			runSQLTemplateString(template, false, false);

			alter(
				CommerceOrderPaymentImpl.class,
				new AlterTableDropColumn("commercePaymentMethodId"));
		}

		if (hasTable("CommercePaymentMethod")) {
			String template = StringUtil.read(
				CommercePaymentMethodUpgradeProcess.class.getResourceAsStream(
					"dependencies/CommercePaymentMethodUpgradeProcess.sql"));

			runSQLTemplateString(template, false, false);
		}
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
		CommercePaymentMethodUpgradeProcess.class);

}