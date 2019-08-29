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

package com.liferay.commerce.internal.upgrade.v4_1_0;

import com.liferay.commerce.internal.upgrade.base.BaseCommerceServiceUpgradeProcess;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.service.CompanyLocalService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Alec Sloan
 */
public class CommerceAssetCategoryUpgradeProcess
	extends BaseCommerceServiceUpgradeProcess {

	public CommerceAssetCategoryUpgradeProcess(
		CompanyLocalService companyLocalService) {

		_companyLocalService = companyLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		PreparedStatement ps = connection.prepareStatement(
			"Update AssetCategory set groupId = ?");

		Statement s = connection.createStatement(
			ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

		ResultSet rs = s.executeQuery(
			"select distinct vocabularyId, companyId from AssetCategory");

		while (rs.next()) {
			Company company = _companyLocalService.getCompany(
				rs.getLong("companyId"));

			ps.setLong(1, company.getGroupId());

			ps.executeUpdate();

			updateAssetVocabulary(
				company.getGroupId(), rs.getLong("vocabularyId"));
		}
	}

	protected void updateAssetVocabulary(long groupId, long vocabularyId)
		throws Exception {

		PreparedStatement ps = connection.prepareStatement(
			"Update AssetVocabulary set groupId = ? where vocabularyId = ?");

		ps.setLong(1, groupId);
		ps.setLong(2, vocabularyId);

		ps.executeUpdate();
	}

	private final CompanyLocalService _companyLocalService;

}