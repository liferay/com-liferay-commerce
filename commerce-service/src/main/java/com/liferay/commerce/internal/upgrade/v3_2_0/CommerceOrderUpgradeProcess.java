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

package com.liferay.commerce.internal.upgrade.v3_2_0;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portlet.asset.model.impl.AssetEntryImpl;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Alec Sloan
 */
public class CommerceOrderUpgradeProcess extends UpgradeProcess {

	public CommerceOrderUpgradeProcess(
		AssetEntryLocalService assetEntryLocalService,
		ClassNameLocalService classNameLocalService,
		CounterLocalService counterLocalService) {

		_assetEntryLocalService = assetEntryLocalService;
		_classNameLocalService = classNameLocalService;
		_counterLocalService = counterLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		long commerceOrderClassNameId = _classNameLocalService.getClassNameId(
			CommerceOrder.class);

		ResultSet rs = null;
		Statement s = null;

		try {
			s = connection.createStatement();

			rs = s.executeQuery("select * from CommerceOrder");

			while (rs.next()) {
				AssetEntry assetEntry = new AssetEntryImpl();

				assetEntry.setEntryId(_counterLocalService.increment());
				assetEntry.setClassNameId(commerceOrderClassNameId);
				assetEntry.setClassName(CommerceOrder.class.getName());
				assetEntry.setClassPK(rs.getLong("CommerceOrderId"));

				_assetEntryLocalService.updateAssetEntry(assetEntry);
			}
		}
		finally {
			DataAccess.cleanUp(s, rs);
		}
	}

	private final AssetEntryLocalService _assetEntryLocalService;
	private final ClassNameLocalService _classNameLocalService;
	private final CounterLocalService _counterLocalService;

}