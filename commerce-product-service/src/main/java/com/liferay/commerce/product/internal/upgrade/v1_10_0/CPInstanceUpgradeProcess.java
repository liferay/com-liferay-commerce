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

package com.liferay.commerce.product.internal.upgrade.v1_10_0;

import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Marco Leo
 */
public class CPInstanceUpgradeProcess extends UpgradeProcess {

	public CPInstanceUpgradeProcess(JSONFactory jsonFactory) {
		_jsonFactory = jsonFactory;
	}

	@Override
	protected void doUpgrade() throws Exception {
		String updateCPInstanceSQL = StringBundler.concat(
			"update CPInstance set json = ? WHERE CPInstanceId = ?");

		try (PreparedStatement ps1 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection, updateCPInstanceSQL);
			Statement s = connection.createStatement(
				ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			Statement s2 = connection.createStatement(
				ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			Statement s3 = connection.createStatement(
				ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = s.executeQuery(
				"select CPInstanceId, json from CPInstance where json <> ''")) {

			while (rs.next()) {
				JSONArray jsonArray2 = _jsonFactory.createJSONArray();

				JSONArray jsonArray = _jsonFactory.createJSONArray(
					rs.getString("json"));

				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);

					ResultSet rs2 = s2.executeQuery(
						"select key_ from CPDefinitionOptionRel where CPDefinitionOptionRelId = " +
							jsonObject.getLong("key"));

					if (!rs2.next()) {
						continue;
					}

					JSONArray valueJSONArray = jsonObject.getJSONArray("value");

					JSONArray valueJSONArray2 = _jsonFactory.createJSONArray();

					for (int j = 0; j < valueJSONArray.length(); j++) {
						ResultSet rs3 = s3.executeQuery(
							"select key_ from CPDefinitionOptionValueRel where CPDefinitionOptionValueRelId = " +
								valueJSONArray.getLong(j));

						if (!rs3.next()) {
							continue;
						}

						valueJSONArray2.put(rs3.getString("key_"));
					}

					JSONObject jsonObject2 = _jsonFactory.createJSONObject();

					jsonObject2.put("key", rs2.getString("key_"));
					jsonObject2.put("value", valueJSONArray2);

					jsonArray2.put(jsonObject2);
				}

				ps1.setString(1, jsonArray2.toString());
				ps1.setLong(2, rs.getLong("CPInstanceId"));

				ps1.executeUpdate();
			}

			ps1.executeBatch();
		}
	}

	private final JSONFactory _jsonFactory;

}