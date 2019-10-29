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

package com.liferay.commerce.internal.upgrade.v4_6_0;

import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.impl.CommerceOrderModelImpl;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Alec Sloan
 */
public class CommerceOrderAddressUpgradeProcess extends UpgradeProcess {

	public CommerceOrderAddressUpgradeProcess(
		ClassNameLocalService classNameLocalService) {

		_classNameLocalService = classNameLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		PreparedStatement ps = connection.prepareStatement(
			"select * from CommerceAddress where classNameId = ?");

		ps.setLong(
			1, _classNameLocalService.getClassNameId(CommerceOrder.class));

		ResultSet rs = ps.executeQuery();

		// Create columns on CommerceOrder to hold billing and shipping address info for placed orders

		_addOrderAddressColumns();

		// Add to new billing address columns for placed orders

		String updateOrderBillingAddressSQL = StringBundler.concat(
			"update CommerceOrder set billingName = ?, billingDescription = ?,",
			"billingStreet1 = ?, billingStreet2 = ?, billingStreet3 = ?,",
			"billingCity = ?, billingZip = ?, billingRegionId = ?,",
			"billingCountryId = ?, billingPhoneNumber = ?, billingAddressId = ",
			"0 where billingAddressId = ?");

		_updateAddressValues(rs, updateOrderBillingAddressSQL);

		// Add to new Shipping address columns for placed orders

		String updateOrderShippingAddressSQL = StringBundler.concat(
			"update CommerceOrder set shippingName = ?, shippingDescription = ",
			"?, shippingStreet1 = ?, shippingStreet2 = ?, shippingStreet3 = ?,",
			"shippingCity = ?, shippingZip = ?, shippingRegionId = ?,",
			"shippingCountryId = ?, shippingPhoneNumber = ?,",
			"shippingAddressId = 0 where shippingAddressId = ?");

		_updateAddressValues(rs, updateOrderShippingAddressSQL);


		PreparedStatement ps1 = connection.prepareStatement(
			"delete from CommerceAddress where classNameId = ?");

		ps1.setLong(
			1, _classNameLocalService.getClassNameId(CommerceOrder.class));

		ps1.execute();
	}

	private void _addOrderAddressColumns() throws Exception {
		_addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"billingName", "STRING");
		_addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"billingDescription", "STRING");
		_addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"billingStreet1", "STRING");
		_addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"billingStreet2", "STRING");
		_addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"billingStreet3", "STRING");
		_addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"billingCity", "STRING");
		_addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"billingZip", "STRING");
		_addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"billingRegionId", "LONG");
		_addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"billingCountryId", "LONG");
		_addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"billingPhoneNumber", "STRING");
		_addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"shippingName", "STRING");
		_addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"shippingDescription", "STRING");
		_addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"shippingStreet1", "STRING");
		_addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"shippingStreet2", "STRING");
		_addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"shippingStreet3", "STRING");
		_addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"shippingCity", "STRING");
		_addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"shippingZip", "STRING");
		_addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"shippingRegionId", "LONG");
		_addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"shippingCountryId", "LONG");
		_addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"shippingPhoneNumber", "STRING");
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

	private void _updateAddressValues(ResultSet rs, String updateSql)
		throws Exception {

		try (PreparedStatement ps =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection, updateSql)) {

			while (rs.next()) {
				ps.setString(1, rs.getString("name"));
				ps.setString(2, rs.getString("description"));
				ps.setString(3, rs.getString("street1"));
				ps.setString(4, rs.getString("street2"));
				ps.setString(5, rs.getString("street3"));
				ps.setString(6, rs.getString("city"));
				ps.setString(7, rs.getString("zip"));
				ps.setLong(8, rs.getLong("commerceRegionId"));
				ps.setLong(9, rs.getLong("commerceCountryId"));
				ps.setString(10, rs.getString("phoneNumber"));
				ps.setLong(11, rs.getLong("commerceAddressId"));

				ps.executeUpdate();
			}

			ps.executeBatch();
		}

		// Reset ResultSet iterator to the beginning

		rs.first();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceOrderAddressUpgradeProcess.class);

	private final ClassNameLocalService _classNameLocalService;

}