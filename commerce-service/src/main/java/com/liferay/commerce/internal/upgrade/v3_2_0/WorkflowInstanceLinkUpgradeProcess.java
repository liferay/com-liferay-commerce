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

import com.liferay.commerce.model.CommerceOrder;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.Portal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Alec Sloan
 */
public class WorkflowInstanceLinkUpgradeProcess extends UpgradeProcess {

	public WorkflowInstanceLinkUpgradeProcess(Portal portal) {
		_portal = portal;
	}

	@Override
	protected void doUpgrade() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			long classNameId = _portal.getClassNameId(CommerceOrder.class);

			ps = connection.prepareStatement(
				"select distinct classPK from WorkflowInstanceLink where " +
					"classNameId = ?");

			ps.setLong(1, classNameId);

			rs = ps.executeQuery();

			while (rs.next()) {
				updateGroupId(classNameId, rs.getLong("classPK"));
			}
		}
		finally {
			DataAccess.cleanUp(ps);
			DataAccess.cleanUp(rs);
		}
	}

	protected long getGroupIdByCommerceOrderId(long classPK) throws Exception {
		PreparedStatement ps = connection.prepareStatement(
			"select distinct groupId from CommerceOrder where " +
				"commerceOrderId = ?");

		ps.setLong(1, classPK);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			return rs.getLong("groupId");
		}

		return 0;
	}

	protected void updateGroupId(long classNameId, long classPK)
		throws Exception {

		PreparedStatement ps = connection.prepareStatement(
			"update WorkflowInstanceLink set groupId = ? where classPK = ?");

		ps.setLong(1, getGroupIdByCommerceOrderId(classPK));

		ps.setLong(2, classNameId);

		ps.execute();
	}

	private final Portal _portal;

}