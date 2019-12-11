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

import com.liferay.commerce.internal.upgrade.base.BaseCommerceServiceUpgradeProcess;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.impl.CommerceOrderModelImpl;
import com.liferay.commerce.model.impl.CommerceShipmentModelImpl;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Alec Sloan
 */
public class CommerceOrderAddressUpgradeProcess
	extends BaseCommerceServiceUpgradeProcess {

	public CommerceOrderAddressUpgradeProcess(
		ClassNameLocalService classNameLocalService) {

		_classNameLocalService = classNameLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		long commerceOrderClassNameId = _classNameLocalService.getClassNameId(
			CommerceOrder.class);

		try (Statement s = connection.createStatement(
				ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = s.executeQuery(
				"select * from CommerceAddress where classNameId = " +
					commerceOrderClassNameId)) {

			// Create columns on CommerceOrder for placed order address info

			_addOrderAddressColumns();

			// Add to new billing address columns for placed orders

			String updateOrderBillingAddressSQL = StringBundler.concat(
				"update CommerceOrder set billingName = ?, billingDescription ",
				"= ?, billingStreet1 = ?, billingStreet2 = ?, billingStreet3 ",
				"= ?, billingCity = ?, billingZip = ?, billingRegionId = ?, ",
				"billingCountryId = ?, billingPhoneNumber = ?, ",
				"billingAddressId = 0 where billingAddressId = ?");

			_updateAddressValues(rs, updateOrderBillingAddressSQL);

			// Add to new Shipping address columns for placed orders

			String updateOrderShippingAddressSQL = StringBundler.concat(
				"update CommerceOrder set shippingName = ?, ",
				"shippingDescription = ?, shippingStreet1 = ?, ",
				"shippingStreet2 = ?, shippingStreet3 = ?, shippingCity = ?, ",
				"shippingZip = ?, shippingRegionId = ?, shippingCountryId = ?",
				", shippingPhoneNumber = ?, shippingAddressId = 0 where ",
				"shippingAddressId = ?");

			_updateAddressValues(rs, updateOrderShippingAddressSQL);

			// Create columns on Shipment to hold shipping address info

			_addShipmentAddressColumns();

			// Add to new Shipping address columns for shipments

			String updateShipmentAddressSQL = StringBundler.concat(
				"update CommerceShipment set shippingName = ?,",
				"shippingDescription = ?, shippingStreet1 = ?, ",
				"shippingStreet2 = ?, shippingStreet3 = ?, shippingCity = ?, ",
				"shippingZip = ?, shippingRegionId = ?, shippingCountryId = ?,",
				"shippingPhoneNumber = ? where commerceAddressId = ?");

			_updateAddressValues(rs, updateShipmentAddressSQL);

			// Remove all addresses that belong to orders

			try (Statement s1 = connection.createStatement(
					ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)) {

				s1.executeUpdate(
					"delete from CommerceAddress where classNameId = " +
						commerceOrderClassNameId);
			}

			// Drop stale column from CommerceShipment

			dropColumn(
				CommerceShipmentModelImpl.TABLE_NAME, "commerceAddressId");
		}
	}

	private void _addOrderAddressColumns() throws Exception {
		addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"billingName", "VARCHAR(75)");
		addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"billingDescription", "STRING");
		addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"billingStreet1", "VARCHAR(75)");
		addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"billingStreet2", "VARCHAR(75)");
		addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"billingStreet3", "VARCHAR(75)");
		addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"billingCity", "VARCHAR(75)");
		addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"billingZip", "VARCHAR(75)");
		addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"billingRegionId", "LONG");
		addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"billingCountryId", "LONG");
		addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"billingPhoneNumber", "VARCHAR(75)");
		addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"shippingName", "VARCHAR(75)");
		addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"shippingDescription", "STRING");
		addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"shippingStreet1", "VARCHAR(75)");
		addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"shippingStreet2", "VARCHAR(75)");
		addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"shippingStreet3", "VARCHAR(75)");
		addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"shippingCity", "VARCHAR(75)");
		addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"shippingZip", "VARCHAR(75)");
		addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"shippingRegionId", "LONG");
		addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"shippingCountryId", "LONG");
		addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"shippingPhoneNumber", "VARCHAR(75)");
	}

	private void _addShipmentAddressColumns() throws Exception {
		addColumn(
			CommerceShipmentModelImpl.class,
			CommerceShipmentModelImpl.TABLE_NAME, "shippingName", "VARCHAR(75)");
		addColumn(
			CommerceShipmentModelImpl.class,
			CommerceShipmentModelImpl.TABLE_NAME, "shippingDescription",
			"STRING");
		addColumn(
			CommerceShipmentModelImpl.class,
			CommerceShipmentModelImpl.TABLE_NAME, "shippingStreet1", "VARCHAR(75)");
		addColumn(
			CommerceShipmentModelImpl.class,
			CommerceShipmentModelImpl.TABLE_NAME, "shippingStreet2", "VARCHAR(75)");
		addColumn(
			CommerceShipmentModelImpl.class,
			CommerceShipmentModelImpl.TABLE_NAME, "shippingStreet3", "VARCHAR(75)");
		addColumn(
			CommerceShipmentModelImpl.class,
			CommerceShipmentModelImpl.TABLE_NAME, "shippingCity", "VARCHAR(75)");
		addColumn(
			CommerceShipmentModelImpl.class,
			CommerceShipmentModelImpl.TABLE_NAME, "shippingZip", "VARCHAR(75)");
		addColumn(
			CommerceShipmentModelImpl.class,
			CommerceShipmentModelImpl.TABLE_NAME, "shippingRegionId", "LONG");
		addColumn(
			CommerceShipmentModelImpl.class,
			CommerceShipmentModelImpl.TABLE_NAME, "shippingCountryId", "LONG");
		addColumn(
			CommerceShipmentModelImpl.class,
			CommerceShipmentModelImpl.TABLE_NAME, "shippingPhoneNumber",
			"VARCHAR(75)");
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