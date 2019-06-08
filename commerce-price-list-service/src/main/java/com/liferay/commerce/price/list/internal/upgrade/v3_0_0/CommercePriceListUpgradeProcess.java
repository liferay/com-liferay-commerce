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

package com.liferay.commerce.price.list.internal.upgrade.v3_0_0;

import com.liferay.commerce.price.list.internal.upgrade.base.BaseCommercePriceListUpgradeProcess;
import com.liferay.commerce.price.list.model.impl.CommercePriceListImpl;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Luca Pellizzon
 */
public class CommercePriceListUpgradeProcess
	extends BaseCommercePriceListUpgradeProcess {

	@Override
	public void doUpgrade() throws Exception {

		// Add commerceCurrencyCode column

		addColumn(
			CommercePriceListImpl.class, CommercePriceListImpl.TABLE_NAME,
			"commerceCurrencyCode", "VARCHAR(75)");

		// Convert commerceCurrencyId to commerceCurrencyCode

		PreparedStatement ps = null;
		ResultSet rs = null;
		Statement s = null;

		try {
			ps = connection.prepareStatement(
				"update CommercePriceList set commerceCurrencyCode = ? where " +
					"commerceCurrencyId = ?");

			s = connection.createStatement();

			rs = s.executeQuery(
				"select commerceCurrencyId, code_ from CommerceCurrency");

			while (rs.next()) {
				long commerceCurrencyId = rs.getLong("commerceCurrencyId");

				String currencyCode = rs.getString("code_");

				ps.setString(1, currencyCode);

				ps.setLong(2, commerceCurrencyId);

				ps.execute();
			}
		}
		finally {
			DataAccess.cleanUp(ps);
			DataAccess.cleanUp(s, rs);
		}

		// Drop column commerceCurrencyId

		dropColumn(CommercePriceListImpl.TABLE_NAME, "commerceCurrencyId");
	}

}